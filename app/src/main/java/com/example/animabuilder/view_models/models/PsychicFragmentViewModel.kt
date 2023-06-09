package com.example.animabuilder.view_models.models

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.attributes.class_objects.CharClass
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
 * @param charClass class record of the character
 * @param dexMod character's dexterity modifier
 */
class PsychicFragmentViewModel(
    private val psychic: Psychic,
    private val charClass: MutableState<CharClass>,
    dexMod: Int
): ViewModel() {
    //initialize character's free psychic point text
    private val _freePsyPoints = MutableStateFlow(psychic.getFreePsyPoints().toString())
    val freePsyPoints = _freePsyPoints.asStateFlow()

    //initialize color of free point text
    private val _freePointColor = MutableStateFlow(
        if(psychic.getFreePsyPoints() >= 0) Color.Black
        else Color.Red
    )
    val freePointColor = _freePointColor.asStateFlow()

    //initialize character's taken number of innate slots
    private val _innateSlotDisplay = MutableStateFlow(psychic.innateSlotCount.value.toString())
    val innateSlotDisplay = _innateSlotDisplay.asStateFlow()

    //initialize open state of the detail alert
    private val _detailAlertOpen = MutableStateFlow(false)
    val detailAlertOpen = _detailAlertOpen.asStateFlow()

    //initialize title of the detail alert
    private val _detailTitle = MutableStateFlow("")
    val detailTitle = _detailTitle.asStateFlow()

    //initialize detail alert's displayed item
    private val _detailItem = MutableStateFlow<PsychicPower?>(null)
    val detailItem = _detailItem.asStateFlow()

    /**
     * Updates the free psychic points display to reflect the value held in the character.
     */
    fun updateFreePsyPoints(){
        _freePsyPoints.update{psychic.getFreePsyPoints().toString()}
        _freePointColor.update{
            //set to normal color for valid input
            if(psychic.getFreePsyPoints() >= 0) Color.Black
            //notify user of illegal input
            else Color.Red
        }
    }

    /**
     * Sets the number of innate slots the character can use.
     *
     * @param input number of innate slots to acquire
     */
    fun setInnateSlotDisplay(input: Int){
        psychic.buyInnateSlots(input)
        setInnateSlotDisplay(input.toString())
        updateFreePsyPoints()
    }

    /**
     * Sets the displayed number of innate slots.
     *
     * @param input amount of slots to display
     */
    fun setInnateSlotDisplay(input: String){_innateSlotDisplay.update{input}}

    /**
     * Opens and closes the detail alert as needed.
     */
    fun toggleDetailAlertOpen(){_detailAlertOpen.update{!detailAlertOpen.value}}

    /**
     * Sets the displayed content for the detail alert.
     */
    fun setDetailItem(input: PsychicPower){
        _detailTitle.update{input.name}
        _detailItem.update{input}
    }

    /**
     * Returns if the inputted discipline is legally obtainable by the character.
     *
     * @param input discipline to check
     */
    fun isLegalDiscipline(input: Discipline): Boolean{
        return psychic.legalDisciplines.contains(input)
    }

    //initialize data in regards to the character's psychic potential
    private val psychicPotential = PsychicPurchaseItemData(
        R.string.psyPotentialLabel,
        {psychic.psyPotentialBase.value},
        {psychic.pointsInPotential.value},
        {psychic.psyPotentialTotal.value},
        null,
        {Color.Black},
    ){input, item ->
        psychic.setPointPotential(input)
        item.update{psychic.psyPotentialTotal.value.toString()}
        updateFreePsyPoints()
    }

    //initialize data in regards to the character's psychic points
    private val psychicPoints = PsychicPurchaseItemData(
        R.string.psyPointLabel,
        {psychic.innatePsyPoints.value},
        {psychic.boughtPsyPoints.value},
        {psychic.totalPsychicPoints.value},
        {charClass.value.psyPointGrowth},
        {Color.Black}
    ){input, item ->
        psychic.buyPsyPoints(input)
        item.update{psychic.totalPsychicPoints.value.toString()}
        updateFreePsyPoints()
    }

    //initialize data in regards to the character's psychic projection
    private val psychicProjection = PsychicPurchaseItemData(
        R.string.psyProjectionLabel,
        {dexMod},
        {psychic.psyProjectionBought.value},
        {psychic.psyProjectionTotal.value},
        {charClass.value.psyProjGrowth},
        {
            if(psychic.getValidProjection())
                Color.Black
            else
                Color.Red
        }
    ){input, item ->
        psychic.buyPsyProjection(input)
        item.update{psychic.psyProjectionTotal.value.toString()}
    }

    //gather purchase items into a list
    val buyItems = listOf(psychicPotential, psychicProjection, psychicPoints)

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
            //set investment checkbox
            it.setInvestedIn()

            //update each of the discipline's powers
            it.powerList.forEach{power ->
                if(power.powerInvestedIn.value != psychic.masteredPowers.contains(power.item))
                    power.setPowerInvestedIn(psychic.masteredPowers.contains(power.item))
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
     * @param dpGetter function to run to determine the DP needed for this item
     * @param changeColor function to run to check to legitimacy of the inputted value
     * @param totalUpdate function to run on update of this item's value
     */
    class PsychicPurchaseItemData(
        val title: Int,
        val baseString: () -> Int,
        val boughtVal: () -> Int,
        val totalVal: () -> Int,
        val dpGetter: (() -> Int)?,
        val changeColor: () -> Color,
        val totalUpdate: (Int, MutableStateFlow<String>) -> Unit
    ){
        //initialize purchase input for this item
        private val _purchaseAmount = MutableStateFlow(boughtVal().toString())
        val purchaseAmount = _purchaseAmount.asStateFlow()

        //initialize text's color
        private val _textColor = MutableStateFlow(changeColor())
        val textColor = _textColor.asStateFlow()

        private val _dpDisplay = MutableStateFlow("")
        val dpDisplay = _dpDisplay.asStateFlow()

        //initialize total display for this item
        private val _totalAmount = MutableStateFlow(totalVal().toString())
        val totalAmount = _totalAmount.asStateFlow()

        /**
         * Sets the purchased amount to the indicated value.
         *
         * @param input value to buy for the character
         */
        fun setPurchaseAmount(input: Int){
            setPurchaseAmount(input.toString())
            totalUpdate(input, _totalAmount)
            setTextColor()
        }

        /**
         * Sets the purchased display to the inputted value.
         *
         * @param input new item to display
         */
        fun setPurchaseAmount(input: String){_purchaseAmount.update{input}}

        /**
         * Sets the color of the displayed input text.
         */
        fun setTextColor(){_textColor.update{changeColor()}}

        /**
         * Sets the displayed DP string.
         */
        fun setDPDisplay(input: String){_dpDisplay.update{input}}

        /**
         * Refreshes the item value on loading this page.
         */
        fun refreshItem(){
            setPurchaseAmount(boughtVal())
        }
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
        val powerList = mutableListOf<PowerItemData>()

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

        init{
            //initialize checkboxes for all powers
            item.allPowers.forEach{
                powerList.add(PowerItemData(psyFragVM, psychic, this, it))
            }
        }
    }

    /**
     * Object that manages data for an individual psychic power.
     *
     * @param psyFragVM view model that manages this item
     * @param psychic character's psychic ability object
     * @param home discipline data object that this power is associated with
     * @param item psychic power maintained by this object
     */
    class PowerItemData(
        val psyFragVM: PsychicFragmentViewModel,
        val psychic: Psychic,
        val home: DisciplineItemData,
        val item: PsychicPower
    ){
        //initialize whether the character has mastered this power
        private val _powerInvestedIn = MutableStateFlow(psychic.masteredPowers.contains(item))
        val powerInvestedIn = _powerInvestedIn.asStateFlow()

        //initialize psychic point investment in this power
        private val _pointInvestment = MutableStateFlow(
            if(psychic.masteredPowers.contains(item)) psychic.masteredPowers[item].toString()
            else "0"
        )
        val pointInvestment = _pointInvestment.asStateFlow()

        //initialize display for bonus potential gained
        private val _bonusGained = MutableStateFlow(
            if(psychic.masteredPowers.contains(item)) psychic.masteredPowers[item]!!
            else null
        )
        val bonusGained = _bonusGained.asStateFlow()

        /**
         * Sets the character's mastery of this power.
         *
         * @param input true if character is mastering this power
         */
        fun setPowerInvestedIn(input: Boolean){
            //attempt to add discipline if character does not currently have it
            if(input && !psychic.disciplineInvestment.contains(home.item) && home.name != R.string.matrixLabel)
                home.setInvestedIn(true)

            //change mastery as needed
            _powerInvestedIn.update{psychic.masterPower(item, home.item, input)}

            //if user is removing mastery
            if(!input) {
                //reflect other power checks if this power is being removed
                psyFragVM.setAllPowers()

                //update point investment on power's removal
                setPointInvestment("0")
                setBonusGained()
            }

            //update character's psychic points
            psyFragVM.updateFreePsyPoints()
        }

        /**
         * Sets the number of points invested in enhancing this power.
         *
         * @param input number of points to invest
         */
        fun setPointInvestment(input: Int){
            //only run if power is mastered and input is valid
            if(psychic.masteredPowers.contains(item) && input <= 10){
                //update power enhancement
                psychic.enhancePower(item, input)

                //update related displays
                setPointInvestment(psychic.masteredPowers[item].toString())
                psyFragVM.updateFreePsyPoints()
                setBonusGained()
            }
        }

        /**
         * Updates the displayed value for the power's enhancement.
         *
         * @param input string to display
         */
        fun setPointInvestment(input: String){_pointInvestment.update{input}}

        /**
         * Updates the displayed potential gained from psychic point investment.
         */
        fun setBonusGained(){
            _bonusGained.update{
                //display bonus to mastered power
                if(psychic.masteredPowers.contains(item))
                    psychic.masteredPowers[item]!!
                //display nothing if power isn't mastered
                else null
            }
        }
    }

    /**
     * Refresh the displayed items for a page reload.
     */
    fun refreshPage(){
        //refresh each bought item
        buyItems.forEach{it.refreshItem()}

        //refresh innate slots purchased
        setInnateSlotDisplay(psychic.innateSlotCount.value.toString())

        //refresh all power and discipline data
        setAllPowers()
        allDisciplines.forEach{
            it.powerList.forEach{power ->
                if(psychic.masteredPowers.contains(power.item))
                    power.setPointInvestment(psychic.masteredPowers[power.item]!!)
                else
                    power.setPointInvestment("0")
            }
        }
    }
}