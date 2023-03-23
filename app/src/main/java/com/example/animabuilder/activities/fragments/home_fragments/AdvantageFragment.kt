package com.example.animabuilder.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.animabuilder.DetailButton
import com.example.animabuilder.activities.fragments.dialogs.AdvantageCostPick
import com.example.animabuilder.character_creation.attributes.advantages.advantage_types.Advantage
import com.example.animabuilder.view_models.AdvantageFragmentViewModel

/**
 * Section that displays advantage and disadvantage information to the user
 * Allows the user to select advantages and disadvantages for their character
 * Also displays advantages of their character's current race
 */

@Composable
fun AdvantageFragment(
    advantageFragVM: AdvantageFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateBottomBar: () -> Unit
){
    //initialize local context
    val context = LocalContext.current

    LazyColumn{
        //display creation points remaining
        item{Text(text = "Creation Points: " + advantageFragVM.creationPoints.collectAsState().value)}

        //display each list of base advantages
        items(advantageFragVM.advantageButtons){
            AdvantageDisplay(
                advantageFragVM,
                it,
                openDetailAlert,
                updateBottomBar
            )
        }

        //display all of the character's taken advantages
        items(advantageFragVM.takenAdvantages){
            HeldAdvantageDisplay(
                advantageFragVM,
                it,
                openDetailAlert,
                updateBottomBar
            )
        }

        //display all advantages acquired by their race
        item{Text(text = "Racial Advantages")}
        items(advantageFragVM.getRacialAdvantages()){
            AdvantageRow(it, null, openDetailAlert, {}, {})
        }
    }

    //display for advantage adjustment
    if(advantageFragVM.advantageCostOn.collectAsState().value)
        AdvantageCostPick(
            advantageFragVM
        ){input: String? ->
            if(input != null)
                Toast.makeText(context, input, Toast.LENGTH_LONG).show()

            updateBottomBar()
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
    advantageFragVM: AdvantageFragmentViewModel,
    advantageList: AdvantageFragmentViewModel.AdvantageButtonData,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateBottomBar: () -> Unit
){
    val context = LocalContext.current

    Column {
        //button to open advantage list
        Button(onClick = {advantageList.setOpen(!advantageList.isOpen.value)}) {
            //display category name
            Text(text = stringResource(advantageList.category))
        }

        //displayable list of options
        AnimatedVisibility(visible = advantageList.isOpen.collectAsState().value) {
            Column {
                advantageList.items.forEach {
                    //display advantage as an obtainable item
                    AdvantageRow(
                        it,
                        null,
                        openDetailAlert,
                        {Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Advantage")})
                    {
                        if(it.options != null || it.cost.size > 1) {
                            //activate dialog if multiple options available
                            if (it.options != null)
                                advantageFragVM.setAdjustingPage(1)
                            //activate dialog if multiple costs available
                            else
                                advantageFragVM.setAdjustingPage(2)

                            advantageFragVM.setAdjustedAdvantage(it)
                            advantageFragVM.setAdvantageCostOn(true)
                        }
                        //attempt to add the base advantage to the character
                        else {
                            val resultText = advantageFragVM.acquireAdvantage(
                                it,
                                it.picked,
                                it.pickedCost
                            )

                            //display text if unable to add advantage
                            if (resultText != null)
                                Toast.makeText(context, resultText, Toast.LENGTH_LONG).show()
                            else
                                updateBottomBar()
                        }
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
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    button: @Composable () -> Unit,
    buttonAction:() -> Unit,
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
        DetailButton(
            onClick = {openDetailAlert(nameString){AdvantageDetails(item)}},
            modifier = Modifier
        )
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
    advantageFragVM: AdvantageFragmentViewModel,
    item: Advantage,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateBottomBar: () -> Unit
){
    //retrieve additional information on the advantage
    val nameAddition =
        if(item.picked != null)
            " (${item.options!![item.picked]})"
        else null

    //display the advantage row
    AdvantageRow(
        item,
        nameAddition,
        openDetailAlert,
        {Icon(imageVector = Icons.Filled.Close, contentDescription = "Add Advantage")}
    )
    {
        advantageFragVM.removeAdvantage(item)
        updateBottomBar()
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