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

/**
 * Dialog that gives the user a sequence of pages to develop their own custom dominion technique
 * Applies the created technique to the character after creation is complete
 * Takes the appropriate checks to confirm a valid technique as it is being built
 */

@Composable
fun CustomTechnique(
    techContents: @Composable (Technique) -> Unit,
    deactivate: () -> Unit
) {
    //get current context
    val context = LocalContext.current

    //create list of stat names to cycle through
    val statName = listOf("STR", "DEX", "AGI", "CON", "POW", "WP")

    //instantiate the dialog's current page
    val pageNum = remember{mutableStateOf(1)}

    //custom technique to be created
    val customTechnique = Technique("", "", 1, mutableListOf(0, 0, 0, 0, 0, 0), mutableListOf())

    //tracker for technique effect dropdown index
    val techniqueIndex = remember{mutableStateOf(0)}

    //selected value for the technique's level
    val customTechLevelSelection = remember{mutableStateOf(1)}

    //boolean for technique effect being the primary effect
    val isPrimary = remember{mutableStateOf(false)}

    //instantiate master lists for technique effect checkboxes and element checkboxes
    val allEffectChecks = remember{mutableMapOf<TechniqueEffect, MutableState<Boolean>>()}
    val optionalCheck1 = remember{mutableMapOf<TechniqueEffect, MutableState<Boolean>>()}
    val optionalCheck2 = remember{mutableMapOf<TechniqueEffect, MutableState<Boolean>>()}
    val allElementChecks = remember{mutableListOf<Pair<Element, MutableState<Boolean>>>()}

    //list of effects to be removed from the custom technique
    val removeEffects = remember{mutableListOf<TechniqueEffect>()}

    //list of ki builds for each taken technique effect
    val allKiBuilds = remember{mutableListOf<Pair<String, MutableList<Int>>>()}

    //technique accumulation totals for each stat
    val strAccTotal = remember{mutableStateOf("")}
    val dexAccTotal = remember{mutableStateOf("")}
    val agiAccTotal = remember{mutableStateOf("")}
    val conAccTotal = remember{mutableStateOf("")}
    val powAccTotal = remember{mutableStateOf("")}
    val wpAccTotal = remember{mutableStateOf("")}

    //maintenance state of the custom technique
    val customTechMaintenanceSelection = remember{mutableStateOf(false)}

    Dialog(
        onDismissRequest = {},
        content = {
            Box(
                Modifier
                    .background(Color.White)
                    .size(600.dp, 600.dp)) {

                //title header
                Row(
                    Modifier
                        .align(Alignment.TopCenter)
                        .height(100.dp)){Text(text = "Create Custom Technique")}

                //content box
                Row(
                    Modifier
                        .align(Alignment.Center)
                        .height(400.dp)) {
                    when (pageNum.value) {
                        //page for determining technique level
                        1 -> {
                            Column{
                                //prompt for technique's level
                                Row{Text(text = "Select the Technique's Level:")}
                                Column{
                                    //selection for level 1 technique
                                    Row {
                                        RadioButton(
                                            selected = customTechLevelSelection.value == 1,
                                            onClick = { customTechLevelSelection.value = 1 })
                                        Text(text = "1")
                                    }

                                    //selection for level 2 technique
                                    Row{
                                        RadioButton(
                                            selected = customTechLevelSelection.value == 2,
                                            onClick = {customTechLevelSelection.value = 2})
                                        Text(text = "2")
                                    }

                                    //selection for level 3 technique
                                    Row{
                                        RadioButton(
                                            selected = customTechLevelSelection.value == 3,
                                            onClick = {customTechLevelSelection.value = 3})
                                        Text(text = "3")
                                    }
                                }

                                //set minimum and maximum MK values for each level
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

                                //display minimums and maximums
                                Text(text = "Martial Knowledge Range: $minMK - $maxMK")
                            }
                        }

                        //page for determining primary effect
                        2 -> {
                            //define added technique as primary
                            isPrimary.value = true

                            //clear custom technique's effects
                            customTechnique.givenAbilities.clear()
                            allKiBuilds.clear()

                            //set dropdown's default index
                            techniqueIndex.value = 0

                            Column{
                                //prompt for ability selection
                                Text(text = "Select Primary Ability: ")

                                //create dropdown and displayed table
                                TechniqueAbilityDropdown(
                                    isPrimary.value,
                                    techniqueIndex,
                                    allEffectChecks,
                                    optionalCheck1,
                                    optionalCheck2,
                                    allElementChecks,
                                    customTechnique
                                ) { input: Int ->
                                    techniqueIndex.value = input
                                    allEffectChecks.forEach { it.value.value = false }
                                    optionalCheck1.forEach { it.value.value = false }
                                    optionalCheck1.forEach { it.value.value = false }
                                    allElementChecks.forEach { it.second.value = false }
                                }
                            }
                        }

                        //page for determining secondary effects
                        3 -> {
                            //reset dropdown index
                            techniqueIndex.value = 0

                            Column{
                                //prompt for ability addition
                                Text(text = "Add Secondary Abilities: ")

                                //create dropdown and displayed table
                                TechniqueAbilityDropdown(
                                    isPrimary.value,
                                    techniqueIndex,
                                    allEffectChecks,
                                    optionalCheck1,
                                    optionalCheck2,
                                    allElementChecks,
                                    customTechnique
                                ) { input: Int ->
                                    techniqueIndex.value = input
                                    allEffectChecks.forEach { it.value.value = false }
                                    optionalCheck1.forEach { it.value.value = false }
                                    optionalCheck1.forEach { it.value.value = false }
                                    allElementChecks.forEach { it.second.value = false }
                                }
                            }
                        }

                        //page for editing taken secondary effects
                        4 -> {
                            //initialize all deletion checkboxes
                            val deletionCheckList = customTechnique.givenAbilities.map{mutableStateOf(false)}

                            LazyColumn{
                                //display each taken effect with their deletable checkboxes
                                customTechnique.givenAbilities.forEach{
                                    item{EditEffectRow(it, deletionCheckList[customTechnique.givenAbilities.indexOf(it)], removeEffects)}
                                }

                                //display total current MK cost
                                item{Text(text = "MK: " + customTechnique.mkCost().toString())}
                            }
                        }

                        //page for redistributing ki accumulations
                        5 -> {
                            //set initial accumulation total values
                            strAccTotal.value = getAccTotal(0, allKiBuilds)
                            dexAccTotal.value = getAccTotal(1, allKiBuilds)
                            agiAccTotal.value = getAccTotal(2, allKiBuilds)
                            conAccTotal.value = getAccTotal(3, allKiBuilds)
                            powAccTotal.value = getAccTotal(4, allKiBuilds)
                            wpAccTotal.value = getAccTotal(5, allKiBuilds)

                            //gather all totals into a single list
                            val allAccs = listOf(strAccTotal, dexAccTotal, agiAccTotal, conAccTotal, powAccTotal, wpAccTotal)

                            Column{
                                Row{Text(text = "Accumulation Totals:")}

                                //display each characteristic accumulation totals
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
                                    //for each taken effect
                                    customTechnique.givenAbilities.forEach {
                                        //display the effect's name
                                        item { Text(text = it.name) }

                                        //create an input for the stat's accumulation input
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
                                //prompt for making technique maintainable
                                Row { Text(text = "Make technique maintainable?") }

                                //option for making it maintainable
                                Row {
                                    RadioButton(
                                        selected = customTechMaintenanceSelection.value,
                                        onClick = { customTechMaintenanceSelection.value = true }
                                    )
                                    Text(text = "Yes")
                                }

                                //option for technique not being maintained
                                Row {
                                    RadioButton(
                                        selected = !customTechMaintenanceSelection.value,
                                        onClick = { customTechMaintenanceSelection.value = false }
                                    )
                                    Text(text = "No")
                                }

                                //if user makes technique maintainable
                                if (customTechMaintenanceSelection.value) {
                                    //display required maintenance distribution
                                    Row { Text(text = "Total Maintenance Cost: " + customTechnique.maintTotal().toString()) }

                                    //make an input for each available stat
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
                                //prompt and input for technique's name
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
                                //prompt and input for technique's description
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
                            Column {
                                Row { Text(text = "Description of " + customTechnique.name) }
                                techContents(customTechnique)
                            }
                        }

                        else -> {deactivate()}
                    }
                }

                //row for user's buttons
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
                                //remove selected effects from the technique
                                customTechnique.givenAbilities -= removeEffects.toSet()

                                //remove selected effects from the kiBuild list
                                removeEffects.forEach removeItem@{removable ->
                                    allKiBuilds.forEach{
                                        if(removable.name == it.first) {
                                            allKiBuilds.remove(it)
                                            return@removeItem
                                        }
                                    }
                                }

                                //clear deletion list
                                removeEffects.clear()

                                //realign effects for removed primary ability
                                customTechnique.fixPrimaryAbility()

                                //go back to adding secondaries
                                if (customTechnique.givenAbilities.isNotEmpty()) {
                                    val newPrimary = customTechnique.givenAbilities[0]

                                    for(index in 0..5){
                                        findBuild(newPrimary.name, allKiBuilds)!!.second[index] = newPrimary.kiBuild[index]
                                    }

                                    pageNum.value = 3
                                }
                                //if effects list is empty, go back to primary effect page
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
                                //get all selected effects
                                val addedTechnique = listOf(
                                    getSelectedEffect(allEffectChecks, allElementChecks, true),
                                    getSelectedEffect(optionalCheck1, allElementChecks, true),
                                    getSelectedEffect(optionalCheck2, allElementChecks, true)
                                )

                                //initialize list of valid input returns
                                val validAddition = mutableListOf<String?>()

                                //initialize cost forecast
                                var addedCost = 0

                                //initialize state of all selected items being legal
                                var allAdd = false

                                //for each selected effect
                                addedTechnique.forEach {
                                    if (it != null) {
                                        //change continuation to true for at least one effect present
                                        allAdd = true

                                        //get if effect is valid and add to return list
                                        val newInput = customTechnique.validEffectAddition(
                                            it,
                                            charInstance.kiList.martialKnowledgeRemaining - addedCost
                                        )
                                        validAddition.add(newInput)

                                        //increment cost forecast if input is valid
                                        if (newInput == null)
                                            addedCost += it.mkCost

                                        //stop addition process
                                        else {
                                            allAdd = false
                                            return@forEach
                                        }
                                    }
                                }

                                //if no problem in adding effects
                                if (allAdd){
                                    //attempt to add each one to the character
                                    addedTechnique.forEach {
                                        //found effect to add
                                        if(it != null) {
                                            //get next valid statement
                                            val validReturn = validAddition.removeFirst()

                                            //addition is valid
                                            if (validReturn == null) {
                                                //add disadvantage marker to Elemental Binding list
                                                if (techniqueIndex.value == 35)
                                                    it.elements += Element.Free

                                                //add effect to technique
                                                customTechnique.givenAbilities += it

                                                //add effect to ki build master list
                                                allKiBuilds += Pair(
                                                    it.name,
                                                    it.kiBuild.toMutableList()
                                                )

                                                //reset dropdown index
                                                techniqueIndex.value = 0
                                            }

                                            //display addition error
                                            else
                                                Toast.makeText(
                                                    context,
                                                    validReturn,
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                        }
                                    }
                                }
                            }
                        )
                        {Text(text = "Add")}
                    }

                    //display back button on any page except the first and edit effect pages
                    if(pageNum.value != 1 && pageNum.value != 4){
                        TextButton(onClick = {
                            //set back button function for each page
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
                                //if character can take technique of this level
                                if((customTechLevelSelection.value == 1 && charInstance.kiList.martialKnowledgeRemaining >= 20) ||
                                    (customTechLevelSelection.value == 2 && charInstance.kiList.takenFirstTechniques.size >= 2 && charInstance.kiList.martialKnowledgeRemaining >= 40) ||
                                    (customTechLevelSelection.value == 3 && charInstance.kiList.takenSecondTechniques.size >= 2 && charInstance.kiList.martialKnowledgeRemaining >= 60)) {

                                    //set technique's level
                                    customTechnique.level = customTechLevelSelection.value

                                    //proceed to next page
                                    pageNum.value = 2
                                }
                            }

                            2 -> {
                                //get any possible selected effects
                                val selectedEffects = listOf(
                                    getSelectedEffect(allEffectChecks, allElementChecks, true),
                                    getSelectedEffect(optionalCheck1, allElementChecks, true),
                                    getSelectedEffect(optionalCheck2, allElementChecks, true)
                                )

                                //get technique's maximum cost
                                val mkMax = when(customTechnique.level){
                                    1 -> 50
                                    2 -> 100
                                    3 -> 200
                                    else -> 0
                                }

                                //get current expenditure of
                                val currSpent = mutableStateOf(0)

                                //make certain custom technique has no effects before addition
                                customTechnique.givenAbilities.clear()

                                //for each potential effect addition
                                selectedEffects.forEach{
                                    //if there is a valid addition
                                    if(it != null){
                                        //add it to the technique
                                        customTechnique.givenAbilities += it

                                        //track its build
                                        allKiBuilds += Pair(it.name, it.kiBuild)

                                        //determine cost of addition
                                        currSpent.value += it.mkCost
                                    }
                                }

                                //notify if maximum cost exceeded
                                if(currSpent.value > mkMax)
                                    Toast.makeText(context, "Cannot take all abilities: level cost exceeded", Toast.LENGTH_SHORT).show()
                                //proceed to next page if any ability was added
                                else if(customTechnique.givenAbilities.isNotEmpty()) {
                                    isPrimary.value = false
                                    pageNum.value = 3
                                }
                            }

                            3 -> {
                                //go to next page if minimum costs are met
                                if((customTechnique.level == 1 && customTechnique.mkCost() >= 20) ||
                                    (customTechnique.level == 2 && customTechnique.mkCost() >= 40) ||
                                    (customTechnique.level == 3 && customTechnique.mkCost() >= 60))
                                    pageNum.value = 5
                            }

                            //return to secondary additions page
                            4 -> {pageNum.value = 3}

                            5 -> {
                                //initialize booleans for primary ability and valid inputs
                                var isFirst = true
                                var moveOn = true

                                //for each effect build
                                allKiBuilds.forEach{
                                    //initialize build total
                                    var total = 0

                                    //retrieve associated effect
                                    val effect = customTechnique.getAbility(it.first)

                                    //determine accumulation total for the effect
                                    var accTotal = if(isFirst) effect!!.costPair.first else effect!!.costPair.second

                                    //for each accumulation index
                                    for(index in 0..5){
                                        //add amount to the total
                                        total += it.second[index]

                                        //if any points added to this stat
                                        if(it.second[index] > 0)
                                            //add stat's additional build value to required accumulation
                                            accTotal += effect.buildAdditions[index]!!
                                    }

                                    //set effect's ki build if total matches needed accumulation
                                    if(total == accTotal) {
                                        for(index in 0 .. 5)
                                            effect.kiBuild[index] = it.second[index]
                                    }

                                    //notify of invalid ki build
                                    else
                                        moveOn = false

                                    //notify of no longer checking primary effect
                                    isFirst = false
                                }

                                //go to next page if all effects have valid builds
                                if(moveOn)
                                    pageNum.value = 6
                            }

                            6 -> {
                                //initialize valid input indicator
                                var toNext = true

                                //user makes technique maintainable
                                if(customTechMaintenanceSelection.value){
                                    //initialize maintenance input total
                                    var total = 0

                                    //add each index's value
                                    customTechnique.maintArray.forEach{
                                        total += it
                                    }

                                    //determine if input is valid
                                    toNext = total == customTechnique.maintTotal()
                                }

                                //set default input for no maintenance
                                else
                                    for(index in 0..5)
                                        customTechnique.maintArray[index] = 0

                                //go to next page if input is valid
                                if(toNext)
                                    pageNum.value = 7
                            }

                            //go to next page if technique is named
                            7 -> {if(customTechnique.name != "") pageNum.value = 8}

                            //add technique to character and close dialog
                            8 -> {
                                charInstance.kiList.addTechnique(customTechnique)
                                deactivate()
                            }
                            else -> {}
                        }
                    }) {
                        Text(
                            //set next button text depending on the active page
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

/**
 * Displays a dropdown and table for the technique's effects
 *
 * isPrimary: whether the added effect is the primary effect or not
 * techniqueIndex: current index of the effect selection
 * allEffectChecks: master list of main effects
 * optionalCheck1: first master list of optional effect
 * optionalCheck2: second master list of optional effect
 * elementChecks: master list of elemental checks
 * customTechnique: technique to add the effects to
 * changeIndex: action to do when the index is changed
 */
@Composable
private fun TechniqueAbilityDropdown(
    isPrimary: Boolean,
    techniqueIndex: MutableState<Int>,
    allEffectChecks: MutableMap<TechniqueEffect, MutableState<Boolean>>,
    optionalCheck1: MutableMap<TechniqueEffect, MutableState<Boolean>>,
    optionalCheck2: MutableMap<TechniqueEffect, MutableState<Boolean>>,
    elementChecks: MutableList<Pair<Element, MutableState<Boolean>>>,
    customTechnique: Technique,
    changeIndex: (Int) -> Unit,
){
    //initialize string list of effects
    var source = stringArrayResource(R.array.techniqueAbilities)

    //add disadvantages if adding secondary abilities
    if(!isPrimary)
        source += stringArrayResource(R.array.techniqueDisadvantages)

    //initialize open state of dropdown list
    val isOpen = remember{mutableStateOf(false)}

    //initialize size of dropdown box
    val size = remember{mutableStateOf(Size.Zero)}

    //initialize symbol in dropdown box
    val icon = if(isOpen.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    //initialize lists and labels for the table data
    val header = remember{mutableStateOf<String?>(null)}
    val useTable = remember{mutableStateOf<List<TechniqueTableData>?>(null)}
    val optHeader1 = remember{mutableStateOf<String?>(null)}
    val optTable1 = remember{mutableStateOf<List<TechniqueTableData>?>(null)}
    val optHeader2 = remember{mutableStateOf<String?>(null)}
    val optTable2 = remember{mutableStateOf<List<TechniqueTableData>?>(null)}
    val optElement = remember{mutableStateOf<List<Element>?>(null)}

    Column {
        //dropdown text field
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

        //effect options list
        DropdownMenu(
            expanded = isOpen.value,
            onDismissRequest = { isOpen.value = false },
            modifier = Modifier.width(with(LocalDensity.current) { size.value.width.toDp() })
        ) {
            source.forEach {
                DropdownMenuItem(onClick = {
                    //perform change action
                    changeIndex(source.indexOf(it))

                    //close the dropdown list
                    isOpen.value = false

                    //clear all displayed values
                    optHeader1.value = null
                    optTable1.value = null
                    optHeader2.value = null
                    optTable2.value = null
                    optElement.value = null
                }) {
                    Text(text = it)
                }
            }
        }

        //initialize table data lists
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

        //get element lists for Elemental Attack and Elemental Binding
        val elementAttackList = listOf(Element.Fire, Element.Water, Element.Air, Element.Earth)
        val elementBindList = elementAttackList + listOf(Element.Light, Element.Dark)

        //initialize build addition list
        val buildArray = mutableListOf<Int?>()

        //create all tables for data
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

        //define primary index for each effect
        val kiIndex = when(techniqueIndex.value){
            7, 8, 17, 18, 19, 25, 26 -> 0
            1, 2, 3, 4, 9, 10, 12, 15 -> 1
            5, 6, 11, 13, 22 -> 2
            16, 34 -> 3
            14, 20, 21, 23, 24, 27, 28, 30, 31, 32, 33 -> 4
            else -> 5
        }

        //initialize effects' associated elements
        val elementList = mutableListOf<Element>()

        //add elements for any effect that applies
        if(listOf(1, 7, 8, 17, 18, 19, 20, 21, 23, 26, 31).contains(techniqueIndex.value)){
            elementList.add(Element.Fire)
        }
        if(listOf(2, 3, 4, 5, 9, 10, 16, 20, 27, 28, 29, 30).contains(techniqueIndex.value)){
            elementList.add(Element.Water)
        }
        if(listOf(1, 2, 5, 6, 9, 10, 12, 13, 15, 20, 22).contains(techniqueIndex.value)){
            elementList.add(Element.Air)
        }
        if(listOf(2, 3, 4, 7, 8, 16, 18, 19, 23, 24, 25, 26, 34).contains(techniqueIndex.value)){
            elementList.add(Element.Earth)
        }
        if(listOf(3, 4, 5, 6, 11, 14, 16, 21, 22, 24, 27, 28, 30, 31, 33).contains(techniqueIndex.value)){
            elementList.add(Element.Light)
        }
        if(listOf(1, 6, 10, 14, 17, 21, 22, 24, 28, 29, 30, 31, 33).contains(techniqueIndex.value)){
            elementList.add(Element.Dark)
        }
        if(listOf(36, 37, 38).contains(techniqueIndex.value)){
            elementList.add(Element.Free)
        }

        //display elements for any technique with these exceptions
        if(!listOf(0, 32, 35, 36, 37, 38).contains(techniqueIndex.value))
            Text(text = getElementString(elementList))

        //apply build, title, and table for each technique index
        when(techniqueIndex.value){
            //Attack Ability
            1 -> {
                buildArray.clear()
                buildArray.addAll(listOf(2, 0, 2, null, 2, 3))
                header.value = "Attack Bonus"
                useTable.value = table1
            }

            //Counterattack Ability
            2 -> {
                buildArray.clear()
                buildArray.addAll(listOf(2, 0, 2, null, 2, 3))
                header.value = "Attack Bonus"
                useTable.value = table2
            }

            //Block Ability
            3 -> {
                buildArray.clear()
                buildArray.addAll(listOf(2, 0, 2, null, 2, 3))
                header.value = "Block Bonus"
                useTable.value = table3
            }

            //Limited Block Ability
            4 -> {
                buildArray.clear()
                buildArray.addAll(listOf(2, 0, 2, null, 2, 3))
                header.value = "Block Bonus"
                useTable.value = table4
            }

            //Dodge Ability
            5 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, 2, 0, 2, 2, 3))
                header.value = "Dodge Bonus"
                useTable.value = table5
            }

            //Limited Dodge Ability
            6 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, 2, 0, 2, 2, 3))
                header.value = "Dodge Bonus"
                useTable.value = table6
            }

            //Damage Multiplier
            7 -> {
                buildArray.clear()
                buildArray.addAll(listOf(0, 3, null, 2, 1, 1))
                header.value = "Multiplier"
                useTable.value = table7
            }

            //Damage Augmentation
            8 -> {
                buildArray.clear()
                buildArray.addAll(listOf(0, 3, null, 1, 2, 1))
                header.value = "Damage Bonus"
                useTable.value = table8
                optHeader1.value = "Optional Advantage: Sacrifice"
                optTable1.value = table8a
            }

            //Additional Attack
            9 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, 0, 2, 1, 3, 3))
                header.value = "Attacks"
                useTable.value = table9
                optHeader1.value = "Optional Advantage: Continuous Attack"
                optTable1.value = listOf(TechniqueTableData("Continuous Attack", 10, 10, 30, 5, 1))
                optHeader2.value = "Optional Advantage: Added Fatigue Bonus"
                optTable2.value = listOf(TechniqueTableData("Added Fatigue Bonus", 8, 8, 30, 2, 1))
            }

            //Limited Additional Attack
            10 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, 0, 2, 1, 3, 3))
                header.value = "Attacks"
                useTable.value = table10
                optHeader1.value = "Optional Advantage: Continuous Attack"
                optTable1.value = listOf(TechniqueTableData("Continuous Attack", 10, 10, 30, 5, 1))
            }

            //Additional Defense
            11 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, 1, 0, 1, 3, 3))
                header.value = "Defenses"
                useTable.value = table11
                optHeader1.value = "Optional Advantage: Added Fatigue Bonus"
                optTable1.value = listOf(TechniqueTableData("Added Fatigue Bonus", 6, 6, 20, 2, 1))
            }

            //Additional Action
            12 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, 0, 1, 1, 3, 3))
                header.value = "Actions"
                useTable.value = table12
                optHeader1.value = "Optional Advantage: Added Fatigue Bonus"
                optTable1.value = listOf(TechniqueTableData("Added Fatigue Bonus", 6, 6, 20, 1, 1))
            }

            //Initiative Augmentation
            13 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, 1, 0, 2, 3, 3))
                header.value = "Initiative Bonus"
                useTable.value = table13
            }

            //States
            14 -> {
                buildArray.clear()
                buildArray.addAll(listOf(4, 4, null, 4, 0, 1))
                header.value = "PhR Check"
                useTable.value = table14
                optHeader1.value = "Optional Advantage: Added State"
                optTable1.value = table14a
            }

            //Combat Maneuvers and Aiming
            15 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, 0, 1, 2, 2, 2))
                header.value = "Precision"
                useTable.value = table15
            }

            //Armor Increase
            16 -> {
                buildArray.clear()
                buildArray.addAll(listOf(2, null, 3, 0, 1, 2))
                header.value = "AT"
                useTable.value = table16
            }

            //Armor Destruction
            17 -> {
                buildArray.clear()
                buildArray.addAll(listOf(0, 2, null, 2, 1, 2))
                header.value = "Reduction"
                useTable.value = table17
            }

            //Breakage Augmentation
            18 -> {
                buildArray.clear()
                buildArray.addAll(listOf(0, 4, null, 2, 2, 1))
                header.value = "Breakage"
                useTable.value = table18
            }

            //Fortitude Augmentation
            19 -> {
                buildArray.clear()
                buildArray.addAll(listOf(0, 4, null, 2, 2, 1))
                header.value = "Fortitude"
                useTable.value = table19
            }

            //Long-Distance Attack
            20 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, 2, 3, 4, 0, 1))
                header.value = "Distance"
                useTable.value = table20
            }

            //Area Attack
            21 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, 2, 3, 3, 0, 1))
                header.value = "Radius"
                useTable.value = table21
                optHeader1.value = "Optional Advantage: Target Choice"
                optTable1.value = listOf(TechniqueTableData("Target Choice", 2, 2, 10, 1, 1))
            }

            //Automatic Transportation
            22 -> {
                buildArray.clear()
                buildArray.addAll(listOf(2, 2, 0, 2, 3, null))
                header.value = "Distance"
                useTable.value = table22
            }

            //Critical Enhancement
            23 -> {
                buildArray.clear()
                buildArray.addAll(listOf(1, 2, null, 2, 0, 1))
                header.value = "Critical"
                useTable.value = table23
                optHeader1.value = "Optional Advantage: Automatic Critical"
                optTable1.value = listOf(TechniqueTableData("Automatic Critical", 8, 8, 30, 4, 1))
            }

            //Physical Ki Weapons
            24 -> {
                buildArray.clear()
                buildArray.addAll(listOf(2, 3, null, 1, 0, 1))
                header.value = "Quality"
                useTable.value = table24
                optHeader1.value = "Optional Advantage: Projectiles"
                optTable1.value = listOf(TechniqueTableData("Projectile Weapon", 2, 2, 10, 1, 1))
            }

            //Trapping
            25 -> {
                buildArray.clear()
                buildArray.addAll(listOf(0, 1, null, 2, 2, 2))
                header.value = "Trap"
                useTable.value = table25
            }

            //Projection
            26 -> {
                buildArray.clear()
                buildArray.addAll(listOf(0, 3, null, 2, 1, 1))
                header.value = "Projection"
                useTable.value = table26
            }

            //Energy Shield
            27 -> {
                buildArray.clear()
                buildArray.addAll(listOf(2, 3, null, 2, 0, 1))
                header.value = "LP"
                useTable.value = table27
            }

            //Intangibility
            28 -> {
                buildArray.clear()
                buildArray.addAll(listOf(3, 3, null, 3, 0, 1))
                header.value = "Effect"
                useTable.value = listOf(TechniqueTableData("Intangibility", 3, 5, 10, 2, 1))
            }

            //Mirage
            29 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, 3, 2, 3, 1, 0))
                header.value = "Mirages"
                useTable.value = table29
                optHeader1.value = "Optional advantage: Non-Detection"
                optTable1.value = table29a
            }

            //Attack Mirroring
            30 -> {
                buildArray.clear()
                buildArray.addAll(listOf(2, 3, 3, null, 0, 1))
                header.value = "Effect"
                useTable.value = listOf(TechniqueTableData("Attack Mirroring", 12, 15, 30, 8, 2))
                optHeader1.value = "Optional Advantage: Target Choice"
                optTable1.value = listOf(TechniqueTableData("Target Choice", 2, 2, 10, 2, 1))
                optHeader2.value = "Optional Advantage: Mirroring Esoteric Abilities"
                optTable2.value = listOf(TechniqueTableData("Mirror Esoteric Abilities", 4, 4, 20, 1, 1))
            }

            //Energy Damaging Attack
            31 -> {
                buildArray.clear()
                buildArray.addAll(listOf(3, 3, null, 2, 0, 1))
                header.value = "Attack"
                useTable.value = listOf(TechniqueTableData("Energy", 1, 2, 5, 1, 1))
            }

            //Elemental Attack
            32 -> {
                buildArray.clear()
                buildArray.addAll(listOf(3, 3, null, 2, 0, 1))
                header.value = "Attack"
                useTable.value = listOf(TechniqueTableData("", 2, 4, 5, 1, 1))
                optHeader1.value = "Select Element: "
                optElement.value = elementAttackList
            }

            //Supernatural Attack
            33 -> {
                buildArray.clear()
                buildArray.addAll(listOf(3, 3, null, 2, 0, 1))
                header.value = "Attack"
                useTable.value = listOf(TechniqueTableData("Energy", 5, 8, 10, 1, 1))
            }

            //Damage Resistance
            34 -> {
                buildArray.clear()
                buildArray.addAll(listOf(3, 3, null, 0, 3, 1))
                header.value = "LP"
                useTable.value = table34
            }

            //ElementalBinding
            35 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, null, null, null, null, null))
                useTable.value = table35
                optHeader1.value = "Select Element(s): "
                optElement.value = elementBindList
            }

            //Reduce Damage
            36 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, null, null, null, null, null))
                useTable.value = table36
            }

            //Special Requirements
            37 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, null, null, null, null, null))
                useTable.value = table37
            }

            //Predetermination
            38 -> {
                buildArray.clear()
                buildArray.addAll(listOf(null, null, null, null, null, null))
                useTable.value = listOf(TechniqueTableData("Predetermination", 0, 0, -20, 0, 1))
            }

            else -> {
                header.value = null
                useTable.value = null
                optHeader1.value = null
                optTable1.value = null
                optHeader2.value = null
                optTable2.value = null
                optElement.value = null
            }
        }

        //produce booleans for main check item
        val mainCheckList =
            if(useTable.value != null)
                useTable.value!!.map{mutableStateOf(false)}
            else null

        //produce booleans for first optional effect
        val opt1CheckList =
            if(optTable1.value != null)
                optTable1.value!!.map{mutableStateOf(false)}
            else null

        //produce booleans for second optional effect
        val opt2CheckList =
            if(optTable2.value != null)
                optTable2.value!!.map{mutableStateOf(false)}
            else null

        //produce booleans for elemental effects
        val elementCheckList =
            if(optElement.value != null)
                optElement.value!!.map{mutableStateOf(false)}
            else null

        LazyColumn {
            //display header if one given
            if (header.value != null)
                item{TechniqueTableHeader(header.value!!)}

            //display effect table if one given
            if(useTable.value != null) {
                items(useTable.value!!) {
                    TechniqueTableRow(
                        it,
                        elementList,
                        techniqueIndex.value,
                        kiIndex,
                        isPrimary,
                        buildArray,
                        mainCheckList!![useTable.value!!.indexOf(it)],
                        allEffectChecks,
                        elementChecks
                    ) { effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(
                            effectInput,
                            charInstance.kiList.martialKnowledgeRemaining
                        )
                    }
                }
            }

            //display optional effect table if one given
            if (optTable1.value != null) {
                item { Text(text = optHeader1.value!!) }
                items(optTable1.value!!) {
                    TechniqueTableRow(
                        it,
                        elementList,
                        techniqueIndex.value,
                        kiIndex,
                        isPrimary,
                        buildArray,
                        opt1CheckList!![optTable1.value!!.indexOf(it)],
                        optionalCheck1,
                        elementChecks
                    ) { effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(
                            effectInput,
                            charInstance.kiList.martialKnowledgeRemaining
                        )
                    }
                }
            }

            //display second optional effect table if one given
            if (optTable2.value != null) {
                item { Text(text = optHeader2.value!!) }
                items(optTable2.value!!) {
                    TechniqueTableRow(
                        it,
                        elementList,
                        techniqueIndex.value,
                        kiIndex,
                        isPrimary,
                        buildArray,
                        opt2CheckList!![optTable2.value!!.indexOf(it)],
                        optionalCheck2,
                        elementChecks
                    ) { effectInput: TechniqueEffect ->
                        customTechnique.validEffectAddition(
                            effectInput,
                            charInstance.kiList.martialKnowledgeRemaining
                        )
                    }
                }
            }

            //display element table if one given
            if (optElement.value != null) {
                item { Text(text = optHeader1.value!!) }
                items(optElement.value!!) {
                    ElementalRow(it, elementCheckList!![optElement.value!!.indexOf(it)], allEffectChecks, elementChecks)
                }
            }
        }
    }
}

