package com.paetus.animaCharCreator.activities.fragments.dialogs

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.composables.DetailButton
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.FreeSpell
import com.paetus.animaCharCreator.view_models.models.MagicFragmentViewModel

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
        dialogTitle = stringResource(id = R.string.freeSpellDialogHeader),
        mainContent = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                //create selection item for each available spell
                items(freeList){spell ->
                    //determine that spell is legal for the book and not already taken
                    if(!spell.forbiddenElements.contains(element = magFragVM.freeElement.collectAsState().value) &&
                        !magFragVM.getSpellHeld(spell = spell)){
                        PickFreeRow(
                            spell = spell,
                            magFragVM = magFragVM
                        )
                    }
                }
            }

            //close dialog on back button press
            BackHandler{magFragVM.toggleFreeExchangeOpen()}
        },
        buttonContent = {
            //confirmation button adds spell to character
            TextButton(
                onClick = {magFragVM.addFreeSpell()}
            ){
                Text(
                    text = stringResource(id = R.string.confirmLabel),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            //back button closes dialog
            TextButton(
                onClick = {magFragVM.toggleFreeExchangeOpen()}
            ){
                Text(
                    text = stringResource(id = R.string.backLabel),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}

/**
 * Creates a row with a free spell for the user to select for their character.
 *
 * @param spell spell associated with this row
 * @param magFragVM source of currently selected spell
 */
@Composable
private fun PickFreeRow(
    spell: FreeSpell,
    magFragVM: MagicFragmentViewModel
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //radio button to denote user's selection
        RadioButton(
            selected = spell == magFragVM.selectedFreeSpell.collectAsState().value,
            onClick = {magFragVM.setSelectedFreeSpell(freeSpell = spell)},
            modifier = Modifier
                .weight(0.1f),
            colors = RadioButtonDefaults.colors(
                unselectedColor = MaterialTheme.colorScheme.onSurface
            )
        )
        //spell's name
        Text(
            text = stringResource(id = spell.name),
            modifier = Modifier
                .weight(0.65f)
                .clickable{magFragVM.setSelectedFreeSpell(freeSpell = spell)},
            textAlign = TextAlign.Center
        )

        //button to show the spell's details
        DetailButton(
            onClick = {
                magFragVM.setDetailItem(spell = spell)
                magFragVM.toggleDetailAlertOpen()
            },
            modifier = Modifier
                .weight(0.25f)
        )
    }
}

@Preview
@Composable
fun FreeSpellPreview(){
    val charInstance = BaseCharacter()

    val magFragVM = MagicFragmentViewModel(
        charInstance.magic,
        charInstance,
        charInstance.ownClass,
        LocalContext.current
    )
    magFragVM.setFreeLevel(8)

    FreeSpellPick(magFragVM)
}