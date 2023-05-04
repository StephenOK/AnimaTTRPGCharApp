package com.example.animabuilder.activities.fragments.home_fragments

import android.widget.Toast
import com.example.animabuilder.R
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animabuilder.DetailButton
import com.example.animabuilder.activities.fragments.dialogs.AdvantageCostPick
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.advantages.advantage_types.Advantage
import com.example.animabuilder.view_models.models.AdvantageFragmentViewModel
import com.example.animabuilder.view_models.models.HomePageViewModel

/**
 * Section that displays advantage and disadvantage information to the user.
 * Allows the user to select advantages and disadvantages for their character.
 * Also displays advantages of their character's current race.
 *
 * @param advantageFragVM viewModel to run with this fragment
 * @param openDetailAlert function to run when opening an item's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
fun AdvantageFragment(
    advantageFragVM: AdvantageFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
){
    //initialize local context
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth()
            .background(Color.White)
            .padding(
                top = 15.dp,
                bottom = 15.dp,
                start = 30.dp,
                end = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //display creation points remaining
        Text(
            text = "Creation Points: " + advantageFragVM.creationPoints.collectAsState().value,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //display each list of base advantages
            items(advantageFragVM.advantageButtons) {
                AdvantageDisplay(
                    advantageFragVM,
                    it,
                    openDetailAlert,
                    homePageVM
                )
            }

            item { Spacer(Modifier.height(20.dp)) }
            item { Text(text = stringResource(R.string.heldAdvantageLabel)) }

            //display all of the character's taken advantages and disadvantages
            items(advantageFragVM.takenAdvantages) {
                HeldAdvantageDisplay(
                    advantageFragVM,
                    it,
                    openDetailAlert,
                    homePageVM
                )
            }

            item { Spacer(Modifier.height(20.dp)) }

            //display all advantages acquired from their race
            item { Text(text = "Racial Advantages") }
            items(advantageFragVM.getRacialAdvantages()) {
                AdvantageRow(it, null, openDetailAlert, {}, null)
            }
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

            homePageVM.updateExpenditures()
        }
}

/**
 * Creates a button that displays the base advantages or disadvantages in the input.
 *
 * @param advantageFragVM viewModel for the advantage fragment
 * @param advantageList advantage data to display
 * @param openDetailAlert function to run when opening an item's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun AdvantageDisplay(
    advantageFragVM: AdvantageFragmentViewModel,
    advantageList: AdvantageFragmentViewModel.AdvantageButtonData,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
){
    //get local context
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //button to open advantage list
        Button(
            onClick = {advantageList.toggleOpen()},
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ){
            //display category name
            Text(text = stringResource(advantageList.category))
        }

        //displayable list of options
        AnimatedVisibility(
            visible = advantageList.isOpen.collectAsState().value,
            modifier = Modifier
                .fillMaxWidth()
        ) {
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
                                homePageVM.updateExpenditures()
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
    buttonAction: (() -> Unit)?,
){
    //initialize the advantage's name with potential additional information
    val nameString =
        if(takenAddition != null) item.name + takenAddition
        else item.name

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ){
        if(buttonAction != null) {
            //implement button with given image and function
            Button(
                onClick = { buttonAction() },
                modifier = Modifier
                    .weight(0.15f)
            ) { button() }
        }
        else
            Spacer(Modifier.weight(0.15f))

        //display advantage information
        Text(
            text = nameString,
            modifier = Modifier
                .weight(0.6f),
            textAlign = TextAlign.Center
        )

        //details button
        DetailButton(
            onClick = {openDetailAlert(nameString){AdvantageDetails(item)}},
            modifier = Modifier
                .weight(0.25f)
        )
    }
}

/**
 * Display for an already acquired advantage.
 *
 * @param advantageFragVM viewModel that holds data for this object
 * @param item advantage to display
 * @param openDetailAlert function to run when opening an item's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun HeldAdvantageDisplay(
    advantageFragVM: AdvantageFragmentViewModel,
    item: Advantage,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
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
        homePageVM.updateExpenditures()
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

@Preview
@Composable
fun AdvantagePreview(){
    val charInstance = BaseCharacter()
    charInstance.setOwnRace(1)

    charInstance.advantageRecord.acquireAdvantage(charInstance.advantageRecord.commonAdvantages.gift, null, 0)

    val advantageFragVM = AdvantageFragmentViewModel(charInstance, charInstance.advantageRecord)
    //advantageFragVM.advantageButtons[4].toggleOpen()

    val homePageVM = HomePageViewModel(charInstance)

    AdvantageFragment(advantageFragVM, {_, _ -> }, homePageVM)
}