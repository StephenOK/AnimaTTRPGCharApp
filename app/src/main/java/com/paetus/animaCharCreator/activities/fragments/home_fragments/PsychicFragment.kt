package com.paetus.animaCharCreator.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.paetus.animaCharCreator.activities.fragments.dialogs.DetailAlert
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.composables.PopInItem
import com.paetus.animaCharCreator.numberScroll
import com.paetus.animaCharCreator.view_models.models.HomePageViewModel
import com.paetus.animaCharCreator.view_models.models.PsychicFragmentViewModel

/**
 * Fragment that manages a character's psychic abilities.
 * User can manage their character's psychic points and projection.
 * User can select the psychic powers their character can use.
 *
 * @param psyFragVM viewModel that is to be run on this page
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
fun PsychicFragment(
    psyFragVM: PsychicFragmentViewModel,
    homePageVM: HomePageViewModel
) {
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
                //construct item header
                Row(verticalAlignment = Alignment.CenterVertically){
                    Spacer(modifier = Modifier.weight(0.25f))

                    //header for item's base value
                    Text(
                        text = stringResource(id = R.string.baseLabel),
                        modifier = Modifier
                            .weight(0.25f),
                        textAlign = TextAlign.Center
                    )

                    //header for item's purchased amount
                    Text(
                        text = stringResource(id = R.string.boughtLabel),
                        modifier = Modifier
                            .weight(0.25f),
                        textAlign = TextAlign.Center
                    )

                    //header for item's total value
                    Text(
                        text = stringResource(id = R.string.totalLabel),
                        modifier = Modifier
                            .weight(0.25f),
                        textAlign = TextAlign.Center
                    )
                }

                //display psychic potential, point, and projection inputs
                psyFragVM.buyItems.forEach{psyItem ->
                    PsychicPurchaseTable(
                        tableData = psyItem,
                        homePageVM = homePageVM
                    )
                }
            }
        }

        item{Spacer(modifier = Modifier.height(10.dp))}

        //implement innate slot purchase input
        item{
            val innateLabel = stringResource(id = R.string.psyPointInput, 2)

            GeneralCard {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.6f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //head for innate slot purchase
                    Text(
                        text = stringResource(id = R.string.innateSlotLabel),
                        modifier = Modifier
                            .weight(0.4f)
                    )

                    //purchase input
                    NumberInput(
                        inputText = psyFragVM.innateSlotDisplay.collectAsState().value,
                        inputFunction = {psyFragVM.setInnateSlotDisplay(slotBuy = it.toInt())},
                        emptyFunction = {psyFragVM.setInnateSlotDisplay(display = "")},
                        modifier = Modifier
                            .weight(0.2f)
                            .onFocusChanged {
                                if (it.isFocused)
                                    psyFragVM.setInnateSlotLabel(dpLabel = innateLabel)
                                else
                                    psyFragVM.setInnateSlotLabel(dpLabel = "")
                            },
                        label = psyFragVM.innateSlotLabel.collectAsState().value
                    )
                }
            }
        }

        item{Spacer(modifier = Modifier.height(20.dp))}

        //display currently free psychic points
        item{
            GeneralCard {
                InfoRow(
                    label = stringResource(id = R.string.freePsyPointLabel),
                    textColor =
                        if (psyFragVM.freePointValid.collectAsState().value)
                            MaterialTheme.colorScheme.secondary
                        else
                            MaterialTheme.colorScheme.onError
                ){modifier, color ->
                    AnimatedContent(
                        targetState = psyFragVM.freePsyPoints.collectAsState().value,
                        modifier = modifier,
                        transitionSpec = numberScroll,
                        label = "freePsyPoints"
                    ){
                        Text(
                            text = "$it",
                            color = color
                        )
                    }
                }
            }
        }

        item{Spacer(modifier = Modifier.height(10.dp))}

        //display discipline info
        items(psyFragVM.allDisciplines){discipline ->
            DisciplineDisplay(
                discipline = discipline,
                psyFragVM = psyFragVM
            )
        }

        item{Spacer(modifier = Modifier.height(15.dp))}
    }

    //display psychic power details when requested
    if(psyFragVM.detailAlertOpen.collectAsState().value)
        DetailAlert(
            title = psyFragVM.detailTitle.collectAsState().value,
            item = psyFragVM.detailItem.collectAsState().value!!
        ){psyFragVM.toggleDetailAlertOpen()}
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
    //get DP cost of table item if one available
    val dpString = stringResource(id = tableData.getResource(), tableData.getValue())

    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //display title of this section
        Text(
            text = stringResource(id = tableData.title),
            modifier = Modifier
                .weight(0.25f)
        )

        //display value's base input
        Text(
            text = tableData.baseString().toString(),
            modifier = Modifier
                .weight(0.25f),
            textAlign = TextAlign.Center
        )

        //input for user purchased value
        NumberInput(
            inputText = tableData.purchaseAmount.collectAsState().value,
            inputFunction = {tableData.setPurchaseAmount(buyVal = it.toInt())},
            emptyFunction = {tableData.setPurchaseAmount(display = "")},
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused)
                        tableData.setDPDisplay(dpDisplay = dpString)
                    else
                        tableData.setDPDisplay(dpDisplay = "")
                }
                .weight(0.25f),
            label = tableData.dpDisplay.collectAsState().value,
            postRun = {homePageVM.updateExpenditures()},
            isError = !tableData.inputValid.collectAsState().value
        )

        //display value's final total
        AnimatedContent(
            targetState = tableData.totalAmount.collectAsState().value,
            modifier = Modifier
                .weight(0.25f),
            transitionSpec = numberScroll,
            label = "${stringResource(id = tableData.title)}Total"
        ) {
            Text(
                text = "$it",
                textAlign = TextAlign.Center
            )
        }
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
    //initialize context
    val context = LocalContext.current

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
            if(discipline.nameRef != 8) {
                Checkbox(
                    checked = discipline.investedIn.collectAsState().value,
                    onCheckedChange = {
                        if(!psyFragVM.isLegalDiscipline(discipline = discipline.discipline))
                            Toast.makeText(
                                context,
                                context.getString(
                                    R.string.needDisciplineMessage,
                                    context.resources.getStringArray(R.array.disciplineNames)[discipline.nameRef]
                                ),
                                Toast.LENGTH_LONG
                            ).show()
                        else
                            discipline.setInvestedIn(investIn = it)
                    },
                    modifier = Modifier
                        .weight(0.1f)
                )
            }
            else
                Spacer(modifier = Modifier.weight(0.1f))

            //button to display Psychic Powers
            Button(
                onClick = {discipline.toggleOpen()},
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(text = stringArrayResource(id = R.array.disciplineNames)[discipline.nameRef])
            }
        }

        //display for discipline's Psychic Powers
        AnimatedVisibility(visible = discipline.isOpen.collectAsState().value) {
            GeneralCard{
                discipline.powerList.forEach {power ->
                    PsyPowerRow(
                        power = power,
                        psyFragVM = psyFragVM
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
    //initialize current context
    val context = LocalContext.current

    //construct psychic point cost string
    val pointString = stringResource(id = R.string.psyPointInput, 1)

    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //checkbox to select or deselect this power
        Checkbox(
            checked = power.powerInvestedIn.collectAsState().value,
            onCheckedChange = {
                if(!psyFragVM.isLegalDiscipline(power.discipline.discipline))
                    Toast.makeText(
                        context,
                        context.getString(
                            R.string.needDisciplineMessage,
                            context.resources.getStringArray(R.array.disciplineNames)[power.discipline.nameRef]
                        ),
                        Toast.LENGTH_LONG
                    ).show()
                else
                    power.setPowerInvestedIn(isTaking = it)
            },
            modifier = Modifier
                .weight(0.1f)
        )

        //display power name
        Text(
            text = stringArrayResource(id = R.array.powerNames)[power.psyPower.name],
            modifier = Modifier
                .weight(0.3f)
        )

        //input for psychic point enhancement
        NumberInput(
            inputText = power.pointInvestment.collectAsState().value,
            inputFunction = {power.setPointInvestment(ppInvest = it.toInt())},
            emptyFunction = {power.setPointInvestment(display = "")},
            modifier = Modifier
                .weight(0.18f)
                .onFocusChanged {
                    if (it.isFocused) power.setPointLabel(display = pointString)
                    else power.setPointLabel(display = "")
                },
            label = power.pointLabel.collectAsState().value
        )

        //display the bonus from psychic point enhancement
        PopInItem(
            visible = power.powerInvestedIn.collectAsState().value,
            modifier = Modifier
                .weight(0.17f)
        ){
            Column{
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    AnimatedContent(
                        targetState = power.bonusGained.collectAsState().value,
                        transitionSpec = numberScroll,
                        label = "${stringArrayResource(id = R.array.powerNames)[power.psyPower.name]}PointPotential"
                    ) { value ->
                        Text(
                            text = stringResource(id = R.string.addNumber, value * 10),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = stringResource(id = R.string.potentialBonusLabel),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        //power's detail button
        DetailButton(
            onClick = {
                psyFragVM.setDetailItem(power = power.psyPower)
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
        charInstance.primaryList.dex.outputMod.intValue,
        LocalContext.current
    )
    val homePageFrag = HomePageViewModel(charInstance)

    psyFragVM.allDisciplines[0].toggleOpen()

    PsychicFragment(psyFragVM, homePageFrag)
}