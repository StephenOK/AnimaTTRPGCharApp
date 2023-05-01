package com.example.animabuilder.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.animabuilder.DetailButton
import com.example.animabuilder.NumberInput
import com.example.animabuilder.R
import com.example.animabuilder.activities.fragments.dialogs.FreeSpellPick
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.FreeSpell
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.view_models.HomePageViewModel
import com.example.animabuilder.view_models.MagicFragmentViewModel

/**
 * Fragment that manages the character's magical properties.
 * Users can update their zeon maximum, accumulation, and magic projection.
 * User can define magic projection imbalance here.
 * User can invest magic levels in spellbook levels and individual spells.
 * User can change the free spells they have in this fragment.
 *
 * @param magFragVM viewModel to run with this fragment
 * @param openDetailAlert function to run when opening an item's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
fun MagicFragment(
    magFragVM: MagicFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
) {
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
                    inputText = magFragVM.boughtZeonString.collectAsState().value,
                    inputFunction = {input -> magFragVM.setBoughtZeonString(input.toInt())},
                    emptyFunction = {magFragVM.setBoughtZeonString("")},
                    modifier = Modifier.weight(0.25f),
                    postRun = {homePageVM.updateExpenditures()}
                )

                //display multiplier for zeon point purchases
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

        //display zeon recovery
        item{Text(text = stringResource(R.string.zeonRecoveryLabel) + magFragVM.zeonRecoveryString.collectAsState().value)}

        //display zeon accumulation and magic projection tables
        items(magFragVM.allPurchaseData){
            ZeonPurchaseItem(it, homePageVM)
        }

        //display character's innate magic
        item{
            Text(text = stringResource(R.string.innateMagic) + magFragVM.innateMagic.collectAsState().value)
        }

        //projection imbalance section
        item {
            Row {
                //title for section
                Text(text = stringResource(R.string.imbalanceLabel))

                //input to change imbalance
                NumberInput(
                    inputText = magFragVM.projectionImbalance.collectAsState().value,
                    inputFunction = {
                        //if imbalance is a legal input
                        if (it.toInt() in 0..30)
                            magFragVM.setProjectionImbalance(it.toInt())
                    },
                    emptyFunction = {magFragVM.setProjectionImbalance("")},
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
        item{Text(text = stringResource(R.string.magicLevelLabel) + magFragVM.getMagicLevelMax())}
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
                    openDetailAlert
                )
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
 * Creates a table for inputs of the particular stat.
 *
 * @param tableItem data for the displayed table
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun ZeonPurchaseItem(
    tableItem: MagicFragmentViewModel.ZeonPurchaseItemData,
    homePageVM: HomePageViewModel
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
                inputText = tableItem.boughtString.collectAsState().value,
                inputFunction = {tableItem.setBoughtString(it.toInt())},
                emptyFunction = {tableItem.setBoughtString("")},
                postRun = {homePageVM.updateExpenditures()},
                color = tableItem.textColor.collectAsState().value
            )

            //display final total
            Text(text = tableItem.totalString.collectAsState().value)
        }
    }
}

/**
 * Creates a  section in the app to acquire spells through purchasing book levels or individually.
 *
 * @param magFragVM viewModel that manages the data for this section of data
 * @param spellData SpellRowData for the individual element
 * @param openDetailAlert function to run when looking at an item's details
 */
@Composable
private fun SpellBookInvestment(
    magFragVM: MagicFragmentViewModel,
    spellData: MagicFragmentViewModel.SpellRowData,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
){
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
                inputText = spellData.elementInvestment.collectAsState().value,
                inputFunction = {spellData.setElementInvestment(it.toInt())},
                emptyFunction = {spellData.setElementInvestment("")}
            )

            //spell display button
            Button(
                onClick = {spellData.toggleListOpen()},
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
 * Row that displays the inputted spell.
 *
 * @param magFragVM viewModel that manages the data for this page
 * @param displayItem spell to display
 * @param buyable whether the row is a purchasable individual spell or just a display
 * @param openDetailAlert function to run when opening this spell's details
 * @param updateList function to run on a button change
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
 * Row to display a free spell in the book column.
 *
 * @param magFragVM viewModel that manages the data for this page
 * @param lvlVal level of the free spell
 * @param eleVal element of the free spell
 * @param updateList function to run on free spell purchase
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
 * Button to purchase a spell individually.
 *
 * @param magFragVM viewModel that manages the data for this page
 * @param inputSpell spell to be purchased
 * @param updateList function to run after spell purchase
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
                if(magFragVM.getSpellHeld(inputSpell)) R.string.spellRemoval
                else R.string.spellPurchase
            )
        )
    }
}

/**
 * Button to purchase an individual free spell.
 *
 * @param magFragVM viewModel that manages the data on this page
 * @param spellLevel level of the free spell
 * @param spellElement element associated with the free spell
 * @param updateList function to run after spell purchase
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
 * @param magFragVM viewModel that manages the data for this page
 * @param currentFreeSpell free spell currently in the slot
 * @param openDetailAlert function to run when looking at an item's details
 */
@Composable
private fun FreeSpellExchange(
    magFragVM: MagicFragmentViewModel,
    currentFreeSpell: FreeSpell,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
){
    //get local context
    val context = LocalContext.current

    //set spell name if one given
    val spellName =
        if(currentFreeSpell.name != "PlaceHolder") currentFreeSpell.name
        else "Empty Free Slot"

    Row{
        //display slot's current spell
        Text(text = spellName)

        //display slot's level and element
        Text(text = "(" + magFragVM.getFreeElement(currentFreeSpell).name + " Lvl ${currentFreeSpell.level})")

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
                if(magFragVM.tryExchangeOpen(
                        currentFreeSpell
                    ))
                    Toast.makeText(
                        context,
                        "Magic Ties prevents getting Free Spells",
                        Toast.LENGTH_LONG
                    ).show()
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
    var spellType = ""
    spell.type.forEach{
        spellType += it.name + " "
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
        Row{Text(text = stringResource(R.string.typeLabel) + spellType)}
        if(spell is FreeSpell)
            Row{Text(text = stringResource(R.string.forbiddenLabel) + forbiddenList)}
    }
}