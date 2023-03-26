package com.example.animabuilder.view_models

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.attributes.ki_abilities.Ki
import com.example.animabuilder.character_creation.attributes.ki_abilities.KiStat
import com.example.animabuilder.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.Technique
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class KiFragmentViewModel(
    private val ki: Ki,
    context: Context
): ViewModel() {
    private val _remainingMK = MutableStateFlow(ki.martialKnowledgeRemaining.toString())
    private val _kiAccTotal = MutableStateFlow(ki.totalAcc.toString())
    private val _kiPointTotal = MutableStateFlow(ki.totalKi.toString())

    private val _techListOpen = MutableStateFlow(false)
    private val _kiListOpen = MutableStateFlow(false)
    private val _customTechOpen = MutableStateFlow(false)

    val remainingMK = _remainingMK.asStateFlow()
    val kiAccTotal = _kiAccTotal.asStateFlow()
    val kiPointTotal = _kiPointTotal.asStateFlow()

    val techListOpen = _techListOpen.asStateFlow()
    val kiListOpen = _kiListOpen.asStateFlow()
    val customTechOpen = _customTechOpen.asStateFlow()

    val allKiAbilities = mutableMapOf<KiAbility, MutableState<Boolean>>()
    val allTechniques = mutableMapOf<Technique, MutableState<Boolean>>()

    fun getMartialRemaining(): Int{return ki.martialKnowledgeRemaining}
    fun getMartialMax(): String{return ki.martialKnowledgeMax.toString()}
    fun getAllKiAbilities(): List<KiAbility>{return ki.kiRecord.allKiAbilities}
    fun getAllTechniques(): List<Technique>{return ki.allTechniques}
    fun getCustomTechniques(): List<Technique>{return ki.customTechniques}

    fun getTakenFirstSize(): Int{return ki.takenFirstTechniques.size}
    fun getTakenSecondSize(): Int{return ki.takenSecondTechniques.size}

    fun setRemainingMK(){_remainingMK.update{ki.martialKnowledgeRemaining.toString()}}
    fun setKiAccTotal(input: String){_kiAccTotal.update{input}}
    fun setKiPointTotal(input: String){_kiPointTotal.update{input}}

    fun toggleTechListOpen(): Boolean{
        if(ki.takenAbilities.contains(ki.kiRecord.kiControl)) {
            _techListOpen.update{!techListOpen.value}
            return true
        }

        return false
    }

    fun toggleKiListOpen() {_kiListOpen.update{!kiListOpen.value}}
    fun toggleCustomTechOpen() {_customTechOpen.update{!customTechOpen.value}}

    fun setKiAbilityTaken(item: KiAbility, input: Boolean){
        if(input) {
            if (item.prerequisites == null || ki.takenAbilities.contains(item.prerequisites))
                allKiAbilities[item]!!.value = ki.attemptAbilityAdd(item)
        }
        else{
            ki.removeAbility(item)
            allKiAbilities[item]!!.value = false
            updateKiTaken()
        }

        setRemainingMK()
    }

    fun updateKiTaken(){
        allKiAbilities.forEach{
            it.value.value = ki.takenAbilities.contains(it.key)
        }

        if(techListOpen.value && !ki.takenAbilities.contains(ki.kiRecord.kiControl))
            toggleTechListOpen()
    }

    fun attemptTechniqueChange(item: Technique, input: Boolean){
        if(input){
            if (ki.addTechnique(item))
                allTechniques[item]!!.value = true
        }
        else{
            ki.removeTechnique(item)
            allTechniques[item]!!.value = false
            updateTechTaken()
        }

        setRemainingMK()
    }

    fun addTechnique(item: Technique){
        ki.addTechnique(item)
        allTechniques += Pair(item, mutableStateOf(true))
    }

    fun updateTechTaken(){
        allTechniques.forEach{
            it.value.value = ki.takenTechniques.contains(it.key)
        }
    }

    private val kiSTR = KiRowData(
        context.resources.getStringArray(R.array.primaryCharArray)[0],
        ki.strKi,
        {setKiPointTotal(ki.totalKi.toString())},
        {setKiAccTotal(ki.totalAcc.toString())}
    )

    private val kiDEX = KiRowData(
        context.resources.getStringArray(R.array.primaryCharArray)[1],
        ki.dexKi,
        {setKiPointTotal(ki.totalKi.toString())},
        {setKiAccTotal(ki.totalAcc.toString())}
    )

    private val kiAGI = KiRowData(
        context.resources.getStringArray(R.array.primaryCharArray)[2],
        ki.agiKi,
        {setKiPointTotal(ki.totalKi.toString())},
        {setKiAccTotal(ki.totalAcc.toString())}
    )

    private val kiCON = KiRowData(
        context.resources.getStringArray(R.array.primaryCharArray)[3],
        ki.conKi,
        {setKiPointTotal(ki.totalKi.toString())},
        {setKiAccTotal(ki.totalAcc.toString())}
    )

    private val kiPOW = KiRowData(
        context.resources.getStringArray(R.array.primaryCharArray)[4],
        ki.powKi,
        {setKiPointTotal(ki.totalKi.toString())},
        {setKiAccTotal(ki.totalAcc.toString())}
    )

    private val kiWP = KiRowData(
        context.resources.getStringArray(R.array.primaryCharArray)[5],
        ki.wpKi,
        {setKiPointTotal(ki.totalKi.toString())},
        {setKiAccTotal(ki.totalAcc.toString())}
    )

    val allRowData = listOf(kiSTR, kiDEX, kiAGI, kiCON, kiPOW, kiWP)

    class KiRowData(
        val title: String,
        val item: KiStat,
        val setTotalPoints: () -> Unit,
        val setTotalAcc: () -> Unit
    ){
        private val _pointInputString = MutableStateFlow(item.boughtKiPoints.toString())
        private val _pointTotalString = MutableStateFlow(item.totalKiPoints.toString())
        private val _accInputString = MutableStateFlow(item.boughtAccumulation.toString())
        private val _accTotalString = MutableStateFlow(item.totalAccumulation.toString())

        val pointInputString = _pointInputString.asStateFlow()
        val pointTotalString = _pointTotalString.asStateFlow()
        val accInputString = _accInputString.asStateFlow()
        val accTotalString = _accTotalString.asStateFlow()

        fun setPointInputString(input: Int){
            item.setBoughtKiPoints(input)
            setPointInputString(input.toString())
            setPointTotalString(item.totalKiPoints.toString())
            setTotalPoints()
        }

        fun setPointInputString(input: String){_pointInputString.update{input}}
        fun setPointTotalString(input: String){_pointTotalString.update{input}}

        fun setAccInputString(input: Int){
            item.setBoughtAccumulation(input)
            setAccInputString(input.toString())
            setAccTotalString(item.totalAccumulation.toString())
            setTotalAcc()
        }

        fun setAccInputString(input: String){_accInputString.update{input}}
        fun setAccTotalString(input: String){_accTotalString.update{input}}
    }

    init{
        ki.kiRecord.allKiAbilities.forEach{
            allKiAbilities += Pair(it, mutableStateOf(ki.takenAbilities.contains(it)))
        }

        ki.allTechniques.forEach{
            allTechniques += Pair(it, mutableStateOf(ki.takenTechniques.contains(it)))
        }
    }
}