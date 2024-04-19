package com.paetus.animaCharCreator.activities

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.ScreenPage
import com.paetus.animaCharCreator.activities.fragments.home_fragments.*
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.numberScroll
import com.paetus.animaCharCreator.theme.detailLightColors
import com.paetus.animaCharCreator.theme.drawerLightColors
import com.paetus.animaCharCreator.theme.headerLightColors
import com.paetus.animaCharCreator.theme.homeLightColors
import com.paetus.animaCharCreator.view_models.CustomFactory
import com.paetus.animaCharCreator.view_models.models.AdvantageFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.CharacterFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.CombatFragViewModel
import com.paetus.animaCharCreator.view_models.models.CustomTechniqueViewModel
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
import java.io.FileOutputStream
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
        val isByLevel = intent.getBooleanExtra("isByLevel", false)

        //get custom item directories
        val customSecondaryFile = File(this.filesDir, "CustomSecondaryDIR")
        val customTechFile = File(this.filesDir, "CustomTechDIR")

        //create character object
        val charInstance =
            //character is not by level and is new
            if(!isByLevel && isNew)
                BaseCharacter(
                    filename = filename,
                    secondaryFile = customSecondaryFile,
                    techFile = customTechFile
                )
            //character is not by level and is loaded
            else if(!isByLevel)
                BaseCharacter(
                    charFile = File("${this.filesDir}/AnimaChars", filename),
                    secondaryFile = customSecondaryFile,
                    techFile = customTechFile
                )
            //character is save by level
            else {
                SblChar(
                    sourceDIR = File("${this.filesDir}/AnimaChars", filename),
                    secondaryFile = customSecondaryFile,
                    techFile = customTechFile
                )
            }

        //save file on new character creation
        if(isNew)
            attemptSave(
                charInstance = charInstance,
                filename = filename,
                showToast = false
            )

        //create viewModels for this activity
        val homePageVM: HomePageViewModel by viewModels{
            CustomFactory(
                viewModel = HomePageViewModel::class.java,
                charInstance = charInstance,
                context = baseContext
            )
        }

        //draw home page
        setContent{
            MaterialTheme(colorScheme = homeLightColors){
                HomeContents(
                    charInstance = charInstance,
                    filename = filename,
                    homePageVM = homePageVM
                )
            }
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    /**
     * Create the framework for the Home Activity.
     *
     * @param charInstance character the user is editing
     * @param filename name of the character's associated file
     * @param homePageVM viewModel for the home page activity
     */
    @Composable
    private fun HomeContents(
        charInstance: BaseCharacter,
        filename: String,
        homePageVM: HomePageViewModel
    ) {
        //prevent user from flipping app
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //initialize current context
        val context = LocalContext.current

        //initialize coroutine scope and navigation controller
        val scope = rememberCoroutineScope()
        val navController = rememberNavController()

        //initialize drawer state
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        //create viewModels for each individual fragment
        val charFragVM: CharacterFragmentViewModel by viewModels{
            CustomFactory(
                viewModel = CharacterFragmentViewModel::class.java,
                charInstance = charInstance,
                context = context
            )
        }

        val combatFragVM: CombatFragViewModel by viewModels{
            CustomFactory(
                viewModel = CombatFragViewModel::class.java,
                charInstance = charInstance,
                context = context
            )
        }

        val secondaryFragVM: SecondaryFragmentViewModel by viewModels{
            CustomFactory(
                viewModel = SecondaryFragmentViewModel::class.java,
                charInstance = charInstance,
                context = context
            )
        }

        val advantageFragVM: AdvantageFragmentViewModel by viewModels{
            CustomFactory(
                viewModel = AdvantageFragmentViewModel::class.java,
                charInstance = charInstance,
                context = context
            )
        }

        val modFragVM: ModuleFragmentViewModel by viewModels{
            CustomFactory(
                viewModel = ModuleFragmentViewModel::class.java,
                charInstance = charInstance,
                context = context
            )
        }

        val kiFragVM: KiFragmentViewModel by viewModels{
            CustomFactory(
                viewModel = KiFragmentViewModel::class.java,
                charInstance = charInstance,
                context = context
            )
        }

        val customTechVM: CustomTechniqueViewModel by viewModels{
            CustomFactory(
                viewModel = CustomTechniqueViewModel::class.java,
                charInstance = charInstance,
                context = context
            )
        }

        val magFragVM: MagicFragmentViewModel by viewModels{
            CustomFactory(
                viewModel = MagicFragmentViewModel::class.java,
                charInstance = charInstance,
                context = context
            )
        }

        val summonFragVM: SummoningFragmentViewModel by viewModels{
            CustomFactory(
                viewModel = SummoningFragmentViewModel::class.java,
                charInstance = charInstance,
                context = context
            )
        }

        val psyFragVM: PsychicFragmentViewModel by viewModels{
            CustomFactory(
                viewModel = PsychicFragmentViewModel::class.java,
                charInstance = charInstance,
                context = context
            )
        }

        val equipFragVM: EquipmentFragmentViewModel by viewModels{
            CustomFactory(
                viewModel = EquipmentFragmentViewModel::class.java,
                charInstance = charInstance,
                context = context
            )
        }

        //create activity drawer
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                MaterialTheme(colorScheme = drawerLightColors) {
                    AppDrawer(
                        charInstance = charInstance,
                        filename = filename,
                        drawerState = drawerState,
                        scope = scope,
                        navController = navController,
                        homePageVM = homePageVM
                    )
                }
            }
        ){
            //scaffold for the home page
            Scaffold(
                topBar = {
                    MaterialTheme(colorScheme = headerLightColors) {
                        AppHeader(
                            drawerState = drawerState,
                            scope = scope,
                            homePageVM = homePageVM
                        )
                    }
                },
                bottomBar = {
                    MaterialTheme(colorScheme = headerLightColors){
                        AppFooter(homePageVM = homePageVM)
                    }
                }
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
                            charFragVM = charFragVM,
                            maxNumVM = homePageVM
                        )
                    }

                    //route to combat abilities page
                    composable(route = ScreenPage.Combat.name) {
                        combatFragVM.refreshPage()
                        CombatFragment(
                            combatFragVM = combatFragVM,
                            homePageVM = homePageVM
                        )
                    }

                    //route to secondary characteristics page
                    composable(route = ScreenPage.SecondaryCharacteristics.name) {
                        secondaryFragVM.refreshPage()
                        SecondaryAbilityFragment(
                            filename = filename,
                            secondaryFragVM = secondaryFragVM,
                            homePageVM = homePageVM
                        )
                    }

                    //route to advantages page
                    composable(route = ScreenPage.Advantages.name) {
                        advantageFragVM.refreshPage()
                        AdvantageFragment(
                            secondaryList = charInstance.secondaryList,
                            advantageFragVM = advantageFragVM,
                            homePageVM = homePageVM
                        )
                    }

                    //route to combat page
                    composable(route = ScreenPage.Modules.name) {
                        modFragVM.refreshPage()
                        ModuleFragment(
                            modFragVM = modFragVM,
                            homePageVM = homePageVM
                        )
                    }

                    //route to ki page
                    composable(route = ScreenPage.Ki.name) {
                        kiFragVM.refreshPage()
                        KiFragment(
                            filename = filename,
                            kiFragVM = kiFragVM,
                            customTechVM = customTechVM,
                            homePageVM = homePageVM
                        )
                    }

                    //route to magic page
                    composable(route = ScreenPage.Magic.name) {
                        magFragVM.refreshPage()
                        MagicFragment(
                            magFragVM = magFragVM,
                            homePageVM = homePageVM
                        )
                    }

                    //route to summoning page
                    composable(route = ScreenPage.Summoning.name) {
                        summonFragVM.refreshPage()
                        SummoningFragment(
                            summoningVM = summonFragVM,
                            homePageVM = homePageVM
                        )
                    }

                    //route to psychic page
                    composable(route = ScreenPage.Psychic.name) {
                        psyFragVM.refreshPage()
                        PsychicFragment(
                            psyFragVM = psyFragVM,
                            homePageVM = homePageVM
                        )
                    }

                    //route to equipment page
                    composable(route = ScreenPage.Equipment.name) {
                        equipFragVM.refreshPage()
                        EquipmentFragment(
                            equipFragVM = equipFragVM
                        )
                    }
                }

                //show exit alert if user opens it
                if (homePageVM.exitOpen.collectAsState().value)
                    MaterialTheme(colorScheme = detailLightColors) {
                        ExitAlert(
                            charInstance = charInstance,
                            filename = filename
                        ) {homePageVM.toggleExitAlert()}
                    }
            }
        }

        //open or close exit alert on back button push
        BackHandler{homePageVM.toggleExitAlert()}
    }

    /**
     * Composes the top bar for this activity.
     *
     * @param drawerState state manager of the drawer state
     * @param scope coroutine for this item
     * @param homePageVM viewModel that manages this activity
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun AppHeader(
        drawerState: DrawerState,
        scope: CoroutineScope,
        homePageVM: HomePageViewModel
    ) {
        TopAppBar(
            //draw border for the page title
            title = {
                Text(
                    text = stringResource(id = ScreenPage.toAddress(screenPage = homePageVM.currentFragment.collectAsState().value)),
                    style = LocalTextStyle.current.copy(
                        drawStyle = Stroke(
                            miter = 10f,
                            width = 5f,
                            join = StrokeJoin.Round
                        ),
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                //create the page title
                Text(
                    text = stringResource(id = ScreenPage.toAddress(screenPage = homePageVM.currentFragment.collectAsState().value)),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
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
                    }
                ){
                    Icon(
                        painter = painterResource(R.drawable.baseline_menu_24),
                        contentDescription = stringResource(id = R.string.openLabel)
                    )
                }
            }

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
     * Composes the sidebar for this activity.
     *
     * @param charInstance character object being worked on
     * @param filename name of the file being worked on
     * @param drawerState state manager of the activity's scaffold
     * @param scope coroutine for this item
     * @param navController navigation host this drawer affects
     * @param homePageVM viewModel that manages this section
     */
    @Composable
    private fun AppDrawer(
        charInstance: BaseCharacter,
        filename: String,
        drawerState: DrawerState,
        scope: CoroutineScope,
        navController: NavHostController,
        homePageVM: HomePageViewModel
    ){
        ModalDrawerSheet{
            LazyColumn {
                //for each page enumeration present
                items(enumValues<ScreenPage>()){page ->
                    //create a drawer item
                    NavigationDrawerItem(
                        label = {Text(text = stringResource(id = ScreenPage.toAddress(page)))},
                        selected = homePageVM.currentFragment.collectAsState().value == page,
                        onClick = {
                            //set display to this item
                            homePageVM.setCurrentFragment(input = page)

                            //close drawer
                            scope.launch {drawerState.close()}

                            //remove backstack
                            navController.navigate(route = page.name) {
                                popUpTo(id = 0)
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.onSurface,
                            selectedTextColor = MaterialTheme.colorScheme.surfaceTint
                        ),
                        shape = RectangleShape
                    )
                }

                //divide page items from actions
                item{
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(10.dp))
                }

                //create save option
                item {
                    NavigationDrawerItem(
                        label = {Text(text = stringResource(id = R.string.saveLabel))},
                        selected = false,
                        onClick = {
                            //close drawer and save character
                            scope.launch{drawerState.close()}
                            attemptSave(
                                charInstance = charInstance,
                                filename = filename,
                                showToast = true
                            )
                        }
                    )
                }

                //create exit option
                item {
                    NavigationDrawerItem(
                        label = {Text(text = stringResource(id = R.string.exitLabel))},
                        selected = false,
                        onClick = {
                            scope.launch{drawerState.close()}
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
    private fun AppFooter(
        homePageVM: HomePageViewModel
    ){
        Surface {
            Column {
                //row for table header
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(0.2f))
                    //total column
                    Text(
                        text = stringResource(id = R.string.totalLabel),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(0.2f)
                    )
                    //combat column
                    Text(
                        text = stringResource(id = R.string.combatLabel),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(0.2f)
                    )
                    //magic column
                    Text(
                        text = stringResource(id = R.string.magicLabel),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(0.2f)
                    )
                    //psychic column
                    Text(
                        text = stringResource(id = R.string.psychicLabel),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(0.2f)
                    )
                }

                //create row for maximum values
                BottomBarRow(item = homePageVM.maximums)

                //create row for spent values
                BottomBarRow(item = homePageVM.expenditures)
            }
        }
    }

    /**
     * Creates a row for the bottom bar's display.
     *
     * @param item bottom bar row data that is to be displayed in this row
     */
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
                text = stringResource(id = item.nameRef),
                textAlign = TextAlign.End,
                modifier = Modifier.weight(0.2f)
            )

            //display maximum value
            AnimatedContent(
                targetState = item.maxVal.collectAsState().value,
                modifier = Modifier
                    .weight(0.2f),
                transitionSpec = numberScroll,
                label = "${stringResource(id = item.nameRef)}Max"
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
                label = "${stringResource(id = item.nameRef)}Combat"
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
                label = "${stringResource(id = item.nameRef)}Magic"
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
                label = "${stringResource(id = item.nameRef)}Psychic"
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
     * @param charInstance character object for save option
     * @param filename name of the character file for save option
     * @param closeDialog function to run on close option
     */
    @Composable
    private fun ExitAlert(
        charInstance: BaseCharacter,
        filename: String,
        closeDialog: () -> Unit
    ){
        AlertDialog(
            //close alert on dismissal
            onDismissRequest = {closeDialog()},
            title = {
                Text(
                    text = stringResource(id = R.string.exitTitle)
                )
            },
            confirmButton = {
                //attempt to save then leave page
                TextButton(
                    onClick = {
                        attemptSave(
                            charInstance = charInstance,
                            filename = filename,
                            showToast = true
                        )
                        finish()
                    }
                ){
                    Text(
                        text = stringResource(id = R.string.saveLabel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            dismissButton = {
                //leave page without saving
                TextButton(
                    onClick = {finish()}
                ){
                    Text(
                        text = stringResource(id = R.string.exitLabel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )
    }

    /**
     * Runs the appropriate save function for the given character item type.
     *
     * @param charInstance character object to save
     * @param filename name of the file to save to
     * @param showToast true if the toast notification in shown
     */
    private fun attemptSave(
        charInstance: BaseCharacter,
        filename: String,
        showToast: Boolean
    ){
        if(charInstance is SblChar) sblSave(charInstance, filename, showToast)
        else defaultSave(charInstance, filename, showToast)
    }

    /**
     * Attempts to save a normal character's data to its designated file.
     *
     * @param charInstance character object to be saved
     * @param filename name of the file to save to
     * @param showToast true if the toast notification is shown
     */
    private fun defaultSave(
        charInstance: BaseCharacter,
        filename: String,
        showToast: Boolean
    ){
        try{
            //get file
            val file = File("${this.filesDir}/AnimaChars", filename)

            //create file writer
            val saveStream = FileOutputStream(file)

            //get and write character's bytes
            val charData = charInstance.bytes
            saveStream.write(charData)
            saveStream.close()

            //notify of action completion
            if(showToast)
                Toast.makeText(
                    this@HomeActivity,
                    baseContext.resources.getString(R.string.saveMessage),
                    Toast.LENGTH_SHORT
                ).show()
        }
        //notify that file not found
        catch(e: FileNotFoundException){
            Toast.makeText(
                this@HomeActivity,
                baseContext.resources.getString(R.string.fileNotFound),
                Toast.LENGTH_SHORT
            ).show()
        }
        //notify for error in file writing
        catch(e: IOException) {
            Toast.makeText(
                this@HomeActivity,
                baseContext.resources.getString(R.string.fileError),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Attempts to save a by-level character's data to its designated file.
     *
     * @param charInstance character object to be saved
     * @param filename name of the file to save to
     * @param showToast true if the toast notification is shown
     */
    private fun sblSave(
        charInstance: SblChar,
        filename: String,
        showToast: Boolean
    ){
        try {
            //get file
            val file = File("${this.filesDir}/AnimaChars", filename)

            //turn the file into a directory if it is save by level
            if (!file.isDirectory)
                file.mkdir()

            //write each character's individual levels to their own files
            charInstance.charRefs.forEach {subChar ->
                //get the individual file's location
                val writeFile = File(file, "${charInstance.charRefs.indexOf(subChar)}")

                //create file writer
                val saveStream = FileOutputStream(writeFile)

                //get and write character's bytes
                val charData = charInstance.bytes
                saveStream.write(charData)
                saveStream.close()
            }

            //notify of action completion
            if(showToast)
                Toast.makeText(
                    this@HomeActivity,
                    baseContext.resources.getString(R.string.saveMessage),
                    Toast.LENGTH_SHORT
                ).show()
        }
        //notify that file not found
        catch(e: FileNotFoundException){
            Toast.makeText(
                this@HomeActivity,
                baseContext.resources.getString(R.string.fileNotFound),
                Toast.LENGTH_SHORT
            ).show()
        }
        //notify for error in file writing
        catch(e: IOException) {
            Toast.makeText(
                this@HomeActivity,
                baseContext.resources.getString(R.string.fileError),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @Preview
    @Composable
    fun PreviewHeader(){
        MaterialTheme(colorScheme = headerLightColors) {
            AppHeader(
                rememberDrawerState(DrawerValue.Closed),
                rememberCoroutineScope(),
                HomePageViewModel(BaseCharacter())
            )
        }
    }
}