package com.example.animabuilder.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.animabuilder.activities.*
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.ki_abilities.KiAbility
import com.example.animabuilder.character_creation.attributes.ki_abilities.Technique

private var allKiAbilities: List<Pair<KiAbility, MutableState<Boolean>>> = listOf()
private var allTechniques: List<Pair<Technique, MutableState<Boolean>>> = listOf()

private val remainingMK = mutableStateOf("")
private var techListOpen = mutableStateOf(false)

private val kiPointTotal = mutableStateOf("")
private val kiAccTotal = mutableStateOf("")

@Composable
fun KiFragment(
    charInstance: BaseCharacter,
    updateFunc: () -> Unit
) {
    val context = LocalContext.current

    val kiListOpen = remember{mutableStateOf(false)}
    remainingMK.value = charInstance.kiList.martialKnowledgeRemaining.toString()

    kiPointTotal.value = charInstance.kiList.totalKi.toString()
    kiAccTotal.value = charInstance.kiList.totalAcc.toString()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ){
        InfoRow("Max Martial Knowledge: ", charInstance.kiList.martialKnowledgeMax.toString())
        InfoRow("Martial Knowledge Remaining: ", remainingMK.value)

        Column{
            Row{
                Spacer(modifier = Modifier.weight(0.13f))
                Text(text = "Stat Ki", modifier = Modifier.weight(0.13f))
                Text(text = "Buy Ki", modifier = Modifier.weight(0.13f))
                Text(text = "Ki Total", modifier = Modifier.weight(0.13f))
                Text(text = "Stat Acc", modifier = Modifier.weight(0.13f))
                Text(text = "Buy Acc", modifier = Modifier.weight(0.13f))
                Text(text = "Acc Total", modifier = Modifier.weight(0.13f))
            }

            KiFromStatRow(
                charInstance,
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

            KiFromStatRow(
                charInstance,
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

            KiFromStatRow(
                charInstance,
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

            KiFromStatRow(
                charInstance,
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

            KiFromStatRow(
                charInstance,
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

            KiFromStatRow(
                charInstance,
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

            Row{
                Text(text = "Totals:", modifier = Modifier.weight(0.13f))

                Spacer(Modifier.weight(0.26f))
                Text(text = kiPointTotal.value, textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))

                Spacer(Modifier.weight(0.26f))
                Text(text = kiAccTotal.value, textAlign = TextAlign.Center, modifier = Modifier.weight(0.13f))
            }
        }
        
        Button(
            onClick = {
                kiListOpen.value = !kiListOpen.value
            },
            modifier = Modifier.width(250.dp)
        ) {
            Text(text = "Ki Abilities")
        }
        
        AnimatedVisibility(visible = kiListOpen.value) {
            Column {
                charInstance.kiList.allKiAbilities.forEach {
                    KiAbilityRow(charInstance, it)
                }
            }
        }

        Button(
            onClick = {
                if(charInstance.kiList.takenAbilities.contains(charInstance.kiList.kiControl))
                    techListOpen.value = !techListOpen.value
                else
                    Toast.makeText(context, "You need Ki Control for Dominion Techniques", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.width(250.dp)
        ){
            Text(text = "Dominion Techniques")
        }

        AnimatedVisibility(visible = techListOpen.value) {
            Column{
                charInstance.kiList.allTechniques.forEach{
                    TechniqueRow(charInstance, it)
                }

                Button(
                    onClick = {
                    /*TODO*/
                    }
                ) {
                    Text(text = "Add Technique")
                }
            }
        }
    }
}

@Composable
private fun KiFromStatRow(
    charInstance: BaseCharacter,
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

    Row(){
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
private fun KiAbilityRow(charInstance: BaseCharacter, ability: KiAbility){
    val abilityTaken = remember{mutableStateOf(charInstance.kiList.takenAbilities.contains(ability))}

    allKiAbilities = allKiAbilities + Pair(ability, abilityTaken)

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
                    updateKiTaken(charInstance)
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

private fun updateKiTaken(charInstance: BaseCharacter){
    allKiAbilities.forEach{
        if(charInstance.kiList.takenAbilities.contains(it.first) != it.second.value)
            it.second.value = false
    }

    if(techListOpen.value && !charInstance.kiList.takenAbilities.contains(charInstance.kiList.kiControl))
        techListOpen.value = false
}

@Composable
private fun TechniqueRow(
    charInstance: BaseCharacter,
    toShow: Technique
) {
    val techCheck = remember{mutableStateOf(charInstance.kiList.takenTechniques.contains(toShow))}

    allTechniques = allTechniques + Pair(toShow, techCheck)

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
                    updateTechTaken(charInstance)
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

private fun updateTechTaken(charInstance: BaseCharacter){
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
    Column{
        technique.givenAbilities.forEach{
            Row{
                Text(text = it.name + " " + it.effect)
                if(it.maint != null && technique.isMaintained)
                    Text(text = " Maint: " + it.maint + " (" + getStatName(it.maintIndex!!) + ")")
            }
        }

        val kiBuilds = technique.statSpent()

        if(kiBuilds[0] > 0)
            InfoRow("STR: ", kiBuilds[0].toString())
        if(kiBuilds[1] > 0)
            InfoRow("DEX: ", kiBuilds[1].toString())
        if(kiBuilds[2] > 0)
            InfoRow("AGI: ", kiBuilds[2].toString())
        if(kiBuilds[3] > 0)
            InfoRow("CON: ", kiBuilds[3].toString())
        if(kiBuilds[4] > 0)
            InfoRow("POW: ", kiBuilds[4].toString())
        if(kiBuilds[5] > 0)
            InfoRow("WP: ", kiBuilds[5].toString())

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