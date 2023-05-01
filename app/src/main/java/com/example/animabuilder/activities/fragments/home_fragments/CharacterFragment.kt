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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.animabuilder.NumberInput
import com.example.animabuilder.view_models.CharacterFragmentViewModel
import com.example.animabuilder.view_models.HomePageViewModel

/**
 * Fragment to be displayed when working with basic characteristics.
 * Used to manipulate primary characteristics, class, race, and name.
 * Default fragment at character load.
 *
 * @param charFragVM viewModel for this page
 * @param maxNumVM viewModel that manages the bottom bar display
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CharacterPageFragment(
    charFragVM: CharacterFragmentViewModel,
    maxNumVM: HomePageViewModel
){
    //get context and keyboard state
    val context = LocalContext.current
    val keyboardActive = LocalSoftwareKeyboardController.current

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
                    else
                        charFragVM.setNameInput(it)
                },
                label = { Text(text = stringResource(R.string.nameText)) },
            )
        }

        //class, race, and level dropdown items
        items(charFragVM.dropdownList){dropItem ->
            DropdownObject(dropItem, maxNumVM)
        }

        //display gender bonus selection if the character is a duk'zarist
        item{
            if(charFragVM.raceDropdown.output.collectAsState().value == stringArrayResource(R.array.raceArray)[6]){
                Row {
                    //display checkbox for selection
                    Checkbox(
                        checked = charFragVM.isMale.collectAsState().value,
                        onCheckedChange = { charFragVM.toggleGender() }
                    )
                    //display current selection
                    Text(text = stringResource(charFragVM.genderString.collectAsState().value))
                }
            }
        }

        //implement paladin checkbox if necessary
        item{
            if(charFragVM.magPaladinOpen.collectAsState().value){
                Row {
                    Checkbox(
                        checked = charFragVM.magPaladin.collectAsState().value,
                        onCheckedChange = {charFragVM.toggleMagPaladin()}
                    )

                    Text(
                        text = stringResource(R.string.isMagPaladin)
                    )
                }
            }
        }

        //primary characteristic table
        item {
            //table header row
            Row {
                //column buffer
                Spacer(modifier = Modifier.weight(0.2f))

                //score header
                Text(
                    text = stringResource(R.string.scoreLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )

                //level bonus input header
                Text(
                    text = stringResource(R.string.bonusLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )

                //bonus header
                Text(
                    text = stringResource(R.string.specialLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )

                //mod header
                Text(
                    text = stringResource(R.string.modLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )
            }
        }

        //create row for each primary characteristic
        items(charFragVM.primaryDataList){
            PrimaryRow(charFragVM, it)
        }

        item{
            NumberInput(
                inputText = charFragVM.experiencePoints.collectAsState().value,
                inputFunction = {charFragVM.setExp(it.toInt())},
                emptyFunction = {charFragVM.setExp("")}
            )
        }

        //display character's size category
        item{Text(text = stringResource(R.string.sizeCat) + charFragVM.sizeInput.collectAsState().value)}

        //display character's movement value
        item{Text(text = stringResource(R.string.movement) + charFragVM.movementDisplay.collectAsState().value)}

        //display character's weight index
        item{Text(text = stringResource(R.string.weightIndex) + charFragVM.weightIndex.collectAsState().value)}

        //create input for character's appearance score
        item{
            Text(text = stringResource(R.string.appearance))
            NumberInput(
                inputText = charFragVM.appearInput.collectAsState().value,
                inputFunction = {
                    //attempt new input and notify user of failed input
                    if(it.toInt() <= 10 && !charFragVM.setAppearInput(it.toInt()))
                        Toast.makeText(context, "Invalid Appearance Input", Toast.LENGTH_LONG).show()
                },
                emptyFunction = {
                    //clear input if user can change it
                    if(charFragVM.isNotUnattractive())
                        charFragVM.setAppearInput("")
                }
            )
        }

        //create input for character's gnosis
        item{
            Text(text = stringResource(R.string.gnosisLabel))
            NumberInput(
                inputText = charFragVM.gnosisDisplay.collectAsState().value,
                inputFunction = {charFragVM.setGnosisDisplay(it.toInt())},
                emptyFunction = {charFragVM.setGnosisDisplay("")}
            )
        }
    }
}

/**
 * Creates a dropdown object for user inputs.
 *
 * @param item data held for this dropdown object
 * @param maxNumVM viewModel that manages the bottom bar display
 */
@Composable
private fun DropdownObject(
    item: CharacterFragmentViewModel.DropdownData,
    maxNumVM: HomePageViewModel
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.67).dp)
    ){
        //object to hold the dropdown menu
        OutlinedTextField(
            value = item.output.collectAsState().value,
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
                    modifier = Modifier.clickable{item.openToggle()})
            }
        )

        //dropdown object
        DropdownMenu(
            expanded = item.isOpen.collectAsState().value,
            onDismissRequest = {item.openToggle()},
            modifier = Modifier.width(with(LocalDensity.current) { item.size.value.width.toDp() })
        ) {
            //create an object for each option in the inputted list
            item.options.forEach { stringIn ->
                DropdownMenuItem(onClick = {
                    //set the new item to show
                    item.setOutput(item.options.indexOf(stringIn))
                    maxNumVM.updateMaximums()
                    maxNumVM.updateExpenditures()
                }) {
                    Text(text = stringIn)
                }
            }
        }
    }
}

/**
 * Create a row for the primary characteristics table.
 *
 * @param charFragVM viewModel that manages this fragment
 * @param primeItem primary characteristic data to display
 */
@Composable
private fun PrimaryRow(
    charFragVM: CharacterFragmentViewModel,
    primeItem: CharacterFragmentViewModel.PrimeCharacteristicData
){
    Row(verticalAlignment = Alignment.CenterVertically){
        //row label
        Text(
            text = "${primeItem.name}:",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f))

        //user input section
        NumberInput(
            inputText = primeItem.input.collectAsState().value,
            inputFunction = {
                //change input and other necessary items if in legal range
                if(it.toInt() in 1..20)
                    primeItem.setInput(it.toInt())
            },
            emptyFunction = {primeItem.setInput("")},
            modifier = Modifier.weight(0.2f)
        )

        //level bonus input
        NumberInput(
            inputText = primeItem.bonusInput.collectAsState().value,
            inputFunction = {primeItem.setBonusInput(it.toInt())},
            emptyFunction = {primeItem.setBonusInput("")},
            modifier = Modifier.weight(0.2f),
            color = charFragVM.bonusColor.collectAsState().value
        )

        //characteristic bonus display
        Text(
            text = primeItem.bonus.collectAsState().value,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )

        //mod display
        Text(
            text = primeItem.modTotal.collectAsState().value,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f))
    }
}