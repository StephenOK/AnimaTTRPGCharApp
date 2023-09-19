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
    val optionsList = stringArrayResource(optionsRef)

    Row(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        OutlinedTextField(
            value = optionsList[index],
            onValueChange = {},
            modifier = Modifier
                .clickable { openFunc() }
                .onGloballyPositioned { sizeSetter(it) },
            label = {Text(text = stringResource(labelRef))},
            readOnly = true,
            trailingIcon = {
                Icon(
                    icon,
                    "",
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
            optionsList.forEach{
                DropdownMenuItem(
                    onClick = {itemSelection(optionsList.indexOf(it))}
                ) {
                    Text(text = it)
                }
            }
        }
    }
}