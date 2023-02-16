package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animabuilder.DetailButton
import com.example.animabuilder.UserInput
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.psychic.Discipline
import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower
import com.example.animabuilder.character_creation.attributes.race_objects.RaceName

@Composable
fun PsychicFragment(
    charInstance: BaseCharacter,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
) {
    //initialize Psychic Point display values
    val innatePsyPoints = remember{mutableStateOf(charInstance.psychic.innatePsyPoints.toString())}
    val psyPointsBought = remember{mutableStateOf(charInstance.psychic.boughtPsyPoints.toString())}
    val psyPointsTotal = remember{mutableStateOf(charInstance.psychic.totalPsychicPoints.toString())}
    val freePsyPoints = remember{mutableStateOf(charInstance.psychic.getFreePsyPoints().toString())}

    //initialize Psychic Projection display values
    val psyProjBought = remember{mutableStateOf(charInstance.psychic.psyProjectionBought.toString())}
    val psyProjTotal = remember{mutableStateOf(charInstance.psychic.psyProjectionTotal.toString())}

    val disciplineMasterList = remember{mutableStateListOf<MutableState<Boolean>>()}
    val powerMasterList = remember{mutableStateListOf<MutableState<Boolean>>()}

    //initialize and create input table data
    val psyPurchaseData = mutableListOf<PsychicPurchaseItemData>()

    psyPurchaseData.add(PsychicPurchaseItemData(
        "Psychic Points",
        innatePsyPoints.value,
        psyPointsBought,
        psyPointsTotal
    ) { input ->
        charInstance.psychic.buyPsyPoints(input.toInt())
        psyPointsBought.value = input
        psyPointsTotal.value = charInstance.psychic.totalPsychicPoints.toString()
        freePsyPoints.value = charInstance.psychic.getFreePsyPoints().toString()
    })

    psyPurchaseData.add(
        PsychicPurchaseItemData(
        "Psychic Projection",
        charInstance.modDEX.toString(),
        psyProjBought,
        psyProjTotal
    ) { input ->
            charInstance.psychic.buyPsyProjection(input.toInt())
            psyProjBought.value = input
            psyProjTotal.value = charInstance.psychic.psyProjectionTotal.toString()
        }
    )

    //initialize and create Psychic Discipline data
    val disciplineData = mutableListOf<DisciplineItemData>()

    disciplineData.add(DisciplineItemData("Telepathy", charInstance.psychic.telepathy))
    disciplineData.add(DisciplineItemData("Psychokinesis", charInstance.psychic.psychokinesis))
    disciplineData.add(DisciplineItemData("Pyrokinesis", charInstance.psychic.pyrokinesis))
    disciplineData.add(DisciplineItemData("Cryokinesis", charInstance.psychic.cryokinesis))
    disciplineData.add(DisciplineItemData("Physical Increase", charInstance.psychic.physicalIncrease))
    disciplineData.add(DisciplineItemData("Energy", charInstance.psychic.energyPowers))
    disciplineData.add(DisciplineItemData("Sentience", charInstance.psychic.sentiencePowers))
    disciplineData.add(DisciplineItemData("Telemetry", charInstance.psychic.telemetry))
    disciplineData.add(DisciplineItemData("Matrix Powers", charInstance.psychic.matrixPowers))

    LazyColumn{
        //display character's Psychic Potential
        item{Text(text = "Psychic Potential: " + charInstance.psychic.psyPotentialBase.toString())}

        //display psychic item input
        items(psyPurchaseData){
            PsychicPurchaseTable(charInstance, it, updateFunc)
        }

        //display currently free psychic points
        item{Text(text = "Free Psychic Points: " + freePsyPoints.value)}

        //display discipline info
        items(disciplineData){
            DisciplineDisplay(
                charInstance,
                it,
                {input -> disciplineMasterList.add(input)},
                {input -> powerMasterList.add(input)},
                {
                    disciplineMasterList.forEach{item -> item.value = false}
                    powerMasterList.forEach{item -> item.value = false}
                },
                openDetailAlert
            ){freePsyPoints.value = charInstance.psychic.getFreePsyPoints().toString()}
        }
    }
}

/**
 * Displays an item that allows for the user to purchase amounts of the indicated psychic ability
 *
 * tableData: information regarding this individual table
 * updateFunc: bottom bar update function
 */
@Composable
private fun PsychicPurchaseTable(
    charInstance: BaseCharacter,
    tableData: PsychicPurchaseItemData,
    updateFunc: () -> Unit
){
    //display title of this section
    Row{Text(text = tableData.title)}
    Row{
        //display value's base input
        Text(text = tableData.baseString)

        //input for user purchased value
        UserInput(
            tableData.purchaseAmount,
            {},
            tableData.buyFunction,
            {
                tableData.buyFunction("0")
                tableData.purchaseAmount.value = ""
            },
            {
                charInstance.updateTotalSpent()
                updateFunc()
            },
            Modifier
        )

        //display value's final total
        Text(text = tableData.totalAmount.value)
    }
}

/**
 * Row that displays information about a Psychic Discipline and allows the user to spend points in
 * that discipline
 *
 * discipline: information regarding the specific Psychic Discipline
 * updateFreePoints: function that affects the free point display
 */
