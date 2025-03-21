package com.paetus.animaCharCreator.character_creation.attributes.summoning

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Object that manages one of the SBL Character's summoning abilities.
 *
 * @param charInstance object that manages all of the character's data
 * @param summoningIndex which index this item's corresponding elements are held in
 */
class SblSummonAbility(
    val charInstance: SblChar,
    val summoningIndex: Int,
    val stringRef: Int
): SummonAbility(charInstance){
    /**
     * Set the number of points purchased in this item.
     *
     * @param pointPurchase number of points to buy for this section
     */
    override fun setBuyVal(pointPurchase: Int) {
        //get the number of points from previous levels
        var prevInput = 0
        charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){character ->
            prevInput += character.summoning.allSummoning()[summoningIndex].buyVal.intValue
        }

        //set amount of points added to this level
        charInstance.getCharAtLevel().summoning.allSummoning()[summoningIndex].buyVal.intValue = pointPurchase - prevInput

        //update point totals
        updateAbility()
    }

    /**
     * Updates the points spent in this item.
     */
    fun updateAbility(){
        //reset points bought
        buyVal.intValue = 0

        //add points bought at each level
        charInstance.levelLoop{character ->
            buyVal.intValue += character.summoning.allSummoning()[summoningIndex].buyVal.intValue
        }

        //update final total
        updateTotal()

        //update points spent
        charInstance.updateTotalSpent()
    }

    /**
     * Refresh the number of points in this stat from levels.
     */
    override fun updateLevelTotal() {
        //get points from classes
        levelTotal.intValue =
            //sum up each level's class bonus
            if(charInstance.lvl.intValue != 0){
                var output = 0

                charInstance.levelLoop(startLevel = 1){
                    output += it.summoning.allSummoning()[summoningIndex].pointsPerLevel.intValue
                }

                output
            }
            //get half of first level's bonus
            else charInstance.charRefs[0]!!.summoning.allSummoning()[summoningIndex].pointsPerLevel.intValue/2

        updateTotal()

        charInstance.updateTotalSpent()
    }

    /**
     * Determine if the points bought are not negative in this level.
     */
    fun legalGrowth(): Boolean{
        return charInstance.getCharAtLevel().summoning.allSummoning()[summoningIndex].buyVal.intValue >= 0
    }

    /**
     * Updates the character's ability for a level change.
     */
    fun levelUpdate(){
        updateAbility()
    }
}