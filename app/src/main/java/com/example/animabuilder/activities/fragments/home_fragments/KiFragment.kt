package com.example.animabuilder.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.animabuilder.activities.*
import com.example.animabuilder.activities.fragments.dialogs.CustomTechnique
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.ki_abilities.KiAbility
import com.example.animabuilder.character_creation.attributes.ki_abilities.Technique

private val allKiAbilities = mutableListOf<Pair<KiAbility, MutableState<Boolean>>>()
private val allTechniques = mutableListOf<Pair<Technique, MutableState<Boolean>>>()

private val remainingMK = mutableStateOf("")
private val techListOpen = mutableStateOf(false)

private val kiPointTotal = mutableStateOf("")
private val kiAccTotal = mutableStateOf("")

private val customTechOn = mutableStateOf(false)

/**
 * Fragment that displays items related to martial knowledge
 * Ki points and accumulations can be acquired here
 * Ki abilities are acquireable
 * Dominion Techniques are taken and created in this page
 */

@Composable
fun KiFragment(
    updateFunc: () -> Unit
) {
    //get fragment's context
    val context = LocalContext.current

    //initialize ki ability list's open state
    val kiListOpen = remember{mutableStateOf(false)}

    //get strings for remaining martial knowledge, total ki points, and total ki accumulation
    remainingMK.value = charInstance.kiList.martialKnowledgeRemaining.toString()
    kiPointTotal.value = charInstance.kiList.totalKi.toString()
    kiAccTotal.value = charInstance.kiList.totalAcc.toString()

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ){
        //display martial knowledge values
        item{InfoRow("Max Martial Knowledge: ", charInstance.kiList.martialKnowledgeMax.toString())}
        item{InfoRow("Martial Knowledge Remaining: ", remainingMK.value)}

        //header for ki point and accumulation table
        item {
            Row {
                Spacer(modifier = Modifier.weight(0.13f))
                Text(text = "Stat Ki", modifier = Modifier.weight(0.13f))
                Text(text = "Buy Ki", modifier = Modifier.weight(0.13f))
                Text(text = "Ki Total", modifier = Modifier.weight(0.13f))
                Text(text = "Stat Acc", modifier = Modifier.weight(0.13f))
                Text(text = "Buy Acc", modifier = Modifier.weight(0.13f))
                Text(text = "Acc Total", modifier = Modifier.weight(0.13f))
            }
        }

        //ki values from strength
        item {
            KiFromStatRow(
                "STR",
                charInstance.str,
                charInstance.kiList.boughtStrPoint,
                charInstance.kiList.setBoughtStr,
                charInstance.kiList.boughtStrAcc,
                charInstance.kiList.setStrAcc,
                charInstance.kiList.kiSTR,
                charInstance.kiList.accSTR,
                updateFunc
            )
        }

        //ki values from dexterity
        item {
            KiFromStatRow(
                "DEX",
                charInstance.dex,
                charInstance.kiList.boughtDexPoint,
                charInstance.kiList.setBoughtDex,
                charInstance.kiList.boughtDexAcc,
                charInstance.kiList.setDexAcc,
                charInstance.kiList.kiDEX,
                charInstance.kiList.accDEX,
                updateFunc
            )
        }

        //ki values from agility
        item {
            KiFromStatRow(
                "AGI",
                charInstance.agi,
                charInstance.kiList.boughtAgiPoint,
                charInstance.kiList.setBoughtAgi,
                charInstance.kiList.boughtAgiAcc,
                charInstance.kiList.setAgiAcc,
                charInstance.kiList.kiAGI,
                charInstance.kiList.accAGI,
                updateFunc
            )
        }

        //ki values from constitution
        item {
            KiFromStatRow(
                "CON",
                charInstance.con,
                charInstance.kiList.boughtConPoint,
                charInstance.kiList.setBoughtCon,
                charInstance.kiList.boughtConAcc,
                charInstance.kiList.setConAcc,
                charInstance.kiList.kiCON,
                charInstance.kiList.accCON,
                updateFunc
            )
        }

        //ki values from power
        item {
            KiFromStatRow(
                "POW",
                charInstance.pow,
                charInstance.kiList.boughtPowPoint,
                charInstance.kiList.setBoughtPow,
                charInstance.kiList.boughtPowAcc,
                charInstance.kiList.setPowAcc,
                charInstance.kiList.kiPOW,
                charInstance.kiList.accPOW,
                updateFunc
            )
        }

        //ki values from will
        item {
            KiFromStatRow(
                "WP",
                charInstance.wp,
                charInstance.kiList.boughtWpPoint,
                charInstance.kiList.setBoughtWp,
                charInstance.kiList.boughtWpAcc,
                charInstance.kiList.setWpAcc,
                charInstance.kiList.kiWP,
                charInstance.kiList.accWP,
                updateFunc
            )
        }

        //total ki points and accumulation
        item {
            Row {
                Text(text = "Totals:", modifier = Modifier.weight(0.13f))

                Spacer(Modifier.weight(0.26f))
                Text(
                    text = kiPointTotal.value,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.13f)
                )

                Spacer(Modifier.weight(0.26f))
                Text(
                    text = kiAccTotal.value,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.13f)
                )
            }
        }

        //button to display ki abilities
        item {
            Button(
                onClick = {
                    kiListOpen.value = !kiListOpen.value
                },
                modifier = Modifier.width(250.dp)
            ) {
                Text(text = "Ki Abilities")
            }
        }

        //ki ability display
        item {
            AnimatedVisibility(visible = kiListOpen.value) {
                Column {
                    charInstance.kiList.allKiAbilities.forEach {
                        KiAbilityRow(it)
                    }
                }
            }
        }

        //button to display dominion techniques
        item {
            Button(
                onClick = {
                    //check that character can take a dominion technique
                    if (charInstance.kiList.takenAbilities.contains(charInstance.kiList.kiControl))
                        techListOpen.value = !techListOpen.value
                    //notify user of invalid action
                    else
                        Toast.makeText(
                            context,
                            "You need Ki Control for Dominion Techniques",
                            Toast.LENGTH_SHORT
                        ).show()
                },
                modifier = Modifier.width(250.dp)
            ) {
                Text(text = "Dominion Techniques")
            }
        }

        //technique display
        item {
            AnimatedVisibility(visible = techListOpen.value) {
                Column {
                    //display each prebuilt technique
                    charInstance.kiList.allTechniques.forEach {
                        TechniqueRow(it)
                    }

                    //button for custom technique creation
                    Button(
                        onClick = {
                            customTechOn.value = true
                        }
                    ) {
                        Text(text = "Add Technique")
                    }

                    //display custom techniques
                    charInstance.kiList.customTechniques.forEach {
                        TechniqueRow(it)
                    }
                }
            }
        }
    }

    //alert for custom technique creation
    if(customTechOn.value)
        CustomTechnique(TechContents) { customTechOn.value = false }
}

