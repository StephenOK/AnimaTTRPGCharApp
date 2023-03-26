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
 * Section that displays advantage and disadvantage information to the user.
 * Allows the user to select advantages and disadvantages for their character.
 * Also displays advantages of their character's current race.
 *
 * @param advantageFragVM viewModel to run with this fragment
 * @param openDetailAlert function to run when opening an item's details
 * @param updateBottomBar function to run to update the screen's bottom bar
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

        //display all of the character's taken advantages and disadvantages
        items(advantageFragVM.takenAdvantages){
            HeldAdvantageDisplay(
                advantageFragVM,
                it,
                openDetailAlert,
                updateBottomBar
            )
        }

        //display all advantages acquired from their race
        item{Text(text = "Racial Advantages")}
        items(advantageFragVM.getRacialAdvantages()){
            AdvantageRow(it, null, openDetailAlert, {}, {})
        }
    }

    //dialog for advantage adjustment
    if(advantageFragVM.advantageCostOn.collectAsState().value)
        AdvantageCostPick(
            advantageFragVM
        ){input: String? ->
            //notify user of failed acquisition
            if(input != null)
                Toast.makeText(context, input, Toast.LENGTH_LONG).show()

            updateBottomBar()
        }
}

/**
 * Creates a button that displays the base advantages or disadvantages in the input.
 *
 * @param advantageFragVM viewModel for the advantage fragment
 * @param advantageList advantage data to display
 * @param openDetailAlert function to run when opening an item's details
 * @param updateBottomBar function to run to update the screen's bottom bar
 */
@Composable
private fun AdvantageDisplay(
    advantageFragVM: AdvantageFragmentViewModel,
    advantageList: AdvantageFragmentViewModel.AdvantageButtonData,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateBottomBar: () -> Unit
){
    //get local context
    val context = LocalContext.current

    Column {
        //button to open advantage list
        Button(onClick = {advantageList.toggleOpen() }) {
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
                        //if more selections need to be made
                        if(it.options != null || it.cost.size > 1) {
                            //activate dialog if multiple options available
                            if (it.options != null)
                                advantageFragVM.setAdjustingPage(1)
                            //activate dialog if multiple costs available
                            else
                                advantageFragVM.setAdjustingPage(2)

                            //open cost selection dialog for this advantage
                            advantageFragVM.setAdjustedAdvantage(it)
                            advantageFragVM.toggleAdvantageCostOn()
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
 * Creates a row for an advantage where the user can add or remove it, depending on the inputted
 * button action.
 *
 * @param item advantage in this display
 * @param takenAddition potential additional information to display
 * @param openDetailAlert function to run when opening an item's details
 * @param button display image on the row's button
 * @param buttonAction what the button does in its context
 */
@Composable
private fun AdvantageRow(
    item: Advantage,
    takenAddition: String?,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    button: @Composable () -> Unit,
    buttonAction: () -> Unit,
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
 * Display for an already acquired advantage.
 *
 * @param advantageFragVM viewModel that holds data for this object
 * @param item advantage to display
 * @param openDetailAlert function to run when opening an item's details
 * @param updateBottomBar function to run to update the screen's bottom bar
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