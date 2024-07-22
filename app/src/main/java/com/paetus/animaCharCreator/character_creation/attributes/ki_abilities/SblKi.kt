package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Component that manages a SBL Character's ki points and accumulation.
 * Also manages the SBL Character's ki abilities and dominion techniques.
 *
 * @param charInstance object that holds all of the character's data
 */
class SblKi(
    val charInstance: SblChar
): Ki(charInstance = charInstance){
    override val strKi = SblKiStat(kiParent = this, 0)
    override val dexKi = SblKiStat(kiParent = this, 1)
    override val agiKi = SblKiStat(kiParent = this, 2)
    override val conKi = SblKiStat(kiParent = this, 3)
    override val powKi = SblKiStat(kiParent = this, 4)
    override val wpKi = SblKiStat(kiParent = this, 5)

    /**
     * Gets the class's ki accumulation DP cost.
     */
    override fun getKiAccumulationCost(): Int {
        return charInstance.getCharAtLevel().ki.getKiAccumulationCost()
    }

    /**
     * Gets the class's ki point DP cost.
     */
    override fun getKiPointCost(): Int {
        return charInstance.getCharAtLevel().ki.getKiPointCost()
    }

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

    /**
     * Function to run when the character changes level.
     */
    fun levelUpdate(){
        //update all points and accumulations for ki stats
        allKiStats().forEach{
            (it as SblKiStat).updateInput()
            it.accUpdate()
        }

        //update martial knowledge
        updateMK()
    }
}