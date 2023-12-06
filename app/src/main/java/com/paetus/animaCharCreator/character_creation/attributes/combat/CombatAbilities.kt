package com.paetus.animaCharCreator.character_creation.attributes.combat

import androidx.compose.runtime.mutableIntStateOf
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Section of the character that holds combat data, such as life points and attack.
 * Holds data on the character's resistances.
 * Holds data on the character's initiative, fatigue, and regeneration.
 *
 * @param charInstance object that holds all of the character's data
 */
class CombatAbilities(private val charInstance: BaseCharacter){
    //set default presence
    val presence = mutableIntStateOf(20)

    //character's health items
    val lifeBase = mutableIntStateOf(0)
    val lifeMultsTaken = mutableIntStateOf(0)
    val lifeClassTotal = mutableIntStateOf(0)
    val lifeMax = mutableIntStateOf(0)

    //initialize the character's combat data
    val attack = CombatItem(charInstance = charInstance)
    val block = CombatItem(charInstance = charInstance)
    val dodge = CombatItem(charInstance = charInstance)
    val wearArmor = CombatItem(charInstance = charInstance)

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
    val specInitiative = mutableIntStateOf(0)
    val totalInitiative = mutableIntStateOf(0)

    //initialize fatigue
    val fatigue = mutableIntStateOf(0)
    val specFatigue = mutableIntStateOf(0)

    //initialize regeneration
    private val baseRegen = mutableIntStateOf(0)
    val specRegen = mutableIntStateOf(0)
    val totalRegen = mutableIntStateOf(0)

    /**
     * Update the character's presence.
     */
    fun updatePresence(){
        //set the presence value
        presence.intValue =
            if(charInstance.lvl.intValue == 0)
                20
            else
                25 + (5 * charInstance.lvl.intValue)

        //update the character's resistances
        allResistances.forEach{resistance -> resistance.setBase(presence.intValue)}
    }

    /**
     * Update the base number of life points based on the character's constitution.
     */
    fun updateLifeBase(){
        lifeBase.intValue = if(charInstance.primaryList.con.total.value == 1)
            5
        else
            20 + (charInstance.primaryList.con.total.value * 10) + charInstance.primaryList.con.outputMod.value
    }

    /**
     * Updates the number of life multiples the user is taking for their character.
     *
     * @param multTake number of multiples the user intends to take
     */
    fun takeLifeMult(
        multTake: Int
    ){
        lifeMultsTaken.intValue = multTake
        charInstance.updateTotalSpent()
        updateLifePoints()
    }

    /**
     * Updates the number of life points gained from their class and level.
     */
    fun updateClassLife(){
        lifeClassTotal.intValue =
            if(charInstance.lvl.intValue != 0)
                charInstance.ownClass.value.lifePointsPerLevel * charInstance.lvl.intValue
            else charInstance.ownClass.value.lifePointsPerLevel/2

        updateLifePoints()
    }

    /**
     * Updates the character's total life points
     */
    fun updateLifePoints(){
        lifeMax.intValue =
            lifeBase.intValue +
                    (lifeMultsTaken.intValue * charInstance.primaryList.con.total.value) +
                    lifeClassTotal.intValue
    }

    /**
     * Determines if the attack, block, and dodge the user has taken is valid.
     *
     * @return valid status of the combat inputs
     */
    fun validAttackDodgeBlock(): Boolean{
        //if only one stat developed, cannot exceed 25% of overall devPT
        return ((block.inputVal.intValue == 0 && dodge.inputVal.intValue == 0 && attack.inputVal.intValue * charInstance.ownClass.value.atkGrowth <= charInstance.devPT.intValue/4) ||
                (attack.inputVal.intValue == 0 && dodge.inputVal.intValue == 0 && block.inputVal.intValue * charInstance.ownClass.value.blockGrowth <= charInstance.devPT.intValue/4) ||
                (attack.inputVal.intValue == 0 && block.inputVal.intValue == 0 && dodge.inputVal.intValue * charInstance.ownClass.value.dodgeGrowth <= charInstance.devPT.intValue/4)) ||

                //attack, dodge, and block cannot equate to over 50% of overall devPT
                (((attack.inputVal.intValue * charInstance.ownClass.value.atkGrowth) + (block.inputVal.intValue * charInstance.ownClass.value.blockGrowth) + (dodge.inputVal.intValue * charInstance.ownClass.value.dodgeGrowth) <= charInstance.devPT.intValue/2) &&

                        //attack can not be more than 50 of either one of block or dodge
                        (attack.total.intValue - block.total.intValue <= 50 || attack.total.intValue - dodge.total.intValue <= 50) &&

                        //neither block nor dodge can be 50 more than attack
                        (block.total.intValue - attack.total.intValue <= 50 && dodge.total.intValue - attack.total.intValue <= 50))
    }

    /**
     * Applies changes to the special initiative value.
     *
     * @param initSpecial value to alter the special initiative by
     */
    fun changeSpecInitiative(initSpecial: Int){
        specInitiative.intValue += initSpecial
        updateInitiative()
    }

    /**
     * Function that updates the character's total initiative.
     */
    fun updateInitiative(){
        //only add half a level value if character is level 0
        val classInitiative =
            if(charInstance.lvl.intValue != 0) charInstance.ownClass.value.initiativePerLevel * charInstance.lvl.intValue
            else charInstance.ownClass.value.initiativePerLevel/2

        //add together class level value, dexterity, agility, and special input
        totalInitiative.intValue =
            classInitiative + charInstance.primaryList.dex.outputMod.value +
                    charInstance.primaryList.agi.outputMod.value + specInitiative.intValue
    }

    /**
     * Function that updates the character's total fatigue.
     */
    fun updateFatigue(){
        fatigue.intValue = charInstance.primaryList.con.total.value + specFatigue.intValue
    }

    /**
     * Retrieves the character's base regeneration value.
     */
    fun getBaseRegen(){
        //get value based on the character's constitution
        baseRegen.intValue = when(charInstance.primaryList.con.total.value){
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
        totalRegen.intValue = baseRegen.intValue + specRegen.intValue
    }

    /**
     * Calculates the total development points spent in this section.
     *
     * @return development points spent
     */
    fun calculateSpent(): Int{
        return attack.inputVal.intValue * charInstance.ownClass.value.atkGrowth +
                block.inputVal.intValue * charInstance.ownClass.value.blockGrowth +
                dodge.inputVal.intValue * charInstance.ownClass.value.dodgeGrowth +
                wearArmor.inputVal.intValue * charInstance.ownClass.value.armorGrowth
    }

    /**
     * Load the data for the combat section.
     *
     * @param fileReader file to retrieve the data from
     */
    fun loadCombat(fileReader: BufferedReader){
        //retrieve life multiple data
        takeLifeMult(multTake = fileReader.readLine().toInt())

        //retrieve data on attack, defenses, and wear armor
        allAbilities.forEach{combatItem ->
            combatItem.loadItem(fileReader = fileReader)
        }
    }

    /**
     * Write the data for the combat section.
     *
     * @param byteArray output stream to write the data to
     */
    fun writeCombat(byteArray: ByteArrayOutputStream) {
        //write life multiple data
        writeDataTo(writer = byteArray, input = lifeMultsTaken.intValue)

        //write data on attack, defenses, and wear armor
        allAbilities.forEach{combatItem ->
            combatItem.writeItem(byteArray = byteArray)
        }
    }
}