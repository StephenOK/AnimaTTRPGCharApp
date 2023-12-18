package com.paetus.animaCharCreator.activities.fragments.dialogs

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SecondaryList
import com.paetus.animaCharCreator.view_models.models.AdvantageFragmentViewModel

/**
 * Dialog that allows the user to choose specific information about their desired advantage.
 * First gives option for the item the advantage affects.
 * Second gives option for the cost of advantage desired.
 * Attempts to apply advantage after choices are made.
 *
 * @param advantageFragVM advantage viewModel passed down to this composable
 * @param closeDialog function to run on dialog's close
 */
@Composable
fun AdvantageCostPick(
    secondaryList: SecondaryList,
    advantageFragVM: AdvantageFragmentViewModel,
    closeDialog: (Int?) -> Unit
){
    //initialize current context
    val context = LocalContext.current

    //initialize page tracker
    val currentPage = advantageFragVM.adjustingPage.collectAsState().value

    DialogFrame(
        //prompt for user selection(s)
        dialogTitle = stringResource(id = R.string.advantageSelectPrompt),
        mainContent = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (currentPage) {
                    //page for type options
                    1 -> {
                        //for each available option
                        context.resources.getStringArray(advantageFragVM.adjustedAdvantage.value!!.options!!)
                            .forEach {option ->
                                //display indicated choices
                                if (advantageFragVM.adjustedAdvantage.value!!.name != R.string.halfTreeAttuned) {
                                    item {
                                        OptionRow(
                                            name = option,
                                            secondaryList = secondaryList,
                                            advantageFragVM = advantageFragVM
                                        )
                                    }
                                }

                                //special choice options for Half-Tree Attuned advantage
                                else
                                    item {
                                        HalfTreeOptions(
                                            name = option,
                                            advantageFragVM = advantageFragVM
                                        )
                                    }
                            }

                        //add custom secondary characteristic options for natural learner and subject aptitude
                        if(advantageFragVM.adjustedAdvantage.value!!.saveTag == "subjectAptitude" ||
                            advantageFragVM.adjustedAdvantage.value!!.saveTag == "naturalLearner"){
                            secondaryList.getAllCustoms().forEach{secondary ->
                                item{
                                    OptionRow(
                                        name = secondary.name.value + " (Custom)",
                                        secondaryList = secondaryList,
                                        advantageFragVM = advantageFragVM
                                    )
                                }
                            }
                        }
                    }

                    //page for cost option
                    2 -> {
                        //make selection for each option
                        advantageFragVM.adjustedAdvantage.value!!.cost.forEach {cost ->
                            item {
                                CostRow(
                                    costVal = cost,
                                    advantageFragVM = advantageFragVM
                                )
                            }
                        }
                    }
                }
            }

            //always close dialog on back press from options selection
            BackHandler(currentPage == 1) {
                closeDialog(null)
            }

            //back options for the cost selection
            BackHandler(currentPage == 2) {
                //return to first page if available
                if (advantageFragVM.adjustedAdvantage.value!!.options != null)
                    advantageFragVM.setAdjustingPage(pageNum = 1)
                //close dialog if no additional options available
                else closeDialog(null)
            }
        },
        buttonContent = {
            //button to go back if option is available
            if (advantageFragVM.adjustingPage.collectAsState().value == 2 &&
                advantageFragVM.adjustedAdvantage.collectAsState().value!!.options != null
            )
                TextButton(
                    onClick = {advantageFragVM.setAdjustingPage(pageNum = 1) }
                ){
                    Text(
                        text = stringResource(id = R.string.backLabel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

            //button to confirm user's selection
            TextButton(
                onClick = {
                    //if selecting option and cost must also be chosen, go to cost selection
                    if(advantageFragVM.adjustingPage.value == 1 &&
                        advantageFragVM.adjustedAdvantage.value!!.cost.size > 1)
                        advantageFragVM.setAdjustingPage(pageNum = 2)
                    //otherwise attempt advantage acquisition
                    else
                        closeDialog(advantageFragVM.acquireAdvantage())
                }
            ){
                Text(
                    text = stringResource(id = R.string.selectLabel),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            //close selection dialog
            TextButton(onClick = {closeDialog(null)}) {
                Text(
                    text = stringResource(id = R.string.cancelLabel),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}

/**
 * Row displaying an option for the selected advantage.
 *
 * @param name description of the current option
 * @param secondaryList secondary characteristic record to retrieve custom characteristic data
 * @param advantageFragVM viewModel for the advantage page
 */
@Composable
private fun OptionRow(
    name: String,
    secondaryList: SecondaryList,
    advantageFragVM: AdvantageFragmentViewModel
) {
    //do not display matrix powers for psychic discipline access
    if(advantageFragVM.adjustedAdvantage.collectAsState().value!!.name == R.string.psyDiscAccess &&
        stringArrayResource(R.array.disciplineNames).indexOf(element = name) == 8)
        return

    //get current context
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth(0.6f),
        verticalAlignment = Alignment.CenterVertically
    ){
        //display a radio button
        RadioButton(
            selected =
                if(stringArrayResource(advantageFragVM.adjustedAdvantage.collectAsState().value!!.options!!).indexOf(element = name) >= 0)
                    advantageFragVM.optionPicked.collectAsState().value == stringArrayResource(advantageFragVM.adjustedAdvantage.collectAsState().value!!.options!!).indexOf(element = name)
                else
                    advantageFragVM.optionPicked.collectAsState().value == 38 + secondaryList.getCustomIndex(name.dropLast(9)),
            onClick = {
                //attempt to get the index of this item
                val input = context.resources.getStringArray(advantageFragVM.adjustedAdvantage.value!!.options!!).indexOf(element = name)

                //if getting a custom item's string
                if(input < 0){
                    //apply added index value
                    advantageFragVM.setOptionPicked(
                        advOption = 38 + secondaryList.getCustomIndex(name.dropLast(9))
                    )
                }
                //otherwise apply index value
                else
                    advantageFragVM.setOptionPicked(advOption = input)
            },
            modifier = Modifier
                .weight(0.1f),
            colors = RadioButtonDefaults.colors(
                unselectedColor = MaterialTheme.colorScheme.onSurface
            )
        )

        //display name of option
        Text(
            text = name,
            modifier = Modifier
                .weight(0.2f)
                .clickable {
                    //attempt to get the index of this item
                    val input = context.resources
                        .getStringArray(advantageFragVM.adjustedAdvantage.value!!.options!!)
                        .indexOf(element = name)

                    //if getting a custom item's string
                    if (input < 0) {
                        //apply added index value
                        advantageFragVM.setOptionPicked(
                            advOption = 38 + secondaryList.getCustomIndex(name.dropLast(9))
                        )
                    }
                    //otherwise apply index value
                    else
                        advantageFragVM.setOptionPicked(advOption = input)
                },
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Composes an option when selecting for the Half-Attuned to Tree advantage.
 *
 * @param name description of the current option
 * @param advantageFragVM viewModel for the advantage page
 */
@Composable
private fun HalfTreeOptions(
    name: String,
    advantageFragVM: AdvantageFragmentViewModel
) {
    //do not display necromancy for this option
    if(stringArrayResource(R.array.elementList).indexOf(name) == 10)
        return

    //get current context
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth(0.6f),
        verticalAlignment = Alignment.CenterVertically
    ){
        //create radio button for this option
        RadioButton(
            selected = advantageFragVM.halfAttunedOptions.collectAsState().value.contains(stringArrayResource(advantageFragVM.adjustedAdvantage.collectAsState().value!!.options!!).indexOf(element = name)),
            onClick = {advantageFragVM.addOptionPicked(context.resources.getStringArray(advantageFragVM.adjustedAdvantage.value!!.options!!).indexOf(element = name))},
            colors = RadioButtonDefaults.colors(
                unselectedColor = MaterialTheme.colorScheme.onSurface
            )
        )

        //display option text
        Text(
            text = name,
            modifier = Modifier
                .weight(0.2f)
                .clickable {
                    advantageFragVM.addOptionPicked(
                        context.resources
                            .getStringArray(advantageFragVM.adjustedAdvantage.value!!.options!!)
                            .indexOf(element = name)
                    )
                },
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Compose a row for a cost option to select.
 *
 * @param costVal cost to display in this row
 * @param advantageFragVM viewModel for the advantage page
 */
@Composable
private fun CostRow(
    costVal: Int,
    advantageFragVM: AdvantageFragmentViewModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //display a radio button
        RadioButton(
            selected = advantageFragVM.costPicked.collectAsState().value == advantageFragVM.adjustedAdvantage.collectAsState().value!!.cost.indexOf(element = costVal),
            onClick = {advantageFragVM.setCostPicked(advantageFragVM.adjustedAdvantage.value!!.cost.indexOf(element = costVal))},
            modifier = Modifier
                .weight(0.1f),
            colors = RadioButtonDefaults.colors(
                unselectedColor = MaterialTheme.colorScheme.onSurface
            )
        )

        //display the cost value
        Text(
            text = costVal.toString(),
            modifier = Modifier
                .weight(0.2f)
        )
    }
}

@Preview
@Composable
fun AdvantageCostPreview(){
    val charInstance = BaseCharacter()

    val advantageFragVM = AdvantageFragmentViewModel(charInstance, charInstance.advantageRecord)
    advantageFragVM.setAdjustedAdvantage(charInstance.advantageRecord.commonAdvantages.naturalPsychicPower)
    //advantageFragVM.setAdjustingPage(2)

    AdvantageCostPick(charInstance.secondaryList, advantageFragVM){}
}