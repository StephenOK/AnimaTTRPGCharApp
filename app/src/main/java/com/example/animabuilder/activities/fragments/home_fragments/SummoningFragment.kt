package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.animabuilder.R
import com.example.animabuilder.UserInput
import com.example.animabuilder.character_creation.attributes.summoning.SummonAbility
import com.example.animabuilder.character_creation.attributes.summoning.Summoning

/**
 * Fragment that displays the character's summoning abilities
 */
@Composable
fun SummoningFragment(
    summoning: Summoning,
    updateFunc: () -> Unit
){
    //initialize display data table
    val summoningRowTable = mutableListOf<SummoningAbilityItem>()

    summoningRowTable.add(
        SummoningAbilityItem(
            R.string.summonTitle,
            summoning.summon
        )
    )

    summoningRowTable.add(
        SummoningAbilityItem(
            R.string.controlTitle,
            summoning.control
        )
    )

    summoningRowTable.add(
        SummoningAbilityItem(
            R.string.bindTitle,
            summoning.bind
        )
    )

    summoningRowTable.add(
        SummoningAbilityItem(
            R.string.banishTitle,
            summoning.banish
        )
    )

    LazyColumn{
        //display table header
        item{
            Row(Modifier.fillMaxWidth()){
                Spacer(Modifier.weight(0.3f))
                Text(text = stringResource(R.string.baseLabel), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
                Text(text = stringResource(R.string.classLabel), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
                Text(text = stringResource(R.string.boughtLabel), textAlign = TextAlign.Center, modifier = Modifier.weight(0.3f))
                Text(text = stringResource(R.string.totalLabel), textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
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
    val boughtVal = remember{mutableStateOf(inputData.ability.buyVal.toString())}
    val totalVal = remember{mutableStateOf(inputData.ability.abilityTotal.toString())}

    Row(Modifier.fillMaxWidth()){
        //display item, points from modifier, and points from class
        Text(text = stringResource(inputData.title), textAlign = TextAlign.Center, modifier = Modifier.weight(0.3f))
        Text(text = inputData.ability.modVal.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = inputData.ability.levelTotal.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))

        //display points bought and give option to buy points
        UserInput(
            boughtVal.value,
            {},
            {input ->
                inputData.ability.setBuyVal(input.toInt())
                boughtVal.value = input
            },
            {
                inputData.ability.setBuyVal(0)
                boughtVal.value = ""
            },
            {
                totalVal.value = inputData.ability.abilityTotal.toString()
                updateFunc()
            },
            Modifier.weight(0.3f)
        )

        //display final total
        Text(text = totalVal.value, textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
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
    val title: Int,
    val ability: SummonAbility
)