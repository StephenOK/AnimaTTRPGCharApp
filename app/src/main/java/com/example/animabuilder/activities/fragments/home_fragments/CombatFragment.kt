package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import com.example.animabuilder.R
import com.example.animabuilder.activities.charInstance
import com.example.animabuilder.activities.numberCatcher

@Composable
fun CombatFragment(updateFunc: () -> Unit) {
    //initialize color item
    val pointColor =
        if(charInstance.validAttackDodgeBlock())
            remember{mutableStateOf(Color.Black)}
        else
            remember{mutableStateOf(Color.Red)}

    //initialize mutable states for resistance display
    val presence = remember{mutableStateOf(charInstance.presence)}

    //initialize life point data
    val baseLife = remember{mutableStateOf(charInstance.lifeBase.toString())}
    val lifeTotal = remember{mutableStateOf(charInstance.lifeMax)}
    val lifeMults = remember{mutableStateOf(charInstance.lifeMultsTaken.toString())}

    //determine class bonuses to combat abilities
    val classAttack = remember{mutableStateOf((charInstance.attackPerLevel * charInstance.lvl).toString())}
    val classBlock = remember{mutableStateOf((charInstance.blockPerLevel * charInstance.lvl).toString())}
    val classDodge = remember{mutableStateOf((charInstance.dodgePerLevel * charInstance.lvl).toString())}
    val classWear = remember{mutableStateOf((charInstance.wearPerLevel * charInstance.lvl).toString())}

    //get current attack ability values
    val totalAttack = remember{mutableStateOf(charInstance.attack.toString())}
    val totalBlock = remember{mutableStateOf(charInstance.block.toString())}
    val totalDodge = remember{mutableStateOf(charInstance.dodge.toString())}
    val totalWear = remember{mutableStateOf(charInstance.wearArmor.toString())}

    val disSpec = remember{mutableStateOf(charInstance.rdSpec)}
    val magSpec = remember{mutableStateOf(charInstance.rmSpec)}
    val physSpec = remember{mutableStateOf(charInstance.rphysSpec)}
    val venSpec = remember{mutableStateOf(charInstance.rvSpec)}
    val psySpec = remember{mutableStateOf(charInstance.rpsySpec)}

    val disTotal = remember{mutableStateOf(charInstance.resistDisease)}
    val magTotal = remember{mutableStateOf(charInstance.resistMag)}
    val physTotal = remember{mutableStateOf(charInstance.resistPhys)}
    val venTotal = remember{mutableStateOf(charInstance.resistVen)}
    val psyTotal = remember{mutableStateOf(charInstance.resistPsy)}

    val totalInitiative = remember{mutableStateOf(charInstance.totalInitiative.toString())}
    val fatigueText = remember{mutableStateOf(charInstance.fatigue.toString())}
    val regenText = remember{mutableStateOf(charInstance.totalRegen.toString())}

    val combatItemList = mutableListOf<CombatItemData>()

    //define combat row items
    combatItemList.add(CombatItemData(
        "Attack", remember{mutableStateOf(charInstance.pointInAttack)}, charInstance.applyAttackPoint, charInstance.modDEX,
        classAttack, pointColor, totalAttack
    ))
    combatItemList.add(CombatItemData(
        "Block", remember{mutableStateOf(charInstance.pointInBlock)}, charInstance.applyBlockPoint, charInstance.modDEX,
        classBlock, pointColor, totalBlock
    ))
    combatItemList.add(CombatItemData(
        "Dodge", remember{mutableStateOf(charInstance.pointInDodge)}, charInstance.applyDodgePoint, charInstance.modAGI,
        classDodge, pointColor, totalDodge
    ))
    combatItemList.add(CombatItemData(
        "Wear Armor", remember{mutableStateOf(charInstance.pointInWear)}, charInstance.applyWearPoint, charInstance.modSTR,
        classWear, remember { mutableStateOf(Color.Black) }, totalWear
    ))

    val resistanceList = mutableListOf<ResistanceData>()

    //define resistance items
    resistanceList.add(ResistanceData(
        "DR",
        presence,
        charInstance.modCON,
        disSpec,
        disTotal
    ))
    resistanceList.add(ResistanceData(
        "MR",
        presence,
        charInstance.modPOW,
        magSpec,
        magTotal
    ))
    resistanceList.add(ResistanceData(
        "PhR",
        presence,
        charInstance.modCON,
        physSpec,
        physTotal
    ))
    resistanceList.add(ResistanceData(
        "VR",
        presence,
        charInstance.modCON,
        venSpec,
        venTotal
    ))
    resistanceList.add(ResistanceData(
        "PsR",
        presence,
        charInstance.modWP,
        psySpec,
        psyTotal
    ))

    LazyColumn{
        //create header row for combat items
        item {
            Row {
                Spacer(modifier = Modifier.weight(0.2f))
                Text(text = "Base", textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
                Text(text = "Class", textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
                Text(text = "Multiplier", textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
                Text(text = "Total", textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
            }
        }

        //character life point row
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Life Points: ", textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))

                //display life points from base and class values
                Text(text = baseLife.value, textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
                Text(
                    text = (charInstance.ownClass.lifePointsPerLevel * charInstance.lvl).toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )

                //create input for life point multiples
                TextField(
                    value = lifeMults.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        numberCatcher(it,
                            { input ->
                                charInstance.takeLifeMult(input.toInt())
                                lifeTotal.value = charInstance.lifeMax

                                lifeMults.value = input
                                updateFunc()
                            },
                            { lifeMults.value = "" }
                        )
                    },
                    modifier = Modifier.weight(0.2f)
                )

                //display life point total
                Text(text = lifeTotal.value.toString(), modifier = Modifier.weight(0.2f))
            }
        }

        //create table header for combat items
        item {
            Row {
                Spacer(modifier = Modifier.weight(0.2f))
                Text(text = "Points", modifier = Modifier.weight(0.2f))
                Text(
                    text = stringResource(R.string.modLabel),
                    modifier = Modifier.weight(0.2f)
                )
                Text(
                    text = stringResource(R.string.classLabel),
                    modifier = Modifier.weight(0.2f)
                )
                Text(
                    text = stringResource(R.string.totalLabel),
                    modifier = Modifier.weight(0.2f)
                )
            }
        }

        //create table row for each combat item
        items(combatItemList){combatItem ->
            CombatItemRow(combatItem, updateFunc)
        }

        //resistances table
        item {
            //table header row
            Row {
                //column buffer
                Spacer(modifier = Modifier.weight(0.2f))

                //presence header
                Text(
                    text = "Presence",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )

                //modifier header
                Text(
                    text = stringResource(R.string.modLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )

                //special header
                Text(
                    text = "Special",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )

                //total header
                Text(
                    text = stringResource(R.string.totalLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )
            }
        }

        //create a row for each of the character's resistances
        items(resistanceList){resistanceItem ->
            ResistanceRow(resistanceItem)
        }

        item{Text(text = "Total Initiative: " + totalInitiative.value)}
        item{Text(text = "Fatigue: " + fatigueText.value)}
        item{Text(text = "Regeneration: " + regenText.value)}
    }
}

/**
 * Creates a table row for the combat items and allows input and total display
 *
 * combatItem: character's combat stat to display
 * updateFunc: function to run for bottom bar update
 */
@Composable
private fun CombatItemRow(
    combatItem: CombatItemData,
    updateFunc: () -> Unit
){
    val pointInScore = remember{mutableStateOf(combatItem.pointIn.value.toString())}

    Row {
        //row label
        Text(text = combatItem.labelText, modifier = Modifier.weight(0.2f))

        //stat input field
        TextField(
            value = pointInScore.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                numberCatcher(it,
                    {input ->
                        //determine if input is valid
                        if(combatItem.changeAct(input.toInt(), combatItem.totalText))
                            combatItem.pointColor.value = Color.Black
                        else
                            combatItem.pointColor.value = Color.Red

                        //update character and display for inputted value
                        pointInScore.value = input
                        updateFunc()
                    },
                    {pointInScore.value = ""}
                )
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, color = combatItem.pointColor.value),
            modifier = Modifier.weight(0.2f)
        )

        //display remaining stat values
        Text(text = combatItem.modInput.toString(), modifier = Modifier.weight(0.2f))
        Text(text = combatItem.classAdd.value, modifier = Modifier.weight(0.2f))
        Text(text = combatItem.totalText.value, modifier = Modifier.weight(0.2f))
    }
}

/**
 * Create a row for the resistances table
 *
 * resistance: resistance data to display
 */
@Composable
private fun ResistanceRow(resistance: ResistanceData){
    Row(verticalAlignment = Alignment.CenterVertically){
        //name of the resistance type
        Text(
            text = resistance.labelText,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //character's presence
        Text(
            text = resistance.presence.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //associated modifier value
        Text(
            text = resistance.modStat.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //associated special addition
        Text(
            text = resistance.special.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //total resistance value
        Text(
            text = resistance.total.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
    }
}

/**
 * Class that holds data for a combat row
 *
 * labelText: name of the item for the row
 * pointIn: number of points applied to the stat
 * changeAct: function to run when the pointIn value changes
 * modInput: modifier value applied to the specific stat
 * classAdd: class bonus to the combat stat
 * pointColor: text color that indicates valid inputs
 * totalText: text that displays final stat value
 */
private data class CombatItemData(
    val labelText: String,
    val pointIn: MutableState<Int>,
    val changeAct: (Int, MutableState<String>) -> Boolean,
    val modInput: Int,
    val classAdd: MutableState<String>,
    val pointColor: MutableState<Color>,
    val totalText: MutableState<String>,
)

/**
 * Class that holds data for a resistance row
 *
 * labelText: head for the row item
 * presence: mutable state of the character's presence
 * modStat: mutable state of the resistance's associated modifier
 * special: special buff to the resistance stat
 * total: final total of the resistance stat
 */
private data class ResistanceData(
    val labelText: String,
    val presence: MutableState<Int>,
    val modStat: Int,
    val special: MutableState<Int>,
    val total: MutableState<Int>
)