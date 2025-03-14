package com.paetus.animaCharCreator.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paetus.animaCharCreator.composables.DetailButton
import com.paetus.animaCharCreator.composables.GeneralCard
import com.paetus.animaCharCreator.composables.NumberInput
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.activities.fragments.dialogs.DetailAlert
import com.paetus.animaCharCreator.activities.fragments.dialogs.EquipmentItemPurchase
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.numberScroll
import com.paetus.animaCharCreator.view_models.models.EquipmentFragmentViewModel

/**
 * Fragment that manages the character's inventory.
 *
 * @param equipFragVM viewModel that manages the character's data
 */
@Composable
fun EquipmentFragment(
    equipFragVM: EquipmentFragmentViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 30.dp,
                end = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item{Spacer(modifier = Modifier.height(15.dp))}

        item{
            GeneralCard {
                //create inputs for each maximum coin expenditure
                equipFragVM.allQuantityMaximums.forEach{quantity ->
                    MaximumDisplay(maxData = quantity)
                }

                //display bonus wealth if any taken
                if(equipFragVM.getBonusWealth() > 0){
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text =
                        stringResource(
                            id = R.string.bonusWealthLabel,
                            stringResource(id = R.string.goldLabel, equipFragVM.getBonusWealth())
                        )
                    )
                }
            }
        }

        item{Spacer(modifier = Modifier.height(20.dp))}

        //display all coin spent
        item{
            GeneralCard {
                Row{
                    Text(
                        text = stringResource(id = R.string.spentLabel),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                ) {
                    //gold spent
                    SpentDisplay(
                        coinType = CoinType.Gold,
                        amount = equipFragVM.getCoinSpent(CoinType.Gold),
                        modifier = Modifier
                            .weight(0.2f)
                    )

                    //silver spent
                    SpentDisplay(
                        coinType = CoinType.Silver,
                        amount = equipFragVM.getCoinSpent(CoinType.Silver),
                        modifier = Modifier
                            .weight(0.2f)
                    )

                    //copper spent
                    SpentDisplay(
                        coinType = CoinType.Copper,
                        amount = equipFragVM.getCoinSpent(CoinType.Copper),
                        modifier = Modifier
                            .weight(0.2f)
                    )
                }
            }
        }

        item{Spacer(modifier = Modifier.height(10.dp))}

        //display all purchasable items by category
        items(equipFragVM.allCategoryData){category ->
            CategoryButton(
                category = category,
                equipFragVM = equipFragVM
            )
        }

        item{Spacer(modifier = Modifier.height(20.dp))}

        item{
            GeneralCard{
                //current inventory label
                Text(
                    text = stringResource(id = R.string.inventoryHeader),
                    textAlign = TextAlign.Center
                )

                //display current inventory
                equipFragVM.boughtGoods.forEach{equipment ->
                    HeldItemRow(
                        equipment = equipment,
                        equipFragVM = equipFragVM
                    )
                }
            }
        }

        item{Spacer(modifier = Modifier.height(15.dp))}
    }

    //display purchase options alert if currently visible
    if(equipFragVM.itemPurchaseOpen.collectAsState().value)
        EquipmentItemPurchase(equipFragVM = equipFragVM)

    //display item details if requested
    if(equipFragVM.detailAlertOpen.collectAsState().value)
        DetailAlert(
            title = stringResource(id = equipFragVM.detailTitle.collectAsState().value),
            item = equipFragVM.detailItem.collectAsState().value!!
        ){equipFragVM.toggleDetailAlertOpen()}
}

/**
 * Displays a maximum coin input item.
 *
 * @param maxData data in regards to the coin type
 */
@Composable
fun MaximumDisplay(
    maxData: EquipmentFragmentViewModel.MaximumData
){
    Row (
        modifier = Modifier
            .fillMaxWidth(0.7f),
        verticalAlignment = Alignment.CenterVertically
    ){
        //display header
        Text(
            text = stringResource(id = maxData.nameRef),
            modifier = Modifier
                .weight(0.25f)
        )

        //display input
        NumberInput(
            inputText = maxData.maxValue.collectAsState().value,
            inputFunction = {maxData.setMaxValue(maxCoin = it.toInt())},
            emptyFunction = {maxData.setMaxValue(display = "")},
            refill = {maxData.maxInput()},
            modifier = Modifier
                .weight(0.45f)
        )
    }
}

/**
 * Displays a button that displays all purchasable items in its category.
 *
 * @param category category data for this section
 * @param equipFragVM viewModel managing this section
 */
