package com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Subclass of SecondaryCharacteristic for use in a SBL character.
 *
 * @param parent full list that holds this object
 */
open class SblSecondaryCharacteristic(
    private val parent: SblSecondaryList
): SecondaryCharacteristic(parent){
    /**
     * Retrieves the index pointer for this item in the secondary list.
     *
     * @return item's index in the secondary list
     */
    fun getIndex(): Int{
        return parent.getAllSecondaries().indexOf(this)
    }

    /**
     * Setter for points applied by user.
     *
     * @param pointInput amount to set the points applied to
     */
    override fun setPointsApplied(pointInput: Int) {
        val charInstance = parent.sblChar

        //set current level value
        charInstance.getCharAtLevel().secondaryList.getAllSecondaries()[getIndex()].pointsApplied.intValue = pointInput - getPreviousPoints()
        charInstance.getCharAtLevel().secondaryList.getAllSecondaries()[getIndex()].updateDevSpent()

        //if character is not level 0
        if(charInstance.lvl.intValue != 0) {
            //get previous level's record
            val previousLevel = charInstance.charRefs[charInstance.lvl.intValue - 1]

            //set natural bonus if points applied aren't zero and previous level has a natural bonus
            if (pointInput - getPreviousPoints() == 0 && previousLevel!!.secondaryList.getAllSecondaries()[getIndex()].bonusApplied.value)
                setNatBonus(true)
        }

        //if no points applied to stat
        if(pointInput + getPreviousPoints() == 0)
            //go through all level records
            parent.sblChar.levelLoop(
                startLevel = parent.charInstance.lvl.intValue + 1,
                endLevel = 20
            ){
                //remove any applied natural bonus
                val secondary = it.secondaryList.getAllSecondaries()[getIndex()]
                if(secondary.bonusApplied.value && getPreviousPoints(level = parent.sblChar.charRefs.indexOf(it)) == 0)
                    secondary.setNatBonus(false)
            }

        pointsAppliedUpdate()
    }

    /**
     * Set the class points obtained for this characteristic.
     *
     * @param classBonus value to set the class points to
     */
    override fun setClassPointsPerLevel(classBonus: Int) {
        parent.sblChar.getCharAtLevel().secondaryList.getAllSecondaries()[getIndex()].setClassPointsPerLevel(classBonus)
        classTotalRefresh()
    }

    /**
     * Updates the number of points gained from levels for this characteristic.
     */
    override fun classTotalRefresh() {
        //get new class point value
        classPointTotal.intValue =
            //get each level's individual value and apply to output
            if(parent.charInstance.lvl.intValue != 0){
                var output = 0

                (parent.charInstance as SblChar).levelLoop(startLevel = 1){character ->
                    output += character.secondaryList.getAllSecondaries()[getIndex()].classPointsPerLevel.intValue
                }

                output
            }
            //get half of the first level's class bonus
            else (parent.charInstance as SblChar).charRefs[0]!!.secondaryList.getAllSecondaries()[getIndex()].classPointsPerLevel.intValue/2

        //update secondary total
        refreshTotal()
    }

    /**
     * Setter for natural bonus.
     *
     * @param natBonus true if applying a natural bonus to the characteristic
     */
    override fun setNatBonus(natBonus: Boolean) {
        //apply natural bonus to record
        parent.sblChar.getCharAtLevel().secondaryList.getAllSecondaries()[getIndex()].setNatBonus(natBonus = natBonus)

        super.setNatBonus(natBonus = natBonus)
    }

    /**
     * Determines that this characteristic has had a natural bonus applied to it.
     *
     * @return true if natural bonus is taken
     */
    fun natTaken(): Boolean{
        //initialize output
        var output = false

        //search previous levels for bonus acquisition
        parent.sblChar.levelLoop{character ->
            if(character.secondaryList.getAllSecondaries()[getIndex()].bonusApplied.value) {
                output = true
                return@levelLoop
            }
        }

        //return taken state
        return output
    }

    /**
     * Recalculates the total value after any other setter is called.
     */
    override fun refreshTotal() {
        total.intValue = modVal.intValue + special.intValue + pointsApplied.intValue + classPointTotal.intValue

        //add special level bonus from each record
        parent.sblChar.levelLoop{character ->
            total.intValue += character.secondaryList.getAllSecondaries()[getIndex()].specialPerLevel.intValue * character.lvl.intValue
        }

        //add natural bonus points
        if (natTaken())
            total.intValue += 5

        //add points for jack of all trades advantage
        if(parent.allTradesTaken.value) total.intValue += 10
        //remove points for no points and missing that advantage
        else if (pointsApplied.intValue == 0) total.intValue -= 30
    }

    /**
     * Recalculates the points applied to this characteristic.
     */
    fun pointsAppliedUpdate() {
        //reset point value
        pointsApplied.intValue = 0

        //add points from levels up to this one
        parent.sblChar.levelLoop{character ->
            pointsApplied.intValue += character.secondaryList.getAllSecondaries()[getIndex()].pointsApplied.intValue
        }

        refreshTotal()
        updateDevSpent()
        parent.charInstance.updateTotalSpent()
    }

    /**
     * Get all applied points applied to the character before the given level.
     *
     * @param level last character reference to check before outputting total
     * @return point total up to the indicated level
     */
    fun getPreviousPoints(level: Int = parent.sblChar.lvl.intValue - 1): Int{
        //initialize point counter
        var output = 0

        //count points applied per level
        parent.sblChar.levelLoop(endLevel = level){
            output += it.secondaryList.getAllSecondaries()[getIndex()].pointsApplied.intValue
        }

        //give result
        return output
    }

    /**
     * Determines if the character's growth in this characteristic has valid growth at this level.
     *
     * @param level character level to check at
     * @return true if value is zero or positive
     */
    fun validGrowthAtLevel(level: Int): Boolean{
        return parent.sblChar.charRefs[level]!!.secondaryList.getAllSecondaries()[getIndex()].pointsApplied.intValue >= 0
    }

    /**
     * Determines the DP spent in this characteristic at the inndicated level.
     *
     * @param level character level to check this stat at
     * @return points invested in this item
     */
    fun getPointsInAtLevel(level: Int): Int{
        return parent.sblChar.getRecordSum(endLevel = level){character ->
            character.secondaryList.getAllSecondaries()[getIndex()].pointsIn.intValue
        }
    }
}