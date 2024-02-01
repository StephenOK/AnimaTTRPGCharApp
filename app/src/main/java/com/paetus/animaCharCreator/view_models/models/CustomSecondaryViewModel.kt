package com.paetus.animaCharCreator.view_models.models

import com.paetus.animaCharCreator.DropdownData
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

    private val fieldDropdown = DropdownData(
        nameRef = R.string.fieldLabel,
        optionsRef = R.array.secondaryFields,
        initialIndex = 0,
        onChange = {customSecondary.setFieldIndex(fieldIndex = it)}
    )

    private val primaryDropdown = DropdownData(
        nameRef = R.string.primeCharLabel,
        optionsRef = R.array.primaryCharArray,
        initialIndex = 0,
        onChange = {customSecondary.setPrimaryCharIndex(primeIndex = it)}
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

}