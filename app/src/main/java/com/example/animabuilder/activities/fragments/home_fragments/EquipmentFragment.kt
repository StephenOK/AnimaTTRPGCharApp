package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.animabuilder.DetailButton
import com.example.animabuilder.NumberInput
import com.example.animabuilder.R
import com.example.animabuilder.activities.fragments.dialogs.EquipmentItemPurchase
import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.character_creation.equipment.general_goods.Availability
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralCategory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment
import com.example.animabuilder.view_models.EquipmentFragmentViewModel

/**
 * Fragment that manages the character's inventory.
 *
 * @param equipFragVM viewModel that manages the character's data
 * @param openDetailAlert method to run when opening an item's details
 */
@Composable
fun EquipmentFragment(
    equipFragVM: EquipmentFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
) {
    LazyColumn{
        //create inputs for each maximum coin expenditure
        items(equipFragVM.allQuantityMaximums){MaximumDisplay(it)}

        //display spent coin
        item{
            Row {
                SpentDisplay(CoinType.Gold, equipFragVM.getCoinSpent(CoinType.Gold))
                SpentDisplay(CoinType.Silver, equipFragVM.getCoinSpent(CoinType.Silver))
                SpentDisplay(CoinType.Copper, equipFragVM.getCoinSpent(CoinType.Copper))
            }
        }

        //display all purchasable items by category
        items(equipFragVM.allCategoryData){
            CategoryButton(equipFragVM, it, openDetailAlert)
        }

        //display current inventory
        items(equipFragVM.boughtGoods){
            HeldItemRow(equipFragVM, it)
        }
    }

    //display purchase options alert if currently visible
    if(equipFragVM.itemPurchaseOpen.collectAsState().value)
        EquipmentItemPurchase(equipFragVM)
}

/**
 * Displays a maximum coin input item.
 *
 * @param input data in regards to the coin type
 */
@Composable
fun MaximumDisplay(input: EquipmentFragmentViewModel.MaximumData){
    //display header
    Text(text = stringResource(input.nameRef))

    //display input
    NumberInput(
        inputText = input.maxValue.collectAsState().value,
        preRun = {},
        inputFunction = {input.setMaxValue(it.toInt())},
        emptyFunction = {input.setMaxValue("")},
        postRun = {},
        colorInput = Color.Black,
        modifier = Modifier
    )
}

/**
 * Displays a button that displays all purchasable items in its category.
 *
 * @param equipFragVM viewModel managing this section
 * @param item category data for this section
 * @param openDetailAlert function to run when looking at an item's details
 */
@Composable
fun CategoryButton(
    equipFragVM: EquipmentFragmentViewModel,
    item: EquipmentFragmentViewModel.CategoryData,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
){
    //create button that displays category name
    Button(onClick = {item.toggleCatOpen()}){
        Text(text = stringResource(item.nameRef))
    }

    //list of items in this category that can be purchased
    AnimatedVisibility(visible = item.catOpen.collectAsState().value) {
        Column{
            //create a row for each item
            item.reference.itemsAvailable.forEach{
                EquipmentRow(equipFragVM, it, item.reference, openDetailAlert)
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
 * @param openDetailAlert function to run on looking at the equipment details
 */
@Composable
fun EquipmentRow(
    equipFragVM: EquipmentFragmentViewModel,
    item: GeneralEquipment,
    ownCategory: GeneralCategory,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
){
    Row{
        //button to add item
        Button(onClick = {
            equipFragVM.toggleItemPurchaseOpen()
            equipFragVM.setPurchasedItem(item)
            equipFragVM.setPurchasingCategory(ownCategory)
        }) {Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Item")}

        //display equipment name
        Text(text = item.name)

        //display item's base cost
        Text(text = item.baseCost.toString() +
                when(item.coinType){
                    CoinType.Copper -> " CC"
                    CoinType.Silver -> " SC"
                    CoinType.Gold -> " GC"
                }
        )

        //display item's weight, if available
        if(item.weight != null)
            Text(text = item.weight.toString())
        else
            Spacer(Modifier.weight(0.1f))

        //display item's rarity
        Text(text = when(item.availability){
            Availability.Common -> ""
            Availability.Uncommon -> "U"
            Availability.Rare -> "R"
        })

        //display details button
        DetailButton(
            onClick = {openDetailAlert(item.name){EquipmentDetails(item)}},
            modifier = Modifier
        )
    }
}

/**
 * Row that displays the amount of the given coin spent.
 *
 * @param type kind of coin displayed in this row
 * @param value amount of coin spent
 */
@Composable
fun SpentDisplay(type: CoinType, value: Int){
    Text(text = type.name + ": $value")
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

    Row{
        //display item removal button
        Button(onClick = {equipFragVM.removeItem(input)})
        {Icon(imageVector = Icons.Filled.Clear, contentDescription = "Remove Item")}

        //display item name
        Text(text = titleString)

        //display number of the held item
        Text(text = equipFragVM.getQuantity(input).toString())
    }
}

//details composable for a piece of equipment
val EquipmentDetails = @Composable {item: GeneralEquipment ->
    //create price of the object
    val priceString = item.baseCost.toString() +
            when(item.coinType){
                CoinType.Gold -> " GC"
                CoinType.Silver -> " SC"
                CoinType.Copper -> " CC"
            }

    Column{
        //display item's cost
        Row{Text(text = stringResource(R.string.basePriceLabel) + priceString)}

        //display item's weight, if any given
        if(item.weight != null)
            Row{Text(text = stringResource(R.string.weightLabel) + item.weight.toString())}

        //display item's availability
        Row{Text(text = stringResource(R.string.availabilityLabel) + item.availability.name)}
    }
}