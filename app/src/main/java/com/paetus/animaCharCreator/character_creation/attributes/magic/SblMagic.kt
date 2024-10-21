package com.paetus.animaCharCreator.character_creation.attributes.magic

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Component that holds a SBL Character's magical abilities.
 *
 * @param charInstance object that holds all of the character's data
 */
class SblMagic(val charInstance: SblChar): Magic(charInstance) {
    /**
     * Gets the class's zeon point DP cost.
     */
    override fun getZeonPointCost(): Int {
        return charInstance.getCharAtLevel().magic.getZeonPointCost()
    }

    /**
     * Gets the class's zeon accumulation DP cost.
     */
    override fun getZeonAccCost(): Int {
        return charInstance.getCharAtLevel().magic.getZeonAccCost()
    }

    /**
     * Gets the class's magic projection DP cost.
     */
    override fun getMagProjCost(): Int {
        return charInstance.getCharAtLevel().magic.getMagProjCost()
    }

    /**
     * Set the number of Zeon points the character has bought.
     *
     * @param zeonBuy the number of Zeon points bought for the character
     */
    override fun buyZeon(zeonBuy: Int) {
        //determine points from previous levels
        var preInputVal = 0
        charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){character ->
            preInputVal += character.magic.boughtZeon.intValue
        }

        //apply new points
        charInstance.getCharAtLevel().magic.buyZeon(zeonBuy = zeonBuy - preInputVal)

        //update zeon point total
        updateBoughtZeon()
    }

    /**
     * The user attempts to buy the given amount of accumulation.
     *
     * @param accBuy the accumulation amount bought for the character
     */
    override fun buyZeonAcc(accBuy: Int) {
        //determine points from previous levels
        var preInputVal = 0
        charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){character ->
            preInputVal += character.magic.zeonAccMult.intValue - 1
        }

        //apply new points
        charInstance.getCharAtLevel().magic.buyZeonAcc(accBuy = accBuy - preInputVal)

        //update zeon accumulation
        updateZeonAcc()
    }

    /**
     * Set the amount of magic projection bought by the user.
     *
     * @param projBuy the projection amount bought for the character
     */
    override fun buyMagProj(projBuy: Int) {
        //determine points from previous levels
        var preInputVal = 0
        charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){character ->
            preInputVal += character.magic.boughtMagProjection.intValue
        }

        //apply new points
        charInstance.getCharAtLevel().magic.buyMagProj(projBuy = projBuy - preInputVal)

        //update magic projection
        updateMagProj()
    }

    /**
     * Updates the number of bought zeon points.
     */
    private fun updateBoughtZeon(){
        //reset bought value
        boughtZeon.intValue = 0

        //add points from each level
        charInstance.levelLoop{character ->
            boughtZeon.intValue += character.magic.boughtZeon.intValue
        }

        //reevaluate total zeon points
        calcMaxZeon()
        charInstance.updateTotalSpent()
    }

    /**
     * Updates the number of zeon accumulation points bought.
     */
    private fun updateZeonAcc(){
        //reset bought value
        zeonAccMult.intValue = 1

        //add points from each level
        charInstance.levelLoop{character ->
            zeonAccMult.intValue += character.magic.zeonAccMult.intValue - 1
        }

        //reevaluate total accumulation
        calcZeonAcc()
        charInstance.updateTotalSpent()
    }

    /**
     * Updates the number of magic projection points bought.
     */
    private fun updateMagProj(){
        //reset bought value
        boughtMagProjection.intValue = 0

        //add points from each level
        charInstance.levelLoop{character ->
            boughtMagProjection.intValue += character.magic.boughtMagProjection.intValue
        }

        //reevaluate total projection
        calcMagProj()
        charInstance.updateTotalSpent()
    }

    /**
     * Update the total number of Zeon points gained from levels.
     */
    override fun updateZeonFromClass() {
        //get total from classes
        zeonFromClass.intValue =
            //sum each level's class bonus
            if(charInstance.lvl.intValue != 0){
                var output = 0

                charInstance.levelLoop(startLevel = 1){
                    output += it.magic.zeonPerLevel.intValue
                }

                output
            }
            //get half of first level's class bonus
            else charInstance.charRefs[0]!!.magic.zeonPerLevel.intValue/2

        calcMaxZeon()
    }

    /**
     * Recalculate the character's maximum Zeon.
     */
    override fun calcMaxZeon() {
        //reset points to base and class points
        zeonMax.intValue = baseZeon.intValue + zeonFromClass.intValue

        //add zeon points from each level
        charInstance.levelLoop{character ->
            zeonMax.intValue += (character.magic.boughtZeon.intValue * 5)
        }
    }

    /**
     * Updates the character's values relevant to level changes.
     */
    fun levelUpdate(){
        updateBoughtZeon()
        updateZeonAcc()
        updateMagProj()
    }

    /**
     * Determines if the spent points in zeon points are valid.
     *
     * @return true if no loss in points
     */
    fun validPointGrowth(): Boolean{return charInstance.getCharAtLevel().magic.boughtZeon.intValue >= 0}

    /**
     * Determines if the spent points in zeon accumulation are valid.
     *
     * @return true if no loss in points
     */
    fun validAccGrowth(): Boolean{return charInstance.getCharAtLevel().magic.zeonAccMult.intValue >= 1}

    /**
     * Determine if the spent points in magic projection are valid.
     *
     * @return true if no loss in points
     */
    fun validProjGrowth(): Boolean{return charInstance.getCharAtLevel().magic.boughtMagProjection.intValue >= 0}
}