package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.animabuilder.DetailButton
import com.example.animabuilder.NumberInput
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower
import com.example.animabuilder.view_models.PsychicFragmentViewModel

@Composable
fun PsychicFragment(
    psyFragVM: PsychicFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
) {
    LazyColumn{
        //display character's Psychic Potential
        item{Text(text = stringResource(R.string.psyPotentialLabel) + psyFragVM.getPotentialBase().toString())}

        //display psychic item input
        items(psyFragVM.buyItems){
            PsychicPurchaseTable(it, updateFunc)
        }

        //display currently free psychic points
        item{Text(text = stringResource(R.string.freePsyPointLabel) + psyFragVM.freePsyPoints.collectAsState().value)}

        //display discipline info
        items(psyFragVM.allDisciplines){
            DisciplineDisplay(
                it,
                openDetailAlert
            )
        }
    }
}

/**
 * Displays an item that allows for the user to purchase amounts of the indicated psychic ability
 *
 * tableData: information regarding this individual table
 * updateFunc: bottom bar update function
 */
@Composable
private fun PsychicPurchaseTable(
    tableData: PsychicFragmentViewModel.PsychicPurchaseItemData,
    updateFunc: () -> Unit
){
    //display title of this section
    Row{Text(text = stringResource(tableData.title))}
    Row{
        //display value's base input
        Text(text = tableData.baseString)

        //input for user purchased value
        NumberInput(
            tableData.purchaseAmount.collectAsState().value,
            {},
            {tableData.setPurchaseAmount(0)},
            {tableData.setPurchaseAmount("")},
            {updateFunc()},
            Color.Black,
            Modifier
        )

        //display value's final total
        Text(text = tableData.totalAmount.collectAsState().value)
    }
}

/**
 * Row that displays information about a Psychic Discipline and allows the user to spend points in
 * that discipline
 *
 * discipline: information regarding the specific Psychic Discipline
 * updateFreePoints: function that affects the free point display
 */
@Composable
private fun DisciplineDisplay(
    discipline: PsychicFragmentViewModel.DisciplineItemData,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
){
    Column {
        Row(Modifier.fillMaxWidth()) {
            //create a checkbox for any discipline that isn't Matrix Powers
            if(discipline.name != R.string.matrixLabel) {
                Checkbox(
                    checked = discipline.investedIn.collectAsState().value,
                    onCheckedChange = {discipline.setInvestedIn(it)}
                )
            }

            //button to display Psychic Powers
            Button(onClick = {discipline.toggleOpen()}) {
                Text(text = stringResource(discipline.name))
            }
        }

        //display for discipline's Psychic Powers
        AnimatedVisibility(visible = discipline.isOpen.collectAsState().value) {
            Column {
                discipline.item.allPowers.forEach {
                    PsyPowerRow(
                        discipline,
                        it,
                        openDetailAlert
                    )
                }
            }
        }
    }
}

/**
 * Display for a Psychic Power
 *
 * discipline: power's associated Psychic Discipline
 * power: the displayed object
 * addCheckbox: function that hoists the power and checkbox to the master list
 * updateDisciplineInvestment: function that changes the investment in the associated discipline
 * updateCheckboxes: function that sets all checkboxes to their appropriate values
 * updateFreePoints: function that updates the free point display
 */
@Composable
private fun PsyPowerRow(
    discipline: PsychicFragmentViewModel.DisciplineItemData,
    power: PsychicPower,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
){
    Row{
        Checkbox(
            checked = discipline.powerChecks[power]!!.value,
            onCheckedChange = {discipline.setPower(power, it)}
        )
        Text(text = power.name)

        //power's detail button
        DetailButton(
            onClick = {openDetailAlert(power.name) @Composable{PowerDetails(power)}},
            modifier = Modifier
        )
    }
}

//detail display for the inputted Psychic Power
val PowerDetails = @Composable{power: PsychicPower ->
    //retrieve level, if power is active, and if power is maintained
    val itemLevel = if(power.level > 0) power.level.toString() else stringResource(R.string.notApplicable)
    val isActive = if(power.active) stringResource(R.string.activeLabel) else stringResource(R.string.passiveLabel)
    val isMaintained = if(power.maintained) stringResource(R.string.yesLabel) else stringResource(R.string.noLabel)

    Column{
        //display power values
        Row{Text(text = stringResource(R.string.levelText) + " $itemLevel") }
        Row{Text(text = stringResource(R.string.actionLabel) + " $isActive")}
        Row{Text(text = stringResource(R.string.maintenanceLabel) + " $isMaintained")}
        Row{Text(text = power.description)}

        Spacer(Modifier.height(20.dp))

        //display power's Effects Table
        power.effects.forEach{
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = stringArrayResource(R.array.difficultyTable)[power.effects.indexOf(it)], modifier = Modifier.weight(0.5f))
                Text(text = it, modifier = Modifier.weight(0.5f))
            }
        }
    }
}