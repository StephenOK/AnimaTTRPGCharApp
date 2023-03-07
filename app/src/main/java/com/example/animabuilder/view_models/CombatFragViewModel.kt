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

class CombatFragViewModel(
    private val combat: CombatAbilities,
    primaryList: PrimaryList
): ViewModel() {
    private val _pointColor = MutableStateFlow(Color.Black)

    private val _lifeTotal = MutableStateFlow(combat.lifeMax.toString())
    private val _lifeMults = MutableStateFlow(combat.lifeMultsTaken.toString())

    val pointColor = _pointColor.asStateFlow()

    val lifeTotal = _lifeTotal.asStateFlow()
    val lifeMults = _lifeMults.asStateFlow()

    fun setPointColor(input: Color){_pointColor.update{input}}

    fun getPresence(): String{return combat.presence.toString()}
    fun getInitiativeTotal(): String{return combat.totalInitiative.toString()}
    fun getFatigue(): String{return combat.fatigue.toString()}
    fun getRegen(): String{return combat.totalRegen.toString()}

    fun getBaseLife(): String{return combat.lifeBase.toString()}
    fun getClassLife(): String{return combat.lifeClassTotal.toString()}
    fun setLifeMults(input: Int){
        combat.takeLifeMult(input)
        setLifeMults(input.toString())
        setLifeTotal(combat.lifeMax.toString())
    }
    fun setLifeMults(input: String){_lifeMults.update{input}}
    fun setLifeTotal(input: String){_lifeTotal.update{input}}

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

    val resistanceList = listOf(disRes, magRes, physRes, venRes, psyRes)

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

    val allCombatItems = listOf(attack, block, dodge, wearArmor)

    data class ResistanceData(
        val label: String,
        val modStat: String,
        val item: ResistanceItem
    )

    class CombatItemData(
        private val combat: CombatAbilities,
        val label: String,
        val item: CombatItem,
        val setPointColor: (Color) -> Unit
    ){
        private val _pointsIn = MutableStateFlow(item.inputVal.toString())
        private val _totalVal = MutableStateFlow(item.total.toString())

        val pointsIn = _pointsIn.asStateFlow()
        val totalVal = _totalVal.asStateFlow()

        fun setPointsIn(input: Int){
            item.setInputVal(input)
            setPointsIn(input.toString())

            if(combat.validAttackDodgeBlock())
                setPointColor(Color.Black)
            else
                setPointColor(Color.Red)

            setTotalVal(item.total.toString())
        }
        fun setPointsIn(input: String){_pointsIn.update{input}}
        fun setTotalVal(input: String){_totalVal.update{input}}
    }
}