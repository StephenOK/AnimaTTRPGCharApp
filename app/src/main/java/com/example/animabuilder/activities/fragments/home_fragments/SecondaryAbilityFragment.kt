package com.example.animabuilder.activities.fragments.home_fragments

import com.example.animabuilder.R
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.animabuilder.NumberInput
import com.example.animabuilder.view_models.SecondaryFragmentViewModel

/**
 * Fragment to be displayed when working with secondary characteristics
 *
 * charInstance: character to work on
 * spentDisplay: bottombar mutable of spent development points
 */

@Composable
fun SecondaryAbilityFragment(
    secondaryFragVM: SecondaryFragmentViewModel,
    updateBottomBar: () -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(secondaryFragVM.allFields){
            MakeTableDisplay(it, updateBottomBar)
        }
    }
}

/**
 * Creates a toggle button to display the associated secondary characteristic table
 *
 * input: data item for this field of information
 */
@Composable
private fun MakeTableDisplay(
    input: SecondaryFragmentViewModel.SecondaryFieldData,
    updateBottomBar: () -> Unit
){
    //toggle button for the table
    Button(
        onClick = {input.toggleOpen()},
        modifier = Modifier.width(250.dp)
    ){
        //button label
        Text(
            text = stringResource(input.fieldName)
        )
    }

    //visibility group for the table
    AnimatedVisibility(visible = input.tableOpen.collectAsState().value){
        Column {
            RowHead()

            input.fieldCharacteristics.forEach {
                MakeRow(it, updateBottomBar)
            }
        }
    }
}

/**
 * Header row for a secondary characteristic table
 */
@Composable
private fun RowHead(){
    Row{
        //input point label
        Text(
            text = stringResource(R.string.pointsLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.25f)
        )

        //mod value label
        Text(
            text = stringResource(R.string.modLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )

        //class bonus label
        Text(
            text = stringResource(R.string.classLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )

        //natural bonus label
        Text(
            text = stringResource(R.string.natLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )

        //characteristic total label
        Text(
            text = stringResource(R.string.totalLabel),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f)
        )
    }
}

/**
 * Make on row for a secondary characteristic in a table
 *
 * charInstance: character to work on
 * stringReference: name of the secondary characteristic
 * item: characteristic item to work on
 * spentDisplay: mutable value of the characteristic's score input
 */
@Composable
private fun MakeRow(
    item: SecondaryFragmentViewModel.SecondaryFieldData.SecondaryItem,
    updateBottomBar: () -> Unit
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        //characteristic label
        Text(text = stringResource(item.getName()),
            textAlign = TextAlign.Start)
        Text(text = "(" + item.getMultiplier().toString() + ")")
    }

    Row(
        //modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        //user input for the stat's score
        NumberInput(
            item.pointInput.collectAsState().value,
            {},
            {item.setPointInput(it.toInt())},
            {item.setPointInput("")},
            {updateBottomBar()},
            Color.Black,
            Modifier.weight(0.25f)
        )

        //display associated mod value
        Text(text = item.getModVal(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f))

        //display associated class bonus value
        Text(text = item.getClassPointTotal(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f))

        //checkbox to apply natural bonus
        Checkbox(
            checked = item.natBonusCheck.collectAsState().value,
            onCheckedChange = {
                //attempt to toggle natural bonus
                item.setBonusNatCheck()
            },

            modifier = Modifier.weight(0.125f)
        )

        //display for characteristic's total value
        Text(text = item.totalOutput.collectAsState().value,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f))
    }
}