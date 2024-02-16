package com.paetus.animaCharCreator.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paetus.animaCharCreator.composables.GeneralCard
import com.paetus.animaCharCreator.composables.InfoRow
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.composables.NumberInput
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.numberScroll
import com.paetus.animaCharCreator.view_models.models.CombatFragViewModel
import com.paetus.animaCharCreator.view_models.models.HomePageViewModel

/**
 * Section for the user to alter their character's combat abilities.
 * Allows user to change their character's life points.
 * Allows for changes to their character's Attack and Defense abilities as well as their wear armor.
 * Displays information on the character's resistance stats.
 * Displays information on the character's initiative, fatigue, and regeneration.
 *
 * @param combatFragVM viewModel utilized in this fragment
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
fun CombatFragment(
    combatFragVM: CombatFragViewModel,
    homePageVM: HomePageViewModel
) {
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
                //create header row for life points table
                Row {
                    Spacer(modifier = Modifier.weight(0.2f))

                    //head for base life points
                    Text(
                        text = stringResource(id = R.string.baseLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //head for class life points
                    Text(
                        text = stringResource(id = R.string.classLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //head for bought multipliers
                    Text(
                        text = stringResource(id = R.string.multLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //head for total life points
                    Text(
                        text = stringResource(id = R.string.totalLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )
                }

                //initialize dp value string
                val lifeMultDP = stringResource(id = R.string.dpLabel, combatFragVM.getLifeDP())

                //character life point row
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //row label
                    Text(
                        text = stringResource(id = R.string.lifePointLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //display life points from base and class values
                    Text(
                        text = combatFragVM.getBaseLife(),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = combatFragVM.getClassLife(),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //create input for life point multiples
                    NumberInput(
                        inputText = combatFragVM.lifeMults.collectAsState().value,
                        inputFunction = {
                            combatFragVM.setLifeMults(multBuy = it.toInt())
                            homePageVM.updateExpenditures()
                        },
                        emptyFunction = {combatFragVM.setLifeMults(display = "")},
                        modifier = Modifier
                            .onFocusChanged {
                                if (it.isFocused)
                                    combatFragVM.setLifeDPLabel(dpDisplay = lifeMultDP)
                                else
                                    combatFragVM.setLifeDPLabel(dpDisplay = "")
                            }
                            .weight(0.2f),
                        label = combatFragVM.lifeDPLabel.collectAsState().value
                    )

                    //display life point total
                    AnimatedContent(
                        targetState = combatFragVM.lifeTotal.collectAsState().value,
                        modifier = Modifier
                            .weight(0.2f),
                        transitionSpec = numberScroll,
                        label = "lifePointTotal"
                    ) {
                        Text(
                            text = "$it",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        item{Spacer(modifier = Modifier.height(10.dp))}

        item{
            GeneralCard{
                //create table header for combat items
                Row {
                    Spacer(modifier = Modifier.weight(0.2f))

                    //head for points inputted
                    Text(
                        text = stringResource(id = R.string.pointsLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //head for mod bonus
                    Text(
                        text = stringResource(id = R.string.modLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //head for class points
                    Text(
                        text = stringResource(id = R.string.classLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //head for point total
                    Text(
                        text = stringResource(id = R.string.totalLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )
                }

                //create table row for each combat item
                combatFragVM.allCombatItems.forEach{combatItem ->
                    CombatItemRow(
                        combatItem = combatItem,
                        combatFragVM = combatFragVM,
                        homePageVM = homePageVM
                    )
                }
            }
        }

        item{Spacer(modifier = Modifier.height(20.dp))}

        item{
            //resistances table
            GeneralCard {
                //table header row
                Row {
                    //column buffer
                    Spacer(modifier = Modifier.weight(0.2f))

                    //presence header
                    Text(
                        text = stringResource(id = R.string.presenceLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //modifier header
                    Text(
                        text = stringResource(id = R.string.modLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //special header
                    Text(
                        text = stringResource(id = R.string.specialLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //total header
                    Text(
                        text = stringResource(id = R.string.totalLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )
                }

                //create a row for each of the character's resistances
                combatFragVM.resistanceList.forEach{resItem ->
                    ResistanceRow(
                        resistance = resItem,
                        combatFragVM = combatFragVM
                    )
                }
            }
        }

        item{Spacer(modifier = Modifier.height(20.dp))}

        item{
            GeneralCard{
                //display character's initiative
                InfoRow(
                    label = stringResource(id = R.string.totalInitiative)
                ){modifier, _ ->
                    Text(
                        text = combatFragVM.getInitiativeTotal(),
                        modifier = modifier
                    )
                }

                //display character's fatigue
                InfoRow(
                    label = stringResource(id = R.string.fatigueLabel)
                ){modifier, _ ->
                    Text(
                        text = combatFragVM.getFatigue(),
                        modifier = modifier
                    )
                }

                //display character's regeneration
                InfoRow(
                    label = stringResource(id = R.string.regenLabel)
                ){modifier, _ ->
                    Text(
                        text = combatFragVM.getRegen(),
                        modifier = modifier
                    )
                }
            }
        }

        item{Spacer(modifier = Modifier.height(15.dp))}
    }
}

/**
 * Creates a table row for the combat items and allows input and total display.
 *
 * @param combatItem character's combat stat to display
 * @param combatFragVM viewModel that manages this fragment
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun CombatItemRow(
    combatItem: CombatFragViewModel.CombatItemData,
    combatFragVM: CombatFragViewModel,
    homePageVM: HomePageViewModel
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //initialize dp cost of this item
        val focusString = stringResource(id = R.string.dpLabel, combatItem.growthGetter())

        //row label
        Text(
            text = stringResource(id = combatItem.label),
            modifier = Modifier.weight(0.2f)
        )

        //stat input field
        NumberInput(
            inputText = combatItem.pointsIn.collectAsState().value,
            inputFunction = {
                combatItem.setPointsIn(it.toInt())
                homePageVM.updateExpenditures()
            },
            emptyFunction = {combatItem.setPointsIn(display = "")},
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused) combatItem.setLabelDisplay(dpDisplay = focusString)
                    else combatItem.setLabelDisplay(dpDisplay = "")
                }
                .weight(0.2f),
            label = combatItem.labelDisplay.collectAsState().value,
            isError = combatItem.label != R.string.wearLabel && !combatFragVM.pointValid.collectAsState().value
        )

        //display stat's modifier
        Text(
            text = combatItem.combatItem.modPoints.intValue.toString(),
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center
        )

        //display stat's class bonus
        Text(
            text = combatItem.combatItem.classTotal.intValue.toString(),
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center
        )

        //display stat's total
        AnimatedContent(
            targetState = combatItem.totalVal.collectAsState().value,
            modifier = Modifier
                .weight(0.2f),
            transitionSpec = numberScroll,
            label = "${stringResource(id = combatItem.label)}Total"
        ){
            Text(
                text = "$it",
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Create a row for the resistances table.
 *
 * @param resistance resistance data to display
 * @param combatFragVM viewModel that controls this fragment's data
 */
@Composable
private fun ResistanceRow(
    resistance: CombatFragViewModel.ResistanceData,
    combatFragVM: CombatFragViewModel
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //name of the resistance type
        Text(
            text = stringResource(id = resistance.resLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.2f)
        )
        //character's presence
        Text(
            text = combatFragVM.getPresence(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.2f)
        )
        //associated modifier value
        Text(
            text = resistance.getModStat(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.2f)
        )
        //associated special addition
        Text(
            text = resistance.resItem.special.intValue.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.2f)
        )
        //total resistance value
        Text(
            text = resistance.resItem.total.intValue.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.2f)
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
        charInstance.classes.ownClass
    )
    val homePageFragVM = HomePageViewModel(charInstance)

    CombatFragment(
        combatFragVM,
        homePageFragVM
    )
}