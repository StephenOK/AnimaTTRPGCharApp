package com.example.animabuilder.activities.fragments.dialogs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.animabuilder.view_models.AdvantageFragmentViewModel

/**
 * Dialog that allows the user to choose specific information about their desired advantage
 * First gives option for the type of advantage desired
 * Second gives option for the cost of advantage desired
 *
 * item: base advantage they are adding
 * startPage: page to first display to the user
 * closeDialog: function to run upon dialog's closure
 */

@Composable
fun AdvantageCostPick(
    advantageFragmentVM: AdvantageFragmentViewModel,
    closeDialog: (String?) -> Unit
){
    val item = advantageFragmentVM.adjustedAdvantage.collectAsState().value!!

    DialogFrame(
        "Select Items for Advantage",
        {
            LazyColumn {
                when (advantageFragmentVM.adjustingPage.value) {
                    //page for type options
                    1 -> {
                        //for each available option
                        item.options!!.forEach {
                            item {
                                Row {
                                    //display a radio button
                                    RadioButton(
                                        selected = advantageFragmentVM.optionPicked.collectAsState().value == item.options.indexOf(it),
                                        onClick = {advantageFragmentVM.setOptionPicked(item.options.indexOf(it))}
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
                        item.cost.forEach {
                            item {
                                Row {
                                    //display a radio button
                                    RadioButton(
                                        selected = advantageFragmentVM.costPicked.collectAsState().value == item.cost.indexOf(it),
                                        onClick = {advantageFragmentVM.setCostPicked(item.cost.indexOf(it))}
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
                when(advantageFragmentVM.adjustingPage.value){
                    1 -> {
                        //go to cost page if cost must be chosen
                        if(item.cost.size > 1)
                            advantageFragmentVM.setAdjustingPage(2)
                        //attempt to apply advantage to character
                        else
                            closeDialog(advantageFragmentVM.acquireAdvantage())
                    }

                    2 ->
                        //attempt to apply advantage to character
                        closeDialog(advantageFragmentVM.acquireAdvantage())
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