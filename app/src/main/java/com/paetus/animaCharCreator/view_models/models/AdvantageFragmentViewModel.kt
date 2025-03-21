package com.paetus.animaCharCreator.view_models.models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.character_creation.attributes.advantages.AdvantageRecord
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the character's taken advantages and disadvantages.
 * Works on variables with the corresponding advantage fragment.
 *
 * @param charInstance object that holds all of the data for the character
 * @param advantageRecord object that holds all of the character's advantage information
 */
class AdvantageFragmentViewModel(
    private val charInstance: BaseCharacter,
    private val advantageRecord: AdvantageRecord
): ViewModel() {
    //initialize creation point display state flow
    private val _creationPoints = MutableStateFlow(value = 3 - advantageRecord.creationPointSpent.intValue)
    val creationPoints = _creationPoints.asStateFlow()

    //initialize open state flow of the advantage cost dialog
    private val _advantageCostOn = MutableStateFlow(value = false)
    val advantageCostOn = _advantageCostOn.asStateFlow()

    //initialize base advantage worked on
    private val _adjustedAdvantage = MutableStateFlow<Advantage?>(value = null)
    val adjustedAdvantage = _adjustedAdvantage.asStateFlow()

    //initialize page number of the dialog
    private val _adjustingPage = MutableStateFlow(value = 1)
    val adjustingPage = _adjustingPage.asStateFlow()

    //initialize the option picked for the advantage
    private val _optionPicked = MutableStateFlow<Int?>(value = null)
    val optionPicked = _optionPicked.asStateFlow()

    //initialize selected cost item for the advantage
    private val _costPicked = MutableStateFlow(value = 0)
    val costPicked = _costPicked.asStateFlow()

    //initialize alert open state for removing The Gift
    private val _giftAlertOpen = MutableStateFlow(value = false)
    val giftAlertOpen = _giftAlertOpen.asStateFlow()

    //initialize detail alert open state
    private val _detailAlertOpen = MutableStateFlow(value = false)
    val detailAlertOpen = _detailAlertOpen.asStateFlow()

    //initialize item in detail alert
    private val _detailItem = MutableStateFlow<Advantage?>(value = null)
    val detailItem = _detailItem.asStateFlow()

    //initialize list of taken advantages
    val takenAdvantages = mutableStateListOf<Advantage>()

    //initialize list of options taken for Half-Attuned to Tree advantage
    private val _halfAttunedOptions = MutableStateFlow(listOf<Int>())
    val halfAttunedOptions = _halfAttunedOptions.asStateFlow()

    /**
     * Change the on state of the advantage cost selection dialog.
     */
    fun toggleAdvantageCostOn() {
        _advantageCostOn.update{!advantageCostOn.value}

        //reset options on advantage cost dialog's opening
        if(advantageCostOn.value) {
            setOptionPicked(advOption = null)
            setCostPicked(cost = 0)
        }
    }

    /**
     * Sets the advantage that is having options and/or costs selected for.
     *
     * @param advantage advantage item to select options for
     */
    fun setAdjustedAdvantage(advantage: Advantage){_adjustedAdvantage.update{advantage}}

    /**
     * Set the page number of the advantage selection dialog.
     *
     * @param pageNum page to turn to
     */
    fun setAdjustingPage(pageNum: Int){_adjustingPage.update{pageNum}}

    /**
     * Set which option is selected for the advantage selection dialog.
     *
     * @param advOption option to set the selection to
     */
    fun setOptionPicked(advOption: Int?){_optionPicked.update{advOption}}

    /**
     * Clears the half-attuned tree selections taken.
     */
    fun emptyHalfAttuned(){_halfAttunedOptions.update{listOf()}}

    /**
     * Adds the indicated item to the half-attuned tree option selection list.
     *
     * @param elementSelection item to add to the selection list
     */
    fun addOptionPicked(elementSelection: Int){
        //add item if it is not currently present in the selection
        if(!halfAttunedOptions.value.contains(element = elementSelection))
            _halfAttunedOptions.update{halfAttunedOptions.value + elementSelection}

        //remove opposite element if it is currently selected
        if(elementSelection % 2 == 0){
            if(halfAttunedOptions.value.contains(element = elementSelection + 1))
                _halfAttunedOptions.update{halfAttunedOptions.value - (elementSelection + 1)}
        }
        else{
            if(halfAttunedOptions.value.contains(element = elementSelection - 1))
                _halfAttunedOptions.update{halfAttunedOptions.value - (elementSelection - 1)}
        }
    }

    /**
     * Retrieve the name of the indicated custom secondary characteristic.
     *
     * @param customIndex index in the custom characteristic list to retrieve the name of
     */
    fun getCustomName(
        customIndex: Int
    ): String{
        return charInstance.secondaryList.getAllCustoms()[customIndex].name.value
    }

    /**
     * Set cost selection for the advantage selection dialog.
     *
     * @param cost cost to set the selection to
     */
    fun setCostPicked(cost: Int){_costPicked.update{cost}}

    /**
     * Toggles the open state of the Gift removal dialog.
     */
    fun toggleGiftAlertOn(){_giftAlertOpen.update{!giftAlertOpen.value}}

    /**
     * Retrieves the Gift advantage from the character, if they have it.
     */
    fun getGift(): Advantage?{
        //for each advantage the character has
        takenAdvantages.forEach{advantage ->
            //return found gift advantage
            if(advantage.name == R.string.gift)
                return advantage
        }

        //notify of not found
        return null
    }

    /**
     * Toggles the open state of the detail alert.
     */
    fun toggleDetailAlertOn(){_detailAlertOpen.update{!detailAlertOpen.value}}

    /**
     * Sets the item to be displayed in the detail alert.
     *
     * @param advantage advantage to display
     */
    fun setDetailItem(advantage: Advantage){_detailItem.update{advantage}}

    /**
     * Determine whether the character can currently acquire or remove advantages.
     *
     * return true if changeable
     */
    fun getAdvantageChangeable(): Boolean{
        //return true for non-SBL characters or level 0 SBL characters
        return charInstance !is SblChar || charInstance.lvl.intValue == 0
    }

    /**
     * Attempts to give an advantage to the character.
     * Always used in the advantage cost selection dialog.
     *
     * @return string message regarding nature of failure, if process fails
     */
    fun acquireAdvantage(): Int?{
        //retrieve half-attuned to tree input, if needed
        val multInput =
            if(adjustedAdvantage.value!!.name == R.string.halfTreeAttuned)
                halfAttunedOptions.value.toList()
            else null

        //attempt to add advantage and get message if it fails
        val attemptAction = advantageRecord.acquireAdvantage(
            advantageBase = adjustedAdvantage.value!!,
            taken = optionPicked.value,
            takenCost = costPicked.value,
            multTaken = multInput
        )

        //update advantages taken on successful acquisition
        if(attemptAction == null) updateAdvantagesTaken()

        //return status
        return attemptAction
    }

    /**
     * Attempts to give an advantage to the character.
     * Always used outside of the advantage cost selection dialog.
     *
     * @param advantage base advantage to add
     * @param taken item selection to apply to the base advantage
     * @param takenCost cost selection to apply to the base advantage
     * @param multTaken multiple selections made for this advantage, if needed
     * @return string message regarding nature of failure, if process fails
     */
    fun acquireAdvantage(
        advantage: Advantage,
        taken: Int?,
        takenCost: Int,
        multTaken: List<Int>?
    ): Int?{
        //attempt to add advantage and get message if it fails
        val attemptAction = advantageRecord.acquireAdvantage(
            advantageBase = advantage,
            taken = taken,
            takenCost = takenCost,
            multTaken = multTaken
        )

        //update advantages taken on successful acquisition
        if(attemptAction == null) updateAdvantagesTaken()

        //return status
        return attemptAction
    }

    /**
     * Removes the indicated advantage from the character and updates the advantage list.
     *
     * @param advantage advantage to remove from the character
     */
    fun removeAdvantage(advantage: Advantage){
        advantageRecord.removeAdvantage(advantage = advantage)
        updateAdvantagesTaken()
    }

    /**
     * Updates the takenAdvantages state list to align with the character's advantage record.
     */
    private fun updateAdvantagesTaken() {
        //empty and refill the current state list
        takenAdvantages.clear()
        takenAdvantages.addAll(elements = advantageRecord.takenAdvantages)

        //update the creation points spent by the user
        _creationPoints.update{3 - advantageRecord.creationPointSpent.intValue}
    }

    //initialize the data for all of the advantage and disadvantage categories
    private val commonAdv = AdvantageButtonData(
        category = R.string.commonAdv,
        advList = advantageRecord.commonAdvantages().advantages
    )
    private val commonDisadv = AdvantageButtonData(
        category = R.string.commonDisadv,
        advList = advantageRecord.commonAdvantages().disadvantages
    )
    private val magicAdv = AdvantageButtonData(
        category = R.string.magicAdv,
        advList = advantageRecord.magicAdvantages().advantages
    )
    private val magicDisadv = AdvantageButtonData(
        category = R.string.magicDisadv,
        advList = advantageRecord.magicAdvantages().disadvantages
    )
    private val psychicAdv = AdvantageButtonData(
        category = R.string.psychicAdv,
        advList = advantageRecord.psyAdvantages().advantages
    )
    private val psychicDisadv = AdvantageButtonData(
        category = R.string.psychicDisadv,
        advList = advantageRecord.psyAdvantages().disadvantages
    )

    //gather all advantage items
    val advantageButtons =
        listOf(commonAdv, magicAdv, psychicAdv, commonDisadv, magicDisadv, psychicDisadv)

    /**
     * Data object for an advantage button.
     *
     * @param category name reference of the button
     * @param advList list of associated advantages
     */
    class AdvantageButtonData(
        val category: Int,
        val advList: List<Advantage>
    ){
        //initialize button open state flow
        private val _isOpen = MutableStateFlow(value = false)
        val isOpen = _isOpen.asStateFlow()

        /**
         * Toggles the open state flow between on and off.
         */
        fun toggleOpen() {_isOpen.update{!isOpen.value}}
    }

    init{
        updateAdvantagesTaken()
    }

    /**
     * Refreshes the page items on the fragment's reload.
     */
    fun refreshPage(){
        //update the advantage list
        updateAdvantagesTaken()
    }
}