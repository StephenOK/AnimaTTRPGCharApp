package com.paetus.animaCharCreator.view_models.models

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.TextInput
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
    private val _dataShareOpen = MutableStateFlow(false)
    val dataShareOpen = _dataShareOpen.asStateFlow()

    //initialize the selected sharing state
    private val _shareSelection = MutableStateFlow<Boolean?>(null)
    val shareSelection = _shareSelection.asStateFlow()

    //initialize failed load alert
    private val _failedLoadOpen = MutableStateFlow(false)
    val failedLoadOpen = _failedLoadOpen.asStateFlow()

    /**
     * Opens and closes the data sharing alert.
     */
    fun toggleDataShareOpen(){_dataShareOpen.update{!dataShareOpen.value}}

    /**
     * Sets the sharing selection to the inputted value.
     *
     * @param input value to set the boolean to
     */
    fun setShareSelection(input: Boolean){_shareSelection.update{input}}

    /**
     * Opens and closes the failed load alert.
     */
    fun toggleFailedLoadOpen(){_failedLoadOpen.update{!failedLoadOpen.value}}

    //initialize new character alert item
    private val newChar = AlertData(
        R.string.newCharacterTitle,
        R.string.newCharacterHeader,
        R.string.newButtonConfirm,
        R.string.emptyNewChar,
        {context, name ->
            //get all current files
            val fileList = context.fileList()

            //initialize file name and counter
            val fileBase = "AnimaChar$name"
            var fileNum = 0

            //construct initial file name
            var filename = fileBase

            //change file name until it is unique
            while(fileList.contains(filename)) {
                fileNum++
                filename = "$fileBase($fileNum)"
            }

            //initialize next intent
            val toNextPage = Intent(context, HomeActivity::class.java)

            //add file's name and notify of new character
            toNextPage.putExtra("filename", filename)
            toNextPage.putExtra("isNew", true)

            //move to new intention
            startActivity(context, toNextPage, null)
        }
    ){characterName, setCharacterName ->
        //display character name input
        TextInput(
            display = characterName.collectAsState().value,
            onValueChange = {setCharacterName(it)}
        )
    }

    //initialize load character alert item
    private val loadChar = AlertData(
        R.string.loadCharacterTitle,
        R.string.loadCharacterHeader,
        R.string.loadButtonConfirm,
        R.string.noCharSelected,
        {context, name ->
            //initialize next intent
            val toNextPage = Intent(context, HomeActivity::class.java)

            //add file's name and notify of existing character
            toNextPage.putExtra("filename", name)
            toNextPage.putExtra("isNew", false)

            try{
                BaseCharacter(File(context.filesDir, name))

                //move to next intention
                startActivity(context, toNextPage, null)
            }
            catch(e: Exception){
                Firebase.crashlytics.recordException(e)
                toggleFailedLoadOpen()
            }
        }
    ){characterName, setCharacterName ->
        //get current file list and sort alphabetically
        val fileList = LocalContext.current.fileList()
        fileList.sort()

        LazyColumn{
            fileList.forEach{
                //check if file is a character file
                if(it.contains("AnimaChar")) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = characterName.collectAsState().value == it,
                                    onClick = { setCharacterName(it) }
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            //display selected item
                            RadioButton(
                                selected = (it == characterName.collectAsState().value),
                                onClick = {setCharacterName(it)}
                            )

                            //display file name with relevant information
                            Text(text = it.drop(9))
                        }
                    }
                }
            }
        }
    }

    private val deleteChar = AlertData(
        R.string.deleteCharacterTitle,
        R.string.deleteCharacterHeader,
        R.string.deleteLabel,
        R.string.noCharSelected,
        {context, name ->
            //delete the selected file
            context.deleteFile(name)
        }
    ){characterName, setCharacterName ->
        //get files and sort alphabetically
        val fileList = LocalContext.current.fileList()
        fileList.sort()

        LazyColumn{
            fileList.forEach{
                //if the file is a character file
                if(it.contains("AnimaChar")){
                    item{
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = characterName.collectAsState().value == it,
                                    onClick = { setCharacterName(it) }
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            //display selection button
                            RadioButton(
                                selected = characterName.collectAsState().value == it,
                                onClick = {setCharacterName(it)}
                            )

                            //display the relevant file name
                            Text(text = it.drop(9))
                        }
                    }
                }
            }
        }
    }

    //gather all button data
    val allButtons = listOf(newChar, loadChar, deleteChar)

    //initialize currently selected alert option
    private val _currentAlert = MutableStateFlow(newChar)
    val currentAlert = _currentAlert.asStateFlow()

    //initialize action dialog's open state
    private val _actionOpen = MutableStateFlow(false)
    val actionOpen = _actionOpen.asStateFlow()

    /**
     * Sets the current action the user is taking.
     *
     * @param input action the user is taking
     */
    fun setCurrentAlert(input: AlertData){
        _currentAlert.update{input}
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
        private val _characterName = MutableStateFlow("")
        val characterName = _characterName.asStateFlow()

        /**
         * Changes the name input to the desired value.
         *
         * @param input value to set
         */
        fun setCharacterName(input: String){_characterName.update{input}}

        @Composable
        fun MakeDisplay(){
            display(_characterName) {setCharacterName(it)}
        }
    }
}