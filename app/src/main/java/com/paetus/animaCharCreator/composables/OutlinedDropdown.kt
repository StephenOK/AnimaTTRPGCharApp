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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource

/**
 * Creates a dropdown object with a border around its contents.
 *
 * @param optionsRef list of items contained in the dropdown
 * @param index currently selected item in the list
 * @param openState whether the dropdown is open or not
 * @param labelRef contents for this item's label
 * @param icon trailing icon contents
 * @param size size needed for this item
 * @param sizeSetter function to change this item's size
 * @param itemSelection function to run on the selection of a dropdown item
 * @param openFunc function to run on opening and closing this dropdown
 */
@Composable
fun OutlinedDropdown(
    optionsRef: Int,
    index: Int,
    openState: Boolean,
    labelRef: Int,
    icon: ImageVector,
    size: Size,
    sizeSetter: (LayoutCoordinates) -> Unit,
    itemSelection: (Int) -> Unit,
    openFunc: () -> Unit
){
    //retrieve list of options
    val optionsList = stringArrayResource(id = optionsRef)

    Row(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        //create outlined text field
        OutlinedTextField(
            value = optionsList[index],
            onValueChange = {},
            modifier = Modifier
                .clickable {openFunc()}
                .onGloballyPositioned { sizeSetter(it) },
            label = {Text(text = stringResource(id = labelRef))},
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable{openFunc()}
                )
            }
        )

        DropdownMenu(
            expanded = openState,
            onDismissRequest = {openFunc()},
            modifier = Modifier
                .width(with(LocalDensity.current){size.width.toDp()})
        ){
            optionsList.forEach{option ->
                DropdownMenuItem(
                    onClick = {itemSelection(optionsList.indexOf(element = option))}
                ) {
                    Text(text = option)
                }
            }
        }
    }
}