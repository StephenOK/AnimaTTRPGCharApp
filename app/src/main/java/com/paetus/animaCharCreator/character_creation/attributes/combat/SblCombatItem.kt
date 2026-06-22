package com.paetus.animaCharCreator.character_creation.attributes.combat

import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.SblPrimaryChar

/**
 * Subclass of CombatItem for use in a SBL character.
 *
 * @param charInstance object that holds all of a character's data
 * @param label reference to the string to label this object
 * @param combatIndex index of the matching combat items in BaseCharacters
 */
class SblCombatItem(
    val charInstance: SblChar,
    val label: Int,
    val combatIndex: Int
): CombatItem(charInstance = charInstance, itemLabel = label){
    /**
     * Sets the user's input for this stat.
     *
     * @param purchase value of user applied points
     */
    override fun setInputVal(purchase: Int) {
        //get value of combat input from previous character levels
        var preInputVal = 0
        charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){character ->
            preInputVal += character.combat.allAbilities()[combatIndex].inputVal.intValue
        }

        //set current level value
        charInstance.getCharAtLevel().combat.allAbilities()[combatIndex].inputVal.intValue = purchase - preInputVal

        //get new total
        updateInput()
    }

    /**
     * Refresh the input total for this item.
     */
    fun updateInput(){
        //reset input value
        inputVal.intValue = 0

        //add each level's inputted value
        charInstance.levelLoop{character ->
            inputVal.intValue += character.combat.allAbilities()[combatIndex].inputVal.intValue
        }

        //update the item total and points spent
        charInstance.updateTotalSpent()
        updateTotal()
    }

    /**
     * Sets additional bonuses that apply to class maximum cap.
     *
     * @param classBonus amount to increment the bonus by
     */
    override fun setClassBonus(classBonus: Int) {
        charInstance.getCharAtLevel().combat.allAbilities()[combatIndex].classBonus.intValue = classBonus
        updateClassTotal()
    }

    /**
     * Update the class total for the individual stat.
     */
    override fun updateClassTotal() {
        //determine actual total
        classTotal.intValue =
            if(charInstance.lvl.intValue != 0){
                //initialize value
                var output = 0

                //add each individual level's class value
                charInstance.levelLoop(startLevel = 1){
                    output += it.combat.allAbilities()[combatIndex].pointPerLevel.intValue
                }

                //return total value
                output
            }
            else
                charInstance.charRefs[0]!!.combat.allAbilities()[combatIndex].pointPerLevel.intValue/2

        charInstance.levelLoop{
            classTotal.intValue += it.combat.allAbilities()[combatIndex].classBonus.intValue
        }

        //set class cap if it is exceeded
        if(classTotal.intValue > 50)
            classTotal.intValue = 50

        //update overall total
        updateTotal()
    }

    /**
     * Get the total for this item at the indicated level.
     *
     * @param level character level to get the total for
     * @return the total value of this item at this level
     */
    fun getLevelTotal(level: Int): Int{
        //initialize final output and class point tracker
        var output = 0
        var dummyClass = 0

        //for each level record up to the indicated point
        charInstance.levelLoop(endLevel = level){character ->
            //retrieve the record's associated combat item
            val checkedItem = character.combat.allAbilities()[combatIndex]

            //add the point input to the output
            output += checkedItem.inputVal.intValue

            //increment the class point tracker by the appropriate amount
            dummyClass += checkedItem.pointPerLevel.intValue
            dummyClass += checkedItem.classBonus.intValue
        }

        //add either the class point value or the cap of 50 to the output
        output +=
            if(dummyClass < 50) dummyClass
            else 50

        //add the character's appropriate mod bonus to the output
        output += when(combatIndex){
            2 -> (charInstance.primaryList.allPrimaries()[2] as SblPrimaryChar).getLevelModBonus(level = level)
            3 -> (charInstance.primaryList.allPrimaries()[0] as SblPrimaryChar).getLevelModBonus(level = level)
            else -> (charInstance.primaryList.allPrimaries()[1] as SblPrimaryChar).getLevelModBonus(level = level)
        }

        //return the final output
        return output
    }

    /**
     * Determines the DP spent on this item at the indicated level.
     *
     * @param level character level to get this total at
     * @return DP total spent
     */
    fun pointsSpentAtLevel(level: Int): Int{
        return charInstance.getRecordSum(endLevel = level){character ->
            //retrieve the character's points invested in the related item
            character.combat.allAbilities()[combatIndex].inputVal.intValue *
                    //multiply by the appropriate class item
                    when(combatIndex){
                        0 -> character.classes.getClass().atkGrowth
                        1 -> character.classes.getClass().blockGrowth
                        2 -> character.classes.getClass().dodgeGrowth
                        else -> character.classes.getClass().armorGrowth
                    }
        }
    }

    /**
     * Validates the item's growth up to the indicated level.
     *
     * @param level character level to check
     * @return true if held value is positive or zero
     */
    fun validGrowthAtLevel(level: Int): Boolean{
        return charInstance.charRefs[level]!!.combat.allAbilities()[combatIndex].inputVal.intValue >= 0
    }
}