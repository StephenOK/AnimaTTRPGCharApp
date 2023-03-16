package com.example.animabuilder.view_models

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
    private val weaponProficiencies: WeaponProficiencies
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

    fun setModuleTaken(input: List<Weapon>, into: Boolean){
        weaponProficiencies.updateModulesTaken(input, into)
        allArchetypes[input]!!.value = into
    }

    fun getUnarmed(): Weapon{return weaponProficiencies.unarmed}
    fun getArchetypes(): List<List<Weapon>>{return weaponProficiencies.allArchetypes}
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
        R.string.shortLabel,
        weaponProficiencies.shortArms.shortArms,
        true
    )

    private val axes = WeaponListData(
        R.string.axeLabel,
        weaponProficiencies.axes.axes,
        true
    )

    private val maces = WeaponListData(
        R.string.maceLabel,
        weaponProficiencies.maces.maces,
        true
    )

    private val swords = WeaponListData(
        R.string.swordLabel,
        weaponProficiencies.swords.swords,
        true
    )

    private val twoHandeds = WeaponListData(
        R.string.twoHandLabel,
        weaponProficiencies.twoHanded.twoHanded,
        true
    )

    private val poles = WeaponListData(
        R.string.poleLabel,
        weaponProficiencies.poles.poles,
        true
    )

    private val cords = WeaponListData(
        R.string.cordLabel,
        weaponProficiencies.cords.cords,
        true
    )

    private val mixed = WeaponListData(
        R.string.mixedLabel,
        weaponProficiencies.mixed.mixed,
        false
    )

    private val shields = WeaponListData(
        R.string.shieldLabel,
        weaponProficiencies.shields.shields,
        true
    )

    private val projectiles = WeaponListData(
        R.string.projectileLabel,
        weaponProficiencies.projectiles.projectiles,
        true
    )

    private val thrown = WeaponListData(
        R.string.thrownLabel,
        weaponProficiencies.thrown.thrown,
        true
    )

    val allWeapons = listOf(shorts, axes, maces, swords, twoHandeds, poles, cords, mixed, shields, projectiles, thrown)

    class WeaponListData(
        val nameRef: Int,
        val items: List<Weapon>,
        val wholeClass: Boolean
    ){
        private val _listOpen = MutableStateFlow(false)
        val listOpen = _listOpen.asStateFlow()
        fun setListOpen(input: Boolean){_listOpen.update{input}}
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