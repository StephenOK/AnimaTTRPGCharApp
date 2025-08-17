package com.paetus.animaCharCreator.character_creation.attributes.magic.spells

import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.character_creation.attributes.magic.Magic
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
        charInstance.getCharAtLevel().magic.retrieveBooks()[10].buyLevels(pointBuy = pointBuy - prevPoints)

        //update book level totals
        updateMagLevels()
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
            if(hasInvestment() && !isPrimary.value && !getOpposedInvestment())
                changePrimary(isTaking = true)
            else if(!hasInvestment())
                changePrimary(isTaking = false)
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
                    charInstance.getCharAtLevel().magic.retrieveBooks()[index].isPrimary.value = false
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

            //initialize opposing book pointer
            var index = 0

            while(index < 10){
                //retrieve the two opposing books
                val book1 = magic.retrieveBooks()[index++]
                val book2 = magic.retrieveBooks()[index++]

                //if only first book invested in, set first book as primary element
                if(book1.hasInvestment() && !book2.hasInvestment())
                    book1.changePrimary(true)

                //if only second book invested in, set second book as primary element
                else if(book2.hasInvestment() && !book1.hasInvestment())
                    book2.changePrimary(true)
                //if both books invested in
                else if(book1.hasInvestment() && book2.hasInvestment()){
                    //set second book as primary if it has more points, more individual spells, or if the only one with a natural bonus
                    if(book2.pointsIn.intValue > book1.pointsIn.intValue ||
                        book2.individualSpells.size > book1.individualSpells.size ||
                        book2.isNatural.value && !book1.isNatural.value)
                        book2.changePrimary(true)
                    //otherwise, set first book as primary
                    else
                        book1.changePrimary(true)
                }
            }
        }

        (magic as SblMagic).updateBookPrimaries()
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