package com.example.animabuilder.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animabuilder.R
import com.example.animabuilder.activities.fragments.home_fragments.*
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.activities.fragments.dialogs.DetailAlert
import com.example.animabuilder.view_models.CustomFactory
import com.example.animabuilder.view_models.models.AdvantageFragmentViewModel
import com.example.animabuilder.view_models.models.CharacterFragmentViewModel
import com.example.animabuilder.view_models.models.CombatFragViewModel
import com.example.animabuilder.view_models.models.EquipmentFragmentViewModel
import com.example.animabuilder.view_models.models.HomePageAlertViewModel
import com.example.animabuilder.view_models.models.HomePageViewModel
import com.example.animabuilder.view_models.models.KiFragmentViewModel
import com.example.animabuilder.view_models.models.MagicFragmentViewModel
import com.example.animabuilder.view_models.models.ModuleFragmentViewModel
import com.example.animabuilder.view_models.models.PsychicFragmentViewModel
import com.example.animabuilder.view_models.models.SecondaryFragmentViewModel
import com.example.animabuilder.view_models.models.SummoningFragmentViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

/**
 * Activity that runs all character creation fragments.
 * Instantiates all viewModels used in the character pages.
 * Initially loads the CharacterPageFragment.
 */

class HomeActivity : AppCompatActivity() {

    //enumeration for current displayed page
    enum class ScreenPage{
        Character,
        Combat,
        Secondary_Characteristics,
        Advantages,
        Modules,
        Ki,
        Magic,
        Summoning,
        Psychic,
        Equipment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get new character flag and filename from intent
        val isNew = intent.getBooleanExtra("isNew", false)
        val filename = intent.getStringExtra("filename")!!

        val charInstance = if(isNew)
            BaseCharacter()
        else
            BaseCharacter(File(this.filesDir, filename))

