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

/**
 * Fragment to be displayed when working with basic characteristics
 * Used to manipulate core stats, class, race, and name
 * Default fragment at character load
 */

@OptIn(ExperimentalComposeUiApi::class)
var keyboardActive: SoftwareKeyboardController? = null

@Composable
@OptIn(ExperimentalComposeUiApi::class)

fun CharacterPageFragment(
    charInstance: BaseCharacter,
    maxDP: MutableState<Int>,
    maxCombat: MutableState<Int>,
    maxMagic: MutableState<Int>,
    maxPsychic: MutableState<Int>
){
    val screenSize = LocalConfiguration.current
    keyboardActive = LocalSoftwareKeyboardController.current

    val presence = remember{mutableStateOf(charInstance.presence)}

    val strMod = remember{mutableStateOf(charInstance.modSTR)}
    val dexMod = remember{mutableStateOf(charInstance.modDEX)}
    val agiMod = remember{mutableStateOf(charInstance.modAGI)}
    val conMod = remember{mutableStateOf(charInstance.modCON)}
    val intMod = remember{mutableStateOf(charInstance.modINT)}
    val powMod = remember{mutableStateOf(charInstance.modPOW)}
    val wpMod = remember{mutableStateOf(charInstance.modWP)}
    val perMod = remember{mutableStateOf(charInstance.modPER)}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width((screenSize.screenWidthDp * 0.67).dp)
        ) {
            val inputName = remember { mutableStateOf(charInstance.charName) }

            OutlinedTextField(
                value = inputName.value!!,
                onValueChange = {
                    if(it[it.length - 1] == '\n')
                        keyboardActive?.hide()
                    else{
                        inputName.value = it
                        charInstance.charName = inputName.value
                    }},
                label = {Text(text = stringResource(R.string.nameText))},
            )
        }

        DropdownObject(stringResource(R.string.classText), charInstance.ownClass!!.classIndex,
            stringArrayResource(id = R.array.classArray))
        {index:Int ->
            charInstance.setOwnClass(index)

            maxDP.value = charInstance.devPT
            maxCombat.value = charInstance.maxCombatDP
            maxMagic.value = charInstance.maxMagDP
            maxPsychic.value = charInstance.maxPsyDP
        }

        DropdownObject(stringResource(R.string.raceText), charInstance.ownRace!!.raceIndex,
            stringArrayResource(id = R.array.raceArray))
        {index:Int -> charInstance.setOwnRace(index) }

        DropdownObject(stringResource(R.string.levelText), charInstance.lvl,
            stringArrayResource(R.array.levelCountArray))
        {index:Int ->
            charInstance.setLvl(index)
            presence.value = charInstance.presence
            maxDP.value = charInstance.devPT
            maxCombat.value = charInstance.maxCombatDP
            maxMagic.value = charInstance.maxMagDP
            maxPsychic.value = charInstance.maxPsyDP
        }

        SpaceObjects()

        Column(modifier = Modifier.width((screenSize.screenWidthDp * 0.80).dp)){
            Row{
                Spacer(modifier = Modifier.weight(0.33f))

                Text(
                    text = stringResource(R.string.scoreLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.33f)
                )

                Text(
                    text = stringResource(R.string.modLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.33f)
                )
            }

            PrimaryRow(stringResource(R.string.strText), charInstance.str, strMod)
            {newSTR -> charInstance.setSTR(newSTR); charInstance.modSTR}

            PrimaryRow(stringResource(R.string.dexText), charInstance.dex, dexMod)
            {newDEX -> charInstance.setDEX(newDEX); charInstance.modDEX}

            PrimaryRow(stringResource(R.string.agiText), charInstance.agi, agiMod)
            {newAGI -> charInstance.setAGI(newAGI); charInstance.modAGI}

            PrimaryRow(stringResource(R.string.conText), charInstance.con, conMod)
            {newCON -> charInstance.setCON(newCON); charInstance.modCON}

            PrimaryRow(stringResource(R.string.intText), charInstance.int, intMod)
            {newINT -> charInstance.setINT(newINT); charInstance.modINT}

            PrimaryRow(stringResource(R.string.powText), charInstance.pow, powMod)
            {newPOW -> charInstance.setPOW(newPOW); charInstance.modPOW}

            PrimaryRow(stringResource(R.string.wpText), charInstance.wp, wpMod)
            {newWP -> charInstance.setWP(newWP); charInstance.modWP}

            PrimaryRow(stringResource(R.string.perText), charInstance.per, perMod)
            {newPER -> charInstance.setPER(newPER); charInstance.modPER}
        }

        SpaceObjects()

        Column(modifier = Modifier.width((screenSize.screenWidthDp * 0.80).dp)){
            Row{
                Spacer(modifier = Modifier.weight(0.2f))
                Text(
                    text = "Presence",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )
                Text(
                    text = stringResource(R.string.modLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )
                Text(
                    text = "Special",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )
                Text(
                    text = stringResource(R.string.totalLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )
            }

            ResistanceRow("DR", presence, conMod, charInstance.rdSpec, charInstance.resistDisease)
            ResistanceRow("MR", presence, powMod, charInstance.rmSpec, charInstance.resistMag)
            ResistanceRow("PhR", presence, conMod, charInstance.rphysSpec, charInstance.resistPhys)
            ResistanceRow("VR", presence, conMod, charInstance.rvSpec, charInstance.resistVen)
            ResistanceRow("PsR", presence, wpMod, charInstance.rpsySpec, charInstance.resistPsy)
        }
    }
}

@Composable
private fun DropdownObject(title: String, objIndex: Int, options: Array<String>,
                               clickAct: (num:Int)->Unit){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.67).dp)
    ){
        val isOpen = remember{mutableStateOf(false)}
        val index = remember{mutableStateOf(objIndex)}
        val size = remember{mutableStateOf(Size.Zero)}
        val icon = if(isOpen.value)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

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

        DropdownMenu(
            expanded = isOpen.value,
            onDismissRequest = {isOpen.value = false},
            modifier = Modifier.width(with(LocalDensity.current){size.value.width.toDp()})
        ) {
            options.forEach { item ->
                DropdownMenuItem(onClick = {
                    index.value = options.indexOf(item)
                    clickAct(index.value)
                    isOpen.value = false
                }) {
                    Text(text = item)
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun PrimaryRow(
    labelText:String,
    statInput:Int,
    modOutput: MutableState<Int>,
    change: (newVal:Int) -> Int
){
    Row(verticalAlignment = Alignment.CenterVertically){
        val statIn = remember{mutableStateOf(statInput.toString())}

        Text(
            text = labelText,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.33f))

        TextField(
            value = statIn.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange ={
                try{
                    if(it.toInt() in 1..20) {
                        statIn.value = it
                        modOutput.value = change(statIn.value.toInt())
                    }
                }catch(e: NumberFormatException){
                    if(it == "")
                        statIn.value = it
                    else if(it[it.length - 1] == '\n')
                        keyboardActive!!.hide()
                }},
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier.weight(0.33f)
        )

        Text(
            text = modOutput.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.33f))
    }
}

@Composable
private fun ResistanceRow(
    labelText: String,
    presence: MutableState<Int>,
    modStat: MutableState<Int>,
    special: Int,
    total: Int
){
    val specialStat = remember{mutableStateOf(special.toString())}
    val resistStat = remember{mutableStateOf(total.toString())}

    Row(verticalAlignment = Alignment.CenterVertically){
        Text(
            text = labelText,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        Text(
            text = presence.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        Text(
            text = modStat.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        Text(
            text = specialStat.value,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
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