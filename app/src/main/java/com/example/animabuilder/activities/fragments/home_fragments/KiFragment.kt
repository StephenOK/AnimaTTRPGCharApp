package com.example.animabuilder.activities.fragments.home_fragments

import android.hardware.lights.Light
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
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

private val selected = mutableStateOf("1")

private val customTechOn = mutableStateOf(false)
private var customConfirm: MutableState<(() -> Unit)> = mutableStateOf({})
private var customContents: MutableState<@Composable () -> Unit> = mutableStateOf({})

private var techniqueIndex = mutableStateOf(0)
private lateinit var allEffectChecks: List<Pair<TechniqueEffect, MutableState<Boolean>>>
private lateinit var advantageOneCheck: List<Pair<TechniqueEffect, MutableState<Boolean>>>
private lateinit var advantageTwoCheck: List<Pair<TechniqueEffect, MutableState<Boolean>>>

private var isPrimary = mutableStateOf(true)
private var kiIndex: MutableState<Int?> = mutableStateOf(0)
private var elementList: MutableState<List<Element>> = mutableStateOf(listOf())
private var elementChecks: List<Pair<Element, MutableState<Boolean>>> = listOf()

private lateinit var customTechnique: Technique

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
                        customTechOn.value = true
                        customContents.value = customFirstContents
                        customConfirm.value = customFirstConfirm
                        selected.value = "1"
                    }
                ) {
                    Text(text = "Add Technique")
                }
            }
        }
    }

    if(customTechOn.value)
        CustomTechniqueAlert(customContents.value, customConfirm.value)
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

@Composable
private fun CustomTechniqueAlert(
    contents: @Composable () -> Unit,
    confirmAction: () -> Unit
){
    AlertDialog(
        onDismissRequest = {customTechOn.value = false},
        title = {Text(text = "Create Custom Technique")},
        text = {
            Box(modifier = Modifier.size(300.dp, 300.dp)){
                contents()
            }
        },
        confirmButton = {TextButton(onClick = {confirmAction()}){Text(text = "Next")} },
        dismissButton = {TextButton(onClick = {customTechOn.value = false}){Text(text = "Cancel")} }
    )
}

val customFirstContents = @Composable {
    customTechnique = Technique("", "", selected.value.toInt(), false, listOf())
    techniqueIndex.value = 0

    Column{
        Row{Text(text = "Select the Technique's Level:")}
        Column{
            Row {
                RadioButton(
                    selected = selected.value == "1",
                    onClick = { selected.value = "1" })
                Text(text = "1")
            }
            Row{
                RadioButton(
                    selected = selected.value == "2",
                    onClick = {selected.value = "2"})
                Text(text = "2")
            }
            Row{
                RadioButton(
                    selected = selected.value == "3",
                    onClick = {selected.value = "3"})
                Text(text = "3")
            }
        }

        val minMK = when(selected.value){
            "1" -> 20
            "2" -> 40
            "3" -> 60
            else -> 0
        }

        val maxMK = when(selected.value){
            "1" -> 50
            "2" -> 100
            "3" -> 200
            else -> 0
        }

        Text(text = "Martial Knowledge Range: $minMK - $maxMK")
    }
}

val customFirstConfirm = {
    isPrimary.value = true
    customTechnique.level = selected.value.toInt()
    customContents.value = customSecondContents
    customConfirm.value = customSecondConfirm
}

val customSecondContents = @Composable{
    Column{
        Text(text = "Select Primary Ability: ")
        TechniqueAbilityDropdown()
        TechniqueTable()
    }
}

val customSecondConfirm = {
    if(getSelectedEffect().isNotEmpty()) {
        isPrimary.value = false
        customTechnique.givenAbilities = getSelectedEffect()
        customContents.value = customThirdContents
        customConfirm.value = customThirdConfirm
    }
}

val customThirdContents = @Composable{
    Column{
        Text(text = "Add Secondary Abilities: ")
        TechniqueAbilityDropdown()
        TechniqueTable()
    }
}

