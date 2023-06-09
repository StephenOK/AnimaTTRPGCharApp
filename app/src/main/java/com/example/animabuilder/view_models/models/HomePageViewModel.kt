package com.example.animabuilder.view_models.models

import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.ScreenPage
import com.example.animabuilder.character_creation.BaseCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the Home Fragment items.
 * Manages the bottom bar display values.
 * Manages the currently selected fragment that is displayed.
 *
 * @param charInstance character the user is working on
 */
class HomePageViewModel(val charInstance: BaseCharacter): ViewModel() {
    //initialize the page's current fragment
    private val _currentFragment = MutableStateFlow(ScreenPage.Character)
    val currentFragment = _currentFragment.asStateFlow()

    //initialize open state of exit dialog
    private val _exitOpen = MutableStateFlow(false)
    val exitOpen = _exitOpen.asStateFlow()

    /**
     * Changes the current page to the indicated one.
     *
     * @param input page to now display
     */
    fun setCurrentFragment(input: ScreenPage){_currentFragment.update{input}}

    /**
     * Toggles the open state of the exit alert.
     */
    fun toggleExitAlert() {
        _exitOpen.update{!exitOpen.value}
    }

    //initialize bottom bar maximum values
    val maximums = BottomBarRowData(
        R.string.maxRowLabel,
        charInstance.devPT.value,
        charInstance.maxCombatDP.value,
        charInstance.maxMagDP.value,
        charInstance.maxPsyDP.value
    )

    //initialize bottom bar spent values
    val expenditures = BottomBarRowData(
        R.string.usedRowLabel,
        charInstance.spentTotal.value,
        charInstance.ptInCombat.value,
        charInstance.ptInMag.value,
        charInstance.ptInPsy.value
    )

    /**
     * Updates the displayed maximums to the values held in the character object.
     */
    fun updateMaximums(){
        maximums.updateItems(
            charInstance.devPT.value,
            charInstance.maxCombatDP.value,
            charInstance.maxMagDP.value,
            charInstance.maxPsyDP.value
        )
    }

    /**
     * Updates the displayed spent amounts to the values held in the character object.
     */
    fun updateExpenditures(){
        expenditures.updateItems(
            charInstance.spentTotal.value,
            charInstance.ptInCombat.value,
            charInstance.ptInMag.value,
            charInstance.ptInPsy.value
        )
    }

    /**
     * Row of data contained in the app's bottom bar.
     *
     * @param nameRef header string reference of the row
     * @param maxInput initial total value displayed
     * @param combatInput initial combat value displayed
     * @param magInput initial magic value displayed
     * @param psyInput initial psychic value displayed
     */
    class BottomBarRowData(
        val nameRef: Int,
        maxInput: Int,
        combatInput: Int,
        magInput: Int,
        psyInput: Int
    ){
        //initialize maximum display
        private val _maxVal = MutableStateFlow(maxInput.toString())
        val maxVal = _maxVal.asStateFlow()

        //initialize combat display
        private val _combatVal = MutableStateFlow(combatInput.toString())
        val combatVal = _combatVal.asStateFlow()

        //initialize magic display
        private val _magVal = MutableStateFlow(magInput.toString())
        val magVal = _magVal.asStateFlow()

        //initialize psychic display
        private val _psyVal = MutableStateFlow(psyInput.toString())
        val psyVal = _psyVal.asStateFlow()

        /**
         * Update all of the row's items to the inputted values.
         *
         * @param max total value column input
         * @param combat combat value column input
         * @param mag magic value column input
         * @param psy psychic value column input
         */
        fun updateItems(max: Int, combat: Int, mag: Int, psy: Int){
            _maxVal.update{max.toString()}
            _combatVal.update{combat.toString()}
            _magVal.update{mag.toString()}
            _psyVal.update{psy.toString()}
        }
    }
}