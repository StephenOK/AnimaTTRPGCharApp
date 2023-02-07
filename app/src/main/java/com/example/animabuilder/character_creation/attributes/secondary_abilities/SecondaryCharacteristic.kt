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

class SecondaryCharacteristic(val name: Int, private val parent: SecondaryList) : Serializable {
    var pointsIn: Int = 0

    //initialize points from the associated modifier
    var modVal = 0

    //initialize points applied by the user
    var pointsApplied = 0

    //initialize development cost
    var devPerPoint = 0

    //initialize reduction in cost due to advantages
    var developmentDeduction = 0

    //initialize points per level from class
    var pointsFromClass = 0

    //initialize bonus points in this characteristic
    var special = 0

    //initialize bonus points per level in this characteristic
    var specialPerLevel = 0

    //initialize natural bonus application
    var bonusApplied = false

    //initialize final total
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

        if(points == 0 && bonusApplied) {
            setBonusApplied(false)
            parent.incrementNat(false)
        }

        updateDevSpent()
        refreshTotal()
    }

    @JvmName("setDevPerPoint1")
    fun setDevPerPoint(perPoints:Int){
        devPerPoint = perPoints
        updateDevSpent()
    }

    @JvmName("setDevelopmentDeduction1")
    fun setDevelopmentDeduction(perPoints: Int){
        developmentDeduction += perPoints
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

    @JvmName("setSpecialPerLevel1")
    fun setSpecialPerLevel(points: Int){
        specialPerLevel += points
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
            if(devPerPoint > developmentDeduction) pointsApplied * (devPerPoint - developmentDeduction)
            else pointsApplied
    }

    //recalculates the total value after any other setter is called
    fun refreshTotal() {
        total = modVal + pointsApplied + special +
                ((pointsFromClass + specialPerLevel) * parent.charInstance.lvl)
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