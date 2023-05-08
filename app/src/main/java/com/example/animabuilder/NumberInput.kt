package com.example.animabuilder

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

/**
 * Object that runs numerical input for the character.
 * Only allows taking positive or zero numbers.
 * Catches empty input.
 *
 * @param inputText String to display in the TextField
 * @param inputFunction function to run for user's numerical input
 * @param emptyFunction function to run for no input
 *
 * @param modifier optional design properties of the input object
 * @param label optional label for the input field
 * @param postRun optional function to run after reading user input
 * @param color optional color the text is
 * @param alignment optional alignment for the input text
 * @param readOnly optional state of the text input
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NumberInput(
    inputText: String,
    inputFunction: (String) -> Unit,
    emptyFunction: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    postRun: () -> Unit = {},
    color: Color = Color.Black,
    alignment: TextAlign = TextAlign.Center,
    readOnly: Boolean = false
){
    //initialize user keyboard
    val keyboardActive = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = inputText,

        //get number keyboard for input
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

        onValueChange = {
            //read user input
            try{
                //implement numerical input
                inputFunction(it)
            }
            //catch non-numerical input
            catch(e: NumberFormatException){
                //display and run empty input
                if(it == "")
                    emptyFunction()

                //close keyboard if 'enter' ever input
                else if (it.contains('\n'))
                    keyboardActive?.hide()

                //no changes for any other kind of input
            }

            //run function after reading input
            postRun()
        },

        //set other modifiers
        modifier = modifier,

        //set text color
        textStyle = LocalTextStyle.current.copy(
            color = color,
            textAlign = alignment
        ),

        //set readonly status
        readOnly = readOnly,

        //set object label
        label = {Text(text = label)}
    )
}