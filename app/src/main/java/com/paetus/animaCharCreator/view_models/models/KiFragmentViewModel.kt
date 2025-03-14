package com.paetus.animaCharCreator.view_models.models

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.Ki
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.KiStat
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.PrebuiltTech
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.CustomTechnique
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.TechniqueBase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the character's ki data.
 * Works on variables in the corresponding ki fragment.
 *
 * @param ki character's ki ability data=
 */
class KiFragmentViewModel(
    private val ki: Ki,
    private val context: Context
): ViewModel() {
    //initialize remaining martial knowledge display
    private val _remainingMK = MutableStateFlow(value = ki.martialKnowledgeRemaining.intValue)
    val remainingMK = _remainingMK.asStateFlow()

    //initialize ki accumulation total display
    private val _kiAccTotal = MutableStateFlow(value = ki.totalAcc.intValue)
    val kiAccTotal = _kiAccTotal.asStateFlow()

    //initialize ki point total display
    private val _kiPointTotal = MutableStateFlow(value = ki.totalKi.intValue)
    val kiPointTotal = _kiPointTotal.asStateFlow()

    //initialize open state of technique list
    private val _techListOpen = MutableStateFlow(value = false)
    val techListOpen = _techListOpen.asStateFlow()

    //initialize open state of ki ability list
    private val _kiListOpen = MutableStateFlow(value = false)
    val kiListOpen = _kiListOpen.asStateFlow()

    //initialize open state of the detail alert
    private val _detailAlertOpen = MutableStateFlow(value = false)
    val detailAlertOpen = _detailAlertOpen.asStateFlow()

    //initialize name of detailed item
    private val _detailName = MutableStateFlow(value = "")
    val detailName = _detailName.asStateFlow()

    //initialize item to get the details of
    private val _detailItem = MutableStateFlow<Any?>(value = null)
    val detailItem = _detailItem.asStateFlow()

    //initialize map of all ki ability checkboxes
    val allKiAbilities = mutableMapOf<KiAbility, MutableState<Boolean>>()

    /**
     * Sets the remaining martial knowledge display to the character's recorded value.
     */
    private fun setRemainingMK(){_remainingMK.update{ki.martialKnowledgeRemaining.intValue}}

    /**
     * Sets the ki accumulation total display to the character's recorded value.
     */
    private fun setKiAccTotal(){_kiAccTotal.update{ki.totalAcc.intValue}}

    /**
     * Sets the ki point total display to the character's recorded value.
     */
    private fun setKiPointTotal() {_kiPointTotal.update{ki.totalKi.intValue}}

    /**
     * Changes the technique list's open state.
     *
     * @return if open state is openable given the character's current abilities
     */
    fun checkIfToggle(): Boolean{
        //return true if character has ki control ability or if list is already open
        return techListOpen.value || ki.takenAbilities.contains(ki.getKiRecord().kiControl)
    }

    /**
     * Toggles the open state of the technique dropdown.
     */
    fun toggleTechOpen(){_techListOpen.update{!techListOpen.value}}

    /**
     * Changes the ki ability list's open state.
     */
    fun toggleKiListOpen() {_kiListOpen.update{!kiListOpen.value}}

    /**
     * Changes the detail alert's open state.
     */
    fun toggleDetailAlertOn(){_detailAlertOpen.update{!detailAlertOpen.value}}

    /**
     * Sets a ki ability displayed in the detail alert.
     *
     * @param kiAbility ki ability to display in the details alert
     */
    fun setDetailItem(kiAbility: KiAbility){
        _detailName.update{context.getString(kiAbility.name)}
        _detailItem.update{kiAbility}
    }

    /**
     * Sets a technique displayed in the detail alert.
     *
     * @param technique technique to display in the detail alert
     */
    fun setDetailItem(technique: TechniqueBase){
        _detailName.update{
            if(technique is PrebuiltTech) context.getString(technique.name)
            else (technique as CustomTechnique).name.value
        }
        _detailItem.update{technique}
    }

    /**
     * Attempts to grant or remove the indicated ki ability to the character.
     *
     * @param kiAbility ki ability to affect
     * @param isTaken true if character is gaining the ability
     */
    fun setKiAbilityTaken(
        kiAbility: KiAbility,
        isTaken: Boolean
    ){
        //if attempting to acquire
        if(isTaken) {
            //grant to character if prerequisites met
            if (kiAbility.prerequisite == null || ki.takenAbilities.contains(element = kiAbility.prerequisite))
                allKiAbilities[kiAbility]!!.value = ki.attemptAbilityAdd(newAbility = kiAbility)
        }

        //if attempting to remove
        else{
            //remove the item from the character
            ki.removeAbility(ability = kiAbility)
            updateKiTaken()
        }

        //update martial knowledge expenditure
        setRemainingMK()
    }

    /**
     * Updates the ki abilities taken checkboxes.
     */
    private fun updateKiTaken(){
        //update each checkbox to match their taken state
        allKiAbilities.forEach{(kiAbility, isTaken) ->
            isTaken.value = ki.takenAbilities.contains(element = kiAbility)
        }

        //close the technique list if it is open and the character no longer has ki control
        if(techListOpen.value && !allKiAbilities[ki.getKiRecord().kiControl]!!.value)
            toggleTechOpen()
    }

    /**
     * Applies ar removes the indicated technique as desired by the user.
     *
     * @param technique technique to affect
     * @param isTaken true if adding the item
     */
    fun attemptTechniqueChange(
        technique: TechniqueBase,
        isTaken: Boolean
    ){
        //if attempting an addition
        if(isTaken){
            //attempt to add to the character and reflect that change
            ki.attemptTechAddition(technique = technique)
        }

        //if attempting a removal
        else{
            //remove the item and indicate the change
            ki.removeTechnique(technique = technique)
        }

        //update the martial knowledge display
        setRemainingMK()
    }

    /**
     * Add the indicated custom technique to the character.
     *
     * @param customTech technique to add
     */
    fun addTechnique(customTech: CustomTechnique){
        val copy = CustomTechnique(
            name = customTech.name.value,
            isPublic = customTech.isPublic.value,
            fileOrigin = customTech.fileOrigin.value,
            description = customTech.description.value,
            level = customTech.level.intValue,
            maintArray = customTech.maintArray,
            givenAbilities = customTech.givenAbilities
        )

        ki.attemptTechAddition(technique = copy)
    }

    /**
     * Retrieve the remaining martial knowledge the character possesses.
     *
     * @return numeric value of remaining martial knowledge
     */
    fun getMartialRemaining(): Int{return ki.martialKnowledgeRemaining.intValue}

    /**
     * Retrieves the maximum martial knowledge the character can spend.
     *
     * @return string of the maximum martial knowledge capacity
     */
    fun getMartialMax(): String{return ki.martialKnowledgeMax.intValue.toString()}

    /**
     * Retrieves the DP cost of ki points for this character.
     *
     * @return DP cost of ki points
     */
    fun getKiPointDP(): Int{return ki.getKiPointCost()}

    /**
     * Retrieves the DP cost of ki accumulation for this character.
     *
     * @return DP cost of ki accumulation
     */
    fun getKiAccDP(): Int{return ki.getKiAccumulationCost()}

    /**
     * Retrieves a list of all possible ki abilities.
     *
     * @return the full list of ki abilities the character may take
     */
    fun getAllKiAbilities(): List<KiAbility>{return ki.getKiRecord().allKiAbilities}

    /**
     * Retrieves the list of prebuilt techniques available to the character.
     *
     * @return list of available prebuilt techniques
     */
    fun getAllPrebuilts(): Map<PrebuiltTech, MutableState<Boolean>>{return ki.allPrebuilts}

    /**
     * Retrieves the list of custom techniques the character has access to.
     *
     * @return list of available custom techniques
     */
    fun getCustomTechniques(): Map<CustomTechnique, MutableState<Boolean>>{return ki.customTechniques}

    //initialize all ki items for each relevant primary characteristic
    private val kiSTR = KiRowData(
        title = 0,
        kiStat = ki.strKi,
        setTotalPoints = {setKiPointTotal()},
        setTotalAcc = {setKiAccTotal()}
    )

    private val kiDEX = KiRowData(
        title = 1,
        kiStat = ki.dexKi,
        setTotalPoints = {setKiPointTotal()},
        setTotalAcc = {setKiAccTotal()}
    )

    private val kiAGI = KiRowData(
        title = 2,
        kiStat = ki.agiKi,
        setTotalPoints = {setKiPointTotal()},
        setTotalAcc = {setKiAccTotal()}
    )

    private val kiCON = KiRowData(
        title = 3,
        kiStat = ki.conKi,
        setTotalPoints = {setKiPointTotal()},
        setTotalAcc = {setKiAccTotal()}
    )

    private val kiPOW = KiRowData(
        title = 5,
        kiStat = ki.powKi,
        setTotalPoints = {setKiPointTotal()},
        setTotalAcc = {setKiAccTotal()}
    )

    private val kiWP = KiRowData(
        title = 6,
        kiStat = ki.wpKi,
        setTotalPoints = {setKiPointTotal()},
        setTotalAcc = {setKiAccTotal()}
    )

    //gather all ki point and accumulation items
    val allRowData = listOf(kiSTR, kiDEX, kiAGI, kiCON, kiPOW, kiWP)

    /**
     * Item that holds data on a primary characteristic's ki accumulation and points.
     *
     * @param title characteristic name for this item
     * @param kiStat source of the primary ki data
     * @param setTotalPoints function to run to update ki point total
     * @param setTotalAcc function to run to update ki accumulation total
     */
    class KiRowData(
        val title: Int,
        val kiStat: KiStat,
        val setTotalPoints: () -> Unit,
        val setTotalAcc: () -> Unit
    ){
        //initialize point input string
        private val _pointInputString = MutableStateFlow(value = kiStat.boughtKiPoints.intValue.toString())
        val pointInputString = _pointInputString.asStateFlow()

        private val _pointDPLabel = MutableStateFlow(value = "")
        val pointDPLabel = _pointDPLabel.asStateFlow()

        //initialize accumulation input
        private val _accInputString = MutableStateFlow(value = kiStat.boughtAccumulation.intValue.toString())
        val accInputString = _accInputString.asStateFlow()

        private val _accDPLabel = MutableStateFlow(value = "")
        val accDPLabel = _accDPLabel.asStateFlow()

        //initialize characteristic ki point total
        private val _pointTotalString = MutableStateFlow(value = kiStat.totalKiPoints.intValue)
        val pointTotalString = _pointTotalString.asStateFlow()

        //initialize characteristic ki accumulation total
        private val _accTotalString = MutableStateFlow(value = kiStat.totalAccumulation.intValue)
        val accTotalString = _accTotalString.asStateFlow()

        /**
         * Set the bought value for ki points to the inputted value.
         *
         * @param pointBuy value to apply to the character
         */
        fun setPointInputString(pointBuy: Int){
            kiStat.setBoughtKiPoints(kiPurchase = pointBuy)
            setPointInputString(display = pointBuy.toString())
            _pointTotalString.update{kiStat.totalKiPoints.intValue}
            setTotalPoints()
        }

        /**
         * Sets the point display to the desired input.
         *
         * @param display value to display
         */
        fun setPointInputString(display: String){_pointInputString.update{display}}

        /**
         * Get current value input for the ki stat's current point input.
         */
        fun currentPoints(): Int{return kiStat.boughtKiPoints.intValue}

        /**
         * Sets the DP cost display to the indicated value.
         *
         * @param dpDisplay dp cost to show
         */
        fun setPointDPLabel(dpDisplay: String){_pointDPLabel.update{dpDisplay}}

        /**
         * Set the bought value for ki accumulation to the inputted value.
         *
         * @param accBuy value to apply to the character
         */
        fun setAccInputString(accBuy: Int){
            kiStat.setBoughtAccumulation(accPurchase = accBuy)
            setAccInputString(display = accBuy.toString())
            _accTotalString.update{kiStat.totalAccumulation.intValue}
            setTotalAcc()
        }

        /**
         * Sets the accumulation display to the desired input.
         *
         * @param display value to now show
         */
        fun setAccInputString(display: String){_accInputString.update{display}}

        /**
         * Get current value input for the ki stat's current accumulation.
         */
        fun currentAcc(): Int{return kiStat.boughtAccumulation.intValue}

        /**
         * Defines the dp label for this item's accumulation input.
         *
         * @param dpDisplay value to show for this item's dp cost
         */
        fun setAccDPLabel(dpDisplay: String){_accDPLabel.update{dpDisplay}}

        /**
         * Updates the values of this item on opening the Ki Fragment.
         */
        fun refreshItem(){
            //update the point values for this item
            setPointInputString(kiStat.boughtKiPoints.intValue.toString())
            _pointTotalString.update{kiStat.totalKiPoints.intValue}
            setTotalPoints()

            //update the accumulation value for this item
            setAccInputString(kiStat.boughtAccumulation.intValue.toString())
            _accTotalString.update{kiStat.totalAccumulation.intValue}
            setTotalAcc()
        }
    }

    init{
        //get each ki ability and designate a checkbox to it
        ki.getKiRecord().allKiAbilities.forEach{kiAbility ->
            allKiAbilities += Pair(kiAbility, mutableStateOf(value = ki.takenAbilities.contains(element = kiAbility)))
        }
        updateKiTaken()
        setRemainingMK()
    }

    /**
     * Refreshes the page on the user returning to it.
     */
    fun refreshPage(){
        //refresh each ki point and accumulation item
        allRowData.forEach{kiRowData ->
            kiRowData.refreshItem()
        }

        //update the ki ability taken checkboxes
        allKiAbilities.forEach{(ability, taken) ->
            taken.value = ki.takenAbilities.contains(ability)
        }

        //refresh the martial knowledge items
        setRemainingMK()
    }
}