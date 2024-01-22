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
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import com.paetus.animaCharCreator.BuildConfig
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.activities.fragments.dialogs.DialogFrame
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.composables.OutlinedDropdown
import com.paetus.animaCharCreator.theme.detailLightColors
import com.paetus.animaCharCreator.theme.mainLightColors
import com.paetus.animaCharCreator.view_models.CustomFactory
import com.paetus.animaCharCreator.view_models.models.EditSecondaryViewModel
import com.paetus.animaCharCreator.view_models.models.MainPageViewModel
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * Startup activity for the app.
 * Gives the option to load, delete, or create a new character.
 * Gives options for other app settings.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //if a settings file has been created
        if(this.fileList().contains("settings")){
            //get the settings file and its reader
            val settingsFile = File(this.filesDir, "settings")
            val settingInputStream = FileInputStream(settingsFile)
            val settingInputReader = InputStreamReader(settingInputStream, StandardCharsets.UTF_8)
            val settingsReader = BufferedReader(settingInputReader)

            val currVersion = settingsReader.readLine().toInt()

            //set night mode
            AppCompatDelegate.setDefaultNightMode(settingsReader.readLine().toInt())

            settingInputStream.close()
        }
        else{
            //create settings file if it doesn't exist
            writeSettings(this)
        }

        //find character files
        val charFileDIR = File("$filesDir/AnimaChars")

        //find custom data files
        val customSecondDIR = File("$filesDir/CustomSecondaryDIR")
        val customTechDIR = File("$filesDir/CustomTechDIR")

        //if character file directory doesn't exist
        if(!charFileDIR.isDirectory){
            //instantiate is as directory
            charFileDIR.mkdir()

            //for each outside character file
            fileList().forEach{filename ->
                if(filename != "AnimaChars" && filename.contains("AnimaChar")){
                    //retrieve name without flag prefix
                    val newName = filename.drop(9)

                    //copy file data to its new location
                    val original = File("$filesDir/$filename")
                    original.copyTo(
                        File("$filesDir/AnimaChars/$newName")
                    )

                    //remove outside file
                    original.delete()
                }
            }
        }

        //create custom secondaries directory if it doesn't exist
        if(!customSecondDIR.isDirectory)
            customSecondDIR.mkdir()

        //if custom technique directory doesn't exist
        if(!customTechDIR.isDirectory){
            //create the directory
            customTechDIR.mkdir()

            //for each character file
            charFileDIR.walk().forEach{charFile ->
                //write its custom techniques to this directory
                if(charFile != charFileDIR){
                    BaseCharacter(
                        charFile = charFile,
                        secondaryFile = customSecondDIR,
                        techFile = customTechDIR
                    ).ki.saveOutCustoms(directory = customTechDIR)
                }
            }
        }

        //draw main page contents
        setContent {
            MaterialTheme(colorScheme = mainLightColors){
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
        val dummyCharacter = BaseCharacter()

        //start up main page's viewModel
        val mainVM: MainPageViewModel by viewModels {
            CustomFactory(
                viewModel = MainPageViewModel::class.java,
                charInstance = dummyCharacter,
                context = context
            )
        }

        //create viewModel for editing secondary characteristics
        val editSecondaryVM: EditSecondaryViewModel by viewModels{
            CustomFactory(
                viewModel = EditSecondaryViewModel::class.java,
                charInstance = dummyCharacter,
                context = context
            )
        }

        Column(
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.background)
        ) {
            //create title border format
            val textPaintStroke = Paint().asFrameworkPaint().apply{
                isAntiAlias = true
                style = android.graphics.Paint.Style.STROKE
                textSize = 60.sp.value
                textAlign = android.graphics.Paint.Align.CENTER
                color = MaterialTheme.colorScheme.onBackground.toArgb()
                strokeWidth = 5f
                strokeMiter = 10f
                strokeJoin = android.graphics.Paint.Join.ROUND
                isFakeBoldText = true
            }

            //create title fill format
            val textPaint = Paint().asFrameworkPaint().apply{
                isAntiAlias = true
                style = android.graphics.Paint.Style.FILL
                textSize = 60.sp.value
                textAlign = android.graphics.Paint.Align.CENTER
                color = MaterialTheme.colorScheme.primary.toArgb()
                isFakeBoldText = true
            }

            //retrieve app title
            val display = stringResource(id = R.string.mainTitle)

            //display app title
            Canvas(
                modifier = Modifier,
                onDraw = {
                    drawIntoCanvas {
                        it.nativeCanvas.drawText(
                            display,
                            0f,
                            0f,
                            textPaintStroke
                        )

                        it.nativeCanvas.drawText(
                            display,
                            0f,
                            0f,
                            textPaint
                        )
                    }
                }
            )

            //display subtitle
            Text(
                text = stringResource(id = R.string.mainSubtitle),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(30.dp))

            //display user's file actions
            mainVM.allButtons.forEach{fileButton ->
                MainButton(
                    alertData = fileButton,
                    mainVM = mainVM
                )
            }

            //button to close app
            TextButton(onClick = {context.finish()}) {
                Text(text = stringResource(id = R.string.exitLabel))
            }

            Spacer(modifier = Modifier.height(50.dp))

            //display additional options
            Button(
                onClick = {mainVM.toggleOptionsOpen()},
                modifier = Modifier
                    .width(200.dp)
            ){
                Text(text = stringResource(id = R.string.settingsLabel))
            }
        }

        MaterialTheme(colorScheme = detailLightColors) {
            //display user's selected action alert
            if (mainVM.actionOpen.collectAsState().value)
                MakeAlert(
                    alertData = mainVM.currentAlert.collectAsState().value,
                    mainVM = mainVM
                )

            //display settings open state
            if (mainVM.optionsOpen.collectAsState().value)
                OptionsAlert(
                    editSecondaryVM = editSecondaryVM,
                    mainVM = mainVM
                )

            //display custom secondaries editing
            if(mainVM.editSecondaries.collectAsState().value)
                SecondariesAlert(
                    mainVM = mainVM,
                    editSecondaryVM = editSecondaryVM
                )

            //display share analytics selection option
            if (mainVM.dataShareOpen.collectAsState().value)
                ShareAlert(
                    mainVM = mainVM
                )

            //dialog to notify user of failed character load
            if (mainVM.failedLoadOpen.collectAsState().value)
                FailedLoadAlert(mainVM = mainVM)
        }
    }

    /**
     * Create a button for the main page.
     *
     * @param alertData holds the data to be used in this specific button's action
     * @param mainVM viewModel of the current page
     */
    @Composable
    private fun MainButton(
        alertData: MainPageViewModel.AlertData,
        mainVM: MainPageViewModel
    ){
        Row {
            Button(
                onClick = {
                    //set selected action
                    mainVM.setCurrentAlert(alert = alertData)
                },
                modifier = Modifier
                    .width(200.dp)
            ){
                Text(text = stringResource(id = alertData.titleRef))
            }
        }
    }

    /**
     * Creates an alert for the user to interact with.
     *
     * @param alertData data package to use in this alert
     * @param mainVM viewModel of the current page
     */
    @Composable
    fun MakeAlert(
        alertData: MainPageViewModel.AlertData,
        mainVM: MainPageViewModel
    ){
        //get local context
        val context = LocalContext.current

        AlertDialog(
            onDismissRequest = {
                //clear name and toggle open status
                alertData.setCharacterName(charName = "")
                mainVM.toggleActionOpen()
            },
            //get and display item's title
            title = {
                Text(
                    text = stringResource(id = alertData.headerRef),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            text = {alertData.MakeDisplay()},
            confirmButton = {
                TextButton(onClick = {
                    //terminate process if no selection made
                    if(alertData.characterName.value == "")
                        Toast.makeText(
                            this@MainActivity,
                            context.getString(alertData.failedText),
                            Toast.LENGTH_SHORT
                        ).show()

                    //run action on user's indicated character
                    else
                        alertData.clickAct(context, alertData.characterName.value)

                    //close the dialog
                    mainVM.toggleActionOpen()
                }) {
                    Text(
                        text = stringResource(id = alertData.buttonName),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        //clear name and toggle open status
                        alertData.setCharacterName("")
                        mainVM.toggleActionOpen()
                    }
                ){
                    Text(
                        text = stringResource(id = R.string.cancelLabel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )
    }

    /**
     * Display additional options to the user on request.
     *
     * @param editSecondaryVM viewModel for secondary characteristic editing
     * @param mainVM viewModel for this activity
     */
    @Composable
    private fun OptionsAlert(
        editSecondaryVM: EditSecondaryViewModel,
        mainVM: MainPageViewModel
    ){
        AlertDialog(
            //hide alert on dismissal
            onDismissRequest = {mainVM.toggleOptionsOpen()},
            title = {
                Text(
                    text = stringResource(id = R.string.settingsLabel),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            text = {
                Column(
                    horizontalAlignment = CenterHorizontally
                ){
                    //Row(
                    //    modifier = Modifier
                    //        .fillMaxWidth(),
                    //    horizontalArrangement = Arrangement.Center,
                    //    verticalAlignment = Alignment.CenterVertically
                    //){
                    //    Switch(
                    //        checked = !isSystemInDarkTheme(),
                    //        onCheckedChange = {
                    //            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    //                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    //            }
                    //            else {
                    //                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    //            }
//
                    //            writeSettings(this@MainActivity)
                    //        },
                    //        modifier = Modifier
                    //            .weight(0.25f)
                    //    )
                    //    Text(
                    //        text =
                    //            if(isSystemInDarkTheme())
                    //                stringResource(R.string.darkMode)
                    //            else
                    //                stringResource(R.string.lightMode),
                    //        modifier = Modifier
                    //            .weight(0.5f),
                    //        fontSize = 20.sp
                    //    )
//
                    //    Spacer(Modifier.weight(0.25f))
                    //}
//
                    //Spacer(Modifier.height(10.dp))

                    //display option to edit secondary characteristics
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                //refresh secondary characteristic items
                                editSecondaryVM.refreshCustomList()

                                //open editing window
                                mainVM.toggleEditSecondaries()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = CenterVertically
                    ){
                        Spacer(modifier = Modifier.weight(0.25f))

                        //prompt for secondary characteristic editing option
                        Text(
                            text = stringResource(id = R.string.editSecondariesTitle),
                            modifier = Modifier
                                .weight(0.5f),
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_forward_ios_24),
                            contentDescription = null,
                            modifier = Modifier
                                .weight(0.25f)
                        )
                    }

                    //display option for setting analytics sharing
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                mainVM.toggleDataShareOpen()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = CenterVertically
                    ){
                        Spacer(modifier = Modifier.weight(0.25f))

                        //prompt for analytics settings editing
                        Text(
                            text = stringResource(id = R.string.sharingTitle),
                            modifier = Modifier
                                .weight(0.5f),
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
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
                        //save settings and close alert
                        writeSettings(this)
                        mainVM.toggleOptionsOpen()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.closeLabel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )
    }

    /**
     * Displays a window for editing secondary characteristics.
     *
     * @param mainVM viewModel for this activity
     * @param editSecondaryVM viewModel for this alert
     */
    @Composable
    private fun SecondariesAlert(
        mainVM: MainPageViewModel,
        editSecondaryVM: EditSecondaryViewModel
    ) {
        DialogFrame(
            dialogTitle = stringResource(id = R.string.editSecondariesTitle),
            mainContent = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ){
                    //for each custom secondary file present
                    editSecondaryVM.customList.forEach{editPanel ->
                        //display the characteristic's name
                        item{
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        //make this the only open item
                                        if (!editPanel.editOpen.value)
                                            editSecondaryVM.closeAll()

                                        //toggle secondary's open state
                                        editPanel.toggleOpen()
                                    },
                                horizontalArrangement = Arrangement.Center
                            ){
                                Text(
                                    text = editPanel.customChar.name.value,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        //display editable values
                        item{
                            AnimatedVisibility(
                                visible = editPanel.editOpen.collectAsState().value,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ){
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = CenterHorizontally
                                ){
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(0.8f),
                                        verticalAlignment = CenterVertically
                                    ) {
                                        //input to change public setting for characteristic
                                        Checkbox(
                                            checked = editPanel.isPrivate.collectAsState().value,
                                            onCheckedChange = {_ -> editPanel.togglePrivate()},
                                            modifier = Modifier
                                                .weight(0.1f)
                                        )

                                        Text(
                                            text = stringResource(id = R.string.publicLabel),
                                            modifier = Modifier
                                                .weight(0.4f),
                                            textAlign = TextAlign.Center
                                        )

                                        //button to delete characteristic
                                        TextButton(
                                            onClick = {
                                                editSecondaryVM.toggleDeleteConfirm()
                                            },
                                            modifier = Modifier
                                                .weight(0.5f)
                                        ) {
                                            Text(text = stringResource(id = R.string.deleteLabel))
                                        }
                                    }

                                    //input to change secondary's field and primary base and
                                    editPanel.allDropdowns.forEach {dropdown ->
                                        OutlinedDropdown(
                                            data = dropdown
                                        ) {}
                                    }
                                }
                            }
                        }
                    }
                }
            },
            buttonContent = {
                Row {
                    //button to save changes
                    TextButton(
                        onClick = {
                            //rewrite characteristic data
                            editSecondaryVM.customList.forEach{panel ->
                                editSecondaryVM.overwriteItem(customChar = panel.customChar)
                            }

                            //close editing page
                            mainVM.toggleEditSecondaries()
                        }
                    ){
                        Text(
                            text = stringResource(id = R.string.saveLabel),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    //button to cancel changes
                    TextButton(
                        onClick = {mainVM.toggleEditSecondaries()}
                    ){
                        Text(
                            text = stringResource(id = R.string.cancelLabel),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        )

        //display confirmation of deletion alert
        if(editSecondaryVM.deleteConfirmOpen.collectAsState().value)
            DeleteSecondaryConfirmation(editSecondaryVM = editSecondaryVM)
    }

    /**
     * Displays an alert that the user can use to confirm a secondary characteristic's deletion.
     *
     * @param editSecondaryVM viewModel for the secondary characteristics editing page
     */
    @Composable
    fun DeleteSecondaryConfirmation(
        editSecondaryVM: EditSecondaryViewModel
    ){
        val context = LocalContext.current

        AlertDialog(
            onDismissRequest = {},

            //prompt for user confirmation
            title = {
                Text(
                    text = stringResource(
                        id = R.string.deleteSecondaryPrompt,
                        editSecondaryVM.deletingItem.collectAsState().value
                    )
                )
            },

            //on deletion confirmation
            confirmButton = {
                TextButton(
                    onClick = {
                        //get custom secondary's file
                        File("${context.filesDir}/CustomSecondaryDIR/${editSecondaryVM.deletingItem.value}").delete()

                        //refresh viewModel items and close confirmation screen
                        editSecondaryVM.refreshCustomList()
                        editSecondaryVM.toggleDeleteConfirm()
                    }
                ){
                    Text(
                        text = stringResource(id = R.string.confirmLabel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },

            //on deletion cancellation
            dismissButton = {
                TextButton(
                    //close deletion alert
                    onClick = {editSecondaryVM.toggleDeleteConfirm()}
                ){
                    Text(
                        text = stringResource(id = R.string.cancelLabel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )
    }

    /**
     * Composes an Alert Dialog for the user to select their sharing settings.
     *
     * @param mainVM viewModel that manages this page
     */
    @Composable
    private fun ShareAlert(
        mainVM: MainPageViewModel
    ) {
        //initialize local context
        val context = LocalContext.current

        AlertDialog(
            onDismissRequest = {mainVM.toggleDataShareOpen()},
            title = {
                //prompt for user sharing
                Text(
                    text = stringResource(id = R.string.sharingHeader),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            text = {
                Column {
                    //user wishes to activate sharing
                    Row(
                        modifier = Modifier
                            .clickable {mainVM.setShareSelection(true)},
                        verticalAlignment = CenterVertically
                    ) {
                        RadioButton(
                            selected = mainVM.shareSelection.collectAsState().value == true,
                            onClick = {mainVM.setShareSelection(toShare = true)},
                            colors = RadioButtonDefaults.colors(
                                unselectedColor = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Text(
                            text = stringResource(id = R.string.sendData),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    //user wishes to deactivate sharing
                    Row(
                        modifier = Modifier
                            .clickable { mainVM.setShareSelection(false) },
                        verticalAlignment = CenterVertically
                    ) {
                        RadioButton(
                            selected = mainVM.shareSelection.collectAsState().value == false,
                            onClick = {mainVM.setShareSelection(toShare = false)},
                            colors = RadioButtonDefaults.colors(
                                unselectedColor = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Text(
                            text = stringResource(id = R.string.notSendData),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    //display notes about sharing
                    Row {
                        Text(
                            text = stringResource(id = R.string.defaultShareNotice),
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
                ) {
                    Text(
                        text = stringResource(id = R.string.confirmLabel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },

            //close dialog button
            dismissButton = {
                TextButton(
                    onClick = {mainVM.toggleDataShareOpen()}
                ) {
                    Text(
                        text = stringResource(id = R.string.cancelLabel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )
    }

    /**
     * Compose an alert that notifies the user of a failed character load.
     *
     * @param mainVM viewModel for the main page
     */
    @Composable
    private fun FailedLoadAlert(
        mainVM: MainPageViewModel
    ) {
        AlertDialog(
            onDismissRequest = {mainVM.toggleFailedLoadOpen()},
            title = {Text(text = stringResource(id = R.string.failedLoadTitle))},
            text = {Text(text = stringResource(id = R.string.failedLoadText))},
            confirmButton = {
                TextButton(onClick = {mainVM.toggleFailedLoadOpen()}) {
                    Text(
                        text = stringResource(id = R.string.closeLabel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )
    }
}

/**
 * Writes the app settings to its own file.
 *
 * @param home activity writing the settings from
 */
fun writeSettings(home: Activity){
    //open file output to the settings file
    val saveSettings = home.openFileOutput("settings", Context.MODE_PRIVATE)

    //initialize byte array output stream
    val settingsDefault = ByteArrayOutputStream()

    //write the current version and the current default night mode
    writeDataTo(settingsDefault, BuildConfig.VERSION_CODE)
    writeDataTo(settingsDefault, AppCompatDelegate.getDefaultNightMode())

    //close byte output stream
    settingsDefault.close()

    //write byte array to file
    saveSettings.write(settingsDefault.toByteArray())

    //close file writer
    saveSettings.close()
}