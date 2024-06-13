package com.paetus.animaCharCreator.character_creation.attributes.primary_abilities

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Subclass of PrimaryCharacteristic.
 * Saves level bonus in a different way for use in SBL characters.
 *
 * @param charInstance object that holds all of the character's stats
 * @param advantageCap highest value stat can get from an advantage
 * @param charIndex index of this characteristic
 * @param setUpdate function to run on this stat's change
 */
class SblPrimaryChar(
    override val charInstance: SblChar,
    advantageCap: Int,
    override val charIndex: Int,
    setUpdate: (mod: Int, total: Int) -> Unit
): PrimaryCharacteristic(charInstance, advantageCap, charIndex, setUpdate) {
    /**
     * Sets the character's bonus in this stat from levels.
     *
     * @param lvlBonus value to set as bonus
     */
    override fun setLevelBonus(lvlBonus: Int) {
        //get value of level bonus from previous character levels
        var preBonusVal = 0
        charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){character ->
            preBonusVal += character.primaryList.allPrimaries()[charIndex].levelBonus.intValue
        }

        //set current level value
        charInstance.getCharAtLevel().primaryList.allPrimaries()[charIndex].levelBonus.intValue = lvlBonus - preBonusVal

        //get new total
        refreshBonusTotal()
    }

    /**
     * Updates the level bonus applied to this item.
     */
    fun refreshBonusTotal(){
        //reset bonus amount
        levelBonus.intValue = 0

        //add each level's bonus to this total
        charInstance.levelLoop{character ->
            levelBonus.intValue += character.primaryList.allPrimaries()[charIndex].levelBonus.intValue
        }

        //update all relevant items
        updateValues()
    }

    /**
     * Determines if all inputs for this item are valid.
     *
     * @return true if inputs are valid
     */
    fun validGrowth(): Boolean{
        //initialize output as valid
        var output = true

        //check each level for a negative input
        charInstance.levelLoop{
            //update to false if one found
            if(it.primaryList.allPrimaries()[charIndex].levelBonus.intValue < 0) {
                output = false
                return@levelLoop
            }
        }

        //give final output
        return output
    }
}