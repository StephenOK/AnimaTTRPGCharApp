package com.example.animabuilder.activities.fragments.home_fragments

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.platform.LocalContext
import com.example.animabuilder.activities.charInstance
import com.example.animabuilder.activities.contents
import com.example.animabuilder.activities.detailAlertOn
import com.example.animabuilder.activities.detailItem
import com.example.animabuilder.activities.fragments.dialogs.AdvantageCostPick
import com.example.animabuilder.character_creation.attributes.advantages.Advantage

@Composable
fun AdvantageFragment(){
    val context = LocalContext.current

    val advantageAdjustOn = remember{mutableStateOf(false)}
    val advantageToAdjust = remember{mutableStateOf<Advantage?>(null)}
    val advantageAdjustInput = remember{mutableStateOf(1)}

    val takenAdvantages = remember{charInstance.advantageRecord.takenAdvantages.toMutableStateList()}

    val advantageTable = mutableListOf<AdvantageList>()

    advantageTable.add(AdvantageList("Common Advantages", charInstance.advantageRecord.commonAdvantages.advantages))
    advantageTable.add(AdvantageList("Magic Advantages", charInstance.advantageRecord.magicAdvantages.advantages))
    advantageTable.add(AdvantageList("Psychic Advantages", charInstance.advantageRecord.psychicAdvantages.advantages))

    advantageTable.add(AdvantageList("Common Disadvantages", charInstance.advantageRecord.commonAdvantages.disadvantages))
    advantageTable.add(AdvantageList("Magic Disadvantages", charInstance.advantageRecord.magicAdvantages.disadvantages))
    advantageTable.add(AdvantageList("Psychic Disadvantages", charInstance.advantageRecord.psychicAdvantages.disadvantages))

    LazyColumn{
        items(advantageTable){
            AdvantageDisplay(it, context, {takenAdvantages.clear(); takenAdvantages.addAll(charInstance.advantageRecord.takenAdvantages.toList())})
            {input, pageNum ->
                advantageAdjustOn.value = true
                advantageToAdjust.value = input
                advantageAdjustInput.value = pageNum
            }
        }

        items(takenAdvantages){
            HeldAdvantageDisplay(it)
            { takenAdvantages.clear(); takenAdvantages.addAll(charInstance.advantageRecord.takenAdvantages.toList()) }
        }
    }

    if(advantageAdjustOn.value)
        AdvantageCostPick(advantageToAdjust.value!!, advantageAdjustInput.value)
        {
            takenAdvantages.clear()
            takenAdvantages.addAll(charInstance.advantageRecord.takenAdvantages.toList())
            advantageAdjustOn.value = false
        }
}

@Composable
private fun AdvantageDisplay(
    advantageList: AdvantageList,
    context: Context,
    updateTaken: () -> Unit,
    activateAdjustment: (Advantage, Int) -> Unit
){
    val listOpen = remember{mutableStateOf(false)}

    Column {
        Button(onClick = {listOpen.value = !listOpen.value}) {
            Text(text = advantageList.category)
        }

        AnimatedVisibility(visible = listOpen.value) {
            Column {
                advantageList.items.forEach {
                    AdvantageRow(it, null, {Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Advantage")})
                    {
                        if(it.options != null)
                            activateAdjustment(it, 1)

                        else if(it.cost.size > 1)
                            activateAdjustment(it, 2)

                        else {
                            val resultText = charInstance.advantageRecord.acquireAdvantage(
                                it,
                                it.picked,
                                it.pickedCost
                            )

                            if (resultText != null)
                                Toast.makeText(context, resultText, Toast.LENGTH_LONG).show()
                            else
                                updateTaken()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AdvantageRow(
    item: Advantage,
    takenAddition: String?,
    button: @Composable () -> Unit,
    buttonAction:() -> Unit
){

    val nameString =
        if(takenAddition != null) item.name + takenAddition
        else item.name

    Row{
        Button(onClick = {buttonAction()}) {button()}

        Text(text = nameString)

        TextButton(onClick = {
            detailItem.value = item.name
            contents.value = @Composable{ AdvantageDetails(item) }
            detailAlertOn.value = true
        }) {
            Text(text = "Details")
        }
    }
}

@Composable
private fun HeldAdvantageDisplay(item: Advantage, updateTaken: () -> Unit){
    val nameAddition =
        if(item.picked != null)
            " (" + item.options!![item.picked] + ")"
        else null

    AdvantageRow(item, nameAddition, {Icon(imageVector = Icons.Filled.Close, contentDescription = "Add Advantage")})
    {charInstance.advantageRecord.removeAdvantage(item); updateTaken()}
}

val AdvantageDetails = @Composable{item: Advantage ->
    var costString = ""
    item.cost.forEach{
        costString += "$it "
    }

    Column{
        Row{Text(text = item.description)}

        if(item.effect != null)
            Row{Text(text = "Effect: " + item.effect)}

        if(item.restriction != null)
            Row{Text(text = "Restriction: " + item.restriction)}

        if(item.special != null)
            Row{Text(text = "Special: " + item.special)}

        Row{Text(text = "Cost: $costString")}
    }
}

private data class AdvantageList(
    val category: String,
    val items: List<Advantage>
)