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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.animabuilder.activities.charInstance
import com.example.animabuilder.activities.numberCatcher
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryCharacteristic

/**
 * Fragment to be displayed when working with secondary characteristics
 *
 * charInstance: character to work on
 * spentDisplay: bottombar mutable of spent development points
 */

@Composable
fun SecondaryAbilityFragment(updateBottomBar: () -> Unit) {
    MaterialTheme{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            //make tables for each of the secondary characteristic categories
            MakeTableDisplay(athleticsTable(charInstance, updateBottomBar), R.string.athleticsLabel)
            MakeTableDisplay(socialTable(charInstance, updateBottomBar), R.string.socialLabel)
            MakeTableDisplay(percTable(charInstance, updateBottomBar), R.string.perceptionLabel)
            MakeTableDisplay(intelTable(charInstance, updateBottomBar), R.string.intellectualLabel)
            MakeTableDisplay(vigorTable(charInstance, updateBottomBar), R.string.vigorLabel)
            MakeTableDisplay(subterTable(charInstance, updateBottomBar), R.string.subterfugeLabel)
            MakeTableDisplay(creatTable(charInstance, updateBottomBar), R.string.creativeLabel)
        }
    }
}

/**
 * Creates a toggle button to display the associated secondary characteristic table
 *
 * tableFund: function for the table display
 * stringReference: name of the characteristic group
 */
@Composable
private fun MakeTableDisplay(
    tableFunc: @Composable () -> Unit,
    stringReference: Int
){
    //open state of the table
    val active = remember{mutableStateOf(false)}

    //toggle button for the table
    Button(
        onClick = {active.value = !active.value},
        modifier = Modifier.width(250.dp)
    ){
        //button label
        Text(
            text = stringResource(stringReference)
        )
    }

    //visibility group for the table
    AnimatedVisibility(visible = active.value){
        tableFunc()
    }
}

/**
 * Header row for a secondary characteristic table
 */
