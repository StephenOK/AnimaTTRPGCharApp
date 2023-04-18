package com.example.animabuilder.view_models

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.primary_abilities.PrimaryCharacteristic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the base character attributes such as class, race, and level.
 * Works on variables with the corresponding character fragment.
 *
 * @param charInstance object that holds all of the character's stats
 * @param maxNumVM viewModel for the bottom bar values
 * @param context context passed from the HomePageFragment for obtaining resources
 */
class CharacterFragmentViewModel(
    private val charInstance: BaseCharacter,
    maxNumVM: HomePageViewModel,
    context: Context
): ViewModel() {
    //initialize input for the character's name
    private val _nameInput = MutableStateFlow(charInstance.charName)
    val nameInput = _nameInput.asStateFlow()

    //initialize the character's size category display
    private val _sizeInput = MutableStateFlow(charInstance.sizeCategory.toString())
    val sizeInput = _sizeInput.asStateFlow()

    //initialize the character's appearance input string
    private val _appearInput = MutableStateFlow(charInstance.appearance.toString())
    val appearInput = _appearInput.asStateFlow()

    /**
     * Checks if the character has the Unattractive disadvantage.
     *
     * @return true if character has the Unattractive disadvantage
     */
    fun isNotUnattractive(): Boolean{
        return charInstance.advantageRecord.getAdvantage("Unattractive") == null
    }

    /**
     * Sets the character's name to the user's input.
     *
     * @param newIn name to set for the character
     */
    fun setNameInput(newIn: String){
        charInstance.charName = newIn
        _nameInput.update{newIn}
    }

    /**
     * Sets the size category display to the character's value.
     */
    private fun setSizeInput() {_sizeInput.update{charInstance.sizeCategory.toString()}}

    /**
     * Attempts to change the character's appearance to the inputted value.
     *
     * @param newIn value to set the appearance to
     * @return true if there was a change inputted
     */
    fun setAppearInput(newIn: Int): Boolean{
        //set appearance in the character
        charInstance.setAppearance(newIn)

        //check that a change occurred
        val output = charInstance.appearance == newIn

        //change display string if there was a change
        if(output)
            setAppearInput(newIn.toString())

        //return if a change happened
        return output
    }

    /**
     * Sets the string display for the character's appearance.
     *
     * @param newIn value to set the display to
     */
    fun setAppearInput(newIn: String){_appearInput.update{newIn}}

    //initialize level bonus string color
    private val _bonusColor = MutableStateFlow(Color.Black)
    val bonusColor = _bonusColor.asStateFlow()

    /**
     * Sets the text color based on the applied level bonuses to the character.
     */
    fun setBonusColor(){
        //set text to black if valid
        if(charInstance.primaryList.validLevelBonuses())
            _bonusColor.update{Color.Black}

        //set text to red if invalid
        else
            _bonusColor.update{Color.Red}
    }

    //set all primary characteristic data
    private val strengthData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[0],
        charInstance.primaryList.str,
        {setBonusColor()},
    ){this.setSizeInput()}

    private val dexterityData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[1],
        charInstance.primaryList.dex,
        {setBonusColor()}
    ){}

    private val agilityData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[2],
        charInstance.primaryList.agi,
        {setBonusColor()}
    ){}

    private val constitutionData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[3],
        charInstance.primaryList.con,
        {setBonusColor()}
    ){this.setSizeInput()}

    private val intelligenceData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[6],
        charInstance.primaryList.int,
        {setBonusColor()}
    ){}

    private val powerData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[4],
        charInstance.primaryList.pow,
        {setBonusColor()}
    ){}

    private val willpowerData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[5],
        charInstance.primaryList.wp,
        {setBonusColor()}
    ){}

    private val perceptionData = PrimeCharacteristicData(
        context.resources.getStringArray(R.array.primaryCharArray)[7],
        charInstance.primaryList.per,
        {setBonusColor()}
    ){}

    //gather all primary data
    val primaryDataList = listOf(strengthData, dexterityData, agilityData, constitutionData,
        intelligenceData, powerData, willpowerData, perceptionData)

    //set class dropdown data
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

    //set race dropdown data
    private val raceDropdown = DropdownData(
        R.string.raceText,
        context.resources.getStringArray(R.array.raceArray),
        charInstance.races.allAdvantageLists.indexOf(charInstance.ownRace)
    ) {
        charInstance.setOwnRace(it)
        strengthData.setOutput()
        setSizeInput()
        setAppearInput(charInstance.appearance.toString())
    }

    //set level dropdown data
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
        setBonusColor()
    }

    //set all dropdown data
    val dropdownList = listOf(classDropdown, raceDropdown, levelDropdown)

    /**
     * Object that holds data for the page's dropdown menus.
     *
     * @param nameRef reference to the title of the item
     * @param options list of options to show in the dropdown
     * @param initialIndex item to initially display
     * @param onChange function to run on the option change
     */
    class DropdownData(
        val nameRef: Int,
        val options: Array<String>,
        initialIndex: Int,
        val onChange: (Int) -> Unit
    ){
        //initialize box size
        private val _size = MutableStateFlow(Size.Zero)
        val size = _size.asStateFlow()

        //initialize the selected item
        private val _output = MutableStateFlow(options[initialIndex])
        val output = _output.asStateFlow()

        //initialize dropdown's open state
        private val _isOpen = MutableStateFlow(false)
        val isOpen = _isOpen.asStateFlow()

        //initialize dropdown icon
        private val _icon = MutableStateFlow(Icons.Filled.KeyboardArrowDown)
        val icon = _icon.asStateFlow()

        /**
         * Setter for the item's size.
         *
         * @param input size to define for this object
         */
        fun setSize(input: Size){_size.update{input}}

        /**
         * Sets the item to the indicated index.
         *
         * @param index location of the item selection
         */
        fun setOutput(index: Int){
            _output.update{options[index]}

            //perform the required change and close the dropdown
            onChange(index)
            openToggle()
        }

        /**
         * Changes the open state of the dropdown item.
         */
        fun openToggle() {
            _isOpen.update{!isOpen.value}
            _icon.update{
                if(isOpen.value) Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown
            }
        }
    }

    /**
     * Data regarding primary statistics and their related displays.
     *
     * @param name displayed name for this item
     * @param primaryStat stat item to work with and display
     * @param bonusColorChange function to run to update the bonus texts' color
     * @param changeFunc function that accesses the size category display when it needs to be updated
     */
    class PrimeCharacteristicData(
        val name: String,
        private val primaryStat: PrimaryCharacteristic,
        val bonusColorChange: () -> Unit,
        val changeFunc: () -> Unit
    ){
        //initialize characteristic input value
        private val _input = MutableStateFlow(primaryStat.inputValue.toString())
        val input = _input.asStateFlow()

        //initialize characteristic bonus input
        private val _bonusInput = MutableStateFlow(primaryStat.levelBonus.toString())
        val bonusInput = _bonusInput.asStateFlow()

        //initialize other bonus display
        private val _bonus = MutableStateFlow(primaryStat.bonus.toString())
        val bonus = _bonus.asStateFlow()

        //initialize final output mod display
        private val _modTotal = MutableStateFlow(primaryStat.outputMod.toString())
        val modTotal = _modTotal.asStateFlow()

        /**
         * Change the value of this primary characteristic's input to the indicated value.
         *
         * @param input value to set this to
         */
        fun setInput(input: Int){
            primaryStat.setInput(input)
            setInput(input.toString())
            setOutput()
            changeFunc()
        }

        /**
         * Change the displayed value of the primary characteristic input.
         *
         * @param input string item to now display
         */
        fun setInput(input: String){_input.update{input}}

        /**
         * Sets the level bonus for this characteristic.
         *
         * @param input value to set the level bonus to
         */
        fun setBonusInput(input: Int){
            primaryStat.setLevelBonus(input)
            setBonusInput(input.toString())
            bonusColorChange()
            setOutput()
            changeFunc()
        }

        /**
         * Sets the display of the characteristic's level bonus.
         *
         * @param input new string to display
         */
        fun setBonusInput(input: String){_bonusInput.update{input}}

        /**
         * Update the bonus and total displays of this string item.
         */
        fun setOutput() {
            _bonus.update{primaryStat.bonus.toString()}
            _modTotal.update{primaryStat.outputMod.toString()}
        }
    }
}