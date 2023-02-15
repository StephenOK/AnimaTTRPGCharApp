package com.example.animabuilder.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.animabuilder.DetailButton
import com.example.animabuilder.InfoRow
import com.example.animabuilder.UserInput
import com.example.animabuilder.activities.*
import com.example.animabuilder.activities.fragments.dialogs.CustomTechnique
import com.example.animabuilder.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.Technique

/**
 * Fragment that displays items related to martial knowledge
 * Ki points and accumulations can be acquired here
 * Ki abilities are acquirable
 * Dominion Techniques are taken and created in this page
 */

@Composable
fun KiFragment(
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
) {
    //get fragment's context
    val context = LocalContext.current

    //prepare remaining MK display
    val remainingMK = remember{mutableStateOf(charInstance.ki.martialKnowledgeRemaining.toString())}

    //prepare lists of ki abilities and techniques
    val allKiAbilities = mutableListOf<Pair<KiAbility, MutableState<Boolean>>>()
    val allTechniques = mutableListOf<Pair<Technique, MutableState<Boolean>>>()

    //prepare technique list open state
    val techListOpen = remember{mutableStateOf(false)}

    //prepare ki point and accumulation displays
    val kiPointTotal = remember{mutableStateOf(charInstance.ki.totalKi.toString())}
    val kiAccTotal = remember{mutableStateOf(charInstance.ki.totalAcc.toString())}

    //initialize ki ability list's open state
    val kiListOpen = remember{mutableStateOf(false)}

    //prepare custom technique dialog open state
    val customTechOn = remember{mutableStateOf(false)}

    val kiRowTable = mutableListOf<KiRowData>()
    kiRowTable.add(KiRowData(
        "STR",
        charInstance.totalSTR,
        charInstance.ki.boughtStrPoint,
        charInstance.ki.setBoughtStr,
        charInstance.ki.boughtStrAcc,
        charInstance.ki.setStrAcc,
        charInstance.ki.kiSTR,
        charInstance.ki.accSTR
    ))
    kiRowTable.add(KiRowData(
        "DEX",
        charInstance.totalDEX,
        charInstance.ki.boughtDexPoint,
        charInstance.ki.setBoughtDex,
        charInstance.ki.boughtDexAcc,
        charInstance.ki.setDexAcc,
        charInstance.ki.kiDEX,
        charInstance.ki.accDEX
    ))
    kiRowTable.add(KiRowData(
        "AGI",
        charInstance.totalAGI,
        charInstance.ki.boughtAgiPoint,
        charInstance.ki.setBoughtAgi,
        charInstance.ki.boughtAgiAcc,
        charInstance.ki.setAgiAcc,
        charInstance.ki.kiAGI,
        charInstance.ki.accAGI
    ))
    kiRowTable.add(KiRowData(
        "CON",
        charInstance.totalCON,
        charInstance.ki.boughtConPoint,
        charInstance.ki.setBoughtCon,
        charInstance.ki.boughtConAcc,
        charInstance.ki.setConAcc,
        charInstance.ki.kiCON,
        charInstance.ki.accCON
    ))
    kiRowTable.add(KiRowData(
        "POW",
        charInstance.totalPOW,
        charInstance.ki.boughtPowPoint,
        charInstance.ki.setBoughtPow,
        charInstance.ki.boughtPowAcc,
        charInstance.ki.setPowAcc,
        charInstance.ki.kiPOW,
        charInstance.ki.accPOW
    ))
    kiRowTable.add(KiRowData(
        "WP",
        charInstance.totalWP,
        charInstance.ki.boughtWpPoint,
        charInstance.ki.setBoughtWp,
        charInstance.ki.boughtWpAcc,
        charInstance.ki.setWpAcc,
        charInstance.ki.kiWP,
        charInstance.ki.accWP
    ))

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ){
        //display martial knowledge values
        item{InfoRow("Max Martial Knowledge: ", charInstance.ki.martialKnowledgeMax.toString())}
        item{InfoRow("Martial Knowledge Remaining: ", remainingMK.value)}

        //header for ki point and accumulation table
        item {
            Row {
                Spacer(modifier = Modifier.weight(0.13f))
                Text(text = "Stat Ki", modifier = Modifier.weight(0.13f))
                Text(text = "Buy Ki", modifier = Modifier.weight(0.13f))
                Text(text = "Ki Total", modifier = Modifier.weight(0.13f))
                Text(text = "Stat Acc", modifier = Modifier.weight(0.13f))
                Text(text = "Buy Acc", modifier = Modifier.weight(0.13f))
                Text(text = "Acc Total", modifier = Modifier.weight(0.13f))
            }
        }

        //display a ki row for each stat
        items(kiRowTable) {kiRowInput ->
            KiFromStatRow(
                kiRowInput,
                updateFunc,
                { input: String -> kiPointTotal.value = input}
            ) { input: String -> kiAccTotal.value = input }
        }

        //total ki points and accumulation
        item {
            Row {
                Text(text = "Totals:", modifier = Modifier.weight(0.13f))

                Spacer(Modifier.weight(0.26f))
                Text(
                    text = kiPointTotal.value,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.13f)
                )

                Spacer(Modifier.weight(0.26f))
                Text(
                    text = kiAccTotal.value,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.13f)
                )
            }
        }

        //button to display ki abilities
        item {
            Button(
                onClick = {
                    kiListOpen.value = !kiListOpen.value
                },
                modifier = Modifier.width(250.dp)
            ) {
                Text(text = "Ki Abilities")
            }
        }

        //ki ability display
        item {
            AnimatedVisibility(visible = kiListOpen.value) {
                Column {
                    charInstance.ki.kiRecord.allKiAbilities.forEach {
                        KiAbilityRow(
                            it,
                            allKiAbilities,
                            {
                            remainingMK.value =
                                charInstance.ki.martialKnowledgeRemaining.toString()
                            },
                            techListOpen.value,
                            openDetailAlert
                        ) { techListOpen.value = false }
                    }
                }
            }
        }

        //button to display dominion techniques
        item {
            Button(
                onClick = {
                    //check that character can take a dominion technique
                    if (charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.kiControl))
                        techListOpen.value = !techListOpen.value
                    //notify user of invalid action
                    else
                        Toast.makeText(
                            context,
                            "You need Ki Control for Dominion Techniques",
                            Toast.LENGTH_SHORT
                        ).show()
                },
                modifier = Modifier.width(250.dp)
            ) {
                Text(text = "Dominion Techniques")
            }
        }

        //technique display
        item {
            AnimatedVisibility(visible = techListOpen.value) {
                Column {
                    //display each prebuilt technique
                    charInstance.ki.allTechniques.forEach {
                        TechniqueRow(it, allTechniques, openDetailAlert) {
                            remainingMK.value =
                                charInstance.ki.martialKnowledgeRemaining.toString()
                        }
                    }

                    //button for custom technique creation
                    Button(
                        onClick = {
                            customTechOn.value = true
                        }
                    ) {
                        Text(text = "Add Technique")
                    }

                    //display custom techniques
                    charInstance.ki.customTechniques.forEach {
                        TechniqueRow(it, allTechniques, openDetailAlert) {
                            remainingMK.value =
                                charInstance.ki.martialKnowledgeRemaining.toString()
                        }
                    }
                }
            }
        }
    }

    //alert for custom technique creation
    if(customTechOn.value)
        CustomTechnique(TechContents) { customTechOn.value = false }
}

