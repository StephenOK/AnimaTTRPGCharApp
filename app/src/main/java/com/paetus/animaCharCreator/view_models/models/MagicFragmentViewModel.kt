package com.paetus.animaCharCreator.view_models.models

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.class_objects.CharClass
import com.paetus.animaCharCreator.character_creation.attributes.magic.Magic
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.FreeSpell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.MagicBook
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.FreeBook
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the character's magical abilities.
 * Works on variables in the corresponding magic fragment.
 *
 * @param magic character's magic abilities section
 * @param charInstance full character object
 * @param charClass state of this character's class
 */
class MagicFragmentViewModel(
    private val magic: Magic,
    private val charInstance: BaseCharacter,
    private val charClass: MutableState<CharClass>,
    val context: Context
): ViewModel() {
    //initialize the character's bought zeon input
    private val _boughtZeonString = MutableStateFlow(value = magic.boughtZeon.intValue.toString())
    val boughtZeonString = _boughtZeonString.asStateFlow()

    //initialize character's zeon point DP cost
    private val _boughtZeonDP = MutableStateFlow(value = "")
    val boughtZeonDP = _boughtZeonDP.asStateFlow()

    //initialize the character's maximum zeon display
    private val _maxZeonString = MutableStateFlow(value = magic.zeonMax.intValue.toString())
    val maxZeonString = _maxZeonString.asStateFlow()

    //initialize the character's zeon recovery string
    private val _zeonRecoverString = MutableStateFlow(value = magic.magicRecoveryTotal.intValue)
    val zeonRecoveryString = _zeonRecoverString.asStateFlow()

    //initialize the character's innate magic display
    private val _innateMagic = MutableStateFlow(value = magic.innateMagic.intValue)
    val innateMagic = _innateMagic.asStateFlow()

    //initialize the character's magic projection imbalance input
    private val _projectionImbalance = MutableStateFlow(value = magic.magProjImbalance.intValue.toString())
    val projectionImbalance = _projectionImbalance.asStateFlow()

    //initialize the tracker of the character's imbalance bias
    private val _imbalanceIsAttack = MutableStateFlow(value = magic.imbalanceIsAttack.value)
    val imbalanceIsAttack = _imbalanceIsAttack.asStateFlow()

    //initialize the display of the character's imbalance bias
    private val _imbalanceTypeString = MutableStateFlow(
        value =
            if(magic.imbalanceIsAttack.value) R.string.offenseLabel
            else R.string.defenseLabel
    )
    val imbalanceTypeString = _imbalanceTypeString.asStateFlow()

    //initialize the displays for the character's offensive and defensive magic projections
    private val _offenseImbalance = MutableStateFlow(value = 0)
    val offenseImbalance = _offenseImbalance.asStateFlow()
    private val _defenseImbalance = MutableStateFlow(value = 0)
    val defenseImbalance = _defenseImbalance.asStateFlow()

    //initialize the display for the character's magic levels spent
    private val _magicLevelSpent = MutableStateFlow(value = magic.magicLevelSpent.intValue)
    val magicLevelSpent = _magicLevelSpent.asStateFlow()

    //initialize open state of the free spell exchange dialog
    private val _freeExchangeOpen = MutableStateFlow(value = false)
    val freeExchangeOpen = _freeExchangeOpen.asStateFlow()

    //initialize element of the free spell being changed
    private val _freeElement = MutableStateFlow(value = Element.Free)
    val freeElement = _freeElement.asStateFlow()

    //initialize level of the free spell being changed
    private val _freeLevel = MutableStateFlow(value = 4)
    val freeLevel = _freeLevel.asStateFlow()

    //initialize spell being changed
    private val _selectedFreeSpell = MutableStateFlow<FreeSpell?>(value = null)
    val selectedFreeSpell = _selectedFreeSpell.asStateFlow()

    //initialize open state of the detail alert
    private val _detailAlertOpen = MutableStateFlow(value = false)
    val detailAlertOpen = _detailAlertOpen.asStateFlow()

    //initialize the title of the detail alert
    private val _detailTitle = MutableStateFlow(value = "")
    val detailTitle = _detailTitle.asStateFlow()

    //initialize the item displayed in the alert
    private val _detailItem = MutableStateFlow<Spell?>(value = null)
    val detailItem = _detailItem.asStateFlow()

    //initialize character's learned spells
    val heldSpells = mutableStateListOf<Spell>()

    /**
     * Change the character's bought zeon display.
     *
     * @param display new value to display
     */
    fun setBoughtZeonString(display: String){
        _boughtZeonString.update{display}
    }

    /**
     * Sets the display for the character's zeon cost DP.
     *
     * @param dpCost new value to display
     */
    fun setBoughtZeonDP(dpCost: String){_boughtZeonDP.update{dpCost}}

    /**
     * Change the character's bought zeon input.
     *
     * @param zeonBought value to set the bought zeon to
     */
    fun setBoughtZeonString(zeonBought: Int){
        magic.buyZeon(zeonBought)
        setBoughtZeonString(zeonBought.toString())
        _maxZeonString.update{magic.zeonMax.intValue.toString()}
    }

    /**
     * Change the character's magic imbalance input.
     *
     * @param imbalance value to set the magic imbalance to
     */
    fun setProjectionImbalance(imbalance: Int){
        magic.magProjImbalance.intValue = imbalance
        setProjectionImbalance(imbalance.toString())
        refreshImbalance(imbalanceIsAttack.value)
    }

    /**
     * Calculates the character's imbalance value depending on the projection bias.
     *
     * @param additionMade true if the bias favors this item
     * @return actual projection value determined
     */
    private fun determineImbalanceValue(additionMade: Boolean): Int{
        return if(additionMade)
            magic.magProjTotal.intValue + magic.magProjImbalance.intValue
        else
            magic.magProjTotal.intValue - magic.magProjImbalance.intValue
    }

    /**
     * Sets the spent magic level display to the character's held value.
     */
    fun setMagicLevelSpent(){
        magic.updateMagLevelSpent()
        _magicLevelSpent.update{magic.magicLevelSpent.intValue}
    }

    /**
     * Change the character's magic imbalance display.
     *
     * @param display new value to display
     */
    fun setProjectionImbalance(display: String){_projectionImbalance.update{display}}

    /**
     * Sets the magic imbalance bias to the inputted value.
     *
     * @param isOffense true if setting bias to offense
     */
    fun setImbalanceIsAttack(isOffense: Boolean){
        _imbalanceIsAttack.update{isOffense}
        refreshImbalance(isOffense)
        _imbalanceTypeString.update{if(isOffense) R.string.offenseLabel else R.string.defenseLabel}
    }

    /**
     * Updates the offense and defense projections.
     *
     * @param isOffense true if imbalance favors offense
     */
    private fun refreshImbalance(isOffense: Boolean){
        _offenseImbalance.update{determineImbalanceValue(isOffense)}
        _defenseImbalance.update{determineImbalanceValue(!isOffense)}
    }

    /**
     * Gets the spell data object associated with the inputted spell.
     *
     * @param spell item to find the data of
     * @return row data to find for this spell
     */
    fun getSpellData(spell: Spell): SpellRowData{
        //search for the spell in each book
        allBooks.forEach{book ->
            if(spell in book.magicBook.fullBook) return book
        }

        //return necromancy if nothing found
        return necromancyBook
    }

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
        setFreeElement(element = getFreeElement(freeSpell = freeSpell))
        setFreeLevel(lvlInput = freeSpell.level)

        //set the book the free spell will be added to
        val book = magic.getFreeSpellBook(freeSpell = freeSpell)
        allBooks.forEach{bookData ->
            if(bookData.magicBook == book){
                setFreeBookAddition(bookData = bookData)
                return@forEach
            }
        }

        toggleFreeExchangeOpen()

        //terminate process
        return false
    }

    /**
     * Changes the free spell exchange's open state.
     */
    fun toggleFreeExchangeOpen() {_freeExchangeOpen.update{!freeExchangeOpen.value}}

    /**
     * Set the free spell's element to the indicated value.
     *
     * @param element element to apply to the free spell
     */
    private fun setFreeElement(element: Element){_freeElement.update{element}}

    /**
     * Set the free spell's level to the indicated value.
     *
     * @param lvlInput level to apply to the free spell
     */
    fun setFreeLevel(lvlInput: Int){_freeLevel.update{lvlInput}}

    /**
     * Retrieve the inputted free spell's associated element.
     *
     * @param freeSpell free spell to check
     * @return element associated with this spell
     */
    fun getFreeElement(freeSpell: FreeSpell): Element {
        val book = magic.necromancyBook.charHasFreeSpell(freeSpell = freeSpell)
        return book?.element ?: freeSpell.forbiddenElements[0]
    }

    /**
     * Sets the free spell to the inputted item.
     *
     * @param freeSpell free spell to set
     */
    fun setSelectedFreeSpell(freeSpell: FreeSpell?){_selectedFreeSpell.update{freeSpell}}

    /**
     * Gets whether a spell of the given level is castable by the character.
     *
     * @param spellLevel spell to check the castability of
     * @return true if character can cast this spell
     */
    fun getCastable(spellLevel: Int): Boolean{
        return (spellLevel in 81..90 && charInstance.gnosis.intValue < 25) ||
            (spellLevel > 90 && charInstance.gnosis.intValue < 40)
    }

    /**
     * Opens and closes the detail alert as needed.
     */
    fun toggleDetailAlertOpen(){_detailAlertOpen.update{!detailAlertOpen.value}}

    /**
     * Sets the item displayed in the detail alert.
     *
     * @param spell displayed spell in the detail alert
     */
    fun setDetailItem(spell: Spell){
        _detailTitle.update{context.getString(spell.name)}
        _detailItem.update{spell}
    }

    /**
     * Determines if the character possesses The Gift Advantage.
     *
     * @return true if character has this advantage
     */
    fun isGifted(): Boolean{
        return charInstance.advantageRecord.getAdvantage(advantageString = "gift") != null
    }

    /**
     * Retrieves the base zeon points the character has.
     *
     * @return character's base zeon
     */
    fun getBaseZeon(): Int{return magic.baseZeon.intValue}

    /**
     * Retrieves the zeon the character gained from levels.
     *
     * @return zeon points gained from class levels
     */
    fun getClassZeon(): Int{return magic.zeonFromClass.intValue}

    /**
     * Gets the DP cost of the character's zeon point acquisitions.
     *
     * @return the DP cost of zeon points
     */
    fun getBoughtZeonDP(): Int{return charClass.value.zeonGrowth}

    /**
     * Gets the DP cost of the character's magic accumulation.
     *
     * @return the DP cost of magic accumulation
     */
    private fun getZeonAccDP(): Int{return charClass.value.maGrowth}

    /**
     * Gets the DP cost of the character's magic projection.
     *
     * @return the DP cost of magic projection
     */
    private fun getMagProjDP(): Int{return charClass.value.maProjGrowth}

    /**
     * Retrieve the maximum magic level the character can spend.
     *
     * @return the maximum magic level string
     */
    fun getMagicLevelMax(): String{return magic.magicLevelMax.intValue.toString()}

    /**
     * Retrieves the book of free spells.
     *
     * @return free spell record
     */
    fun getFreeSpellbook(): FreeBook{return magic.freeBook}

    /**
     * Determines if the indicated spell can be individually bought or removed from the character.
     *
     * @param spell spell to check
     * @return true if individually purchasable
     */
    fun spellIsRemovable(spell: Spell): Boolean{
        return magic.getSpellBook(spell = spell)!!.getCap() < spell.level ||
                (spell.level/2) - 1 in magic.getElementBook(element = magic.getSpellElement(spell = spell))!!.individualSpells
    }

    /**
     * Determine if the free spell from the indicated attributes is removable by the character.
     *
     * @param spellLevel level of the free spell to check
     * @param element element of the free spell to check
     * @return true if spell is removable
     */
    fun freeSpellIsRemovable(
        spellLevel: Int,
        element: Element
    ): Boolean{
        return magic.getElementBook(element = element)!!.pointsIn.intValue < spellLevel ||
                spellLevel in magic.getElementBook(element = element)!!.individualSpells
    }

    /**
     * Determine if the character is holding the inputted spell.
     *
     * @param spell spell to determine the character has
     * @return true if the character has learned this spell
     */
    fun getSpellHeld(spell: Spell): Boolean{return magic.hasCopyOf(check = spell)}

    /**
     * Add the user's selected free spell item to the character.
     */
    fun addFreeSpell(){
        if(selectedFreeSpell.value != null){
            //create the free spell
            val item = FreeSpell(
                saveName = selectedFreeSpell.value!!.saveName,
                name = selectedFreeSpell.value!!.name,
                isActive = selectedFreeSpell.value!!.isActive,
                level = freeLevel.value,
                zCost = selectedFreeSpell.value!!.zCost,
                effect = selectedFreeSpell.value!!.effect,
                addedEffect = selectedFreeSpell.value!!.addedEffect,
                zMax = selectedFreeSpell.value!!.zMax,
                maintenance = selectedFreeSpell.value!!.maintenance,
                isDaily = selectedFreeSpell.value!!.isDaily,
                type = selectedFreeSpell.value!!.type,
                forbiddenElements = selectedFreeSpell.value!!.forbiddenElements
            )

            //add it to the character
            freeBookAddition.value.magicBook.addFreeSpell(spell = item)
            updateHeldSpells()

            //close the free exchange dialog
            toggleFreeExchangeOpen()
        }
    }

    /**
     * Refresh the full list of the character's learned spells
     */
    fun updateHeldSpells(){
        heldSpells.clear()
        heldSpells.addAll(elements = magic.getAllSpells())
    }

    //create item to manage zeon accumulation
    val zeonAccumulation = ZeonPurchaseItemData(
        nameRef = R.string.zeonAccumulationLabel,
        baseInput = {magic.baseZeonAcc.intValue},
        boughtInput = {magic.zeonAccMult.intValue},
        totalInput = {magic.zeonAccTotal.intValue},
        dpGetter = {getZeonAccDP()},
        buyItem = {
            if(it >= 1){
                magic.buyZeonAcc(it)
                _zeonRecoverString.update{magic.magicRecoveryTotal.intValue}
                _innateMagic.update{magic.innateMagic.intValue}
            }
        },
        getValid = {true}
    ) {it.update{magic.zeonAccTotal.intValue.toString()}}

    //create item to manage zeon projection
    val zeonProjection = ZeonPurchaseItemData(
        nameRef = R.string.magProjectionLabel,
        baseInput = {charInstance.primaryList.dex.outputMod.intValue},
        boughtInput = {magic.boughtMagProjection.intValue},
        totalInput = {magic.magProjTotal.intValue},
        dpGetter = {getMagProjDP()},
        buyItem = {
            magic.buyMagProj(it)
            refreshImbalance(imbalanceIsAttack.value)
        },
        getValid = {magic.getValidProjection()}
    ){it.update{magic.magProjTotal.intValue.toString()}}

    //gather purchase data created
    private val allPurchaseData = listOf(zeonAccumulation, zeonProjection)

    //create manager object for each spellbook
    private val lightBook = SpellRowData(
        magic = magic,
        magFragVM = this,
        pointsIn = {magic.lightBook.pointsIn.intValue},
        magicBook = magic.lightBook
    )

    private val darkBook = SpellRowData(
        magic = magic,
        magFragVM = this,
        pointsIn = {magic.darkBook.pointsIn.intValue},
        magicBook = magic.darkBook
    )

    private val creationBook = SpellRowData(
        magic = magic,
        magFragVM = this,
        pointsIn = {magic.creationBook.pointsIn.intValue},
        magicBook = magic.creationBook
    )

    private val destructionBook = SpellRowData(
        magic = magic,
        magFragVM = this,
        pointsIn = {magic.destructionBook.pointsIn.intValue},
        magicBook = magic.destructionBook
    )

    private val airBook = SpellRowData(
        magic = magic,
        magFragVM = this,
        pointsIn = {magic.airBook.pointsIn.intValue},
        magicBook = magic.airBook
    )

    private val earthBook = SpellRowData(
        magic = magic,
        magFragVM = this,
        pointsIn = {magic.earthBook.pointsIn.intValue},
        magicBook = magic.earthBook
    )

    private val waterBook = SpellRowData(
        magic = magic,
        magFragVM = this,
        pointsIn = {magic.waterBook.pointsIn.intValue},
        magicBook = magic.waterBook
    )

    private val fireBook = SpellRowData(
        magic = magic,
        magFragVM = this,
        pointsIn = {magic.fireBook.pointsIn.intValue},
        magicBook = magic.fireBook
    )

    private val essenceBook = SpellRowData(
        magic = magic,
        magFragVM = this,
        pointsIn = {magic.essenceBook.pointsIn.intValue},
        magicBook = magic.essenceBook
    )

    private val illusionBook = SpellRowData(
        magic = magic,
        magFragVM = this,
        pointsIn = {magic.illusionBook.pointsIn.intValue},
        magicBook = magic.illusionBook
    )

    private val necromancyBook = SpellRowData(
        magic = magic,
        magFragVM = this,
        pointsIn = {magic.necromancyBook.pointsIn.intValue},
        magicBook = magic.necromancyBook
    )

    //gather all books in one place
    val allBooks = listOf(lightBook, darkBook, creationBook, destructionBook, airBook, earthBook,
        waterBook, fireBook, essenceBook, illusionBook, necromancyBook)

    //initialize the book the free spell is being added to
    private val freeBookAddition = mutableStateOf(value = necromancyBook)

    /**
     * Sets the book a free spell will be applied to.
     *
     * @param bookData book data item to change the addition item to
     */
    private fun setFreeBookAddition(bookData: SpellRowData){freeBookAddition.value = bookData}

    /**
     * Class that holds data on a zeon purchase item.
     *
     * @param nameRef string reference that titles this object
     * @param baseInput base value of the stat
     * @param boughtInput initial bought value for this item
     * @param totalInput initial total value for this item
     * @param dpGetter retrieves the DP needed for this item
     * @param buyItem function to run on a change in the purchase amount
     * @param getValid function to run to determine the text color
     * @param updateTotal function to run on the change of the total output
     */
    class ZeonPurchaseItemData(
        val nameRef: Int,
        val baseInput: () -> Int,
        val boughtInput: () -> Int,
        totalInput: () -> Int,
        val dpGetter: () -> Int,
        val buyItem: (Int) -> Unit,
        val getValid: () -> Boolean,
        val updateTotal: (MutableStateFlow<String>) -> Unit
    ){
        //initialize bought amount input
        private val _boughtString = MutableStateFlow(value = boughtInput().toString())
        val boughtString = _boughtString.asStateFlow()

        //initialize the color of the displayed text
        private val _textValid = MutableStateFlow(value = getValid())
        val textValid = _textValid.asStateFlow()

        private val _dpDisplay = MutableStateFlow(value = "")
        val dpDisplay = _dpDisplay.asStateFlow()

        //initialize total amount display
        private val _totalString = MutableStateFlow(value = totalInput().toString())
        val totalString = _totalString.asStateFlow()

        /**
         * Set the bought amount to the desired input.
         *
         * @param buyValue value to set the bought amount to
         */
        fun setBoughtString(buyValue: Int){
            buyItem(buyValue)
            _textValid.update{getValid()}
            setBoughtString(display = buyValue.toString())
            updateTotal(_totalString)
        }

        /**
         * Sets the DP display to the indicated value.
         *
         * @param dpDisplay new item to display
         */
        fun setDPDisplay(dpDisplay: String){_dpDisplay.update{dpDisplay}}

        /**
         * Set the bought display to the inputted value.
         *
         * @param display string to now display
         */
        fun setBoughtString(display: String){_boughtString.update{display}}

        /**
         * Refreshes the input and total for this item's data.
         */
        fun refreshItem(){
            if(_boughtString.value != "")
                setBoughtString(buyValue = boughtInput())
        }
    }

    /**
     * Data in regards to the character's investment in a particular spell book.
     *
     * @param magic character's magic abilities
     * @param magFragVM magic view model this object is contained in
     * @param pointsIn initial investment in this book
     * @param magicBook magic book associated with this item
     */
    class SpellRowData(
        val magic: Magic,
        private val magFragVM: MagicFragmentViewModel,
        val pointsIn: () -> Int,
        val magicBook: MagicBook
    ){
        //initialize primary checkbox value
        private val _isPrimary = MutableStateFlow(value = false)
        val isPrimary = _isPrimary.asStateFlow()

        //initialize value of investment levels
        private val _elementInvestment = MutableStateFlow(value = pointsIn().toString())
        val elementInvestment = _elementInvestment.asStateFlow()

        //initialize open state of this list
        private val _listOpen = MutableStateFlow(value = false)
        val listOpen = _listOpen.asStateFlow()

        /**
         * Sets the primary checkbox for this item's display.
         *
         * @param isPrimary value to set the checkbox to
         */
        fun setPrimaryElement(isPrimary: Boolean){
            //attempt to apply the change to the book
            magicBook.changePrimary(isTaking = isPrimary)

            //reflect the book's data
            _isPrimary.update{magicBook.isPrimary.value}
        }

        /**
         * Sets the number of magic levels invested in this book.
         *
         * @param magLevels number of levels to invest
         */
        fun setElementInvestment(magLevels: Int){
            magicBook.buyLevels(pointBuy = magLevels)

            setElementInvestment(display = magLevels.toString())
            magFragVM.setMagicLevelSpent()
            _isPrimary.update{magicBook.isPrimary.value}
            magFragVM.updateHeldSpells()
        }

        /**
         * Sets the display of the number of spell levels invested in this book.
         *
         * @param display string to display
         */
        fun setElementInvestment(display: String){_elementInvestment.update{display}}

        /**
         * Buys a single spell item for the character.
         *
         * @param spellLevel slot level to individually purchase
         */
        fun buySingleSpell(spellLevel: Int){
            magicBook.changeIndividualSpell(spellLevel = spellLevel)
        }

        /**
         * Changes the open state of this item's spell list.
         */
        fun toggleListOpen() {_listOpen.update{!listOpen.value}}

        /**
         * Refreshes this item on a page refresh.
         */
        fun refreshItem(){
            _isPrimary.update{magicBook.isPrimary.value}
            if(_elementInvestment.value != "")
                _elementInvestment.update{pointsIn().toString()}
        }
    }

    init{
        //set the initial imbalance bias
        setImbalanceIsAttack(isOffense = magic.imbalanceIsAttack.value)
        allBooks.forEach{it.refreshItem()}
        updateHeldSpells()
    }

    /**
     * Refreshes all items on this page when it is loaded.
     */
    fun refreshPage(){
        if(_boughtZeonString.value != "")
            setBoughtZeonString(zeonBought = magic.boughtZeon.intValue)

        allPurchaseData.forEach{purchaseData ->
            purchaseData.refreshItem()
        }

        if(_projectionImbalance.value != "")
            setProjectionImbalance(magic.magProjImbalance.intValue.toString())

        setMagicLevelSpent()

        allBooks.forEach{book -> book.refreshItem()}

        updateHeldSpells()
    }
}