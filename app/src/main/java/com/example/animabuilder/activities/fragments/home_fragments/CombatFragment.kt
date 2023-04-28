package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.animabuilder.R
import com.example.animabuilder.NumberInput
import com.example.animabuilder.view_models.CombatFragViewModel

/**
 * Section for the user to alter their character's combat abilities.
 * Allows user to change their character's life points.
 * Allows for changes to their character's Attack and Defense abilities as well as their wear armor.
 * Displays information on the character's resistance stats.
 * Displays information on the character's initiative, fatigue, and regeneration.
 *
 * @param combatFragVM viewModel utilized in this fragment
 * @param updateFunc function to run to update the bottom bar values
 */
@Composable
fun CombatFragment(
    combatFragVM: CombatFragViewModel,
    updateFunc: () -> Unit
) {
    LazyColumn{
        //create header row for combat items
        item {
            Row {
                Spacer(modifier = Modifier.weight(0.2f))
                Text(text = stringResource(R.string.baseLabel), textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
                Text(text = stringResource(R.string.classLabel), textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
                Text(text = stringResource(R.string.multLabel), textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
                Text(text = stringResource(R.string.totalLabel), textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
            }
        }

        //character life point row
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(R.string.lifePointLabel), textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))

                //display life points from base and class values
                Text(
                    text = combatFragVM.getBaseLife(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )
                Text(
                    text = combatFragVM.getClassLife(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )

                //create input for life point multiples
                NumberInput(
                    inputText = combatFragVM.lifeMults.collectAsState().value,
                    inputFunction = {
                        combatFragVM.setLifeMults(it.toInt())
                        updateFunc()
                    },
                    emptyFunction = {combatFragVM.setLifeMults("")},
                    modifier = Modifier.weight(0.2f)
                )

                //display life point total
                Text(
                    text = combatFragVM.lifeTotal.collectAsState().value,
                    modifier = Modifier.weight(0.2f)
                )
            }
        }

        //create table header for combat items
        item {
            Row {
                Spacer(modifier = Modifier.weight(0.2f))
                Text(text = stringResource(R.string.pointsLabel), modifier = Modifier.weight(0.2f))
                Text(
                    text = stringResource(R.string.modLabel),
                    modifier = Modifier.weight(0.2f)
                )
                Text(
                    text = stringResource(R.string.classLabel),
                    modifier = Modifier.weight(0.2f)
                )
                Text(
                    text = stringResource(R.string.totalLabel),
                    modifier = Modifier.weight(0.2f)
                )
            }
        }

        //create table row for each combat item
        items(combatFragVM.allCombatItems){combatItem ->
            CombatItemRow(combatFragVM, combatItem, updateFunc)
        }

        //resistances table
        item {
            //table header row
            Row {
                //column buffer
                Spacer(modifier = Modifier.weight(0.2f))

                //presence header
                Text(
                    text = stringResource(R.string.presenceLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )

                //modifier header
                Text(
                    text = stringResource(R.string.modLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )

                //special header
                Text(
                    text = stringResource(R.string.specialLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )

                //total header
                Text(
                    text = stringResource(R.string.totalLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )
            }
        }

        //create a row for each of the character's resistances
        items(combatFragVM.resistanceList){resistanceItem ->
            ResistanceRow(combatFragVM, resistanceItem)
        }

        //create displays for the character's initiative, fatigue, and regeneration
        item{Text(text = stringResource(R.string.totalInitiative) + combatFragVM.getInitiativeTotal())}
        item{Text(text = stringResource(R.string.fatigueLabel) + combatFragVM.getFatigue())}
        item{Text(text = stringResource(R.string.regenLabel) + combatFragVM.getRegen())}
    }
}

/**
 * Creates a table row for the combat items and allows input and total display.
 *
 * @param combatFragVM viewModel that controls this fragment's data
 * @param combatItem character's combat stat to display
 * @param updateFunc function to run for bottom bar update
 */
@Composable
private fun CombatItemRow(
    combatFragVM: CombatFragViewModel,
    combatItem: CombatFragViewModel.CombatItemData,
    updateFunc: () -> Unit
){
    Row {
        //row label
        Text(text = combatItem.label, modifier = Modifier.weight(0.2f))

        //stat input field
        NumberInput(
            inputText = combatItem.pointsIn.collectAsState().value,
            inputFunction = {
                combatItem.setPointsIn(it.toInt())
                updateFunc()
            },
            emptyFunction = {combatItem.setPointsIn("")},
            modifier = Modifier.weight(0.2f),
            color = combatFragVM.pointColor.collectAsState().value
        )

        //display remaining stat values
        Text(text = combatItem.item.modPoints.value.toString(), modifier = Modifier.weight(0.2f))
        Text(text = combatItem.item.classTotal.value.toString(), modifier = Modifier.weight(0.2f))
        Text(text = combatItem.totalVal.collectAsState().value, modifier = Modifier.weight(0.2f))
    }
}

/**
 * Create a row for the resistances table.
 *
 * @param combatFragVM viewModel that controls this fragment's data
 * @param resistance resistance data to display
 */
@Composable
private fun ResistanceRow(
    combatFragVM: CombatFragViewModel,
    resistance: CombatFragViewModel.ResistanceData
){
    Row(verticalAlignment = Alignment.CenterVertically){
        //name of the resistance type
        Text(
            text = resistance.label,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //character's presence
        Text(
            text = combatFragVM.getPresence(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //associated modifier value
        Text(
            text = resistance.modStat,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //associated special addition
        Text(
            text = resistance.item.special.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //total resistance value
        Text(
            text = resistance.item.total.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
    }
}