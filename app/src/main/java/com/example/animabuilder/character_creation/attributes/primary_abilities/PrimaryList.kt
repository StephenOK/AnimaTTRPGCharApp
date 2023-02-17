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
        charInstance.combat.updateWear()
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
        charInstance.combat.updateAttack()
        charInstance.combat.updateBlock()
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
        charInstance.combat.updateDodge()
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
        charInstance.combat.updateResistances()
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
        charInstance.combat.updateResistances()
        charInstance.ki.updateKiStats()
        charInstance.magic.setBaseZeon()
        charInstance.magic.setBaseZeonAcc()
        charInstance.summoning.updateSummon()
        charInstance.summoning.updateBind()
        charInstance.summoning.updateBanish()
    }

    val wp = PrimaryCharacteristic(
        charInstance,
        13,
        6
    ){
        charInstance.secondaryList.updateWP()
        charInstance.combat.updateResistances()
        charInstance.ki.updateKiStats()
        charInstance.summoning.updateControl()
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