package com.example.animabuilder.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.*
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.animabuilder.R

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.view_models.MainPageViewModel
import java.io.File

/**
 * Startup activity for the app.
 * Gives the option to load a character or create a new one.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //start up main page's viewModel
        val mainVM = MainPageViewModel()

        setContent {
            //display both buttons on page
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                MainButton(mainVM.newChar)
                MainButton(mainVM.loadChar)
            }

            //display new character alert if it's open
            if(mainVM.newChar.isOpen.collectAsState().value)
                MakeAlert(
                    mainVM.newChar
                )

            //display load character alert if it's open
            else if(mainVM.loadChar.isOpen.collectAsState().value)
                MakeAlert(
                    mainVM.loadChar
                )
        }
    }

    /**
     * Create a button for the main page.
     *
     * @param item holds the data to be used in this specific button's action
     */
    @Composable
    private fun MainButton(item: MainPageViewModel.AlertData){
        Row {
            Button(
                //open alert on click
                onClick = {item.toggleOpen()},
                modifier = Modifier.width(200.dp)
            ){
                Text(text = stringResource(item.titleRef))
            }
        }
    }

    /**
     * Creates an alert for the user to interact with.
     *
     * @param item data package to use in this alert
     */
    @Composable
    private fun MakeAlert(
        item: MainPageViewModel.AlertData
    ){
        AlertDialog(
            onDismissRequest = {
                //clear name and toggle open status
                item.setCharacterName("")
                item.toggleOpen()
            },
            //get and display item's title
            title = {Text(text = stringResource(item.headerRef))},
            text = {
                //display for new character
                if(item.headerRef == R.string.newCharacterHeader)
                    item.NewDisplay()
                //display for loading a character
                else
                    item.LoadDisplay()
            },
            confirmButton = {
                TextButton(onClick = {
                    //initialize intent
                    val toNextPage = Intent(this@MainActivity, HomeActivity::class.java)

                    //ready filename to pass
                    val filename =
                        if(item.headerRef == R.string.newCharacterHeader)
                            "AnimaChar" + item.characterName.value
                        else item.characterName.value

                    //ready character to pass
                    val charInstance =
                        if(item.headerRef == R.string.newCharacterHeader)
                            BaseCharacter()
                        else
                            BaseCharacter(File(this.filesDir, filename))

                    //terminate process and notify user of failure
                    if(item.characterName.value == "")
                        Toast.makeText(this@MainActivity, item.failedText, Toast.LENGTH_SHORT).show()
                    else{
                        //prepare data for next activity
                        toNextPage.putExtra("filename", filename)
                        toNextPage.putExtra("Character", charInstance)

                        //start next activity
                        startActivity(toNextPage)
                    }
                }) {
                    Text(text = stringResource(item.buttonName))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    //clear name and toggle open status
                    item.setCharacterName("")
                    item.toggleOpen()
                }){
                    Text(text = "Cancel")
                }
            }
        )
    }
}