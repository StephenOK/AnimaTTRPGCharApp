package com.paetus.animaCharCreator.activities.fragments.dialogs

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.DetailButton
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
        stringResource(R.string.freeSpellDialogHeader),
        {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ){
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

            //close dialog on back button press
            BackHandler{magFragVM.toggleFreeExchangeOpen()}
        },
        {
            //confirmation button adds spell to character
            TextButton(onClick = {magFragVM.addFreeSpell()})
            {Text(text = stringResource(R.string.confirmLabel))}

            //back button closes dialog
            TextButton(onClick = {magFragVM.toggleFreeExchangeOpen() }) {
                Text(text = stringResource(R.string.backLabel))
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
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        //radio button to denote user's selection
        RadioButton(
            selected = displayItem == magFragVM.selectedFreeSpell.collectAsState().value,
            onClick = {magFragVM.setSelectedFreeSpell(displayItem)},
            modifier = Modifier
                .weight(0.1f)
        )
        //spell's name
        Text(
            text = displayItem.name,
            modifier = Modifier
                .weight(0.65f)
                .clickable{magFragVM.setSelectedFreeSpell(displayItem)},
            textAlign = TextAlign.Center
        )

        //button to show the spell's details
        DetailButton(
            onClick = {
                magFragVM.setDetailItem(displayItem)
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

    val magFragVM = MagicFragmentViewModel(charInstance.magic, charInstance, charInstance.ownClass)
    magFragVM.setFreeLevel(8)

    FreeSpellPick(magFragVM)
}