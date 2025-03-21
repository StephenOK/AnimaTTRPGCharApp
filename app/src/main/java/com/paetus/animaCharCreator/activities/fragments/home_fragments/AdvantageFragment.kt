package com.paetus.animaCharCreator.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import com.paetus.animaCharCreator.R
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paetus.animaCharCreator.composables.DetailButton
import com.paetus.animaCharCreator.composables.GeneralCard
import com.paetus.animaCharCreator.activities.fragments.dialogs.AdvantageCostPick
import com.paetus.animaCharCreator.activities.fragments.dialogs.DetailAlert
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SecondaryList
import com.paetus.animaCharCreator.composables.InfoRow
import com.paetus.animaCharCreator.numberScroll
import com.paetus.animaCharCreator.view_models.models.AdvantageFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.HomePageViewModel

/**
 * Section that displays advantage and disadvantage information to the user.
 * Allows the user to select advantages and disadvantages for their character.
 * Also displays advantages of their character's current race.
 *
 * @param advantageFragVM viewModel that manages the advantage page data
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
fun AdvantageFragment(
    secondaryList: SecondaryList,
    advantageFragVM: AdvantageFragmentViewModel,
    homePageVM: HomePageViewModel,
){
    //initialize local context
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 30.dp,
                end = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(15.dp))

        //display creation points remaining
        InfoRow(
            label = stringResource(id = R.string.creationPointLabel)
        ) {modifier, _ ->
            AnimatedContent(
                targetState = advantageFragVM.creationPoints.collectAsState().value,
                modifier = modifier,
                transitionSpec = numberScroll,
                label = "pointsRemainingDisplay"
            ) {
                Text(
                    text = "$it",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //display each list of base advantages
            items(advantageFragVM.advantageButtons) {buttonData ->
                AdvantageDisplay(
                    advantageList = buttonData,
                    advantageFragVM = advantageFragVM,
                    homePageVM = homePageVM
                )
            }

            item {Spacer(Modifier.height(20.dp))}

            item{
                GeneralCard{
                    //display held advantages title
                    Text(text = stringResource(id = R.string.heldAdvantageLabel))

                    //display all of the character's taken advantages and disadvantages
                    advantageFragVM.takenAdvantages.forEach{advantage ->
                        HeldAdvantageDisplay(
                            advantage = advantage,
                            advantageFragVM = advantageFragVM,
                            homePageVM = homePageVM
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(15.dp))

        //display advantage choices if available
        if(advantageFragVM.advantageCostOn.collectAsState().value) {
            //clear the half-attuned list
            advantageFragVM.emptyHalfAttuned()

            AdvantageCostPick(
                secondaryList,
                advantageFragVM = advantageFragVM
            ) {input: Int? ->
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
                        text = stringResource(id = R.string.giftWarning)
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
                        Text(text = stringResource(id = R.string.confirmLabel))
                    }
                },
                //close dialog without removing The Gift
                dismissButton = {
                    TextButton(onClick = {advantageFragVM.toggleGiftAlertOn()}){
                        Text(text = stringResource(id = R.string.cancelLabel))
                    }
                }
            )
        }

        //display an advantage's details if open
        if(advantageFragVM.detailAlertOpen.collectAsState().value)
            DetailAlert(
                title = stringResource(id = advantageFragVM.detailItem.collectAsState().value!!.name),
                item = advantageFragVM.detailItem.collectAsState().value!!,
                closeFunc = {advantageFragVM.toggleDetailAlertOn()}
            )
    }
}

/**
 * Creates a button that displays the base advantages or disadvantages in the input.
 *
 * @param advantageList advantage data to display
 * @param advantageFragVM viewModel for the advantage fragment
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun AdvantageDisplay(
    advantageList: AdvantageFragmentViewModel.AdvantageButtonData,
    advantageFragVM: AdvantageFragmentViewModel,
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
            Text(text = stringResource(id = advantageList.category))
        }

        //displayable list of options
        AnimatedVisibility(
            visible = advantageList.isOpen.collectAsState().value,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            GeneralCard {
                advantageList.advList.forEach { advantage ->
                    //display advantage as an obtainable item
                    AdvantageRow(
                        advantage = advantage,
                        takenAddition = null,
                        advantageFragVM = advantageFragVM,
                        buttonIcon = {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add Advantage"
                            )
                        }
                    ){
                        //if more selections need to be made
                        if(advantage.options != null || advantage.cost.size > 1) {
                            //activate dialog if multiple options available
                            if (advantage.options != null)
                                advantageFragVM.setAdjustingPage(pageNum = 1)
                            //activate dialog if multiple costs available
                            else
                                advantageFragVM.setAdjustingPage(pageNum = 2)

                            //open cost selection dialog for this advantage
                            advantageFragVM.setAdjustedAdvantage(advantage = advantage)
                            advantageFragVM.toggleAdvantageCostOn()
                        }
                        //attempt to add the base advantage to the character
                        else {
                            val resultText = advantageFragVM.acquireAdvantage(
                                advantage = advantage,
                                taken = advantage.picked,
                                takenCost = advantage.pickedCost,
                                multTaken = advantage.multPicked
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
 * @param advantage advantage in this display
 * @param takenAddition potential additional information to display
 * @param advantageFragVM viewModel that manages this fragment's data
 * @param buttonIcon display image on the row's button
 * @param buttonAction what the button does in its context
 */
