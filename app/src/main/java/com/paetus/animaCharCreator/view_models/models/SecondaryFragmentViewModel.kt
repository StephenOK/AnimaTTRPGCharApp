package com.paetus.animaCharCreator.view_models.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.class_objects.ClassInstances
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SecondaryCharacteristic
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SecondaryList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the character's secondary characteristics.
 * Works on variables in the corresponding secondary fragment.
 *
 * @param charInstance character object the user is working on
 * @param secondaryList character's secondary characteristic data
 */
class SecondaryFragmentViewModel(
    val charInstance: BaseCharacter,
    secondaryList: SecondaryList
): ViewModel() {
    //initialize open state of the freelancer bonus options
    private val _freelancerOptionsOpen = MutableStateFlow(charInstance.ownClass.value == charInstance.classes.freelancer)
    val freelancerOptionsOpen = _freelancerOptionsOpen.asStateFlow()

    private val _customIsOpen = MutableStateFlow(false)
    val customIsOpen = _customIsOpen.asStateFlow()

    fun toggleCustomOpen(){_customIsOpen.update{!customIsOpen.value}}

    //initialize each secondary characteristic field data
    private val athletics = SecondaryFieldData(
        secondaryList,
        0,
        secondaryList.intToField(0)
    )

    private val creative = SecondaryFieldData(
        secondaryList,
        6,
        secondaryList.intToField(6)
    )

    private val perceptive = SecondaryFieldData(
        secondaryList,
        2,
        secondaryList.intToField(2)
    )

    private val social = SecondaryFieldData(
        secondaryList,
        1,
        secondaryList.intToField(1)
    )

    private val subterfuge = SecondaryFieldData(
        secondaryList,
        5,
        secondaryList.intToField(5)
    )

    private val intellectual = SecondaryFieldData(
        secondaryList,
        3,
        secondaryList.intToField(3)
    )

    private val vigor = SecondaryFieldData(
        secondaryList,
        4,
        secondaryList.intToField(4)
    )

    //gather all field data
    val allFields = listOf(athletics, social, perceptive, intellectual, vigor, subterfuge, creative)

    //get all individual characteristic items
    val allCharacteristics = athletics.fieldCharacteristics + social.fieldCharacteristics +
            perceptive.fieldCharacteristics + intellectual.fieldCharacteristics +
            vigor.fieldCharacteristics + subterfuge.fieldCharacteristics + creative.fieldCharacteristics

    //create a object for each freelancer bonus option
    val firstSelection = FreelancerSelection(charInstance.classes, 0, allCharacteristics)
    val secondSelection = FreelancerSelection(charInstance.classes, 1, allCharacteristics)
    val thirdSelection = FreelancerSelection(charInstance.classes, 2, allCharacteristics)

    /**
     * Class for a freelancer's secondary characteristic selection.
     *
     * @param classes source of selection record
     * @param selection index this object manages
     * @param allCharacteristics master list of characteristics
     */
    class FreelancerSelection(
        private val classes: ClassInstances,
        val selection: Int,
        val allCharacteristics: List<SecondaryItem>
    ){
        //initialize dropdown size
        private val _size = MutableStateFlow(Size.Zero)
        val size = _size.asStateFlow()

        //initialize selection in this index
        private val _selectedIndex = MutableStateFlow(classes.freelancerSelection[selection])
        val selectedIndex = _selectedIndex.asStateFlow()

        //initialize open state of this item's dropdown
        private val _isOpen = MutableStateFlow(false)
        val isOpen = _isOpen.asStateFlow()

        //initialize displayed icon
        private val _icon = MutableStateFlow(Icons.Filled.KeyboardArrowDown)
        val icon = _icon.asStateFlow()

        /**
         * Function to change the dropdown's size.
         *
         * @param input value to set the size to
         */
        fun setSize(input: Size){_size.update{input}}

        /**
         * Sets the user's selected characteristic for bonus.
         */
        fun setSelection(newIndex: Int){
            //attempt to change the selection
            _selectedIndex.update{classes.setSelection(selection, newIndex)}

            //update the class bonus displays of all characteristic data
            allCharacteristics.forEach{
                it.setClassPoints()
            }
        }

        /**
         * Toggles the open state of the characteristic dropdown.
         */
        fun openToggle(){
            _isOpen.update{!isOpen.value}
            _icon.update{
                if(isOpen.value) Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown
            }
        }
    }

    /**
     * Data in regards to a secondary field item.
     *
     * @param secondaryList character's secondary characteristic data
     * @param fieldName string reference of the field's name
     * @param fieldItems secondary characteristics from this field
     */
    class SecondaryFieldData(
        private val secondaryList: SecondaryList,
        val fieldName: Int,
        fieldItems: List<SecondaryCharacteristic>
    ){
        //initialize open state of the secondary field table
        private val _tableOpen = MutableStateFlow(false)
        val tableOpen = _tableOpen.asStateFlow()

        /**
         * Change the open state of the table display.
         */
        fun toggleOpen(){_tableOpen.update{!tableOpen.value}}

        //initialize data for each of the field's characteristics
        val fieldCharacteristics = mutableListOf<SecondaryItem>()

        init{
            //create data objects for each field characteristic
            fieldItems.forEach{
                fieldCharacteristics.add(SecondaryItem(it, secondaryList))
            }
        }
    }

    /**
     * Data in regards to an individual secondary characteristic.
     *
     * @param secondaryItem characteristic in this data
     * @param secondaryList characteristic's associated field
     */
    class SecondaryItem(
        private val secondaryItem: SecondaryCharacteristic,
        private val secondaryList: SecondaryList
    ){
        //initialize the characteristic's natural bonus check
        private val _natBonusCheck = MutableStateFlow(secondaryItem.bonusApplied.value)
        val natBonusCheck = _natBonusCheck.asStateFlow()

        //initialize the natural bonus text
        private val _checkedText = MutableStateFlow(updateCheckedText())
        val checkedText = _checkedText.asStateFlow()

        //initialize the inputted points in this characteristic
        private val _pointInput = MutableStateFlow(secondaryItem.pointsApplied.value.toString())
        val pointInput = _pointInput.asStateFlow()

        //initialize display for class points in this characteristic
        private val _classPoints = MutableStateFlow(secondaryItem.classPointTotal.value)
        val classPoints = _classPoints.asStateFlow()

        //initialize DP display
        private val _dpDisplay = MutableStateFlow("")
        val dpDisplay = _dpDisplay.asStateFlow()

        //initialize the total score in this characteristic
        private val _totalOutput = MutableStateFlow(secondaryItem.total.value)
        val totalOutput = _totalOutput.asStateFlow()

        /**
         * Attempts to change the natural bonus of this secondary characteristic.
         */
        fun setBonusNatCheck(){
            //attempt to change the characteristic's natural bonus
            secondaryList.toggleNatBonus(secondaryItem)

            //update the appropriate values
            _natBonusCheck.update{secondaryItem.bonusApplied.value}
            updateTotal()
            _checkedText.update{updateCheckedText()}
        }

        /**
         * Updates the point string for the character's natural bonus.
         *
         * @return string reference to display
         */
        private fun updateCheckedText(): Int{
            return if(secondaryItem.bonusApplied.value) R.string.natTaken
            else R.string.natNotTaken
        }

        /**
         * Sets the character's point investment to the indicated value.
         *
         * @param input value to set the purchase to
         */
        fun setPointInput(input: Int){
            //set and update the input value
            setPointInput(input.toString())
            secondaryItem.setPointsApplied(input)

            updateTotal()

            //remove natural bonus check if points are set to zero
            if(input == 0 && natBonusCheck.value)
                setBonusNatCheck()
        }

        /**
         * Sets the point display to the inputted string.
         *
         * @param input new string to display
         */
        fun setPointInput(input: String){_pointInput.update{input}}

        /**
         * Refreshes the class point display for the characteristic.
         */
        fun setClassPoints(){
            _classPoints.update{secondaryItem.classPointTotal.value}
            updateTotal()
        }

        /**
         * Sets the value displayed for DP needed for this item.
         *
         * @param input new item to display
         */
        fun setDPDisplay(input: String){_dpDisplay.update{input}}

        /**
         * Updates the total value to the appropriate amount.
         */
        fun updateTotal(){_totalOutput.update{secondaryItem.total.value}}

        /**
         * Retrieves the name of the characteristic.
         *
         * @return characteristic's name
         */
        fun getName(): Int{return secondaryList.fullList.indexOf(secondaryItem)}

        /**
         * Retrieves the modifier value of the characteristic.
         *
         * @return displayed string for the characteristic's modifier
         */
        fun getModVal(): String{return secondaryItem.modVal.value.toString()}

        /**
         * Retrieves the character's development point cost.
         *
         * @return string of the characteristic's development point cost
         */
        fun getMultiplier(): Int{
            return if(secondaryItem.devPerPoint.value > secondaryItem.developmentDeduction.value)
                secondaryItem.devPerPoint.value - secondaryItem.developmentDeduction.value
            else 1
        }
    }

    /**
     * Refreshes the fragment's values for changes on other pages.
     */
    fun refreshPage(){
        _freelancerOptionsOpen.update{charInstance.ownClass.value == charInstance.classes.freelancer}
        allCharacteristics.forEach{it.setClassPoints()}
        allCharacteristics.forEach{it.updateTotal()}
    }
}