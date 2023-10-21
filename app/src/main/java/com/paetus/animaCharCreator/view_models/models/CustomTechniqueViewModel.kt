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
 * @param context context in which the dialog is being held
 * @param kiFragVM ki fragment view model that is operating at the same time as this object
 */
class CustomTechniqueViewModel(
    private val ki: Ki,
    val context: Context
): ViewModel(){
    //initialize the newly created technique
    private val customTechnique = CustomTechnique()

    //initialize open state of custom technique dialog
    private val _customTechOpen = MutableStateFlow(false)
    val customTechOpen = _customTechOpen.asStateFlow()

    //create element lists for the relevant effects
    private val elementAttackList = listOf(Element.Fire, Element.Water, Element.Air, Element.Earth)
    private val elementBindList = elementAttackList + listOf(Element.Light, Element.Dark)

    //data table of technique effects
    private val techniqueDatabase = ki.techniqueDatabase

    //initialize the page tracker of the custom technique
    private val _customPageNum = MutableStateFlow(1)
    val customPageNum = _customPageNum.asStateFlow()

    //initialize custom technique's level indicator
    private val _customTechLevel = MutableStateFlow(1)
    val customTechLevel = _customTechLevel.asStateFlow()

    //initialize minimum and maximum costs of the custom technique
    private val _costMinimum = MutableStateFlow(20)
    val costMinimum = _costMinimum.asStateFlow()
    private val _costMaximum = MutableStateFlow(50)
    val costMaximum = _costMaximum.asStateFlow()

    //initialize whether the character is picking their primary effect
    private val _pickingPrimary = MutableStateFlow(false)
    private val pickingPrimary = _pickingPrimary.asStateFlow()

    //initialize list displayed in effect selection dropdown
    private val _listSource = MutableStateFlow(context.resources.getStringArray(R.array.techniqueAbilities))
    val listSource = _listSource.asStateFlow()

    //initialize selected item in the dropdown
    private val _techniqueIndex = MutableStateFlow(0)
    val techniqueIndex = _techniqueIndex.asStateFlow()

    //initialize the string in the dropdown
    private val _dropdownTitle = MutableStateFlow(context.resources.getStringArray(R.array.techniqueAbilities)[0])
    val dropdownTitle = _dropdownTitle.asStateFlow()

    //initialize open state of the effect selection dropdown
    private val _dropdownOpen = MutableStateFlow(false)
    val dropdownOpen = _dropdownOpen.asStateFlow()

    //initialize logo displayed on the dropdown
    private val _dropdownIcon = MutableStateFlow(Icons.Filled.KeyboardArrowDown)
    val dropdownIcon = _dropdownIcon.asStateFlow()

    //initialize the size for the dropdowns in this dialog
    private val _size = MutableStateFlow(Size.Zero)
    val size = _size.asStateFlow()

    //initialize list of build additions for the selected technique effect
    private val _buildArray = MutableStateFlow<List<Int?>>(listOf())
    private val buildArray = _buildArray.asStateFlow()

    //initialize the effect's associated element list
    private val _elementList = MutableStateFlow<List<Element>>(listOf())
    val elementList = _elementList.asStateFlow()

    //initialize title for the effects table
    private val _header = MutableStateFlow<String?>(null)
    val header = _header.asStateFlow()

    //initialize title for the first optional effects table
    private val _optHeader1 = MutableStateFlow<String?>(null)
    val optHeader1 = _optHeader1.asStateFlow()

    //initialize title for the second optional effects table
    private val _optHeader2 = MutableStateFlow<String?>(null)
    val optHeader2 = _optHeader2.asStateFlow()

    //initialize table of data for the technique effect
    private val _useTable = MutableStateFlow<List<TechniqueTableData>?>(null)
    val useTable = _useTable.asStateFlow()

    //initialize the first optional table for the technique effect
    private val _optTable1 = MutableStateFlow<List<TechniqueTableData>?>(null)
    val optTable1 = _optTable1.asStateFlow()

    //initialize the second optional table for the technique effect
    private val _optTable2 = MutableStateFlow<List<TechniqueTableData>?>(null)
    val optTable2 = _optTable2.asStateFlow()

    //initialize list of element options for a technique effect
    private val _optElement = MutableStateFlow<List<Element>?>(null)
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
    private val _predeterminedCost = MutableStateFlow("20")
    val predeterminedCost = _predeterminedCost.asStateFlow()

    //initialize checklist for deleting technique effects
    val deletionChecklist = mutableMapOf<TechniqueEffect, MutableState<Boolean>>()

    //initialize list of ki builds for the technique's effects
    val editBuildList = mutableListOf<BuildPackage>()

    //initialize user's selection for technique maintenance
    private val _maintenanceSelection = MutableStateFlow(false)
    val maintenanceSelection = _maintenanceSelection.asStateFlow()

    //initialize the name of the technique
    private val _techniqueName = MutableStateFlow(customTechnique.name.value)
    val techniqueName = _techniqueName.asStateFlow()

    //initialize the description of the technique
    private val _techniqueDesc = MutableStateFlow(customTechnique.description.value)
    val techniqueDesc = _techniqueDesc.asStateFlow()

    private val _isPublic = MutableStateFlow(customTechnique.isPublic.value)
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
     * Sets the page number of the custom technique.
     *
     * @param input page number to set the effect to
     */
    fun setCustomPageNum(input: Int){_customPageNum.update{input}}

    /**
     * Sets the minimum cost required for the technique created.
     *
     * @param input minimum value to set
     */
    private fun setCostMinimum(input: Int){_costMinimum.update{input}}

    /**
     * Sets the maximum cost required for the technique created.
     *
     * @param input maximum value to set
     */
    private fun setCostMaximum(input: Int){_costMaximum.update{input}}

    /**
     * Sets the technique's level to the indicated value.
     *
     * @param input level to set the technique to
     */
    fun setTechniqueLevel(input: Int){
        //set the technique's level
        _customTechLevel.update{input}
        customTechnique.setLevel(input)

        //update the minimum and maximum values as needed
        when(input){
            1 -> {
                setCostMinimum(20)
                setCostMaximum(50)
            }
            2 -> {
                setCostMinimum(40)
                setCostMaximum(100)
            }
            3 -> {
                setCostMinimum(60)
                setCostMaximum(200)
            }
            else -> {
                setCostMinimum(0)
                setCostMaximum(0)
            }
        }
    }

    /**
     * Determines if the minimum cost requirement has been met.
     *
     * @return true if condition met
     */
    fun costMinMet(toMeet: Int): Boolean{return toMeet >= costMinimum.value}

    /**
     * Determines that the level of technique to make is a valid addition for the character.
     *
     * @return true if this level of technique is valid
     */
    fun minTechsMet(): Boolean{
        when(customTechnique.level.intValue){
            1 -> return true
            2 -> return ki.getLevelCount(1) >= 2
            3 -> return ki.getLevelCount(2) >= 2
        }

        return false
    }

    /**
     * Notifies the viewModel of whether the user is selecting the primary effect or not.
     *
     * @param input boolean to set the selection flag to
     */
    fun setPickingPrimary(input: Boolean){
        //update the primary effect flag
        _pickingPrimary.update{input}

        //update the displayed techniques for the dropdown
        if(input) {
            _listSource.update { context.resources.getStringArray(R.array.techniqueAbilities) }
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
     * @param input index of the dropdown to set
     */
    fun setTechniqueIndex(input: Int){
        _techniqueIndex.update{input}
        _dropdownTitle.update{listSource.value[techniqueIndex.value]}

        tableClear()

        when(input){
            //Attack Ability
            1 -> {
                setHeader(context.getString(R.string.attackBonusHeader))
                setUseTable(techniqueDatabase.table1)
                setBuildArray(listOf(2, 0, 2, null, 2, 3))
                setElementList(listOf(Element.Fire, Element.Air, Element.Dark))
            }

            //Counterattack Ability
            2 -> {
                setHeader(context.getString(R.string.attackBonusHeader))
                setUseTable(techniqueDatabase.table2)
                setBuildArray(listOf(2, 0, 2, null, 2, 3))
                setElementList(listOf(Element.Water, Element.Air, Element.Earth))
            }

            //Block Ability
            3 -> {
                setHeader(context.getString(R.string.blockBonusHeader))
                setUseTable(techniqueDatabase.table3)
                setBuildArray(listOf(2, 0, 2, null, 2, 3))
                setElementList(listOf(Element.Water, Element.Earth, Element.Light))
            }

            //Limited Block Ability
            4 -> {
                setHeader(context.getString(R.string.blockBonusHeader))
                setUseTable(techniqueDatabase.table4)
                setBuildArray(listOf(2, 0, 2, null, 2, 3))
                setElementList(listOf(Element.Water, Element.Earth, Element.Light))
            }

            //Dodge Ability
            5 -> {
                setHeader(context.getString(R.string.dodgeBonusHeader))
                setUseTable(techniqueDatabase.table5)
                setBuildArray(listOf(null, 2, 0, 2, 2, 3))
                setElementList(listOf(Element.Water, Element.Air, Element.Light))
            }

            //Limited Dodge Ability
            6 -> {
                setHeader(context.getString(R.string.dodgeBonusHeader))
                setUseTable(techniqueDatabase.table6)
                setBuildArray(listOf(null, 2, 0, 2, 2, 3))
                setElementList(listOf(Element.Air, Element.Light, Element.Dark))
            }

            //Damage Multiplier
            7 -> {
                setHeader(context.getString(R.string.multLabel))
                setUseTable(techniqueDatabase.table7)
                setBuildArray(listOf(0, 3, null, 2, 1, 1))
                setElementList(listOf(Element.Fire, Element.Earth))
            }

            //Damage Augmentation
            8 -> {
                setHeader(context.getString(R.string.damageBonusHeader))
                setUseTable(techniqueDatabase.table8)
                setOptHeader1(optionalHeaderComposer(R.string.sacrificeHeader))
                setOptTable1(techniqueDatabase.table8a)
                setBuildArray(listOf(0, 3, null, 1, 2, 1))
                setElementList(listOf(Element.Fire, Element.Earth))
            }

            //Additional Attack
            9 -> {
                setHeader(context.getString(R.string.attacksHeader))
                setUseTable(techniqueDatabase.table9)
                setOptHeader1(optionalHeaderComposer(R.string.continuousAttackHeader))
                setOptTable1(techniqueDatabase.table9a)
                setOptHeader2(optionalHeaderComposer(R.string.fatigueBonusHeader))
                setOptTable2(techniqueDatabase.table9b)
                setBuildArray(listOf(null, 0, 2, 1, 3, 3))
                setElementList(listOf(Element.Water, Element.Air))
            }

            //Limited Additional Attack
            10 -> {
                setHeader(context.getString(R.string.attacksHeader))
                setUseTable(techniqueDatabase.table10)
                setOptHeader1(optionalHeaderComposer(R.string.continuousAttackHeader))
                setOptTable1(techniqueDatabase.table10a)
                setBuildArray(listOf(null, 0, 2, 1, 3, 3))
                setElementList(listOf(Element.Water, Element.Air, Element.Dark))
            }

            //Additional Defense
            11 -> {
                setHeader(context.getString(R.string.defensesHeader))
                setUseTable(techniqueDatabase.table11)
                setOptHeader1(optionalHeaderComposer(R.string.fatigueBonusHeader))
                setOptTable1(techniqueDatabase.table11a)
                setBuildArray(listOf(null, 1, 0, 1, 3, 3))
                setElementList(listOf(Element.Light))
            }

            //Additional Action
            12 -> {
                setHeader(context.getString(R.string.actionsHeader))
                setUseTable(techniqueDatabase.table12)
                setOptHeader1(optionalHeaderComposer(R.string.fatigueBonusHeader))
                setOptTable1(techniqueDatabase.table12a)
                setBuildArray(listOf(null, 0, 1, 1, 3, 3))
                setElementList(listOf(Element.Air))
            }

            //Initiative Augmentation
            13 -> {
                setHeader(context.getString(R.string.initiativeBonusHeader))
                setUseTable(techniqueDatabase.table13)
                setBuildArray(listOf(null, 1, 0, 2, 3, 3))
                setElementList(listOf(Element.Air))
            }

            //States
            14 -> {
                setHeader(context.getString(R.string.phrHeader))
                setUseTable(techniqueDatabase.table14)
                setOptHeader1(optionalHeaderComposer(R.string.addedStateHeader))
                setOptTable1(techniqueDatabase.table14a)
                setBuildArray(listOf(4, 4, null, 4, 0, 1))
                setElementList(listOf(Element.Light, Element.Dark))
            }

            //Combat Maneuvers and Aiming
            15 -> {
                setHeader(context.getString(R.string.precisionHeader))
                setUseTable(techniqueDatabase.table15)
                setBuildArray(listOf(null, 0, 1, 2, 2, 2))
                setElementList(listOf(Element.Air))
            }

            //Armor Increase
            16 -> {
                setHeader(context.getString(R.string.atHeader))
                setUseTable(techniqueDatabase.table16)
                setBuildArray(listOf(2, null, 3, 0, 1, 2))
                setElementList(listOf(Element.Water, Element.Earth, Element.Light))
            }

            //Armor Destruction
            17 -> {
                setHeader(context.getString(R.string.reductionHeader))
                setUseTable(techniqueDatabase.table17)
                setBuildArray(listOf(0, 2, null, 2, 1, 2))
                setElementList(listOf(Element.Fire, Element.Dark))
            }

            //Breakage Augmentation
            18 -> {
                setHeader(context.getString(R.string.breakageHeader))
                setUseTable(techniqueDatabase.table18)
                setBuildArray(listOf(0, 4, null, 2, 2, 1))
                setElementList(listOf(Element.Fire, Element.Earth))
            }

            //Fortitude Augmentation
            19 -> {
                setHeader(context.getString(R.string.fortitudeHeader))
                setUseTable(techniqueDatabase.table19)
                setBuildArray(listOf(0, 4, null, 2, 2, 1))
                setElementList(listOf(Element.Fire, Element.Earth))
            }

            //Long-Distance Attack
            20 -> {
                setHeader(context.getString(R.string.distanceHeader))
                setUseTable(techniqueDatabase.table20)
                setBuildArray(listOf(null, 2, 3, 4, 0, 1))
                setElementList(listOf(Element.Fire, Element.Water, Element.Air))
            }

            //Area Attack
            21 -> {
                setHeader(context.getString(R.string.radiusHeader))
                setUseTable(techniqueDatabase.table21)
                setOptHeader1(optionalHeaderComposer(R.string.targetChoiceHeader))
                setOptTable1(techniqueDatabase.table21a)
                setBuildArray(listOf(null, 2, 3, 3, 0, 1))
                setElementList(listOf(Element.Fire, Element.Light, Element.Dark))
            }

            //Automatic Transportation
            22 -> {
                setHeader(context.getString(R.string.distanceHeader))
                setUseTable(techniqueDatabase.table22)
                setBuildArray(listOf(2, 2, 0, 2, 3, null))
                setElementList(listOf(Element.Air, Element.Light, Element.Dark))
            }

            //Critical Enhancement
            23 -> {
                setHeader(context.getString(R.string.criticalHeader))
                setUseTable(techniqueDatabase.table23)
                setOptHeader1(optionalHeaderComposer(R.string.autoCritHeader))
                setOptTable1(techniqueDatabase.table23a)
                setBuildArray(listOf(1, 2, null, 2, 0, 1))
                setElementList(listOf(Element.Fire, Element.Earth))
            }

            //Physical Ki Weapons
            24 -> {
                setHeader(context.getString(R.string.qualityHeader))
                setUseTable(techniqueDatabase.table24)
                setOptHeader1(optionalHeaderComposer(R.string.projectileLabel))
                setOptTable1(techniqueDatabase.table24a)
                setBuildArray(listOf(2, 3, null, 1, 0, 1))
                setElementList(listOf(Element.Earth, Element.Light, Element.Dark))
            }

            //Trapping
            25 -> {
                setHeader(context.getString(R.string.trapHeader))
                setUseTable(techniqueDatabase.table25)
                setBuildArray(listOf(0, 1, null, 2, 2, 2))
                setElementList(listOf(Element.Earth))
            }

            //Projection
            26 -> {
                setHeader(context.getString(R.string.projectionHeader))
                setUseTable(techniqueDatabase.table26)
                setBuildArray(listOf(0, 3, null, 2, 1, 1))
                setElementList(listOf(Element.Fire, Element.Earth))
            }

            //Energy Shield
            27 -> {
                setHeader(context.getString(R.string.lpHeader))
                setUseTable(techniqueDatabase.table27)
                setBuildArray(listOf(2, 3, null, 2, 0, 1))
                setElementList(listOf(Element.Water, Element.Light))
            }

            //Intangibility
            28 -> {
                setHeader(context.getString(R.string.effectHeader))
                setUseTable(techniqueDatabase.table28)
                setBuildArray(listOf(3, 3, null, 3, 0, 1))
                setElementList(listOf(Element.Water, Element.Light, Element.Dark))
            }

            //Mirage
            29 -> {
                setHeader(context.getString(R.string.mirageHeader))
                setUseTable(techniqueDatabase.table29)
                setOptHeader1(optionalHeaderComposer(R.string.nonDetectionHeader))
                setOptTable1(techniqueDatabase.table29a)
                setBuildArray(listOf(null, 3, 2, 3, 1, 0))
                setElementList(listOf(Element.Water, Element.Dark))
            }

            //Attack Mirroring
            30 -> {
                setHeader(context.getString(R.string.effectHeader))
                setUseTable(techniqueDatabase.table30)
                setOptHeader1(optionalHeaderComposer(R.string.targetChoiceHeader))
                setOptTable1(techniqueDatabase.table30a)
                setOptHeader2(optionalHeaderComposer(R.string.mirrorEsotericHeader))
                setOptTable2(techniqueDatabase.table30b)
                setBuildArray(listOf(2, 3, 3, null, 0, 1))
                setElementList(listOf(Element.Water, Element.Light, Element.Dark))
            }

            //Energy Damaging Attack
            31 -> {
                setHeader(context.getString(R.string.attackLabel))
                setUseTable(techniqueDatabase.table31)
                setBuildArray(listOf(3, 3, null, 2, 0, 1))
                setElementList(listOf(Element.Fire, Element.Light, Element.Dark))
            }

            //Elemental Attack
            32 -> {
                setHeader(context.getString(R.string.attackLabel))
                setUseTable(techniqueDatabase.table32)
                setOptHeader1(context.getString(R.string.selectElementLabel))
                setOptElement(elementAttackList)
                setBuildArray(listOf(3, 3, null, 2, 0, 1))
                setElementList(listOf())
            }

            //Supernatural Attack
            33 -> {
                setHeader(context.getString(R.string.attackLabel))
                setUseTable(techniqueDatabase.table33)
                setBuildArray(listOf(3, 3, null, 2, 0, 1))
                setElementList(listOf(Element.Light, Element.Dark))
            }

            //Damage Resistance
            34 -> {
                setHeader(context.getString(R.string.lpHeader))
                setUseTable(techniqueDatabase.table34)
                setBuildArray(listOf(3, 3, null, 0, 3, 1))
                setElementList(listOf(Element.Earth))
            }

            //ElementalBinding
            35 -> {
                setUseTable(techniqueDatabase.table35)
                setOptHeader1(context.getString(R.string.selectElementsLabel))
                setOptElement(elementBindList)
                setBuildArray(listOf(null, null, null, null, null, null))
                setElementList(listOf())
            }

            //Reduce Damage
            36 -> {
                setUseTable(techniqueDatabase.table36)
                setBuildArray(listOf(null, null, null, null, null, null))
                setElementList(listOf(Element.Free))
            }

            //Special Requirements
            37 -> {
                setUseTable(techniqueDatabase.table37)
                setBuildArray(listOf(null, null, null, null, null, null))
                setElementList(listOf(Element.Free))
            }

            //Predetermination
            38 -> {
                setUseTable(techniqueDatabase.table38)
                setBuildArray(listOf(null, null, null, null, null, null))
                setElementList(listOf(Element.Free))
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
            useTable.value!!.forEach{
                mainChecklist += Pair(it, mutableStateOf(false))
            }
        }

        if(optTable1.value != null){
            optTable1.value!!.forEach{
                opt1Checklist += Pair(it, mutableStateOf(false))
            }
        }

        if(optTable2.value != null){
            optTable2.value!!.forEach{
                opt2Checklist += Pair(it, mutableStateOf(false))
            }
        }

        if(optElement.value != null){
            optElement.value!!.forEach{
                elementChecklist += Pair(it, mutableStateOf(false))
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

    fun optionalHeaderComposer(address: Int): String{
        return context.getString(R.string.optionalHeader, context.getString(address))
    }

    /**
     * Sets the dropdown item's size.
     *
     * @param input size to set the dropdown to
     */
    fun setSize(input: Size){_size.update{input}}

    /**
     * Sets the build addition list for the technique effect.
     *
     * @param input list of additions to set the state to
     */
    private fun setBuildArray(input: List<Int?>){_buildArray.update{input}}

    /**
     * Sets the display of the effect's elements.
     *
     * @param input element list to set the state to
     */
    private fun setElementList(input: List<Element>){_elementList.update{input}}

    /**
     * Sets the header of the effect table.
     *
     * @param input header to set
     */
    private fun setHeader(input: String?){_header.update{input}}

    /**
     * Sets the first optional header of the effect table.
     *
     * @param input header to set
     */
    private fun setOptHeader1(input: String?){_optHeader1.update{input}}

    /**
     * Sets the second optional header of the effect table.
     *
     * @param input header to set
     */
    private fun setOptHeader2(input: String?){_optHeader2.update{input}}

    /**
     * Sets the information for the effect table.
     *
     * @param input table data to set
     */
    private fun setUseTable(input: List<TechniqueTableData>?){_useTable.update{input}}

    /**
     * Sets the information for the first optional table.
     *
     * @param input table data to set
     */
    private fun setOptTable1(input: List<TechniqueTableData>?){_optTable1.update{input}}

    /**
     * Sets the information for the second optional table.
     *
     * @param input table data to set
     */
    private fun setOptTable2(input: List<TechniqueTableData>?){_optTable2.update{input}}

    /**
     * Sets the information for the element options table.
     *
     * @param input table data to set
     */
    private fun setOptElement(input: List<Element>?){ _optElement.update{input}}

    /**
     * Empties all tables and headers of data.
     */
    private fun tableClear(){
        setHeader(null)
        setOptHeader1(null)
        setOptHeader2(null)

        setUseTable(null)
        setOptTable1(null)
        setOptTable2(null)
        setOptElement(null)
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
        addedTechniques.forEach{
            //attempt to add the effect
            val newInput =
                customTechnique.validEffectAddition(it, ki.martialKnowledgeRemaining.value - additionCost)

            //add cost if no error
            if(newInput == null){
                additionCost += it.data.mkCost
            }

            //return error if failed
            else
                return newInput
        }

        //for each valid technique selection
        addedTechniques.forEach{
            //retrieve the technique's element
            it.elements = getSelectedElement(it)

            //add disadvantage flag if adding elemental binding
            if(techniqueIndex.value == 35) it.elements += Element.Free

            //add effects to technique
            customTechnique.givenAbilities.add(it)
        }

        //reset dropdown and terminate method
        setTechniqueIndex(0)
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
        mainChecklist.forEach{
            if(it.value.value){
                output.add(dataToEffect(it.key))
                return@forEach
            }
        }

        //check the first optional table for a valid technique input
        opt1Checklist.forEach{
            if(it.value.value){
                output.add(dataToEffect(it.key))
                return@forEach
            }
        }

        //check the second optional table for a valid technique input
        opt2Checklist.forEach{
            if(it.value.value){
                output.add(dataToEffect(it.key))
                return@forEach
            }
        }

        //check for legal state technique addition
        if(techniqueIndex.value == 14){
            //merge two options into one technique
            return if(output.size == 2)
                listOf(
                    TechniqueEffect(
                        TechniqueTableData(
                            14,
                            output[1].data.effectRef,
                            output[0].data.effectVal,
                            output[0].data.primaryCost + output[1].data.primaryCost,
                            output[0].data.secondaryCost + output[1].data.secondaryCost,
                            output[0].data.mkCost + output[1].data.mkCost,
                            output[0].data.maintCost,
                            if(output[0].data.level > output[1].data.level) output[0].data.level else output[1].data.level
                        ),
                        mutableListOf(
                            output[0].kiBuild[0] + output[1].kiBuild[0],
                            output[0].kiBuild[1] + output[1].kiBuild[1],
                            output[0].kiBuild[2] + output[1].kiBuild[2],
                            output[0].kiBuild[3] + output[1].kiBuild[3],
                            output[0].kiBuild[4] + output[1].kiBuild[4],
                            output[0].kiBuild[5] + output[1].kiBuild[5]
                        ),
                        output[0].buildAdditions,
                        output[0].elements
                    )
                )

                //return no technique if incomplete state technique
                else
                    listOf()
        }

        if(techniqueIndex.value == 37 && output.isNotEmpty()){
            return listOf(
                TechniqueEffect(
                    TechniqueTableData(
                        output[0].data.name,
                        output[0].data.effectRef,
                        null,
                        output[0].data.primaryCost,
                        output[0].data.secondaryCost,
                        predeterminedCost.value.toInt() * -1,
                        output[0].data.maintCost,
                        output[0].data.level
                    ),
                    output[0].kiBuild,
                    output[0].buildAdditions,
                    output[0].elements
                )
            )
        }

        //return list of selections
        return output.toList()
    }

    fun getDeterminedConditions(): Boolean{
        return techniqueIndex.value == 37 &&
                getSelectedEffects().isNotEmpty() &&
                getSelectedEffects()[0].data.effectRef == R.string.determinedCondition
    }

    /**
     * Retrieve the selection for the effect's element.
     *
     * @param input currently selected technique effect
     */
    fun getSelectedElement(input: TechniqueEffect): MutableList<Element>{
        //return own elements if they have them
        if(input.elements.isNotEmpty())
            return input.elements

        //initialize user selected output
        val output = mutableListOf<Element>()

        //get user's selected elements
        elementChecklist.forEach{
            if(it.value.value && !output.contains(it.key))
                output.add(it.key)
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
        getSelectedEffects().forEach{
            output += it.data.mkCost
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
    fun validCheckInput(effectInput: TechniqueTableData): Int?{
        return customTechnique.validEffectAddition(dataToEffect(effectInput), ki.martialKnowledgeRemaining.value)
    }

    /**
     * Converts a technique table data item into a technique effect.
     *
     * @param item technique table data to convert
     * @return effect to return from the table data
     */
    private fun dataToEffect(item: TechniqueTableData): TechniqueEffect {
        //initialize the build for the technique
        val defaultBuild = mutableListOf(0, 0, 0, 0, 0, 0)

        //set the default value of the build array
        if(buildArray.value.indexOf(0) >= 0){
            if(pickingPrimary.value)
                defaultBuild[buildArray.value.indexOf(0)] = item.primaryCost
            else
                defaultBuild[buildArray.value.indexOf(0)] = item.secondaryCost
        }

        return TechniqueEffect(
            item,
            defaultBuild,
            buildArray.value,
            elementList.value.toMutableList()
        )
    }

    /**
     * Clears the inputted selection map.
     *
     * @param input map of checkboxes to be cleared
     */
    fun clearInputList(input: MutableMap<TechniqueTableData, MutableState<Boolean>>){
        input.forEach{it.value.value = false}
    }

    /**
     * Clears the checkboxes for the element selection.
     */
    fun clearElementChecks(){
        elementChecklist.forEach{
            it.value.value = false
        }
    }

    /**
     * Sets the displayed value for the predetermination cost with an integer check.
     *
     * @param input integer to convert to string
     */
    fun setPredeterminedCost(input: Int){setPredeterminedCost(input.toString())}

    /**
     * Sets the displayed value for the predetermination cost.
     *
     * @param input new item to display
     */
    fun setPredeterminedCost(input: String){_predeterminedCost.update{input}}

    /**
     * Retrieves the total cost of ki build needed for one index.
     *
     * @param index array index to sum up
     */
    fun gatherIndex(index: Int): Int{
        var output = 0
        customTechnique.givenAbilities.forEach{
            output += it.kiBuild[index]
        }

        return output
    }

    /**
     * Sets the checklist items for effect deletion.
     */
    fun setDeletionChecklist(){
        //clear any previous list items
        deletionChecklist.clear()

        //add all currently held effects to the list
        customTechnique.givenAbilities.forEach{
            deletionChecklist += Pair(it, mutableStateOf(false))
        }
    }

    /**
     * Removes the desired technique effects from the custom technique.
     */
    fun deleteEffects(){
        //remove the effects from the custom technique
        deletionChecklist.forEach{
            if(it.value.value){
                customTechnique.givenAbilities.remove(customTechnique.getAbility(it.key.data.name, it.key.data.primaryCost, it.key.data.secondaryCost, it.key.data.mkCost))
            }
        }

        //change the primary effect if change is necessary
        customTechnique.fixPrimaryAbility()

        //set to primary effect selection page if no more effects are in the technique
        if(customTechnique.givenAbilities.isNotEmpty())
            setCustomPageNum(3)
        else
            setCustomPageNum(2)
    }

    /**
     * Initialize the items to be utilized for ki build distribution section.
     */
    fun initializeBuildList(){
        editBuildList.clear()
        customTechnique.givenAbilities.forEach{
            editBuildList += BuildPackage(it, this, context)
        }
    }

    /**
     * Sets the custom technique's selection on having maintenance or not.
     *
     * @param input state to set the maintenance selection to
     */
    fun setMaintenanceSelection(input: Boolean){
        if(!input){
            for(index in 0..5)
                customTechnique.maintArray[index] = 0
        }

        _maintenanceSelection.update{input}
    }

    /**
     * Retrieves whether the indicated primary characteristic has any accumulation required for
     * this technique.
     *
     * @param index primary characteristic's location in its array
     * @return true if any accumulation found here
     */
    fun techHasAccIn(index: Int): Boolean{return customTechnique.hasAccumulation(index)}

    /**
     * Sets the custom technique's name to the given input.
     *
     * @param input name to apply to the technique
     */
    fun setTechniqueName(input: String){
        _techniqueName.update{input}
        customTechnique.name.value = input
    }

    /**
     * Sets the custom technique's description to the given input.
     *
     * @param input description to apply to the technique
     */
    fun setTechniqueDesc(input: String){
        _techniqueDesc.update{input}
        customTechnique.description.value = input
    }

    fun toggleTechniquePublic(){
        _isPublic.update{!isPublic.value}
        customTechnique.togglePublic()
    }

    /**
     * Retrieves the custom technique being made here.
     *
     * @return the whole technique being worked on.
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

    fun resetDialog(){
        setCustomPageNum(1)
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
    private val strAccTotal = AccTotalString(this, 0)
    private val dexAccTotal = AccTotalString(this, 1)
    private val agiAccTotal = AccTotalString(this, 2)
    private val conAccTotal = AccTotalString(this, 3)
    private val powAccTotal = AccTotalString(this, 4)
    private val wpAccTotal = AccTotalString(this, 5)

    //gather all build displays
    val allAccs = listOf(strAccTotal, dexAccTotal, agiAccTotal, conAccTotal, powAccTotal, wpAccTotal)

    //initialize all input items for technique maintenance
    private val strMaintInput = MaintInput(
        context.resources.getStringArray(R.array.primaryCharArray)[0],
        customTechnique,
        0
    )

    private val dexMaintInput = MaintInput(
        context.resources.getStringArray(R.array.primaryCharArray)[1],
        customTechnique,
        1
    )

    private val agiMaintInput = MaintInput(
        context.resources.getStringArray(R.array.primaryCharArray)[2],
        customTechnique,
        2
    )

    private val conMaintInput = MaintInput(
        context.resources.getStringArray(R.array.primaryCharArray)[3],
        customTechnique,
        3
    )

    private val powMaintInput = MaintInput(
        context.resources.getStringArray(R.array.primaryCharArray)[5],
        customTechnique,
        4
    )

    private val wpMaintInput = MaintInput(
        context.resources.getStringArray(R.array.primaryCharArray)[6],
        customTechnique,
        5
    )

    //gather all maintenance inputs
    val allMaintInputs = listOf(strMaintInput, dexMaintInput, agiMaintInput, conMaintInput, powMaintInput, wpMaintInput)

    /**
     * Batch of build input data associated with the inputted effect.
     *
     * @param input associated technique effect
     * @param context source of the resources used
     */
    class BuildPackage(
        val input: TechniqueEffect,
        customTechVM: CustomTechniqueViewModel,
        val context: Context
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
                            customTechVM,
                            input,
                            context.resources.getStringArray(R.array.primaryCharArray)[it],
                            index
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
     */
    class BuildItem(
        val customTechVM: CustomTechniqueViewModel,
        val home: TechniqueEffect,
        val indexName: String,
        val index: Int
    ){
        //initialize input display
        private val _display = MutableStateFlow(home.kiBuild[index].toString())
        val display = _display.asStateFlow()

        /**
         * Change the ki input to the indicated number.
         *
         * @param input value to change the build to
         */
        fun setDisplay(input: Int){
            setDisplay(input.toString())
            home.kiBuild[index] = input
            customTechVM.allAccs[index].setTotalDisplay()
        }

        /**
         * Set the value to be shown in the ki build display.
         *
         * @param input value to change the display to
         */
        fun setDisplay(input: String){
            _display.update{input}
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
        private val _totalDisplay = MutableStateFlow(0)
        val totalDisplay = _totalDisplay.asStateFlow()

        /**
         * Sets the value of the display for the total build.
         */
        fun setTotalDisplay(){_totalDisplay.update{customTechVM.gatherIndex(index)}}
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
         * @param input value to set the maintenance input to
         */
        fun setDisplayValue(input: Int){
            customTechnique.maintArray[index] = input
            setDisplayValue(input.toString())
        }

        /**
         * Change the display to the indicated value.
         *
         * @param input new value to display
         */
        fun setDisplayValue(input: String){_displayValue.update{input}}
    }
}