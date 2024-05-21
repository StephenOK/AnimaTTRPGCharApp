package com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities

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
        charInstance.levelLoop(charLevel = charInstance.lvl.intValue - 1){character ->
            preSecondaryValue += character.secondaryList.fullList()[secondaryIndex].pointsApplied.intValue
        }

        //set current level value
        charInstance.getCharAtLevel().secondaryList.fullList()[secondaryIndex].setPointsApplied(pointInput - preSecondaryValue)

        //get points applied from each level
        pointsApplied.intValue = 0
        parent.sblChar.levelLoop{character ->
            pointsApplied.intValue += character.secondaryList.getAllSecondaries()[secondaryIndex].pointsApplied.intValue
        }

        //remove natural bonus is no points applied to this stat
        if(pointsApplied.intValue == 0 && bonusApplied.value)
            setNatBonus(natBonus = false)

        //get new totals
        updateDevSpent()
        refreshTotal()
    }

    /**
     * Gets the initial total value for the secondary characteristic.
     */
    override fun getTotal(){
        total.intValue = modVal.intValue + special.intValue + pointsApplied.intValue

        //get class points from each level
        parent.sblChar.levelLoop{character ->
            val levelItem = character.secondaryList.getAllSecondaries()[secondaryIndex]
            total.intValue += levelItem.classPointsPerLevel.intValue + levelItem.specialPerLevel.intValue
        }
    }

    fun levelUpdate(newLevel: Int){
        //reset point value
        pointsApplied.intValue = 0

        //add points from levels up to this one
        parent.sblChar.levelLoop(charLevel = newLevel){character ->
            pointsApplied.intValue += character.secondaryList.fullList()[secondaryIndex].pointsApplied.intValue
        }

        updateDevSpent()
        refreshTotal()
    }
}