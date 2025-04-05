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
import com.paetus.animaCharCreator.activities.fragments.dialogs.DetailAlert
import com.paetus.animaCharCreator.composables.GeneralCard
import com.paetus.animaCharCreator.composables.InfoRow
import com.paetus.animaCharCreator.composables.NumberInput
import com.paetus.animaCharCreator.composables.TextInput
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.composables.DetailButton
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
) {
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
        item{Spacer(modifier = Modifier.height(15.dp))}

        item{
            GeneralCard{
                //name input
                TextInput(
                    display = charFragVM.nameInput.collectAsState().value,
                    onValueChange = {
                        charFragVM.setNameInput(nameIn = it)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    label = stringResource(id = R.string.nameText)
                )

                //class, race, and level dropdown items
                charFragVM.dropdownList.forEach {dropdown ->
                    //get which options qualify for dropdown display
                    val qualifyOption =
                        if(dropdown.data.nameRef == R.string.levelText)
                            {levelString: String ->
                                charFragVM.getValidLevel(
                                    levelString = levelString,
                                    firstLoop = true
                                )
                            }
                        else {_: String -> true}

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedDropdown(
                            data = dropdown.data,
                            modifier = Modifier
                                .weight(dropdown.weight),
                            isOpenable = dropdown.isOpenable(),
                            unopenFunc = {dropdown.failedOpen(context)},
                            qualifyOption = qualifyOption,
                            itemSelection = {
                                maxNumVM.updateMaximums()
                                maxNumVM.updateExpenditures()
                            }
                        )

                        if (dropdown.weight != 1f) {
                            DetailButton(
                                onClick = {dropdown.detailOpen()},
                                modifier = Modifier
                                    .weight(1f - dropdown.weight)
                            )
                        }
                    }

                    //display if at class object and if next level's class is different
                    if(dropdown.data.nameRef == R.string.classLabel &&
                        charFragVM.getClassChanged()){
                        //notify of changing class
                        InfoRow(label = stringResource(R.string.classChangedNotice)){modifier, _ ->
                            //display next level's class name
                            Text(
                                text = stringArrayResource(id = R.array.classArray)[charFragVM.getNextLevelClass()],
                                modifier = modifier
                            )
                        }
                    }
                }

                //experience point input
                NumberInput(
                    inputText = charFragVM.experiencePoints.collectAsState().value,
                    inputFunction = {charFragVM.setExp(expVal = it.toInt())},
                    emptyFunction = {charFragVM.setExp(display = "")},
                    refill = {charFragVM.currentExp()},
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    alignment = TextAlign.Left,
                    label = stringResource(id  = R.string.expText)
                )
            }
        }

        item{Spacer(modifier = Modifier.height(20.dp))}

        //display gender bonus selection if the character is a duk'zarist
        item {
            if(charFragVM.raceDropdown.data.output.collectAsState().value == 6 ||
                charFragVM.magPaladinOpen.collectAsState().value) {
                GeneralCard {
                    if (charFragVM.raceDropdown.data.output.collectAsState().value == 6) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                        ) {
                            //display checkbox for selection
                            Checkbox(
                                checked = charFragVM.isMale.collectAsState().value,
                                onCheckedChange = {charFragVM.toggleGender()},
                                modifier = Modifier
                                    .weight(0.1f)
                            )
                            //display current selection
                            AnimatedContent(
                                targetState = stringResource(id = charFragVM.genderString.collectAsState().value),
                                modifier = Modifier
                                    .clickable {charFragVM.toggleGender()}
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
                            //checkbox for selection
                            Checkbox(
                                checked = charFragVM.magPaladin.collectAsState().value,
                                onCheckedChange = {charFragVM.toggleMagPaladin()},
                                modifier = Modifier
                                    .weight(0.1f)
                            )

                            //prompt for paladin's magic abilities
                            Text(
                                text = stringResource(id = R.string.isMagPaladin),
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

        item{Spacer(modifier = Modifier.height(20.dp))}

        item{
            GeneralCard{
                //primary characteristic table
                //table header row
                Row {
                    //column buffer
                    Spacer(modifier = Modifier.weight(0.2f))

                    //score header
                    Text(
                        text = stringResource(id = R.string.scoreLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.weight(0.01f))

                    //level bonus input header
                    Text(
                        text = stringResource(id = R.string.bonusLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //bonus header
                    Text(
                        text = stringResource(id = R.string.specialLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )

                    //mod header
                    Text(
                        text = stringResource(id = R.string.modLabel),
                        modifier = Modifier
                            .weight(0.2f),
                        textAlign = TextAlign.Center
                    )
                }

                //create row for each primary characteristic
                charFragVM.primaryDataList.forEach{primary ->
                    PrimaryRow(
                        primeItem = primary,
                        charFragVM = charFragVM
                    )
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
                            if (it.toInt() <= 10 && !charFragVM.setAppearInput(appearance = it.toInt()))
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.appearanceFailure),
                                    Toast.LENGTH_LONG
                                ).show()
                        },
                        emptyFunction = {
                            //clear input if user can change it
                            if (charFragVM.isNotUnattractive())
                                charFragVM.setAppearInput(display = "")
                        },
                        refill = {charFragVM.currentAppearance()},
                        modifier = Modifier
                            .weight(0.5f),
                        label = stringResource(id = R.string.appearance)
                    )

                    Spacer(Modifier.weight(0.01f))

                    //create input for a character's gnosis
                    NumberInput(
                        inputText = charFragVM.gnosisDisplay.collectAsState().value,
                        inputFunction = {charFragVM.setGnosisDisplay(gnosisInput = it.toInt())},
                        emptyFunction = {charFragVM.setGnosisDisplay(display = "")},
                        refill = {charFragVM.currentGnosis()},
                        modifier = Modifier
                            .weight(0.5f),
                        label = stringResource(id = R.string.gnosisLabel)
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                //display character's size category
                InfoRow(
                    stringResource(id = R.string.sizeCat)
                ){modifier, _ ->
                    AnimatedContent(
                        targetState = charFragVM.sizeInput.collectAsState().value,
                        modifier = modifier,
                        transitionSpec = numberScroll,
                        label = "sizeDisplay"
                    ){
                        Text(text = "$it")
                    }
                }

                //display character's movement value
                InfoRow(
                    label = stringResource(id = R.string.movement)
                ){modifier, _ ->
                    AnimatedContent(
                        targetState = charFragVM.getCharMovement(),
                        modifier = modifier,
                        transitionSpec = numberScroll,
                        label = "movementDisplay"
                    ) {
                        Text(
                            text =
                                if(it == 0)
                                    stringResource(id = charFragVM.movementDisplay.collectAsState().value)
                                else
                                    stringResource(id = charFragVM.movementDisplay.collectAsState().value, it)
                        )
                    }
                }

                //display character's weight index
                InfoRow(
                    label = stringResource(id = R.string.weightIndex)
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
                                    stringResource(id = charFragVM.weightIndex.collectAsState().value)
                                else
                                    stringResource(id = charFragVM.weightIndex.collectAsState().value, it)
                        )
                    }
                }
            }
        }

        item{Spacer(modifier = Modifier.height(15.dp))}
    }

    if(charFragVM.classDetailOpen.collectAsState().value)
        DetailAlert(
            title = stringArrayResource(id = R.array.classArray)[charFragVM.classDropdown.data.output.collectAsState().value],
            item = charFragVM,
            closeFunc = {charFragVM.toggleClassDetailOpen()}
        )

    //display racial detail page
    if(charFragVM.raceDetailOpen.collectAsState().value)
        DetailAlert(
            title = stringArrayResource(id = R.array.raceArray)[charFragVM.raceDropdown.data.output.collectAsState().value],
            item = charFragVM,
            closeFunc = {charFragVM.toggleRaceDetailOpen()}
        )

    //display specific racial advantage details
    if(charFragVM.raceAdvantageOpen.collectAsState().value)
        DetailAlert(
            title = stringResource(id = charFragVM.racialDisplayed.collectAsState().value.name),
            item = charFragVM.racialDisplayed.collectAsState().value,
            closeFunc = {charFragVM.toggleRacialAdvantageOpen()}
        )
}

/**
 * Create a row for the primary characteristics table.
 *
 * @param primeItem primary characteristic data to display
 * @param charFragVM viewModel that manages this fragment
 */
@Composable
private fun PrimaryRow(
    primeItem: CharacterFragmentViewModel.PrimeCharacteristicData,
    charFragVM: CharacterFragmentViewModel
){
    //get current context
    val context = LocalContext.current

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
                //change input and other necessary items if able and in legal range
                if(charFragVM.getChangeable() && it.toInt() in 1..20)
                    primeItem.setInput(statVal = it.toInt())
                //notify user of failure do to not currently changeable
                else if(!charFragVM.getChangeable())
                    Toast.makeText(context, R.string.changeAtZero, Toast.LENGTH_LONG).show()
            },
            emptyFunction = {primeItem.setInput(display = "")},
            refill = {primeItem.currentInput()},
            modifier = Modifier
                .weight(0.2f)
        )

        Spacer(modifier = Modifier.weight(0.01f))

        //level bonus input
        NumberInput(
            inputText = primeItem.bonusInput.collectAsState().value,
            inputFunction = {primeItem.setBonusInput(lvlBonus = it.toInt())},
            emptyFunction = {primeItem.setBonusInput(display = "")},
            refill = {primeItem.currentBonus()},
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

    Spacer(modifier = Modifier.height(5.dp))
}

@Preview
@Composable
fun CharacterPreview(){
    val charInstance = BaseCharacter()
    charInstance.classes.setOwnClass(3)
    charInstance.setOwnRace(6)

    val charFragVM = CharacterFragmentViewModel(charInstance)
    charFragVM.toggleGender()

    CharacterPageFragment(
        charFragVM,
        HomePageViewModel(charInstance)
    )
}
