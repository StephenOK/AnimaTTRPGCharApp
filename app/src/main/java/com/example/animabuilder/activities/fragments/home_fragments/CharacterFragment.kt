package com.example.animabuilder.activities.fragments.home_fragments

import android.widget.Toast
import com.example.animabuilder.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.animabuilder.view_models.BottomBarViewModel
import com.example.animabuilder.UserInput
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.primary_abilities.PrimaryCharacteristic
import com.example.animabuilder.view_models.CharacterFragmentVM

/**
 * Fragment to be displayed when working with basic characteristics
 * Used to manipulate core stats, class, race, and name
 * Default fragment at character load
 *
 * maxDP: passed mutable for maximum dp to spend
 * maxCombat: passed mutable for maximum dp for combat abilities
 * maxMagic: passed mutable for maximum dp for magic abilities
 * maxPsychic: passed mutable for maximum dp for psychic abilities
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CharacterPageFragment(
    charInstance: BaseCharacter,
    maxNumVM: BottomBarViewModel,
    charFragVM: CharacterFragmentVM = CharacterFragmentVM(charInstance.primaryList),
    updateFunc: () -> Unit,
){
    val context = LocalContext.current

    val primaryList = charInstance. primaryList

    //initialize mutable name
    val inputName = remember{mutableStateOf(charInstance.charName)}

    val sizeText = remember{mutableStateOf(charInstance.sizeCategory.toString())}
    val appearanceText = remember{mutableStateOf(charInstance.appearance.toString())}

    val primaryDataList = mutableListOf<PrimaryData>()

    //define primary characteristic items
    primaryDataList.add(
        PrimaryData(
            stringResource(R.string.strText),
            primaryList.str,
            charFragVM.strengthData
        ) {sizeText.value = charInstance.sizeCategory.toString()}
    )

    primaryDataList.add(
        PrimaryData(
            stringResource(R.string.dexText),
            primaryList.dex,
            charFragVM.dexterityData
        ) {}
    )

    primaryDataList.add(
        PrimaryData(
            stringResource(R.string.agiText),
            primaryList.agi,
            charFragVM.agilityData
        ) {}
    )

    primaryDataList.add(
        PrimaryData(
            stringResource(R.string.conText),
            primaryList.con,
            charFragVM.constitutionData
        ) {sizeText.value = charInstance.sizeCategory.toString()}
    )

    primaryDataList.add(
        PrimaryData(
            stringResource(R.string.intText),
            primaryList.int,
            charFragVM.intelligenceData
        ) {}
    )

    primaryDataList.add(
        PrimaryData(
            stringResource(R.string.powText),
            primaryList.pow,
            charFragVM.powerData
        ) {}
    )

    primaryDataList.add(
        PrimaryData(
            stringResource(R.string.wpText),
            primaryList.wp,
            charFragVM.willpowerData
        ) {}
    )

    primaryDataList.add(
        PrimaryData(
            stringResource(R.string.perText),
            primaryList.per,
            charFragVM.perceptionData
        ) {}
    )

    //initialize list for items() displays
    val dropdownList = mutableListOf<DropdownData>()

    //define dropdown items
    dropdownList.add(DropdownData(stringResource(R.string.classText), remember{mutableStateOf(charInstance.classes.allClasses.indexOf(charInstance.ownClass))},
        stringArrayResource(id = R.array.classArray)
    ) { index: Int ->
        //set character's class by the given index number
        charInstance.setOwnClass(index)

        //update point maximums appropriately
        maxNumVM.setMaxCombat(charInstance.maxCombatDP)
        maxNumVM.setMaxMagic(charInstance.maxMagDP)
        maxNumVM.setMaxPsychic(charInstance.maxPsyDP)

        updateFunc()
    })

    dropdownList.add(DropdownData(stringResource(R.string.raceText), remember{mutableStateOf(charInstance.ownRace.raceIndex)},
        stringArrayResource(id = R.array.raceArray)
    )
    //set character's race by the given index number
    { index: Int ->
        charInstance.setOwnRace(index)

        charFragVM.strengthData.setOutput(primaryList.str)

        sizeText.value = charInstance.sizeCategory.toString()

        appearanceText.value = charInstance.appearance.toString()
    })

    dropdownList.add(DropdownData(stringResource(R.string.levelText), remember{mutableStateOf(charInstance.lvl)},
        stringArrayResource(R.array.levelCountArray)
    )
    { index: Int ->
        //set character's level by the given index number
        charInstance.setLvl(index)

        //update appropriate display values
        maxNumVM.setMaxDP(charInstance.devPT)
        maxNumVM.setMaxCombat(charInstance.maxCombatDP)
        maxNumVM.setMaxMagic(charInstance.maxMagDP)
        maxNumVM.setMaxPsychic(charInstance.maxPsyDP)
    })

    val keyboardActive = LocalSoftwareKeyboardController.current

    //page column
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        //name input
        item {
            OutlinedTextField(
                value = inputName.value,
                onValueChange = {
                    //close keyboard if enter is pushed
                    if (it.contains('\n'))
                        keyboardActive?.hide()
                    //otherwise, update name
                    else {
                        inputName.value = it
                        charInstance.charName = inputName.value
                    }
                },
                label = { Text(text = stringResource(R.string.nameText)) },
            )
        }

        //other dropdown items
        items(dropdownList){dropItem ->
            DropdownObject(dropItem)
        }

        //primary statistic table
        item {
            //table header row
            Row {
                //column buffer
                Spacer(modifier = Modifier.weight(0.25f))

                //score header
                Text(
                    text = stringResource(R.string.scoreLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.25f)
                )

                //bonus header
                Text(
                    text = stringResource(R.string.specialLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.25f)
                )

                //mod header
                Text(
                    text = stringResource(R.string.modLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.25f)
                )
            }
        }

        //create row for each primary statistic
        items(primaryDataList){ primaryItem ->
            PrimaryRow(primaryItem)
        }

        item{Text(text = "Size Category: " + sizeText.value)}

        item{
            Text(text = "Appearance: ")
            UserInput(
                appearanceText.value,
                {},
                {input ->
                    if(input.toInt() <= 10) {
                        charInstance.setAppearance(input.toInt())
                        if(charInstance.appearance == input.toInt())
                            appearanceText.value = input
                        else
                            Toast.makeText(context, "Invalid Appearance Input", Toast.LENGTH_LONG).show()
                    }
                },
                {
                    if(charInstance.advantageRecord.getAdvantage("Unattractive") == null) {
                        charInstance.setAppearance(5)
                        appearanceText.value = ""
                    }
                },
                {},
                Modifier
            )
        }
    }
}

/**
 * Creates a dropdown object for user inputs
 *
 * item: data set of dropdown object
 */
