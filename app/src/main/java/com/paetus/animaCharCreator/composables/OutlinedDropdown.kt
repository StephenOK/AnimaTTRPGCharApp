package com.paetus.animaCharCreator.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.toSize
import com.paetus.animaCharCreator.DropdownData

/**
 * Creates a dropdown object with a border around its contents.
 *
 * @param data item that holds state data for the dropdown
 * @param modifier modifier to define this object's weight
 * @param isOpenable defines whether the outlined dropdown can be opened
 * @param unopenFunc what to run if the dropdown isn't openable
 * @param qualifyOption checks if the option input is valid for display
 * @param itemSelection function to run on the selection of a dropdown item
 */
@Composable
fun OutlinedDropdown(
    data: DropdownData,
    modifier: Modifier = Modifier,
    isOpenable: Boolean = true,
    unopenFunc: () -> Unit = {},
    qualifyOption: (String) -> Boolean = {true},
    itemSelection: () -> Unit
){
    //retrieve list of options
    val optionsList = stringArrayResource(id = data.optionsRef)

    //create outlined text field
    OutlinedTextField(
        value = optionsList[data.output.collectAsState().value],
        onValueChange = {},
        modifier = Modifier
            .clickable {data.openToggle()}
            .onGloballyPositioned {data.setSize(newSize = it.size.toSize())}
            .then(modifier),
        label = {Text(text = stringResource(id = data.nameRef))},
        readOnly = true,
        trailingIcon = {
            Icon(
                imageVector = data.icon.collectAsState().value,
                contentDescription = "",
                modifier = Modifier
                    .clickable{
                        //toggle item if openable
                        if(isOpenable)
                            data.openToggle()
                        //run other function if not
                        else
                            unopenFunc()
                    }
            )
        }
    )

    DropdownMenu(
        expanded = data.isOpen.collectAsState().value,
        onDismissRequest = {data.openToggle()},
        modifier = Modifier
            .width(with(LocalDensity.current){data.size.collectAsState().value.width.toDp()})
    ){
        optionsList.forEach{option ->
            //hide option if not legal
            if(qualifyOption(option))
                DropdownMenuItem(
                    text = {Text(text = option)},
                    onClick = {
                        data.setOutput(dropdownIndex = optionsList.indexOf(element = option))
                        itemSelection()
                    }
                )
        }
    }
}