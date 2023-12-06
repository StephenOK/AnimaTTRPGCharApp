package com.paetus.animaCharCreator.view_models.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.PrimaryCharacteristic
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
    private val _nameInput = MutableStateFlow(value = charInstance.charName.value)
    val nameInput = _nameInput.asStateFlow()

    //initialize input for the character's experience points
    private val _experiencePoints = MutableStateFlow(value = charInstance.experiencePoints.intValue.toString())
    val experiencePoints = _experiencePoints.asStateFlow()

    //initialize character's gender tracker
    private val _isMale = MutableStateFlow(value = charInstance.isMale.value)
    val isMale = _isMale.asStateFlow()

    //initialize display for character's gender
    private val _genderString = MutableStateFlow(
        value =
            if(charInstance.isMale.value) R.string.maleString
            else R.string.femaleString
    )
    val genderString = _genderString.asStateFlow()

    //initialize magic paladin open state
    private val _magPaladinOpen = MutableStateFlow(value = getPaladin())
    val magPaladinOpen = _magPaladinOpen.asStateFlow()

    //initialize magic paladin checkbox input
    private val _magPaladin = MutableStateFlow(value = charInstance.classes.magPaladin.value)
    val magPaladin = _magPaladin.asStateFlow()

    //initialize the character's size category display
    private val _sizeInput = MutableStateFlow(value = charInstance.sizeCategory.intValue)
    val sizeInput = _sizeInput.asStateFlow()

    //initialize the character's appearance input string
    private val _appearInput = MutableStateFlow(value = charInstance.appearance.intValue.toString())
    val appearInput = _appearInput.asStateFlow()

    //initialize the character's movement value display
    private val _movementDisplay = MutableStateFlow(
        value =
            if(charInstance.movement.intValue == 0) R.string.specialLabelCaps
            else R.string.distanceLabelM
    )
    val movementDisplay = _movementDisplay.asStateFlow()

    //initialize character's gnosis input
    private val _gnosisDisplay = MutableStateFlow(value = charInstance.gnosis.intValue.toString())
    val gnosisDisplay = _gnosisDisplay.asStateFlow()

    //initialize the character's weight index display
    private val _weightIndex = MutableStateFlow(
        value = when(charInstance.primaryList.str.total.intValue) {
            in 0 .. 11 -> R.string.weightLabelKG
            in 12..19 -> R.string.weightLabelTons
            else -> R.string.specialLabelCaps
        }
    )
    val weightIndex = _weightIndex.asStateFlow()

    //initialize level bonus string color
    private val _bonusValid = MutableStateFlow(value = charInstance.primaryList.validLevelBonuses())
    val bonusValid = _bonusValid.asStateFlow()

    /**
     * Sets the character's name to the user's input.
     *
     * @param nameIn name to set for the character
     */
    fun setNameInput(nameIn: String){
        charInstance.charName.value = nameIn
        _nameInput.update{nameIn}
    }

    /**
     * Sets the number of experience points the character currently has.
     *
     * @param expVal number of points to set
     */
    fun setExp(expVal: Int){
        charInstance.experiencePoints.intValue = expVal
        setExp(display = expVal.toString())
    }

    /**
     * Sets the display for the number of experience points held.
     *
     * @param display new string to display
     */
    fun setExp(display: String){_experiencePoints.update{display}}

    /**
     * Swaps the character's gender to the other one.
     */
    fun toggleGender(){
        //update the gender input
        _isMale.update{!isMale.value}
        charInstance.setGender(gender = isMale.value)

        //update display string appropriately
        if(isMale.value)
            _genderString.update{R.string.maleString}
        else
            _genderString.update{R.string.femaleString}
    }

    /**
     * Sets the visibility of the paladin boon option checkbox.
     */
    private fun setMagPaladinOpen(){_magPaladinOpen.update{getPaladin()}}

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
    private fun getPaladin(): Boolean{
        return charInstance.ownClass.value == charInstance.classes.paladin ||
                charInstance.ownClass.value == charInstance.classes.darkPaladin
    }

    /**
     * Sets the size category display to the character's value.
     */
    private fun setSizeInput() {_sizeInput.update{charInstance.sizeCategory.intValue}}

    /**
     * Attempts to change the character's appearance to the inputted value.
     *
     * @param appearance value to set the appearance to
     * @return true if there was a change inputted
     */
    fun setAppearInput(appearance: Int): Boolean{
        //set appearance in the character
        charInstance.setAppearance(newAppearance = appearance)

        //check that a change occurred
        val output = charInstance.appearance.intValue == appearance

        //change display string if there was a change
        if(output)
            setAppearInput(display = appearance.toString())

        //return if a change happened
        return output
    }

    /**
     * Sets the string display for the character's appearance.
     *
     * @param display value to set the display to
     */
    fun setAppearInput(display: String){_appearInput.update{display}}

    /**
     * Checks if the character has the Unattractive disadvantage.
     *
     * @return true if character has the Unattractive disadvantage
     */
    fun isNotUnattractive(): Boolean{
        return charInstance.advantageRecord.getAdvantage(advantageString = "unattractive") == null
    }

    /**
     * Sets the display for the character's movement value as defined in the character.
     */
    private fun setMovementDisplay(){
        if(charInstance.movement.intValue == 0)
            _movementDisplay.update{R.string.specialLabelCaps}
        else
            _movementDisplay.update{R.string.distanceLabelM}
    }

    /**
     * Retrieves the character's movement value.
     *
     * @return character's movement value in meters
     */
    fun getCharMovement(): Int{return charInstance.movement.intValue}

    /**
     * Sets the character's gnosis value to the indicated number.
     *
     * @param gnosisInput value to set the gnosis to
     */
    fun setGnosisDisplay(gnosisInput: Int){
        charInstance.gnosis.intValue = gnosisInput
        setGnosisDisplay(display = gnosisInput.toString())
    }

    /**
     * Sets the displayed gnosis string to the indicated value.
     *
     * @param display new value to display
     */
    fun setGnosisDisplay(display: String){_gnosisDisplay.update{display}}

    /**
     * Sets the display for the character's movement value as defined in the character.
     */
    private fun setWeightIndex(){
        when(charInstance.primaryList.str.total.intValue) {
            //index in kilograms
            in 0 .. 11 ->
                _weightIndex.update{R.string.weightLabelKG}
            //index in tons
            in 12..19 ->
                _weightIndex.update{R.string.weightLabelTons}
            //special weight index value
            else ->
                _weightIndex.update{R.string.specialLabelCaps}
        }
    }

    /**
     * Retrieves the character's weight value.
     *
     * @return the character's weight
     */
    fun getCharWeight(): Int{return charInstance.weightIndex.intValue}

    /**
     * Updates the validation flag for the characteristic bonus.
     */
    private fun setBonusColor(){_bonusValid.update{charInstance.primaryList.validLevelBonuses()}}

    //set race dropdown data
    val raceDropdown = DropdownData(
        nameRef = R.string.raceText,
        options = R.array.raceArray,
        initialIndex = charInstance.races.allAdvantageLists.indexOf(charInstance.ownRace.value),
        onChange = {
            //change the character's race
            charInstance.setOwnRace(raceNum = it)

            //update the strength value for this character
            strengthData.setOutput()
            setSizeInput()

            //update the appearance value for this character
            setAppearInput(display = charInstance.appearance.intValue.toString())
        }
    )

    //set class dropdown data
    private val classDropdown = DropdownData(
        nameRef = R.string.classLabel,
        options = R.array.classArray,
        initialIndex = charInstance.classes.allClasses.indexOf(charInstance.ownClass.value),
        onChange = {
            charInstance.setOwnClass(classInt = it)
            setMagPaladinOpen()
        }
    )

    //set level dropdown data
    private val levelDropdown = DropdownData(
        nameRef = R.string.levelText,
        options = R.array.levelCountArray,
        initialIndex = charInstance.lvl.intValue,
        onChange = {
            charInstance.setLvl(levNum = it)
            setBonusColor()
        }
    )

    //set all dropdown data
    val dropdownList = listOf(raceDropdown, classDropdown, levelDropdown)

    //set all primary characteristic data
    private val strengthData = PrimeCharacteristicData(
        name = 0,
        primaryStat = charInstance.primaryList.str,
        bonusColorChange = {setBonusColor()},
        changeFunc = {
            this.setSizeInput()
            this.setWeightIndex()
        }
    )

    private val dexterityData = PrimeCharacteristicData(
        name = 1,
        primaryStat = charInstance.primaryList.dex,
        bonusColorChange = {setBonusColor()},
        changeFunc = {}
    )

    private val agilityData = PrimeCharacteristicData(
        name = 2,
        primaryStat = charInstance.primaryList.agi,
        bonusColorChange = {
            setBonusColor()
            this.setMovementDisplay()
        },
        changeFunc = {}
    )

    private val constitutionData = PrimeCharacteristicData(
        name = 3,
        primaryStat = charInstance.primaryList.con,
        bonusColorChange = {setBonusColor()},
        changeFunc = {this.setSizeInput()}
    )

    private val intelligenceData = PrimeCharacteristicData(
        name = 4,
        primaryStat = charInstance.primaryList.int,
        bonusColorChange = {setBonusColor()},
        changeFunc = {}
    )

    private val powerData = PrimeCharacteristicData(
        name = 5,
        primaryStat = charInstance.primaryList.pow,
        bonusColorChange = {setBonusColor()},
        changeFunc = {}
    )

    private val willpowerData = PrimeCharacteristicData(
        name = 6,
        primaryStat = charInstance.primaryList.wp,
        bonusColorChange = {setBonusColor()},
        changeFunc = {}
    )

    private val perceptionData = PrimeCharacteristicData(
        name = 7,
        primaryStat = charInstance.primaryList.per,
        bonusColorChange = {setBonusColor()},
        changeFunc = {}
    )

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
        //initialize the selected item
        private val _output = MutableStateFlow(value = initialIndex)
        val output = _output.asStateFlow()

        //initialize box size
        private val _size = MutableStateFlow(value = Size.Zero)
        val size = _size.asStateFlow()

        //initialize dropdown's open state
        private val _isOpen = MutableStateFlow(value = false)
        val isOpen = _isOpen.asStateFlow()

        //initialize dropdown icon
        private val _icon = MutableStateFlow(value = Icons.Filled.KeyboardArrowDown)
        val icon = _icon.asStateFlow()

        /**
         * Sets the item to the indicated index.
         *
         * @param dropdownIndex new string of the selection
         */
        fun setOutput(dropdownIndex: Int){
            _output.update{dropdownIndex}

            //perform the required change and close the dropdown
            onChange(dropdownIndex)
            openToggle()
        }

        /**
         * Setter for the item's size.
         *
         * @param newSize size to define for this object
         */
        fun setSize(newSize: Size){_size.update{newSize}}

        /**
         * Changes the open state of the dropdown item.
         */
        fun openToggle() {
            _isOpen.update{!isOpen.value}

            //set icon to the appropriate value
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
        private val _input = MutableStateFlow(value = primaryStat.inputValue.intValue.toString())
        val input = _input.asStateFlow()

        //initialize characteristic bonus input
        private val _bonusInput = MutableStateFlow(value = primaryStat.levelBonus.intValue.toString())
        val bonusInput = _bonusInput.asStateFlow()

        //initialize other bonus display
        private val _bonus = MutableStateFlow(value = primaryStat.bonus.intValue)
        val bonus = _bonus.asStateFlow()

        //initialize final output mod display
        private val _modTotal = MutableStateFlow(value = primaryStat.outputMod.intValue)
        val modTotal = _modTotal.asStateFlow()

        /**
         * Change the value of this primary characteristic's input to the indicated value.
         *
         * @param statVal value to set this to
         */
        fun setInput(statVal: Int){
            primaryStat.setInput(baseIn = statVal)
            setInput(display = primaryStat.inputValue.intValue.toString())
            setOutput()
            changeFunc()
        }

        /**
         * Change the displayed value of the primary characteristic input.
         *
         * @param display string item to now display
         */
        fun setInput(display: String){
            //only change value if character does not have the indicated advantage
            if(primaryStat.charInstance.advantageRecord.getAdvantage(
                    name = "Increase One Characteristic to Nine",
                    taken = primaryStat.charIndex,
                    cost = 0
                ) == null
            )
                _input.update{display}

            //otherwise, keep value at 9
            else _input.update{"9"}
        }

        /**
         * Sets the level bonus for this characteristic.
         *
         * @param lvlBonus value to set the level bonus to
         */
        fun setBonusInput(lvlBonus: Int){
            primaryStat.setLevelBonus(lvlBonus = lvlBonus)
            setBonusInput(display = lvlBonus.toString())
            bonusColorChange()
            setOutput()
            changeFunc()
        }

        /**
         * Sets the display of the characteristic's level bonus.
         *
         * @param display new string to display
         */
        fun setBonusInput(display: String){_bonusInput.update{display}}

        /**
         * Update the bonus and total displays of this string item.
         */
        fun setOutput() {
            _bonus.update{primaryStat.bonus.intValue}
            _modTotal.update{primaryStat.outputMod.intValue}
        }

        /**
         * Resets the display for this item on returning to this page.
         */
        fun refreshItem(){
            setInput(display = primaryStat.inputValue.intValue.toString())
            setBonusInput(display = primaryStat.levelBonus.intValue.toString())
            setOutput()
        }
    }

    /**
     * Refreshes items on returning to this page.
     */
    fun refreshPage(){
        setExp(display = charInstance.experiencePoints.intValue.toString())
        primaryDataList.forEach{primary -> primary.refreshItem()}
        setSizeInput()
        setAppearInput(display = charInstance.appearance.intValue.toString())
        setGnosisDisplay(display = charInstance.gnosis.intValue.toString())
    }
}