package com.example.animabuilder.activities.fragments.home_fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.fragment.app.Fragment
import com.example.animabuilder.character_creation.attributes.class_objects.CharClass
import com.example.animabuilder.character_creation.attributes.class_objects.ClassName
import com.example.animabuilder.character_creation.attributes.race_objects.CharRace
import com.example.animabuilder.character_creation.attributes.race_objects.RaceName

/**
 * Fragment to be displayed when working with basic characteristics
 * Used to manipulate core stats, class, race, and name
 * Default fragment at character load
 */

class CharacterPageFragment : Fragment() {

    private var charInstance: BaseCharacter = BaseCharacter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)

        val fromActivity = arguments
        charInstance = fromActivity!!.getSerializable("Character") as BaseCharacter

        return ComposeView(requireContext()).apply{
            setContent{
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Row() {
                        val inputName = remember { mutableStateOf(charInstance.charName) }

                        Text(text = stringResource(R.string.nameText))

                        TextField(
                            value = inputName.value!!,
                            onValueChange = {
                                inputName.value = it
                                charInstance.charName = inputName.value
                            }
                        )
                    }

                    val maxDP = remember{mutableStateOf(charInstance.devPT)}
                    val maxCombat = remember{mutableStateOf(charInstance.maxCombatDP)}
                    val maxMagic = remember{mutableStateOf(charInstance.maxMagDP)}
                    val maxPsychic = remember{mutableStateOf(charInstance.maxPsyDP)}

                    Row(){
                        val classOpen = remember{mutableStateOf(false)}
                        val className = remember{mutableStateOf(charInstance.ownClass)}
                        val classArray = stringArrayResource(id = R.array.classArray)

                        val classSize = remember{mutableStateOf(Size.Zero)}
                        val classIcon = if(classOpen.value)
                            Icons.Filled.KeyboardArrowUp
                        else
                            Icons.Filled.KeyboardArrowDown

                        Text(text = stringResource(R.string.classText))

                        OutlinedTextField(
                            value = classArray[className.value!!.classIndex],
                            onValueChange = {},
                            modifier = Modifier
                                .onGloballyPositioned { coordinates ->
                                    classSize.value = coordinates.size.toSize()
                                },
                            trailingIcon = {
                                Icon(classIcon, "contentDescription", modifier= Modifier.clickable{classOpen.value = !classOpen.value})
                            }
                        )

                        DropdownMenu(
                            expanded = classOpen.value,
                            onDismissRequest = {classOpen.value = false},
                            modifier = Modifier.width(with(LocalDensity.current){classSize.value.width.toDp()})
                        ) {
                            classArray.forEach { item ->
                                DropdownMenuItem(
                                    onClick = {
                                        className.value = CharClass(ClassName.fromString(item))

                                        charInstance.setOwnClass(item)
                                        maxDP.value = charInstance.devPT
                                        maxCombat.value = charInstance.maxCombatDP
                                        maxMagic.value = charInstance.maxMagDP
                                        maxPsychic.value = charInstance.maxPsyDP

                                        classOpen.value = false
                                    }) {
                                    Text(text = item)
                                }
                            }
                        }
                    }

                    Row(){
                        val raceOpen = remember{mutableStateOf(false)}
                        val raceName = remember{mutableStateOf(charInstance.ownRace)}
                        val raceArray = stringArrayResource(id = R.array.raceArray)

                        val raceSize = remember{mutableStateOf(Size.Zero)}
                        val raceIcon = if(raceOpen.value)
                            Icons.Filled.KeyboardArrowUp
                        else
                            Icons.Filled.KeyboardArrowDown

                        Text(text = stringResource(R.string.raceText))

                        OutlinedTextField(
                            value = raceArray[raceName.value!!.raceIndex],
                            onValueChange = {},
                            modifier = Modifier
                                .onGloballyPositioned { coordinates ->
                                    raceSize.value = coordinates.size.toSize()
                                },
                            trailingIcon = {
                                Icon(raceIcon, "contentDescription", modifier= Modifier.clickable{raceOpen.value = !raceOpen.value})
                            }
                        )

                        DropdownMenu(
                            expanded = raceOpen.value,
                            onDismissRequest = {raceOpen.value = false}
                        ){
                            raceArray.forEach{ item ->
                                DropdownMenuItem(
                                    onClick = {
                                        raceName.value = CharRace(RaceName.fromString(item))
                                        charInstance.setOwnRace(item)
                                        raceOpen.value = false
                                    }){
                                    Text(text = item)
                                }
                            }
                        }
                    }

                    Row(){
                        val levelOpen = remember{mutableStateOf(false)}
                        val levelName = remember{mutableStateOf(charInstance.lvl)}
                        val levelArray = stringArrayResource(id = R.array.levelCountArray)

                        val levelSize = remember{mutableStateOf(Size.Zero)}
                        val levelIcon = if(levelOpen.value)
                            Icons.Filled.KeyboardArrowUp
                        else
                            Icons.Filled.KeyboardArrowDown

                        Text(text = stringResource(R.string.levelText))

                        OutlinedTextField(
                            value = levelArray[levelName.value!!],
                            onValueChange = {},
                            modifier = Modifier
                                .onGloballyPositioned { coordinates ->
                                    levelSize.value = coordinates.size.toSize()
                                },
                            trailingIcon = {
                                Icon(levelIcon, "contentDescription", modifier= Modifier.clickable{levelOpen.value = !levelOpen.value})
                            }
                        )

                        DropdownMenu(
                            expanded = levelOpen.value,
                            onDismissRequest = {levelOpen.value = false}
                        ){
                            levelArray.forEach{item ->
                                DropdownMenuItem(
                                    onClick = {
                                        levelName.value = item.toInt()

                                        charInstance.setLvl(levelName.value)
                                        maxDP.value = charInstance.devPT
                                        maxCombat.value = charInstance.maxCombatDP
                                        maxMagic.value = charInstance.maxMagDP
                                        maxPsychic.value = charInstance.maxPsyDP

                                        levelOpen.value = false
                                    }){
                                    Text(text = item)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(){
                        Spacer(Modifier.weight(0.2f))
                        Text(text = stringResource(R.string.totalLabel), modifier = Modifier.weight(0.2f))
                        Text(text = stringResource(R.string.dpCombatLabel), modifier = Modifier.weight(0.2f))
                        Text(text = stringResource(R.string.dpMagicLabel), modifier = Modifier.weight(0.2f))
                        Text(text = stringResource(R.string.dpPsychicLabel), modifier = Modifier.weight(0.2f))
                    }

                    Row(){
                        Text(text = stringResource(R.string.maxRowLabel), modifier = Modifier.weight(0.2f))
                        Text(text = maxDP.value.toString(), modifier = Modifier.weight(0.2f))
                        Text(text = maxCombat.value.toString(), modifier = Modifier.weight(0.2f))
                        Text(text = maxMagic.value.toString(), modifier = Modifier.weight(0.2f))
                        Text(text = maxPsychic.value.toString(), modifier = Modifier.weight(0.2f))
                    }

                    val usedDP = remember{mutableStateOf(charInstance.spentTotal)}
                    val usedCombat = remember{mutableStateOf(charInstance.ptInCombat)}
                    val usedMagic = remember{mutableStateOf(charInstance.ptInMag)}
                    val usedPsychic = remember{mutableStateOf(charInstance.ptInPsy)}

                    Row(){
                        Text(text = stringResource(R.string.usedRowLabel), modifier = Modifier.weight(0.2f))
                        Text(text = usedDP.value.toString(), modifier = Modifier.weight(0.2f))
                        Text(text = usedCombat.value.toString(), modifier = Modifier.weight(0.2f))
                        Text(text = usedMagic.value.toString(), modifier = Modifier.weight(0.2f))
                        Text(text = usedPsychic.value.toString(), modifier = Modifier.weight(0.2f))
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Column(){
                        primaryRow(stringResource(R.string.strText), charInstance.str, charInstance.modSTR)
                            {newSTR -> charInstance.setSTR(newSTR); charInstance.modSTR}

                        primaryRow(stringResource(R.string.dexText), charInstance.dex, charInstance.modDEX)
                            {newDEX -> charInstance.setDEX(newDEX); charInstance.modDEX}

                        primaryRow(stringResource(R.string.agiText), charInstance.agi, charInstance.modAGI)
                            {newAGI -> charInstance.setAGI(newAGI); charInstance.modAGI}

                        primaryRow(stringResource(R.string.conText), charInstance.con, charInstance.modCON)
                            {newCON -> charInstance.setCON(newCON); charInstance.modCON}

                        primaryRow(stringResource(R.string.intText), charInstance.int, charInstance.modINT)
                            {newINT -> charInstance.setINT(newINT); charInstance.modINT}

                        primaryRow(stringResource(R.string.powText), charInstance.pow, charInstance.modPOW)
                            {newPOW -> charInstance.setPOW(newPOW); charInstance.modPOW}

                        primaryRow(stringResource(R.string.wpText), charInstance.wp, charInstance.modWP)
                            {newWP -> charInstance.setWP(newWP); charInstance.modWP}

                        primaryRow(stringResource(R.string.perText), charInstance.per, charInstance.modPER)
                            {newPER -> charInstance.setPER(newPER); charInstance.modPER}
                    }
                }
            }
        }
    }

    @Composable
    private fun primaryRow(labelText:String, statInput:Int, modOutput: Int, change: (newVal:Int) -> Int){
        Row(){
            val statIn = remember{mutableStateOf(statInput.toString())}
            val modOut = remember{mutableStateOf(modOutput)}

            Text(text = labelText, modifier = Modifier.weight(0.33f))

            TextField(value = statIn.value,
                onValueChange ={
                    if(it == "") {
                        statIn.value = it
                    }
                    else if(it.toInt() in 0..20){
                        statIn.value = it
                        modOut.value = change(statIn.value.toInt())
                    }
                },
                modifier = Modifier.weight(0.33f)
            )

            Text(text = modOut.value.toString(), modifier = Modifier.weight(0.33f))
        }
    }
}