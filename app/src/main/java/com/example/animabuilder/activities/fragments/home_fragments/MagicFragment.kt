package com.example.animabuilder.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import com.example.animabuilder.activities.charInstance
import com.example.animabuilder.activities.numberCatcher
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell

@Composable
fun MagicFragment(updateFunc: () -> Unit) {
    val boughtZeonString = remember{mutableStateOf(charInstance.magic.boughtZeon.toString())}
    val maxZeonString = remember{mutableStateOf(charInstance.magic.zeonMax.toString())}

    val boughtAccString = remember{mutableStateOf(charInstance.magic.zeonAccMult.toString())}
    val totalAccString = remember{mutableStateOf(charInstance.magic.zeonAccTotal.toString())}

    val boughtProjString = remember{mutableStateOf(charInstance.magic.boughtMagProjection.toString())}
    val totalMagProjectString = remember{mutableStateOf(charInstance.magic.magProjTotal.toString())}

    val projectionImbalance = remember{mutableStateOf(charInstance.magic.magProjImbalance.toString())}
    val imbalanceIsAttack = remember{mutableStateOf(charInstance.magic.imbalanceIsAttack)}
    val imbalanceTypeString =
        if(imbalanceIsAttack.value)
            remember{mutableStateOf("Attack")}
        else
            remember{mutableStateOf("Defense")}

    val offenseImbalance = remember{mutableStateOf(determineImbalanceValue(imbalanceIsAttack.value).toString())}
    val defenseImbalance = remember{mutableStateOf(determineImbalanceValue(!imbalanceIsAttack.value).toString())}

    val magicLevelMax = remember{mutableStateOf(charInstance.magic.magicLevelMax.toString())}
    val magicLevelSpent = remember{mutableStateOf(charInstance.magic.magicLevelSpent.toString())}

    val primaryElementBoxes = remember{ mutableMapOf<Element, MutableState<Boolean>>() }

    val spellTable = mutableListOf<SpellRowData>()

    spellTable.add(SpellRowData(
        Element.Light,
        charInstance.magic.lightBook.fullBook,
        remember{mutableStateOf(charInstance.magic.pointsInLightBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Dark,
        charInstance.magic.darkBook.fullBook,
        remember{mutableStateOf(charInstance.magic.pointsInDarkBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Creation,
        charInstance.magic.creationBook.fullBook,
        remember{mutableStateOf(charInstance.magic.pointsInCreateBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Destruction,
        charInstance.magic.destructionBook.fullBook,
        remember{mutableStateOf(charInstance.magic.pointsInDestructBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Air,
        charInstance.magic.airBook.fullBook,
        remember{mutableStateOf(charInstance.magic.pointsInAirBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Earth,
        charInstance.magic.earthBook.fullBook,
        remember{mutableStateOf(charInstance.magic.pointsInEarthBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Water,
        charInstance.magic.waterBook.fullBook,
        remember{mutableStateOf(charInstance.magic.pointsInWaterBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Fire,
        charInstance.magic.fireBook.fullBook,
        remember{mutableStateOf(charInstance.magic.pointsInFireBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Essence,
        charInstance.magic.essenceBook.fullBook,
        remember{mutableStateOf(charInstance.magic.pointsInEssenceBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Illusion,
        charInstance.magic.illusionBook.fullBook,
        remember{mutableStateOf(charInstance.magic.pointsInIllusionBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Necromancy,
        charInstance.magic.necromancyBook.fullBook,
        remember{mutableStateOf(charInstance.magic.pointsInNecroBook.toString())}
    ))

    LazyColumn{
        item{Text(text = "Zeon Points:")}
        item {
            Row {
                Text(text = "Base", modifier = Modifier.weight(0.25f))
                Text(text = "Bought", modifier = Modifier.weight(0.25f))
                Text(text = "Class", modifier = Modifier.weight(0.25f))
                Text(text = "Total", modifier = Modifier.weight(0.25f))
            }
        }
        item {
            Row {
                Text(
                    text = charInstance.magic.baseZeon.toString(),
                    modifier = Modifier.weight(0.25f)
                )
                TextField(
                    value = boughtZeonString.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        numberCatcher(
                            it,
                            { input ->
                                charInstance.magic.buyZeon(input.toInt())
                                boughtZeonString.value = input
                                maxZeonString.value = charInstance.magic.zeonMax.toString()
                                updateFunc()
                            },
                            {
                                charInstance.magic.buyZeon(0)
                                boughtZeonString.value = ""
                                maxZeonString.value = charInstance.magic.zeonMax.toString()
                                updateFunc()
                            }
                        )
                    }, modifier = Modifier.weight(0.15f)
                )
                Text(text = "x 5", modifier = Modifier.weight(0.1f))
                Text(
                    text = (charInstance.lvl * charInstance.ownClass.zeonPerLevel).toString(),
                    modifier = Modifier.weight(0.25f)
                )
                Text(text = maxZeonString.value, modifier = Modifier.weight(0.25f))
            }
        }

        item{Text(text = "Zeon Accumulation")}
        item {
            Row {
                Text(text = "Base")
                Text(text = "Bought")
                Text(text = "Total")
            }
        }
        item {
            Row {
                Text(text = charInstance.magic.baseZeonAcc.toString())
                TextField(
                    value = boughtAccString.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        numberCatcher(it,
                            { input ->
                                if (input.toInt() >= 1) {
                                    charInstance.magic.buyZeonAcc(input.toInt())
                                    boughtAccString.value = input
                                    totalAccString.value =
                                        charInstance.magic.zeonAccTotal.toString()
                                    updateFunc()
                                }
                            },
                            {
                                charInstance.magic.buyZeonAcc(1)
                                boughtAccString.value = ""
                                totalAccString.value = charInstance.magic.zeonAccTotal.toString()
                                updateFunc()
                            }
                        )
                    }
                )
                Text(text = totalAccString.value)
            }
        }

        item{Text(text = "Magic Projection")}
        item {
            Row {
                Text(text = "Base")
                Text(text = "Bought")
                Text(text = "Total")
            }
        }
        item {
            Row {
                Text(text = charInstance.modDEX.toString())
                TextField(
                    value = boughtProjString.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        numberCatcher(
                            it,
                            { input ->
                                charInstance.magic.buyMagProj(input.toInt())
                                boughtProjString.value = input
                                totalMagProjectString.value =
                                    charInstance.magic.magProjTotal.toString()
                                offenseImbalance.value =
                                    determineImbalanceValue(imbalanceIsAttack.value).toString()
                                defenseImbalance.value =
                                    determineImbalanceValue(!imbalanceIsAttack.value).toString()
                                updateFunc()
                            },
                            {
                                charInstance.magic.buyMagProj(0)
                                boughtProjString.value = ""
                                totalMagProjectString.value =
                                    charInstance.magic.magProjTotal.toString()
                                offenseImbalance.value =
                                    determineImbalanceValue(imbalanceIsAttack.value).toString()
                                defenseImbalance.value =
                                    determineImbalanceValue(!imbalanceIsAttack.value).toString()
                                updateFunc()
                            }
                        )
                    }
                )
                Text(text = totalMagProjectString.value)
            }
        }

        item {
            Row {
                Text(text = "Projection Imbalance: ")
                TextField(
                    value = projectionImbalance.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        numberCatcher(
                            it,
                            { input ->
                                if (input.toInt() in 0..30) {
                                    charInstance.magic.magProjImbalance = input.toInt()
                                    projectionImbalance.value = input
                                    offenseImbalance.value =
                                        determineImbalanceValue(imbalanceIsAttack.value).toString()
                                    defenseImbalance.value =
                                        determineImbalanceValue(!imbalanceIsAttack.value).toString()
                                }
                            },
                            {
                                charInstance.magic.magProjImbalance = 0
                                projectionImbalance.value = ""
                                offenseImbalance.value =
                                    determineImbalanceValue(imbalanceIsAttack.value).toString()
                                defenseImbalance.value =
                                    determineImbalanceValue(!imbalanceIsAttack.value).toString()
                            }
                        )
                    }
                )
            }
        }

        item {
            Row {
                Button(onClick = {
                    imbalanceIsAttack.value = !imbalanceIsAttack.value
                    charInstance.magic.imbalanceIsAttack = !charInstance.magic.imbalanceIsAttack
                    offenseImbalance.value =
                        determineImbalanceValue(imbalanceIsAttack.value).toString()
                    defenseImbalance.value =
                        determineImbalanceValue(!imbalanceIsAttack.value).toString()
                }) {
                    Text(text = imbalanceTypeString.value)
                }
                Column {
                    Text(text = "Offense: " + offenseImbalance.value)
                    Text(text = "Defense: " + defenseImbalance.value)
                }
            }
        }

        item{Text(text = "Magic Levels: " + magicLevelMax.value)}
        item{Text(text = "Magic Levels Spent: " + magicLevelSpent.value)}

        items(spellTable){
            SpellBookInvestment(
                it,
                primaryElementBoxes,
                {magicLevelSpent.value = charInstance.magic.magicLevelSpent.toString()},
                {element: Element, box: MutableState<Boolean> ->
                    primaryElementBoxes += Pair(element, box)
                }
            )
        }
    }
}

@Composable
private fun SpellBookInvestment(
    spellData: SpellRowData,
    allElementList: MutableMap<Element, MutableState<Boolean>>,
    updateMgLvlSpent: () -> Unit,
    addElementBox: (Element, MutableState<Boolean>) -> Unit
){
    val displayActive = remember{mutableStateOf(false)}

    val isPrimaryElement = remember{mutableStateOf(
        charInstance.magic.primarySpellList.contains(spellData.spellElement))}
    addElementBox(spellData.spellElement, isPrimaryElement)

    Row{
        Checkbox(
            checked = isPrimaryElement.value,
            onCheckedChange = {
                charInstance.magic.changePrimaryBook(spellData.spellElement, it)
                reflectPrimaryElements(allElementList)
            }
        )

        Text(text = spellData.spellElement.name)

        TextField(
            value = spellData.elementInvestment.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                numberCatcher(
                    it,
                    {input ->
                        charInstance.magic.buyBookLevels(input.toInt(), spellData.spellElement)
                        updateMgLvlSpent()
                        spellData.elementInvestment.value = input
                    },
                    {
                        charInstance.magic.buyBookLevels(0, spellData.spellElement)
                        updateMgLvlSpent()
                        spellData.elementInvestment.value = ""
                    }
                )

                reflectPrimaryElements(allElementList)
            }
        )
        //Button(onClick = {displayActive.value = !displayActive.value}) {
        //    Text(text = "Show Spells")
        //}
    }
    AnimatedVisibility(visible = displayActive.value) {
        Column {
            spellData.spellList.forEach {
                if (it != null) {
                    SpellRow()
                } else {
                    FreeSpellRow()
                }
            }
        }
    }
}

@Composable
private fun SpellRow(){
    Row{

    }
}

@Composable
private fun FreeSpellRow(){

}

private fun determineImbalanceValue(
    additionMade: Boolean
): Int{
    return if(additionMade)
        charInstance.magic.magProjTotal + charInstance.magic.magProjImbalance
    else
        charInstance.magic.magProjTotal - charInstance.magic.magProjImbalance
}

private fun reflectPrimaryElements(allElementList: MutableMap<Element, MutableState<Boolean>>){
    allElementList.forEach{
        if(charInstance.magic.primarySpellList.contains(it.key) != it.value.value)
            it.value.value = !it.value.value
    }
}

private data class SpellRowData(
    val spellElement: Element,
    val spellList: List<Spell?>,
    val elementInvestment: MutableState<String>
)