package com.example.animabuilder.activities.fragments.home_fragments

import com.example.animabuilder.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
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

    //initialize mutable name
    val inputName = remember{mutableStateOf(charInstance.charName)}

    val strMod = remember{mutableStateOf(charInstance.modSTR)}
    val agiMod = remember{mutableStateOf(charInstance.modAGI)}
    val dexMod = remember{mutableStateOf(charInstance.modDEX)}
    val conMod = remember{mutableStateOf(charInstance.modCON)}
    val powMod = remember{mutableStateOf(charInstance.modPOW)}
    val wpMod = remember{mutableStateOf(charInstance.modWP)}

    //initialize mutable states for resistance display
    val presence = remember{mutableStateOf(charInstance.presence)}

    //initialize life point data
    val lifeTotal = remember{mutableStateOf(charInstance.lifeMax)}
    val lifeMults = remember{mutableStateOf(charInstance.lifeMultsTaken.toString())}

    //determine class bonuses to combat abilities
    val classAttack = remember{mutableStateOf((charInstance.ownClass.atkPerLevel * charInstance.lvl).toString())}
    val classBlock = remember{mutableStateOf((charInstance.ownClass.blockPerLevel * charInstance.lvl).toString())}
    val classDodge = remember{mutableStateOf((charInstance.ownClass.dodgePerLevel * charInstance.lvl).toString())}
    val classWear = remember{mutableStateOf((charInstance.ownClass.armorPerLevel * charInstance.lvl).toString())}

    //get current attack ability values
    val totalAttack = remember{mutableStateOf(charInstance.attack.toString())}
    val totalBlock = remember{mutableStateOf(charInstance.block.toString())}
    val totalDodge = remember{mutableStateOf(charInstance.dodge.toString())}
    val totalWear = remember{mutableStateOf(charInstance.wearArmor.toString())}

    //initialize color item
    val pointColor =
        if(charInstance.validAttackDodgeBlock())
            remember{mutableStateOf(Color.Black)}
        else
            remember{mutableStateOf(Color.Red)}

    //initialize list for items() displays
    val dropdownList = mutableListOf<DropdownData>()
    val primaryList = mutableListOf<PrimaryData>()
    val resistanceList = mutableListOf<ResistanceData>()
    val combatItemList = mutableListOf<CombatItemData>()

    //define dropdown items
    dropdownList.add(DropdownData(stringResource(R.string.classText), remember{mutableStateOf(charInstance.ownClass.classIndex)},
        stringArrayResource(id = R.array.classArray)
    ) { index: Int ->
        //set character's class by the given index number
        charInstance.setOwnClass(index)

        //update point maximums appropriately
        maxCombat.value = charInstance.maxCombatDP
        maxMagic.value = charInstance.maxMagDP
        maxPsychic.value = charInstance.maxPsyDP

        classAttack.value =
            (charInstance.ownClass.atkPerLevel * charInstance.lvl).toString()
        classBlock.value =
            (charInstance.ownClass.blockPerLevel * charInstance.lvl).toString()
        classDodge.value =
            (charInstance.ownClass.dodgePerLevel * charInstance.lvl).toString()
        classWear.value =
            (charInstance.ownClass.armorPerLevel * charInstance.lvl).toString()

        updateFunc()
    })

    dropdownList.add(DropdownData(stringResource(R.string.raceText), remember{mutableStateOf(charInstance.ownRace!!.raceIndex)},
        stringArrayResource(id = R.array.raceArray)
    )
    //set character's race by the given index number
    { index: Int -> charInstance.setOwnRace(index) })

    dropdownList.add(DropdownData(stringResource(R.string.levelText), remember{mutableStateOf(charInstance.lvl)},
        stringArrayResource(R.array.levelCountArray)
    )
    { index: Int ->
        //set character's level by the given index number
        charInstance.setLvl(index)

        //update appropriate display values
        presence.value = charInstance.presence
        maxDP.value = charInstance.devPT
        maxCombat.value = charInstance.maxCombatDP
        maxMagic.value = charInstance.maxMagDP
        maxPsychic.value = charInstance.maxPsyDP

        classAttack.value =
            (charInstance.ownClass.atkPerLevel * charInstance.lvl).toString()
        classBlock.value =
            (charInstance.ownClass.blockPerLevel * charInstance.lvl).toString()
        classDodge.value =
            (charInstance.ownClass.dodgePerLevel * charInstance.lvl).toString()
        classWear.value =
            (charInstance.ownClass.armorPerLevel * charInstance.lvl).toString()
    })

    //define primary characteristic items
    primaryList.add(PrimaryData(stringResource(R.string.strText), remember{mutableStateOf(charInstance.str)}, strMod)
    { newSTR ->
        charInstance.setSTR(newSTR)
        totalWear.value = charInstance.wearArmor.toString()
        charInstance.modSTR
    })
    primaryList.add(PrimaryData(stringResource(R.string.dexText), remember{mutableStateOf(charInstance.dex)}, dexMod)
    { newDEX ->
        charInstance.setDEX(newDEX)
        totalAttack.value = charInstance.attack.toString()
        totalBlock.value = charInstance.block.toString()
        charInstance.modDEX
    })
    primaryList.add(PrimaryData(stringResource(R.string.agiText), remember{mutableStateOf(charInstance.agi)}, agiMod)
    { newAGI ->
        charInstance.setAGI(newAGI)
        totalDodge.value = charInstance.dodge.toString()
        charInstance.modAGI
    })
    primaryList.add(PrimaryData(stringResource(R.string.conText), remember{mutableStateOf(charInstance.con)}, conMod)
    { newCON -> charInstance.setCON(newCON); charInstance.modCON })
    primaryList.add(PrimaryData(stringResource(R.string.intText), remember{mutableStateOf(charInstance.int)}, remember{mutableStateOf(charInstance.modINT)})
    {newINT -> charInstance.setINT(newINT); charInstance.modINT})
    primaryList.add(PrimaryData(stringResource(R.string.powText), remember{mutableStateOf(charInstance.pow)}, powMod)
    {newPOW -> charInstance.setPOW(newPOW); charInstance.modPOW})
    primaryList.add(PrimaryData(stringResource(R.string.wpText), remember{mutableStateOf(charInstance.wp)}, wpMod)
    {newWP -> charInstance.setWP(newWP); charInstance.modWP})
    primaryList.add(PrimaryData(stringResource(R.string.perText), remember{mutableStateOf(charInstance.per)}, remember{mutableStateOf(charInstance.modPER)})
    {newPER -> charInstance.setPER(newPER); charInstance.modPER})

    //define resistance items
    resistanceList.add(ResistanceData(
        "DR",
        presence,
        conMod,
        charInstance.rdSpec,
        charInstance.resistDisease
    ))
    resistanceList.add(ResistanceData(
        "MR",
        presence,
        powMod,
        charInstance.rmSpec,
        charInstance.resistMag
    ))
    resistanceList.add(ResistanceData(
        "PhR",
        presence,
        conMod,
        charInstance.rphysSpec,
        charInstance.resistPhys
    ))
    resistanceList.add(ResistanceData(
        "VR",
        presence,
        conMod,
        charInstance.rvSpec,
        charInstance.resistVen
    ))
    resistanceList.add(ResistanceData(
        "PsR",
        presence,
        wpMod,
        charInstance.rpsySpec,
        charInstance.resistPsy
    ))

    //define combat row items
    combatItemList.add(CombatItemData(
        "Attack", remember{mutableStateOf(charInstance.pointInAttack)}, charInstance.applyAttackPoint, dexMod,
        classAttack, pointColor, totalAttack
    ))
    combatItemList.add(CombatItemData(
        "Block", remember{mutableStateOf(charInstance.pointInBlock)}, charInstance.applyBlockPoint, dexMod,
        classBlock, pointColor, totalBlock
    ))
    combatItemList.add(CombatItemData(
        "Dodge", remember{mutableStateOf(charInstance.pointInDodge)}, charInstance.applyDodgePoint, agiMod,
        classDodge, pointColor, totalDodge
    ))
    combatItemList.add(CombatItemData(
        "Wear Armor", remember{mutableStateOf(charInstance.pointInWear)}, charInstance.applyWearPoint, strMod,
        classWear, remember { mutableStateOf(Color.Black) }, totalWear
    ))

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
                        keyboardActive.value?.hide()
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

        item{SpaceObjects()}

        //primary statistic table
        item {
            //table header row
            Row {
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
        }

        //create row for each primary statistic
        items(primaryList){primaryItem ->
            PrimaryRow(primaryItem)
        }

        item{SpaceObjects()}

        //resistances table
        item {
            //table header row
            Row {
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
        }

        //create a row for each of the character's resistances
        items(resistanceList){resistanceItem ->
            ResistanceRow(resistanceItem)
        }

        //create header row for combat items
        item {
            Row {
                Spacer(modifier = Modifier.weight(0.2f))
                Text(text = "Base", modifier = Modifier.weight(0.2f))
                Text(text = "Class", modifier = Modifier.weight(0.2f))
                Text(text = "Multiplier", modifier = Modifier.weight(0.2f))
                Text(text = "Total", modifier = Modifier.weight(0.2f))
            }
        }

        //character life point row
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Life Points: ", modifier = Modifier.weight(0.2f))

                //display life points from base and class values
                Text(text = charInstance.lifeBase.toString(), modifier = Modifier.weight(0.2f))
                Text(
                    text = (charInstance.ownClass.lifePointsPerLevel * charInstance.lvl).toString(),
                    modifier = Modifier.weight(0.2f)
                )

                //create input for life point multiples
                TextField(
                    value = lifeMults.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        numberCatcher(it,
                            { input ->
                                charInstance.takeLifeMult(input.toInt())
                                lifeTotal.value = charInstance.lifeMax

                                lifeMults.value = input
                                updateFunc()
                            },
                            { lifeMults.value = "" }
                        )
                    },
                    modifier = Modifier.weight(0.2f)
                )

                //display life point total
                Text(text = lifeTotal.value.toString(), modifier = Modifier.weight(0.2f))
            }
        }

        item{Spacer(Modifier.height(30.dp))}

        //create table header for combat items
        item {
            Row {
                Spacer(modifier = Modifier.weight(0.2f))
                Text(text = "Points", modifier = Modifier.weight(0.2f))
                Text(
                    text = stringResource(R.string.modLabel),
                    modifier = Modifier.weight(0.2f)
                )
                Text(
                    text = stringResource(R.string.classLabel),
                    modifier = Modifier.weight(0.2f)
                )
                Text(
                    text = stringResource(R.string.totalLabel),
                    modifier = Modifier.weight(0.2f)
                )
            }
        }

        //create table row for each combat item
        items(combatItemList){combatItem ->
            CombatItemRow(combatItem, updateFunc)
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
            onDismissRequest = { isOpen.value = false },
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
private fun PrimaryRow(primeItem: PrimaryData){
    Row(verticalAlignment = Alignment.CenterVertically){
        //make user input in textfield mutable
        val statIn = remember{mutableStateOf(primeItem.statInput.value.toString())}

        //row label
        Text(
            text = primeItem.labelText,
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
                            primeItem.modOutput.value = primeItem.change(statIn.value.toInt())
                    }},
                    {statIn.value = ""}
                )
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier.weight(0.33f)
        )

        //mod display
        Text(
            text = primeItem.modOutput.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.33f))
    }
}

