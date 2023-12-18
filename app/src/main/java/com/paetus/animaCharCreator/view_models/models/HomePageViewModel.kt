package com.paetus.animaCharCreator.view_models.models

import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.ScreenPage
import com.paetus.animaCharCreator.character_creation.BaseCharacter
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
    private val _currentFragment = MutableStateFlow(value = ScreenPage.Character)
    val currentFragment = _currentFragment.asStateFlow()

    //initialize open state of exit dialog
    private val _exitOpen = MutableStateFlow(value = false)
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
        nameRef = R.string.maxRowLabel,
        maxInput = charInstance.devPT.intValue,
        combatInput = charInstance.maxCombatDP.intValue,
        magInput = charInstance.maxMagDP.intValue,
        psyInput = charInstance.maxPsyDP.intValue
    )

    //initialize bottom bar spent values
    val expenditures = BottomBarRowData(
        nameRef = R.string.usedRowLabel,
        maxInput = charInstance.spentTotal.intValue,
        combatInput = charInstance.ptInCombat.intValue,
        magInput = charInstance.ptInMag.intValue,
        psyInput = charInstance.ptInPsy.intValue
    )

    /**
     * Updates the displayed maximums to the values held in the character object.
     */
    fun updateMaximums(){
        maximums.updateItems(
            max = charInstance.devPT.intValue,
            combat = charInstance.maxCombatDP.intValue,
            mag = charInstance.maxMagDP.intValue,
            psy = charInstance.maxPsyDP.intValue
        )
    }

    /**
     * Updates the displayed spent amounts to the values held in the character object.
     */
    fun updateExpenditures(){
        expenditures.updateItems(
            max = charInstance.spentTotal.intValue,
            combat = charInstance.ptInCombat.intValue,
            mag = charInstance.ptInMag.intValue,
            psy = charInstance.ptInPsy.intValue
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
        private val _maxVal = MutableStateFlow(value = maxInput)
        val maxVal = _maxVal.asStateFlow()

        //initialize combat display
        private val _combatVal = MutableStateFlow(value = combatInput)
        val combatVal = _combatVal.asStateFlow()

        //initialize magic display
        private val _magVal = MutableStateFlow(value = magInput)
        val magVal = _magVal.asStateFlow()

        //initialize psychic display
        private val _psyVal = MutableStateFlow(value = psyInput)
        val psyVal = _psyVal.asStateFlow()

        /**
         * Update all of the row's items to the inputted values.
         *
         * @param max total value column input
         * @param combat combat value column input
         * @param mag magic value column input
         * @param psy psychic value column input
         */
        fun updateItems(
            max: Int,
            combat: Int,
            mag: Int,
            psy: Int
        ){
            _maxVal.update{max}
            _combatVal.update{combat}
            _magVal.update{mag}
            _psyVal.update{psy}
        }
    }
}