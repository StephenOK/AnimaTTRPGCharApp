package com.example.animabuilder.view_models.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.Magic
import com.example.animabuilder.character_creation.attributes.magic.spells.FreeSpell
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.magic.spells.spellbook.FreeBook
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the character's magical abilities.
 * Works on variables in the corresponding magic fragment.
 *
 * @param magic character's magic abilities section
 * @param charInstance full character object
 */
class MagicFragmentViewModel(
    private val magic: Magic,
    private val charInstance: BaseCharacter
): ViewModel() {
    //initialize the character's bought zeon input
    private val _boughtZeonString = MutableStateFlow(magic.boughtZeon.value.toString())
    val boughtZeonString = _boughtZeonString.asStateFlow()

    //initialize the character's maximum zeon display
    private val _maxZeonString = MutableStateFlow(magic.zeonMax.value.toString())
    val maxZeonString = _maxZeonString.asStateFlow()

    //initialize the character's zeon recovery string
    private val _zeonRecoverString = MutableStateFlow(magic.magicRecoveryTotal.value.toString())
    val zeonRecoveryString = _zeonRecoverString.asStateFlow()

    //initialize the character's innate magic display
    private val _innateMagic = MutableStateFlow(magic.innateMagic.value.toString())
    val innateMagic = _innateMagic.asStateFlow()

    //initialize the character's magic projection imbalance input
    private val _projectionImbalance = MutableStateFlow(magic.magProjImbalance.value.toString())
    val projectionImbalance = _projectionImbalance.asStateFlow()

    //initialize the tracker of the character's imbalance bias
    private val _imbalanceIsAttack = MutableStateFlow(magic.imbalanceIsAttack.value)
    val imbalanceIsAttack = _imbalanceIsAttack.asStateFlow()

    //initialize the display of the character's imbalance bias
    private val _imbalanceTypeString = MutableStateFlow(if(magic.imbalanceIsAttack.value)"Attack" else "Block")
    val imbalanceTypeString = _imbalanceTypeString.asStateFlow()

    //initialize the displays for the character's offensive and defensive magic projections
    private val _offenseImbalance = MutableStateFlow("")
    val offenseImbalance = _offenseImbalance.asStateFlow()
    private val _defenseImbalance = MutableStateFlow("")
    val defenseImbalance = _defenseImbalance.asStateFlow()

    //initialize the display for the character's magic levels spent
    private val _magicLevelSpent = MutableStateFlow(magic.magicLevelSpent.value.toString())
    val magicLevelSpent = _magicLevelSpent.asStateFlow()

    //initialize open state of the free spell exchange dialog
    private val _freeExchangeOpen = MutableStateFlow(false)
    val freeExchangeOpen = _freeExchangeOpen.asStateFlow()

    //initialize element of the free spell being changed
    private val _freeElement = MutableStateFlow(Element.Free)
    val freeElement = _freeElement.asStateFlow()

    //initialize level of the free spell being changed
    private val _freeLevel = MutableStateFlow(4)
    val freeLevel = _freeLevel.asStateFlow()

    //initialize spell being changed
    private val _selectedFreeSpell = MutableStateFlow<FreeSpell?>(null)
    val selectedFreeSpell = _selectedFreeSpell.asStateFlow()

    private val _detailAlertOpen = MutableStateFlow(false)
    val detailAlertOpen = _detailAlertOpen.asStateFlow()

    private val _detailTitle = MutableStateFlow("")
    val detailTitle = _detailTitle.asStateFlow()

    private val _detailItem = MutableStateFlow<Spell?>(null)
    val detailItem = _detailItem.asStateFlow()

    fun toggleDetailAlertOpen(){_detailAlertOpen.update{!detailAlertOpen.value}}

    fun setDetailItem(item: Spell){
        _detailTitle.update{item.name}
        _detailItem.update{item}
    }

    fun isGifted(): Boolean{
        return charInstance.advantageRecord.getAdvantage("The Gift") != null
    }

    //create item to manage zeon accumulation
    private val zeonAccumulation = ZeonPurchaseItemData(
        R.string.zeonAccumulationLabel,
        {magic.baseZeonAcc.value},
        {magic.zeonAccMult.value},
        {magic.zeonAccTotal.value},
        {
            if(it >= 1){
                magic.buyZeonAcc(it)
                _zeonRecoverString.update{magic.magicRecoveryTotal.value.toString()}
                _innateMagic.update{magic.innateMagic.value.toString()}
            }
        },
        {Color.Black}
    ) {it.update{magic.zeonAccTotal.value.toString()}}

    //create item to manage zeon projection
    private val zeonProjection = ZeonPurchaseItemData(
        R.string.magProjectionLabel,
        {charInstance.primaryList.dex.outputMod.value},
        {magic.boughtMagProjection.value},
        {magic.magProjTotal.value},
        {
            magic.buyMagProj(it)
            refreshImbalance(imbalanceIsAttack.value)
        },
        {
            if(magic.getValidProjection())
                Color.Black
            else
                Color.Red
        }
    ){it.update{magic.magProjTotal.value.toString()}}

    //gather purchase data created
    val allPurchaseData = listOf(zeonAccumulation, zeonProjection)

    //create manager object for each spellbook
    private val lightBook = SpellRowData(
        magic,
        this,
        {magic.pointsInLightBook.value},
        Element.Light,
        magic.lightBook.fullBook
    )

    private val darkBook = SpellRowData(
        magic,
        this,
        {magic.pointsInDarkBook.value},
        Element.Dark,
        magic.darkBook.fullBook
    )

    private val creationBook = SpellRowData(
        magic,
        this,
        {magic.pointsInCreateBook.value},
        Element.Creation,
        magic.creationBook.fullBook
    )

    private val destructionBook = SpellRowData(
        magic,
        this,
        {magic.pointsInDestructBook.value},
        Element.Destruction,
        magic.destructionBook.fullBook
    )

    private val airBook = SpellRowData(
        magic,
        this,
        {magic.pointsInAirBook.value},
        Element.Air,
        magic.airBook.fullBook
    )

    private val earthBook = SpellRowData(
        magic,
        this,
        {magic.pointsInEarthBook.value},
        Element.Earth,
        magic.earthBook.fullBook
    )

    private val waterBook = SpellRowData(
        magic,
        this,
        {magic.pointsInWaterBook.value},
        Element.Water,
        magic.waterBook.fullBook
    )

    private val fireBook = SpellRowData(
        magic,
        this,
        {magic.pointsInFireBook.value},
        Element.Fire,
        magic.fireBook.fullBook
    )

    private val essenceBook = SpellRowData(
        magic,
        this,
        {magic.pointsInEssenceBook.value},
        Element.Essence,
        magic.essenceBook.fullBook
    )

    private val illusionBook = SpellRowData(
        magic,
        this,
        {magic.pointsInIllusionBook.value},
        Element.Illusion,
        magic.illusionBook.fullBook
    )

    private val necromancyBook = SpellRowData(
        magic,
        this,
        {magic.pointsInNecroBook.value},
        Element.Necromancy,
        magic.necromancyBook.fullBook
    )

    //gather all books in one place
    val allBooks = listOf(lightBook, darkBook, creationBook, destructionBook, airBook, earthBook,
        waterBook, fireBook, essenceBook, illusionBook, necromancyBook)

    //initialize character's learned spells
    val heldSpells = mutableStateListOf<Spell>()

    /**
     * Refresh the full list of the character's learned spells
     */
    fun updateHeldSpells(){
        heldSpells.clear()
        heldSpells.addAll(magic.spellList)
    }

    /**
     * Retrieve the inputted free spell's associated element.
     *
     * @param input free spell to check
     * @return element associated with this spell
     */
    fun getFreeElement(input: FreeSpell): Element{return magic.findFreeSpellElement(input)}

    /**
     * Retrieves the base zeon points the character has.
     *
     * @return character's base zeon
     */
    fun getBaseZeon(): Int{return magic.baseZeon.value}

    /**
     * Retrieves the zeon the character gained from levels.
     *
     * @return zeon points gained from class levels
     */
    fun getClassZeon(): Int{return magic.zeonFromClass.value}

    /**
     * Retrieves the book of free spells.
     *
     * @return free spell record
     */
    fun getFreeSpellbook(): FreeBook{return magic.freeBook}

    /**
     * Determine if the character is holding the inputted spell.
     *
     * @param item spell to determine the character has
     * @return true if the character has learned this spell
     */
    fun getSpellHeld(item: Spell): Boolean{return magic.hasCopyOf(item)}

    /**
     * Determines if the character is holding the inputted free spell.
     *
     * @param level free spell's level
     * @param element free spell's associated element
     * @return true if the character has learned this spell
     */
    fun getFreeSpellHeld(level: Int, element: Element): Boolean{
        return magic.hasCopyOf(magic.getFreeSpell(level, element))
    }

    /**
     * Add or remove the spell to the character's learned list.
     *
     * @param item spell to change the learned state of
     */
    fun changeIndividualSpell(item: Spell){
        magic.changeIndividualSpell(item, !magic.individualSpells.contains(item))
        updateHeldSpells()
    }

    /**
     * Add or remove the free spell to the character's learned list.
     *
     * @param level free spell's level
     * @param element free spell's associated element
     */
    fun changeIndividualFreeSpell(level: Int, element: Element){
        magic.changeIndividualFreeSpell(level, element, !getFreeSpellHeld(level, element))
        updateHeldSpells()
    }

    /**
     * Add the user's selected free spell item to the character.
     */
    fun addFreeSpell(){
        if(selectedFreeSpell.value != null){
            //create the free spell
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

            //add it to the character
            magic.addFreeSpell(item, freeElement.value)
            updateHeldSpells()

            //close the free exchange dialog
            toggleFreeExchangeOpen()
        }
    }

    /**
     * Change the character's bought zeon input.
     *
     * @param input value to set the bought zeon to
     */
    fun setBoughtZeonString(input: Int){
        magic.buyZeon(input)
        setBoughtZeonString(input.toString())
        _maxZeonString.update{magic.zeonMax.value.toString()}
    }

    /**
     * Change the character's bought zeon display.
     *
     * @param input new value to display
     */
    fun setBoughtZeonString(input: String){
        _boughtZeonString.update{input}
    }

    /**
     * Change the character's magic imbalance input.
     *
     * @param input value to set the magic imbalance to
     */
    fun setProjectionImbalance(input: Int){
        magic.magProjImbalance.value = input
        setProjectionImbalance(input.toString())
        refreshImbalance(imbalanceIsAttack.value)
    }

    /**
     * Change the character's magic imbalance display.
     *
     * @param input new value to display
     */
    fun setProjectionImbalance(input: String){_projectionImbalance.update{input}}

    /**
     * Sets the magic imbalance bias to the inputted value.
     *
     * @param input true if setting bias to offense
     */
    fun setImbalanceIsAttack(input: Boolean){
        _imbalanceIsAttack.update{input}
        refreshImbalance(input)
        _imbalanceTypeString.update{if(input) "Offense" else "Defense"}
    }

    /**
     * Updates the offense and defense projections.
     */
    private fun refreshImbalance(input: Boolean){
        _offenseImbalance.update{determineImbalanceValue(input).toString()}
        _defenseImbalance.update{determineImbalanceValue(!input).toString()}
    }

    /**
     * Retrieve the maximum magic level the character can spend.
     *
     * @return the maximum magic level string
     */
    fun getMagicLevelMax(): String{return magic.magicLevelMax.value.toString()}

    /**
     * Sets the spent magic level display to the character's held value.
     */
    fun setMagicLevelSpent(){_magicLevelSpent.update{magic.magicLevelSpent.value.toString()}}

    //initialize list of primary element checkboxes
    val primaryElementBoxes = mutableMapOf<Element, MutableState<Boolean>>()

    /**
     * Adds a primary element checkbox to the master list.
     *
     * @param input element to add to the master list
     */
    private fun addPrimaryElementBox(input: Element){
        primaryElementBoxes += Pair(input, mutableStateOf(magic.primaryElementList.contains(input)))
    }

    /**
     * Attempt to change the character's primary element as indicated by the user.
     *
     * @param item element to change the state of
     * @param input value to attempt to change it to
     */
    fun changePrimaryBook(item: Element, input: Boolean){
        magic.changePrimaryBook(item, input)
        reflectPrimaryElement()
        setMagicLevelSpent()
    }

    /**
     * Change the primary element checkboxes to reflect the character's primary element list.
     */
    fun reflectPrimaryElement(){
        primaryElementBoxes.forEach{
            it.value.value = magic.primaryElementList.contains(it.key)
        }
    }

    /**
     * Calculates the character's imbalance value depending on the projection bias.
     *
     * @param additionMade true if the bias favors this item
     * @return actual projection value determined
     */
    private fun determineImbalanceValue(additionMade: Boolean): Int{
        return if(additionMade)
            magic.magProjTotal.value + magic.magProjImbalance.value
        else
            magic.magProjTotal.value - magic.magProjImbalance.value
    }

    /**
     * Changes the free spell exchange's open state.
     */
    fun toggleFreeExchangeOpen() {_freeExchangeOpen.update{!freeExchangeOpen.value}}

    /**
     * Set the free spell's element to the indicated value.
     *
     * @param input element to apply to the free spell
     */
    private fun setFreeElement(input: Element){_freeElement.update{input}}

    /**
     * Set the free spell's level to the indicated value.
     *
     * @param input level to apply to the free spell
     */
    fun setFreeLevel(input: Int){_freeLevel.update{input}}

    /**
     * Attempt to open the free spell exchange dialog.
     *
     * @param freeSpell spell to set for choosing the free spell of
     * @return true if cannot open dialog
     */
    fun tryExchangeOpen(
        freeSpell: FreeSpell
    ): Boolean{
        //terminate if character has Magic Ties disadvantage
        if(magic.magicTies.value)
            return true

        //set free spell values
        setFreeElement(getFreeElement(freeSpell))
        setFreeLevel(freeSpell.level)
        toggleFreeExchangeOpen()

        //terminate process
        return false
    }

    /**
     * Sets the free spell to the inputted item.
     *
     * @param input free spell to set
     */
    fun setSelectedFreeSpell(input: FreeSpell?){_selectedFreeSpell.update{input}}

    /**
     * Gets a color based on whether the character can cast the indicated spell.
     *
     * @param input spell to check the castability of
     */
    fun getCastableColor(input: Int): Color{
        //return red if character cannot cast it
        return if((input in 81..90 && charInstance.gnosis.value < 25) ||
            (input > 90 && charInstance.gnosis.value < 40))
            Color.Red

        //return black if castable
        else Color.Black
    }

    /**
     * Class that holds data on a zeon purchase item.
     *
     * @param nameRef string reference that titles this object
     * @param baseInput base value of the stat
     * @param boughtInput initial bought value for this item
     * @param totalInput initial total value for this item
     * @param buyItem function to run on a change in the purchase amount
     * @param updateTotal function to run on the change of the total output
     */
    class ZeonPurchaseItemData(
        val nameRef: Int,
        val baseInput: () -> Int,
        val boughtInput: () -> Int,
        totalInput: () -> Int,
        val buyItem: (Int) -> Unit,
        val changeColor: () -> Color,
        val updateTotal: (MutableStateFlow<String>) -> Unit
    ){
        //initialize bought amount input
        private val _boughtString = MutableStateFlow(boughtInput().toString())
        val boughtString = _boughtString.asStateFlow()

        //initialize total amount display
        private val _totalString = MutableStateFlow(totalInput().toString())
        val totalString = _totalString.asStateFlow()

        //initialize the color of the displayed text
        private val _textColor = MutableStateFlow(changeColor())
        val textColor = _textColor.asStateFlow()

        /**
         * Set the bought amount to the desired input.
         *
         * @param input value to set the bought amount to
         */
        fun setBoughtString(input: Int){
            buyItem(input)
            setNewColor()
            setBoughtString(input.toString())
            updateTotal(_totalString)
        }

        /**
         * Set the bought display to the inputted value.
         *
         * @param input string to now display
         */
        fun setBoughtString(input: String){_boughtString.update{input}}

        /**
         * Sets the color of this item's text to the indicated value.
         */
        fun setNewColor(){_textColor.update{changeColor()}}

        /**
         * Refreshes the input and total for this item's data.
         */
        fun refreshItem(){
            setBoughtString(boughtInput())
        }
    }

    /**
     * Data in regards to the character's investment in a particular spell book.
     *
     * @param magic character's magic abilities
     * @param magFragVM magic view model this object is contained in
     * @param pointsIn initial investment in this book
     * @param spellElement book element of this object
     * @param spellList spells associated with this book
     */
    class SpellRowData(
        val magic: Magic,
        private val magFragVM: MagicFragmentViewModel,
        val pointsIn: () -> Int,
        val spellElement: Element,
        val spellList: List<Spell?>
    ){
        //initialize value of investment levels
        private val _elementInvestment = MutableStateFlow(pointsIn().toString())
        val elementInvestment = _elementInvestment.asStateFlow()

        //initialize open state of this list
        private val _listOpen = MutableStateFlow(false)
        val listOpen = _listOpen.asStateFlow()

        /**
         * Sets the number of magic levels invested in this book.
         *
         * @param input number of levels to invest
         */
        fun setElementInvestment(input: Int){
            magic.buyBookLevels(input, spellElement)
            setElementInvestment(input.toString())
            magFragVM.updateHeldSpells()
        }

        /**
         * Sets the display of the number of spell levels invested in this book.
         *
         * @param input string to display
         */
        fun setElementInvestment(input: String){
            _elementInvestment.update{input}
            magFragVM.setMagicLevelSpent()
            magFragVM.reflectPrimaryElement()
        }

        /**
         * Changes the open state of this item's spell list.
         */
        fun toggleListOpen() {_listOpen.update{!listOpen.value}}

        fun refreshItem(){
            _elementInvestment.update{pointsIn().toString()}
        }
    }

    init{
        //set the initial imbalance bias
        setImbalanceIsAttack(magic.imbalanceIsAttack.value)

        //initialize all book primary checkboxes
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

        updateHeldSpells()
    }

    /**
     * Refreshes all items on this page when it is loaded.
     */
    fun refreshPage(){
        setBoughtZeonString(magic.boughtZeon.value)
        allPurchaseData.forEach{it.refreshItem()}
        setProjectionImbalance(magic.magProjImbalance.value.toString())
        allBooks.forEach{it.refreshItem()}
        reflectPrimaryElement()
        updateHeldSpells()
    }
}