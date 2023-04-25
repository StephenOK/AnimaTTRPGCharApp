package com.example.animabuilder.character_creation.attributes.secondary_abilities

import kotlin.Throws
import java.io.BufferedReader
import java.io.IOException

/**
 * Object for a single secondary characteristic.
 * Updates total whenever there is a change in other any value.
 *
 * @param parent full list that holds this objejct
 */

class SecondaryCharacteristic(private val parent: SecondaryList){
    //initialize points from the associated modifier
    var modVal = 0

    //initialize points applied by the user
    var pointsApplied = 0

    //initialize development cost
    var devPerPoint = 0

    //initialize reduction in cost due to advantages
    var developmentDeduction = 0

    //initialize points per level from class
    var classPointsPerLevel = 0
    var classPointTotal = 0

    //initialize bonus points in this characteristic
    var special = 0

    //initialize bonus points per level in this characteristic
    var specialPerLevel = 0

    //initialize natural bonus application
    var bonusApplied = false

    //initialize development points spent in this characteristic
    var pointsIn: Int = 0

    //initialize final total
    var total = 0

    /**
     * Setter for characteristic's modifier.
     *
     * @param value amount to set the mod to
     */
    @JvmName("setModVal1")
    fun setModVal(value: Int) {
        modVal = value
        refreshTotal()
    }

    /**
     * Setter for points applied by user.
     *
     * @param points amount to set the points applied to
     */
    @JvmName("setPointsApplied1")
    fun setPointsApplied(points: Int) {
        pointsApplied = points

        //remove natural bonus if no points applied to this stat
        if(points == 0 && bonusApplied)
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
        devPerPoint = perPoints
        updateDevSpent()
    }

    /**
     * Change any deduction to the development point cost by the indicated amount.
     *
     * @param perPoints amount to change the deduction by
     */
    @JvmName("setDevelopmentDeduction1")
    fun setDevelopmentDeduction(perPoints: Int){
        developmentDeduction += perPoints
        updateDevSpent()
    }

    /**
     * Setter for class points.
     *
     * @param points amount to set the points per level to
     */
    @JvmName("setClassPointsPerLevel1")
    fun setClassPointsPerLevel(points: Int) {
        classPointsPerLevel = points
        classTotalRefresh()
    }

    /**
     * Updates the number of points gained from levels for this characteristic.
     */
    fun classTotalRefresh(){
        classPointTotal =
            if(parent.charInstance.lvl != 0) classPointsPerLevel * parent.charInstance.lvl
            else classPointsPerLevel/2

        refreshTotal()
    }

    /**
     * Setter for special points.
     *
     * @param points amount to change the special bonus to this characteristic by
     */
    @JvmName("setSpecial1")
    fun setSpecial(points: Int) {
        special += points
        refreshTotal()
    }

    /**
     * Change the characteristic's bonus by the indicated amount.
     *
     * @param points amount to change the bonus by
     */
    @JvmName("setSpecialPerLevel1")
    fun setSpecialPerLevel(points: Int){
        specialPerLevel += points
        refreshTotal()
    }

    /**
     * Setter for natural bonus.
     *
     * @param bonus true if applying a natural bonus to the characteristic
     */
    @JvmName("setBonusApplied1")
    fun setBonusApplied(bonus: Boolean) {
        bonusApplied = bonus
        refreshTotal()
    }

    /**
     * Get actual development points spent per point applied to this characteristic.
     */
    fun updateDevSpent(){
        pointsIn =
            if(devPerPoint > developmentDeduction) pointsApplied * (devPerPoint - developmentDeduction)
            else pointsApplied

        parent.charInstance.updateTotalSpent()
    }

    /**
     * Recalculates the total value after any other setter is called.
     */
    fun refreshTotal() {
        total = modVal + pointsApplied + special +
                ((classPointsPerLevel + specialPerLevel) * parent.charInstance.lvl)
        if(parent.allTradesTaken) total += 10
        else if (pointsApplied == 0) total -= 30

        if (bonusApplied) total += 5
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
        parent.charInstance.addNewData(pointsApplied)
        parent.charInstance.addNewData(bonusApplied.toString())
    }
}