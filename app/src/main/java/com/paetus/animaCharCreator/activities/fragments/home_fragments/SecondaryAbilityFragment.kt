package com.paetus.animaCharCreator.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedContent
import com.paetus.animaCharCreator.R
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.paetus.animaCharCreator.activities.fragments.dialogs.CustomSecondaryDialog
import com.paetus.animaCharCreator.composables.GeneralCard
import com.paetus.animaCharCreator.composables.NumberInput
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.CustomCharacteristic
import com.paetus.animaCharCreator.numberScroll
import com.paetus.animaCharCreator.textScrollUp
import com.paetus.animaCharCreator.view_models.models.CustomSecondaryViewModel
import com.paetus.animaCharCreator.view_models.models.HomePageViewModel
import com.paetus.animaCharCreator.view_models.models.SecondaryFragmentViewModel

/**
 * Fragment to be displayed when working with secondary characteristics.
 *
 * @param filename name of the character's file to reference for custom secondaries
 * @param secondaryFragVM viewModel that manages this fragment
 * @param homePageVM viewModel that manages the bottom bar
 */

@Composable
fun SecondaryAbilityFragment(
    filename: String,
    secondaryFragVM: SecondaryFragmentViewModel,
    homePageVM: HomePageViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 30.dp,
                end = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{Spacer(modifier = Modifier.height(15.dp))}

        //open freelancer class bonus options if available
        item{
            if(secondaryFragVM.freelancerOptionsOpen.collectAsState().value){
                GeneralCard {
                    //section label
                    Text(text = stringResource(id = R.string.freelancerPrompt))

                    //freelancer bonus dropdowns
                    FreelancerDropdown(
                        selection = secondaryFragVM.firstSelection,
                        secondaryFragVM = secondaryFragVM
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    FreelancerDropdown(
                        selection =secondaryFragVM.secondSelection,
                        secondaryFragVM = secondaryFragVM
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    FreelancerDropdown(
                        selection = secondaryFragVM.thirdSelection,
                        secondaryFragVM = secondaryFragVM
                    )
                }
            }
        }

        item{Spacer(modifier = Modifier.height(10.dp))}

        //display every secondary ability category
        items(secondaryFragVM.allFields){field ->
            MakeTableDisplay(
                field = field,
                homePageVM = homePageVM
            )
        }

        item{Spacer(modifier = Modifier.height(15.dp))}

        //button to initiate custom secondary characteristic creation
        item{
            Button(
                onClick = {secondaryFragVM.toggleCustomOpen()}
            ){
                Text(text = stringResource(id = R.string.customCharacteristicButton))
            }
        }
    }

    //display custom characteristic creation dialog, if open
    if(secondaryFragVM.customIsOpen.collectAsState().value)
        CustomSecondaryDialog(
            secondaryList = secondaryFragVM.charInstance.secondaryList,
            filename = filename,
            customSecondVM = CustomSecondaryViewModel(secondaryFragVM)
        )
}

/**
 * Creates a dropdown for the freelancer's class bonus selection.
 *
 * @param selection item that manages the created freelancer dropdown object
 */
@Composable
private fun FreelancerDropdown(
    selection: SecondaryFragmentViewModel.FreelancerSelection,
    secondaryFragVM: SecondaryFragmentViewModel
){
    Row{
        OutlinedTextField(
            //display currently selected characteristic
            value =
                if(selection.selectedIndex.collectAsState().value == 0)
                    stringResource(id = R.string.selectCharPrompt)
                else if (selection.selectedIndex.collectAsState().value < 39)
                    stringArrayResource(R.array.secondaryCharacteristics)[selection.selectedIndex.collectAsState().value - 1]
                else
                    (secondaryFragVM.getAllSecondaries()[selection.selectedIndex.collectAsState().value - 1] as CustomCharacteristic).name.value,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    selection.setSize(coordinates.size.toSize())
                },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = selection.icon.collectAsState().value,
                    contentDescription = "contentDescription",
                    modifier = Modifier
                        .clickable{selection.openToggle()}
                )
            }
        )

        DropdownMenu(
            expanded = selection.isOpen.collectAsState().value,
            onDismissRequest = {selection.openToggle()},
            modifier = Modifier
                .width(with(LocalDensity.current){selection.size.value.width.toDp()})
        ) {
            DropdownMenuItem(
                text = {Text(text = stringResource(id = R.string.selectCharPrompt))},
                onClick = {
                    selection.setSelection(newSecondary = 0)
                    selection.openToggle()
                }
            )

            //display all secondary characteristic options
            secondaryFragVM.allFields.forEach{discipline ->
                discipline.fieldCharacteristics.forEach {characteristic ->
                    val displayName =
                        if(characteristic.secondaryItem is CustomCharacteristic)
                            characteristic.getCustomName()
                        else
                            stringArrayResource(id = R.array.secondaryCharacteristics)[characteristic.getName()]

                    DropdownMenuItem(
                        text = {Text(text = displayName)},
                        onClick = {
                            selection.setSelection(newSecondary = secondaryFragVM.getAllSecondaries().indexOf(characteristic.secondaryItem) + 1)
                            selection.openToggle()
                        }
                    )
                }
            }
        }
    }
}

