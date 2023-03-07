package com.example.animabuilder.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.animabuilder.DetailButton
import com.example.animabuilder.NumberInput
import com.example.animabuilder.activities.fragments.dialogs.FreeSpellPick
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.Magic
import com.example.animabuilder.character_creation.attributes.magic.spells.FreeSpell
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.primary_abilities.PrimaryCharacteristic

@Composable
fun MagicFragment(
    magic: Magic,
    dex: PrimaryCharacteristic,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
) {
    val context = LocalContext.current

    //initialize strings for maximum Zeon purchase and total
    val boughtZeonString = remember{mutableStateOf(magic.boughtZeon.toString())}
    val maxZeonString = remember{mutableStateOf(magic.zeonMax.toString())}

    //initialize strings for Zeon Accumulation purchase and total
    val boughtAccString = remember{mutableStateOf(magic.zeonAccMult.toString())}
    val totalAccString = remember{mutableStateOf(magic.zeonAccTotal.toString())}

    val zeonRecoverText = remember{mutableStateOf(magic.magicRecoveryTotal.toString())}

    //initialize strings for Magic Projection purchase and total
    val boughtProjString = remember{mutableStateOf(magic.boughtMagProjection.toString())}
    val totalMagProjectString = remember{mutableStateOf(magic.magProjTotal.toString())}

    //initialize states for Magic Projection imbalance
    val projectionImbalance = remember{mutableStateOf(magic.magProjImbalance.toString())}
    val imbalanceIsAttack = remember{mutableStateOf(magic.imbalanceIsAttack)}
    val imbalanceTypeString = remember{mutableStateOf(
        if(imbalanceIsAttack.value)
            "Attack"
        else
            "Defense"
    )}
    val offenseImbalance = remember{mutableStateOf(determineImbalanceValue(
        magic,
        imbalanceIsAttack.value
    ).toString())}
    val defenseImbalance = remember{mutableStateOf(determineImbalanceValue(
        magic,
        !imbalanceIsAttack.value
    ).toString())}

    //initialize maximum and spent values for magic level
    val magicLevelMax = remember{mutableStateOf(magic.magicLevelMax.toString())}
    val magicLevelSpent = remember{mutableStateOf(magic.magicLevelSpent.toString())}

    //initialize list of character's primary spell elements
    val primaryElementBoxes = mutableMapOf<Element, MutableState<Boolean>>()

    //initialize state copy of character's spell list
    val spellList = remember{magic.spellList.toMutableStateList()}

    //initialize values for the free exchange dialog
    val freeExchangeOpen = remember{mutableStateOf(false)}
    val freeElement = remember{mutableStateOf(Element.Light)}
    val freeLevel = remember{mutableStateOf(4)}
    val textChange = remember{mutableStateOf<(String) -> Unit>({})}

    //initialize and fill zeon purchase table data
    val zeonPurchaseItemTable = mutableListOf<ZeonPurchaseItemData>()

    zeonPurchaseItemTable.add(ZeonPurchaseItemData(
        "Zeon Accumulation",
        magic.baseZeonAcc.toString(),
        boughtAccString,
        totalAccString,
        { input ->
            if (input.toInt() >= 1) {
                magic.buyZeonAcc(input.toInt())
                boughtAccString.value = input
                totalAccString.value =
                    magic.zeonAccTotal.toString()
                zeonRecoverText.value = magic.magicRecoveryTotal.toString()
                updateFunc()
            }
        },
        {
            magic.buyZeonAcc(1)
            boughtAccString.value = ""
            totalAccString.value = magic.zeonAccTotal.toString()
            zeonRecoverText.value = magic.magicRecoveryTotal.toString()
            updateFunc()
        }
    ))

    zeonPurchaseItemTable.add(ZeonPurchaseItemData(
        "Magic Projection",
        dex.outputMod.toString(),
        boughtProjString,
        totalMagProjectString,
        { input ->
            magic.buyMagProj(input.toInt())
            boughtProjString.value = input
            totalMagProjectString.value =
                magic.magProjTotal.toString()
            offenseImbalance.value =
                determineImbalanceValue(magic, imbalanceIsAttack.value).toString()
            defenseImbalance.value =
                determineImbalanceValue(magic, !imbalanceIsAttack.value).toString()
            updateFunc()
        },
        {
            magic.buyMagProj(0)
            boughtProjString.value = ""
            totalMagProjectString.value =
                magic.magProjTotal.toString()
            offenseImbalance.value =
                determineImbalanceValue(magic, imbalanceIsAttack.value).toString()
            defenseImbalance.value =
                determineImbalanceValue(magic, !imbalanceIsAttack.value).toString()
            updateFunc()
        }
    ))

    //initialize and fill spell table data
    val spellTable = mutableListOf<SpellRowData>()

    spellTable.add(SpellRowData(
        Element.Light,
        magic.lightBook.fullBook,
        remember{mutableStateOf(magic.pointsInLightBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Dark,
        magic.darkBook.fullBook,
        remember{mutableStateOf(magic.pointsInDarkBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Creation,
        magic.creationBook.fullBook,
        remember{mutableStateOf(magic.pointsInCreateBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Destruction,
        magic.destructionBook.fullBook,
        remember{mutableStateOf(magic.pointsInDestructBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Air,
        magic.airBook.fullBook,
        remember{mutableStateOf(magic.pointsInAirBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Earth,
        magic.earthBook.fullBook,
        remember{mutableStateOf(magic.pointsInEarthBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Water,
        magic.waterBook.fullBook,
        remember{mutableStateOf(magic.pointsInWaterBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Fire,
        magic.fireBook.fullBook,
        remember{mutableStateOf(magic.pointsInFireBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Essence,
        magic.essenceBook.fullBook,
        remember{mutableStateOf(magic.pointsInEssenceBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Illusion,
        magic.illusionBook.fullBook,
        remember{mutableStateOf(magic.pointsInIllusionBook.toString())}
    ))
    spellTable.add(SpellRowData(
        Element.Necromancy,
        magic.necromancyBook.fullBook,
        remember{mutableStateOf(magic.pointsInNecroBook.toString())}
    ))





    //start page content
    LazyColumn{

        //header for zeon point maximums
        item{Text(text = "Zeon Points:")}
        item {
            Row {
                Text(text = "Base", modifier = Modifier.weight(0.25f))
                Text(text = "Bought", modifier = Modifier.weight(0.25f))
                Text(text = "Class", modifier = Modifier.weight(0.25f))
                Text(text = "Total", modifier = Modifier.weight(0.25f))
            }
        }

        //zeon point maximum row
        item {
            Row {
                //display base maximum Zeon
                Text(
                    text = magic.baseZeon.toString(),
                    modifier = Modifier.weight(0.25f)
                )

                //Zeon point purchase input
                NumberInput(
                    boughtZeonString.value,
                    {},
                    { input ->
                        //take inputted number and buy that many Zeon points
                        magic.buyZeon(input.toInt())
                        boughtZeonString.value = input
                    },
                    {
                        //clear bought Zeon amount
                        magic.buyZeon(0)
                        boughtZeonString.value = ""
                    },
                    {
                        maxZeonString.value = magic.zeonMax.toString()
                        updateFunc()
                    },
                    Color.Black,
                    Modifier.weight(0.25f)
                )

                Text(text = "x 5", modifier = Modifier.weight(0.1f))

                //display zeon points from class levels
                Text(
                    text = magic.zeonFromClass.toString(),
                    modifier = Modifier.weight(0.25f)
                )

                //display final total
                Text(text = maxZeonString.value, modifier = Modifier.weight(0.25f))
            }
        }

        item{Text(text = "Zeon Recovery: " + zeonRecoverText.value)}

        //display zeon accumulation and magic projection tables
        items(zeonPurchaseItemTable){
            ZeonPurchaseItem(it)
        }

        //projection imbalance section
        item {
            Row {
                //title for section
                Text(text = "Projection Imbalance: ")

                //input to change imbalance
                NumberInput(
                    projectionImbalance.value,
                    {},
                    { input ->
                        //if imbalance is a legal input
                        if (input.toInt() in 0..30) {
                            //set magic imbalance to input
                            magic.magProjImbalance = input.toInt()
                            projectionImbalance.value = input
                        }
                    },
                    {
                        //default imbalance to 0
                        magic.magProjImbalance = 0
                        projectionImbalance.value = ""
                    },
                    {
                        offenseImbalance.value =
                            determineImbalanceValue(magic, imbalanceIsAttack.value).toString()
                        defenseImbalance.value =
                            determineImbalanceValue(magic, !imbalanceIsAttack.value).toString()
                    },
                    Color.Black,
                    Modifier
                )
            }
        }

        //item to switch imbalance preference
        item {
            Row {
                Button(onClick = {
                    //switch imbalance preference
                    imbalanceIsAttack.value = !imbalanceIsAttack.value
                    magic.imbalanceIsAttack = !magic.imbalanceIsAttack
                    offenseImbalance.value =
                        determineImbalanceValue(magic, imbalanceIsAttack.value).toString()
                    defenseImbalance.value =
                        determineImbalanceValue(magic, !imbalanceIsAttack.value).toString()

                    imbalanceTypeString.value = if(imbalanceIsAttack.value) "Attack" else "Defense"
                }) {
                    //display current imbalance preference
                    Text(text = imbalanceTypeString.value)
                }
                //display imbalanced projection values
                Column {
                    Text(text = "Offense: " + offenseImbalance.value)
                    Text(text = "Defense: " + defenseImbalance.value)
                }
            }
        }

        //display magic levels available and magic levels spent
        item{Text(text = "Magic Levels: " + magicLevelMax.value)}
        item{Text(text = "Magic Levels Spent: " + magicLevelSpent.value)}

        //display each book investment row and spell displays
        items(spellTable){
            SpellBookInvestment(
                magic,
                it,
                primaryElementBoxes,
                {magicLevelSpent.value = magic.magicLevelSpent.toString()},
                {spellList.clear(); spellList.addAll(magic.spellList.toList())},
                openDetailAlert
            ) { element: Element, box: MutableState<Boolean> ->
                primaryElementBoxes += Pair(element, box)
            }
        }

        //display all currently taken spells
        item{Text(text = "Current Taken Spells: ")}
        items(spellList){
            //display free spell exchange if it's a free spell
            if(it is FreeSpell)
                FreeSpellExchange(
                    it,
                    magic.findFreeSpellElement(it),
                    it.level,
                    {input: Element -> freeElement.value = input},
                    {input: Int -> freeLevel.value = input},
                    {input: (String) -> Unit -> textChange.value = input},
                    openDetailAlert
                ) {
                    if(magic.magicTies)
                        Toast.makeText(context, "Magic Ties prevents getting Free Spells", Toast.LENGTH_LONG).show()
                    else
                        freeExchangeOpen.value = true
                }
            else
                SpellRow(
                    magic,
                    it,
                    false,
                    openDetailAlert
                ) { spellList.clear(); spellList.addAll(magic.spellList.toList()) }
        }
    }

    //show free spell dialog if displayed
    if(freeExchangeOpen.value)
        FreeSpellPick(
            magic,
            freeElement.value,
            freeLevel.value,
            textChange.value,
            SpellDetails,
            openDetailAlert
        )
        {spellList.clear(); spellList.addAll(magic.spellList); freeExchangeOpen.value = false}
}

/**
 * Creates a table for inputs of the particular stat
 *
 * tableItem: data for the displayed table
 */
@Composable
private fun ZeonPurchaseItem(tableItem: ZeonPurchaseItemData){
    Column{
        //display table title
        Row{Text(text = tableItem.sectionTitle)}

        //display table header
        Row{
            Text(text = "Base")
            Text(text = "Bought")
            Text(text = "Total")
        }

        //display table items
        Row{
            //display base value
            Text(text = tableItem.baseDisplay)

            //input for user to purchase these points
            NumberInput(
                tableItem.boughtValue.value,
                {},
                tableItem.functionInput,
                tableItem.functionZero,
                {},
                Color.Black,
                Modifier
            )

            //display final total
            Text(text = tableItem.totalDisplay.value)
        }
    }
}

/**
 * Creates a  section in the app to acquire spells through purchasing book levels or individually
 *
 * spellData: SpellRowData for the individual element
 * allElementList: master list of checkboxes for primary elements
 * updateMgLvlSpent: function to update magic level spent
 * updateSpellList: function to update the page's master spell list
 * addElementBox: puts this item's element box into the master checkbox list
 */
@Composable
private fun SpellBookInvestment(
    magic: Magic,
    spellData: SpellRowData,
    allElementList: MutableMap<Element, MutableState<Boolean>>,
    updateMgLvlSpent: () -> Unit,
    updateSpellList: () -> Unit,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    addElementBox: (Element, MutableState<Boolean>) -> Unit
){
    //initialize spell list display boolean
    val displayActive = remember{mutableStateOf(false)}

    //initialize whether spell is a primary book or not
    val isPrimaryElement = remember{mutableStateOf(
        magic.primaryElementList.contains(spellData.spellElement))}

    //add mutable boolean to master list
    addElementBox(spellData.spellElement, isPrimaryElement)

    Column {
        Row {
            //primary element checkbox
            Checkbox(
                checked = isPrimaryElement.value,
                onCheckedChange = {
                    //change primary element in character
                    magic.changePrimaryBook(spellData.spellElement, it)

                    //change checkbox booleans to reflect the change
                    reflectPrimaryElements(magic, allElementList)

                    //update any potentially changed values
                    updateMgLvlSpent()
                    updateSpellList()
                },
                modifier = Modifier.weight(0.1f)
            )

            //display associated element
            Text(text = spellData.spellElement.name, modifier = Modifier.weight(0.3f))

            //display book investment value
            NumberInput(
                spellData.elementInvestment.value,
                { displayActive.value = false },
                { input ->
                    //buy levels in the associated book
                    magic.buyBookLevels(input.toInt(), spellData.spellElement)
                    spellData.elementInvestment.value = input
                },
                {
                    //default purchase to 0
                    magic.buyBookLevels(0, spellData.spellElement)
                    spellData.elementInvestment.value = ""
                },
                {
                    updateMgLvlSpent()

                    //reflect any primary book change
                    reflectPrimaryElements(magic, allElementList)

                    //update spells taken
                    updateSpellList()
                },
                Color.Black,
                Modifier
            )

            //spell display button
            Button(
                onClick = { displayActive.value = !displayActive.value },
                modifier = Modifier.weight(0.3f)
            ) {
                Text(text = if(displayActive.value) "Hide Spells" else "Show Spells")
            }
        }

        //display of spells
        AnimatedVisibility(visible = displayActive.value) {
            Column {
                //initialize free spell level indicator
                var freeSpellLevel = 0

                //for each of the element's spells
                spellData.spellList.forEach {
                    //display the given spell if one is given
                    if (it != null) {
                        SpellRow(
                            magic,
                            it,
                            true,
                            openDetailAlert
                        ) { reflectPrimaryElements(magic, allElementList); updateMgLvlSpent(); updateSpellList() }

                        //increment free spell level
                        freeSpellLevel = it.level + 2
                    }

                    //display free spell row if no spell given
                    else
                        FreeSpellRow(magic, freeSpellLevel, spellData.spellElement)
                        { reflectPrimaryElements(magic, allElementList); updateMgLvlSpent(); updateSpellList() }
                }
            }
        }
    }
}

/**
 * Row that displays the inputted spell
 *
 * displayItem: spell to display
 * buyable: whether the row is a purchasable individual spell or just a display
 * updateList: function to run on a button change
 */
@Composable
private fun SpellRow(
    magic: Magic,
    displayItem: Spell,
    buyable: Boolean,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateList: () -> Unit
){
    Row{
        //make purchase button if buyable row
        if(buyable) BuySingleSpellButton(magic, displayItem, updateList)

        //display spell name
        Text(text = displayItem.name)

        //create display button
        DetailButton(
            onClick = {openDetailAlert(displayItem.name) @Composable{SpellDetails(displayItem)}},
            modifier = Modifier
        )
    }
}

/**
 * Row to display a free spell in the book column
 *
 * lvlVal: level of the free spell
 * eleVal: element of the free spell
 * updateList: function to run on free spell purchase
 */
@Composable
private fun FreeSpellRow(
    magic: Magic,
    lvlVal: Int,
    eleVal: Element,
    updateList: () -> Unit
){
    Row{
        //button to buy free spell individually
        BuySingleFreeSpellButton(magic, lvlVal, eleVal, updateList)

        //display free spell values
        Text(text = "Free Spell (Lvl $lvlVal)")
    }
}

/**
 * Button to purchase a spell individually
 *
 * inputSpell: spell to be purchased
 * updateList: function to run after spell purchase
 */
@Composable
private fun BuySingleSpellButton(
    magic: Magic,
    inputSpell: Spell,
    updateList: () -> Unit
){
    //whether spell is individually acquired by the character
    val isBought = remember{mutableStateOf(magic.individualSpells.contains(inputSpell))}
    Button(
        onClick = {
            //attempt to purchase the spell
            isBought.value = magic.changeIndividualSpell(inputSpell, !isBought.value)
            updateList()
        }
    ){
        Text(text = if(isBought.value) "Remove Spell" else "Buy Spell")
    }
}

/**
 * Button to purchase an individual free spell
 *
 * spellLevel: level of the free spell
 * spellElement: element associated with the free spell
 * updateList: function to run after spell purchase
 */
@Composable
private fun BuySingleFreeSpellButton(
    magic: Magic,
    spellLevel: Int,
    spellElement: Element,
    updateList: () -> Unit
){
    //determine if character has equivalent free spell taken
    val isBought = remember{mutableStateOf(magic.hasCopyOf(magic.getFreeSpell(spellLevel, spellElement)))}

    Button(
        onClick = {
            //attempt to acquire free spell slot
            isBought.value = magic.changeIndividualFreeSpell(spellLevel, spellElement, !isBought.value)
            updateList()
        }
    ){
        Text(text = if(isBought.value) "Remove Spell" else "Buy Spell")
    }
}

/**
 * Row to change the spell in the character's free spell slot
 *
 * currentFreeSpell: free spell currently in the slot
 * associatedElement: book the slot comes from
 * associatedLevel: level of the free spell slot
 * setFreeElement: set dialog's used element
 * setFreeLevel: set dialog's used level
 * setTextChanged: set change function for dialog
 * openChoice: function to open free spell dialog
 */
@Composable
private fun FreeSpellExchange(
    currentFreeSpell: FreeSpell,
    associatedElement: Element,
    associatedLevel: Int,
    setFreeElement: (Element) -> Unit,
    setFreeLevel: (Int) -> Unit,
    setTextChange: ((String) -> Unit) -> Unit,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    openChoice: () -> Unit
){
    //set spell name if one given
    val spellName = remember{mutableStateOf(
        if(currentFreeSpell.name != "PlaceHolder") currentFreeSpell.name
        else "Empty Free Slot"
    )}

    Row{
        //display slot's current spell
        Text(text = spellName.value)

        //display slot's level and element
        Text(text = "(" + associatedElement.name + " Lvl $associatedLevel)")

        //show nothing here if no spell found
        if(currentFreeSpell.name == "PlaceHolder"){Spacer(Modifier.weight(0.1f))}

        //show detail button if spell found
        else
            DetailButton(
                onClick = {openDetailAlert(currentFreeSpell.name)
                @Composable {SpellDetails(currentFreeSpell)}},
                modifier = Modifier
            )

        //button that opens free spell exchange dialog
        Button(
            onClick = {
                //set dialog elements and open
                setFreeElement(associatedElement)
                setFreeLevel(associatedLevel)
                setTextChange { input -> spellName.value = input }
                openChoice()
            }
        )
        {Text(text = "Change Free Spell")}
    }
}

//contents of the spell's detail dialog
val SpellDetails = @Composable {spell: Spell ->
    //get active or passive text
    val action =
        if(spell.isActive)
            "Active"
        else
            "Passive"

    //get daily text if able
    val daily =
        if(spell.isDaily)
            " (Daily)"
        else
            ""

    //create string of the spell's categories
    val spellType = remember{mutableStateOf("")}
    spell.type.forEach{
        spellType.value += it.name + " "
    }

    //create string of forbidden elements if this is a free spell
    var forbiddenList = ""
    if(spell is FreeSpell){
        spell.forbiddenElements.forEach {
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

/**
 * Calculates the magic projection after taking into account the character's imbalance
 *
 * additionMade: whether imbalance favors the stat or not
 */
private fun determineImbalanceValue(
    magic: Magic,
    additionMade: Boolean
): Int{
    return if(additionMade)
        magic.magProjTotal + magic.magProjImbalance
    else
        magic.magProjTotal - magic.magProjImbalance
}

/**
 * Sets the displayed primary elements to match the character's primary elements
 *
 * allElementList: master list of primary element checkboxes
 */
private fun reflectPrimaryElements(
    magic: Magic,
    allElementList: MutableMap<Element, MutableState<Boolean>>
){
    //change checkbox value if it does not match character's value
    allElementList.forEach{
        if(magic.primaryElementList.contains(it.key) != it.value.value)
            it.value.value = !it.value.value
    }
}

/**
 * Data class for stat purchase table
 *
 * sectionTitle: name of the table
 * baseDisplay: base stat of the given table
 * boughtValue: purchase value of the stat
 * totalDisplay: total value of the stat
 * functionInput: function to run for user purchase
 * functionZero: function to run if input is empty
 */
private data class ZeonPurchaseItemData(
    val sectionTitle: String,
    val baseDisplay: String,
    val boughtValue: MutableState<String>,
    val totalDisplay: MutableState<String>,
    val functionInput: (String) -> Unit,
    val functionZero: () -> Unit
)

/**
 * Data class for spellbook data
 *
 * spellElement: element of the book displayed
 * spellList: list of spells to display
 * elementInvestment: amount of magical knowledge spent in this book
 */
private data class SpellRowData(
    val spellElement: Element,
    val spellList: List<Spell?>,
    val elementInvestment: MutableState<String>
)