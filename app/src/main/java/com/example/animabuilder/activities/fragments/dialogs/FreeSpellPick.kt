package com.example.animabuilder.activities.fragments.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.animabuilder.activities.charInstance
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.FreeSpell

@Composable
fun FreeSpellPick(
    spellElement: Element,
    spellLevel: Int,
    changeIndex: Int,
    textChange: (String) -> Unit,
    closeDialog: () -> Unit
){
    val freeList = when (spellLevel){
        4, 8 -> charInstance.magic.freeBook.firstBook
        14, 18 -> charInstance.magic.freeBook.secondBook
        24, 28 -> charInstance.magic.freeBook.thirdBook
        34, 38 -> charInstance.magic.freeBook.fourthBook
        44, 48 -> charInstance.magic.freeBook.fifthBook
        54, 58 -> charInstance.magic.freeBook.sixthBook
        64, 68 -> charInstance.magic.freeBook.seventhBook
        74, 78 -> charInstance.magic.freeBook.eighthBook
        84, 88 -> charInstance.magic.freeBook.ninthBook
        94, 98 -> charInstance.magic.freeBook.tenthBook
        else -> listOf()
    }

    val selectedSpell = remember{mutableStateOf<FreeSpell?>(null)}

    Dialog(
        onDismissRequest = {closeDialog()},
        content = {
            Box(
                Modifier
                    .background(Color.White)
                    .size(600.dp, 600.dp)){
                Row(
                    Modifier
                        .align(Alignment.TopCenter)
                        .height(100.dp)
                ) { Text(text = "Choose Free Spell") }
                Row(
                    Modifier
                        .align(Alignment.Center)
                        .height(400.dp)
                ){
                    LazyColumn{
                        items(freeList){
                            if(!it.forbiddenElements.contains(spellElement) && !charInstance.magic.spellList.contains(it)){
                                PickFreeRow(it, selectedSpell.value)
                                {input: FreeSpell ->
                                    selectedSpell.value = input
                                }
                            }
                        }
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .height(100.dp)
                ){
                    TextButton(onClick = {
                        if(selectedSpell.value != null) {
                            charInstance.magic.spellList[changeIndex] = FreeSpell(
                                selectedSpell.value!!.name,
                                selectedSpell.value!!.isActive,
                                spellLevel,
                                selectedSpell.value!!.zCost,
                                selectedSpell.value!!.effect,
                                selectedSpell.value!!.addedEffect,
                                selectedSpell.value!!.zMax,
                                selectedSpell.value!!.maintenance,
                                selectedSpell.value!!.isDaily,
                                selectedSpell.value!!.type,
                                selectedSpell.value!!.forbiddenElements
                            )
                            textChange(selectedSpell.value!!.name)
                            closeDialog()
                        }
                    }) {
                        Text(text = "Confirm")
                    }
                    TextButton(onClick = closeDialog) {
                        Text(text = "Back")
                    }
                }
            }
        }
    )
}

@Composable
private fun PickFreeRow(
    displayItem: FreeSpell,
    radioCheck: FreeSpell?,
    changeRadioCheck: (FreeSpell) -> Unit
){
    Row{
        RadioButton(
            selected = displayItem == radioCheck,
            onClick = {changeRadioCheck(displayItem)}
        )
        Text(text = displayItem.name)
    }
}