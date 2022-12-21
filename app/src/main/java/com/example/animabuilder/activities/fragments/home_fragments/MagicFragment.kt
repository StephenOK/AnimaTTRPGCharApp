package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.animabuilder.activities.*
import com.example.animabuilder.activities.fragments.dialogs.FreeSpellPick
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.FreeSpell
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
    val imbalanceTypeString = remember{mutableStateOf(
        if(imbalanceIsAttack.value)
            "Attack"
        else
            "Defense"
    )}

    val offenseImbalance = remember{mutableStateOf(determineImbalanceValue(imbalanceIsAttack.value).toString())}
    val defenseImbalance = remember{mutableStateOf(determineImbalanceValue(!imbalanceIsAttack.value).toString())}

    val magicLevelMax = remember{mutableStateOf(charInstance.magic.magicLevelMax.toString())}
    val magicLevelSpent = remember{mutableStateOf(charInstance.magic.magicLevelSpent.toString())}

    val primaryElementBoxes = mutableMapOf<Element, MutableState<Boolean>>()

    val spellTable = mutableListOf<SpellRowData>()

    val spellList = remember{charInstance.magic.spellList.toMutableStateList()}

    val freeExchangeOpen = remember{mutableStateOf(false)}
    val freeElement = remember{mutableStateOf(Element.Light)}
    val freeLevel = remember{mutableStateOf(4)}
    val freeIndex = remember{mutableStateOf(1)}

    val textChange = remember{mutableStateOf<(String) -> Unit>({})}

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
                {spellList.clear(); spellList.addAll(charInstance.magic.spellList.toList())},
                {element: Element, box: MutableState<Boolean> ->
                    primaryElementBoxes += Pair(element, box)
                }
            )
        }

        item{Text(text = "Current Taken Spells: ")}
        items(spellList){
            if(it is FreeSpell)
                FreeSpellExchange(
                    it,
                    charInstance.magic.findFreeSpellElement(it),
                    it.level,
                    {input: Element -> freeElement.value = input},
                    {input: Int -> freeLevel.value = input},
                    {input: Int -> freeIndex.value = input},
                    {input: (String) -> Unit -> textChange.value = input}
                ) { freeExchangeOpen.value = true }
            else
                SpellRow(
                    it,
                    false
                ) { spellList.clear(); spellList.addAll(charInstance.magic.spellList.toList()) }
        }
    }

    if(freeExchangeOpen.value)
        FreeSpellPick(freeElement.value, freeLevel.value, textChange.value)
        {spellList.clear(); spellList.addAll(charInstance.magic.spellList); freeExchangeOpen.value = false}
}

