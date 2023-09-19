package com.paetus.animaCharCreator.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import com.paetus.animaCharCreator.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.paetus.animaCharCreator.composables.GeneralCard
import com.paetus.animaCharCreator.composables.InfoRow
import com.paetus.animaCharCreator.composables.NumberInput
import com.paetus.animaCharCreator.composables.TextInput
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.composables.OutlinedDropdown
import com.paetus.animaCharCreator.numberScroll
import com.paetus.animaCharCreator.textScrollUp
import com.paetus.animaCharCreator.view_models.models.CharacterFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.HomePageViewModel

/**
 * Fragment to be displayed when working with basic characteristics.
 * Used to manipulate primary characteristics, class, race, and name.
 * Default fragment at character load.
 *
 * @param charFragVM viewModel for this page
 * @param maxNumVM viewModel that manages the bottom bar display
 */

@Composable
fun CharacterPageFragment(
    charFragVM: CharacterFragmentViewModel,
    maxNumVM: HomePageViewModel
){
    //get local context
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 30.dp,
                end = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{Spacer(Modifier.height(15.dp))}

        item{
            GeneralCard{
                //name input
                TextInput(
                    display = charFragVM.nameInput.collectAsState().value,
                    onValueChange = {
                        charFragVM.setNameInput(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    label = stringResource(R.string.nameText)
                )

                //class, race, and level dropdown items
                charFragVM.dropdownList.forEach {
                    OutlinedDropdown(
                        it.options,
                        it.output.collectAsState().value,
                        it.isOpen.collectAsState().value,
                        it.nameRef,
                        it.icon.collectAsState().value,
                        it.size.collectAsState().value,
                        {coordinates ->
                            it.setSize(coordinates.size.toSize())
                        },
                        {input ->
                            it.setOutput(input)
                            maxNumVM.updateMaximums()
                            maxNumVM.updateExpenditures()
                        }
                    ){it.openToggle()}
                }

                //experience point input
                NumberInput(
                    inputText = charFragVM.experiencePoints.collectAsState().value,
                    inputFunction = { charFragVM.setExp(it.toInt()) },
                    emptyFunction = { charFragVM.setExp("") },
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    alignment = TextAlign.Left,
                    label = stringResource(R.string.expText)
                )
            }
        }

        item{Spacer(Modifier.height(20.dp))}

        //display gender bonus selection if the character is a duk'zarist
        item {
            if(charFragVM.raceDropdown.output.collectAsState().value == 6 ||
                charFragVM.magPaladinOpen.collectAsState().value) {
                GeneralCard {
                    if (charFragVM.raceDropdown.output.collectAsState().value == 6) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                        ) {
                            //display checkbox for selection
                            Checkbox(
                                checked = charFragVM.isMale.collectAsState().value,
                                onCheckedChange = { charFragVM.toggleGender() },
                                modifier = Modifier
                                    .weight(0.1f)
                            )
                            //display current selection
                            AnimatedContent(
                                targetState = stringResource(charFragVM.genderString.collectAsState().value),
                                modifier = Modifier
                                    .clickable { charFragVM.toggleGender() }
                                    .weight(0.5f),
                                transitionSpec = textScrollUp,
                                label = "genderDisplay"
                            ){
                                Text(
                                    text = it,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    //implement paladin checkbox if necessary
                    if (charFragVM.magPaladinOpen.collectAsState().value) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = charFragVM.magPaladin.collectAsState().value,
                                onCheckedChange = { charFragVM.toggleMagPaladin() },
                                modifier = Modifier
                                    .weight(0.1f)
                            )

                            Text(
                                text = stringResource(R.string.isMagPaladin),
                                modifier = Modifier
                                    .clickable { charFragVM.toggleMagPaladin() }
                                    .weight(0.5f),
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }
            }
        }

        item{Spacer(Modifier.height(20.dp))}

        item{
            GeneralCard{
                //primary characteristic table
                //table header row
                Row {
                    //column buffer
                    Spacer(modifier = Modifier.weight(0.2f))

                    //score header
                    Text(
                        text = stringResource(R.string.scoreLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.weight(0.01f))

                    //level bonus input header
                    Text(
                        text = stringResource(R.string.bonusLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //bonus header
                    Text(
                        text = stringResource(R.string.specialLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //mod header
                    Text(
                        text = stringResource(R.string.modLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )
                }

                //create row for each primary characteristic
                charFragVM.primaryDataList.forEach{
                    PrimaryRow(charFragVM, it)
                }
            }
        }

        item{Spacer(modifier = Modifier.height(20.dp))}

        item{
            GeneralCard{
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    NumberInput(
                        //create input for character's appearance score
                        inputText = charFragVM.appearInput.collectAsState().value,
                        inputFunction = {
                            //attempt new input and notify user of failed input
                            if (it.toInt() <= 10 && !charFragVM.setAppearInput(it.toInt()))
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.appearanceFailure),
                                    Toast.LENGTH_LONG
                                ).show()
                        },
                        emptyFunction = {
                            //clear input if user can change it
                            if (charFragVM.isNotUnattractive())
                                charFragVM.setAppearInput("")
                        },
                        modifier = Modifier
                            .weight(0.5f),
                        label = stringResource(R.string.appearance)
                    )

                    Spacer(Modifier.weight(0.01f))

                    //create input for a character's gnosis
                    NumberInput(
                        inputText = charFragVM.gnosisDisplay.collectAsState().value,
                        inputFunction = {charFragVM.setGnosisDisplay(it.toInt())},
                        emptyFunction = {charFragVM.setGnosisDisplay("")},
                        modifier = Modifier
                            .weight(0.5f),
                        label = stringResource(R.string.gnosisLabel)
                    )
                }

                Spacer(Modifier.height(15.dp))

                //display character's size category
                InfoRow(
                    stringResource(R.string.sizeCat)
                ){modifier, _ ->
                    AnimatedContent(
                        targetState = charFragVM.sizeInput.collectAsState().value,
                        modifier = modifier,
                        transitionSpec = numberScroll,
                        label = "sizeDisplay"
                    ){
                        Text(
                            text = "$it"
                        )
                    }
                }

                //display character's movement value
                InfoRow(
                    label = stringResource(R.string.movement)
                ){modifier, _ ->
                    AnimatedContent(
                        targetState = charFragVM.getCharMovement(),
                        modifier = modifier,
                        transitionSpec = numberScroll,
                        label = "movementDisplay"
                    ) {
                        Text(
                            text =
                            if(it == 0) stringResource(charFragVM.movementDisplay.collectAsState().value)
                            else stringResource(charFragVM.movementDisplay.collectAsState().value, it)
                        )
                    }
                }

                //display character's weight index
                InfoRow(
                    label = stringResource(R.string.weightIndex)
                ){modifier, _ ->
                    AnimatedContent(
                        targetState = charFragVM.getCharWeight(),
                        modifier = modifier,
                        transitionSpec = numberScroll,
                        label = "weightIndexDisplay"
                    ){
                        Text(
                            text =
                                if(charFragVM.getCharWeight() == 0)
                                    stringResource(charFragVM.weightIndex.collectAsState().value)
                                else
                                    stringResource(charFragVM.weightIndex.collectAsState().value, it)
                        )
                    }
                }
            }
        }

        item{Spacer(Modifier.height(15.dp))}
    }
}

/**
 * Create a row for the primary characteristics table.
 *
 * @param charFragVM viewModel that manages this fragment
 * @param primeItem primary characteristic data to display
 */
@Composable
private fun PrimaryRow(
    charFragVM: CharacterFragmentViewModel,
    primeItem: CharacterFragmentViewModel.PrimeCharacteristicData
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //row label
        Text(
            text = stringArrayResource(R.array.primaryCharArray)[primeItem.name],
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )
        //input for characteristic score
        NumberInput(
            inputText = primeItem.input.collectAsState().value,
            inputFunction = {
                //change input and other necessary items if in legal range
                if(it.toInt() in 1..20)
                    primeItem.setInput(it.toInt())
            },
            emptyFunction = {primeItem.setInput("")},
            modifier = Modifier
                .weight(0.2f)
        )

        Spacer(Modifier.weight(0.01f))

        //level bonus input
        NumberInput(
            inputText = primeItem.bonusInput.collectAsState().value,
            inputFunction = {primeItem.setBonusInput(it.toInt())},
            emptyFunction = {primeItem.setBonusInput("")},
            modifier = Modifier
                .weight(0.2f),
            isError = !charFragVM.bonusValid.collectAsState().value,
        )

        //other bonus display
        AnimatedContent(
            targetState = primeItem.bonus.collectAsState().value,
            modifier = Modifier
                .weight(0.2f),
            transitionSpec = numberScroll,
            label = "${stringArrayResource(R.array.primaryCharArray)[primeItem.name]}Bonus"
        ) {
            Text(
                text = "$it",
                textAlign = TextAlign.Center
            )
        }

        //mod display
        AnimatedContent(
            targetState = primeItem.modTotal.collectAsState().value,
            modifier = Modifier
                .weight(0.2f),
            transitionSpec = numberScroll,
            label = "${stringArrayResource(R.array.primaryCharArray)[primeItem.name]}Mod"
        ) {
            Text(
                text = "$it",
                textAlign = TextAlign.Center
            )
        }
    }

    Spacer(Modifier.height(5.dp))
}

@Preview
@Composable
fun CharacterPreview(){
    val charInstance = BaseCharacter()
    charInstance.setOwnClass(3)
    charInstance.setOwnRace(6)

    val charFragVM = CharacterFragmentViewModel(charInstance)
    charFragVM.toggleGender()

    CharacterPageFragment(
        charFragVM,
        HomePageViewModel(charInstance)
    )
}
