package com.paetus.animaCharCreator.character_creation.attributes.primary_abilities

import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import kotlin.math.ceil

/**
 * Core stat of the character build.
 *
 * @param charInstance object that holds all of the character's stats
 * @param advantageCap highest value stat can get from an advantage
 * @param charIndex index of this characteristic
 * @param setUpdate function to run on this stat's change
 */
class PrimaryCharacteristic(
    val charInstance: BaseCharacter,
    private val advantageCap: Int,
    val charIndex: Int,
    private val setUpdate: (mod: Int, total: Int) -> Unit
){
    //base value fo the stat
    val inputValue = mutableStateOf(0)

    //additional points in this stata
    val bonus = mutableStateOf(0)

    //additional points in this stat due to character levels
    val levelBonus = mutableStateOf(0)

    //total value of the stat
    val total = mutableStateOf(0)

    //modifier value for this stat
    val outputMod = mutableStateOf(0)

    /**
     * Sets the base value of the stat.
     *
     * @param input value to set the base to
     */
    fun setInput(input: Int){
        //set base to 9 if advantage requires it
        if(charInstance.advantageRecord.getAdvantage(
                "Increase One Characteristic to Nine",
                charIndex,
                0
            ) != null
        )
            inputValue.value = 9

        //set the base stat value
        else inputValue.value = input

        //remove any excess advantage bonuses
        while(inputValue.value + bonus.value > advantageCap &&
                charInstance.advantageRecord.getAdvantage("Add One Point to a Characteristic", charIndex, 0) != null){
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.getAdvantage("Add One Point to a Characteristic", charIndex, 0)!!)
        }

        //update related values
        updateValues()
    }

    /**
     * Change the character's stat bonus by the indicated amount.
     *
     * @param input amount to change the bonus by
     */
    @JvmName("setBonus1")
    fun setBonus(input: Int){
        bonus.value += input
        updateValues()
    }

    /**
     * Sets the character's bonus in this stat from levels.
     *
     * @param input value to set as bonus
     */
    @JvmName("setLevelBonus1")
    fun setLevelBonus(input: Int){
        levelBonus.value = input
        updateValues()
    }

    /**
     * Updates the stat's total, modifier, and updates related character values.
     */
    fun updateValues(){
        //change the total value
        total.value = inputValue.value + bonus.value + levelBonus.value

        //update the modifier's value
        outputMod.value =
            if(total.value < 5){
                when(total.value){
                    2 -> -20
                    3 -> -10
                    4 -> -5
                    else -> -30
                }
            }
            else
                (15 * (total.value/5 - 1) + 5 * ceil(total.value % 5 / 2.0)).toInt()

        //update any related stats
        setUpdate(outputMod.value, total.value)
    }

    /**
     * Load the data on the characteristic from the file.
     *
     * @param fileReader file to get the data from
     */
    fun load(fileReader: BufferedReader){
        setInput(fileReader.readLine().toInt())
        setLevelBonus(fileReader.readLine().toInt())
    }

    /**
     * Save the characteristic's data to file.
     */
    fun write(byteArray: ByteArrayOutputStream) {
        writeDataTo(byteArray, inputValue.value)
        writeDataTo(byteArray, levelBonus.value)
    }
}