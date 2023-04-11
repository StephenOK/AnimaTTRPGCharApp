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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animabuilder.R
import com.example.animabuilder.activities.fragments.home_fragments.*
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.activities.fragments.dialogs.DetailAlert
import com.example.animabuilder.view_models.*
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
            val homePageVM = HomePageViewModel(charInstance)
            val homeAlertsVM = viewModel<HomePageAlertViewModel>()

            //create viewModels for each individual fragment
            val charFragVM = CharacterFragmentViewModel(charInstance, homePageVM, context)
            val combatFragVM = CombatFragViewModel(charInstance.combat, charInstance.primaryList)
            val secondaryFragVM = SecondaryFragmentViewModel(charInstance.secondaryList)
            val advantageFragVM = AdvantageFragmentViewModel(
                charInstance,
                charInstance.advantageRecord
            )
            val modFragVM = ModuleFragmentViewModel(charInstance.weaponProficiencies, context)
            val kiFragVM = KiFragmentViewModel(charInstance.ki, context)
            val magFragVM = MagicFragmentViewModel(charInstance.magic, charInstance.primaryList.dex)
            val summonFragVM = SummoningFragmentViewModel(charInstance.summoning)
            val psyFragVM = PsychicFragmentViewModel(
                charInstance.psychic,
                charInstance.primaryList.dex.outputMod
            )
            val equipFragVM = EquipmentFragmentViewModel(charInstance.inventory)

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
                        CharacterPageFragment(charFragVM)
                        {homePageVM.expenditures.updateItems(charInstance.spentTotal, charInstance.ptInCombat, charInstance.ptInMag, charInstance.ptInPsy) }
                    }

                    //route to combat abilities page
                    composable(route = ScreenPage.Combat.name){
                        CombatFragment(combatFragVM)
                        {homePageVM.expenditures.updateItems(charInstance.spentTotal, charInstance.ptInCombat, charInstance.ptInMag, charInstance.ptInPsy) }
                    }

                    //route to secondary characteristics page
                    composable(route = ScreenPage.Secondary_Characteristics.name){
                        SecondaryAbilityFragment(secondaryFragVM)
                        {homePageVM.expenditures.updateItems(charInstance.spentTotal, charInstance.ptInCombat, charInstance.ptInMag, charInstance.ptInPsy) }
                    }

                    //route to advantages page
                    composable(route = ScreenPage.Advantages.name){
                        AdvantageFragment(
                            advantageFragVM,
                            homeAlertsVM.openDetailAlert
                        )
                        {homePageVM.expenditures.updateItems(charInstance.spentTotal, charInstance.ptInCombat, charInstance.ptInMag, charInstance.ptInPsy) }
                    }

                    //route to combat page
                    composable(route = ScreenPage.Modules.name){
                        ModuleFragment(
                            modFragVM,
                            homeAlertsVM.openDetailAlert
                        )
                        {homePageVM.expenditures.updateItems(charInstance.spentTotal, charInstance.ptInCombat, charInstance.ptInMag, charInstance.ptInPsy) }
                    }

                    //route to ki page
                    composable(route = ScreenPage.Ki.name){
                        KiFragment(
                            kiFragVM,
                            homeAlertsVM.openDetailAlert
                        )
                        {homePageVM.expenditures.updateItems(charInstance.spentTotal, charInstance.ptInCombat, charInstance.ptInMag, charInstance.ptInPsy) }
                    }

                    //route to magic page
                    composable(route = ScreenPage.Magic.name){
                        MagicFragment(
                            magFragVM,
                            homeAlertsVM.openDetailAlert
                        )
                        {homePageVM.expenditures.updateItems(charInstance.spentTotal, charInstance.ptInCombat, charInstance.ptInMag, charInstance.ptInPsy) }
                    }

                    //route to summoning page
                    composable(route = ScreenPage.Summoning.name){
                        SummoningFragment(
                            summonFragVM
                        )
                        {homePageVM.expenditures.updateItems(charInstance.spentTotal, charInstance.ptInCombat, charInstance.ptInMag, charInstance.ptInPsy) }
                    }

                    //route to psychic page
                    composable(route = ScreenPage.Psychic.name){
                        PsychicFragment(
                            psyFragVM,
                            homeAlertsVM.openDetailAlert
                        )
                        {homePageVM.expenditures.updateItems(charInstance.spentTotal, charInstance.ptInCombat, charInstance.ptInMag, charInstance.ptInPsy) }
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