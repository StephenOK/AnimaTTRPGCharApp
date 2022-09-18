package com.example.animabuilder.activities.fragments.home_fragments

import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

//initialize keyboard interaction
@OptIn(ExperimentalComposeUiApi::class)
var keyboardActive: SoftwareKeyboardController? = null


/**
 * Fragment to be displayed when working with basic characteristics
 * Used to manipulate core stats, class, race, and name
 * Default fragment at character load
 *
 * charInstance: passed character from the home page
 * maxDP: passed mutable for maximum dp to spend
 * maxCombat: passed mutable for maximum dp for combat abilities
 * maxMagic: passed mutable for maximum dp for magic abilities
 * maxPsychic: passed mutable for maximum dp for psychic abilities
 */
@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun CharacterPageFragment(
    charInstance: BaseCharacter,
    maxDP: MutableState<Int>,
    maxCombat: MutableState<Int>,
    maxMagic: MutableState<Int>,
    maxPsychic: MutableState<Int>
){
    //initialize screen size and keyboard
    val screenSize = LocalConfiguration.current
    keyboardActive = LocalSoftwareKeyboardController.current

    //initialize mutable states for resistance display
    val presence = remember{mutableStateOf(charInstance.presence)}
    val conMod = remember{mutableStateOf(charInstance.modCON)}
    val powMod = remember{mutableStateOf(charInstance.modPOW)}
    val wpMod = remember{mutableStateOf(charInstance.modWP)}

    //page column
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        //name input row
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width((screenSize.screenWidthDp * 0.67).dp)
        ) {
            //initialize mutable name
            val inputName = remember { mutableStateOf(charInstance.charName) }

            //name input field
            OutlinedTextField(
                value = inputName.value!!,
                onValueChange = {
                    //close keyboard if enter is pushed
                    if(it[it.length - 1] == '\n')
                        keyboardActive?.hide()
                    //otherwise, update name
                    else{
                        inputName.value = it
                        charInstance.charName = inputName.value
                    }},
                label = {Text(text = stringResource(R.string.nameText))},
            )
        }

        //class dropdown object
        DropdownObject(stringResource(R.string.classText), charInstance.ownClass!!.classIndex,
            stringArrayResource(id = R.array.classArray))
        {index:Int ->
            //set character's class by the given index number
            charInstance.setOwnClass(index)

            //update point maximums appropriately
            maxCombat.value = charInstance.maxCombatDP
            maxMagic.value = charInstance.maxMagDP
            maxPsychic.value = charInstance.maxPsyDP
        }

        //race dropdown object
        DropdownObject(stringResource(R.string.raceText), charInstance.ownRace!!.raceIndex,
            stringArrayResource(id = R.array.raceArray))
        //set character's race by the given index number
        {index:Int -> charInstance.setOwnRace(index) }

        //level dropdown object
        DropdownObject(stringResource(R.string.levelText), charInstance.lvl,
            stringArrayResource(R.array.levelCountArray))
        {index:Int ->
            //set character's level by the given index number
            charInstance.setLvl(index)

            //update appropriate display values
            presence.value = charInstance.presence
            maxDP.value = charInstance.devPT
            maxCombat.value = charInstance.maxCombatDP
            maxMagic.value = charInstance.maxMagDP
            maxPsychic.value = charInstance.maxPsyDP
        }

        SpaceObjects()

        //primary statistic table
        Column(modifier = Modifier.width((screenSize.screenWidthDp * 0.80).dp)){
            //table header row
            Row{
                //column buffer
                Spacer(modifier = Modifier.weight(0.33f))

                //score header
                Text(
                    text = stringResource(R.string.scoreLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.33f)
                )

                //mod header
                Text(
                    text = stringResource(R.string.modLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.33f)
                )
            }

            //create row for each primary statistic
            PrimaryRow(stringResource(R.string.strText), charInstance.str, remember{mutableStateOf(charInstance.modSTR)})
            {newSTR -> charInstance.setSTR(newSTR); charInstance.modSTR}

            PrimaryRow(stringResource(R.string.dexText), charInstance.dex, remember{mutableStateOf(charInstance.modDEX)})
            {newDEX -> charInstance.setDEX(newDEX); charInstance.modDEX}

            PrimaryRow(stringResource(R.string.agiText), charInstance.agi, remember{mutableStateOf(charInstance.modAGI)})
            {newAGI -> charInstance.setAGI(newAGI); charInstance.modAGI}

            PrimaryRow(stringResource(R.string.conText), charInstance.con, conMod)
            {newCON -> charInstance.setCON(newCON); charInstance.modCON}

            PrimaryRow(stringResource(R.string.intText), charInstance.int, remember{mutableStateOf(charInstance.modINT)})
            {newINT -> charInstance.setINT(newINT); charInstance.modINT}

            PrimaryRow(stringResource(R.string.powText), charInstance.pow, powMod)
            {newPOW -> charInstance.setPOW(newPOW); charInstance.modPOW}

            PrimaryRow(stringResource(R.string.wpText), charInstance.wp, wpMod)
            {newWP -> charInstance.setWP(newWP); charInstance.modWP}

            PrimaryRow(stringResource(R.string.perText), charInstance.per, remember{mutableStateOf(charInstance.modPER)})
            {newPER -> charInstance.setPER(newPER); charInstance.modPER}
        }

        SpaceObjects()

        //resistances table
        Column(modifier = Modifier.width((screenSize.screenWidthDp * 0.80).dp)){
            //table header row
            Row{
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

            //create a row for each of the character's resistances
            ResistanceRow("DR", presence, conMod, charInstance.rdSpec, charInstance.resistDisease)
            ResistanceRow("MR", presence, powMod, charInstance.rmSpec, charInstance.resistMag)
            ResistanceRow("PhR", presence, conMod, charInstance.rphysSpec, charInstance.resistPhys)
            ResistanceRow("VR", presence, conMod, charInstance.rvSpec, charInstance.resistVen)
            ResistanceRow("PsR", presence, wpMod, charInstance.rpsySpec, charInstance.resistPsy)
        }
    }
}

