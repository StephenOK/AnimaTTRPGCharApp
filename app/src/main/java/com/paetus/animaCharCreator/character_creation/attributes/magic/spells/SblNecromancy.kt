package com.paetus.animaCharCreator.character_creation.attributes.magic.spells

import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.character_creation.attributes.magic.SblMagic
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.NecromancySpells

/**
 * Save by level version of the character's Necromancy magic book.
 *
 * @param charInstance reference to the character data
 * @param necromancy list of spells in the Necromancy book
 * @param magic reference to the character's magical qualities
 */
class SblNecromancy(
    val charInstance: SblChar,
    necromancy: NecromancySpells,
    magic: SblMagic
): NecromancyBook(necromancy, magic){
    /**
     * Sets the direct point investment in this book to the indicated value.
     *
     * @param pointBuy magic levels to invest into this book
     */
    override fun buyLevels(pointBuy: Int) {
        //count points invested in levels previous to this one
        var prevPoints = 0
        charInstance.levelLoop(endLevel =  charInstance.lvl.intValue - 1){character ->
            prevPoints += character.magic.retrieveBooks()[10].pointsIn.intValue
        }

        //determine number of points added to this level
        charInstance.getCharAtLevel().magic.retrieveBooks()[10].pointsIn.intValue = pointBuy - prevPoints

        //update book level totals
        updateMagLevels()

        //apply primary status if no other book is primary and points are invested
        if(!isPrimary.value && !getOpposedPrimary() && pointBuy != 0)
            changePrimary(isTaking = true)

        //remove primary status if no points are invested
        else if(isPrimary.value && !hasInvestment())
            changePrimary(isTaking = false)

        //update individual spells taken
        individualSpells.forEach{spellIndex ->
            val spellLevel = (spellIndex + 1)/2
            if(charInstance.getCharAtLevel().magic.retrieveBooks()[10].individualSpells.contains(element = spellIndex) &&
                spellLevel <= getCap())
                charInstance.getCharAtLevel().magic.retrieveBooks()[10].individualSpells.remove(element = spellIndex)
        }

        //update spell list
        updateIndividualSpells()
    }

    /**
     * Purchase a spell individually by the given spell level.
     *
     * @param spellLevel level of the spell to individually purchase
     */
    override fun changeIndividualSpell(spellLevel: Int) {
        //convert the spell's level to its index
        val spellIndex = (spellLevel/2) - 1

        //apply change if spell not taken or spell taken previously at this level
        if(!individualSpells.contains(element = spellIndex) ||
            charInstance.getCharAtLevel().magic.retrieveBooks()[10].individualSpells.contains(spellIndex)){
            //apply change to the level
            charInstance.getCharAtLevel().magic.retrieveBooks()[10].changeIndividualSpell(
                spellLevel = spellLevel
            )

            //clear future instances of this spell
            if(charInstance.getCharAtLevel().magic.retrieveBooks()[10].individualSpells.contains(element = spellIndex))
                charInstance.levelLoop(
                    startLevel = charInstance.lvl.intValue + 1,
                    endLevel = 20
                ){character ->
                    if(character.magic.retrieveBooks()[10].individualSpells.contains(element = spellIndex))
                        character.magic.retrieveBooks()[10].changeIndividualSpell(spellLevel = spellLevel)
                }

            //update spell list
            updateIndividualSpells()

            //update primary investment
            if(hasInvestment() && !isPrimary.value && !getOpposedPrimary())
                changePrimary(isTaking = true)
            else if(!hasInvestment())
                changePrimary(isTaking = false)
        }
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
                charInstance.getCharAtLevel().magic.retrieveBooks()[10].freeSpells.remove(removedSpell)

            //add this spell to the level record
            charInstance.getCharAtLevel().magic.retrieveBooks()[10].freeSpells.add(element = spell)

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
     * Sets the primary status of this book in ways unique to the necromancy book.
     *
     * @param isTaking value to set the primary status to
     */
    override fun changePrimary(isTaking: Boolean) {
        //if taking Necromancy and points are invested in it
        if(hasInvestment() && isTaking){
            //initialize flag for ability to add necromancy
            var canAddNecromancy = true

            //determine that no other book has primary status before this level
            for(index in 0..9){
                //determined by presence of primary state and if it's not taken this level
                if(magic.retrieveBooks()[index].isPrimary.value &&
                    !charInstance.getCharAtLevel().magic.retrieveBooks()[index].isPrimary.value)
                    canAddNecromancy = false
            }

            //if character can take necromancy
            if(canAddNecromancy){
                //remove all primary statuses from other books
                for(index in 0..9){
                    charInstance.magic.retrieveBooks()[index].isPrimary.value = false
                    charInstance.levelLoop(
                        startLevel = charInstance.lvl.intValue,
                        endLevel = 20
                    ){character ->
                        character.magic.retrieveBooks()[index].isPrimary.value = false
                    }
                }

                //add necromancy to this character
                charInstance.getCharAtLevel().magic.retrieveBooks()[10].isPrimary.value = true
            }
        }

        //if removing primary state and status taken at this level
        else if(!isTaking && isPrimary.value && charInstance.getCharAtLevel().magic.retrieveBooks()[10].isPrimary.value){
            //remove state from SBL record and level record
            isPrimary.value = false
            charInstance.getCharAtLevel().magic.retrieveBooks()[10].isPrimary.value = false

            //initialize list of checked element pairs
            val exceptions = mutableListOf<Int>()

            //retrieve any checked element pairs for this level
            exceptions.addAll(elements = primaryDistribute(character = charInstance.getCharAtLevel()))

            charInstance.levelLoop(
                startLevel = charInstance.lvl.intValue + 1,
                endLevel = 20
            ){character ->
                //get this level's necromancy book
                val levelNec = character.magic.retrieveBooks()[10] as NecromancyBook

                //apply primary to this book's necromancy if applicable
                if(exceptions.isEmpty() && levelNec.hasInvestment())
                    levelNec.isPrimary.value = true

                //apply element pair primaries as needed at this level
                else if(levelNec.getOpposedInvestment())
                    exceptions.addAll(
                        elements = primaryDistribute(
                            character = character,
                            exceptions = exceptions
                        )
                    )
            }
        }

        //update primary records
        (magic as SblMagic).updateBookPrimaries()
    }

    /**
     * Applies primary state to element pairs after necromancy has its primary status removed.
     *
     * @param character item holding the changing data
     * @param exceptions any indicated element pairs that do not need checking
     * @return list of elements that were affected by this run
     */
    private fun primaryDistribute(
        character: BaseCharacter,
        exceptions: List<Int> = listOf()
    ): MutableList<Int>{
        //initialize opposing book pointer
        var index = 0

        //initialize output
        val output = mutableListOf<Int>()

        while (index < 10) {
            val firstIndex = index++
            val secondIndex = index++

            //retrieve the two opposing books
            val book1 = character.magic.retrieveBooks()[firstIndex]
            val book2 = character.magic.retrieveBooks()[secondIndex]

            if(!exceptions.contains(firstIndex) && !exceptions.contains(secondIndex)) {
                //if only first book invested in, set first book as primary element
                if (book1.hasInvestment() && !book2.hasInvestment()) {
                    book1.changePrimary(true)
                    output.add(firstIndex)
                }

                //if only second book invested in, set second book as primary element
                else if (book2.hasInvestment() && !book1.hasInvestment()) {
                    book2.changePrimary(true)
                    output.add(secondIndex)
                }

                //if both books invested in
                else if (book1.hasInvestment() && book2.hasInvestment()) {
                    //set second book as primary if it has more points, more individual spells, or if the only one with a natural bonus
                    if (book2.pointsIn.intValue > book1.pointsIn.intValue ||
                        book2.individualSpells.size > book1.individualSpells.size ||
                        book2.isNatural.value && !book1.isNatural.value
                    ) {
                        book2.changePrimary(true)
                        output.add(secondIndex)
                    }

                    //otherwise, set first book as primary
                    else {
                        book1.changePrimary(true)
                        output.add(firstIndex)
                    }
                }
            }
        }

        return output
    }

    /**
     * Sets the total magic levels invested in this book.
     */
    override fun updateMagLevels(){
        //reset point total
        pointsIn.intValue = 0

        //add points from all accessible levels
        charInstance.levelLoop{character ->
            pointsIn.intValue += character.magic.retrieveBooks()[10].pointsIn.intValue
        }

        //update primary state
        updatePrimary()
    }

    /**
     * Updates the primary status of the magic book.
     */
    override fun updatePrimary(){
        //reset primary state
        isPrimary.value = false

        //check levels for primary status
        charInstance.levelLoop{character ->
            if(character.magic.retrieveBooks()[10].isPrimary.value)
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
            individualSpells.addAll(elements = character.magic.retrieveBooks()[10].individualSpells)
        }
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
            prevCap += character.magic.retrieveBooks()[10].pointsIn.intValue

            //return found free spell in bought levels or individual purchases
            if(spellLevel <= prevCap ||
                character.magic.retrieveBooks()[10].individualSpells.contains(spellLevel)) {
                output = true
                return@levelLoop
            }
        }

        //return final result
        return output
    }

    /**
     * Updates the free spells taken by the character.
     */
    fun updateFreeSpells(){
        freeSpells.clear()

        //add individual free spells taken at each level
        charInstance.levelLoop{character ->
            freeSpells.addAll(elements = character.magic.retrieveBooks()[10].freeSpells)
        }
    }

    /**
     * Gets whether the points spent in this book are valid for an SBL character.
     *
     * @return true if points not removed
     */
    override fun validBookGrowth(): Boolean {
        return charInstance.getCharAtLevel().magic.retrieveBooks()[10].pointsIn.intValue >= 0
    }

    /**
     * Update required values for a level change.
     */
    fun levelUpdate(){
        updateMagLevels()
        updatePrimary()
        updateIndividualSpells()
        updateFreeSpells()
    }
}