package com.paetus.animaCharCreator

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.geometry.Size
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Data instantiated in viewModel items to be used in OutlinedDropdown objects.
 *
 * @param nameRef reference to the dropdown's label text
 * @param optionsRef reference to list of dropdown items
 * @param initialIndex starting item index to first display
 * @param onChange function to run on changing selected item
 */
class DropdownData(
    val nameRef: Int,
    val optionsRef: Int,
    initialIndex: Int,
    val onChange: (Int) -> Unit
){
    //initialize the selected item
    private val _output = MutableStateFlow(value = initialIndex)
    val output = _output.asStateFlow()

    //initialize box size
    private val _size = MutableStateFlow(value = Size.Zero)
    val size = _size.asStateFlow()

    //initialize open state
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

        //close dropdown if it's open
        if(isOpen.value) openToggle()
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
    fun openToggle(){
        _isOpen.update{!isOpen.value}

        //set icon to the appropriate value
        _icon.update{
            if(isOpen.value) Icons.Filled.KeyboardArrowUp
            else Icons.Filled.KeyboardArrowDown
        }
    }
}