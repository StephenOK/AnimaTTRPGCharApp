package com.paetus.animaCharCreator.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedContent
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
        item{Spacer(modifier = Modifier.height(15.dp))}

        item{
            GeneralCard{
                //display table header
                Row(Modifier.fillMaxWidth()){
                    Spacer(modifier = Modifier.weight(0.25f))

                    //head for base value
                    Text(
                        text = stringResource(id = R.string.baseLabel),
                        modifier = Modifier
                            .weight(0.15f),
                        textAlign = TextAlign.Center
                    )

                    //head for class bonus
                    Text(
                        text = stringResource(id = R.string.classLabel),
                        modifier = Modifier
                            .weight(0.15f),
                        textAlign = TextAlign.Center
                    )

                    //head for bought amount
                    Text(
                        text = stringResource(id = R.string.boughtLabel),
                        modifier = Modifier
                            .weight(0.25f),
                        textAlign = TextAlign.Center
                    )

                    //head for final total
                    Text(
                        text = stringResource(id = R.string.totalLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )
                }

                //display table data
                summoningVM.allRows.forEach{summonItem ->
                    SummoningAbilityRow(
                        summonData = summonItem,
                        homePageVM = homePageVM
                    )
                }
            }
        }

        item{Spacer(modifier = Modifier.height(15.dp))}
    }
}

/**
 * Creates a row of data that displays information on the given ability of the character.
 *
 * @param summonData table data to display to the user
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun SummoningAbilityRow(
    summonData: SummoningFragmentViewModel.SummonItemData,
    homePageVM: HomePageViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //initialize DP cost display of this item
        val dpDisplay = stringResource(id = R.string.dpLabel, summonData.dpGetter())

        //display item name
        Text(
            text = stringResource(id = summonData.nameRef),
            modifier = Modifier
                .weight(0.25f),
            textAlign = TextAlign.Center
        )

        //display modifier point value
        Text(
            text = summonData.summonAbility.modVal.intValue.toString(),
            modifier = Modifier
                .weight(0.15f),
            textAlign = TextAlign.Center
        )

        //display class point value
        Text(
            text = summonData.summonAbility.levelTotal.intValue.toString(),
            modifier = Modifier
                .weight(0.15f),
            textAlign = TextAlign.Center
        )

        //display points bought and give option to buy points
        NumberInput(
            inputText = summonData.boughtVal.collectAsState().value,
            inputFunction = {summonData.setBoughtVal(it.toInt())},
            emptyFunction = {summonData.setBoughtVal("")},
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused)
                        summonData.setDPLabel(dpLabel = dpDisplay)
                    else
                        summonData.setDPLabel(dpLabel = "")
                }
                .weight(0.25f),
            label = summonData.dpLabel.collectAsState().value,
            postRun = {homePageVM.updateExpenditures()}
        )

        //display final total
        AnimatedContent(
            targetState = summonData.total.collectAsState().value,
            modifier = Modifier
                .weight(0.2f),
            transitionSpec = numberScroll,
            label = "${stringResource(id = summonData.nameRef)}Total"
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