@Composable
private fun DisciplineDisplay(
    charInstance: BaseCharacter,
    discipline: DisciplineItemData,
    addDiscipline: (MutableState<Boolean>) -> Unit,
    addPower: (MutableState<Boolean>) -> Unit,
    dukzaristClear: () -> Unit,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFreePoints: () -> Unit
){
    //initialize discipline's open state
    val disciplineOpen = remember{mutableStateOf(false)}

    //initialize discipline's taken state
    val disciplineInvestedIn = remember{mutableStateOf(charInstance.psychic.disciplineInvestment.contains(discipline.item))}
    addDiscipline(disciplineInvestedIn)

    //initialize list of discipline's powers and if they're taken or not
    val powerChecks = remember{mutableStateMapOf<PsychicPower, MutableState<Boolean>>()}

    Column {
        Row(Modifier.fillMaxWidth()) {
            //create a checkbox for any discipline that isn't Matrix Powers
            if(discipline.name != "Matrix Powers") {
                Checkbox(
                    checked = disciplineInvestedIn.value,
                    onCheckedChange = {
                        //attempt to change the taken state of the discipline
                        disciplineInvestedIn.value =
                            charInstance.psychic.updateInvestment(discipline.item, it)

                        //set Psychic Powers' checkboxes to the appropriate values
                        powerChecks.forEach { item ->
                            item.value.value =
                                charInstance.psychic.masteredPowers.contains(item.key)
                        }

                        if(!it && charInstance.ownRace.heldRace == RaceName.dukzarist && discipline.name == "Pyrokinesis")
                            dukzaristClear()

                        //update free point display
                        updateFreePoints()
                    }
                )
            }

            //button to display Psychic Powers
            Button(onClick = { disciplineOpen.value = !disciplineOpen.value }) {
                Text(text = discipline.name)
            }
        }

        //display for discipline's Psychic Powers
        AnimatedVisibility(visible = disciplineOpen.value) {
            Column {
                discipline.item.allPowers.forEach {
                    PsyPowerRow(
                        charInstance, discipline.item,
                        it,
                        {item -> powerChecks += item},
                        addPower,
                        {input ->
                            if(discipline.name != "Matrix Powers")
                                disciplineInvestedIn.value =
                                    charInstance.psychic.updateInvestment(discipline.item, input) },
                        {powerChecks.forEach{item ->
                            item.value.value = charInstance.psychic.masteredPowers.contains(item.key) }},
                        openDetailAlert,
                        updateFreePoints
                    )
                }
            }
        }
    }
}

/**
 * Display for a Psychic Power
 *
 * discipline: power's associated Psychic Discipline
 * power: the displayed object
 * addCheckbox: function that hoists the power and checkbox to the master list
 * updateDisciplineInvestment: function that changes the investment in the associated discipline
 * updateCheckboxes: function that sets all checkboxes to their appropriate values
 * updateFreePoints: function that updates the free point display
 */
@Composable
private fun PsyPowerRow(
    charInstance: BaseCharacter,
    discipline: Discipline,
    power: PsychicPower,
    addCheckbox: (Pair<PsychicPower, MutableState<Boolean>>) -> Unit,
    addPower: (MutableState<Boolean>) -> Unit,
    updateDisciplineInvestment: (Boolean) -> Unit,
    updateCheckboxes: () -> Unit,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFreePoints: () -> Unit
){
    //initialize taken state of the power
    val powerMastered = remember{mutableStateOf(charInstance.psychic.masteredPowers.contains(power))}
    addPower(powerMastered)

    //add checkbox to the master list
    addCheckbox(Pair(power, powerMastered))

    Row{
        Checkbox(
            checked = powerMastered.value,
            onCheckedChange = {
                //add power's discipline if character does not already have it
                if(!charInstance.psychic.disciplineInvestment.contains(discipline))
                    updateDisciplineInvestment(true)

                //attempt to perform inputted action
                powerMastered.value = charInstance.psychic.masterPower(power, discipline, it)

                //update checkbox list and free point display
                updateCheckboxes()
                updateFreePoints()
            }
        )
        Text(text = power.name)

        //power's detail button
        DetailButton(
            onClick = {openDetailAlert(power.name) @Composable{PowerDetails(power)}},
            modifier = Modifier
        )
    }
}

//make list of difficulty levels
val tableRows = listOf(
    "Routine (20)",
    "Easy (40)",
    "Medium (80)",
    "Difficult (120)",
    "Very Difficult (140)",
    "Absurd (180)",
    "Almost Impossible (240)",
    "Impossible (280)",
    "Inhuman (320)",
    "Zen (440)"
)

//detail display for the inputted Psychic Power
val PowerDetails = @Composable{power: PsychicPower ->
    //retrieve level, if power is active, and if power is maintained
    val itemLevel = if(power.level > 0) power.level.toString() else "N/A"
    val isActive = if(power.active) "Active" else "Passive"
    val isMaintained = if(power.maintained) "Yes" else "No"

    Column{
        //display power values
        Row{Text(text = "Level: $itemLevel") }
        Row{Text(text = "Action: $isActive")}
        Row{Text(text = "Maintenance: $isMaintained")}
        Row{Text(text = power.description)}

        Spacer(Modifier.height(20.dp))

        //display power's Effects Table
        power.effects.forEach{
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = tableRows[power.effects.indexOf(it)], modifier = Modifier.weight(0.5f))
                Text(text = it, modifier = Modifier.weight(0.5f))
            }
        }
    }
}

/**
 * Class that holds data in regards to a the purchase table data
 *
 * title: name of the stat involved in this table
 * baseString: base value of the indicated stat
 * purchaseAmount: amount that the user has bought of this stat
 * totalAmount: stat's final total
 * buyFunction: function to run upon stat purchase
 */
private data class PsychicPurchaseItemData(
    val title: String,
    val baseString: String,
    val purchaseAmount: MutableState<String>,
    val totalAmount: MutableState<String>,
    val buyFunction: (String) -> Unit
)

/**
 * Class that holds data about a Psychic Discipline
 *
 * name: string of the discipline's name
 * item: discipline object to work with
 */
private data class DisciplineItemData(
    val name: String,
    val item: Discipline
)