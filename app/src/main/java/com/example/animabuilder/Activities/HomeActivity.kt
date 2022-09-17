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
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animabuilder.R
import com.example.animabuilder.activities.fragments.home_fragments.*
import com.example.animabuilder.character_creation.BaseCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.io.IOException

/**
 * Activity that runs all character creation fragments
 * Initially loads the CharacterPageFragment
 */

class HomeActivity : AppCompatActivity() {

    private enum class ScreenPage{
        Primary,
        Secondary,
        Advantages,
        Combat,
        Ki,
        Magic,
        Psychic,
        Equipment
    }

    lateinit var charInstance: BaseCharacter
    var filename: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        charInstance = intent.getSerializableExtra("Character") as BaseCharacter
        filename = intent.getStringExtra("filename")

        setContent{
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()

            val navController = rememberNavController()

            val currentFragment = remember{ mutableStateOf(ScreenPage.Primary)}

            val exitOpen = remember{mutableStateOf(false)}

            val maxDP = remember{mutableStateOf(charInstance.devPT)}
            val maxCombat = remember{mutableStateOf(charInstance.maxCombatDP)}
            val maxMagic = remember{mutableStateOf(charInstance.maxMagDP)}
            val maxPsychic = remember{mutableStateOf(charInstance.maxPsyDP)}

            val usedDP = remember { mutableStateOf(charInstance.spentTotal) }
            val usedCombat = remember { mutableStateOf(charInstance.ptInCombat) }
            val usedMagic = remember { mutableStateOf(charInstance.ptInMag) }
            val usedPsychic = remember { mutableStateOf(charInstance.ptInPsy) }

            Scaffold(
                scaffoldState = scaffoldState,

                topBar = {TopAppBar (
                    title = {Text(text = currentFragment.value.name)},
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

                drawerContent = {
                    Column{
                        enumValues<ScreenPage>().forEach {
                            DrawerButton(it.name)
                            {
                                currentFragment.value = it
                                scope.launch{scaffoldState.drawerState.close()}
                                navController.navigate(it.name)
                            }
                        }

                        DrawerButton("Save"){
                            scope.launch{scaffoldState.drawerState.close()}
                            attemptSave()}

                        DrawerButton("Exit"){
                            scope.launch{scaffoldState.drawerState.close()}
                            exitOpen.value = true }
                    }
                },

                bottomBar = {
                    Column {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.weight(0.2f))
                            Text(
                                text = stringResource(R.string.totalLabel),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = stringResource(R.string.dpCombatLabel),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = stringResource(R.string.dpMagicLabel),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = stringResource(R.string.dpPsychicLabel),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.maxRowLabel),
                                textAlign = TextAlign.End,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = maxDP.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = maxCombat.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(text = maxMagic.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = maxPsychic.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.usedRowLabel),
                                textAlign = TextAlign.End,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = usedDP.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f))
                            Text(
                                text = usedCombat.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = usedMagic.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                            Text(
                                text = usedPsychic.value.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(0.2f)
                            )
                        }
                    }
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = ScreenPage.Primary.name,
                    modifier = Modifier.padding(it)
                ){
                    composable(route = ScreenPage.Primary.name){
                        CharacterPageFragment(charInstance, maxDP, maxCombat, maxMagic, maxPsychic)
                    }

                    composable(route = ScreenPage.Secondary.name){
                        SecondaryAbilityFragment(charInstance, usedDP)
                    }

                    composable(route = ScreenPage.Advantages.name){
                        Advantages(charInstance)
                    }

                    composable(route = ScreenPage.Combat.name){
                        CombatFragment()
                    }

                    composable(route = ScreenPage.Magic.name){
                        MagicFragment()
                    }

                    composable(route = ScreenPage.Psychic.name){
                        PsychicFragment()
                    }

                    composable(route = ScreenPage.Equipment.name){
                        EquipmentFragment()
                    }
                }
            }

            if(exitOpen.value)
                ExitAlert(exitOpen)
        }
    }

    @Composable
    private fun DrawerButton(display:String, action: () -> Unit){
        TextButton(onClick = {action()}) {
            Text(text = display)
        }
    }

    private fun attemptSave(){
        try{
            val saveStream = openFileOutput(filename, Context.MODE_PRIVATE)
            val charData = charInstance.bytes
            saveStream.write(charData)
            saveStream.close()

            Toast.makeText(
                this@HomeActivity,
                "Save successful!",
                Toast.LENGTH_SHORT
            ).show()
        }
        catch(e: FileNotFoundException){
            Toast.makeText(
                this@HomeActivity,
                "Unable to find file!",
                Toast.LENGTH_SHORT
            ).show()
        }
        catch(e: IOException) {
            Toast.makeText(
                this@HomeActivity,
                "Failed to write data!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @Composable
    private fun ExitAlert(
        isOpen: MutableState<Boolean>,
    ){
        AlertDialog(
            onDismissRequest = {isOpen.value = false},
            title = {Text(text = "Save before exiting?")},
            confirmButton = {
                TextButton(
                    onClick = {
                        attemptSave()
                        exitPage()}
                ){
                    Text(text = "Save")
                }},
            dismissButton = {
                TextButton(
                    onClick = {
                        exitPage()
                    }){
                    Text(text = "Exit")
                }
            }
        )
    }

    private fun exitPage(){
        startActivity(Intent(this@HomeActivity, MainActivity::class.java))
    }
}