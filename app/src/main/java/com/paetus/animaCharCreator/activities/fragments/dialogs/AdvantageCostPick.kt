package com.paetus.animaCharCreator.activities.fragments.dialogs

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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
import com.paetus.animaCharCreator.view_models.models.AdvantageFragmentViewModel

/**
 * Dialog that allows the user to choose specific information about their desired advantage.
 * First gives option for the type of advantage desired.
 * Second gives option for the cost of advantage desired.
 * Attempts to apply advantage after choices are made.
 *
 * @param advantageFragVM advantage viewModel passed down to this composable
 * @param closeDialog function to run on dialog's close
 */

@Composable
fun AdvantageCostPick(
    advantageFragVM: AdvantageFragmentViewModel,
    closeDialog: (Int?) -> Unit
){
    //initialize current context
    val context = LocalContext.current

    //initialize page tracker
    val currentPage = advantageFragVM.adjustingPage.collectAsState().value

    DialogFrame(
        stringResource(R.string.advantageSelectPrompt),
        {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                when (currentPage) {
                    //page for type options
                    1 -> {
                        //for each available option
                        context.resources.getStringArray(advantageFragVM.adjustedAdvantage.value!!.options!!).forEach {
                            if(advantageFragVM.adjustedAdvantage.value!!.name != R.string.halfTreeAttuned)
                                item{OptionRow(advantageFragVM, it)}
                            else
                                item{HalfTreeOptions(advantageFragVM, it)}
                        }
                    }

                    //page for cost option
                    2 -> {
                        //for each cost available
                        advantageFragVM.adjustedAdvantage.value!!.cost.forEach {
                            item{CostRow(advantageFragVM, it)}
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
                //return to first page if multiples options
                if(advantageFragVM.adjustedAdvantage.value!!.options != null)
                    advantageFragVM.setAdjustingPage(1)
                //close dialog if no additional options available
                else closeDialog(null)
            }
        },
        {
            //button to go back if option is available
            if(advantageFragVM.adjustingPage.collectAsState().value == 2 &&
                    advantageFragVM.adjustedAdvantage.collectAsState().value!!.options != null)
                TextButton(onClick = {
                    advantageFragVM.setAdjustingPage(1)
                }) {
                    Text(text = stringResource(R.string.backLabel))
                }

            //button for user's item selection
            TextButton(onClick = {
                when(advantageFragVM.adjustingPage.value){
                    1 -> {
                        //go to cost page if cost must be chosen
                        if(advantageFragVM.adjustedAdvantage.value!!.cost.size > 1)
                            advantageFragVM.setAdjustingPage(2)
                        //attempt to apply advantage to character
                        else
                            closeDialog(advantageFragVM.acquireAdvantage())
                    }

                    2 ->
                        //attempt to apply advantage to character
                        closeDialog(advantageFragVM.acquireAdvantage())
                }
            }) {
                Text(text = stringResource(R.string.selectLabel))
            }

            //close selection dialog
            TextButton(onClick = {closeDialog(null)}) {
                Text(text = stringResource(R.string.cancelLabel))
            }
        }
    )
}

@Composable
private fun OptionRow(
    advantageFragVM: AdvantageFragmentViewModel,
    name: String
) {
    if(advantageFragVM.adjustedAdvantage.collectAsState().value!!.name == R.string.psyDiscAccess &&
        stringArrayResource(R.array.disciplineNames).indexOf(name) == 8)
        return

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth(0.6f),
        verticalAlignment = Alignment.CenterVertically
    ){
        //display a radio button
        RadioButton(
            selected = advantageFragVM.optionPicked.collectAsState().value == stringArrayResource(advantageFragVM.adjustedAdvantage.collectAsState().value!!.options!!).indexOf(name),
            onClick = {advantageFragVM.setOptionPicked(context.resources.getStringArray(advantageFragVM.adjustedAdvantage.value!!.options!!).indexOf(name))},
            modifier = Modifier
                .weight(0.1f)
        )

        //display name of option
        Text(
            text = name,
            modifier = Modifier
                .weight(0.2f)
                .clickable {
                    advantageFragVM.setOptionPicked(
                        context.resources
                            .getStringArray(advantageFragVM.adjustedAdvantage.value!!.options!!)
                            .indexOf(
                                name
                            )
                    )
                },
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun HalfTreeOptions(
    advantageFragVM: AdvantageFragmentViewModel,
    name: String
) {
    if(stringArrayResource(R.array.elementList).indexOf(name) == 10)
        return

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth(0.6f),
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(
            selected = advantageFragVM.halfAttunedOptions.collectAsState().value.contains(stringArrayResource(advantageFragVM.adjustedAdvantage.collectAsState().value!!.options!!).indexOf(name)),
            onClick = {advantageFragVM.addOptionPicked(context.resources.getStringArray(advantageFragVM.adjustedAdvantage.value!!.options!!).indexOf(name))}
        )

        Text(
            text = name,
            modifier = Modifier
                .weight(0.2f)
                .clickable {
                    advantageFragVM.addOptionPicked(
                        context.resources
                            .getStringArray(advantageFragVM.adjustedAdvantage.value!!.options!!)
                            .indexOf(name)
                    )
                },
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun CostRow(
    advantageFragVM: AdvantageFragmentViewModel,
    item: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //display a radio button
        RadioButton(
            selected = advantageFragVM.costPicked.collectAsState().value == advantageFragVM.adjustedAdvantage.collectAsState().value!!.cost.indexOf(item),
            onClick = {advantageFragVM.setCostPicked(advantageFragVM.adjustedAdvantage.value!!.cost.indexOf(item))},
            modifier = Modifier
                .weight(0.1f)
        )

        //display the cost value
        Text(
            text = item.toString(),
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

    AdvantageCostPick(advantageFragVM){}
}