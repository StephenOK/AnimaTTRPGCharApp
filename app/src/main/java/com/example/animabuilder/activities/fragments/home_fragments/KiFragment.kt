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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.animabuilder.DetailButton
import com.example.animabuilder.InfoRow
import com.example.animabuilder.NumberInput
import com.example.animabuilder.R
import com.example.animabuilder.activities.fragments.dialogs.CustomTechnique
import com.example.animabuilder.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.Technique
import com.example.animabuilder.view_models.CustomTechniqueViewModel
import com.example.animabuilder.view_models.KiFragmentViewModel

/**
 * Fragment that displays items related to martial knowledge.
 * Ki points and accumulations can be acquired here.
 * Ki abilities are acquirable.
 * Dominion Techniques are taken and created in this page.
 *
 * @param kiFragVM viewModel to run for this page
 * @param openDetailAlert function to run when opening an item's details
 * @param updateFunc function to run to update the bottom bar's values
 */

@Composable
fun KiFragment(
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
        item{InfoRow(stringResource(R.string.maxMKLabel), kiFragVM.getMartialMax())}
        item{InfoRow(stringResource(R.string.remainingMKLabel), kiFragVM.remainingMK.collectAsState().value)}

        //header for ki point and accumulation table
        item {
            Row {
                Spacer(modifier = Modifier.weight(0.13f))
                Text(text = stringResource(R.string.statKiLabel), modifier = Modifier.weight(0.13f))
                Text(text = stringResource(R.string.buyKiLabel), modifier = Modifier.weight(0.13f))
                Text(text = stringResource(R.string.totalKiLabel), modifier = Modifier.weight(0.13f))
                Text(text = stringResource(R.string.statAccLabel), modifier = Modifier.weight(0.13f))
                Text(text = stringResource(R.string.buyAccLabel), modifier = Modifier.weight(0.13f))
                Text(text = stringResource(R.string.totalAccLabel), modifier = Modifier.weight(0.13f))
            }
        }

        //display a ki row for each characteristic
        items(kiFragVM.allRowData) {kiRowInput ->
            KiFromStatRow(
                kiRowInput,
                updateFunc
            )
        }

        item {
            Row {
                Text(text = stringResource(R.string.totalLabel), modifier = Modifier.weight(0.13f))

                //display total ki points
                Spacer(Modifier.weight(0.26f))
                Text(
                    text = kiFragVM.kiPointTotal.collectAsState().value,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.13f)
                )

                //display total accumulation
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
                onClick = {kiFragVM.toggleKiListOpen() },
                modifier = Modifier.width(250.dp)
            ) {
                Text(text = stringResource(R.string.kiAbilityLabel))
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
                    if (!kiFragVM.toggleTechListOpen())
                        Toast.makeText(
                            context,
                            "You need Ki Control for Dominion Techniques",
                            Toast.LENGTH_SHORT
                        ).show()
                },
                modifier = Modifier.width(250.dp)
            ) {
                Text(text = stringResource(R.string.dominionLabel))
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
                        onClick = {kiFragVM.toggleCustomTechOpen()}
                    ) {
                        Text(text = stringResource(R.string.addTechniques))
                    }

                    //display custom techniques
                    kiFragVM.getCustomTechniques().forEach {
                        TechniqueRow(kiFragVM, it, openDetailAlert)
                    }
                }
            }
        }
    }

    //dialog for custom technique creation
    if(kiFragVM.customTechOpen.collectAsState().value)
        CustomTechnique(kiFragVM, CustomTechniqueViewModel(context, kiFragVM), TechContents)
}

/**
 * Creates a display row for ki point and accumulation purchases of an individual primary characteristic.
 *
 * @param kiRowData data specific to this row's stat
 * @param updateFunc bottom bar update function
 */
@Composable
private fun KiFromStatRow(
    kiRowData: KiFragmentViewModel.KiRowData,
    updateFunc: () -> Unit
){
    Row{
        //display stat name
        Text(text = kiRowData.title, textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        //get stat's inherit ki points
        Text(text = kiRowData.item.baseKiPoints.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        //ki points purchased input
        NumberInput(
            kiRowData.pointInputString.collectAsState().value,
            {},
            {
                kiRowData.setPointInputString(it.toInt())
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
            {
                kiRowData.setAccInputString(it.toInt())
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
 * Gives a row for a character to take the indicated Ki Ability.
 *
 * @param kiFragVM viewModel managing this page's data
 * @param ability ki ability to display in this row
 * @param openDetailAlert function to run on opening an item's details
 */
@Composable
private fun KiAbilityRow(
    kiFragVM: KiFragmentViewModel,
    ability: KiAbility,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
){
    Row(verticalAlignment = Alignment.CenterVertically){
        //checkbox to take or remove the ability
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
 * Displays a technique the user can add to their character.
 *
 * @param kiFragVM viewModel that is managing the data on this page
 * @param toShow technique associated with the row
 * @param openDetailAlert function to run when opening this item's details
 */
@Composable
private fun TechniqueRow(
    kiFragVM: KiFragmentViewModel,
    toShow: Technique,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
) {
    Row{
        //checkbox to apply or remove technique to the character
        Checkbox(
            checked = kiFragVM.allTechniques[toShow]!!.value,
            onCheckedChange ={kiFragVM.attemptTechniqueChange(toShow, it)}
        )

        //show technique's name, cost, and level
        Text(text = toShow.name)
        Text(text = toShow.mkCost().toString() + " " + stringResource(R.string.mkLabel))
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

        InfoRow(stringResource(R.string.prereqLabel), preString)
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
                Text(text = stringResource(R.string.maintenanceLabel))
                for(index in 0..5){
                    if(technique.maintArray[index] != 0)
                        Text(text = technique.maintArray[index].toString() + " (" + stringArrayResource(R.array.primaryCharArray)[index] + ")")
                }
            }
        }

        val kiBuilds = technique.statSpent()

        for(index in 0..5){
            if(kiBuilds[index] > 0)
                InfoRow(stringArrayResource(R.array.primaryCharArray)[index], kiBuilds[index].toString())
        }

        InfoRow(stringResource(R.string.totalAccumulation), technique.accTotal().toString())

        Text(text = technique.description)
    }
}