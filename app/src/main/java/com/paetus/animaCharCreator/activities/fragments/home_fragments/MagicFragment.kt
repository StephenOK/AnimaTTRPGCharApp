package com.paetus.animaCharCreator.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
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
import com.paetus.animaCharCreator.composables.DetailButton
import com.paetus.animaCharCreator.composables.GeneralCard
import com.paetus.animaCharCreator.composables.InfoRow
import com.paetus.animaCharCreator.composables.NumberInput
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.activities.fragments.dialogs.DetailAlert
import com.paetus.animaCharCreator.activities.fragments.dialogs.FreeSpellPick
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.FreeSpell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.composables.PopInItem
import com.paetus.animaCharCreator.numberScroll
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
                start = 30.dp,
                end = 30.dp
            )
    ){
        item{Spacer(modifier = Modifier.height(15.dp))}

        item{
            GeneralCard{
                //header for zeon point maximums
                Text(
                    text = stringResource(id = R.string.zeonPointLabel),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))

                //zeon point table header
                Row(
                    Modifier
                        .fillMaxWidth()
                ){
                    //head for base zeon points
                    Text(
                        text = stringResource(id = R.string.baseLabel),
                        modifier = Modifier
                            .weight(0.25f),
                        textAlign = TextAlign.Center
                    )

                    //head for bought zeon points
                    Text(
                        text = stringResource(id = R.string.boughtLabel),
                        modifier = Modifier
                            .weight(0.25f),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.weight(0.1f))

                    //head for class zeon points
                    Text(
                        text = stringResource(id = R.string.classLabel),
                        modifier = Modifier
                            .weight(0.15f),
                        textAlign = TextAlign.Center
                    )

                    //head for total zeon points
                    Text(
                        text = stringResource(id = R.string.totalLabel),
                        modifier = Modifier
                            .weight(0.25f),
                        textAlign = TextAlign.Center
                    )
                }

                //initialize zeon point DP cost string
                val dpString = stringResource(id = R.string.dpLabel, magFragVM.getBoughtZeonDP())

                //zeon point maximum row
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    //display base zeon points
                    Text(
                        text = magFragVM.getBaseZeon().toString(),
                        modifier = Modifier
                            .weight(0.25f),
                        textAlign = TextAlign.Center
                    )

                    //zeon point purchase input
                    NumberInput(
                        inputText = magFragVM.boughtZeonString.collectAsState().value,
                        inputFunction = {
                            if(magFragVM.isGifted() || it.contains('\n'))
                                magFragVM.setBoughtZeonString(zeonBought = it.toInt())
                        },
                        emptyFunction = {magFragVM.setBoughtZeonString(display = "")},
                        modifier = Modifier
                            .onFocusChanged {
                                //display Gift not taken message
                                if (it.isFocused && !magFragVM.isGifted())
                                    Toast
                                        .makeText(
                                            context,
                                            context.getString(R.string.needGiftMessage),
                                            Toast.LENGTH_LONG
                                        )
                                        .show()

                                //change DP display to appropriate value
                                if (it.isFocused)
                                    magFragVM.setBoughtZeonDP(dpCost = dpString)
                                else
                                    magFragVM.setBoughtZeonDP(dpCost = "")
                            }
                            .weight(0.25f),
                        label = magFragVM.boughtZeonDP.collectAsState().value,
                        postRun = {homePageVM.updateExpenditures()}
                    )

                    //display multiplier for zeon point purchases
                    Text(
                        text = stringResource(id = R.string.zeonPointMultiplier),
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

        item{Spacer(modifier = Modifier.height(10.dp))}

        item{
            GeneralCard{
                //display magic accumulation
                ZeonPurchaseItem(
                    tableItem = magFragVM.zeonAccumulation,
                    magFragVM = magFragVM,
                    homePageVM = homePageVM
                )

                //display zeon recovery
                InfoRow(
                    label = stringResource(id = R.string.zeonRecoveryLabel)
                ){modifier, _ ->
                    AnimatedContent(
                        targetState = magFragVM.zeonRecoveryString.collectAsState().value,
                        modifier = modifier,
                        transitionSpec = numberScroll,
                        label = "zeonRecovery"
                    ){
                        Text(text = "$it")
                    }
                }

                //display innate magic
                InfoRow(
                    label = stringResource(id = R.string.innateMagic)
                ){modifier, _ ->
                    AnimatedContent(
                        targetState = magFragVM.innateMagic.collectAsState().value,
                        modifier = modifier,
                        transitionSpec = numberScroll,
                        label = "innateMagic"
                    ){
                        Text(text = "$it")
                    }
                }
            }
        }

        item{Spacer(Modifier.height(10.dp))}

        //display zeon accumulation and magic projection tables
        item{
            GeneralCard {
                ZeonPurchaseItem(
                    tableItem = magFragVM.zeonProjection,
                    magFragVM = magFragVM,
                    homePageVM = homePageVM
                )

                Spacer(Modifier.height(10.dp))

                //projection imbalance section
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //title for section
                    Text(
                        text = stringResource(id = R.string.imbalanceLabel),
                        modifier = Modifier
                            .weight(0.21f)
                    )

                    Spacer(modifier = Modifier.weight(0.01f))

                    //input to change imbalance
                    NumberInput(
                        inputText = magFragVM.projectionImbalance.collectAsState().value,
                        inputFunction = {
                            //if imbalance is a legal input
                            if ((it.toInt() in 0..30 && magFragVM.isGifted()) || it.contains(char = '\n'))
                                magFragVM.setProjectionImbalance(imbalance = it.toInt())
                        },
                        emptyFunction = {
                            magFragVM.setProjectionImbalance(display = "")
                        },
                        modifier = Modifier
                            .onFocusChanged {
                                if (it.isFocused && !magFragVM.isGifted())
                                    Toast
                                        .makeText(
                                            context,
                                            context.getString(R.string.needGiftMessage),
                                            Toast.LENGTH_LONG
                                        )
                                        .show()
                            }
                            .weight(0.22f)
                    )

                    Spacer(modifier = Modifier.weight(0.01f))

                    Button(
                        //switch imbalance preference
                        onClick = {
                            magFragVM.setImbalanceIsAttack(isOffense = !magFragVM.imbalanceIsAttack.value)
                        },
                        modifier = Modifier
                            .weight(0.3f)
                    ) {
                        //display current imbalance preference
                        Text(text = stringResource(id = magFragVM.imbalanceTypeString.collectAsState().value))
                    }

                    Spacer(modifier = Modifier.weight(0.01f))

                    Column(
                        Modifier
                            .weight(0.24f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //display imbalanced offensive projection
                        Row {
                            Text(
                                text = "${stringResource(id = R.string.offenseLabel)}:"
                            )

                            AnimatedContent(
                                targetState = magFragVM.offenseImbalance.collectAsState().value,
                                transitionSpec = numberScroll,
                                label = "offenseImbalance"
                            ){
                                Text(text = "$it")
                            }
                        }

                        //display imbalanced defensive projection
                        Row {
                            Text(
                                text = "${stringResource(id = R.string.defenseLabel)}:"
                            )

                            AnimatedContent(
                                targetState = magFragVM.defenseImbalance.collectAsState().value,
                                transitionSpec = numberScroll,
                                label = "defenseImbalance"
                            ){
                                Text(
                                    text = "$it"
                                )
                            }
                        }
                    }
                }
            }
        }

        item{Spacer(modifier = Modifier.height(20.dp))}

        //display magic levels available and magic levels spent
        item{
            GeneralCard{
                InfoRow(
                    label = stringResource(id = R.string.magicLevelLabel)
                ){modifier, _ ->
                    Text(
                        text = magFragVM.getMagicLevelMax(),
                        modifier = modifier
                    )
                }

                InfoRow(
                    label = stringResource(id = R.string.magicLevelSpentLabel)
                ){modifier, _ ->
                    AnimatedContent(
                        targetState = magFragVM.magicLevelSpent.collectAsState().value,
                        modifier = modifier,
                        transitionSpec = numberScroll,
                        label = "magLevelSpent"
                    ){
                        Text(text = "$it")
                    }
                }
            }
        }

        //display each book investment row and spell displays
        items(magFragVM.allBooks){spellBook ->
            SpellBookInvestment(
                magFragVM = magFragVM,
                spellData = spellBook
            )
        }

        item{Spacer(modifier = Modifier.height(20.dp))}

        item{
            GeneralCard{
                //header for held spells section
                Text(
                    text = stringResource(id = R.string.spellsTakenLabel),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                //display all currently taken spells
                magFragVM.heldSpells.forEach{spell ->
                    //display free spell exchange if it's a free spell
                    if(spell is FreeSpell)
                        FreeSpellExchange(
                            currentFreeSpell = spell,
                            magFragVM = magFragVM
                        )
                    else
                        SpellRow(
                            spell = spell,
                            buyable = false,
                            spellData = magFragVM.getSpellData(spell = spell),
                            magFragVM = magFragVM,
                            updateList = {}
                        )

                    Row{Spacer(modifier = Modifier.height(3.dp))}
                }
            }
        }

        item{Spacer(modifier = Modifier.height(15.dp))}
    }

    //show free spell dialog if displayed
    if(magFragVM.freeExchangeOpen.collectAsState().value)
        FreeSpellPick(
            magFragVM = magFragVM
        )

    //show spell details if requested
    if(magFragVM.detailAlertOpen.collectAsState().value)
        DetailAlert(
            title = magFragVM.detailTitle.collectAsState().value,
            item = magFragVM.detailItem.collectAsState().value!!
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
        Row{Spacer(modifier = Modifier.height(20.dp))}

        //display table title
        Row(
            Modifier
                .fillMaxWidth()
        ){
            Text(
                text = stringResource(id = tableItem.nameRef),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        //display table header
        Row{
            //display base value head
            Text(
                text = stringResource(id = R.string.baseLabel),
                modifier = Modifier
                    .weight(0.3f),
                textAlign = TextAlign.Center
            )

            //display bought points header
            Text(
                text = stringResource(id = R.string.boughtLabel),
                modifier = Modifier
                    .weight(0.3f),
                textAlign = TextAlign.Center
            )

            //display total value header
            Text(
                text = stringResource(id = R.string.totalLabel),
                modifier = Modifier
                    .weight(0.3f),
                textAlign = TextAlign.Center
            )
        }

        //initialize item's DP cost string
        val dpString = stringResource(id = R.string.dpLabel, tableItem.dpGetter())

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
                    if(magFragVM.isGifted() || it.contains(char = '\n'))
                        tableItem.setBoughtString(buyValue = it.toInt())
                },
                emptyFunction = {tableItem.setBoughtString(display = "")},
                modifier = Modifier
                    .onFocusChanged {
                        //display warning for not having The Gift
                        if (it.isFocused && !magFragVM.isGifted())
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.needGiftMessage),
                                    Toast.LENGTH_LONG
                                )
                                .show()

                        //update DP display
                        if (it.isFocused)
                            tableItem.setDPDisplay(dpDisplay = dpString)
                        else
                            tableItem.setDPDisplay(dpDisplay = "")
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
                checked = spellData.isPrimary.collectAsState().value,
                onCheckedChange = {spellData.setPrimaryElement(isPrimary = it)},
                modifier = Modifier
                    .weight(0.1f)
            )

            //display associated element
            Text(
                text = spellData.magicBook.element.name,
                modifier = Modifier
                    .weight(0.25f)
            )

            //display book investment value
            NumberInput(
                inputText = spellData.elementInvestment.collectAsState().value,
                inputFunction = {
                    if(magFragVM.isGifted() || it.contains(char = '\n'))
                        spellData.setElementInvestment(magLevels = it.toInt())
                },
                emptyFunction = {spellData.setElementInvestment(display = "")},
                modifier = Modifier
                    .onFocusChanged {
                        if (it.isFocused && !magFragVM.isGifted())
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.needGiftMessage),
                                    Toast.LENGTH_LONG
                                )
                                .show()
                    }
                    .weight(0.24f),
            )

            Spacer(modifier = Modifier.weight(0.01f))

            //button to display book's spell list
            Button(
                onClick = {spellData.toggleListOpen()},
                modifier = Modifier
                    .weight(0.4f)
            ) {
                Text(
                    text = stringResource(
                        id =
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
                spellData.magicBook.fullBook.forEach {spell ->
                    //display the given spell if one is given
                    if (spell != null) {
                        SpellRow(
                            spell = spell,
                            buyable = true,
                            spellData = spellData,
                            magFragVM = magFragVM,
                            updateList = {
                                spellData.setPrimaryElement(isPrimary = spellData.magicBook.isPrimary.value)
                                magFragVM.setMagicLevelSpent()
                            }
                        )

                        //increment free spell level
                        freeSpellLevel = spell.level + 2
                    }

                    //display free spell row if no spell given
                    else
                        FreeSpellRow(
                            spellLevel = freeSpellLevel,
                            spellElement = spellData.magicBook.element,
                            spellData = spellData,
                            magFragVM = magFragVM,
                            updateList = {
                                spellData.setPrimaryElement(isPrimary = spellData.isPrimary.value)
                                magFragVM.setMagicLevelSpent()
                            }
                        )

                    Spacer(modifier = Modifier.height(3.dp))
                }
            }
        }
    }
}

