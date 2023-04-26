package com.example.animabuilder

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType

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
 * @param postRun optional function to run after reading user input
 * @param color optional color the text is
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NumberInput(
    inputText: String,
    inputFunction: (String) -> Unit,
    emptyFunction: () -> Unit,
    modifier: Modifier = Modifier,
    postRun: () -> Unit = {},
    color: Color = Color.Black
){
    //initialize user keyboard
    val keyboardActive = LocalSoftwareKeyboardController.current

    TextField(
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

        //set text color and modifier
        textStyle = LocalTextStyle.current.copy(color = color),
        modifier = modifier
    )
}