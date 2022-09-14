package com.example.animabuilder.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animabuilder.activities.fragments.home_fragments.*
import com.example.animabuilder.character_creation.BaseCharacter
import kotlinx.coroutines.launch

/**
 * Activity that runs all character creation fragments
 * Initially loads the CharacterPageFragment
 */

class HomeActivity : AppCompatActivity() {

    private enum class ScreenPage{
        Primary,
        Secondary,
        Combat,
        Magic,
        Psychic,
        Equipment
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val charInstance = intent.getSerializableExtra("Character") as BaseCharacter

        setContent{
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()

            val navController = rememberNavController()

            val currentFragment = remember{ mutableStateOf(ScreenPage.Primary)}

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
                    }
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = ScreenPage.Primary.name,
                    modifier = Modifier.padding(it)
                ){
                    composable(route = ScreenPage.Primary.name){
                        CharacterPageFragment(charInstance)
                    }

                    composable(route = ScreenPage.Secondary.name){
                        SecondaryAbilityFragment(charInstance)
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
        }
    }

    @Composable
    private fun DrawerButton(display:String, action: () -> Unit){
        TextButton(onClick = {action()}) {
            Text(text = display)
        }
    }
}