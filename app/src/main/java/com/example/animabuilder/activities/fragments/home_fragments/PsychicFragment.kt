package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.animabuilder.activities.*
import com.example.animabuilder.character_creation.attributes.psychic.Discipline
import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower

@Composable
fun PsychicFragment(updateFunc: () -> Unit) {
    val innatePsyPoints = remember{mutableStateOf(charInstance.psychic.innatePsyPoints.toString())}
    val psyPointsBought = remember{mutableStateOf(charInstance.psychic.boughtPsyPoints.toString())}
    val psyPointsTotal = remember{mutableStateOf(charInstance.psychic.totalPsychicPoints.toString())}
    val freePsyPoints = remember{mutableStateOf(charInstance.psychic.getFreePsyPoints().toString())}

    val psyProjBought = remember{mutableStateOf(charInstance.psychic.psyProjectionBought.toString())}
    val psyProjTotal = remember{mutableStateOf(charInstance.psychic.psyProjectionTotal.toString())}

    val disciplineData = mutableListOf<DisciplineDataItem>()

    disciplineData.add(DisciplineDataItem("Telepathy", charInstance.psychic.telepathy))
    disciplineData.add(DisciplineDataItem("Psychokinesis", charInstance.psychic.psychokinesis))
    disciplineData.add(DisciplineDataItem("Pyrokinesis", charInstance.psychic.pyrokinesis))
    disciplineData.add(DisciplineDataItem("Cryokinesis", charInstance.psychic.cryokinesis))
    disciplineData.add(DisciplineDataItem("Physical Increase", charInstance.psychic.physicalIncrease))
    disciplineData.add(DisciplineDataItem("Energy", charInstance.psychic.energyPowers))
    disciplineData.add(DisciplineDataItem("Sentience", charInstance.psychic.sentiencePowers))
    disciplineData.add(DisciplineDataItem("Telemetry", charInstance.psychic.telemetry))
    disciplineData.add(DisciplineDataItem("Matrix Powers", charInstance.psychic.matrixPowers))

    LazyColumn{
        item{Text(text = "Psychic Potential: " + charInstance.psychic.psyPotentialBase.toString())}
        item{Text(text = "Psychic Points")}
        item{
            Row{
                Text(text = innatePsyPoints.value)
                TextField(
                    value = psyPointsBought.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        numberCatcher(
                            it,
                            {input ->
                                charInstance.psychic.buyPsyPoints(input.toInt())
                                psyPointsBought.value = input
                            },
                            {
                                charInstance.psychic.buyPsyPoints(0)
                                psyPointsBought.value = ""
                            }
                        )

                        psyPointsTotal.value =  charInstance.psychic.totalPsychicPoints.toString()
                        freePsyPoints.value = charInstance.psychic.getFreePsyPoints().toString()
                        charInstance.updateTotalSpent()
                        updateFunc()
                    }
                )
                Text(text = psyPointsTotal.value)
            }
        }

        item{Text(text = "Free Psychic Points: " + freePsyPoints.value)}

        item{Text(text = "Psychic Projection")}
        item{
            Text(text = charInstance.modDEX.toString())
            TextField(
                value = psyProjBought.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    numberCatcher(
                        it,
                        {input ->
                            charInstance.psychic.buyPsyProjection(input.toInt())
                            psyProjBought.value = input
                        },
                        {
                            charInstance.psychic.buyPsyProjection(0)
                            psyProjBought.value =  ""
                        }
                    )

                    psyProjTotal.value = charInstance.psychic.totalPsychicPoints.toString()
                    charInstance.updateTotalSpent()
                    updateFunc()
                }
            )
            Text(text = psyProjTotal.value)
        }

        items(disciplineData){
            DisciplineDisplay(it){
                freePsyPoints.value = charInstance.psychic.getFreePsyPoints().toString()
            }
        }
    }
}

@Composable
private fun DisciplineDisplay(
    discipline: DisciplineDataItem,
    updateFreePoints: () -> Unit
){
    val disciplineOpen = remember{mutableStateOf(false)}
    val disciplineInvestedIn = remember{mutableStateOf(charInstance.psychic.disciplineInvestment.contains(discipline.item))}
    val powerChecks = remember{mutableStateMapOf<PsychicPower, MutableState<Boolean>>()}

    Column {
        Row(Modifier.fillMaxWidth()) {
            if(discipline.name != "Matrix Powers") {
                Checkbox(
                    checked = disciplineInvestedIn.value,
                    onCheckedChange = {
                        disciplineInvestedIn.value =
                            charInstance.psychic.updateInvestment(discipline.item, it)
                        powerChecks.forEach { item ->
                            item.value.value =
                                charInstance.psychic.masteredPowers.contains(item.key)
                        }
                        updateFreePoints()
                    }
                )
            }
            Button(onClick = { disciplineOpen.value = !disciplineOpen.value }) {
                Text(text = discipline.name)
            }
        }
        AnimatedVisibility(visible = disciplineOpen.value) {
            Column {
                discipline.item.allPowers.forEach {
                    PsyPowerRow(
                        discipline.item, it,
                        {item -> powerChecks += item},
                        {input ->
                            if(discipline.name != "Matrix Powers")
                                disciplineInvestedIn.value =
                                    charInstance.psychic.updateInvestment(discipline.item, input) },
                        {powerChecks.forEach{item ->
                            item.value.value = charInstance.psychic.masteredPowers.contains(item.key) }},
                        updateFreePoints
                    )
                }
            }
        }
    }
}

@Composable
private fun PsyPowerRow(
    discipline: Discipline,
    power: PsychicPower,
    addCheckbox: (Pair<PsychicPower, MutableState<Boolean>>) -> Unit,
    updateDisciplineInvestment: (Boolean) -> Unit,
    updateCheckboxes: () -> Unit,
    updateFreePoints: () -> Unit
){
    val powerMastered = remember{mutableStateOf(charInstance.psychic.masteredPowers.contains(power))}
    addCheckbox(Pair(power, powerMastered))

    Row{
        Checkbox(
            checked = powerMastered.value,
            onCheckedChange = {
                if(!charInstance.psychic.disciplineInvestment.contains(discipline))
                    updateDisciplineInvestment(true)

                powerMastered.value = charInstance.psychic.masterPower(power, discipline, it)

                updateCheckboxes()
                updateFreePoints()
            }
        )
        Text(text = power.name)
        Button(onClick = {
            detailItem.value = power.name
            contents.value = @Composable{ PowerDetails(power) }
            detailAlertOn.value = true
        }) {
            Text(text = "Details")
        }
    }
}

val PowerDetails = @Composable{power: PsychicPower ->
    val itemLevel = if(power.level > 0) power.level.toString() else "N/A"
    val isActive = if(power.active) "Active" else "Passive"
    val isMaintained = if(power.maintained) "Yes" else "No"

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

    Column{
        Row{
            Text(text = "Level: $itemLevel")
        }
        Row{Text(text = "Action: $isActive")}
        Row{Text(text = "Maintenance: $isMaintained")}
        Row{Text(text = power.description)}

        Spacer(Modifier.height(20.dp))

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

private data class DisciplineDataItem(
    val name: String,
    val item: Discipline
)