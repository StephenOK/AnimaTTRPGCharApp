package com.example.animabuilder.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.*
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.File

/**
 * Startup activity for the app
 * Gives the option to load a character or create a new one
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize intent to character page
        val toNextPage = Intent(this@MainActivity, HomeActivity::class.java)

        setContent {
            //initialize character
            var charInstance: BaseCharacter

            //prepare values for new character alert
            val newOpen = remember{mutableStateOf(false)}
            val newName = remember{mutableStateOf("")}
            val newComposable = @Composable{
                TextField(value = newName.value, onValueChange = {newName.value = it})
            }
            val newConfirmation = {
                //check that a name is given for the new character
                if(newName.value == "")
                    Toast.makeText(this@MainActivity, "File must have a name!", Toast.LENGTH_SHORT).show()
                else{
                    //create new character
                    charInstance = BaseCharacter()

                    //prepare data for next activity
                    toNextPage.putExtra("filename", "AnimaChar" + newName.value)
                    toNextPage.putExtra("Character", charInstance)

                    //start next activity
                    startActivity(toNextPage)
                }
            }
            val newDecline = {newOpen.value = false}

            //prepare values for load character alert
            val loadOpen = remember{mutableStateOf(false)}
            val selected = remember{mutableStateOf("")}
            val loadComposable = @Composable{
                Column{
                    //display each file name as a radio button
                    fileList().forEach{name ->
                        if(name.contains("AnimaChar")) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .selectable(
                                        selected = (name == selected.value),
                                        onClick = { selected.value = name }
                                    )
                            ) {
                                RadioButton(
                                    selected = (name == selected.value),
                                    onClick = { selected.value = name }
                                )

                                Text(
                                    text = name.drop(9)
                                )
                            }
                        }
                    }
                }
            }
            val loadConfirmation = {
                //make sure the user has selected a character to load
                if(selected.value == "")
                    Toast.makeText(this@MainActivity, "Please select a file", Toast.LENGTH_SHORT).show()
                else {
                    val inputFile = File(this.filesDir, selected.value)

                    //instantiate character off of file data
                    charInstance = BaseCharacter(inputFile)
                    toNextPage.putExtra("filename", selected.value)
                    toNextPage.putExtra("Character", charInstance)

                    //start next activity
                    startActivity(toNextPage)
                }
            }
            val loadDecline = {
                selected.value = ""
                loadOpen.value = false
            }

            //display both buttons on page
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                MainButton("NEW CHARACTER", newOpen)
                MainButton("LOAD CHARACTER", loadOpen)
            }

            //display new character alert if it's open
            if(newOpen.value)
                MakeAlert(newOpen, "Save Character As:", newComposable,
                    newConfirmation, "Create", newDecline)

            //display load character alert if it's open
            else if(loadOpen.value)
                MakeAlert(loadOpen, "Load Character:", loadComposable,
                    loadConfirmation, "Load", loadDecline)
        }
    }

    /**
     * Create a button for the main page
     *
     * title: name displayed on the button
     * isOpen: boolean mutable for the alert's active state
     */
    @Composable
    private fun MainButton(title: String, isOpen: MutableState<Boolean>){
        Row {
            Button(
                onClick = {isOpen.value = true},
                modifier = Modifier.width(200.dp)
            ){
                Text(text = title)
            }
        }
    }

    /**
     * Creates an alert for the user to interact with
     *
     * isOpen: active state of the alert
     * titleIn: head title of the alert
     * contents: display for the alert
     * confirmation: action for the confirm button to take
     * conTitle: name displayed by the confirmation button
     * declination: action for the dismiss button to take
     */
    @Composable
    private fun MakeAlert(
        isOpen: MutableState<Boolean>,
        titleIn: String,
        contents: @Composable () -> Unit,
        confirmation: () -> Unit,
        conTitle: String,
        declination: () -> Unit
    ){
        AlertDialog(
            onDismissRequest = { isOpen.value = false },
            title = {Text(text = titleIn)},
            text = {contents()},
            confirmButton = {
                TextButton(onClick = confirmation) {
                    Text(text = conTitle)
                }
            },
            dismissButton = {
                TextButton(onClick = declination){
                    Text(text = "Cancel")
                }
            }
        )
    }
}