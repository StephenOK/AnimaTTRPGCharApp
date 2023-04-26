package com.example.animabuilder.activities.fragments.dialogs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.animabuilder.NumberInput
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.view_models.EquipmentFragmentViewModel
import kotlinx.coroutines.flow.StateFlow

/**
 * Dialog that allows the user to determine a purchased item's quality and quantity.
 *
 * @param equipFragVM viewModel that manages this object's data
 */
@Composable
fun EquipmentItemPurchase(
    equipFragVM: EquipmentFragmentViewModel
){
    DialogFrame(
        "Purchase Details",
        {
            LazyColumn {
                //input for number of the item bought
                item {
                    NumberInput(
                        inputText = equipFragVM.purchasedNumber.collectAsState().value,
                        inputFunction = { equipFragVM.setPurchasedNumber(it.toInt()) },
                        emptyFunction = { equipFragVM.setPurchasedNumber("") }
                    )
                }

                //quality options of the item bought, if available
                if(equipFragVM.currentQuality.value != null) {
                    items(equipFragVM.purchasingCategory.value!!.qualityInput!!){
                        Row {
                            RadioButton(
                                selected = it == equipFragVM.currentQuality.collectAsState().value,
                                onClick = {equipFragVM.setCurrentQuality(it)}
                            )
                            Text(text = it.qualityType)
                        }
                    }
                }

                //display total purchase  cost
                item {
                    Row {
                        TotalDisplay(CoinType.Gold, equipFragVM.totalGoldPurchase)
                        TotalDisplay(CoinType.Silver, equipFragVM.totalSilverPurchase)
                        TotalDisplay(CoinType.Copper, equipFragVM.totalCopperPurchase)
                    }
                }
            }
        },
        {
            //button to confirm purchase
            TextButton(onClick = {equipFragVM.purchaseItems()}) {
                Text(text = stringResource(R.string.confirmLabel))
            }

            //button to cancel purchase
            TextButton(onClick = {equipFragVM.toggleItemPurchaseOpen()}) {
                Text(text = stringResource(R.string.cancelLabel))
            }
        }
    )
}

/**
 * Display for the cost in the indicated coin amount.
 *
 * @param value coin type to display
 * @param display string stateflow of the amount
 */
@Composable
fun TotalDisplay(
    value: CoinType,
    display: StateFlow<String>
){
    Text(text = value.name + ": ")
    Text(text = display.collectAsState().value)
}