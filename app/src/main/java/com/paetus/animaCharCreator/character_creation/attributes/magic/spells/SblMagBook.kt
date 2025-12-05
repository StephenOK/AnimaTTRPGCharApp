package com.paetus.animaCharCreator.character_creation.attributes.magic.spells

import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.character_creation.attributes.magic.SblMagic

/**
 * A magic book for use in a SBL character.
 *
 * @param charInstance full SBL character to reference back to
 * @param homeMagic SBL magic section for this character
 * @param spellList list of spells available to this book
 * @param opposed index number of the opposing element's book
 * @param bookIndex index of this book
 */
open class SblMagBook(
    val charInstance: SblChar,
    private val homeMagic: SblMagic,
    spellList: SpellList,
    val opposed: Int,
    private val bookIndex: Int
): MagicBook(spells = spellList, magic = homeMagic, opposingIndex = opposed) {
    /**
     * Sets the direct point investment in this book to the indicated value.
     *
     * @param pointBuy magic levels to invest into this book
     */
    override fun buyLevels(pointBuy: Int) {
        //initialize magic levels from previous levels
        var prevPoints = 0

        //count magic levels from previous levels
        charInstance.levelLoop(endLevel =  charInstance.lvl.intValue - 1){character ->
            prevPoints += character.magic.retrieveBooks()[bookIndex].pointsIn.intValue
        }

        //value for magic levels gained this level
        charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].pointsIn.intValue = pointBuy - prevPoints

        //update magic level total
        updateMagLevels()

        //apply appropriate primary status to the character record
        if(!isPrimary.value && !getOpposedPrimary() && pointBuy != 0)
            changePrimary(isTaking = true)

        else if(isPrimary.value && !hasInvestment() &&
            charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].pointsIn.intValue >= 0)
            changePrimary(isTaking = false)

        //remove individual spells that are acquired by this purchase
        individualSpells.forEach{spellIndex ->
            val spellLevel = (spellIndex + 1) * 2
            if(charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].individualSpells.contains(element = spellIndex) &&
                spellLevel <= getCap())
                charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].individualSpells.remove(element = spellIndex)
        }

        //update the spell list
        updateIndividualSpells()
    }

    /**
     * Purchase a spell individually by the given spell level.
     *
     * @param spellLevel level of the spell to individually purchase
     */
    override fun changeIndividualSpell(spellLevel: Int) {
        val spellIndex = (spellLevel/2) - 1

        //apply change if spell not taken or spell taken previously at this level
        if(!individualSpells.contains(spellIndex) ||
            charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].individualSpells.contains(spellIndex)) {
            //apply change to level
            charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].changeIndividualSpell(
                spellLevel = spellLevel
            )

            //clear future instances of this spell
            if(charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].individualSpells.contains(spellIndex))
                charInstance.levelLoop(
                    startLevel =  charInstance.lvl.intValue + 1,
                    endLevel = 20
                ){character ->
                    if(character.magic.retrieveBooks()[bookIndex].individualSpells.contains(spellIndex))
                        character.magic.retrieveBooks()[bookIndex].changeIndividualSpell(spellLevel = spellLevel)
                }
        }

        //update spell list
        updateIndividualSpells()

        //update primary investment if needed
        if(hasInvestment() && !isPrimary.value && !magic.retrieveBooks()[opposingIndex].isPrimary.value)
            changePrimary(isTaking = true)
        else if (!hasInvestment())
            changePrimary(isTaking = false)
    }

    /**
     * Attempts to add a free spell to this book's list.
     *
     * @param spell freespell to attempt to add to this book
     */
    override fun addFreeSpell(spell: FreeSpell) {
        //stop function if character possesses this spell
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

            //remove spell from the level record
            if(removedSpell != null)
                charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].freeSpells.remove(removedSpell)

            //add this spell to the level record
            charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].freeSpells.add(element = spell)

            //update the character's free spells
            updateFreeSpells()

            //remove this spell from any future level records
            charInstance.levelLoop(
                startLevel = charInstance.lvl.intValue + 1,
                endLevel = 20
            ){character ->
                //determine that this spell is taken by this character
                val bookWithSpell = character.magic.retrieveBooks()[10].charHasFreeSpell(freeSpell = spell)

                //remove spell if present
                bookWithSpell?.freeSpells?.remove(bookWithSpell.getFreeSpell(spell.level))
            }
        }
    }

    /**
     * Sets the total magic levels invested in this book.
     */
    override fun updateMagLevels(){
        //reset total magic levels spent
        pointsIn.intValue = 0

        //add magic levels from each character level
        charInstance.levelLoop{character ->
            pointsIn.intValue += character.magic.retrieveBooks()[bookIndex].pointsIn.intValue
        }

        //updates the primary state of all books
        homeMagic.updateBookPrimaries()
    }

    /**
     * Sets the primary status of this element to the indicated value.
     *
     * @param isTaking value to set the primary status to
     */
    override fun changePrimary(isTaking: Boolean) {
        if(hasInvestment() && isTaking){
            //remove necromancy's primary status, if needed
            if(magic.retrieveBooks()[10].isPrimary.value){
                //check that necromancy was not taken before this level
                var necPrevious = false
                charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){character ->
                    if(character.magic.retrieveBooks()[10].isPrimary.value) necPrevious = true
                }
                //remove necromancy from this and future levels
                if(!necPrevious)
                    magic.retrieveBooks()[10].changePrimary(isTaking = false)
            }
            //remove the other opposing book's primary status, if needed
            if(magic.retrieveBooks()[opposed].isPrimary.value) {
                //check that this was not taken before this level
                var opposePrevious = false
                charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){
                    if(it.magic.retrieveBooks()[opposed].isPrimary.value)
                        opposePrevious = true
                }
                //remove this book from this and future levels
                if(!opposePrevious){
                    magic.retrieveBooks()[opposed].isPrimary.value = false

                    charInstance.levelLoop(
                        startLevel = charInstance.lvl.intValue,
                        endLevel = 20
                    ){character ->
                        character.magic.retrieveBooks()[opposed].isPrimary.value = false
                    }
                }
            }

            //if no opposing books still have primary status
            if(!magic.retrieveBooks()[opposed].isPrimary.value && !magic.retrieveBooks()[10].isPrimary.value) {
                //reflect change in level record
                charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].isPrimary.value = true
                charInstance.levelLoop(
                    startLevel = charInstance.lvl.intValue + 1,
                    endLevel = 20
                ){character ->
                    character.magic.retrieveBooks()[opposed].isPrimary.value = false
                    character.magic.retrieveBooks()[10].isPrimary.value = false
                }
            }
        }
        else if(!isTaking && charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].isPrimary.value){
            //invest in either opposite book or necromancy if points in these books
            if(magic.retrieveBooks()[opposingIndex].hasInvestment())
                magic.retrieveBooks()[opposingIndex].changePrimary(isTaking = true)
            else if(magic.retrieveBooks()[10].hasInvestment() &&
                !(magic.retrieveBooks()[10] as NecromancyBook).getElseInvestment(exclude = bookIndex))
                magic.retrieveBooks()[10].changePrimary(isTaking = true)
            else if(!hasInvestment()) {
                //remove primary status from the level record
                charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].isPrimary.value =
                    false

                //update future level records
                charInstance.levelLoop(
                    startLevel = charInstance.lvl.intValue + 1,
                    endLevel = 20
                ){character ->
                    //if future levels have points in this book,
                    if(character.magic.retrieveBooks()[bookIndex].hasInvestment()){
                        //set it as primary
                        character.magic.retrieveBooks()[bookIndex].isPrimary.value = true
                        return@levelLoop
                    }
                    //if the opposing book has points in it,
                    else if(character.magic.retrieveBooks()[opposingIndex].hasInvestment()){
                        //set the opposing book as primary
                        character.magic.retrieveBooks()[opposingIndex].isPrimary.value = true
                        return@levelLoop
                    }
                    //if there are points in necromancy and no other books at this level,
                    else if(character.magic.retrieveBooks()[10].hasInvestment() &&
                        !(magic.retrieveBooks()[10] as NecromancyBook).getElseInvestment(exclude = bookIndex)){
                        //set necromancy as primary
                        character.magic.retrieveBooks()[10].isPrimary.value = true
                        return@levelLoop
                    }
                }
            }
        }

        //update all book primary values
        homeMagic.updateBookPrimaries()
    }

    /**
     * Determines if a free spell of the given level was selected at an earlier character level.
     *
     * @param spellLevel level of the free spell to be checked
     * @return true if spell from earlier level
     */
    override fun freeSpellEarlier(
        spellLevel: Int
    ): Boolean{
        //initialize output
        var output = false

        //initialize magic level counter
        var prevCap = 0

        //for each level up to this point
        charInstance.levelLoop(
            endLevel = charInstance.lvl.intValue - 1
        ){character ->
            //increment the point counter
            prevCap += character.magic.retrieveBooks()[bookIndex].pointsIn.intValue

            //return found free spell in bought levels or individual purchases
            if(spellLevel <= prevCap ||
                character.magic.retrieveBooks()[bookIndex].individualSpells.contains(spellLevel/2 - 1)) {
                output = true
                return@levelLoop
            }
        }

        //return final result
        return output
    }

    /**
     * Updates the primary status of the magic book.
     */
    override fun updatePrimary(){
        //reset primary state
        isPrimary.value = false

        //check levels for primary status
        charInstance.levelLoop{character ->
            if(character.magic.retrieveBooks()[bookIndex].isPrimary.value)
                isPrimary.value = true
        }
    }

    /**
     * Update the individual spells taken by the character.
     */
    fun updateIndividualSpells(){
        //remove all individual spells
        individualSpells.clear()

        //add individual spells taken at each level
        charInstance.levelLoop{character ->
            character.magic.retrieveBooks()[bookIndex].individualSpells.forEach{spellIndex ->
                val spellLevel = (spellIndex + 1) * 2
                if(spellLevel > getCap()) individualSpells.add(spellIndex)
            }
        }
    }

    /**
     * Update the free spells taken by the character.
     */
    fun updateFreeSpells(){
        //remove all free spells taken
        freeSpells.clear()

        //add individual free spells taken at each level
        charInstance.levelLoop{character ->
            freeSpells.addAll(character.magic.retrieveBooks()[bookIndex].freeSpells)
        }
    }

    /**
     * Gets whether the points spent in this book are valid for an SBL character.
     *
     * @return true if points not removed
     */
    override fun validBookGrowth(): Boolean {
        return charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].pointsIn.intValue >= 0
    }

    /**
     * Updates all items affected by a character's level change.
     */
    fun levelUpdate(){
        updateMagLevels()
        updatePrimary()
        updateIndividualSpells()
        updateFreeSpells()
    }
}