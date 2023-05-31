package com.example.animabuilder.view_models.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.animabuilder.character_creation.attributes.class_objects.CharClass
import com.example.animabuilder.character_creation.attributes.ki_abilities.Ki
import com.example.animabuilder.character_creation.attributes.ki_abilities.KiStat
import com.example.animabuilder.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.Technique
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the character's ki data.
 * Works on variables in the corresponding ki fragment.
 *
 * @param ki character's ki ability data
 */
class KiFragmentViewModel(
    private val ki: Ki,
    private val charClass: MutableState<CharClass>
): ViewModel() {
    //initialize remaining martial knowledge display
    private val _remainingMK = MutableStateFlow(ki.martialKnowledgeRemaining.value.toString())
    val remainingMK = _remainingMK.asStateFlow()

    //initialize ki accumulation total display
    private val _kiAccTotal = MutableStateFlow(ki.totalAcc.value.toString())
    val kiAccTotal = _kiAccTotal.asStateFlow()

    //initialize ki point total display
    private val _kiPointTotal = MutableStateFlow(ki.totalKi.value.toString())
    val kiPointTotal = _kiPointTotal.asStateFlow()

    //initialize open state of technique list
    private val _techListOpen = MutableStateFlow(false)
    val techListOpen = _techListOpen.asStateFlow()

    //initialize open state of ki ability list
    private val _kiListOpen = MutableStateFlow(false)
    val kiListOpen = _kiListOpen.asStateFlow()

    //initialize open state of custom technique dialog
    private val _customTechOpen = MutableStateFlow(false)
    val customTechOpen = _customTechOpen.asStateFlow()

    private val _detailAlertOpen = MutableStateFlow(false)
    val detailAlertOpen = _detailAlertOpen.asStateFlow()

    private val _detailName = MutableStateFlow("")
    val detailName = _detailName.asStateFlow()

    private val _detailItem = MutableStateFlow<Any?>(null)
    val detailItem = _detailItem.asStateFlow()

    fun getKiPointDP(): Int{return charClass.value.kiGrowth}
    fun getKiAccDP(): Int{return charClass.value.kiAccumMult}

    fun toggleDetailAlertOn(){_detailAlertOpen.update{!detailAlertOpen.value}}

    fun setDetailItem(input: KiAbility){
        _detailName.update{input.name}
        _detailItem.update{input}
    }

    fun setDetailItem(input: Technique){
        _detailName.update{input.name}
        _detailItem.update{input}
    }

    //initialize map of all ki ability checkboxes
    val allKiAbilities = mutableMapOf<KiAbility, MutableState<Boolean>>()

    //initialize map of all technique checkboxes
    val allTechniques = mutableMapOf<Technique, MutableState<Boolean>>()

    /**
     * Retrieve the remaining martial knowledge the character possesses.
     *
     * @return numeric value of remaining martial knowledge
     */
    fun getMartialRemaining(): Int{return ki.martialKnowledgeRemaining.value}

    /**
     * Retrieves the maximum martial knowledge the character can spend.
     *
     * @return string of the maximum martial knowledge capacity
     */
    fun getMartialMax(): String{return ki.martialKnowledgeMax.value.toString()}

    /**
     * Retrieves a list of all possible ki abilities.
     *
     * @return the full list of ki abilities the character may take
     */
    fun getAllKiAbilities(): List<KiAbility>{return ki.kiRecord.allKiAbilities}

    /**
     * Retrieves the list of techniques the character currently has.
     *
     * @return list of character's techniques
     */
    fun getAllTechniques(): List<Technique>{return ki.allTechniques}

    /**
     * Retrieves the list of custom techniques the character currently has.
     *
     * @return list of character's custom techniques
     */
    fun getCustomTechniques(): List<Technique>{return ki.customTechniques}

    /**
     * Retrieves the number of first level techniques the character has.
     *
     * @return number of first level techniques taken
     */
    fun getTakenFirstSize(): Int{return ki.takenFirstTechniques.size}

    /**
     * Retrieves the number of second level techniques the character has.
     *
     * @return number of second level techniques taken
     */
    fun getTakenSecondSize(): Int{return ki.takenSecondTechniques.size}

    /**
     * Sets the remaining martial knowledge display to the character's recorded value.
     */
    private fun setRemainingMK(){_remainingMK.update{ki.martialKnowledgeRemaining.value.toString()}}

    /**
     * Sets the ki accumulation total display to the character's recorded value.
     */
    private fun setKiAccTotal() {_kiAccTotal.update{ki.totalAcc.value.toString()}}

    /**
     * Sets the ki point total display to the character's recorded value.
     */
    private fun setKiPointTotal() {_kiPointTotal.update{ki.totalKi.value.toString()}}

    /**
     * Changes the technique list's open state.
     *
     * @return if open state is openable given the character's current abilities
     */
    fun toggleTechListOpen(): Boolean{
        //return true if character has ki control ability
        if(ki.takenAbilities.contains(ki.kiRecord.kiControl)) {
            _techListOpen.update{!techListOpen.value}
            return true
        }

        //otherwise return false
        return false
    }

    /**
     * Changes the ki ability list's open state.
     */
    fun toggleKiListOpen() {_kiListOpen.update{!kiListOpen.value}}

    /**
     * Changes the custom technique dialog's open state.
     */
    fun toggleCustomTechOpen() {_customTechOpen.update{!customTechOpen.value}}

    /**
     * Attempts to grant or remove the indicated ki ability to the character.
     *
     * @param item ki ability to affect
     * @param input true if character is gaining the ability
     */
    fun setKiAbilityTaken(item: KiAbility, input: Boolean){
        //if attempting to acquire
        if(input) {
            //grant to character if prerequisites met
            if (item.prerequisites == null || ki.takenAbilities.contains(item.prerequisites))
                allKiAbilities[item]!!.value = ki.attemptAbilityAdd(item)
        }

        //if attempting to remove
        else{
            //remove the item from the character
            ki.removeAbility(item)
            allKiAbilities[item]!!.value = false
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
        allKiAbilities.forEach{
            it.value.value = ki.takenAbilities.contains(it.key)
        }

        //close the technique list if it is open and the character no longer has ki control
        if(techListOpen.value && !allKiAbilities[ki.kiRecord.kiControl]!!.value)
            toggleTechListOpen()
    }

    /**
     * Either applies ar removes the indicated technique as desired by the user.
     *
     * @param item technique to affect
     * @param input true if adding the item
     */
    fun attemptTechniqueChange(item: Technique, input: Boolean){
        //if attempting an addition
        if(input){
            //attempt to add to the character and reflect that change
            if (ki.addTechnique(item))
                allTechniques[item]!!.value = true
        }

        //if attempting a removal
        else{
            //remove the item and indicate the change
            ki.removeTechnique(item)
            allTechniques[item]!!.value = false
            updateTechTaken()
        }

        //update the martial knowledge display
        setRemainingMK()
    }

    /**
     * Add the indicated technique to the character.
     *
     * @param item technique to add
     */
    fun addTechnique(item: Technique){
        ki.addTechnique(item)
        allTechniques += Pair(item, mutableStateOf(true))
    }

    /**
     * Updates the checkboxes for techniques taken.
     */
    private fun updateTechTaken(){
        allTechniques.forEach{
            it.value.value = ki.takenTechniques.contains(it.key)
        }
    }

    //initialize all ki items for each relevant primary characteristic
    private val kiSTR = KiRowData(
        0,
        ki.strKi,
        {setKiPointTotal() },
        {setKiAccTotal() }
    )

    private val kiDEX = KiRowData(
        1,
        ki.dexKi,
        {setKiPointTotal() },
        {setKiAccTotal() }
    )

    private val kiAGI = KiRowData(
        2,
        ki.agiKi,
        {setKiPointTotal() },
        {setKiAccTotal() }
    )

    private val kiCON = KiRowData(
        3,
        ki.conKi,
        {setKiPointTotal() },
        {setKiAccTotal() }
    )

    private val kiPOW = KiRowData(
        4,
        ki.powKi,
        {setKiPointTotal() },
        {setKiAccTotal() }
    )

    private val kiWP = KiRowData(
        5,
        ki.wpKi,
        {setKiPointTotal() },
        {setKiAccTotal() }
    )

    //gather all ki point and accumulation items
    val allRowData = listOf(kiSTR, kiDEX, kiAGI, kiCON, kiPOW, kiWP)

    /**
     * Item that holds data on a primary characteristic's ki accumulation and points.
     *
     * @param title characteristic name for this item
     * @param item source of the primary ki data
     * @param setTotalPoints function to run to update ki point total
     * @param setTotalAcc function to run to update ki accumulation total
     */
    class KiRowData(
        val title: Int,
        val item: KiStat,
        val setTotalPoints: () -> Unit,
        val setTotalAcc: () -> Unit
    ){
        //initialize point input string
        private val _pointInputString = MutableStateFlow(item.boughtKiPoints.value.toString())
        val pointInputString = _pointInputString.asStateFlow()

        //initialize characteristic ki point total
        private val _pointTotalString = MutableStateFlow(item.totalKiPoints.value.toString())
        val pointTotalString = _pointTotalString.asStateFlow()

        private val _pointDPLabel = MutableStateFlow("")
        val pointDPLabel = _pointDPLabel.asStateFlow()

        //initialize accumulation input
        private val _accInputString = MutableStateFlow(item.boughtAccumulation.value.toString())
        val accInputString = _accInputString.asStateFlow()

        //initialize characteristic ki accumulation total
        private val _accTotalString = MutableStateFlow(item.totalAccumulation.value.toString())
        val accTotalString = _accTotalString.asStateFlow()

        private val _accDPLabel = MutableStateFlow("")
        val accDPLabel = _accDPLabel.asStateFlow()

        /**
         * Set the bought value for ki points to the inputted value.
         *
         * @param input value to apply to the character
         */
        fun setPointInputString(input: Int){
            item.setBoughtKiPoints(input)
            setPointInputString(input.toString())
            _pointTotalString.update{item.totalKiPoints.value.toString()}
            setTotalPoints()
        }

        /**
         * Sets the point display to the desired input.
         *
         * @param input value to display
         */
        fun setPointInputString(input: String){_pointInputString.update{input}}

        fun setPointDPLabel(input: String){_pointDPLabel.update{input}}

        /**
         * Set the bought value for ki accumulation to the inputted value.
         *
         * @param input value to apply to the character
         */
        fun setAccInputString(input: Int){
            item.setBoughtAccumulation(input)
            setAccInputString(input.toString())
            _accTotalString.update{item.totalAccumulation.value.toString()}
            setTotalAcc()
        }

        /**
         * Sets the accumulation display to the desired input.
         *
         * @param input value to display
         */
        fun setAccInputString(input: String){_accInputString.update{input}}

        fun setAccDPLabel(input: String){_accDPLabel.update{input}}

        fun refreshItem(){
            setPointInputString(item.boughtKiPoints.value.toString())
            setTotalPoints()
            setAccInputString(item.boughtAccumulation.value.toString())
            setTotalAcc()
        }
    }

    init{
        //get each ki ability and designate a checkbox to it
        ki.kiRecord.allKiAbilities.forEach{
            allKiAbilities += Pair(it, mutableStateOf(ki.takenAbilities.contains(it)))
        }

        //get each technique and designate a checkbox to it
        ki.allTechniques.forEach{
            allTechniques += Pair(it, mutableStateOf(ki.takenTechniques.contains(it)))
        }
    }

    fun refreshPage(){
        allRowData.forEach{
            it.refreshItem()
        }

        if(kiListOpen.value)
            toggleKiListOpen()

        if(techListOpen.value)
            toggleTechListOpen()
    }
}