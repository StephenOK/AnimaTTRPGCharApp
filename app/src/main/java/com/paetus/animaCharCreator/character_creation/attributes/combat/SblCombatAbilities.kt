package com.paetus.animaCharCreator.character_creation.attributes.combat

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * A combat ability item in a save by level character.
 */
class SblCombatAbilities(
    private val charInstance: SblChar
): CombatAbilities(charInstance) {
    override val attack = SblCombatItem(charInstance = charInstance, combatIndex = 0)
    override val block = SblCombatItem(charInstance = charInstance, combatIndex = 1)
    override val dodge = SblCombatItem(charInstance = charInstance, combatIndex = 2)
    override val wearArmor = SblCombatItem(charInstance = charInstance, combatIndex = 3)

    /**
     * Updates the number of life points gained from their class and level
     */
    override fun updateClassLife() {
        //set new life points from classes
        lifeClassTotal.intValue =
            //get half a level of points for level 0 character
            if(charInstance.lvl.intValue != 0){
                var output = 0

                charInstance.levelLoop(startLevel = 1){
                    output += it.classes.getClass().lifePointsPerLevel
                }

                output
            }
            //add points from each class at each level
            else charInstance.charRefs[0]!!.classes.getClass().lifePointsPerLevel/2

        //update the life point total
        updateLifePoints()
    }
}