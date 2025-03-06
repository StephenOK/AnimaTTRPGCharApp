package com.paetus.animaCharCreator.view_models.models

import android.content.Context
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Psychic
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the character's psychic abilities.
 * Works on variables in the corresponding psychic fragment.
 *
 * @param psychic character's psychic abilities
 * @param dexMod character's dexterity modifier
 * @param context source of the accessed resources
 */
class PsychicFragmentViewModel(
    private val psychic: Psychic,
    dexMod: Int,
    private val context: Context
): ViewModel() {
    //initialize character's free psychic point text
    private val _freePsyPoints = MutableStateFlow(value = psychic.getFreePsyPoints())
    val freePsyPoints = _freePsyPoints.asStateFlow()

    //initialize color of free point text
    private val _freePointValid = MutableStateFlow(value = psychic.getFreePsyPoints() >= 0)
    val freePointValid = _freePointValid.asStateFlow()

    //initialize character's taken number of innate slots
    private val _innateSlotDisplay = MutableStateFlow(value = psychic.innateSlotCount.intValue.toString())
    val innateSlotDisplay = _innateSlotDisplay.asStateFlow()

    //initialize character's innate slot label
    private val _innateSlotLabel = MutableStateFlow(value = "")
    val innateSlotLabel = _innateSlotLabel.asStateFlow()

    //initialize open state of the detail alert
    private val _detailAlertOpen = MutableStateFlow(value = false)
    val detailAlertOpen = _detailAlertOpen.asStateFlow()

    //initialize title of the detail alert
    private val _detailTitle = MutableStateFlow(value = "")
    val detailTitle = _detailTitle.asStateFlow()

    //initialize detail alert's displayed item
    private val _detailItem = MutableStateFlow<PsychicPower?>(value = null)
    val detailItem = _detailItem.asStateFlow()

    /**
     * Updates the free psychic points display to reflect the value held in the character.
     */
    fun updateFreePsyPoints(){
        _freePsyPoints.update{psychic.getFreePsyPoints()}

        //set to normal color for valid input
        _freePointValid.update{psychic.getFreePsyPoints() >= 0}
    }

    /**
     * Sets the number of innate slots the character can use.
     *
     * @param slotBuy number of innate slots to acquire
     */
    fun setInnateSlotDisplay(slotBuy: Int){
        psychic.buyInnateSlots(slotBuy = slotBuy)
        setInnateSlotDisplay(display = slotBuy.toString())
        updateFreePsyPoints()
    }

    /**
     * Sets the displayed number of innate slots.
     *
     * @param display amount of slots to display
     */
    fun setInnateSlotDisplay(display: String){_innateSlotDisplay.update{display}}

    /**
     * Sets the label for the innate slot input.
     *
     * @param dpLabel new label to set
     */
    fun setInnateSlotLabel(dpLabel: String){_innateSlotLabel.update{dpLabel}}

    /**
     * Opens and closes the detail alert as needed.
     */
    fun toggleDetailAlertOpen(){_detailAlertOpen.update{!detailAlertOpen.value}}

    /**
     * Sets the displayed content for the detail alert.
     *
     * @param power power to show the details of
     */
    fun setDetailItem(power: PsychicPower){
        _detailTitle.update{context.resources.getStringArray(R.array.powerNames)[power.name]}
        _detailItem.update{power}
    }

    /**
     * Returns if the inputted discipline is legally obtainable by the character.
     *
     * @param discipline discipline to check
     * @return true if the character can take this discipline
     */
    fun isLegalDiscipline(discipline: Discipline): Boolean{
        return psychic.legalDisciplines.contains(discipline)
    }

    //initialize data in regards to the character's psychic potential
    private val psychicPotential = PsychicPurchaseItemData(
        title = R.string.psyPotentialLabel,
        baseString = {psychic.psyPotentialBase.intValue},
        boughtVal = {psychic.pointsInPotential.intValue},
        totalVal = {psychic.psyPotentialTotal.intValue},
        getResource = {R.string.psyPointInput},
        getValue = {1},
        getValid = {true},
        totalUpdate = {input, item ->
            psychic.setPointPotential(potentialBuy = input)
            item.update{psychic.psyPotentialTotal.intValue}
            updateFreePsyPoints()
        }
    )

    //initialize data in regards to the character's psychic points
    private val psychicPoints = PsychicPurchaseItemData(
        title = R.string.psyPointLabel,
        baseString = {psychic.innatePsyPoints.intValue},
        boughtVal = {psychic.boughtPsyPoints.intValue},
        totalVal = {psychic.totalPsychicPoints.intValue},
        getResource = {R.string.dpLabel},
        getValue = {psychic.psyPointCost()},
        getValid = {true},
        totalUpdate = {input, item ->
            psychic.buyPsyPoints(ppBuy = input)
            item.update{psychic.totalPsychicPoints.intValue}
            updateFreePsyPoints()
        }
    )

    //initialize data in regards to the character's psychic projection
    private val psychicProjection = PsychicPurchaseItemData(
        title = R.string.psyProjectionLabel,
        baseString = {dexMod},
        boughtVal = {psychic.psyProjectionBought.intValue},
        totalVal = {psychic.psyProjectionTotal.intValue},
        getResource = {R.string.dpLabel},
        getValue = {psychic.psyProjCost()},
        getValid = {psychic.getValidProjection()},
        totalUpdate = {input, item ->
            psychic.buyPsyProjection(projBuy = input)
            item.update{psychic.psyProjectionTotal.intValue}
        }
    )

    //gather purchase items into a list
    val buyItems = listOf(psychicPotential, psychicProjection, psychicPoints)

    //initialize data for each psychic discipline
    private val telepathy = DisciplineItemData(
        psychic = psychic,
        nameRef = 0,
        discipline = psychic.telepathy,
        psyFragVM = this
    )

    private val kinesis = DisciplineItemData(
        psychic = psychic,
        nameRef = 1,
        discipline = psychic.psychokinesis,
        psyFragVM = this
    )

    private val pyrokinesis = DisciplineItemData(
        psychic = psychic,
        nameRef = 2,
        discipline = psychic.pyrokinesis,
        psyFragVM = this
    )

    private val cryokinesis = DisciplineItemData(
        psychic = psychic,
        nameRef = 3,
        discipline = psychic.cryokinesis,
        psyFragVM = this
    )

    private val physIncrease = DisciplineItemData(
        psychic = psychic,
        nameRef = 4,
        discipline = psychic.physicalIncrease,
        psyFragVM = this
    )

    private val energy = DisciplineItemData(
        psychic = psychic,
        nameRef = 5,
        discipline = psychic.energyPowers,
        psyFragVM = this
    )

    private val sentience = DisciplineItemData(
        psychic = psychic,
        nameRef = 6,
        discipline = psychic.sentiencePowers,
        psyFragVM = this
    )

    private val telemetry = DisciplineItemData(
        psychic = psychic,
        nameRef = 7,
        discipline = psychic.telemetry,
        psyFragVM = this
    )

    private val matrixPowers = DisciplineItemData(
        psychic = psychic,
        nameRef = 8,
        discipline = psychic.matrixPowers,
        psyFragVM = this
    )

    //gather all discipline data
    val allDisciplines = listOf(telepathy, kinesis, pyrokinesis, cryokinesis, physIncrease,
        energy, sentience, telemetry, matrixPowers)

    /**
     * Updates all discipline and power checkboxes to reflect the character's ownership of them.
     */
    fun setAllPowers(){
        allDisciplines.forEach{discipline ->
            //set investment checkbox
            discipline.setInvestedIn()

            //update each of the discipline's powers
            discipline.powerList.forEach{power ->
                if(power.powerInvestedIn.value != psychic.masteredPowers.contains(power.psyPower))
                    power.setPowerInvestedIn(psychic.masteredPowers.contains(power.psyPower))
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
     * @param getResource gets the string resource associated with the item
     * @param getValue function to run to determine the DP needed for this item
     * @param getValid function to run to check to legitimacy of the inputted value
     * @param totalUpdate function to run on update of this item's value
     */
    class PsychicPurchaseItemData(
        val title: Int,
        val baseString: () -> Int,
        val boughtVal: () -> Int,
        val totalVal: () -> Int,
        val getResource: () -> Int,
        val getValue: () -> Int,
        val getValid: () -> Boolean,
        val totalUpdate: (Int, MutableStateFlow<Int>) -> Unit
    ){
        //initialize purchase input for this item
        private val _purchaseAmount = MutableStateFlow(value = boughtVal().toString())
        val purchaseAmount = _purchaseAmount.asStateFlow()

        //initialize text's color
        private val _inputValid = MutableStateFlow(value = getValid())
        val inputValid = _inputValid.asStateFlow()

        //initialize development point label
        private val _dpDisplay = MutableStateFlow(value = "")
        val dpDisplay = _dpDisplay.asStateFlow()

        //initialize total display for this item
        private val _totalAmount = MutableStateFlow(value = totalVal())
        val totalAmount = _totalAmount.asStateFlow()

        /**
         * Sets the purchased amount to the indicated value.
         *
         * @param buyVal value to buy for the character
         */
        fun setPurchaseAmount(buyVal: Int){
            setPurchaseAmount(display = buyVal.toString())
            totalUpdate(buyVal, _totalAmount)
            setTextColor()
        }

        /**
         * Sets the purchased display to the inputted value.
         *
         * @param display new item to display
         */
        fun setPurchaseAmount(display: String){_purchaseAmount.update{display}}

        /**
         * Sets the color of the displayed input text.
         */
        private fun setTextColor(){_inputValid.update{getValid()}}

        /**
         * Sets the displayed DP string.
         */
        fun setDPDisplay(dpDisplay: String){_dpDisplay.update{dpDisplay}}

        /**
         * Refreshes the item value on loading this page.
         */
        fun refreshItem(){
            if(purchaseAmount.value != "")
                setPurchaseAmount(buyVal = boughtVal())
        }
    }

    /**
     * Data in regards to a character's psychic discipline.
     *
     * @param psychic character's psychic abilities
     * @param nameRef string reference to the discipline's name
     * @param discipline discipline related to this item
     * @param psyFragVM view model that manages psychic abilities
     */
    class DisciplineItemData(
        val psychic: Psychic,
        val nameRef: Int,
        val discipline: Discipline,
        private val psyFragVM: PsychicFragmentViewModel
    ){
        //initialize open state of the discipline item
        private val _isOpen = MutableStateFlow(value = false)
        val isOpen = _isOpen.asStateFlow()

        //initialize invested state of this discipline
        private val _investedIn = MutableStateFlow(value = psychic.disciplineInvestment.contains(element = discipline))
        val investedIn = _investedIn.asStateFlow()

        //initialize checklist of all associated powers
        val powerList = mutableListOf<PowerItemData>()

        /**
         * Changes the open state of the discipline display.
         */
        fun toggleOpen(){_isOpen.update{!isOpen.value}}

        /**
         * Sets the discipline's invested in state to the inputted value.
         *
         * @param investIn true if user desires to add it to their character
         */
        fun setInvestedIn(investIn: Boolean){
            //attempt discipline investment change
            _investedIn.update{psychic.updateInvestment(discipline = discipline, isTaken = investIn)}

            //remove psychic powers if removing discipline
            if(!investIn)
                psyFragVM.setAllPowers()

            //update available psychic points
            psyFragVM.updateFreePsyPoints()
        }

        /**
         * Sets the invested in state to the value held by the character.
         */
        fun setInvestedIn(){_investedIn.update{psychic.disciplineInvestment.contains(element = discipline)}}

        init{
            //initialize checkboxes for all powers
            discipline.allPowers.forEach{power ->
                powerList.add(
                    element = PowerItemData(
                        psychic = psychic,
                        discipline = this,
                        psyPower = power,
                        psyFragVM = psyFragVM
                    )
                )
            }
        }
    }

    /**
     * Object that manages data for an individual psychic power.
     *
     * @param psychic character's psychic ability object
     * @param discipline discipline data object that this power is associated with
     * @param psyPower psychic power maintained by this object
     * @param psyFragVM view model that manages this item
     */
    class PowerItemData(
        val psychic: Psychic,
        val discipline: DisciplineItemData,
        val psyPower: PsychicPower,
        val psyFragVM: PsychicFragmentViewModel
    ){
        //initialize whether the character has mastered this power
        private val _powerInvestedIn = MutableStateFlow(value = psychic.masteredPowers.contains(psyPower))
        val powerInvestedIn = _powerInvestedIn.asStateFlow()

        //initialize displayed psychic point cost label
        private val _pointLabel = MutableStateFlow(value = "")
        val pointLabel = _pointLabel.asStateFlow()

        //initialize psychic point investment in this power
        private val _pointInvestment = MutableStateFlow(
            value =
                if(psychic.masteredPowers.contains(key = psyPower)) psychic.masteredPowers[psyPower].toString()
                else "0"
        )
        val pointInvestment = _pointInvestment.asStateFlow()

        //initialize display for bonus potential gained
        private val _bonusGained = MutableStateFlow(
            value =
                if(psychic.masteredPowers.contains(key = psyPower)) psychic.masteredPowers[psyPower]!!
                else 0
        )
        val bonusGained = _bonusGained.asStateFlow()

        /**
         * Sets the character's mastery of this power.
         *
         * @param isTaking true if character is mastering this power
         */
        fun setPowerInvestedIn(isTaking: Boolean){
            //attempt to add discipline if character does not currently have it
            if(isTaking && !psychic.disciplineInvestment.contains(element = discipline.discipline) && discipline.nameRef != 8)
                discipline.setInvestedIn(investIn = true)

            //change mastery as needed
            _powerInvestedIn.update{psychic.masterPower(
                power = psyPower,
                discipline = discipline.discipline,
                isMastering = isTaking
            )}

            //if user is removing mastery
            if(!isTaking) {
                //reflect other power checks if this power is being removed
                psyFragVM.setAllPowers()

                //update point investment on power's removal
                setPointInvestment(display = "0")
                setBonusGained()
            }

            //update character's psychic points
            psyFragVM.updateFreePsyPoints()
        }

        /**
         * Sets the displayed psychic point cost to the inputted value.
         *
         * @param display new string to display
         */
        fun setPointLabel(display: String){_pointLabel.update{display}}

        /**
         * Sets the number of points invested in enhancing this power.
         *
         * @param ppInvest number of points to invest
         */
        fun setPointInvestment(ppInvest: Int){
            //only run if power is mastered and input is valid
            if(psychic.masteredPowers.contains(key = psyPower) && ppInvest <= 10){
                //update power enhancement
                psychic.enhancePower(power = psyPower, applyPoints = ppInvest)

                //update related displays
                setPointInvestment(display = psychic.masteredPowers[psyPower].toString())
                psyFragVM.updateFreePsyPoints()
                setBonusGained()
            }
        }

        /**
         * Updates the displayed value for the power's enhancement.
         *
         * @param display string to display
         */
        fun setPointInvestment(display: String){_pointInvestment.update{display}}

        /**
         * Updates the displayed potential gained from psychic point investment.
         */
        private fun setBonusGained(){
            _bonusGained.update{
                //display bonus to mastered power
                if(psychic.masteredPowers.contains(key = psyPower))
                    psychic.masteredPowers[psyPower]!!
                //display nothing if power isn't mastered
                else 0
            }
        }

        /**
         * Refresh the power enhancement value for this psychic power.
         */
        fun refreshItem(){
            //if power input is not empty
            if(_pointInvestment.value != ""){
                //display enhancement value
                if(psychic.masteredPowers.contains(key = psyPower))
                    setPointInvestment(ppInvest = psychic.masteredPowers[psyPower]!!)
                //otherwise display 0
                else
                    setPointInvestment(display = "0")
            }
        }
    }

    /**
     * Refresh the displayed items for a page reload.
     */
    fun refreshPage(){
        //refresh each bought item
        buyItems.forEach{power -> power.refreshItem()}

        //refresh innate slots purchased
        if(_innateSlotDisplay.value != "")
            setInnateSlotDisplay(display = psychic.innateSlotCount.intValue.toString())

        //refresh all power and discipline data
        setAllPowers()
        allDisciplines.forEach{discipline ->
            discipline.powerList.forEach{power -> power.refreshItem()}
        }
    }
}