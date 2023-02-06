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
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.animabuilder.activities.charInstance
import com.example.animabuilder.activities.contents
import com.example.animabuilder.activities.detailAlertOn
import com.example.animabuilder.activities.detailItem
import com.example.animabuilder.activities.fragments.dialogs.AdvantageCostPick
import com.example.animabuilder.character_creation.attributes.advantages.advantage_types.Advantage

/**
 * Section that displays advantage and disadvantage information to the user
 * Allows the user to select advantages and disadvantages for their character
 * Also displays advantages of their character's current race
 */

@Composable
fun AdvantageFragment(updateBottomBar: () -> Unit){
    //initialize local context
    val context = LocalContext.current

    //initialize creation point display
    val creationPoints = remember{mutableStateOf((3 - charInstance.advantageRecord.creationPointSpent).toString())}

    //initialize advantage dialog values
    val advantageAdjustOn = remember{mutableStateOf(false)}
    val advantageToAdjust = remember{mutableStateOf<Advantage?>(null)}
    val advantageAdjustInput = remember{mutableStateOf(1)}

    //initialize list of acquired advantages
    val takenAdvantages = remember{charInstance.advantageRecord.takenAdvantages.toMutableStateList()}

    //create list of base advantages and disadvantages
    val advantageTable = mutableListOf<AdvantageList>()

    advantageTable.add(AdvantageList("Common Advantages", charInstance.advantageRecord.commonAdvantages.advantages))
    advantageTable.add(AdvantageList("Magic Advantages", charInstance.advantageRecord.magicAdvantages.advantages))
    advantageTable.add(AdvantageList("Psychic Advantages", charInstance.advantageRecord.psychicAdvantages.advantages))

    advantageTable.add(AdvantageList("Common Disadvantages", charInstance.advantageRecord.commonAdvantages.disadvantages))
    advantageTable.add(AdvantageList("Magic Disadvantages", charInstance.advantageRecord.magicAdvantages.disadvantages))
    advantageTable.add(AdvantageList("Psychic Disadvantages", charInstance.advantageRecord.psychicAdvantages.disadvantages))

    LazyColumn{
        //display creation points remaining
        item{Text(text = "Creation Points: " + creationPoints.value)}

        //display each list of base advantages
        items(advantageTable){
            AdvantageDisplay(
                it,
                context,
                creationPoints,
                {updateBottomBar(); takenAdvantages.clear(); takenAdvantages.addAll(charInstance.advantageRecord.takenAdvantages.toList())})
            {input, pageNum ->
                advantageAdjustOn.value = true
                advantageToAdjust.value = input
                advantageAdjustInput.value = pageNum
            }
        }

        //display all of the character's taken advantages
        items(takenAdvantages){
            HeldAdvantageDisplay(it, creationPoints)
            { updateBottomBar(); takenAdvantages.clear(); takenAdvantages.addAll(charInstance.advantageRecord.takenAdvantages.toList()) }
        }

        //display all advantages acquired by their race
        item{Text(text = "Racial Advantages")}
        items(charInstance.ownRace.raceAdvantages){
            AdvantageRow(it, null, {}, {})
        }
    }

    //display for advantage adjustment
    if(advantageAdjustOn.value)
        AdvantageCostPick(advantageToAdjust.value!!, advantageAdjustInput.value)
        {input: String? ->
            if(input != null)
                Toast.makeText(context, input, Toast.LENGTH_LONG).show()

            updateBottomBar()

            takenAdvantages.clear()
            takenAdvantages.addAll(charInstance.advantageRecord.takenAdvantages.toList())
            creationPoints.value = (3 - charInstance.advantageRecord.creationPointSpent).toString()
            advantageAdjustOn.value = false
        }
}

/**
 * Creates a button that displays the base advantages or disadvantages in the input
 *
 * advantageList: data class that holds data on the category name and its contents
 * context: phone context to display Toast messages to
 * creationPoints: displayed string for the character's current creation points
 * updateTaken: hoisted function to run updates for the bottom bar and taken advantage list
 * activateAdjustment: function to run to initiate advantage dialog
 */
