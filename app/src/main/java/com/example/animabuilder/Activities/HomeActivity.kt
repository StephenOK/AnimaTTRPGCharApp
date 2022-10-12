package com.example.animabuilder.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animabuilder.R
import com.example.animabuilder.activities.fragments.home_fragments.*
import com.example.animabuilder.character_creation.BaseCharacter
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.io.IOException

@OptIn(ExperimentalComposeUiApi::class)
var keyboardActive: SoftwareKeyboardController? = null

val detailAlertOn = mutableStateOf(false)
val detailItem = mutableStateOf("")
val contents: MutableState<(@Composable () -> Unit)?> = mutableStateOf(null)

/**
 * Activity that runs all character creation fragments
 * Initially loads the CharacterPageFragment
 */

class HomeActivity : AppCompatActivity() {

    //private enumeration for current displayed page
    private enum class ScreenPage{
        Primary,
        Secondary,
        Advantages,
        Weapon_Modules,
        Ki,
        Magic,
        Psychic,
        Equipment
    }


    //character to work with and its associated file name
    private lateinit var charInstance: BaseCharacter
    private var filename: String? = null

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //get values from intent
        charInstance = intent.getSerializableExtra("Character") as BaseCharacter
        filename = intent.getStringExtra("filename")

        setContent{
            keyboardActive = LocalSoftwareKeyboardController.current

            //create scaffold state and coroutine scope
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()

            //create navigation controller
            val navController = rememberNavController()

            //set initial display page
            val currentFragment = remember{ mutableStateOf(ScreenPage.Primary)}

            //boolean for exit alert
            val exitOpen = remember{mutableStateOf(false)}

            //displays for maximum development points
            val maxDP = remember{mutableStateOf(charInstance.devPT)}
            val maxCombat = remember{mutableStateOf(charInstance.maxCombatDP)}
            val maxMagic = remember{mutableStateOf(charInstance.maxMagDP)}
            val maxPsychic = remember{mutableStateOf(charInstance.maxPsyDP)}

            //displays for spent development points
            val usedDP = remember { mutableStateOf(charInstance.spentTotal) }
            val usedCombat = remember { mutableStateOf(charInstance.ptInCombat) }
            val usedMagic = remember { mutableStateOf(charInstance.ptInMag) }
            val usedPsychic = remember { mutableStateOf(charInstance.ptInPsy) }

            //scaffold for the home page
            Scaffold(
                scaffoldState = scaffoldState,

                //home page's top bar
                topBar = {TopAppBar (
                    //update title with any page change
                    title = {Text(text = currentFragment.value.name)},

                    //icon to open the navigation drawer
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                if(scaffoldState.drawerState.isClosed)
                                    scope.launch{scaffoldState.drawerState.open()}
                            })
                        {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Open"
                            )
                        }
                })},

                //navigation drawer
                drawerContent = {
                    Column{
                        //for each page enumeration present
                        enumValues<ScreenPage>().forEach {
                            //create a drawer button with the given onclick function
                            DrawerButton(it.name)
                            {
                                currentFragment.value = it
                                scope.launch{scaffoldState.drawerState.close()}
                                navController.navigate(it.name)
                            }
                        }

                        //drawer button for saving the character
                        DrawerButton("Save"){
                            scope.launch{scaffoldState.drawerState.close()}
                            attemptSave()}

                        //drawer button for exiting the character creator
                        DrawerButton("Exit"){
                            scope.launch{scaffoldState.drawerState.close()}
                            exitOpen.value = true }
                    }
                },

                //bottom bar keeps track of development point maximums and spent
                bottomBar = {
                    Column {
                        //row for table header
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.weight(0.2f))
                            //total column
                            Text(
                                text = stringResource(R.string.totalLabel),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            //combat column
                            Text(
                                text = stringResource(R.string.dpCombatLabel),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            //magic column
                            Text(
                                text = stringResource(R.string.dpMagicLabel),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            //psychic column
                            Text(
                                text = stringResource(R.string.dpPsychicLabel),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                        }

                        //create row for maximum values
                        BottomBarRow(stringResource(R.string.maxRowLabel), maxDP.value.toString(),
                            maxCombat.value.toString(), maxMagic.value.toString(), maxPsychic.value.toString())

                        //create row for spent values
                        BottomBarRow(stringResource(R.string.usedRowLabel), usedDP.value.toString(),
                            usedCombat.value.toString(), usedMagic.value.toString(), usedPsychic.value.toString())
                    }
                }
            ) {
                //set navigation host in scaffold
                NavHost(
                    navController = navController,
                    startDestination = ScreenPage.Primary.name,
                    modifier = Modifier.padding(it)
                ){
                    //route to primary characteristics page
                    composable(route = ScreenPage.Primary.name){
                        CharacterPageFragment(charInstance, maxDP, maxCombat, maxMagic, maxPsychic)
                        {updateBottomBar(usedDP, usedCombat, usedMagic, usedPsychic)}
                    }

                    //route to secondary characteristics page
                    composable(route = ScreenPage.Secondary.name){
                        SecondaryAbilityFragment(charInstance, usedDP)
                    }

                    //route to advantages page
                    composable(route = ScreenPage.Advantages.name){
                        Advantages(charInstance)
                    }

                    //route to combat page
                    composable(route = ScreenPage.Weapon_Modules.name){
                        CombatFragment(charInstance)
                        {updateBottomBar(usedDP, usedCombat, usedMagic, usedPsychic)}
                    }

                    //route to ki page
                    composable(route = ScreenPage.Ki.name){
                        KiFragment(charInstance)
                        {updateBottomBar(usedDP, usedCombat, usedMagic, usedPsychic)}
                    }

                    //route to magic page
                    composable(route = ScreenPage.Magic.name){
                        MagicFragment()
                    }

                    //route to psychic page
                    composable(route = ScreenPage.Psychic.name){
                        PsychicFragment()
                    }

                    //route to equipment page
                    composable(route = ScreenPage.Equipment.name){
                        EquipmentFragment()
                    }
                }
            }

            //show exit alert if user opens it
            if(exitOpen.value)
                ExitAlert(exitOpen)
            if(detailAlertOn.value)
                DetailAlert(contents.value!!)
        }
    }

    /**
     * Creates a drawer button with the given inputs
     *
     * display: name the button will have
     * action: function to run on user input
     */
    @Composable
    private fun DrawerButton(display:String, action: () -> Unit){
        TextButton(onClick = {action()}) {
            Text(text = display)
        }
    }

    /**
     * Creates a row for the bottom bar's display
     *
     * rowLabel: title for the given row
     * maxValue: display of the maximum point column
     * comValue: display of the combat column
     * magValue: display of the magic column
     * psyValue: display of the psychic column
     */
    @Composable
    private fun BottomBarRow(
        rowLabel: String,
        maxValue: String,
        comValue: String,
        magValue: String,
        psyValue: String
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //display title
            Text(
                text = rowLabel,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(0.2f)
            )

            //display maximum value
            Text(
                text = maxValue,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.2f))

            //display combat value
            Text(
                text = comValue,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.2f)
            )

            //display magic value
            Text(
                text = magValue,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.2f)
            )

            //display psychic value
            Text(
                text = psyValue,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.2f)
            )
        }
    }

    /**
     * Attempts to save the character's data to its designated file
     */
    private fun attemptSave(){
        try{
            //create file writer
            val saveStream = openFileOutput(filename, Context.MODE_PRIVATE)

            //get and write character's bytes
            val charData = charInstance.bytes
            saveStream.write(charData)
            saveStream.close()

            //notify of action completion
            Toast.makeText(
                this@HomeActivity,
                "Save successful!",
                Toast.LENGTH_SHORT
            ).show()
        }
        //notify of no file found
        catch(e: FileNotFoundException){
            Toast.makeText(
                this@HomeActivity,
                "Unable to find file!",
                Toast.LENGTH_SHORT
            ).show()
        }
        //notify of error in file writing
        catch(e: IOException) {
            Toast.makeText(
                this@HomeActivity,
                "Failed to write data!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @Composable
    private fun DetailAlert(contents: @Composable () -> Unit){
        AlertDialog(
            onDismissRequest = {detailAlertOn.value = false},
            title = {Text(text = "Description of " + detailItem.value)},
            text = {contents()},
            confirmButton = {TextButton(onClick = {detailAlertOn.value = false}){Text(text = "Close")} }
        )
    }

    /**
     * Alert for when user wishes to leave the current activity
     *
     * isOpen: open state of alert
     */
    @Composable
    private fun ExitAlert(
        isOpen: MutableState<Boolean>,
    ){
        AlertDialog(
            //only close alert on dismissal
            onDismissRequest = {isOpen.value = false},
            title = {Text(text = "Save before exiting?")},
            confirmButton = {
                //attempt to save then leave page
                TextButton(
                    onClick = {
                        attemptSave()
                        exitPage()}
                ){
                    Text(text = "Save")
                }},
            dismissButton = {
                //leave page without saving
                TextButton(
                    onClick = {
                        exitPage()
                    }){
                    Text(text = "Exit")
                }
            }
        )
    }

    //transfer back to MainActivity
    private fun exitPage(){
        startActivity(Intent(this@HomeActivity, MainActivity::class.java))
    }

    fun updateBottomBar(
        usedDP: MutableState<Int>,
        usedCombat: MutableState<Int>,
        usedMagic: MutableState<Int>,
        usedPsychic: MutableState<Int>
    ){
        usedDP.value = charInstance.spentTotal
        usedCombat.value = charInstance.ptInCombat
        usedMagic.value = charInstance.ptInMag
        usedPsychic.value = charInstance.ptInPsy
    }
}

@Composable
fun InfoRow(
    label: String,
    info: String
){
    Row(verticalAlignment = Alignment.CenterVertically){
        Text(text = label, modifier = Modifier.weight(0.5f))
        Text(text = info, modifier = Modifier.weight(0.5f))
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun numberCatcher(
    input: String,
    tryBlock: (String) -> Unit,
    blankBlock: () -> Unit,
){
    try{
        tryBlock(input)
    }
    catch(e: NumberFormatException){
        if(input == "")
            blankBlock()
        else if (input.contains('\n'))
            keyboardActive?.hide()
    }
}