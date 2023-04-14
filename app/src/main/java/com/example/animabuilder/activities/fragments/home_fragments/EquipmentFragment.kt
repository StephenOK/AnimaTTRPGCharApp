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

@Composable
fun EquipmentFragment(
    equipFragVM: EquipmentFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
) {
    LazyColumn{
        item{
            Text(text = stringResource(R.string.maxGoldLabel))
            NumberInput(
                inputText = equipFragVM.maxExpenditure.collectAsState().value,
                preRun = {},
                inputFunction = {equipFragVM.setMaxExpenditure(it.toInt())},
                emptyFunction = {equipFragVM.setMaxExpenditure("")},
                postRun = {},
                colorInput = Color.Black,
                modifier = Modifier
            )
        }

        items(equipFragVM.allCategoryData){
            CategoryButton(equipFragVM, it, openDetailAlert)
        }

        //display spent coin
        item{
            SpentDisplay(CoinType.Gold, equipFragVM.getCoinSpent(CoinType.Gold))
            SpentDisplay(CoinType.Silver, equipFragVM.getCoinSpent(CoinType.Silver))
            SpentDisplay(CoinType.Copper, equipFragVM.getCoinSpent(CoinType.Copper))
        }

        //display current inventory
        items(equipFragVM.boughtGoods){
            HeldItemRow(equipFragVM, it)
        }
    }

    if(equipFragVM.itemPurchaseOpen.collectAsState().value)
        EquipmentItemPurchase(equipFragVM)
}

@Composable
fun CategoryButton(
    equipFragVM: EquipmentFragmentViewModel,
    item: EquipmentFragmentViewModel.CategoryData,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit
){
    Button(onClick = {item.toggleCatOpen()}){
        Text(text = stringResource(item.nameRef))
    }

    AnimatedVisibility(visible = item.catOpen.collectAsState().value) {
        Column{
            item.reference.itemsAvailable.forEach{
                EquipmentRow(equipFragVM, it, item.reference, openDetailAlert)
            }
        }
    }
}

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

@Composable
fun SpentDisplay(type: CoinType, value: Int){
    Text(text = type.name + ": $value")
}

@Composable
fun HeldItemRow(
    equipFragVM: EquipmentFragmentViewModel,
    input: GeneralEquipment
){
    val titleString = input.name +
        if(input.currentQuality != null)
            " " + equipFragVM.getCategory(input)!!.qualityInput!![input.currentQuality].qualityType
        else ""

    Row{
        Button(onClick = {equipFragVM.removeItem(input)})
        {Icon(imageVector = Icons.Filled.Clear, contentDescription = "Add Item")}

        Text(text = titleString)

        Text(text = equipFragVM.getQuantity(input).toString())
    }
}

val EquipmentDetails = @Composable {item: GeneralEquipment ->
    val priceString = item.baseCost.toString() +
            when(item.coinType){
                CoinType.Gold -> " GC"
                CoinType.Silver -> " SC"
                CoinType.Copper -> " CC"
            }

    Column{
        Row{Text(text = stringResource(R.string.basePriceLabel) + priceString)}

        if(item.weight != null)
            Row{Text(text = stringResource(R.string.weightLabel) + item.weight.toString())}

        Row{Text(text = stringResource(R.string.availabilityLabel) + item.availability.name)}
    }
}