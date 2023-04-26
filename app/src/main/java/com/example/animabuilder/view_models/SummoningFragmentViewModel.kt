package com.example.animabuilder.view_models

import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.attributes.summoning.SummonAbility
import com.example.animabuilder.character_creation.attributes.summoning.Summoning
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the character's summoning data.
 * Works on variables in the corresponding fragment.
 *
 * @param summoning character's summoning abilities
 */
class SummoningFragmentViewModel(
    val summoning: Summoning
): ViewModel() {
    //initialize all summoning ability data
    private val summon = SummonItemData(R.string.summonTitle, summoning.summon)
    private val control = SummonItemData(R.string.controlTitle, summoning.control)
    private val bind = SummonItemData(R.string.bindTitle, summoning.bind)
    private val banish = SummonItemData(R.string.banishTitle, summoning.banish)

    //gather all summoning items
    val allRows = listOf(summon, control, bind, banish)

    /**
     * Data object for this summoning ability.
     *
     * @param nameRef string reference for this ability
     * @param item summoning ability for this item
     */
    class SummonItemData(
        val nameRef: Int,
        val item: SummonAbility
    ){
        //initialize bought input
        private val _boughtVal = MutableStateFlow(item.buyVal.toString())
        val boughtVal = _boughtVal.asStateFlow()

        //initialize total display
        private val _total = MutableStateFlow(item.abilityTotal.toString())
        val total = _total.asStateFlow()

        /**
         * Change bought input to the indicated value.
         *
         * @param input value to buy for the character
         */
        fun setBoughtVal(input: Int){
            item.setBuyVal(input)
            _total.update{item.abilityTotal.toString()}
            setBoughtVal(input.toString())
        }

        /**
         * Change the bought display to the inputted string.
         *
         * @param input new string to display
         */
        fun setBoughtVal(input: String){_boughtVal.update{input}}
    }
}