package com.paetus.animaCharCreator.character_creation

import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Defines the rule sets that the created character will adhere to.
 */
class RuleRecord {
    //initialize if base or core exxet is used
    private val isCoreExxet = mutableStateOf(value = false)

    //initialize use of dominus exxet
    private val hasDominus = mutableStateOf(value = false)

    //initialize use of arcana exxet
    private val hasArcana = mutableStateOf(value = false)

    //initialize use of promethium exxet
    private val hasPromethium = mutableStateOf(value = false)

    /**
     * Toggles between using the base rules or the core exxet.
     */
    fun toggleCoreExxet(){isCoreExxet.value = !isCoreExxet.value}

    /**
     * Toggles between using or not using the dominus exxet.
     */
    fun toggleDominus(){hasDominus.value = !hasDominus.value}

    /**
     * Toggles between using or not using the arcana exxet.
     */
    fun toggleArcana(){hasArcana.value = !hasArcana.value}

    /**
     * Toggles between using or not using the promethium exxet.
     */
    fun togglePromethium(){hasPromethium.value = !hasPromethium.value}

    /**
     * Loads the rules used for this character.
     *
     * @param fileReader object that looks through the character's file
     */
    fun loadRules(fileReader: BufferedReader){
        isCoreExxet.value = fileReader.readLine().toBoolean()
        hasDominus.value = fileReader.readLine().toBoolean()
        hasArcana.value = fileReader.readLine().toBoolean()
        hasPromethium.value = fileReader.readLine().toBoolean()
    }

    /**
     * Writes this section's data to the character's file.
     */
    fun writeRules(byteArray: ByteArrayOutputStream) {
        writeDataTo(writer = byteArray, input = isCoreExxet.value)
        writeDataTo(writer = byteArray, input = hasDominus.value)
        writeDataTo(writer = byteArray, input = hasArcana.value)
        writeDataTo(writer = byteArray, input = hasPromethium.value)
    }
}