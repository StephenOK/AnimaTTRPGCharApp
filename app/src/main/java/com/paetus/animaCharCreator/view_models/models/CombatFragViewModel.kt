package com.paetus.animaCharCreator.view_models.models

import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.combat.CombatAbilities
import com.paetus.animaCharCreator.character_creation.attributes.combat.CombatItem
import com.paetus.animaCharCreator.character_creation.attributes.combat.ResistanceItem
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.PrimaryList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the combat section of the character.
 * Works on variables in the corresponding combat fragment.
 *
 * @param combat character's combat abilities
 * @param primaryList character's primary characteristics that affect this section
 */
class CombatFragViewModel(
    private val combat: CombatAbilities,
    primaryList: PrimaryList
): ViewModel() {
    //initialize life multiples taken input string
    private val _lifeMults = MutableStateFlow(value = combat.lifeMultsTaken.intValue.toString())
    val lifeMults = _lifeMults.asStateFlow()

    //initialize class life display
    private val _classLife = MutableStateFlow(value = combat.lifeClassTotal.intValue)
    val classLife = _classLife.asStateFlow()

    //initialize maximum life point display
    private val _lifeTotal = MutableStateFlow(value = combat.lifeMax.intValue)
    val lifeTotal = _lifeTotal.asStateFlow()

    //initialize life point DP cost label
    private val _lifeDPLabel = MutableStateFlow(value = "")
    val lifeDPLabel = _lifeDPLabel.asStateFlow()

    //initialize text color for the combat items
    private val _pointValid = MutableStateFlow(value = true)
    val pointValid = _pointValid.asStateFlow()

    /**
     * Changes the number of life multiples the character has obtained.
     *
     * @param multBuy value to set the multiples count to
     */
    fun setLifeMults(multBuy: Int){
        combat.takeLifeMult(multTake = multBuy)
        setLifeMults(display = multBuy.toString())
        setLifeTotal(totalDisplay = combat.lifeMax.intValue)
    }

    /**
     * Changes the display of life mults taken.
     *
     * @param display new string to display
     */
    fun setLifeMults(display: String){_lifeMults.update{display}}

    /**
     * Get current value input for the character's life point multiples taken.
     */
    fun currentLifeMults(): Int{return combat.lifeMultsTaken.intValue}

    /**
     * Changes the display of life point total.
     *
     * @param totalDisplay new string to display
     */
    private fun setLifeTotal(totalDisplay: Int){_lifeTotal.update{totalDisplay}}

    /**
     * Changes the display of the life mult DP cost.
     *
     * @param dpDisplay new string to display
     */
    fun setLifeDPLabel(dpDisplay: String){_lifeDPLabel.update{dpDisplay}}

    /**
     * Defines the color of the text for the combat items.
     *
     * @param isValid color to set
     */
    private fun setPointColor(isValid: Boolean){_pointValid.update{isValid}}

    /**
     * Retrieves the character's presence to display.
     *
     * @return string of the character's presence
     */
    fun getPresence(): String{return combat.presence.intValue.toString()}

    /**
     * Retrieves the character's base life points to display.
     *
     * @return string of the character's base life points
     */
    fun getBaseLife(): String{return combat.lifeBase.intValue.toString()}

    /**
     * Retrieves the character's life points gained from class levels to display.
     *
     * @return string of the character's life points from levels
     */
    fun getClassLife(): String{return combat.lifeClassTotal.intValue.toString()}

    /**
     * Retrieves the character's DP cost for life mults.
     *
     * @return value of the DP required
     */
    fun getLifeDP(): Int{return combat.getLifeCost()}

    /**
     * Retrieves the character's initiative to display.
     *
     * @return string of the character's initiative
     */
    fun getInitiativeTotal(): String{return combat.totalInitiative.intValue.toString()}

    /**
     * Retrieves the character's fatigue to display.
     *
     * @return string of the character's fatigue
     */
    fun getFatigue(): String{return combat.fatigue.intValue.toString()}

    /**
     * Retrieves the character's regeneration to display.
     *
     * @return string of the character's regeneration
     */
    fun getRegen(): String{return combat.totalRegen.intValue.toString()}

    //initialize all resistance display items
    private val disRes = ResistanceData(
        resLabel = R.string.drLabel,
        resItem = combat.diseaseRes,
        getModStat = {primaryList.con.outputMod.intValue.toString()}
    )

    private val magRes = ResistanceData(
        resLabel = R.string.mrLabel,
        resItem = combat.magicRes,
        getModStat = {primaryList.pow.outputMod.intValue.toString()}
    )

    private val physRes = ResistanceData(
        resLabel = R.string.phrLabel,
        resItem = combat.physicalRes,
        getModStat = {primaryList.con.outputMod.intValue.toString()}
    )

    private val venRes = ResistanceData(
        resLabel = R.string.vrLabel,
        resItem = combat.venomRes,
        getModStat = {primaryList.con.outputMod.intValue.toString()}
    )

    private val psyRes = ResistanceData(
        resLabel = R.string.psrLabel,
        resItem = combat.psychicRes,
        getModStat = {primaryList.wp.outputMod.intValue.toString()}
    )

    //gather all resistance items
    val resistanceList = listOf(disRes, magRes, physRes, venRes, psyRes)

    //initialize all combat items
    private val attack = CombatItemData(
        combat = combat,
        combatItem = combat.attack,
        growthGetter = {combat.getAtkCost()},
        setPointValid = {setPointColor(isValid = it)}
    )

    private val block = CombatItemData(
        combat = combat,
        combatItem = combat.block,
        growthGetter = {combat.getBlockCost()},
        setPointValid = {setPointColor(isValid = it)}
    )

    private val dodge = CombatItemData(
        combat = combat,
        combatItem = combat.dodge,
        growthGetter = {combat.getDodgeCost()},
        setPointValid = {setPointColor(isValid = it)}
    )

    private val wearArmor = CombatItemData(
        combat = combat,
        combatItem = combat.wearArmor,
        growthGetter = {combat.getWearCost()},
        setPointValid = {}
    )

    //gather all combat item data
    val allCombatItems = listOf(attack, block, dodge, wearArmor)

    /**
     * Data regarding one of a character's resistances.
     *
     * @param resLabel name of the resistance
     * @param resItem resistance data to reference
     * @param getModStat gets the appropriate modifier value for the resistance
     */
    data class ResistanceData(
        val resLabel: Int,
        val resItem: ResistanceItem,
        val getModStat: () -> String
    )

    /**
     * Object that holds data on one of the character's combat abilities.
     *
     * @param combat section of the character to work on
     * @param combatItem data regarding this individual item
     * @param growthGetter function to run to get the stat's associated DP amount
     * @param setPointValid changes the point color in the combat fragment's view model
     */
    class CombatItemData(
        private val combat: CombatAbilities,
        val combatItem: CombatItem,
        val growthGetter: () -> Int,
        val setPointValid: (Boolean) -> Unit
    ){
        //initialize user's input value into this ability
        private val _pointsIn = MutableStateFlow(value = combatItem.inputVal.intValue.toString())
        val pointsIn = _pointsIn.asStateFlow()

        //initialize final display for this stat
        private val _totalVal = MutableStateFlow(value = combatItem.total.intValue)
        val totalVal = _totalVal.asStateFlow()

        //initialize the DP display for this stat
        private val _labelDisplay = MutableStateFlow(value = "")
        val labelDisplay = _labelDisplay.asStateFlow()

        /**
         * Sets the purchased amount for this item as indicated.
         *
         * @param pointBuy amount to set as bought
         */
        fun setPointsIn(pointBuy: Int){
            //change the bought value and display
            combatItem.setInputVal(purchase = pointBuy)
            setPointsIn(display = pointBuy.toString())

            //update valid state of user's input
            setPointValid(combat.validAttackDodgeBlock())

            //update item total display
            setTotalVal()
        }

        /**
         * Sets the display of the purchased amount to the inputted string.
         *
         * @param display value to set this to
         */
        fun setPointsIn(display: String){_pointsIn.update{display}}

        /**
         * Get current value input for the combat item's current input.
         */
        fun getCurrent(): Int{return combatItem.inputVal.intValue}

        /**
         * Sets the displayed total to the appropriate value.
         */
        fun setTotalVal() {_totalVal.update{combatItem.total.intValue}}

        /**
         * Sets the DP label to the inputted value.
         *
         * @param dpDisplay new string to display
         */
        fun setLabelDisplay(dpDisplay: String){_labelDisplay.update{dpDisplay}}
    }

    /**
     * Function to run on loading this model's page.
     */
    fun refreshPage(){
        //update life point information
        setLifeMults(display = combat.lifeMultsTaken.intValue.toString())
        _classLife.update{combat.lifeClassTotal.intValue}
        setLifeTotal(totalDisplay = combat.lifeMax.intValue)

        //update all combat item info
        allCombatItems.forEach{combatItemData ->
            combatItemData.setPointsIn(display = combatItemData.combatItem.inputVal.intValue.toString())
            combatItemData.setTotalVal()
        }
    }
}