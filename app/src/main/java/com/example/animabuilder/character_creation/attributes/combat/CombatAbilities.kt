package com.example.animabuilder.character_creation.attributes.combat

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader
import java.io.Serializable

class CombatAbilities(private val charInstance: BaseCharacter): Serializable {
    var presence: Int = 20

    //character's maximum hp
    var lifeMax = 0
    var lifePerLevel = 0
    var lifeClassTotal = 0
    var lifeBase = 0
    var lifeMultsTaken = 0

    val attack = CombatItem(charInstance)
    val block = CombatItem(charInstance)
    val dodge = CombatItem(charInstance)
    val wearArmor = CombatItem(charInstance)

    val allAbilities = listOf(attack, block, dodge, wearArmor)

    val physicalRes = ResistanceItem()
    val diseaseRes = ResistanceItem()
    val venomRes = ResistanceItem()
    val magicRes = ResistanceItem()
    val psychicRes = ResistanceItem()

    val allResistances = listOf(physicalRes, diseaseRes, venomRes, magicRes, psychicRes)

    var specInitiative = 0
    var totalInitiative = 0

    var fatigue = 0
    var specFatigue = 0

    var baseRegen = 0
    var specRegen = 0
    var totalRegen = 0

    fun updatePresence(){
        presence =
            if(charInstance.lvl == 0)
                20
            else
                25 + (5 * charInstance.lvl)

        allResistances.forEach{it.setBase(presence)}
    }

    //update the base number of life points based on the character's constitution
    fun updateLifeBase(){
        lifeBase = if(charInstance.primaryList.con.total == 1)
            5
        else
            20 + (charInstance.primaryList.con.total * 10) + charInstance.primaryList.con.outputMod
    }

    //updates the number of life multiples the user is taking for their character
    fun takeLifeMult(multTake: Int){
        lifeMultsTaken = multTake
        charInstance.updateTotalSpent()
        updateLifePoints()
    }

    @JvmName("setLifePerLevel1")
    fun setLifePerLevel(input: Int){
        lifePerLevel = input
        updateClassLife()
    }

    fun updateClassLife(){
        lifeClassTotal = lifePerLevel * charInstance.lvl
        updateLifePoints()
    }

    //updates the character's total life points
    fun updateLifePoints(){
        lifeMax = lifeBase + (lifeMultsTaken * charInstance.primaryList.con.total) + lifeClassTotal
    }

    //determines if the attack, block, and dodge the user has taken is valid
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

    fun changeSpecInitiative(changeBy: Int){
        specInitiative += changeBy
        updateInitiative()
    }

    fun updateInitiative(){
        totalInitiative =
            (charInstance.ownClass.initiativePerLevel * charInstance.lvl) +
                    charInstance.primaryList.dex.outputMod +
                    charInstance.primaryList.agi.outputMod + specInitiative
    }

    fun updateFatigue(){
        fatigue = charInstance.primaryList.con.total + specFatigue
    }

    fun getBaseRegen(){
        baseRegen = when(charInstance.primaryList.con.total){
            in 3..7 -> 1
            in 8..9 -> 2
            in 10..18 -> charInstance.primaryList.con.total - 7
            in 19..20 -> 12
            else -> 0
        }

        updateRegeneration()
    }

    fun updateRegeneration(){
        totalRegen = baseRegen + specRegen
    }

    fun calculateSpent(): Int{
        return attack.inputVal * charInstance.ownClass.atkGrowth +
                block.inputVal * charInstance.ownClass.blockGrowth +
                dodge.inputVal * charInstance.ownClass.dodgeGrowth +
                wearArmor.inputVal * charInstance.ownClass.armorGrowth
    }

    fun loadCombat(fileReader: BufferedReader){
        takeLifeMult(fileReader.readLine().toInt())
        allAbilities.forEach{
            it.loadItem(fileReader)
        }
    }

    fun writeCombat(){
        charInstance.addNewData(lifeMultsTaken)
        allAbilities.forEach{
            it.writeItem()
        }
    }
}