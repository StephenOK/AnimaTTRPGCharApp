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

/**
 * View model that manages the main activity of the app.
 */
class MainPageViewModel: ViewModel() {
    //initialize new character alert item
    val newChar = AlertData(
        R.string.newCharacterTitle,
        R.string.newCharacterHeader,
        R.string.newButtonConfirm,
        "File must have a name!"
    )

    //initialize load character alert item
    val loadChar = AlertData(
        R.string.loadCharacterTitle,
        R.string.loadCharacterHeader,
        R.string.loadButtonConfirm,
        "Please select a file"
    )

    /**
     * Object that holds data in regards to a main menu option.
     *
     * @param titleRef name used on the option button
     * @param headerRef string used on the alert header
     * @param buttonName string used on the alert's confirmation button
     * @param failedText error string when there is a failed confirmation
     */
    class AlertData(
        val titleRef: Int,
        val headerRef: Int,
        val buttonName: Int,
        val failedText: String
    ){
        //initialize open state of option's dialog
        private val _isOpen = MutableStateFlow(false)
        val isOpen = _isOpen.asStateFlow()

        //initialize the name input for this dialog
        private val _characterName = MutableStateFlow("")
        val characterName = _characterName.asStateFlow()

        /**
         * Changes the open state of the dialog.
         */
        fun toggleOpen(){_isOpen.update{!isOpen.value}}

        /**
         * Changes the name input to the desired value.
         *
         * @param input value to set
         */
        fun setCharacterName(input: String){_characterName.update{input}}

        /**
         * Composable item for the new character dialog.
         */
        @Composable
        fun NewDisplay(){
            //make input for the new character's name
            TextField(
                value = characterName.collectAsState().value,
                onValueChange = {setCharacterName(it)}
            )
        }

        /**
         * Composable item for the load character dialog.
         */
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