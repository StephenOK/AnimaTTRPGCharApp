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
        charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].buyLevels(pointBuy = pointBuy - prevPoints)

        //update magic level total
        updateMagLevels()
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

        //updates the primary state of this book
        updatePrimary()
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
                charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){
                    if(it.magic.retrieveBooks()[10].isPrimary.value) necPrevious = true
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
                if(!opposePrevious) {
                    magic.retrieveBooks()[opposed].isPrimary.value = false

                    charInstance.levelLoop(
                        startLevel = charInstance.lvl.intValue,
                        endLevel = 20
                    ){
                        it.magic.retrieveBooks()[opposed].isPrimary.value = false
                    }
                }
            }

            //if no opposing books still have primary status
            if(!magic.retrieveBooks()[opposed].isPrimary.value && !magic.retrieveBooks()[10].isPrimary.value)
                //reflect change in level record
                charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].isPrimary.value = true
        }
        else if(!isTaking && charInstance.getCharAtLevel().magic.retrieveBooks()[bookIndex].isPrimary.value){
            //invest in either opposite book or necromancy if points in these books
            if(magic.retrieveBooks()[opposingIndex].hasInvestment())
                magic.retrieveBooks()[opposingIndex].changePrimary(isTaking = true)
            else if(magic.retrieveBooks()[10].hasInvestment())
                magic.retrieveBooks()[10].changePrimary(isTaking = true)
        }

        //update all book primary values
        homeMagic.updateBookPrimaries()
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
    }
}