package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities

import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.TechniqueBase

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
     * Get the martial knowledge spent at the indicated level.
     *
     * @param level character level to check the spent value at
     * @return spent knowledge at the indicated level
     */
    fun getSpentMKAtLevel(level: Int): Int{
        //initialize final result
        var output = 0

        //add knowledge spent from acquiring ki abilities
        kiAbilitiesAtLevel(level = level).forEach{kiAbility ->
            output += kiAbility.mkCost
        }

        //add knowledge spent from acquiring techniques
        techsAtLevel(level = level).forEach{techniques ->
            output += techniques.mkCost()
        }

        //give the final result
        return output
    }

    /**
     * Get the martial knowledge available at the indicated level.
     *
     * @param level character level to check the martial knowledge value at
     * @return martial knowledge available at the indicated level
     */
    fun getMKAtLevel(level: Int): Int{
        //initialize final result
        var output = 0

        //if checking zeroth level, get half of the class level's mk bonus
        if(level == 0) output += charInstance.charRefs[0]!!.classes.getClass().mkPerLevel/2
        //if checking any other level
        else {
            //add the zeroth level's class' mk bonus in place of the first level record to avoid bug with resetting a class change
            output += charInstance.charRefs[0]!!.classes.getClass().mkPerLevel

            //add class mk from each level after 2
            charInstance.levelLoop(
                startLevel = 2,
                endLevel = level
            ) { character ->
                output += character.classes.getClass().mkPerLevel
            }
        }

        //return class total plus knowledge from advantages and martial arts
        return output + martialKnowledgeSpec.intValue + charInstance.weaponProficiencies.mkFromArtsAtLevel(level = level)
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

            //TODO: Check that there isn't a reason for the repeated level loops
            //get all other abilities that need removal
            val removeList = mutableListOf<KiAbility>()
            charInstance.levelLoop(
                startLevel = charInstance.lvl.intValue,
                endLevel = 20
            ){character ->
                character.ki.takenAbilities.forEach {
                    if (!isQualified(ability = it)) removeList += it
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
     * Retrieves a list of ki abilities held at the indicated level.
     *
     * @param level character level to get the list at
     * @return list of ki abilities the character has at this level
     */
    fun kiAbilitiesAtLevel(level: Int): List<KiAbility>{
        //initialize final list
        val output = mutableListOf<KiAbility>()

        //add ki abilities held at each level
        charInstance.levelLoop(endLevel = level){character ->
            output += character.ki.takenAbilities
        }

        //give the final result
        return output
    }

    /**
     * Retrieves the techniques held at the indicated level.
     *
     * @param level character level to get the data at
     * @return list of techniques at the indicated level
     */
    fun techsAtLevel(level: Int): List<TechniqueBase>{
        //initialize the final result
        val output = mutableListOf<TechniqueBase>()

        //add techniques to the list from each level
        charInstance.levelLoop(endLevel = level){character ->
            output += character.ki.getTakenTechs()
        }

        //give the final result
        return output
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

        //check that martial arts are still valid and update total
        charInstance.updateTotalSpent()

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