@Composable
private fun RowHead(){
    Row{
        //column buffer
        Spacer(modifier = Modifier.weight(0.25f))

        //input point label
        Text(
            text = stringResource(R.string.pointsLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.25f)
        )

        //mod value label
        Text(
            text = stringResource(R.string.modLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )

        //class bonus label
        Text(
            text = stringResource(R.string.classLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )

        //natural bonus label
        Text(
            text = stringResource(R.string.natLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )

        //characteristic total label
        Text(
            text = stringResource(R.string.totalLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )
    }
}

/**
 * Make on row for a secondary characteristic in a table
 *
 * charInstance: character to work on
 * stringReference: name of the secondary characteristic
 * item: characteristic item to work on
 * spentDisplay: mutable value of the characteristic's score input
 */
@Composable
private fun MakeRow(
    charInstance: BaseCharacter,
    stringReference: Int,
    item: SecondaryCharacteristic,
    updateBottomBar: () -> Unit
){
    //secondary characteristic list from the character
    val charList = charInstance.secondaryList

    //initial score stat
    val userInput = remember{mutableStateOf(item.pointsApplied.toString())}

    //color of the score label's text
    val textColor = remember{mutableStateOf(Color.Black)}

    //state of natural bonus taken
    val checkedState = remember{mutableStateOf(item.bonusApplied)}

    //text associated with the natural bonus check
    val checkedText =
        if(checkedState.value)
            remember{mutableStateOf(R.string.natTaken)}
        else
            remember{mutableStateOf(R.string.natNotTaken)}

    //string to display for characteristic's total
    val total = remember{mutableStateOf(item.total.toString())}

    Row(
        //modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        //row's label
        Text(text = stringResource(stringReference),
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(0.25f))

        //user input for the stat's score
        TextField(
            value = userInput.value,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            onValueChange = {
                numberCatcher(it,
                    {input: String ->
                        secondaryInput(charInstance, item, input.toInt(), textColor, total)
                        userInput.value = input},
                    {secondaryInput(charInstance, item, 0, textColor, total)
                        userInput.value = ""}
                )

                checkedState.value = item.bonusApplied
                charInstance.updateTotalSpent()
                updateBottomBar()
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, color = textColor.value),
            modifier = Modifier.weight(0.25f)
        )

        //display associated mod value
        Text(text = item.modVal.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f))

        //display associated class bonus value
        Text(text = item.pointsFromClass.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f))

        //checkbox to apply natural bonus
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                //update checkbox state
                checkedState.value = it

                //if user is applying a natural bonus
                if (it) {
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

        //display for characteristic's total value
        Text(text = total.value,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f))
    }
}

/**
 * Update characteristic and development point values for an inputted score value
 *
 * charInstance: character to work on
 * item: secondary characteristic to operate with
 * input: new value of the characteristic's score
 * spentDisplay: display value for the bottom bar of the spent point total
 * textColor: color the the associated textfield's text
 * total: secondary characteristic's final value
 */
private fun secondaryInput(
    charInstance: BaseCharacter,
    item: SecondaryCharacteristic,
    input: Int,
    textColor: MutableState<Color>,
    total: MutableState<String>
){
    //set characteristic's point value
    item.setPointsApplied(input)

    //update text displays
    total.value = item.total.toString()

    //check if spent is  valid
    if(charInstance.spentTotal < charInstance.devPT)
        //make text black for valid
        textColor.value = Color.Black

    else
        //make text red for invalid
        textColor.value = Color.Red
}

//composable for athletic characteristics
private fun athleticsTable(charInstance: BaseCharacter, updateBottomBar: () -> Unit): @Composable () -> Unit{
    return (@Composable{
        Column {
            RowHead()
            MakeRow(charInstance, R.string.acrobaticsLabel, charInstance.secondaryList.acrobatics, updateBottomBar)
            MakeRow(charInstance, R.string.athleticsLabel, charInstance.secondaryList.athletics, updateBottomBar)
            MakeRow(charInstance, R.string.climbLabel, charInstance.secondaryList.climb, updateBottomBar)
            MakeRow(charInstance, R.string.jumpLabel, charInstance.secondaryList.jump, updateBottomBar)
            MakeRow(charInstance, R.string.rideLabel, charInstance.secondaryList.ride, updateBottomBar)
            MakeRow(charInstance, R.string.swimLabel, charInstance.secondaryList.swim, updateBottomBar)
        }
    })
}

//composable for social characteristics
private fun socialTable(charInstance: BaseCharacter, updateBottomBar: () -> Unit): @Composable () -> Unit{
    return (@Composable{
        Column {
            RowHead()
            MakeRow(charInstance, R.string.intimidateLabel, charInstance.secondaryList.intimidate, updateBottomBar)
            MakeRow(charInstance, R.string.leadershipLabel, charInstance.secondaryList.leadership, updateBottomBar)
            MakeRow(charInstance, R.string.persuasionLabel, charInstance.secondaryList.persuasion, updateBottomBar)
            MakeRow(charInstance, R.string.styleLabel, charInstance.secondaryList.style, updateBottomBar)
        }
    })
}

//composable for perceptive characteristics
private fun percTable(charInstance: BaseCharacter, updateBottomBar: () -> Unit): @Composable () -> Unit{
    return (@Composable{
        Column{
            RowHead()
            MakeRow(charInstance, R.string.noticeLabel, charInstance.secondaryList.notice, updateBottomBar)
            MakeRow(charInstance, R.string.searchLabel, charInstance.secondaryList.search, updateBottomBar)
            MakeRow(charInstance, R.string.trackLabel, charInstance.secondaryList.track, updateBottomBar)
        }
    })
}

//composable for intellectual characteristics
private fun intelTable(charInstance: BaseCharacter, updateBottomBar: () -> Unit): @Composable () -> Unit{
    return (@Composable {
        Column{
            RowHead()
            MakeRow(charInstance, R.string.animalLabel, charInstance.secondaryList.animals, updateBottomBar)
            MakeRow(charInstance, R.string.appraiseLabel, charInstance.secondaryList.appraise, updateBottomBar)
            MakeRow(charInstance, R.string.herbalLabel, charInstance.secondaryList.herbalLore, updateBottomBar)
            MakeRow(charInstance, R.string.histLabel, charInstance.secondaryList.history, updateBottomBar)
            MakeRow(charInstance, R.string.memLabel, charInstance.secondaryList.memorize, updateBottomBar)
            MakeRow(charInstance, R.string.mAppraiseLabel, charInstance.secondaryList.magicAppraise, updateBottomBar)
            MakeRow(charInstance, R.string.medLabel, charInstance.secondaryList.medic, updateBottomBar)
            MakeRow(charInstance, R.string.navLabel, charInstance.secondaryList.navigate, updateBottomBar)
            MakeRow(charInstance, R.string.occultLabel, charInstance.secondaryList.occult, updateBottomBar)
            MakeRow(charInstance, R.string.scienceLabel, charInstance.secondaryList.sciences, updateBottomBar)
        }
    })
}

//composable for vigor characteristics
private fun vigorTable(charInstance: BaseCharacter, updateBottomBar: () -> Unit): @Composable () -> Unit{
    return (@Composable{
        Column{
            RowHead()
            MakeRow(charInstance, R.string.composureLabel, charInstance.secondaryList.composure, updateBottomBar)
            MakeRow(charInstance, R.string.strFeatLabel, charInstance.secondaryList.strengthFeat, updateBottomBar)
            MakeRow(charInstance, R.string.resistPainLabel, charInstance.secondaryList.resistPain, updateBottomBar)
        }
    })
}

//composable for subterfuge characteristics
private fun subterTable(charInstance: BaseCharacter, updateBottomBar: () -> Unit): @Composable () -> Unit{
    return (@Composable{
        Column{
            RowHead()
            MakeRow(charInstance, R.string.disguiseLabel, charInstance.secondaryList.disguise, updateBottomBar)
            MakeRow(charInstance, R.string.hideLabel, charInstance.secondaryList.hide, updateBottomBar)
            MakeRow(charInstance, R.string.lockpickLabel, charInstance.secondaryList.lockPick, updateBottomBar)
            MakeRow(charInstance, R.string.poisonLabel, charInstance.secondaryList.poisons, updateBottomBar)
            MakeRow(charInstance, R.string.theftLabel, charInstance.secondaryList.theft, updateBottomBar)
            MakeRow(charInstance, R.string.stealthLabel, charInstance.secondaryList.stealth, updateBottomBar)
            MakeRow(charInstance, R.string.trapLabel, charInstance.secondaryList.trapLore, updateBottomBar)
        }
    })
}

//composable for creative characteristics
private fun creatTable(charInstance: BaseCharacter, updateBottomBar: () -> Unit): @Composable () -> Unit{
    return (@Composable{
        Column{
            RowHead()
            MakeRow(charInstance, R.string.artLabel, charInstance.secondaryList.art, updateBottomBar)
            MakeRow(charInstance, R.string.danceLabel, charInstance.secondaryList.dance, updateBottomBar)
            MakeRow(charInstance, R.string.forgeLabel, charInstance.secondaryList.forging, updateBottomBar)
            MakeRow(charInstance, R.string.musicLabel, charInstance.secondaryList.music, updateBottomBar)
            MakeRow(charInstance, R.string.sleightLabel, charInstance.secondaryList.sleightHand, updateBottomBar)
        }
    })
}