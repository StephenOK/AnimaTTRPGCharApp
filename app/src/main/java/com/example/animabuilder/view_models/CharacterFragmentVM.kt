package com.example.animabuilder.view_models

import androidx.lifecycle.ViewModel
import com.example.animabuilder.character_creation.attributes.primary_abilities.PrimaryCharacteristic
import com.example.animabuilder.character_creation.attributes.primary_abilities.PrimaryList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CharacterFragmentVM(primaryList: PrimaryList): ViewModel() {
    val strengthData = PrimeCharacteristicData(primaryList.str)
    val dexterityData = PrimeCharacteristicData(primaryList.dex)
    val agilityData = PrimeCharacteristicData(primaryList.agi)
    val constitutionData = PrimeCharacteristicData(primaryList.con)
    val intelligenceData = PrimeCharacteristicData(primaryList.int)
    val powerData = PrimeCharacteristicData(primaryList.pow)
    val willpowerData = PrimeCharacteristicData(primaryList.wp)
    val perceptionData = PrimeCharacteristicData(primaryList.per)

    class PrimeCharacteristicData(primeItem: PrimaryCharacteristic){
        private val _input = MutableStateFlow(primeItem.inputValue.toString())
        private val _output = MutableStateFlow(
            PrimaryStrings(
                primeItem.bonus.toString(),
                primeItem.outputMod.toString()
            )
        )

        val input = _input.asStateFlow()
        val output = _output.asStateFlow()

        fun setInput(input: String){_input.update{input}}
        fun setOutput(primeInput: PrimaryCharacteristic){
            _output.update{
                it.copy(
                    specialVal = primeInput.bonus.toString(),
                    modVal = primeInput.outputMod.toString()
                )
            }
        }
    }

    data class PrimaryStrings(
        val specialVal: String,
        val modVal: String
    )
}