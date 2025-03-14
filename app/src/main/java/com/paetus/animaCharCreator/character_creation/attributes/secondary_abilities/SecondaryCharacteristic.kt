package com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Object for a single secondary characteristic.
 * Updates total whenever there is a change in other any value.
 *
 * @param parent full list that holds this object
 */

open class SecondaryCharacteristic(private val parent: SecondaryList){
    //initialize points from the associated modifier
    val modVal = mutableIntStateOf(value = 0)

    //initialize points applied by the user
    val pointsApplied = mutableIntStateOf(0)

    //initialize development cost
    val devPerPoint = mutableIntStateOf(value = 2)

    //initialize reduction in cost due to advantages
    val developmentDeduction = mutableIntStateOf(value = 0)

    //initialize points per level from class
    val classPointsPerLevel = mutableIntStateOf(value = 0)
    val classPointTotal = mutableIntStateOf(value = 0)

    //initialize bonus points in this characteristic
    val special = mutableIntStateOf(value = 0)

    //initialize bonus points per level in this characteristic
    val specialPerLevel = mutableIntStateOf(value = 0)

    //initialize natural bonus application
    val bonusApplied = mutableStateOf(value = false)

    //initialize development points spent in this characteristic
    val pointsIn = mutableIntStateOf(value = 0)

    //initialize final total
    val total = mutableIntStateOf(value = -30)

    /**
     * Setter for characteristic's modifier.
     *
     * @param modValue amount to set the mod to
     */
    fun setModVal(modValue: Int) {
        modVal.intValue = modValue
        refreshTotal()
    }

    /**
     * Setter for points applied by user.
     *
     * @param pointInput amount to set the points applied to
     */
    open fun setPointsApplied(pointInput: Int) {
        pointsApplied.intValue = pointInput

        //remove natural bonus if no points applied to this stat
        if(pointInput == 0 && bonusApplied.value)
            setNatBonus(natBonus = false)

        updateDevSpent()
        refreshTotal()
    }

    /**
     * Set the number of development points spent per bought point in this characteristic.
     *
     * @param dpCost value to set the development point multiplier to
     */
    fun setDevCost(dpCost: Int){
        devPerPoint.intValue = dpCost
        updateDevSpent()
    }

    /**
     * Change any deduction to the development point cost by the indicated amount.
     *
     * @param dpDeduction amount to change the deduction by
     */
    fun setDevelopmentDeduction(dpDeduction: Int){
        developmentDeduction.intValue += dpDeduction
        updateDevSpent()
    }

    /**
     * Setter for class points.
     *
     * @param classBonus amount to set the points per level to
     */
    open fun setClassPointsPerLevel(classBonus: Int) {
        classPointsPerLevel.intValue = classBonus
        classTotalRefresh()
    }

    /**
     * Updates the number of points gained from levels for this characteristic.
     */
    open fun classTotalRefresh(){
        classPointTotal.intValue =
            if(parent.charInstance.lvl.intValue != 0) classPointsPerLevel.intValue * parent.charInstance.lvl.intValue
            else classPointsPerLevel.intValue/2

        refreshTotal()
    }

    /**
     * Setter for special points.
     *
     * @param specBonus amount to change the special bonus to this characteristic by
     */
    fun setSpecial(specBonus: Int) {
        special.intValue += specBonus
        refreshTotal()
    }

    /**
     * Change the characteristic's bonus by the indicated amount.
     *
     * @param lvlBonus amount to change the bonus by
     */
    fun setSpecialPerLevel(lvlBonus: Int){
        specialPerLevel.intValue += lvlBonus
        refreshTotal()
    }

    /**
     * Setter for natural bonus.
     *
     * @param natBonus true if applying a natural bonus to the characteristic
     */
    open fun setNatBonus(natBonus: Boolean) {
        bonusApplied.value = natBonus
        refreshTotal()
    }

    /**
     * Get actual development points spent per point applied to this characteristic.
     */
    fun updateDevSpent(){
        pointsIn.intValue =
            if(devPerPoint.intValue > developmentDeduction.intValue) pointsApplied.intValue * (devPerPoint.intValue - developmentDeduction.intValue)
            else pointsApplied.intValue

        parent.charInstance.updateTotalSpent()
    }

    /**
     * Recalculates the total value after any other setter is called.
     */
    open fun refreshTotal() {
        //add all invested and level points for this section
        total.intValue = modVal.intValue + pointsApplied.intValue + special.intValue +
                ((classPointsPerLevel.intValue + specialPerLevel.intValue) * parent.charInstance.lvl.intValue)

        //add natural bonus points
        if (bonusApplied.value) total.intValue += 5

        //add points for jack of all trades advantage
        if(parent.allTradesTaken.value) total.intValue += 10
        //remove points for no points and missing that advantage
        else if (pointsApplied.intValue == 0) total.intValue -= 30
    }

    /**
     * Loads data for the characteristic from given file reader
     *
     * @param fileReader file to read the data from
     */
    fun load(fileReader: BufferedReader) {
        //retrieve characteristic's applied points and natural bonus state
        setPointsApplied(pointInput = fileReader.readLine().toInt())
        setNatBonus(natBonus = fileReader.readLine().toBoolean())
    }

    /**
     * Write characteristic data to the file output stream
     *
     * @param byteArray output stream for this item's data
     */
    open fun write(byteArray: ByteArrayOutputStream) {
        //record characteristic's applied points and natural bonus state
        writeDataTo(writer = byteArray, input = pointsApplied.intValue)
        writeDataTo(writer = byteArray, input = bonusApplied.value)
    }
}