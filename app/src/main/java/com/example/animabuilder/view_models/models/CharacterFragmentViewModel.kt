package com.example.animabuilder.view_models.models

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
 */
class CharacterFragmentViewModel(
    private val charInstance: BaseCharacter
): ViewModel() {
    //initialize input for the character's name
    private val _nameInput = MutableStateFlow(charInstance.charName.value)
    val nameInput = _nameInput.asStateFlow()

    /**
     * Sets the character's name to the user's input.
     *
     * @param newIn name to set for the character
     */
    fun setNameInput(newIn: String){
        charInstance.charName.value = newIn
        _nameInput.update{newIn}
    }

    //initialize input for the character's experience points
    private val _experiencePoints = MutableStateFlow(charInstance.experiencePoints.value.toString())
    val experiencePoints = _experiencePoints.asStateFlow()

    /**
     * Sets the number of experience points the character currently has.
     *
     * @param input number of points to set
     */
    fun setExp(input: Int){
        charInstance.experiencePoints.value = input
        setExp(input.toString())
    }

    /**
     * Sets the display for the number of experience points held.
     *
     * @param input new string to display
     */
    fun setExp(input: String){_experiencePoints.update{input}}

    //initialize character's gender tracker
    private val _isMale = MutableStateFlow(charInstance.isMale.value)
    val isMale = _isMale.asStateFlow()

    //initialize display for character's gender
    private val _genderString = MutableStateFlow(
        if(charInstance.isMale.value) R.string.maleString
        else R.string.femaleString
    )
    val genderString = _genderString.asStateFlow()

    /**
     * Swaps the character's gender to the other one.
     */
    fun toggleGender(){
        //update the gender input
        _isMale.update{!isMale.value}
        charInstance.setGender(isMale.value)

        //update display string appropriately
        if(isMale.value)
            _genderString.update{R.string.maleString}
        else
            _genderString.update{R.string.femaleString}
    }

    //initialize magic paladin open state
    private val _magPaladinOpen = MutableStateFlow(getPaladin())
    val magPaladinOpen = _magPaladinOpen.asStateFlow()

    //initialize magic paladin checkbox input
    private val _magPaladin = MutableStateFlow(charInstance.classes.magPaladin.value)
    val magPaladin = _magPaladin.asStateFlow()

    /**
     * Sets the visibility of the paladin boon option checkbox.
     */
    fun setMagPaladinOpen(){_magPaladinOpen.update{getPaladin()}}

    /**
     * Toggles the character's selected paladin boon.
     */
    fun toggleMagPaladin(){
        charInstance.classes.toggleMagPaladin()
        _magPaladin.update{!magPaladin.value}
    }

    /**
     * Determines if the character is one of the paladin classes.
     *
     * @return true if character is a paladin or dark paladin
     */
    fun getPaladin(): Boolean{
        return charInstance.ownClass.value == charInstance.classes.paladin ||
                charInstance.ownClass.value == charInstance.classes.darkPaladin
    }

    //initialize the character's size category display
    private val _sizeInput = MutableStateFlow(charInstance.sizeCategory.value.toString())
    val sizeInput = _sizeInput.asStateFlow()

    /**
     * Sets the size category display to the character's value.
     */
    private fun setSizeInput() {_sizeInput.update{charInstance.sizeCategory.value.toString()}}

    //initialize the character's appearance input string
    private val _appearInput = MutableStateFlow(charInstance.appearance.value.toString())
    val appearInput = _appearInput.asStateFlow()

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
        val output = charInstance.appearance.value == newIn

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

    /**
     * Checks if the character has the Unattractive disadvantage.
     *
     * @return true if character has the Unattractive disadvantage
     */
    fun isNotUnattractive(): Boolean{
        return charInstance.advantageRecord.getAdvantage("Unattractive") == null
    }

    //initialize the character's movement value display
    private val _movementDisplay = MutableStateFlow(charInstance.movement.value.toString() + "m")
    val movementDisplay = _movementDisplay.asStateFlow()

    /**
     * Sets the display for the character's movement value as defined in the character.
     */
    fun setMovementDisplay(){
        if(charInstance.movement.value == 0)
            _movementDisplay.update{"SPECIAL"}
        else
            _movementDisplay.update{charInstance.movement.value.toString() + " m"}
    }

    //initialize character's gnosis input
    private val _gnosisDisplay = MutableStateFlow(charInstance.gnosis.value.toString())
    val gnosisDisplay = _gnosisDisplay.asStateFlow()

    fun setGnosisDisplay(input: Int){
        charInstance.gnosis.value = input
        setGnosisDisplay(input.toString())
    }

    fun setGnosisDisplay(input: String){_gnosisDisplay.update{input}}

    //initialize the character's weight index display
    private val _weightIndex = MutableStateFlow(
        if(charInstance.primaryList.str.total.value < 12) charInstance.weightIndex.value.toString() + " kg"
        else charInstance.weightIndex.value.toString() + " tons"
    )
    val weightIndex = _weightIndex.asStateFlow()

    /**
     * Sets the display for the character's movement value as defined in the character.
     */
    fun setWeightIndex(){
        if(charInstance.weightIndex.value == 0)
            _weightIndex.update{"SPECIAL"}
        else if(charInstance.primaryList.str.total.value < 12)
            _weightIndex.update{charInstance.weightIndex.value.toString() + " kg"}
        else
            _weightIndex.update{charInstance.weightIndex.value.toString() + " tons"}
    }

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

    //set race dropdown data
    val raceDropdown = DropdownData(
        R.string.raceText,
        R.array.raceArray,
        charInstance.races.allAdvantageLists.indexOf(charInstance.ownRace.value)
    ) {
        charInstance.setOwnRace(it)
        strengthData.setOutput()
        setSizeInput()
        setAppearInput(charInstance.appearance.value.toString())
    }

    //set class dropdown data
    private val classDropdown = DropdownData(
        R.string.classText,
        R.array.classArray,
        charInstance.classes.allClasses.indexOf(charInstance.ownClass.value)
    ) {
        charInstance.setOwnClass(it)
        setMagPaladinOpen()
    }

    //set level dropdown data
    private val levelDropdown = DropdownData(
        R.string.levelText,
        R.array.levelCountArray,
        charInstance.lvl.value
    ) {
        charInstance.setLvl(it)
        setBonusColor()
    }

    //set all dropdown data
    val dropdownList = listOf(raceDropdown, classDropdown, levelDropdown)

    //set all primary characteristic data
    private val strengthData = PrimeCharacteristicData(
        0,
        charInstance.primaryList.str,
        {setBonusColor()},
    ){
        this.setSizeInput()
        this.setWeightIndex()
    }

    private val dexterityData = PrimeCharacteristicData(
        1,
        charInstance.primaryList.dex,
        {setBonusColor()}
    ){}

    private val agilityData = PrimeCharacteristicData(
        2,
        charInstance.primaryList.agi,
        {
            setBonusColor()
            this.setMovementDisplay()
        }
    ){}

    private val constitutionData = PrimeCharacteristicData(
        3,
        charInstance.primaryList.con,
        {setBonusColor()}
    ){this.setSizeInput()}

    private val intelligenceData = PrimeCharacteristicData(
        6,
        charInstance.primaryList.int,
        {setBonusColor()}
    ){}

    private val powerData = PrimeCharacteristicData(
        4,
        charInstance.primaryList.pow,
        {setBonusColor()}
    ){}

    private val willpowerData = PrimeCharacteristicData(
        5,
        charInstance.primaryList.wp,
        {setBonusColor()}
    ){}

    private val perceptionData = PrimeCharacteristicData(
        7,
        charInstance.primaryList.per,
        {setBonusColor()}
    ){}

    //gather all primary data
    val primaryDataList = listOf(strengthData, dexterityData, agilityData, constitutionData,
        intelligenceData, powerData, willpowerData, perceptionData)

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
        val options: Int,
        initialIndex: Int,
        val onChange: (Int) -> Unit
    ){
        //initialize box size
        private val _size = MutableStateFlow(Size.Zero)
        val size = _size.asStateFlow()

        //initialize the selected item
        private val _output = MutableStateFlow(initialIndex)
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
         * @param item new string of the selection
         */
        fun setOutput(item: Int){
            _output.update{item}

            //perform the required change and close the dropdown
            onChange(item)
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
        val name: Int,
        private val primaryStat: PrimaryCharacteristic,
        val bonusColorChange: () -> Unit,
        val changeFunc: () -> Unit
    ){
        //initialize characteristic input value
        private val _input = MutableStateFlow(primaryStat.inputValue.value.toString())
        val input = _input.asStateFlow()

        //initialize characteristic bonus input
        private val _bonusInput = MutableStateFlow(primaryStat.levelBonus.value.toString())
        val bonusInput = _bonusInput.asStateFlow()

        //initialize other bonus display
        private val _bonus = MutableStateFlow(primaryStat.bonus.value.toString())
        val bonus = _bonus.asStateFlow()

        //initialize final output mod display
        private val _modTotal = MutableStateFlow(primaryStat.outputMod.value.toString())
        val modTotal = _modTotal.asStateFlow()

        /**
         * Change the value of this primary characteristic's input to the indicated value.
         *
         * @param input value to set this to
         */
        fun setInput(input: Int){
            primaryStat.setInput(input)
            setInput(primaryStat.inputValue.value.toString())
            setOutput()
            changeFunc()
        }

        /**
         * Change the displayed value of the primary characteristic input.
         *
         * @param input string item to now display
         */
        fun setInput(input: String){
            if(primaryStat.charInstance.advantageRecord.getAdvantage(
                    "Increase One Characteristic to Nine",
                    primaryStat.charIndex,
                    0
                ) == null
            )
                _input.update{input}
            else _input.update{"9"}
        }

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
            _bonus.update{primaryStat.bonus.value.toString()}
            _modTotal.update{primaryStat.outputMod.value.toString()}
        }

        /**
         * Resets the display for this item on returning to this page.
         */
        fun refreshItem(){
            setInput(primaryStat.inputValue.value.toString())
            setBonusInput(primaryStat.levelBonus.value.toString())
            setOutput()
        }
    }

    /**
     * Refreshes items on returning to this page.
     */
    fun refreshPage(){
        setExp(charInstance.experiencePoints.value.toString())
        primaryDataList.forEach{it.refreshItem()}
        setAppearInput(charInstance.appearance.value.toString())
        setGnosisDisplay(charInstance.gnosis.value.toString())
    }
}