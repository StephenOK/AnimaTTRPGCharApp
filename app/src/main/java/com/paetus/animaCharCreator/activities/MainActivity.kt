package com.paetus.animaCharCreator.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.view_models.CustomFactory
import com.paetus.animaCharCreator.view_models.models.MainPageViewModel

/**
 * Startup activity for the app.
 * Gives the option to load, delete, or create a new character.
 */
class MainActivity : AppCompatActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //prevent user from flipping app
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            //initialize current context
            val context = LocalContext.current as Activity

            //start up main page's viewModel
            val mainVM: MainPageViewModel by viewModels{
                CustomFactory(MainPageViewModel::class.java, BaseCharacter())
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White)
            ){
                //display app title
                Text(
                    text = stringResource(R.string.mainTitle),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.mainSubtitle),
                    fontSize = 20.sp
                )

                Spacer(Modifier.height(30.dp))

                //display user's options
                mainVM.allButtons.forEach{
                    MainButton(mainVM, it)
                }

                //button to close app
                TextButton(onClick = {context.finish()}) {
                    Text(text = stringResource(R.string.exitLabel))
                }

                Spacer(Modifier.height(50.dp))

                //button to open analytics sharing options
                Button(
                    onClick = {mainVM.toggleDataShareOpen()},
                    modifier = Modifier
                        .width(200.dp)
                ){
                    Text(text = stringResource(R.string.sharingTitle))
                }
            }

            //display user's selected action alert
            if(mainVM.actionOpen.collectAsState().value)
                MakeAlert(mainVM, mainVM.currentAlert.collectAsState().value)

            //display share analytics selection option
            if(mainVM.dataShareOpen.collectAsState().value)
                AlertDialog(
                    onDismissRequest = {mainVM.toggleDataShareOpen()},
                    title = {Text(
                        text = stringResource(R.string.sharingHeader)
                    )},
                    text = {
                        Column {
                            //user wishes to activate sharing
                            Row(
                                modifier = Modifier
                                    .clickable{mainVM.setShareSelection(true)},
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                RadioButton(
                                    selected = mainVM.shareSelection.collectAsState().value == true,
                                    onClick = { mainVM.setShareSelection(true) }
                                )
                                Text(text = stringResource(R.string.sendData))
                            }

                            //user wishes to deactivate sharing
                            Row(
                                modifier = Modifier
                                    .clickable{mainVM.setShareSelection(false)},
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                RadioButton(
                                    selected = mainVM.shareSelection.collectAsState().value == false,
                                    onClick = { mainVM.setShareSelection(false) }
                                )
                                Text(text = stringResource(R.string.notSendData))
                            }

                            //notes about sharing
                            Row{
                                Text(
                                    text = stringResource(R.string.defaultShareNotice),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                if(mainVM.shareSelection.value != null) {
                                    Firebase.analytics.setAnalyticsCollectionEnabled(mainVM.shareSelection.value!!)
                                    Firebase.crashlytics.deleteUnsentReports()
                                    Firebase.crashlytics.setCrashlyticsCollectionEnabled(mainVM.shareSelection.value)
                                    Firebase.performance.setPerformanceCollectionEnabled(mainVM.shareSelection.value)
                                    mainVM.toggleDataShareOpen()
                                }
                                else
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.selectSharePreference),
                                        Toast.LENGTH_LONG
                                    ).show()
                            }
                        ) {
                            Text(text = stringResource(R.string.confirmLabel))
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {mainVM.toggleDataShareOpen()}
                        ) {
                            Text(text = stringResource(R.string.cancelLabel))
                        }
                    }
                )
        }
    }

    /**
     * Create a button for the main page.
     *
     * @param mainVM viewModel of the current page
     * @param item holds the data to be used in this specific button's action
     */
    @Composable
    private fun MainButton(
        mainVM: MainPageViewModel,
        item: MainPageViewModel.AlertData
    ){
        Row {
            Button(
                onClick = {
                    //set selected action
                    mainVM.setCurrentAlert(item)

                    //open associated alert
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
     * @param mainVM viewModel of the current page
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
                    //terminate process if no selection made
                    if(item.characterName.value == "")
                        Toast.makeText(
                            this@MainActivity,
                            baseContext.resources.getString(item.failedText),
                            Toast.LENGTH_SHORT
                        ).show()

                    //run action on user's indicated character
                    else
                        item.clickAct(context, item.characterName.value)

                    //close the dialog
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
                    Text(text = stringResource(R.string.cancelLabel))
                }
            }
        )
    }
}