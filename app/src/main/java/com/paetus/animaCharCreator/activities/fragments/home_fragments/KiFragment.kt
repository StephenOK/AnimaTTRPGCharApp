package com.paetus.animaCharCreator.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
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
import com.paetus.animaCharCreator.activities.fragments.dialogs.CustomTechniqueDialog
import com.paetus.animaCharCreator.activities.fragments.dialogs.DetailAlert
import com.paetus.animaCharCreator.activities.fragments.dialogs.TechniqueDetails
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.PrebuiltTech
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.CustomTechnique
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
 * @param filename file to associate with any custom techniques created
 * @param kiFragVM viewModel to run for this page
 * @param customTechVM viewModel for custom technique dialog
 * @param homePageVM viewModel that manages the bottom bar display
 */

@Composable
fun KiFragment(
    filename: String,
    kiFragVM: KiFragmentViewModel,
    customTechVM: CustomTechniqueViewModel,
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
        item{Spacer(modifier = Modifier.height(15.dp))}

        item{
            GeneralCard{
                //header for ki point and accumulation table
                Row {
                    Spacer(modifier = Modifier.weight(0.1f))

                    //ki point items
                    Text(
                        text = stringResource(id = R.string.statKiLabel),
                        modifier = Modifier
                            .weight(0.1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = R.string.buyKiLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = R.string.totalKiLabel),
                        modifier = Modifier
                            .weight(0.1f),
                        textAlign = TextAlign.Center
                    )

                    //ki accumulation items
                    Text(
                        text = stringResource(id = R.string.statAccLabel),
                        modifier = Modifier
                            .weight(0.1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = R.string.buyAccLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = R.string.totalAccLabel),
                        modifier = Modifier
                            .weight(0.1f),
                        textAlign = TextAlign.Center
                    )
                }

                //display a ki row for each characteristic
                kiFragVM.allRowData.forEach{rowData ->
                    KiFromStatRow(
                        kiRowData = rowData,
                        kiFragVM = kiFragVM,
                        homePageVM = homePageVM
                    )
                }

                //display both overall totals
                Row {
                    Text(
                        text = stringResource(id = R.string.totalLabel),
                        modifier = Modifier
                            .weight(0.13f),
                        textAlign = TextAlign.Center
                    )

                    //display total ki points
                    Spacer(modifier = Modifier.weight(0.26f))

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
                    Spacer(modifier = Modifier.weight(0.26f))

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

        item{Spacer(modifier = Modifier.height(20.dp))}

        item{
            GeneralCard{
                //display maximum MK to spend
                InfoRow(
                    label = stringResource(id = R.string.maxMKLabel)
                ){modifier, _ ->
                    Text(
                        text = kiFragVM.getMartialMax(),
                        modifier = modifier
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                //display remaining MK to spend
                InfoRow(
                    label = stringResource(id = R.string.remainingMKLabel)
                ){modifier, _ ->
                    AnimatedContent(
                        targetState = kiFragVM.remainingMK.collectAsState().value,
                        modifier = modifier,
                        transitionSpec = numberScroll,
                        label = "mkRemaining"
                    ) {
                        Text(text = "$it")
                    }
                }
            }
        }

        item{Spacer(modifier = Modifier.height(10.dp))}

        //button to display ki abilities
        item {
            Button(
                onClick = {kiFragVM.toggleKiListOpen()},
                modifier = Modifier
                    .width(250.dp)
            ) {
                Text(text = stringResource(id = R.string.kiAbilityLabel))
            }
        }

        //ki ability display
        item {
            AnimatedVisibility(visible = kiFragVM.kiListOpen.collectAsState().value) {
                GeneralCard {
                    kiFragVM.getAllKiAbilities().forEach {kiAbility ->
                        KiAbilityRow(
                            ability = kiAbility,
                            kiFragVM = kiFragVM
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
                    if (kiFragVM.checkIfToggle())
                        kiFragVM.toggleTechOpen()
                    else
                        Toast.makeText(
                            context,
                            context.getString(R.string.kiControlRequired),
                            Toast.LENGTH_SHORT
                        ).show()
                },
                modifier = Modifier
                    .width(250.dp)
            ) {
                Text(text = stringResource(id = R.string.dominionLabel))
            }
        }

        //technique display
        item {
            AnimatedVisibility(visible = kiFragVM.techListOpen.collectAsState().value) {
                GeneralCard{
                    //display each prebuilt technique
                    kiFragVM.getAllPrebuilts().forEach {(technique, taken) ->
                        TechniqueRow(
                            technique = technique,
                            isTaken = taken,
                            kiFragVM = kiFragVM
                        )
                    }

                    //button for custom technique creation
                    Button(
                        onClick = {customTechVM.toggleCustomTechOpen()}
                    ) {
                        Text(text = stringResource(id = R.string.addTechniques))
                    }

                    //display custom techniques
                    kiFragVM.getCustomTechniques().forEach {(technique, taken) ->
                        TechniqueRow(
                            technique = technique,
                            isTaken = taken,
                            kiFragVM = kiFragVM
                        )
                    }
                }
            }
        }

        item{Spacer(modifier = Modifier.height(15.dp))}
    }

    //dialog for custom technique creation
    if(customTechVM.customTechOpen.collectAsState().value)
        CustomTechniqueDialog(
            filename = filename,
            kiFragVM = kiFragVM,
            customTechVM = customTechVM
        ){TechniqueDetails(it)}

    //dialog for an item's details
    if(kiFragVM.detailAlertOpen.collectAsState().value)
        DetailAlert(
            title = kiFragVM.detailName.collectAsState().value,
            item = kiFragVM.detailItem.collectAsState().value!!
        ){kiFragVM.toggleDetailAlertOn()}
}

/**
 * Creates a display row for ki point and accumulation purchases of an individual primary characteristic.
 *
 * @param kiRowData data specific to this row's stat
 * @param kiFragVM viewModel that manages this fragment
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun KiFromStatRow(
    kiRowData: KiFragmentViewModel.KiRowData,
    kiFragVM: KiFragmentViewModel,
    homePageVM: HomePageViewModel
){
    //initialize DP cost strings for ki points and accumulation
    val pointDP = stringResource(id = R.string.dpLabel, kiFragVM.getKiPointDP())
    val accDP = stringResource(id = R.string.dpLabel, kiFragVM.getKiAccDP())

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
            text = kiRowData.kiStat.baseKiPoints.intValue.toString(),
            modifier = Modifier
                .weight(0.1f),
            textAlign = TextAlign.Center
        )

        //ki points purchased input
        NumberInput(
            inputText = kiRowData.pointInputString.collectAsState().value,
            inputFunction = {
                kiRowData.setPointInputString(pointBuy = it.toInt())
                homePageVM.updateExpenditures()
            },
            emptyFunction = {kiRowData.setPointInputString(display = "")},
            refill = {kiRowData.currentPoints()},
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused)
                        kiRowData.setPointDPLabel(dpDisplay = pointDP)
                    else
                        kiRowData.setPointDPLabel(dpDisplay = "")
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
            text = kiRowData.kiStat.baseAccumulation.intValue.toString(),
            modifier = Modifier
                .weight(0.1f),
            textAlign = TextAlign.Center
        )

        //ki accumulation purchased input
        NumberInput(
            inputText = kiRowData.accInputString.collectAsState().value,
            inputFunction = {
                kiRowData.setAccInputString(accBuy = it.toInt())
                homePageVM.updateExpenditures()
            },
            emptyFunction = {kiRowData.setAccInputString(display = "")},
            refill = {kiRowData.currentAcc()},
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused)
                        kiRowData.setAccDPLabel(dpDisplay = accDP)
                    else
                        kiRowData.setAccDPLabel(dpDisplay = "")
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
 * @param ability ki ability to display in this row
 * @param kiFragVM viewModel managing this page's data
 */
@Composable
private fun KiAbilityRow(
    ability: KiAbility,
    kiFragVM: KiFragmentViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //checkbox to take or remove the ability
        Checkbox(
            checked = kiFragVM.allKiAbilities[ability]!!.value,
            onCheckedChange = {
                kiFragVM.setKiAbilityTaken(
                    kiAbility = ability,
                    isTaken = it
                )
            },
            modifier = Modifier.weight(0.1f)
        )

        //display ki ability name and cost
        Text(
            text = stringResource(id = ability.name),
            modifier = Modifier
                .weight(0.45f),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(id = R.string.mkLabel, ability.mkCost),
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )

        //button to display ki ability details
        DetailButton(
            onClick = {
                kiFragVM.setDetailItem(kiAbility = ability)
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
 * @param technique technique associated with the row
 * @param isTaken taken state of the inputted technique
 * @param kiFragVM viewModel that is managing the data on this page
 */
@Composable
private fun TechniqueRow(
    technique: TechniqueBase,
    isTaken: MutableState<Boolean>,
    kiFragVM: KiFragmentViewModel
) {
    //retrieve the technique's name
    val techName =
        if(technique is PrebuiltTech) stringResource(id = technique.name)
        else (technique as CustomTechnique).name.value

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //checkbox to apply or remove technique to the character
        Checkbox(
            checked = isTaken.value,
            onCheckedChange ={
                kiFragVM.attemptTechniqueChange(
                    technique = technique,
                    isTaken = it
                )
            },
            modifier = Modifier
                .weight(0.1f)
        )

        //display technique name
        Text(
            text = techName,
            modifier = Modifier
                .weight(0.35f),
            textAlign = TextAlign.Center
        )

        //display technique cost
        Text(
            text = stringResource(id = R.string.mkLabel, technique.mkCost()),
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )

        //display technique's level
        Text(
            text = technique.level.intValue.toString(),
            modifier = Modifier
                .weight(0.1f),
            textAlign = TextAlign.Center
        )

        //give button to display technique's details
        DetailButton(
            onClick = {
                kiFragVM.setDetailItem(technique = technique)
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

    val kiFragVM = KiFragmentViewModel(
        charInstance.ki,
        LocalContext.current
    )
    kiFragVM.toggleTechOpen()

    val homePageVM = HomePageViewModel(charInstance)
    val customTechVM = CustomTechniqueViewModel(charInstance.ki, LocalContext.current)

    KiFragment("", kiFragVM, customTechVM, homePageVM)
}