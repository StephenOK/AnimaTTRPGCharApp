package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.animabuilder.activities.charInstance
import com.example.animabuilder.activities.numberCatcher

@Composable
fun PsychicFragment(updateFunc: () -> Unit) {
    val innatePsyPoints = remember{mutableStateOf(charInstance.psychic.innatePsyPoints.toString())}
    val psyPointsBought = remember{mutableStateOf(charInstance.psychic.boughtPsyPoints.toString())}
    val psyPointsTotal = remember{mutableStateOf(charInstance.psychic.totalPsychicPoints.toString())}

    val psyProjBought = remember{mutableStateOf(charInstance.psychic.psyProjectionBought.toString())}
    val psyProjTotal = remember{mutableStateOf(charInstance.psychic.psyProjectionTotal.toString())}

    LazyColumn{
        item{Text(text = "Psychic Potential: " + charInstance.psychic.psyPotentialBase.toString())}
        item{Text(text = "Psychic Points")}
        item{
            Row{
                Text(text = innatePsyPoints.value)
                TextField(
                    value = psyPointsBought.value,
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
                        charInstance.updateTotalSpent()
                        updateFunc()
                    }
                )
                Text(text = psyPointsTotal.value)
            }
        }

        item{Text(text = "Psychic Projection")}
        item{
            Text(text = charInstance.modDEX.toString())
            TextField(
                value = psyProjBought.value,
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
    }
}