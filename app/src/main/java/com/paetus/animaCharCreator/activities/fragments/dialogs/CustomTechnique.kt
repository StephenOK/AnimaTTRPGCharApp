package com.paetus.animaCharCreator.activities.fragments.dialogs

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.paetus.animaCharCreator.InfoRow
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.NumberInput
import com.paetus.animaCharCreator.TechniqueTableData
import com.paetus.animaCharCreator.TextInput
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.Element
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.Technique
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.TechniqueEffect
import com.paetus.animaCharCreator.view_models.models.CustomTechniqueViewModel
import com.paetus.animaCharCreator.view_models.models.KiFragmentViewModel

/**
 * Dialog that gives the user a sequence of pages to develop their own custom dominion technique.
 * Applies the created technique to the character after creation is complete.
 * Takes the appropriate checks to confirm a valid technique as it is being built.
 *
 * @param kiFragVM ki fragment viewModel passed down from the ki page
 * @param customTechVM viewModel initialized for this dialog
 * @param techContents detail function to pass to this object
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        //prompt for technique's level
                        Text(
                            text = stringResource(R.string.firstPageTitle),
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        //display each level option
                        for(level in 1..3)
                            TechLevelSelection(
                                level,
                                customTechVM
                            )


                        //display technique cost minimums and maximums
                        InfoRow(
                            stringResource(R.string.mkRange),
                            customTechVM.costMinimum.collectAsState().value.toString() +
                                    " - " + customTechVM.costMaximum.collectAsState().value.toString())
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
                        TechniqueAbilityDropdown(customTechVM)
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

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        //display each taken effect with their deletable checkboxes
                        customTechVM.deletionChecklist.forEach{
                            item{EditEffectRow(it)}
                            item{Spacer(Modifier.height(5.dp))}
                        }

                        item{Spacer(Modifier.height(5.dp))}

                        //display total current MK cost
                        item{
                            Text(
                                text = stringResource(R.string.mkLabel, customTechVM.getTechniqueCost()),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                //page for redistributing ki accumulations
                5 -> {
                    //set initial accumulation total values
                    customTechVM.allAccs.forEach{it.setTotalDisplay()}
                    customTechVM.initializeBuildList()

                    Column{
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            Text(
                                text = stringResource(R.string.fifthPageTitle),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        //display accumulation totals' header
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            for(index in 0..5)
                                Text(
                                    text = stringArrayResource(R.array.primaryCharArray)[index],
                                    modifier = Modifier
                                        .weight(0.13f),
                                    textAlign = TextAlign.Center
                                )
                        }

                        //display each characteristic accumulation totals
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    bottom = 5.dp
                                )
                        ){
                            customTechVM.allAccs.forEach{
                                Text(
                                    text = it.totalDisplay.collectAsState().value,
                                    modifier = Modifier
                                        .weight(0.13f),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        LazyColumn{
                            //for each taken effect
                            customTechVM.editBuildList.forEach {
                                item{Divider()}

                                //display the effect's name
                                item {
                                    Text(
                                        text = it.input.name,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                top = 5.dp
                                            ),
                                        textAlign = TextAlign.Center,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                //create an input for the stat's accumulation input
                                it.buildItems.forEach{
                                    item{
                                        EditBuildRow(it)
                                    }
                                }
                            }
                        }
                    }
                }

                //page for determining maintenance values
                6 -> {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        //prompt for making technique maintainable
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = stringResource(R.string.sixthPageTitle),
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        //option for making it maintainable
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.3f),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                RadioButton(
                                    selected = customTechVM.maintenanceSelection.collectAsState().value,
                                    onClick = {customTechVM.setMaintenanceSelection(true)},
                                    modifier = Modifier
                                        .weight(0.1f)
                                )
                                Text(
                                    text = stringResource(R.string.yesLabel),
                                    modifier = Modifier
                                        .weight(0.2f)
                                        .clickable { customTechVM.setMaintenanceSelection(true) },
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        //option for technique not being maintained
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.3f),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                RadioButton(
                                    selected = !customTechVM.maintenanceSelection.collectAsState().value,
                                    onClick = {customTechVM.setMaintenanceSelection(false)},
                                    modifier = Modifier
                                        .weight(0.1f)
                                )
                                Text(
                                    text = stringResource(R.string.noLabel),
                                    modifier = Modifier
                                        .weight(0.2f)
                                        .clickable { customTechVM.setMaintenanceSelection(false) },
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        item{Spacer(Modifier.height(10.dp))}

                        //if user makes technique maintainable
                        item {
                            if (customTechVM.maintenanceSelection.collectAsState().value) {
                                //display required maintenance distribution
                                InfoRow(
                                    stringResource(R.string.totalMaintCost),
                                    customTechVM.getMaintenanceTotal().toString(),
                                )

                                Spacer(Modifier.height(5.dp))

                                //make an input for each available stat
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    for (index in 0..5)
                                        if (customTechVM.techHasAccIn(index))
                                            MaintenanceInput(customTechVM.allMaintInputs[index])
                                }
                            }
                        }
                    }
                }

                //page for naming and describing technique
                7 -> {
                    Column{
                        //prompt and input for technique's name
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            TextInput(
                                display = customTechVM.techniqueName.collectAsState().value,
                                onValueChange = {
                                    customTechVM.setTechniqueName(it)
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                label = stringResource(R.string.nameText)
                            )
                        }

                        //input for technique's description
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            TextField(
                                value = customTechVM.techniqueDesc.collectAsState().value,
                                onValueChange = {
                                    customTechVM.setTechniqueDesc(it)
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                label = {Text(text = stringResource(R.string.descriptionLabel))}
                            )
                        }
                    }
                }

                //confirmation page
                8 -> {
                    Column {
                        //display details of created technique
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            Text(
                                text = customTechVM.techniqueName.collectAsState().value,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }

                        techContents(customTechVM.getCustomTechnique())
                    }
                }

                //close dialog on invalid input
                else -> {customTechVM.closeDialog()}
            }

            //handle any back inputs in dialog
            BackHandler{
                when(customTechVM.customPageNum.value){
                    //close dialog on first page
                    1 -> kiFragVM.toggleCustomTechOpen()

                    //go to appropriate page from accumulation distribution
                    5 -> customTechVM.setCustomPageNum(3)

                    //go to previous page on any other page value
                    else -> customTechVM.setCustomPageNum(customTechVM.customPageNum.value - 1)
                }
            }
        },
        {
            //button to terminate process
            TextButton(onClick = {customTechVM.closeDialog()}) { Text(text = stringResource(R.string.cancelLabel)) }

            //if on edit page, button to delete selected items
            if (customTechVM.customPageNum.collectAsState().value == 4) {
                TextButton(
                    onClick = {customTechVM.deleteEffects()}
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
                                context.getString(attempt),
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
                    //go back to third page if on fifth page
                    if(customTechVM.customPageNum.value == 5)
                        customTechVM.setCustomPageNum(3)
                    //go back to previous page if on any other page
                    else
                        customTechVM.setCustomPageNum(customTechVM.customPageNum.value - 1)
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
                                context.getString(R.string.abilityAdditionFailure),
                                Toast.LENGTH_SHORT
                            ).show()

                        //proceed to next page if any ability was added
                        else if (customTechVM.getSelectionPrice() != 0) {
                            customTechVM.getSelectedEffects().forEach{
                                it.elements = customTechVM.getSelectedElement(it)
                                customTechVM.getTechniqueEffects().add(it)
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

                    //go to next page if builds are valid
                    5 -> {
                        if(customTechVM.getCheckBuilds())
                            customTechVM.setCustomPageNum(6)
                    }

                    //go to next page if maintenance inputs are valid
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
 * Radio button option when selecting the technique's level.
 *
 * @param level option value
 * @param customTechVM viewModel that manages this dialog
 */
