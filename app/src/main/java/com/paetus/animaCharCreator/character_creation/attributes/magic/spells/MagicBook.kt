package com.paetus.animaCharCreator.character_creation.attributes.magic.spells

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.magic.Magic
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.FreeSpells
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Item that holds data for a character's investment in a particular magical element.
 *
 * @param spells actual spells associated with this element
 * @param magic the character's entire magical capacity section
 * @param opposingIndex index of the element that is opposite to this one
 */
open class MagicBook(
    val spells: SpellList,
    val magic: Magic,
    val opposingIndex: Int
){
    //initialize points in this book
    val pointsIn = mutableIntStateOf(value = 0)

    //initialize list of individually purchased spells
    val individualSpells = mutableListOf<Int>()

    //initialize the state of this book being a primary element
    val isPrimary = mutableStateOf(value = false)

    //initialize natural path taken for this element
    val isNatural = mutableStateOf(false)

    //initialize free spells associated with this element
    val freeSpells = mutableListOf<FreeSpell>()

    /**
     * Updates the primary status of the magic book.
     */
    open fun updatePrimary(){}

    /**
     * Gets whether the points spent in this book are valid for an SBL character.
     *
     * @return true if points not removed
     */
    open fun validBookGrowth(): Boolean{return true}

    /**
     * Sets the total magic levels invested in this book.
     */
    open fun updateMagLevels(){}

    /**
     * Sets the primary status of this element to the indicated value.
     *
     * @param isTaking value to set the primary status to
     */
    open fun changePrimary(isTaking: Boolean){
        if(hasInvestment() && isTaking){
            //remove necromancy's primary status, if needed
            if(magic.retrieveBooks()[10].isPrimary.value)
                magic.retrieveBooks()[10].changePrimary(isTaking = false)

            //remove the other opposing book's primary status, if needed
            else if(magic.retrieveBooks()[opposingIndex].isPrimary.value)
                magic.retrieveBooks()[opposingIndex].isPrimary.value = false

            //set this element as the primary one
            isPrimary.value = true
        }
        else if(!isTaking && isPrimary.value){
            //remove this item's primary flag
            isPrimary.value = false

            //invest in either opposite book or necromancy if points in these books
            if(magic.retrieveBooks()[opposingIndex].hasInvestment())
                magic.retrieveBooks()[opposingIndex].isPrimary.value = true
            else if(magic.retrieveBooks()[10].hasInvestment())
                magic.retrieveBooks()[10].changePrimary(isTaking = true)
        }
    }

    /**
     * Sets the direct point investment in this book to the indicated value.
     *
     * @param pointBuy magic levels to invest into this book
     */
    open fun buyLevels(pointBuy: Int){
        //set the invested point value
        pointsIn.intValue = pointBuy

        //determine if any opposing books are primary elements
        val opposingInvestment = getOpposedInvestment()

        //set this element as primary if it has points and no opposing element is primary
        if(!isPrimary.value && !opposingInvestment && pointBuy != 0)
            changePrimary(isTaking = true)

        //remove own primary status if no points in this item
        else if(isPrimary.value && !hasInvestment())
            changePrimary(isTaking = false)

        //remove individual spells bought that now are gained from direct investment
        val removalList = mutableListOf<Int>()
        individualSpells.forEach{spellIndex ->
            val spellLevel = (spellIndex + 1) * 2
            if(spellLevel <= getCap()) removalList.add(element = spellIndex)
        }
        individualSpells.removeAll(elements = removalList)
    }

    /**
     * Purchase a spell individually by the given spell level.
     *
     * @param spellLevel level of the spell to individually purchase
     */
    fun changeIndividualSpell(
        spellLevel: Int
    ){
        //convert the spell's level to its index
        val spellIndex = (spellLevel/2) - 1

        //if spell is not currently purchased
        if(spellIndex !in individualSpells) {
            //if spell is not acquired by point investment
            if (spellIndex > getCap()) {
                //add spell to individually purchased record
                individualSpells.add(element = spellIndex)

                //apply primary status if needed
                if(!isPrimary.value)
                    changePrimary(isTaking = true)
            }
        }

        //remove spell if it is already purchased
        else{
            individualSpells.remove(element = spellIndex)

            //remove primary status, if needed
            if(!hasInvestment())
                changePrimary(isTaking = false)
        }
    }

    /**
     * Attempts to add a free spell to this book's list.
     *
     * @param spell freespell to attempt to add to this book
     */
    fun addFreeSpell(spell: FreeSpell){
        //if the character does not already possess this free spell
        if(charHasFreeSpell(freeSpell = spell) == null){
            //search for a freespell in this book of equivalent level
            var removedSpell: FreeSpell? = null
            freeSpells.forEach{freeSpell ->
                //record spell to remove
                if(freeSpell.level == spell.level){
                    removedSpell = freeSpell
                    return@forEach
                }
            }

            //remove it if found
            if(removedSpell != null) freeSpells.remove(removedSpell)

            //add this spell to the book
            freeSpells.add(element = spell)
        }

    }

    /**
     * Sets the natural path status of this book.
     *
     * @param isNat what value to set the natural path
     */
    fun setNatural(isNat: Boolean){
        //set the natural value
        isNatural.value = isNat

        //check if any opposing book is a primary element
        val hasOpposing = magic.retrieveBooks()[opposingIndex].isPrimary.value ||
                magic.retrieveBooks()[10].isPrimary.value

        //add primary element status if needed
        if(isNat && !hasOpposing) isPrimary.value = true
        //remove primary element status if needed
        else if(!hasInvestment() && isPrimary.value) isPrimary.value = false
    }

    /**
     * Determines if this book has any points invested in it.
     *
     * @return true if any points are invested
     */
    fun hasInvestment(): Boolean{
        //return true for points in book or individual spells purchased
        return pointsIn.intValue > 0 || individualSpells.isNotEmpty() || isNatural.value
    }

    /**
     * Check if the character has the indicated free spell.
     *
     * @param freeSpell spell to check for character's ownership of
     * @return magic book the free spell belongs to
     */
    open fun charHasFreeSpell(freeSpell: FreeSpell): MagicBook?{
        return magic.retrieveBooks()[10].charHasFreeSpell(freeSpell = freeSpell)
    }

    /**
     * Gets the highest level spell the character has access to from points and natural path bonus.
     *
     * @return level maximum from these inputs
     */
    fun getCap(): Int{
        //half points invested if not primary
        return (if(isPrimary.value) pointsIn.intValue else pointsIn.intValue/2) +
                //add 40 to level if natural path applied
                (if(isNatural.value) 40 else 0)
    }

    /**
     * Determines the number of magic levels spent in this book.
     *
     * @return total magic levels spent
     */
    fun getMagLevels(): Int{
        //initialize output
        var output = 0

        //add points for each individually purchased spell
        individualSpells.forEach{spellIndex ->
            output += when((spellIndex + 1) * 2){
                in 0..10 -> 2
                in 11..20 -> 4
                in 21..30 -> 6
                in 31..40 -> 8
                in 41..50 -> 10
                in 51..60 -> 12
                in 61..70 -> 14
                in 71..80 -> 16
                in 81..90 -> 18
                else -> 20
            }
        }

        //double value if book is not primary book
        if(!isPrimary.value) output * 2

        //return individual cost with directly invested points
        return output + pointsIn.intValue
    }

    /**
     * Removes individual spells and free spells when Magic Ties disadvantage is taken.
     */
    fun magicTiesClear(){
        freeSpells.clear()
        individualSpells.clear()
    }

    /**
     * Retrieves this book's free spell of the given level.
     *
     * @param level free spell's level to find
     * @return either the spell of that level or a placeholder object
     */
    private fun getFreeSpell(level: Int): FreeSpell{
        //search for the free spell based on the level
        freeSpells.forEach{freeSpell ->
            if(freeSpell.level == level) return freeSpell
        }

        //return empty spell for nothing found
        return FreeSpell(
            saveName = "PlaceHolder",
            name = R.string.emptyItem,
            isActive = false,
            level = level,
            zCost = 0,
            effect = R.string.emptyItem,
            addedEffect = R.string.emptyItem,
            zMax = 0,
            maintenance = null,
            isDaily = false,
            type = listOf(),
            forbiddenElements = listOf(spells.element)
        )
    }

    /**
     * Gets if the opposite element has any points invested in it.
     *
     * @return true if points in opposite element's book
     */
    open fun getOpposedInvestment(): Boolean{
        return (magic.retrieveBooks()[opposingIndex].hasInvestment()) ||
                magic.retrieveBooks()[10].hasInvestment()
    }

    /**
     * Retrieves all spells possessed by the character in this book.
     *
     * @return all spells available from this book
     */
    fun getSpells(): List<Spell>{
        //initialize final list
        val output = mutableListOf<Spell>()

        //retrieve spells from point investment and natural bonus
        for(index in 0 until getCap()/2){
            //add elemental spell
            output +=
                if(spells.fullBook[index] != null) spells.fullBook[index]!!
                //add free spell
                else getFreeSpell(level = (index + 1) * 2)
        }

        //retrieve individually purchased spells
        individualSpells.forEach{index ->
            //add elemental spell
            output +=
                if(spells.fullBook[index] != null) spells.fullBook[index]!!
                //add free spell
                else getFreeSpell(level = (index + 1) * 2)
        }

        //produce final output
        return output.toList()
    }

    /**
     * Clears all data in this item.
     */
    fun clear(){
        changePrimary(isTaking = false)
        buyLevels(pointBuy = 0)
        individualSpells.clear()
        freeSpells.clear()
        magicTiesClear()
    }

    /**
     * Loads data from file into this item.
     *
     * @param fileReader file input reader to get data from
     * @param freeSpells reference to free spell data
     */
    fun load(
        fileReader: BufferedReader,
        freeSpells: FreeSpells
    ){
        //set the magic level investment
        buyLevels(pointBuy = fileReader.readLine().toInt())

        //apply individual spells purchased
        (0 until fileReader.readLine().toInt()).forEach{
            changeIndividualSpell(spellLevel = (fileReader.readLine().toInt() + 1) * 2)
        }

        //apply recorded free spells to this book
        (0 until fileReader.readLine().toInt()).forEach{
            //get the free spell base from this data
            val spellBase = freeSpells.findFreeSpell(saveName = fileReader.readLine())

            //add the free spell base with the appropriate level
            addFreeSpell(spell = FreeSpell(
                saveName = spellBase.saveName,
                name = spellBase.name,
                isActive = spellBase.isActive,
                level = fileReader.readLine().toInt(),
                zCost = spellBase.zCost,
                effect = spellBase.effect,
                addedEffect = spellBase.addedEffect,
                zMax = spellBase.zMax,
                maintenance = spellBase.maintenance,
                isDaily = spellBase.isDaily,
                type = spellBase.type,
                forbiddenElements = spellBase.forbiddenElements
            ))
        }

        //apply primary status data
        isPrimary.value = fileReader.readLine().toBoolean()
    }

    /**
     * Writes this item's data to file.
     *
     * @param byteArray output stream to write data to
     */
    fun write(byteArray: ByteArrayOutputStream){
        //write invested point data
        writeDataTo(writer = byteArray, input = pointsIn.intValue)

        //write individually purchased spell data
        writeDataTo(writer = byteArray, input = individualSpells.size)
        individualSpells.forEach{spellIndex ->
            writeDataTo(writer = byteArray, spellIndex)
        }

        //write free spell data in this book
        writeDataTo(writer = byteArray, input = freeSpells.size)
        freeSpells.forEach{freeSpell ->
            writeDataTo(writer = byteArray, input = freeSpell.saveName)
            writeDataTo(writer = byteArray, input = freeSpell.level)
        }

        //write primary status of this book
        writeDataTo(writer = byteArray, input = isPrimary.value)
    }
}