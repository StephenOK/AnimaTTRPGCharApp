package com.example.animabuilder.view_models

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.animabuilder.R
import com.example.animabuilder.TechniqueTableData
import com.example.animabuilder.TechniqueTableDataRecord
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.Technique
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.TechniqueEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CustomTechniqueViewModel(
    val context: Context,
    val kiFragVM: KiFragmentViewModel
) {
    val customTechnique = Technique(
        "",
        "",
        1,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf()
    )

    private val techniqueDatabase = TechniqueTableDataRecord()

    private val _customPageNum = MutableStateFlow(1)

    private val _costMinimum = MutableStateFlow(20)
    private val _costMaximum = MutableStateFlow(50)

    private val _pickingPrimary = MutableStateFlow(false)

    private val _listSource = MutableStateFlow(context.resources.getStringArray(R.array.techniqueAbilities))
    private val _techniqueIndex = MutableStateFlow(0)

    private val _dropdownTitle = MutableStateFlow("--Select an ability --")
    private val _dropdownOpen = MutableStateFlow(false)
    private val _dropdownIcon = MutableStateFlow(Icons.Filled.KeyboardArrowDown)

    private val _buildArray = MutableStateFlow<List<Int?>>(listOf())
    private val _elementList = MutableStateFlow<List<Element>>(listOf())

    private val _header = MutableStateFlow<String?>(null)
    private val _optHeader1 = MutableStateFlow<String?>(null)
    private val _optHeader2 = MutableStateFlow<String?>(null)

    private val _useTable = MutableStateFlow<List<TechniqueTableData>?>(null)
    private val _optTable1 = MutableStateFlow<List<TechniqueTableData>?>(null)
    private val _optTable2 = MutableStateFlow<List<TechniqueTableData>?>(null)
    private val _optElement = MutableStateFlow<List<Element>?>(null)

    private val _maintenanceSelection = MutableStateFlow(false)

    private val _techniqueName = MutableStateFlow(customTechnique.name)
    private val _techniqueDesc = MutableStateFlow(customTechnique.description)

    val mainChecklist = mutableMapOf<TechniqueTableData, MutableState<Boolean>>()
    val opt1Checklist = mutableMapOf<TechniqueTableData, MutableState<Boolean>>()
    val opt2Checklist = mutableMapOf<TechniqueTableData, MutableState<Boolean>>()
    val elementChecklist = mutableMapOf<Element, MutableState<Boolean>>()

    val deletionChecklist = mutableMapOf<TechniqueEffect, MutableState<Boolean>>()

    val customPageNum = _customPageNum.asStateFlow()

    val costMinimum = _costMinimum.asStateFlow()
    val costMaximum = _costMaximum.asStateFlow()

    val pickingPrimary = _pickingPrimary.asStateFlow()

    val listSource = _listSource.asStateFlow()
    val techniqueIndex = _techniqueIndex.asStateFlow()
    val dropdownTitle = _dropdownTitle.asStateFlow()
    val dropdownOpen = _dropdownOpen.asStateFlow()
    val dropdownIcon = _dropdownIcon.asStateFlow()

    val buildArray = _buildArray.asStateFlow()
    val elementList = _elementList.asStateFlow()

    val header = _header.asStateFlow()
    val optHeader1 = _optHeader1.asStateFlow()
    val optHeader2 = _optHeader2.asStateFlow()

    val useTable = _useTable.asStateFlow()
    val optTable1 = _optTable1.asStateFlow()
    val optTable2 = _optTable2.asStateFlow()
    val optElement = _optElement.asStateFlow()

    val maintenanceSelection = _maintenanceSelection.asStateFlow()

    val techniqueName = _techniqueName.asStateFlow()
    val techniqueDesc = _techniqueDesc.asStateFlow()

    val strAccTotal = AccTotalString(this, 0)
    val dexAccTotal = AccTotalString(this, 1)
    val agiAccTotal = AccTotalString(this, 2)
    val conAccTotal = AccTotalString(this, 3)
    val powAccTotal = AccTotalString(this, 4)
    val wpAccTotal = AccTotalString(this, 5)

    val allAccs = listOf(strAccTotal, dexAccTotal, agiAccTotal, conAccTotal, powAccTotal, wpAccTotal)

    val strMaintInput = MaintInput(
        R.string.strText,
        customTechnique,
        0
    )

    val dexMaintInput = MaintInput(
        R.string.dexText,
        customTechnique,
        1
    )

    val agiMaintInput = MaintInput(
        R.string.agiText,
        customTechnique,
        2
    )

    val conMaintInput = MaintInput(
        R.string.conText,
        customTechnique,
        3
    )

    val powMaintInput = MaintInput(
        R.string.powText,
        customTechnique,
        4
    )

    val wpMaintInput = MaintInput(
        R.string.wpText,
        customTechnique,
        5
    )

    val allMaintInputs = listOf(strMaintInput, dexMaintInput, agiMaintInput, conMaintInput, powMaintInput, wpMaintInput)

    val elementAttackList = listOf(Element.Fire, Element.Water, Element.Air, Element.Earth)
    val elementBindList = elementAttackList + listOf(Element.Light, Element.Dark)

    fun setCustomPageNum(input: Int){_customPageNum.update{input}}

    fun setTechniqueLevel(input: Int){
        customTechnique.level = input

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

    fun setCostMinimum(input: Int){_costMinimum.update{input}}
    fun setCostMaximum(input: Int){_costMaximum.update{input}}

    fun setPickingPrimary(input: Boolean){
        _pickingPrimary.update{input}
        if(input) _listSource.update{context.resources.getStringArray(R.array.techniqueAbilities)}
        else
            _listSource.update{
                context.resources.getStringArray(R.array.techniqueAbilities) +
                        context.resources.getStringArray(R.array.techniqueDisadvantages)
            }
    }

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

        mainChecklist.clear()
        opt1Checklist.clear()
        opt2Checklist.clear()
        elementChecklist.clear()

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

    fun setDropdownOpen(input: Boolean){
        _dropdownOpen.update{input}
        _dropdownIcon.update{
            if(input) Icons.Filled.KeyboardArrowUp
            else Icons.Filled.KeyboardArrowDown
        }
    }

    fun setBuildArray(input: List<Int?>){_buildArray.update{input}}
    fun setElementList(input: List<Element>){_elementList.update{input}}

    fun setHeader(input: String?){_header.update{input}}
    fun setOptHeader1(input: String?){_optHeader1.update{input}}
    fun setOptHeader2(input: String?){_optHeader2.update{input}}

    fun setUseTable(input: List<TechniqueTableData>?){_useTable.update{input}}
    fun setOptTable1(input: List<TechniqueTableData>?){_optTable1.update{input}}
    fun setOptTable2(input: List<TechniqueTableData>?){_optTable2.update{input}}
    fun setOptElement(input: List<Element>?){ _optElement.update{input}}

    fun tableClear(){
        setHeader(null)
        setOptHeader1(null)
        setOptHeader2(null)

        setUseTable(null)
        setOptTable1(null)
        setOptTable2(null)
        setOptElement(null)
    }

    fun attemptTechAddition(): String?{
        val addedTechniques = getSelectedEffects()
        var additionCost = 0

        addedTechniques.forEach{
            if(it != null){
                val newInput =
                    customTechnique.validEffectAddition(it, kiFragVM.ki.martialKnowledgeRemaining - additionCost)

                if(newInput == null){
                    additionCost += it.mkCost
                }

                else
                    return newInput
            }
        }

        addedTechniques.forEach{
            if(it != null) {
                it.elements = getSelectedElement(it)

                if(techniqueIndex.value == 35) it.elements += Element.Free

                customTechnique.givenAbilities.add(it)
            }
        }

        setTechniqueIndex(0)
        return null
    }

    fun getSelectedEffects(): List<TechniqueEffect?>{
        val output = mutableListOf<TechniqueEffect?>()

        mainChecklist.forEach{
            if(it.value.value){
                output.add(dataToEffect(it.key))
                return@forEach
            }
        }

        opt1Checklist.forEach{
            if(it.value.value){
                output.add(dataToEffect(it.key))
                return@forEach
            }
        }

        opt2Checklist.forEach{
            if(it.value.value){
                output.add(dataToEffect(it.key))
                return@forEach
            }
        }

        return output.toList()
    }

    fun getSelectionPrice(): Int{
        var output = 0

        getSelectedEffects().forEach{
            if(it != null)
                output += it.mkCost
        }

        return output
    }

    fun dataToEffect(item: TechniqueTableData): TechniqueEffect{
        val nameString = listSource.value[techniqueIndex.value]

        val defaultBuild = mutableListOf(0, 0, 0, 0, 0, 0)

        if(buildArray.value.indexOf(0) >= 0){
            if(pickingPrimary.value)
                defaultBuild[buildArray.value.indexOf(0)] = item.primaryCost
            else
                defaultBuild[buildArray.value.indexOf(0)] = item.secondaryCost
        }

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

    fun getSelectedElement(input: TechniqueEffect): MutableList<Element>{
        if(input.elements.isNotEmpty())
            return input.elements

        val output = mutableListOf<Element>()

        elementChecklist.forEach{
            if(it.value.value && !output.contains(it.key))
                output.add(it.key)
        }

        return output
    }

    fun clearInputList(input: MutableMap<TechniqueTableData, MutableState<Boolean>>){
        input.forEach{it.value.value = false}
    }

    fun clearElementChecks(){
        elementChecklist.forEach{
            it.value.value = false
        }
    }

    class AccTotalString(
        val customTechVM: CustomTechniqueViewModel,
        val index: Int
    ){
        private val _totalDisplay = MutableStateFlow("")
        val totalDisplay = _totalDisplay.asStateFlow()

        fun setTotalDisplay(){_totalDisplay.update{customTechVM.gatherIndex(index).toString()}}
    }

    fun findBuild(input: String): MutableList<Int>?{
        customTechnique.givenAbilities.forEach{
            if(it.name == input)
                return it.kiBuild
        }

        return null
    }

    fun gatherIndex(index: Int): Int{
        var output = 0
        customTechnique.givenAbilities.forEach{
            output += it.kiBuild[index]
        }

        return output
    }

    fun setDeletionChecklist(){
        deletionChecklist.clear()
        customTechnique.givenAbilities.forEach{
            deletionChecklist += Pair(it, mutableStateOf(false))
        }
    }

    fun deleteTechniques(){
        deletionChecklist.forEach{
            if(it.value.value){
                customTechnique.givenAbilities.remove(customTechnique.getAbility(it.key.name))
            }
        }

        customTechnique.fixPrimaryAbility()

        if(customTechnique.givenAbilities.isNotEmpty())
            setCustomPageNum(3)
        else
            setCustomPageNum(2)
    }

    fun setMaintenanceSelection(input: Boolean){
        if(!input){
            for(index in 0..5)
                customTechnique.maintArray[index] = 0
        }

        _maintenanceSelection.update{input}
    }

    fun setTechniqueName(input: String){
        _techniqueName.update{input}
        customTechnique.name = input
    }

    fun setTechniqueDesc(input: String){
        _techniqueDesc.update{input}
        customTechnique.description = input
    }

    fun closeDialog(){kiFragVM.setCustomTechOpen(false)}

    class MaintInput(
        val nameRef: Int,
        val customTechnique: Technique,
        val index: Int
    ){
        private val _displayValue = MutableStateFlow("0")
        val displayValue = _displayValue.asStateFlow()
        fun setDisplayValue(input: Int){
            customTechnique.maintArray[index] = input
            setDisplayValue(input.toString())
        }
        fun setDisplayValue(input: String){_displayValue.update{input}}
    }
}