/**
 * Header for the table of technique effects
 *
 * description: unique descriptor for the associated technique effect
 */
@Composable
private fun TechniqueTableHeader(
    description: String
){
    Row(verticalAlignment = Alignment.CenterVertically)
    {
        //space for effect degree
        Spacer(Modifier.weight(0.1f))

        //display individual value description
        Text(text = description, textAlign = TextAlign.Center, modifier = Modifier.weight(0.4f))

        //labels for primary and secondary costs, MK cost, maintenance cost, and effect level
        Text(text = "P", textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = "S", textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = "MK", textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = "Mt", textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = "Lvl", textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
    }
}

/**
 * Displays a single row for the effect table
 *
 * input: table data to be displayed
 * elementList: effect's associated elements
 * techniqueIndex: index to denote the effect chosen
 * kiIndex: primary characteristic of the effect's build
 * isPrimary: whether to use the primary or secondary cost
 * buildArray: additional costs for each characteristic build
 * inputBox: checkbox boolean to utilize in the row
 * allChecksList: master list of checkboxes for this table
 * getValidInput: function that tests validity of effect's addition
 */
@Composable
private fun TechniqueTableRow(
    input: TechniqueTableData,
    elementList: MutableList<Element>,
    techniqueIndex: Int,
    kiIndex: Int,
    isPrimary: Boolean,
    buildArray: MutableList<Int?>,

    inputBox: MutableState<Boolean>,
    allChecksList: MutableMap<TechniqueEffect, MutableState<Boolean>>,
    elementChecks: MutableList<Pair<Element, MutableState<Boolean>>>,
    getValidInput: (TechniqueEffect) -> String?
){
    //get context for toast displays
    val context = LocalContext.current

    //prepare default ki build
    val defaultArray = mutableListOf(0, 0, 0, 0, 0, 0)

    //apply initial cost to build array
    defaultArray[kiIndex] =
        if(isPrimary)
            input.primaryCost
        else
            input.secondaryCost

    //get name of the effect
    val useString =
        if(techniqueIndex < 35)
            stringArrayResource(R.array.techniqueAbilities)[techniqueIndex]
        else
            stringArrayResource(R.array.techniqueDisadvantages)[techniqueIndex - 35]

    //construct row's associated effect
    val thisEffect =
        TechniqueEffect(useString, input.effect, input.mkCost, input.maintCost, Pair(input.primaryCost, input.secondaryCost),
            defaultArray, buildArray, elementList, input.level)

    //add effect and checkbox to master list
    allChecksList += Pair(thisEffect, inputBox)

    Row(verticalAlignment = Alignment.CenterVertically)
    {
        //display checkbox
        Checkbox(
            checked = inputBox.value,
            onCheckedChange = {
                //clear any selected elements
                elementChecks.forEach{elementPair ->
                    elementPair.second.value = false
                }

                //if user attempts to add effect
                if(it) {
                    //determine if effect is a valid addition
                    val textOut = getValidInput(thisEffect)

                    //indicate viable addition
                    if(textOut == null) {
                        allChecksList.forEach { boxVal -> boxVal.value.value = false }
                        inputBox.value = true
                    }
                    //display reason for invalid addition
                    else
                        Toast.makeText(context, textOut, Toast.LENGTH_SHORT).show()
                }
                //remove addition state
                else
                    inputBox.value = false
            },
            modifier = Modifier.weight(0.1f)
        )
        //display effect value, costs, and level
        Text(text = input.effect, textAlign = TextAlign.Center, modifier = Modifier.weight(0.4f))
        Text(text = input.primaryCost.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = input.secondaryCost.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = input.mkCost.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = input.maintCost.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
        Text(text = input.level.toString(), textAlign = TextAlign.Center, modifier = Modifier.weight(0.1f))
    }
}

/**
 * Displays a table row for adding element effects
 *
 * elementType: the associated element of the row
 * checkStatus: mutable boolean for the checkbox
 * allEffectChecks: master list of main effect checkboxes
 * elementChecks: master list of element checkboxes
 */
@Composable
private fun ElementalRow(
    elementType: Element,
    checkStatus: MutableState<Boolean>,
    allEffectChecks: MutableMap<TechniqueEffect, MutableState<Boolean>>,
    elementChecks: MutableList<Pair<Element, MutableState<Boolean>>>
){
    //get context of dialog
    val context = LocalContext.current

    //add row data to the element master list
    elementChecks += Pair(elementType, checkStatus)

    Row{
        Checkbox(
            checked = checkStatus.value,
            onCheckedChange = {
                //get selected effect that may not have an element
                val selection = getSelectedEffect(allEffectChecks, elementChecks, false)

                //clear dummy effect of existing elements
                selection?.elements?.clear()

                //notify of selection needed if none given
                if(selection == null)
                    Toast.makeText(context, "Please select an effect first", Toast.LENGTH_SHORT).show()

                //if two elements selected for Elemental Binding
                else if(selection.effect == "Two Elements")
                    //change checkbox if input is valid
                    checkStatus.value = it && getSelectedElement(selection, elementChecks).size < 2


                //any other selection
                else{
                    //if trying to add
                    if(it) {
                        //clear other elements first
                        elementChecks.forEach { item ->
                            item.second.value = false
                        }
                    }

                    //apply user's desired state
                    checkStatus.value = it
                }
            }
        )

        //Display element name
        Text(text = elementType.name)
    }
}

/**
 * Create a row for when the user is editing their technique's effects
 *
 * effect: the currently applied effect to display
 * deletionCheck: checkbox boolean associated with the item
 * removeEffects: master list of chosen effects to remove
 */
@Composable
private fun EditEffectRow(
    effect: TechniqueEffect,
    deletionCheck: MutableState<Boolean>,
    removeEffects: MutableList<TechniqueEffect>
){
    Row {
        //checkbox to indicate effect deletion
        Checkbox(
            checked = deletionCheck.value,
            onCheckedChange = {
                //change deletion status accordingly
                deletionCheck.value = it

                //add or remove from deletion list accordingly
                if(it)
                    removeEffects += effect
                else
                    removeEffects -= effect
            }
        )
        //display effect
        Text(text = effect.name)
        Text(text = effect.effect)

    }

    //display effect's cost and elements
    Row {
        Text(text = effect.mkCost.toString())
        Text(text = getElementString(effect.elements))
    }
}

/**
 * Row that displays an effect's ki build needed for the indicated characteristic and allows the
 * user to alter that value
 *
 * effect: the associated effect for the row
 * index: characteristic's corresponding index number
 * workArray: initial build to start with and alter
 * statName: name of the characteristic for the row
 * changeAccString: hoisting function for a change in the build value
 */
@Composable
private fun EditBuildRow(
    effect: TechniqueEffect,
    index: Int,
    workArray: MutableList<Int>,
    statName: String,
    changeAccString: () -> Unit
){
    Row {
        //if the characteristic has a valid input
        if (effect.buildAdditions[index] != null) {
            //display characteristic name
            Text(text = "$statName: ", modifier = Modifier.weight(0.4f))

            //initialize build value
            val buildVal = remember { mutableStateOf(workArray[index].toString()) }
            TextField(
                value = buildVal.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { input ->
                    numberCatcher(input,
                        { catchIn: String ->
                            //change value to given input
                            workArray[index] = catchIn.toInt()
                            changeAccString()
                            buildVal.value = catchIn
                        },
                        {
                            //change value to 0 if nothing given
                            workArray[index] = 0
                            changeAccString()
                            buildVal.value = ""
                        }
                    )
                },
                modifier = Modifier.weight(0.2f)
            )

            //display characteristic's additional cost
            Text(text = "+" + effect.buildAdditions[index], modifier = Modifier.weight(0.4f))
        }
    }
}

/**
 * Displays a primary characteristic's current maintenance value and allows the user to alter it
 *
 * index: index of the primary characteristic used
 * customTechnique: the technique the user is creating with this dialog
 * statName: indicator of the primary characteristic used
 */
@Composable
private fun MaintenanceInput(
    index: Int,
    customTechnique: Technique,
    statName: String
){
    //initialize maintained value's display
    val maintInput = remember{mutableStateOf(customTechnique.maintArray[index].toString())}

    Row{
        //display characteristic
        Text(text = statName, modifier = Modifier.weight(0.5f))

        //maintenance input
        TextField(
            value = maintInput.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                numberCatcher(
                    it,
                    { input ->
                        //change maintenance and display with user's input
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

/**
 * Displays the elements associated with the displayed effect table
 *
 * elementList: effect's associated elements
 */
private fun getElementString(
    elementList: List<Element>
): String{
    //initialize display string
    var elementString = ""

    //add each element's name to the string
    elementList.forEach{
        elementString += it.name
        if(elementList.indexOf(it) < elementList.size)
            elementString += ", "
    }

    return elementString
}

/**
 * Retrieves the effect the user has selected
 *
 * effectList: master list of the effects to check
 * elementList: master list of elements to check for elemental effects
 * elementRequired: whether there must be elements associated with the output
 */
private fun getSelectedEffect(
    effectList: MutableMap<TechniqueEffect, MutableState<Boolean>>,
    elementList: MutableList<Pair<Element, MutableState<Boolean>>>,
    elementRequired: Boolean
): TechniqueEffect?{
    effectList.forEach{
        //if effect is selection
        if(it.value.value){
            //initialize dummy effect
            val dummyEffect = it.key

            //determine effect's elements form element inputs
            dummyEffect.elements = getSelectedElement(it.key, elementList)

            //return if either no element is required or effect gives elements
            if(!elementRequired || dummyEffect.elements.isNotEmpty())
                return dummyEffect
        }
    }

    //return for no valid selection
    return null
}

/**
 * Retrieves either the effect's elements or the user's elemental selection
 *
 * effect: effect to find or apply elements at
 * elementChecks: master list of element checkboxes user can select from
 */
private fun getSelectedElement(
    effect: TechniqueEffect,
    elementChecks: MutableList<Pair<Element, MutableState<Boolean>>>
): MutableList<Element>{
    //return the effect's given elements if any found
    if(effect.elements.isNotEmpty())
        return effect.elements

    //initialize output
    val output = mutableListOf<Element>()

    //retrieve any given element inputs
    elementChecks.forEach{
        if(it.second.value && !output.contains(it.first))
            output.add(it.first)
    }

    //return user's selection
    return output
}

/**
 * Retrieves the total ki accumulation for a characteristic from all implemented technique effects
 *
 * index: characteristic's corresponding index
 * allKiBuilds: master list of kibuild arrays to look through
 */
private fun getAccTotal(
    index: Int,
    allKiBuilds: MutableList<Pair<String, MutableList<Int>>>
): String{
    //initialize build total
    val total = mutableStateOf(0)

    //add each build's corresponding value
    allKiBuilds.forEach{
        total.value += it.second[index]
    }

    //return total
    return total.value.toString()
}

/**
 * Finds the ki build with the given effect name
 *
 * token: name of the effect to find
 * allKiBuilds: master list of the kibuild items
 */
private fun findBuild(
    token: String,
    allKiBuilds: MutableList<Pair<String, MutableList<Int>>>
): Pair<String, MutableList<Int>>?{
    //search through kibuild list
    allKiBuilds.forEach{
        //return it if found
        if(token == it.first)
            return it
    }

    return null
}

/**
 * Data to utilize in a technique table row
 *
 * effect: degree of the effect
 * primaryCost: ki cost as a primary effect
 * secondaryCost: ki cost as a secondary effect
 * mkCost: martial knowledge needed to add this effect
 * maintCost: cost to maintain this effect
 * level: level technique needs to be to acquire this effect
 */
private data class TechniqueTableData(
    val effect: String,
    val primaryCost: Int,
    val secondaryCost: Int,
    val mkCost: Int,
    val maintCost: Int,
    val level: Int
)
