package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.animabuilder.activities.charInstance
import com.example.animabuilder.activities.numberCatcher

@Composable
fun MagicFragment(updateFunc: () -> Unit) {
    val boughtZeonString = remember{mutableStateOf(charInstance.magic.boughtZeon.toString())}
    val maxZeonString = remember{mutableStateOf(charInstance.magic.zeonMax.toString())}

    val boughtAccString = remember{mutableStateOf(charInstance.magic.zeonAccMult.toString())}
    val totalAccString = remember{mutableStateOf(charInstance.magic.zeonAccTotal.toString())}

    val boughtProjString = remember{mutableStateOf(charInstance.magic.boughtMagProjection.toString())}
    val totalMagProjectString = remember{mutableStateOf(charInstance.magic.magProjTotal.toString())}

    val projectionImbalance = remember{mutableStateOf(charInstance.magic.magProjImbalance.toString())}
    val imbalanceIsAttack = remember{mutableStateOf(charInstance.magic.imbalanceIsAttack)}
    val imbalanceTypeString =
        if(imbalanceIsAttack.value)
            remember{mutableStateOf("Attack")}
        else
            remember{mutableStateOf("Defense")}

    val offenseImbalance = remember{mutableStateOf(determineImbalanceValue(imbalanceIsAttack.value).toString())}
    val defenseImbalance = remember{mutableStateOf(determineImbalanceValue(!imbalanceIsAttack.value).toString())}

    Column{
        Row{Text(text = "Zeon Points:")}
        Row{
            Text(text = "Base", modifier = Modifier.weight(0.25f))
            Text(text = "Bought", modifier = Modifier.weight(0.25f))
            Text(text = "Class", modifier = Modifier.weight(0.25f))
            Text(text = "Total", modifier = Modifier.weight(0.25f))
        }
        Row{
            Text(text = charInstance.magic.baseZeon.toString(), modifier = Modifier.weight(0.25f))
            TextField(
                value = boughtZeonString.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    numberCatcher(
                        it,
                        {input ->
                            charInstance.magic.buyZeon(input.toInt())
                            boughtZeonString.value = input
                            maxZeonString.value = charInstance.magic.zeonMax.toString()
                            updateFunc()
                        },
                        {
                            charInstance.magic.buyZeon(0)
                            boughtZeonString.value = ""
                            maxZeonString.value = charInstance.magic.zeonMax.toString()
                            updateFunc()
                        }
                    )
                }, modifier = Modifier.weight(0.15f)
            )
            Text(text = "x 5", modifier = Modifier.weight(0.1f))
            Text(text = (charInstance.lvl * charInstance.ownClass.zeonPerLevel).toString(), modifier = Modifier.weight(0.25f))
            Text(text = maxZeonString.value, modifier = Modifier.weight(0.25f))
        }

        Row{Text(text = "Zeon Accumulation")}
        Row{
            Text(text = "Base")
            Text(text = "Bought")
            Text(text = "Total")
        }
        Row{
            Text(text = charInstance.magic.baseZeonAcc.toString())
            TextField(
                value = boughtAccString.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    numberCatcher(it,
                        {input ->
                            if(input.toInt() >= 1) {
                                charInstance.magic.buyZeonAcc(input.toInt())
                                boughtAccString.value = input
                                totalAccString.value =
                                    charInstance.magic.zeonAccTotal.toString()
                                updateFunc()
                            }
                        },
                        {
                            charInstance.magic.buyZeonAcc(1)
                            boughtAccString.value = ""
                            totalAccString.value = charInstance.magic.zeonAccTotal.toString()
                            updateFunc()
                        }
                    )
                }
            )
            Text(text = totalAccString.value)
        }

        Row{Text(text = "Magic Projection")}
        Row{
            Text(text = "Base")
            Text(text = "Bought")
            Text(text = "Total")
        }
        Row{
            Text(text = charInstance.modDEX.toString())
            TextField(
                value = boughtProjString.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    numberCatcher(
                        it,
                        {input ->
                            charInstance.magic.buyMagProj(input.toInt())
                            boughtProjString.value = input
                            totalMagProjectString.value = charInstance.magic.magProjTotal.toString()
                            offenseImbalance.value = determineImbalanceValue(imbalanceIsAttack.value).toString()
                            defenseImbalance.value = determineImbalanceValue(!imbalanceIsAttack.value).toString()
                            updateFunc()
                        },
                        {
                            charInstance.magic.buyMagProj(0)
                            boughtProjString.value = ""
                            totalMagProjectString.value = charInstance.magic.magProjTotal.toString()
                            offenseImbalance.value = determineImbalanceValue(imbalanceIsAttack.value).toString()
                            defenseImbalance.value = determineImbalanceValue(!imbalanceIsAttack.value).toString()
                            updateFunc()
                        }
                    )
                }
            )
            Text(text = totalMagProjectString.value)
        }

        Row{
            Text(text = "Projection Imbalance: ")
            TextField(
                value = projectionImbalance.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    numberCatcher(
                        it,
                        {input ->
                            if(input.toInt() in 0..30){
                                charInstance.magic.magProjImbalance = input.toInt()
                                projectionImbalance.value = input
                                offenseImbalance.value = determineImbalanceValue(imbalanceIsAttack.value).toString()
                                defenseImbalance.value = determineImbalanceValue(!imbalanceIsAttack.value).toString()
                            }
                        },
                        {
                            charInstance.magic.magProjImbalance = 0
                            projectionImbalance.value = ""
                            offenseImbalance.value = determineImbalanceValue(imbalanceIsAttack.value).toString()
                            defenseImbalance.value = determineImbalanceValue(!imbalanceIsAttack.value).toString()
                        }
                    )
                }
            )
        }

        Row{
            Button(onClick = {
                imbalanceIsAttack.value = !imbalanceIsAttack.value
                charInstance.magic.imbalanceIsAttack = !charInstance.magic.imbalanceIsAttack
                offenseImbalance.value = determineImbalanceValue(imbalanceIsAttack.value).toString()
                defenseImbalance.value = determineImbalanceValue(!imbalanceIsAttack.value).toString()
            }) {
                Text(text = imbalanceTypeString.value)
            }
            Column {
                Text(text = "Offense: " + offenseImbalance.value)
                Text(text = "Defense: " + defenseImbalance.value)
            }
        }
    }
}

private fun determineImbalanceValue(
    additionMade: Boolean
): Int{
    return if(additionMade)
        charInstance.magic.magProjTotal + charInstance.magic.magProjImbalance
    else
        charInstance.magic.magProjTotal - charInstance.magic.magProjImbalance
}