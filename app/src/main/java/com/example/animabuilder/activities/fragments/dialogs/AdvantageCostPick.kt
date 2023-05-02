package com.example.animabuilder.activities.fragments.dialogs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.animabuilder.view_models.models.AdvantageFragmentViewModel

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
    closeDialog: (String?) -> Unit
){
    DialogFrame(
        "Select Items for Advantage",
        {
            LazyColumn {
                when (advantageFragVM.adjustingPage.value) {
                    //page for type options
                    1 -> {
                        //for each available option
                        advantageFragVM.adjustedAdvantage.value!!.options!!.forEach {
                            item {
                                Row {
                                    //display a radio button
                                    RadioButton(
                                        selected = advantageFragVM.optionPicked.collectAsState().value == advantageFragVM.adjustedAdvantage.collectAsState().value!!.options!!.indexOf(it),
                                        onClick = {advantageFragVM.setOptionPicked(advantageFragVM.adjustedAdvantage.value!!.options!!.indexOf(it))}
                                    )

                                    //display name of option
                                    Text(text = it)
                                }
                            }
                        }

                    }

                    //page for cost option
                    2 -> {
                        //for each cost available
                        advantageFragVM.adjustedAdvantage.value!!.cost.forEach {
                            item {
                                Row {
                                    //display a radio button
                                    RadioButton(
                                        selected = advantageFragVM.costPicked.collectAsState().value == advantageFragVM.adjustedAdvantage.collectAsState().value!!.cost.indexOf(it),
                                        onClick = {advantageFragVM.setCostPicked(advantageFragVM.adjustedAdvantage.value!!.cost.indexOf(it))}
                                    )
                                }

                                //display the cost value
                                Text(text = it.toString())
                            }
                        }
                    }
                }
            }
        },
        {
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
                Text(text = "Select")
            }

            //close selection dialog
            TextButton(onClick = {closeDialog(null)}) {
                Text(text = "Cancel")
            }
        }
    )
}