package com.example.animabuilder.activities.fragments.home_fragments

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animabuilder.R
import com.example.animabuilder.NumberInput
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.view_models.models.HomePageViewModel
import com.example.animabuilder.view_models.models.SummoningFragmentViewModel

/**
 * Fragment that displays the character's summoning abilities.
 *
 * @param summoningVM viewModel to run with this fragment
 * @param homePageVM viewModel that manages the bottom bar display
 * @param backFunc function to run on user's back button input
 */
@Composable
fun SummoningFragment(
    summoningVM: SummoningFragmentViewModel,
    homePageVM: HomePageViewModel,
    backFunc: () -> Unit
){
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
        //display table header
        item{
            Row(Modifier.fillMaxWidth()){
                Spacer(Modifier.weight(0.25f))
                Text(
                    text = stringResource(R.string.baseLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(0.15f)
                )
                Text(
                    text = stringResource(R.string.classLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(0.15f)
                )
                Text(
                    text = stringResource(R.string.boughtLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(0.25f)
                )
                Text(
                    text = stringResource(R.string.totalLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(0.2f)
                )
            }
        }

        //display table data
        items(summoningVM.allRows){
            SummoningAbilityRow(it, homePageVM)
        }
    }

    BackHandler{backFunc()}
}

/**
 * Creates a row of data that displays information on the given ability of the character.
 *
 * @param inputData table data to display to the user
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun SummoningAbilityRow(
    inputData: SummoningFragmentViewModel.SummonItemData,
    homePageVM: HomePageViewModel
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        val dpDisplay = stringResource(R.string.dpLabel, inputData.dpGetter())

        //display item, points from modifier, and points from class
        Text(
            text = stringResource(inputData.nameRef),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.25f)
        )
        Text(
            text = inputData.item.modVal.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.15f)
        )
        Text(
            text = inputData.item.levelTotal.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.15f)
        )

        //display points bought and give option to buy points
        NumberInput(
            inputText = inputData.boughtVal.collectAsState().value,
            inputFunction = {inputData.setBoughtVal(it.toInt())},
            emptyFunction = {inputData.setBoughtVal("")},
            modifier = Modifier
                .onFocusChanged {
                    if(it.isFocused)
                        inputData.setDPLabel(dpDisplay)
                    else
                        inputData.setDPLabel("")
                }
                .weight(0.25f),
            label = inputData.dpLabel.collectAsState().value,
            postRun = {homePageVM.updateExpenditures()}
        )

        //display final total
        Text(
            text = inputData.total.collectAsState().value,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.2f)
        )
    }
}

@Preview
@Composable
fun SummoningPreview(){
    val charInstance = BaseCharacter()
    val summoningFragVM = SummoningFragmentViewModel(charInstance.summoning, charInstance.ownClass)
    val homePageVM = HomePageViewModel(charInstance)

    SummoningFragment(summoningFragVM, homePageVM) {}
}