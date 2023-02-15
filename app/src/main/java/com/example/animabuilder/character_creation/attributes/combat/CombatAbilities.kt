package com.example.animabuilder.character_creation.attributes.combat

import androidx.compose.runtime.MutableState
import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader
import java.io.Serializable

class CombatAbilities(private val charInstance: BaseCharacter): Serializable {
    var presence: Int = 20

    //character's maximum hp
    var lifeMax = 0

    var lifeBase = 0
    var lifeMultsTaken = 0

    //character's attack stat
    var attack = 0
    var pointInAttack = 0
    var attackPerLevel = 0

    //character's block stat
    var block = 0
    var pointInBlock = 0
    var blockPerLevel = 0

    //character's dodge stat
    var dodge = 0
    var pointInDodge = 0
    var dodgePerLevel = 0

    //character's wear armor stat
    var wearArmor = 0
    var pointInWear = 0
    var wearPerLevel = 0

    var specInitiative = 0
    var totalInitiative = 0

    //character resistance stats
    var resistPhys = 0
    var resistDisease = 0
    var resistVen = 0
    var resistMag = 0
    var resistPsy = 0

    var rphysSpec = 0
    var rdSpec = 0
    var rvSpec = 0
    var rmSpec = 0
    var rpsySpec = 0

    var rphysMult = 1.0
    var rdMult = 1.0
    var rvMult = 1.0
    var rmMult = 1.0
    var rpsyMult = 1.0

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
    }

    //update the base number of life points based on the character's constitution
    fun updateLifeBase(){
        lifeBase = if(charInstance.totalCON == 1)
            5
        else
            20 + (charInstance.totalCON * 10) + charInstance.modCON
    }

    //updates the number of life multiples the user is taking for their character
    fun takeLifeMult(multTake: Int){
        lifeMultsTaken = multTake
        charInstance.updateTotalSpent()
        updateLifePoints()
    }

    //updates the character's total life points
    fun updateLifePoints(){
        lifeMax = lifeBase + (lifeMultsTaken * charInstance.totalCON) + (charInstance.ownClass.lifePointsPerLevel * charInstance.lvl)
    }

    //updates attack points and updates a string item if one given
    val applyAttackPoint = {score: Int, textChange: MutableState<String>? ->
        pointInAttack = score
        charInstance.updateTotalSpent()
        updateAttack()

        if(textChange != null)
            textChange.value = attack.toString()

        validAttackDodgeBlock()
    }

    @JvmName("setAttackPerLevel1")
    fun setAttackPerLevel(newVal: Int){
        attackPerLevel = newVal
        updateAttack()
    }

    //updates the character's attack value
    fun updateAttack(){
        attack = pointInAttack + charInstance.modDEX + attackClassVal()
        charInstance.weaponProficiencies.updateMartialMax()
    }

    //gets the character's class values for attack
    fun attackClassVal(): Int{
        var total = attackPerLevel * charInstance.lvl
        if(charInstance.weaponProficiencies.takenMartialList.contains(charInstance.weaponProficiencies.martials.capoeira))
            total += 10

        return if(total > 50)
            50
        else
            total
    }

    //updates block points and updates a string item if one given
    val applyBlockPoint = {score: Int, textChange: MutableState<String>? ->
        pointInBlock = score
        charInstance.updateTotalSpent()
        updateBlock()

        if(textChange != null)
            textChange.value = block.toString()

        validAttackDodgeBlock()
    }

    @JvmName("setBlockPerLevel1")
    fun setBlockPerLevel(newNum: Int){
        blockPerLevel = newNum
        updateBlock()
    }

    //updates the character's block value
    fun updateBlock(){
        block = pointInBlock + charInstance.modDEX + (blockPerLevel * charInstance.lvl)
        charInstance.weaponProficiencies.updateMartialMax()
    }

    //updates dodge points and updates a string item if one given
    val applyDodgePoint = {score: Int, textChange: MutableState<String>? ->
        pointInDodge = score
        charInstance.updateTotalSpent()
        updateDodge()

        if(textChange != null)
            textChange.value = dodge.toString()

        validAttackDodgeBlock()
    }

    @JvmName("setDodgePerLevel1")
    fun setDodgePerLevel(newNum: Int){
        dodgePerLevel = newNum
        updateDodge()
    }

    //updates the character's dodge value
    fun updateDodge(){
        dodge = pointInDodge + charInstance.modAGI + (dodgePerLevel * charInstance.lvl)
        charInstance.weaponProficiencies.updateMartialMax()
    }

    //determines if the attack, block, and dodge the user has taken is valid
    fun validAttackDodgeBlock(): Boolean{
        //if only one stat developed, cannot exceed 25% of overall devPT
        return ((pointInBlock == 0 && pointInDodge == 0 && pointInAttack * charInstance.ownClass.atkGrowth <= charInstance.devPT/4) ||
                (pointInAttack == 0 && pointInDodge == 0 && pointInBlock * charInstance.ownClass.blockGrowth <= charInstance.devPT/4) ||
                (pointInAttack == 0 && pointInBlock == 0 && pointInDodge * charInstance.ownClass.dodgeGrowth <= charInstance.devPT/4)) ||

                //attack, dodge, and block cannot equate to over 50% of overall devPT
                (((pointInAttack * charInstance.ownClass.atkGrowth) + (pointInBlock * charInstance.ownClass.blockGrowth) + (pointInDodge * charInstance.ownClass.dodgeGrowth) <= charInstance.devPT/2) &&

                        //attack can not be more than 50 of either one of block or dodge
                        (attack - block <= 50 || attack - dodge <= 50) &&

                        //neither block nor dodge can be 50 more than attack
                        (block - attack <= 50 && dodge - attack <= 50))
    }

    //updates wear armor points and updates a string item if one given
    val applyWearPoint = {score: Int, textChange: MutableState<String>? ->
        pointInWear = score
        charInstance.updateTotalSpent()
        updateWear()

        if(textChange != null)
            textChange.value = wearArmor.toString()

        true
    }

    @JvmName("setWearPerLevel1")
    fun setWearPerLevel(newNum: Int){
        wearPerLevel = newNum
        updateWear()
    }

    //updates the character's wear armor value
    fun updateWear(){
        wearArmor = pointInWear + charInstance.modSTR + (wearPerLevel * charInstance.lvl)
    }

    fun changeSpecInitiative(changeBy: Int){
        specInitiative += changeBy
        updateInitiative()
    }

    fun updateInitiative(){
        totalInitiative = (charInstance.ownClass.initiativePerLevel * charInstance.lvl) + charInstance.modDEX + charInstance.modAGI + specInitiative
    }

    //updates the character's resistances
    fun updateResistances(){
        resistPhys = ((presence + charInstance.modCON + rphysSpec) * rphysMult).toInt()
        resistDisease = ((presence + charInstance.modCON + rdSpec) * rdMult).toInt()
        resistVen = ((presence + charInstance.modCON + rvSpec) * rvMult).toInt()
        resistMag = ((presence + charInstance.modPOW + rmSpec) * rmMult).toInt()
        resistPsy = ((presence + charInstance.modWP + rpsySpec) * rpsyMult).toInt()
    }

    fun updateFatigue(){
        fatigue = charInstance.totalCON + specFatigue
    }

    fun getBaseRegen(){
        baseRegen = when(charInstance.totalCON){
            in 3..7 -> 1
            in 8..9 -> 2
            in 10..18 -> charInstance.totalCON - 7
            in 19..20 -> 12
            else -> 0
        }

        updateRegeneration()
    }

    fun updateRegeneration(){
        totalRegen = baseRegen + specRegen
    }

    fun calculateSpent(): Int{
        return pointInAttack * charInstance.ownClass.atkGrowth +
                pointInBlock * charInstance.ownClass.blockGrowth +
                pointInDodge * charInstance.ownClass.dodgeGrowth +
                pointInWear * charInstance.ownClass.armorGrowth
    }

    fun loadCombat(fileReader: BufferedReader){
        takeLifeMult(fileReader.readLine().toInt())
        applyAttackPoint(fileReader.readLine().toInt(), null)
        applyBlockPoint(fileReader.readLine().toInt(), null)
        applyDodgePoint(fileReader.readLine().toInt(), null)
        applyWearPoint(fileReader.readLine().toInt(), null)
    }

    fun writeCombat(){
        charInstance.addNewData(lifeMultsTaken)
        charInstance.addNewData(pointInAttack)
        charInstance.addNewData(pointInBlock)
        charInstance.addNewData(pointInDodge)
        charInstance.addNewData(pointInWear)
    }
}