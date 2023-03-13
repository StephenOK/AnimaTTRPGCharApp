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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.animabuilder.DetailButton
import com.example.animabuilder.InfoRow
import com.example.animabuilder.NumberInput
import com.example.animabuilder.activities.fragments.dialogs.CustomTechnique
import com.example.animabuilder.character_creation.attributes.ki_abilities.Ki
import com.example.animabuilder.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.Technique
import com.example.animabuilder.view_models.CustomTechniqueViewModel
import com.example.animabuilder.view_models.KiFragmentViewModel

/**
 * Fragment that displays items related to martial knowledge
 * Ki points and accumulations can be acquired here
 * Ki abilities are acquirable
 * Dominion Techniques are taken and created in this page
 */

@Composable
fun KiFragment(
    ki: Ki,
    kiFragVM: KiFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
) {
    //get fragment's context
    val context = LocalContext.current

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ){
        //display martial knowledge values
        item{InfoRow("Max Martial Knowledge: ", kiFragVM.getMartialMax())}
        item{InfoRow("Martial Knowledge Remaining: ", kiFragVM.remainingMK.collectAsState().value)}

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
        items(kiFragVM.allRowData) {kiRowInput ->
            KiFromStatRow(
                kiRowInput,
                updateFunc
            )
        }

        //total ki points and accumulation
        item {
            Row {
                Text(text = "Totals:", modifier = Modifier.weight(0.13f))

                Spacer(Modifier.weight(0.26f))
                Text(
                    text = kiFragVM.kiPointTotal.collectAsState().value,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.13f)
                )

                Spacer(Modifier.weight(0.26f))
                Text(
                    text = kiFragVM.kiAccTotal.collectAsState().value,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.13f)
                )
            }
        }

        //button to display ki abilities
        item {
            Button(
                onClick = {
                    kiFragVM.setKiListOpen(!kiFragVM.kiListOpen.value)
                },
                modifier = Modifier.width(250.dp)
            ) {
                Text(text = "Ki Abilities")
            }
        }

        //ki ability display
        item {
            AnimatedVisibility(visible = kiFragVM.kiListOpen.collectAsState().value) {
                Column {
                    kiFragVM.getAllKiAbilities().forEach {
                        KiAbilityRow(
                            kiFragVM,
                            it,
                            openDetailAlert
                        )
                    }
                }
            }
        }

        //button to display dominion techniques
        item {
            Button(
                onClick = {
                    //check that character can take a dominion technique
                    if (!kiFragVM.setTechListOpen(!kiFragVM.techListOpen.value))
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
            AnimatedVisibility(visible = kiFragVM.techListOpen.collectAsState().value) {
                Column {
                    //display each prebuilt technique
                    kiFragVM.getAllTechniques().forEach {
                        TechniqueRow(kiFragVM, it, openDetailAlert)
                    }

                    //button for custom technique creation
                    Button(
                        onClick = {
                            kiFragVM.setCustomTechOpen(true)
                        }
                    ) {
                        Text(text = "Add Technique")
                    }

                    //display custom techniques
                    kiFragVM.getCustomTechniques().forEach {
                        TechniqueRow(kiFragVM, it, openDetailAlert)
                    }
                }
            }
        }
    }

    //alert for custom technique creation
    if(kiFragVM.customTechOpen.collectAsState().value)
        CustomTechnique(ki, CustomTechniqueViewModel(context, kiFragVM), TechContents)
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
    kiRowData: KiFragmentViewModel.KiRowData,
    updateFunc: () -> Unit
){
    Row{
        //display stat name
        Text(text = stringResource(kiRowData.title), textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        //get stat's inherit ki points
        Text(text = kiRowData.item.baseKiPoints.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        //ki points purchased input
        NumberInput(
            kiRowData.pointInputString.collectAsState().value,
            {},
            {input: String ->
                kiRowData.setPointInputString(input.toInt())
                updateFunc()
            },
            {kiRowData.setPointInputString("")},
            {},
            Color.Black,
            Modifier.weight(0.13f)
        )

        //display for ki points from this stat
        Text(text = kiRowData.pointTotalString.collectAsState().value, textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        //get stat's inherit accumulation
        Text(text = kiRowData.item.baseAccumulation.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        //ki accumulation purchased input
        NumberInput(
            kiRowData.accInputString.collectAsState().value,
            {},
            {input: String ->
                kiRowData.setAccInputString(input.toInt())
                updateFunc()
            },
            {kiRowData.setAccInputString("")},
            {},
            Color.Black,
            Modifier.weight(0.13f)
        )

        //display for ki accumulation from this stat
        Text(text = kiRowData.accTotalString.collectAsState().value, textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))
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
    kiFragVM: KiFragmentViewModel,
    ability: KiAbility,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
){
    Row(verticalAlignment = Alignment.CenterVertically){
        //taken checkbox
        Checkbox(
            checked = kiFragVM.allKiAbilities[ability]!!.value,
            onCheckedChange = {kiFragVM.setKiAbilityTaken(ability, it)},
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
 * Displays a prebuilt technique the user can add to their character
 *
 * toShow: technique associated with the row
 * allTechniques: master list of the shown techniques
 * updateMKRemaining: function to update the displayed martial knowledge available
 */
@Composable
private fun TechniqueRow(
    kiFragVM: KiFragmentViewModel,
    toShow: Technique,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
) {
    Row{
        //checkbox to apply technique to the character
        Checkbox(
            checked = kiFragVM.allTechniques[toShow]!!.value,
            onCheckedChange ={kiFragVM.attemptTechniqueChange(toShow, it)}
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