package com.paetus.animaCharCreator.character_creation.attributes.summoning

import androidx.compose.runtime.mutableIntStateOf
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Object that manages one of the summoning abilities.
 *
 * @param charInstance object that manages all of the character's stats
 */
open class SummonAbility(private val charInstance: BaseCharacter){
    //initialize points spent in this item
    val buyVal = mutableIntStateOf(value = 0)

    //initialize modifier for this item
    val modVal = mutableIntStateOf(value = 0)

    //initialize points gained in this item per level
    val pointsPerLevel = mutableIntStateOf(value = 0)

    //initialize total class points in this item
    val levelTotal = mutableIntStateOf(value = 0)

    //initialize total in this item
    val abilityTotal = mutableIntStateOf(value = 0)

    /**
     * Set the number of points purchased in this item.
     *
     * @param pointPurchase number of points to buy for this section
     */
    open fun setBuyVal(pointPurchase: Int){
        buyVal.intValue = pointPurchase
        charInstance.updateTotalSpent()
        updateTotal()
    }

    /**
     * Set the number of modifier points for this item.
     *
     * @param modValue value to set the modifier to
     */
    fun setModVal(modValue: Int){
        modVal.intValue = modValue
        updateTotal()
    }

    /**
     * Set the number of points gained in this item per level.
     *
     * @param lvlBonus value to set the per level value to
     */
    fun setPointsPerLevel(lvlBonus: Int){
        pointsPerLevel.intValue = lvlBonus
        updateLevelTotal()
    }

    /**
     * Refresh the number of points in this stat from levels.
     */
    open fun updateLevelTotal(){
        levelTotal.intValue =
            if(charInstance.lvl.intValue != 0) pointsPerLevel.intValue * charInstance.lvl.intValue
            else pointsPerLevel.intValue/2

        updateTotal()
    }

    /**
     * Refresh the total value for this item.
     */
    fun updateTotal(){
        abilityTotal.intValue = buyVal.intValue + modVal.intValue + levelTotal.intValue
    }

    /**
     * Get data for this item from file.
     *
     * @param fileReader file to get the data from
     */
    fun loadAbility(fileReader: BufferedReader){
        setBuyVal(pointPurchase = fileReader.readLine().toInt())
    }

    /**
     * Write the points bought in this item to file.
     *
     * @param byteArray output stream for this item's data
     */
    fun writeAbility(byteArray: ByteArrayOutputStream) {
        writeDataTo(writer = byteArray, input = buyVal.intValue)
    }
}