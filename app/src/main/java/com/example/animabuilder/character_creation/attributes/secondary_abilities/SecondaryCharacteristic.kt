package com.example.animabuilder.character_creation.attributes.secondary_abilities

import com.example.animabuilder.character_creation.BaseCharacter
import kotlin.Throws
import com.example.animabuilder.serializables.SerialOutputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.Serializable
import java.nio.charset.StandardCharsets

/**
 * Object for a single secondary characteristic
 * Updates total whenever there is a change in other any value
 */

class SecondaryCharacteristic : Serializable {
    var pointsIn: Int = 0

    //initialize values associated with the secondary characteristic
    var modVal = 0
    var pointsApplied = 0
    var devPerPoint = 0
    var pointsFromClass = 0
    var bonusApplied = false
    var total = 0

    //setter for characteristic's modifier
    @JvmName("setModVal1")
    fun setModVal(value: Int) {
        modVal = value
        refreshTotal()
    }

    //setter for points applied by user
    @JvmName("setPointsApplied1")
    fun setPointsApplied(points: Int) {
        pointsApplied = points
        pointsIn = pointsApplied * devPerPoint
        refreshTotal()
    }

    @JvmName("setDevPerPoint1")
    fun setDevPerPoint(perPoints:Int){
        devPerPoint = perPoints
        pointsIn = pointsApplied * devPerPoint
    }

    //setter for class points
    @JvmName("setPointsFromClass1")
    fun setPointsFromClass(points: Int) {
        pointsFromClass = points
        refreshTotal()
    }

    //setter for natural bonus
    @JvmName("setBonusApplied1")
    fun setBonusApplied(bonus: Boolean) {
        bonusApplied = bonus
        refreshTotal()
    }

    //recalculates the total value after any other setter is called
    private fun refreshTotal() {
        total = modVal + pointsApplied + pointsFromClass
        if (pointsApplied == 0) total -= 30 else if (bonusApplied) total += 5
    }

    /**
     * Loads data for the characteristic from given file reader
     */
    @Throws(IOException::class)
    fun load(
        fileReader: BufferedReader,
        caller: SecondaryList
    ) {
        //get and set the user's recorded applied points
        setPointsApplied(fileReader.readLine().toInt())

        //get and set the character's natural bonus status
        val loadBoolean = fileReader.readLine()
        if (loadBoolean == "true") {
            setBonusApplied(true)
            caller.incrementNat(true)
        }
        else setBonusApplied(false)
    }

    /**
     * Write characteristic data to the file output stream
     */
    fun write(charInstance: BaseCharacter) {
        charInstance.addNewData(pointsApplied)

        //write natural bonus applied data
        val booleanIn: String = if (bonusApplied) "true" else "false"

        charInstance.addNewData(booleanIn)
    }

    init {
        refreshTotal()
    }
}