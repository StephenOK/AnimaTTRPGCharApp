package com.paetus.animaCharCreator.view_models.models

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.class_objects.CharClass
import com.paetus.animaCharCreator.character_creation.attributes.summoning.SummonAbility
import com.paetus.animaCharCreator.character_creation.attributes.summoning.Summoning
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the character's summoning data.
 * Works on variables in the corresponding fragment.
 *
 * @param summoning character's summoning abilities
 * @param charClass current class that the character is
 */
class SummoningFragmentViewModel(
    val summoning: Summoning,
    val charClass: MutableState<CharClass>
): ViewModel() {
    //initialize all summoning ability data
    private val summon =
        SummonItemData(
            nameRef = R.string.summonTitle,
            summonAbility = summoning.summon,
            dpGetter = {charClass.value.summonGrowth}
        )

    private val control =
        SummonItemData(
            nameRef = R.string.controlTitle,
            summonAbility = summoning.control,
            dpGetter = {charClass.value.controlGrowth}
        )

    private val bind =
        SummonItemData(
            nameRef = R.string.bindTitle,
            summonAbility = summoning.bind,
            dpGetter = {charClass.value.bindGrowth}
        )

    private val banish =
        SummonItemData(
            nameRef = R.string.banishTitle,
            summonAbility = summoning.banish,
            dpGetter = {charClass.value.banishGrowth}
        )

    //gather all summoning items
    val allRows = listOf(summon, control, bind, banish)

    /**
     * Data object for this summoning ability.
     *
     * @param nameRef string reference for this ability
     * @param summonAbility summoning ability for this item
     * @param dpGetter function to run for the DP cost of this item
     */
    class SummonItemData(
        val nameRef: Int,
        val summonAbility: SummonAbility,
        val dpGetter: () -> Int
    ){
        //initialize bought input
        private val _boughtVal = MutableStateFlow(value = summonAbility.buyVal.intValue.toString())
        val boughtVal = _boughtVal.asStateFlow()

        //initialize DP display
        private val _dpLabel = MutableStateFlow(value = "")
        val dpLabel = _dpLabel.asStateFlow()

        //initialize total display
        private val _total = MutableStateFlow(value = summonAbility.abilityTotal.intValue)
        val total = _total.asStateFlow()

        /**
         * Change bought input to the indicated value.
         *
         * @param buyVal value to buy for the character
         */
        fun setBoughtVal(buyVal: Int){
            summonAbility.setBuyVal(pointPurchase = buyVal)
            _total.update{summonAbility.abilityTotal.intValue}
            setBoughtVal(display = buyVal.toString())
        }

        /**
         * Change the bought display to the inputted string.
         *
         * @param display new string to display
         */
        fun setBoughtVal(display: String){_boughtVal.update{display}}

        /**
         * Change the DP display item to show the indicated item.
         *
         * @param dpLabel new item to display
         */
        fun setDPLabel(dpLabel: String){_dpLabel.update{dpLabel}}

        /**
         * Refreshes the item on page loading.
         */
        fun refreshItem(){
            setBoughtVal(summonAbility.buyVal.intValue)
        }
    }

    /**
     * Function to run on loading this page.
     */
    fun refreshPage(){
        allRows.forEach{summoningRow -> summoningRow.refreshItem()}
    }
}