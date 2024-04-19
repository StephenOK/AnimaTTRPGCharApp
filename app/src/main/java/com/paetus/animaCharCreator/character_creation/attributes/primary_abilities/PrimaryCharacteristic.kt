package com.paetus.animaCharCreator.character_creation.attributes.primary_abilities

import androidx.compose.runtime.mutableIntStateOf
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
open class PrimaryCharacteristic(
    open val charInstance: BaseCharacter,
    private val advantageCap: Int,
    open val charIndex: Int,
    private val setUpdate: (mod: Int, total: Int) -> Unit
){
    //base value fo the stat
    val inputValue = mutableIntStateOf(value = 5)

    //additional points in this stata
    val bonus = mutableIntStateOf(value = 0)

    //additional points in this stat due to character levels
    val levelBonus = mutableIntStateOf(value = 0)

    //total value of the stat
    val total = mutableIntStateOf(value = 5)

    //modifier value for this stat
    val outputMod = mutableIntStateOf(value = 0)

    /**
     * Sets the base value of the stat.
     *
     * @param baseIn value to set the base to
     */
    fun setInput(baseIn: Int){
        //set base to 9 if advantage requires it
        if(charInstance.advantageRecord.getAdvantage(
                "Increase One Characteristic to Nine",
                charIndex,
                0
            ) != null
        )
            inputValue.intValue = 9

        //set the base stat value
        else inputValue.intValue = baseIn

        //remove any excess advantage bonuses
        while(inputValue.intValue + bonus.intValue > advantageCap &&
                charInstance.advantageRecord.getAdvantage(
                    name = "Add One Point to a Characteristic",
                    taken = charIndex,
                    cost = 0
                ) != null){
            charInstance.advantageRecord.removeAdvantage(advantage = charInstance.advantageRecord.getAdvantage("Add One Point to a Characteristic", charIndex, 0)!!)
        }

        //update related values
        updateValues()
    }

    /**
     * Change the character's stat bonus by the indicated amount.
     *
     * @param bonusInput amount to change the bonus by
     */
    fun setBonus(bonusInput: Int){
        bonus.intValue += bonusInput
        updateValues()
    }

    /**
     * Sets the character's bonus in this stat from levels.
     *
     * @param lvlBonus value to set as bonus
     */
    open fun setLevelBonus(lvlBonus: Int){
        levelBonus.intValue = lvlBonus
        updateValues()
    }

    /**
     * Updates the stat's total, modifier, and updates related character values.
     */
    fun updateValues(){
        //change the total value
        total.intValue = inputValue.intValue + bonus.intValue + levelBonus.intValue

        //update the modifier's value
        outputMod.intValue =
            when(total.intValue){
                1 -> -30
                2 -> -20
                3 -> -10
                4 -> -5
                else -> (15 * (total.intValue/5 - 1) + 5 * ceil(total.intValue % 5 / 2.0)).toInt()
            }

        //update any related stats
        setUpdate(outputMod.intValue, total.intValue)
    }

    /**
     * Load the data on the characteristic from the file.
     *
     * @param fileReader file to get the data from
     */
    fun load(fileReader: BufferedReader){
        setInput(baseIn = fileReader.readLine().toInt())
        setLevelBonus(lvlBonus = fileReader.readLine().toInt())
    }

    /**
     * Save the characteristic's data to file.
     *
     * @param byteArray output stream of the data
     */
    fun write(byteArray: ByteArrayOutputStream) {
        writeDataTo(writer = byteArray, input = inputValue.intValue)
        writeDataTo(writer = byteArray, input = levelBonus.intValue)
    }
}