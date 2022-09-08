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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
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

class SecondaryAbilityFragment : Fragment() {

    private var charInstance: BaseCharacter = BaseCharacter()
    private var charList: SecondaryList = charInstance.secondaryList

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //receive BaseCharacter from bundle
        val fromActivity = arguments
        charInstance = fromActivity!!.getSerializable("Character") as BaseCharacter
        charList = charInstance.secondaryList

        return ComposeView(requireContext()).apply {
            setContent {

                MaterialTheme(

                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        makeTableDisplay(athleticsTable(), R.string.athleticsLabel)
                        makeTableDisplay(socialTable(), R.string.socialLabel)
                        makeTableDisplay(percTable(), R.string.perceptionLabel)
                        makeTableDisplay(intelTable(), R.string.intellectualLabel)
                        makeTableDisplay(vigorTable(), R.string.vigorLabel)
                        makeTableDisplay(subterTable(), R.string.subterfugeLabel)
                        makeTableDisplay(creatTable(), R.string.creativeLabel)
                    }
                }
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

    @Composable
    private fun makeRow(
        stringReference: Int,
        item: SecondaryCharacteristic
    ){
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
                    userInput.value = it

                    val calcNum =
                        if(userInput.value == "")
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
                    if(charInstance.spentTotal < charInstance.devPT)
                        //make text black for valid
                        //textColor.value = Color.BLACK

                    else
                        //make text red for invalid
                        //textColor.value = Color.RED

                    preValue = calcNum
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

    private fun athleticsTable(): @Composable () -> Unit{
        return (@Composable{
            Column {
                rowHead()
                makeRow(R.string.acrobaticsLabel, charList.acrobatics)
                makeRow(R.string.athleticsLabel, charList.athletics)
                makeRow(R.string.climbLabel, charList.climb)
                makeRow(R.string.jumpLabel, charList.jump)
                makeRow(R.string.rideLabel, charList.ride)
                makeRow(R.string.swimLabel, charList.swim)
            }
        })
    }

    @Composable
    private fun socialTable(): @Composable () -> Unit{
        return (@Composable{
            Column {
                rowHead()
                makeRow(R.string.intimidateLabel, charList.intimidate)
                makeRow(R.string.leadershipLabel, charList.leadership)
                makeRow(R.string.persuasionLabel, charList.persuasion)
                makeRow(R.string.styleLabel, charList.style)
            }
        })
    }

    @Composable
    private fun percTable(): @Composable () -> Unit{
        return (@Composable{
            Column{
                rowHead()
                makeRow(R.string.noticeLabel, charList.notice)
                makeRow(R.string.searchLabel, charList.search)
                makeRow(R.string.trackLabel, charList.track)
            }
        })
    }

    @Composable
    private fun intelTable(): @Composable () -> Unit{
        return (@Composable {
            Column{
                rowHead()
                makeRow(R.string.animalLabel, charList.animals)
                makeRow(R.string.appraiseLabel, charList.appraise)
                makeRow(R.string.herbalLabel, charList.herbalLore)
                makeRow(R.string.histLabel, charList.history)
                makeRow(R.string.memLabel, charList.memorize)
                makeRow(R.string.mAppraiseLabel, charList.magicAppraise)
                makeRow(R.string.medLabel, charList.medic)
                makeRow(R.string.navLabel, charList.navigate)
                makeRow(R.string.occultLabel, charList.occult)
                makeRow(R.string.scienceLabel, charList.sciences)
            }
        })
    }

    @Composable
    private fun vigorTable(): @Composable () -> Unit{
        return (@Composable{
            Column{
                rowHead()
                makeRow(R.string.composureLabel, charList.composure)
                makeRow(R.string.strFeatLabel, charList.strengthFeat)
                makeRow(R.string.resistPainLabel, charList.resistPain)
            }
        })
    }

    @Composable
    private fun subterTable(): @Composable () -> Unit{
        return (@Composable{
            Column{
                rowHead()
                makeRow(R.string.disguiseLabel, charList.disguise)
                makeRow(R.string.hideLabel, charList.hide)
                makeRow(R.string.lockpickLabel, charList.lockPick)
                makeRow(R.string.poisonLabel, charList.poisons)
                makeRow(R.string.theftLabel, charList.theft)
                makeRow(R.string.stealthLabel, charList.stealth)
                makeRow(R.string.trapLabel, charList.trapLore)
            }
        })
    }

    @Composable
    private fun creatTable(): @Composable () -> Unit{
        return (@Composable{
            Column{
                rowHead()
                makeRow(R.string.artLabel, charList.art)
                makeRow(R.string.danceLabel, charList.dance)
                makeRow(R.string.forgeLabel, charList.forging)
                makeRow(R.string.musicLabel, charList.music)
                makeRow(R.string.sleightLabel, charList.sleightHand)
            }
        })
    }
}