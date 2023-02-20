package com.example.animabuilder.character_creation.attributes.combat

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader
import java.io.Serializable

class CombatItem(
    private val charInstance: BaseCharacter
): Serializable {
    var inputVal = 0
    var modPoints = 0
    var pointFromClass = 0
    var classBonus = 0
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

    @JvmName("setPointFromClass1")
    fun setPointFromClass(input: Int){
        pointFromClass = (input * charInstance.lvl) + classBonus

        if(pointFromClass > 50)
            pointFromClass = 50

        updateTotal()
    }

    fun levelUpdate(){setPointFromClass(pointFromClass)}

    @JvmName("setClassBonus1")
    fun setClassBonus(input: Int){
        classBonus = input
        setPointFromClass(pointFromClass)
    }

    fun updateTotal(){
        total = inputVal + modPoints + pointFromClass
        charInstance.weaponProficiencies.updateMartialMax()
    }

    fun loadItem(fileReader: BufferedReader){
        setInputVal(fileReader.readLine().toInt())
    }

    fun writeItem(){
        charInstance.addNewData(inputVal)
    }
}