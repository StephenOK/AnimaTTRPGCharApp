package com.example.animabuilder.view_models

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.attributes.modules.MartialArt
import com.example.animabuilder.character_creation.attributes.modules.StyleModule
import com.example.animabuilder.character_creation.attributes.modules.WeaponProficiencies
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.Weapon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ModuleFragmentViewModel(
    private val weaponProficiencies: WeaponProficiencies,
    context: Context
) : ViewModel() {
    private val _primaryWeapon = MutableStateFlow(weaponProficiencies.primaryWeapon)

    private val _archetypeOpen = MutableStateFlow(false)
    private val _styleOpen = MutableStateFlow(false)
    private val _martialOpen = MutableStateFlow(false)

    val primaryWeapon = _primaryWeapon.asStateFlow()

    val allSecondaryWeapons = mutableMapOf<Weapon, MutableState<Boolean>>()
    val allArchetypes = mutableMapOf<List<Weapon>, MutableState<Boolean>>()
    val allMartials = mutableMapOf<MartialArt, MutableState<Boolean>>()
    val allStyles = mutableMapOf<StyleModule, MutableState<Boolean>>()

    val archetypeOpen = _archetypeOpen.asStateFlow()
    val styleOpen = _styleOpen.asStateFlow()
    val martialOpen = _martialOpen.asStateFlow()

    fun setPrimaryWeapon(input: Weapon){
        weaponProficiencies.setPrimaryWeapon(input.name)
        _primaryWeapon.update{input}
    }

    fun archetypesHasWeapon(input: Weapon): Boolean{
        allArchetypes.forEach{
            if(it.key.contains(input) && it.value.value)
                return true
        }

        return false
    }

    fun setArchetypeOpen(input: Boolean){_archetypeOpen.update{input}}
    fun setStyleOpen(input: Boolean){_styleOpen.update{input}}
    fun setMartialOpen(input: Boolean){_martialOpen.update{input}}

    fun getUnarmed(): Weapon{return weaponProficiencies.unarmed}
    fun getAllStyles(): List<StyleModule>{return weaponProficiencies.styles.allStyles}
    fun getMartialMax(): Int{return weaponProficiencies.martialMax}
    fun getAllMartials(): List<MartialArt>{return weaponProficiencies.martials.allMartialArts}

    fun changeIndividualModule(item: Weapon, input: Boolean){
        weaponProficiencies.changeIndividualModule(item, input)
        allSecondaryWeapons[item]!!.value = input
    }
    fun changeMartial(martialArt: MartialArt, input: Boolean){
        allMartials[martialArt]!!.value = weaponProficiencies.changeMartial(martialArt, input)
    }
    fun changeStyle(item: StyleModule, input: Boolean){
        weaponProficiencies.changeStyle(item, input)
        allStyles[item]!!.value = input
    }

    private val shorts = WeaponListData(
        weaponProficiencies,
        R.string.shortLabel,
        weaponProficiencies.shortArms.shortArms,
        true,
        context
    )

    private val axes = WeaponListData(
        weaponProficiencies,
        R.string.axeLabel,
        weaponProficiencies.axes.axes,
        true,
        context
    )

    private val maces = WeaponListData(
        weaponProficiencies,
        R.string.maceLabel,
        weaponProficiencies.maces.maces,
        true,
        context
    )

    private val swords = WeaponListData(
        weaponProficiencies,
        R.string.swordLabel,
        weaponProficiencies.swords.swords,
        true,
        context
    )

    private val twoHandeds = WeaponListData(
        weaponProficiencies,
        R.string.twoHandLabel,
        weaponProficiencies.twoHanded.twoHanded,
        true,
        context
    )

    private val poles = WeaponListData(
        weaponProficiencies,
        R.string.poleLabel,
        weaponProficiencies.poles.poles,
        true,
        context
    )

    private val cords = WeaponListData(
        weaponProficiencies,
        R.string.cordLabel,
        weaponProficiencies.cords.cords,
        true,
        context
    )

    private val mixed = WeaponListData(
        weaponProficiencies,
        R.string.mixedLabel,
        weaponProficiencies.mixed.mixed,
        false,
        context
    )

    private val shields = WeaponListData(
        weaponProficiencies,
        R.string.shieldLabel,
        weaponProficiencies.shields.shields,
        true,
        context
    )

    private val projectiles = WeaponListData(
        weaponProficiencies,
        R.string.projectileLabel,
        weaponProficiencies.projectiles.projectiles,
        true,
        context
    )

    private val thrown = WeaponListData(
        weaponProficiencies,
        R.string.thrownLabel,
        weaponProficiencies.thrown.thrown,
        true,
        context
    )

    val allWeapons = listOf(shorts, axes, maces, swords, twoHandeds, poles, cords, mixed, shields, projectiles, thrown)

    private val barbarianArchetype = ArchetypeData(
        weaponProficiencies,
        weaponProficiencies.allArchetypes[0],
        context.resources.getString(R.string.barbarianLabel)
    )

    private val ninjaArchetype = ArchetypeData(
        weaponProficiencies,
        weaponProficiencies.allArchetypes[1],
        context.resources.getString(R.string.ninjaLabel)
    )

    private val duelArchetype = ArchetypeData(
        weaponProficiencies,
        weaponProficiencies.allArchetypes[2],
        context.resources.getString(R.string.duelLabel)
    )

    private val pirateArchetype = ArchetypeData(
        weaponProficiencies,
        weaponProficiencies.allArchetypes[3],
        context.resources.getString(R.string.pirateLabel)
    )

    private val nomadArchetype = ArchetypeData(
        weaponProficiencies,
        weaponProficiencies.allArchetypes[4],
        context.resources.getString(R.string.nomadLabel)
    )

    private val hunterArchetype = ArchetypeData(
        weaponProficiencies,
        weaponProficiencies.allArchetypes[5],
        context.resources.getString(R.string.hunterLabel)
    )

    private val knightArchetype = ArchetypeData(
        weaponProficiencies,
        weaponProficiencies.allArchetypes[6],
        context.resources.getString(R.string.knightLabel)
    )

    private val gladiatorArchetype = ArchetypeData(
        weaponProficiencies,
        weaponProficiencies.allArchetypes[7],
        context.resources.getString(R.string.gladiatorLabel)
    )

    private val assassinArchetype = ArchetypeData(
        weaponProficiencies,
        weaponProficiencies.allArchetypes[8],
        context.resources.getString(R.string.assassinLabel)
    )

    private val soldierArchetype = ArchetypeData(
        weaponProficiencies,
        weaponProficiencies.allArchetypes[9],
        context.resources.getString(R.string.soldierLabel)
    )

    private val indigenousArchetype = ArchetypeData(
        weaponProficiencies,
        weaponProficiencies.allArchetypes[10],
        context.resources.getString(R.string.indigenousLabel)
    )

    private val banditArchetype = ArchetypeData(
        weaponProficiencies,
        weaponProficiencies.allArchetypes[11],
        context.resources.getString(R.string.banditLabel)
    )

    private val improvisedArchetype = ArchetypeData(
        weaponProficiencies,
        weaponProficiencies.allArchetypes[12],
        context.resources.getString(R.string.improvisedLabel)
    )

    val allArchetypeData = listOf(barbarianArchetype, ninjaArchetype, duelArchetype, pirateArchetype,
        nomadArchetype, hunterArchetype, knightArchetype, gladiatorArchetype, assassinArchetype,
        soldierArchetype, indigenousArchetype, banditArchetype, improvisedArchetype)

    class WeaponListData(
        weaponProficiencies: WeaponProficiencies,
        val nameRef: Int,
        val items: List<Weapon>,
        val wholeClass: Boolean,
        context: Context
    ){
        private val _listOpen = MutableStateFlow(false)
        val listOpen = _listOpen.asStateFlow()
        fun toggleListOpen() {_listOpen.update{!listOpen.value}}

        val weaponArchetype = ArchetypeData(
            weaponProficiencies,
            items,
            context.resources.getString(nameRef) + context.resources.getString(R.string.moduleSuffix)
        )
    }

    class ArchetypeData(
        val weaponProficiencies: WeaponProficiencies,
        val items: List<Weapon>,
        val name: String
    ){
        private val _takenCheck = MutableStateFlow(weaponProficiencies.takenModules.contains(items))
        val takenCheck = _takenCheck.asStateFlow()
        fun toggleCheck(){
            _takenCheck.update{!takenCheck.value}
            weaponProficiencies.updateModulesTaken(items, takenCheck.value)
        }
    }

    init{
        weaponProficiencies.allWeapons.forEach{
            allSecondaryWeapons += Pair(it, mutableStateOf(weaponProficiencies.individualModules.contains(it)))
        }

        weaponProficiencies.allArchetypes.forEach{
            allArchetypes += Pair(it, mutableStateOf(weaponProficiencies.takenModules.contains(it)))
        }

        weaponProficiencies.martials.allMartialArts.forEach{
            allMartials += Pair(it, mutableStateOf(weaponProficiencies.takenMartialList.contains(it)))
        }

        weaponProficiencies.styles.allStyles.forEach{
            allStyles += Pair(it, mutableStateOf(weaponProficiencies.styleMods.contains(it)))
        }
    }
}