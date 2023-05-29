package com.example.animabuilder.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentManager
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.view_models.CustomFactory
import com.example.animabuilder.view_models.models.MainPageViewModel

/**
 * Startup activity for the app.
 * Gives the option to load a character or create a new one.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current as Activity

            //start up main page's viewModel
            val mainVM: MainPageViewModel by viewModels{
                CustomFactory(MainPageViewModel::class.java, BaseCharacter())
            }

            //display all buttons on page
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                mainVM.allButtons.forEach{
                    MainButton(mainVM, it)
                }

                TextButton(onClick = {context.finish()}) {
                    Text(text = stringResource(R.string.exitLabel))
                }

                BackHandler{context.finish()}
            }

            if(mainVM.actionOpen.collectAsState().value)
                MakeAlert(mainVM, mainVM.currentAlert.collectAsState().value)
        }
    }

    /**
     * Create a button for the main page.
     *
     * @param item holds the data to be used in this specific button's action
     */
    @Composable
    private fun MainButton(
        mainVM: MainPageViewModel,
        item: MainPageViewModel.AlertData
    ){
        Row {
            Button(
                //open alert on click
                onClick = {
                    mainVM.setCurrentAlert(item)
                    mainVM.toggleActionOpen()
                },
                modifier = Modifier
                    .width(200.dp)
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
    fun MakeAlert(
        mainVM: MainPageViewModel,
        item: MainPageViewModel.AlertData
    ){
        val context = LocalContext.current

        AlertDialog(
            onDismissRequest = {
                //clear name and toggle open status
                item.setCharacterName("")
                mainVM.toggleActionOpen()
            },
            //get and display item's title
            title = {
                Text(
                    text = stringResource(item.headerRef),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            text = {item.MakeDisplay()},
            confirmButton = {
                TextButton(onClick = {
                    //terminate process and notify user of failure
                    if(item.characterName.value == "")
                        Toast.makeText(this@MainActivity, item.failedText, Toast.LENGTH_SHORT).show()
                    else
                        item.clickAct(context, item.characterName.value)

                    mainVM.toggleActionOpen()
                }) {
                    Text(text = stringResource(item.buttonName))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    //clear name and toggle open status
                    item.setCharacterName("")
                    mainVM.toggleActionOpen()
                }){
                    Text(text = "Cancel")
                }
            }
        )
    }
}