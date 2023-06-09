package com.example.animabuilder.character_creation.attributes.secondary_abilities

import androidx.compose.runtime.mutableStateOf
import kotlin.Throws
import java.io.BufferedReader
import java.io.IOException

/**
 * Object for a single secondary characteristic.
 * Updates total whenever there is a change in other any value.
 *
 * @param parent full list that holds this object
 */

class SecondaryCharacteristic(private val parent: SecondaryList){
    //initialize points from the associated modifier
    val modVal = mutableStateOf(0)

    //initialize points applied by the user
    val pointsApplied = mutableStateOf(0)

    //initialize development cost
    val devPerPoint = mutableStateOf(0)

    //initialize reduction in cost due to advantages
    val developmentDeduction = mutableStateOf(0)

    //initialize points per level from class
    val classPointsPerLevel = mutableStateOf(0)
    val classPointTotal = mutableStateOf(0)

    //initialize bonus points in this characteristic
    val special = mutableStateOf(0)

    //initialize bonus points per level in this characteristic
    val specialPerLevel = mutableStateOf(0)

    //initialize natural bonus application
    val bonusApplied = mutableStateOf(false)

    //initialize development points spent in this characteristic
    val pointsIn = mutableStateOf(0)

    //initialize final total
    val total = mutableStateOf(0)

    /**
     * Setter for characteristic's modifier.
     *
     * @param value amount to set the mod to
     */
    @JvmName("setModVal1")
    fun setModVal(value: Int) {
        modVal.value = value
        refreshTotal()
    }

    /**
     * Setter for points applied by user.
     *
     * @param points amount to set the points applied to
     */
    @JvmName("setPointsApplied1")
    fun setPointsApplied(points: Int) {
        pointsApplied.value = points

        //remove natural bonus if no points applied to this stat
        if(points == 0 && bonusApplied.value)
            setBonusApplied(false)

        updateDevSpent()
        refreshTotal()
    }

    /**
     * Set the number of development points spent per bought point in this characteristic.
     *
     * @param perPoints value to set the development point multiplier to
     */
    @JvmName("setDevPerPoint1")
    fun setDevPerPoint(perPoints:Int){
        devPerPoint.value = perPoints
        updateDevSpent()
    }

    /**
     * Change any deduction to the development point cost by the indicated amount.
     *
     * @param perPoints amount to change the deduction by
     */
    @JvmName("setDevelopmentDeduction1")
    fun setDevelopmentDeduction(perPoints: Int){
        developmentDeduction.value += perPoints
        updateDevSpent()
    }

    /**
     * Setter for class points.
     *
     * @param points amount to set the points per level to
     */
    @JvmName("setClassPointsPerLevel1")
    fun setClassPointsPerLevel(points: Int) {
        classPointsPerLevel.value = points
        classTotalRefresh()
    }

    /**
     * Updates the number of points gained from levels for this characteristic.
     */
    fun classTotalRefresh(){
        classPointTotal.value =
            if(parent.charInstance.lvl.value != 0) classPointsPerLevel.value * parent.charInstance.lvl.value
            else classPointsPerLevel.value/2

        refreshTotal()
    }

    /**
     * Setter for special points.
     *
     * @param points amount to change the special bonus to this characteristic by
     */
    @JvmName("setSpecial1")
    fun setSpecial(points: Int) {
        special.value += points
        refreshTotal()
    }

    /**
     * Change the characteristic's bonus by the indicated amount.
     *
     * @param points amount to change the bonus by
     */
    @JvmName("setSpecialPerLevel1")
    fun setSpecialPerLevel(points: Int){
        specialPerLevel.value += points
        refreshTotal()
    }

    /**
     * Setter for natural bonus.
     *
     * @param bonus true if applying a natural bonus to the characteristic
     */
    @JvmName("setBonusApplied1")
    fun setBonusApplied(bonus: Boolean) {
        bonusApplied.value = bonus
        refreshTotal()
    }

    /**
     * Get actual development points spent per point applied to this characteristic.
     */
    fun updateDevSpent(){
        pointsIn.value =
            if(devPerPoint.value > developmentDeduction.value) pointsApplied.value * (devPerPoint.value - developmentDeduction.value)
            else pointsApplied.value

        parent.charInstance.updateTotalSpent()
    }

    /**
     * Recalculates the total value after any other setter is called.
     */
    fun refreshTotal() {
        total.value = modVal.value + pointsApplied.value + special.value +
                ((classPointsPerLevel.value + specialPerLevel.value) * parent.charInstance.lvl.value)
        if(parent.allTradesTaken.value) total.value += 10
        else if (pointsApplied.value == 0) total.value -= 30

        if (bonusApplied.value) total.value += 5
    }

    /**
     * Loads data for the characteristic from given file reader
     *
     * @param fileReader file to read the data from
     */
    @Throws(IOException::class)
    fun load(fileReader: BufferedReader) {
        //retrieve characteristic's applied points and natural bonus state
        setPointsApplied(fileReader.readLine().toInt())
        setBonusApplied(fileReader.readLine().toBoolean())
    }

    /**
     * Write characteristic data to the file output stream
     */
    fun write() {
        //record characteristic's applied points and natural bonus state
        parent.charInstance.addNewData(pointsApplied.value)
        parent.charInstance.addNewData(bonusApplied.value.toString())
    }
}