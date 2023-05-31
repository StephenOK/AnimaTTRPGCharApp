package com.example.animabuilder.character_creation

import androidx.compose.runtime.mutableStateOf
import java.io.BufferedReader

class RuleRecord(val charInstance: BaseCharacter) {
    val isCoreExxet = mutableStateOf(false)
    val hasDominus = mutableStateOf(false)
    val hasArcana = mutableStateOf(false)
    val hasPromethium = mutableStateOf(false)

    fun toggleCoreExxet(){isCoreExxet.value = !isCoreExxet.value}
    fun toggleDominus(){hasDominus.value = !hasDominus.value}
    fun toggleArcana(){hasArcana.value = !hasArcana.value}
    fun togglePromethium(){hasPromethium.value = !hasPromethium.value}

    fun loadRules(fileReader: BufferedReader){
        isCoreExxet.value = fileReader.readLine().toBoolean()
        hasDominus.value = fileReader.readLine().toBoolean()
        hasArcana.value = fileReader.readLine().toBoolean()
        hasPromethium.value = fileReader.readLine().toBoolean()
    }

    fun writeRules(){
        charInstance.addNewData(isCoreExxet.value)
        charInstance.addNewData(hasDominus.value)
        charInstance.addNewData(hasArcana.value)
        charInstance.addNewData(hasPromethium.value)
    }
}