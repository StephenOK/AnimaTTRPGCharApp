package com.paetus.animaCharCreator.activities.fragments.home_fragments

import android.widget.Toast
import com.paetus.animaCharCreator.R
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paetus.animaCharCreator.DetailButton
import com.paetus.animaCharCreator.activities.fragments.dialogs.AdvantageCostPick
import com.paetus.animaCharCreator.activities.fragments.dialogs.DetailAlert
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage
import com.paetus.animaCharCreator.view_models.models.AdvantageFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.HomePageViewModel

/**
 * Section that displays advantage and disadvantage information to the user.
 * Allows the user to select advantages and disadvantages for their character.
 * Also displays advantages of their character's current race.
 *
 * @param advantageFragVM viewModel to run with this fragment
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
fun AdvantageFragment(
    advantageFragVM: AdvantageFragmentViewModel,
    homePageVM: HomePageViewModel,
){
    //initialize local context
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                top = 15.dp,
                bottom = 15.dp,
                start = 30.dp,
                end = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //display creation points remaining
        Text(
            text = stringResource(R.string.creationPointLabel) +
                    " ${advantageFragVM.creationPoints.collectAsState().value}",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //display each list of base advantages
            items(advantageFragVM.advantageButtons) {
                AdvantageDisplay(
                    advantageFragVM,
                    it,
                    homePageVM
                )
            }

            item { Spacer(Modifier.height(20.dp)) }

            //display held advantages title
            item { Text(text = stringResource(R.string.heldAdvantageLabel)) }

            //display all of the character's taken advantages and disadvantages
            items(advantageFragVM.takenAdvantages) {
                HeldAdvantageDisplay(
                    advantageFragVM,
                    it,
                    homePageVM
                )
            }

            item { Spacer(Modifier.height(20.dp)) }

            //display all advantages acquired from their race
            item { Text(text = stringResource(R.string.racialAdvantageLabel)) }
            items(advantageFragVM.getRacialAdvantages()) {
                AdvantageRow(it, advantageFragVM, null, {}, null)
            }
        }


        //display advantage choices if available
        if(advantageFragVM.advantageCostOn.collectAsState().value) {
            //clear the half-attuned list
            advantageFragVM.emptyHalfAttuned()

            AdvantageCostPick(
                advantageFragVM
            ) { input: Int? ->
                //notify user of failed acquisition
                if (input != null)
                    Toast.makeText(
                        context,
                        context.getString(input),
                        Toast.LENGTH_LONG
                    ).show()
                //otherwise, close dialog
                else
                    advantageFragVM.toggleAdvantageCostOn()

                //update spent DP
                homePageVM.updateExpenditures()
            }
        }

        //warn user of removal of gift action
        if(advantageFragVM.giftAlertOpen.collectAsState().value){
            AlertDialog(
                onDismissRequest = {},
                text = {
                    Text(
                        text = stringResource(R.string.giftWarning)
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        //remove gift advantage
                        advantageFragVM.removeAdvantage(advantageFragVM.getGift()!!)

                        //update expenditures after change
                        homePageVM.updateExpenditures()

                        //close gift alert
                        advantageFragVM.toggleGiftAlertOn()
                    }) {
                        Text(text = stringResource(R.string.confirmLabel))
                    }
                },
                //close dialog without removing The Gift
                dismissButton = {
                    TextButton(onClick = {advantageFragVM.toggleGiftAlertOn()}){
                        Text(text = stringResource(R.string.cancelLabel))
                    }
                }
            )
        }

        //display an advantage's details if open
        if(advantageFragVM.detailAlertOpen.collectAsState().value)
            DetailAlert(
                stringResource(advantageFragVM.detailItem.collectAsState().value!!.name),
                advantageFragVM.detailItem.collectAsState().value!!
            ){advantageFragVM.toggleDetailAlertOn()}
    }
}

/**
 * Creates a button that displays the base advantages or disadvantages in the input.
 *
 * @param advantageFragVM viewModel for the advantage fragment
 * @param advantageList advantage data to display
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun AdvantageDisplay(
    advantageFragVM: AdvantageFragmentViewModel,
    advantageList: AdvantageFragmentViewModel.AdvantageButtonData,
    homePageVM: HomePageViewModel
){
    //get local context
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //button to open advantage list
        Button(
            onClick = {advantageList.toggleOpen()},
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ){
            //display category name
            Text(text = stringResource(advantageList.category))
        }

        //displayable list of options
        AnimatedVisibility(
            visible = advantageList.isOpen.collectAsState().value,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column {
                advantageList.items.forEach {
                    //display advantage as an obtainable item
                    AdvantageRow(
                        it,
                        advantageFragVM,
                        null,
                        {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add Advantage"
                            )
                        }
                    ){
                        //if more selections need to be made
                        if(it.options != null || it.cost.size > 1) {
                            //activate dialog if multiple options available
                            if (it.options != null)
                                advantageFragVM.setAdjustingPage(1)
                            //activate dialog if multiple costs available
                            else
                                advantageFragVM.setAdjustingPage(2)

                            //open cost selection dialog for this advantage
                            advantageFragVM.setAdjustedAdvantage(it)
                            advantageFragVM.toggleAdvantageCostOn()
                        }
                        //attempt to add the base advantage to the character
                        else {
                            val resultText = advantageFragVM.acquireAdvantage(
                                it,
                                it.picked,
                                it.pickedCost,
                                it.multPicked
                            )

                            //display text if unable to add advantage
                            if (resultText != null)
                                Toast.makeText(
                                    context,
                                    context.getString(resultText),
                                    Toast.LENGTH_LONG
                                ).show()
                            else
                                homePageVM.updateExpenditures()
                        }
                    }
                }
            }
        }
    }
}

/**
 * Creates a row for an advantage where the user can add or remove it, depending on the inputted
 * button action.
 *
 * @param item advantage in this display
 * @param advantageFragVM viewModel that manages this fragment's data
 * @param takenAddition potential additional information to display
 * @param button display image on the row's button
 * @param buttonAction what the button does in its context
 */
