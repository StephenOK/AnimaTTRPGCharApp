package com.example.animabuilder.activities.fragments.home_fragments

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.animabuilder.InfoRow
import com.example.animabuilder.NumberInput
import com.example.animabuilder.TextInput
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.view_models.models.CharacterFragmentViewModel
import com.example.animabuilder.view_models.models.HomePageViewModel

/**
 * Fragment to be displayed when working with basic characteristics.
 * Used to manipulate primary characteristics, class, race, and name.
 * Default fragment at character load.
 *
 * @param charFragVM viewModel for this page
 * @param maxNumVM viewModel that manages the bottom bar display
 * @param backFunc function to run on user's back button input
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CharacterPageFragment(
    charFragVM: CharacterFragmentViewModel,
    maxNumVM: HomePageViewModel,
    backFunc: () -> Unit
){
    //get context and keyboard state
    val context = LocalContext.current
    val keyboardActive = LocalSoftwareKeyboardController.current

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                top = 15.dp,
                bottom = 15.dp,
                start = 30.dp,
                end = 30.dp
            )
    ) {
        //name input
        item {
            TextInput(
                display = charFragVM.nameInput.collectAsState().value,
                onValueChange = {
                    //close keyboard if enter is pushed
                    if (it.contains('\n'))
                        keyboardActive?.hide()
                    //otherwise, update name
                    else
                        charFragVM.setNameInput(it)
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                label = stringResource(R.string.nameText)
            )
        }

        //class, race, and level dropdown items
        items(charFragVM.dropdownList){dropItem ->
            DropdownObject(dropItem, maxNumVM)
        }

        //experience point input
        item{
            NumberInput(
                inputText = charFragVM.experiencePoints.collectAsState().value,
                inputFunction = {charFragVM.setExp(it.toInt())},
                emptyFunction = {charFragVM.setExp("")},
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                alignment = TextAlign.Left,
                label = stringResource(R.string.expText)
            )
        }

        item{Spacer(Modifier.height(20.dp))}

        //display gender bonus selection if the character is a duk'zarist
        item{
            if(charFragVM.raceDropdown.output.collectAsState().value == 6){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                ){
                    //display checkbox for selection
                    Checkbox(
                        checked = charFragVM.isMale.collectAsState().value,
                        onCheckedChange = {charFragVM.toggleGender()},
                        modifier = Modifier
                            .weight(0.1f)
                    )
                    //display current selection
                    Text(
                        text = stringResource(charFragVM.genderString.collectAsState().value),
                        modifier = Modifier
                            .clickable { charFragVM.toggleGender() }
                            .weight(0.5f),
                        textAlign = TextAlign.Center
                    )
                }

                if(!charFragVM.magPaladinOpen.collectAsState().value)
                    Spacer(Modifier.height(20.dp))
            }
        }

        //implement paladin checkbox if necessary
        item{
            if(charFragVM.magPaladinOpen.collectAsState().value){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                ){
                    Checkbox(
                        checked = charFragVM.magPaladin.collectAsState().value,
                        onCheckedChange = {charFragVM.toggleMagPaladin()},
                        modifier = Modifier
                            .weight(0.1f)
                    )

                    Text(
                        text = stringResource(R.string.isMagPaladin),
                        modifier = Modifier
                            .clickable { charFragVM.toggleMagPaladin() }
                            .weight(0.5f),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(Modifier.height(20.dp))
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

                Spacer(Modifier.weight(0.01f))

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

        item{Spacer(modifier = Modifier.height(20.dp))}

        item{
            Row(
                Modifier
                    .fillMaxWidth(0.7f)
            ){
                NumberInput(
                    //create input for character's appearance score
                    inputText = charFragVM.appearInput.collectAsState().value,
                    inputFunction = {
                        //attempt new input and notify user of failed input
                        if (it.toInt() <= 10 && !charFragVM.setAppearInput(it.toInt()))
                            Toast.makeText(context, "Invalid Appearance Input", Toast.LENGTH_LONG)
                                .show()
                    },
                    modifier = Modifier
                        .weight(0.5f),
                    emptyFunction = {
                        //clear input if user can change it
                        if (charFragVM.isNotUnattractive())
                            charFragVM.setAppearInput("")
                    },
                    label = stringResource(R.string.appearance)
                )

                Spacer(Modifier.weight(0.01f))

                //create input for a character's gnosis
                NumberInput(
                    inputText = charFragVM.gnosisDisplay.collectAsState().value,
                    inputFunction = {charFragVM.setGnosisDisplay(it.toInt())},
                    emptyFunction = {charFragVM.setGnosisDisplay("")},
                    modifier = Modifier
                        .weight(0.5f),
                    label = stringResource(R.string.gnosisLabel)
                )
            }
        }

        item{Spacer(Modifier.height(15.dp))}

        //display character's size category
        item{
            InfoRow(
                stringResource(R.string.sizeCat),
                charFragVM.sizeInput.collectAsState().value
            )
        }

        //display character's movement value
        item{
            InfoRow(
                stringResource(R.string.movement),
                charFragVM.movementDisplay.collectAsState().value
            )
        }

        //display character's weight index
        item{
            InfoRow(
                stringResource(R.string.weightIndex),
                charFragVM.weightIndex.collectAsState().value
            )
        }
    }

    BackHandler{backFunc()}
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
    val options = stringArrayResource(item.options)

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        //object to hold the dropdown menu
        OutlinedTextField(
            value = options[item.output.collectAsState().value],
            onValueChange = {},
            modifier = Modifier
                .clickable { item.openToggle() }
                .onGloballyPositioned { coordinates ->
                    item.setSize(coordinates.size.toSize())
                },
            label = {Text(text = stringResource(item.nameRef))},
            readOnly = true,
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
            options.forEach { stringIn ->
                DropdownMenuItem(onClick = {
                    //set the new item to show
                    item.setOutput(options.indexOf(stringIn))
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
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //row label
        Text(
            text = stringArrayResource(R.array.primaryCharArray)[primeItem.name],
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
            modifier = Modifier
                .weight(0.2f)
        )

        Spacer(Modifier.weight(0.01f))

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

    Spacer(Modifier.height(5.dp))
}

@Preview
@Composable
fun CharacterPreview(){
    val charInstance = BaseCharacter()
    charInstance.setOwnClass(3)
    charInstance.setOwnRace(6)

    val charFragVM = CharacterFragmentViewModel(charInstance)
    charFragVM.toggleGender()

    CharacterPageFragment(
        charFragVM,
        HomePageViewModel(charInstance)
    ) {}
}
