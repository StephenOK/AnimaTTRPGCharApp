package com.example.animabuilder.view_models

import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.activities.HomeActivity
import com.example.animabuilder.character_creation.BaseCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomePageViewModel(private val charInstance: BaseCharacter): ViewModel() {
    private val _currentFragment = MutableStateFlow(HomeActivity.ScreenPage.Character)

    val currentFragment = _currentFragment.asStateFlow()

    fun setCurrentFragment(input: HomeActivity.ScreenPage){_currentFragment.update{input}}

    val maximums = BottomBarRowData(
        R.string.maxRowLabel,
        charInstance.devPT,
        charInstance.maxCombatDP,
        charInstance.maxMagDP,
        charInstance.maxPsyDP
    )

    val expenditures = BottomBarRowData(
        R.string.usedRowLabel,
        charInstance.spentTotal,
        charInstance.ptInCombat,
        charInstance.ptInMag,
        charInstance.ptInPsy
    )

    class BottomBarRowData(
        val nameRef: Int,
        maxInput: Int,
        combatInput: Int,
        magInput: Int,
        psyInput: Int
    ){
        private val _maxVal = MutableStateFlow(maxInput.toString())
        private val _combatVal = MutableStateFlow(combatInput.toString())
        private val _magVal = MutableStateFlow(magInput.toString())
        private val _psyVal = MutableStateFlow(psyInput.toString())

        val maxVal = _maxVal.asStateFlow()
        val combatVal = _combatVal.asStateFlow()
        val magVal = _magVal.asStateFlow()
        val psyVal = _psyVal.asStateFlow()

        fun updateItems(max: Int, combat: Int, mag: Int, psy: Int){
            _maxVal.update{max.toString()}
            _combatVal.update{combat.toString()}
            _magVal.update{mag.toString()}
            _psyVal.update{psy.toString()}
        }
    }
}