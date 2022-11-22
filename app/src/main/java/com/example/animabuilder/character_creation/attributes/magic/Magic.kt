package com.example.animabuilder.character_creation.attributes.magic

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.Serializable

class Magic(private val charInstance: BaseCharacter) : Serializable {
    var baseZeon = 0
    var boughtZeon = 0
    var zeonMax = 0

    var baseZeonAcc = 0
    var zeonAccMult = 1
    var zeonAccTotal = 0

    var boughtMagProjection = 0
    var magProjTotal = 0
    var magProjImbalance = 0
    var imbalanceIsAttack = true

    var magicLevel = 0

    @JvmName("setBaseZeon1")
    fun setBaseZeon() {
        baseZeon =
            if(charInstance.pow == 1)
                5
            else
                20 + (10 * charInstance.pow) + charInstance.modPOW

        calcMaxZeon()
    }

    fun buyZeon(toBuy: Int){
        boughtZeon = toBuy
        calcMaxZeon()
        charInstance.updateTotalSpent()
    }

    fun calcMaxZeon(){
        zeonMax = baseZeon + (boughtZeon * 5) + (charInstance.lvl * charInstance.ownClass.zeonPerLevel)
    }

    @JvmName("setBaseZeonAcc1")
    fun setBaseZeonAcc() {
        baseZeonAcc = when(charInstance.pow){
            in 5..7 -> 5
            in 8 .. 11 -> 10
            in 12..14 -> 15
            15 -> 20
            16, 17 -> 25
            18, 19, -> 30
            20 -> 35
            else -> 0
        }

        calcZeonAcc()
    }

    fun buyZeonAcc(toBuy: Int){
        zeonAccMult = toBuy
        calcZeonAcc()
        charInstance.updateTotalSpent()
    }

    fun calcZeonAcc(){
        zeonAccTotal = baseZeonAcc * zeonAccMult
    }

    fun buyMagProj(toBuy: Int){
        boughtMagProjection = toBuy
        calcMagProj()
    }

    fun calcMagProj(){
        magProjTotal = charInstance.modDEX + boughtMagProjection
    }

    @JvmName("setMagicLevel1")
    fun setMagicLevel(){
        magicLevel = when(charInstance.int) {
            in 6..10 -> (charInstance.int) * 10
            11 -> 75
            12 -> 100
            13 -> 150
            in 14..20 -> (charInstance.int - 12)* 100
            else -> 0
        }
    }

    fun calculateSpent(): Int{
         return boughtZeon * charInstance.ownClass.zeonGrowth +
                (zeonAccMult - 1) * charInstance.ownClass.maGrowth +
                magProjTotal * charInstance.ownClass.maProjGrowth
    }
}