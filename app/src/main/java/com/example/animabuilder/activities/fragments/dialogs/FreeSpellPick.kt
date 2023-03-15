package com.example.animabuilder.activities.fragments.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.animabuilder.DetailButton
import com.example.animabuilder.character_creation.attributes.magic.spells.FreeSpell
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.view_models.MagicFragmentViewModel

/**
 * Dialog for the player to choose their character's Free Spells
 * Shows spells that are available to the indicated element that the character does not already have
 * Allows display of description of the available spells
 *
 * spellElement: book that the associated spell is a part of
 * spellLevel: level of free spell that is being taken
 * textChange: function that changes which text item display to change on spell acquisition
 * detailContents: composable for detail alert for the spell
 * closeDialog: function to run on dialog's close
 */
@Composable
fun FreeSpellPick(
    magFragVM: MagicFragmentViewModel,
    detailContents: @Composable (Spell) -> Unit,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    closeDialog: () -> Unit
){
    //determine which level of spells to display
    val freeList = when (magFragVM.freeLevel.collectAsState().value){
        4, 8 -> magFragVM.getFreeSpellbook().firstBook
        14, 18 -> magFragVM.getFreeSpellbook().secondBook
        24, 28 -> magFragVM.getFreeSpellbook().thirdBook
        34, 38 -> magFragVM.getFreeSpellbook().fourthBook
        44, 48 -> magFragVM.getFreeSpellbook().fifthBook
        54, 58 -> magFragVM.getFreeSpellbook().sixthBook
        64, 68 -> magFragVM.getFreeSpellbook().seventhBook
        74, 78 -> magFragVM.getFreeSpellbook().eighthBook
        84, 88 -> magFragVM.getFreeSpellbook().ninthBook
        94, 98 -> magFragVM.getFreeSpellbook().tenthBook
        else -> listOf()
    }

    //initialize user's selection
    val selectedSpell = remember{mutableStateOf<FreeSpell?>(null)}

    DialogFrame(
        "Choose Free Spell",
        {
            LazyColumn{
                //create selection item for each available spell
                items(freeList){
                    //determine that spell is legal for the book and not already taken
                    if(!it.forbiddenElements.contains(magFragVM.freeElement.collectAsState().value) && !magFragVM.getFreeSpellHeld(it)){
                        PickFreeRow(it, selectedSpell.value, { input: FreeSpell ->
                            selectedSpell.value = input
                        }, openDetailAlert, detailContents)
                    }
                }
            }
        },
        {
            //confirmation button adds spell to character
            TextButton(onClick = {
                if(selectedSpell.value != null) {
                    magFragVM.addFreeSpell(FreeSpell(
                        selectedSpell.value!!.name,
                        selectedSpell.value!!.isActive,
                        magFragVM.freeLevel.value,
                        selectedSpell.value!!.zCost,
                        selectedSpell.value!!.effect,
                        selectedSpell.value!!.addedEffect,
                        selectedSpell.value!!.zMax,
                        selectedSpell.value!!.maintenance,
                        selectedSpell.value!!.isDaily,
                        selectedSpell.value!!.type,
                        selectedSpell.value!!.forbiddenElements
                    ), magFragVM.freeElement.value)
                    magFragVM.textChange.value(selectedSpell.value!!.name)
                    closeDialog()
                }
            }) {
                Text(text = "Confirm")
            }

            //back button closes dialog
            TextButton(onClick = closeDialog) {
                Text(text = "Back")
            }
        }
    )
}

/**
 * Creates a row with a free spell for the user to select for their character
 *
 * displayItem: spell associated with this row
 * radioCheck: player's currently selected free spell
 * changeRadioCheck: function to run when the user changes their selection
 * detailContents: composable to send to the DetailAlert when called
 */
@Composable
private fun PickFreeRow(
    displayItem: FreeSpell,
    radioCheck: FreeSpell?,
    changeRadioCheck: (FreeSpell) -> Unit,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    detailContents: @Composable (Spell) -> Unit
){
    Row{
        //radio button to denote user's selection
        RadioButton(
            selected = displayItem == radioCheck,
            onClick = {changeRadioCheck(displayItem)}
        )
        //spell's name
        Text(text = displayItem.name)
        //button to show the spell's details
        DetailButton(
            onClick = {openDetailAlert(displayItem.name) @Composable{detailContents(displayItem)}},
            modifier = Modifier
        )
    }
}