package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities

import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities.KiAbility

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
     * Attempt to add a Ki Ability to the character.
     *
     * @param newAbility Ki Ability to attempt to add
     * @return true if ability has been successfully added
     */
    override fun attemptAbilityAdd(newAbility: KiAbility): Boolean {
        //check if character has the necessary martial knowledge for the ability
        if(martialKnowledgeRemaining.intValue - newAbility.mkCost >= 0){
            charInstance.getCharAtLevel().ki.takenAbilities += newAbility
            updateKiAbilities()
            updateMkSpent()
        }

        return takenAbilities.contains(newAbility)
    }

    /**
     * Removes the Ki Ability indicated by the user
     *
     * @param ability Ki Ability to remove
     */
    override fun removeAbility(ability: KiAbility){
        //run only if character gained this ability at this level
        if(charInstance.getCharAtLevel().ki.takenAbilities.contains(ability)){
            //remove ability from full list and level record
            takenAbilities -= ability
            charInstance.getCharAtLevel().ki.takenAbilities -= ability

            //get all other abilities that need removal
            val removeList = mutableListOf<KiAbility>()
            charInstance.levelLoop(
                startLevel = charInstance.lvl.intValue,
                endLevel = 20
            ){character ->
                character.ki.takenAbilities.forEach {
                    if (!isQualified(it)) removeList += it
                }
            }

            //remove other abilities that need removal
            removeList.forEach{removed ->
                takenAbilities -= removed
                charInstance.levelLoop(
                    startLevel = charInstance.lvl.intValue,
                    endLevel = 20
                ){
                    if(it.ki.takenAbilities.contains(removed))
                        it.ki.takenAbilities.remove(removed)
                }
            }

            //if(!takenAbilities.contains(kiRecord.kiControl)){
            //    allPrebuilts.forEach{it.value.value = false}
            //    customTechniques.forEach{it.value.value = false}
            //}

            //fully update main list
            updateKiAbilities()
        }
    }

    /**
     * Updates the main list of ki abilities taken.
     */
    private fun updateKiAbilities(){
        //clear main list
        takenAbilities.clear()

        //add all abilities taken at each level
        charInstance.levelLoop {character ->
            character.ki.takenAbilities.forEach{
                takenAbilities.add(it)
            }
        }

        //update martial knowledge spent
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

        //update ki abilities taken
        updateKiAbilities()

        //update martial knowledge
        updateMK()
    }
}