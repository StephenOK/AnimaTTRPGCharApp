package com.example.animabuilder.character_creation.attributes.primary_abilities

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader
import java.io.Serializable

class PrimaryList(private val charInstance: BaseCharacter): Serializable {
    val str = PrimaryCharacteristic(
        charInstance,
        11,
        0
    ){
        charInstance.combat.wearArmor.setModPoints(it)
        charInstance.updateSize()

        charInstance.secondaryList.updateSTR()
        charInstance.ki.updateKiStats()
    }

    val dex = PrimaryCharacteristic(
        charInstance,
        11,
        1
    ){
        charInstance.secondaryList.updateDEX()
        charInstance.combat.attack.setModPoints(it)
        charInstance.combat.block.setModPoints(it)
        charInstance.combat.updateInitiative()
        charInstance.ki.updateKiStats()
        charInstance.magic.calcMagProj()
        charInstance.psychic.updatePsyProjection()
    }

    val agi = PrimaryCharacteristic(
        charInstance,
        11,
        2
    ){
        charInstance.secondaryList.updateAGI()
        charInstance.combat.dodge.setModPoints(it)
        charInstance.combat.updateInitiative()
        charInstance.ki.updateKiStats()
    }

    val con = PrimaryCharacteristic(
        charInstance,
        11,
        3
    ){
        charInstance.combat.updateFatigue()
        charInstance.combat.getBaseRegen()
        charInstance.updateSize()

        charInstance.combat.updateLifeBase()
        charInstance.combat.updateLifePoints()
        charInstance.combat.diseaseRes.setMod(it)
        charInstance.combat.venomRes.setMod(it)
        charInstance.combat.physicalRes.setMod(it)
        charInstance.ki.updateKiStats()
    }

    val int = PrimaryCharacteristic(
        charInstance,
        13,
        4
    ){
        charInstance.secondaryList.updateINT()
        charInstance.magic.setMagicLevelMax()
    }

    val pow = PrimaryCharacteristic(
        charInstance,
        13,
        5
    ){
        charInstance.secondaryList.updatePOW()
        charInstance.combat.magicRes.setMod(it)
        charInstance.ki.updateKiStats()
        charInstance.magic.setBaseZeon()
        charInstance.magic.setBaseZeonAcc()
        charInstance.summoning.summon.setModVal(it)
        charInstance.summoning.bind.setModVal(it)
        charInstance.summoning.banish.setModVal(it)
    }

    val wp = PrimaryCharacteristic(
        charInstance,
        13,
        6
    ){
        charInstance.secondaryList.updateWP()
        charInstance.combat.psychicRes.setMod(it)
        charInstance.ki.updateKiStats()
        charInstance.summoning.control.setModVal(it)
        charInstance.psychic.setBasePotential()
    }

    val per = PrimaryCharacteristic(
        charInstance,
        13,
        7
    ){
        charInstance.secondaryList.updatePER()
    }

    val allPrimaries = listOf(str, dex, agi, con, int, pow, wp, per)

    fun loadPrimaries(fileReader: BufferedReader){
        allPrimaries.forEach{it.load(fileReader)}
    }

    fun writePrimaries(){
        allPrimaries.forEach{it.write()}
    }
}