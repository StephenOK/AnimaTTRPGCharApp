package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animabuilder.DetailButton
import com.example.animabuilder.NumberInput
import com.example.animabuilder.R
import com.example.animabuilder.activities.fragments.dialogs.DetailAlert
import com.example.animabuilder.activities.fragments.dialogs.EquipmentItemPurchase
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralCategory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment
import com.example.animabuilder.view_models.models.EquipmentFragmentViewModel

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
            .background(Color.White)
            .padding(
                top = 15.dp,
                bottom = 15.dp,
                start = 30.dp,
                end = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //create inputs for each maximum coin expenditure
        items(equipFragVM.allQuantityMaximums){MaximumDisplay(it)}

        //display bonus wealth if any taken
        if(equipFragVM.getBonusWealth() > 0){
            item{Spacer(Modifier.height(10.dp))}
            item{
                Text(
                    text =
                    stringResource(
                        R.string.bonusWealthLabel,
                        stringResource(R.string.goldLabel, equipFragVM.getBonusWealth())
                    )
                )
            }
        }

        item{Spacer(Modifier.height(20.dp))}

        //display all coin spent
        item{
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ){
                //gold spent
                SpentDisplay(
                    CoinType.Gold,
                    equipFragVM.getCoinSpent(CoinType.Gold),
                    Modifier
                        .weight(0.2f)
                )

                //silver spent
                SpentDisplay(
                    CoinType.Silver,
                    equipFragVM.getCoinSpent(CoinType.Silver),
                    Modifier
                        .weight(0.2f)
                )

                //copper spent
                SpentDisplay(
                    CoinType.Copper,
                    equipFragVM.getCoinSpent(CoinType.Copper),
                    Modifier
                        .weight(0.2f)
                )
            }
        }

        item{Spacer(Modifier.height(10.dp))}

        //display all purchasable items by category
        items(equipFragVM.allCategoryData){
            CategoryButton(equipFragVM, it)
        }

        item{Spacer(Modifier.height(20.dp))}

        //current inventory label
        item{
            Text(
                text = stringResource(R.string.inventoryHeader),
                textAlign = TextAlign.Center
            )
        }

        //display current inventory
        items(equipFragVM.boughtGoods){
            HeldItemRow(equipFragVM, it)
        }
    }

    //display purchase options alert if currently visible
    if(equipFragVM.itemPurchaseOpen.collectAsState().value)
        EquipmentItemPurchase(equipFragVM)

    //display item details if requested
    if(equipFragVM.detailAlertOpen.collectAsState().value)
        DetailAlert(
            equipFragVM.detailTitle.collectAsState().value,
            equipFragVM.detailItem.collectAsState().value!!
        ){equipFragVM.toggleDetailAlertOpen()}
}

/**
 * Displays a maximum coin input item.
 *
 * @param input data in regards to the coin type
 */
@Composable
fun MaximumDisplay(input: EquipmentFragmentViewModel.MaximumData){
    Row (
        modifier = Modifier
            .fillMaxWidth(0.7f),
        verticalAlignment = Alignment.CenterVertically
    ){
        //display header
        Text(
            text = stringResource(input.nameRef),
            modifier = Modifier
                .weight(0.25f)
        )

        //display input
        NumberInput(
            inputText = input.maxValue.collectAsState().value,
            inputFunction = { input.setMaxValue(it.toInt()) },
            emptyFunction = { input.setMaxValue("") },
            modifier = Modifier
                .weight(0.45f)
        )
    }
}

/**
 * Displays a button that displays all purchasable items in its category.
 *
 * @param equipFragVM viewModel managing this section
 * @param item category data for this section
 */
@Composable
fun CategoryButton(
    equipFragVM: EquipmentFragmentViewModel,
    item: EquipmentFragmentViewModel.CategoryData
){
    //create button that displays category name
    Button(
        onClick = {item.toggleCatOpen()},
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        Text(text = stringResource(item.nameRef))
    }

    //list of items in this category that can be purchased
    AnimatedVisibility(visible = item.catOpen.collectAsState().value) {
        Column{
            //create a row for each item
            item.reference.itemsAvailable.forEach{
                EquipmentRow(equipFragVM, it, item.reference)
            }
        }
    }
}

/**
 * Row that displays a purchasable piece of equipment.
 *
 * @param equipFragVM viewModel that manages this page
 * @param item piece of equipment to display
 * @param ownCategory category the item is from
 */
@Composable
fun EquipmentRow(
    equipFragVM: EquipmentFragmentViewModel,
    item: GeneralEquipment,
    ownCategory: GeneralCategory
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //button to add item
        Button(
            onClick = {
                equipFragVM.toggleItemPurchaseOpen()
                equipFragVM.setPurchasedItem(item)
                equipFragVM.setPurchasingCategory(ownCategory)
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
            text = item.name,
            modifier = Modifier
                .weight(0.35f),
            textAlign = TextAlign.Center
        )

        //display item's base cost
        Text(text =
                when(item.coinType){
                    CoinType.Copper -> stringResource(R.string.copperLabel, item.baseCost)
                    CoinType.Silver -> stringResource(R.string.silverLabel, item.baseCost)
                    CoinType.Gold -> stringResource(R.string.goldLabel, item.baseCost)
                },
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )

        //display details button
        DetailButton(
            onClick = {
                equipFragVM.setDetailItem(item)
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
 * @param type kind of coin displayed in this row
 * @param value amount of coin spent
 * @param modifier code to alter the form of the item
 */
@Composable
fun SpentDisplay(
    type: CoinType,
    value: Int,
    modifier: Modifier
){
    Text(
        text = stringResource(R.string.spentAmount, type.name, value),
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

/**
 * Row that displays a character's currently purchased equipment.
 *
 * @param equipFragVM viewModel that manages this fragment
 * @param input piece of purchased equipment
 */
@Composable
fun HeldItemRow(
    equipFragVM: EquipmentFragmentViewModel,
    input: GeneralEquipment
){
    //create name of the item to include quality, if any
    val titleString = input.name +
        if(input.currentQuality != null)
            " " + equipFragVM.getCategory(input)!!.qualityInput!![input.currentQuality].qualityType
        else ""

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //display item removal button
        Button(
            onClick = {equipFragVM.removeItem(input)},
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
        Text(
            text = equipFragVM.getQuantity(input).toString(),
            modifier = Modifier
                .weight(0.25f),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun EquipmentPreview(){
    val charInstance = BaseCharacter()
    val equipFragVM = EquipmentFragmentViewModel(charInstance.inventory)

    charInstance.advantageRecord.acquireAdvantage(
        charInstance.advantageRecord.commonAdvantages.startingWealth,
        null,
        2
    )

    //equipFragVM.allCategoryData[8].toggleCatOpen()

    EquipmentFragment(equipFragVM)
}