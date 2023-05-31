package com.example.animabuilder.activities.fragments.home_fragments

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animabuilder.InfoRow
import com.example.animabuilder.R
import com.example.animabuilder.NumberInput
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.view_models.models.CombatFragViewModel
import com.example.animabuilder.view_models.models.HomePageViewModel

/**
 * Section for the user to alter their character's combat abilities.
 * Allows user to change their character's life points.
 * Allows for changes to their character's Attack and Defense abilities as well as their wear armor.
 * Displays information on the character's resistance stats.
 * Displays information on the character's initiative, fatigue, and regeneration.
 *
 * @param combatFragVM viewModel utilized in this fragment
 * @param homePageVM viewModel that manages the bottom bar display
 * @param backFunc function to run on user's back button input
 */
@Composable
fun CombatFragment(
    combatFragVM: CombatFragViewModel,
    homePageVM: HomePageViewModel,
    backFunc: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                top = 15.dp,
                bottom = 15.dp,
                start = 30.dp,
                end = 30.dp
            )
    ){
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
            val lifeMultDP = stringResource(R.string.dpLabel, combatFragVM.getLifeDP())

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
                        homePageVM.updateExpenditures()
                    },
                    emptyFunction = {combatFragVM.setLifeMults("")},
                    modifier = Modifier
                        .onFocusChanged {
                            if (it.isFocused)
                                combatFragVM.setLifeDPLabel(lifeMultDP)
                            else
                                combatFragVM.setLifeDPLabel("")
                        }
                        .weight(0.2f),
                    label = combatFragVM.lifeDPLabel.collectAsState().value
                )

                //display life point total
                Text(
                    text = combatFragVM.lifeTotal.collectAsState().value,
                    modifier = Modifier.weight(0.2f),
                    textAlign = TextAlign.Center
                )
            }
        }

        item{Spacer(Modifier.height(10.dp))}

        //create table header for combat items
        item {
            Row {
                Spacer(modifier = Modifier.weight(0.2f))
                Text(
                    text = stringResource(R.string.pointsLabel),
                    modifier = Modifier
                        .weight(0.2f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.modLabel),
                    modifier = Modifier.weight(0.2f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.classLabel),
                    modifier = Modifier.weight(0.2f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.totalLabel),
                    modifier = Modifier.weight(0.2f),
                    textAlign = TextAlign.Center
                )
            }
        }

        //create table row for each combat item
        items(combatFragVM.allCombatItems){combatItem ->
            CombatItemRow(combatFragVM, combatItem, homePageVM)
        }

        item{Spacer(Modifier.height(20.dp))}

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

        item{Spacer(Modifier.height(20.dp))}

        //create displays for the character's initiative, fatigue, and regeneration
        item{
            InfoRow(
                stringResource(R.string.totalInitiative),
                combatFragVM.getInitiativeTotal()
            )
        }

        item{
            InfoRow(
                stringResource(R.string.fatigueLabel),
                combatFragVM.getFatigue()
            )
        }

        item{
            InfoRow(
                stringResource(R.string.regenLabel),
                combatFragVM.getRegen()
            )
        }
    }

    BackHandler{backFunc()}
}

/**
 * Creates a table row for the combat items and allows input and total display.
 *
 * @param combatItem character's combat stat to display
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun CombatItemRow(
    combatFragVM: CombatFragViewModel,
    combatItem: CombatFragViewModel.CombatItemData,
    homePageVM: HomePageViewModel
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        val focusString = stringResource(R.string.dpLabel, combatItem.growthGetter())

        //row label
        Text(
            text = stringResource(combatItem.label),
            modifier = Modifier.weight(0.2f)
        )

        //stat input field
        NumberInput(
            inputText = combatItem.pointsIn.collectAsState().value,
            inputFunction = {
                combatItem.setPointsIn(it.toInt())
                homePageVM.updateExpenditures()
            },
            emptyFunction = {combatItem.setPointsIn("")},
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused) combatItem.setLabelDisplay(focusString)
                    else combatItem.setLabelDisplay("")
                }
                .weight(0.2f),
            label = combatItem.labelDisplay.collectAsState().value,
            color =
                if(combatItem.label != R.string.wearLabel)
                    combatFragVM.pointColor.collectAsState().value
                else Color.Black
        )

        //display remaining stat values
        Text(
            text = combatItem.item.modPoints.value.toString(),
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center
        )
        Text(
            text = combatItem.item.classTotal.value.toString(),
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center
        )
        Text(
            text = combatItem.totalVal.collectAsState().value,
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center
        )
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
            text = stringResource(resistance.label),
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
            text = resistance.getModStat(),
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

@Preview
@Composable
fun CombatPreview(){
    val charInstance = BaseCharacter()
    val combatFragVM = CombatFragViewModel(
        charInstance.combat,
        charInstance.primaryList,
        charInstance.ownClass
    )
    val homePageFragVM = HomePageViewModel(charInstance)

    CombatFragment(
        combatFragVM,
        homePageFragVM
    ) {}
}