        setContent{
            //get local context
            val context = LocalContext.current

            //get scaffold state, coroutine scope, and navigation controller
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val navController = rememberNavController()

            //create viewModels for the home page and home alert items
            val homePageVM: HomePageViewModel by viewModels{
                CustomFactory(HomePageViewModel::class.java, charInstance, context)
            }

            val homeAlertsVM: HomePageAlertViewModel by viewModels{
                CustomFactory(HomePageAlertViewModel::class.java, charInstance, context)
            }

            //create viewModels for each individual fragment
            val charFragVM: CharacterFragmentViewModel by viewModels{
                CustomFactory(CharacterFragmentViewModel::class.java, charInstance, context)
            }

            val combatFragVM: CombatFragViewModel by viewModels{
                CustomFactory(CombatFragViewModel::class.java, charInstance, context)
            }

            val secondaryFragVM: SecondaryFragmentViewModel by viewModels{
                CustomFactory(SecondaryFragmentViewModel::class.java, charInstance, context)
            }

            val advantageFragVM: AdvantageFragmentViewModel by viewModels{
                CustomFactory(AdvantageFragmentViewModel::class.java, charInstance, context)
            }

            val modFragVM: ModuleFragmentViewModel by viewModels{
                CustomFactory(ModuleFragmentViewModel::class.java, charInstance, context)
            }

            val kiFragVM: KiFragmentViewModel by viewModels{
                CustomFactory(KiFragmentViewModel::class.java, charInstance, context)
            }

            val magFragVM: MagicFragmentViewModel by viewModels{
                CustomFactory(MagicFragmentViewModel::class.java, charInstance, context)
            }

            val summonFragVM: SummoningFragmentViewModel by viewModels{
                CustomFactory(SummoningFragmentViewModel::class.java, charInstance, context)
            }

            val psyFragVM: PsychicFragmentViewModel by viewModels{
                CustomFactory(PsychicFragmentViewModel::class.java, charInstance, context)
            }

            val equipFragVM: EquipmentFragmentViewModel by viewModels{
                CustomFactory(EquipmentFragmentViewModel::class.java, charInstance, context)
            }

            //scaffold for the home page
            Scaffold(
                scaffoldState = scaffoldState,

                //home page's top bar
                topBar = {TopAppBar (
                    //update title with any page change
                    title = {Text(text = homePageVM.currentFragment.collectAsState().value.name)},

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
                                //change page if not on own page
                                if(homePageVM.currentFragment.value != it) {
                                    homePageVM.setCurrentFragment(it)
                                    scope.launch { scaffoldState.drawerState.close() }
                                    navController.navigate(it.name)
                                }
                            }
                        }

                        //drawer button for saving the character
                        DrawerButton("Save"){
                            scope.launch{scaffoldState.drawerState.close()}
                            attemptSave(filename, charInstance)
                        }

                        //drawer button for exiting the character creator
                        DrawerButton("Exit"){
                            scope.launch{scaffoldState.drawerState.close()}
                            homeAlertsVM.toggleExitAlert()
                        }
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
                        BottomBarRow(homePageVM.maximums)

                        //create row for spent values
                        BottomBarRow(homePageVM.expenditures)}
                }
            ) {
                //set navigation host in scaffold
                NavHost(
                    navController = navController,
                    startDestination = ScreenPage.Character.name,
                    modifier = Modifier.padding(it)
                ){
                    //route to primary characteristics page
                    composable(route = ScreenPage.Character.name){
                        charFragVM.refreshPage()
                        CharacterPageFragment(charFragVM, homePageVM)
                    }

                    //route to combat abilities page
                    composable(route = ScreenPage.Combat.name){
                        combatFragVM.refreshPage()
                        CombatFragment(combatFragVM, homePageVM)
                    }

                    //route to secondary characteristics page
                    composable(route = ScreenPage.Secondary_Characteristics.name){
                        secondaryFragVM.refreshPage()
                        SecondaryAbilityFragment(secondaryFragVM, homePageVM)
                    }

                    //route to advantages page
                    composable(route = ScreenPage.Advantages.name){
                        AdvantageFragment(
                            advantageFragVM,
                            homeAlertsVM.openDetailAlert,
                            homePageVM
                        )
                    }

                    //route to combat page
                    composable(route = ScreenPage.Modules.name){
                        modFragVM.refreshPage()
                        ModuleFragment(
                            modFragVM,
                            homeAlertsVM.openDetailAlert,
                            homePageVM
                        )
                    }

                    //route to ki page
                    composable(route = ScreenPage.Ki.name){
                        kiFragVM.refreshPage()
                        KiFragment(
                            kiFragVM,
                            homeAlertsVM.openDetailAlert,
                            homePageVM
                        )
                    }

                    //route to magic page
                    composable(route = ScreenPage.Magic.name){
                        magFragVM.refreshPage()
                        MagicFragment(
                            magFragVM,
                            homeAlertsVM.openDetailAlert,
                            homePageVM
                        )
                    }

                    //route to summoning page
                    composable(route = ScreenPage.Summoning.name){
                        summonFragVM.refreshPage()
                        SummoningFragment(
                            summonFragVM,
                            homePageVM
                        )
                    }

                    //route to psychic page
                    composable(route = ScreenPage.Psychic.name){
                        psyFragVM.refreshPage()
                        PsychicFragment(
                            psyFragVM,
                            homeAlertsVM.openDetailAlert,
                            homePageVM
                        )
                    }

                    //route to equipment page
                    composable(route = ScreenPage.Equipment.name){
                        EquipmentFragment(
                            equipFragVM,
                            homeAlertsVM.openDetailAlert
                        )
                    }
                }

                //show exit alert if user opens it
                if(homeAlertsVM.exitOpen.collectAsState().value)
                    ExitAlert(filename, charInstance) {homeAlertsVM.toggleExitAlert() }
                //show detail alert if user opens one
                if(homeAlertsVM.detailAlertOn.collectAsState().value)
                    DetailAlert(homeAlertsVM)
            }
        }
    }

    /**
     * Creates a navigation button with the given inputs.
     *
     * @param display name the button will have
     * @param action function to run on user input
     */
    @Composable
    private fun DrawerButton(display:String, action: () -> Unit){
        TextButton(onClick = {action()}) {
            Text(text = display)
        }
    }

    /**
     * Creates a row for the bottom bar's display.
     *
     * @param item bottom bar row data that is to be displayed in this row
     */
    @Composable
    private fun BottomBarRow(item: HomePageViewModel.BottomBarRowData){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //display title
            Text(
                text = stringResource(item.nameRef),
                textAlign = TextAlign.End,
                modifier = Modifier.weight(0.2f)
            )

            //display maximum value
            Text(
                text = item.maxVal.collectAsState().value,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.2f))

            //display combat value
            Text(
                text = item.combatVal.collectAsState().value,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.2f)
            )

            //display magic value
            Text(
                text = item.magVal.collectAsState().value,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.2f)
            )

            //display psychic value
            Text(
                text = item.psyVal.collectAsState().value,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.2f)
            )
        }
    }

    /**
     * Attempts to save the character's data to its designated file.
     *
     * @param filename name of the file to save to
     * @param charInstance character object to be saved
     */
    private fun attemptSave(filename: String, charInstance: BaseCharacter) {
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


    /**
     * Alert for when user wishes to leave the current activity.
     *
     * @param filename name of the character file for save option
     * @param charInstance character object for save option
     * @param closeDialog function to run on close option
     */
    @Composable
    private fun ExitAlert(
        filename: String,
        charInstance: BaseCharacter,
        closeDialog: () -> Unit
    ){
        AlertDialog(
            //only close alert on dismissal
            onDismissRequest = {closeDialog()},
            title = {Text(text = "Save before exiting?")},
            confirmButton = {
                //attempt to save then leave page
                TextButton(
                    onClick = {
                        attemptSave(filename, charInstance)
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

    /**
     * Function that transfers the user back to the main page.
     */
    private fun exitPage(){
        startActivity(Intent(this@HomeActivity, MainActivity::class.java))
    }
}