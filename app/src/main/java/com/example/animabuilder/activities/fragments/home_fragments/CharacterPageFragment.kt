package com.example.animabuilder.activities.fragments.home_fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import android.view.View
import android.widget.Toast
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotMutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.fragment.app.Fragment
import com.example.animabuilder.character_creation.attributes.class_objects.CharClass
import com.example.animabuilder.character_creation.attributes.class_objects.ClassName
import com.example.animabuilder.character_creation.attributes.race_objects.CharRace
import com.example.animabuilder.character_creation.attributes.race_objects.RaceName
import java.lang.reflect.Type

/**
 * Fragment to be displayed when working with basic characteristics
 * Used to manipulate core stats, class, race, and name
 * Default fragment at character load
 */

class CharacterPageFragment : Fragment() {

    private var charInstance: BaseCharacter = BaseCharacter()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreate(savedInstanceState)

        val fromActivity = arguments
        charInstance = fromActivity!!.getSerializable("Character") as BaseCharacter

        return ComposeView(requireContext()).apply{
            setContent{
                val screenSize = LocalConfiguration.current
                val keyboardActive = LocalSoftwareKeyboardController.current
                
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.verticalScroll(rememberScrollState())
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
                                }
                            },
                            label = {Text(text = stringResource(R.string.nameText))},
                        )
                    }

                    val maxDP = remember{mutableStateOf(charInstance.devPT)}
                    val maxCombat = remember{mutableStateOf(charInstance.maxCombatDP)}
                    val maxMagic = remember{mutableStateOf(charInstance.maxMagDP)}
                    val maxPsychic = remember{mutableStateOf(charInstance.maxPsyDP)}

                    dropdownObject(stringResource(R.string.classText), charInstance.ownClass!!.classIndex,
                        stringArrayResource(id = R.array.classArray))
                    {index:Int ->
                        charInstance.setOwnClass(index)

                        maxDP.value = charInstance.devPT
                        maxCombat.value = charInstance.maxCombatDP
                        maxMagic.value = charInstance.maxMagDP
                        maxPsychic.value = charInstance.maxPsyDP
                    }

                    dropdownObject(stringResource(R.string.raceText), charInstance.ownRace!!.raceIndex,
                        stringArrayResource(id = R.array.raceArray))
                    {index:Int -> charInstance.setOwnRace(index) }

                    dropdownObject(stringResource(R.string.levelText), charInstance.lvl,
                        stringArrayResource(R.array.levelCountArray))
                    {index:Int ->
                        charInstance.setLvl(index)
                        maxDP.value = charInstance.devPT
                        maxCombat.value = charInstance.maxCombatDP
                        maxMagic.value = charInstance.maxMagDP
                        maxPsychic.value = charInstance.maxPsyDP
                    }

                    spaceObjects()

                    Column(modifier = Modifier.width((screenSize.screenWidthDp * 0.65).dp)) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.weight(0.2f))
                            Text(
                                text = stringResource(R.string.totalLabel),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = stringResource(R.string.dpCombatLabel),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = stringResource(R.string.dpMagicLabel),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = stringResource(R.string.dpPsychicLabel),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.maxRowLabel),
                                textAlign = TextAlign.End,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = maxDP.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = maxCombat.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(text = maxMagic.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = maxPsychic.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                        }

                        val usedDP = remember { mutableStateOf(charInstance.spentTotal) }
                        val usedCombat = remember { mutableStateOf(charInstance.ptInCombat) }
                        val usedMagic = remember { mutableStateOf(charInstance.ptInMag) }
                        val usedPsychic = remember { mutableStateOf(charInstance.ptInPsy) }

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.usedRowLabel),
                                textAlign = TextAlign.End,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = usedDP.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f))
                            Text(
                                text = usedCombat.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = usedMagic.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = usedPsychic.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                        }
                    }

                    spaceObjects()

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
    private fun dropdownObject(title: String, objIndex: Int, options: Array<String>,
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
                        size.value = coordinates.size.toSize()
                    },
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

    @Composable
    private fun primaryRow(labelText:String, statInput:Int, modOutput: Int, change: (newVal:Int) -> Int){
        Row(verticalAlignment = Alignment.CenterVertically){
            val statIn = remember{mutableStateOf(statInput.toString())}
            val modOut = remember{mutableStateOf(modOutput)}

            Text(
                text = labelText,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.33f))

            TextField(
                value = statIn.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange ={
                    if(it == "") {
                        statIn.value = it
                    }
                    else if(it.toInt() in 0..20){
                        statIn.value = it
                        modOut.value = change(statIn.value.toInt())
                    }
                },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                modifier = Modifier.weight(0.33f)
            )

            Text(
                text = modOut.value.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.33f))
        }
    }

    @Composable
    private fun spaceObjects(){
        Spacer(modifier = Modifier.height(30.dp))
    }
}