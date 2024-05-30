package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Component that manages a SBL Character's ki points and accumulation.
 * Also manages the SBL Character's ki abilities and dominion techniques.
 *
 * @param charInstance object that holds all of the character's data
 */
class SblKi(val charInstance: SblChar): Ki(charInstance = charInstance) {
    /**
     * Recalculates the character's maximum martial knowledge.
     */
    override fun updateMK() {
        //get the class value for martial knowledge
        val classMK =
            //sum each individual level's value
            if(charInstance.lvl.intValue != 0){
                var output = 0

                charInstance.levelLoop(startLevel = 1){
                    output += it.classes.getClass().mkPerLevel
                }

                output
            }
            //get half of the first level's class bonus
            else charInstance.charRefs[0]!!.classes.getClass().mkPerLevel/2

        //update MK total
        martialKnowledgeMax.intValue = classMK + charInstance.weaponProficiencies.mkFromArts() + martialKnowledgeSpec.intValue

        //update spent amount of martial knowledge
        updateMkSpent()
    }
}