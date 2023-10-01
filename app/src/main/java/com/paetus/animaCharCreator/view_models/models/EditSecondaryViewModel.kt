package com.paetus.animaCharCreator.view_models.models

import android.content.Context
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.CustomCharacteristic
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SecondaryList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
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

        val customInput = FileInputStream(File(context.filesDir, "customSecondaries"))
        val readCustom = InputStreamReader(customInput, StandardCharsets.UTF_8)
        val fileReader = BufferedReader(readCustom)

        while(fileReader.ready()){
            val valid = fileReader.readLine().toBoolean()
            fileReader.readLine()
            val name = fileReader.readLine()
            val field = fileReader.readLine().toInt()
            val primary = fileReader.readLine().toInt()

            val newTech = CustomCharacteristic(
                SecondaryList(dummyChar, dummyChar.primaryList),
                name,
                valid,
                field,
                primary
            )

            customList.add(ItemPanel(newTech))
        }
    }

    fun toggleDeleteConfirm(){_deleteConfirmOpen.update{!deleteConfirmOpen.value}}

    fun setDeletingItem(input: String){_deletingItem.update{input}}

    class ItemPanel(val item: CustomCharacteristic){
        private val _editOpen = MutableStateFlow(false)
        val editOpen = _editOpen.asStateFlow()

        fun toggleOpen(){_editOpen.update{!editOpen.value}}
    }

    init{
        refreshCustomList()
    }
}