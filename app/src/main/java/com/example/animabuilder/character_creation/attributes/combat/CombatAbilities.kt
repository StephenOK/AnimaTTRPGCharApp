package com.example.animabuilder.character_creation.attributes.combat

import androidx.compose.runtime.mutableStateOf
import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader

/**
 * Section of the character that holds combat data, such as life points and attack.
 * Holds data on the character's resistances.
 * Holds data on the character's initiative, fatigue, and regeneration.
 *
 * @param charInstance object that holds all of the character's data
 */
class CombatAbilities(private val charInstance: BaseCharacter){
    //set default presence
    val presence = mutableStateOf(20)

    //character's health items
    val lifeBase = mutableStateOf(0)
    val lifeMultsTaken = mutableStateOf(0)
    val lifeClassTotal = mutableStateOf(0)
    val lifeMax = mutableStateOf(0)

    //initialize the character's combat data
    val attack = CombatItem(charInstance)
    val block = CombatItem(charInstance)
    val dodge = CombatItem(charInstance)
    val wearArmor = CombatItem(charInstance)

    //gather all combat abilities
    val allAbilities = listOf(attack, block, dodge, wearArmor)

    //initialize the character's resistances
    val physicalRes = ResistanceItem()
    val diseaseRes = ResistanceItem()
    val venomRes = ResistanceItem()
    val magicRes = ResistanceItem()
    val psychicRes = ResistanceItem()

    //gather all resistance data
    val allResistances = listOf(physicalRes, diseaseRes, venomRes, magicRes, psychicRes)

    //initialize initiative
    val specInitiative = mutableStateOf(0)
    val totalInitiative = mutableStateOf(0)

    //initialize fatigue
    val fatigue = mutableStateOf(0)
    val specFatigue = mutableStateOf(0)

    //initialize regeneration
    val baseRegen = mutableStateOf(0)
    val specRegen = mutableStateOf(0)
    val totalRegen = mutableStateOf(0)

    /**
     * Update the character's presence.
     */
    fun updatePresence(){
        //set the presence value
        presence.value =
            if(charInstance.lvl.value == 0)
                20
            else
                25 + (5 * charInstance.lvl.value)

        //update the character's resistances
        allResistances.forEach{it.setBase(presence.value)}
    }

    /**
     * Update the base number of life points based on the character's constitution.
     */
    fun updateLifeBase(){
        lifeBase.value = if(charInstance.primaryList.con.total.value == 1)
            5
        else
            20 + (charInstance.primaryList.con.total.value * 10) + charInstance.primaryList.con.outputMod.value
    }

    /**
     * Updates the number of life multiples the user is taking for their character.
     *
     * @param multTake number of multiples the user intends to take
     */
    fun takeLifeMult(multTake: Int){
        lifeMultsTaken.value = multTake
        charInstance.updateTotalSpent()
        updateLifePoints()
    }

    /**
     * Updates the number of life points gained from their class and level.
     */
    fun updateClassLife(){
        lifeClassTotal.value =
            if(charInstance.lvl.value != 0)
                charInstance.ownClass.value.lifePointsPerLevel * charInstance.lvl.value
            else charInstance.ownClass.value.lifePointsPerLevel/2

        updateLifePoints()
    }

    /**
     * Updates the character's total life points
     */
    fun updateLifePoints(){
        lifeMax.value =
            lifeBase.value +
                    (lifeMultsTaken.value * charInstance.primaryList.con.total.value) +
                    lifeClassTotal.value
    }

    /**
     * Determines if the attack, block, and dodge the user has taken is valid.
     *
     * @return valid status of the combat inputs
     */
    fun validAttackDodgeBlock(): Boolean{
        //if only one stat developed, cannot exceed 25% of overall devPT
        return ((block.inputVal.value == 0 && dodge.inputVal.value == 0 && attack.inputVal.value * charInstance.ownClass.value.atkGrowth <= charInstance.devPT.value/4) ||
                (attack.inputVal.value == 0 && dodge.inputVal.value == 0 && block.inputVal.value * charInstance.ownClass.value.blockGrowth <= charInstance.devPT.value/4) ||
                (attack.inputVal.value == 0 && block.inputVal.value == 0 && dodge.inputVal.value * charInstance.ownClass.value.dodgeGrowth <= charInstance.devPT.value/4)) ||

                //attack, dodge, and block cannot equate to over 50% of overall devPT
                (((attack.inputVal.value * charInstance.ownClass.value.atkGrowth) + (block.inputVal.value * charInstance.ownClass.value.blockGrowth) + (dodge.inputVal.value * charInstance.ownClass.value.dodgeGrowth) <= charInstance.devPT.value/2) &&

                        //attack can not be more than 50 of either one of block or dodge
                        (attack.total.value - block.total.value <= 50 || attack.total.value - dodge.total.value <= 50) &&

                        //neither block nor dodge can be 50 more than attack
                        (block.total.value - attack.total.value <= 50 && dodge.total.value - attack.total.value <= 50))
    }

    /**
     * Applies changes to the special initiative value.
     *
     * @param changeBy value to alter the special initiative by
     */
    fun changeSpecInitiative(changeBy: Int){
        specInitiative.value += changeBy
        updateInitiative()
    }

    /**
     * Function that updates the character's total initiative.
     */
    fun updateInitiative(){
        val classInitiative =
            if(charInstance.lvl.value != 0) charInstance.ownClass.value.initiativePerLevel * charInstance.lvl.value
            else charInstance.ownClass.value.initiativePerLevel/2

        //add together class level value, dexterity, agility, and special input
        totalInitiative.value =
            classInitiative + charInstance.primaryList.dex.outputMod.value +
                    charInstance.primaryList.agi.outputMod.value + specInitiative.value
    }

    /**
     * Function that updates the character's total fatigue.
     */
    fun updateFatigue(){
        fatigue.value = charInstance.primaryList.con.total.value + specFatigue.value
    }

    /**
     * Retrieves the character's base regeneration value.
     */
    fun getBaseRegen(){
        //get value based on the character's constitution
        baseRegen.value = when(charInstance.primaryList.con.total.value){
            in 3..7 -> 1
            in 8..9 -> 2
            in 10..18 -> charInstance.primaryList.con.total.value - 7
            in 19..20 -> 12
            else -> 0
        }

        //recalculate the character's regeneration
        updateRegeneration()
    }

    /**
     * Updates the character's total regeneration value.
     */
    fun updateRegeneration(){
        totalRegen.value = baseRegen.value + specRegen.value
    }

    /**
     * Calculates the total development points spent in this section.
     *
     * @return development points spent
     */
    fun calculateSpent(): Int{
        return attack.inputVal.value * charInstance.ownClass.value.atkGrowth +
                block.inputVal.value * charInstance.ownClass.value.blockGrowth +
                dodge.inputVal.value * charInstance.ownClass.value.dodgeGrowth +
                wearArmor.inputVal.value * charInstance.ownClass.value.armorGrowth
    }

    /**
     * Load the data for the combat section.
     *
     * @param fileReader file to retrieve the data from
     */
    fun loadCombat(fileReader: BufferedReader){
        //retrieve life multiple data
        takeLifeMult(fileReader.readLine().toInt())

        //retrieve data on attack, defenses, and wear armor
        allAbilities.forEach{
            it.loadItem(fileReader)
        }
    }

    /**
     * Write the data for the combat section.
     */
    fun writeCombat(){
        //write life multiple data
        charInstance.addNewData(lifeMultsTaken.value)

        //write data on attack, defenses, and wear armor
        allAbilities.forEach{
            it.writeItem()
        }
    }
}