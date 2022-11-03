package com.example.animabuilder.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import com.example.animabuilder.R
import com.example.animabuilder.activities.*
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.ki_abilities.KiAbility
import com.example.animabuilder.character_creation.attributes.ki_abilities.Technique
import com.example.animabuilder.character_creation.attributes.ki_abilities.TechniqueEffect

private var allKiAbilities: List<Pair<KiAbility, MutableState<Boolean>>> = listOf()
private var allTechniques: List<Pair<Technique, MutableState<Boolean>>> = listOf()

private val remainingMK = mutableStateOf("")
private var techListOpen = mutableStateOf(false)

private val kiPointTotal = mutableStateOf("")
private val kiAccTotal = mutableStateOf("")

private val customTechLevelSelection = mutableStateOf("1")

private val customTechOn = mutableStateOf(false)
private val customConfirm: MutableState<(() -> Unit)> = mutableStateOf({})
private val customBack: MutableState<(() -> Unit)?> = mutableStateOf(null)
private val customContents: MutableState<@Composable () -> Unit> = mutableStateOf({})

private var techniqueIndex = mutableStateOf(0)
private lateinit var allEffectChecks: List<Pair<TechniqueEffect, MutableState<Boolean>>>
private lateinit var advantageOneCheck: List<Pair<TechniqueEffect, MutableState<Boolean>>>
private lateinit var advantageTwoCheck: List<Pair<TechniqueEffect, MutableState<Boolean>>>

private var isPrimary = mutableStateOf(true)
private var kiIndex: MutableState<Int> = mutableStateOf(0)
private var elementList: MutableState<List<Element>> = mutableStateOf(listOf())
private var elementChecks: List<Pair<Element, MutableState<Boolean>>> = listOf()

private val isThird = mutableStateOf(false)
private val toRemove: MutableState<List<TechniqueEffect>> = mutableStateOf(listOf())

private val buildArray: MutableState<List<Int?>> = mutableStateOf(listOf(null))
private val allKiBuilds: MutableState<List<Pair<String, MutableList<Int>>>> = mutableStateOf(listOf())
private val strAccTotal = mutableStateOf(getAccTotal(0))
private val dexAccTotal = mutableStateOf(getAccTotal(1))
private val agiAccTotal = mutableStateOf(getAccTotal(2))
private val conAccTotal = mutableStateOf(getAccTotal(3))
private val powAccTotal = mutableStateOf(getAccTotal(4))
private val wpAccTotal = mutableStateOf(getAccTotal(5))
private val allAccs = listOf(strAccTotal, dexAccTotal, agiAccTotal, conAccTotal, powAccTotal, wpAccTotal)

private val customTechMaintenanceSelection = mutableStateOf(false)

private lateinit var customTechnique: Technique

@Composable
fun KiFragment(
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
                    TechniqueRow(it)
                }

                Button(
                    onClick = {
                        customTechOn.value = true
                        customContents.value = customFirstContents
                        customBack.value = null
                        customConfirm.value = customFirstConfirm
                        customTechLevelSelection.value = "1"
                    }
                ) {
                    Text(text = "Add Technique")
                }

                charInstance.kiList.customTechniques.forEach{
                    TechniqueRow(it)
                }
            }
        }
    }

    if(customTechOn.value)
        CustomTechniqueAlert(customContents.value, customBack.value, customConfirm.value)
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

@Composable
private fun CustomTechniqueAlert(
    contents: @Composable () -> Unit,
    backAction: (() -> Unit)?,
    confirmAction: () -> Unit
){
    Dialog(
        onDismissRequest = {customTechOn.value = false},
        content = {
            Box(
                Modifier
                    .background(Color.White)
                    .size(600.dp, 600.dp)) {

                    Row(
                        Modifier
                            .align(Alignment.TopCenter)
                            .height(100.dp)){ Text(text = "Create Custom Technique") }
                    Row(
                        Modifier
                            .align(Alignment.Center)
                            .height(400.dp)){ contents() }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .height(100.dp)) {
                        TextButton(onClick = {
                            customTechOn.value = false
                        }) { Text(text = "Cancel") }

                        if (isThird.value && customBack.value == null) {
                            TextButton(
                                onClick = {
                                    customTechnique.givenAbilities -= toRemove.value
                                    toRemove.value.forEach {
                                        allKiBuilds.value = allKiBuilds.value - findBuild(it.name)!!
                                    }

                                    toRemove.value = listOf()

                                    customTechnique.fixPrimaryAbility()

                                    if (customTechnique.givenAbilities.isNotEmpty())
                                        customThirdBack()
                                    else {
                                        isThird.value = false
                                        customSecondBack()
                                    }
                                }
                            ) {
                                Text(text = "Delete")
                            }
                        } else if (isThird.value) {
                            TextButton(
                                onClick = {
                                    setAlertPage(customEditContents, null, customEditBack)
                                }
                            ) { Text(text = "Edit") }

                            TextButton(
                                onClick = {
                                    if (getSelectedEffect() != null) {
                                        val addedTechnique = getSelectedEffect()!!
                                        if (techniqueIndex.value == 35)
                                            addedTechnique.elements += Element.Free

                                        customTechnique.givenAbilities += addedTechnique
                                        techniqueIndex.value = 0

                                        allKiBuilds.value = allKiBuilds.value + Pair(
                                            addedTechnique.name,
                                            addedTechnique.kiBuild.toMutableList()
                                        )
                                    }
                                }
                            ) { Text(text = "Add") }
                        }

                        if (backAction != null)
                            TextButton(onClick = { backAction() }) { Text(text = "Back") }

                        TextButton(onClick = { confirmAction() }) { Text(text = "Next") }
                    }

            }
        }
    )
}

val customFirstContents = @Composable {
    customTechnique = Technique("", "", customTechLevelSelection.value.toInt(), mutableListOf(0, 0, 0, 0, 0, 0), listOf())
    techniqueIndex.value = 0

    Column{
        Row{Text(text = "Select the Technique's Level:")}
        Column{
            Row {
                RadioButton(
                    selected = customTechLevelSelection.value == "1",
                    onClick = { customTechLevelSelection.value = "1" })
                Text(text = "1")
            }
            Row{
                RadioButton(
                    selected = customTechLevelSelection.value == "2",
                    onClick = {customTechLevelSelection.value = "2"})
                Text(text = "2")
            }
            Row{
                RadioButton(
                    selected = customTechLevelSelection.value == "3",
                    onClick = {customTechLevelSelection.value = "3"})
                Text(text = "3")
            }
        }

        val minMK = when(customTechLevelSelection.value){
            "1" -> 20
            "2" -> 40
            "3" -> 60
            else -> 0
        }

        val maxMK = when(customTechLevelSelection.value){
            "1" -> 50
            "2" -> 100
            "3" -> 200
            else -> 0
        }

        Text(text = "Martial Knowledge Range: $minMK - $maxMK")
    }
}