@Composable
fun CategoryButton(
    category: EquipmentFragmentViewModel.CategoryData,
    equipFragVM: EquipmentFragmentViewModel
){
    //create button that displays category name
    Button(
        onClick = {category.toggleCatOpen()},
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        Text(text = stringResource(id = category.nameRef))
    }

    //list of items in this category that can be purchased
    AnimatedVisibility(visible = category.catOpen.collectAsState().value) {
        GeneralCard{
            //create a row for each item
            category.equipmentList.itemsAvailable.forEach{ equipment ->
                EquipmentRow(
                    item = equipment,
                    ownCategory = category.equipmentList,
                    equipFragVM = equipFragVM
                )
            }
        }
    }
}

/**
 * Row that displays a purchasable piece of equipment.
 *
 * @param item piece of equipment to display
 * @param ownCategory category the item is from
 * @param equipFragVM viewModel that manages this page
 */
@Composable
fun EquipmentRow(
    item: GeneralEquipment,
    ownCategory: GeneralCategory,
    equipFragVM: EquipmentFragmentViewModel
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //button to add item
        Button(
            onClick = {
                equipFragVM.toggleItemPurchaseOpen()
                equipFragVM.setPurchasedItem(equipment = item)
                equipFragVM.setPurchasingCategory(category = ownCategory)
            },
            modifier = Modifier
                .weight(0.2f)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Item"
            )
        }

        //display equipment name
        Text(
            text = stringResource(id = item.name),
            modifier = Modifier
                .weight(0.35f),
            textAlign = TextAlign.Center
        )

        //display item's base cost
        Text(text =
                when(item.coinType){
                    CoinType.Copper -> stringResource(id = R.string.copperLabel, item.baseCost)
                    CoinType.Silver -> stringResource(id = R.string.silverLabel, item.baseCost)
                    CoinType.Gold -> stringResource(id = R.string.goldLabel, item.baseCost)
                },
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )

        //display details button
        DetailButton(
            onClick = {
                equipFragVM.setDetailItem(equipment = item)
                equipFragVM.toggleDetailAlertOpen()
            },
            modifier = Modifier
                .weight(0.25f)
        )
    }
}

/**
 * Row that displays the amount of the given coin spent.
 *
 * @param coinType kind of coin displayed in this row
 * @param amount number of this coin type spent
 * @param modifier code to alter the form of the item
 */
@Composable
fun SpentDisplay(
    coinType: CoinType,
    amount: Int,
    modifier: Modifier
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.spentAmount, coinType.name),
            modifier = Modifier
                .weight(0.6f),
            textAlign = TextAlign.Center,
            maxLines = 1
        )

        Spacer(modifier = Modifier.weight(0.1f))

        AnimatedContent(
            targetState = amount,
            modifier = Modifier
                .weight(0.3f),
            transitionSpec = numberScroll,
            label = "${coinType.name}Spent"
        ){
            Text(
                text = "$it",
                textAlign = TextAlign.Left
            )
        }
    }
}

/**
 * Row that displays a character's currently purchased equipment.
 *
 * @param equipment item in the character's inventory
 * @param equipFragVM viewModel that manages this fragment
 */
@Composable
fun HeldItemRow(
    equipment: GeneralEquipment,
    equipFragVM: EquipmentFragmentViewModel
){
    //create name of the item to include quality, if any
    val titleString = stringResource(id = equipment.name) +
        if(equipment.currentQuality != null)
            " " + stringResource(id = equipFragVM.getCategory(equipment)!!.qualityInput!![equipment.currentQuality].qualityType)
        else ""

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //display item removal button
        Button(
            onClick = {equipFragVM.removeItem(equipment = equipment)},
            modifier = Modifier
                .weight(0.2f)
        )
        {Icon(imageVector = Icons.Filled.Clear, contentDescription = "Remove Item")}

        //display item name
        Text(
            text = titleString,
            modifier = Modifier
                .weight(0.55f),
            textAlign = TextAlign.Center
        )

        //display number of the held item
        AnimatedContent(
            targetState = equipFragVM.getQuantity(equipment = equipment)!!,
            modifier = Modifier
                .weight(0.25f),
            transitionSpec = numberScroll,
            label = titleString + "Amount"
        ) {
            Text(
                text = "$it",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun EquipmentPreview(){
    val charInstance = BaseCharacter()
    val equipFragVM = EquipmentFragmentViewModel(charInstance.inventory)

    equipFragVM.allCategoryData[8].toggleCatOpen()

    EquipmentFragment(equipFragVM)
}