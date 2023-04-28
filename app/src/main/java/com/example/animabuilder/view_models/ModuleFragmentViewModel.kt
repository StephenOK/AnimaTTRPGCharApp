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

/**
 * View model that manages the character's module data.
 * Works on variables in the corresponding module fragment.
 *
 * @param weaponProficiencies character's module object
 * @param context where to get string resources from
 */
class ModuleFragmentViewModel(
    private val weaponProficiencies: WeaponProficiencies,
    context: Context
) : ViewModel() {
    //initialize character's current primary weapon selection
    private val _primaryWeapon = MutableStateFlow(weaponProficiencies.primaryWeapon.value)
    val primaryWeapon = _primaryWeapon.asStateFlow()

    //initialize open state of the archetype module menu
    private val _archetypeOpen = MutableStateFlow(false)
    val archetypeOpen = _archetypeOpen.asStateFlow()

    //initialize open state of the style module menu
    private val _styleOpen = MutableStateFlow(false)
    val styleOpen = _styleOpen.asStateFlow()

    //initialize open state of the martial art menu
    private val _martialOpen = MutableStateFlow(false)
    val martialOpen = _martialOpen.asStateFlow()

    //initialize list of secondary weapon checkboxes
    val allSecondaryWeapons = mutableMapOf<Weapon, MutableState<Boolean>>()

    //initialize list of archetype checkboxes
    private val allArchetypes = mutableMapOf<List<Weapon>, MutableState<Boolean>>()

    //initialize list of martial art checkboxes
    val allMartials = mutableMapOf<MartialArt, MutableState<Boolean>>()

    //initialize list of style module checkboxes
    val allStyles = mutableMapOf<StyleModule, MutableState<Boolean>>()

    /**
     * Sets the character's primary weapon to the inputted item.
     *
     * @param input weapon to set as the primary one
     */
    fun setPrimaryWeapon(input: Weapon){
        weaponProficiencies.setPrimaryWeapon(input.name)
        _primaryWeapon.update{input}
    }

    /**
     * Determine that the inputted weapon is gained from a character's selected archetype.
     *
     * @param input weapon to look for in archetypes
     */
    fun archetypesHasWeapon(input: Weapon): Boolean{
        //search through each archetype
        allArchetypes.forEach{
            //return true if the weapon is in the archetype and that archetype is taken
            if(it.key.contains(input) && it.value.value)
                return true
        }

        //weapon is not taken from an archetype
        return false
    }

    /**
     * Changes the open state of the archetype module list.
     */
    fun toggleArchetypeOpen() {_archetypeOpen.update{!archetypeOpen.value}}

    /**
     * Changes the open state of the style module list.
     */
    fun toggleStyleOpen() {_styleOpen.update{!styleOpen.value}}

    /**
     * Changes the open state of the martial arts list.
     */
    fun toggleMartialOpen() {_martialOpen.update{!martialOpen.value}}

    /**
     * Retrieves the unarmed weapon from the record.
     *
     * @return the stats for unarmed combat
     */
    fun getUnarmed(): Weapon{return weaponProficiencies.unarmed}

    /**
     * Retrieves information on all style modules.
     *
     * @return all style module data
     */
    fun getAllStyles(): List<StyleModule>{return weaponProficiencies.styles.allStyles}

    /**
     * Retrieves the character's maximum number of martial arts they can take.
     *
     * @return number of martial arts available to the user
     */
    fun getMartialMax(): Int{return weaponProficiencies.martialMax.value}

    /**
     * Retrieves information on all martial arts.
     *
     * @return all martial art data
     */
    fun getAllMartials(): List<MartialArt>{return weaponProficiencies.martials.allMartialArts}

    /**
     * Add or remove the indicated individual weapon module.
     *
     * @param item weapon module to affect
     * @param input true if adding the item
     */
    fun changeIndividualModule(item: Weapon, input: Boolean){
        weaponProficiencies.changeIndividualModule(item, input)
        allSecondaryWeapons[item]!!.value = input
    }

    /**
     * Attempt to change the character's ownership of the inputted martial art.
     *
     * @param martialArt item to change
     * @param input true if adding the item
     */
    fun changeMartial(martialArt: MartialArt, input: Boolean){
        allMartials[martialArt]!!.value = weaponProficiencies.changeMartial(martialArt, input)
    }

    /**
     * Attempt to change the character's ownership of the inputted style module.
     *
     * @param item style module to change
     * @param input true if adding the style module
     */
    fun changeStyle(item: StyleModule, input: Boolean){
        weaponProficiencies.changeStyle(item, input)
        allStyles[item]!!.value = input
    }

    //initialize all weapon data for each weapon category
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

    //gather all weapon data
    val allWeapons = listOf(shorts, axes, maces, swords, twoHandeds, poles, cords, mixed, shields, projectiles, thrown)

    //initialize all archetype data for each archetype module
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

    //gather all archetype data
    val allArchetypeData = listOf(barbarianArchetype, ninjaArchetype, duelArchetype, pirateArchetype,
        nomadArchetype, hunterArchetype, knightArchetype, gladiatorArchetype, assassinArchetype,
        soldierArchetype, indigenousArchetype, banditArchetype, improvisedArchetype)

    /**
     * Data object that holds information on the inputted weapon type.
     *
     * @param weaponProficiencies character's weapon proficiencies ability
     * @param nameRef string name of the weapon category
     * @param items list of weapon associated with this list
     * @param wholeClass true if the module has a whole weapons archetype
     * @param context context to get the resources from
     */
    class WeaponListData(
        weaponProficiencies: WeaponProficiencies,
        val nameRef: Int,
        val items: List<Weapon>,
        val wholeClass: Boolean,
        context: Context
    ){
        //initialize open state of the list
        private val _listOpen = MutableStateFlow(false)
        val listOpen = _listOpen.asStateFlow()

        /**
         * Changes the open state of the weapon category list.
         */
        fun toggleListOpen() {_listOpen.update{!listOpen.value}}

        //create an archetype data object for the whole class module
        val weaponArchetype = ArchetypeData(
            weaponProficiencies,
            items,
            context.resources.getString(nameRef) + context.resources.getString(R.string.moduleSuffix)
        )
    }

    /**
     * Data object that holds information on the inputted archetype module.
     *
     * @param weaponProficiencies character's weapon proficiencies ability
     * @param items list of weapons associated with this archetype
     * @param name label for this archetype
     */
    class ArchetypeData(
        private val weaponProficiencies: WeaponProficiencies,
        val items: List<Weapon>,
        val name: String
    ){
        //initialize checkbox for this archetype
        private val _takenCheck = MutableStateFlow(weaponProficiencies.takenModules.contains(items))
        val takenCheck = _takenCheck.asStateFlow()

        /**
         * Toggles the taken checkbox of this archetype module.
         */
        fun toggleCheck(){
            _takenCheck.update{!takenCheck.value}
            weaponProficiencies.updateModulesTaken(items, takenCheck.value)
        }
    }

    init{
        //create checkboxes for each individual weapon
        weaponProficiencies.allWeapons.forEach{
            allSecondaryWeapons += Pair(it, mutableStateOf(weaponProficiencies.individualModules.contains(it)))
        }

        //create checkboxes for each archetype module
        weaponProficiencies.allArchetypes.forEach{
            allArchetypes += Pair(it, mutableStateOf(weaponProficiencies.takenModules.contains(it)))
        }

        //create checkboxes for each martial art
        weaponProficiencies.martials.allMartialArts.forEach{
            allMartials += Pair(it, mutableStateOf(weaponProficiencies.takenMartialList.contains(it)))
        }

        //create checkboxes for each style module
        weaponProficiencies.styles.allStyles.forEach{
            allStyles += Pair(it, mutableStateOf(weaponProficiencies.styleMods.contains(it)))
        }
    }
}