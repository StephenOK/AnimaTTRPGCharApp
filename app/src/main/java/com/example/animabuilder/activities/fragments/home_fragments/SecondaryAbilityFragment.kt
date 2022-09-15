package com.example.animabuilder.activities.fragments.home_fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryCharacteristic
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryList

/**
 * Fragment to be displayed when working with secondary characteristics
 */

@Composable
fun SecondaryAbilityFragment(
    charInstance: BaseCharacter
) {
    var charList: SecondaryList = charInstance.secondaryList

    MaterialTheme(

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            makeTableDisplay(athleticsTable(charInstance), R.string.athleticsLabel)
            makeTableDisplay(socialTable(charInstance), R.string.socialLabel)
            makeTableDisplay(percTable(charInstance), R.string.perceptionLabel)
            makeTableDisplay(intelTable(charInstance), R.string.intellectualLabel)
            makeTableDisplay(vigorTable(charInstance), R.string.vigorLabel)
            makeTableDisplay(subterTable(charInstance), R.string.subterfugeLabel)
            makeTableDisplay(creatTable(charInstance), R.string.creativeLabel)
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
    item: SecondaryCharacteristic
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
                if(it[it.length - 1] == '\n')
                    keyboardActive?.hide()

                else {
                    userInput.value = it

                    val calcNum =
                        if (userInput.value == "")
                            0
                        else
                            userInput.value.trim().toInt()

                    //apply input to SecondaryCharacteristic
                    item.setPointsApplied(calcNum)

                    //get new amount of points spent
                    charInstance.spentTotal += item.devPerPoint * (calcNum - preValue)

                    //update text
                    total.value = item.total.toString()

                    //check if spent is  valid
                    //if(charInstance.spentTotal < charInstance.devPT)
                    //make text black for valid
                    //textColor.value = Color.BLACK

                    //else
                    //make text red for invalid
                    //textColor.value = Color.RED

                    preValue = calcNum
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

private fun athleticsTable(charInstance: BaseCharacter): @Composable () -> Unit{
    return (@Composable{
        Column {
            rowHead()
            makeRow(charInstance, R.string.acrobaticsLabel, charInstance.secondaryList.acrobatics)
            makeRow(charInstance, R.string.athleticsLabel, charInstance.secondaryList.athletics)
            makeRow(charInstance, R.string.climbLabel, charInstance.secondaryList.climb)
            makeRow(charInstance, R.string.jumpLabel, charInstance.secondaryList.jump)
            makeRow(charInstance, R.string.rideLabel, charInstance.secondaryList.ride)
            makeRow(charInstance, R.string.swimLabel, charInstance.secondaryList.swim)
        }
    })
}

@Composable
private fun socialTable(charInstance: BaseCharacter): @Composable () -> Unit{
    return (@Composable{
        Column {
            rowHead()
            makeRow(charInstance, R.string.intimidateLabel, charInstance.secondaryList.intimidate)
            makeRow(charInstance, R.string.leadershipLabel, charInstance.secondaryList.leadership)
            makeRow(charInstance, R.string.persuasionLabel, charInstance.secondaryList.persuasion)
            makeRow(charInstance, R.string.styleLabel, charInstance.secondaryList.style)
        }
    })
}

@Composable
private fun percTable(charInstance: BaseCharacter): @Composable () -> Unit{
    return (@Composable{
        Column{
            rowHead()
            makeRow(charInstance, R.string.noticeLabel, charInstance.secondaryList.notice)
            makeRow(charInstance, R.string.searchLabel, charInstance.secondaryList.search)
            makeRow(charInstance, R.string.trackLabel, charInstance.secondaryList.track)
        }
    })
}

@Composable
private fun intelTable(charInstance: BaseCharacter): @Composable () -> Unit{
    return (@Composable {
        Column{
            rowHead()
            makeRow(charInstance, R.string.animalLabel, charInstance.secondaryList.animals)
            makeRow(charInstance, R.string.appraiseLabel, charInstance.secondaryList.appraise)
            makeRow(charInstance, R.string.herbalLabel, charInstance.secondaryList.herbalLore)
            makeRow(charInstance, R.string.histLabel, charInstance.secondaryList.history)
            makeRow(charInstance, R.string.memLabel, charInstance.secondaryList.memorize)
            makeRow(charInstance, R.string.mAppraiseLabel, charInstance.secondaryList.magicAppraise)
            makeRow(charInstance, R.string.medLabel, charInstance.secondaryList.medic)
            makeRow(charInstance, R.string.navLabel, charInstance.secondaryList.navigate)
            makeRow(charInstance, R.string.occultLabel, charInstance.secondaryList.occult)
            makeRow(charInstance, R.string.scienceLabel, charInstance.secondaryList.sciences)
        }
    })
}

@Composable
private fun vigorTable(charInstance: BaseCharacter): @Composable () -> Unit{
    return (@Composable{
        Column{
            rowHead()
            makeRow(charInstance, R.string.composureLabel, charInstance.secondaryList.composure)
            makeRow(charInstance, R.string.strFeatLabel, charInstance.secondaryList.strengthFeat)
            makeRow(charInstance, R.string.resistPainLabel, charInstance.secondaryList.resistPain)
        }
    })
}

@Composable
private fun subterTable(charInstance: BaseCharacter): @Composable () -> Unit{
    return (@Composable{
        Column{
            rowHead()
            makeRow(charInstance, R.string.disguiseLabel, charInstance.secondaryList.disguise)
            makeRow(charInstance, R.string.hideLabel, charInstance.secondaryList.hide)
            makeRow(charInstance, R.string.lockpickLabel, charInstance.secondaryList.lockPick)
            makeRow(charInstance, R.string.poisonLabel, charInstance.secondaryList.poisons)
            makeRow(charInstance, R.string.theftLabel, charInstance.secondaryList.theft)
            makeRow(charInstance, R.string.stealthLabel, charInstance.secondaryList.stealth)
            makeRow(charInstance, R.string.trapLabel, charInstance.secondaryList.trapLore)
        }
    })
}

@Composable
private fun creatTable(charInstance: BaseCharacter): @Composable () -> Unit{
    return (@Composable{
        Column{
            rowHead()
            makeRow(charInstance, R.string.artLabel, charInstance.secondaryList.art)
            makeRow(charInstance, R.string.danceLabel, charInstance.secondaryList.dance)
            makeRow(charInstance, R.string.forgeLabel, charInstance.secondaryList.forging)
            makeRow(charInstance, R.string.musicLabel, charInstance.secondaryList.music)
            makeRow(charInstance, R.string.sleightLabel, charInstance.secondaryList.sleightHand)
        }
    })
}