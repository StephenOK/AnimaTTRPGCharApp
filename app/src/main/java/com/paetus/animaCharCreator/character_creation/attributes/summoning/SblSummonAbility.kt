package com.paetus.animaCharCreator.character_creation.attributes.summoning

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Object that manages one of the SBL Character's summoning abilities.
 *
 * @param charInstance object that manages all of the character's data
 * @param summoningIndex which index this item's corresponding elements are held in
 */
class SblSummonAbility(
    val charInstance: SblChar,
    val summoningIndex: Int
): SummonAbility(charInstance){
    /**
     * Refresh the number of points in this stat from levels.
     */
    override fun updateLevelTotal() {
        //get points from classes
        levelTotal.intValue =
            //sum up each level's class bonus
            if(charInstance.lvl.intValue != 0){
                var output = 0

                charInstance.levelLoop(startLevel = 1){
                    output += it.summoning.allSummoning()[summoningIndex].pointsPerLevel.intValue
                }

                output
            }
            //get half of first level's bonus
            else charInstance.charRefs[0]!!.summoning.allSummoning()[summoningIndex].pointsPerLevel.intValue/2

        updateTotal()
    }
}