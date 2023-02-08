package com.example.animabuilder.activities.fragments.home_fragments

import com.example.animabuilder.R
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.animabuilder.activities.charInstance
import com.example.animabuilder.activities.numberCatcher
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryCharacteristic

/**
 * Fragment to be displayed when working with secondary characteristics
 *
 * charInstance: character to work on
 * spentDisplay: bottombar mutable of spent development points
 */

@Composable
fun SecondaryAbilityFragment(updateBottomBar: () -> Unit) {
    val secondaryFieldTable = mutableListOf<SecondaryFieldData>()

    secondaryFieldTable.add(SecondaryFieldData(
        R.string.athleticsLabel,
        charInstance.secondaryList.intToField(0)
    ))
    secondaryFieldTable.add(SecondaryFieldData(
        R.string.creativeLabel,
        charInstance.secondaryList.intToField(1)
    ))
    secondaryFieldTable.add(SecondaryFieldData(
        R.string.perceptionLabel,
        charInstance.secondaryList.intToField(2)
    ))
    secondaryFieldTable.add(SecondaryFieldData(
        R.string.socialLabel,
        charInstance.secondaryList.intToField(3)
    ))
    secondaryFieldTable.add(SecondaryFieldData(
        R.string.subterfugeLabel,
        charInstance.secondaryList.intToField(4)
    ))
    secondaryFieldTable.add(SecondaryFieldData(
        R.string.intellectualLabel,
        charInstance.secondaryList.intToField(5)
    ))
    secondaryFieldTable.add(SecondaryFieldData(
        R.string.vigorLabel,
        charInstance.secondaryList.intToField(6)
    ))

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(secondaryFieldTable){
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
    input: SecondaryFieldData,
    updateBottomBar: () -> Unit
){
    //open state of the table
    val active = remember{mutableStateOf(false)}

    //toggle button for the table
    Button(
        onClick = {active.value = !active.value},
        modifier = Modifier.width(250.dp)
    ){
        //button label
        Text(
            text = stringResource(input.fieldName)
        )
    }

    //visibility group for the table
    AnimatedVisibility(visible = active.value){
        Column {
            RowHead()

            input.fieldItems.forEach {
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
        //column buffer
        Spacer(modifier = Modifier.weight(0.25f))

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
    item: SecondaryCharacteristic,
    updateBottomBar: () -> Unit
){
    //initial score stat
    val userInput = remember{mutableStateOf(item.pointsApplied.toString())}

    //color of the score label's text
    val textColor = remember{mutableStateOf(Color.Black)}

    //state of natural bonus taken
    val checkedState = remember{mutableStateOf(item.bonusApplied)}

    //text associated with the natural bonus check
    val checkedText =
        if(checkedState.value)
            remember{mutableStateOf(R.string.natTaken)}
        else
            remember{mutableStateOf(R.string.natNotTaken)}

    //string to display for characteristic's total
    val total = remember{mutableStateOf(item.total.toString())}

    Row(
        //modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        //row's label
        Text(text = stringResource(item.name),
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(0.25f))

        //user input for the stat's score
        TextField(
            value = userInput.value,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            onValueChange = {
                numberCatcher(it,
                    {input: String ->
                        secondaryInput(item, input.toInt(), textColor, total)
                        userInput.value = input},
                    {secondaryInput(item, 0, textColor, total)
                        userInput.value = ""}
                )

                checkedState.value = item.bonusApplied
                charInstance.updateTotalSpent()
                updateBottomBar()
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, color = textColor.value),
            modifier = Modifier.weight(0.25f)
        )

        //display associated mod value
        Text(text = item.modVal.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f))

        //display associated class bonus value
        Text(text = (item.pointsFromClass * charInstance.lvl).toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f))

        //checkbox to apply natural bonus
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                //attempt to toggle natural bonus
                checkedState.value = charInstance.secondaryList.toggleNatBonus(item)

                //change text to reflect value
                checkedText.value =
                    if(checkedState.value) R.string.natTaken
                    else R.string.natNotTaken

                //update total text
                total.value = item.total.toString()
            },

            modifier = Modifier.weight(0.125f)
        )

        //display for characteristic's total value
        Text(text = total.value,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.125f))
    }
}

/**
 * Update characteristic and development point values for an inputted score value
 *
 * charInstance: character to work on
 * item: secondary characteristic to operate with
 * input: new value of the characteristic's score
 * spentDisplay: display value for the bottom bar of the spent point total
 * textColor: color the the associated textfield's text
 * total: secondary characteristic's final value
 */
private fun secondaryInput(
    item: SecondaryCharacteristic,
    input: Int,
    textColor: MutableState<Color>,
    total: MutableState<String>
){
    //set characteristic's point value
    item.setPointsApplied(input)

    //update text displays
    total.value = item.total.toString()

    //check if spent is  valid
    if(charInstance.spentTotal < charInstance.devPT)
        //make text black for valid
        textColor.value = Color.Black

    else
        //make text red for invalid
        textColor.value = Color.Red
}

/**
 * Data class for information on a secondary field
 *
 * fieldName: name of the secondary characteristic field
 * fieldItems: secondary abilities in this field
 * itemNames: string names of the secondary characteristics
 */
private data class SecondaryFieldData(
    val fieldName: Int,
    val fieldItems: List<SecondaryCharacteristic>
)