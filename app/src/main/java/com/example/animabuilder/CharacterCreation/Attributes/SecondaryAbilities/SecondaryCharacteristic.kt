package com.example.animabuilder.CharacterCreation.Attributes.SecondaryAbilities

import kotlin.Throws
import com.example.animabuilder.CharacterCreation.Attributes.SecondaryAbilities.SecondaryList
import com.example.animabuilder.Serializables.SerialOutputStream
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.Serializable
import java.nio.charset.StandardCharsets

class SecondaryCharacteristic : Serializable {

    var modVal = 0
    var pointsApplied = 0
    var devPerPoint = 0
    var pointsFromClass = 0
    var bonusApplied = false
    var total = 0

    @JvmName("getModVal1")
    fun getModVal(): Int {
        return modVal
    }

    @JvmName("setModVal1")
    fun setModVal(value: Int) {
        modVal = value
        refreshTotal()
    }

    @JvmName("getPointsApplied1")
    fun getPointsApplied(): Int {
        return pointsApplied
    }

    @JvmName("setPointsApplied1")
    fun setPointsApplied(points: Int) {
        pointsApplied = points
        refreshTotal()
    }

    @JvmName("getPointsFromClass1")
    fun getPointsFromClass(): Int {
        return pointsFromClass
    }

    @JvmName("setPointsFromClass1")
    fun setPointsFromClass(points: Int) {
        pointsFromClass = points
        refreshTotal()
    }

    fun isBonusApplied(): Boolean {
        return bonusApplied
    }

    @JvmName("setBonusApplied1")
    fun setBonusApplied(bonus: Boolean) {
        bonusApplied = bonus
        refreshTotal()
    }

    private fun refreshTotal() {
        total = modVal + pointsApplied + pointsFromClass
        if (pointsApplied == 0) total -= 30 else if (bonusApplied) total += 5
    }

    @Throws(IOException::class)
    fun load(fileReader: BufferedReader, caller: SecondaryList) {
        setPointsApplied(fileReader.readLine().toInt())
        val loadBoolean = fileReader.readLine()
        if (loadBoolean == "true") {
            setBonusApplied(true)
            caller.incrementNat(true)
        } else setBonusApplied(false)
    }

    fun write(byteArray: SerialOutputStream) {
        byteArray.write(
            """$pointsApplied
""".toByteArray(StandardCharsets.UTF_8), 0, """$pointsApplied
""".toByteArray(StandardCharsets.UTF_8).size
        )
        val booleanIn: String
        booleanIn = if (bonusApplied) "true" else "false"
        byteArray.write(
            """$booleanIn
""".toByteArray(StandardCharsets.UTF_8), 0, """$booleanIn
""".toByteArray(StandardCharsets.UTF_8).size
        )
    }

    init {
        refreshTotal()
    }
}