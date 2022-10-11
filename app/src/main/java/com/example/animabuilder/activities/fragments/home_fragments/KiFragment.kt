package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animabuilder.activities.InfoRow
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.ki_abilities.KiAbility

private var allKiAbilities: List<Pair<KiAbility, MutableState<Boolean>>> = listOf()
private val remainingMK = mutableStateOf("")


@Composable
fun KiFragment(
    charInstance: BaseCharacter,
    updateFunc: () -> Unit
) {
    val kiListOpen = remember{mutableStateOf(false)}
    remainingMK.value = charInstance.kiList.martialKnowledgeRemaining.toString()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ){
        InfoRow("Max Martial Knowledge: ", charInstance.kiList.martialKnowledgeMax.toString())
        InfoRow("Martial Knowledge Remaining: ", remainingMK.value)
        
        Button(
            onClick = {
                kiListOpen.value = !kiListOpen.value
            },
            modifier = Modifier.width(250.dp)
        ) {
            Text(text = "Ki Abilities")
        }
        
        AnimatedVisibility(visible = kiListOpen.value) {
            Column {
                charInstance.kiList.allKiAbilities.forEach {
                    KiAbilityRow(charInstance, it)
                }
            }
        }
    }
}

@Composable
private fun KiAbilityRow(charInstance: BaseCharacter, ability: KiAbility){
    val abilityTaken = remember{mutableStateOf(charInstance.kiList.takenAbilities.contains(ability))}

    allKiAbilities = allKiAbilities + Pair(ability, abilityTaken)

    Row(){
        Checkbox(
            checked = abilityTaken.value,
            onCheckedChange = {
                if(it){
                    if(ability.prerequisites == null ||
                        charInstance.kiList.takenAbilities.contains(ability.prerequisites))
                        abilityTaken.value = charInstance.kiList.attemptAbilityAdd(ability)
                }
                else{
                    abilityTaken.value = false
                    charInstance.kiList.removeItem(ability)
                    updateKiTaken(charInstance)
                }

                remainingMK.value = charInstance.kiList.martialKnowledgeRemaining.toString()
            },
            modifier = Modifier.weight(0.1f)
        )
        Text(text = ability.name, modifier = Modifier.weight(0.5f))
        Text(text = ability.mkCost.toString(), modifier = Modifier.weight(0.2f))
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.weight(0.2f)
        ) {
            Text(text = "Details")
        }
    }
}

private fun updateKiTaken(charInstance: BaseCharacter){
    allKiAbilities.forEach{
        if(charInstance.kiList.takenAbilities.contains(it.first) != it.second.value)
            it.second.value = false
    }
}