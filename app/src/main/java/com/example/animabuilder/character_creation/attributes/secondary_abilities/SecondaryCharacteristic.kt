package com.example.animabuilder.character_creation.attributes.secondary_abilities

import com.example.animabuilder.character_creation.BaseCharacter
import kotlin.Throws
import java.io.BufferedReader
import java.io.IOException
import java.io.Serializable

/**
 * Object for a single secondary characteristic
 * Updates total whenever there is a change in other any value
 */

class SecondaryCharacteristic(private val parent: SecondaryList) : Serializable {
    var pointsIn: Int = 0

    //initialize values associated with the secondary characteristic
    var modVal = 0
    var pointsApplied = 0
    var devPerPoint = 0
    var advPerPoint = 0
    var pointsFromClass = 0
    var special = 0
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
        updateDevSpent()
        refreshTotal()
    }

    @JvmName("setDevPerPoint1")
    fun setDevPerPoint(perPoints:Int){
        devPerPoint = perPoints
        updateDevSpent()
    }

    @JvmName("setAdvPerPoint1")
    fun setAdvPerPoint(perPoints: Int){
        advPerPoint += perPoints
        updateDevSpent()
    }

    //setter for class points
    @JvmName("setPointsFromClass1")
    fun setPointsFromClass(points: Int) {
        pointsFromClass = points
        refreshTotal()
    }

    //setter for special points
    @JvmName("setSpecial1")
    fun setSpecial(points: Int) {
        special += points
        refreshTotal()
    }

    //setter for natural bonus
    @JvmName("setBonusApplied1")
    fun setBonusApplied(bonus: Boolean) {
        bonusApplied = bonus
        refreshTotal()
    }

    private fun updateDevSpent(){
        pointsIn =
            if(devPerPoint > advPerPoint) pointsApplied * (devPerPoint - advPerPoint)
            else pointsApplied
    }

    //recalculates the total value after any other setter is called
    fun refreshTotal() {
        total = modVal + pointsApplied + pointsFromClass + special
        if(parent.allTradesTaken) total += 10
        else if (pointsApplied == 0) total -= 30

        if (bonusApplied) total += 5
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