package com.example.animabuilder.activities.fragments.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.animabuilder.activities.charInstance
import com.example.animabuilder.character_creation.attributes.advantages.advantage_types.Advantage

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
    item: Advantage,
    startPage: Int,
    closeDialog: (String?) -> Unit
){
    //initialize radio button trackers
    val optionPicked = remember{mutableStateOf(item.picked)}
    val costPicked = remember{mutableStateOf(item.pickedCost)}

    //initialize page tracker
    val pageNum = remember{mutableStateOf(startPage)}

    Dialog(
        onDismissRequest = {},
        content = {
            Box(
                Modifier
                    .background(Color.White)
                    .size(600.dp, 600.dp)){

                //title row
                Row(
                    Modifier
                        .align(Alignment.TopCenter)
                        .height(100.dp)
                ){
                    Text(text = "Select Items for Advantage")
                }

                //display page
                Row(
                    Modifier
                        .align(Alignment.Center)
                        .height(400.dp)
                ){
                    LazyColumn {
                        when (pageNum.value) {
                            //page for type options
                            1 -> {
                                //for each available option
                                item.options!!.forEach {
                                    item {
                                        Row {
                                            //display a radio button
                                            RadioButton(
                                                selected = optionPicked.value == item.options.indexOf(it),
                                                onClick = {optionPicked.value = item.options.indexOf(it)}
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
                                                selected = costPicked.value == item.cost.indexOf(it),
                                                onClick = {costPicked.value = item.cost.indexOf(it)}
                                            )
                                        }

                                        //display the cost value
                                        Text(text = it.toString())
                                    }
                                }
                            }
                        }
                    }
                }

                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .height(100.dp)
                ){
                    //button for user's item selection
                    TextButton(onClick = {
                        when(pageNum.value){
                            1 -> {
                                //go to cost page if cost must be chosen
                                if(item.cost.size > 1)
                                    pageNum.value = 2
                                //attempt to apply advantage to character
                                else
                                    closeDialog(charInstance.advantageRecord.acquireAdvantage(
                                        item,
                                        optionPicked.value,
                                        costPicked.value
                                    ))
                            }

                            2 ->
                                //attempt to apply advantage to character
                                closeDialog(charInstance.advantageRecord.acquireAdvantage(item, optionPicked.value, costPicked.value))
                        }
                    }) {
                        Text(text = "Select")
                    }

                    //close selection dialog
                    TextButton(onClick = {closeDialog(null)}) {
                        Text(text = "Cancel")
                    }
                }
            }
        }
    )
}