@Composable
private fun AdvantageDisplay(
    advantageList: AdvantageList,
    context: Context,
    creationPoints: MutableState<String>,
    updateTaken: () -> Unit,
    activateAdjustment: (Advantage, Int) -> Unit
){
    //initialize display open state
    val listOpen = remember{mutableStateOf(false)}

    Column {
        //button to open advantage list
        Button(onClick = {listOpen.value = !listOpen.value}) {
            //display category name
            Text(text = advantageList.category)
        }

        //displayable list of options
        AnimatedVisibility(visible = listOpen.value) {
            Column {
                advantageList.items.forEach {
                    //display advantage as an obtainable item
                    AdvantageRow(it, null, {Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Advantage")})
                    {
                        //activate dialog if multiple options available
                        if(it.options != null)
                            activateAdjustment(it, 1)

                        //activate dialog if multiple costs available
                        else if(it.cost.size > 1)
                            activateAdjustment(it, 2)

                        //attempt to add the base advantage to the character
                        else {
                            val resultText = charInstance.advantageRecord.acquireAdvantage(
                                it,
                                it.picked,
                                it.pickedCost
                            )

                            //display text if unable to add advantage
                            if (resultText != null)
                                Toast.makeText(context, resultText, Toast.LENGTH_LONG).show()
                            else
                                updateTaken()
                        }

                        //change creation points as needed
                        creationPoints.value = (3 - charInstance.advantageRecord.creationPointSpent).toString()
                    }
                }
            }
        }
    }
}

/**
 * Creates a row to display an advantage to the user
 *
 * item: target of the display
 * takenAddition: potential additional information about the advantage
 * button: display image on the row's button
 * buttonAction: what the button does in its context
 */
@Composable
private fun AdvantageRow(
    item: Advantage,
    takenAddition: String?,
    button: @Composable () -> Unit,
    buttonAction:() -> Unit
){
    //initialize the advantage's name with potential additional information
    val nameString =
        if(takenAddition != null) item.name + takenAddition
        else item.name

    Row{
        //implement button with given image and function
        Button(onClick = {buttonAction()}) {button()}

        //display advantage information
        Text(text = nameString)

        //details button
        TextButton(onClick = {
            detailItem.value = item.name
            contents.value = @Composable{ AdvantageDetails(item) }
            detailAlertOn.value = true
        }) {
            Text(text = "Details")
        }
    }
}

/**
 * Display row for an already acquired advantage
 *
 * item: advantage to display
 * creationPoints: display of character's current creation points
 * updateTaken: update function to run after an action
 */
@Composable
private fun HeldAdvantageDisplay(
    item: Advantage,
    creationPoints: MutableState<String>,
    updateTaken: () -> Unit
){
    //retrieve additional information on the advantage
    val nameAddition =
        if(item.picked != null)
            " (" + item.options!![item.picked] + ")"
        else null

    //display the advantage row
    AdvantageRow(item, nameAddition, {Icon(imageVector = Icons.Filled.Close, contentDescription = "Add Advantage")})
    {
        charInstance.advantageRecord.removeAdvantage(item)
        updateTaken()
        creationPoints.value = (3 - charInstance.advantageRecord.creationPointSpent).toString()
    }
}

//detail page for an advantage
val AdvantageDetails = @Composable{item: Advantage ->
    //retrieve the costs of the item
    var costString = ""
    item.cost.forEach{
        costString += "$it "
    }

    Column{
        //display advantage's description
        Row{Text(text = item.description)}

        //display advantage's effect, if one given
        if(item.effect != null)
            Row{Text(text = "Effect: " + item.effect)}

        //display advantage's restriction, if one given
        if(item.restriction != null)
            Row{Text(text = "Restriction: " + item.restriction)}

        //display advantage's special, if one given
        if(item.special != null)
            Row{Text(text = "Special: " + item.special)}

        //display available costs
        Row{Text(text = "Cost: $costString")}
    }
}

/**
 * Data class relating to a list of advantages
 *
 * category: type of advantages held in the list
 * items: list of advantages held here
 */
private data class AdvantageList(
    val category: String,
    val items: List<Advantage>
)