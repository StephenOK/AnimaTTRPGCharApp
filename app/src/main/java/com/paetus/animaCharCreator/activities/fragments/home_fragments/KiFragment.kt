package com.paetus.animaCharCreator.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paetus.animaCharCreator.composables.DetailButton
import com.paetus.animaCharCreator.composables.GeneralCard
import com.paetus.animaCharCreator.composables.InfoRow
import com.paetus.animaCharCreator.composables.NumberInput
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.activities.fragments.dialogs.CustomTechnique
import com.paetus.animaCharCreator.activities.fragments.dialogs.DetailAlert
import com.paetus.animaCharCreator.activities.fragments.dialogs.TechContents
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.PrebuiltTech
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.Technique
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.TechniqueBase
import com.paetus.animaCharCreator.numberScroll
import com.paetus.animaCharCreator.view_models.models.CustomTechniqueViewModel
import com.paetus.animaCharCreator.view_models.models.HomePageViewModel
import com.paetus.animaCharCreator.view_models.models.KiFragmentViewModel

/**
 * Fragment that displays items related to martial knowledge.
 * Ki points and accumulations can be acquired here.
 * Ki abilities are acquirable.
 * Dominion Techniques are taken and created in this page.
 *
 * @param kiFragVM viewModel to run for this page
 * @param homePageVM viewModel that manages the bottom bar display
 */