/**
 * Row that displays the inputted spell.
 *
 * @param spell spell to display
 * @param buyable whether the row is a purchasable individual spell or just a display
 * @param spellData spellbook data for this row's spell
 * @param magFragVM viewModel that manages the data for this page
 * @param updateList function to run on a button change
 */
@Composable
private fun SpellRow(
    spell: Spell,
    buyable: Boolean,
    spellData: MagicFragmentViewModel.SpellRowData,
    magFragVM: MagicFragmentViewModel,
    updateList: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //make purchase button if buyable row
        PopInItem(
            visible = buyable && magFragVM.spellIsRemovable(spell = spell),
            modifier = Modifier
                .weight(0.35f)
        ){
            BuySingleSpellButton(
                spell = spell,
                spellData = spellData,
                modifier = Modifier
                    .weight(0.35f),
                magFragVM = magFragVM,
                updateList = updateList
            )
        }

        //display spell name
        Text(
            text = stringResource(id = spell.name),
            modifier = Modifier
                .weight(0.4f),
            textAlign = TextAlign.Center,
            color =
                if(!magFragVM.getCastable(spellLevel = spell.level))
                    MaterialTheme.colorScheme.secondary
                else
                    MaterialTheme.colorScheme.error
        )

        //create display button
        DetailButton(
            onClick = {
                magFragVM.setDetailItem(spell = spell)
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
 * @param spellLevel level of the free spell
 * @param spellElement element of the free spell
 * @param spellData spellbook data for this row's spell
 * @param magFragVM viewModel that manages the data for this page
 * @param updateList function to run on free spell purchase
 */
@Composable
private fun FreeSpellRow(
    spellLevel: Int,
    spellElement: Element,
    spellData: MagicFragmentViewModel.SpellRowData,
    magFragVM: MagicFragmentViewModel,
    updateList: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //button to buy free spell individually
        PopInItem(
            visible = magFragVM.freeSpellIsRemovable(spellLevel = spellLevel, element = spellElement),
            modifier = Modifier
                .weight(0.35f)
        ) {
            BuySingleFreeSpellButton(
                spellLevel = spellLevel,
                spellData = spellData,
                modifier = Modifier
                    .weight(0.35f),
                magFragVM = magFragVM,
                updateList = updateList
            )
        }

        //display free spell slot's level
        Text(
            text = stringResource(id = R.string.freeLevelLabel, spellLevel),
            modifier = Modifier
                .weight(0.4f),
            textAlign = TextAlign.Center,
            color =
                if(!magFragVM.getCastable(spellLevel = spellLevel))
                    MaterialTheme.colorScheme.secondary
                else
                    MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.weight(0.25f))
    }
}

/**
 * Button to purchase a spell individually.
 *
 * @param spell spell to be purchased
 * @param spellData spellbook data for this item's associated spell
 * @param modifier parameters for the button's style
 * @param magFragVM viewModel that manages the data for this page
 * @param updateList function to run after spell purchase
 */
@Composable
private fun BuySingleSpellButton(
    spell: Spell,
    spellData: MagicFragmentViewModel.SpellRowData,
    modifier: Modifier,
    magFragVM: MagicFragmentViewModel,
    updateList: () -> Unit
){
    //initialize context
    val context = LocalContext.current

    Button(
        onClick = {
            //attempt to purchase the spell
            if(magFragVM.isGifted()) {
                spellData.buySingleSpell(spellLevel = spell.level)
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
                if(magFragVM.getSpellHeld(spell = spell)) R.string.spellRemoval
                else R.string.spellPurchase
            ),
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Button to purchase an individual free spell.
 *
 * @param spellLevel level of the free spell
 * @param spellData spellbook data for this row's associated spell
 * @param modifier parameters for the button style
 * @param magFragVM viewModel that manages the data on this page
 * @param updateList function to run after spell purchase
 */
@Composable
private fun BuySingleFreeSpellButton(
    spellLevel: Int,
    spellData: MagicFragmentViewModel.SpellRowData,
    modifier: Modifier,
    magFragVM: MagicFragmentViewModel,
    updateList: () -> Unit
){
    //initialize context
    val context = LocalContext.current

    //determine if character has equivalent free spell taken
    Button(
        onClick = {
            if(magFragVM.isGifted()) {
                spellData.buySingleSpell(spellLevel = spellLevel)
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
                if((spellLevel/2) - 1 in spellData.magicBook.individualSpells) R.string.spellRemoval
                else R.string.spellPurchase
            )
        )
    }
}

/**
 * Row to change the spell in the character's free spell slot
 *
 * @param currentFreeSpell free spell currently in the slot
 * @param magFragVM viewModel that manages the data for this page
 */
@Composable
private fun FreeSpellExchange(
    currentFreeSpell: FreeSpell,
    magFragVM: MagicFragmentViewModel
){
    //get local context
    val context = LocalContext.current

    //set spell name if one given
    val spellName =
        if(currentFreeSpell.saveName != "PlaceHolder") stringResource(id = currentFreeSpell.name)
        else stringResource(id = R.string.emptySlotLabel)

    Row{
        //button that opens free spell exchange dialog
        Button(
            onClick = {
                if(magFragVM.tryExchangeOpen(freeSpell = currentFreeSpell))
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
                text = stringResource(id = R.string.changeSpellLabel),
                textAlign = TextAlign.Center
            )
        }

        //display slot's current spell, level, and element
        Text(
            text = stringResource(
                id = R.string.currentFreeSpell,
                spellName,
                magFragVM.getFreeElement(freeSpell = currentFreeSpell).name,
                currentFreeSpell.level
            ),
            modifier = Modifier
                .weight(0.4f),
            textAlign = TextAlign.Center
        )

        //show nothing here if no spell found
        if(currentFreeSpell.saveName == "PlaceHolder"){Spacer(modifier = Modifier.weight(0.25f))}

        //show detail button if spell found
        else
            DetailButton(
                onClick = {
                    magFragVM.setDetailItem(spell = currentFreeSpell)
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
    magFragVM.allBooks[0].buySingleSpell(magFragVM.allBooks[0].magicBook.fullBook[2]!!.level)
    magFragVM.allBooks[0].buySingleSpell(magFragVM.allBooks[0].magicBook.fullBook[3]!!.level)

    MagicFragment(magFragVM, homePageVM)
}