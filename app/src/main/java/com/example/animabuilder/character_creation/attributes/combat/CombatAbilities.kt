package com.example.animabuilder.character_creation.attributes.combat

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
    var presence = 20

    //character's maximum hp
    var lifeMax = 0
    var lifeClassTotal = 0
    var lifeBase = 0
    var lifeMultsTaken = 0

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
    var specInitiative = 0
    var totalInitiative = 0

    //initialize fatigue
    var fatigue = 0
    var specFatigue = 0

    //initialize regeneration
    var baseRegen = 0
    var specRegen = 0
    var totalRegen = 0

    /**
     * Update the character's presence.
     */
    fun updatePresence(){
        //set the presence value
        presence =
            if(charInstance.lvl == 0)
                20
            else
                25 + (5 * charInstance.lvl)

        //update the character's resistances
        allResistances.forEach{it.setBase(presence)}
    }

    /**
     * Update the base number of life points based on the character's constitution.
     */
    fun updateLifeBase(){
        lifeBase = if(charInstance.primaryList.con.total == 1)
            5
        else
            20 + (charInstance.primaryList.con.total * 10) + charInstance.primaryList.con.outputMod
    }

    /**
     * Updates the number of life multiples the user is taking for their character.
     *
     * @param multTake number of multiples the user intends to take
     */
    fun takeLifeMult(multTake: Int){
        lifeMultsTaken = multTake
        charInstance.updateTotalSpent()
        updateLifePoints()
    }

    /**
     * Updates the number of life points gained from their class and level.
     */
    fun updateClassLife(){
        lifeClassTotal =
            if(charInstance.lvl != 0) charInstance.ownClass.lifePointsPerLevel * charInstance.lvl
            else charInstance.ownClass.lifePointsPerLevel/2
        updateLifePoints()
    }

    /**
     * Updates the character's total life points
     */
    fun updateLifePoints(){
        lifeMax = lifeBase + (lifeMultsTaken * charInstance.primaryList.con.total) + lifeClassTotal
    }

    /**
     * Determines if the attack, block, and dodge the user has taken is valid.
     *
     * @return valid status of the combat inputs
     */
    fun validAttackDodgeBlock(): Boolean{
        //if only one stat developed, cannot exceed 25% of overall devPT
        return ((block.inputVal == 0 && dodge.inputVal == 0 && attack.inputVal * charInstance.ownClass.atkGrowth <= charInstance.devPT/4) ||
                (attack.inputVal == 0 && dodge.inputVal == 0 && block.inputVal * charInstance.ownClass.blockGrowth <= charInstance.devPT/4) ||
                (attack.inputVal == 0 && block.inputVal == 0 && dodge.inputVal * charInstance.ownClass.dodgeGrowth <= charInstance.devPT/4)) ||

                //attack, dodge, and block cannot equate to over 50% of overall devPT
                (((attack.inputVal * charInstance.ownClass.atkGrowth) + (block.inputVal * charInstance.ownClass.blockGrowth) + (dodge.inputVal * charInstance.ownClass.dodgeGrowth) <= charInstance.devPT/2) &&

                        //attack can not be more than 50 of either one of block or dodge
                        (attack.total - block.total <= 50 || attack.total - dodge.total <= 50) &&

                        //neither block nor dodge can be 50 more than attack
                        (block.total - attack.total <= 50 && dodge.total - attack.total <= 50))
    }

    /**
     * Applies changes to the special initiative value.
     *
     * @param changeBy value to alter the special initiative by
     */
    fun changeSpecInitiative(changeBy: Int){
        specInitiative += changeBy
        updateInitiative()
    }

    /**
     * Function that updates the character's total initiative.
     */
    fun updateInitiative(){
        val classInitiative =
            if(charInstance.lvl != 0) charInstance.ownClass.initiativePerLevel * charInstance.lvl
            else charInstance.ownClass.initiativePerLevel/2

        //add together class level value, dexterity, agility, and special input
        totalInitiative =
            classInitiative + charInstance.primaryList.dex.outputMod +
                    charInstance.primaryList.agi.outputMod + specInitiative
    }

    /**
     * Function that updates the character's total fatigue.
     */
    fun updateFatigue(){
        fatigue = charInstance.primaryList.con.total + specFatigue
    }

    /**
     * Retrieves the character's base regeneration value.
     */
    fun getBaseRegen(){
        //get value based on the character's constitution
        baseRegen = when(charInstance.primaryList.con.total){
            in 3..7 -> 1
            in 8..9 -> 2
            in 10..18 -> charInstance.primaryList.con.total - 7
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
        totalRegen = baseRegen + specRegen
    }

    /**
     * Calculates the total development points spent in this section.
     *
     * @return development points spent
     */
    fun calculateSpent(): Int{
        return attack.inputVal * charInstance.ownClass.atkGrowth +
                block.inputVal * charInstance.ownClass.blockGrowth +
                dodge.inputVal * charInstance.ownClass.dodgeGrowth +
                wearArmor.inputVal * charInstance.ownClass.armorGrowth
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
        charInstance.addNewData(lifeMultsTaken)

        //write data on attack, defenses, and wear armor
        allAbilities.forEach{
            it.writeItem()
        }
    }
}