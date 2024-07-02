package com.paetus.animaCharCreator.view_models.models

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.composables.TextInput
import com.paetus.animaCharCreator.activities.HomeActivity
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.File

/**
 * View model that manages the main activity of the app.
 */
class MainPageViewModel: ViewModel() {
    //initialize open state of the data sharing alert
    private val _dataShareOpen = MutableStateFlow(value = false)
    val dataShareOpen = _dataShareOpen.asStateFlow()

    //initialize open state of the options menu
    private val _optionsOpen = MutableStateFlow(value = false)
    val optionsOpen = _optionsOpen.asStateFlow()

    //initialize open state of the edit secondaries menu
    private val _editSecondaries = MutableStateFlow(value = false)
    val editSecondaries = _editSecondaries.asStateFlow()

    //initialize the selected sharing state
    private val _shareSelection = MutableStateFlow<Boolean?>(value = null)
    val shareSelection = _shareSelection.asStateFlow()

    //initialize failed load alert
    private val _failedLoadOpen = MutableStateFlow(value = false)
    val failedLoadOpen = _failedLoadOpen.asStateFlow()

    //initialize save by level checkbox value
    private val isByLevel = mutableStateOf(value = false)

    /**
     * Opens and closes the data sharing alert.
     */
    fun toggleDataShareOpen(){_dataShareOpen.update{!dataShareOpen.value}}

    /**
     * Opens and closes the custom secondary editing page.
     */
    fun toggleEditSecondaries(){_editSecondaries.update{!editSecondaries.value}}

    /**
     * Sets the sharing selection to the inputted value.
     *
     * @param toShare value to set the boolean to
     */
    fun setShareSelection(toShare: Boolean){_shareSelection.update{toShare}}

    /**
     * Opens and closes the options alert.
     */
    fun toggleOptionsOpen(){_optionsOpen.update{!optionsOpen.value}}

    /**
     * Opens and closes the failed load alert.
     */
    fun toggleFailedLoadOpen(){_failedLoadOpen.update{!failedLoadOpen.value}}

    //initialize new character alert item
    private val newChar = AlertData(
        titleRef = R.string.newCharacterTitle,
        headerRef = R.string.newCharacterHeader,
        buttonName = R.string.newButtonConfirm,
        failedText = R.string.emptyNewChar,
        clickAct = {context, name ->
            //get all current files
            val fileList = context.fileList()

            //initialize file name and counter
            var fileNum = 0

            //construct initial file name
            var filename = name

            //change file name until it is unique
            while(fileList.contains(element = filename)) {
                fileNum++
                filename = "$name($fileNum)"
            }

            //initialize next intent
            val toNextPage = Intent(context, HomeActivity::class.java)

            //add file's name and notify of new character
            toNextPage.putExtra("filename", filename)
            toNextPage.putExtra("isNew", true)
            toNextPage.putExtra("isByLevel", isByLevel.value)

            //move to new intention
            startActivity(context, toNextPage, null)
        },
        display = {characterName, setCharacterName ->
            Column {
                //display character name input
                TextInput(
                    display = characterName.collectAsState().value,
                    onValueChange = {setCharacterName(it)}
                )

                //display save by level option
                //Row(
                //    modifier = Modifier
                //        .fillMaxWidth(),
                //    verticalAlignment = Alignment.CenterVertically,
                //    horizontalArrangement = Arrangement.Center
                //){
                //    //display setter for the by level save option
                //    Checkbox(
                //        checked = isByLevel.value,
                //        onCheckedChange = {isByLevel.value = !isByLevel.value}
                //    )
//
                //    Text(
                //        text = stringResource(R.string.saveByLevelPrompt),
                //    )
                //}
            }
        }
    )

