package com.example.animabuilder.activities.fragments.dialogs

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.animabuilder.activities.charInstance
import com.example.animabuilder.activities.numberCatcher
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.ki_abilities.Technique
import com.example.animabuilder.character_creation.attributes.ki_abilities.TechniqueEffect

@Composable
fun CustomTechnique(
    techContents: @Composable (Technique) -> Unit,
    deactivate: () -> Unit
) {
    val context = LocalContext.current
    val statName = listOf("STR", "DEX", "AGI", "CON", "POW", "WP")

    val pageNum = remember{mutableStateOf(1)}
    val customTechnique = Technique("", "", 1, mutableListOf(0, 0, 0, 0, 0, 0), listOf())
    val techniqueIndex = remember{mutableStateOf(0)}

    val customTechLevelSelection = remember{mutableStateOf(1)}

    val isPrimary = remember{mutableStateOf(false)}

    val allEffectChecks = mutableListOf<Pair<TechniqueEffect, MutableState<Boolean>>>()
    val optionalCheck1 = mutableListOf<Pair<TechniqueEffect, MutableState<Boolean>>>()
    val optionalCheck2 = mutableListOf<Pair<TechniqueEffect, MutableState<Boolean>>>()
    val allElementChecks = mutableListOf<Pair<Element, MutableState<Boolean>>>()

    val removeEffects = mutableListOf<TechniqueEffect>()

    val allKiBuilds = mutableListOf<Pair<String, MutableList<Int>>>()

    val strAccTotal = remember{mutableStateOf("")}
    val dexAccTotal = remember{mutableStateOf("")}
    val agiAccTotal = remember{mutableStateOf("")}
    val conAccTotal = remember{mutableStateOf("")}
    val powAccTotal = remember{mutableStateOf("")}
    val wpAccTotal = remember{mutableStateOf("")}

    val customTechMaintenanceSelection = remember{mutableStateOf(false)}

    Dialog(
        onDismissRequest = {},
        content = {
            Box(
                Modifier.background(Color.White)
                .size(600.dp, 600.dp)) {

                Row(Modifier.align(Alignment.TopCenter)
                    .height(100.dp)){Text(text = "Create Custom Technique")}

                Row(
                    Modifier
                        .align(Alignment.Center)
                        .height(400.dp)) {
                    when (pageNum.value) {
                        //page for determining technique level
                        1 -> {
                            Column{
                                Row{Text(text = "Select the Technique's Level:")}
                                Column{
                                    Row {
                                        RadioButton(
                                            selected = customTechLevelSelection.value == 1,
                                            onClick = { customTechLevelSelection.value = 1 })
                                        Text(text = "1")
                                    }
                                    Row{
                                        RadioButton(
                                            selected = customTechLevelSelection.value == 2,
                                            onClick = {customTechLevelSelection.value = 2})
                                        Text(text = "2")
                                    }
                                    Row{
                                        RadioButton(
                                            selected = customTechLevelSelection.value == 3,
                                            onClick = {customTechLevelSelection.value = 3})
                                        Text(text = "3")
                                    }
                                }

                                val minMK = when(customTechLevelSelection.value){
                                    1 -> 20
                                    2 -> 40
                                    3 -> 60
                                    else -> 0
                                }

                                val maxMK = when(customTechLevelSelection.value){
                                    1 -> 50
                                    2 -> 100
                                    3 -> 200
                                    else -> 0
                                }

                                Text(text = "Martial Knowledge Range: $minMK - $maxMK")
                            }
                        }

                        //page for determining primary effect
                        2 -> {
                            isPrimary.value = true
                            customTechnique.givenAbilities = listOf()
                            allKiBuilds.clear()
                            techniqueIndex.value = 0

                            Column{
                                Text(text = "Select Primary Ability: ")
                                TechniqueAbilityDropdown(
                                    isPrimary.value,
                                    techniqueIndex,
                                    allEffectChecks,
                                    optionalCheck1,
                                    optionalCheck2,
                                    allElementChecks,
                                    customTechnique,
                                    { input: Int -> techniqueIndex.value = input}
                                ) {
                                    allEffectChecks.clear()
                                    optionalCheck1.clear()
                                    optionalCheck2.clear()
                                    allElementChecks.clear()
                                }
                            }
                        }

                        //page for determining secondary effects
                        3 -> {
                            techniqueIndex.value = 0

                            Column{
                                Text(text = "Add Secondary Abilities: ")
                                TechniqueAbilityDropdown(
                                    isPrimary.value,
                                    techniqueIndex,
                                    allEffectChecks,
                                    optionalCheck1,
                                    optionalCheck2,
                                    allElementChecks,
                                    customTechnique,
                                    { input: Int -> techniqueIndex.value = input}
                                ) {
                                    allEffectChecks.clear()
                                    optionalCheck1.clear()
                                    optionalCheck2.clear()
                                    allElementChecks.clear()
                                }
                            }
                        }

                        //page for editing taken secondary effects
                        4 -> {
                            LazyColumn{
                                customTechnique.givenAbilities.forEach{
                                    item{EditEffectRow(it, removeEffects)}
                                }

                                item{Text(text = "MK: " + customTechnique.mkCost().toString())}
                            }
                        }

                        //page for redistributing ki accumulations
                        5 -> {
                            strAccTotal.value = getAccTotal(0, allKiBuilds)
                            dexAccTotal.value = getAccTotal(1, allKiBuilds)
                            agiAccTotal.value = getAccTotal(2, allKiBuilds)
                            conAccTotal.value = getAccTotal(3, allKiBuilds)
                            powAccTotal.value = getAccTotal(4, allKiBuilds)
                            wpAccTotal.value = getAccTotal(5, allKiBuilds)
                            val allAccs = listOf(strAccTotal, dexAccTotal, agiAccTotal, conAccTotal, powAccTotal, wpAccTotal)

                            Column{
                                Row{Text(text = "Accumulation Totals:")}
                                Row{
                                    for(index in 0..5)
                                        Text(text = statName[index], modifier = Modifier.weight(0.13f))
                                }

                                Row {
                                    Text(text = strAccTotal.value, modifier = Modifier.weight(0.13f))
                                    Text(text = dexAccTotal.value, modifier = Modifier.weight(0.13f))
                                    Text(text = agiAccTotal.value, modifier = Modifier.weight(0.13f))
                                    Text(text = conAccTotal.value, modifier = Modifier.weight(0.13f))
                                    Text(text = powAccTotal.value, modifier = Modifier.weight(0.13f))
                                    Text(text = wpAccTotal.value, modifier = Modifier.weight(0.13f))
                                }

                                LazyColumn{
                                    customTechnique.givenAbilities.forEach {
                                        item { Text(text = it.name) }

                                        for (index in 0..5) {
                                            item{
                                                EditBuildRow(
                                                    it,
                                                    index,
                                                    findBuild(it.name, allKiBuilds)!!.second,
                                                    statName[index]
                                                )
                                                {allAccs[index].value = getAccTotal(index, allKiBuilds)}
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        //page for determining maintenance values
                        6 -> {
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
                                                MaintenanceInput(
                                                    index,
                                                    customTechnique,
                                                    statName[index]
                                                )
                                    }
                                }
                            }
                        }

                        //page for naming and describing technique
                        7 -> {
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

                        //confirmation page
                        8 -> {
                            Row{Text(text = "Description of " + customTechnique.name)}
                            Row{techContents(customTechnique)}
                        }

                        else -> {deactivate()}
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(100.dp)){

                    //button to terminate process
                    TextButton(onClick = {deactivate()}){Text(text = "Cancel")}

                    //if on edit page, button to delete selected items
                    if(pageNum.value == 4){
                        TextButton(
                            onClick = {
                                customTechnique.givenAbilities -= removeEffects
                                removeEffects.forEach {removable ->
                                    allKiBuilds.forEach{
                                        if(removable.name == it.first)
                                            allKiBuilds.remove(it)
                                    }
                                }

                                removeEffects.clear()

                                customTechnique.fixPrimaryAbility()

                                if (customTechnique.givenAbilities.isNotEmpty())
                                    pageNum.value = 3
                                else {
                                    pageNum.value = 2
                                }
                            }
                        ) {
                            Text(text = "Delete")
                        }
                    }

                    //if on secondary page
                    if(pageNum.value == 3){
                        //option to edit taken effects
                        TextButton(onClick = {pageNum.value = 4})
                        {Text(text = "Edit")}

                        //option to add selected effect
                        TextButton(
                            onClick = {
                                val addedTechnique = listOf(
                                    getSelectedEffect(allEffectChecks, allElementChecks, true),
                                    getSelectedEffect(optionalCheck1, allElementChecks, true),
                                    getSelectedEffect(optionalCheck2, allElementChecks, true)
                                )

                                addedTechnique.forEach {
                                    val validAddition = customTechnique.validEffectAddition(
                                        it,
                                        charInstance.kiList.martialKnowledgeRemaining
                                    )

                                    if (validAddition == null) {
                                        if (techniqueIndex.value == 35)
                                            it!!.elements += Element.Free

                                        customTechnique.givenAbilities += it!!

                                        allKiBuilds += Pair(
                                            it.name,
                                            it.kiBuild.toMutableList()
                                        )

                                        techniqueIndex.value = 0
                                    }
                                    else if(it != null)
                                        Toast.makeText(context, validAddition, Toast.LENGTH_SHORT).show()
                                }
                            }
                        ) {Text(text = "Add")}
                    }

                    if(pageNum.value != 1 && pageNum.value != 4){
                        TextButton(onClick = {
                            when(pageNum.value){
                                2 -> {
                                    customTechLevelSelection.value = 1
                                    pageNum.value = 1
                                }
                                3 -> {pageNum.value = 2}
                                5 -> {
                                    techniqueIndex.value = 0
                                    pageNum.value = 3
                                }
                                6 -> {pageNum.value = 5}
                                7 -> {pageNum.value = 6}
                                8 -> {pageNum.value = 7}
                                else -> {deactivate()}
                            }
                        }){Text(text = "Back")}
                    }

                    //next page button
                    TextButton(onClick = {
                        when(pageNum.value){
                            1 -> {
                                if((customTechLevelSelection.value == 1 && charInstance.kiList.martialKnowledgeRemaining >= 20) ||
                                    (customTechLevelSelection.value == 2 && charInstance.kiList.takenFirstTechniques.size >= 2 && charInstance.kiList.martialKnowledgeRemaining >= 40) ||
                                    (customTechLevelSelection.value == 3 && charInstance.kiList.takenSecondTechniques.size >= 2 && charInstance.kiList.martialKnowledgeRemaining >= 60)) {

                                    customTechnique.level = customTechLevelSelection.value

                                    pageNum.value = 2
                                }
                            }

                            2 -> {
                                val selectedMain = getSelectedEffect(allEffectChecks, allElementChecks, true)
                                val selectedOpt1 = getSelectedEffect(optionalCheck1, allElementChecks, true)
                                val selectedOpt2 = getSelectedEffect(optionalCheck2, allElementChecks, true)

                                if(selectedMain != null){
                                    customTechnique.givenAbilities = listOf(selectedMain)
                                    allKiBuilds += Pair(selectedMain.name, selectedMain.kiBuild)
                                }

                                if(selectedOpt1 != null){
                                    customTechnique.givenAbilities = customTechnique.givenAbilities + selectedOpt1
                                    allKiBuilds += Pair(selectedOpt1.name, selectedOpt1.kiBuild)
                                }

                                if(selectedOpt2 != null){
                                    customTechnique.givenAbilities = customTechnique.givenAbilities + selectedOpt2
                                    allKiBuilds += Pair(selectedOpt2.name, selectedOpt2.kiBuild)
                                }

                                if(selectedMain != null || selectedOpt1 != null || selectedOpt2 != null) {
                                    isPrimary.value = false
                                    pageNum.value = 3
                                }
                            }

                            3 -> {
                                if((customTechnique.level == 1 && customTechnique.mkCost() >= 20) ||
                                    (customTechnique.level == 2 && customTechnique.mkCost() >= 40) ||
                                    (customTechnique.level == 3 && customTechnique.mkCost() >= 60))
                                    pageNum.value = 5
                            }

                            4 -> {pageNum.value = 3}

                            5 -> {
                                var isFirst = true
                                var moveOn = true

                                allKiBuilds.forEach{
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
                                    pageNum.value = 6
                            }
                            6 -> {
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
                                    pageNum.value = 7
                            }
                            7 -> {if(customTechnique.name != "") pageNum.value = 8}
                            8 -> {
                                charInstance.kiList.addTechnique(customTechnique)
                                deactivate()
                            }
                            else -> {}
                        }
                    }) {
                        Text(
                            text = when (pageNum.value) {
                                4 -> "Return"
                                8 -> "Confirm"
                                else -> "Next"
                            }
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun TechniqueAbilityDropdown(
    isPrimary: Boolean,
    techniqueIndex: MutableState<Int>,
    allEffectChecks: MutableList<Pair<TechniqueEffect, MutableState<Boolean>>>,
    optionalCheck1: MutableList<Pair<TechniqueEffect, MutableState<Boolean>>>,
    optionalCheck2: MutableList<Pair<TechniqueEffect, MutableState<Boolean>>>,
    elementChecks: MutableList<Pair<Element, MutableState<Boolean>>>,
    customTechnique: Technique,
    changeIndex: (Int) -> Unit,
    tableClear: () -> Unit
){
    var source = stringArrayResource(R.array.techniqueAbilities)

    if(!isPrimary)
        source += stringArrayResource(R.array.techniqueDisadvantages)

    val isOpen = remember{mutableStateOf(false)}
    val size = remember{mutableStateOf(Size.Zero)}

    val icon = if(isOpen.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column {
        OutlinedTextField(
            value = source[techniqueIndex.value],
            onValueChange = {},
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    size.value = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(
                    icon,
                    "contentDescription",
                    modifier = Modifier.clickable { isOpen.value = !isOpen.value })
            }
        )
        DropdownMenu(
            expanded = isOpen.value,
            onDismissRequest = { isOpen.value = false },
            modifier = Modifier.width(with(LocalDensity.current) { size.value.width.toDp() })
        ) {
            source.forEach {
                DropdownMenuItem(onClick = {
                    changeIndex(source.indexOf(it))
                    isOpen.value = false
                }) {
                    Text(text = it)
                }
            }
        }

        TechniqueTable(
            techniqueIndex.value,
            isPrimary,
            allEffectChecks,
            optionalCheck1,
            optionalCheck2,
            elementChecks,
            customTechnique,
            tableClear
        )
    }
}

@Composable
private fun TechniqueTable(
    index: Int,
    isPrimary: Boolean,
    allEffectChecks: MutableList<Pair<TechniqueEffect, MutableState<Boolean>>>,
    optionalCheck1: MutableList<Pair<TechniqueEffect, MutableState<Boolean>>>,
    optionalCheck2: MutableList<Pair<TechniqueEffect, MutableState<Boolean>>>,
    elementChecks: MutableList<Pair<Element, MutableState<Boolean>>>,
    customTechnique: Technique,
    listClear: () -> Unit
){
    listClear()

    val table1 = mutableListOf<TechniqueTableData>()
    val table2 = mutableListOf<TechniqueTableData>()
    val table3 = mutableListOf<TechniqueTableData>()
    val table4 = mutableListOf<TechniqueTableData>()
    val table5 = mutableListOf<TechniqueTableData>()
    val table6 = mutableListOf<TechniqueTableData>()
    val table7 = mutableListOf<TechniqueTableData>()
    val table8 = mutableListOf<TechniqueTableData>()
    val table8a = mutableListOf<TechniqueTableData>()
    val table9 = mutableListOf<TechniqueTableData>()
    val table10 = mutableListOf<TechniqueTableData>()
    val table11 = mutableListOf<TechniqueTableData>()
    val table12 = mutableListOf<TechniqueTableData>()
    val table13 = mutableListOf<TechniqueTableData>()
    val table14 = mutableListOf<TechniqueTableData>()
    val table14a = mutableListOf<TechniqueTableData>()
    val table15 = mutableListOf<TechniqueTableData>()
    val table16 = mutableListOf<TechniqueTableData>()
    val table17 = mutableListOf<TechniqueTableData>()
    val table18 = mutableListOf<TechniqueTableData>()
    val table19 = mutableListOf<TechniqueTableData>()
    val table20 = mutableListOf<TechniqueTableData>()
    val table21 = mutableListOf<TechniqueTableData>()
    val table22 = mutableListOf<TechniqueTableData>()
    val table23 = mutableListOf<TechniqueTableData>()
    val table24 = mutableListOf<TechniqueTableData>()
    val table25 = mutableListOf<TechniqueTableData>()
    val table26 = mutableListOf<TechniqueTableData>()
    val table27 = mutableListOf<TechniqueTableData>()
    val table29 = mutableListOf<TechniqueTableData>()
    val table29a = mutableListOf<TechniqueTableData>()
    val table34 = mutableListOf<TechniqueTableData>()
    val table35 = mutableListOf<TechniqueTableData>()
    val table36 = mutableListOf<TechniqueTableData>()
    val table37 = mutableListOf<TechniqueTableData>()

    val elementAttackList = listOf(Element.Fire, Element.Water, Element.Air, Element.Earth)
    val elementBindList = elementAttackList + listOf(Element.Light, Element.Dark)

    val buildArray = remember{ mutableStateOf(listOf<Int?>()) }

    table1.add(TechniqueTableData("+10", 2, 4, 5, 1, 1))
    table1.add(TechniqueTableData("+25", 3, 5, 5, 2, 1))
    table1.add(TechniqueTableData("+40", 4, 6, 10, 3, 1))
    table1.add(TechniqueTableData("+50", 5, 8, 15, 4, 1))
    table1.add(TechniqueTableData("+75", 8, 11, 20, 6, 1))
    table1.add(TechniqueTableData("+90", 12, 15, 25, 8, 1))
    table1.add(TechniqueTableData("+100", 14, 18, 30, 10, 1))
    table1.add(TechniqueTableData("+125", 18, 22, 35, 12, 2))
    table1.add(TechniqueTableData("+150", 22, 26, 40, 14, 2))
    table1.add(TechniqueTableData("+175", 26, 32, 45, 16, 3))
    table1.add(TechniqueTableData("+200", 30, 36, 50, 18, 3))

    table2.add(TechniqueTableData("+10", 1, 2, 5, 1, 1))
    table2.add(TechniqueTableData("+25", 2, 4, 5, 2, 1))
    table2.add(TechniqueTableData("+40", 3, 5, 10, 3, 1))
    table2.add(TechniqueTableData("+50", 4, 6, 10, 4, 1))
    table2.add(TechniqueTableData("+75", 6, 9, 15, 6, 1))
    table2.add(TechniqueTableData("+90", 9, 12, 20, 8, 1))
    table2.add(TechniqueTableData("+100", 12, 15, 25, 10, 1))
    table2.add(TechniqueTableData("+125", 14, 18, 30, 12, 2))
    table2.add(TechniqueTableData("+150", 18, 22, 35, 14, 2))
    table2.add(TechniqueTableData("+175", 22, 26, 40, 16, 3))
    table2.add(TechniqueTableData("+200", 26, 32, 45, 18, 3))

    table3.add(TechniqueTableData("+10", 2, 4, 5, 1, 1))
    table3.add(TechniqueTableData("+25", 3, 5, 5, 1, 1))
    table3.add(TechniqueTableData("+40", 4, 6, 10, 2, 1))
    table3.add(TechniqueTableData("+50", 5, 8, 15, 3, 1))
    table3.add(TechniqueTableData("+75", 8, 11, 20, 4, 1))
    table3.add(TechniqueTableData("+90", 12, 15, 25, 5, 1))
    table3.add(TechniqueTableData("+100", 14, 18, 30, 8, 1))
    table3.add(TechniqueTableData("125", 18, 22, 35, 10, 2))
    table3.add(TechniqueTableData("+150", 22, 26, 40, 12, 2))
    table3.add(TechniqueTableData("+175", 26, 32, 45, 14, 3))
    table3.add(TechniqueTableData("+200", 30, 36, 50, 16, 3))

    table4.add(TechniqueTableData("+10", 1, 2, 5, 1, 1))
    table4.add(TechniqueTableData("+25", 2, 4, 5, 1, 1))
    table4.add(TechniqueTableData("+40", 3, 5, 10, 1, 1))
    table4.add(TechniqueTableData("+50", 4, 6, 10, 2, 1))
    table4.add(TechniqueTableData("+75", 6, 9, 15, 3, 1))
    table4.add(TechniqueTableData("+90", 8, 11, 20, 4, 1))
    table4.add(TechniqueTableData("+100", 10, 13, 25, 6, 1))
    table4.add(TechniqueTableData("+125", 12, 15, 30, 8, 2))
    table4.add(TechniqueTableData("+150", 16, 20, 35, 10, 2))
    table4.add(TechniqueTableData("+175", 20, 24, 40, 12, 3))
    table4.add(TechniqueTableData("+200", 24, 29, 45, 14, 3))

    table5.add(TechniqueTableData("+10", 2, 4, 5, 1, 1))
    table5.add(TechniqueTableData("+25", 3, 5, 5, 1, 1))
    table5.add(TechniqueTableData("+40", 4, 6, 10, 2, 1))
    table5.add(TechniqueTableData("+50", 5, 8, 15, 3, 1))
    table5.add(TechniqueTableData("+75", 8, 11, 20, 4, 1))
    table5.add(TechniqueTableData("+90", 12, 15, 25, 5, 1))
    table5.add(TechniqueTableData("+100", 14, 18, 30, 8, 1))
    table5.add(TechniqueTableData("+125", 18, 22, 35, 10, 2))
    table5.add(TechniqueTableData("+150", 22, 26, 40, 12, 2))
    table5.add(TechniqueTableData("+175", 26, 32, 45, 14, 3))
    table5.add(TechniqueTableData("+200", 30, 36, 50, 16, 3))

    table6.add(TechniqueTableData("+10", 1, 2, 5, 1, 1))
    table6.add(TechniqueTableData("+25", 2, 4, 5, 1, 1))
    table6.add(TechniqueTableData("+40", 3, 5, 10, 1, 1))
    table6.add(TechniqueTableData("+50", 4, 6, 10, 2, 1))
    table6.add(TechniqueTableData("+75", 6, 9, 15, 3, 1))
    table6.add(TechniqueTableData("+90", 8, 11, 20, 4, 1))
    table6.add(TechniqueTableData("+100", 10, 13, 25, 6, 1))
    table6.add(TechniqueTableData("+125", 12, 15, 30, 8, 2))
    table6.add(TechniqueTableData("+150", 16, 20, 35, 10, 2))
    table6.add(TechniqueTableData("+175", 20, 24, 40, 12, 3))
    table6.add(TechniqueTableData("+200", 24, 29, 45, 14, 3))

    table7.add(TechniqueTableData("x2", 10, 15, 25, 4, 1))
    table7.add(TechniqueTableData("x3", 15, 20, 40, 8, 2))
    table7.add(TechniqueTableData("x4", 20, 30, 80, 12, 3))

    table8.add(TechniqueTableData("+10", 1, 2, 5, 1, 1))
    table8.add(TechniqueTableData("+25", 2, 4, 5, 1, 1))
    table8.add(TechniqueTableData("+40", 3, 5, 10, 1, 1))
    table8.add(TechniqueTableData("+50", 4, 6, 15, 2, 1))
    table8.add(TechniqueTableData("+75", 6, 9, 20, 3, 1))
    table8.add(TechniqueTableData("+90", 8, 11, 25, 4, 1))
    table8.add(TechniqueTableData("+100", 10, 13, 30, 5, 1))
    table8.add(TechniqueTableData("+125", 14, 18, 35, 6, 2))
    table8.add(TechniqueTableData("+150", 16, 20, 40, 8, 2))
    table8.add(TechniqueTableData("+175", 18, 22, 45, 10, 3))
    table8.add(TechniqueTableData("+200", 20, 24, 50, 12, 3))

    table8a.add(TechniqueTableData("Vital Sacrifice", 4, 4, 15, 3, 1))
    table8a.add(TechniqueTableData("Double Vital Sacrifice", 10, 10, 50, 4, 1))
    table8a.add(TechniqueTableData("Health Sacrifice", 2, 2, 10, 2, 1))
    table8a.add(TechniqueTableData("Characteristic Sacrifice", 2, 2, 10, 2, 1))

    table9.add(TechniqueTableData("+1", 6, 9, 20, 3, 1))
    table9.add(TechniqueTableData("+2", 12, 15, 30, 6, 1))
    table9.add(TechniqueTableData("+3", 18, 22, 40, 9, 1))
    table9.add(TechniqueTableData("+4", 24, 29, 50, 12, 2))
    table9.add(TechniqueTableData("+5", 30, 36, 60, 15, 3))

    table10.add(TechniqueTableData("+1", 3, 5, 5, 1, 1))
    table10.add(TechniqueTableData("+2", 6, 9, 10, 2, 1))
    table10.add(TechniqueTableData("+3", 9, 12, 15, 3, 1))
    table10.add(TechniqueTableData("+4", 12, 15, 20, 4, 1))
    table10.add(TechniqueTableData("+5", 15, 19, 30, 6, 1))
    table10.add(TechniqueTableData("+6", 18, 22, 40, 8, 2))
    table10.add(TechniqueTableData("+8", 22, 26, 50, 10, 2))
    table10.add(TechniqueTableData("+10", 26, 32, 60, 12, 3))

    table11.add(TechniqueTableData("+1", 1, 2, 5, 1, 1))
    table11.add(TechniqueTableData("+2", 2, 4, 5, 2, 1))
    table11.add(TechniqueTableData("+3", 3, 5, 10, 3, 1))
    table11.add(TechniqueTableData("+4", 4, 6, 15, 4, 1))
    table11.add(TechniqueTableData("+6", 5, 8, 20, 6, 1))
    table11.add(TechniqueTableData("+8", 6, 9, 25, 8, 1))
    table11.add(TechniqueTableData("+10", 7, 10, 30, 10, 2))
    table11.add(TechniqueTableData("Unlimited", 8, 11, 35, 12, 3))

    table12.add(TechniqueTableData("+1", 1, 2, 5, 1, 1))
    table12.add(TechniqueTableData("+2", 2, 4, 5, 2, 1))
    table12.add(TechniqueTableData("+3", 3, 5, 10, 3, 1))
    table12.add(TechniqueTableData("+4", 4, 6, 15, 4, 1))
    table12.add(TechniqueTableData("+5", 5, 8, 20, 6, 1))
    table12.add(TechniqueTableData("+6", 6, 9, 25, 8, 1))
    table12.add(TechniqueTableData("+8", 7, 10, 30, 10, 2))
    table12.add(TechniqueTableData("+10", 8, 11, 35, 12, 3))

    table13.add(TechniqueTableData("+25", 1, 2, 5, 1, 1))
    table13.add(TechniqueTableData("+50", 2, 4, 10, 1, 1))
    table13.add(TechniqueTableData("+75", 4, 6, 15, 2, 1))
    table13.add(TechniqueTableData("+100", 6, 9, 20, 3, 1))
    table13.add(TechniqueTableData("+125", 8, 11, 25, 4, 2))
    table13.add(TechniqueTableData("+150", 10, 13, 30, 5, 2))
    table13.add(TechniqueTableData("+175", 12, 15, 35, 6, 3))
    table13.add(TechniqueTableData("+200", 14, 18, 40, 7, 3))

    table14.add(TechniqueTableData("40", 1, 2, 5, 1, 1))
    table14.add(TechniqueTableData("60", 2, 4, 5, 1, 1))
    table14.add(TechniqueTableData("80", 3, 5, 10, 2, 1))
    table14.add(TechniqueTableData("100", 5, 8, 15, 3, 1))
    table14.add(TechniqueTableData("120", 6, 9, 20, 4, 1))
    table14.add(TechniqueTableData("140", 8, 11, 20, 5, 2))
    table14.add(TechniqueTableData("180", 10, 13, 30, 6, 2))
    table14.add(TechniqueTableData("200", 14, 18, 50, 8, 3))
    table14.add(TechniqueTableData("240", 18, 22, 80, 10, 3))

    table14a.add(TechniqueTableData("PhR Reduction", 2, 2, 10, 0, 1))
    table14a.add(TechniqueTableData("Blindness", 5, 5, 15, 0, 1))
    table14a.add(TechniqueTableData("Characteristic Reduction", 2, 2, 10, 0, 1))
    table14a.add(TechniqueTableData("Partial Paralysis", 6, 6, 10, 0, 1))
    table14a.add(TechniqueTableData("Damage", 5, 5, 10, 0, 1))
    table14a.add(TechniqueTableData("Unconscious", 8, 8, 15, 0, 1))
    table14a.add(TechniqueTableData("Coma", 10, 10, 30, 0, 2))
    table14a.add(TechniqueTableData("Total Paralysis", 8, 8, 20, 0, 2))
    table14a.add(TechniqueTableData("Life Drain", 8, 8, 15, 0, 2))
    table14a.add(TechniqueTableData("Control", 10, 10, 40, 0, 3))
    table14a.add(TechniqueTableData("Death", 12, 12, 50, 0, 3))

    table15.add(TechniqueTableData("-10", 1, 2, 5, 1, 1))
    table15.add(TechniqueTableData("-25", 2, 4, 5, 1, 1))
    table15.add(TechniqueTableData("-50", 3, 5, 10, 2, 1))
    table15.add(TechniqueTableData("-75", 4, 6, 10, 2, 2))
    table15.add(TechniqueTableData("-100", 6, 9, 15, 3, 2))
    table15.add(TechniqueTableData("-120", 8, 11, 20, 3, 3))

    table16.add(TechniqueTableData("1", 1, 2, 5, 1, 1))
    table16.add(TechniqueTableData("2", 2, 4, 5, 1, 1))
    table16.add(TechniqueTableData("3", 4, 6, 10, 2, 1))
    table16.add(TechniqueTableData("4", 6, 9, 15, 2, 1))
    table16.add(TechniqueTableData("5", 8, 11, 20, 3, 2))
    table16.add(TechniqueTableData("6", 10, 13, 25, 3, 2))
    table16.add(TechniqueTableData("7", 12, 15, 30, 4, 2))
    table16.add(TechniqueTableData("8", 14, 18, 40, 5, 3))

    table17.add(TechniqueTableData("-1 AT", 1, 2, 5, 1, 1))
    table17.add(TechniqueTableData("-2 AT", 2, 4, 5, 1, 1))
    table17.add(TechniqueTableData("-3 AT", 3, 5, 10, 2, 1))
    table17.add(TechniqueTableData("-4 AT", 4, 6, 10, 2, 1))
    table17.add(TechniqueTableData("-5 AT", 5, 8, 15, 3, 2))
    table17.add(TechniqueTableData("-6 AT", 6, 9, 20, 3, 2))
    table17.add(TechniqueTableData("-7 AT", 8, 11, 25, 4, 2))
    table17.add(TechniqueTableData("-8 AT", 10, 13, 30, 5, 3))

    table18.add(TechniqueTableData("+5", 1, 2, 5, 1, 1))
    table18.add(TechniqueTableData("+10", 2, 4, 10, 1, 1))
    table18.add(TechniqueTableData("+15", 4, 6, 15, 2, 1))
    table18.add(TechniqueTableData("+20", 6, 9, 20, 3, 1))
    table18.add(TechniqueTableData("+25", 8, 11, 25, 4, 2))
    table18.add(TechniqueTableData("+30", 12, 15, 30, 5, 2))
    table18.add(TechniqueTableData("+35", 13, 18, 35, 6, 2))
    table18.add(TechniqueTableData("+40", 18, 22, 40, 8, 3))

    table19.add(TechniqueTableData("+10", 1, 2, 5, 1, 1))
    table19.add(TechniqueTableData("+15", 2, 4, 5, 1, 1))
    table19.add(TechniqueTableData("+20", 3, 5, 10, 2, 1))
    table19.add(TechniqueTableData("+25", 4, 6, 10, 2, 1))
    table19.add(TechniqueTableData("+30", 5, 8, 15, 3, 2))
    table19.add(TechniqueTableData("+35", 6, 9, 20, 3, 2))
    table19.add(TechniqueTableData("+40", 7, 10, 25, 4, 3))

    table20.add(TechniqueTableData("5m", 1, 2, 5, 1, 1))
    table20.add(TechniqueTableData("10m", 2, 4, 10, 1, 1))
    table20.add(TechniqueTableData("20m", 3, 5, 10, 2, 1))
    table20.add(TechniqueTableData("50m", 4, 6, 15, 3, 1))
    table20.add(TechniqueTableData("100m", 5, 8, 20, 4, 1))
    table20.add(TechniqueTableData("250m", 6, 9, 25, 5, 2))
    table20.add(TechniqueTableData("500m", 8, 11, 30, 6, 2))
    table20.add(TechniqueTableData("1km", 10, 13, 35, 8, 2))
    table20.add(TechniqueTableData("5km", 14, 18, 40, 10, 3))
    table20.add(TechniqueTableData("10km", 18, 22, 45, 12, 3))
    table20.add(TechniqueTableData("100km", 22, 26, 50, 14, 3))

    table21.add(TechniqueTableData("1m", 1, 2, 5, 1, 1))
    table21.add(TechniqueTableData("5m", 2, 4, 10, 1, 1))
    table21.add(TechniqueTableData("10m", 3, 5, 15, 2, 1))
    table21.add(TechniqueTableData("25m", 4, 6, 20, 3, 1))
    table21.add(TechniqueTableData("50m", 6, 9, 25, 4, 2))
    table21.add(TechniqueTableData("100m", 8, 11, 30, 5, 2))
    table21.add(TechniqueTableData("500m", 10, 13, 40, 6, 2))
    table21.add(TechniqueTableData("1km", 12, 15, 50, 8, 3))
    table21.add(TechniqueTableData("5km", 16, 20, 60, 10, 3))

    table22.add(TechniqueTableData("10m", 2, 4, 5, 1, 1))
    table22.add(TechniqueTableData("20m", 3, 5, 10, 2, 1))
    table22.add(TechniqueTableData("50m", 4, 6, 10, 3, 1))
    table22.add(TechniqueTableData("100m", 5, 8, 15, 4, 1))
    table22.add(TechniqueTableData("250m", 6, 9, 20, 5, 1))
    table22.add(TechniqueTableData("500m", 8, 11, 25, 6, 2))
    table22.add(TechniqueTableData("1km", 10, 13, 30, 7, 2))
    table22.add(TechniqueTableData("5km", 14, 18, 35, 8, 2))
    table22.add(TechniqueTableData("10km", 18, 22, 40, 10, 3))
    table22.add(TechniqueTableData("100km", 22, 26, 50, 12, 3))

    table23.add(TechniqueTableData("+10", 2, 4, 5, 1, 1))
    table23.add(TechniqueTableData("+25", 3, 5, 5, 2, 1))
    table23.add(TechniqueTableData("+40", 4, 6, 10, 3, 1))
    table23.add(TechniqueTableData("+50", 5, 8, 15, 4, 1))
    table23.add(TechniqueTableData("+75", 8, 11, 20, 6, 1))
    table23.add(TechniqueTableData("+90", 12, 15, 25, 8, 1))
    table23.add(TechniqueTableData("+100", 14, 18, 30, 10, 1))
    table23.add(TechniqueTableData("+125", 18, 22, 35, 12, 2))
    table23.add(TechniqueTableData("+150", 22, 26, 40, 14, 2))
    table23.add(TechniqueTableData("+175", 26, 32, 45, 16, 3))
    table23.add(TechniqueTableData("+200", 30, 36, 50, 18, 3))

    table24.add(TechniqueTableData("+0", 2, 4, 5, 1, 1))
    table24.add(TechniqueTableData("+5", 4, 6, 5, 1, 1))
    table24.add(TechniqueTableData("+10", 6, 9, 10, 2, 1))
    table24.add(TechniqueTableData("+15", 8, 11, 15, 3, 2))
    table24.add(TechniqueTableData("+20", 10, 13, 20, 4, 3))

    table25.add(TechniqueTableData("4", 2, 4, 5, 1, 1))
    table25.add(TechniqueTableData("6", 3, 5, 10, 2, 1))
    table25.add(TechniqueTableData("8", 4, 6, 10, 3, 1))
    table25.add(TechniqueTableData("10", 5, 8, 15, 4, 1))
    table25.add(TechniqueTableData("12", 6, 9, 20, 5, 1))
    table25.add(TechniqueTableData("14", 8, 11, 25, 6, 2))
    table25.add(TechniqueTableData("16", 10, 13, 30, 7, 2))
    table25.add(TechniqueTableData("18", 14, 18, 35, 8, 3))
    table25.add(TechniqueTableData("20", 18, 22, 40, 10, 3))

    table26.add(TechniqueTableData("4", 1, 2, 5, 1, 1))
    table26.add(TechniqueTableData("6", 2, 4, 5, 2, 1))
    table26.add(TechniqueTableData("8", 3, 5, 10, 3, 1))
    table26.add(TechniqueTableData("10", 4, 6, 10, 4, 1))
    table26.add(TechniqueTableData("12", 5, 8, 15, 5, 1))
    table26.add(TechniqueTableData("14", 6, 9, 20, 6, 2))
    table26.add(TechniqueTableData("16", 8, 11, 25, 7, 2))
    table26.add(TechniqueTableData("18", 10, 13, 30, 8, 3))
    table26.add(TechniqueTableData("20", 12, 15, 35, 10, 3))

    table27.add(TechniqueTableData("100", 2, 4, 5, 1, 1))
    table27.add(TechniqueTableData("200", 3, 5, 5, 1, 1))
    table27.add(TechniqueTableData("300", 4, 6, 10, 2, 1))
    table27.add(TechniqueTableData("400", 5, 8, 15, 3, 1))
    table27.add(TechniqueTableData("500", 8, 11, 20, 4, 1))
    table27.add(TechniqueTableData("800", 12, 15, 25, 5, 2))
    table27.add(TechniqueTableData("1000", 14, 18, 30, 8, 2))
    table27.add(TechniqueTableData("1250", 18, 22, 35, 10, 2))
    table27.add(TechniqueTableData("1500", 22, 26, 40, 12, 3))
    table27.add(TechniqueTableData("2000", 26, 32, 45, 14, 3))

    table29.add(TechniqueTableData("1", 1, 2, 5, 1, 1))
    table29.add(TechniqueTableData("2", 2, 4, 5, 2, 1))
    table29.add(TechniqueTableData("4", 4, 6, 10, 3, 1))
    table29.add(TechniqueTableData("6", 6, 9, 10, 4, 1))
    table29.add(TechniqueTableData("10", 8, 11, 15, 6, 2))
    table29.add(TechniqueTableData("15", 10, 13, 20, 8, 2))
    table29.add(TechniqueTableData("20", 12, 15, 25, 10, 2))
    table29.add(TechniqueTableData("25", 14, 18, 30, 12, 3))

    table29a.add(TechniqueTableData("Moderate", 1, 1, 5, 0, 1))
    table29a.add(TechniqueTableData("Difficult", 2, 2, 10, 0, 1))
    table29a.add(TechniqueTableData("Very Difficult", 3, 3, 10, 0, 1))
    table29a.add(TechniqueTableData("Absurd", 4, 4, 15, 0, 1))
    table29a.add(TechniqueTableData("Almost Impossible", 5, 5, 15, 0, 1))
    table29a.add(TechniqueTableData("Impossible", 6, 6, 20, 0, 2))
    table29a.add(TechniqueTableData("Inhuman", 7, 7, 25, 0, 2))
    table29a.add(TechniqueTableData("Zen", 8, 8, 30, 0, 3))

    table34.add(TechniqueTableData("100", 2, 4, 5, 1, 1))
    table34.add(TechniqueTableData("200", 3, 5, 5, 1, 1))
    table34.add(TechniqueTableData("300", 4, 6, 10, 2, 1))
    table34.add(TechniqueTableData("400", 5, 8, 15, 3, 1))
    table34.add(TechniqueTableData("600", 8, 11, 20, 4, 1))
    table34.add(TechniqueTableData("800", 12, 15, 25, 5, 1))
    table34.add(TechniqueTableData("1000", 14, 18, 30, 8, 2))
    table34.add(TechniqueTableData("1200", 18, 22, 35, 10, 2))
    table34.add(TechniqueTableData("1400", 22, 26, 40, 12, 3))

    table35.add(TechniqueTableData("Single Element", 0, 0, -15, 0, 1))
    table35.add(TechniqueTableData("Two Elements", 0, 0, -10, 0, 1))

    table36.add(TechniqueTableData("No Damage", 0, 0, -20, 0, 1))
    table36.add(TechniqueTableData("Half Damage", 0, 0, -10, 0, 1))

    table37.add(TechniqueTableData("Simple Intensity", 0, 0, -15, 0, 1))
    table37.add(TechniqueTableData("Major Intensity", 0, 0, -10, 0, 1))
    table37.add(TechniqueTableData("Determined Condition", 0, 0, -5, 0, 1))

    LazyColumn{
        val kiIndex = when(index){
            7, 8, 17, 18, 19, 25, 26 -> 0
            1, 2, 3, 4, 9, 10, 12, 15 -> 1
            5, 6, 11, 13, 22 -> 2
            16, 34 -> 3
            14, 20, 21, 23, 24, 27, 28, 30, 31, 32, 33 -> 4
            else -> 5
        }

        val elementList = mutableListOf<Element>()

        if(listOf(1, 7, 8, 17, 18, 19, 20, 21, 23, 26, 31).contains(index)){
           elementList.add(Element.Fire)
        }
        if(listOf(2, 3, 4, 5, 9, 10, 16, 20, 27, 28, 29, 30).contains(index)){
            elementList.add(Element.Water)
        }
        if(listOf(1, 2, 5, 6, 9, 10, 12, 13, 15, 20, 22).contains(index)){
            elementList.add(Element.Air)
        }
        if(listOf(2, 3, 4, 7, 8, 16, 18, 19, 23, 24, 25, 26, 34).contains(index)){
            elementList.add(Element.Earth)
        }
        if(listOf(3, 4, 5, 6, 11, 14, 16, 21, 22, 24, 27, 28, 30, 31, 33).contains(index)){
            elementList.add(Element.Light)
        }
        if(listOf(1, 6, 10, 14, 17, 21, 22, 24, 28, 29, 30, 31, 33).contains(index)){
            elementList.add(Element.Dark)
        }
        if(listOf(36, 37, 38).contains(index)){
            elementList.add(Element.Free)
        }

        if(!listOf(0, 32, 35, 36, 37, 38).contains(index))
            item{ElementDisplayRow(elementList)}

        when(index){
            //Attack Ability
            1 -> {
                buildArray.value = listOf(2, 0, 2, null, 2, 3)
                item{TechniqueTableHeader("Attack Bonus")}

                items(table1){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining) }
                }
            }

            //Counterattack Ability
            2 -> {
                buildArray.value = listOf(2, 0, 2, null, 2, 3)
                item{TechniqueTableHeader("Attack Bonus")}

                items(table2){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Block Ability
            3 -> {
                buildArray.value = listOf(2, 0, 2, null, 2, 3)
                item { TechniqueTableHeader("Block Bonus") }

                items(table3) { tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    { effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(
                            effectInput,
                            charInstance.kiList.martialKnowledgeRemaining
                        )
                    }

                }
            }

            //Limited Block Ability
            4 -> {
                buildArray.value = listOf(2, 0, 2, null, 2, 3)
                item{TechniqueTableHeader("Block Bonus")}

                items(table4){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Dodge Ability
            5 -> {
                buildArray.value = listOf(null, 2, 0, 2, 2, 3)
                item{TechniqueTableHeader("Dodge Bonus")}

                items(table5){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Limited Dodge Ability
            6 -> {
                buildArray.value = listOf(null, 2, 0, 2, 2, 3)
                item{TechniqueTableHeader("Dodge Bonus")}

                items(table6){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Damage Multiplier
            7 -> {
                buildArray.value = listOf(0, 3, null, 2, 1, 1)
                item{TechniqueTableHeader("Multiplier")}

                items(table7){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Damage Augmentation
            8 -> {
                buildArray.value = listOf(0, 3, null, 1, 2, 1)
                item{TechniqueTableHeader("Damage Bonus")}

                items(table8){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Optional Advantage: Sacrifice")}
                items(table8a){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        optionalCheck1
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}

                }
            }

            //Additional Attack
            9 -> {
                buildArray.value = listOf(null, 0, 2, 1, 3, 3)
                item{TechniqueTableHeader("Attacks")}

                items(table9){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Optional Advantage: Continuous Attack")}
                item{
                    TechniqueTableRow(
                        TechniqueTableData("Continuous Attack", 10, 10, 30, 5, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        optionalCheck1
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Optional Advantage: Added Fatigue Bonus")}
                item{
                    TechniqueTableRow(
                        TechniqueTableData("Added Fatigue Bonus", 8, 8, 30, 2, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        optionalCheck2
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Limited Additional Attack
            10 -> {
                buildArray.value = listOf(null, 0, 2, 1, 3, 3)
                item{TechniqueTableHeader("Attacks")}

                items(table10){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Optional Advantage: Continuous Attack")}
                item{TechniqueTableRow(
                    TechniqueTableData("Continuous Attack", 10, 10, 30, 5, 1),
                    elementList,
                    index,
                    kiIndex,
                    isPrimary,
                    buildArray.value,
                    optionalCheck1
                )
                {effectInput: TechniqueEffect ->
                    customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}

                }
            }

            //Additional Defense
            11 -> {
                buildArray.value = listOf(null, 1, 0, 1, 3, 3)
                item{TechniqueTableHeader("Defenses")}

                items(table11){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Optional Advantage: Added Fatigue Bonus")}
                item{
                    TechniqueTableRow(
                        TechniqueTableData("Added Fatigue Bonus", 6, 6, 20, 2, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        optionalCheck1
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Additional Action
            12 -> {
                buildArray.value = listOf(null, 0, 1, 1, 3, 3)
                item{TechniqueTableHeader("Actions")}

                items(table12){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Optional Advantage: Added Fatigue Bonus")}
                item{
                    TechniqueTableRow(
                        TechniqueTableData("Added Fatigue Bonus", 6, 6, 20, 1, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        optionalCheck1
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Initiative Augmentation
            13 -> {
                buildArray.value = listOf(null, 1, 0, 2, 3, 3)
                item{TechniqueTableHeader("Initiative Bonus")}

                items(table13){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //States
            14 -> {
                buildArray.value = listOf(4, 4, null, 4, 0, 1)
                item{TechniqueTableHeader("PhR Check")}

                items(table14){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Optional Advantage: Added State")}
                items(table14a){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        optionalCheck1
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Combat Maneuvers and Aiming
            15 -> {
                buildArray.value = listOf(null, 0, 1, 2, 2, 2)
                item{TechniqueTableHeader("Precision")}

                items(table15){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Armor Increase
            16 -> {
                buildArray.value = listOf(2, null, 3, 0, 1, 2)
                item{TechniqueTableHeader("AT")}

                items(table16){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Armor Destruction
            17 -> {
                buildArray.value = listOf(0, 2, null, 2, 1, 2)
                item{TechniqueTableHeader("Reduction")}

                items(table17){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Breakage Augmentation
            18 -> {
                buildArray.value = listOf(0, 4, null, 2, 2, 1)
                item{TechniqueTableHeader("Breakage")}

                items(table18){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Fortitude Augmentation
            19 -> {
                buildArray.value = listOf(0, 4, null, 2, 2, 1)
                item{TechniqueTableHeader("Fortitude")}

                items(table19){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Long-Distance Attack
            20 -> {
                buildArray.value = listOf(null, 2, 3, 4, 0, 1)
                item{TechniqueTableHeader("Distance")}

                items(table20){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Area Attack
            21 -> {
                buildArray.value = listOf(null, 2, 3, 3, 0, 1)
                item{TechniqueTableHeader("Radius")}

                items(table21){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Optional Advantage: Target Choice")}
                item{
                    TechniqueTableRow(
                        TechniqueTableData("Target Choice", 2, 2, 10, 1, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        optionalCheck1
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Automatic Transportation
            22 -> {
                buildArray.value = listOf(2, 2, 0, 2, 3, null)
                item{TechniqueTableHeader("Distance")}

                items(table22){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Critical Enhancement
            23 -> {
                buildArray.value = listOf(1, 2, null, 2, 0, 1)
                item{TechniqueTableHeader("Critical")}

                items(table23){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Optional Advantage: Automatic Critical")}
                item{
                    TechniqueTableRow(
                        TechniqueTableData("Automatic Critical", 8, 8, 30, 4, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        optionalCheck1
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Physical Ki Weapons
            24 -> {
                buildArray.value = listOf(2, 3, null, 1, 0, 1)
                item{TechniqueTableHeader("Quality")}

                items(table24){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Optional Advantage: Projectiles")}
                item{
                    TechniqueTableRow(
                        TechniqueTableData("Projectile Weapon", 2, 2, 10, 1, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        optionalCheck1
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Trapping
            25 -> {
                buildArray.value = listOf(0, 1, null, 2, 2, 2)
                item{TechniqueTableHeader("Trap")}

                items(table25){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Projection
            26 -> {
                buildArray.value = listOf(0, 3, null, 2, 1, 1)
                item{TechniqueTableHeader("Projection")}

                items(table26){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Energy Shield
            27 -> {
                buildArray.value = listOf(2, 3, null, 2, 0, 1)
                item{TechniqueTableHeader("LP")}

                items(table27){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Intangibility
            28 -> {
                buildArray.value = listOf(3, 3, null, 3, 0, 1)
                item{TechniqueTableHeader("Effect")}

                item{
                    TechniqueTableRow(
                        TechniqueTableData("Intangibility", 3, 5, 10, 2, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Mirage
            29 -> {
                buildArray.value = listOf(null, 3, 2, 3, 1, 0)
                item{TechniqueTableHeader("Mirages")}

                items(table29){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Optional dvantage: Non-Detection")}
                items(table29a){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        optionalCheck1
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Attack Mirroring
            30 -> {
                buildArray.value = listOf(2, 3, 3, null, 0, 1)
                item{TechniqueTableHeader("Effect")}

                item{
                    TechniqueTableRow(
                        TechniqueTableData("Attack Mirroring", 12, 15, 30, 8, 2),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Optional Advantage: Target Choice")}
                item{
                    TechniqueTableRow(
                        TechniqueTableData("Target Choice", 2, 2, 10, 2, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        optionalCheck1
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Optional Advantage: Mirroring Esoteric Abilities")}
                item{
                    TechniqueTableRow(
                        TechniqueTableData("Mirror Esoteric Abilities", 4, 4, 20, 1, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        optionalCheck2
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Energy Damaging Attack
            31 -> {
                buildArray.value = listOf(3, 3, null, 2, 0, 1)
                item{TechniqueTableHeader("Attack")}

                item{
                    TechniqueTableRow(
                        TechniqueTableData("Energy", 1, 2, 5, 1, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Elemental Attack
            32 -> {
                buildArray.value = listOf(3, 3, null, 2, 0, 1)
                item{TechniqueTableHeader("Attack")}

                item{
                    TechniqueTableRow(
                        TechniqueTableData("2", 2, 4, 5, 1, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Select Element: ")}
                items(elementAttackList){element ->
                    ElementalRow(
                        element,
                        allEffectChecks,
                        elementChecks
                    )
                }
            }

            //Supernatural Attack
            33 -> {
                buildArray.value = listOf(3, 3, null, 2, 0, 1)
                item{TechniqueTableHeader("Attack")}

                item{
                    TechniqueTableRow(
                        TechniqueTableData("Energy", 5, 8, 10, 1, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Damage Resistance
            34 -> {
                buildArray.value = listOf(3, 3, null, 0, 3, 1)
                item{TechniqueTableHeader("LP")}

                items(table34){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //ElementalBinding
            35 -> {
                buildArray.value = listOf(null, null, null, null, null, null)

                items(table35){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }

                item{Text(text = "Select Element(s): ")}
                items(elementBindList){element ->
                    ElementalRow(element, allEffectChecks, elementChecks)
                }
            }

            //Reduce Damage
            36 -> {
                buildArray.value = listOf(null, null, null, null, null, null)

                items(table36){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Special Requirements
            37 -> {
                buildArray.value = listOf(null, null, null, null, null, null)

                items(table37){tableRow ->
                    TechniqueTableRow(
                        tableRow,
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            //Predetermination
            38 -> {
                buildArray.value = listOf(null, null, null, null, null, null)

                item{
                    TechniqueTableRow(
                        TechniqueTableData("Predetermination", 0, 0, -20, 0, 1),
                        elementList,
                        index,
                        kiIndex,
                        isPrimary,
                        buildArray.value,
                        allEffectChecks
                    )
                    {effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(effectInput, charInstance.kiList.martialKnowledgeRemaining)}
                }
            }

            else -> {}
        }
    }
}

@Composable
private fun ElementDisplayRow(
    elementList: List<Element>
){
    var elementString = ""

    elementList.forEach{
        elementString += it.name
        if(elementList.indexOf(it) < elementList.size)
            elementString += ", "
    }

    Row {
        Text(text = "Elements: $elementString")
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
    input: TechniqueTableData,
    elementList: List<Element>,
    techniqueIndex: Int,
    kiIndex: Int,
    isPrimary: Boolean,
    buildArray: List<Int?>,

    allChecksList: MutableList<Pair<TechniqueEffect, MutableState<Boolean>>>,
    getValidInput: (TechniqueEffect) -> String?
){
    val context = LocalContext.current

    val thisCheck = remember{ mutableStateOf(false) }

    val defaultArray = mutableListOf(0, 0, 0, 0, 0, 0)
    defaultArray[kiIndex] =
        if(isPrimary)
            input.primaryCost
        else
            input.secondaryCost

    val useString =
        if(techniqueIndex < 35)
            stringArrayResource(R.array.techniqueAbilities)[techniqueIndex]
        else
            stringArrayResource(R.array.techniqueDisadvantages)[techniqueIndex - 35]

    val thisEffect =
        TechniqueEffect(useString, input.effect, input.mkCost, input.maintCost, Pair(input.primaryCost, input.secondaryCost),
            defaultArray, buildArray, elementList, input.level)

    allChecksList += Pair(thisEffect, thisCheck)

    Row(verticalAlignment = Alignment.CenterVertically)
    {
        Checkbox(
            checked = thisCheck.value,
            onCheckedChange = {
                val textOut = getValidInput(thisEffect)
                    //customTechnique.validEffectAddition(thisEffect, charInstance.kiList.martialKnowledgeRemaining)
                if(it) {
                    if(textOut == null) {
                        allChecksList.forEach { boxVal -> boxVal.second.value = false }

                        thisCheck.value = true
                    }
                    else
                        Toast.makeText(context, textOut, Toast.LENGTH_SHORT).show()
                }
                else
                    thisCheck.value = false
            },
            modifier = Modifier.weight(0.1f)
        )
        Text(text = input.effect, textAlign = TextAlign.Center, modifier = Modifier.weight(0.4f))
        Text(text = input.primaryCost.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = input.secondaryCost.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = input.mkCost.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = input.maintCost.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = input.level.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
    }
}

@Composable
private fun ElementalRow(
    elementType: Element,
    allEffectChecks: MutableList<Pair<TechniqueEffect, MutableState<Boolean>>>,
    elementChecks: MutableList<Pair<Element, MutableState<Boolean>>>
){
    val checkStatus = remember{ mutableStateOf(false) }
    elementChecks += Pair(elementType, checkStatus)

    val context = LocalContext.current

    Row{
        Checkbox(
            checked = checkStatus.value,
            onCheckedChange = {
                val selection = getSelectedEffect(allEffectChecks, elementChecks, false)
                if(selection == null)
                    Toast.makeText(context, "Please select an effect first", Toast.LENGTH_SHORT).show()
                else if(selection.name == "Two Elements"){
                    checkStatus.value = it && selection.elements.size < 2
                }
                else{
                    if(it) {
                        elementChecks.forEach { item ->
                            item.second.value = false
                        }
                    }

                    checkStatus.value = it
                }
            }
        )

        Text(text = elementType.name)
    }
}
        
@Composable
private fun EditEffectRow(
    effect: TechniqueEffect,
    removeEffects: MutableList<TechniqueEffect>
){
    val deleteCheck = remember{mutableStateOf(false)}
    var elementString = ""
    effect.elements.forEach{
        elementString += it.name

        if(effect.elements.indexOf(it) != effect.elements.size - 1)
            elementString += "/"
    }

    Row {
        Checkbox(
            checked = deleteCheck.value,
            onCheckedChange = {
                deleteCheck.value = it

                if(it)
                    removeEffects += effect
                else
                    removeEffects -= effect
            }
        )
        Text(text = effect.name)
        Text(text = effect.effect)

    }

    Row {
        Text(text = effect.mkCost.toString())
        Text(text = elementString)
    }
}

@Composable
private fun EditBuildRow(
    effect: TechniqueEffect,
    index: Int,
    workArray: MutableList<Int>,
    statName: String,
    changeAccString: () -> Unit
){
    Row {
        if (effect.buildAdditions[index] != null) {
            Text(text = "$statName: ", modifier = Modifier.weight(0.4f))

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
                            changeAccString()
                            buildVal.value = catchIn
                        },
                        {
                            workArray[index] = 0
                            changeAccString()
                            buildVal.value = ""
                        }
                    )
                },
                modifier = Modifier.weight(0.2f)
            )

            Text(text = "+" + effect.buildAdditions[index], modifier = Modifier.weight(0.4f))
        }
    }
}

@Composable
private fun MaintenanceInput(
    index: Int,
    customTechnique: Technique,
    statName: String
){
    val maintInput = remember{mutableStateOf(customTechnique.maintArray[index].toString())}

    Row{
        Text(text = statName, modifier = Modifier.weight(0.5f))

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

private fun getSelectedEffect(
    effectList: MutableList<Pair<TechniqueEffect, MutableState<Boolean>>>,
    elementList: MutableList<Pair<Element, MutableState<Boolean>>>,
    elementRequired: Boolean
): TechniqueEffect?{
    effectList.forEach{
        if(it.second.value){
            val dummyEffect = it.first
            dummyEffect.elements = getSelectedElement(it.first, elementList)

            if(!elementRequired || dummyEffect.elements.isNotEmpty())
                return dummyEffect
        }
    }

    return null
}


private fun getSelectedElement(
    effect: TechniqueEffect,
    elementChecks: MutableList<Pair<Element, MutableState<Boolean>>>
): List<Element>{
    if(effect.elements.isNotEmpty())
        return effect.elements

    val output = mutableListOf<Element>()

    elementChecks.forEach{
        if(it.second.value && !output.contains(it.first))
            output.add(it.first)
    }

    return output
}

private fun getAccTotal(
    index: Int,
    allKiBuilds: MutableList<Pair<String, MutableList<Int>>>
): String{
    val total = mutableStateOf(0)

    allKiBuilds.forEach{
        total.value += it.second[index]
    }

    return total.value.toString()
}

private fun findBuild(
    token: String,
    allKiBuilds: MutableList<Pair<String, MutableList<Int>>>
): Pair<String, MutableList<Int>>?{
    allKiBuilds.forEach{
        if(token == it.first)
            return it
    }

    return null
}

private data class TechniqueTableData(
    val effect: String,
    val primaryCost: Int,
    val secondaryCost: Int,
    val mkCost: Int,
    val maintCost: Int,
    val level: Int
)
