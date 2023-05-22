package com.example.animabuilder.view_models.models

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
import com.example.animabuilder.R
import com.example.animabuilder.TextInput
import com.example.animabuilder.activities.HomeActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the main activity of the app.
 */
class MainPageViewModel: ViewModel() {
    //initialize new character alert item
    private val newChar = AlertData(
        R.string.newCharacterTitle,
        R.string.newCharacterHeader,
        R.string.newButtonConfirm,
        "File must have a name!",
        {context, name ->
            val toNextPage = Intent(context, HomeActivity::class.java)

            val filename = "AnimaChar$name"

            toNextPage.putExtra("filename", filename)
            toNextPage.putExtra("isNew", true)

            startActivity(context, toNextPage, null)
        }
    ){characterName, setCharacterName ->
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
        "Please select a file",
        {context, name ->
            val toNextPage = Intent(context, HomeActivity::class.java)

            toNextPage.putExtra("filename", name)
            toNextPage.putExtra("isNew", false)

            startActivity(context, toNextPage, null)
        }
    ){characterName, setCharacterName ->
        val context = LocalContext.current

        val fileList = context.fileList()
        fileList.sort()

        LazyColumn{
            //display each file name as a radio button
            fileList.forEach{
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
                            RadioButton(
                                selected = (it == characterName.collectAsState().value),
                                onClick = {setCharacterName(it)}
                            )

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
        "No character selected",
        {context, name ->
            context.deleteFile(name)
        }
    ){characterName, setCharacterName ->
        val context = LocalContext.current

        val fileList = context.fileList()
        fileList.sort()

        LazyColumn{
            fileList.forEach{
                if(it.contains("AnimaChar")){
                    item{
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = characterName.collectAsState().value == it,
                                    onClick = {setCharacterName(it)}
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            RadioButton(
                                selected = characterName.collectAsState().value == it,
                                onClick = {setCharacterName(it)}
                            )

                            Text(text = it.drop(9))
                        }
                    }
                }
            }
        }
    }

    val allButtons = listOf(newChar, loadChar, deleteChar)

    private val _currentAlert = MutableStateFlow(newChar)
    val currentAlert = _currentAlert.asStateFlow()

    fun setCurrentAlert(input: AlertData){_currentAlert.update{input}}

    private val _actionOpen = MutableStateFlow(false)
    val actionOpen = _actionOpen.asStateFlow()

    fun toggleActionOpen(){_actionOpen.update{!actionOpen.value}}

    /**
     * Object that holds data in regards to a main menu option.
     *
     * @param titleRef name used on the option button
     * @param headerRef string used on the alert header
     * @param buttonName string used on the alert's confirmation button
     * @param failedText error string when there is a failed confirmation
     */
    class AlertData(
        val titleRef: Int,
        val headerRef: Int,
        val buttonName: Int,
        val failedText: String,
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