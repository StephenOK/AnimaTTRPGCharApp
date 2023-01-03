package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.animabuilder.activities.charInstance
import com.example.animabuilder.activities.numberCatcher

/**
 * Fragment that displays the character's summoning abilities
 */
@Composable
fun SummoningFragment(updateFunc: () -> Unit){
    //initialize mutable strings for the page
    val boughtSummon = remember{mutableStateOf(charInstance.summoning.summonBought.toString())}
    val totalSummon = remember{mutableStateOf(charInstance.summoning.summonTotal.toString())}

    val boughtControl = remember{mutableStateOf(charInstance.summoning.controlBought.toString())}
    val totalControl = remember{mutableStateOf(charInstance.summoning.controlTotal.toString())}

    val boughtBind = remember{mutableStateOf(charInstance.summoning.bindBought.toString())}
    val totalBind = remember{mutableStateOf(charInstance.summoning.bindTotal.toString())}

    val boughtBanish = remember{mutableStateOf(charInstance.summoning.banishBought.toString())}
    val totalBanish = remember{mutableStateOf(charInstance.summoning.banishTotal.toString())}

    //initialize display data table
    val summoningRowTable = mutableListOf<SummoningAbilityItem>()

    summoningRowTable.add(SummoningAbilityItem(
        "Summoning",
        charInstance.modPOW.toString(),
        (charInstance.ownClass.summonPerLevel * charInstance.lvl).toString(),
        boughtSummon,
        totalSummon
    ){
        charInstance.summoning.buySummon(it)
        totalSummon.value = charInstance.summoning.summonTotal.toString()
    })

    summoningRowTable.add(SummoningAbilityItem(
        "Control",
        charInstance.modWP.toString(),
        (charInstance.ownClass.controlPerLevel * charInstance.lvl).toString(),
        boughtControl,
        totalControl
    ){
        charInstance.summoning.buyControl(it)
        totalControl.value = charInstance.summoning.controlTotal.toString()
    })

    summoningRowTable.add(SummoningAbilityItem(
        "Bind",
        charInstance.modPOW.toString(),
        (charInstance.ownClass.bindPerLevel * charInstance.lvl).toString(),
        boughtBind,
        totalBind
    ){
        charInstance.summoning.buyBind(it)
        totalBind.value = charInstance.summoning.bindTotal.toString()
    })

    summoningRowTable.add(SummoningAbilityItem(
        "Banish",
        charInstance.modPOW.toString(),
        (charInstance.ownClass.banishPerLevel * charInstance.lvl).toString(),
        boughtBanish,
        totalBanish
    ){
        charInstance.summoning.buyBanish(it)
        totalBanish.value = charInstance.summoning.banishTotal.toString()
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
private fun SummoningAbilityRow(inputData: SummoningAbilityItem, updateFunc: () -> Unit){
    Row(Modifier.fillMaxWidth()){
        //display item, points from modifier, and points from class
        Text(text = inputData.title, textAlign = TextAlign.Center, modifier = Modifier.weight(0.3f))
        Text(text = inputData.modPoints, textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = inputData.classPoints, textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))

        //display points bought and give option to buy points
        TextField(
            value = inputData.boughtVal.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                numberCatcher(
                    it,
                    {input ->
                        inputData.purchaseAct(input.toInt())
                        inputData.boughtVal.value = it
                        charInstance.updateTotalSpent()
                    },
                    {
                        inputData.purchaseAct(0)
                        inputData.boughtVal.value = ""
                        charInstance.updateTotalSpent()
                    }
                )

                updateFunc()
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier.weight(0.3f)
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