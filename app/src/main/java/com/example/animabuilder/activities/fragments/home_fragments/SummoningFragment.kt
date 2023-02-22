package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.animabuilder.UserInput
import com.example.animabuilder.character_creation.attributes.summoning.Summoning

/**
 * Fragment that displays the character's summoning abilities
 */
@Composable
fun SummoningFragment(
    summoning: Summoning,
    updateFunc: () -> Unit
){
    //initialize mutable strings for the page
    val boughtSummon = remember{mutableStateOf(summoning.summon.buyVal.toString())}
    val totalSummon = remember{mutableStateOf(summoning.summon.abilityTotal.toString())}

    val boughtControl = remember{mutableStateOf(summoning.control.buyVal.toString())}
    val totalControl = remember{mutableStateOf(summoning.control.abilityTotal.toString())}

    val boughtBind = remember{mutableStateOf(summoning.bind.buyVal.toString())}
    val totalBind = remember{mutableStateOf(summoning.bind.abilityTotal.toString())}

    val boughtBanish = remember{mutableStateOf(summoning.banish.buyVal.toString())}
    val totalBanish = remember{mutableStateOf(summoning.banish.abilityTotal.toString())}

    //initialize display data table
    val summoningRowTable = mutableListOf<SummoningAbilityItem>()

    summoningRowTable.add(SummoningAbilityItem(
        "Summoning",
        summoning.summon.modVal.toString(),
        summoning.summon.levelTotal.toString(),
        boughtSummon,
        totalSummon
    ){
        summoning.summon.setBuyVal(it)
        totalSummon.value = summoning.summon.abilityTotal.toString()
    })

    summoningRowTable.add(SummoningAbilityItem(
        "Control",
        summoning.control.modVal.toString(),
        summoning.control.levelTotal.toString(),
        boughtControl,
        totalControl
    ){
        summoning.control.setBuyVal(it)
        totalControl.value = summoning.control.abilityTotal.toString()
    })

    summoningRowTable.add(SummoningAbilityItem(
        "Bind",
        summoning.bind.modVal.toString(),
        summoning.bind.levelTotal.toString(),
        boughtBind,
        totalBind
    ){
        summoning.bind.setBuyVal(it)
        totalBind.value = summoning.bind.abilityTotal.toString()
    })

    summoningRowTable.add(SummoningAbilityItem(
        "Banish",
        summoning.banish.modVal.toString(),
        summoning.banish.levelTotal.toString(),
        boughtBanish,
        totalBanish
    ){
        summoning.banish.setBuyVal(it)
        totalBanish.value = summoning.banish.abilityTotal.toString()
    })

    LazyColumn{
        //display table header
        item{
            Row(Modifier.fillMaxWidth()){
                Spacer(Modifier.weight(0.3f))
                Text(text = "Base", textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
                Text(text = "Class", textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
                Text(text = "Bought", textAlign = TextAlign.Center, modifier = Modifier.weight(0.3f))
                Text(text = "Total", textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
            }
        }

        //display table data
        items(summoningRowTable){
            SummoningAbilityRow(it, updateFunc)
        }
    }
}

/**
 * Creates a row of data that displays information on the given ability of the character
 *
 * inputData: table data to display to the user
 * updateFunc: function to update the bottom bar
 */
@Composable
private fun SummoningAbilityRow(
    inputData: SummoningAbilityItem,
    updateFunc: () -> Unit
){
    Row(Modifier.fillMaxWidth()){
        //display item, points from modifier, and points from class
        Text(text = inputData.title, textAlign = TextAlign.Center, modifier = Modifier.weight(0.3f))
        Text(text = inputData.modPoints, textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = inputData.classPoints, textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))

        //display points bought and give option to buy points
        UserInput(
            inputData.boughtVal,
            {},
            {input ->
                inputData.purchaseAct(input.toInt())
                inputData.boughtVal.value = input
            },
            {
                inputData.purchaseAct(0)
                inputData.boughtVal.value = ""
            },
            {updateFunc()},
            Modifier.weight(0.3f)
        )

        //display final total
        Text(text = inputData.abilityTotal.value, textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
    }
}

/**
 * Holds data to be displayed on a particular summoning ability of a character
 *
 * title: name of the ability
 * modPoints: points acquired from the character's primary characteristic modifier
 * classPoints: points acquired from the character's class
 * boughtVal: points purchased for the character by the user
 * abilityTotal: final total of the ability's value
 * purchaseAct: function to run on a change in the boughtVal value
 */
data class SummoningAbilityItem(
    val title: String,
    val modPoints: String,
    val classPoints: String,
    val boughtVal: MutableState<String>,
    val abilityTotal: MutableState<String>,
    val purchaseAct: (Int) -> Unit
)