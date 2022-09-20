package com.example.animabuilder.activities.fragments.home_fragments

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CombatFragment(
    charInstance: BaseCharacter,
    updateFunc: () -> Unit
) {
    Column(){

        Row(){
            Spacer(modifier = Modifier.weight(0.2f))
            Text(text = "Base", modifier = Modifier.weight(0.2f))
            Text(text = "Class", modifier = Modifier.weight(0.2f))
            Text(text = "Multiplier", modifier = Modifier.weight(0.2f))
            Text(text = "Total", modifier = Modifier.weight(0.2f))
        }

        Row(verticalAlignment = Alignment.CenterVertically){
            val lifeTotal = remember{mutableStateOf(charInstance.lifeMax)}
            val lifeMults = remember{mutableStateOf(charInstance.lifeMultsTaken.toString())}

            Text(text = "Life Points: ", modifier = Modifier.weight(0.2f))

            Text(text = charInstance.lifeBase.toString(), modifier = Modifier.weight(0.2f))
            Text(text = (charInstance.ownClass.lifePointsPerLevel * charInstance.lvl).toString(),
                modifier = Modifier.weight(0.2f))

            TextField(
                value = lifeMults.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    try{
                        charInstance.takeLifeMult(it.toInt())
                        lifeTotal.value = charInstance.lifeMax

                        lifeMults.value = it
                        updateFunc()
                    }catch(e: NumberFormatException){
                        if(it == "")
                            lifeMults.value = it
                        else if(it.contains('\n'))
                            keyboardActive!!.hide()
                    }
                },
                modifier = Modifier.weight(0.2f)
            )

            Text(text = lifeTotal.value.toString(), modifier = Modifier.weight(0.2f))
        }

        Spacer(Modifier.height(30.dp))

        Column(){
            Row(){
                Spacer(modifier = Modifier.weight(0.2f))
                Text(text = "Points", modifier = Modifier.weight(0.2f))
                Text(text = stringResource(R.string.modLabel), modifier = Modifier.weight(0.2f))
                Text(text = stringResource(R.string.classLabel), modifier = Modifier.weight(0.2f))
                Text(text = stringResource(R.string.totalLabel), modifier = Modifier.weight(0.2f))
            }

            CombatItemRow(charInstance, "Attack", charInstance.pointInAttack, charInstance.applyAttackPoint,
                charInstance.modDEX, charInstance.ownClass.atkPerLevel, charInstance.attack, updateFunc)
            CombatItemRow(charInstance, "Block", charInstance.pointInBlock, charInstance.applyBlockPoint,
                charInstance.modDEX, charInstance.ownClass.blockPerLevel, charInstance.block, updateFunc)
            CombatItemRow(charInstance, "Dodge", charInstance.pointInDodge, charInstance.applyDodgePoint,
                charInstance.modAGI, charInstance.ownClass.dodgePerLevel, charInstance.dodge, updateFunc)
            CombatItemRow(charInstance, "Wear Armor", charInstance.pointInWear, charInstance.applyWearPoint,
                charInstance.modSTR, charInstance.ownClass.armorPerLevel, charInstance.wearArmor, updateFunc)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CombatItemRow(
    charInstance: BaseCharacter,
    labelText: String,
    pointIn: Int,
    changeAct: (Int, MutableState<String>) -> Unit,
    modInput: Int,
    classAdd: Int,
    total: Int,

    updateFunc: () -> Unit
){
    val pointInScore = remember{mutableStateOf(pointIn.toString())}
    val pointTotal = remember{mutableStateOf(total.toString())}

    Row() {
        Text(text = labelText, modifier = Modifier.weight(0.2f))

        TextField(
            value = pointInScore.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                try{
                    changeAct(it.toInt(), pointTotal)
                    pointInScore.value = it
                    updateFunc()
                }catch(e: NumberFormatException){
                    if(it == "")
                        pointInScore.value = it
                    else if(it.contains('\n'))
                        keyboardActive!!.hide()
                }
            },
            modifier = Modifier.weight(0.2f)
        )

        Text(text = modInput.toString(), modifier = Modifier.weight(0.2f))
        Text(text = (classAdd * charInstance.lvl).toString(), modifier = Modifier.weight(0.2f))
        Text(text = pointTotal.value, modifier = Modifier.weight(0.2f))
    }
}