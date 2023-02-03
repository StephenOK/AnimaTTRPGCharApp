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

@Composable
fun AdvantageCostPick(
    item: Advantage,
    startPage: Int,
    closeDialog: (String?) -> Unit
){
    val optionPicked = remember{mutableStateOf(item.picked)}
    val costPicked = remember{mutableStateOf(item.pickedCost)}
    val pageNum = remember{mutableStateOf(startPage)}

    Dialog(
        onDismissRequest = {},
        content = {
            Box(
                Modifier
                    .background(Color.White)
                    .size(600.dp, 600.dp)){

                Row(
                    Modifier
                        .align(Alignment.TopCenter)
                        .height(100.dp)
                ){
                    Text(text = "Select Items for Advantage")
                }

                Row(
                    Modifier
                        .align(Alignment.Center)
                        .height(400.dp)
                ){
                    LazyColumn {
                        when (pageNum.value) {
                            1 -> {
                                if (item.options!!.isNotEmpty()) {
                                    item.options.forEach {
                                        item {
                                            Row {
                                                RadioButton(
                                                    selected = optionPicked.value == item.options.indexOf(
                                                        it
                                                    ),
                                                    onClick = {
                                                        optionPicked.value =
                                                            item.options.indexOf(it)
                                                    }
                                                )

                                                Text(text = it)
                                            }
                                        }
                                    }
                                } else {

                                }
                            }

                            2 -> {
                                item.cost.forEach {
                                    item {
                                        Row {
                                            RadioButton(
                                                selected = costPicked.value == item.cost.indexOf(it),
                                                onClick = {
                                                    costPicked.value = item.cost.indexOf(it)
                                                }
                                            )
                                        }
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
                    TextButton(onClick = {
                        when(pageNum.value){
                            1 -> {
                                if(item.cost.size > 1)
                                    pageNum.value = 2
                                else
                                    closeDialog(charInstance.advantageRecord.acquireAdvantage(
                                        item,
                                        optionPicked.value,
                                        costPicked.value
                                    ))
                            }

                            2 ->
                                closeDialog(charInstance.advantageRecord.acquireAdvantage(item, optionPicked.value, costPicked.value))
                        }
                    }) {
                        Text(text = "Select")
                    }

                    TextButton(onClick = {closeDialog(null)}) {
                        Text(text = "Cancel")
                    }
                }
            }
        }
    )
}