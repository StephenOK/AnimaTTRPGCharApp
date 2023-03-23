package com.example.animabuilder.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.attributes.advantages.AdvantageRecord
import com.example.animabuilder.character_creation.attributes.advantages.advantage_types.Advantage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AdvantageFragmentViewModel(
    private val advantageRecord: AdvantageRecord
): ViewModel() {
    private val _creationPoints = MutableStateFlow((3 - advantageRecord.creationPointSpent).toString())

    val creationPoints = _creationPoints.asStateFlow()

    val takenAdvantages = mutableStateListOf<Advantage>()

    fun acquireAdvantage(): String?{
        val attemptAction = advantageRecord.acquireAdvantage(
            adjustedAdvantage.value!!,
            optionPicked.value,
            costPicked.value
        )

        if(attemptAction.first == null) updateAdvantagesTaken()

        setAdvantageCostOn(false)

        return attemptAction.first
    }

    fun acquireAdvantage(item: Advantage, taken: Int?, takenCost: Int): String?{
        val attemptAction =  advantageRecord.acquireAdvantage(item, taken, takenCost)

        if(attemptAction.first == null) updateAdvantagesTaken()

        return attemptAction.first
    }

    fun removeAdvantage(item: Advantage){
        advantageRecord.removeAdvantage(item)
        updateAdvantagesTaken()
    }

    private fun updateAdvantagesTaken() {
        takenAdvantages.clear()
        takenAdvantages.addAll(advantageRecord.takenAdvantages)
        _creationPoints.update{(3 - advantageRecord.creationPointSpent).toString()}
    }

    fun getRacialAdvantages(): List<Advantage>{return advantageRecord.raceAdvantages}

    private val commonAdv = AdvantageButtonData(
        R.string.commonAdv, advantageRecord.commonAdvantages.advantages)
    private val commonDisadv = AdvantageButtonData(
        R.string.commonDisadv, advantageRecord.commonAdvantages.disadvantages)
    private val magicAdv = AdvantageButtonData(
        R.string.magicAdv, advantageRecord.magicAdvantages.advantages)
    private val magicDisadv = AdvantageButtonData(
        R.string.magicDisadv, advantageRecord.magicAdvantages.disadvantages)
    private val psychicAdv = AdvantageButtonData(
        R.string.psychicAdv, advantageRecord.psychicAdvantages.advantages)
    private val psychicDisadv = AdvantageButtonData(
        R.string.psychicDisadv, advantageRecord.psychicAdvantages.disadvantages)

    val advantageButtons =
        listOf(commonAdv, magicAdv, psychicAdv, commonDisadv, magicDisadv, psychicDisadv)

    class AdvantageButtonData(
        val category: Int,
        val items: List<Advantage>
    ){
        private val _isOpen = MutableStateFlow(false)

        val isOpen = _isOpen.asStateFlow()

        fun setOpen(input: Boolean){_isOpen.update{input}}
    }




    private val _advantageCostOn = MutableStateFlow(false)
    private val _adjustedAdvantage = MutableStateFlow<Advantage?>(null)
    private val _adjustingPage = MutableStateFlow(1)

    private val _optionPicked = MutableStateFlow<Int?>(null)
    private val _costPicked = MutableStateFlow(0)

    val advantageCostOn = _advantageCostOn.asStateFlow()
    val adjustedAdvantage = _adjustedAdvantage.asStateFlow()
    val adjustingPage = _adjustingPage.asStateFlow()

    val optionPicked = _optionPicked.asStateFlow()
    val costPicked = _costPicked.asStateFlow()

    fun setAdvantageCostOn(input: Boolean){_advantageCostOn.update{input}}
    fun setAdjustedAdvantage(input: Advantage){_adjustedAdvantage.update{input}}
    fun setAdjustingPage(input: Int){_adjustingPage.update{input}}

    fun setOptionPicked(input: Int){_optionPicked.update{input}}
    fun setCostPicked(input: Int){_costPicked.update{input}}

    init{updateAdvantagesTaken()}
}