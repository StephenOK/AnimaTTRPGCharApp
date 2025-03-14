package com.paetus.animaCharCreator.activities.fragments.dialogs

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paetus.animaCharCreator.composables.InfoRow
import com.paetus.animaCharCreator.composables.NumberInput
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.numberScroll
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
        dialogTitle = stringResource(id = R.string.purchaseDialogHeader),
        mainContent = {
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                //input for number of the item bought
                item {
                    NumberInput(
                        inputText = equipFragVM.purchasedNumber.collectAsState().value,
                        inputFunction = {equipFragVM.setPurchasedNumber(quantity = it.toInt())},
                        emptyFunction = {equipFragVM.setPurchasedNumber(display = "")},
                        refill = {1},
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                    )
                }

                //quality options of the item bought, if available
                if(equipFragVM.currentQuality.value != null) {
                    items(equipFragVM.purchasingCategory.value!!.qualityInput!!){quality ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.6f),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            RadioButton(
                                selected = quality == equipFragVM.currentQuality.collectAsState().value,
                                onClick = {equipFragVM.setCurrentQuality(quality = quality)},
                                modifier = Modifier
                                    .weight(0.1f),
                                colors = RadioButtonDefaults.colors(
                                    unselectedColor = MaterialTheme.colorScheme.onSurface
                                )
                            )
                            Text(
                                text = stringResource(id = quality.qualityType),
                                modifier = Modifier
                                    .weight(0.2f)
                                    .clickable {equipFragVM.setCurrentQuality(quality = quality)}
                            )
                        }
                    }
                }

                item{Spacer(modifier = Modifier.height(10.dp))}

                //gold cost of purchase
                item{
                    InfoRow(
                        label = CoinType.Gold.name
                    ){modifier, _ ->
                        AnimatedContent(
                            targetState = equipFragVM.totalGoldPurchase.collectAsState().value,
                            modifier = modifier,
                            transitionSpec = numberScroll,
                            label = "dialogGoldSpent"
                        ) {
                            Text(text = "$it")
                        }
                    }
                }

                //silver cost of purchase
                item{
                    InfoRow(
                        label = CoinType.Silver.name
                    ){modifier, _ ->
                        AnimatedContent(
                            targetState = equipFragVM.totalSilverPurchase.collectAsState().value,
                            modifier = modifier,
                            transitionSpec = numberScroll,
                            label = "dialogSilverSpent"
                        ) {
                            Text(text = "$it")
                        }
                    }
                }

                //copper cost of purchase
                item{
                    InfoRow(
                        label = CoinType.Copper.name
                    ){modifier, _ ->
                        AnimatedContent(
                            targetState = equipFragVM.totalCopperPurchase.collectAsState().value,
                            modifier = modifier,
                            transitionSpec = numberScroll,
                            label = "dialogCopperSpent"
                        ) {
                            Text(text = "$it")
                        }
                    }
                }
            }

            //close dialog on back press
            BackHandler{equipFragVM.toggleItemPurchaseOpen()}
        },
        buttonContent = {
            //button to confirm purchase
            TextButton(
                onClick = {equipFragVM.purchaseItems()}
            ){
                Text(
                    text = stringResource(id = R.string.confirmLabel),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            //button to cancel purchase
            TextButton(
                onClick = {equipFragVM.toggleItemPurchaseOpen()}
            ){
                Text(
                    text = stringResource(id = R.string.cancelLabel),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}

@Preview
@Composable
fun PurchasePreview(){
    val charInstance = BaseCharacter()

    val equipFragVM = EquipmentFragmentViewModel(charInstance.inventory)
    equipFragVM.setPurchasedItem(charInstance.inventory.weapons.itemsAvailable[57])
    equipFragVM.setPurchasingCategory(charInstance.inventory.weapons)

    EquipmentItemPurchase(equipFragVM)
}