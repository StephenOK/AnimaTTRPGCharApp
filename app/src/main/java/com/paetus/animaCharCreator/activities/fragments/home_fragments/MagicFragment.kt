package com.paetus.animaCharCreator.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paetus.animaCharCreator.DetailButton
import com.paetus.animaCharCreator.GeneralCard
import com.paetus.animaCharCreator.InfoRow
import com.paetus.animaCharCreator.NumberInput
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.activities.fragments.dialogs.DetailAlert
import com.paetus.animaCharCreator.activities.fragments.dialogs.FreeSpellPick
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.FreeSpell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.view_models.models.HomePageViewModel
import com.paetus.animaCharCreator.view_models.models.MagicFragmentViewModel

/**
 * Fragment that manages the character's magical properties.
 * Users can update their zeon maximum, accumulation, and magic projection.
 * User can define magic projection imbalance here.
 * User can invest magic levels in spellbook levels and individual spells.
 * User can change the free spells they have in this fragment.
 *
 * @param magFragVM viewModel to run with this fragment
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
fun MagicFragment(
    magFragVM: MagicFragmentViewModel,
    homePageVM: HomePageViewModel
) {
    //initialize context
    val context = LocalContext.current

    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(
                top = 15.dp,
                bottom = 15.dp,
                start = 30.dp,
                end = 30.dp
            )
    ){
        item{
            GeneralCard{
                //header for zeon point maximums
                Text(
                    text = stringResource(R.string.zeonPointLabel),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(5.dp))

                //zeon point table header
                Row(
                    Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = stringResource(R.string.baseLabel),
                        modifier = Modifier
                            .weight(0.25f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(R.string.boughtLabel),
                        modifier = Modifier
                            .weight(0.25f),
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.weight(0.1f))

                    Text(
                        text = stringResource(R.string.classLabel),
                        modifier = Modifier
                            .weight(0.15f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(R.string.totalLabel),
                        modifier = Modifier
                            .weight(0.25f),
                        textAlign = TextAlign.Center
                    )
                }

                //initialize zeon point DP cost string
                val dpString = stringResource(R.string.dpLabel, magFragVM.getBoughtZeonDP())

                //zeon point maximum row
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    //display base maximum Zeon
                    Text(
                        text = magFragVM.getBaseZeon().toString(),
                        modifier = Modifier
                            .weight(0.25f),
                        textAlign = TextAlign.Center
                    )

                    //Zeon point purchase input
                    NumberInput(
                        inputText = magFragVM.boughtZeonString.collectAsState().value,
                        inputFunction = {
                            if(magFragVM.isGifted() || it.contains('\n'))
                                magFragVM.setBoughtZeonString(it.toInt())
                        },
                        emptyFunction = {magFragVM.setBoughtZeonString("")},
                        modifier = Modifier
                            .onFocusChanged {
                                //display Gift not taken message
                                if(it.isFocused && !magFragVM.isGifted())
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.needGiftMessage),
                                        Toast.LENGTH_LONG
                                    ).show()

                                //change DP display to appropriate value
                                if (it.isFocused)
                                    magFragVM.setBoughtZeonDP(dpString)
                                else
                                    magFragVM.setBoughtZeonDP("")
                            }
                            .weight(0.25f),
                        label = magFragVM.boughtZeonDP.collectAsState().value,
                        postRun = {homePageVM.updateExpenditures()}
                    )

                    //display multiplier for zeon point purchases
                    Text(
                        text = stringResource(R.string.zeonPointMultiplier),
                        modifier = Modifier
                            .weight(0.1f),
                        textAlign = TextAlign.Center
                    )

                    //display zeon points from class levels
                    Text(
                        text = magFragVM.getClassZeon().toString(),
                        modifier = Modifier
                            .weight(0.15f),
                        textAlign = TextAlign.Center
                    )

                    //display final total
                    Text(
                        text = magFragVM.maxZeonString.collectAsState().value,
                        modifier = Modifier
                            .weight(0.25f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        item{Spacer(Modifier.height(10.dp))}

        item{
            GeneralCard{
                ZeonPurchaseItem(magFragVM.allPurchaseData[0], magFragVM, homePageVM)

                //display zeon recovery
                InfoRow(
                    stringResource(R.string.zeonRecoveryLabel),
                    magFragVM.zeonRecoveryString.collectAsState().value
                )

                //display innate magic
                InfoRow(
                    stringResource(R.string.innateMagic),
                    magFragVM.innateMagic.collectAsState().value
                )
            }
        }

        item{Spacer(Modifier.height(10.dp))}

        //display zeon accumulation and magic projection tables
        item{
            GeneralCard {
                ZeonPurchaseItem(magFragVM.allPurchaseData[1], magFragVM, homePageVM)

                Spacer(Modifier.height(10.dp))

                //projection imbalance section
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //title for section
                    Text(
                        text = stringResource(R.string.imbalanceLabel),
                        modifier = Modifier
                            .weight(0.21f)
                    )

                    Spacer(Modifier.weight(0.01f))

                    //input to change imbalance
                    NumberInput(
                        inputText = magFragVM.projectionImbalance.collectAsState().value,
                        inputFunction = {
                            //if imbalance is a legal input
                            if ((it.toInt() in 0..30 && magFragVM.isGifted()) || it.contains('\n'))
                                magFragVM.setProjectionImbalance(it.toInt())
                        },
                        emptyFunction = {
                            magFragVM.setProjectionImbalance("")
                        },
                        modifier = Modifier
                            .onFocusChanged {
                                if (it.isFocused && !magFragVM.isGifted())
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.needGiftMessage),
                                        Toast.LENGTH_LONG
                                    ).show()
                            }
                            .weight(0.22f)
                    )

                    Spacer(Modifier.weight(0.01f))

                    Button(
                        //switch imbalance preference
                        onClick = {
                            magFragVM.setImbalanceIsAttack(!magFragVM.imbalanceIsAttack.value)
                        },
                        modifier = Modifier
                            .weight(0.3f)
                    ) {
                        //display current imbalance preference
                        Text(text = stringResource(magFragVM.imbalanceTypeString.collectAsState().value))
                    }

                    Spacer(Modifier.weight(0.01f))

                    //display imbalanced projection values
                    Column(
                        Modifier
                            .weight(0.24f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(
                                R.string.offenseHeader,
                                magFragVM.offenseImbalance.collectAsState().value
                            )
                        )

                        Text(
                            text = stringResource(
                                R.string.defenseHeader,
                                magFragVM.defenseImbalance.collectAsState().value
                            )
                        )
                    }
                }
            }
        }

        item{Spacer(Modifier.height(20.dp))}

        //display magic levels available and magic levels spent
        item{
            GeneralCard{
                InfoRow(
                    stringResource(R.string.magicLevelLabel),
                    magFragVM.getMagicLevelMax(),
                )

                InfoRow(
                    stringResource(R.string.magicLevelSpentLabel),
                    magFragVM.magicLevelSpent.collectAsState().value
                )
            }
        }

        //display each book investment row and spell displays
        items(magFragVM.allBooks){
            SpellBookInvestment(
                magFragVM,
                it
            )
        }

        item{Spacer(Modifier.height(20.dp))}

        item{
            GeneralCard{
                //header for held spells section
                Text(
                    text = stringResource(R.string.spellsTakenLabel),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(10.dp))

                //display all currently taken spells
                magFragVM.heldSpells.forEach{
                    //display free spell exchange if it's a free spell
                    if(it is FreeSpell)
                        FreeSpellExchange(
                            magFragVM,
                            it
                        )
                    else
                        SpellRow(
                            magFragVM,
                            it,
                            false
                        ) {}

                    Row{Spacer(Modifier.height(3.dp))}
                }
            }
        }
    }

    //show free spell dialog if displayed
    if(magFragVM.freeExchangeOpen.collectAsState().value)
        FreeSpellPick(
            magFragVM
        )

    //show spell details if requested
    if(magFragVM.detailAlertOpen.collectAsState().value)
        DetailAlert(
            magFragVM.detailTitle.collectAsState().value,
            magFragVM.detailItem.collectAsState().value!!
        ) {magFragVM.toggleDetailAlertOpen()}
}

/**
 * Creates a table for inputs of the particular stat.
 *
 * @param tableItem data for the displayed table
 * @param magFragVM viewModel that manages this fragment
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun ZeonPurchaseItem(
    tableItem: MagicFragmentViewModel.ZeonPurchaseItemData,
    magFragVM: MagicFragmentViewModel,
    homePageVM: HomePageViewModel
){
    //initialize context
    val context = LocalContext.current

    Column{
        Row{Spacer(Modifier.height(20.dp))}

        //display table title
        Row(
            Modifier
                .fillMaxWidth()
        ){
            Text(
                text = stringResource(tableItem.nameRef),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        //display table header
        Row{
            Text(
                text = stringResource(R.string.baseLabel),
                modifier = Modifier
                    .weight(0.3f),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.boughtLabel),
                modifier = Modifier
                    .weight(0.3f),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.totalLabel),
                modifier = Modifier
                    .weight(0.3f),
                textAlign = TextAlign.Center
            )
        }

        //initialize item's DP cost string
        val dpString = stringResource(R.string.dpLabel, tableItem.dpGetter())

        //display table items
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            //display base value
            Text(
                text = tableItem.baseInput().toString(),
                modifier = Modifier
                    .weight(0.3f),
                textAlign = TextAlign.Center
            )

            //input for user to purchase these points
            NumberInput(
                inputText = tableItem.boughtString.collectAsState().value,
                inputFunction = {
                    if(magFragVM.isGifted() || it.contains('\n'))
                        tableItem.setBoughtString(it.toInt())
                },
                emptyFunction = {tableItem.setBoughtString("")},
                modifier = Modifier
                    .onFocusChanged {
                        //display warning for not having The Gift
                        if(it.isFocused && !magFragVM.isGifted())
                            Toast.makeText(
                                context,
                                context.getString(R.string.needGiftMessage),
                                Toast.LENGTH_LONG
                            ).show()

                        //update DP display
                        if(it.isFocused)
                            tableItem.setDPDisplay(dpString)
                        else
                            tableItem.setDPDisplay("")
                    }
                    .weight(0.3f),
                label = tableItem.dpDisplay.collectAsState().value,
                postRun = {homePageVM.updateExpenditures()},
                isError = !tableItem.textValid.collectAsState().value
            )

            //display final total
            Text(
                text = tableItem.totalString.collectAsState().value,
                modifier = Modifier
                    .weight(0.3f),
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Creates a  section in the app to acquire spells through purchasing book levels or individually.
 *
 * @param magFragVM viewModel that manages the data for this section of data
 * @param spellData SpellRowData for the individual element
 */
