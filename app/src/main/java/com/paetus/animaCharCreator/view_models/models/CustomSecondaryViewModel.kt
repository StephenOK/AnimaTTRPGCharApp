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

class CustomSecondaryViewModel(val secondarySource: SecondaryFragmentViewModel){
    val customSecondary = CustomCharacteristic(secondarySource.charInstance.secondaryList)

    private val _charName = MutableStateFlow("")
    val charName = _charName.asStateFlow()

    private val _secondaryIsPublic = MutableStateFlow(false)
    val secondaryIsPublic = _secondaryIsPublic.asStateFlow()

    val fieldDropdown = CustomDropdownItem(
        R.array.secondaryFields,
        R.string.fieldLabel
    ){
        customSecondary.setFieldIndex(it)
    }

    val primaryDropdown = CustomDropdownItem(
        R.array.primaryCharArray,
        R.string.primeCharLabel
    ){
        customSecondary.setPrimaryCharIndex(it)
    }

    val allDropdowns = listOf(fieldDropdown, primaryDropdown)

    fun setCustomName(input: String){
        _charName.update{input}
        customSecondary.setName(input)
    }

    fun setSecondaryPublic(input: Boolean){
        _secondaryIsPublic.update{input}
        customSecondary.setPublic(input)
    }

    class CustomDropdownItem(
        val optionsRef: Int,
        val labelRef: Int,
        val customUpdate: (Int) -> Unit
    ){
        private val _index = MutableStateFlow(0)
        val index = _index.asStateFlow()

        private val _size = MutableStateFlow(Size.Zero)
        val size = _size.asStateFlow()

        private val _isOpen = MutableStateFlow(false)
        val isOpen = _isOpen.asStateFlow()

        private val _icon = MutableStateFlow(Icons.Filled.KeyboardArrowDown)
        val icon = _icon.asStateFlow()

        fun setIndex(input: Int){
            _index.update{input}
            customUpdate(input)
            openToggle()
        }

        fun setSize(input: Size){_size.update{input}}

        fun openToggle(){
            _isOpen.update{!isOpen.value}

            _icon.update{
                if(isOpen.value) Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown
            }
        }
    }
}