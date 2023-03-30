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

/**
 * View model that manages the character's psychic abilities.
 * Works on variables in the corresponding psychic fragment.
 *
 * @param psychic character's psychic abilities
 * @param dexMod character's dexterity modifier
 */
class PsychicFragmentViewModel(
    private val psychic: Psychic,
    dexMod: Int
): ViewModel() {
    //initialize character's free psychic point text
    private val _freePsyPoints = MutableStateFlow(psychic.getFreePsyPoints().toString())
    val freePsyPoints = _freePsyPoints.asStateFlow()

    /**
     * Updates the free psychic points display to reflect the value held in the character.
     */
    fun updateFreePsyPoints(){_freePsyPoints.update{psychic.getFreePsyPoints().toString()}}

    /**
     * Retrieves the data in regards to the character's base psychic potential.
     *
     * @return character's base psychic potential
     */
    fun getPotentialBase(): Int{return psychic.psyPotentialBase}

    //initialize data in regards to the character's psychic points
    private val psychicPoints = PsychicPurchaseItemData(
        R.string.psyPointLabel,
        psychic.innatePsyPoints.toString(),
        psychic.boughtPsyPoints,
        psychic.totalPsychicPoints,
    ){input, item ->
        psychic.buyPsyPoints(input)
        item.update{psychic.totalPsychicPoints.toString()}
        updateFreePsyPoints()
    }

    //initialize data in regards to the character's psychic projection
    private val psychicProjection = PsychicPurchaseItemData(
        R.string.psyProjectionLabel,
        dexMod.toString(),
        psychic.psyProjectionBought,
        psychic.psyProjectionTotal
    ){input, item ->
        psychic.buyPsyProjection(input)
        item.update{psychic.psyProjectionTotal.toString()}
    }

    //gather purchase items into a list
    val buyItems = listOf(psychicPoints, psychicProjection)

    //initialize data for each psychic discipline
    private val telepathy = DisciplineItemData(
        psychic,
        this,
        R.string.telepathyLabel,
        psychic.telepathy
    )

    private val kinesis = DisciplineItemData(
        psychic,
        this,
        R.string.kinesisLabel,
        psychic.psychokinesis
    )

    private val pyrokinesis = DisciplineItemData(
        psychic,
        this,
        R.string.pyroLabel,
        psychic.pyrokinesis
    )

    private val cryokinesis = DisciplineItemData(
        psychic,
        this,
        R.string.cryoLabel,
        psychic.cryokinesis
    )

    private val physIncrease = DisciplineItemData(
        psychic,
        this,
        R.string.physIncreaseLabel,
        psychic.physicalIncrease
    )

    private val energy = DisciplineItemData(
        psychic,
        this,
        R.string.energyLabel,
        psychic.energyPowers
    )

    private val sentience = DisciplineItemData(
        psychic,
        this,
        R.string.sentienceLabel,
        psychic.sentiencePowers
    )

    private val telemetry = DisciplineItemData(
        psychic,
        this,
        R.string.telemetryLabel,
        psychic.telemetry
    )

    private val matrixPowers = DisciplineItemData(
        psychic,
        this,
        R.string.matrixLabel,
        psychic.matrixPowers
    )

    //gather all discipline data
    val allDisciplines = listOf(telepathy, kinesis, pyrokinesis, cryokinesis, physIncrease,
        energy, sentience, telemetry, matrixPowers)

    /**
     * Updates all discipline and power checkboxes to reflect the character's ownership of them.
     */
    fun setAllPowers(){
        allDisciplines.forEach{
            it.setInvestedIn()
            it.powerChecks.forEach{item ->
                item.value.value = psychic.masteredPowers.contains(item.key)
            }
        }
    }

    /**
     * Data in regards to a purchasable psychic ability.
     *
     * @param title name of this section
     * @param baseString base score of the item
     * @param boughtVal initial amount purchased by the user
     * @param totalVal initial total for this stat
     * @param totalUpdate function to run on update of this item's value
     */
    class PsychicPurchaseItemData(
        val title: Int,
        val baseString: String,
        boughtVal: Int,
        totalVal: Int,
        val totalUpdate: (Int, MutableStateFlow<String>) -> Unit
    ){
        //initialize purchase input for this item
        private val _purchaseAmount = MutableStateFlow(boughtVal.toString())
        val purchaseAmount = _purchaseAmount.asStateFlow()

        //initialize total display for this item
        private val _totalAmount = MutableStateFlow(totalVal.toString())
        val totalAmount = _totalAmount.asStateFlow()

        /**
         * Sets the purchased amount to the indicated value.
         *
         * @param input value to buy for the character
         */
        fun setPurchaseAmount(input: Int){
            setPurchaseAmount(input.toString())
            totalUpdate(input, _totalAmount)
        }

        /**
         * Sets the purchased display to the inputted value.
         *
         * @param input new item to display
         */
        fun setPurchaseAmount(input: String){_purchaseAmount.update{input}}
    }

    /**
     * Data in regards to a character's psychic discipline.
     *
     * @param psychic character's psychic abilities
     * @param psyFragVM view model that manages psychic abilities
     * @param name string reference to the discipline's name
     * @param item discipline related to this item
     */
    class DisciplineItemData(
        val psychic: Psychic,
        private val psyFragVM: PsychicFragmentViewModel,
        val name: Int,
        val item: Discipline
    ){
        //initialize open state of the discipline item
        private val _isOpen = MutableStateFlow(false)
        val isOpen = _isOpen.asStateFlow()

        //initialize invested state of this discipline
        private val _investedIn = MutableStateFlow(psychic.disciplineInvestment.contains(item))
        val investedIn = _investedIn.asStateFlow()

        //initialize checklist of all associated powers
        val powerChecks = mutableMapOf<PsychicPower, MutableState<Boolean>>()

        /**
         * Changes the open state of the discipline display.
         */
        fun toggleOpen(){_isOpen.update{!isOpen.value}}

        /**
         * Sets the discipline's invested in state to the inputted value.
         *
         * @param input true if user desires to add it to their character
         */
        fun setInvestedIn(input: Boolean){
            //attempt discipline investment change
            _investedIn.update{psychic.updateInvestment(item, input)}

            //remove psychic powers if removing discipline
            if(!input)
                psyFragVM.setAllPowers()

            //update available psychic points
            psyFragVM.updateFreePsyPoints()
        }

        /**
         * Sets the invested in state to the value held by the character.
         */
        fun setInvestedIn(){_investedIn.update{psychic.disciplineInvestment.contains(item)}}

        /**
         * Changes the state of the indicated power to the desired state.
         *
         * @param power item to change the state of
         * @param input state to set the taken state to
         */
        fun setPower(power: PsychicPower, input: Boolean){
            //attempt to add discipline if character does not currently have it
            if(!psychic.disciplineInvestment.contains(item) && name != R.string.matrixLabel)
                setInvestedIn(true)

            //attempt to add the power to the character
            powerChecks[power]!!.value = psychic.masterPower(power, item, input)

            //reflect other power checks if this power is being removed
            if(!input)
                psyFragVM.setAllPowers()

            //update character's psychic points
            psyFragVM.updateFreePsyPoints()
        }

        init{
            //initialize checkboxes for all powers
            item.allPowers.forEach{
                powerChecks += Pair(it, mutableStateOf(psychic.masteredPowers.contains(it)))
            }
        }
    }
}