@Composable
private fun KiFromStatRow(
    title: String,
    statVal: Int,
    boughtPoint: Int,
    pointUpdate: (Int, BaseCharacter, MutableState<String>, MutableState<String>) -> Unit,
    boughtAcc: Int,
    accUpdate: (Int, BaseCharacter, MutableState<String>, MutableState<String>) -> Unit,
    pointTotal: Int,
    accTotal: Int,
    updateFunc: () -> Unit
){
    val pointString = remember{ mutableStateOf(boughtPoint.toString()) }
    val accString = remember{ mutableStateOf(boughtAcc.toString()) }
    val pointTotalString = remember{ mutableStateOf(pointTotal.toString()) }
    val accTotalString = remember{mutableStateOf(accTotal.toString())}

    Row{
        Text(text = title, textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        Text(text = charInstance.kiList.getStatKi(statVal).toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        TextField(
            value = pointString.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                numberCatcher(it,
                    {input: String ->
                        pointUpdate(input.toInt(), charInstance, pointTotalString, kiPointTotal)
                        pointString.value = input
                        updateFunc()},
                    {pointUpdate(0, charInstance, pointTotalString, kiPointTotal)
                        pointString.value = ""},
                )
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier.weight(0.13f)
        )

        Text(text = pointTotalString.value, textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        Text(text = charInstance.kiList.getStatKiAcc(statVal).toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

        TextField(
            value = accString.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                numberCatcher(it,
                    {input: String ->
                        accUpdate(input.toInt(), charInstance, accTotalString, kiAccTotal)
                        accString.value = input
                        updateFunc()},
                    {accUpdate(0, charInstance, accTotalString, kiAccTotal)
                        accString.value = ""}
                )
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier.weight(0.13f)
        )

        Text(text = accTotalString.value, textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))
    }
}

@Composable
private fun KiAbilityRow(ability: KiAbility){
    val abilityTaken = remember{mutableStateOf(charInstance.kiList.takenAbilities.contains(ability))}

    allKiAbilities += Pair(ability, abilityTaken)

    Row(verticalAlignment = Alignment.CenterVertically){
        Checkbox(
            checked = abilityTaken.value,
            onCheckedChange = {
                if(it){
                    if(ability.prerequisites == null ||
                        charInstance.kiList.takenAbilities.contains(ability.prerequisites))
                        abilityTaken.value = charInstance.kiList.attemptAbilityAdd(ability)
                }
                else{
                    abilityTaken.value = false
                    charInstance.kiList.removeAbility(ability)
                    updateKiTaken()
                }

                remainingMK.value = charInstance.kiList.martialKnowledgeRemaining.toString()
            },
            modifier = Modifier.weight(0.1f)
        )
        Text(text = ability.name, modifier = Modifier.weight(0.5f))
        Text(text = ability.mkCost.toString(), modifier = Modifier.weight(0.2f))
        TextButton(
            onClick = {
                detailAlertOn.value = true
                detailItem.value = ability.name
                contents.value = @Composable{KiContents(ability)}
            },
            modifier = Modifier.weight(0.2f)
        ) {
            Text(text = "Details")
        }
    }
}

private fun updateKiTaken() {
    allKiAbilities.forEach{
        if(charInstance.kiList.takenAbilities.contains(it.first) != it.second.value)
            it.second.value = false
    }

    if(techListOpen.value && !charInstance.kiList.takenAbilities.contains(charInstance.kiList.kiControl))
        techListOpen.value = false
}

@Composable
private fun TechniqueRow(
    toShow: Technique
) {
    val techCheck = remember{mutableStateOf(charInstance.kiList.takenTechniques.contains(toShow))}

    allTechniques += Pair(toShow, techCheck)

    Row{
        Checkbox(
            checked = techCheck.value,
            onCheckedChange ={
                if(it) {
                    if(charInstance.kiList.addTechnique(toShow))
                        techCheck.value = true
                }
                else{
                    charInstance.kiList.removeTechnique(toShow)
                    techCheck.value = false
                    updateTechTaken()
                }

                remainingMK.value = charInstance.kiList.martialKnowledgeRemaining.toString()
            }
        )

        Text(text = toShow.name)
        Text(text = toShow.mkCost().toString() + " MK")
        Text(text = toShow.level.toString())

        TextButton(
            onClick = {
                detailAlertOn.value = true
                detailItem.value = toShow.name
                contents.value = @Composable{TechContents(toShow)}
            }) {
            Text(text = "Details")
        }
    }
}

private fun updateTechTaken() {
    allTechniques.forEach{
        if(charInstance.kiList.takenTechniques.contains(it.first) != it.second.value)
            it.second.value = false
    }
}

val KiContents = @Composable
{ability: KiAbility ->
    Column{
        val preString =
            if(ability.prerequisites != null)
                ability.prerequisites!!.name
            else
                "null"

        InfoRow("Prerequisite:", preString)
        Text(text = ability.description)
    }
}

val TechContents = @Composable
{technique: Technique ->
    Column {
        technique.givenAbilities.forEach {
            Row { Text(text = it.name + " " + it.effect) }
        }

        if (technique.isMaintained()){
            Row {
                Text(text = "Maintenance: ")
                for(index in 0..5){
                    if(technique.maintArray[index] != 0)
                        Text(text = technique.maintArray[index].toString() + " (" + getStatName(index) + ")")
                }
            }
        }

        val kiBuilds = technique.statSpent()

        for(index in 0..5){
            if(kiBuilds[index] > 0)
                InfoRow(getStatName(index), kiBuilds[index].toString())
        }

        InfoRow("Total Accumulation: ", technique.accTotal().toString())

        Text(text = technique.description)
    }
}

private fun getStatName(label: Int): String{
    return when(label){
        0 -> "STR"
        1 -> "DEX"
        2 -> "AGI"
        3 -> "CON"
        4 -> "POW"
        5 -> "WP"
        else -> ""
    }
}