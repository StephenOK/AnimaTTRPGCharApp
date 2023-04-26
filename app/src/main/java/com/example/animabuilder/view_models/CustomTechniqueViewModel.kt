package com.example.animabuilder.view_models

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Size
import com.example.animabuilder.R
import com.example.animabuilder.TechniqueTableData
import com.example.animabuilder.TechniqueTableDataRecord
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.Technique
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.TechniqueEffect
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
    val context: Context,
    private val kiFragVM: KiFragmentViewModel
) {
    //initialize the newly created technique
    private val customTechnique = Technique(
        "",
        "",
        1,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf()
    )

    //data table of technique effects
    private val techniqueDatabase = TechniqueTableDataRecord()

    //initialize the page tracker of the custom technique
    private val _customPageNum = MutableStateFlow(1)
    val customPageNum = _customPageNum.asStateFlow()

    //initialize the size for the dropdowns in this dialog
    private val _size = MutableStateFlow(Size.Zero)
    val size = _size.asStateFlow()

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
    private val _dropdownTitle = MutableStateFlow("--Select an ability --")
    val dropdownTitle = _dropdownTitle.asStateFlow()

    //initialize open state of the effect selection dropdown
    private val _dropdownOpen = MutableStateFlow(false)
    val dropdownOpen = _dropdownOpen.asStateFlow()

    //initialize logo displayed on the dropdown
    private val _dropdownIcon = MutableStateFlow(Icons.Filled.KeyboardArrowDown)
    val dropdownIcon = _dropdownIcon.asStateFlow()

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

    //initialize checklist for deleting technique effects
    val deletionChecklist = mutableMapOf<TechniqueEffect, MutableState<Boolean>>()

    //initialize list of ki builds for the technique's effects
    val editBuildList = mutableListOf<BuildPackage>()

    //initialize build total displays for ki builds
    private val strAccTotal = AccTotalString(this, 0)
    private val dexAccTotal = AccTotalString(this, 1)
    private val agiAccTotal = AccTotalString(this, 2)
    private val conAccTotal = AccTotalString(this, 3)
    private val powAccTotal = AccTotalString(this, 4)
    private val wpAccTotal = AccTotalString(this, 5)

    //gather all build displays
    val allAccs = listOf(strAccTotal, dexAccTotal, agiAccTotal, conAccTotal, powAccTotal, wpAccTotal)

    //initialize user's selection for technique maintenance
    private val _maintenanceSelection = MutableStateFlow(false)
    val maintenanceSelection = _maintenanceSelection.asStateFlow()

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
        context.resources.getStringArray(R.array.primaryCharArray)[4],
        customTechnique,
        4
    )

    private val wpMaintInput = MaintInput(
        context.resources.getStringArray(R.array.primaryCharArray)[5],
        customTechnique,
        5
    )

    //gather all maintenance inputs
    val allMaintInputs = listOf(strMaintInput, dexMaintInput, agiMaintInput, conMaintInput, powMaintInput, wpMaintInput)

    //initialize the name of the technique
    private val _techniqueName = MutableStateFlow(customTechnique.name)
    val techniqueName = _techniqueName.asStateFlow()

    //initialize the description of the technique
    private val _techniqueDesc = MutableStateFlow(customTechnique.description)
    val techniqueDesc = _techniqueDesc.asStateFlow()

    //create element lists for the relevant effects
    private val elementAttackList = listOf(Element.Fire, Element.Water, Element.Air, Element.Earth)
    private val elementBindList = elementAttackList + listOf(Element.Light, Element.Dark)

    /**
     * Sets the page number of the custom technique.
     *
     * @param input page number to set the effect to
     */
    fun setCustomPageNum(input: Int){_customPageNum.update{input}}

    /**
     * Sets the dropdown item's size.
     *
     * @param input size to set the dropdown to
     */
    fun setSize(input: Size){_size.update{input}}

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
     * Retrieves the current level of the custom technique.
     *
     * @return technique's level value
     */
    fun getTechniqueLevel(): Int{return customTechnique.level}

    /**
     * Sets the technique's level to the indicated value.
     *
     * @param input level to set the technique to
     */
    fun setTechniqueLevel(input: Int){
        //set the technique's level
        customTechnique.level = input

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
        when(customTechnique.level){
            1 -> return true
            2 -> return kiFragVM.getTakenFirstSize() >= 2
            3 -> return kiFragVM.getTakenSecondSize() >= 2
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

        when(input){
            //Attack Ability
            1 -> {
                setHeader("Attack Bonus")
                setUseTable(techniqueDatabase.table1)
                setBuildArray(listOf(2, 0, 2, null, 2, 3))
                setElementList(listOf(Element.Fire, Element.Air, Element.Dark))
            }

            //Counterattack Ability
            2 -> {
                setHeader("Attack Bonus")
                setUseTable(techniqueDatabase.table2)
                setBuildArray(listOf(2, 0, 2, null, 2, 3))
                setElementList(listOf(Element.Water, Element.Air, Element.Earth))
            }

            //Block Ability
            3 -> {
                setHeader("Block Bonus")
                setUseTable(techniqueDatabase.table3)
                setBuildArray(listOf(2, 0, 2, null, 2, 3))
                setElementList(listOf(Element.Water, Element.Earth, Element.Light))
            }

            //Limited Block Ability
            4 -> {
                setHeader("Block Bonus")
                setUseTable(techniqueDatabase.table4)
                setBuildArray(listOf(2, 0, 2, null, 2, 3))
                setElementList(listOf(Element.Water, Element.Earth, Element.Light))
            }

            //Dodge Ability
            5 -> {
                setHeader("Dodge Bonus")
                setUseTable(techniqueDatabase.table5)
                setBuildArray(listOf(null, 2, 0, 2, 2, 3))
                setElementList(listOf(Element.Water, Element.Air, Element.Light))
            }

            //Limited Dodge Ability
            6 -> {
                setHeader("Dodge Bonus")
                setUseTable(techniqueDatabase.table6)
                setBuildArray(listOf(null, 2, 0, 2, 2, 3))
                setElementList(listOf(Element.Air, Element.Light, Element.Dark))
            }

            //Damage Multiplier
            7 -> {
                setHeader("Multiplier")
                setUseTable(techniqueDatabase.table7)
                setBuildArray(listOf(0, 3, null, 2, 1, 1))
                setElementList(listOf(Element.Fire, Element.Earth))
            }

            //Damage Augmentation
            8 -> {
                setHeader("Damage Bonus")
                setUseTable(techniqueDatabase.table8)
                setOptHeader1("Optional Advantage: Sacrifice")
                setOptTable1(techniqueDatabase.table8a)
                setBuildArray(listOf(0, 3, null, 1, 2, 1))
                setElementList(listOf(Element.Fire, Element.Earth))
            }

            //Additional Attack
            9 -> {
                setHeader("Attacks")
                setUseTable(techniqueDatabase.table9)
                setOptHeader1("Optional Advantage: Continuous Attack")
                setOptTable1(techniqueDatabase.table9a)
                setOptHeader2("Optional Advantage: Added Fatigue Bonus")
                setOptTable2(techniqueDatabase.table9b)
                setBuildArray(listOf(null, 0, 2, 1, 3, 3))
                setElementList(listOf(Element.Water, Element.Air))
            }

            //Limited Additional Attack
            10 -> {
                setHeader("Attacks")
                setUseTable(techniqueDatabase.table10)
                setOptHeader1("Optional Advantage: Continuous Attack")
                setOptTable1(techniqueDatabase.table10a)
                setBuildArray(listOf(null, 0, 2, 1, 3, 3))
                setElementList(listOf(Element.Water, Element.Air, Element.Dark))
            }

            //Additional Defense
            11 -> {
                setHeader("Defenses")
                setUseTable(techniqueDatabase.table11)
                setOptHeader1("Optional Advantage: Added Fatigue Bonus")
                setOptTable1(techniqueDatabase.table11a)
                setBuildArray(listOf(null, 1, 0, 1, 3, 3))
                setElementList(listOf(Element.Light))
            }

            //Additional Action
            12 -> {
                setHeader("Actions")
                setUseTable(techniqueDatabase.table12)
                setOptHeader1("Optional Advantage: Added Fatigue Bonus")
                setOptTable1(techniqueDatabase.table12a)
                setBuildArray(listOf(null, 0, 1, 1, 3, 3))
                setElementList(listOf(Element.Air))
            }

            //Initiative Augmentation
            13 -> {
                setHeader("Initiative Bonus")
                setUseTable(techniqueDatabase.table13)
                setBuildArray(listOf(null, 1, 0, 2, 3, 3))
                setElementList(listOf(Element.Air))
            }

            //States
            14 -> {
                setHeader("PhR Check")
                setUseTable(techniqueDatabase.table14)
                setOptHeader1("Optional Advantage: Added State")
                setOptTable1(techniqueDatabase.table14a)
                setBuildArray(listOf(4, 4, null, 4, 0, 1))
                setElementList(listOf(Element.Light, Element.Dark))
            }

            //Combat Maneuvers and Aiming
            15 -> {
                setHeader("Precision")
                setUseTable(techniqueDatabase.table15)
                setBuildArray(listOf(null, 0, 1, 2, 2, 2))
                setElementList(listOf(Element.Air))
            }

            //Armor Increase
            16 -> {
                setHeader("AT")
                setUseTable(techniqueDatabase.table16)
                setBuildArray(listOf(2, null, 3, 0, 1, 2))
                setElementList(listOf(Element.Water, Element.Earth, Element.Light))
            }

            //Armor Destruction
            17 -> {
                setHeader("Reduction")
                setUseTable(techniqueDatabase.table17)
                setBuildArray(listOf(0, 2, null, 2, 1, 2))
                setElementList(listOf(Element.Fire, Element.Dark))
            }

            //Breakage Augmentation
            18 -> {
                setHeader("Breakage")
                setUseTable(techniqueDatabase.table18)
                setBuildArray(listOf(0, 4, null, 2, 2, 1))
                setElementList(listOf(Element.Fire, Element.Earth))
            }

            //Fortitude Augmentation
            19 -> {
                setHeader("Fortitude")
                setUseTable(techniqueDatabase.table19)
                setBuildArray(listOf(0, 4, null, 2, 2, 1))
                setElementList(listOf(Element.Fire, Element.Earth))
            }

            //Long-Distance Attack
            20 -> {
                setHeader("Distance")
                setUseTable(techniqueDatabase.table20)
                setBuildArray(listOf(null, 2, 3, 4, 0, 1))
                setElementList(listOf(Element.Fire, Element.Water, Element.Air))
            }

            //Area Attack
            21 -> {
                setHeader("Radius")
                setUseTable(techniqueDatabase.table21)
                setOptHeader1("Optional Advantage: Target Choice")
                setOptTable1(techniqueDatabase.table21a)
                setBuildArray(listOf(null, 2, 3, 3, 0, 1))
                setElementList(listOf(Element.Fire, Element.Light, Element.Dark))
            }

            //Automatic Transportation
            22 -> {
                setHeader("Distance")
                setUseTable(techniqueDatabase.table22)
                setBuildArray(listOf(2, 2, 0, 2, 3, null))
                setElementList(listOf(Element.Air, Element.Light, Element.Dark))
            }

            //Critical Enhancement
            23 -> {
                setHeader("Critical")
                setUseTable(techniqueDatabase.table23)
                setOptHeader1("Optional Advantage: Automatic Critical")
                setOptTable1(techniqueDatabase.table23a)
                setBuildArray(listOf(1, 2, null, 2, 0, 1))
                setElementList(listOf(Element.Fire, Element.Earth))
            }

            //Physical Ki Weapons
            24 -> {
                setHeader("Quality")
                setUseTable(techniqueDatabase.table24)
                setOptHeader1("Optional Advantage: Projectiles")
                setOptTable1(techniqueDatabase.table24a)
                setBuildArray(listOf(2, 3, null, 1, 0, 1))
                setElementList(listOf(Element.Earth, Element.Light, Element.Dark))
            }

            //Trapping
            25 -> {
                setHeader("Trap")
                setUseTable(techniqueDatabase.table25)
                setBuildArray(listOf(0, 1, null, 2, 2, 2))
                setElementList(listOf(Element.Earth))
            }

            //Projection
            26 -> {
                setHeader("Projection")
                setUseTable(techniqueDatabase.table26)
                setBuildArray(listOf(0, 3, null, 2, 1, 1))
                setElementList(listOf(Element.Fire, Element.Earth))
            }

            //Energy Shield
            27 -> {
                setHeader("LP")
                setUseTable(techniqueDatabase.table27)
                setBuildArray(listOf(2, 3, null, 2, 0, 1))
                setElementList(listOf(Element.Water, Element.Light))
            }

            //Intangibility
            28 -> {
                setHeader("Effect")
                setUseTable(techniqueDatabase.table28)
                setBuildArray(listOf(3, 3, null, 3, 0, 1))
                setElementList(listOf(Element.Water, Element.Light, Element.Dark))
            }

            //Mirage
            29 -> {
                setHeader("Mirages")
                setUseTable(techniqueDatabase.table29)
                setOptHeader1("Optional advantage: Non-Detection")
                setOptTable1(techniqueDatabase.table29a)
                setBuildArray(listOf(null, 3, 2, 3, 1, 0))
                setElementList(listOf(Element.Water, Element.Dark))
            }

            //Attack Mirroring
            30 -> {
                setHeader("Effect")
                setUseTable(techniqueDatabase.table30)
                setOptHeader1("Optional Advantage: Target Choice")
                setOptTable1(listOf(TechniqueTableData("Target Choice", 2, 2, 10, 2, 1)))
                setOptHeader2("Optional Advantage: Mirroring Esoteric Abilities")
                setOptTable2(techniqueDatabase.table30a)
                setBuildArray(listOf(2, 3, 3, null, 0, 1))
                setElementList(listOf(Element.Water, Element.Light, Element.Dark))
            }

            //Energy Damaging Attack
            31 -> {
                setHeader("Attack")
                setUseTable(techniqueDatabase.table31)
                setBuildArray(listOf(3, 3, null, 2, 0, 1))
                setElementList(listOf(Element.Fire, Element.Light, Element.Dark))
            }

            //Elemental Attack
            32 -> {
                setHeader("Attack")
                setUseTable(techniqueDatabase.table32)
                setOptHeader1("Select Element: ")
                setOptElement(elementAttackList)
                setBuildArray(listOf(3, 3, null, 2, 0, 1))
                setElementList(listOf())
            }

            //Supernatural Attack
            33 -> {
                setHeader("Attack")
                setUseTable(techniqueDatabase.table33)
                setBuildArray(listOf(3, 3, null, 2, 0, 1))
                setElementList(listOf(Element.Light, Element.Dark))
            }

            //Damage Resistance
            34 -> {
                setHeader("LP")
                setUseTable(techniqueDatabase.table34)
                setBuildArray(listOf(3, 3, null, 0, 3, 1))
                setElementList(listOf(Element.Earth))
            }

            //ElementalBinding
            35 -> {
                setUseTable(techniqueDatabase.table35)
                setOptHeader1("Select Element(s): ")
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

            else -> {tableClear()}
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
    fun attemptTechAddition(): String?{
        //retrieve the effect the user has selected
        val addedTechniques = getSelectedEffects()

        //initialize additional cost of new additions
        var additionCost = 0

        //for each valid technique selection
        addedTechniques.forEach{
            if(it != null){
                //attempt to add the effect
                val newInput =
                    customTechnique.validEffectAddition(it, kiFragVM.getMartialRemaining() - additionCost)

                //add cost if no error
                if(newInput == null){
                    additionCost += it.mkCost
                }

                //return error if failed
                else
                    return newInput
            }
        }

        //for each valid technique selection
        addedTechniques.forEach{
            if(it != null) {
                //retrieve the technique's element
                it.elements = getSelectedElement(it)

                //add disadvantage flag if adding elemental binding
                if(techniqueIndex.value == 35) it.elements += Element.Free

                //add effects to technique
                customTechnique.givenAbilities.add(it)
            }
        }

        //reset dropdown and terminate method
        setTechniqueIndex(0)
        return null
    }

    /**
     * Determines that the effect data can be added to the custom technique.
     *
     * @param effectInput effect data to attempt to add to the character
     * @return error message if any failure occurs
     */
    fun validCheckInput(effectInput: TechniqueTableData): String?{
        return customTechnique.validEffectAddition(dataToEffect(effectInput), kiFragVM.getMartialRemaining())
    }

    /**
     * Retrieve the technique effects the user has selected.
     *
     * @return list of effects selected by the user
     */
    fun getSelectedEffects(): List<TechniqueEffect?>{
        //initialize output list
        val output = mutableListOf<TechniqueEffect?>()

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

        //return list of selections
        return output.toList()
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
            if(it != null)
                output += it.mkCost
        }

        //return final cost
        return output
    }

    /**
     * Converts a technique table data item into a technique effect.
     *
     * @param item technique table data to convert
     * @return effect to return from the table data
     */
    private fun dataToEffect(item: TechniqueTableData): TechniqueEffect{
        //get the name of the technique effect
        val nameString = listSource.value[techniqueIndex.value]

        //initialize the build for the technique
        val defaultBuild = mutableListOf(0, 0, 0, 0, 0, 0)

        //set the default value of the build array
        if(buildArray.value.indexOf(0) >= 0){
            if(pickingPrimary.value)
                defaultBuild[buildArray.value.indexOf(0)] = item.primaryCost
            else
                defaultBuild[buildArray.value.indexOf(0)] = item.secondaryCost
        }

        //return the composed technique
        return TechniqueEffect(
            nameString,
            item.effect,
            item.mkCost,
            item.maintCost,
            Pair(item.primaryCost, item.secondaryCost),
            defaultBuild,
            buildArray.value,
            elementList.value.toMutableList(),
            item.level
        )
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
        private val _totalDisplay = MutableStateFlow("")
        val totalDisplay = _totalDisplay.asStateFlow()

        /**
         * Sets the value of the display for the total build.
         */
        fun setTotalDisplay(){_totalDisplay.update{customTechVM.gatherIndex(index).toString()}}
    }

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
                customTechnique.givenAbilities.remove(customTechnique.getAbility(it.key.name))
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
            editBuildList += BuildPackage(it, context)
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
     * Retrieves the total maintenance value of the custom technique.
     *
     * @return technique's maintenance cost
     */
    fun getMaintenanceTotal(): Int{return customTechnique.maintTotal()}

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
        customTechnique.name = input
    }

    /**
     * Sets the custom technique's description to the given input.
     *
     * @param input description to apply to the technique
     */
    fun setTechniqueDesc(input: String){
        _techniqueDesc.update{input}
        customTechnique.description = input
    }

    /**
     * Retrieves the custom technique being made here.
     *
     * @return the whole technique being worked on.
     */
    fun getCustomTechnique(): Technique{return customTechnique}

    /**
     * Closes the custom technique dialog.
     */
    fun closeDialog(){kiFragVM.toggleCustomTechOpen()}

    /**
     * Batch of build input data associated with the inputted effect.
     *
     * @param input associated technique effect
     * @param context source of the resources used
     */
    class BuildPackage(
        val input: TechniqueEffect,
        val context: Context
    ){
        //initialize all build strings for this effect
        val buildItems = mutableListOf<BuildItem>()

        init{
            //add a build item for each valid index input for this effect
            for(index in 0..5){
                if(input.buildAdditions[index] != null)
                    buildItems +=
                        BuildItem(
                            input,
                            context.resources.getStringArray(R.array.primaryCharArray)[index],
                            index
                        )
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
        }

        /**
         * Set the value to be shown in the ki build display.
         *
         * @param input value to change the display to
         */
        fun setDisplay(input: String){_display.update{input}}
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
        private val customTechnique: Technique,
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