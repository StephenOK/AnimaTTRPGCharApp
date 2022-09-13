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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.animabuilder.R

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
            var charInstance: BaseCharacter

            val newOpen = remember{mutableStateOf(false)}
            val newName = remember{mutableStateOf("")}

            val newComposable = @Composable{
                TextField(value = newName.value, onValueChange = {newName.value = it})
            }

            val newConfirmation = {
                if(newName.value == "")
                    Toast.makeText(this@MainActivity, "File must have a name!", Toast.LENGTH_SHORT).show()
                else{
                    charInstance = BaseCharacter()
                    toNextPage.putExtra("filename", newName.value)
                    toNextPage.putExtra("Character", charInstance)

                    //start next activity
                    startActivity(toNextPage)
                }
            }

            val newDecline = {newOpen.value = false}

            val loadOpen = remember{mutableStateOf(false)}

            val selected = remember{mutableStateOf("")}

            val loadComposable = @Composable{
                Column{
                    fileList().forEach{name ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .selectable(
                                    selected = (name == selected.value),
                                    onClick = {selected.value = name}
                                )
                        ){
                            RadioButton(
                                selected = (name == selected.value),
                                onClick = {selected.value = name}
                            )

                            Text(
                                text = name
                            )
                        }
                    }
                }
            }

            val loadConfirmation = {
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

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                mainButton("NEW CHARACTER", newOpen)
                mainButton("LOAD CHARACTER", loadOpen)
            }

            if(newOpen.value)
                makeAlert(newOpen, "Save Character As:", newComposable,
                    newConfirmation, "Create", newDecline)

            else if(loadOpen.value)
                makeAlert(loadOpen, "Load Character:", loadComposable,
                    loadConfirmation, "Load", loadDecline)
        }
    }

    @Composable
    private fun mainButton(title: String, isOpen: MutableState<Boolean>){
        Row {
            Button(
                onClick = {isOpen.value = true},
                modifier = Modifier.width(200.dp)
            ){
                Text(text = title)
            }
        }
    }

    @Composable
    private fun makeAlert(
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