package com.example.animabuilder.view_models

import androidx.compose.material.ScaffoldState
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.animabuilder.activities.HomeActivity.ScreenPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NavigationViewModel: ViewModel() {
    private val _scaffoldState = MutableStateFlow<ScaffoldState?>(null)
    private val _coroutineScope = MutableStateFlow<CoroutineScope?>(null)
    private val _navHostController = MutableStateFlow<NavHostController?>(null)
    private val _currentFragment = MutableStateFlow(ScreenPage.Character)

    val scaffoldState = _scaffoldState.asStateFlow()
    val coroutineScope = _coroutineScope.asStateFlow()
    val navHostController = _navHostController.asStateFlow()
    val currentFragment = _currentFragment.asStateFlow()

    fun setScaffoldState(input: ScaffoldState){_scaffoldState.update{input}}
    fun setCoroutineScope(input: CoroutineScope){_coroutineScope.update{input}}
    fun setNavHostController(input: NavHostController){_navHostController.update{input}}
    fun setCurrentFragment(input: ScreenPage){_currentFragment.update{input}}
}