@Composable
private fun SpellBookInvestment(
    magFragVM: MagicFragmentViewModel,
    spellData: MagicFragmentViewModel.SpellRowData
){
    //initialize context
    val context = LocalContext.current

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            //primary element checkbox
            Checkbox(
                checked = magFragVM.primaryElementBoxes[spellData.spellElement]!!.value,
                onCheckedChange = {magFragVM.changePrimaryBook(spellData.spellElement, it)},
                modifier = Modifier.weight(0.1f)
            )

            //display associated element
            Text(
                text = spellData.spellElement.name,
                modifier = Modifier
                    .weight(0.25f)
            )

            //display book investment value
            NumberInput(
                inputText = spellData.elementInvestment.collectAsState().value,
                inputFunction = {
                    if(magFragVM.isGifted() || it.contains('\n'))
                        spellData.setElementInvestment(it.toInt())
                },
                emptyFunction = {spellData.setElementInvestment("")},
                modifier = Modifier
                    .onFocusChanged {
                        if(it.isFocused && !magFragVM.isGifted())
                            Toast.makeText(
                                context,
                                context.getString(R.string.needGiftMessage),
                                Toast.LENGTH_LONG
                            ).show()
                    }
                    .weight(0.24f),
            )

            Spacer(Modifier.weight(0.01f))

            //spell display button
            Button(
                onClick = {spellData.toggleListOpen()},
                modifier = Modifier
                    .weight(0.4f)
            ) {
                Text(
                    text = stringResource(
                        if(spellData.listOpen.collectAsState().value) R.string.spellsHidden
                        else R.string.spellsShown
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }

        //display of spells
        AnimatedVisibility(
            visible = spellData.listOpen.collectAsState().value,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            GeneralCard {
                //initialize free spell level indicator
                var freeSpellLevel = 0

                //for each of the element's spells
                spellData.spellList.forEach {
                    //display the given spell if one is given
                    if (it != null) {
                        SpellRow(
                            magFragVM,
                            it,
                            true
                        ) { magFragVM.reflectPrimaryElement(); magFragVM.setMagicLevelSpent()}

                        //increment free spell level
                        freeSpellLevel = it.level + 2
                    }

                    //display free spell row if no spell given
                    else
                        FreeSpellRow(magFragVM, freeSpellLevel, spellData.spellElement)
                        { magFragVM.reflectPrimaryElement(); magFragVM.setMagicLevelSpent()}

                    Spacer(Modifier.height(3.dp))
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
 * @param updateList function to run on a button change
 */
@Composable
private fun SpellRow(
    magFragVM: MagicFragmentViewModel,
    displayItem: Spell,
    buyable: Boolean,
    updateList: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //make purchase button if buyable row
        if(buyable && magFragVM.spellIsRemovable(displayItem))
            BuySingleSpellButton(
                magFragVM,
                displayItem,
                Modifier.weight(0.35f),
                updateList
            )
        else
            Spacer(Modifier.weight(0.35f))

        //display spell name
        Text(
            text = stringResource(displayItem.name),
            modifier = Modifier
                .weight(0.4f),
            textAlign = TextAlign.Center,
            color =
                if(!magFragVM.getCastable(displayItem.level))
                    MaterialTheme.colorScheme.secondary
                else
                    MaterialTheme.colorScheme.onError
        )

        //create display button
        DetailButton(
            onClick = {
                magFragVM.setDetailItem(displayItem)
                magFragVM.toggleDetailAlertOpen()
            },
            modifier = Modifier
                .weight(0.25f)
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
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //button to buy free spell individually
        if(magFragVM.freeSpellIsRemovable(lvlVal, eleVal)){
            BuySingleFreeSpellButton(
                magFragVM,
                lvlVal,
                eleVal,
                Modifier.weight(0.35f),
                updateList
            )
        }
        else
            Spacer(Modifier.weight(0.35f))

        //display free spell values
        Text(
            text = stringResource(R.string.freeLevelLabel, lvlVal),
            modifier = Modifier
                .weight(0.4f),
            textAlign = TextAlign.Center,
            color =
                if(!magFragVM.getCastable(lvlVal))
                    MaterialTheme.colorScheme.secondary
                else
                    MaterialTheme.colorScheme.onError
        )

        Spacer(Modifier.weight(0.25f))
    }
}

/**
 * Button to purchase a spell individually.
 *
 * @param magFragVM viewModel that manages the data for this page
 * @param inputSpell spell to be purchased
 * @param modifier parameters for the button's style
 * @param updateList function to run after spell purchase
 */
@Composable
private fun BuySingleSpellButton(
    magFragVM: MagicFragmentViewModel,
    inputSpell: Spell,
    modifier: Modifier,
    updateList: () -> Unit
){
    //initialize context
    val context = LocalContext.current

    Button(
        onClick = {
            //attempt to purchase the spell
            if(magFragVM.isGifted()) {
                magFragVM.changeIndividualSpell(inputSpell)
                updateList()
            }
            else
                Toast.makeText(
                    context,
                    context.getString(R.string.needGiftMessage),
                    Toast.LENGTH_LONG
                ).show()
        },
        modifier = modifier
    ){
        Text(
            text = stringResource(
                if(magFragVM.getSpellHeld(inputSpell)) R.string.spellRemoval
                else R.string.spellPurchase
            ),
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Button to purchase an individual free spell.
 *
 * @param magFragVM viewModel that manages the data on this page
 * @param spellLevel level of the free spell
 * @param spellElement element associated with the free spell
 * @param modifier parameters for the button style
 * @param updateList function to run after spell purchase
 */
@Composable
private fun BuySingleFreeSpellButton(
    magFragVM: MagicFragmentViewModel,
    spellLevel: Int,
    spellElement: Element,
    modifier: Modifier,
    updateList: () -> Unit
){
    //initialize context
    val context = LocalContext.current

    //determine if character has equivalent free spell taken
    Button(
        onClick = {
            if(magFragVM.isGifted()) {
                magFragVM.changeIndividualFreeSpell(spellLevel, spellElement)
                updateList()
            }
            else
                Toast.makeText(
                    context,
                    context.getString(R.string.needGiftMessage),
                    Toast.LENGTH_LONG
                ).show()
        },
        modifier = modifier
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
 */
@Composable
private fun FreeSpellExchange(
    magFragVM: MagicFragmentViewModel,
    currentFreeSpell: FreeSpell
){
    //get local context
    val context = LocalContext.current

    //set spell name if one given
    val spellName =
        if(currentFreeSpell.saveName != "PlaceHolder") stringResource(currentFreeSpell.name)
        else stringResource(R.string.emptySlotLabel)

    Row{
        //button that opens free spell exchange dialog
        Button(
            onClick = {
                if(magFragVM.tryExchangeOpen(
                        currentFreeSpell
                    ))
                    Toast.makeText(
                        context,
                        context.getString(R.string.magicTiesRestriction),
                        Toast.LENGTH_LONG
                    ).show()
            },
            modifier = Modifier
                .weight(0.34f)
        )
        {
            Text(
                text = stringResource(R.string.changeSpellLabel),
                textAlign = TextAlign.Center
            )
        }

        //display slot's current spell, level, and element
        Text(
            text = stringResource(
                R.string.currentFreeSpell,
                spellName,
                magFragVM.getFreeElement(currentFreeSpell).name,
                currentFreeSpell.level
            ),
            modifier = Modifier
                .weight(0.4f),
            textAlign = TextAlign.Center
        )

        //show nothing here if no spell found
        if(currentFreeSpell.saveName == "PlaceHolder"){Spacer(Modifier.weight(0.25f))}

        //show detail button if spell found
        else
            DetailButton(
                onClick = {
                    magFragVM.setDetailItem(currentFreeSpell)
                    magFragVM.toggleDetailAlertOpen()
                },
                modifier = Modifier
                    .weight(0.25f)
            )
    }
}

@Preview
@Composable
fun MagicPreview(){
    val charInstance = BaseCharacter()
    val magFragVM = MagicFragmentViewModel(
        charInstance.magic,
        charInstance,
        charInstance.ownClass,
        LocalContext.current
    )
    val homePageVM = HomePageViewModel(charInstance)

    magFragVM.setImbalanceIsAttack(false)
    magFragVM.setProjectionImbalance(30)

    magFragVM.allBooks[0].toggleListOpen()
    magFragVM.changeIndividualSpell(magFragVM.allBooks[0].spellList[2]!!)
    magFragVM.changeIndividualSpell(magFragVM.allBooks[0].spellList[3]!!)

    MagicFragment(magFragVM, homePageVM)
}