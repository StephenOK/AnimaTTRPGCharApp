package com.example.animabuilder.view_models

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.primary_abilities.PrimaryCharacteristic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CharacterFragmentViewModel(
    private val charInstance: BaseCharacter,
    maxNumVM: HomePageViewModel,
    context: Context
): ViewModel() {
    private val _nameInput = MutableStateFlow(charInstance.charName)
    private val _sizeInput = MutableStateFlow(charInstance.sizeCategory.toString())
    private val _appearInput = MutableStateFlow(charInstance.appearance.toString())

    val nameInput = _nameInput.asStateFlow()
    val sizeInput = _sizeInput.asStateFlow()
    val appearInput = _appearInput.asStateFlow()

    fun isNotUnattractive(): Boolean{
        return charInstance.advantageRecord.getAdvantage("Unattractive") == null
    }

    fun setNameInput(newIn: String){
        charInstance.charName = newIn
        _nameInput.update{newIn}
    }
    private fun setSizeInput(newIn: String){_sizeInput.update{newIn}}

    fun setAppearInput(newIn: Int): Boolean{
        charInstance.setAppearance(newIn)

        val output = charInstance.appearance == newIn
        if(output)
            setAppearInput(newIn.toString())

        return output
    }
    fun setAppearInput(newIn: String){_appearInput.update{newIn}}

    private val strengthData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[0],
        charInstance.primaryList.str,
    ){this.setSizeInput(charInstance.sizeCategory.toString())}

    private val dexterityData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[1],
        charInstance.primaryList.dex
    ){}

    private val agilityData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[2],
        charInstance.primaryList.agi
    ){}

    private val constitutionData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[3],
        charInstance.primaryList.con
    ){this.setSizeInput(charInstance.sizeCategory.toString())}

    private val intelligenceData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[6],
        charInstance.primaryList.int
    ){}

    private val powerData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[4],
        charInstance.primaryList.pow
    ){}

    private val willpowerData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[5],
        charInstance.primaryList.wp
    ){}

    private val perceptionData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[7],
        charInstance.primaryList.per
    ){}

    val primaryDataList = listOf(strengthData, dexterityData, agilityData, constitutionData,
        intelligenceData, powerData, willpowerData, perceptionData)

    private val classDropdown = DropdownData(
        R.string.classText,
        context.resources.getStringArray(R.array.classArray),
        charInstance.classes.allClasses.indexOf(charInstance.ownClass)
    ) {
        charInstance.setOwnClass(it)
        maxNumVM.maximums.updateItems(
            charInstance.devPT,
            charInstance.maxCombatDP,
            charInstance.maxMagDP,
            charInstance.maxPsyDP
        )
    }

    private val raceDropdown = DropdownData(
        R.string.raceText,
        context.resources.getStringArray(R.array.raceArray),
        charInstance.races.allAdvantageLists.indexOf(charInstance.ownRace)
    ) {
        charInstance.setOwnRace(it)
        strengthData.setOutput()
        setSizeInput(charInstance.sizeCategory.toString())
        setAppearInput(charInstance.appearance.toString())
    }

    private val levelDropdown = DropdownData(
        R.string.levelText,
        context.resources.getStringArray(R.array.levelCountArray),
        charInstance.lvl
    ) {
        charInstance.setLvl(it)
        maxNumVM.maximums.updateItems(
            charInstance.devPT,
            charInstance.maxCombatDP,
            charInstance.maxMagDP,
            charInstance.maxPsyDP
        )
    }

    val dropdownList = listOf(classDropdown, raceDropdown, levelDropdown)

    class DropdownData(
        val nameRef: Int,
        val options: Array<String>,
        initialIndex: Int,
        val onChange: (Int) -> Unit
    ){
        private val _size = MutableStateFlow(Size.Zero)
        private val _output = MutableStateFlow(options[initialIndex])
        private val _isOpen = MutableStateFlow(false)
        private val _icon = MutableStateFlow(Icons.Filled.KeyboardArrowDown)

        val size = _size.asStateFlow()
        val output = _output.asStateFlow()
        val isOpen = _isOpen.asStateFlow()
        val icon = _icon.asStateFlow()

        fun setSize(input: Size){_size.update{input}}

        fun setOutput(index: Int){
            _output.update{options[index]}
            onChange(index)
            openToggle()
        }

        fun openToggle() {
            _isOpen.update{!isOpen.value}
            _icon.update{
                if(isOpen.value) Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown
            }
        }
    }

    class PrimeCharacteristicData(
        val name: String,
        val primaryStat: PrimaryCharacteristic,
        val changeFunc: () -> Unit
    ){
        private val _input = MutableStateFlow(primaryStat.inputValue.toString())
        private val _output = MutableStateFlow(
            PrimaryStrings(
                primaryStat.bonus.toString(),
                primaryStat.outputMod.toString()
            )
        )

        val input = _input.asStateFlow()
        val output = _output.asStateFlow()

        fun setInput(input: Int){
            primaryStat.setInput(input)
            setInput(input.toString())
            setOutput()
            changeFunc()
        }
        fun setInput(input: String){_input.update{input}}
        fun setOutput() {
            _output.update{
                it.copy(
                    specialVal = primaryStat.bonus.toString(),
                    modVal = primaryStat.outputMod.toString()
                )
            }
        }
    }

    data class PrimaryStrings(
        val specialVal: String,
        val modVal: String,
    )
}