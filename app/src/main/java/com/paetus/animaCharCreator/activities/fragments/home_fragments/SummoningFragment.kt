package com.paetus.animaCharCreator.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paetus.animaCharCreator.composables.GeneralCard
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.composables.NumberInput
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.numberScroll
import com.paetus.animaCharCreator.view_models.models.HomePageViewModel
import com.paetus.animaCharCreator.view_models.models.SummoningFragmentViewModel

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
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 30.dp,
                end = 30.dp
            )
    ){
        item{Spacer(Modifier.height(15.dp))}

        item{
            GeneralCard{
                //display table header
                Row(Modifier.fillMaxWidth()){
                    Spacer(Modifier.weight(0.25f))
                    Text(
                        text = stringResource(R.string.baseLabel),
                        modifier = Modifier
                            .weight(0.15f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(R.string.classLabel),
                        modifier = Modifier
                            .weight(0.15f),
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
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )
                }

                //display table data
                summoningVM.allRows.forEach{
                    SummoningAbilityRow(it, homePageVM)
                }
            }
        }

        item{Spacer(Modifier.height(15.dp))}
    }
}

/**
 * Creates a row of data that displays information on the given ability of the character.
 *
 * @param inputData table data to display to the user
 * @param homePageVM viewModel that manages the bottom bar display
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SummoningAbilityRow(
    inputData: SummoningFragmentViewModel.SummonItemData,
    homePageVM: HomePageViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //initialize DP cost display of this item
        val dpDisplay = stringResource(R.string.dpLabel, inputData.dpGetter())

        //display item, points from modifier, and points from class
        Text(
            text = stringResource(inputData.nameRef),
            modifier = Modifier
                .weight(0.25f),
            textAlign = TextAlign.Center
        )
        Text(
            text = inputData.item.modVal.value.toString(),
            modifier = Modifier
                .weight(0.15f),
            textAlign = TextAlign.Center
        )
        Text(
            text = inputData.item.levelTotal.value.toString(),
            modifier = Modifier
                .weight(0.15f),
            textAlign = TextAlign.Center
        )

        //display points bought and give option to buy points
        NumberInput(
            inputText = inputData.boughtVal.collectAsState().value,
            inputFunction = {inputData.setBoughtVal(it.toInt())},
            emptyFunction = {inputData.setBoughtVal("")},
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused)
                        inputData.setDPLabel(dpDisplay)
                    else
                        inputData.setDPLabel("")
                }
                .weight(0.25f),
            label = inputData.dpLabel.collectAsState().value,
            postRun = {homePageVM.updateExpenditures()}
        )

        //display final total
        AnimatedContent(
            targetState = inputData.total.collectAsState().value,
            modifier = Modifier
                .weight(0.2f),
            transitionSpec = numberScroll,
            label = "${stringResource(inputData.nameRef)}Total"
        ) {
            Text(
                text = "$it",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun SummoningPreview(){
    val charInstance = BaseCharacter()
    val summoningFragVM = SummoningFragmentViewModel(charInstance.summoning, charInstance.ownClass)
    val homePageVM = HomePageViewModel(charInstance)

    SummoningFragment(summoningFragVM, homePageVM)
}