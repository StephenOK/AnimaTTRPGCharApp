package com.example.animabuilder.character_creation.attributes.primary_abilities

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader
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
    private val charInstance: BaseCharacter,
    private val advantageCap: Int,
    private val charIndex: Int,
    private val setUpdate: (mod: Int, total: Int) -> Unit
){
    //base value fo the stat
    var inputValue = 0

    //additional points in this stata
    var bonus = 0

    //total value of the stat
    var total = 0

    //modifier value for this stat
    var outputMod = 0

    /**
     * Sets the base value of the stat.
     *
     * @param input value to set the base to
     */
    fun setInput(input: Int){
        //set the base stat value
        inputValue = input

        //remove any excess advantage bonuses
        while(inputValue + bonus > advantageCap &&
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
        bonus += input
        updateValues()
    }

    /**
     * Updates the stat's total, modifier, and updates related character values.
     */
    fun updateValues(){
        //change the total value
        total =
            if(charInstance.advantageRecord.getAdvantage("Increase One Characteristic to Nine", charIndex, 0) != null) 9
            else inputValue + bonus

        //update the modifier's value
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

        //update any related stats
        setUpdate(outputMod, total)
    }

    /**
     * Load the data on the characteristic from the file.
     *
     * @param fileReader file to get the data from
     */
    fun load(fileReader: BufferedReader){
        setInput(fileReader.readLine().toInt())
    }

    /**
     * Save the characteristic's data to file.
     */
    fun write(){
        charInstance.addNewData(inputValue)
    }
}