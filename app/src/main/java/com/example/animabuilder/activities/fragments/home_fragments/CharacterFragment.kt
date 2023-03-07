package com.example.animabuilder.activities.fragments.home_fragments

import android.widget.Toast
import com.example.animabuilder.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.animabuilder.view_models.BottomBarViewModel
import com.example.animabuilder.NumberInput
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.view_models.CharacterFragmentViewModel

/**
 * Fragment to be displayed when working with basic characteristics
 * Used to manipulate core stats, class, race, and name
 * Default fragment at character load
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CharacterPageFragment(
    charInstance: BaseCharacter,
    maxNumVM: BottomBarViewModel,
    charFragVM: CharacterFragmentViewModel = CharacterFragmentViewModel(charInstance, maxNumVM),
    updateFunc: () -> Unit,
){
    val context = LocalContext.current
    val keyboardActive = LocalSoftwareKeyboardController.current

    //page column
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        //name input
        item {
            OutlinedTextField(
                value = charFragVM.nameInput.collectAsState().value,
                onValueChange = {
                    //close keyboard if enter is pushed
                    if (it.contains('\n'))
                        keyboardActive?.hide()
                    //otherwise, update name
                    else {
                        charFragVM.setNameInput(it)
                        charInstance.charName = it
                    }
                },
                label = { Text(text = stringResource(R.string.nameText)) },
            )
        }

        //other dropdown items
        items(charFragVM.dropdownList){dropItem ->
            DropdownObject(dropItem, updateFunc)
        }

        //primary statistic table
        item {
            //table header row
            Row {
                //column buffer
                Spacer(modifier = Modifier.weight(0.25f))

                //score header
                Text(
                    text = stringResource(R.string.scoreLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.25f)
                )

                //bonus header
                Text(
                    text = stringResource(R.string.specialLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.25f)
                )

                //mod header
                Text(
                    text = stringResource(R.string.modLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.25f)
                )
            }
        }

        //create row for each primary statistic
        items(charFragVM.primaryDataList){ primaryItem ->
            PrimaryRow(primaryItem)
        }

        item{Text(text = stringResource(R.string.sizeCat) + charFragVM.sizeInput.collectAsState().value)}

        item{
            Text(text = stringResource(R.string.appearance))
            NumberInput(
                charFragVM.appearInput.collectAsState().value,
                {},
                {input ->
                    if(input.toInt() <= 10) {
                        charInstance.setAppearance(input.toInt())
                        if(charInstance.appearance == input.toInt())
                            charFragVM.setAppearInput(input)
                        else
                            Toast.makeText(context, "Invalid Appearance Input", Toast.LENGTH_LONG).show()
                    }
                },
                {
                    if(charInstance.advantageRecord.getAdvantage("Unattractive") == null) {
                        charInstance.setAppearance(5)
                        charFragVM.setAppearInput("")
                    }
                },
                {},
                Color.Black,
                Modifier
            )
        }
    }
}

/**
 * Creates a dropdown object for user inputs
 *
 * item: data set of dropdown object
 */
@Composable
private fun DropdownObject(
    item: CharacterFragmentViewModel.DropdownData,
    updateFunc: () -> Unit
){
    val heldArray = stringArrayResource(item.options)

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.67).dp)
    ){
        //object to hold the dropdown menu
        OutlinedTextField(
            value = heldArray[item.indexTracker.collectAsState().value],
            onValueChange = {},
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    item.setSize(coordinates.size.toSize())
                },
            label = {Text(text = stringResource(item.nameRef))},
            trailingIcon = {
                Icon(
                    item.icon.collectAsState().value,
                    "contentDescription",
                    modifier = Modifier.clickable {item.setOpen(!item.isOpen.value)})
            }
        )

        //dropdown object
        DropdownMenu(
            expanded = item.isOpen.collectAsState().value,
            onDismissRequest = {item.setOpen(false)},
            modifier = Modifier.width(with(LocalDensity.current) { item.size.value.width.toDp() })
        ) {
            //create an object for each option in the inputted list
            heldArray.forEach { stringIn ->
                DropdownMenuItem(onClick = {
                    //set the new item to show
                    item.setIndexTracker(heldArray.indexOf(stringIn))

                    //run the inputted action
                    item.onChange(item.indexTracker.value)
                    updateFunc()

                    //close the dropdown menu
                    item.setOpen(false)
                }) {
                    Text(text = stringIn)
                }
            }
        }
    }
}

/**
 * Create a row for the primary characteristics table
 *
 * primeItem: primary characteristic data to display
 */
@Composable
private fun PrimaryRow(
    primeItem: CharacterFragmentViewModel.PrimeCharacteristicData
){
    Row(verticalAlignment = Alignment.CenterVertically){
        //row label
        Text(
            text = "${stringResource(primeItem.nameRef)}:",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.25f))

        //user input section
        NumberInput(
            primeItem.input.collectAsState().value,
            {},
            {input ->
                if(input.toInt() in 1..20) {
                    //update display and mod values
                    primeItem.primaryStat.setInput(input.toInt())
                    primeItem.setInput(input)
                    primeItem.setOutput(primeItem.primaryStat)

                    primeItem.changeFunc()
                }
            },
            {primeItem.setInput("")},
            {},
            Color.Black,
            Modifier.weight(0.25f)
        )

        Text(
            text = primeItem.output.collectAsState().value.specialVal,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.25f)
        )

        //mod display
        Text(
            text = primeItem.output.collectAsState().value.modVal,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.25f))
    }
}