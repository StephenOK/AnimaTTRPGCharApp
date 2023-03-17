package com.example.animabuilder.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.attributes.psychic.Discipline
import com.example.animabuilder.character_creation.attributes.psychic.Psychic
import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PsychicFragmentViewModel(
    private val psychic: Psychic,
    dexMod: Int
): ViewModel() {
    private val _freePsyPoints = MutableStateFlow(psychic.getFreePsyPoints().toString())
    val freePsyPoints = _freePsyPoints.asStateFlow()
    fun updateFreePsyPoints(){_freePsyPoints.update{psychic.getFreePsyPoints().toString()}}

    fun getPotentialBase(): Int{return psychic.psyPotentialBase}

    val psychicPoints = PsychicPurchaseItemData(
        R.string.psyPointLabel,
        psychic.innatePsyPoints.toString(),
        psychic.boughtPsyPoints,
        psychic.totalPsychicPoints,
    ){input, item ->
        psychic.buyPsyPoints(input)
        item.update{psychic.totalPsychicPoints.toString()}
        updateFreePsyPoints()
    }

    val psychicProjection = PsychicPurchaseItemData(
        R.string.psyProjectionLabel,
        dexMod.toString(),
        psychic.psyProjectionBought,
        psychic.psyProjectionTotal
    ){input, item ->
        psychic.buyPsyProjection(input)
        item.update{psychic.psyProjectionTotal.toString()}
    }

    val buyItems = listOf(psychicPoints, psychicProjection)

    val telepathy = DisciplineItemData(
        psychic,
        this,
        R.string.telepathyLabel,
        psychic.telepathy
    )

    val kinesis = DisciplineItemData(
        psychic,
        this,
        R.string.kinesisLabel,
        psychic.psychokinesis
    )

    val pyrokinesis = DisciplineItemData(
        psychic,
        this,
        R.string.pyroLabel,
        psychic.pyrokinesis
    )

    val cryokinesis = DisciplineItemData(
        psychic,
        this,
        R.string.cryoLabel,
        psychic.cryokinesis
    )

    val physIncrease = DisciplineItemData(
        psychic,
        this,
        R.string.physIncreaseLabel,
        psychic.physicalIncrease
    )

    val energy = DisciplineItemData(
        psychic,
        this,
        R.string.energyLabel,
        psychic.energyPowers
    )

    val sentience = DisciplineItemData(
        psychic,
        this,
        R.string.sentienceLabel,
        psychic.sentiencePowers
    )

    val telemetry = DisciplineItemData(
        psychic,
        this,
        R.string.telemetryLabel,
        psychic.telemetry
    )

    val matrixPowers = DisciplineItemData(
        psychic,
        this,
        R.string.matrixLabel,
        psychic.matrixPowers
    )

    val allDisciplines = listOf(telepathy, kinesis, pyrokinesis, cryokinesis, physIncrease,
        energy, sentience, telemetry, matrixPowers)

    fun setAllPowers(){
        allDisciplines.forEach{
            it.setInvestedIn()
            it.powerChecks.forEach{item ->
                item.value.value = psychic.masteredPowers.contains(item.key)
            }
        }
    }

    class PsychicPurchaseItemData(
        val title: Int,
        val baseString: String,
        boughtVal: Int,
        totalVal: Int,
        val totalUpdate: (Int, MutableStateFlow<String>) -> Unit
    ){
        private val _purchaseAmount = MutableStateFlow(boughtVal.toString())
        private val _totalAmount = MutableStateFlow(totalVal.toString())

        val purchaseAmount = _purchaseAmount.asStateFlow()
        val totalAmount = _totalAmount.asStateFlow()

        fun setPurchaseAmount(input: Int){
            setPurchaseAmount(input.toString())
            totalUpdate(input, _totalAmount)
        }
        fun setPurchaseAmount(input: String){_purchaseAmount.update{input}}
    }

    class DisciplineItemData(
        val psychic: Psychic,
        val psyFragVM: PsychicFragmentViewModel,
        val name: Int,
        val item: Discipline
    ){
        private val _isOpen = MutableStateFlow(false)
        private val _investedIn = MutableStateFlow(psychic.disciplineInvestment.contains(item))

        val isOpen = _isOpen.asStateFlow()
        val investedIn = _investedIn.asStateFlow()

        val powerChecks = mutableMapOf<PsychicPower, MutableState<Boolean>>()

        fun toggleOpen(){_isOpen.update{!isOpen.value}}
        fun setInvestedIn(input: Boolean){
            _investedIn.update{psychic.updateInvestment(item, input)}

            if(!input)
                psyFragVM.setAllPowers()

            psyFragVM.updateFreePsyPoints()
        }
        fun setInvestedIn(){
            _investedIn.update{psychic.disciplineInvestment.contains(item)}
        }

        fun setPower(power: PsychicPower, input: Boolean){
            if(!psychic.disciplineInvestment.contains(item) && name != R.string.matrixLabel)
                setInvestedIn(true)

            powerChecks[power]!!.value = psychic.masterPower(power, item, input)

            if(!input)
                psyFragVM.setAllPowers()

            psyFragVM.updateFreePsyPoints()
        }

        init{
            item.allPowers.forEach{
                powerChecks += Pair(it, mutableStateOf(psychic.masteredPowers.contains(it)))
            }
        }
    }
}