@Composable
private fun DropdownObject(item: DropdownData){
    //open value of the dropdown list
    val isOpen = remember{mutableStateOf(false)}

    //index of the dropdown display
    val index = item.objIndex

    //size of the textfield
    val size = remember{mutableStateOf(Size.Zero)}

    //dropdown icon image depending on whether it's open or not
    val icon = remember{mutableStateOf(Icons.Filled.KeyboardArrowDown)}

    icon.value =
        if(isOpen.value)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.67).dp)
    ){
        //object to hold the dropdown menu
        OutlinedTextField(
            value = item.options[index.value],
            onValueChange = {},
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    size.value = coordinates.size.toSize()
                },
            label = { Text(text = item.title) },
            trailingIcon = {
                Icon(
                    icon.value,
                    "contentDescription",
                    modifier = Modifier.clickable { isOpen.value = !isOpen.value })
            }
        )

        //dropdown object
        DropdownMenu(
            expanded = isOpen.value,
            onDismissRequest = {isOpen.value = false},
            modifier = Modifier.width(with(LocalDensity.current) { size.value.width.toDp() })
        ) {
            //create an object for each option in the inputted list
            item.options.forEach { stringIn ->
                DropdownMenuItem(onClick = {
                    //set the new item to show
                    index.value = item.options.indexOf(stringIn)

                    //run the inputted action
                    item.clickAct(index.value)

                    //close the dropdown menu
                    isOpen.value = false
                }) {
                    Text(text = stringIn)
                }
            }
        }
    }
}

/**
 * Create a row for the primary characteristics table
 *
 * primeItem: primary characteristic data to display
 */
@Composable
private fun PrimaryRow(
    primeItem: PrimaryData
){
    Row(verticalAlignment = Alignment.CenterVertically){
        //row label
        Text(
            text = primeItem.labelText,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.25f))

        //user input section
        UserInput(
            primeItem.vmData.input.collectAsState().value,
            {},
            {input ->
                if(input.toInt() in 1..20) {
                    //update display and mod values
                    primeItem.primaryStat.setInput(input.toInt())
                    primeItem.vmData.setInput(input)
                    primeItem.vmData.setOutput(primeItem.primaryStat)

                    primeItem.changeFunc()
                }
            },
            {primeItem.vmData.setInput("")},
            {},
            Modifier.weight(0.25f)
        )

        Text(
            text = primeItem.vmData.output.collectAsState().value.specialVal,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.25f)
        )

        //mod display
        Text(
            text = primeItem.vmData.output.collectAsState().value.modVal,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.25f))
    }
}

/**
 * Class that holds data for a dropdown object
 *
 * title: label of the dropdown object
 * objIndex: initial index to set the dropdown to
 * options: list for the dropdown object
 * clickAct: function to run on selecting a dropdown item
 */
private data class DropdownData(
    val title: String,
    val objIndex: MutableState<Int>,
    val options: Array<String>,
    val clickAct: (num:Int)->Unit)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DropdownData

        if (title != other.title) return false
        if (objIndex != other.objIndex) return false
        if (!options.contentEquals(other.options)) return false
        if (clickAct != other.clickAct) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + objIndex.value
        result = 31 * result + options.contentHashCode()
        result = 31 * result + clickAct.hashCode()
        return result
    }
}

/**
 * Class that holds data for a primary characteristic
 *
 * labelText: name of the primary characteristic
 * statInput: initial score input
 * statSpec: additional points in the characteristic
 * modOutput: mutable state of the mod display portion of the row
 * change: function to run on user input
 */
private data class PrimaryData(
    val labelText: String,
    val primaryStat: PrimaryCharacteristic,
    val vmData: CharacterFragmentVM.PrimeCharacteristicData,
    val changeFunc: () -> Unit
)