@Composable
private fun TechLevelSelection(
    level: Int,
    customTechVM: CustomTechniqueViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(0.4f),
        verticalAlignment = Alignment.CenterVertically
    ){
        //display item selection state
        RadioButton(
            selected = customTechVM.getTechniqueLevel() == level,
            onClick = {customTechVM.setTechniqueLevel(level)},
            modifier = Modifier
                .weight(0.1f)
        )

        //display option name
        Text(
            text = level.toString(),
            modifier = Modifier
                .weight(0.1f)
                .clickable { customTechVM.setTechniqueLevel(level) },
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Displays a dropdown and table for the technique's effects.
 *
 * @param customTechVM viewModel for the dialog
 */
@Composable
private fun TechniqueAbilityDropdown(
    customTechVM: CustomTechniqueViewModel
){
    Column {
        //dropdown text field
        OutlinedTextField(
            value = customTechVM.dropdownTitle.collectAsState().value,
            onValueChange = {},
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    customTechVM.setSize(coordinates.size.toSize())
                },
            trailingIcon = {
                Icon(
                    customTechVM.dropdownIcon.collectAsState().value,
                    "contentDescription",
                    modifier = Modifier.clickable {customTechVM.toggleDropdownOpen() })
            },
            readOnly = true
        )

        //effect options list
        DropdownMenu(
            expanded = customTechVM.dropdownOpen.collectAsState().value,
            onDismissRequest = {customTechVM.toggleDropdownOpen() },
            modifier = Modifier
                .width(with(LocalDensity.current) {customTechVM.size.value.width.toDp()})
        ) {
            customTechVM.listSource.collectAsState().value.forEach {
                DropdownMenuItem(onClick = {
                    customTechVM.setTechniqueIndex(customTechVM.listSource.value.indexOf(it))

                    //close the dropdown list
                    customTechVM.toggleDropdownOpen()
                }) {
                    Text(text = it)
                }
            }
        }

        //display elements for any technique with these exceptions
        if(!listOf(0, 32, 35, 36, 37, 38).contains(customTechVM.techniqueIndex.collectAsState().value)){
            Spacer(Modifier.height(5.dp))

            Text(
                text = getElementString(customTechVM.elementList.collectAsState().value),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center)
        }

        Spacer(Modifier.height(5.dp))

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
                        customTechVM.mainChecklist
                    )
                }
            }

            //display optional effect table if one given
            if (customTechVM.optTable1.value != null) {
                item{Spacer(Modifier.height(5.dp))}
                item { Text(text = customTechVM.optHeader1.collectAsState().value!!) }
                items(customTechVM.optTable1.value!!) {
                    TechniqueTableRow(
                        customTechVM,
                        it,
                        customTechVM.opt1Checklist
                    )
                }
            }

            //display second optional effect table if one given
            if (customTechVM.optTable2.value != null) {
                item{Spacer(Modifier.height(5.dp))}
                item { Text(text = customTechVM.optHeader2.collectAsState().value!!) }
                items(customTechVM.optTable2.value!!) {
                    TechniqueTableRow(
                        customTechVM,
                        it,
                        customTechVM.opt2Checklist
                    )
                }
            }

            //display element table if one given
            if (customTechVM.optElement.value != null) {
                item {Text(text = customTechVM.optHeader1.collectAsState().value!!) }
                items(customTechVM.optElement.value!!) {
                    ElementalRow(
                        customTechVM,
                        it
                    )
                }
            }

            //if selecting a predetermination effect
            if(customTechVM.techniqueIndex.value == 38){
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = stringResource(R.string.predeterminedPrompt),
                            modifier = Modifier
                                .weight(0.6f),
                            textAlign = TextAlign.Center
                        )
                        NumberInput(
                            inputText = customTechVM.predeterminedCost.collectAsState().value,
                            inputFunction = { customTechVM.setPredeterminedCost(it.toInt()) },
                            emptyFunction = { customTechVM.setPredeterminedCost("") },
                            modifier = Modifier
                                .weight(0.4f)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Header for the table of technique effects.
 *
 * @param description unique descriptor for the associated technique effect
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
        Text(
            text = stringResource(R.string.primaryHeader),
            modifier = Modifier
                .weight(0.1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.secondaryHeader),
            modifier = Modifier.weight(0.1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.knowledgeCostHeader),
            modifier = Modifier
                .weight(0.1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.maintenanceHeader),
            modifier = Modifier
                .weight(0.1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.levelHeader),
            modifier = Modifier
                .weight(0.1f),
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Displays a single row for the effect table.
 *
 * @param customTechVM viewModel for the custom technique dialog
 * @param input specific technique data to display
 * @param holdingList specific list this data is found in
 */
@Composable
private fun TechniqueTableRow(
    customTechVM: CustomTechniqueViewModel,
    input: TechniqueTableData,
    holdingList: MutableMap<TechniqueTableData, MutableState<Boolean>>
){
    //get context for toast displays
    val context = LocalContext.current

    Row(verticalAlignment = Alignment.CenterVertically)
    {
        //display checkbox
        Checkbox(
            checked = holdingList[input]!!.value,
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
                        holdingList[input]!!.value = true
                    }
                    //display reason for invalid addition
                    else
                        Toast.makeText(
                            context,
                            context.getString(textOut),
                            Toast.LENGTH_SHORT
                        ).show()
                }
                //remove addition state
                else
                    holdingList[input]!!.value = false
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
 * Displays a table row for adding element effects.
 *
 * @param customTechVM viewModel for the custom technique dialog
 * @param elementType element to display in this row
 */
@Composable
private fun ElementalRow(
    customTechVM: CustomTechniqueViewModel,
    elementType: Element
){
    //get context of dialog
    val context = LocalContext.current

    Row{
        //elemental selection checkbox
        Checkbox(
            checked = customTechVM.elementChecklist[elementType]!!.value,
            onCheckedChange = {
                //make sure an effect is selected first
                if(customTechVM.getSelectedEffects().isNotEmpty()){
                    //retrieve selection and clear elements
                    val selection = customTechVM.getSelectedEffects()[0]
                    selection.elements.clear()

                    //for elemental binding of two elements
                    if(selection.effect == "Two Elements")
                        //change checkbox if input is valid
                        customTechVM.elementChecklist[elementType]!!.value = it && customTechVM.getSelectedElement(selection).size < 2


                    //any other selection
                    else{
                        //if trying to add
                        if(it) {
                            //clear other elements first
                            customTechVM.clearElementChecks()
                        }

                        //apply user's desired state
                        customTechVM.elementChecklist[elementType]!!.value = it
                    }
                }

                //notify user of failure
                else
                    Toast.makeText(
                        context,
                        context.getString(R.string.emptyEffectNotice),
                        Toast.LENGTH_SHORT
                    ).show()
            }
        )

        //display element name
        Text(text = elementType.name)
    }
}

/**
 * Create a row for when the user is editing their technique's effects.
 *
 * @param effectPair effect and boolean state to display in this row
 */
@Composable
private fun EditEffectRow(
    effectPair: Map.Entry<TechniqueEffect, MutableState<Boolean>>
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //checkbox to indicate effect deletion
        Checkbox(
            checked = effectPair.value.value,
            onCheckedChange = {
                //change deletion status accordingly
                effectPair.value.value = it
            },
            modifier = Modifier
                .weight(0.1f)
        )

        //display effect
        Text(
            text = "${effectPair.key.name} ${effectPair.key.effect}",
            modifier = Modifier
                .weight(0.9f),
            fontWeight = FontWeight.Bold
        )

    }

    //display effect's cost
    Row {
        Spacer(Modifier.weight(0.1f))

        Text(
            text = stringResource(R.string.costLabel) + " ${effectPair.key.mkCost}",
            modifier = Modifier
                .weight(0.9f)
        )
    }

    //display effect's elements
    Row{
        Spacer(Modifier.weight(0.1f))

        Text(
            text = getElementString(effectPair.key.elements),
            modifier = Modifier.weight(0.9f)
        )
    }
}

/**
 * Row that displays an effect's ki build needed for the indicated characteristic and allows the
 * user to alter that value
 *
 * @param item display data for the specific item
 */
@Composable
private fun EditBuildRow(
    item: CustomTechniqueViewModel.BuildItem
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ){
        //display characteristic name
        Text(
            text = "${item.indexName}: ",
            modifier = Modifier
                .weight(0.4f),
            textAlign = TextAlign.Center
        )

        //create numerical input for the user
        NumberInput(
            inputText = item.display.collectAsState().value,
            inputFunction = {item.setDisplay(it.toInt())},
            emptyFunction = {item.setDisplay("")},
            modifier = Modifier.weight(0.3f)
        )

        //display characteristic's additional cost
        Text(
            text = "+${item.home.buildAdditions[item.index]}",
            modifier = Modifier
                .weight(0.3f),
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Displays a primary characteristic's current maintenance value and allows the user to alter it.
 *
 * @param maintInput viewModel data for this item's display and changes to it
 */
@Composable
private fun MaintenanceInput(
    maintInput: CustomTechniqueViewModel.MaintInput
){
    Row(
        modifier = Modifier
            .fillMaxWidth(0.7f),
        verticalAlignment = Alignment.CenterVertically
    ){
        //display characteristic
        Text(
            text = maintInput.name,
            modifier = Modifier
                .weight(0.5f),
            textAlign = TextAlign.Center
        )

        //maintenance input
        NumberInput(
            inputText = maintInput.displayValue.collectAsState().value,
            inputFunction = {maintInput.setDisplayValue(it.toInt())},
            emptyFunction = {maintInput.setDisplayValue("")},
            modifier = Modifier.weight(0.5f)
        )
    }
}

/**
 * Displays the elements associated with the displayed effect table.
 *
 * @param elementList effect's associated elements
 * @return string of all elements inputted
 */
@Composable
private fun getElementString(
    elementList: List<Element>
): String{
    //initialize display string
    var elementString = ""

    //add each element's name to the string
    elementList.forEach{
        if(elementString.isNotEmpty()) elementString += ", "
        elementString += it.name
    }

    //return element output
    return if (elementString.isNotEmpty()) stringResource(R.string.elementLabel) + " $elementString"
        else elementString
}

@Preview
@Composable
fun CustomTechniquePreview(){
    val charInstance = BaseCharacter()

    val kiFragVM = KiFragmentViewModel(charInstance.ki, charInstance.ownClass, LocalContext.current)

    val customTechVM = CustomTechniqueViewModel(LocalContext.current, kiFragVM)
    customTechVM.setCustomPageNum(8)
    customTechVM.getCustomTechnique().givenAbilities.add(
        TechniqueEffect(
            "Test",
            "Value",
            5,
            1,
            Pair(1, 2),
            mutableListOf(0, 0, 0, 0, 0, 1),
            listOf(0, 0, 0, 0, 0, 0),
            mutableListOf(Element.Light),
            1
        )
    )

    customTechVM.getCustomTechnique().givenAbilities.add(
        TechniqueEffect(
            "Test2",
            "Value",
            5,
            1,
            Pair(1, 2),
            mutableListOf(0, 2, 0, 0, 0, 0),
            listOf(0, 0, 0, 0, 0, 0),
            mutableListOf(Element.Light),
            1
        )
    )

    customTechVM.setMaintenanceSelection(true)
    customTechVM.setTechniqueName("Test Tech")

    CustomTechnique(kiFragVM, customTechVM){TechContents(it)}
}