package com.example.animabuilder.activities.fragments.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.animabuilder.DetailButton
import com.example.animabuilder.character_creation.attributes.magic.spells.FreeSpell
import com.example.animabuilder.view_models.models.MagicFragmentViewModel

/**
 * Dialog for the player to choose their character's Free Spells.
 * Shows spells that are available to the indicated element that the character does not already have.
 * Allows display of description of the available spells.
 *
 * @param magFragVM viewModel for magic page and data
 */
@Composable
fun FreeSpellPick(
    magFragVM: MagicFragmentViewModel
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

    DialogFrame(
        "Choose Free Spell",
        {
            LazyColumn{
                //create selection item for each available spell
                items(freeList){
                    //determine that spell is legal for the book and not already taken
                    if(!it.forbiddenElements.contains(magFragVM.freeElement.collectAsState().value) && !magFragVM.getSpellHeld(it)){
                        PickFreeRow(
                            magFragVM,
                            it
                        )
                    }
                }
            }
        },
        {
            //confirmation button adds spell to character
            TextButton(onClick = {magFragVM.addFreeSpell()})
            {Text(text = "Confirm")}

            //back button closes dialog
            TextButton(onClick = {magFragVM.toggleFreeExchangeOpen() }) {
                Text(text = "Back")
            }
        }
    )
}

/**
 * Creates a row with a free spell for the user to select for their character.
 *
 * @param magFragVM source of currently selected spell
 * @param displayItem spell associated with this row
 */
@Composable
private fun PickFreeRow(
    magFragVM: MagicFragmentViewModel,
    displayItem: FreeSpell
){
    Row{
        //radio button to denote user's selection
        RadioButton(
            selected = displayItem == magFragVM.selectedFreeSpell.collectAsState().value,
            onClick = {magFragVM.setSelectedFreeSpell(displayItem)}
        )
        //spell's name
        Text(text = displayItem.name)
        //button to show the spell's details
        DetailButton(
            onClick = {
                magFragVM.setDetailItem(displayItem)
                magFragVM.toggleDetailAlertOpen()
            },
            modifier = Modifier
        )
    }
}