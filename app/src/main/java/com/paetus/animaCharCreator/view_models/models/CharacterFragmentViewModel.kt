package com.paetus.animaCharCreator.view_models.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.DropdownData
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.RacialAdvantage
import com.paetus.animaCharCreator.character_creation.attributes.class_objects.CharClass
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.PrimaryCharacteristic
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.SblPrimaryChar
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

    //initialize open state of race detail alert
    private val _raceDetailOpen = MutableStateFlow(value = false)
    val raceDetailOpen = _raceDetailOpen.asStateFlow()

    //initialize race advantage detail open state
    private val _raceAdvantageOpen = MutableStateFlow(value = false)
    val raceAdvantageOpen = _raceAdvantageOpen.asStateFlow()

    //initialize racial advantage displayed
    private val _racialDisplayed = MutableStateFlow(value = charInstance.races.sylvainAdvantages[0])
    val racialDisplayed = _racialDisplayed.asStateFlow()

    //initialize open state of class detail alert
    private val _classDetailOpen = MutableStateFlow(value = false)
    val classDetailOpen = _classDetailOpen.asStateFlow()

    //initialize failed level change alert
    private val _failedLevelChangeOpen = MutableStateFlow(value = false)
    val failedLevelChangeOpen = _failedLevelChangeOpen.asStateFlow()

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
        return charInstance.classes.ownClass.intValue == 3 ||
                charInstance.classes.ownClass.intValue == 4
    }

    /**
     * Retrieves the freelancer class from the character.
     */
    fun getFreelancer(): CharClass {return charInstance.classRecord.allClasses[0]}

    /**
     * Retrieves the character's selections for their freelancer bonuses.
     *
     * @return secondary characteristics chosen for freelancer bonuses
     */
    fun getFreelancerBonuses(): MutableList<Int>{return charInstance.classes.freelancerSelection}

    /**
     * Retrieves the character's current list of racial advantages.
     *
     * @return list of character's racial advantages
     */
    fun getRace(): List<RacialAdvantage> {return charInstance.ownRace.value}

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

    /**
     * Opens and closes the detail alert for class details.
     */
    fun toggleClassDetailOpen(){_classDetailOpen.update{!classDetailOpen.value}}

    /**
     * Opens and closes the detail alert for race details.
     */
    fun toggleRaceDetailOpen(){_raceDetailOpen.update{!raceDetailOpen.value}}

    /**
     * Opens and closes the detail alert for individual racial advantage details.
     */
    fun toggleRacialAdvantageOpen(){_raceAdvantageOpen.update{!raceAdvantageOpen.value}}

    /**
     * Retrieves the class displayed inn the class dropdown item.
     */
    fun getDisplayedClass(): CharClass{
        return charInstance.classRecord.allClasses[classDropdown.data.output.value]
    }

    /**
     * Determines if the user may change the level of the character.
     *
     * @return list of error string outputs if errors are found; null if no errors found
     */
    @Composable
    fun getLevelChangeable(): List<@Composable () -> String>?{
        //no need to run if character is not save by level
        if (charInstance !is SblChar) return null

        //initialize error list
        val output = mutableListOf<@Composable () -> String>()

        //determine if DP spent appropriately
        if(charInstance.spentTotal.intValue != charInstance.devPT.intValue)
            output.add{
                //add either indicator of overspent DP
                if (charInstance.spentTotal.intValue > charInstance.devPT.intValue) {
                    stringResource(R.string.overDpFailure)
                }
                //or indicator of underspent DP
                else {
                    stringResource(R.string.underDpFailure)
                }
            }

        //determine if any error in primary bonus distribution
        if(!bonusValid.collectAsState().value){
            //check each primary characteristic
            charInstance.primaryList.allPrimaries().forEach{
                if(!(it as SblPrimaryChar).validGrowth())
                    //if growth is not logical, notify of error in this stat
                    output.add {
                        stringResource(
                            R.string.primaryBonusPointReduction,
                            stringArrayResource(id = R.array.primaryCharArray)[it.charIndex]
                        )
                    }
            }

            //notify of too many primary bonus points added
            if(charInstance.primaryList.getPrimaryBonusTotal() > charInstance.lvl.intValue/2)
                output.add{stringResource(R.string.invalidPrimaryBonus)}
        }

        //give final output list
        return if(output.isEmpty()) null else output.toList()
    }

    /**
     * Determines if level is legal to display to the user
     *
     * @param levelString level to look for in the character
     * @return true if not a SBL character or SBL character has access to the queried level
     */
    fun getExistingCharacter(levelString: String): Boolean{
        //return level option is legal if not looking for 0 or character is SBL
        return if(levelString.toInt() != 0 && charInstance is SblChar)
            charInstance.charRefs[levelString.toInt()] != null
        //true if looking for level 0 or character is not SBL
        else true
    }

    /**
     * Gets whether the character's class changes next level.
     *
     * @return true if a change is found
     */
    fun getClassChanged(): Boolean{
        //only needs to check if SblChar
        return if(charInstance is SblChar)
            charInstance.getCharAtLevel().classes.ownClass.intValue != charInstance.charRefs[charInstance.lvl.intValue + 1]!!.classes.ownClass.intValue
            //otherwise, always false
            else false
    }

    /**
     * Gets the recorded class of the next level for the character.
     *
     * @return class pointer in the next level
     */
    fun getNextLevelClass(): Int{
        return (charInstance as SblChar).charRefs[charInstance.lvl.intValue + 1]!!.classes.ownClass.intValue
    }

    /**
     * Opens and closes the failed level change alert.
     */
    fun toggleFailedLevelChangeOpen(){_failedLevelChangeOpen.update{!failedLevelChangeOpen.value}}

    fun setRacialAdvantage(racial: RacialAdvantage){_racialDisplayed.update{racial}}

    //set race dropdown data
    val raceDropdown = DropdownRowData(
        data = DropdownData(
            nameRef = R.string.raceText,
            optionsRef = R.array.raceArray,
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
        ),
        weight = 0.6f,
        detailOpen = {toggleRaceDetailOpen()}
    )

    //set class dropdown data
    val classDropdown = DropdownRowData(
        data = DropdownData(
            nameRef = R.string.classLabel,
            optionsRef = R.array.classArray,
            initialIndex = charInstance.classes.ownClass.intValue,
            onChange = {
                charInstance.classes.setOwnClass(classIndex = it)
                setMagPaladinOpen()
            },
            refreshFunc = {
                //get current level's class pointer
                if (charInstance is SblChar)
                    charInstance.getCharAtLevel().classes.ownClass.intValue
                else
                    charInstance.classes.ownClass.intValue
            }
        ),
        weight = 0.6f,
        detailOpen = {toggleClassDetailOpen()}
    )

    //set level dropdown data
    private val levelDropdown = DropdownRowData(
        data = DropdownData(
            nameRef = R.string.levelText,
            optionsRef = R.array.levelCountArray,
            initialIndex = charInstance.lvl.intValue,
            onChange = {newLevel ->
                charInstance.setLvl(levNum = newLevel)
                setBonusColor()

                //get the character's class at this level
                if(charInstance is SblChar)
                    classDropdown.data.refreshDisplay()

            }
        ),
        weight = 1f,
        detailOpen = {}
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

    //initialize dropdown for the class detail dialog
    val classDetailDropdown = DropdownData(
        nameRef = R.string.categoryLabel,
        optionsRef = R.array.classDescDropdown,
        initialIndex = 0,
        onChange = {}
    )

    /**
     * Object that manages a row with a dropdown object.
     *
     * @param data item that is used in the dropdown object
     * @param weight size percentile of the dropdown object
     * @param detailOpen function to run on opening this row's details
     */
    class DropdownRowData(
        val data: DropdownData,
        val weight: Float,
        val detailOpen: () -> Unit
    )

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