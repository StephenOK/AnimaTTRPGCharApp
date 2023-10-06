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

class EditSecondaryViewModel(val context: Context): ViewModel() {
    val customList = mutableListOf<ItemPanel>()

    private val _deleteConfirmOpen = MutableStateFlow(false)
    val deleteConfirmOpen = _deleteConfirmOpen.asStateFlow()

    private val _deletingItem = MutableStateFlow("")
    val deletingItem = _deletingItem.asStateFlow()

    fun closeAll(){
        customList.forEach{
            if(it.editOpen.value)
                it.toggleOpen()
        }
    }

    fun refreshCustomList(){
        customList.clear()

        val dummyChar = BaseCharacter()
        val mainFile = File("${context.filesDir}/CustomSecondaryDIR")

        mainFile.walk().forEach{
            if(it != mainFile) {
                val customInput = FileInputStream(it)
                val readCustom = InputStreamReader(customInput, StandardCharsets.UTF_8)
                val fileReader = BufferedReader(readCustom)

                val valid = fileReader.readLine().toBoolean()
                val filename = fileReader.readLine()
                val name = fileReader.readLine()
                val field = fileReader.readLine().toInt()
                val primary = fileReader.readLine().toInt()

                val newTech = CustomCharacteristic(
                    SecondaryList(dummyChar, dummyChar.primaryList),
                    name,
                    filename,
                    valid,
                    field,
                    primary
                )

                customList.add(
                    ItemPanel(newTech) {
                        setDeletingItem(newTech.name.value)
                    }
                )
            }
        }
    }

    fun toggleDeleteConfirm(){_deleteConfirmOpen.update{!deleteConfirmOpen.value}}

    fun setDeletingItem(input: String){_deletingItem.update{input}}

    fun overwriteItem(input: CustomCharacteristic){
        val filename = "${context.filesDir}/CustomSecondaryDIR/${input.name.value}"

        val saveSecChar = FileOutputStream(File(filename))

        val secCharData = ByteArrayOutputStream()
        writeDataTo(
            secCharData,
            input.isPublic.value
        )
        writeDataTo(secCharData, input.filename.value)
        writeDataTo(secCharData, input.name.value)
        writeDataTo(
            secCharData,
            input.fieldIndex.value
        )
        writeDataTo(
            secCharData,
            input.primaryCharIndex.value
        )
        secCharData.close()

        saveSecChar.write(secCharData.toByteArray())
        saveSecChar.close()
    }

    class ItemPanel(
        val item: CustomCharacteristic,
        val updateItem: () -> Unit
    ){
        private val _editOpen = MutableStateFlow(false)
        val editOpen = _editOpen.asStateFlow()

        private val _isPrivate = MutableStateFlow(item.isPublic.value)
        val isPrivate = _isPrivate.asStateFlow()

        fun toggleOpen(){
            _editOpen.update{!editOpen.value}
            updateItem()
        }

        fun togglePrivate(){
            _isPrivate.update{!isPrivate.value}
            item.setPublic(isPrivate.value)
        }

        val fieldDropdown = ItemDropdown(
            item.fieldIndex.value,
            R.array.secondaryFields,
            R.string.fieldLabel
        ){item.setFieldIndex(it)}

        val primaryDropdown = ItemDropdown(
            item.primaryCharIndex.value,
            R.array.primaryCharArray,
            R.string.primeCharLabel
        ){item.setPrimaryCharIndex(it)}

        val allDropdowns = listOf(fieldDropdown, primaryDropdown)
    }

    class ItemDropdown(
        initialIndex: Int,
        val optionsRef: Int,
        val labelRef: Int,
        val itemUpdate: (Int) -> Unit
    ){
        private val _index = MutableStateFlow(initialIndex)
        val index = _index.asStateFlow()

        private val _size = MutableStateFlow(Size.Zero)
        val size = _size.asStateFlow()

        private val _isOpen = MutableStateFlow(false)
        val isOpen = _isOpen.asStateFlow()

        private val _icon = MutableStateFlow(Icons.Filled.KeyboardArrowDown)
        val icon = _icon.asStateFlow()

        fun setIndex(input: Int){
            _index.update{input}
            itemUpdate(input)
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

    init{
        refreshCustomList()
    }
}