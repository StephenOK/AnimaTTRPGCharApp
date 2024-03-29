package com.paetus.animaCharCreator.view_models.models

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.modules.MartialArt
import com.paetus.animaCharCreator.character_creation.attributes.modules.StyleModule
import com.paetus.animaCharCreator.character_creation.attributes.modules.WeaponProficiencies
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the character's module data.
 * Works on variables in the corresponding module fragment.
 *
 * @param weaponProficiencies character's module object
 */
class ModuleFragmentViewModel(
    private val weaponProficiencies: WeaponProficiencies,
    val context: Context
) : ViewModel() {
    //initialize character's current primary weapon selection
    private val _primaryWeapon = MutableStateFlow(value = weaponProficiencies.primaryWeapon.value)
    val primaryWeapon = _primaryWeapon.asStateFlow()

    //initialize open state of the archetype module menu
    private val _archetypeOpen = MutableStateFlow(value = false)
    val archetypeOpen = _archetypeOpen.asStateFlow()

    //initialize open state of the martial art menu
    private val _martialOpen = MutableStateFlow(value = false)
    val martialOpen = _martialOpen.asStateFlow()

    //initialize open state of the style module menu
    private val _styleOpen = MutableStateFlow(value = false)
    val styleOpen = _styleOpen.asStateFlow()

    //initialize open state of the detail alert
    private val _detailAlertOpen = MutableStateFlow(value = false)
    val detailAlertOpen = _detailAlertOpen.asStateFlow()

    //header of the detail alert
    private val _detailName = MutableStateFlow(value = "")
    val detailName = _detailName.asStateFlow()

    //item to be displayed in the detail alert
    private val _detailItem = MutableStateFlow<Any?>(value = null)
    val detailItem = _detailItem.asStateFlow()

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
     * @param primeWeapon weapon to set as the primary one
     */
    fun setPrimaryWeapon(primeWeapon: Weapon){
        weaponProficiencies.setPrimaryWeapon(weaponName = primeWeapon.saveName)
        _primaryWeapon.update{primeWeapon}
    }

    /**
     * Changes the open state of the archetype module list.
     */
    fun toggleArchetypeOpen() {_archetypeOpen.update{!archetypeOpen.value}}

    /**
     * Determine that the inputted weapon is gained from a character's selected archetype.
     *
     * @param weapon weapon to look for in archetypes
     */
    fun archetypesHasWeapon(weapon: Weapon): Boolean{
        //search through each archetype
        allArchetypes.forEach{(archetype, isTaken) ->
            //return true if the weapon is in the archetype and that archetype is taken
            if(archetype.contains(element = weapon) && isTaken.value)
                return true
        }

        //weapon is not taken from an archetype
        return false
    }

    /**
     * Changes the open state of the martial arts list.
     */
    fun toggleMartialOpen() {_martialOpen.update{!martialOpen.value}}

    /**
     * Changes the open state of the style module list.
     */
    fun toggleStyleOpen() {_styleOpen.update{!styleOpen.value}}

    /**
     * Toggles the open state of the detail alert.
     */
    fun toggleDetailAlertOn(){_detailAlertOpen.update{!detailAlertOpen.value}}

    /**
     * Sets the weapon to get the details of.
     *
     * @param weapon weapon to get the details of
     */
    fun setDetailItem(weapon: Weapon){
        _detailName.update{context.getString(weapon.name)}
        _detailItem.update{weapon}
    }

    /**
     * Sets the archetype module to get the details of.
     *
     * @param archName title of the archetype module
     * @param archetype list of weapons in the archetype
     */
    fun setDetailItem(archName: String, archetype: ArchetypeData){
        _detailName.update{archName}
        _detailItem.update{archetype}
    }

    /**
     * Sets the style module to get the details of.
     *
     * @param style style module to get the details of
     */
    fun setDetailItem(style: StyleModule){
        _detailName.update{context.getString(style.name)}
        _detailItem.update{style}
    }

    /**
     * Sets the martial art to get the details of.
     *
     * @param martialArt martial art to get the details of
     */
    fun setDetailItem(martialArt: MartialArt){
        _detailName.update{context.getString(martialArt.name)}
        _detailItem.update{martialArt}
    }

    /**
     * Add or remove the indicated individual weapon module.
     *
     * @param weapon weapon module to affect
     * @param isTaking true if adding the item
     */
    fun changeIndividualModule(
        weapon: Weapon,
        isTaking: Boolean
    ){
        weaponProficiencies.changeIndividualModule(weapon = weapon, toAdd = isTaking)
        allSecondaryWeapons[weapon]!!.value = weaponProficiencies.individualModules.contains(element = weapon) ||
                weaponProficiencies.fullModWeapons.contains(element = weapon)
    }

    /**
     * Attempt to change the character's ownership of the inputted martial art.
     *
     * @param martialArt item to change
     * @param isTaking true if adding the item
     */
    fun changeMartial(
        martialArt: MartialArt,
        isTaking: Boolean
    ){
        allMartials[martialArt]!!.value =
            weaponProficiencies.changeMartial(changeItem = martialArt, isAdded = isTaking)
    }

    /**
     * Attempt to change the character's ownership of the inputted style module.
     *
     * @param style style module to change
     * @param isTaking true if adding the style module
     */
    fun changeStyle(
        style: StyleModule,
        isTaking: Boolean
    ){
        weaponProficiencies.changeStyle(style = style, toAdd = isTaking)
        allStyles[style]!!.value = isTaking
    }

    /**
     * Retrieves the unarmed weapon from the record.
     *
     * @return the stats for unarmed combat
     */
    fun getUnarmed(): Weapon{return weaponProficiencies.unarmed}

    /**
     * Retrieves the character's maximum number of martial arts they can take.
     *
     * @return number of martial arts available to the user
     */
    fun getMartialMax(): Int{return weaponProficiencies.martialMax.intValue}

    /**
     * Retrieves information on all martial arts.
     *
     * @return all martial art data
     */
    fun getAllMartials(): List<MartialArt>{return weaponProficiencies.martials.allMartialArts}

    /**
     * Retrieves information on all style modules.
     *
     * @return all style module data
     */
    fun getAllStyles(): List<StyleModule>{return weaponProficiencies.styles.allStyles}

    //initialize all weapon data for each weapon category
    private val shorts = WeaponListData(
        weaponProficiencies = weaponProficiencies,
        nameRef = R.string.shortLabel,
        weaponList = weaponProficiencies.shortArms.shortArms,
        wholeClass = true,
        archetypeItems = weaponProficiencies.allArchetypes["short"]!!,
        modFragVM = this
    )

    private val axes = WeaponListData(
        weaponProficiencies = weaponProficiencies,
        nameRef = R.string.axeLabel,
        weaponList = weaponProficiencies.axes.axes,
        wholeClass = true,
        archetypeItems = weaponProficiencies.allArchetypes["axe"]!!,
        modFragVM = this
    )

    private val maces = WeaponListData(
        weaponProficiencies = weaponProficiencies,
        nameRef = R.string.maceLabel,
        weaponList = weaponProficiencies.maces.maces,
        wholeClass = true,
        archetypeItems = weaponProficiencies.allArchetypes["mace"]!!,
        modFragVM = this
    )

    private val swords = WeaponListData(
        weaponProficiencies = weaponProficiencies,
        nameRef = R.string.swordLabel,
        weaponList = weaponProficiencies.swords.swords,
        wholeClass = true,
        archetypeItems = weaponProficiencies.allArchetypes["sword"]!!,
        modFragVM = this
    )

    private val twoHandeds = WeaponListData(
        weaponProficiencies = weaponProficiencies,
        nameRef = R.string.twoHandLabel,
        weaponList = weaponProficiencies.twoHanded.twoHanded,
        wholeClass = true,
        archetypeItems = weaponProficiencies.allArchetypes["twoHanded"]!!,
        modFragVM = this
    )

    private val poles = WeaponListData(
        weaponProficiencies = weaponProficiencies,
        nameRef = R.string.poleLabel,
        weaponList = weaponProficiencies.poles.poles,
        wholeClass = true,
        archetypeItems = weaponProficiencies.allArchetypes["pole"]!!,
        modFragVM = this
    )

    private val cords = WeaponListData(
        weaponProficiencies = weaponProficiencies,
        nameRef = R.string.cordLabel,
        weaponList = weaponProficiencies.cords.cords,
        wholeClass = true,
        archetypeItems = weaponProficiencies.allArchetypes["cord"]!!,
        modFragVM = this
    )

    private val mixed = WeaponListData(
        weaponProficiencies = weaponProficiencies,
        nameRef = R.string.mixedLabel,
        weaponList = weaponProficiencies.mixed.mixed,
        wholeClass = false,
        archetypeItems = null,
        modFragVM = this
    )

    private val shields = WeaponListData(
        weaponProficiencies = weaponProficiencies,
        nameRef = R.string.shieldLabel,
        weaponList = weaponProficiencies.shields.shields,
        wholeClass = true,
        archetypeItems = weaponProficiencies.allArchetypes["shield"]!!,
        modFragVM = this
    )

    private val projectiles = WeaponListData(
        weaponProficiencies = weaponProficiencies,
        nameRef = R.string.projectileLabel,
        weaponList = weaponProficiencies.projectiles.projectiles,
        wholeClass = true,
        archetypeItems = weaponProficiencies.allArchetypes["projectile"]!!,
        modFragVM = this
    )

    private val thrown = WeaponListData(
        weaponProficiencies = weaponProficiencies,
        nameRef = R.string.thrownLabel,
        weaponList = weaponProficiencies.thrown.thrown,
        wholeClass = true,
        archetypeItems = weaponProficiencies.allArchetypes["thrown"],
        modFragVM = this
    )

    //gather all weapon data
    val allWeapons = listOf(shorts, axes, maces, swords, twoHandeds, poles, cords, mixed, shields, projectiles, thrown)

    //initialize all archetype data for each archetype module
    private val barbarianArchetype = ArchetypeData(
        weaponProficiencies = weaponProficiencies,
        weapons = weaponProficiencies.allArchetypes["barbarian"]!!,
        name = R.string.barbarianLabel,
        modFragVM = this
    )

    private val ninjaArchetype = ArchetypeData(
        weaponProficiencies = weaponProficiencies,
        weapons = weaponProficiencies.allArchetypes["ninja"]!!,
        name = R.string.ninjaLabel,
        modFragVM = this
    )

    private val duelArchetype = ArchetypeData(
        weaponProficiencies = weaponProficiencies,
        weapons = weaponProficiencies.allArchetypes["duel"]!!,
        name = R.string.duelLabel,
        modFragVM = this
    )

    private val pirateArchetype = ArchetypeData(
        weaponProficiencies = weaponProficiencies,
        weapons = weaponProficiencies.allArchetypes["pirate"]!!,
        name = R.string.pirateLabel,
        modFragVM = this
    )

    private val nomadArchetype = ArchetypeData(
        weaponProficiencies = weaponProficiencies,
        weapons = weaponProficiencies.allArchetypes["nomad"]!!,
        name = R.string.nomadLabel,
        modFragVM = this
    )

    private val hunterArchetype = ArchetypeData(
        weaponProficiencies = weaponProficiencies,
        weapons = weaponProficiencies.allArchetypes["hunter"]!!,
        name = R.string.hunterLabel,
        modFragVM = this
    )

    private val knightArchetype = ArchetypeData(
        weaponProficiencies = weaponProficiencies,
        weapons = weaponProficiencies.allArchetypes["knight"]!!,
        name = R.string.knightLabel,
        modFragVM = this
    )

    private val gladiatorArchetype = ArchetypeData(
        weaponProficiencies = weaponProficiencies,
        weapons = weaponProficiencies.allArchetypes["gladiator"]!!,
        name = R.string.gladiatorLabel,
        modFragVM = this
    )

    private val assassinArchetype = ArchetypeData(
        weaponProficiencies = weaponProficiencies,
        weapons = weaponProficiencies.allArchetypes["assassin"]!!,
        name = R.string.assassinLabel,
        modFragVM = this
    )

    private val soldierArchetype = ArchetypeData(
        weaponProficiencies = weaponProficiencies,
        weapons = weaponProficiencies.allArchetypes["soldier"]!!,
        name = R.string.soldierLabel,
        modFragVM = this
    )

    private val indigenousArchetype = ArchetypeData(
        weaponProficiencies = weaponProficiencies,
        weapons = weaponProficiencies.allArchetypes["indigenous"]!!,
        name = R.string.indigenousLabel,
        modFragVM = this
    )

    private val banditArchetype = ArchetypeData(
        weaponProficiencies = weaponProficiencies,
        weapons = weaponProficiencies.allArchetypes["bandit"]!!,
        name = R.string.banditLabel,
        modFragVM = this
    )

    private val improvisedArchetype = ArchetypeData(
        weaponProficiencies = weaponProficiencies,
        weapons = weaponProficiencies.allArchetypes["improvised"]!!,
        name = R.string.improvisedLabel,
        modFragVM = this
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
     * @param weaponList list of weapon associated with this list
     * @param wholeClass true if the module has a whole weapons archetype
     * @param modFragVM viewModel this object is housed in
     */
    class WeaponListData(
        weaponProficiencies: WeaponProficiencies,
        val nameRef: Int,
        val weaponList: List<Weapon>,
        val wholeClass: Boolean,
        archetypeItems: List<Weapon>?,
        val modFragVM: ModuleFragmentViewModel
    ){
        //initialize open state of the list
        private val _listOpen = MutableStateFlow(value = false)
        val listOpen = _listOpen.asStateFlow()

        /**
         * Changes the open state of the weapon category list.
         */
        fun toggleListOpen() {_listOpen.update{!listOpen.value}}

        //create an archetype data object for the whole class module
        val weaponArchetype = if(wholeClass) ArchetypeData(
            weaponProficiencies = weaponProficiencies,
            weapons = archetypeItems!!,
            name = nameRef,
            modFragVM = modFragVM
        )
        else null
    }

    /**
     * Data object that holds information on the inputted archetype module.
     *
     * @param weaponProficiencies character's weapon proficiencies ability
     * @param weapons list of weapons associated with this archetype
     * @param name label for this archetype
     * @param modFragVM viewModel that this data is housed in
     */
    class ArchetypeData(
        private val weaponProficiencies: WeaponProficiencies,
        val weapons: List<Weapon>,
        val name: Int,
        private val modFragVM: ModuleFragmentViewModel
    ){
        //initialize checkbox for this archetype
        private val _takenCheck = MutableStateFlow(value = weaponProficiencies.takenModules.contains(weapons))
        val takenCheck = _takenCheck.asStateFlow()

        /**
         * Toggles the taken checkbox of this archetype module.
         */
        fun toggleCheck(){
            _takenCheck.update{!takenCheck.value}
            weaponProficiencies.updateModulesTaken(weaponCheck = weapons, isAdded = takenCheck.value)

            //update individually taken weapons
            modFragVM.allSecondaryWeapons.forEach{(weapon, isTaken) ->
                isTaken.value = weaponProficiencies.fullModWeapons.contains(element = weapon)
            }
        }
    }

    init{
        //create checkboxes for each individual weapon
        weaponProficiencies.allWeapons.forEach{weapon ->
            allSecondaryWeapons += Pair(weapon, mutableStateOf(weaponProficiencies.individualModules.contains(element = weapon)))
        }

        //create checkboxes for each archetype module
        weaponProficiencies.allArchetypes.values.forEach{archetype ->
            allArchetypes += Pair(archetype, mutableStateOf(weaponProficiencies.takenModules.contains(element = archetype)))
        }

        //create checkboxes for each martial art
        weaponProficiencies.martials.allMartialArts.forEach{martialArt ->
            allMartials += Pair(martialArt, mutableStateOf(weaponProficiencies.takenMartialList.contains(element = martialArt)))
        }

        //create checkboxes for each style module
        weaponProficiencies.styles.allStyles.forEach{style ->
            allStyles += Pair(style, mutableStateOf(weaponProficiencies.styleMods.contains(element = style)))
        }
    }

    /**
     * Function to run on opening the module fragment.
     */
    fun refreshPage(){
        //close any open weapon lists
        allWeapons.forEach{weaponList ->
            if(weaponList.listOpen.value)
                weaponList.toggleListOpen()
        }

        //close the archetype section
        if(archetypeOpen.value)
            toggleArchetypeOpen()

        //close the martial art section
        if(martialOpen.value)
            toggleMartialOpen()

        //close the style module section
        if(styleOpen.value)
            toggleStyleOpen()
    }
}