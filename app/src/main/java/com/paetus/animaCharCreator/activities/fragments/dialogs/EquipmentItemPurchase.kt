package com.paetus.animaCharCreator.activities.fragments.dialogs

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paetus.animaCharCreator.InfoRow
import com.paetus.animaCharCreator.NumberInput
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.view_models.models.EquipmentFragmentViewModel

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
        stringResource(R.string.purchaseDialogHeader),
        {
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                //input for number of the item bought
                item {
                    NumberInput(
                        inputText = equipFragVM.purchasedNumber.collectAsState().value,
                        inputFunction = { equipFragVM.setPurchasedNumber(it.toInt()) },
                        emptyFunction = { equipFragVM.setPurchasedNumber("") },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                    )
                }

                //quality options of the item bought, if available
                if(equipFragVM.currentQuality.value != null) {
                    items(equipFragVM.purchasingCategory.value!!.qualityInput!!){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.6f),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            RadioButton(
                                selected = it == equipFragVM.currentQuality.collectAsState().value,
                                onClick = {equipFragVM.setCurrentQuality(it)},
                                modifier = Modifier
                                    .weight(0.1f)
                            )
                            Text(
                                text = stringResource(it.qualityType),
                                modifier = Modifier
                                    .weight(0.2f)
                                    .clickable{equipFragVM.setCurrentQuality(it)}
                            )
                        }
                    }
                }

                item{Spacer(Modifier.height(10.dp))}

                //display total purchase cost
                item{
                    InfoRow(
                        CoinType.Gold.name,
                        equipFragVM.totalGoldPurchase.collectAsState().value
                    )
                }

                item{
                    InfoRow(
                        CoinType.Silver.name,
                        equipFragVM.totalSilverPurchase.collectAsState().value
                    )
                }

                item{
                    InfoRow(
                        CoinType.Copper.name,
                        equipFragVM.totalCopperPurchase.collectAsState().value
                    )
                }
            }

            //close dialog on back press
            BackHandler{equipFragVM.toggleItemPurchaseOpen()}
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

@Preview
@Composable
fun PurchasePreview(){
    val charInstance = BaseCharacter()

    val equipFragVM = EquipmentFragmentViewModel(charInstance.inventory)
    equipFragVM.setPurchasedItem(charInstance.inventory.weapons.arquebus)
    equipFragVM.setPurchasingCategory(charInstance.inventory.weapons)

    EquipmentItemPurchase(equipFragVM)
}