    //initialize load character alert item
    private val loadChar = AlertData(
        titleRef = R.string.loadCharacterTitle,
        headerRef = R.string.loadCharacterHeader,
        buttonName = R.string.loadButtonConfirm,
        failedText = R.string.noCharSelected,
        clickAct = {context, name ->
            //initialize next intent
            val toNextPage = Intent(context, HomeActivity::class.java)

            //add file's name and notify of existing character
            toNextPage.putExtra("filename", name)
            toNextPage.putExtra("isNew", false)
            toNextPage.putExtra("isByLevel", File("${context.filesDir}/AnimaChars", name).isDirectory)

            try{
                //test for successful character building
                BaseCharacter(
                    filename = name,
                    secondaryFile = File("${context.filesDir}", "CustomSecondaryDIR"),
                    techFile = File(context.filesDir, "CustomTechDIR")
                )

                //move to next intention
                startActivity(context, toNextPage, null)
            } catch(e: Exception){
                //send failure to firebase
                Firebase.crashlytics.recordException(e)

                //notify user of failed build
                toggleFailedLoadOpen()
            }
        },
        display = {characterName, setCharacterName ->
            //initialize current context
            val context = LocalContext.current

            LazyColumn{
                //get character directory
                val dir = File("${context.filesDir}/AnimaChars")

                //search through the character file directory
                dir.listFiles()?.forEach{ charFile ->
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = characterName.collectAsState().value == charFile.name,
                                    onClick = {setCharacterName(charFile.name)}
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            //display selected item
                            RadioButton(
                                selected = (charFile.name == characterName.collectAsState().value),
                                onClick = {setCharacterName(charFile.name)},
                                colors = RadioButtonDefaults.colors(
                                    unselectedColor = MaterialTheme.colorScheme.onSurface
                                )
                            )

                            //display file name with relevant information
                            Text(
                                text = charFile.name,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    )

    private val deleteChar = AlertData(
        titleRef = R.string.deleteCharacterTitle,
        headerRef = R.string.deleteCharacterHeader,
        buttonName = R.string.deleteLabel,
        failedText = R.string.noCharSelected,
        clickAct = {context, name ->
            //delete the selected file
            File("${context.filesDir}/AnimaChars/$name").delete()
        },
        display = {characterName, setCharacterName ->
            val context = LocalContext.current
            val homeDir = File("${context.filesDir}/AnimaChars")

            LazyColumn{
                homeDir.listFiles()?.forEach{ file ->
                    //if the file is a character file
                    item{
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = characterName.collectAsState().value == file.name,
                                    onClick = {setCharacterName(file.name)}
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            //display selection button
                            RadioButton(
                                selected = characterName.collectAsState().value == file.name,
                                onClick = {setCharacterName(file.name)},
                                colors = RadioButtonDefaults.colors(
                                    unselectedColor = MaterialTheme.colorScheme.onSurface
                                )
                            )

                            //display the relevant file name
                            Text(
                                text = file.name,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    )

    //gather all button data
    val allButtons = listOf(newChar, loadChar, deleteChar)

    //initialize currently selected alert option
    private val _currentAlert = MutableStateFlow(value = newChar)
    val currentAlert = _currentAlert.asStateFlow()

    //initialize action dialog's open state
    private val _actionOpen = MutableStateFlow(value = false)
    val actionOpen = _actionOpen.asStateFlow()

    /**
     * Sets the current action the user is taking.
     *
     * @param alert action the user is taking
     */
    fun setCurrentAlert(alert: AlertData){
        _currentAlert.update{alert}
        toggleActionOpen()
    }

    /**
     * Opens and closes the action dialog prompt.
     */
    fun toggleActionOpen(){_actionOpen.update{!actionOpen.value}}

    /**
     * Object that holds data in regards to a main menu option.
     *
     * @param titleRef name used on the option button
     * @param headerRef string used on the alert header
     * @param buttonName string used on the alert's confirmation button
     * @param failedText error string when there is a failed confirmation
     * @param clickAct action to run on confirmation action
     * @param display composable to display in the action dialog
     */
    class AlertData(
        val titleRef: Int,
        val headerRef: Int,
        val buttonName: Int,
        val failedText: Int,
        val clickAct: (Context, String) -> Unit,
        val display: @Composable (StateFlow<String>, (String) -> Unit) -> Unit
    ){
        //initialize the name input for this dialog
        private val _characterName = MutableStateFlow(value = "")
        val characterName = _characterName.asStateFlow()

        /**
         * Changes the name input to the desired value.
         *
         * @param charName value to set
         */
        fun setCharacterName(charName: String){_characterName.update{charName}}

        /**
         * Composes the alert to display when active.
         */
        @Composable
        fun MakeDisplay(){
            display(_characterName) {setCharacterName(charName = it)}
        }
    }
}