package com.paetus.animaCharCreator.view_models.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.character_creation.attributes.class_objects.ClassInstances
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.CustomCharacteristic
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SblSecondaryCharacteristic
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SblSecondaryList
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
    val secondaryList: SecondaryList
): ViewModel() {
    //initialize open state of the freelancer bonus options
    private val _freelancerOptionsOpen = MutableStateFlow(isFreelancer())
    val freelancerOptionsOpen = _freelancerOptionsOpen.asStateFlow()

    //initialize open state of custom secondaries page
    private val _customIsOpen = MutableStateFlow(value = false)
    val customIsOpen = _customIsOpen.asStateFlow()

    /**
     * Determines if the character's current class is freelancer.
     *
     * @return true if the character is currently a freelancer
     */
    private fun isFreelancer(): Boolean{
        //if SBL, check the character's current level's class
        return if(charInstance is SblChar)
            charInstance.getCharAtLevel().classes.ownClass.intValue == 0
        //check the character's current class
        else
            charInstance.classes.ownClass.intValue == 0
    }

    /**
     * Toggles the open state of the custom secondary characteristic creation dialog.
     */
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
    private val allCharacteristics = athletics.fieldCharacteristics + social.fieldCharacteristics +
            perceptive.fieldCharacteristics + intellectual.fieldCharacteristics +
            vigor.fieldCharacteristics + subterfuge.fieldCharacteristics + creative.fieldCharacteristics

    //create a object for each freelancer bonus option
    private val firstSelection = FreelancerSelection(
        classes = charInstance.classes,
        selection = 0,
        allCharacteristics = allCharacteristics
    )
    private val secondSelection = FreelancerSelection(
        classes = charInstance.classes,
        selection = 1,
        allCharacteristics = allCharacteristics
    )
    private val thirdSelection = FreelancerSelection(
        classes = charInstance.classes,
        selection = 2,
        allCharacteristics = allCharacteristics
    )
    private val fourthSelection = FreelancerSelection(
        classes = charInstance.classes,
        selection = 3,
        allCharacteristics = allCharacteristics
    )
    private val fifthSelection = FreelancerSelection(
        classes = charInstance.classes,
        selection = 4,
        allCharacteristics = allCharacteristics
    )

    val allFreelancerSelections = listOf(firstSelection, secondSelection, thirdSelection, fourthSelection, fifthSelection)

    /**
     * Gets all secondary characteristics available to the character.
     */
    fun getAllSecondaries(): List<SecondaryCharacteristic>{return secondaryList.getAllSecondaries()}

    /**
     * Applies a custom characteristic to the indicated field.
     *
     * @param characteristic custom characteristic to add
     * @param field field to add the characteristic to
     */
    fun addCharToField(
        characteristic: CustomCharacteristic,
        field: Int
    ){
        allFields[field].addFieldChar(customChar = characteristic)
    }

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
        private val _size = MutableStateFlow(value = Size.Zero)
        val size = _size.asStateFlow()

        //initialize selection in this index
        private val _selectedIndex = MutableStateFlow(value = classes.freelancerSelection[selection])
        val selectedIndex = _selectedIndex.asStateFlow()

        //initialize open state of this item's dropdown
        private val _isOpen = MutableStateFlow(value = false)
        val isOpen = _isOpen.asStateFlow()

        //initialize displayed icon
        private val _icon = MutableStateFlow(value = Icons.Filled.KeyboardArrowDown)
        val icon = _icon.asStateFlow()

        /**
         * Function to change the dropdown's size.
         *
         * @param newSize value to set the size to
         */
        fun setSize(newSize: Size){_size.update{newSize}}

        /**
         * Sets the user's selected characteristic for bonus.
         *
         * @param newSecondary secondary characteristic to apply the freelancer class bonus to
         */
        fun setSelection(newSecondary: Int){
            //attempt to change the selection
            _selectedIndex.update{
                classes.setSelection(
                    selectionIndex = selection,
                    secondarySelection = newSecondary
                )
            }

            //update the class bonus displays of all characteristic data
            allCharacteristics.forEach{secondary ->
                secondary.setClassPoints()
            }
        }

        /**
         * Toggles the open state of the characteristic dropdown.
         */
        fun openToggle(){
            //toggle the open state
            _isOpen.update{!isOpen.value}

            //update the icon to match the new open state
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
        private val _tableOpen = MutableStateFlow(value = false)
        val tableOpen = _tableOpen.asStateFlow()

        /**
         * Change the open state of the table display.
         */
        fun toggleOpen(){_tableOpen.update{!tableOpen.value}}

        //initialize data for each of the field's characteristics
        val fieldCharacteristics = mutableListOf<SecondaryItem>()

        /**
         * Adds a characteristic to the field's data list.
         *
         * @param customChar new characteristic to add to the list
         */
        fun addFieldChar(customChar: CustomCharacteristic){
            fieldCharacteristics.add(
                element = SecondaryItem(
                    secondaryItem = customChar,
                    secondaryList = secondaryList
                )
            )
        }

        init{
            //create data objects for each field characteristic
            fieldItems.forEach{secondary ->
                fieldCharacteristics.add(
                    element = SecondaryItem(
                        secondaryItem = secondary,
                        secondaryList = secondaryList
                    )
                )
            }
        }
    }

    /**
     * Data in regards to an individual secondary characteristic.
     *
     * @param secondaryItem characteristic in this data
     * @param secondaryList character's secondary characteristic data
     */
    class SecondaryItem(
        val secondaryItem: SecondaryCharacteristic,
        private val secondaryList: SecondaryList
    ){
        //initialize the inputted points in this characteristic
        private val _pointInput = MutableStateFlow(value = secondaryItem.pointsApplied.intValue.toString())
        val pointInput = _pointInput.asStateFlow()

        //initialize DP display
        private val _dpDisplay = MutableStateFlow(value = "")
        val dpDisplay = _dpDisplay.asStateFlow()

        //initialize display for class points in this characteristic
        private val _classPoints = MutableStateFlow(value = secondaryItem.classPointTotal.intValue)
        val classPoints = _classPoints.asStateFlow()

        //initialize the characteristic's natural bonus check
        private val _natBonusCheck = MutableStateFlow(value = secondaryItem.bonusApplied.value)
        val natBonusCheck = _natBonusCheck.asStateFlow()

        //initialize the natural bonus text
        private val _checkedText = MutableStateFlow(value = updateCheckedText())
        val checkedText = _checkedText.asStateFlow()

        //initialize the total score in this characteristic
        private val _totalOutput = MutableStateFlow(value = secondaryItem.total.intValue)
        val totalOutput = _totalOutput.asStateFlow()

        /**
         * Attempts to change the natural bonus of this secondary characteristic.
         */
        fun setBonusNatCheck(){
            //attempt to change the characteristic's natural bonus
            secondaryList.toggleNatBonus(characteristic = secondaryItem)

            //update the appropriate values
            updateNaturalBonus()
        }

        /**
         * Updates the natural bonus checkbox and associated text.
         */
        fun updateNaturalBonus(){
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
         * @param purchase value to set the purchase to
         */
        fun setPointInput(purchase: Int){
            //set and update the input value
            setPointInput(display = purchase.toString())
            secondaryItem.setPointsApplied(pointInput = purchase)

            updateTotal()

            //remove natural bonus check if points are set to zero
            if(purchase == 0 && natBonusCheck.value)
                setBonusNatCheck()
        }

        /**
         * Sets the point display to the inputted string.
         *
         * @param display new string to display
         */
        fun setPointInput(display: String){_pointInput.update{display}}

        /**
         * Refreshes the class point display for the characteristic.
         */
        fun setClassPoints(){
            _classPoints.update{secondaryItem.classPointTotal.intValue}
            updateTotal()
        }

        /**
         * Sets the value displayed for DP needed for this item.
         *
         * @param dpCost new item to display
         */
        fun setDPDisplay(dpCost: String){_dpDisplay.update{dpCost}}

        /**
         * Updates the total value to the appropriate amount.
         */
        fun updateTotal(){_totalOutput.update{secondaryItem.total.intValue}}

        /**
         * Retrieves the name of the characteristic if it is prebuilt.
         *
         * @return characteristic's name reference
         */
        fun getName(): Int{
            return secondaryList.fullList().indexOf(element = secondaryItem)
        }

        /**
         * Retrieves the name of the characteristic if it is custom.
         *
         * @return characteristic's name
         */
        fun getCustomName(): String{return (secondaryItem as CustomCharacteristic).name.value}

        /**
         * Retrieves the modifier value of the characteristic.
         *
         * @return displayed string for the characteristic's modifier
         */
        fun getModVal(): String{return secondaryItem.modVal.intValue.toString()}

        /**
         * Retrieves the character's development point cost.
         *
         * @return string of the characteristic's development point cost
         */
        fun getMultiplier(): Int{
            //get default cost for buying points in this item
            val devPerPoint =
                if(secondaryList is SblSecondaryList)
                    (secondaryList.charInstance as SblChar).getCharAtLevel().secondaryList.getAllSecondaries()[(secondaryItem as SblSecondaryCharacteristic).secondaryIndex].devPerPoint.intValue
                else secondaryItem.devPerPoint.intValue

            //return default cost minus any deductions
            return if(devPerPoint > secondaryItem.developmentDeduction.intValue)
                devPerPoint - secondaryItem.developmentDeduction.intValue
            else 1
        }
    }

    /**
     * Refreshes the fragment's values for changes on other pages.
     */
    fun refreshPage(){
        //check if freelancer options are available to the character
        _freelancerOptionsOpen.update{isFreelancer()}

        allCharacteristics.forEach{
            //update the point input data for each secondary
            it.setPointInput(it.secondaryItem.pointsApplied.intValue.toString())

            //update the displayed secondary class points
            it.setClassPoints()

            //update the displayed natural bonus taken
            it.updateNaturalBonus()

            //update the displayed secondary totals
            it.updateTotal()
        }
    }
}