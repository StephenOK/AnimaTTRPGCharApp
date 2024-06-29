package com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Subclass of SecondaryCharacteristic for use in a SBL character.
 *
 * @param parent full list that holds this object
 * @param secondaryIndex where to find this item's equivalent in the BaseCharacter records
 */
class SblSecondaryCharacteristic(
    private val parent: SblSecondaryList,
    val secondaryIndex: Int
): SecondaryCharacteristic(parent){
    /**
     * Setter for points applied by user.
     *
     * @param pointInput amount to set the points applied to
     */
    override fun setPointsApplied(pointInput: Int) {
        val charInstance = parent.sblChar

        //get previous secondary total fromm levels
        var preSecondaryValue = 0
        charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){ character ->
            preSecondaryValue += character.secondaryList.fullList()[secondaryIndex].pointsApplied.intValue
        }

        //set current level value
        charInstance.getCharAtLevel().secondaryList.fullList()[secondaryIndex].setPointsApplied(pointInput - preSecondaryValue)

        pointsAppliedUpdate()
    }

    /**
     * Set the class points obtained for this characteristic.
     *
     * @param classBonus value to set the class points to
     */
    override fun setClassPointsPerLevel(classBonus: Int) {
        parent.sblChar.getCharAtLevel().secondaryList.getAllSecondaries()[secondaryIndex].setClassPointsPerLevel(classBonus)
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

                (parent.charInstance as SblChar).levelLoop(startLevel = 1){
                    output += it.secondaryList.getAllSecondaries()[secondaryIndex].classPointsPerLevel.intValue
                }

                output
            }
            //get half of the first level's class bonus
            else (parent.charInstance as SblChar).charRefs[0]!!.secondaryList.getAllSecondaries()[secondaryIndex].classPointsPerLevel.intValue/2

        //update secondary total
        refreshTotal()
    }

    /**
     * Setter for natural bonus.
     *
     * @param natBonus true if applying a natural bonus to the characteristic
     */
    override fun setNatBonus(natBonus: Boolean) {
        (parent.charInstance as SblChar).levelLoop(
            startLevel = parent.charInstance.lvl.intValue,
            endLevel = 20
        ){
            it.secondaryList.getAllSecondaries()[secondaryIndex].setNatBonus(natBonus = natBonus)
        }

        super.setNatBonus(natBonus = natBonus)
    }

    /**
     * Recalculates the total value after any other setter is called.
     */
    override fun refreshTotal() {
        total.intValue = modVal.intValue + special.intValue + pointsApplied.intValue + classPointTotal.intValue

        //add natural bonus points
        if (parent.sblChar.getCharAtLevel().secondaryList.getAllSecondaries()[secondaryIndex].bonusApplied.value)
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
            pointsApplied.intValue += character.secondaryList.fullList()[secondaryIndex].pointsApplied.intValue
        }

        updateDevSpent()
        refreshTotal()
    }

    /**
     * Determines if the character's growth in this characteristic has valid growth through levels.
     */
    fun validGrowth(): Boolean{
        //initialize output
        var output = true

        //determine whether the level has any points removed in that level
        parent.sblChar.levelLoop{
            if(it.secondaryList.getAllSecondaries()[secondaryIndex].pointsApplied.intValue < 0)
                output = false
        }

        //return valid status
        return output
    }
}