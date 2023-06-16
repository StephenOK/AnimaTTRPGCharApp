package com.paetus.animaCharCreator

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

/**
 * Custom input item that only allows for one line of text.
 *
 * @param display string for the associated item
 * @param onValueChange function to run on display's change
 *
 * @param modifier optional settings for the item's parameters
 * @param label optional label for the item
 * @param color optional other color to set the text to
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextInput(
    display: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    color: Color = Color.Black
) {
    //initialize keyboard controller
    val keyboardActive = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = display,
        onValueChange = {
            //close keyboard if enter pressed
            if(it.contains('\n'))
                keyboardActive?.hide()
            //otherwise, update string input
            else
                onValueChange(it)
        },
        textStyle = LocalTextStyle.current.copy(color = color),
        label = {Text(text = label)},
        modifier = modifier
    )
}