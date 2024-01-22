package com.paetus.animaCharCreator.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
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
 * @param itemSelection function to run on the selection of a dropdown item
 */
@Composable
fun OutlinedDropdown(
    data: DropdownData,
    itemSelection: () -> Unit
){
    //retrieve list of options
    val optionsList = stringArrayResource(id = data.optionsRef)

    Row(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        //create outlined text field
        OutlinedTextField(
            value = optionsList[data.output.collectAsState().value],
            onValueChange = {},
            modifier = Modifier
                .clickable {data.openToggle()}
                .onGloballyPositioned {data.setSize(newSize = it.size.toSize())},
            label = {Text(text = stringResource(id = data.nameRef))},
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = data.icon.collectAsState().value,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable{data.openToggle()}
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
                DropdownMenuItem(
                    onClick = {
                        data.setOutput(dropdownIndex = optionsList.indexOf(element = option))
                        itemSelection()
                    }
                ) {
                    Text(text = option)
                }
            }
        }
    }
}