@Composable
private fun SpellBookInvestment(
    spellData: SpellRowData,
    allElementList: MutableMap<Element, MutableState<Boolean>>,
    updateMgLvlSpent: () -> Unit,
    updateSpellList: () -> Unit,
    addElementBox: (Element, MutableState<Boolean>) -> Unit
){
    val displayActive = remember{mutableStateOf(false)}

    val isPrimaryElement = remember{mutableStateOf(
        charInstance.magic.primarySpellList.contains(spellData.spellElement))}
    addElementBox(spellData.spellElement, isPrimaryElement)

    Column {
        Row {
            Checkbox(
                checked = isPrimaryElement.value,
                onCheckedChange = {
                    charInstance.magic.changePrimaryBook(spellData.spellElement, it)
                    reflectPrimaryElements(allElementList)
                    updateMgLvlSpent()
                    updateSpellList()
                },
                modifier = Modifier.weight(0.1f)
            )

            Text(text = spellData.spellElement.name, modifier = Modifier.weight(0.3f))

            TextField(
                value = spellData.elementInvestment.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    displayActive.value = false
                    numberCatcher(
                        it,
                        { input ->
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
                    updateSpellList()
                },
                modifier = Modifier.weight(0.2f)
            )
            Button(
                onClick = { displayActive.value = !displayActive.value },
                modifier = Modifier.weight(0.3f)
            ) {
                Text(text = if(displayActive.value) "Hide Spells" else "Show Spells")
            }
        }
        AnimatedVisibility(visible = displayActive.value) {
            Column {
                var freeSpellLevel = 0
                spellData.spellList.forEach {
                    if (it != null) {
                        SpellRow(
                            it,
                            true
                        ) { reflectPrimaryElements(allElementList); updateMgLvlSpent(); updateSpellList() }
                        freeSpellLevel = it.level + 2
                    } else {
                        FreeSpellRow(freeSpellLevel, spellData.spellElement)
                        { reflectPrimaryElements(allElementList); updateMgLvlSpent(); updateSpellList() }
                    }
                }
            }
        }
    }
}

@Composable
private fun SpellRow(displayItem: Spell, buyable: Boolean, updateList: () -> Unit){
    Row{
        if(buyable) BuySingleSpellButton(displayItem, updateList)
        Text(text = displayItem.name)
        TextButton(
            onClick ={
                detailItem.value = displayItem.name
                contents.value = @Composable{SpellDetails(displayItem)}
                detailAlertOn.value = true
            }
        ) {
            Text(text = "Details")
        }
    }
}

@Composable
private fun FreeSpellRow(lvlVal: Int, eleVal: Element, updateList: () -> Unit){
    Row{
        BuySingleFreeSpellButton(lvlVal, eleVal, updateList)
        Text(text = "Free Spell (Lvl $lvlVal)")
    }
}

@Composable
private fun BuySingleSpellButton(inputSpell: Spell, updateList: () -> Unit){
    val isBought = remember{mutableStateOf(charInstance.magic.individualSpells.contains(inputSpell))}
    Button(
        onClick = {
            isBought.value = charInstance.magic.changeIndividualSpell(inputSpell, !isBought.value)
            updateList()
        }
    ){
        Text(text = if(isBought.value) "Remove Spell" else "Buy Spell")
    }
}

@Composable
private fun BuySingleFreeSpellButton(spellLevel: Int, spellElement: Element, updateList: () -> Unit){
    val isBought = remember{mutableStateOf(false)}
    Button(
        onClick = {
            isBought.value = charInstance.magic.changeIndividualFreeSpell(spellLevel, spellElement, !isBought.value)
            updateList()
        }
    ){
        Text(text = if(isBought.value) "Remove Spell" else "Buy Spell")
    }
}

@Composable
private fun FreeSpellExchange(
    currentFreeSpell: FreeSpell,
    associatedElement: Element,
    associatedLevel: Int,
    setFreeElement: (Element) -> Unit,
    setFreeLevel: (Int) -> Unit,
    setFreeIndex: (Int) -> Unit,
    setTextChange: ((String) -> Unit) -> Unit,
    openChoice: () -> Unit
){
    val spellName = remember{mutableStateOf(
        if(currentFreeSpell.name != "PlaceHolder") currentFreeSpell.name
        else "Empty Free Slot"
    )}

    Row{
        Text(text = spellName.value)
        Text(text = "(" + associatedElement.name + " Lvl $associatedLevel)")
        Button(
            onClick = {
                setFreeElement(associatedElement)
                setFreeLevel(associatedLevel)
                setFreeIndex(charInstance.magic.spellList.indexOf(currentFreeSpell))
                setTextChange { input -> spellName.value = input }
                openChoice()
            }
        )
        {Text(text = "Change Free Spell")}
    }
}

@Composable
private fun SpellDetails(spell: Spell){
    val action =
        if(spell.isActive)
            "Active"
        else
            "Passive"
    val daily =
        if(spell.isDaily)
            " (Daily)"
        else
            ""

    val spellType = remember{mutableStateOf("")}
    spell.type.forEach{
        spellType.value += it.name + " "
    }

    var forbiddenList = ""
    if(spell is FreeSpell){
        (spell as FreeSpell).forbiddenElements.forEach {
            forbiddenList += it.name + " "
        }
    }

    Column{
        Row{Text(text = "Action: $action")}
        Row{Text(text = "Element: " + spell.inBook.name)}
        Row{Text(text = "Level: " + spell.level.toString())}
        Row{Text(text = "Zeon Cost: " + spell.zCost.toString())}
        Row{Text(text = spell.effect)}
        Row{Text(text = "Added Effect: " + spell.addedEffect)}
        Row{Text(text = "Maximum Zeon: Intelligence x " + spell.zMax)}
        if(spell.maintenance != null)
            Row{Text(text = "Maintenance: 1 every " + spell.maintenance + daily)}
        else
            Row{Text(text = "None")}
        Row{Text(text = "Type: " + spellType.value)}
        if(spell is FreeSpell)
            Row{Text(text = "Forbidden Elements: $forbiddenList")}
    }
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