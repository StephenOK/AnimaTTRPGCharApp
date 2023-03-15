package com.example.animabuilder.activities.fragments.dialogs

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.toSize
import com.example.animabuilder.R
import com.example.animabuilder.NumberInput
import com.example.animabuilder.TechniqueTableData
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.Technique
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.TechniqueEffect
import com.example.animabuilder.view_models.CustomTechniqueViewModel
import com.example.animabuilder.view_models.KiFragmentViewModel

/**
 * Dialog that gives the user a sequence of pages to develop their own custom dominion technique
 * Applies the created technique to the character after creation is complete
 * Takes the appropriate checks to confirm a valid technique as it is being built
 */

@Composable
fun CustomTechnique(
    kiFragVM: KiFragmentViewModel,
    customTechVM: CustomTechniqueViewModel,
    techContents: @Composable (Technique) -> Unit
) {
    //get current context
    val context = LocalContext.current

    DialogFrame(
        stringResource(R.string.customTechHeader),
        {
            when (customTechVM.customPageNum.collectAsState().value) {
                //page for determining technique level
                1 -> {
                    Column{
                        //prompt for technique's level
                        Row{Text(text = stringResource(R.string.firstPageTitle))}
                        Column{
                            //selection for level 1 technique
                            Row {
                                RadioButton(
                                    selected = customTechVM.getTechniqueLevel() == 1,
                                    onClick = {customTechVM.setTechniqueLevel(1)})
                                Text(text = "1")
                            }

                            //selection for level 2 technique
                            Row{
                                RadioButton(
                                    selected = customTechVM.getTechniqueLevel() == 2,
                                    onClick = {customTechVM.setTechniqueLevel(2)})
                                Text(text = "2")
                            }

                            //selection for level 3 technique
                            Row{
                                RadioButton(
                                    selected = customTechVM.getTechniqueLevel() == 3,
                                    onClick = {customTechVM.setTechniqueLevel(3)})
                                Text(text = "3")
                            }
                        }

                        //display minimums and maximums
                        Text(text = stringResource(R.string.mkRange) +
                                customTechVM.costMinimum.collectAsState().value.toString() + " - " +
                                customTechVM.costMaximum.collectAsState().value.toString())
                    }
                }

                //page for determining primary effect
                2 -> {
                    //define added technique as primary
                    customTechVM.setPickingPrimary(true)

                    //set dropdown's default index
                    customTechVM.setTechniqueIndex(0)

                    Column{
                        //prompt for ability selection
                        Text(text = stringResource(R.string.secondPageTitle))

                        //create dropdown and displayed table
                        TechniqueAbilityDropdown(
                            customTechVM
                        )
                    }
                }

                //page for determining secondary effects
                3 -> {
                    //reset dropdown index
                    customTechVM.setTechniqueIndex(0)

                    Column{
                        //prompt for ability addition
                        Text(text = stringResource(R.string.thirdPageTitle))

                        //create dropdown and displayed table
                        TechniqueAbilityDropdown(customTechVM)
                    }
                }

                //page for editing taken secondary effects
                4 -> {
                    //initialize all deletion checkboxes
                    customTechVM.setDeletionChecklist()

                    LazyColumn{
                        //display each taken effect with their deletable checkboxes
                        customTechVM.deletionChecklist.forEach{
                            item{EditEffectRow(it.key, it.value)}
                        }

                        //display total current MK cost
                        item{Text(text = "MK: " + customTechVM.getTechniqueCost().toString())}
                    }
                }

                //page for redistributing ki accumulations
                5 -> {
                    //set initial accumulation total values
                    customTechVM.allAccs.forEach{it.setTotalDisplay()}

                    Column{
                        Row{Text(text = stringResource(R.string.fifthPageTitle))}

                        //display each characteristic accumulation totals
                        Row{
                            for(index in 0..5)
                                Text(text = stringArrayResource(R.array.primaryCharArray)[index], modifier = Modifier.weight(0.13f))
                        }
                        Row {
                            customTechVM.allAccs.forEach{
                                Text(
                                    text = it.totalDisplay.collectAsState().value,
                                    modifier = Modifier.weight(0.13f)
                                )
                            }
                        }

                        LazyColumn{
                            //for each taken effect
                            customTechVM.getTechniqueEffects().forEach {
                                //display the effect's name
                                item { Text(text = it.name) }

                                //create an input for the stat's accumulation input
                                for (index in 0..5) {
                                    item{
                                        EditBuildRow(
                                            it,
                                            index,
                                            customTechVM.findBuild(it.name)!!,
                                            stringArrayResource(R.array.primaryCharArray)[index],
                                            customTechVM.allAccs[index]
                                        )
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
                                selected = customTechVM.maintenanceSelection.collectAsState().value,
                                onClick = {customTechVM.setMaintenanceSelection(true)}
                            )
                            Text(text = stringResource(R.string.yesLabel))
                        }

                        //option for technique not being maintained
                        Row {
                            RadioButton(
                                selected = !customTechVM.maintenanceSelection.collectAsState().value,
                                onClick = {customTechVM.setMaintenanceSelection(false)}
                            )
                            Text(text = stringResource(R.string.noLabel))
                        }

                        //if user makes technique maintainable
                        if (customTechVM.maintenanceSelection.collectAsState().value) {
                            //display required maintenance distribution
                            Row { Text(text = stringResource(R.string.totalMaintCost) + customTechVM.getMaintenanceTotal().toString()) }

                            //make an input for each available stat
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                for (index in 0..5)
                                    if(customTechVM.techHasAccIn(index))
                                        MaintenanceInput(customTechVM.allMaintInputs[index])
                            }
                        }
                    }
                }

                //page for naming and describing technique
                7 -> {
                    Column{
                        //prompt and input for technique's name
                        Row{
                            Text(text = stringResource(R.string.seventhPageTitle))
                            TextField(
                                value = customTechVM.techniqueName.collectAsState().value,
                                onValueChange = {
                                    customTechVM.setTechniqueName(it)
                                }
                            )
                        }
                        //prompt and input for technique's description
                        Row{
                            Text(text = stringResource(R.string.descriptionLabel))
                            TextField(
                                value = customTechVM.techniqueDesc.collectAsState().value,
                                onValueChange = {
                                    customTechVM.setTechniqueDesc(it)
                                }
                            )
                        }
                    }
                }

                //confirmation page
                8 -> {
                    Column {
                        Row { Text(text = stringResource(R.string.descriptionOfLabel) + customTechVM.techniqueName.collectAsState().value) }
                        techContents(customTechVM.getCustomTechnique())
                    }
                }

                else -> {customTechVM.closeDialog()}
            }
        },
        {
            //button to terminate process
            TextButton(onClick = {customTechVM.closeDialog()}) { Text(text = stringResource(R.string.cancelLabel)) }

            //if on edit page, button to delete selected items
            if (customTechVM.customPageNum.collectAsState().value == 4) {
                TextButton(
                    onClick = {customTechVM.deleteTechniques()}
                ) {
                    Text(text = stringResource(R.string.deleteLabel))
                }
            }

            //if on secondary page
            if (customTechVM.customPageNum.collectAsState().value == 3) {
                //option to edit taken effects
                TextButton(onClick = {customTechVM.setCustomPageNum(4)})
                { Text(text = stringResource(R.string.editLabel)) }

                //option to add selected effect
                TextButton(
                    onClick = {
                        val attempt = customTechVM.attemptTechAddition()

                        if(attempt != null)
                            Toast.makeText(
                                context,
                                attempt,
                                Toast.LENGTH_SHORT
                            ).show()
                    }
                )
                { Text(text = stringResource(R.string.addLabel)) }
            }

            //display back button on any page except the first and edit effect pages
            if (customTechVM.customPageNum.collectAsState().value != 1 &&
                    customTechVM.customPageNum.collectAsState().value != 4) {
                TextButton(onClick = {
                    //set back button function for each page
                    when (customTechVM.customPageNum.value) {
                        2 -> {
                            customTechVM.setCustomPageNum(1)
                        }
                        3 -> {
                            customTechVM.setCustomPageNum(2)
                        }
                        5 -> {
                            customTechVM.setTechniqueIndex(0)
                            customTechVM.setCustomPageNum(3)
                        }
                        6 -> {
                            customTechVM.setCustomPageNum(5)
                        }
                        7 -> {
                            customTechVM.setCustomPageNum(6)
                        }
                        8 -> {
                            customTechVM.setCustomPageNum(7)
                        }
                        else -> {
                            customTechVM.closeDialog()
                        }
                    }
                }) { Text(text = stringResource(R.string.backLabel)) }
            }

            //next page button
            TextButton(onClick = {
                when (customTechVM.customPageNum.value) {
                    1 -> {
                        //if character can take technique of this level
                        if (customTechVM.costMinMet(kiFragVM.getMartialRemaining()) &&
                            customTechVM.minTechsMet()
                        ) {
                            //proceed to next page
                            customTechVM.setCustomPageNum(2)
                        }
                    }

                    2 -> {
                        //notify if maximum cost exceeded
                        if (customTechVM.getSelectionPrice() > customTechVM.costMaximum.value)
                            Toast.makeText(
                                context,
                                "Cannot take all abilities: level cost exceeded",
                                Toast.LENGTH_SHORT
                            ).show()

                        //proceed to next page if any ability was added
                        else if (customTechVM.getSelectionPrice() != 0) {
                            customTechVM.getSelectedEffects().forEach{
                                if(it != null) {
                                    it.elements = customTechVM.getSelectedElement(it)
                                    customTechVM.getTechniqueEffects().add(it)
                                }
                            }

                            customTechVM.setPickingPrimary(false)
                            customTechVM.setCustomPageNum(3)
                        }
                    }

                    3 -> {
                        //go to next page if minimum costs are met
                        if (customTechVM.costMinMet(customTechVM.getTechniqueCost()))
                            customTechVM.setCustomPageNum(5)
                    }

                    //return to secondary additions page
                    4 -> {
                        customTechVM.setCustomPageNum(3)
                    }

                    5 -> {
                        if(customTechVM.getCheckBuilds())
                            customTechVM.setCustomPageNum(6)
                    }

                    6 -> {
                        if(customTechVM.getMaintenanceCheck())
                            customTechVM.setCustomPageNum(7)
                    }

                    //go to next page if technique is named
                    7 -> {
                        if (customTechVM.techniqueName.value != "") customTechVM.setCustomPageNum(8)
                    }

                    //add technique to character and close dialog
                    8 -> {
                        kiFragVM.addTechnique(customTechVM.getCustomTechnique())
                        customTechVM.closeDialog()
                    }
                    else -> {}
                }
            }) {
                Text(
                    //set next button text depending on the active page
                    text = when (customTechVM.customPageNum.collectAsState().value) {
                        4 -> stringResource(R.string.returnLabel)
                        8 -> stringResource(R.string.confirmLabel)
                        else -> stringResource(R.string.nextLabel)
                    }
                )
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
    customTechVM: CustomTechniqueViewModel
){
    //initialize size of dropdown box
    val size = remember{mutableStateOf(Size.Zero)}

    Column {
        //dropdown text field
        OutlinedTextField(
            value = customTechVM.dropdownTitle.collectAsState().value,
            onValueChange = {},
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    size.value = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(
                    customTechVM.dropdownIcon.collectAsState().value,
                    "contentDescription",
                    modifier = Modifier.clickable {customTechVM.setDropdownOpen(!customTechVM.dropdownOpen.value)})
            }
        )

        //effect options list
        DropdownMenu(
            expanded = customTechVM.dropdownOpen.collectAsState().value,
            onDismissRequest = {customTechVM.setDropdownOpen(false)},
            modifier = Modifier.width(with(LocalDensity.current) { size.value.width.toDp() })
        ) {
            customTechVM.listSource.collectAsState().value.forEach {
                DropdownMenuItem(onClick = {
                    customTechVM.setTechniqueIndex(customTechVM.listSource.value.indexOf(it))

                    //close the dropdown list
                    customTechVM.setDropdownOpen(false)
                }) {
                    Text(text = it)
                }
            }
        }

        //display elements for any technique with these exceptions
        if(!listOf(0, 32, 35, 36, 37, 38).contains(customTechVM.techniqueIndex.collectAsState().value))
            Text(text = getElementString(customTechVM.elementList.collectAsState().value))

        LazyColumn {
            //display header if one given
            if (customTechVM.header.value != null)
                item{TechniqueTableHeader(customTechVM.header.collectAsState().value!!)}

            //display effect table if one given
            if(customTechVM.useTable.value != null) {
                items(customTechVM.useTable.value!!) {
                    TechniqueTableRow(
                        customTechVM,
                        it,
                        customTechVM.mainChecklist,
                        customTechVM.mainChecklist[it]!!
                    )
                }
            }

            //display optional effect table if one given
            if (customTechVM.optTable1.value != null) {
                item { Text(text = customTechVM.optHeader1.collectAsState().value!!) }
                items(customTechVM.optTable1.value!!) {
                    TechniqueTableRow(
                        customTechVM,
                        it,
                        customTechVM.opt1Checklist,
                        customTechVM.opt1Checklist[it]!!
                    )
                }
            }

            //display second optional effect table if one given
            if (customTechVM.optTable2.value != null) {
                item { Text(text = customTechVM.optHeader2.collectAsState().value!!) }
                items(customTechVM.optTable2.value!!) {
                    TechniqueTableRow(
                        customTechVM,
                        it,
                        customTechVM.opt2Checklist,
                        customTechVM.opt2Checklist[it]!!
                    )
                }
            }

            //display element table if one given
            if (customTechVM.optElement.value != null) {
                item {Text(text = customTechVM.optHeader1.collectAsState().value!!) }
                items(customTechVM.optElement.value!!) {
                    ElementalRow(
                        customTechVM,
                        it,
                        customTechVM.elementChecklist[it]!!
                    )
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
    customTechVM: CustomTechniqueViewModel,
    input: TechniqueTableData,

    holdingList: MutableMap<TechniqueTableData, MutableState<Boolean>>,
    inputBox: MutableState<Boolean>
){
    //get context for toast displays
    val context = LocalContext.current

    Row(verticalAlignment = Alignment.CenterVertically)
    {
        //display checkbox
        Checkbox(
            checked = inputBox.value,
            onCheckedChange = {
                //clear any selected elements
                customTechVM.clearElementChecks()

                //if user attempts to add effect
                if(it) {
                    //determine if effect is a valid addition
                    val textOut = customTechVM.validCheckInput(input)

                    //indicate viable addition
                    if(textOut == null) {
                        customTechVM.clearInputList(holdingList)
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
    customTechVM: CustomTechniqueViewModel,
    elementType: Element,
    checkStatus: MutableState<Boolean>
){
    //get context of dialog
    val context = LocalContext.current

    Row{
        Checkbox(
            checked = checkStatus.value,
            onCheckedChange = {
                if(customTechVM.getSelectedEffects().isNotEmpty()){
                    val selection = customTechVM.getSelectedEffects()[0]!!
                    selection.elements.clear()

                    if(selection.effect == "Two Elements")
                    //change checkbox if input is valid
                        checkStatus.value = it && customTechVM.getSelectedElement(selection).size < 2


                    //any other selection
                    else{
                        //if trying to add
                        if(it) {
                            //clear other elements first
                            customTechVM.clearElementChecks()
                        }

                        //apply user's desired state
                        checkStatus.value = it
                    }
                }
                else
                    Toast.makeText(context, "Please select an effect first", Toast.LENGTH_SHORT).show()
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
    deletionCheck: MutableState<Boolean>
){
    Row {
        //checkbox to indicate effect deletion
        Checkbox(
            checked = deletionCheck.value,
            onCheckedChange = {
                //change deletion status accordingly
                deletionCheck.value = it
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
    accItem: CustomTechniqueViewModel.AccTotalString
){
    Row {
        //if the characteristic has a valid input
        if (effect.buildAdditions[index] != null) {
            //display characteristic name
            Text(text = "$statName: ", modifier = Modifier.weight(0.4f))

            //initialize build value
            val buildVal = remember { mutableStateOf(workArray[index].toString()) }

            NumberInput(
                buildVal.value,
                {},
                { catchIn: String ->
                    //change value to given input
                    workArray[index] = catchIn.toInt()
                    buildVal.value = catchIn
                },
                {
                    //change value to 0 if nothing given
                    workArray[index] = 0
                    buildVal.value = ""
                },
                {accItem.setTotalDisplay()},
                Color.Black,
                Modifier.weight(0.2f)
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
    maintInput: CustomTechniqueViewModel.MaintInput
){
    Row{
        //display characteristic
        Text(text = maintInput.name, modifier = Modifier.weight(0.5f))

        //maintenance input
        NumberInput(
            maintInput.displayValue.collectAsState().value,
            {},
            { input ->
                maintInput.setDisplayValue(input.toInt())
            },
            {
                maintInput.setDisplayValue("")
            },
            {},
            Color.Black,
            Modifier.weight(0.5f)
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