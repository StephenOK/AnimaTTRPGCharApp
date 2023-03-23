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
import androidx.compose.ui.res.stringResource
import com.example.animabuilder.DetailButton
import com.example.animabuilder.NumberInput
import com.example.animabuilder.R
import com.example.animabuilder.activities.fragments.dialogs.FreeSpellPick
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.FreeSpell
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.view_models.MagicFragmentViewModel

@Composable
fun MagicFragment(
    magFragVM: MagicFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
) {
    val context = LocalContext.current

    //start page content
    LazyColumn{

        //header for zeon point maximums
        item{Text(text = stringResource(R.string.zeonPointLabel))}
        item {
            Row {
                Text(text = stringResource(R.string.baseLabel), modifier = Modifier.weight(0.25f))
                Text(text = stringResource(R.string.boughtLabel), modifier = Modifier.weight(0.25f))
                Text(text = stringResource(R.string.classLabel), modifier = Modifier.weight(0.25f))
                Text(text = stringResource(R.string.totalLabel), modifier = Modifier.weight(0.25f))
            }
        }

        //zeon point maximum row
        item {
            Row {
                //display base maximum Zeon
                Text(
                    text = magFragVM.getBaseZeon().toString(),
                    modifier = Modifier.weight(0.25f)
                )

                //Zeon point purchase input
                NumberInput(
                    magFragVM.boughtZeonString.collectAsState().value,
                    {},
                    {input -> magFragVM.setBoughtZeonString(input.toInt())},
                    {magFragVM.setBoughtZeonString("")},
                    {updateFunc()},
                    Color.Black,
                    Modifier.weight(0.25f)
                )

                Text(text = stringResource(R.string.zeonPointMultiplier), modifier = Modifier.weight(0.1f))

                //display zeon points from class levels
                Text(
                    text = magFragVM.getClassZeon().toString(),
                    modifier = Modifier.weight(0.25f)
                )

                //display final total
                Text(text = magFragVM.maxZeonString.collectAsState().value, modifier = Modifier.weight(0.25f))
            }
        }

        item{Text(text = stringResource(R.string.zeonRecoveryLabel) + magFragVM.zeonRecoveryString.collectAsState().value)}

        //display zeon accumulation and magic projection tables
        items(magFragVM.allPurchaseData){
            ZeonPurchaseItem(it, updateFunc)
        }

        //projection imbalance section
        item {
            Row {
                //title for section
                Text(text = stringResource(R.string.imbalanceLabel))

                //input to change imbalance
                NumberInput(
                    magFragVM.projectionImbalance.collectAsState().value,
                    {},
                    { input ->
                        //if imbalance is a legal input
                        if (input.toInt() in 0..30) {
                            magFragVM.setProjectionImbalance(input.toInt())
                        }
                    },
                    {magFragVM.setProjectionImbalance("")},
                    {},
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
                    magFragVM.setImbalanceIsAttack(magFragVM.imbalanceIsAttack.value)
                }) {
                    //display current imbalance preference
                    Text(text = magFragVM.imbalanceTypeString.collectAsState().value)
                }
                //display imbalanced projection values
                Column {
                    Text(text = stringResource(R.string.offenseLabel) + magFragVM.offenseImbalance.collectAsState().value)
                    Text(text = stringResource(R.string.defenseLabel) + magFragVM.defenseImbalance.collectAsState().value)
                }
            }
        }

        //display magic levels available and magic levels spent
        item{Text(text = stringResource(R.string.magicLevelLabel) + magFragVM.magicLevelMax.collectAsState().value)}
        item{Text(text = stringResource(R.string.magicLevelSpentLabel) + magFragVM.magicLevelSpent.collectAsState().value)}

        //display each book investment row and spell displays
        items(magFragVM.allBooks){
            SpellBookInvestment(
                magFragVM,
                it,
                openDetailAlert
            )
        }

        //display all currently taken spells
        item{Text(text = stringResource(R.string.spellsTakenLabel))}
        items(magFragVM.heldSpells){
            //display free spell exchange if it's a free spell
            if(it is FreeSpell)
                FreeSpellExchange(
                    magFragVM,
                    it,
                    magFragVM.getFreeElement(it),
                    it.level,
                    openDetailAlert
                ) {
                    if(magFragVM.getMagicTies())
                        Toast.makeText(context, "Magic Ties prevents getting Free Spells", Toast.LENGTH_LONG).show()
                    else
                        magFragVM.setFreeExchangeOpen(true)
                }
            else
                SpellRow(
                    magFragVM,
                    it,
                    false,
                    openDetailAlert
                ) {}
        }
    }

    //show free spell dialog if displayed
    if(magFragVM.freeExchangeOpen.collectAsState().value)
        FreeSpellPick(
            magFragVM,
            SpellDetails,
            openDetailAlert
        )
}

/**
 * Creates a table for inputs of the particular stat
 *
 * tableItem: data for the displayed table
 */