/**
 * Creates a display row for ki point and accumulation purchases
 *
 * kiRowData: data specific to this row's stat
 * updateFunc: bottom bar update function
 * changePointDisplay: function that hoists the total ki point string
 * changeAccDisplay: function that hoists the total accumulation string
 */
@Composable
private fun KiFromStatRow(
    kiRowData: KiRowData,
    updateFunc: () -> Unit,
    changePointDisplay: (String) -> Unit,
    changeAccDisplay: (String) -> Unit
){
    //initialize displays with bought points and accumulation
    val pointString = remember{ mutableStateOf(kiRowData.boughtPoint.toString()) }
    val accString = remember{ mutableStateOf(kiRowData.boughtAcc.toString()) }

    //initialize total displays
    val pointTotalString = remember{ mutableStateOf(kiRowData.pointTotal.toString()) }
    val accTotalString = remember{mutableStateOf(kiRowData.accTotal.toString())}

    Row{
        //display stat name
        Text(text = kiRowData.title, textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        //get stat's inherit ki points
        Text(text = charInstance.ki.getStatKi(kiRowData.statVal).toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        //ki points purchased input
        UserInput(
            pointString,
            {},
            {input: String ->
                kiRowData.pointUpdate(input.toInt(), pointTotalString, changePointDisplay)
                pointString.value = input
                updateFunc()},
            {kiRowData.pointUpdate(0, pointTotalString, changePointDisplay)
                pointString.value = ""},
            {},
            Modifier
        )

        //display for ki points from this stat
        Text(text = pointTotalString.value, textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        //get stat's inherit accumulation
        Text(text = charInstance.ki.getStatKiAcc(kiRowData.statVal).toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        //ki accumulation purchased input
        UserInput(
            accString,
            {},
            {input: String ->
                kiRowData.accUpdate(input.toInt(), accTotalString, changeAccDisplay)
                accString.value = input
                updateFunc()},
            {kiRowData.accUpdate(0, accTotalString, changeAccDisplay)
                accString.value = ""},
            {},
            Modifier
        )

        //display for ki accumulation from this stat
        Text(text = accTotalString.value, textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))
    }
}

/**
 * Gives a row for a character to take a Ki Ability
 *
 * ability: ki ability to display in this row
 * allKiAbilities: master list of ki ability checkboxes
 * updateMKRemaining: function to change the displayed martial knowledge remaining
 * techListOpen: open state of technique list
 * closeList: function to close the technique list
 */
@Composable
private fun KiAbilityRow(
    ability: KiAbility,
    allKiAbilities: MutableList<Pair<KiAbility, MutableState<Boolean>>>,
    updateMKRemaining: () -> Unit,
    techListOpen: Boolean,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    closeList: () -> Unit
){
    //get taken state of ki ability
    val abilityTaken = remember{mutableStateOf(charInstance.ki.takenAbilities.contains(ability))}

    //add checkbox to master list
    allKiAbilities += Pair(ability, abilityTaken)

    Row(verticalAlignment = Alignment.CenterVertically){
        //taken checkbox
        Checkbox(
            checked = abilityTaken.value,
            onCheckedChange = {
                //user attempts to take
                if(it){
                    //accept if all prerequisites are met
                    if(ability.prerequisites == null ||
                        charInstance.ki.takenAbilities.contains(ability.prerequisites))
                        abilityTaken.value = charInstance.ki.attemptAbilityAdd(ability)
                }
                //user attempts to remove
                else{
                    //remove ability and change checkbox accordingly
                    abilityTaken.value = false
                    charInstance.ki.removeAbility(ability)
                    updateKiTaken(allKiAbilities, techListOpen, closeList)
                }

                //update martial knowledge text
                updateMKRemaining()
            },
            modifier = Modifier.weight(0.1f)
        )

        //display ki ability name and cost
        Text(text = ability.name, modifier = Modifier.weight(0.5f))
        Text(text = ability.mkCost.toString(), modifier = Modifier.weight(0.2f))

        //button to display ki ability details
        DetailButton(
            onClick = {openDetailAlert(ability.name) @Composable { KiContents(ability) } },
            modifier = Modifier.weight(0.2f)
        )
    }
}

/**
 * Updates the ki ability checkboxes to check if the character still possesses them
 *
 * allKiAbilities: master list of ki ability checks
 * techListOpen: open state of the technique list
 * closeList: function to close technique list
 */
private fun updateKiTaken(
    allKiAbilities: MutableList<Pair<KiAbility, MutableState<Boolean>>>,
    techListOpen: Boolean,
    closeList: () -> Unit
) {
    //for each ki ability checkbox
    allKiAbilities.forEach{
        //check if the ability is still held by the character
        if(charInstance.ki.takenAbilities.contains(it.first) != it.second.value)
            it.second.value = false
    }

    //close technique list if ki control is no longer present
    if(techListOpen && !charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.kiControl))
        closeList()
}

/**
 * Displays a prebuilt technique the user can add to their character
 *
 * toShow: technique associated with the row
 * allTechniques: master list of the shown techniques
 * updateMKRemaining: function to update the displayed martial knowledge available
 */
@Composable
private fun TechniqueRow(
    toShow: Technique,
    allTechniques: MutableList<Pair<Technique, MutableState<Boolean>>>,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateMKRemaining: () -> Unit
) {
    //instantiate checkbox boolean
    val techCheck = remember{mutableStateOf(charInstance.ki.takenTechniques.contains(toShow))}

    //add boolean and associated technique to the master list
    allTechniques += Pair(toShow, techCheck)

    Row{
        //checkbox to apply technique to the character
        Checkbox(
            checked = techCheck.value,
            onCheckedChange ={
                //if user attempts to take technique
                if(it) {
                    //give if applicable
                    if(charInstance.ki.addTechnique(toShow))
                        techCheck.value = true
                }
                //if user attempts to remove technique
                else{
                    //remove technique from character and change checkbox
                    charInstance.ki.removeTechnique(toShow)
                    techCheck.value = false
                    updateTechTaken(allTechniques)
                }

                //update the martial knowledge display
                updateMKRemaining()
            }
        )

        //show technique's name, cost, and level
        Text(text = toShow.name)
        Text(text = toShow.mkCost().toString() + " MK")
        Text(text = toShow.level.toString())

        //give button to display technique's details
        DetailButton(
            onClick = {openDetailAlert(toShow.name) @Composable {TechContents(toShow)}},
            modifier = Modifier
        )
    }
}

/**
 * Changes checkboxes to show whether they are taken by the character
 *
 * allTechniques: master list of techniques and associated checkboxes
 */
private fun updateTechTaken(allTechniques: MutableList<Pair<Technique, MutableState<Boolean>>>) {
    //check each technique checkbox
    allTechniques.forEach{
        //make sure character still has the checked technique
        if(charInstance.ki.takenTechniques.contains(it.first) != it.second.value)
            it.second.value = false
    }
}

//detail composable for a ki ability
val KiContents = @Composable
{ability: KiAbility ->
    Column{
        val preString =
            if(ability.prerequisites != null)
                ability.prerequisites.name
            else
                "null"

        InfoRow("Prerequisite:", preString)
        Text(text = ability.description)
    }
}

//detail composable for a technique
val TechContents = @Composable
{technique: Technique ->
    Column {
        technique.givenAbilities.forEach {
            Row { Text(text = it.name + " " + it.effect) }
        }

        if (technique.isMaintained()){
            Row {
                Text(text = "Maintenance: ")
                for(index in 0..5){
                    if(technique.maintArray[index] != 0)
                        Text(text = technique.maintArray[index].toString() + " (" + getStatName(index) + ")")
                }
            }
        }

        val kiBuilds = technique.statSpent()

        for(index in 0..5){
            if(kiBuilds[index] > 0)
                InfoRow(getStatName(index), kiBuilds[index].toString())
        }

        InfoRow("Total Accumulation: ", technique.accTotal().toString())

        Text(text = technique.description)
    }
}

//function for getting the index's associated primary characteristic
private fun getStatName(label: Int): String{
    return when(label){
        0 -> "STR"
        1 -> "DEX"
        2 -> "AGI"
        3 -> "CON"
        4 -> "POW"
        5 -> "WP"
        else -> ""
    }
}

/**
 * Data object for a ki ability row
 *
 * title: name of the ki ability
 * statVal: characteristic point's value
 * boughtPoint: points bought in this stat
 * pointUpdate: function to run for updating ki points
 * boughtAcc: points bought in this stat's accumulation
 * accUpdate: function to run for updating ki accumulation
 * pointTotal: total ki points from the associated stat
 * accTotal: total ki accumulation from the associated stat
 */
private data class KiRowData(
    val title: String,
    val statVal: Int,
    val boughtPoint: Int,
    val pointUpdate: (Int, MutableState<String>, (String) -> Unit) -> Unit,
    val boughtAcc: Int,
    val accUpdate: (Int, MutableState<String>, (String) -> Unit) -> Unit,
    val pointTotal: Int,
    val accTotal: Int
)