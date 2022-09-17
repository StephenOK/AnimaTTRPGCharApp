package com.example.animabuilder.activities.fragments.home_fragments

import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryCharacteristic

/**
 * Fragment to be displayed when working with secondary characteristics
 */

@Composable
fun SecondaryAbilityFragment(
    charInstance: BaseCharacter,
    spentDisplay: MutableState<Int>
) {
    MaterialTheme(

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            makeTableDisplay(athleticsTable(charInstance, spentDisplay), R.string.athleticsLabel)
            makeTableDisplay(socialTable(charInstance, spentDisplay), R.string.socialLabel)
            makeTableDisplay(percTable(charInstance, spentDisplay), R.string.perceptionLabel)
            makeTableDisplay(intelTable(charInstance, spentDisplay), R.string.intellectualLabel)
            makeTableDisplay(vigorTable(charInstance, spentDisplay), R.string.vigorLabel)
            makeTableDisplay(subterTable(charInstance, spentDisplay), R.string.subterfugeLabel)
            makeTableDisplay(creatTable(charInstance, spentDisplay), R.string.creativeLabel)
        }
    }
}

@Composable
private fun makeTableDisplay(
    tableFunc: @Composable () -> Unit,
    stringReference: Int
){
    val active = remember{mutableStateOf(false)}

    Button(
        onClick = {active.value = !active.value},
        modifier = Modifier.width(250.dp)
    ){
        Text(
            text = stringResource(stringReference)
        )
    }
    AnimatedVisibility(visible = active.value){
        tableFunc()
    }
}

