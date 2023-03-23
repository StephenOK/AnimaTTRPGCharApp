package com.example.animabuilder.view_models

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainPageViewModel: ViewModel() {
    val newChar = AlertData(
        R.string.newCharacterTitle,
        R.string.newCharacterHeader,
        R.string.newButtonConfirm,
        "File must have a name!"
    )

    val loadChar = AlertData(
        R.string.loadCharacterTitle,
        R.string.loadCharacterHeader,
        R.string.loadButtonConfirm,
        "Please select a file"
    )

    class AlertData(
        val titleRef: Int,
        val headerRef: Int,
        val buttonName: Int,
        val failedText: String
    ){
        private val _isOpen = MutableStateFlow(false)
        private val _characterName = MutableStateFlow("")

        val isOpen = _isOpen.asStateFlow()
        val characterName = _characterName.asStateFlow()

        fun toggleOpen(){_isOpen.update{!isOpen.value}}
        fun setCharacterName(input: String){_characterName.update{input}}

        @Composable
        fun NewDisplay(){
            TextField(
                value = characterName.collectAsState().value,
                onValueChange = {setCharacterName(it)}
            )
        }

        @Composable
        fun LoadDisplay(){
            Column{
                //display each file name as a radio button
                LocalContext.current.fileList().forEach{name ->
                    if(name.contains("AnimaChar")) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .selectable(
                                    selected = (name == characterName.value),
                                    onClick = {setCharacterName(name)}
                                )
                        ) {
                            RadioButton(
                                selected = (name == characterName.collectAsState().value),
                                onClick = {setCharacterName(name)}
                            )

                            Text(
                                text = name.drop(9)
                            )
                        }
                    }
                }
            }
        }
    }
}