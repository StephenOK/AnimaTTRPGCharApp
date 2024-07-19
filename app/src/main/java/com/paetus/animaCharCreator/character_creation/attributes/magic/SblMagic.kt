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
}