/**
 * Creates a dropdown object for user inputs
 *
 * title: label of the dropdown object
 * objIndex: initial index to set the dropdown to
 * options: list for the dropdown object
 * clickAct: function to run on selecting a dropdown item
 */
@Composable
private fun DropdownObject(
    title: String,
    objIndex: Int,
    options: Array<String>,
    clickAct: (num:Int)->Unit
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.67).dp)
    ){
        //open value of the dropdown list
        val isOpen = remember{mutableStateOf(false)}

        //index of the dropdown display
        val index = remember{mutableStateOf(objIndex)}

        //size of the textfield
        val size = remember{mutableStateOf(Size.Zero)}

        //dropdown icon image depending on whether it's open or not
        val icon = if(isOpen.value)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        //object to hold the dropdown menu
        OutlinedTextField(
            value = options[index.value],
            onValueChange = {},
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    size.value = coordinates.size.toSize() },
            label = {Text(text = title)},
            trailingIcon = {
                Icon(icon, "contentDescription", modifier= Modifier.clickable{isOpen.value = !isOpen.value})
            }
        )

        //dropdown object
        DropdownMenu(
            expanded = isOpen.value,
            onDismissRequest = {isOpen.value = false},
            modifier = Modifier.width(with(LocalDensity.current){size.value.width.toDp()})
        ) {
            //create an object for each option in the inputted list
            options.forEach { item ->
                DropdownMenuItem(onClick = {
                    //set the new item to show
                    index.value = options.indexOf(item)

                    //run the inputted action
                    clickAct(index.value)

                    //close the dropdown menu
                    isOpen.value = false
                }) {
                    Text(text = item)
                }
            }
        }
    }
}

/**
 * Create a row for the primary characteristics table
 *
 * labelText: name of the primary characteristic
 * statInput: initial score input
 * modOutput: mutable state of the mod display portion of the row
 * change: function to run on user input
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun PrimaryRow(
    labelText: String,
    statInput: Int,
    modOutput: MutableState<Int>,
    change: (newVal:Int) -> Int
){
    Row(verticalAlignment = Alignment.CenterVertically){
        //make user input in textfield mutable
        val statIn = remember{mutableStateOf(statInput.toString())}

        //row label
        Text(
            text = labelText,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.33f))

        //user input section
        TextField(
            value = statIn.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange ={
                //try to take user's input
                try{
                    //check if input is within legal range
                    if(it.toInt() in 1..20) {
                        //update display and mod values
                        statIn.value = it
                        modOutput.value = change(statIn.value.toInt())
                    }
                }
                //catch non-numeric input
                catch(e: NumberFormatException){
                    //allow empty inputs to display
                    if(it == "")
                        statIn.value = it
                    //hide keyboard if enter pressed
                    else if(it.contains('\n'))
                        keyboardActive!!.hide()
                }},
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier.weight(0.33f)
        )

        //mod display
        Text(
            text = modOutput.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.33f))
    }
}

/**
 * Create a row for the resistances table
 *
 * labelText: head for the row item
 * presence: mutable state of the character's presence
 * modStat: mutable state of the resistance's associated modifier
 * special: special buff to the resistance stat
 * total: final total of the resistance stat
 */
@Composable
private fun ResistanceRow(
    labelText: String,
    presence: MutableState<Int>,
    modStat: MutableState<Int>,
    special: Int,
    total: Int
){
    //set modifiers for integer input
    val specialStat = remember{mutableStateOf(special.toString())}
    val resistStat = remember{mutableStateOf(total.toString())}

    Row(verticalAlignment = Alignment.CenterVertically){
        //name of the resistance type
        Text(
            text = labelText,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //character's presence
        Text(
            text = presence.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //associated modifier value
        Text(
            text = modStat.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //associated special addition
        Text(
            text = specialStat.value,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //total resistance value
        Text(
            text = resistStat.value,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
    }
}

@Composable
private fun SpaceObjects(){
    Spacer(modifier = Modifier.height(30.dp))
}