val customFirstConfirm = {
    if((customTechLevelSelection.value == "1" && remainingMK.value.toInt() >= 20) ||
        (customTechLevelSelection.value == "2" && charInstance.kiList.takenFirstTechniques.size >= 2 && remainingMK.value.toInt() >= 40) ||
        (customTechLevelSelection.value == "3" && charInstance.kiList.takenSecondTechniques.size >= 2 && remainingMK.value.toInt() >= 60)) {
        isThird.value = false
        customTechnique.level = customTechLevelSelection.value.toInt()

        customConfirm.value = customSecondConfirm
        setAlertPage(
            customSecondContents,
            customFirstBack,
            customSecondConfirm
        )
    }
}

val customFirstBack: () -> Unit = {
    customTechLevelSelection.value = "1"
    setAlertPage(customFirstContents, null, customFirstConfirm)
}

val customSecondContents: @Composable () -> Unit = @Composable{
    isPrimary.value = true
    customTechnique.givenAbilities = listOf()
    techniqueIndex.value = 0

    Column{
        Text(text = "Select Primary Ability: ")
        TechniqueAbilityDropdown()
        TechniqueTable()
    }
}

val customSecondConfirm: () -> Unit = {
    if(getSelectedEffect() != null) {
        isPrimary.value = false
        customTechnique.givenAbilities = listOf(getSelectedEffect()!!)
        allKiBuilds.value = listOf(Pair(getSelectedEffect()!!.name, getSelectedEffect()!!.kiBuild.toMutableList()))

        setAlertPage(customThirdContents, customSecondBack, customThirdConfirm)
    }
}

val customSecondBack: () -> Unit = {
    isThird.value = false
    setAlertPage(customSecondContents, customFirstBack, customSecondConfirm)
}

val customThirdContents: @Composable () -> Unit = @Composable{
    techniqueIndex.value = 0
    isThird.value = true

    Column{
        Text(text = "Add Secondary Abilities: ")
        TechniqueAbilityDropdown()
        TechniqueTable()
    }
}

val customThirdConfirm = {
    if((customTechnique.level == 1 && customTechnique.mkCost() >= 20) ||
        (customTechnique.level == 2 && customTechnique.mkCost() >= 40) ||
        (customTechnique.level == 3 && customTechnique.mkCost() >= 60)) {
        isThird.value = false
        setAlertPage(customFourthContents, customThirdBack, customFourthConfirm)
    }
}

val customThirdBack: () -> Unit = {
    techniqueIndex.value = 0
    setAlertPage(customThirdContents, customSecondBack, customThirdConfirm)
}

val customFourthContents: @Composable () -> Unit = @Composable{
    Column{
        for(index in 0..5)
            allAccs[index].value = getAccTotal(index)

        Row{Text(text = "Accumulation Totals:")}
        Row{
            for(index in 0..5)
                Text(text = getStatName(index), modifier = Modifier.weight(0.13f))
        }

        Row {
            Text(text = strAccTotal.value, modifier = Modifier.weight(0.13f))
            Text(text = dexAccTotal.value, modifier = Modifier.weight(0.13f))
            Text(text = agiAccTotal.value, modifier = Modifier.weight(0.13f))
            Text(text = conAccTotal.value, modifier = Modifier.weight(0.13f))
            Text(text = powAccTotal.value, modifier = Modifier.weight(0.13f))
            Text(text = wpAccTotal.value, modifier = Modifier.weight(0.13f))
        }

        Column(modifier = Modifier.verticalScroll(rememberScrollState())){
            customTechnique.givenAbilities.forEach {
                Row { Text(text = it.name) }

                for (index in 0..5) {
                    EditBuildRow(it, index, findBuild(it.name)!!.second)
                }
            }
        }
    }
}

val customFourthConfirm: () -> Unit = {
    var isFirst = true
    var moveOn = true

   allKiBuilds.value.forEach{
       var total = 0

       val effect = customTechnique.getAbility(it.first)

       var accTotal = if(isFirst) effect!!.costPair.first else effect!!.costPair.second

       for(index in 0..5){
           total += it.second[index]

           if(it.second[index] > 0)
               accTotal += effect.buildAdditions[index]!!
       }

       if(total == accTotal)
           effect.kiBuild = it.second

       else
           moveOn = false

       isFirst = false
   }

    if(moveOn)
        setAlertPage(customFifthContents, customFourthBack, customFifthConfirm)
}

val customFourthBack = {
    setAlertPage(customFourthContents, customThirdBack, customFourthConfirm)
}

val customFifthContents = @Composable{
    Column(Modifier.verticalScroll(rememberScrollState())){
        Row { Text(text = "Make technique maintainable?") }
        Row {
            RadioButton(
                selected = customTechMaintenanceSelection.value,
                onClick = { customTechMaintenanceSelection.value = true }
            )
            Text(text = "Yes")
        }
        Row {
            RadioButton(
                selected = !customTechMaintenanceSelection.value,
                onClick = { customTechMaintenanceSelection.value = false }
            )
            Text(text = "No")
        }


        if (customTechMaintenanceSelection.value) {
            Row { Text(text = "Total Maintenance Cost: " + customTechnique.maintTotal().toString()) }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                for (index in 0..5)
                    if(customTechnique.hasAccumulation(index))
                        MaintenanceInput(index)
            }
        }
    }
}

val customFifthConfirm: () -> Unit = {
    var toNext = true

    if(customTechMaintenanceSelection.value){
        var total = 0
        customTechnique.maintArray.forEach{
            total += it
        }

        toNext = total == customTechnique.maintTotal()
    }
    else{
        customTechnique.maintArray = mutableListOf(0, 0, 0, 0, 0, 0)
    }

    if(toNext)
        setAlertPage(customSixthContents, customFifthBack, customSixthConfirm)
}

val customFifthBack = {
    setAlertPage(customFifthContents, customFourthBack, customFifthConfirm)
}

val customSixthContents = @Composable {
    val customName = remember{mutableStateOf("")}
    val customDescription = remember{mutableStateOf("")}

    Column{
        Row{
            Text(text = "Name your Technique: ")
            TextField(
                value = customName.value,
                onValueChange = {
                    customTechnique.name = it
                    customName.value = it
                }
            )
        }
        Row{
            Text(text = "Description")
            TextField(
                value = customDescription.value,
                onValueChange = {
                    customTechnique.description = it
                    customDescription.value = it
                }
            )
        }
    }
}

val customSixthConfirm: () -> Unit = {
    if(customTechnique.name != "")
        setAlertPage(customSeventhContents, customSixthBack, customSeventhConfirm)
}

val customSixthBack = {
    setAlertPage(customSixthContents, customFifthBack, customSixthConfirm)
}

val customSeventhContents = @Composable{
    Row{Text(text = "Description of " + customTechnique.name)}
    Row{TechContents(customTechnique)}
}

val customSeventhConfirm = {
    charInstance.kiList.addTechnique(customTechnique)
    customTechOn.value = false
}

var customEditContents: @Composable () -> Unit = @Composable{
    LazyColumn {
        customTechnique.givenAbilities.forEach {
            item{EditEffectRow(it)}
        }

        item{
            LazyRow {
                item{Text(text = "MK: " + customTechnique.mkCost().toString())}
            }
        }
    }
}

val customEditBack = {
    isThird.value = true
    setAlertPage(customThirdContents, customFirstConfirm, customThirdConfirm)
}

private fun setAlertPage(
    inContents: @Composable () -> Unit,
    inBack: (() -> Unit)?,
    inConfirm: (() -> Unit)
){
    customContents.value = inContents
    customBack.value = inBack
    customConfirm.value = inConfirm
}

