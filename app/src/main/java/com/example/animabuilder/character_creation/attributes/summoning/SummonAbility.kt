package com.example.animabuilder.character_creation.attributes.summoning

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader
import java.io.Serializable

/**
 * Object that manages one of the summoning abilities.
 *
 * @param charInstance object that manages all of the character's stats
 */
class SummonAbility(private val charInstance: BaseCharacter): Serializable {
    //initialize points spent in this item
    var buyVal = 0

    //initialize modifier for this item
    var modVal = 0

    //initialize points gained in this item per level
    var pointsPerLevel = 0

    //initialize total class points in this item
    var levelTotal = 0

    //initialize total in this item
    var abilityTotal = 0

    /**
     * Set the number of points purchased in this section.
     *
     * @param input number of points to buy for this section
     */
    @JvmName("setBuyVal1")
    fun setBuyVal(input: Int){
        buyVal = input
        charInstance.updateTotalSpent()
        updateTotal()
    }

    /**
     * Set the number of modifier points for this item.
     *
     * @param input value to set the modifier to
     */
    @JvmName("setModVal1")
    fun setModVal(input: Int){
        modVal = input
        updateTotal()
    }

    /**
     * Set the number of points gained in this item per level.
     *
     * @param input value to set the per level value to
     */
    @JvmName("setPointsPerLevel1")
    fun setPointsPerLevel(input: Int){
        pointsPerLevel = input
        updateLevelTotal()
    }

    /**
     * Refresh the number of points in this stat from levels.
     */
    fun updateLevelTotal(){
        levelTotal = pointsPerLevel * charInstance.lvl
        updateTotal()
    }

    /**
     * Refresh the total value for this item.
     */
    fun updateTotal(){
        abilityTotal = buyVal + modVal + levelTotal
    }

    /**
     * Get data for this item from file.
     *
     * @param fileReader file to get the data from
     */
    fun loadAbility(fileReader: BufferedReader){
        setBuyVal(fileReader.readLine().toInt())
    }

    /**
     * Write the points bought in this item to file.
     */
    fun writeAbility(){
        charInstance.addNewData(buyVal)
    }
}