@Composable
private fun rowHead(){
    Row{
        Spacer(modifier = Modifier.weight(0.25f))
        Text(
            text = stringResource(R.string.pointsLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.25f)
        )
        Text(
            text = stringResource(R.string.modLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )
        Text(
            text = stringResource(R.string.classLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )
        Text(
            text = stringResource(R.string.natLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )
        Text(
            text = stringResource(R.string.totalLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun makeRow(
    charInstance: BaseCharacter,
    stringReference: Int,
    item: SecondaryCharacteristic,
    spentDisplay: MutableState<Int>
){
    val charList = charInstance.secondaryList
    val keyboardActive = LocalSoftwareKeyboardController.current

    val userInput = remember{mutableStateOf(item.pointsApplied.toString())}
    var preValue = item.pointsApplied

    val textColor = remember{mutableStateOf(Color(0xff000000))}

    val checkedState = remember{mutableStateOf(item.bonusApplied)}
    val checkedText =
        if(checkedState.value)
            remember{mutableStateOf(R.string.natTaken)}
        else
            remember{mutableStateOf(R.string.natNotTaken)}

    val total = remember{mutableStateOf(item.total.toString())}

    Row(
        //modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = stringResource(stringReference),
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(0.25f))

        TextField(
            value = userInput.value,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            onValueChange = {
                try{
                    preValue = secondaryInput(charInstance, item, it.toInt(), preValue, spentDisplay, total)
                    userInput.value = it
                }catch(e: NumberFormatException){
                    if(it == "") {
                        preValue = secondaryInput(charInstance, item, 0, preValue, spentDisplay, total)
                        userInput.value = it
                    }
                    else if(it[it.length - 1] == '\n')
                        keyboardActive?.hide()
                }
            },

            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier.weight(0.25f)
        )

        Text(text = item.modVal.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f))

        Text(text = item.pointsFromClass.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f))

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it

                //if user is applying a natural bonus
                if (checkedState.value) {
                    //check if either no points are applied or if no more bonuses are available
                    if (item.pointsApplied == 0 || !charList.incrementNat(true))
                        //prevent bonus from applying
                        checkedState.value = false
                    else {
                        //apply bonus and display stat change
                        item.setBonusApplied(true)
                        checkedText.value = R.string.natTaken
                    }
                }

                //if user is removing a natural bonus
                else {
                    //remove bonus and change text accordingly
                    charList.incrementNat(false)
                    item.setBonusApplied(false)
                    checkedText.value = R.string.natNotTaken
                }

                //update total text
                total.value = item.total.toString()
            },

            modifier = Modifier.weight(0.125f)
        )

        Text(text = total.value,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f))
    }
}

private fun secondaryInput(
    charInstance: BaseCharacter,
    item: SecondaryCharacteristic,
    input: Int,
    preValue: Int,
    spentDisplay: MutableState<Int>,
    total: MutableState<String>
): Int{
    item.setPointsApplied(input)
    charInstance.spentTotal += item.devPerPoint * (input - preValue)
    spentDisplay.value = charInstance.spentTotal
    total.value = item.total.toString()

    //check if spent is  valid
    //if(charInstance.spentTotal < charInstance.devPT)
    //make text black for valid
    //textColor.value = Color.BLACK

    //else
    //make text red for invalid
    //textColor.value = Color.RED

    return input
}

private fun athleticsTable(charInstance: BaseCharacter, spentDisplay: MutableState<Int>): @Composable () -> Unit{
    return (@Composable{
        Column {
            rowHead()
            makeRow(charInstance, R.string.acrobaticsLabel, charInstance.secondaryList.acrobatics, spentDisplay)
            makeRow(charInstance, R.string.athleticsLabel, charInstance.secondaryList.athletics, spentDisplay)
            makeRow(charInstance, R.string.climbLabel, charInstance.secondaryList.climb, spentDisplay)
            makeRow(charInstance, R.string.jumpLabel, charInstance.secondaryList.jump, spentDisplay)
            makeRow(charInstance, R.string.rideLabel, charInstance.secondaryList.ride, spentDisplay)
            makeRow(charInstance, R.string.swimLabel, charInstance.secondaryList.swim, spentDisplay)
        }
    })
}

@Composable
private fun socialTable(charInstance: BaseCharacter, spentDisplay: MutableState<Int>): @Composable () -> Unit{
    return (@Composable{
        Column {
            rowHead()
            makeRow(charInstance, R.string.intimidateLabel, charInstance.secondaryList.intimidate, spentDisplay)
            makeRow(charInstance, R.string.leadershipLabel, charInstance.secondaryList.leadership, spentDisplay)
            makeRow(charInstance, R.string.persuasionLabel, charInstance.secondaryList.persuasion, spentDisplay)
            makeRow(charInstance, R.string.styleLabel, charInstance.secondaryList.style, spentDisplay)
        }
    })
}

@Composable
private fun percTable(charInstance: BaseCharacter, spentDisplay: MutableState<Int>): @Composable () -> Unit{
    return (@Composable{
        Column{
            rowHead()
            makeRow(charInstance, R.string.noticeLabel, charInstance.secondaryList.notice, spentDisplay)
            makeRow(charInstance, R.string.searchLabel, charInstance.secondaryList.search, spentDisplay)
            makeRow(charInstance, R.string.trackLabel, charInstance.secondaryList.track, spentDisplay)
        }
    })
}

@Composable
private fun intelTable(charInstance: BaseCharacter, spentDisplay: MutableState<Int>): @Composable () -> Unit{
    return (@Composable {
        Column{
            rowHead()
            makeRow(charInstance, R.string.animalLabel, charInstance.secondaryList.animals, spentDisplay)
            makeRow(charInstance, R.string.appraiseLabel, charInstance.secondaryList.appraise, spentDisplay)
            makeRow(charInstance, R.string.herbalLabel, charInstance.secondaryList.herbalLore, spentDisplay)
            makeRow(charInstance, R.string.histLabel, charInstance.secondaryList.history, spentDisplay)
            makeRow(charInstance, R.string.memLabel, charInstance.secondaryList.memorize, spentDisplay)
            makeRow(charInstance, R.string.mAppraiseLabel, charInstance.secondaryList.magicAppraise, spentDisplay)
            makeRow(charInstance, R.string.medLabel, charInstance.secondaryList.medic, spentDisplay)
            makeRow(charInstance, R.string.navLabel, charInstance.secondaryList.navigate, spentDisplay)
            makeRow(charInstance, R.string.occultLabel, charInstance.secondaryList.occult, spentDisplay)
            makeRow(charInstance, R.string.scienceLabel, charInstance.secondaryList.sciences, spentDisplay)
        }
    })
}

@Composable
private fun vigorTable(charInstance: BaseCharacter, spentDisplay: MutableState<Int>): @Composable () -> Unit{
    return (@Composable{
        Column{
            rowHead()
            makeRow(charInstance, R.string.composureLabel, charInstance.secondaryList.composure, spentDisplay)
            makeRow(charInstance, R.string.strFeatLabel, charInstance.secondaryList.strengthFeat, spentDisplay)
            makeRow(charInstance, R.string.resistPainLabel, charInstance.secondaryList.resistPain, spentDisplay)
        }
    })
}

@Composable
private fun subterTable(charInstance: BaseCharacter, spentDisplay: MutableState<Int>): @Composable () -> Unit{
    return (@Composable{
        Column{
            rowHead()
            makeRow(charInstance, R.string.disguiseLabel, charInstance.secondaryList.disguise, spentDisplay)
            makeRow(charInstance, R.string.hideLabel, charInstance.secondaryList.hide, spentDisplay)
            makeRow(charInstance, R.string.lockpickLabel, charInstance.secondaryList.lockPick, spentDisplay)
            makeRow(charInstance, R.string.poisonLabel, charInstance.secondaryList.poisons, spentDisplay)
            makeRow(charInstance, R.string.theftLabel, charInstance.secondaryList.theft, spentDisplay)
            makeRow(charInstance, R.string.stealthLabel, charInstance.secondaryList.stealth, spentDisplay)
            makeRow(charInstance, R.string.trapLabel, charInstance.secondaryList.trapLore, spentDisplay)
        }
    })
}

@Composable
private fun creatTable(charInstance: BaseCharacter, spentDisplay: MutableState<Int>): @Composable () -> Unit{
    return (@Composable{
        Column{
            rowHead()
            makeRow(charInstance, R.string.artLabel, charInstance.secondaryList.art, spentDisplay)
            makeRow(charInstance, R.string.danceLabel, charInstance.secondaryList.dance, spentDisplay)
            makeRow(charInstance, R.string.forgeLabel, charInstance.secondaryList.forging, spentDisplay)
            makeRow(charInstance, R.string.musicLabel, charInstance.secondaryList.music, spentDisplay)
            makeRow(charInstance, R.string.sleightLabel, charInstance.secondaryList.sleightHand, spentDisplay)
        }
    })
}