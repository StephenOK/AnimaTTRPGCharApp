package com.paetus.animaCharCreator.activities.fragments.home_fragments

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
import com.paetus.animaCharCreator.GeneralCard
import com.paetus.animaCharCreator.NumberInput
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.view_models.models.HomePageViewModel
import com.paetus.animaCharCreator.view_models.models.SecondaryFragmentViewModel

/**
 * Fragment to be displayed when working with secondary characteristics.
 *
 * @param secondaryFragVM viewModel that manages this fragment
 * @param homePageVM viewModel that manages the bottom bar
 */

@Composable
fun SecondaryAbilityFragment(
    secondaryFragVM: SecondaryFragmentViewModel,
    homePageVM: HomePageViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 15.dp,
                bottom = 15.dp,
                start = 30.dp,
                end = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //open freelancer class bonus options if available
        item{
            if(secondaryFragVM.freelancerOptionsOpen.collectAsState().value){
                GeneralCard {
                    //section label
                    Text(text = stringResource(R.string.freelancerPrompt))

                    //freelancer bonus dropdowns
                    FreelancerDropdown(secondaryFragVM.firstSelection)
                    Spacer(Modifier.height(5.dp))
                    FreelancerDropdown(secondaryFragVM.secondSelection)
                    Spacer(Modifier.height(5.dp))
                    FreelancerDropdown(secondaryFragVM.thirdSelection)
                }
            }
        }

        item{Spacer(Modifier.height(10.dp))}

        //display every secondary ability category
        items(secondaryFragVM.allFields){
            MakeTableDisplay(it, homePageVM)
        }
    }
}

/**
 * Creates a dropdown for the freelancer's class bonus selection.
 *
 * @param item item for the index of the selection
 */
@Composable
private fun FreelancerDropdown(
    item: SecondaryFragmentViewModel.FreelancerSelection
){
    Row{
        OutlinedTextField(
            //display currently selected characteristic
            value =
                if(item.selectedIndex.collectAsState().value == 0)
                    stringResource(R.string.selectCharPrompt)
                else
                    stringArrayResource(R.array.secondaryCharacteristics)[item.selectedIndex.collectAsState().value - 1],
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    item.setSize(coordinates.size.toSize())
                },
            readOnly = true,
            trailingIcon = {
                Icon(
                    item.icon.collectAsState().value,
                    "contentDescription",
                    modifier = Modifier
                        .clickable{item.openToggle()}
                )
            }
        )

        DropdownMenu(
            expanded = item.isOpen.collectAsState().value,
            onDismissRequest = {item.openToggle()},
            modifier = Modifier
                .width(with(LocalDensity.current){item.size.value.width.toDp()})
        ) {
            DropdownMenuItem(
                text = {Text(text = stringResource(R.string.selectCharPrompt))},
                onClick = {
                    item.setSelection(0)
                    item.openToggle()
                }
            )

            //display all secondary characteristic options
            stringArrayResource(R.array.secondaryCharacteristics).forEach{
                val index = stringArrayResource(R.array.secondaryCharacteristics).indexOf(it) + 1
                DropdownMenuItem(
                    text = {Text(text = it)},
                    onClick = {
                        item.setSelection(index)
                        item.openToggle()
                    }
                )
            }
        }
    }
}

/**
 * Creates a toggle button to display the associated secondary characteristic table.
 *
 * @param input data item for this field of information
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun MakeTableDisplay(
    input: SecondaryFragmentViewModel.SecondaryFieldData,
    homePageVM: HomePageViewModel
){
    //toggle button for the table
    Button(
        onClick = {input.toggleOpen()},
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        //button label
        Text(
            text = stringArrayResource(R.array.secondaryFields)[input.fieldName]
        )
    }

    //visibility group for the table
    AnimatedVisibility(
        visible = input.tableOpen.collectAsState().value,
        modifier = Modifier
            .fillMaxWidth()
    ){
        GeneralCard {
            //make the header of this section
            RowHead()

            //display each of the field's characteristics
            input.fieldCharacteristics.forEach {
                MakeRow(it, homePageVM)
            }

            Spacer(Modifier.height(5.dp))
        }
    }
}

/**
 * Header row for a secondary characteristic table.
 */
@Composable
private fun RowHead(){
    Row(Modifier.fillMaxWidth()){
        //input point label
        Text(
            text = stringResource(R.string.pointsLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.15f)
        )

        //mod value label
        Text(
            text = stringResource(R.string.modLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )

        //class bonus label
        Text(
            text = stringResource(R.string.classLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )

        //natural bonus label
        Text(
            text = stringResource(R.string.natLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.225f)
        )

        //characteristic total label
        Text(
            text = stringResource(R.string.totalLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )
    }
}

/**
 * Make a row for a secondary characteristic in a table.
 *
 * @param item characteristic item to display for this row
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun MakeRow(
    item: SecondaryFragmentViewModel.SecondaryItem,
    homePageVM: HomePageViewModel
){
    Row{Spacer(Modifier.height(5.dp))}
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        //characteristic label
        Text(
            text = stringArrayResource(R.array.secondaryCharacteristics)[item.getName()],
            textAlign = TextAlign.Start
        )
    }

    //initialize DP cost display of this item
    val dpVal = stringResource(R.string.dpLabel, item.getMultiplier())

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        //user input for the stat's score
        NumberInput(
            inputText = item.pointInput.collectAsState().value,
            inputFunction = {item.setPointInput(it.toInt())},
            emptyFunction = {item.setPointInput("")},
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused)
                        item.setDPDisplay(dpVal)
                    else
                        item.setDPDisplay("")
                }
                .weight(0.15f),
            label = item.dpDisplay.collectAsState().value,
            postRun = {homePageVM.updateExpenditures()}
        )

        //display associated mod value
        Text(
            text = item.getModVal(),
            modifier = Modifier
                .weight(0.125f),
            textAlign = TextAlign.Center
        )

        //display associated class bonus value
        Text(
            text = item.classPoints.collectAsState().value,
            modifier = Modifier
                .weight(0.125f),
            textAlign = TextAlign.Center
        )

        //checkbox to apply natural bonus
        Checkbox(
            checked = item.natBonusCheck.collectAsState().value,
            onCheckedChange = {
                //attempt to toggle natural bonus
                item.setBonusNatCheck()
            },
            modifier = Modifier
                .weight(0.125f)
        )

        //display for the natural bonus value
        Text(
            text = stringResource(item.checkedText.collectAsState().value),
            modifier = Modifier
                .weight(0.1f)
        )

        //display for characteristic's total value
        Text(
            text = item.totalOutput.collectAsState().value,
            modifier = Modifier
                .weight(0.125f),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun SecondaryPreview(){
    val charInstance = BaseCharacter()
    val secondaryFragVM = SecondaryFragmentViewModel(charInstance, charInstance.secondaryList)
    val homePageVM = HomePageViewModel(charInstance)

    secondaryFragVM.allFields[2].toggleOpen()

    SecondaryAbilityFragment(secondaryFragVM = secondaryFragVM, homePageVM = homePageVM)
}