@Composable
fun KiFragment(
    kiFragVM: KiFragmentViewModel,
    homePageVM: HomePageViewModel
) {
    //get fragment's context
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 30.dp,
                end = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item{Spacer(Modifier.height(15.dp))}

        item{
            GeneralCard{
                //header for ki point and accumulation table
                Row {
                    Spacer(modifier = Modifier.weight(0.1f))

                    //ki point items
                    Text(
                        text = stringResource(R.string.statKiLabel),
                        modifier = Modifier
                            .weight(0.1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(R.string.buyKiLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(R.string.totalKiLabel),
                        modifier = Modifier
                            .weight(0.1f),
                        textAlign = TextAlign.Center
                    )

                    //ki accumulation items
                    Text(
                        text = stringResource(R.string.statAccLabel),
                        modifier = Modifier
                            .weight(0.1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(R.string.buyAccLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(R.string.totalAccLabel),
                        modifier = Modifier
                            .weight(0.1f),
                        textAlign = TextAlign.Center
                    )
                }

                //display a ki row for each characteristic
                kiFragVM.allRowData.forEach{
                    KiFromStatRow(
                        it,
                        kiFragVM,
                        homePageVM
                    )
                }

                //display both overall totals
                Row {
                    Text(
                        text = stringResource(R.string.totalLabel),
                        modifier = Modifier
                            .weight(0.13f),
                        textAlign = TextAlign.Center
                    )

                    //display total ki points
                    Spacer(Modifier.weight(0.26f))

                    AnimatedContent(
                        targetState = kiFragVM.kiPointTotal.collectAsState().value,
                        modifier = Modifier
                            .weight(0.13f),
                        transitionSpec = numberScroll,
                        label = "kiPointTotal"
                    ) {
                        Text(
                            text = "$it",
                            textAlign = TextAlign.Center
                        )
                    }

                    //display total accumulation
                    Spacer(Modifier.weight(0.26f))

                    AnimatedContent(
                        targetState = kiFragVM.kiAccTotal.collectAsState().value,
                        modifier = Modifier
                            .weight(0.13f),
                        transitionSpec = numberScroll,
                        label = "kiAccTotal"
                    ) {
                        Text(
                            text = "$it",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        item{Spacer(Modifier.height(20.dp))}

        item{
            GeneralCard{
                //display maximum MK to spend
                InfoRow(
                    label = stringResource(R.string.maxMKLabel)
                ){it, _ ->
                    Text(
                        text = kiFragVM.getMartialMax(),
                        modifier = it
                    )
                }

                Spacer(Modifier.height(10.dp))

                //display remaining MK to spend
                InfoRow(
                    label = stringResource(R.string.remainingMKLabel)
                ){modifier, _ ->
                    AnimatedContent(
                        targetState = kiFragVM.remainingMK.collectAsState().value,
                        modifier = modifier,
                        transitionSpec = numberScroll,
                        label = "mkRemaining"
                    ) {
                        Text(
                            text = "$it"
                        )
                    }
                }
            }
        }

        item{Spacer(Modifier.height(10.dp))}

        //button to display ki abilities
        item {
            Button(
                onClick = {kiFragVM.toggleKiListOpen() },
                modifier = Modifier
                    .width(250.dp)
            ) {
                Text(text = stringResource(R.string.kiAbilityLabel))
            }
        }

        //ki ability display
        item {
            AnimatedVisibility(visible = kiFragVM.kiListOpen.collectAsState().value) {
                GeneralCard {
                    kiFragVM.getAllKiAbilities().forEach {
                        KiAbilityRow(
                            kiFragVM,
                            it
                        )
                    }
                }
            }
        }

        //button to display dominion techniques
        item {
            Button(
                onClick = {
                    //check that character can take a dominion technique
                    if (!kiFragVM.toggleTechListOpen())
                        Toast.makeText(
                            context,
                            context.getString(R.string.kiControlRequired),
                            Toast.LENGTH_SHORT
                        ).show()
                },
                modifier = Modifier
                    .width(250.dp)
            ) {
                Text(text = stringResource(R.string.dominionLabel))
            }
        }

        //technique display
        item {
            AnimatedVisibility(visible = kiFragVM.techListOpen.collectAsState().value) {
                GeneralCard{
                    //display each prebuilt technique
                    kiFragVM.getAllPrebuilts().forEach {
                        TechniqueRow(kiFragVM, it)
                    }

                    //button for custom technique creation
                    Button(
                        onClick = {kiFragVM.toggleCustomTechOpen()}
                    ) {
                        Text(text = stringResource(R.string.addTechniques))
                    }

                    //display custom techniques
                    kiFragVM.getCustomTechniques().forEach {
                        TechniqueRow(kiFragVM, it)
                    }
                }
            }
        }

        item{Spacer(Modifier.height(15.dp))}
    }

    //dialog for custom technique creation
    if(kiFragVM.customTechOpen.collectAsState().value)
        CustomTechnique(kiFragVM, CustomTechniqueViewModel(context, kiFragVM)){TechContents(it)}

    //dialog for an item's details
    if(kiFragVM.detailAlertOpen.collectAsState().value)
        DetailAlert(
            kiFragVM.detailName.collectAsState().value,
            kiFragVM.detailItem.collectAsState().value!!
        ){kiFragVM.toggleDetailAlertOn()}
}

/**
 * Creates a display row for ki point and accumulation purchases of an individual primary characteristic.
 *
 * @param kiRowData data specific to this row's stat
 * @param kiFragVM viewModel that manages this fragment
 * @param homePageVM viewModel that manages the bottom bar display
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun KiFromStatRow(
    kiRowData: KiFragmentViewModel.KiRowData,
    kiFragVM: KiFragmentViewModel,
    homePageVM: HomePageViewModel
){
    //initialize DP cost strings for ki points and accumulation
    val pointDP = stringResource(R.string.dpLabel, kiFragVM.getKiPointDP())
    val accDP = stringResource(R.string.dpLabel, kiFragVM.getKiAccDP())

    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //display stat name
        Text(
            text = stringArrayResource(R.array.primaryCharArray)[kiRowData.title],
            modifier = Modifier
                .weight(0.1f),
            textAlign = TextAlign.Center
        )

        //get stat's inherit ki points
        Text(
            text = kiRowData.item.baseKiPoints.value.toString(),
            modifier = Modifier
                .weight(0.1f),
            textAlign = TextAlign.Center
        )

        //ki points purchased input
        NumberInput(
            inputText = kiRowData.pointInputString.collectAsState().value,
            inputFunction = {
                kiRowData.setPointInputString(it.toInt())
                homePageVM.updateExpenditures()
            },
            emptyFunction = {kiRowData.setPointInputString("")},
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused)
                        kiRowData.setPointDPLabel(pointDP)
                    else
                        kiRowData.setPointDPLabel("")
                }
                .weight(0.2f),
            label = kiRowData.pointDPLabel.collectAsState().value
        )

        //display for ki points from this stat
        AnimatedContent(
            targetState = kiRowData.pointTotalString.collectAsState().value,
            modifier = Modifier
                .weight(0.1f),
            transitionSpec = numberScroll,
            label = "${stringArrayResource(R.array.primaryCharArray)[kiRowData.title]}KiPoints"
        ) {
            Text(
                text = "$it",
                textAlign = TextAlign.Center
            )
        }

        //get stat's inherit accumulation
        Text(
            text = kiRowData.item.baseAccumulation.value.toString(),
            modifier = Modifier
                .weight(0.1f),
            textAlign = TextAlign.Center
        )

        //ki accumulation purchased input
        NumberInput(
            inputText = kiRowData.accInputString.collectAsState().value,
            inputFunction = {
                kiRowData.setAccInputString(it.toInt())
                homePageVM.updateExpenditures()
            },
            emptyFunction = {kiRowData.setAccInputString("")},
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused)
                        kiRowData.setAccDPLabel(accDP)
                    else
                        kiRowData.setAccDPLabel("")
                }
                .weight(0.2f),
            label = kiRowData.accDPLabel.collectAsState().value
        )

        //display for ki accumulation from this stat
        AnimatedContent(
            targetState = kiRowData.accTotalString.collectAsState().value,
            modifier = Modifier
                .weight(0.1f),
            transitionSpec = numberScroll,
            label = "${stringArrayResource(R.array.primaryCharArray)[kiRowData.title]}KiAcc"
        ) {
            Text(
                text = "$it",
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Gives a row for a character to take the indicated Ki Ability.
 *
 * @param kiFragVM viewModel managing this page's data
 * @param ability ki ability to display in this row
 */
@Composable
private fun KiAbilityRow(
    kiFragVM: KiFragmentViewModel,
    ability: KiAbility
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //checkbox to take or remove the ability
        Checkbox(
            checked = kiFragVM.allKiAbilities[ability]!!.value,
            onCheckedChange = {kiFragVM.setKiAbilityTaken(ability, it)},
            modifier = Modifier.weight(0.1f)
        )

        //display ki ability name and cost
        Text(
            text = stringResource(ability.name),
            modifier = Modifier
                .weight(0.45f),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.mkLabel, ability.mkCost),
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )

        //button to display ki ability details
        DetailButton(
            onClick = {
                kiFragVM.setDetailItem(ability)
                kiFragVM.toggleDetailAlertOn()
            },
            modifier = Modifier
                .weight(0.25f)
        )
    }
}

/**
 * Displays a technique the user can add to their character.
 *
 * @param kiFragVM viewModel that is managing the data on this page
 * @param toShow technique associated with the row
 */
@Composable
private fun TechniqueRow(
    kiFragVM: KiFragmentViewModel,
    toShow: TechniqueBase
) {
    val techName =
        if(toShow is PrebuiltTech) stringResource(toShow.name)
        else (toShow as Technique).name

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //checkbox to apply or remove technique to the character
        Checkbox(
            checked = kiFragVM.allTechniques[toShow]!!.value,
            onCheckedChange ={kiFragVM.attemptTechniqueChange(toShow, it)},
            modifier = Modifier
                .weight(0.1f)
        )

        //show technique's name, cost, and level
        Text(
            text = techName,
            modifier = Modifier
                .weight(0.35f),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.mkLabel, toShow.mkCost()),
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )
        Text(
            text = toShow.level.toString(),
            modifier = Modifier
                .weight(0.1f),
            textAlign = TextAlign.Center
        )

        //give button to display technique's details
        DetailButton(
            onClick = {
                kiFragVM.setDetailItem(toShow)
                kiFragVM.toggleDetailAlertOn()
            },
            modifier = Modifier
                .weight(0.25f)
        )
    }
}

@Preview
@Composable
fun KiPreview(){
    val charInstance = BaseCharacter()

    val kiFragVM = KiFragmentViewModel(charInstance.ki, charInstance.ownClass, LocalContext.current)
    kiFragVM.toggleTechListOpen()

    val homePageVM = HomePageViewModel(charInstance)

    KiFragment(kiFragVM, homePageVM)
}