val customThirdConfirm = {}

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

    Column(modifier = Modifier.verticalScroll(rememberScrollState())
        .fillMaxWidth()
        .fillMaxHeight()){
        when(techniqueIndex.value){
            //Attack Ability
            1 -> {
                kiIndex.value = 1
                elementList.value = listOf(Element.Air, Element.Fire, Element.Dark)

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

                TechniqueTableHeader("Multiplier")
                TechniqueTableRow("x2", 10, 15, 25, 4, 1, 1)
                TechniqueTableRow("x3", 15, 20, 40, 8, 2, 1)
                TechniqueTableRow("x4", 20, 30, 80, 12, 3, 1)
            }

            //Damage Augmentation
            8-> {
                kiIndex.value = 0
                elementList.value = listOf(Element.Fire, Element.Earth)

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
                TechniqueTableRow("Action Penalty", 2, 2, 5, null,  1, 2)
                TechniqueTableRow("PhR Reduction", 2, 2, 10, null,  1, 2)
                TechniqueTableRow("Blindness", 5, 5, 15, null,  1, 2)
                TechniqueTableRow("Characteristic Reduction", 2, 2, 10, null, 1, 2)
                TechniqueTableRow("Partial Paralysis", 6, 6, 10, null, 1, 2)
                TechniqueTableRow("Damage", 5, 5, 10, null, 1, 2)
                TechniqueTableRow("Unconscious", 8, 8, 15, null, 1, 2)
                TechniqueTableRow("Coma", 10, 10, 30, null, 2, 2)
                TechniqueTableRow("Total Paralysis", 8, 8, 20, null, 2, 2)
                TechniqueTableRow("Life Drain", 8, 8, 15, null, 2, 2)
                TechniqueTableRow("Control", 10, 10, 40, null, 3, 2)
                TechniqueTableRow("Death", 12, 12, 50, null, 3, 2)
            }

            //Combat Maneuvers and Aiming
            15-> {
                kiIndex.value = 1
                elementList.value = listOf(Element.Air)

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

                TechniqueTableHeader("Effect")
                TechniqueTableRow("Intangibility", 3, 5, 10, 2, 1, 1)
            }

            //Mirage
            29-> {
                kiIndex.value = 5
                elementList.value = listOf(Element.Water, Element.Dark)

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
                TechniqueTableRow("Moderate", 1, 1, 5, null, 1, 2)
                TechniqueTableRow("Difficult", 2, 2, 10, null, 1, 2)
                TechniqueTableRow("Very Difficult", 3, 3, 10, null, 1, 2)
                TechniqueTableRow("Absurd", 4, 4, 15, null, 1, 2)
                TechniqueTableRow("Almost Impossible", 5, 5, 15, null, 1, 2)
                TechniqueTableRow("Impossible", 6, 6, 20, null, 2, 2)
                TechniqueTableRow("Inhuman", 7, 7, 25, null, 2, 2)
                TechniqueTableRow("Zen", 8, 8, 30, null, 3, 2)
            }

            //Attack Mirroring
            30-> {
                kiIndex.value = 4
                elementList.value = listOf(Element.Light, Element.Dark, Element.Water)

                TechniqueTableHeader("Effect")
                TechniqueTableRow("Attack Mirroring", 12, 15, 30, 8, 2, 1)

                Row{Text(text = "Optional Advantage: Target Choice")}
                TechniqueTableRow("Target Choide", 2, 2, 10, 2, 1, 2)

                Row{Text(text = "Optional Advantage: Mirroring Esoteric Abilities")}
                TechniqueTableRow("Mirror Esoteric Abilities", 4, 4, 20, 1, 1, 3)
            }

            //Energy Damaging Attack
            31-> {
                kiIndex.value = 4
                elementList.value = listOf(Element.Fire, Element.Light, Element.Dark)

                TechniqueTableHeader("Attack")
                TechniqueTableRow("Energy", 1, 2, 5, 1, 1, 1)
            }

            //Elemental Attack
            32-> {
                kiIndex.value = 4
                elementList.value = listOf()

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

                TechniqueTableHeader("Attack")
                TechniqueTableRow("Energy", 5, 8, 10, 1, 1, 1)
            }

            //Damage Resistance
            34-> {
                kiIndex.value = 3
                elementList.value = listOf(Element.Earth)

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
                elementList.value = listOf(Element.Free)

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

                TechniqueTableRow("No Damage", 0, 0, -20, 0, 1, 1)
                TechniqueTableRow("Half Damage", 0, 0, -10, 0, 1, 1)
            }

            //Special Requirements
            37-> {
                elementList.value = listOf(Element.Free)

                TechniqueTableRow("Simple Intensity", 0, 0, -15, 0, 1, 1)
                TechniqueTableRow("Major Intensity", 0, 0, -10, 0, 1, 1)
                TechniqueTableRow("Determined Condition", 0, 0, -5, 0, 1, 1)
            }

            38-> {
                elementList.value = listOf(Element.Free)

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
    maintCost: Int?,
    level: Int,
    listNum: Int
){
    val thisCheck = remember{ mutableStateOf(false) }

    val defaultArray = mutableListOf(0, 0, 0, 0, 0, 0)
    if(kiIndex.value != null){
        defaultArray[kiIndex.value!!] =
            if(isPrimary.value)
                primaryCost
            else
                secondaryCost
    }

    val useString =
        if(techniqueIndex.value < 35)
            stringArrayResource(R.array.techniqueAbilities)[techniqueIndex.value]
    else
        stringArrayResource(R.array.techniqueDisadvantages)[techniqueIndex.value - 35]

    val thisEffect =
        TechniqueEffect(useString, effect, mkCost, maintCost, kiIndex.value, defaultArray, elementList.value, level)

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
                if(it && customTechnique.validEffectAddition(thisEffect)) {
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

private fun getSelectedEffect(): List<TechniqueEffect>{
    var output: List<TechniqueEffect> = listOf()

    allEffectChecks.forEach{
        if(it.second.value)
            output = output + it.first
    }

    advantageOneCheck.forEach{
        if(it.second.value)
            output = output + it.first
    }

    advantageTwoCheck.forEach{
        if(it.second.value)
            output = output + it.first
    }

    return output
}

private fun getSelectedElement(): List<Element>{
    var output: List<Element> = listOf()

    elementChecks.forEach{
        if(it.second.value)
            output = output + it.first
    }

    return output
}