@Composable
private fun AdvantageRow(
    item: Advantage,
    advantageFragVM: AdvantageFragmentViewModel,
    takenAddition: String?,
    button: @Composable () -> Unit,
    buttonAction: (() -> Unit)?,
){
    //initialize the advantage's name with potential additional information
    val nameString =
        if(takenAddition != null) stringResource(item.name) + takenAddition
        else stringResource(item.name)

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        if(buttonAction != null) {
            //implement button with given image and function
            Button(
                onClick = { buttonAction() },
                modifier = Modifier
                    .weight(0.25f)
            ) { button() }
        }
        else
            Spacer(Modifier.weight(0.25f))

        //display advantage information
        Text(
            text = nameString,
            modifier = Modifier
                .weight(0.5f),
            textAlign = TextAlign.Center
        )

        //details button
        DetailButton(
            onClick = {
                advantageFragVM.setDetailItem(item)
                advantageFragVM.toggleDetailAlertOn()
            },
            modifier = Modifier
                .weight(0.25f)
        )
    }
}

/**
 * Display for an already acquired advantage.
 *
 * @param advantageFragVM viewModel that holds data for this object
 * @param item advantage to display
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun HeldAdvantageDisplay(
    advantageFragVM: AdvantageFragmentViewModel,
    item: Advantage,
    homePageVM: HomePageViewModel
){
    //retrieve additional information on the advantage
    val nameAddition =
        if(item.picked != null)
            " (${stringArrayResource(item.options!!)[item.picked]})"
        else null

    //display the advantage row
    AdvantageRow(
        item,
        advantageFragVM,
        nameAddition,
        {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Add Advantage"
            )
        }
    )
    {
        //toggle gift alert if advantage is gift
        if(item.name == R.string.gift)
            advantageFragVM.toggleGiftAlertOn()

        //otherwise, simply remove advantage
        else {
            advantageFragVM.removeAdvantage(item)
            homePageVM.updateExpenditures()
        }
    }
}

@Preview
@Composable
fun AdvantagePreview(){
    val charInstance = BaseCharacter()
    charInstance.setOwnRace(1)

    charInstance.advantageRecord.acquireAdvantage(charInstance.advantageRecord.commonAdvantages.gift, null, 0, null)

    val advantageFragVM = AdvantageFragmentViewModel(charInstance, charInstance.advantageRecord)
    advantageFragVM.advantageButtons[4].toggleOpen()

    val homePageVM = HomePageViewModel(charInstance)

    AdvantageFragment(advantageFragVM, homePageVM)
}