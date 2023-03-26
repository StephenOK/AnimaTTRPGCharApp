package com.example.animabuilder.view_models

import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.attributes.summoning.SummonAbility
import com.example.animabuilder.character_creation.attributes.summoning.Summoning
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SummoningFragmentViewModel(
    val summoning: Summoning
): ViewModel() {
    private val summon = SummonItemData(R.string.summonTitle, summoning.summon)
    private val control = SummonItemData(R.string.controlTitle, summoning.control)
    private val bind = SummonItemData(R.string.bindTitle, summoning.bind)
    private val banish = SummonItemData(R.string.banishTitle, summoning.banish)

    val allRows = listOf(summon, control, bind, banish)

    class SummonItemData(
        val nameRef: Int,
        val item: SummonAbility
    ){
        private val _boughtVal = MutableStateFlow(item.buyVal.toString())
        private val _total = MutableStateFlow(item.abilityTotal.toString())

        val boughtVal = _boughtVal.asStateFlow()
        val total = _total.asStateFlow()

        fun setBoughtVal(input: Int){
            item.setBuyVal(input)
            _total.update{item.abilityTotal.toString()}
            setBoughtVal(input.toString())
        }

        fun setBoughtVal(input: String){_boughtVal.update{input}}
    }
}