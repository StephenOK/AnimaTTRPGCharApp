package com.example.animabuilder.activities.fragments.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import com.example.animabuilder.character_creation.attributes.advantages.Advantage

@Composable
fun AdvantageCostPick(
    item: Advantage,
    startPage: Int,
    closeDialog: () -> Unit
){
    val optionPicked = remember{mutableStateOf(0)}
    val costPicked = remember{mutableStateOf(0)}
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
                    when(pageNum.value){
                        1 ->{
                            if(item.options!!.isNotEmpty()){
                                item.options.forEach{
                                    RadioButton(
                                        selected = optionPicked.value == item.options.indexOf(it),
                                        onClick = {optionPicked.value = item.options.indexOf(it)}
                                    )
                                    Text(text = it)
                                }
                            }
                            else{
                                val psychicPowerList = charInstance.psychic.telepathy.allPowers +
                                        charInstance.psychic.psychokinesis.allPowers +
                                        charInstance.psychic.pyrokinesis.allPowers +
                                        charInstance.psychic.cryokinesis.allPowers +
                                        charInstance.psychic.physicalIncrease.allPowers +
                                        charInstance.psychic.energyPowers.allPowers +
                                        charInstance.psychic.sentiencePowers.allPowers +
                                        charInstance.psychic.telemetry.allPowers

                                psychicPowerList.forEach{
                                    RadioButton(
                                        selected = optionPicked.value == psychicPowerList.indexOf(it),
                                        onClick = {optionPicked.value = psychicPowerList.indexOf(it)}
                                    )
                                    Text(text = it.name)
                                }
                            }
                        }

                        2 ->{
                            item.cost.forEach{
                                RadioButton(
                                    selected = costPicked.value == item.cost.indexOf(it),
                                    onClick = {costPicked.value = item.cost.indexOf(it)}
                                )
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
                                else {
                                    charInstance.advantageRecord.acquireAdvantage(
                                        item,
                                        optionPicked.value,
                                        costPicked.value
                                    )
                                    closeDialog()
                                }
                            }

                            2 -> {
                                charInstance.advantageRecord.acquireAdvantage(item, optionPicked.value, costPicked.value)
                                closeDialog()
                            }
                        }
                    }) {
                        Text(text = "Select")
                    }

                    TextButton(onClick = {closeDialog()}) {
                        Text(text = "Cancel")
                    }
                }
            }
        }
    )
}