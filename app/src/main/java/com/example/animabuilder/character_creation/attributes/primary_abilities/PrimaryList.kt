package com.example.animabuilder.character_creation.attributes.primary_abilities

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader

/**
 * Record of all of a character's primary characteristic.
 *
 * @param charInstance object that contains all of the character's stats
 */
class PrimaryList(private val charInstance: BaseCharacter){
    val str = PrimaryCharacteristic(
        charInstance,
        11,
        0
    ){mod, total ->
        charInstance.setWeightIndex(total)
        charInstance.combat.wearArmor.setModPoints(mod)
        charInstance.updateSize()

        charInstance.secondaryList.updateSTR()
        charInstance.ki.strKi.primaryUpdate(total)
    }

    val dex = PrimaryCharacteristic(
        charInstance,
        11,
        1
    ){mod, total ->
        charInstance.secondaryList.updateDEX()
        charInstance.combat.attack.setModPoints(mod)
        charInstance.combat.block.setModPoints(mod)
        charInstance.combat.updateInitiative()
        charInstance.ki.dexKi.primaryUpdate(total)
        charInstance.magic.calcMagProj()
        charInstance.psychic.updatePsyProjection()
    }

    val agi = PrimaryCharacteristic(
        charInstance,
        11,
        2
    ){mod, total ->
        charInstance.setMovement(total)
        charInstance.secondaryList.updateAGI()
        charInstance.combat.dodge.setModPoints(mod)
        charInstance.combat.updateInitiative()
        charInstance.ki.agiKi.primaryUpdate(total)
    }

    val con = PrimaryCharacteristic(
        charInstance,
        11,
        3
    ){mod, total ->
        charInstance.combat.updateFatigue()
        charInstance.combat.getBaseRegen()
        charInstance.updateSize()

        charInstance.combat.updateLifeBase()
        charInstance.combat.updateLifePoints()
        charInstance.combat.diseaseRes.setMod(mod)
        charInstance.combat.venomRes.setMod(mod)
        charInstance.combat.physicalRes.setMod(mod)
        charInstance.ki.conKi.primaryUpdate(total)
    }

    val int = PrimaryCharacteristic(
        charInstance,
        13,
        4
    ){mod, total ->
        charInstance.secondaryList.updateINT()
        charInstance.magic.setMagicLevelMax()
    }

    val pow = PrimaryCharacteristic(
        charInstance,
        13,
        5
    ){mod, total ->
        charInstance.secondaryList.updatePOW()
        charInstance.combat.magicRes.setMod(mod)
        charInstance.ki.powKi.primaryUpdate(total)
        charInstance.magic.setBaseZeon()
        charInstance.magic.setBaseZeonAcc()
        charInstance.summoning.summon.setModVal(mod)
        charInstance.summoning.bind.setModVal(mod)
        charInstance.summoning.banish.setModVal(mod)
    }

    val wp = PrimaryCharacteristic(
        charInstance,
        13,
        6
    ){mod, total ->
        charInstance.secondaryList.updateWP()
        charInstance.combat.psychicRes.setMod(mod)
        charInstance.ki.wpKi.primaryUpdate(total)
        charInstance.summoning.control.setModVal(mod)
        charInstance.psychic.setBasePotential()
    }

    val per = PrimaryCharacteristic(
        charInstance,
        13,
        7
    ){mod, total ->
        charInstance.secondaryList.updatePER()
    }

    //gather all primary characteristics into a list
    val allPrimaries = listOf(str, dex, agi, con, int, pow, wp, per)

    /**
     * Determines if the total level bonuses applied are valid.
     *
     * @return true if bonus does not exceed half of the character's level
     */
    fun validLevelBonuses(): Boolean{
        var total = 0

        allPrimaries.forEach{total += it.levelBonus}

        return total <= charInstance.lvl/2
    }

    /**
     * Get data for each of the primary characteristics.
     *
     * @param fileReader file to get the data from
     */
    fun loadPrimaries(fileReader: BufferedReader){
        allPrimaries.forEach{it.load(fileReader)}
    }

    /**
     * Save all primary characteristic data to file.
     */
    fun writePrimaries(){
        allPrimaries.forEach{it.write()}
    }
}