/**
 * Creates a toggle button to display the associated secondary characteristic table.
 *
 * @param field one of the secondary characteristic categories
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun MakeTableDisplay(
    field: SecondaryFragmentViewModel.SecondaryFieldData,
    homePageVM: HomePageViewModel
){
    //toggle button for the table
    Button(
        onClick = {field.toggleOpen()},
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        //button label
        Text(
            text = stringArrayResource(R.array.secondaryFields)[field.fieldName]
        )
    }

    //visibility group for the table
    AnimatedVisibility(
        visible = field.tableOpen.collectAsState().value,
        modifier = Modifier
            .fillMaxWidth()
    ){
        GeneralCard {
            //make the header of this section
            RowHead()

            //display each of the field's characteristics
            field.fieldCharacteristics.forEach {characteristic ->
                MakeRow(
                    secondaryChar = characteristic,
                    homePageVM = homePageVM
                )
            }

            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

/**
 * Header row for a secondary characteristic table.
 */
@Composable
private fun RowHead(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ){
        //input point label
        Text(
            text = stringResource(id = R.string.pointsLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.15f)
        )

        //mod value label
        Text(
            text = stringResource(id = R.string.modLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )

        //class bonus label
        Text(
            text = stringResource(id = R.string.classLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )

        //natural bonus label
        Text(
            text = stringResource(id = R.string.natLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.225f)
        )

        //characteristic total label
        Text(
            text = stringResource(id = R.string.totalLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )
    }
}

/**
 * Make a row for a secondary characteristic in a table.
 *
 * @param secondaryChar secondary item to display for this row
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun MakeRow(
    secondaryChar: SecondaryFragmentViewModel.SecondaryItem,
    homePageVM: HomePageViewModel
){
    //get this characteristic's displayed name
    val itemName =
        if(secondaryChar.secondaryItem is CustomCharacteristic)
            secondaryChar.getCustomName()
        else
            stringArrayResource(R.array.secondaryCharacteristics)[secondaryChar.getName()]

    Row{Spacer(modifier = Modifier.height(5.dp))}

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        //characteristic label
        Text(
            text = itemName,
            textAlign = TextAlign.Start
        )
    }

    //initialize DP cost display of this item
    val dpVal = stringResource(id = R.string.dpLabel, secondaryChar.getMultiplier())

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        //user input for the stat's score
        NumberInput(
            inputText = secondaryChar.pointInput.collectAsState().value,
            inputFunction = {secondaryChar.setPointInput(purchase = it.toInt())},
            emptyFunction = {secondaryChar.setPointInput(display = "")},
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused)
                        secondaryChar.setDPDisplay(dpCost = dpVal)
                    else
                        secondaryChar.setDPDisplay(dpCost = "")
                }
                .weight(0.15f),
            label = secondaryChar.dpDisplay.collectAsState().value,
            postRun = {homePageVM.updateExpenditures()}
        )

        //display associated mod value
        Text(
            text = secondaryChar.getModVal(),
            modifier = Modifier
                .weight(0.125f),
            textAlign = TextAlign.Center
        )

        //display associated class bonus value
        AnimatedContent(
            targetState = secondaryChar.classPoints.collectAsState().value,
            modifier = Modifier
                .weight(0.125f),
            transitionSpec = numberScroll,
            label = "${itemName}ClassPoints"
        ) {
            Text(
                text = "$it",
                textAlign = TextAlign.Center
            )
        }

        //checkbox to apply natural bonus
        Checkbox(
            checked = secondaryChar.natBonusCheck.collectAsState().value,
            onCheckedChange = {
                //attempt to toggle natural bonus
                secondaryChar.setBonusNatCheck()
            },
            modifier = Modifier
                .weight(0.125f)
        )

        //display for the natural bonus value
        AnimatedContent(
            targetState = stringResource(id = secondaryChar.checkedText.collectAsState().value),
            modifier = Modifier
                .weight(0.1f),
            transitionSpec = textScrollUp,
            label = "${itemName}BonusText"
        ) {
            Text(text = it)
        }

        //display for characteristic's total value
        AnimatedContent(
            targetState = secondaryChar.totalOutput.collectAsState().value,
            modifier = Modifier
                .weight(0.125f),
            transitionSpec = numberScroll,
            label = "${itemName}Total"
        ) {
            Text(
                text = "$it",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun SecondaryPreview(){
    val charInstance = BaseCharacter()
    val secondaryFragVM = SecondaryFragmentViewModel(charInstance, charInstance.secondaryList)
    val homePageVM = HomePageViewModel(charInstance)

    secondaryFragVM.allFields[2].toggleOpen()

    SecondaryAbilityFragment(
        filename = "filename",
        secondaryFragVM = secondaryFragVM,
        homePageVM = homePageVM
    )
}