package com.example.animabuilder.character_creation

import androidx.compose.runtime.mutableStateOf
import java.io.BufferedReader

class RuleRecord(val charInstance: BaseCharacter) {
    //initialize if base or core exxet is used
    val isCoreExxet = mutableStateOf(false)

    //initialize use of dominus exxet
    val hasDominus = mutableStateOf(false)

    //initialize use of arcana exxet
    val hasArcana = mutableStateOf(false)

    //initialize use of promethium exxet
    val hasPromethium = mutableStateOf(false)

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
    fun writeRules(){
        charInstance.addNewData(isCoreExxet.value)
        charInstance.addNewData(hasDominus.value)
        charInstance.addNewData(hasArcana.value)
        charInstance.addNewData(hasPromethium.value)
    }
}