package com.example.animabuilder.view_models.models

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.attributes.class_objects.CharClass
import com.example.animabuilder.character_creation.attributes.combat.CombatAbilities
import com.example.animabuilder.character_creation.attributes.combat.CombatItem
import com.example.animabuilder.character_creation.attributes.combat.ResistanceItem
import com.example.animabuilder.character_creation.attributes.primary_abilities.PrimaryList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the combat section of the character.
 * Works on variables in the corresponding combat fragment.
 *
 * @param combat character's combat abilities
 * @param primaryList character's primary characteristics that affect this section
 * @param charClass character's character object for DP displays
 */
class CombatFragViewModel(
    private val combat: CombatAbilities,
    primaryList: PrimaryList,
    private val charClass: MutableState<CharClass>
): ViewModel() {
    //initialize life multiples taken input string
    private val _lifeMults = MutableStateFlow(combat.lifeMultsTaken.value.toString())
    val lifeMults = _lifeMults.asStateFlow()

    //initialize maximum life point display
    private val _lifeTotal = MutableStateFlow(combat.lifeMax.value.toString())
    val lifeTotal = _lifeTotal.asStateFlow()

    private val _lifeDPLabel = MutableStateFlow("")
    val lifeDPLabel = _lifeDPLabel.asStateFlow()

    //initialize text color for the combat items
    private val _pointColor = MutableStateFlow(Color.Black)
    val pointColor = _pointColor.asStateFlow()

    /**
     * Changes the number of life multiples the character has obtained.
     *
     * @param input value to set the multiples count to
     */
    fun setLifeMults(input: Int){
        combat.takeLifeMult(input)
        setLifeMults(input.toString())
        setLifeTotal(combat.lifeMax.value.toString())
    }

    /**
     * Changes the display of life mults taken.
     *
     * @param input new string to display
     */
    fun setLifeMults(input: String){_lifeMults.update{input}}

    /**
     * Changes the display of life point total.
     *
     * @param input new string to display
     */
    private fun setLifeTotal(input: String){_lifeTotal.update{input}}

    /**
     * Changes the display of the life mult DP cost.
     *
     * @param input new string to display
     */
    fun setLifeDPLabel(input: String){_lifeDPLabel.update{input}}

    /**
     * Defines the color of the text for the combat items.
     *
     * @param input color to set
     */
    private fun setPointColor(input: Color){_pointColor.update{input}}

    /**
     * Retrieves the character's presence to display.
     *
     * @return string of the character's presence
     */
    fun getPresence(): String{return combat.presence.value.toString()}

    /**
     * Retrieves the character's base life points to display.
     *
     * @return string of the character's base life points
     */
    fun getBaseLife(): String{return combat.lifeBase.value.toString()}

    /**
     * Retrieves the character's life points gained from class levels to display.
     *
     * @return string of the character's life points from levels
     */
    fun getClassLife(): String{return combat.lifeClassTotal.value.toString()}

    /**
     * Retrieves the character's DP cost for life mults.
     *
     * @return value of the DP required
     */
    fun getLifeDP(): Int{return charClass.value.lifePointMultiple}

    /**
     * Retrieves the character's initiative to display.
     *
     * @return string of the character's initiative
     */
    fun getInitiativeTotal(): String{return combat.totalInitiative.value.toString()}

    /**
     * Retrieves the character's fatigue to display.
     *
     * @return string of the character's fatigue
     */
    fun getFatigue(): String{return combat.fatigue.value.toString()}

    /**
     * Retrieves the character's regeneration to display.
     *
     * @return string of the character's regeneration
     */
    fun getRegen(): String{return combat.totalRegen.value.toString()}

    //initialize all resistance display items
    private val disRes = ResistanceData(
        R.string.drLabel,
        combat.diseaseRes
    ){primaryList.con.outputMod.value.toString()}

    private val magRes = ResistanceData(
        R.string.mrLabel,
        combat.magicRes
    ){primaryList.pow.outputMod.value.toString()}

    private val physRes = ResistanceData(
        R.string.phrLabel,
        combat.physicalRes
    ){primaryList.con.outputMod.value.toString()}

    private val venRes = ResistanceData(
        R.string.vrLabel,
        combat.venomRes
    ){primaryList.con.outputMod.value.toString()}

    private val psyRes = ResistanceData(
        R.string.psrLabel,
        combat.psychicRes
    ){primaryList.wp.outputMod.value.toString()}

    //gather all resistance items
    val resistanceList = listOf(disRes, magRes, physRes, venRes, psyRes)

    //initialize all combat items
    private val attack = CombatItemData(
        combat,
        R.string.attackLabel,
        combat.attack,
        pointColor.value,
        {charClass.value.atkGrowth}
    ) { setPointColor(it) }

    private val block = CombatItemData(
        combat,
        R.string.blockLabel,
        combat.block,
        pointColor.value,
        {charClass.value.blockGrowth}
    ) { setPointColor(it) }

    private val dodge = CombatItemData(
        combat,
        R.string.dodgeLabel,
        combat.dodge,
        pointColor.value,
        {charClass.value.dodgeGrowth}
    ) { setPointColor(it) }

    private val wearArmor = CombatItemData(
        combat,
        R.string.wearLabel,
        combat.wearArmor,
        Color.Black,
        {charClass.value.armorGrowth}
    ) {}

    //gather all combat item data
    val allCombatItems = listOf(attack, block, dodge, wearArmor)

    /**
     * Data regarding one of a character's resistances.
     *
     * @param label name of the resistance
     * @param item resistance data to reference
     * @param getModStat gets the appropriate modifier value for the resistance
     */
    data class ResistanceData(
        val label: Int,
        val item: ResistanceItem,
        val getModStat: () -> String
    )

    /**
     * Object that holds data on one of the character's combat abilities.
     *
     * @param combat section of the character to work on
     * @param label name of the item in question
     * @param item data regarding this individual item
     * @param color color the string will be depending on the stat qualification
     * @param growthGetter function to run to get the stat's associated DP amount
     * @param setPointColor changes the point color in the combat fragment's view model
     */
    class CombatItemData(
        private val combat: CombatAbilities,
        val label: Int,
        val item: CombatItem,
        val color: Color,
        val growthGetter: () -> Int,
        val setPointColor: (Color) -> Unit
    ){
        //initialize user's input value into this ability
        private val _pointsIn = MutableStateFlow(item.inputVal.value.toString())
        val pointsIn = _pointsIn.asStateFlow()

        //initialize final display for this stat
        private val _totalVal = MutableStateFlow(item.total.value.toString())
        val totalVal = _totalVal.asStateFlow()

        //initialize the DP display for this stat
        private val _labelDisplay = MutableStateFlow("")
        val labelDisplay = _labelDisplay.asStateFlow()

        /**
         * Sets the purchased amount for this item as indicated.
         *
         * @param input amount to set as bought
         */
        fun setPointsIn(input: Int){
            //change the bought value and display
            item.setInputVal(input)
            setPointsIn(input.toString())

            //display valid purchase values as black text
            if(combat.validAttackDodgeBlock())
                setPointColor(Color.Black)

            //display invalid purchase values as red text
            else
                setPointColor(Color.Red)

            //update item total display
            setTotalVal()
        }

        /**
         * Sets the display of the purchased amount to the inputted string.
         *
         * @param input value to set this to
         */
        fun setPointsIn(input: String){_pointsIn.update{input}}

        /**
         * Sets the displayed total to the appropriate value.
         */
        fun setTotalVal() {_totalVal.update{item.total.value.toString()}}

        /**
         * Sets the DP label to the inputted value.
         *
         * @param input new string to display
         */
        fun setLabelDisplay(input: String){_labelDisplay.update{input}}
    }

    /**
     * Function to run on loading this model's page.
     */
    fun refreshPage(){
        setLifeMults(combat.lifeMultsTaken.value.toString())
        setLifeTotal(combat.lifeMax.value.toString())
        allCombatItems.forEach{
            it.setPointsIn(it.item.inputVal.value.toString())
            it.setTotalVal()
        }
    }
}