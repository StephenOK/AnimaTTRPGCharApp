package com.example.animabuilder.activities.fragments.home_fragments

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
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
import com.example.animabuilder.DetailButton
import com.example.animabuilder.InfoRow
import com.example.animabuilder.NumberInput
import com.example.animabuilder.R
import com.example.animabuilder.activities.fragments.dialogs.DetailAlert
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.view_models.models.HomePageViewModel
import com.example.animabuilder.view_models.models.PsychicFragmentViewModel

/**
 * Fragment that manages a character's psychic abilities.
 * User can manage their character's psychic points and projection.
 * User can select the psychic powers their character can use.
 *
 * @param psyFragVM viewModel that is to be run on this page
 * @param homePageVM viewModel that manages the bottom bar display
 * @param backFunc function to run on user's back button input
 */
@Composable
fun PsychicFragment(
    psyFragVM: PsychicFragmentViewModel,
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
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //display psychic potential, point, and projection inputs
        items(psyFragVM.buyItems){
            PsychicPurchaseTable(it, homePageVM)
        }

        //implement innate slot purchase input
        item{
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.6f),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = stringResource(R.string.innateSlotLabel),
                    modifier = Modifier
                        .weight(0.4f)
                )
                NumberInput(
                    inputText = psyFragVM.innateSlotDisplay.collectAsState().value,
                    inputFunction = {psyFragVM.setInnateSlotDisplay(it.toInt())},
                    emptyFunction = {psyFragVM.setInnateSlotDisplay("")},
                    modifier = Modifier
                        .weight(0.2f)
                )
            }
        }

        item{Spacer(Modifier.height(20.dp))}

        //display currently free psychic points
        item{
            InfoRow(
                stringResource(R.string.freePsyPointLabel),
                psyFragVM.freePsyPoints.collectAsState().value,
                color = psyFragVM.freePointColor.collectAsState().value
            )
        }

        item{Spacer(Modifier.height(10.dp))}

        //display discipline info
        items(psyFragVM.allDisciplines){
            DisciplineDisplay(
                it,
                psyFragVM
            )
        }
    }

    if(psyFragVM.detailAlertOpen.collectAsState().value)
        DetailAlert(
            psyFragVM.detailTitle.collectAsState().value,
            psyFragVM.detailItem.collectAsState().value!!
        ){psyFragVM.toggleDetailAlertOpen()}

    BackHandler{backFunc()}
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
    val dpString =
        if(tableData.dpGetter != null)
            stringResource(R.string.dpLabel, tableData.dpGetter!!())
        else
            ""

    Row(verticalAlignment = Alignment.CenterVertically){
        Spacer(Modifier.weight(0.25f))
        Text(
            text = stringResource(R.string.baseLabel),
            modifier = Modifier
                .weight(0.25f),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.boughtLabel),
            modifier = Modifier
                .weight(0.25f),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.totalLabel),
            modifier = Modifier
                .weight(0.25f),
            textAlign = TextAlign.Center
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //display title of this section
        Text(
            text = stringResource(tableData.title),
            modifier = Modifier
                .weight(0.25f)
        )

        //display value's base input
        Text(
            text = tableData.baseString().toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.25f)
        )

        //input for user purchased value
        NumberInput(
            inputText = tableData.purchaseAmount.collectAsState().value,
            inputFunction = {tableData.setPurchaseAmount(it.toInt())},
            emptyFunction = {tableData.setPurchaseAmount("")},
            modifier = Modifier
                .onFocusChanged {
                    if(it.isFocused)
                        tableData.setDPDisplay(dpString)
                    else
                        tableData.setDPDisplay("")
                }
                .weight(0.25f),
            label = tableData.dpDisplay.collectAsState().value,
            postRun = {homePageVM.updateExpenditures()},
            color = tableData.textColor.collectAsState().value
        )

        //display value's final total
        Text(
            text = tableData.totalAmount.collectAsState().value,
            modifier = Modifier
                .weight(0.25f),
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Row that displays information about a Psychic Discipline and allows the user to spend points in
 * that discipline.
 *
 * @param discipline information regarding the specific Psychic Discipline
 * @param psyFragVM viewModel that manages this fragment
 */
@Composable
private fun DisciplineDisplay(
    discipline: PsychicFragmentViewModel.DisciplineItemData,
    psyFragVM: PsychicFragmentViewModel
){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(0.7f)
        ) {
            //create a checkbox for any discipline that isn't Matrix Powers
            if(discipline.name != R.string.matrixLabel) {
                Checkbox(
                    checked = discipline.investedIn.collectAsState().value,
                    onCheckedChange = {discipline.setInvestedIn(it)},
                    modifier = Modifier.weight(0.1f)
                )
            }
            else
                Spacer(Modifier.weight(0.1f))

            //button to display Psychic Powers
            Button(
                onClick = {discipline.toggleOpen()},
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(text = stringResource(discipline.name))
            }
        }

        //display for discipline's Psychic Powers
        AnimatedVisibility(visible = discipline.isOpen.collectAsState().value) {
            Column {
                discipline.powerList.forEach {
                    PsyPowerRow(
                        it,
                        psyFragVM
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
 * @param psyFragVM viewModel that manages this page
 */
@Composable
private fun PsyPowerRow(
    power: PsychicFragmentViewModel.PowerItemData,
    psyFragVM: PsychicFragmentViewModel
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //checkbox to select or deselect this power
        Checkbox(
            checked = power.powerInvestedIn.collectAsState().value,
            onCheckedChange = {power.setPowerInvestedIn(it)},
            modifier = Modifier
                .weight(0.1f)
        )
        Text(
            text = power.item.name,
            modifier = Modifier
                .weight(0.3f)
        )

        //input for psychic point enhancement
        NumberInput(
            inputText = power.pointInvestment.collectAsState().value,
            inputFunction = {power.setPointInvestment(it.toInt())},
            emptyFunction = {power.setPointInvestment("")},
            modifier = Modifier.weight(0.18f)
        )

        Text(
            text = power.bonusGained.collectAsState().value,
            modifier = Modifier
                .weight(0.17f),
            textAlign = TextAlign.Center
        )

        //power's detail button
        DetailButton(
            onClick = {
                psyFragVM.setDetailItem(power.item)
                psyFragVM.toggleDetailAlertOpen()
            },
            modifier = Modifier
                .weight(0.25f)
        )
    }
}

@Preview
@Composable
fun PsychicPreview(){
    val charInstance = BaseCharacter()
    val psyFragVM = PsychicFragmentViewModel(
        charInstance.psychic,
        charInstance.ownClass,
        charInstance.primaryList.dex.outputMod.value
    )
    val homePageFrag = HomePageViewModel(charInstance)

    psyFragVM.allDisciplines[0].toggleOpen()

    PsychicFragment(psyFragVM, homePageFrag) {}
}