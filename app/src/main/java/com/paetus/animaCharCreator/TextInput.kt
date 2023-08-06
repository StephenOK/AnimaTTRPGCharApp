package com.paetus.animaCharCreator

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

/**
 * Custom input item that only allows for one line of text.
 *
 * @param display string for the associated item
 * @param onValueChange function to run on display's change
 *
 * @param modifier optional settings for the item's parameters
 * @param label optional label for the item
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextInput(
    display: String,
    onValueChange: (String) -> Unit,

    modifier: Modifier = Modifier,
    label: String = ""
) {
    //initialize keyboard controller
    val keyboardActive = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        //set initial display
        value = display,

        onValueChange = {
            //close keyboard if enter pressed
            if(it.contains('\n'))
                keyboardActive?.hide()
            //otherwise, update string input
            else
                onValueChange(it)
        },
        label = {Text(text = label)},
        modifier = modifier
    )
}