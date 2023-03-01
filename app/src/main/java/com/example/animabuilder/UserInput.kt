package com.example.animabuilder

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserInput(
    inputText: String,
    preRun: () -> Unit,
    inputFunction: (String) -> Unit,
    emptyFunction: () -> Unit,
    postRun: () -> Unit,
    modifier: Modifier
){
    val keyboardActive = LocalSoftwareKeyboardController.current

    TextField(
        value = inputText,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {
            preRun()
            try{
                inputFunction(it)
            }
            catch(e: NumberFormatException){
                if(it == "")
                    emptyFunction()
                else if (it.contains('\n'))
                    keyboardActive?.hide()
            }
            postRun()
        },

        modifier = modifier
    )
}