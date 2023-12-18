package com.paetus.animaCharCreator.view_models.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.geometry.Size
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.CustomCharacteristic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the custom secondary characteristic creation alert.
 *
 * @param secondarySource viewModel that manages the secondary characteristic fragment
 */
class CustomSecondaryViewModel(
    val secondarySource: SecondaryFragmentViewModel
){
    //initialize created characteristic
    val customSecondary = CustomCharacteristic(parent = secondarySource.charInstance.secondaryList)

    //initialize custom characteristic's name
    private val _charName = MutableStateFlow(value = "")
    val charName = _charName.asStateFlow()

    //initialize public state of the created characteristic
    private val _secondaryIsPublic = MutableStateFlow(false)
    val secondaryIsPublic = _secondaryIsPublic.asStateFlow()

    private val fieldDropdown = CustomDropdownItem(
        optionsRef = R.array.secondaryFields,
        labelRef = R.string.fieldLabel,
        customUpdate = {customSecondary.setFieldIndex(fieldIndex = it)}
    )

    private val primaryDropdown = CustomDropdownItem(
        optionsRef = R.array.primaryCharArray,
        labelRef = R.string.primeCharLabel,
        customUpdate = {customSecondary.setPrimaryCharIndex(primeIndex = it)}
    )

    val allDropdowns = listOf(fieldDropdown, primaryDropdown)

    /**
     * Sets the custom characteristic's name to the inputted value.
     *
     * @param nameInput new value to set as the name
     */
    fun setCustomName(nameInput: String){
        _charName.update{nameInput}
        customSecondary.setName(name = nameInput)
    }

    /**
     * Sets the custom characteristic's public state to the indicated value.
     *
     * @param isPublic public state to apply
     */
    fun setSecondaryPublic(isPublic: Boolean){
        _secondaryIsPublic.update{isPublic}
        customSecondary.setPublic(isPublic = isPublic)
    }

    /**
     * Manages a dropdown for the custom secondary dialog.
     *
     * @param optionsRef list of options for this dropdown
     * @param labelRef string reference to this item's label
     * @param customUpdate function to run on user selecting an option
     */
    class CustomDropdownItem(
        val optionsRef: Int,
        val labelRef: Int,
        val customUpdate: (Int) -> Unit
    ){
        //initialize selected index
        private val _index = MutableStateFlow(value = 0)
        val index = _index.asStateFlow()

        //initialize the size of the dropdown
        private val _size = MutableStateFlow(value = Size.Zero)
        val size = _size.asStateFlow()

        //initialize open state of the dropdown
        private val _isOpen = MutableStateFlow(value = false)
        val isOpen = _isOpen.asStateFlow()

        //initialize icon displayed on the dropdown
        private val _icon = MutableStateFlow(value = Icons.Filled.KeyboardArrowDown)
        val icon = _icon.asStateFlow()

        /**
         * Sets the current selection for this dropdown.
         *
         * @param selection selected index to set
         */
        fun setIndex(selection: Int){
            _index.update{selection}
            customUpdate(selection)
            openToggle()
        }

        /**
         * Sets the size of the dropdown object.
         *
         * @param newSize new size to set
         */
        fun setSize(newSize: Size){_size.update{newSize}}

        /**
         * Toggles the open state of the dropdown menu.
         */
        fun openToggle(){
            //toggles the open state
            _isOpen.update{!isOpen.value}

            //updates the trailing icon appropriately
            _icon.update{
                if(isOpen.value) Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown
            }
        }
    }
}