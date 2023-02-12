package com.example.animabuilder

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.animabuilder.activities.keyboardActive

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserInput(
    inputText: MutableState<String>,
    preRun: () -> Unit,
    inputFunction: (String) -> Unit,
    emptyFunction: () -> Unit,
    postRun: () -> Unit,
    modifier: Modifier
){
    TextField(
        value = inputText.value,
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
                    keyboardActive.value?.hide()
            }
            postRun()
        },

        modifier = modifier
    )
}