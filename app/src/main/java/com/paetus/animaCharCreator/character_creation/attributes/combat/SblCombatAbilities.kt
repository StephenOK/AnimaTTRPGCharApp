package com.paetus.animaCharCreator.character_creation.attributes.combat

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * A combat ability item in a save by level character.
 */
class SblCombatAbilities(
    val charInstance: SblChar
): CombatAbilities(charInstance) {
    override val attack = SblCombatItem(charInstance = charInstance, label = R.string.attackLabel, combatIndex = 0)
    override val block = SblCombatItem(charInstance = charInstance, label = R.string.blockLabel,  combatIndex = 1)
    override val dodge = SblCombatItem(charInstance = charInstance, label = R.string.dodgeLabel,  combatIndex = 2)
    override val wearArmor = SblCombatItem(charInstance = charInstance, label = R.string.wearLabel, combatIndex = 3)

    /**
     * Gets the class's cost for life point multiples.
     */
    override fun getLifeCost(): Int {
        return charInstance.getCharAtLevel().combat.getLifeCost()
    }

    /**
     * Gets the class's cost for attack points.
     */
    override fun getAtkCost(): Int {
        return charInstance.getCharAtLevel().combat.getAtkCost()
    }

    /**
     * Gets the class's cost for block points.
     */
    override fun getBlockCost(): Int {
        return charInstance.getCharAtLevel().combat.getBlockCost()
    }

    /**
     * Gets the class's cost for dodge points.
     */
    override fun getDodgeCost(): Int {
        return charInstance.getCharAtLevel().combat.getDodgeCost()
    }

    /**
     * Gets the class's cost for wear armor points.
     */
    override fun getWearCost(): Int {
        return charInstance.getCharAtLevel().combat.getWearCost()
    }

    /**
     * Updates the number of life multiples the user is taking for their character.
     *
     * @param multTake number of multiples the user intends to take
     */
    override fun takeLifeMult(multTake: Int) {
        //get life mults taken from previous levels
        var preInputVal = 0
        charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){character ->
            preInputVal += character.combat.lifeMultsTaken.intValue
        }

        //apply points to this level record
        charInstance.getCharAtLevel().combat.takeLifeMult(multTake - preInputVal)

        //update life multiples taken
        updateLifeMults()
    }

    /**
     * Update the life multiples this character has taken.
     */
    fun updateLifeMults(){
        //reset life mult value
        lifeMultsTaken.intValue = 0

        //add each level's inputted value
        charInstance.levelLoop{
            lifeMultsTaken.intValue += it.combat.lifeMultsTaken.intValue
        }

        //update DP spent and total life points
        charInstance.updateTotalSpent()
        updateLifePoints()
    }

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

    /**
     * Function that updates the character's total initiative.
     */
    override fun updateInitiative() {
        //get total from classes
        val classInitiative =
            //if character isn't level 0
            if (charInstance.lvl.intValue != 0){
                //initialize total
                var output = 0

                //get each level's individual class bonus
                charInstance.levelLoop(startLevel = 1){
                    output += it.classes.getClass().initiativePerLevel
                }

                //return total
                output
            }
            //get half of this level's class bonus otherwise
            else charInstance.charRefs[0]!!.classes.getClass().initiativePerLevel/2

        //add together class level value, dexterity, agility, and special input
        totalInitiative.intValue =
            20 + classInitiative + charInstance.primaryList.dex.outputMod.intValue +
                    charInstance.primaryList.agi.outputMod.intValue + specInitiative.intValue
    }

    /**
     * Get whether life point multiples have been acquired legally.
     *
     * @return true if valid expenditure made
     */
    fun validLifeGrowth(): Boolean{
        return charInstance.getCharAtLevel().combat.lifeMultsTaken.intValue >= 0
    }
}