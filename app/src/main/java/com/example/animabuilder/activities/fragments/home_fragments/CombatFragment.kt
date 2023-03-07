package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.animabuilder.R
import com.example.animabuilder.NumberInput
import com.example.animabuilder.view_models.CombatFragViewModel

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
                    combatFragVM.lifeMults.collectAsState().value,
                    {},
                    { input ->
                        combatFragVM.setLifeMults(input.toInt())
                        updateFunc()
                    },
                    {combatFragVM.setLifeMults("")},
                    {},
                    Color.Black,
                    Modifier.weight(0.2f)
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

        item{Text(text = stringResource(R.string.totalInitiative) + combatFragVM.getInitiativeTotal())}
        item{Text(text = stringResource(R.string.fatigueLabel) + combatFragVM.getFatigue())}
        item{Text(text = stringResource(R.string.regenLabel) + combatFragVM.getRegen())}
    }
}

/**
 * Creates a table row for the combat items and allows input and total display
 *
 * combatItem: character's combat stat to display
 * updateFunc: function to run for bottom bar update
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
            combatItem.pointsIn.collectAsState().value,
            {},
            {input ->
                combatItem.setPointsIn(input.toInt())
                updateFunc()
            },
            {combatItem.setPointsIn("")},
            {},
            combatFragVM.pointColor.collectAsState().value,
            Modifier.weight(0.2f)
        )

        //display remaining stat values
        Text(text = combatItem.item.modPoints.toString(), modifier = Modifier.weight(0.2f))
        Text(text = combatItem.item.classTotal.toString(), modifier = Modifier.weight(0.2f))
        Text(text = combatItem.totalVal.collectAsState().value, modifier = Modifier.weight(0.2f))
    }
}

/**
 * Create a row for the resistances table
 *
 * resistance: resistance data to display
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
            text = resistance.item.special.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //total resistance value
        Text(
            text = resistance.item.total.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
    }
}