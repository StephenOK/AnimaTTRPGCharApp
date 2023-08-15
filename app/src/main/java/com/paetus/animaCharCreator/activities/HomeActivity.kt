package com.paetus.animaCharCreator.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paetus.animaCharCreator.theme.AppTheme
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.ScreenPage
import com.paetus.animaCharCreator.activities.fragments.home_fragments.*
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.numberScroll
import com.paetus.animaCharCreator.view_models.CustomFactory
import com.paetus.animaCharCreator.view_models.models.AdvantageFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.CharacterFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.CombatFragViewModel
import com.paetus.animaCharCreator.view_models.models.EquipmentFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.HomePageViewModel
import com.paetus.animaCharCreator.view_models.models.KiFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.MagicFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.ModuleFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.PsychicFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.SecondaryFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.SummoningFragmentViewModel
import kotlinx.coroutines.CoroutineScope
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get new character flag and filename from intent
        val isNew = intent.getBooleanExtra("isNew", false)
        val filename = intent.getStringExtra("filename")!!

        //create character object
        val charInstance = if(isNew)
            BaseCharacter()
        else
            BaseCharacter(File(this.filesDir, filename))

        //create file on new character creation
        if(isNew)
            attemptSave(filename, charInstance)

        //create viewModels for the home page and home alert items
        val homePageVM: HomePageViewModel by viewModels{
            CustomFactory(HomePageViewModel::class.java, charInstance, baseContext)
        }

        setContent{
            //AppTheme{
                HomeContents(homePageVM, charInstance, filename)
            //}
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    /**
     * Display the general contents for the home page activity.
     *
     * @param charInstance character the user is editing
     * @param filename name of the character's associated file
     */
    @Composable
    private fun HomeContents(
        homePageVM: HomePageViewModel,
        charInstance: BaseCharacter,
        filename: String
    ) {
        //prevent user from flipping app
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //initialize current context
        val context = LocalContext.current

        //get scaffold state, coroutine scope, and navigation controller
        val scope = rememberCoroutineScope()
        val navController = rememberNavController()

        val drawerState = rememberDrawerState(DrawerValue.Closed)

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

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                AppDrawer(
                    homePageVM,
                    filename,
                    charInstance,
                    drawerState,
                    scope,
                    navController
                )
            }
        ){
            //scaffold for the home page
            Scaffold(
                topBar = {AppHeader(homePageVM, drawerState, scope)},
                bottomBar = {AppFooter(homePageVM) }
            ) {

                //set navigation host in scaffold
                NavHost(
                    navController = navController,
                    startDestination = ScreenPage.Character.name,
                    modifier = Modifier
                        .padding(it)
                ) {
                    //route to primary characteristics page
                    composable(route = ScreenPage.Character.name) {
                        charFragVM.refreshPage()
                        CharacterPageFragment(
                            charFragVM,
                            homePageVM
                        )
                    }

                    //route to combat abilities page
                    composable(route = ScreenPage.Combat.name) {
                        combatFragVM.refreshPage()
                        CombatFragment(
                            combatFragVM,
                            homePageVM
                        )
                    }

                    //route to secondary characteristics page
                    composable(route = ScreenPage.SecondaryCharacteristics.name) {
                        secondaryFragVM.refreshPage()
                        SecondaryAbilityFragment(
                            secondaryFragVM,
                            homePageVM
                        )
                    }

                    //route to advantages page
                    composable(route = ScreenPage.Advantages.name) {
                        advantageFragVM.refreshPage()
                        AdvantageFragment(
                            advantageFragVM,
                            homePageVM
                        )
                    }

                    //route to combat page
                    composable(route = ScreenPage.Modules.name) {
                        modFragVM.refreshPage()
                        ModuleFragment(
                            modFragVM,
                            homePageVM
                        )
                    }

                    //route to ki page
                    composable(route = ScreenPage.Ki.name) {
                        kiFragVM.refreshPage()
                        KiFragment(
                            kiFragVM,
                            homePageVM
                        )
                    }

                    //route to magic page
                    composable(route = ScreenPage.Magic.name) {
                        magFragVM.refreshPage()
                        MagicFragment(
                            magFragVM,
                            homePageVM
                        )
                    }

                    //route to summoning page
                    composable(route = ScreenPage.Summoning.name) {
                        summonFragVM.refreshPage()
                        SummoningFragment(
                            summonFragVM,
                            homePageVM
                        )
                    }

                    //route to psychic page
                    composable(route = ScreenPage.Psychic.name) {
                        psyFragVM.refreshPage()
                        PsychicFragment(
                            psyFragVM,
                            homePageVM
                        )
                    }

                    //route to equipment page
                    composable(route = ScreenPage.Equipment.name) {
                        equipFragVM.refreshPage()
                        EquipmentFragment(
                            equipFragVM
                        )
                    }
                }

                //show exit alert if user opens it
                if (homePageVM.exitOpen.collectAsState().value)
                    ExitAlert(filename, charInstance) { homePageVM.toggleExitAlert() }
            }
        }

        BackHandler{homePageVM.toggleExitAlert()}
    }

    /**
     * Composes the top bar for the app in this activity.
     *
     * @param homePageVM viewModel that manages this activity
     * @param drawerState state manager of the drawer state
     * @param scope coroutine for this item
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun AppHeader(
        homePageVM: HomePageViewModel,
        drawerState: DrawerState,
        scope: CoroutineScope
    ) {
        TopAppBar(
            //update title with any page change
            title = {
                Text(
                    text = stringResource(ScreenPage.toAddress(homePageVM.currentFragment.collectAsState().value))
                )
            },

            //icon to open the navigation drawer
            navigationIcon = {
                IconButton(
                    onClick = {
                        if (drawerState.isClosed)
                            scope.launch{drawerState.open()}
                        else
                            scope.launch{drawerState.close()}
                    })
                {
                    Icon(
                        painter = painterResource(R.drawable.baseline_menu_24),
                        contentDescription = stringResource(R.string.openLabel)
                    )
                }
            },

            //actions = {
            //    IconButton(
            //        onClick = {
            //            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            //                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            //            }
            //            else{
            //                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            //            }
//
            //            writeSettings(this@HomeActivity)
            //        }
            //    ){
            //        Icon(
            //            painter =
            //                if(!isSystemInDarkTheme())
            //                    painterResource(R.drawable.baseline_dark_mode_24)
            //                else
            //                    painterResource(R.drawable.baseline_light_mode_24),
            //            contentDescription = null
            //        )
            //    }
            //}
        )
    }

    /**
     * Composes the app sidebar for this activity.
     *
     * @param homePageVM viewModel that manages this section
     * @param filename name of the file being worked on
     * @param charInstance character object being worked on
     * @param drawerState state manager of the activity's scaffold
     * @param scope coroutine for this item
     * @param navController navigation host this drawer affects
     */
    @Composable
    private fun AppDrawer(
        homePageVM: HomePageViewModel,
        filename: String,
        charInstance: BaseCharacter,
        drawerState: DrawerState,
        scope: CoroutineScope,
        navController: NavHostController
    ){
        ModalDrawerSheet {
            LazyColumn {
                //for each page enumeration present
                items(enumValues<ScreenPage>()){
                    NavigationDrawerItem(
                        label = { Text(stringResource(ScreenPage.toAddress(it))) },
                        selected = homePageVM.currentFragment.collectAsState().value == it,
                        onClick = {
                            homePageVM.setCurrentFragment(it)
                            scope.launch { drawerState.close() }

                            //remove backstack
                            navController.navigate(it.name) {
                                popUpTo(0)
                            }
                        }
                    )
                }

                item {
                    NavigationDrawerItem(
                        label = { Text(stringResource(R.string.saveLabel)) },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            attemptSave(filename, charInstance)
                        }
                    )
                }

                item {
                    NavigationDrawerItem(
                        label = { Text(stringResource(R.string.exitLabel)) },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            homePageVM.toggleExitAlert()
                        }
                    )
                }
            }
        }
    }

    /**
     * Composes bottom bar object for the app.
     *
     * @param homePageVM viewModel that manages this section
     */
    @Composable
    private fun AppFooter(homePageVM: HomePageViewModel) {
        Column{
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
                    text = stringResource(R.string.combatLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )
                //magic column
                Text(
                    text = stringResource(R.string.magicLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )
                //psychic column
                Text(
                    text = stringResource(R.string.psychicLabel),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f)
                )
            }

            //create row for maximum values
            BottomBarRow(homePageVM.maximums)

            //create row for spent values
            BottomBarRow(homePageVM.expenditures)
        }
    }

    /**
     * Creates a row for the bottom bar's display.
     *
     * @param item bottom bar row data that is to be displayed in this row
     */
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun BottomBarRow(
        item: HomePageViewModel.BottomBarRowData
    ){
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
            AnimatedContent(
                targetState = item.maxVal.collectAsState().value,
                modifier = Modifier
                    .weight(0.2f),
                transitionSpec = numberScroll,
                label = "${stringResource(item.nameRef)}Max"
            ) {
                Text(
                    text = "$it",
                    textAlign = TextAlign.Center
                )
            }

            //display combat value
            AnimatedContent(
                targetState = item.combatVal.collectAsState().value,
                modifier = Modifier
                    .weight(0.2f),
                transitionSpec = numberScroll,
                label = "${stringResource(item.nameRef)}Combat"
            ){
                Text(
                    text = "$it",
                    textAlign = TextAlign.Center
                )
            }

            //display magic value
            AnimatedContent(
                targetState = item.magVal.collectAsState().value,
                modifier = Modifier
                    .weight(0.2f),
                transitionSpec = numberScroll,
                label = "${stringResource(item.nameRef)}Magic"
            ) {
                Text(
                    text = "$it",
                    textAlign = TextAlign.Center
                )
            }

            //display psychic value
            AnimatedContent(
                targetState = item.psyVal.collectAsState().value,
                modifier = Modifier
                    .weight(0.2f),
                transitionSpec = numberScroll,
                label = "${stringResource(item.nameRef)}Psychic"
            ) {
                Text(
                    text = "$it",
                    textAlign = TextAlign.Center
                )
            }
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
            title = {Text(text = stringResource(R.string.exitTitle))},
            confirmButton = {
                //attempt to save then leave page
                TextButton(
                    onClick = {
                        attemptSave(filename, charInstance)
                        finish()
                    }
                ){
                    Text(text = stringResource(R.string.saveLabel))
                }},
            dismissButton = {
                //leave page without saving
                TextButton(
                    onClick = {
                        finish()
                    }){
                    Text(text = stringResource(R.string.exitLabel))
                }
            }
        )
    }

    /**
     * Attempts to save the character's data to its designated file.
     *
     * @param filename name of the file to save to
     * @param charInstance character object to be saved
     */
    private fun attemptSave(
        filename: String,
        charInstance: BaseCharacter
    ){
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
                baseContext.resources.getString(R.string.saveMessage),
                Toast.LENGTH_SHORT
            ).show()
        }
        //notify of no file found
        catch(e: FileNotFoundException){
            Toast.makeText(
                this@HomeActivity,
                baseContext.resources.getString(R.string.fileNotFound),
                Toast.LENGTH_SHORT
            ).show()
        }
        //notify of error in file writing
        catch(e: IOException) {
            Toast.makeText(
                this@HomeActivity,
                baseContext.resources.getString(R.string.fileError),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}