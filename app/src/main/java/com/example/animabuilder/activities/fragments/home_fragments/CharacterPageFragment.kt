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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.animabuilder.activities.charInstance
import com.example.animabuilder.activities.keyboardActive
import com.example.animabuilder.activities.numberCatcher

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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CharacterPageFragment(
    maxDP: MutableState<Int>,
    maxCombat: MutableState<Int>,
    maxMagic: MutableState<Int>,
    maxPsychic: MutableState<Int>,
    updateFunc: () -> Unit
){
    //initialize screen size and keyboard
    val screenSize = LocalConfiguration.current

    //initialize mutable states for resistance display
    val presence = remember{mutableStateOf(charInstance.presence)}

    val strMod = remember{mutableStateOf(charInstance.modSTR)}
    val agiMod = remember{mutableStateOf(charInstance.modAGI)}
    val dexMod = remember{mutableStateOf(charInstance.modDEX)}
    val conMod = remember{mutableStateOf(charInstance.modCON)}
    val powMod = remember{mutableStateOf(charInstance.modPOW)}
    val wpMod = remember{mutableStateOf(charInstance.modWP)}

    val classAttack = remember{mutableStateOf((charInstance.ownClass.atkPerLevel * charInstance.lvl).toString())}
    val classBlock = remember{mutableStateOf((charInstance.ownClass.blockPerLevel * charInstance.lvl).toString())}
    val classDodge = remember{mutableStateOf((charInstance.ownClass.dodgePerLevel * charInstance.lvl).toString())}
    val classWear = remember{mutableStateOf((charInstance.ownClass.armorPerLevel * charInstance.lvl).toString())}

    val totalAttack = remember{mutableStateOf(charInstance.attack.toString())}
    val totalBlock = remember{mutableStateOf(charInstance.block.toString())}
    val totalDodge = remember{mutableStateOf(charInstance.dodge.toString())}
    val totalWear = remember{mutableStateOf(charInstance.wearArmor.toString())}

    val pointColor =
            if(charInstance.validAttackDodgeBlock())
                remember{mutableStateOf(Color.Black)}
            else
                remember{mutableStateOf(Color.Red)}

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
                value = inputName.value,
                onValueChange = {
                    //close keyboard if enter is pushed
                    if(it.contains('\n'))
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

            classAttack.value = (charInstance.ownClass.atkPerLevel * charInstance.lvl).toString()
            classBlock.value = (charInstance.ownClass.blockPerLevel * charInstance.lvl).toString()
            classDodge.value = (charInstance.ownClass.dodgePerLevel * charInstance.lvl).toString()
            classWear.value = (charInstance.ownClass.armorPerLevel * charInstance.lvl).toString()

            updateFunc()
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

            classAttack.value = (charInstance.ownClass.atkPerLevel * charInstance.lvl).toString()
            classBlock.value = (charInstance.ownClass.blockPerLevel * charInstance.lvl).toString()
            classDodge.value = (charInstance.ownClass.dodgePerLevel * charInstance.lvl).toString()
            classWear.value = (charInstance.ownClass.armorPerLevel * charInstance.lvl).toString()
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
            PrimaryRow(stringResource(R.string.strText), charInstance.str, strMod)
                { newSTR ->
                    charInstance.setSTR(newSTR)
                    totalWear.value = charInstance.wearArmor.toString()
                    charInstance.modSTR
                }

            PrimaryRow(stringResource(R.string.dexText), charInstance.dex, dexMod)
                { newDEX ->
                    charInstance.setDEX(newDEX)
                    totalAttack.value = charInstance.attack.toString()
                    totalBlock.value = charInstance.block.toString()
                    charInstance.modDEX
                }

            PrimaryRow(stringResource(R.string.agiText), charInstance.agi, agiMod)
                {newAGI ->
                    charInstance.setAGI(newAGI)
                    totalDodge.value = charInstance.dodge.toString()
                    charInstance.modAGI
                }

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

        Row{
            Spacer(modifier = Modifier.weight(0.2f))
            Text(text = "Base", modifier = Modifier.weight(0.2f))
            Text(text = "Class", modifier = Modifier.weight(0.2f))
            Text(text = "Multiplier", modifier = Modifier.weight(0.2f))
            Text(text = "Total", modifier = Modifier.weight(0.2f))
        }

        Row(verticalAlignment = Alignment.CenterVertically){
            val lifeTotal = remember{mutableStateOf(charInstance.lifeMax)}
            val lifeMults = remember{mutableStateOf(charInstance.lifeMultsTaken.toString())}

            Text(text = "Life Points: ", modifier = Modifier.weight(0.2f))

            Text(text = charInstance.lifeBase.toString(), modifier = Modifier.weight(0.2f))
            Text(text = (charInstance.ownClass.lifePointsPerLevel * charInstance.lvl).toString(),
                modifier = Modifier.weight(0.2f))

            TextField(
                value = lifeMults.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    numberCatcher(it,
                        {input ->
                            charInstance.takeLifeMult(input.toInt())
                            lifeTotal.value = charInstance.lifeMax

                            lifeMults.value = input
                            updateFunc()},
                        {lifeMults.value = ""}
                    )
                },
                modifier = Modifier.weight(0.2f)
            )

            Text(text = lifeTotal.value.toString(), modifier = Modifier.weight(0.2f))
        }

        Spacer(Modifier.height(30.dp))

        Column{
            Row{
                Spacer(modifier = Modifier.weight(0.2f))
                Text(text = "Points", modifier = Modifier.weight(0.2f))
                Text(text = stringResource(R.string.modLabel), modifier = Modifier.weight(0.2f))
                Text(text = stringResource(R.string.classLabel), modifier = Modifier.weight(0.2f))
                Text(text = stringResource(R.string.totalLabel), modifier = Modifier.weight(0.2f))
            }

            CombatItemRow(
                "Attack", charInstance.pointInAttack, charInstance.applyAttackPoint, dexMod,
                classAttack, pointColor, totalAttack, charInstance.attack, updateFunc
            )
            CombatItemRow(
                "Block", charInstance.pointInBlock, charInstance.applyBlockPoint, dexMod,
                classBlock, pointColor, totalBlock, charInstance.block, updateFunc
            )
            CombatItemRow(
                "Dodge", charInstance.pointInDodge, charInstance.applyDodgePoint, agiMod,
                classDodge, pointColor, totalDodge, charInstance.dodge, updateFunc
            )
            CombatItemRow(
                "Wear Armor", charInstance.pointInWear, charInstance.applyWearPoint, strMod,
                classWear, remember{mutableStateOf(Color.Black)}, totalWear, charInstance.wearArmor, updateFunc
            )
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
    change: (newVal:Int) -> Int,
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
                numberCatcher(it,
                    {input ->
                        if(input.toInt() in 1..20) {
                            //update display and mod values
                            statIn.value = input
                            modOutput.value = change(statIn.value.toInt())
                    }},
                    {statIn.value = ""}
                )
            },
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CombatItemRow(
    labelText: String,
    pointIn: Int,
    changeAct: (Int, MutableState<String>) -> Boolean,
    modInput: MutableState<Int>,
    classAdd: MutableState<String>,
    pointColor: MutableState<Color>,
    totalText: MutableState<String>,
    total: Int,

    updateFunc: () -> Unit
){
    val pointInScore = remember{mutableStateOf(pointIn.toString())}
    val pointTotal = totalText

    Row {
        Text(text = labelText, modifier = Modifier.weight(0.2f))

        TextField(
            value = pointInScore.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                numberCatcher(it,
                    {input ->
                        if(changeAct(input.toInt(), pointTotal))
                            pointColor.value = Color.Black
                        else
                            pointColor.value = Color.Red

                        pointInScore.value = input
                        updateFunc()
                    },
                    {pointInScore.value = ""}
                )
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, color = pointColor.value),
            modifier = Modifier.weight(0.2f)
        )

        Text(text = modInput.value.toString(), modifier = Modifier.weight(0.2f))
        Text(text = classAdd.value, modifier = Modifier.weight(0.2f))
        Text(text = pointTotal.value, modifier = Modifier.weight(0.2f))
    }
}

@Composable
private fun SpaceObjects(){
    Spacer(modifier = Modifier.height(30.dp))
}