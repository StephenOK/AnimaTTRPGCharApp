package com.paetus.animaCharCreator.character_creation.attributes.primary_abilities

import com.paetus.animaCharCreator.character_creation.SblChar
import kotlin.math.ceil

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
     * Sets the base value of the stat.
     *
     * @param baseIn value to set the base to
     */
    override fun setInput(baseIn: Int) {
        super.setInput(baseIn)
        charInstance.charRefs[0]!!.primaryList.allPrimaries()[charIndex].setInput(baseIn)
    }

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
     * Determines this characteristic's value at the indicated level.
     *
     * @param level character level to determine the value at
     * @return the final characteristic value
     */
    fun getCharacteristicAtLevel(level: Int): Int{
        return charInstance.charRefs[0]!!.primaryList.allPrimaries()[charIndex].total.intValue +
                charInstance.getRecordSum(
                    startLevel = 2,
                    endLevel = level
                ){character ->
                    character.primaryList.allPrimaries()[charIndex].levelBonus.intValue
                }
    }

    /**
     * Determines the mod bonus at the inputted level.
     *
     * @param level character level to check the value at
     * @return mod bonus at the queried level
     */
    fun getLevelModBonus(level: Int): Int{
        //return the mod bonus applied by the output
        return when(val statAtLevel = getCharacteristicAtLevel(level = level)){
            1 -> -30
            2 -> -20
            3 -> -10
            4 -> -5
            else -> (15 * statAtLevel/5 - 1 + 5 * ceil(statAtLevel % 5 / 2.0)).toInt()
        }
    }

    /**
     * Determines if all inputs for this item are valid at this level.
     *
     * @param level to check the item at
     * @return true if inputs are valid
     */
    fun validGrowthAtLevel(level: Int): Boolean{
        return charInstance.charRefs[level]!!.primaryList.allPrimaries()[charIndex].levelBonus.intValue >= 0
    }
}