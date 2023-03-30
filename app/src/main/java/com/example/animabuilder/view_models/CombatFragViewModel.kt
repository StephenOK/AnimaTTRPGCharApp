package com.example.animabuilder.view_models

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
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
 */
class CombatFragViewModel(
    private val combat: CombatAbilities,
    primaryList: PrimaryList
): ViewModel() {
    //initialize text color for the combat items
    private val _pointColor = MutableStateFlow(Color.Black)
    val pointColor = _pointColor.asStateFlow()

    //initialize life multiples taken input string
    private val _lifeMults = MutableStateFlow(combat.lifeMultsTaken.toString())
    val lifeMults = _lifeMults.asStateFlow()

    //initialize maximum life point display
    private val _lifeTotal = MutableStateFlow(combat.lifeMax.toString())
    val lifeTotal = _lifeTotal.asStateFlow()

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
    fun getPresence(): String{return combat.presence.toString()}

    /**
     * Retrieves the character's initiative to display.
     *
     * @return string of the character's initiative
     */
    fun getInitiativeTotal(): String{return combat.totalInitiative.toString()}

    /**
     * Retrieves the character's fatigue to display.
     *
     * @return string of the character's fatigue
     */
    fun getFatigue(): String{return combat.fatigue.toString()}

    /**
     * Retrieves the character's regeneration to display.
     *
     * @return string of the character's regeneration
     */
    fun getRegen(): String{return combat.totalRegen.toString()}

    /**
     * Retrieves the character's base life points to display.
     *
     * @return string of the character's base life points
     */
    fun getBaseLife(): String{return combat.lifeBase.toString()}

    /**
     * Retrieves the character's life points gained from class levels to display.
     *
     * @return string of the character's life points from levels
     */
    fun getClassLife(): String{return combat.lifeClassTotal.toString()}

    /**
     * Changes the number of life multiples the character has obtained.
     *
     * @param input value to set the multiples count to
     */
    fun setLifeMults(input: Int){
        combat.takeLifeMult(input)
        setLifeMults(input.toString())
        setLifeTotal(combat.lifeMax.toString())
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

    //initialize all resistance display items
    private val disRes = ResistanceData(
        "DR",
        primaryList.con.outputMod.toString(),
        combat.diseaseRes
    )

    private val magRes = ResistanceData(
        "MR",
        primaryList.pow.outputMod.toString(),
        combat.magicRes
    )

    private val physRes = ResistanceData(
        "PhR",
        primaryList.con.outputMod.toString(),
        combat.physicalRes
    )

    private val venRes = ResistanceData(
        "VR",
        primaryList.con.outputMod.toString(),
        combat.venomRes
    )

    private val psyRes = ResistanceData(
        "PsR",
        primaryList.wp.outputMod.toString(),
        combat.psychicRes
    )

    //gather all resistance items
    val resistanceList = listOf(disRes, magRes, physRes, venRes, psyRes)

    //initialize all combat items
    private val attack = CombatItemData(
        combat,
        "Attack",
        combat.attack
    ) { setPointColor(it) }

    private val block = CombatItemData(
        combat,
        "Block",
        combat.block
    ) { setPointColor(it) }

    private val dodge = CombatItemData(
        combat,
        "Dodge",
        combat.dodge
    ) { setPointColor(it) }

    private val wearArmor = CombatItemData(
        combat,
        "Wear Armor",
        combat.wearArmor
    ) { setPointColor(it) }

    //gather all combat item data
    val allCombatItems = listOf(attack, block, dodge, wearArmor)

    /**
     * Data regarding one of a character's resistances.
     *
     * @param label name of the resistance
     * @param modStat modifier to add to the resistance
     * @param item resistance data to reference
     */
    data class ResistanceData(
        val label: String,
        val modStat: String,
        val item: ResistanceItem
    )

    /**
     * Object that holds data on one of the character's combat abilities.
     *
     * @param combat section of the character to work on
     * @param label name of the item in question
     * @param item data regarding this individual item
     * @param setPointColor changes the point color in the combat fragment's view model
     */
    class CombatItemData(
        private val combat: CombatAbilities,
        val label: String,
        val item: CombatItem,
        val setPointColor: (Color) -> Unit
    ){
        //initialize user's input value into this ability
        private val _pointsIn = MutableStateFlow(item.inputVal.toString())
        val pointsIn = _pointsIn.asStateFlow()

        //initialize final display for this stat
        private val _totalVal = MutableStateFlow(item.total.toString())
        val totalVal = _totalVal.asStateFlow()

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
            setTotalVal(item.total.toString())
        }

        /**
         * Sets the display of the purchased amount to the inputted string.
         *
         * @param input value to set this to
         */
        fun setPointsIn(input: String){_pointsIn.update{input}}
        private fun setTotalVal(input: String){_totalVal.update{input}}
    }
}