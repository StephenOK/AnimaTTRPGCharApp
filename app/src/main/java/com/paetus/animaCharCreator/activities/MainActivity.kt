package com.paetus.animaCharCreator.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paetus.animaCharCreator.theme.AppTheme
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import com.paetus.animaCharCreator.BuildConfig
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.view_models.CustomFactory
import com.paetus.animaCharCreator.view_models.models.MainPageViewModel
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * Startup activity for the app.
 * Gives the option to load, delete, or create a new character.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(this.fileList().contains("settings")){
            val settingsFile = File(this.filesDir, "settings")
            val settingInputStream = FileInputStream(settingsFile)
            val settingInputReader = InputStreamReader(settingInputStream, StandardCharsets.UTF_8)
            val settingsReader = BufferedReader(settingInputReader)

            val currVersion = settingsReader.readLine().toInt()

            AppCompatDelegate.setDefaultNightMode(settingsReader.readLine().toInt())

            settingInputStream.close()
        }
        else{
            writeSettings(this)
        }

        setContent {
            AppTheme{
                MainContents()
            }
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    /**
     * Composable contents of the app's main page.
     */
    @Composable
    fun MainContents() {
        //prevent user from flipping app
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //initialize current context
        val context = LocalContext.current as Activity

        //start up main page's viewModel
        val mainVM: MainPageViewModel by viewModels {
            CustomFactory(MainPageViewModel::class.java, BaseCharacter(), context)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            //display app title
            Text(
                text = stringResource(R.string.mainTitle),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = stringResource(R.string.mainSubtitle),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(30.dp))

            //display user's options
            mainVM.allButtons.forEach {
                MainButton(mainVM, it)
            }

            //button to close app
            TextButton(onClick = { context.finish() }) {
                Text(text = stringResource(R.string.exitLabel))
            }

            Spacer(Modifier.height(50.dp))

            Button(
                onClick = {mainVM.toggleOptionsOpen()},
                modifier = Modifier
                    .width(200.dp)
            ){
                Text(text = stringResource(R.string.settingsLabel))
            }
        }

        //display user's selected action alert
        if (mainVM.actionOpen.collectAsState().value)
            MakeAlert(mainVM, mainVM.currentAlert.collectAsState().value)

        //display settings open state
        if(mainVM.optionsOpen.collectAsState().value)
            OptionsAlert(mainVM)

        //display share analytics selection option
        if (mainVM.dataShareOpen.collectAsState().value)
            ShareAlert(mainVM, context)

        //dialog to notify user of failed character load
        if(mainVM.failedLoadOpen.collectAsState().value)
            FailedLoadAlert(mainVM)
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
                            context.getString(item.failedText),
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

    @Composable
    private fun OptionsAlert(
        mainVM: MainPageViewModel
    ){
        AlertDialog(
            onDismissRequest = {mainVM.toggleOptionsOpen()},
            title = {Text(text = stringResource(R.string.settingsLabel))},
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                                } else {
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                                }
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter =
                                if(isSystemInDarkTheme())
                                    painterResource(R.drawable.baseline_toggle_off_24)
                                else
                                    painterResource(R.drawable.baseline_toggle_on_24),
                            contentDescription = null,
                            modifier = Modifier
                                .weight(0.25f)
                        )
                        Text(
                            text =
                                if(isSystemInDarkTheme())
                                    stringResource(R.string.darkMode)
                                else
                                    stringResource(R.string.lightMode),
                            modifier = Modifier
                                .weight(0.5f),
                            fontSize = 20.sp
                        )

                        Spacer(Modifier.weight(0.25f))
                    }

                    Spacer(Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                mainVM.toggleDataShareOpen()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(Modifier.weight(0.25f))
                        Text(
                            text = stringResource(R.string.sharingTitle),
                            modifier = Modifier
                                .weight(0.25f),
                            fontSize = 20.sp
                        )

                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_forward_ios_24),
                            contentDescription = null,
                            modifier = Modifier
                                .weight(0.25f)
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        writeSettings(this)
                        mainVM.toggleOptionsOpen()
                    }
                ) {
                    Text(text = stringResource(R.string.closeLabel))
                }
            }
        )
    }

    /**
     * Composes an Alert Dialog for the user to select their sharing settings.
     *
     * @param mainVM viewModel that manages this page
     * @param context page context
     */
    @Composable
    private fun ShareAlert(
        mainVM: MainPageViewModel,
        context: Context
    ) {
        AlertDialog(
            onDismissRequest = {mainVM.toggleDataShareOpen()},
            title = {
                //prompt for user sharing
                Text(text = stringResource(R.string.sharingHeader))
            },
            text = {
                Column {
                    //user wishes to activate sharing
                    Row(
                        modifier = Modifier
                            .clickable { mainVM.setShareSelection(true) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = mainVM.shareSelection.collectAsState().value == true,
                            onClick = { mainVM.setShareSelection(true) }
                        )
                        Text(text = stringResource(R.string.sendData))
                    }

                    //user wishes to deactivate sharing
                    Row(
                        modifier = Modifier
                            .clickable { mainVM.setShareSelection(false) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = mainVM.shareSelection.collectAsState().value == false,
                            onClick = { mainVM.setShareSelection(false) }
                        )
                        Text(text = stringResource(R.string.notSendData))
                    }

                    //notes about sharing
                    Row {
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
                        //set user's selection if one made
                        if (mainVM.shareSelection.value != null) {
                            //clear unsent data
                            Firebase.crashlytics.deleteUnsentReports()

                            //set data sending to user's preferred setting
                            Firebase.analytics.setAnalyticsCollectionEnabled(mainVM.shareSelection.value!!)
                            Firebase.crashlytics.setCrashlyticsCollectionEnabled(mainVM.shareSelection.value)
                            Firebase.performance.setPerformanceCollectionEnabled(mainVM.shareSelection.value)

                            //close dialog
                            mainVM.toggleDataShareOpen()
                        }
                        //notify user of no selection made
                        else
                            Toast.makeText(
                                context,
                                context.getString(R.string.selectSharePreference),
                                Toast.LENGTH_LONG
                            ).show()
                    }
                ) {Text(text = stringResource(R.string.confirmLabel))}
            },

            //close dialog button
            dismissButton = {
                TextButton(
                    onClick = { mainVM.toggleDataShareOpen() }
                ) {
                    Text(text = stringResource(R.string.cancelLabel))
                }
            }
        )
    }

    /**
     * Compose an alert that notifies the user of a failed character load.
     */
    @Composable
    private fun FailedLoadAlert(
        mainVM: MainPageViewModel
    ) {
        AlertDialog(
            onDismissRequest = {mainVM.toggleFailedLoadOpen()},
            title = {Text(text = stringResource(R.string.failedLoadTitle))},
            text = {Text(text = stringResource(R.string.failedLoadText))},
            confirmButton = {
                TextButton(onClick = {mainVM.toggleFailedLoadOpen()}) {
                    Text(text = stringResource(R.string.closeLabel))
                }
            }
        )
    }
}

fun writeSettings(home: Activity){
    val saveSettings = home.openFileOutput("settings", Context.MODE_PRIVATE)

    val settingsDefault = ByteArrayOutputStream()

    settingsDefault.write(
        """${BuildConfig.VERSION_CODE}""".toByteArray(StandardCharsets.UTF_8),
        0,
        """${BuildConfig.VERSION_CODE}""".toByteArray(StandardCharsets.UTF_8).size
    )

    settingsDefault.write(
        "\n".toByteArray(StandardCharsets.UTF_8),
        0,
        "\n".toByteArray(StandardCharsets.UTF_8).size
    )

    settingsDefault.write(
        """${AppCompatDelegate.getDefaultNightMode()}""".toByteArray(StandardCharsets.UTF_8),
        0,
        """${AppCompatDelegate.getDefaultNightMode()}""".toByteArray(StandardCharsets.UTF_8).size
    )

    settingsDefault.write(
        "\n".toByteArray(StandardCharsets.UTF_8),
        0,
        "\n".toByteArray(StandardCharsets.UTF_8).size
    )

    settingsDefault.close()

    saveSettings.write(settingsDefault.toByteArray())

    saveSettings.close()
}