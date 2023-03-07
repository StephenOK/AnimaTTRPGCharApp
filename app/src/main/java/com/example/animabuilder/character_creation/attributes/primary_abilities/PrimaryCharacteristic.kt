package com.example.animabuilder.character_creation.attributes.primary_abilities

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader
import java.io.Serializable
import kotlin.math.ceil

class PrimaryCharacteristic(
    private val charInstance: BaseCharacter,
    private val advantageCap: Int,
    private val charIndex: Int,
    private val setUpdate: (Int) -> Unit
): Serializable {
    var inputValue = 0
    var bonus = 0
    var total = 0
    var outputMod = 0

    fun setInput(input: Int){
        inputValue = input

        while(inputValue + bonus > advantageCap &&
                charInstance.advantageRecord.getAdvantage("Add One Point to a Characteristic", charIndex, 0) != null){
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.getAdvantage("Add One Point to a Characteristic", charIndex, 0)!!)
        }

        updateValues()
    }

    @JvmName("setBonus1")
    fun setBonus(input: Int){
        bonus += input
        updateValues()
    }

    fun updateValues(){
        total =
            if(charInstance.advantageRecord.getAdvantage("Increase One Characteristic to Nine", charIndex, 0) != null) 9
            else inputValue + bonus

        outputMod =
            if(total < 5){
                when(total){
                    2 -> -20
                    3 -> -10
                    4 -> -5
                    else -> -30
                }
            }
            else
                (15 * (total/5 - 1) + 5 * ceil(total % 5 / 2.0)).toInt()

        setUpdate(outputMod)
    }

    fun load(fileReader: BufferedReader){
        setInput(fileReader.readLine().toInt())
    }

    fun write(){
        charInstance.addNewData(inputValue)
    }
}