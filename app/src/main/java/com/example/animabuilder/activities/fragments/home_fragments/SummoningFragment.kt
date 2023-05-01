package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.animabuilder.R
import com.example.animabuilder.NumberInput
import com.example.animabuilder.view_models.HomePageViewModel
import com.example.animabuilder.view_models.SummoningFragmentViewModel

/**
 * Fragment that displays the character's summoning abilities.
 *
 * @param summoningVM viewModel to run with this fragment
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
fun SummoningFragment(
    summoningVM: SummoningFragmentViewModel,
    homePageVM: HomePageViewModel
){
    LazyColumn{
        //display table header
        item{
            Row(Modifier.fillMaxWidth()){
                Spacer(Modifier.weight(0.3f))
                Text(text = stringResource(R.string.baseLabel), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
                Text(text = stringResource(R.string.classLabel), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
                Text(text = stringResource(R.string.boughtLabel), textAlign = TextAlign.Center, modifier = Modifier.weight(0.3f))
                Text(text = stringResource(R.string.totalLabel), textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
            }
        }

        //display table data
        items(summoningVM.allRows){
            SummoningAbilityRow(it, homePageVM)
        }
    }
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
    Row(Modifier.fillMaxWidth()){
        //display item, points from modifier, and points from class
        Text(text = stringResource(inputData.nameRef), textAlign = TextAlign.Center, modifier = Modifier.weight(0.3f))
        Text(text = inputData.item.modVal.value.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = inputData.item.levelTotal.value.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))

        //display points bought and give option to buy points
        NumberInput(
            inputText = inputData.boughtVal.collectAsState().value,
            inputFunction = {inputData.setBoughtVal(it.toInt())},
            emptyFunction = {inputData.setBoughtVal("")},
            modifier = Modifier.weight(0.3f),
            postRun = {homePageVM.updateExpenditures()}
        )

        //display final total
        Text(text = inputData.total.collectAsState().value, textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
    }
}