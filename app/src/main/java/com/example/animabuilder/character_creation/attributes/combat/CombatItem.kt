package com.example.animabuilder.character_creation.attributes.combat

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader
import java.io.Serializable

class CombatItem(
    private val charInstance: BaseCharacter
): Serializable {
    var inputVal = 0
    var modPoints = 0
    var pointPerLevel = 0
    var classBonus = 0
    var classTotal = 0
    var total = 0

    @JvmName("setInputVal1")
    fun setInputVal(score: Int){
        inputVal = score
        charInstance.updateTotalSpent()
        updateTotal()
    }

    @JvmName("setModPoints1")
    fun setModPoints(input: Int){
        modPoints = input
        updateTotal()
    }

    @JvmName("setPointPerLevel1")
    fun setPointPerLevel(input: Int){
        pointPerLevel = input
        updateClassTotal()
    }

    @JvmName("setClassBonus1")
    fun setClassBonus(input: Int){
        classBonus += input
        updateClassTotal()
    }

    fun updateClassTotal(){
        classTotal = (pointPerLevel * charInstance.lvl) + classBonus
        if(classTotal > 50)
            classTotal = 50

        updateTotal()
    }

    fun updateTotal(){
        total = inputVal + modPoints + classTotal
        charInstance.weaponProficiencies.updateMartialMax()
    }

    fun loadItem(fileReader: BufferedReader){
        setInputVal(fileReader.readLine().toInt())
    }

    fun writeItem(){
        charInstance.addNewData(inputVal)
    }
}