package com.paetus.animaCharCreator.character_creation.attributes.combat

import com.paetus.animaCharCreator.character_creation.SblChar

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
        charInstance.getCharAtLevel().combat.allAbilities()[combatIndex].setClassBonus(classBonus)
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
     * Checks if the item does not remove points over the levels.
     */
    fun validGrowth(): Boolean{
        return charInstance.getCharAtLevel().combat.allAbilities()[combatIndex].inputVal.intValue >= 0
    }
}