@Composable
private fun AdvantageRow(
    advantage: Advantage,
    takenAddition: String?,
    advantageFragVM: AdvantageFragmentViewModel,
    buttonIcon: @Composable () -> Unit,
    buttonAction: (() -> Unit)?,
){
    //get current context
    val context = LocalContext.current

    //initialize the advantage's name with potential additional information
    val nameString =
        if(takenAddition != null) stringResource(id = advantage.name) + takenAddition
        else stringResource(id = advantage.name)

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        if(buttonAction != null) {
            //implement button with given image and function
            Button(
                onClick = {
                    //perform action if can change ability list
                    if(advantageFragVM.getAdvantageChangeable())
                        buttonAction()
                    //notify user of failed change
                    else
                        Toast.makeText(context, R.string.changeAtZero, Toast.LENGTH_LONG).show()
                },
                modifier = Modifier
                    .weight(0.25f)
            ){buttonIcon()}
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
                advantageFragVM.setDetailItem(advantage = advantage)
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
 * @param advantage advantage to display
 * @param advantageFragVM viewModel that holds data for this fragment
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun HeldAdvantageDisplay(
    advantage: Advantage,
    advantageFragVM: AdvantageFragmentViewModel,
    homePageVM: HomePageViewModel
){
    //retrieve additional information on the advantage
    val nameAddition =
        if(advantage.picked != null) {
            //get indicated option name
            if(advantage.picked < stringArrayResource(id = advantage.options!!).size)
                " (${stringArrayResource(id = advantage.options)[advantage.picked]})"
            //otherwise get custom characteristic name
            else
                " (${advantageFragVM.getCustomName(38 - advantage.picked)} - Custom)"
        }
        else null

    //display the advantage row
    AdvantageRow(
        advantage = advantage,
        takenAddition = nameAddition,
        advantageFragVM = advantageFragVM,
        buttonIcon = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Add Advantage"
            )
        }
    ){
        //toggle gift alert if advantage is gift
        if(advantage.name == R.string.gift)
            advantageFragVM.toggleGiftAlertOn()

        //otherwise, simply remove advantage
        else {
            advantageFragVM.removeAdvantage(advantage = advantage)
            homePageVM.updateExpenditures()
        }
    }
}

@Preview
@Composable
fun AdvantagePreview(){
    val charInstance = BaseCharacter()
    charInstance.setOwnRace(1)

    charInstance.advantageRecord.acquireAdvantage(charInstance.objectDB.commonAdvantages.gift, null, 0, null)

    val advantageFragVM = AdvantageFragmentViewModel(charInstance, charInstance.advantageRecord)
    advantageFragVM.advantageButtons[4].toggleOpen()

    val homePageVM = HomePageViewModel(charInstance)

    AdvantageFragment(charInstance.secondaryList, advantageFragVM, homePageVM)
}