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
    charInstance: BaseCharacter,
    maxNumVM: BottomBarViewModel,
    context: Context
): ViewModel() {
    private val _nameInput = MutableStateFlow(charInstance.charName)
    private val _sizeInput = MutableStateFlow(charInstance.sizeCategory.toString())
    private val _appearInput = MutableStateFlow(charInstance.appearance.toString())

    val nameInput = _nameInput.asStateFlow()
    val sizeInput = _sizeInput.asStateFlow()
    val appearInput = _appearInput.asStateFlow()

    fun setNameInput(newIn: String){_nameInput.update{newIn}}
    private fun setSizeInput(newIn: String){_sizeInput.update{newIn}}
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
        R.array.classArray,
        charInstance.classes.allClasses.indexOf(charInstance.ownClass)
    ){
        charInstance.setOwnClass(it)
        maxNumVM.updateMaxValues(charInstance)
    }

    private val raceDropdown = DropdownData(
        R.string.raceText,
        R.array.raceArray,
        charInstance.ownRace.raceIndex
    ){
        charInstance.setOwnRace(it)
        strengthData.setOutput(charInstance.primaryList.str)
        setSizeInput(charInstance.sizeCategory.toString())
        setAppearInput(charInstance.appearance.toString())
    }

    private val levelDropdown = DropdownData(
        R.string.levelText,
        R.array.levelCountArray,
        charInstance.lvl
    ){
        charInstance.setLvl(it)
        maxNumVM.setMaxDP(charInstance.devPT)
        maxNumVM.updateMaxValues(charInstance)
    }

    val dropdownList = listOf(classDropdown, raceDropdown, levelDropdown)

    class DropdownData(
        val nameRef: Int,
        val options: Int,
        initialIndex: Int,
        val onChange: (Int) -> Unit
    ){
        private val _size = MutableStateFlow(Size.Zero)
        private val _indexTracker = MutableStateFlow(initialIndex)
        private val _isOpen = MutableStateFlow(false)
        private val _icon = MutableStateFlow(Icons.Filled.KeyboardArrowDown)

        val size = _size.asStateFlow()
        val indexTracker = _indexTracker.asStateFlow()
        val isOpen = _isOpen.asStateFlow()
        val icon = _icon.asStateFlow()

        fun setSize(input: Size){_size.update{input}}

        fun setIndexTracker(index: Int){
            _indexTracker.update{index}
            onChange(index)
        }

        fun setOpen(openState: Boolean){
            _isOpen.update{openState}
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
        val modVal: String,
    )
}