@Composable
private fun TechniqueAbilityDropdown(){
    var source = stringArrayResource(R.array.techniqueAbilities)

    if(!isPrimary.value)
        source += stringArrayResource(R.array.techniqueDisadvantages)

    val isOpen = remember{mutableStateOf(false)}
    val size = remember{mutableStateOf(Size.Zero)}

    val icon = if(isOpen.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    OutlinedTextField(
        value = source[techniqueIndex.value],
        onValueChange = {},
        modifier = Modifier
            .onGloballyPositioned { coordinates ->
            size.value = coordinates.size.toSize()},
        trailingIcon = {
            Icon(icon, "contentDescription", modifier = Modifier.clickable{isOpen.value = !isOpen.value})
        }
    )
    DropdownMenu(
        expanded = isOpen.value,
        onDismissRequest = {isOpen.value = false},
        modifier = Modifier.width(with(LocalDensity.current){size.value.width.toDp()})
    ){
        source.forEach{
            DropdownMenuItem(onClick = {
                techniqueIndex.value = source.indexOf(it)
                isOpen.value = false
            }){
                Text(text = it)
            }
        }
    }
}

@Composable
private fun TechniqueTable(){
    allEffectChecks = listOf()
    advantageOneCheck = listOf()
    advantageTwoCheck = listOf()

    elementChecks = listOf()

    Column(modifier = Modifier.verticalScroll(rememberScrollState())){
        when(techniqueIndex.value){
            //Attack Ability
            1 -> {
                kiIndex.value = 1
                elementList.value = listOf(Element.Air, Element.Fire, Element.Dark)
                ElementDisplayRow()
                buildArray.value = listOf(2, 0, 2, null, 2, 3)

                TechniqueTableHeader("Attack Bonus")
                TechniqueTableRow("+10", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("+25", 3, 5, 5, 2, 1, 1)
                TechniqueTableRow("+40", 4, 6, 10, 3, 1, 1)
                TechniqueTableRow("+50", 5, 8, 15, 4, 1, 1)
                TechniqueTableRow("+75", 8, 11, 20, 6, 1, 1)
                TechniqueTableRow("+90", 12, 15, 25, 8, 1, 1)
                TechniqueTableRow("+100", 14, 18, 30, 10, 1, 1)
                TechniqueTableRow("+125", 18, 22, 35, 12, 2, 1)
                TechniqueTableRow("+150", 22, 26, 40, 14, 2, 1)
                TechniqueTableRow("+175", 26, 32, 45, 16, 3, 1)
                TechniqueTableRow("200", 30, 36, 50, 18, 3, 1)
            }

            //Counterattack Ability
            2 -> {
                kiIndex.value = 1
                elementList.value = listOf(Element.Air, Element.Water, Element.Earth)
                ElementDisplayRow()
                buildArray.value = listOf(2, 0, 2, null, 2, 3)

                TechniqueTableHeader("Attack Bonus")
                TechniqueTableRow("+10", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("+25", 2, 4, 5, 2, 1, 1)
                TechniqueTableRow("+40", 3, 5, 10, 3, 1, 1)
                TechniqueTableRow("+50", 4, 6, 10, 4, 1, 1)
                TechniqueTableRow("+75", 6, 9, 15, 6, 1, 1)
                TechniqueTableRow("+90", 9, 12, 20, 8, 1, 1)
                TechniqueTableRow("+100", 12, 15, 25, 10, 1, 1)
                TechniqueTableRow("+125", 14, 18, 30, 12, 2, 1)
                TechniqueTableRow("+150", 18, 22, 35, 14, 2, 1)
                TechniqueTableRow("+175", 22, 26, 40, 16, 3, 1)
                TechniqueTableRow("+200", 26, 32, 45, 18, 3, 1)
            }

            //Block Ability
            3 -> {
                kiIndex.value = 1
                elementList.value = listOf(Element.Light, Element.Water, Element.Earth)
                ElementDisplayRow()
                buildArray.value = listOf(2, 0, 2, null, 2, 3)

                TechniqueTableHeader("Block Bonus")
                TechniqueTableRow("+10", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("+25", 3, 5, 5, 1, 1, 1)
                TechniqueTableRow("+40", 4, 6, 10, 2, 1, 1)
                TechniqueTableRow("+50", 5, 8, 15, 3, 1, 1)
                TechniqueTableRow("+75", 8, 11, 20, 4, 1, 1)
                TechniqueTableRow("+90", 12, 15, 25, 5, 1, 1)
                TechniqueTableRow("+100", 14, 18, 30, 8, 1, 1)
                TechniqueTableRow("125", 18, 22, 35, 10, 2, 1)
                TechniqueTableRow("+150", 22, 26, 40, 12, 2, 1)
                TechniqueTableRow("+175", 26, 32, 45, 14, 3, 1)
                TechniqueTableRow("+200", 30, 36, 50, 16, 3, 1)
            }

            //Limited Block Ability
            4 -> {
                kiIndex.value = 1
                elementList.value = listOf(Element.Light, Element.Water, Element.Earth)
                ElementDisplayRow()
                buildArray.value = listOf(2, 0, 2, null, 2, 3)

                TechniqueTableHeader("Block Bonus")
                TechniqueTableRow("+10", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("+25", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("+40", 3, 5, 10, 1, 1, 1)
                TechniqueTableRow("+50", 4, 6, 10, 2, 1, 1)
                TechniqueTableRow("+75", 6, 9, 15, 3, 1, 1)
                TechniqueTableRow("+90", 8, 11, 20, 4, 1, 1)
                TechniqueTableRow("+100", 10, 13, 25, 6, 1, 1)
                TechniqueTableRow("+125", 12, 15, 30, 8, 2, 1)
                TechniqueTableRow("+150", 16, 20, 35, 10, 2, 1)
                TechniqueTableRow("+175", 20, 24, 40, 12, 3, 1)
                TechniqueTableRow("+200", 24, 29, 45, 14, 3, 1)
            }

            //Dodge Ability
            5-> {
                kiIndex.value = 2
                elementList.value = listOf(Element.Light, Element.Air, Element.Water)
                ElementDisplayRow()
                buildArray.value = listOf(null, 2, 0, 2, 2, 3)

                TechniqueTableHeader("Dodge Bonus")
                TechniqueTableRow("+10", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("+25", 3, 5, 5, 1, 1, 1)
                TechniqueTableRow("+40", 4, 6, 10, 2, 1, 1)
                TechniqueTableRow("+50", 5, 8, 15, 3, 1, 1)
                TechniqueTableRow("+75", 8, 11, 20, 4, 1, 1)
                TechniqueTableRow("+90", 12, 15, 25, 5, 1, 1)
                TechniqueTableRow("+100", 14, 18, 30, 8, 1, 1)
                TechniqueTableRow("+125", 18, 22, 35, 10, 2, 1)
                TechniqueTableRow("+150", 22, 26, 40, 12, 2, 1)
                TechniqueTableRow("+175", 26, 32, 45, 14, 3, 1)
                TechniqueTableRow("+200", 30, 36, 50, 16, 3, 1)
            }

            //Limited Dodge Ability
            6-> {
                kiIndex.value = 2
                elementList.value = listOf(Element.Light, Element.Air, Element.Dark)
                ElementDisplayRow()
                buildArray.value = listOf(null, 2, 0, 2, 2, 3)

                TechniqueTableHeader("Dodge Bonus")
                TechniqueTableRow("+10", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("+25", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("+40", 3, 5, 10, 1, 1, 1)
                TechniqueTableRow("+50", 4, 6, 10, 2, 1, 1)
                TechniqueTableRow("+75", 6, 9, 15, 3, 1, 1)
                TechniqueTableRow("+90", 8, 11, 20, 4, 1, 1)
                TechniqueTableRow("+100", 10, 13, 25, 6, 1, 1)
                TechniqueTableRow("+125", 12, 15, 30, 8, 2, 1)
                TechniqueTableRow("+150", 16, 20, 35, 10, 2, 1)
                TechniqueTableRow("+175", 20, 24, 40, 12, 3, 1)
                TechniqueTableRow("+200", 24, 29, 45, 14, 3, 1)
            }

            //Damage Multiplier
            7-> {
                kiIndex.value = 0
                elementList.value = listOf(Element.Fire, Element.Earth)
                ElementDisplayRow()
                buildArray.value = listOf(0, 3, null, 2, 1, 1)

                TechniqueTableHeader("Multiplier")
                TechniqueTableRow("x2", 10, 15, 25, 4, 1, 1)
                TechniqueTableRow("x3", 15, 20, 40, 8, 2, 1)
                TechniqueTableRow("x4", 20, 30, 80, 12, 3, 1)
            }

            //Damage Augmentation
            8-> {
                kiIndex.value = 0
                elementList.value = listOf(Element.Fire, Element.Earth)
                ElementDisplayRow()
                buildArray.value = listOf(0, 3, null, 1, 2, 1)

                TechniqueTableHeader("Damage Bonus")
                TechniqueTableRow("+10", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("+25", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("+40", 3, 5, 10, 1, 1, 1)
                TechniqueTableRow("+50", 4, 6, 15, 2, 1, 1)
                TechniqueTableRow("+75", 6, 9, 20, 3, 1, 1)
                TechniqueTableRow("+90", 8, 11, 25, 4, 1, 1)
                TechniqueTableRow("+100", 10, 13, 30, 5, 1, 1)
                TechniqueTableRow("+125", 14, 18, 35, 6, 2, 1)
                TechniqueTableRow("+150", 16, 20, 40, 8, 2, 1)
                TechniqueTableRow("+175", 18, 22, 45, 10, 3, 1)
                TechniqueTableRow("+200", 20, 24, 50, 12, 3, 1)

                Row{Text(text = "Optional Advantage: Sacrifice")}
                TechniqueTableRow("Vital Sacrifice", 4, 4, 15, 3, 1, 2)
                TechniqueTableRow("Double Vital Sacrifice", 10, 10, 50, 4, 1, 2)
                TechniqueTableRow("Health Sacrifice", 2, 2, 10, 2, 1, 2)
                TechniqueTableRow("Characteristic Sacrifice", 2, 2, 10, 2, 1, 2)
            }

            //Additional Attack
            9-> {
                kiIndex.value = 1
                elementList.value = listOf(Element.Air, Element.Water)
                ElementDisplayRow()
                buildArray.value = listOf(null, 0, 2, 1, 3, 3)

                TechniqueTableHeader("Attacks")
                TechniqueTableRow("+1", 6, 9, 20, 3, 1, 1)
                TechniqueTableRow("+2", 12, 15, 30, 6, 1, 1)
                TechniqueTableRow("+3", 18, 22, 40, 9, 1, 1)
                TechniqueTableRow("+4", 24, 29, 50, 12, 2, 1)
                TechniqueTableRow("+5", 30, 36, 60, 15, 3, 1)

                Row{Text(text = "Optional Advantage: Continuous Attack")}
                TechniqueTableRow("Continuous Attack", 10, 10, 30, 5, 1, 2)

                Row{Text(text = "Optional Advantage: Added Fatigue Bonus")}
                TechniqueTableRow("Added Fatigue Bonus", 8, 8, 30, 2, 1, 3)
            }

            //Limited Additional Attack
            10-> {
                kiIndex.value = 1
                elementList.value = listOf(Element.Air, Element.Water, Element.Dark)
                ElementDisplayRow()
                buildArray.value = listOf(null, 0, 2, 1, 3, 3)

                TechniqueTableHeader("Attacks")
                TechniqueTableRow("+1", 3, 5, 5, 1, 1, 1)
                TechniqueTableRow("+2", 6, 9, 10, 2, 1, 1)
                TechniqueTableRow("+3", 9, 12, 15, 3, 1, 1)
                TechniqueTableRow("+4", 12, 15, 20, 4, 1, 1)
                TechniqueTableRow("+5", 15, 19, 30, 6, 1, 1)
                TechniqueTableRow("+6", 18, 22, 40, 8, 2, 1)
                TechniqueTableRow("+8", 22, 26, 50, 10, 2, 1)
                TechniqueTableRow("+10", 26, 32, 60, 12, 3, 1)

                Row{Text(text = "Optional Advantage: Continuous Attack")}
                TechniqueTableRow("Continuous Attack", 10, 10, 30, 5, 1, 2)
            }

            //Additional Defense
            11-> {
                kiIndex.value = 2
                elementList.value = listOf(Element.Light)
                ElementDisplayRow()
                buildArray.value = listOf(null, 1, 0, 1, 3, 3)

                TechniqueTableHeader("Defenses")
                TechniqueTableRow("+1", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("+2", 2, 4, 5, 2, 1, 1)
                TechniqueTableRow("+3", 3, 5, 10, 3, 1, 1)
                TechniqueTableRow("+4", 4, 6, 15, 4, 1, 1)
                TechniqueTableRow("+6", 5, 8, 20, 6, 1, 1)
                TechniqueTableRow("+8", 6, 9, 25, 8, 1, 1)
                TechniqueTableRow("+10", 7, 10, 30, 10, 2, 1)
                TechniqueTableRow("Unlimited", 8, 11, 35, 12, 3, 1)

                Row{Text(text = "Optional Advantage: Added Fatigue Bonus")}
                TechniqueTableRow("Added Fatigue Bonus", 6, 6, 20, 2, 1, 2)
            }

            //Additional Action
            12-> {
                kiIndex.value = 1
                elementList.value = listOf(Element.Air)
                ElementDisplayRow()
                buildArray.value = listOf(null, 0, 1, 1, 3, 3)

                TechniqueTableHeader("Actions")
                TechniqueTableRow("+1", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("+2", 2, 4, 5, 2, 1, 1)
                TechniqueTableRow("+3", 3, 5, 10, 3, 1, 1)
                TechniqueTableRow("+4", 4, 6, 15, 4, 1, 1)
                TechniqueTableRow("+5", 5, 8, 20, 6, 1, 1)
                TechniqueTableRow("+6", 6, 9, 25, 8, 1, 1)
                TechniqueTableRow("+8", 7, 10, 30, 10, 2, 1)
                TechniqueTableRow("+10", 8, 11, 35, 12, 3, 1)

                Row{Text(text = "Optional Advantage: Added Fatigue Bonus")}
                TechniqueTableRow("Added Fatigue Bonus", 6, 6, 20, 1, 1, 2)
            }

            //Initiative Augmentation
            13-> {
                kiIndex.value = 2
                elementList.value = listOf(Element.Air)
                ElementDisplayRow()
                buildArray.value = listOf(null, 1, 0, 2, 3, 3)

                TechniqueTableHeader("Initiative Bonus")
                TechniqueTableRow("+25", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("+50", 2, 4, 10, 1, 1, 1)
                TechniqueTableRow("+75", 4, 6, 15, 2, 1, 1)
                TechniqueTableRow("+100", 6, 9, 20, 3, 1, 1)
                TechniqueTableRow("+125", 8, 11, 25, 4, 2, 1)
                TechniqueTableRow("+150", 10, 13, 30, 5, 2, 1)
                TechniqueTableRow("+175", 12, 15, 35, 6, 3, 1)
                TechniqueTableRow("+200", 14, 18, 40, 7, 3, 1)
            }

            //States
            14-> {
                kiIndex.value = 4
                elementList.value = listOf(Element.Dark, Element.Light)
                ElementDisplayRow()
                buildArray.value = listOf(4, 4, null, 4, 0, 1)

                TechniqueTableHeader("PhR Check")
                TechniqueTableRow("40", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("60", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("80", 3, 5, 10, 2, 1, 1)
                TechniqueTableRow("100", 5, 8, 15, 3, 1, 1)
                TechniqueTableRow("120", 6, 9, 20, 4, 1, 1)
                TechniqueTableRow("140", 8, 11, 20, 5, 2, 1)
                TechniqueTableRow("180", 10, 13, 30, 6, 2, 1)
                TechniqueTableRow("200", 14, 18, 50, 8, 3, 1)
                TechniqueTableRow("240", 18, 22, 80, 10, 3, 1)

                Row{Text(text = "Optional Advantage: Added State")}
                TechniqueTableRow("Action Penalty", 2, 2, 5, 0,  1, 2)
                TechniqueTableRow("PhR Reduction", 2, 2, 10, 0,  1, 2)
                TechniqueTableRow("Blindness", 5, 5, 15, 0,  1, 2)
                TechniqueTableRow("Characteristic Reduction", 2, 2, 10, 0, 1, 2)
                TechniqueTableRow("Partial Paralysis", 6, 6, 10, 0, 1, 2)
                TechniqueTableRow("Damage", 5, 5, 10, 0, 1, 2)
                TechniqueTableRow("Unconscious", 8, 8, 15, 0, 1, 2)
                TechniqueTableRow("Coma", 10, 10, 30, 0, 2, 2)
                TechniqueTableRow("Total Paralysis", 8, 8, 20, 0, 2, 2)
                TechniqueTableRow("Life Drain", 8, 8, 15, 0, 2, 2)
                TechniqueTableRow("Control", 10, 10, 40, 0, 3, 2)
                TechniqueTableRow("Death", 12, 12, 50, 0, 3, 2)
            }

            //Combat Maneuvers and Aiming
            15-> {
                kiIndex.value = 1
                elementList.value = listOf(Element.Air)
                ElementDisplayRow()
                buildArray.value = listOf(null, 0, 1, 2, 2, 2)

                TechniqueTableHeader("Precision")
                TechniqueTableRow("-10", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("-25", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("-50", 3, 5, 10, 2, 1, 1)
                TechniqueTableRow("-75", 4, 6, 10, 2, 2, 1)
                TechniqueTableRow("-100", 6, 9, 15, 3, 2, 1)
                TechniqueTableRow("-120", 8, 11, 20, 3, 3, 1)
            }

            //Armor Increase
            16-> {
                kiIndex.value = 3
                elementList.value = listOf(Element.Earth, Element.Water, Element.Light)
                ElementDisplayRow()
                buildArray.value = listOf(2, null, 3, 0, 1, 2)

                TechniqueTableHeader("AT")
                TechniqueTableRow("1", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("2", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("3", 4, 6, 10, 2, 1, 1)
                TechniqueTableRow("4", 6, 9, 15, 2, 1, 1)
                TechniqueTableRow("5", 8, 11, 20, 3, 2, 1)
                TechniqueTableRow("6", 10, 13, 25, 3, 2, 1)
                TechniqueTableRow("7", 12, 15, 30, 4, 2, 1)
                TechniqueTableRow("8", 14, 18, 40, 5, 3, 1)
            }

            //Armor Destruction
            17-> {
                kiIndex.value = 0
                elementList.value = listOf(Element.Dark, Element.Fire)
                ElementDisplayRow()
                buildArray.value = listOf(0, 2, null, 2, 1, 2)

                TechniqueTableHeader("Reduction")
                TechniqueTableRow("-1 AT", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("-2 AT", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("-3 AT", 3, 5, 10, 2, 1, 1)
                TechniqueTableRow("-4 AT", 4, 6, 10, 2, 1, 1)
                TechniqueTableRow("-5 AT", 5, 8, 15, 3, 2, 1)
                TechniqueTableRow("-6 AT", 6, 9, 20, 3, 2, 1)
                TechniqueTableRow("-7 AT", 8, 11, 25, 4, 2, 1)
                TechniqueTableRow("-8 AT", 10, 13, 30, 5, 3, 1)
            }

            //Breakage Augmentation
            18-> {
                kiIndex.value = 0
                elementList.value = listOf(Element.Earth, Element.Fire)
                ElementDisplayRow()
                buildArray.value = listOf(0, 4, null, 2, 2, 1)

                TechniqueTableHeader("Breakage")
                TechniqueTableRow("+5", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("+10", 2, 4, 10, 1, 1, 1)
                TechniqueTableRow("+15", 4, 6, 15, 2, 1, 1)
                TechniqueTableRow("+20", 6, 9, 20, 3, 1, 1)
                TechniqueTableRow("+25", 8, 11, 25, 4, 2, 1)
                TechniqueTableRow("+30", 12, 15, 30, 5, 2, 1)
                TechniqueTableRow("+35", 13, 18, 35, 6, 2, 1)
                TechniqueTableRow("+40", 18, 22, 40, 8, 3, 1)
            }

            //Fortitude Augmentation
            19-> {
                kiIndex.value = 0
                elementList.value = listOf(Element.Earth, Element.Fire)
                ElementDisplayRow()
                buildArray.value = listOf(0, 4, null, 2, 2, 1)

                TechniqueTableHeader("Fortitude")
                TechniqueTableRow("+10", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("+15", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("+20", 3, 5, 10, 2, 1, 1)
                TechniqueTableRow("+25", 4, 6, 10, 2, 1, 1)
                TechniqueTableRow("+30", 5, 8, 15, 3, 2, 1)
                TechniqueTableRow("+35", 6, 9, 20, 3, 2, 1)
                TechniqueTableRow("+40", 7, 10, 25, 4, 3, 1)
            }

            //Long-Distance Attack
            20-> {
                kiIndex.value = 4
                elementList.value = listOf(Element.Air, Element.Water, Element.Fire)
                ElementDisplayRow()
                buildArray.value = listOf(null, 2, 3, 4, 0, 1)

                TechniqueTableHeader("Distance")
                TechniqueTableRow("5m", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("10m", 2, 4, 10, 1, 1, 1)
                TechniqueTableRow("20m", 3, 5, 10, 2, 1, 1)
                TechniqueTableRow("50m", 4, 6, 15, 3, 1, 1)
                TechniqueTableRow("100m", 5, 8, 20, 4, 1, 1)
                TechniqueTableRow("250m", 6, 9, 25, 5, 2, 1)
                TechniqueTableRow("500m", 8, 11, 30, 6, 2, 1)
                TechniqueTableRow("1km", 10, 13, 35, 8, 2, 1)
                TechniqueTableRow("5km", 14, 18, 40, 10, 3, 1)
                TechniqueTableRow("10km", 18, 22, 45, 12, 3, 1)
                TechniqueTableRow("100km", 22, 26, 50, 14, 3, 1)
            }

            //Area Attack
            21-> {
                kiIndex.value = 4
                elementList.value = listOf(Element.Dark, Element.Light, Element.Fire)
                ElementDisplayRow()
                buildArray.value = listOf(null, 2, 3, 3, 0, 1)

                TechniqueTableHeader("Radius")
                TechniqueTableRow("1m", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("5m", 2, 4, 10, 1, 1, 1)
                TechniqueTableRow("10m", 3, 5, 15, 2, 1, 1)
                TechniqueTableRow("25m", 4, 6, 20, 3, 1, 1)
                TechniqueTableRow("50m", 6, 9, 25, 4, 2, 1)
                TechniqueTableRow("100m", 8, 11, 30, 5, 2, 1)
                TechniqueTableRow("500m", 10, 13, 40, 6, 2, 1)
                TechniqueTableRow("1km", 12, 15, 50, 8, 3, 1)
                TechniqueTableRow("5km", 16, 20, 60, 10, 3, 1)

                Row{Text(text = "Optional Advantage: Target Choice")}
                TechniqueTableRow("Target Choice", 2, 2, 10, 1, 1, 2)
            }

            //Automatic Transportation
            22-> {
                kiIndex.value = 2
                elementList.value = listOf(Element.Air, Element.Light, Element.Dark)
                ElementDisplayRow()
                buildArray.value = listOf(2, 2, 0, 2, 3, null)

                TechniqueTableHeader("Distance")
                TechniqueTableRow("10m", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("20m", 3, 5, 10, 2, 1, 1)
                TechniqueTableRow("50m", 4, 6, 10, 3, 1, 1)
                TechniqueTableRow("100m", 5, 8, 15, 4, 1, 1)
                TechniqueTableRow("250m", 6, 9, 20, 5, 1, 1)
                TechniqueTableRow("500m", 8, 11, 25, 6, 2, 1)
                TechniqueTableRow("1km", 10, 13, 30, 7, 2, 1)
                TechniqueTableRow("5km", 14, 18, 35, 8, 2, 1)
                TechniqueTableRow("10km", 18, 22, 40, 10, 3, 1)
                TechniqueTableRow("100km", 22, 26, 50, 12, 3, 1)
            }

            //Critical Enhancement
            23-> {
                kiIndex.value = 4
                elementList.value = listOf(Element.Fire, Element.Earth)
                ElementDisplayRow()
                buildArray.value = listOf(1, 2, null, 2, 0, 1)

                TechniqueTableHeader("Critical")
                TechniqueTableRow("+10", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("+25", 3, 5, 5, 2, 1, 1)
                TechniqueTableRow("+40", 4, 6, 10, 3, 1, 1)
                TechniqueTableRow("+50", 5, 8, 15, 4, 1, 1)
                TechniqueTableRow("+75", 8, 11, 20, 6, 1, 1)
                TechniqueTableRow("+90", 12, 15, 25, 8, 1, 1)
                TechniqueTableRow("+100", 14, 18, 30, 10, 1, 1)
                TechniqueTableRow("+125", 18, 22, 35, 12, 2, 1)
                TechniqueTableRow("+150", 22, 26, 40, 14, 2, 1)
                TechniqueTableRow("+175", 26, 32, 45, 16, 3, 1)
                TechniqueTableRow("+200", 30, 36, 50, 18, 3, 1)

                Row{Text(text = "Optional Advantage: Automatic Critical")}
                TechniqueTableRow("Automatic Critical", 8, 8, 30, 4, 1, 2)
            }

            //Physical Ki Weapons
            24-> {
                kiIndex.value = 4
                elementList.value = listOf(Element.Light, Element.Dark, Element.Earth)
                ElementDisplayRow()
                buildArray.value = listOf(2, 3, null, 1, 0, 1)

                TechniqueTableHeader("Quality")
                TechniqueTableRow("+0", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("+5", 4, 6, 5, 1, 1, 1)
                TechniqueTableRow("+10", 6, 9, 10, 2, 1, 1)
                TechniqueTableRow("+15", 8, 11, 15, 3, 2, 1)
                TechniqueTableRow("+20", 10, 13, 20, 4, 3, 1)

                Row{Text(text = "Optional Advantage: Projectiles")}
                TechniqueTableRow("Projectile Weapon", 2, 2, 10, 1, 1, 2)
            }

            //Trapping
            25-> {
                kiIndex.value = 0
                elementList.value = listOf(Element.Earth)
                ElementDisplayRow()
                buildArray.value = listOf(0, 1, null, 2, 2, 2)

                TechniqueTableHeader("Trap")
                TechniqueTableRow("4", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("6", 3, 5, 10, 2, 1, 1)
                TechniqueTableRow("8", 4, 6, 10, 3, 1, 1)
                TechniqueTableRow("10", 5, 8, 15, 4, 1, 1)
                TechniqueTableRow("12", 6, 9, 20, 5, 1, 1)
                TechniqueTableRow("14", 8, 11, 25, 6, 2, 1)
                TechniqueTableRow("16", 10, 13, 30, 7, 2, 1)
                TechniqueTableRow("18", 14, 18, 35, 8, 3, 1)
                TechniqueTableRow("20", 18, 22, 40, 10, 3, 1)
            }

            //Projection
            26-> {
                kiIndex.value = 0
                elementList.value = listOf(Element.Earth, Element.Fire)
                ElementDisplayRow()
                buildArray.value = listOf(0, 3, null, 2, 1, 1)

                TechniqueTableHeader("Projection")
                TechniqueTableRow("4", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("6", 2, 4, 5, 2, 1, 1)
                TechniqueTableRow("8", 3, 5, 10, 3, 1, 1)
                TechniqueTableRow("10", 4, 6, 10, 4, 1, 1)
                TechniqueTableRow("12", 5, 8, 15, 5, 1, 1)
                TechniqueTableRow("14", 6, 9, 20, 6, 2, 1)
                TechniqueTableRow("16", 8, 11, 25, 7, 2, 1)
                TechniqueTableRow("18", 10, 13, 30, 8, 3, 1)
                TechniqueTableRow("20", 12, 15, 35, 10, 3, 1)
            }

            //Energy Shield
            27-> {
                kiIndex.value = 4
                elementList.value = listOf(Element.Light, Element.Water)
                ElementDisplayRow()
                buildArray.value = listOf(2, 3, null, 2, 0, 1)

                TechniqueTableHeader("LP")
                TechniqueTableRow("100", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("200", 3, 5, 5, 1, 1, 1)
                TechniqueTableRow("300", 4, 6, 10, 2, 1, 1)
                TechniqueTableRow("400", 5, 8, 15, 3, 1, 1)
                TechniqueTableRow("500", 8, 11, 20, 4, 1, 1)
                TechniqueTableRow("800", 12, 15, 25, 5, 2, 1)
                TechniqueTableRow("1000", 14, 18, 30, 8, 2, 1)
                TechniqueTableRow("1250", 18, 22, 35, 10, 2, 1)
                TechniqueTableRow("1500", 22, 26, 40, 12, 3, 1)
                TechniqueTableRow("2000", 26, 32, 45, 14, 3, 1)
            }

            //Intangibility
            28-> {
                kiIndex.value = 4
                elementList.value = listOf(Element.Light, Element.Dark, Element.Water)
                ElementDisplayRow()
                buildArray.value = listOf(3, 3, null, 3, 0, 1)

                TechniqueTableHeader("Effect")
                TechniqueTableRow("Intangibility", 3, 5, 10, 2, 1, 1)
            }

            //Mirage
            29-> {
                kiIndex.value = 5
                elementList.value = listOf(Element.Water, Element.Dark)
                ElementDisplayRow()
                buildArray.value = listOf(null, 3, 2, 3, 1, 0)

                TechniqueTableHeader("Mirages")
                TechniqueTableRow("1", 1, 2, 5, 1, 1, 1)
                TechniqueTableRow("2", 2, 4, 5, 2, 1, 1)
                TechniqueTableRow("4", 4, 6, 10, 3, 1, 1)
                TechniqueTableRow("6", 6, 9, 10, 4, 1, 1)
                TechniqueTableRow("10", 8, 11, 15, 6, 2, 1)
                TechniqueTableRow("15", 10, 13, 20, 8, 2, 1)
                TechniqueTableRow("20", 12, 15, 25, 10, 2, 1)
                TechniqueTableRow("25", 14, 18, 30, 12, 3, 1)

                Row{Text(text = "Optional Advantage: Non-Detection")}
                TechniqueTableRow("Moderate", 1, 1, 5, 0, 1, 2)
                TechniqueTableRow("Difficult", 2, 2, 10, 0, 1, 2)
                TechniqueTableRow("Very Difficult", 3, 3, 10, 0, 1, 2)
                TechniqueTableRow("Absurd", 4, 4, 15, 0, 1, 2)
                TechniqueTableRow("Almost Impossible", 5, 5, 15, 0, 1, 2)
                TechniqueTableRow("Impossible", 6, 6, 20, 0, 2, 2)
                TechniqueTableRow("Inhuman", 7, 7, 25, 0, 2, 2)
                TechniqueTableRow("Zen", 8, 8, 30, 0, 3, 2)
            }

            //Attack Mirroring
            30-> {
                kiIndex.value = 4
                elementList.value = listOf(Element.Light, Element.Dark, Element.Water)
                ElementDisplayRow()
                buildArray.value = listOf(2, 3, 3, null, 0, 1)

                TechniqueTableHeader("Effect")
                TechniqueTableRow("Attack Mirroring", 12, 15, 30, 8, 2, 1)

                Row{Text(text = "Optional Advantage: Target Choice")}
                TechniqueTableRow("Target Choice", 2, 2, 10, 2, 1, 2)

                Row{Text(text = "Optional Advantage: Mirroring Esoteric Abilities")}
                TechniqueTableRow("Mirror Esoteric Abilities", 4, 4, 20, 1, 1, 3)
            }

            //Energy Damaging Attack
            31-> {
                kiIndex.value = 4
                elementList.value = listOf(Element.Fire, Element.Light, Element.Dark)
                ElementDisplayRow()
                buildArray.value = listOf(3, 3, null, 2, 0, 1)

                TechniqueTableHeader("Attack")
                TechniqueTableRow("Energy", 1, 2, 5, 1, 1, 1)
            }

            //Elemental Attack
            32-> {
                kiIndex.value = 4
                elementList.value = listOf()
                ElementDisplayRow()
                buildArray.value = listOf(3, 3, null, 2, 0, 1)

                TechniqueTableHeader("Attack")
                TechniqueTableRow("2", 2, 4, 5, 1, 1, 1)

                Row{Text(text = "Select Element: ")}
                ElementalRow(Element.Fire)
                ElementalRow(Element.Water)
                ElementalRow(Element.Air)
                ElementalRow(Element.Earth)
            }

            //Supernatural Attack
            33-> {
                kiIndex.value = 4
                elementList.value = listOf(Element.Light, Element.Dark)
                ElementDisplayRow()
                buildArray.value = listOf(3, 3, null, 2, 0, 1)

                TechniqueTableHeader("Attack")
                TechniqueTableRow("Energy", 5, 8, 10, 1, 1, 1)
            }

            //Damage Resistance
            34-> {
                kiIndex.value = 3
                elementList.value = listOf(Element.Earth)
                ElementDisplayRow()
                buildArray.value = listOf(3, 3, null, 0, 3, 1)

                TechniqueTableHeader("LP")
                TechniqueTableRow("100", 2, 4, 5, 1, 1, 1)
                TechniqueTableRow("200", 3, 5, 5, 1, 1, 1)
                TechniqueTableRow("300", 4, 6, 10, 2, 1, 1)
                TechniqueTableRow("400", 5, 8, 15, 3, 1, 1)
                TechniqueTableRow("600", 8, 11, 20, 4, 1, 1)
                TechniqueTableRow("800", 12, 15, 25, 5, 1, 1)
                TechniqueTableRow("1000", 14, 18, 30, 8, 2, 1)
                TechniqueTableRow("1200", 18, 22, 35, 10, 2, 1)
                TechniqueTableRow("1400", 22, 26, 40, 12, 3, 1)
            }

            //Elemental Binding
            35-> {
                elementList.value = listOf()
                buildArray.value = listOf(null, null, null, null, null, null)

                TechniqueTableRow("Single Element", 0, 0, -15, 0, 1, 1)
                TechniqueTableRow("Two Elements", 0, 0, -10, 0, 1, 1)

                Row{Text(text = "Select Element(s): ")}
                ElementalRow(Element.Fire)
                ElementalRow(Element.Water)
                ElementalRow(Element.Air)
                ElementalRow(Element.Earth)
                ElementalRow(Element.Light)
                ElementalRow(Element.Dark)
            }

            //Reduce Damage
            36-> {
                elementList.value = listOf(Element.Free)
                buildArray.value = listOf(null, null, null, null, null, null)

                TechniqueTableRow("No Damage", 0, 0, -20, 0, 1, 1)
                TechniqueTableRow("Half Damage", 0, 0, -10, 0, 1, 1)
            }

            //Special Requirements
            37-> {
                elementList.value = listOf(Element.Free)
                buildArray.value = listOf(null, null, null, null, null, null)

                TechniqueTableRow("Simple Intensity", 0, 0, -15, 0, 1, 1)
                TechniqueTableRow("Major Intensity", 0, 0, -10, 0, 1, 1)
                TechniqueTableRow("Determined Condition", 0, 0, -5, 0, 1, 1)
            }

            38-> {
                elementList.value = listOf(Element.Free)
                buildArray.value = listOf(null, null, null, null, null, null)

                TechniqueTableRow("Predetermination", 0, 0, -20, 0, 1, 1)
            }

            else -> {}
        }
    }
}

@Composable
private fun TechniqueTableHeader(
    description: String
){
    Row(verticalAlignment = Alignment.CenterVertically)
    {
        Spacer(Modifier.weight(0.1f))
        Text(text = description, textAlign = TextAlign.Center, modifier = Modifier.weight(0.4f))
        Text(text = "P", textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = "S", textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = "MK", textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = "Mt", textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = "Lvl", textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
    }
}

@Composable
private fun TechniqueTableRow(
    effect: String,
    primaryCost: Int,
    secondaryCost: Int,
    mkCost: Int,
    maintCost: Int,
    level: Int,
    listNum: Int
){
    val thisCheck = remember{ mutableStateOf(false) }

    val defaultArray = mutableListOf(0, 0, 0, 0, 0, 0)
    defaultArray[kiIndex.value] =
        if(isPrimary.value)
            primaryCost
        else
            secondaryCost

    val useString =
        if(techniqueIndex.value < 35)
            stringArrayResource(R.array.techniqueAbilities)[techniqueIndex.value]
        else
            stringArrayResource(R.array.techniqueDisadvantages)[techniqueIndex.value - 35]

    val thisEffect =
        TechniqueEffect(useString, effect, mkCost, maintCost, Pair(primaryCost, secondaryCost),
            defaultArray, buildArray.value, getSelectedElement(), level)

    when(listNum) {
        1 -> allEffectChecks = allEffectChecks + Pair(thisEffect, thisCheck)
        2 -> advantageOneCheck = advantageOneCheck + Pair(thisEffect, thisCheck)
        3 -> advantageTwoCheck = advantageTwoCheck + Pair(thisEffect, thisCheck)
    }

    Row(verticalAlignment = Alignment.CenterVertically)
    {
        Checkbox(
            checked = thisCheck.value,
            onCheckedChange = {
                if(it && customTechnique.validEffectAddition(thisEffect, remainingMK.value.toInt())) {
                    when (listNum) {
                        1 -> allEffectChecks.forEach { boxVal -> boxVal.second.value = false }
                        2 -> advantageOneCheck.forEach { boxVal -> boxVal.second.value = false }
                        3 -> advantageTwoCheck.forEach { boxVal -> boxVal.second.value = false }
                    }

                    thisCheck.value = true
                }
                else
                    thisCheck.value = false
            },
            modifier = Modifier.weight(0.1f)
        )
        Text(text = effect, textAlign = TextAlign.Center, modifier = Modifier.weight(0.4f))
        Text(text = primaryCost.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = secondaryCost.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = mkCost.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = maintCost.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = level.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
    }
}

@Composable
private fun ElementalRow(elementType: Element){
    val checkStatus = remember{ mutableStateOf(false) }
    elementChecks = elementChecks + Pair(elementType, checkStatus)

    Row{
        Checkbox(
            checked = checkStatus.value,
            onCheckedChange = {
                if(techniqueIndex.value == 32 || allEffectChecks[0].second.value){
                    if(it)
                        elementChecks.forEach{item ->
                            item.second.value = false
                        }

                    checkStatus.value = it
                }
                else{
                    checkStatus.value = it && getSelectedElement().size <= 2
                }
            }
        )

        Text(text = elementType.name)
    }
}

@Composable
private fun ElementDisplayRow(){
    val name = stringArrayResource(id = R.array.techniqueAbilities)[techniqueIndex.value]
    var elementString = ""

    elementList.value.forEach{
        elementString += it.name
        if(elementList.value.indexOf(it) < elementList.value.size)
            elementString += ", "
    }

    LazyRow {
        item {
            Text(text = "Elements of $name: $elementString")
        }
    }
}

@Composable
private fun EditEffectRow(effect: TechniqueEffect){
    val deleteCheck = remember{mutableStateOf(false)}
    var elementString = ""
    effect.elements.forEach{
        elementString += it.name

        if(effect.elements.indexOf(it) != effect.elements.size - 1)
            elementString += "/"
    }

    LazyRow {
        item {
            Checkbox(
                checked = deleteCheck.value,
                onCheckedChange = {
                    deleteCheck.value = it

                    if(it)
                        toRemove.value += effect
                    else
                        toRemove.value -= effect
                }
            )
            Text(text = effect.name)
            Text(text = effect.effect)
        }
    }

    LazyRow {
        item {
            Text(text = effect.mkCost.toString())
            Text(text = elementString)
        }
    }
}

@Composable
private fun EditBuildRow(
    effect: TechniqueEffect,
    index: Int,
    workArray: MutableList<Int>
){
    Row {
        if (effect.buildAdditions[index] != null) {
            Text(text = getStatName(index) + ": ", modifier = Modifier.weight(0.4f))

            val buildVal = remember { mutableStateOf(workArray[index].toString()) }
            TextField(
                value = buildVal.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { input ->
                    numberCatcher(input,
                        { catchIn: String ->
                            workArray[index] = catchIn.toInt()
                            allAccs[index].value = getAccTotal(index)
                            buildVal.value = catchIn
                        },
                        {
                            workArray[index] = 0
                            allAccs[index].value = getAccTotal(index)
                            buildVal.value = ""
                        }
                    )},
                modifier = Modifier.weight(0.2f)
            )

            Text(text = "+" + effect.buildAdditions[index], modifier = Modifier.weight(0.4f))
        }
    }
}

@Composable
private fun MaintenanceInput(index: Int){
    val maintInput = remember{mutableStateOf(customTechnique.maintArray[index].toString())}

    Row{
        Text(text = getStatName(index), modifier = Modifier.weight(0.5f))

        TextField(
            value = maintInput.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                numberCatcher(
                    it,
                    { input ->
                        customTechnique.maintArray[index] = input.toInt()
                        maintInput.value = input
                    },
                    {
                        customTechnique.maintArray[index] = 0
                        maintInput.value = ""
                    }
                )
            },
            modifier = Modifier.weight(0.5f)
        )
    }
}

private fun getAccTotal(index: Int): String{
    var total = 0

    allKiBuilds.value.forEach{
        total += it.second[index]
    }

    return total.toString()
}

private fun getSelectedEffect(): TechniqueEffect?{
    allEffectChecks.forEach{
        if(it.second.value && it.first.elements.isNotEmpty())
            return it.first
    }

    advantageOneCheck.forEach{
        if(it.second.value && it.first.elements.isNotEmpty())
            return it.first
    }

    advantageTwoCheck.forEach{
        if(it.second.value && it.first.elements.isNotEmpty())
            return it.first
    }

    return null
}

private fun getSelectedElement(): List<Element>{
    if(elementList.value.isNotEmpty())
        return elementList.value

    var output: List<Element> = listOf()

    elementChecks.forEach{
        if(it.second.value)
            output = output + it.first
    }

    return output
}

private fun findBuild(token: String): Pair<String, MutableList<Int>>?{
    allKiBuilds.value.forEach{
        if(token == it.first)
            return it
    }

    return null
}