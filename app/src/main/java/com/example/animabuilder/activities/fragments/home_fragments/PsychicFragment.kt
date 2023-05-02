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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.animabuilder.DetailButton
import com.example.animabuilder.NumberInput
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower
import com.example.animabuilder.view_models.models.HomePageViewModel
import com.example.animabuilder.view_models.models.PsychicFragmentViewModel

/**
 * Fragment that manages a character's psychic abilities.
 * User can manage their character's psychic points and projection.
 * User can select the psychic powers their character can use.
 *
 * @param psyFragVM viewModel that is to be run on this page
 * @param openDetailAlert function to run when looking at an item's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
fun PsychicFragment(
    psyFragVM: PsychicFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
) {
    LazyColumn{
        //display character's Psychic Potential
        item{Text(text = stringResource(R.string.psyPotentialLabel) + psyFragVM.potentialTotal.collectAsState().value)}

        //implement psychic point investment into psychic potential
        item{
            Row {
                Text(text = stringResource(R.string.pointsInPotential))
                NumberInput(
                    inputText = psyFragVM.pointsInPotential.collectAsState().value,
                    inputFunction = {psyFragVM.setPointsInPotential(it.toInt())},
                    emptyFunction = {psyFragVM.setPointsInPotential("")},
                    modifier = Modifier.weight(0.2f)
                )
            }
        }

        //display psychic point and projection inputs
        items(psyFragVM.buyItems){
            PsychicPurchaseTable(it, homePageVM)
        }

        //display currently free psychic points
        item{
            Text(
                text = stringResource(R.string.freePsyPointLabel) + psyFragVM.freePsyPoints.collectAsState().value,
                color = psyFragVM.freePointColor.collectAsState().value
            )
        }

        //display discipline info
        items(psyFragVM.allDisciplines){
            DisciplineDisplay(
                it,
                openDetailAlert
            )
        }

        //implement innate slot purchase input
        item{
            Row{
                Text(text = stringResource(R.string.innateSlotLabel))
                NumberInput(
                    inputText = psyFragVM.innateSlotDisplay.collectAsState().value,
                    inputFunction = {psyFragVM.setInnateSlotDisplay(it.toInt())},
                    emptyFunction = {psyFragVM.setInnateSlotDisplay("")}
                )
            }
        }
    }
}

/**
 * Displays an item that allows for the user to purchase amounts of the indicated psychic ability.
 *
 * @param tableData information regarding this individual table
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun PsychicPurchaseTable(
    tableData: PsychicFragmentViewModel.PsychicPurchaseItemData,
    homePageVM: HomePageViewModel
){
    //display title of this section
    Row{Text(text = stringResource(tableData.title))}
    Row{
        //display value's base input
        Text(text = tableData.baseString)

        //input for user purchased value
        NumberInput(
            inputText = tableData.purchaseAmount.collectAsState().value,
            inputFunction = {tableData.setPurchaseAmount(it.toInt())},
            emptyFunction = {tableData.setPurchaseAmount("")},
            postRun = {homePageVM.updateExpenditures()},
            color = tableData.textColor.collectAsState().value
        )

        //display value's final total
        Text(text = tableData.totalAmount.collectAsState().value)
    }
}

/**
 * Row that displays information about a Psychic Discipline and allows the user to spend points in
 * that discipline.
 *
 * @param discipline information regarding the specific Psychic Discipline
 * @param openDetailAlert function to run when looking at a power's details
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
                discipline.powerList.forEach {
                    PsyPowerRow(
                        it,
                        openDetailAlert
                    )
                }
            }
        }
    }
}

/**
 * Display for a Psychic Power.
 *
 * @param power the displayed object
 * @param openDetailAlert function to run when looking at a power's details
 */
@Composable
private fun PsyPowerRow(
    power: PsychicFragmentViewModel.PowerItemData,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
){
    Row{
        //checkbox to select or deselect this power
        Checkbox(
            checked = power.powerInvestedIn.collectAsState().value,
            onCheckedChange = {power.setPowerInvestedIn(it)}
        )
        Text(text = power.item.name)

        //input for psychic point enhancement
        NumberInput(
            inputText = power.pointInvestment.collectAsState().value,
            inputFunction = {power.setPointInvestment(it.toInt())},
            emptyFunction = {power.setPointInvestment("")},
            modifier = Modifier.weight(0.2f)
        )

        Text(text = power.bonusGained.collectAsState().value)

        //power's detail button
        DetailButton(
            onClick = {openDetailAlert(power.item.name) @Composable{PowerDetails(power.item)}},
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