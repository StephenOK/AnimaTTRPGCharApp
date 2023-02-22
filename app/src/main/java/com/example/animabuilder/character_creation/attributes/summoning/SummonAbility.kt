package com.example.animabuilder.character_creation.attributes.summoning

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader
import java.io.Serializable

class SummonAbility(private val charInstance: BaseCharacter): Serializable {
    var buyVal = 0
    var modVal = 0
    var pointsPerLevel = 0
    var levelTotal = 0
    var abilityTotal = 0

    @JvmName("setBuyVal1")
    fun setBuyVal(input: Int){
        buyVal = input
        charInstance.updateTotalSpent()
        updateTotal()
    }

    @JvmName("setModVal1")
    fun setModVal(input: Int){
        modVal = input
        updateTotal()
    }

    @JvmName("setPointsPerLevel1")
    fun setPointsPerLevel(input: Int){
        pointsPerLevel = input
        updateLevelTotal()
    }

    fun updateLevelTotal(){
        levelTotal = pointsPerLevel * charInstance.lvl
        updateTotal()
    }

    fun updateTotal(){
        abilityTotal = buyVal + modVal + levelTotal
    }

    fun loadAbility(fileReader: BufferedReader){
        setBuyVal(fileReader.readLine().toInt())
    }

    fun writeAbility(){
        charInstance.addNewData(buyVal)
    }
}