/**
 * Create a row for the resistances table
 *
 * resistance: resistance data to display
 */
@Composable
private fun ResistanceRow(resistance: ResistanceData){
    //set modifiers for integer input
    val specialStat = remember{mutableStateOf(resistance.special.toString())}
    val resistStat = remember{mutableStateOf(resistance.total.toString())}

    Row(verticalAlignment = Alignment.CenterVertically){
        //name of the resistance type
        Text(
            text = resistance.labelText,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //character's presence
        Text(
            text = resistance.presence.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        //associated modifier value
        Text(
            text = resistance.modStat.value.toString(),
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

/**
 * Creates a table row for the combat items and allows input and total display
 *
 * combatItem: character's combat stat to display
 * updateFunc: function to run for bottom bar update
 */
@Composable
private fun CombatItemRow(
    combatItem: CombatItemData,
    updateFunc: () -> Unit
){
    val pointInScore = remember{mutableStateOf(combatItem.pointIn.value.toString())}

    Row {
        //row label
        Text(text = combatItem.labelText, modifier = Modifier.weight(0.2f))

        //stat input field
        TextField(
            value = pointInScore.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                numberCatcher(it,
                    {input ->
                        //determine if input is valid
                        if(combatItem.changeAct(input.toInt(), combatItem.totalText))
                            combatItem.pointColor.value = Color.Black
                        else
                            combatItem.pointColor.value = Color.Red

                        //update character and display for inputted value
                        pointInScore.value = input
                        updateFunc()
                    },
                    {pointInScore.value = ""}
                )
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, color = combatItem.pointColor.value),
            modifier = Modifier.weight(0.2f)
        )

        //display remaining stat values
        Text(text = combatItem.modInput.value.toString(), modifier = Modifier.weight(0.2f))
        Text(text = combatItem.classAdd.value, modifier = Modifier.weight(0.2f))
        Text(text = combatItem.totalText.value, modifier = Modifier.weight(0.2f))
    }
}

@Composable
private fun SpaceObjects(){
    Spacer(modifier = Modifier.height(30.dp))
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
 * modOutput: mutable state of the mod display portion of the row
 * change: function to run on user input
 */
private data class PrimaryData(
    val labelText: String,
    val statInput: MutableState<Int>,
    val modOutput: MutableState<Int>,
    val change: (newVal:Int) -> Int
)

/**
 * Class that holds data for a resistance row
 *
 * labelText: head for the row item
 * presence: mutable state of the character's presence
 * modStat: mutable state of the resistance's associated modifier
 * special: special buff to the resistance stat
 * total: final total of the resistance stat
 */
private data class ResistanceData(
    val labelText: String,
    val presence: MutableState<Int>,
    val modStat: MutableState<Int>,
    val special: Int,
    val total: Int
)

/**
 * Class that holds data for a combat row
 *
 * labelText: name of the item for the row
 * pointIn: number of points applied to the stat
 * changeAct: function to run when the pointIn value changes
 * modInput: modifier value applied to the specific stat
 * classAdd: class bonus to the combat stat
 * pointColor: text color that indicates valid inputs
 * totalText: text that displays final stat value
 */
private data class CombatItemData(
    val labelText: String,
    val pointIn: MutableState<Int>,
    val changeAct: (Int, MutableState<String>) -> Boolean,
    val modInput: MutableState<Int>,
    val classAdd: MutableState<String>,
    val pointColor: MutableState<Color>,
    val totalText: MutableState<String>,
)