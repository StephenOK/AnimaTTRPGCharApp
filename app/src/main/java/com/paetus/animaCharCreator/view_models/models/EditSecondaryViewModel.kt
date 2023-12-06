package com.paetus.animaCharCreator.view_models.models

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.CustomCharacteristic
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SecondaryList
import com.paetus.animaCharCreator.writeDataTo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * ViewModel for when the user is editing their created custom secondary characteristics.
 *
 * @param context page context to retrieve resources for
 */
class EditSecondaryViewModel(val context: Context): ViewModel() {
    //initialize list of custom secondary editing panels
    val customList = mutableListOf<ItemPanel>()

    //initialize the open state of the deletion alert
    private val _deleteConfirmOpen = MutableStateFlow(value = false)
    val deleteConfirmOpen = _deleteConfirmOpen.asStateFlow()

    //initialize the item being deleted
    private val _deletingItem = MutableStateFlow(value = "")
    val deletingItem = _deletingItem.asStateFlow()

    /**
     * Close all editing panels.
     */
    fun closeAll(){
        //go through each panel and close them
        customList.forEach{panel ->
            if(panel.editOpen.value)
                panel.toggleOpen()
        }
    }

    /**
     * Refresh the custom secondary characteristic items to match the file items.
     */
    fun refreshCustomList(){
        //empty the current list
        customList.clear()

        //initialize a dummy character for a SecondaryList item
        val dummyChar = BaseCharacter()

        //retrieve the custom secondary directory
        val mainFile = File("${context.filesDir}/CustomSecondaryDIR")

        mainFile.walk().forEach{ secondaryFile ->
            if(secondaryFile != mainFile) {
                //initialize the file reader
                val customInput = FileInputStream(secondaryFile)
                val readCustom = InputStreamReader(customInput, StandardCharsets.UTF_8)
                val fileReader = BufferedReader(readCustom)

                //retrieve the characteristic's data
                val valid = fileReader.readLine().toBoolean()
                val filename = fileReader.readLine()
                val name = fileReader.readLine()
                val field = fileReader.readLine().toInt()
                val primary = fileReader.readLine().toInt()

                //apply it to a new character
                val newTech = CustomCharacteristic(
                    parent = SecondaryList(
                        charInstance = dummyChar,
                        primaryList = dummyChar.primaryList
                    ),
                    name = name,
                    filename = filename,
                    isPublic = valid,
                    field = field,
                    primary = primary
                )

                //add the file's item
                customList.add(
                    ItemPanel(
                        customChar = newTech,
                        updateDelete = {setDeletingItem(newTech.name.value)}
                    )
                )
            }
        }
    }

    /**
     * Opens and closes the deletion confirmation alert as needed.
     */
    fun toggleDeleteConfirm(){_deleteConfirmOpen.update{!deleteConfirmOpen.value}}

    /**
     * Sets the inputted item as the characteristic to potentially delete.
     *
     * @param customChar name of the custom characteristic to delete
     */
    private fun setDeletingItem(customChar: String){_deletingItem.update{customChar}}

    /**
     * Write new data for the edited characteristic.
     *
     * @param customChar custom characteristic to edit
     */
    fun overwriteItem(customChar: CustomCharacteristic){
        //get the edited item's file
        val filename = "${context.filesDir}/CustomSecondaryDIR/${customChar.name.value}"

        //initialize the file writer
        val saveSecChar = FileOutputStream(File(filename))

        //initialize the output bytes
        val secCharData = ByteArrayOutputStream()

        //write down the byte data for the item
        writeDataTo(writer = secCharData, input = customChar.isPublic.value)
        writeDataTo(writer = secCharData, input = customChar.filename.value)
        writeDataTo(writer = secCharData, input = customChar.name.value)
        writeDataTo(writer = secCharData, input = customChar.fieldIndex.intValue)
        writeDataTo(writer = secCharData, input = customChar.primaryCharIndex.intValue)
        secCharData.close()

        //write the data to file
        saveSecChar.write(secCharData.toByteArray())
        saveSecChar.close()
    }

    /**
     * Object that manages the items for editing a characteristic.
     *
     * @param customChar custom characteristic this panel affects
     * @param updateDelete function that updates the potentially deleted characteristic
     */
    class ItemPanel(
        val customChar: CustomCharacteristic,
        val updateDelete: () -> Unit
    ){
        //initialize open state of the item panel
        private val _editOpen = MutableStateFlow(value = false)
        val editOpen = _editOpen.asStateFlow()

        //initialize private checkbox for this panel
        private val _isPrivate = MutableStateFlow(value = customChar.isPublic.value)
        val isPrivate = _isPrivate.asStateFlow()

        /**
         * Toggle the open state of the panel.
         */
        fun toggleOpen(){
            _editOpen.update{!editOpen.value}
            updateDelete()
        }

        /**
         * Toggles the public status of this characteristic.
         */
        fun togglePrivate(){
            _isPrivate.update{!isPrivate.value}
            customChar.setPublic(isPrivate.value)
        }

        //dropdown item for this characteristic's associated field
        private val fieldDropdown = ItemDropdown(
            initialIndex = customChar.fieldIndex.intValue,
            optionsRef = R.array.secondaryFields,
            labelRef = R.string.fieldLabel,
            itemUpdate = {customChar.setFieldIndex(fieldIndex = it)}
        )

        //dropdown item for this characteristic's associated primary characteristic
        private val primaryDropdown = ItemDropdown(
            initialIndex = customChar.primaryCharIndex.intValue,
            optionsRef = R.array.primaryCharArray,
            labelRef = R.string.primeCharLabel,
            itemUpdate = {customChar.setPrimaryCharIndex(primeIndex = it)}
        )

        val allDropdowns = listOf(fieldDropdown, primaryDropdown)
    }

    /**
     * Manages a dropdown for the editing panel.
     *
     * @param initialIndex sets the initial display for this item
     * @param optionsRef reference to the list of items in the dropdown
     * @param labelRef reference to this item's title
     * @param itemUpdate function to run on changing this item
     */
    class ItemDropdown(
        initialIndex: Int,
        val optionsRef: Int,
        val labelRef: Int,
        val itemUpdate: (Int) -> Unit
    ){
        //selected index for this dropdown
        private val _index = MutableStateFlow(value = initialIndex)
        val index = _index.asStateFlow()

        //current size of the dropdown
        private val _size = MutableStateFlow(value = Size.Zero)
        val size = _size.asStateFlow()

        //open state of the dropdown
        private val _isOpen = MutableStateFlow(value = false)
        val isOpen = _isOpen.asStateFlow()

        //displayed icon of the dropdown
        private val _icon = MutableStateFlow(value = Icons.Filled.KeyboardArrowDown)
        val icon = _icon.asStateFlow()

        /**
         * Sets the currently selected index to this item.
         *
         * @param newIndex new index value to set
         */
        fun setIndex(newIndex: Int){
            _index.update{newIndex}
            itemUpdate(newIndex)
            openToggle()
        }

        /**
         * Sets the size to the indicated value.
         *
         * @param newSize size to set for the dropdown
         */
        fun setSize(newSize: Size){_size.update{newSize}}

        /**
         * Toggles the open state of the dropdown.
         */
        fun openToggle(){
            //toggles the open state
            _isOpen.update{!isOpen.value}

            //sets the trailing icon to the appropriate item
            _icon.update{
                if(isOpen.value) Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown
            }
        }
    }

    init{
        refreshCustomList()
    }
}