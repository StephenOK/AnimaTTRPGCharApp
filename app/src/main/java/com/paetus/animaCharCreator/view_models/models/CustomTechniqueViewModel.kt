package com.paetus.animaCharCreator.view_models.models

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.Ki
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueTableData
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.CustomTechnique
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the custom technique creation dialog.
 * Holds the custom technique and associated data as it is being created.
 *
 * @param ki character's ki segment that is affected by this viewModel
 * @param context context in which the dialog is being held
 */
class CustomTechniqueViewModel(
    private val ki: Ki,
    val context: Context
): ViewModel(){
    //initialize the newly created technique
    private val customTechnique = CustomTechnique()

    //initialize open state of custom technique dialog
    private val _customTechOpen = MutableStateFlow(value = false)
    val customTechOpen = _customTechOpen.asStateFlow()

    //create element lists for the relevant effects
    private val elementAttackList = listOf(Element.Fire, Element.Water, Element.Air, Element.Earth)
    private val elementBindList = elementAttackList + listOf(Element.Light, Element.Dark)

    //data table of technique effects
    private val techniqueDatabase = ki.techniqueDatabase

    //initialize the page tracker of the custom technique
    private val _customPageNum = MutableStateFlow(value = 1)
    val customPageNum = _customPageNum.asStateFlow()

    //initialize custom technique's level indicator
    private val _customTechLevel = MutableStateFlow(value = 1)
    val customTechLevel = _customTechLevel.asStateFlow()

    //initialize minimum and maximum costs of the custom technique
    private val _costMinimum = MutableStateFlow(value = 20)
    val costMinimum = _costMinimum.asStateFlow()
    private val _costMaximum = MutableStateFlow(value = 50)
    val costMaximum = _costMaximum.asStateFlow()

    //initialize whether the character is picking their primary effect
    private val _pickingPrimary = MutableStateFlow(value = false)
    private val pickingPrimary = _pickingPrimary.asStateFlow()

    //initialize list displayed in effect selection dropdown
    private val _listSource = MutableStateFlow(value = context.resources.getStringArray(R.array.techniqueAbilities))
    val listSource = _listSource.asStateFlow()

    //initialize selected item in the dropdown
    private val _techniqueIndex = MutableStateFlow(value = 0)
    val techniqueIndex = _techniqueIndex.asStateFlow()

    //initialize the string in the dropdown
    private val _dropdownTitle = MutableStateFlow(value = context.resources.getStringArray(R.array.techniqueAbilities)[0])
    val dropdownTitle = _dropdownTitle.asStateFlow()

    //initialize open state of the effect selection dropdown
    private val _dropdownOpen = MutableStateFlow(value = false)
    val dropdownOpen = _dropdownOpen.asStateFlow()

    //initialize logo displayed on the dropdown
    private val _dropdownIcon = MutableStateFlow(value = Icons.Filled.KeyboardArrowDown)
    val dropdownIcon = _dropdownIcon.asStateFlow()

    //initialize the size for the dropdowns in this dialog
    private val _size = MutableStateFlow(value = Size.Zero)
    val size = _size.asStateFlow()

    //initialize list of build additions for the selected technique effect
    private val _buildArray = MutableStateFlow<List<Int?>>(value = listOf())
    private val buildArray = _buildArray.asStateFlow()

    //initialize the effect's associated element list
    private val _elementList = MutableStateFlow<List<Element>>(value = listOf())
    val elementList = _elementList.asStateFlow()

    //initialize title for the effects table
    private val _header = MutableStateFlow<String?>(value = null)
    val header = _header.asStateFlow()

    //initialize title for the first optional effects table
    private val _optHeader1 = MutableStateFlow<String?>(value = null)
    val optHeader1 = _optHeader1.asStateFlow()

    //initialize title for the second optional effects table
    private val _optHeader2 = MutableStateFlow<String?>(value = null)
    val optHeader2 = _optHeader2.asStateFlow()

    //initialize table of data for the technique effect
    private val _useTable = MutableStateFlow<List<TechniqueTableData>?>(value = null)
    val useTable = _useTable.asStateFlow()

    //initialize the first optional table for the technique effect
    private val _optTable1 = MutableStateFlow<List<TechniqueTableData>?>(value = null)
    val optTable1 = _optTable1.asStateFlow()

    //initialize the second optional table for the technique effect
    private val _optTable2 = MutableStateFlow<List<TechniqueTableData>?>(value = null)
    val optTable2 = _optTable2.asStateFlow()

    //initialize list of element options for a technique effect
    private val _optElement = MutableStateFlow<List<Element>?>(value = null)
    val optElement = _optElement.asStateFlow()

    //initialize checklist for the technique effect selection
    val mainChecklist = mutableMapOf<TechniqueTableData, MutableState<Boolean>>()

    //initialize checklist for the first optional selection
    val opt1Checklist = mutableMapOf<TechniqueTableData, MutableState<Boolean>>()

    //initialize checklist for the second optional selection
    val opt2Checklist = mutableMapOf<TechniqueTableData, MutableState<Boolean>>()

    //initialize checklist for element selection
    val elementChecklist = mutableMapOf<Element, MutableState<Boolean>>()

    //initialize cost display for
    private val _predeterminedCost = MutableStateFlow(value = "20")
    val predeterminedCost = _predeterminedCost.asStateFlow()

    //initialize checklist for deleting technique effects
    val deletionChecklist = mutableMapOf<TechniqueEffect, MutableState<Boolean>>()

    //initialize list of ki builds for the technique's effects
    val editBuildList = mutableListOf<BuildPackage>()

    //initialize user's selection for technique maintenance
    private val _maintenanceSelection = MutableStateFlow(value = false)
    val maintenanceSelection = _maintenanceSelection.asStateFlow()

    //initialize the name of the technique
    private val _techniqueName = MutableStateFlow(value = customTechnique.name.value)
    val techniqueName = _techniqueName.asStateFlow()

    //initialize the description of the technique
    private val _techniqueDesc = MutableStateFlow(value = customTechnique.description.value)
    val techniqueDesc = _techniqueDesc.asStateFlow()

    //initialize public state of the created technique
    private val _isPublic = MutableStateFlow(value = customTechnique.isPublic.value)
    val isPublic = _isPublic.asStateFlow()

    /**
     * Changes the custom technique dialog's open state.
     */
    fun toggleCustomTechOpen() {
        _customTechOpen.update{!customTechOpen.value}
        if(!customTechOpen.value)
            resetDialog()
    }

    /**
     * Sets the page number of the custom technique dialog.
     *
     * @param pageNum page number to set the effect to
     */
    fun setCustomPageNum(pageNum: Int){_customPageNum.update{pageNum}}

    /**
     * Sets the minimum cost required for the technique created.
     *
     * @param minValue minimum value to set
     */
    private fun setCostMinimum(minValue: Int){_costMinimum.update{minValue}}

    /**
     * Sets the maximum cost required for the technique created.
     *
     * @param maxValue maximum value to set
     */
    private fun setCostMaximum(maxValue: Int){_costMaximum.update{maxValue}}

    /**
     * Sets the technique's level to the indicated value.
     *
     * @param level level to set the technique to
     */
    fun setTechniqueLevel(level: Int){
        //set the technique's level
        _customTechLevel.update{level}
        customTechnique.setLevel(lvlInput = level)

        //update the minimum and maximum values as needed
        when(level){
            1 -> {
                setCostMinimum(minValue = 20)
                setCostMaximum(maxValue = 50)
            }
            2 -> {
                setCostMinimum(minValue = 40)
                setCostMaximum(maxValue = 100)
            }
            3 -> {
                setCostMinimum(minValue = 60)
                setCostMaximum(maxValue = 200)
            }
            else -> {
                setCostMinimum(minValue = 0)
                setCostMaximum(maxValue = 0)
            }
        }
    }

    /**
     * Determines if the minimum cost requirement has been met.
     *
     * @return true if condition met
     */
    fun costMinMet(minimum: Int): Boolean{return minimum >= costMinimum.value}

    /**
     * Determines that the level of technique to make is a valid addition for the character.
     *
     * @return true if this level of technique is valid
     */
    fun minTechsMet(): Boolean{
        when(customTechnique.level.intValue){
            //always approve level 1 tech
            1 -> return true
            //approve if two techniques of one level lower found
            2 -> return ki.getLevelCount(level = 1) >= 2
            3 -> return ki.getLevelCount(level = 2) >= 2
        }

        //notify of illegal technique
        return false
    }

    /**
     * Notifies the viewModel of whether the user is selecting the primary effect or not.
     *
     * @param isPrimary true if selecting technique's primary effect
     */
    fun setPickingPrimary(isPrimary: Boolean){
        //update the primary effect flag
        _pickingPrimary.update{isPrimary}

        //update the displayed techniques for the dropdown
        if(isPrimary) {
            _listSource.update {context.resources.getStringArray(R.array.techniqueAbilities)}
            customTechnique.givenAbilities.clear()
        }
        else
            _listSource.update{
                context.resources.getStringArray(R.array.techniqueAbilities) +
                        context.resources.getStringArray(R.array.techniqueDisadvantages)
            }
    }

    /**
     * Sets the currently selected effect option in the dropdown.
     *
     * @param dropdownIndex index of the dropdown to set
     */
    fun setTechniqueIndex(dropdownIndex: Int){
        //update to the currently selected effect
        _techniqueIndex.update{dropdownIndex}
        _dropdownTitle.update{listSource.value[techniqueIndex.value]}

        //clear previous table display
        tableClear()

        //set the table display to the appropriate items
        when(dropdownIndex){
            //Attack Ability
            1 -> {
                setHeader(headName = context.getString(R.string.attackBonusHeader))
                setUseTable(tableInput = techniqueDatabase.table1)
                setBuildArray(newBuild = listOf(2, 0, 2, null, 2, 3))
                setElementList(elements = listOf(Element.Fire, Element.Air, Element.Dark))
            }

            //Counterattack Ability
            2 -> {
                setHeader(headName = context.getString(R.string.attackBonusHeader))
                setUseTable(tableInput = techniqueDatabase.table2)
                setBuildArray(newBuild = listOf(2, 0, 2, null, 2, 3))
                setElementList(elements = listOf(Element.Water, Element.Air, Element.Earth))
            }

            //Block Ability
            3 -> {
                setHeader(headName = context.getString(R.string.blockBonusHeader))
                setUseTable(tableInput = techniqueDatabase.table3)
                setBuildArray(newBuild = listOf(2, 0, 2, null, 2, 3))
                setElementList(elements = listOf(Element.Water, Element.Earth, Element.Light))
            }

            //Limited Block Ability
            4 -> {
                setHeader(headName = context.getString(R.string.blockBonusHeader))
                setUseTable(tableInput = techniqueDatabase.table4)
                setBuildArray(newBuild = listOf(2, 0, 2, null, 2, 3))
                setElementList(elements = listOf(Element.Water, Element.Earth, Element.Light))
            }

            //Dodge Ability
            5 -> {
                setHeader(headName = context.getString(R.string.dodgeBonusHeader))
                setUseTable(tableInput = techniqueDatabase.table5)
                setBuildArray(newBuild = listOf(null, 2, 0, 2, 2, 3))
                setElementList(elements = listOf(Element.Water, Element.Air, Element.Light))
            }

            //Limited Dodge Ability
            6 -> {
                setHeader(headName = context.getString(R.string.dodgeBonusHeader))
                setUseTable(tableInput = techniqueDatabase.table6)
                setBuildArray(newBuild = listOf(null, 2, 0, 2, 2, 3))
                setElementList(elements = listOf(Element.Air, Element.Light, Element.Dark))
            }

            //Damage Multiplier
            7 -> {
                setHeader(headName = context.getString(R.string.multLabel))
                setUseTable(tableInput = techniqueDatabase.table7)
                setBuildArray(newBuild = listOf(0, 3, null, 2, 1, 1))
                setElementList(elements = listOf(Element.Fire, Element.Earth))
            }

            //Damage Augmentation
            8 -> {
                setHeader(headName = context.getString(R.string.damageBonusHeader))
                setUseTable(tableInput = techniqueDatabase.table8)
                setOptHeader1(headName = optionalHeaderComposer(R.string.sacrificeHeader))
                setOptTable1(tableInput = techniqueDatabase.table8a)
                setBuildArray(newBuild = listOf(0, 3, null, 1, 2, 1))
                setElementList(elements = listOf(Element.Fire, Element.Earth))
            }

            //Additional Attack
            9 -> {
                setHeader(headName = context.getString(R.string.attacksHeader))
                setUseTable(tableInput = techniqueDatabase.table9)
                setOptHeader1(headName = optionalHeaderComposer(R.string.continuousAttackHeader))
                setOptTable1(tableInput = techniqueDatabase.table9a)
                setOptHeader2(headName = optionalHeaderComposer(R.string.fatigueBonusHeader))
                setOptTable2(tableInput = techniqueDatabase.table9b)
                setBuildArray(newBuild = listOf(null, 0, 2, 1, 3, 3))
                setElementList(elements = listOf(Element.Water, Element.Air))
            }

            //Limited Additional Attack
            10 -> {
                setHeader(headName = context.getString(R.string.attacksHeader))
                setUseTable(tableInput = techniqueDatabase.table10)
                setOptHeader1(headName = optionalHeaderComposer(R.string.continuousAttackHeader))
                setOptTable1(tableInput = techniqueDatabase.table10a)
                setBuildArray(newBuild = listOf(null, 0, 2, 1, 3, 3))
                setElementList(elements = listOf(Element.Water, Element.Air, Element.Dark))
            }

            //Additional Defense
            11 -> {
                setHeader(headName = context.getString(R.string.defensesHeader))
                setUseTable(tableInput = techniqueDatabase.table11)
                setOptHeader1(headName = optionalHeaderComposer(R.string.fatigueBonusHeader))
                setOptTable1(tableInput = techniqueDatabase.table11a)
                setBuildArray(newBuild = listOf(null, 1, 0, 1, 3, 3))
                setElementList(elements = listOf(Element.Light))
            }

            //Additional Action
            12 -> {
                setHeader(headName = context.getString(R.string.actionsHeader))
                setUseTable(tableInput = techniqueDatabase.table12)
                setOptHeader1(headName = optionalHeaderComposer(R.string.fatigueBonusHeader))
                setOptTable1(tableInput = techniqueDatabase.table12a)
                setBuildArray(newBuild = listOf(null, 0, 1, 1, 3, 3))
                setElementList(elements = listOf(Element.Air))
            }

            //Initiative Augmentation
            13 -> {
                setHeader(headName = context.getString(R.string.initiativeBonusHeader))
                setUseTable(tableInput = techniqueDatabase.table13)
                setBuildArray(newBuild = listOf(null, 1, 0, 2, 3, 3))
                setElementList(elements = listOf(Element.Air))
            }

            //States
            14 -> {
                setHeader(headName = context.getString(R.string.phrHeader))
                setUseTable(tableInput = techniqueDatabase.table14)
                setOptHeader1(headName = optionalHeaderComposer(R.string.addedStateHeader))
                setOptTable1(tableInput = techniqueDatabase.table14a)
                setBuildArray(newBuild = listOf(4, 4, null, 4, 0, 1))
                setElementList(elements = listOf(Element.Light, Element.Dark))
            }

            //Combat Maneuvers and Aiming
            15 -> {
                setHeader(headName = context.getString(R.string.precisionHeader))
                setUseTable(tableInput = techniqueDatabase.table15)
                setBuildArray(newBuild = listOf(null, 0, 1, 2, 2, 2))
                setElementList(elements = listOf(Element.Air))
            }

            //Armor Increase
            16 -> {
                setHeader(headName = context.getString(R.string.atHeader))
                setUseTable(tableInput = techniqueDatabase.table16)
                setBuildArray(newBuild = listOf(2, null, 3, 0, 1, 2))
                setElementList(elements = listOf(Element.Water, Element.Earth, Element.Light))
            }

            //Armor Destruction
            17 -> {
                setHeader(headName = context.getString(R.string.reductionHeader))
                setUseTable(tableInput = techniqueDatabase.table17)
                setBuildArray(newBuild = listOf(0, 2, null, 2, 1, 2))
                setElementList(elements = listOf(Element.Fire, Element.Dark))
            }

            //Breakage Augmentation
            18 -> {
                setHeader(headName = context.getString(R.string.breakageHeader))
                setUseTable(tableInput = techniqueDatabase.table18)
                setBuildArray(newBuild = listOf(0, 4, null, 2, 2, 1))
                setElementList(elements = listOf(Element.Fire, Element.Earth))
            }

            //Fortitude Augmentation
            19 -> {
                setHeader(headName = context.getString(R.string.fortitudeHeader))
                setUseTable(tableInput = techniqueDatabase.table19)
                setBuildArray(newBuild = listOf(0, 4, null, 2, 2, 1))
                setElementList(elements = listOf(Element.Fire, Element.Earth))
            }

            //Long-Distance Attack
            20 -> {
                setHeader(headName = context.getString(R.string.distanceHeader))
                setUseTable(tableInput = techniqueDatabase.table20)
                setBuildArray(newBuild = listOf(null, 2, 3, 4, 0, 1))
                setElementList(elements = listOf(Element.Fire, Element.Water, Element.Air))
            }

            //Area Attack
            21 -> {
                setHeader(headName = context.getString(R.string.radiusHeader))
                setUseTable(tableInput = techniqueDatabase.table21)
                setOptHeader1(headName = optionalHeaderComposer(R.string.targetChoiceHeader))
                setOptTable1(tableInput = techniqueDatabase.table21a)
                setBuildArray(newBuild = listOf(null, 2, 3, 3, 0, 1))
                setElementList(elements = listOf(Element.Fire, Element.Light, Element.Dark))
            }

            //Automatic Transportation
            22 -> {
                setHeader(headName = context.getString(R.string.distanceHeader))
                setUseTable(tableInput = techniqueDatabase.table22)
                setBuildArray(newBuild = listOf(2, 2, 0, 2, 3, null))
                setElementList(elements = listOf(Element.Air, Element.Light, Element.Dark))
            }

            //Critical Enhancement
            23 -> {
                setHeader(headName = context.getString(R.string.criticalHeader))
                setUseTable(tableInput = techniqueDatabase.table23)
                setOptHeader1(headName = optionalHeaderComposer(R.string.autoCritHeader))
                setOptTable1(tableInput = techniqueDatabase.table23a)
                setBuildArray(newBuild = listOf(1, 2, null, 2, 0, 1))
                setElementList(elements = listOf(Element.Fire, Element.Earth))
            }

            //Physical Ki Weapons
            24 -> {
                setHeader(headName = context.getString(R.string.qualityHeader))
                setUseTable(tableInput = techniqueDatabase.table24)
                setOptHeader1(headName = optionalHeaderComposer(R.string.projectileLabel))
                setOptTable1(tableInput = techniqueDatabase.table24a)
                setBuildArray(newBuild = listOf(2, 3, null, 1, 0, 1))
                setElementList(elements = listOf(Element.Earth, Element.Light, Element.Dark))
            }

            //Trapping
            25 -> {
                setHeader(headName = context.getString(R.string.trapHeader))
                setUseTable(tableInput = techniqueDatabase.table25)
                setBuildArray(newBuild = listOf(0, 1, null, 2, 2, 2))
                setElementList(elements = listOf(Element.Earth))
            }

            //Projection
            26 -> {
                setHeader(headName = context.getString(R.string.projectionHeader))
                setUseTable(tableInput = techniqueDatabase.table26)
                setBuildArray(newBuild = listOf(0, 3, null, 2, 1, 1))
                setElementList(elements = listOf(Element.Fire, Element.Earth))
            }

            //Energy Shield
            27 -> {
                setHeader(headName = context.getString(R.string.lpHeader))
                setUseTable(tableInput = techniqueDatabase.table27)
                setBuildArray(newBuild = listOf(2, 3, null, 2, 0, 1))
                setElementList(elements = listOf(Element.Water, Element.Light))
            }

            //Intangibility
            28 -> {
                setHeader(headName = context.getString(R.string.effectHeader))
                setUseTable(tableInput = techniqueDatabase.table28)
                setBuildArray(newBuild = listOf(3, 3, null, 3, 0, 1))
                setElementList(elements = listOf(Element.Water, Element.Light, Element.Dark))
            }

            //Mirage
            29 -> {
                setHeader(headName = context.getString(R.string.mirageHeader))
                setUseTable(tableInput = techniqueDatabase.table29)
                setOptHeader1(headName = optionalHeaderComposer(R.string.nonDetectionHeader))
                setOptTable1(tableInput = techniqueDatabase.table29a)
                setBuildArray(newBuild = listOf(null, 3, 2, 3, 1, 0))
                setElementList(elements = listOf(Element.Water, Element.Dark))
            }

            //Attack Mirroring
            30 -> {
                setHeader(headName = context.getString(R.string.effectHeader))
                setUseTable(tableInput = techniqueDatabase.table30)
                setOptHeader1(headName = optionalHeaderComposer(R.string.targetChoiceHeader))
                setOptTable1(tableInput = techniqueDatabase.table30a)
                setOptHeader2(headName = optionalHeaderComposer(R.string.mirrorEsotericHeader))
                setOptTable2(tableInput = techniqueDatabase.table30b)
                setBuildArray(newBuild = listOf(2, 3, 3, null, 0, 1))
                setElementList(elements = listOf(Element.Water, Element.Light, Element.Dark))
            }

            //Energy Damaging Attack
            31 -> {
                setHeader(headName = context.getString(R.string.attackLabel))
                setUseTable(tableInput = techniqueDatabase.table31)
                setBuildArray(newBuild = listOf(3, 3, null, 2, 0, 1))
                setElementList(elements = listOf(Element.Fire, Element.Light, Element.Dark))
            }

            //Elemental Attack
            32 -> {
                setHeader(headName = context.getString(R.string.attackLabel))
                setUseTable(tableInput = techniqueDatabase.table32)
                setOptHeader1(headName = context.getString(R.string.selectElementLabel))
                setOptElement(elementTable = elementAttackList)
                setBuildArray(newBuild = listOf(3, 3, null, 2, 0, 1))
                setElementList(elements = listOf())
            }

            //Supernatural Attack
            33 -> {
                setHeader(headName = context.getString(R.string.attackLabel))
                setUseTable(tableInput = techniqueDatabase.table33)
                setBuildArray(newBuild = listOf(3, 3, null, 2, 0, 1))
                setElementList(elements = listOf(Element.Light, Element.Dark))
            }

            //Damage Resistance
            34 -> {
                setHeader(headName = context.getString(R.string.lpHeader))
                setUseTable(tableInput = techniqueDatabase.table34)
                setBuildArray(newBuild = listOf(3, 3, null, 0, 3, 1))
                setElementList(elements = listOf(Element.Earth))
            }

            //ElementalBinding
            35 -> {
                setUseTable(tableInput = techniqueDatabase.table35)
                setOptHeader1(headName = context.getString(R.string.selectElementsLabel))
                setOptElement(elementTable = elementBindList)
                setBuildArray(newBuild = listOf(null, null, null, null, null, null))
                setElementList(elements = listOf())
            }

            //Reduce Damage
            36 -> {
                setUseTable(tableInput = techniqueDatabase.table36)
                setBuildArray(newBuild = listOf(null, null, null, null, null, null))
                setElementList(elements = listOf(Element.Free))
            }

            //Special Requirements
            37 -> {
                setUseTable(tableInput = techniqueDatabase.table37)
                setBuildArray(newBuild = listOf(null, null, null, null, null, null))
                setElementList(elements = listOf(Element.Free))
            }

            //Predetermination
            38 -> {
                setUseTable(tableInput = techniqueDatabase.table38)
                setBuildArray(newBuild = listOf(null, null, null, null, null, null))
                setElementList(elements = listOf(Element.Free))
            }

            else -> {}
        }

        //clear all checklists
        mainChecklist.clear()
        opt1Checklist.clear()
        opt2Checklist.clear()
        elementChecklist.clear()

        //fill checklists with new data
        if(useTable.value != null){
            useTable.value!!.forEach{tableData ->
                mainChecklist += Pair(tableData, mutableStateOf(false))
            }
        }

        if(optTable1.value != null){
            optTable1.value!!.forEach{tableDat ->
                opt1Checklist += Pair(tableDat, mutableStateOf(false))
            }
        }

        if(optTable2.value != null){
            optTable2.value!!.forEach{tableData ->
                opt2Checklist += Pair(tableData, mutableStateOf(false))
            }
        }

        if(optElement.value != null){
            optElement.value!!.forEach{element ->
                elementChecklist += Pair(element, mutableStateOf(false))
            }
        }
    }

    /**
     * Toggles the open state of the effect dropdown.
     */
    fun toggleDropdownOpen() {
        //change the dropdown state
        _dropdownOpen.update{!dropdownOpen.value}

        //appropriately change the icon state
        _dropdownIcon.update{
            if(dropdownOpen.value) Icons.Filled.KeyboardArrowUp
            else Icons.Filled.KeyboardArrowDown
        }
    }

    /**
     * Constructs a header for an optional effect table.
     *
     * @param address resource location of the header addition
     */
    private fun optionalHeaderComposer(address: Int): String{
        return context.getString(R.string.optionalHeader, context.getString(address))
    }

    /**
     * Sets the dropdown item's size.
     *
     * @param newSize size to set the dropdown to
     */
    fun setSize(newSize: Size){_size.update{newSize}}

    /**
     * Sets the build addition list for the technique effect.
     *
     * @param newBuild list of additions to set the state to
     */
    private fun setBuildArray(newBuild: List<Int?>){_buildArray.update{newBuild}}

    /**
     * Sets the display of the effect's elements.
     *
     * @param elements element list to set the state to
     */
    private fun setElementList(elements: List<Element>){_elementList.update{elements}}

    /**
     * Sets the header of the effect table.
     *
     * @param headName header to set
     */
    private fun setHeader(headName: String?){_header.update{headName}}

    /**
     * Sets the first optional header of the effect table.
     *
     * @param headName header to set
     */
    private fun setOptHeader1(headName: String?){_optHeader1.update{headName}}

    /**
     * Sets the second optional header of the effect table.
     *
     * @param headName header to set
     */
    private fun setOptHeader2(headName: String?){_optHeader2.update{headName}}

    /**
     * Sets the information for the effect table.
     *
     * @param tableInput table data to set
     */
    private fun setUseTable(tableInput: List<TechniqueTableData>?){_useTable.update{tableInput}}

    /**
     * Sets the information for the first optional table.
     *
     * @param tableInput table data to set
     */
    private fun setOptTable1(tableInput: List<TechniqueTableData>?){_optTable1.update{tableInput}}

    /**
     * Sets the information for the second optional table.
     *
     * @param tableInput table data to set
     */
    private fun setOptTable2(tableInput: List<TechniqueTableData>?){_optTable2.update{tableInput}}

    /**
     * Sets the information for the element options table.
     *
     * @param elementTable table data to set
     */
    private fun setOptElement(elementTable: List<Element>?){ _optElement.update{elementTable}}

    /**
     * Empties all tables and headers of data.
     */
    private fun tableClear(){
        //empty header items
        setHeader(headName = null)
        setOptHeader1(headName = null)
        setOptHeader2(headName = null)

        //empty table items
        setUseTable(tableInput = null)
        setOptTable1(tableInput = null)
        setOptTable2(tableInput = null)
        setOptElement(elementTable = null)
    }

    /**
     * Attempts to add the selected effect to the custom technique.
     *
     * @return error message if effect addition failed
     */
    fun attemptTechAddition(): Int?{
        //retrieve the effect the user has selected
        val addedTechniques = getSelectedEffects()

        //initialize additional cost of new additions
        var additionCost = 0

        //for each valid technique selection
        addedTechniques.forEach{newEffect ->
            //attempt to add the effect
            val newInput =
                customTechnique.validEffectAddition(
                    effect = newEffect,
                    charMax = ki.martialKnowledgeRemaining.intValue - additionCost
                )

            //add cost if no error
            if(newInput == null){
                additionCost += newEffect.data.mkCost
            }

            //return error if failed
            else
                return newInput
        }

        //for each valid technique selection
        addedTechniques.forEach{ effect ->
            //retrieve the technique's element
            effect.elements = getSelectedElement(effect = effect)

            //add disadvantage flag if adding elemental binding
            if(techniqueIndex.value == 35) effect.elements += Element.Free

            //add effects to technique
            customTechnique.givenAbilities.add(element = effect)
        }

        //reset dropdown and terminate method
        setTechniqueIndex(dropdownIndex = 0)
        return null
    }

    /**
     * Retrieve the technique effects the user has selected.
     *
     * @return list of effects selected by the user
     */
    fun getSelectedEffects(): List<TechniqueEffect>{
        //initialize output list
        val output = mutableListOf<TechniqueEffect>()

        //check the main table for a valid technique input
        mainChecklist.forEach{(effectData, isTaken) ->
            if(isTaken.value){
                output.add(element = dataToEffect(tableData = effectData))
                return@forEach
            }
        }

        //check the first optional table for a valid technique input
        opt1Checklist.forEach{(effectData, isTaken) ->
            if(isTaken.value){
                output.add(element = dataToEffect(tableData = effectData))
                return@forEach
            }
        }

        //check the second optional table for a valid technique input
        opt2Checklist.forEach{(effectData, isTaken) ->
            if(isTaken.value){
                output.add(element = dataToEffect(tableData = effectData))
                return@forEach
            }
        }

        //check for legal state technique addition
        if(techniqueIndex.value == 14){
            //merge two options into one technique
            return if(output.size == 2)
                listOf(
                    TechniqueEffect(
                        data = TechniqueTableData(
                            name = 14,
                            effectRef = output[1].data.effectRef,
                            effectVal = output[0].data.effectVal,
                            primaryCost = output[0].data.primaryCost + output[1].data.primaryCost,
                            secondaryCost = output[0].data.secondaryCost + output[1].data.secondaryCost,
                            mkCost = output[0].data.mkCost + output[1].data.mkCost,
                            maintCost = output[0].data.maintCost,
                            level = if(output[0].data.level > output[1].data.level) output[0].data.level else output[1].data.level
                        ),
                        kiBuild = mutableListOf(
                            output[0].kiBuild[0] + output[1].kiBuild[0],
                            output[0].kiBuild[1] + output[1].kiBuild[1],
                            output[0].kiBuild[2] + output[1].kiBuild[2],
                            output[0].kiBuild[3] + output[1].kiBuild[3],
                            output[0].kiBuild[4] + output[1].kiBuild[4],
                            output[0].kiBuild[5] + output[1].kiBuild[5]
                        ),
                        buildAdditions = output[0].buildAdditions,
                        elements = output[0].elements
                    )
                )

                //return no technique if incomplete state technique
                else
                    listOf()
        }

        if(techniqueIndex.value == 37 && output.isNotEmpty()){
            return listOf(
                TechniqueEffect(
                    data = TechniqueTableData(
                        name = output[0].data.name,
                        effectRef = output[0].data.effectRef,
                        effectVal = null,
                        primaryCost = output[0].data.primaryCost,
                        secondaryCost = output[0].data.secondaryCost,
                        mkCost = predeterminedCost.value.toInt() * -1,
                        maintCost = output[0].data.maintCost,
                        level = output[0].data.level
                    ),
                    kiBuild = output[0].kiBuild,
                    buildAdditions = output[0].buildAdditions,
                    elements = output[0].elements
                )
            )
        }

        //return list of selections
        return output.toList()
    }

    /**
     * Determine if the selected effect is Determined Condition.
     *
     * @return true if selected effect is determined condition
     */
    fun getDeterminedConditions(): Boolean{
        return techniqueIndex.value == 37 &&
                getSelectedEffects().isNotEmpty() &&
                getSelectedEffects()[0].data.effectRef == R.string.determinedCondition
    }

    /**
     * Retrieve the selection for the effect's element.
     *
     * @param effect currently selected technique effect
     * @return applied element list for this effect
     */
    fun getSelectedElement(effect: TechniqueEffect): MutableList<Element>{
        //return own elements if they have them
        if(effect.elements.isNotEmpty())
            return effect.elements

        //initialize user selected output
        val output = mutableListOf<Element>()

        //get user's selected elements
        elementChecklist.forEach{(element, isSelected) ->
            if(isSelected.value && !output.contains(element))
                output.add(element)
        }

        //return user's selection
        return output
    }

    /**
     * Retrieve the price of the user's additions.
     *
     * @return cost of the selected effects
     */
    fun getSelectionPrice(): Int{
        //initialize cost total
        var output = 0

        //add the price of each selected effect
        getSelectedEffects().forEach{effect ->
            output += effect.data.mkCost
        }

        //return final cost
        return output
    }

    /**
     * Determines that the effect data can be added to the custom technique.
     *
     * @param effectInput effect data to attempt to add to the character
     * @return error message if any failure occurs
     */
    fun validCheckInput(
        effectInput: TechniqueTableData
    ): Int?{
        return customTechnique.validEffectAddition(dataToEffect(effectInput), ki.martialKnowledgeRemaining.intValue)
    }

    /**
     * Converts a technique table data item into a technique effect.
     *
     * @param tableData technique table data to convert
     * @return effect to return from the table data
     */
    private fun dataToEffect(tableData: TechniqueTableData): TechniqueEffect {
        //initialize the build for the technique
        val defaultBuild = mutableListOf(0, 0, 0, 0, 0, 0)

        //set the default value of the build array
        if(buildArray.value.indexOf(element = 0) >= 0){
            if(pickingPrimary.value)
                defaultBuild[buildArray.value.indexOf(element = 0)] = tableData.primaryCost
            else
                defaultBuild[buildArray.value.indexOf(element = 0)] = tableData.secondaryCost
        }

        return TechniqueEffect(
            data = tableData,
            kiBuild = defaultBuild,
            buildAdditions = buildArray.value,
            elements = elementList.value.toMutableList()
        )
    }

    /**
     * Clears the inputted selection map.
     *
     * @param selections map of checkboxes to be cleared
     */
    fun clearInputList(selections: MutableMap<TechniqueTableData, MutableState<Boolean>>){
        selections.values.forEach{checkbox -> checkbox.value = false}
    }

    /**
     * Clears the checkboxes for the element selection.
     */
    fun clearElementChecks(){
        elementChecklist.values.forEach{checkbox ->
            checkbox.value = false
        }
    }

    /**
     * Sets the displayed value for the predetermination cost with an integer check.
     *
     * @param cost integer to convert to string
     */
    fun setPredeterminedCost(cost: Int){setPredeterminedCost(display = cost.toString())}

    /**
     * Sets the displayed value for the predetermination cost.
     *
     * @param display new item to display
     */
    fun setPredeterminedCost(display: String){_predeterminedCost.update{display}}

    /**
     * Retrieves the total cost of ki build needed for one index.
     *
     * @param primeIndex array index to sum up
     */
    fun gatherIndex(primeIndex: Int): Int{
        //initialize the counter
        var output = 0
        customTechnique.givenAbilities.forEach{effect ->
            output += effect.kiBuild[primeIndex]
        }

        //return the final count
        return output
    }

    /**
     * Sets the checklist items for effect deletion.
     */
    fun setDeletionChecklist(){
        //clear any previous list items
        deletionChecklist.clear()

        //add all currently held effects to the list
        customTechnique.givenAbilities.forEach{effect ->
            deletionChecklist += Pair(effect, mutableStateOf(value = false))
        }
    }

    /**
     * Removes the desired technique effects from the custom technique.
     */
    fun deleteEffects(){
        //remove the effects from the custom technique
        deletionChecklist.forEach{(effect, isTaken) ->
            if(isTaken.value){
                customTechnique.givenAbilities.remove(
                    element = customTechnique.getAbility(
                        name = effect.data.name,
                        primeCost = effect.data.primaryCost,
                        secondCost = effect.data.secondaryCost,
                        mkCost = effect.data.mkCost
                    )
                )
            }
        }

        //change the primary effect if change is necessary
        customTechnique.fixPrimaryAbility()

        //set to primary effect selection page if no more effects are in the technique
        if(customTechnique.givenAbilities.isNotEmpty())
            setCustomPageNum(pageNum = 3)
        else
            setCustomPageNum(pageNum = 2)
    }

    /**
     * Initialize the items to be utilized for ki build distribution section.
     */
    fun initializeBuildList(){
        editBuildList.clear()
        customTechnique.givenAbilities.forEach{effect ->
            editBuildList += BuildPackage(input = effect, context = context, customTechVM = this)
        }
    }

    /**
     * Sets the custom technique's selection on having maintenance or not.
     *
     * @param isMaintained state to set the maintenance selection to
     */
    fun setMaintenanceSelection(isMaintained: Boolean){
        //clear maintenance if it is removed
        if(!isMaintained){
            for(index in 0..5)
                customTechnique.maintArray[index] = 0
        }

        //update maintained state
        _maintenanceSelection.update{isMaintained}
    }

    /**
     * Retrieves whether the indicated primary characteristic has any accumulation required for
     * this technique.
     *
     * @param primaryIndex primary characteristic's location in its array
     * @return true if any accumulation found here
     */
    fun techHasAccIn(primaryIndex: Int): Boolean{return customTechnique.hasAccumulation(buildIndex = primaryIndex)}

    /**
     * Sets the custom technique's name to the given input.
     *
     * @param newName name to apply to the technique
     */
    fun setTechniqueName(newName: String){
        _techniqueName.update{newName}
        customTechnique.name.value = newName
    }

    /**
     * Sets the custom technique's description to the given input.
     *
     * @param newDesc description to apply to the technique
     */
    fun setTechniqueDesc(newDesc: String){
        _techniqueDesc.update{newDesc}
        customTechnique.description.value = newDesc
    }

    /**
     * Toggles the public status of the custom technique.
     */
    fun toggleTechniquePublic(){
        _isPublic.update{!isPublic.value}
        customTechnique.togglePublic()
    }

    /**
     * Retrieves the custom technique being made here.
     *
     * @return the whole technique being worked on
     */
    fun getCustomTechnique(): CustomTechnique {return customTechnique}

    /**
     * Retrieves the total cost of the custom technique.
     *
     * @return technique's cost value
     */
    fun getTechniqueCost(): Int{return customTechnique.mkCost()}

    /**
     * Retrieves the currently selected effects for the custom technique.
     *
     * @return list of custom technique's effects
     */
    fun getTechniqueEffects(): MutableList<TechniqueEffect>{return customTechnique.givenAbilities}

    /**
     * Retrieves the valid state of all of the technique's effects' builds.
     *
     * @return true if all effects have valid builds
     */
    fun getCheckBuilds(): Boolean{
        return customTechnique.checkBuilds()
    }

    /**
     * Retrieves the valid state of the technique's maintenance array.
     *
     * @return true if the maintenance input is valid
     */
    fun getMaintenanceCheck(): Boolean{
        return customTechnique.checkMaintenance()
    }

    /**
     * Retrieves the total maintenance value of the custom technique.
     *
     * @return technique's maintenance cost
     */
    fun getMaintenanceTotal(): Int{return customTechnique.maintTotal()}

    /**
     * Sets this viewModel back to its default values.
     */
    private fun resetDialog(){
        setCustomPageNum(pageNum = 1)
        customTechnique.name.value = ""
        _techniqueName.update{""}
        customTechnique.isPublic.value = true
        customTechnique.description.value = ""
        _techniqueDesc.update{""}
        customTechnique.level.intValue = 1
        for(index in 0..5)
            customTechnique.maintArray[index] = 0
        customTechnique.givenAbilities.clear()
    }

    //initialize build total displays for ki builds
    private val strAccTotal = AccTotalString(customTechVM = this, index = 0)
    private val dexAccTotal = AccTotalString(customTechVM = this, index = 1)
    private val agiAccTotal = AccTotalString(customTechVM = this, index = 2)
    private val conAccTotal = AccTotalString(customTechVM = this, index = 3)
    private val powAccTotal = AccTotalString(customTechVM = this, index = 4)
    private val wpAccTotal = AccTotalString(customTechVM = this, index = 5)

    //gather all build displays
    val allAccs = listOf(strAccTotal, dexAccTotal, agiAccTotal, conAccTotal, powAccTotal, wpAccTotal)

    //initialize all input items for technique maintenance
    private val strMaintInput = MaintInput(
        name = context.resources.getStringArray(R.array.primaryCharArray)[0],
        customTechnique = customTechnique,
        index = 0
    )

    private val dexMaintInput = MaintInput(
        name = context.resources.getStringArray(R.array.primaryCharArray)[1],
        customTechnique = customTechnique,
        index = 1
    )

    private val agiMaintInput = MaintInput(
        name = context.resources.getStringArray(R.array.primaryCharArray)[2],
        customTechnique = customTechnique,
        index = 2
    )

    private val conMaintInput = MaintInput(
        name = context.resources.getStringArray(R.array.primaryCharArray)[3],
        customTechnique = customTechnique,
        index = 3
    )

    private val powMaintInput = MaintInput(
        name = context.resources.getStringArray(R.array.primaryCharArray)[5],
        customTechnique = customTechnique,
        index = 4
    )

    private val wpMaintInput = MaintInput(
        name = context.resources.getStringArray(R.array.primaryCharArray)[6],
        customTechnique = customTechnique,
        index = 5
    )

    //gather all maintenance inputs
    val allMaintInputs = listOf(strMaintInput, dexMaintInput, agiMaintInput, conMaintInput, powMaintInput, wpMaintInput)

    /**
     * Batch of build input data associated with the inputted effect.
     *
     * @param input associated technique effect
     * @param context source of the resources used
     * @param customTechVM viewModel that manages the custom technique dialog
     */
    class BuildPackage(
        val input: TechniqueEffect,
        val context: Context,
        customTechVM: CustomTechniqueViewModel
    ){
        //initialize all build strings for this effect
        val buildItems = mutableListOf<BuildItem>()

        init{
            var index = 0

            //add a build item for each valid index input for this effect
            listOf(0, 1, 2, 3, 5, 6).forEach {
                if(input.buildAdditions[index] != null)
                    buildItems +=
                        BuildItem(
                            home = input,
                            indexName = context.resources.getStringArray(R.array.primaryCharArray)[it],
                            index = index,
                            customTechVM = customTechVM
                        )

                index++
            }
        }
    }

    /**
     * Item for one input of a technique effect's ki build.
     *
     * @param home effect related to this particular input
     * @param indexName string title for this item
     * @param index corresponding ki build item this data represents
     * @param customTechVM viewModel that manages these items
     */
    class BuildItem(
        val home: TechniqueEffect,
        val indexName: String,
        val index: Int,
        val customTechVM: CustomTechniqueViewModel
    ){
        //initialize input display
        private val _display = MutableStateFlow(value = home.kiBuild[index].toString())
        val display = _display.asStateFlow()

        /**
         * Change the ki input to the indicated number.
         *
         * @param kiValue value to change the build to
         */
        fun setDisplay(kiValue: Int){
            setDisplay(display = kiValue.toString())
            home.kiBuild[index] = kiValue
            customTechVM.allAccs[index].setTotalDisplay()
        }

        /**
         * Set the value to be shown in the ki build display.
         *
         * @param display value to change the display to
         */
        fun setDisplay(display: String){
            _display.update{display}
        }
    }

    /**
     * Item for total accumulation of a particular primary characteristic.
     *
     * @param customTechVM viewModel that manages the custom technique
     * @param index primary characteristic location of the associated object
     */
    class AccTotalString(
        private val customTechVM: CustomTechniqueViewModel,
        val index: Int
    ){
        //initialize display string
        private val _totalDisplay = MutableStateFlow(value = 0)
        val totalDisplay = _totalDisplay.asStateFlow()

        /**
         * Sets the value of the display for the total build.
         */
        fun setTotalDisplay(){_totalDisplay.update{customTechVM.gatherIndex(primeIndex = index)}}
    }

    /**
     * Item that manages a maintenance input for the technique.
     *
     * @param name string to head the row
     * @param customTechnique technique being made in this section
     * @param index associated maintenance item being worked on in this section
     */
    class MaintInput(
        val name: String,
        private val customTechnique: CustomTechnique,
        val index: Int
    ){
        //initialize input display
        private val _displayValue = MutableStateFlow(customTechnique.maintArray[index].toString())
        val displayValue = _displayValue.asStateFlow()

        /**
         * Change the technique's maintenance input to the desired value.
         *
         * @param maintValue value to set the maintenance input to
         */
        fun setDisplayValue(maintValue: Int){
            customTechnique.maintArray[index] = maintValue
            setDisplayValue(maintValue.toString())
        }

        /**
         * Change the display to the indicated value.
         *
         * @param display new value to display
         */
        fun setDisplayValue(display: String){_displayValue.update{display}}
    }
}