@Composable
private fun ZeonPurchaseItem(
    tableItem: MagicFragmentViewModel.ZeonPurchaseItemData,
    updateFunc: () -> Unit
){
    Column{
        //display table title
        Row{Text(text = stringResource(tableItem.nameRef))}

        //display table header
        Row{
            Text(text = stringResource(R.string.baseLabel))
            Text(text = stringResource(R.string.boughtLabel))
            Text(text = stringResource(R.string.totalLabel))
        }

        //display table items
        Row{
            //display base value
            Text(text = tableItem.baseInput.toString())

            //input for user to purchase these points
            NumberInput(
                tableItem.boughtString.collectAsState().value,
                {},
                {tableItem.setBoughtString(it.toInt())},
                {tableItem.setBoughtString("")},
                {updateFunc()},
                Color.Black,
                Modifier
            )

            //display final total
            Text(text = tableItem.totalString.collectAsState().value)
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
    magFragVM: MagicFragmentViewModel,
    spellData: MagicFragmentViewModel.SpellRowData,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
){
    magFragVM.addPrimaryElementBox(spellData.spellElement)

    Column {
        Row {
            //primary element checkbox
            Checkbox(
                checked = magFragVM.primaryElementBoxes[spellData.spellElement]!!.value,
                onCheckedChange = {magFragVM.changePrimaryBook(spellData.spellElement, it)},
                modifier = Modifier.weight(0.1f)
            )

            //display associated element
            Text(text = spellData.spellElement.name, modifier = Modifier.weight(0.3f))

            //display book investment value
            NumberInput(
                spellData.elementInvestment.collectAsState().value,
                {spellData.setListOpen(false)},
                {input -> spellData.setElementInvestment(input.toInt())},
                {spellData.setElementInvestment("")},
                {
                    magFragVM.setMagicLevelSpent()

                    //reflect any primary book change
                    magFragVM.reflectPrimaryElement()
                },
                Color.Black,
                Modifier
            )

            //spell display button
            Button(
                onClick = {spellData.setListOpen(!spellData.listOpen.value)},
                modifier = Modifier.weight(0.3f)
            ) {
                Text(
                    text = stringResource(
                        if(spellData.listOpen.collectAsState().value) R.string.spellsHidden
                        else R.string.spellsShown
                    )
                )
            }
        }

        //display of spells
        AnimatedVisibility(visible = spellData.listOpen.collectAsState().value) {
            Column {
                //initialize free spell level indicator
                var freeSpellLevel = 0

                //for each of the element's spells
                spellData.spellList.forEach {
                    //display the given spell if one is given
                    if (it != null) {
                        SpellRow(
                            magFragVM,
                            it,
                            true,
                            openDetailAlert
                        ) { magFragVM.reflectPrimaryElement(); magFragVM.setMagicLevelSpent()}

                        //increment free spell level
                        freeSpellLevel = it.level + 2
                    }

                    //display free spell row if no spell given
                    else
                        FreeSpellRow(magFragVM, freeSpellLevel, spellData.spellElement)
                        { magFragVM.reflectPrimaryElement(); magFragVM.setMagicLevelSpent()}
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
    magFragVM: MagicFragmentViewModel,
    displayItem: Spell,
    buyable: Boolean,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateList: () -> Unit
){
    Row{
        //make purchase button if buyable row
        if(buyable) BuySingleSpellButton(magFragVM, displayItem, updateList)

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
    magFragVM: MagicFragmentViewModel,
    lvlVal: Int,
    eleVal: Element,
    updateList: () -> Unit
){
    Row{
        //button to buy free spell individually
        BuySingleFreeSpellButton(magFragVM, lvlVal, eleVal, updateList)

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
    magFragVM: MagicFragmentViewModel,
    inputSpell: Spell,
    updateList: () -> Unit
){
    Button(
        onClick = {
            //attempt to purchase the spell
            magFragVM.changeIndividualSpell(inputSpell)
            updateList()
        }
    ){
        Text(
            text = stringResource(
                if(magFragVM.getIndividualSpells().contains(inputSpell)) R.string.spellRemoval
                else R.string.spellPurchase
            )
        )
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
    magFragVM: MagicFragmentViewModel,
    spellLevel: Int,
    spellElement: Element,
    updateList: () -> Unit
){
    //determine if character has equivalent free spell taken
    Button(
        onClick = {
            magFragVM.changeIndividualFreeSpell(spellLevel, spellElement)
            updateList()
        }
    ){
        Text(
            text = stringResource(
                if(magFragVM.getFreeSpellHeld(spellLevel, spellElement)) R.string.spellRemoval
                else R.string.spellPurchase
            )
        )
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
    magFragVM: MagicFragmentViewModel,
    currentFreeSpell: FreeSpell,
    associatedElement: Element,
    associatedLevel: Int,
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
                magFragVM.setFreeElement(associatedElement)
                magFragVM.setFreeLevel(associatedLevel)
                magFragVM.setTextChange{input -> spellName.value = input}
                openChoice()
            }
        )
        {Text(text = stringResource(R.string.changeSpellLabel))}
    }
}

//contents of the spell's detail dialog
val SpellDetails = @Composable {spell: Spell ->
    //get active or passive text
    val action =
        if(spell.isActive)
            stringResource(R.string.activeLabel)
        else
            stringResource(R.string.passiveLabel)

    //get daily text if able
    val daily =
        if(spell.isDaily)
            stringResource(R.string.dailyLabel)
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
        Row{Text(text = stringResource(R.string.actionLabel) + action)}
        Row{Text(text = stringResource(R.string.elementLabel) + spell.inBook.name)}
        Row{Text(text = stringResource(R.string.levelText) + spell.level.toString())}
        Row{Text(text = stringResource(R.string.zeonCostLabel) + spell.zCost.toString())}
        Row{Text(text = spell.effect)}
        Row{Text(text = stringResource(R.string.addedEffectLabel) + spell.addedEffect)}
        Row{Text(text = stringResource(R.string.maxZeonLabel) + spell.zMax)}
        if(spell.maintenance != null)
            Row{Text(text = stringResource(R.string.maintenanceLabel) + "1 every " + spell.maintenance + daily)}
        else
            Row{Text(text = stringResource(R.string.noneLabel))}
        Row{Text(text = stringResource(R.string.typeLabel) + spellType.value)}
        if(spell is FreeSpell)
            Row{Text(text = stringResource(R.string.forbiddenLabel) + forbiddenList)}
    }
}