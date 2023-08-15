package com.paetus.animaCharCreator.view_models.models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
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
    private val _creationPoints = MutableStateFlow(3 - advantageRecord.creationPointSpent.value)
    val creationPoints = _creationPoints.asStateFlow()

    //initialize open state flow of the advantage cost dialog
    private val _advantageCostOn = MutableStateFlow(false)
    val advantageCostOn = _advantageCostOn.asStateFlow()

    //initialize base advantage worked on
    private val _adjustedAdvantage = MutableStateFlow<Advantage?>(null)
    val adjustedAdvantage = _adjustedAdvantage.asStateFlow()

    //initialize page number of the dialog
    private val _adjustingPage = MutableStateFlow(1)
    val adjustingPage = _adjustingPage.asStateFlow()

    //initialize the option picked for the advantage
    private val _optionPicked = MutableStateFlow<Int?>(null)
    val optionPicked = _optionPicked.asStateFlow()

    //initialize selected cost item for the advantage
    private val _costPicked = MutableStateFlow(0)
    val costPicked = _costPicked.asStateFlow()

    //initialize alert open state for removing The Gift
    private val _giftAlertOpen = MutableStateFlow(false)
    val giftAlertOpen = _giftAlertOpen.asStateFlow()

    //initialize detail alert open state
    private val _detailAlertOpen = MutableStateFlow(false)
    val detailAlertOpen = _detailAlertOpen.asStateFlow()

    //initialize item in detail alert
    private val _detailItem = MutableStateFlow<Advantage?>(null)
    val detailItem = _detailItem.asStateFlow()

    //initialize list of taken advantages
    val takenAdvantages = mutableStateListOf<Advantage>()

    private val _halfAttunedOptions = MutableStateFlow(listOf<Int>())
    val halfAttunedOptions = _halfAttunedOptions.asStateFlow()

    /**
     * Change the on state of the advantage cost selection dialog.
     */
    fun toggleAdvantageCostOn() {
        _advantageCostOn.update{!advantageCostOn.value}

        //reset options on advantage cost dialog's opening
        if(advantageCostOn.value) {
            setOptionPicked(null)
            setCostPicked(0)
        }
    }

    /**
     * Sets the advantage that is having options and/or costs selected for.
     *
     * @param input advantage to select options for
     */
    fun setAdjustedAdvantage(input: Advantage){_adjustedAdvantage.update{input}}

    /**
     * Set the page number of the advantage selection dialog.
     *
     * @param input page number to turn to
     */
    fun setAdjustingPage(input: Int){_adjustingPage.update{input}}

    /**
     * Set which option is selected for the advantage selection dialog.
     *
     * @param input option to set the selection to
     */
    fun setOptionPicked(input: Int?){_optionPicked.update{input}}

    fun emptyHalfAttuned(){_halfAttunedOptions.update{listOf()}}

    fun addOptionPicked(input: Int){
        if(!halfAttunedOptions.value.contains(input))
            _halfAttunedOptions.update{halfAttunedOptions.value + input}

        if(input % 2 == 0){
            if(halfAttunedOptions.value.contains(input + 1))
                _halfAttunedOptions.update{halfAttunedOptions.value - (input + 1)}
        }
        else{
            if(halfAttunedOptions.value.contains(input - 1))
                _halfAttunedOptions.update{halfAttunedOptions.value - (input - 1)}
        }
    }

    /**
     * Set cost selection for the advantage selection dialog.
     *
     * @param input cost to set the selection to
     */
    fun setCostPicked(input: Int){_costPicked.update{input}}

    /**
     * Toggles the open state of the Gift removal dialog.
     */
    fun toggleGiftAlertOn(){_giftAlertOpen.update{!giftAlertOpen.value}}

    /**
     * Retrieves the Gift advantage from the character, if they have it.
     */
    fun getGift(): Advantage?{
        //for each advantage the character has
        takenAdvantages.forEach{
            //return found gift advantage
            if(it.name == R.string.gift)
                return it
        }

        return null
    }

    /**
     * Toggles the open state of the detail alert.
     */
    fun toggleDetailAlertOn(){_detailAlertOpen.update{!detailAlertOpen.value}}

    /**
     * Sets the item to be displayed in the detail alert.
     */
    fun setDetailItem(item: Advantage){_detailItem.update{item}}

    /**
     * Attempts to give an advantage to the character.
     * Always used in the advantage cost selection dialog.
     *
     * @return string message regarding nature of failure, if process fails
     */
    fun acquireAdvantage(): Int?{
        val multInput =
            if(adjustedAdvantage.value!!.name == R.string.halfTreeAttuned)
                halfAttunedOptions.value.toList()
            else null

        //attempt to add advantage and get message if it fails
        val attemptAction = advantageRecord.acquireAdvantage(
            adjustedAdvantage.value!!,
            optionPicked.value,
            costPicked.value,
            multInput
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
     * @param item base advantage to add
     * @param taken item selection to apply to the base advantage
     * @param takenCost cost selection to apply to the base advantage
     * @return string message regarding nature of failure, if process fails
     */
    fun acquireAdvantage(
        item: Advantage,
        taken: Int?,
        takenCost: Int,
        multTaken: List<Int>?
    ): Int?{
        //attempt to add advantage and get message if it fails
        val attemptAction =  advantageRecord.acquireAdvantage(item, taken, takenCost, multTaken)

        //update advantages taken on successful acquisition
        if(attemptAction == null) updateAdvantagesTaken()

        //return status
        return attemptAction
    }

    /**
     * Removes the indicated advantage from the character and updates the advantage list.
     *
     * @param item advantage to remove from the character
     */
    fun removeAdvantage(item: Advantage){
        advantageRecord.removeAdvantage(item)
        updateAdvantagesTaken()
    }

    /**
     * Updates the takenAdvantages state list to align with the character's advantage record.
     */
    private fun updateAdvantagesTaken() {
        //empty and refill the current state list
        takenAdvantages.clear()
        takenAdvantages.addAll(advantageRecord.takenAdvantages)

        //update the creation points spent by the user
        _creationPoints.update{3 - advantageRecord.creationPointSpent.value}
    }

    /**
     * Gets the racial advantages the character currently possess.
     *
     * @return the list of racial advantages the character possesses
     */
    fun getRacialAdvantages(): List<Advantage>{
        return charInstance.ownRace.value
    }

    //initialize the data for all of the advantage and disadvantage categories
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

    //gather all advantage items
    val advantageButtons =
        listOf(commonAdv, magicAdv, psychicAdv, commonDisadv, magicDisadv, psychicDisadv)

    /**
     * Data object for an advantage button.
     *
     * @param category name reference of the button
     * @param items list of associated advantages
     */
    class AdvantageButtonData(
        val category: Int,
        val items: List<Advantage>
    ){
        //initialize button open state flow
        private val _isOpen = MutableStateFlow(false)
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