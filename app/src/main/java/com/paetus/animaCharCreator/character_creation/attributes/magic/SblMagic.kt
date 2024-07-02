package com.paetus.animaCharCreator.character_creation.attributes.magic

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Component that holds a SBL Character's magical abilities.
 *
 * @param charInstance object that holds all of the character's data
 */
class SblMagic(val charInstance: SblChar): Magic(charInstance) {
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