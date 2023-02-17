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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animabuilder.view_models.BottomBarViewModel
import com.example.animabuilder.R
import com.example.animabuilder.activities.fragments.home_fragments.*
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.activities.fragments.dialogs.DetailAlert
import com.example.animabuilder.view_models.HomePageAlertViewModel
import com.example.animabuilder.view_models.NavigationViewModel
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.io.IOException

/**
 * Activity that runs all character creation fragments
 * Initially loads the CharacterPageFragment
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


    //character to work with and its associated file name
    private val filename: MutableState<String?> = mutableStateOf(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get values from intent
        val charInstance = intent.getSerializableExtra("Character", BaseCharacter::class.java)!!
        filename.value = intent.getStringExtra("filename")

        setContent{
            val navVM = viewModel<NavigationViewModel>()

            navVM.setScaffoldState(rememberScaffoldState())
            val scaffoldState = navVM.scaffoldState.collectAsState().value!!

            navVM.setCoroutineScope(rememberCoroutineScope())
            val scope = navVM.coroutineScope.collectAsState().value!!

            navVM.setNavHostController(rememberNavController())
            val navController = navVM.navHostController.collectAsState().value!!

            val currentFragment = navVM.currentFragment.collectAsState().value

            //displays for maximum development points
            val bottomBarVM = viewModel<BottomBarViewModel>()
            bottomBarVM.setMaxDP(charInstance.devPT)
            bottomBarVM.setMaxCombat(charInstance.maxCombatDP)
            bottomBarVM.setMaxMagic(charInstance.maxMagDP)
            bottomBarVM.setMaxPsychic(charInstance.maxPsyDP)
            bottomBarVM.updateSpentValues(charInstance)

            val homeAlertsVM = viewModel<HomePageAlertViewModel>()

            //scaffold for the home page
            Scaffold(
                scaffoldState = scaffoldState,

                //home page's top bar
                topBar = {TopAppBar (
                    //update title with any page change
                    title = {Text(text = currentFragment.name)},

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
                                navVM.setCurrentFragment(it)
                                scope.launch{scaffoldState.drawerState.close()}
                                navController.navigate(it.name)
                            }
                        }

                        //drawer button for saving the character
                        DrawerButton("Save"){
                            scope.launch{scaffoldState.drawerState.close()}
                            attemptSave(charInstance)
                        }

                        //drawer button for exiting the character creator
                        DrawerButton("Exit"){
                            scope.launch{scaffoldState.drawerState.close()}
                            homeAlertsVM.setExitAlert(true) }
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
                        BottomBarRow(
                            stringResource(R.string.maxRowLabel),
                            bottomBarVM.maxDP.collectAsState().value.toString(),
                            bottomBarVM.maxCombat.collectAsState().value.toString(),
                            bottomBarVM.maxMagic.collectAsState().value.toString(),
                            bottomBarVM.maxPsychic.collectAsState().value.toString()
                        )

                        //create row for spent values
                        BottomBarRow(
                            stringResource(R.string.usedRowLabel),
                            bottomBarVM.spentDP.collectAsState().value.toString(),
                            bottomBarVM.spentCombat.collectAsState().value.toString(),
                            bottomBarVM.spentMagic.collectAsState().value.toString(),
                            bottomBarVM.spentPsychic.collectAsState().value.toString())
                    }
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
                        CharacterPageFragment(charInstance, bottomBarVM)
                        {bottomBarVM.updateSpentValues(charInstance)}
                    }

                    //route to combat abilities page
                    composable(route = ScreenPage.Combat.name){
                        CombatFragment(charInstance)
                        {bottomBarVM.updateSpentValues(charInstance)}
                    }

                    //route to secondary characteristics page
                    composable(route = ScreenPage.Secondary_Characteristics.name){
                        SecondaryAbilityFragment(charInstance)
                        {bottomBarVM.updateSpentValues(charInstance)}
                    }

                    //route to advantages page
                    composable(route = ScreenPage.Advantages.name){
                        AdvantageFragment(
                            charInstance.advantageRecord,
                            homeAlertsVM.openDetailAlert
                        )
                        {bottomBarVM.updateSpentValues(charInstance)}
                    }

                    //route to combat page
                    composable(route = ScreenPage.Modules.name){
                        ModuleFragment(
                            charInstance.weaponProficiencies,
                            homeAlertsVM.openDetailAlert
                        )
                        {bottomBarVM.updateSpentValues(charInstance)}
                    }

                    //route to ki page
                    composable(route = ScreenPage.Ki.name){
                        KiFragment(
                            charInstance.ki,
                            charInstance.primaryList,
                            homeAlertsVM.openDetailAlert
                        )
                        {bottomBarVM.updateSpentValues(charInstance)}
                    }

                    //route to magic page
                    composable(route = ScreenPage.Magic.name){
                        MagicFragment(charInstance, homeAlertsVM.openDetailAlert)
                        {bottomBarVM.updateSpentValues(charInstance)}
                    }

                    //route to summoning page
                    composable(route = ScreenPage.Summoning.name){
                        SummoningFragment(charInstance)
                        {bottomBarVM.updateSpentValues(charInstance)}
                    }

                    //route to psychic page
                    composable(route = ScreenPage.Psychic.name){
                        PsychicFragment(charInstance, homeAlertsVM.openDetailAlert)
                        {bottomBarVM.updateSpentValues(charInstance)}
                    }

                    //route to equipment page
                    composable(route = ScreenPage.Equipment.name){
                        EquipmentFragment()
                    }
                }
            }


            //show exit alert if user opens it
            if(homeAlertsVM.exitOpen.collectAsState().value)
                ExitAlert(charInstance) {homeAlertsVM.setExitAlert(false)}
            if(homeAlertsVM.detailAlertOn.collectAsState().value)
                DetailAlert(homeAlertsVM)
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
    private fun attemptSave(charInstance: BaseCharacter) {
        try{
            //create file writer
            val saveStream = openFileOutput(filename.value, Context.MODE_PRIVATE)

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
     * Alert for when user wishes to leave the current activity
     *
     * isOpen: open state of alert
     */
    @Composable
    private fun ExitAlert(
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
                        attemptSave(charInstance)
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
}