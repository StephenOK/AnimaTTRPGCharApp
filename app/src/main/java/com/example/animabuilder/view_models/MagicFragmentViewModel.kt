package com.example.animabuilder.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.Magic
import com.example.animabuilder.character_creation.attributes.magic.spells.FreeSpell
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.magic.spells.spellbook.FreeBook
import com.example.animabuilder.character_creation.attributes.primary_abilities.PrimaryCharacteristic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MagicFragmentViewModel(
    private val magic: Magic,
    dex: PrimaryCharacteristic
): ViewModel() {
    private val _boughtZeonString = MutableStateFlow(magic.boughtZeon.toString())
    private val _maxZeonString = MutableStateFlow(magic.zeonMax.toString())

    private val _zeonRecoverString = MutableStateFlow(magic.magicRecoveryTotal.toString())

    private val _projectionImbalance = MutableStateFlow(magic.magProjImbalance.toString())
    private val _imbalanceIsAttack = MutableStateFlow(magic.imbalanceIsAttack)
    private val _imbalanceTypeString = MutableStateFlow(if(magic.imbalanceIsAttack)"Attack" else "Block")

    private val _offenseImbalance = MutableStateFlow("")
    private val _defenseImbalance = MutableStateFlow("")

    private val _magicLevelMax = MutableStateFlow(magic.magicLevelMax.toString())
    private val _magicLevelSpent = MutableStateFlow(magic.magicLevelSpent.toString())

    private val _freeExchangeOpen = MutableStateFlow(false)
    private val _freeElement = MutableStateFlow(Element.Free)
    private val _freeLevel = MutableStateFlow(4)

    private val _selectedFreeSpell = MutableStateFlow<FreeSpell?>(null)

    val boughtZeonString = _boughtZeonString.asStateFlow()
    val maxZeonString = _maxZeonString.asStateFlow()

    val zeonRecoveryString = _zeonRecoverString.asStateFlow()

    val projectionImbalance = _projectionImbalance.asStateFlow()
    val imbalanceIsAttack = _imbalanceIsAttack.asStateFlow()
    val imbalanceTypeString = _imbalanceTypeString.asStateFlow()

    val offenseImbalance = _offenseImbalance.asStateFlow()
    val defenseImbalance = _defenseImbalance.asStateFlow()

    val magicLevelMax = _magicLevelMax.asStateFlow()
    val magicLevelSpent = _magicLevelSpent.asStateFlow()

    val freeExchangeOpen = _freeExchangeOpen.asStateFlow()
    val freeElement = _freeElement.asStateFlow()
    val freeLevel = _freeLevel.asStateFlow()

    val selectedFreeSpell = _selectedFreeSpell.asStateFlow()

    val zeonAccumulation = ZeonPurchaseItemData(
        R.string.zeonAccumulationLabel,
        magic.baseZeonAcc,
        magic.zeonAccMult,
        magic.zeonAccTotal,
        {
            if(it >= 1){
                magic.buyZeonAcc(it)
                _zeonRecoverString.update{magic.magicRecoveryTotal.toString()}
            }
        }
    ) {it.update{magic.zeonAccTotal.toString()}}

    val zeonProjection = ZeonPurchaseItemData(
        R.string.magProjectionLabel,
        dex.outputMod,
        magic.boughtMagProjection,
        magic.magProjTotal,
        {
            magic.buyMagProj(it)
            refreshImbalance(imbalanceIsAttack.value)
        }
    ){it.update{magic.magProjTotal.toString()}}

    val allPurchaseData = listOf(zeonAccumulation, zeonProjection)

    val lightBook = SpellRowData(
        magic,
        this,
        magic.pointsInLightBook,
        Element.Light,
        magic.lightBook.fullBook
    )

    val darkBook = SpellRowData(
        magic,
        this,
        magic.pointsInDarkBook,
        Element.Dark,
        magic.darkBook.fullBook
    )

    val creationBook = SpellRowData(
        magic,
        this,
        magic.pointsInCreateBook,
        Element.Creation,
        magic.creationBook.fullBook
    )

    val destructionBook = SpellRowData(
        magic,
        this,
        magic.pointsInDestructBook,
        Element.Destruction,
        magic.destructionBook.fullBook
    )

    val airBook = SpellRowData(
        magic,
        this,
        magic.pointsInAirBook,
        Element.Air,
        magic.airBook.fullBook
    )

    val earthBook = SpellRowData(
        magic,
        this,
        magic.pointsInEarthBook,
        Element.Earth,
        magic.earthBook.fullBook
    )

    val waterBook = SpellRowData(
        magic,
        this,
        magic.pointsInWaterBook,
        Element.Water,
        magic.waterBook.fullBook
    )

    val fireBook = SpellRowData(
        magic,
        this,
        magic.pointsInFireBook,
        Element.Fire,
        magic.fireBook.fullBook
    )

    val essenceBook = SpellRowData(
        magic,
        this,
        magic.pointsInEssenceBook,
        Element.Essence,
        magic.essenceBook.fullBook
    )

    val illusionBook = SpellRowData(
        magic,
        this,
        magic.pointsInIllusionBook,
        Element.Illusion,
        magic.illusionBook.fullBook
    )

    val necromancyBook = SpellRowData(
        magic,
        this,
        magic.pointsInNecroBook,
        Element.Necromancy,
        magic.necromancyBook.fullBook
    )

    val allBooks = listOf(lightBook, darkBook, creationBook, destructionBook, airBook, earthBook,
        waterBook, fireBook, essenceBook, illusionBook, necromancyBook)

    val heldSpells = mutableStateListOf<Spell>()

    fun updateHeldSpells(){
        heldSpells.clear()
        heldSpells.addAll(magic.spellList)
    }

    fun getFreeElement(input: FreeSpell): Element{return magic.findFreeSpellElement(input)}
    fun getBaseZeon(): Int{return magic.baseZeon}
    fun getClassZeon(): Int{return magic.zeonFromClass}
    fun getFreeSpellbook(): FreeBook{return magic.freeBook}

    fun getSpellHeld(item: Spell): Boolean{return magic.hasCopyOf(item)}

    fun getFreeSpellHeld(level: Int, element: Element): Boolean{
        return magic.hasCopyOf(magic.getFreeSpell(level, element))
    }

    fun changeIndividualSpell(item: Spell){
        magic.changeIndividualSpell(item, !magic.individualSpells.contains(item))
        updateHeldSpells()
    }

    fun changeIndividualFreeSpell(level: Int, element: Element){
        magic.changeIndividualFreeSpell(level, element, !getFreeSpellHeld(level, element))
        updateHeldSpells()
    }

    fun addFreeSpell(){
        if(selectedFreeSpell.value != null){
            val item = FreeSpell(
                selectedFreeSpell.value!!.name,
                selectedFreeSpell.value!!.isActive,
                freeLevel.value,
                selectedFreeSpell.value!!.zCost,
                selectedFreeSpell.value!!.effect,
                selectedFreeSpell.value!!.addedEffect,
                selectedFreeSpell.value!!.zMax,
                selectedFreeSpell.value!!.maintenance,
                selectedFreeSpell.value!!.isDaily,
                selectedFreeSpell.value!!.type,
                selectedFreeSpell.value!!.forbiddenElements
            )

            magic.addFreeSpell(item, freeElement.value)
            updateHeldSpells()
            toggleFreeExchangeOpen()
        }
    }

    fun setBoughtZeonString(input: Int){
        magic.buyZeon(input)
        setBoughtZeonString(input.toString())
    }

    fun setBoughtZeonString(input: String){
        _boughtZeonString.update{input}
        _maxZeonString.update{magic.zeonMax.toString()}
    }

    fun setProjectionImbalance(input: Int){
        magic.magProjImbalance = input
        setProjectionImbalance(input.toString())
    }
    fun setProjectionImbalance(input: String){
        _projectionImbalance.update{input}
        refreshImbalance(imbalanceIsAttack.value)
    }
    fun setImbalanceIsAttack(input: Boolean){
        _imbalanceIsAttack.update{input}
        refreshImbalance(input)
        _imbalanceTypeString.update{if(input) "Offense" else "Defense"}
    }
    fun refreshImbalance(input: Boolean){
        _offenseImbalance.update{determineImbalanceValue(input).toString()}
        _defenseImbalance.update{determineImbalanceValue(!input).toString()}
    }

    fun setMagicLevelSpent(){_magicLevelSpent.update{magic.magicLevelSpent.toString()}}

    val primaryElementBoxes = mutableMapOf<Element, MutableState<Boolean>>()

    fun addPrimaryElementBox(input: Element){
        primaryElementBoxes += Pair(input, mutableStateOf(magic.primaryElementList.contains(input)))
    }

    fun changePrimaryBook(item: Element, input: Boolean){
        magic.changePrimaryBook(item, input)
        reflectPrimaryElement()
        setMagicLevelSpent()
    }

    fun reflectPrimaryElement(){
        primaryElementBoxes.forEach{
            it.value.value = magic.primaryElementList.contains(it.key)
        }
    }

    fun determineImbalanceValue(additionMade: Boolean): Int{
        return if(additionMade)
            magic.magProjTotal + magic.magProjImbalance
        else
            magic.magProjTotal - magic.magProjImbalance
    }

    fun toggleFreeExchangeOpen() {_freeExchangeOpen.update{!freeExchangeOpen.value}}
    fun setFreeElement(input: Element){_freeElement.update{input}}
    fun setFreeLevel(input: Int){_freeLevel.update{input}}

    fun tryExchangeOpen(
        freeSpell: FreeSpell
    ): Boolean{
        if(magic.magicTies)
            return true

        setFreeElement(getFreeElement(freeSpell))
        setFreeLevel(freeSpell.level)
        toggleFreeExchangeOpen()

        return false
    }

    fun setSelectedFreeSpell(input: FreeSpell?){_selectedFreeSpell.update{input}}

    class ZeonPurchaseItemData(
        val nameRef: Int,
        val baseInput: Int,
        boughtInput: Int,
        totalInput: Int,
        val buyItem: (Int) -> Unit,
        val updateTotal: (MutableStateFlow<String>) -> Unit
    ){
        private val _boughtString = MutableStateFlow(boughtInput.toString())
        private val _totalString = MutableStateFlow(totalInput.toString())

        val boughtString = _boughtString.asStateFlow()
        val totalString = _totalString.asStateFlow()

        fun setBoughtString(input: Int){
            buyItem(input)
            setBoughtString(input.toString())
            updateTotal(_totalString)
        }
        fun setBoughtString(input: String){_boughtString.update{input}}
    }

    class SpellRowData(
        val magic: Magic,
        val magFragVM: MagicFragmentViewModel,
        pointsIn: Int,
        val spellElement: Element,
        val spellList: List<Spell?>
    ){
        private val _elementInvestment = MutableStateFlow(pointsIn.toString())
        private val _listOpen = MutableStateFlow(false)

        val elementInvestment = _elementInvestment.asStateFlow()
        val listOpen = _listOpen.asStateFlow()

        fun setElementInvestment(input: Int){
            magic.buyBookLevels(input, spellElement)
            setElementInvestment(input.toString())
            magFragVM.updateHeldSpells()
        }
        fun setElementInvestment(input: String){_elementInvestment.update{input}}
        fun toggleListOpen() {_listOpen.update{!listOpen.value}}
    }

    init{
        setImbalanceIsAttack(magic.imbalanceIsAttack)
        updateHeldSpells()

        addPrimaryElementBox(Element.Light)
        addPrimaryElementBox(Element.Dark)
        addPrimaryElementBox(Element.Creation)
        addPrimaryElementBox(Element.Destruction)
        addPrimaryElementBox(Element.Air)
        addPrimaryElementBox(Element.Earth)
        addPrimaryElementBox(Element.Water)
        addPrimaryElementBox(Element.Fire)
        addPrimaryElementBox(Element.Essence)
        addPrimaryElementBox(Element.Illusion)
        addPrimaryElementBox(Element.Necromancy)
    }
}