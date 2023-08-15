package com.paetus.animaCharCreator.view_models.models

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.Inventory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the character's equipment and funds.
 * Works on variables with the corresponding fragment.
 *
 * @param inventory character's equipment object
 */
class EquipmentFragmentViewModel(
    private val inventory: Inventory
) : ViewModel() {
    //initialize open state of item details
    private val _detailAlertOpen = MutableStateFlow(false)
    val detailAlertOpen = _detailAlertOpen.asStateFlow()

    //initialize title of detail dialog
    private val _detailTitle = MutableStateFlow(R.string.emptyItem)
    val detailTitle = _detailTitle.asStateFlow()

    //initialize item to detail
    private val _detailItem = MutableStateFlow<GeneralEquipment?>(null)
    val detailItem = _detailItem.asStateFlow()

    //initialize dialog's open state
    private val _itemPurchaseOpen = MutableStateFlow(false)
    val itemPurchaseOpen = _itemPurchaseOpen.asStateFlow()

    //initialize item obtained at the moment
    private val _purchasedItem = MutableStateFlow<GeneralEquipment?>(null)
    val purchasedItem = _purchasedItem.asStateFlow()

    //initialize purchased item's category
    private val _purchasingCategory = MutableStateFlow<GeneralCategory?>(null)
    val purchasingCategory = _purchasingCategory.asStateFlow()

    //initialize number of items purchased display
    private val _purchasedNumber = MutableStateFlow("1")
    val purchasedNumber = _purchasedNumber.asStateFlow()

    //initialize item's current quality
    private val _currentQuality = MutableStateFlow<QualityModifier?>(null)
    val currentQuality = _currentQuality.asStateFlow()

    //initialize gold cost display
    private val _totalGoldPurchase = MutableStateFlow("0")
    val totalGoldPurchase = _totalGoldPurchase.asStateFlow()

    //initialize silver cost display
    private val _totalSilverPurchase = MutableStateFlow("0")
    val totalSilverPurchase = _totalSilverPurchase.asStateFlow()

    //initialize copper cost display
    private val _totalCopperPurchase = MutableStateFlow("0")
    val totalCopperPurchase = _totalCopperPurchase.asStateFlow()

    //initialize state list for character's inventory
    val boughtGoods = inventory.boughtGoods.keys.toMutableStateList()

    /**
     * Opens and closes the detail alert for inventory items.
     */
    fun toggleDetailAlertOpen(){_detailAlertOpen.update{!detailAlertOpen.value}}

    /**
     * Sets the item to be displayed in the detail alert.
     *
     * @param input item to detail
     */
    fun setDetailItem(input: GeneralEquipment){
        _detailTitle.update{input.name}
        _detailItem.update{input}
    }

    /**
     * Function that opens and closes the purchase selection dialog.
     */
    fun toggleItemPurchaseOpen(){_itemPurchaseOpen.update{!itemPurchaseOpen.value}}

    /**
     * Declares what item the user is currently purchasing for their character.
     *
     * @param input item to set as purchased
     */
    fun setPurchasedItem(input: GeneralEquipment){
        _purchasedItem.update{input}

        //set default amount to 1
        setPurchasedNumber(1)
    }

    /**
     * Defines the category of the item being purchased.
     *
     * @param input category item belongs to
     */
    fun setPurchasingCategory(input: GeneralCategory){
        _purchasingCategory.update{input}

        //set corresponding quality options
        if(input.qualityInput != null)
            setCurrentQuality(input.qualityInput[0])
        else
            setCurrentQuality(null)
    }

    /**
     * Sets the value of the number of items purchased.
     *
     * @param input number to set for purchase
     */
    fun setPurchasedNumber(input: Int){
        setPurchasedNumber(input.toString())
        updatePurchaseTotals()
    }

    /**
     * Sets the displayed purchased item number.
     *
     * @param input string to display
     */
    fun setPurchasedNumber(input: String){_purchasedNumber.update{input}}

    /**
     * Sets the currently selected quality of the purchased item.
     *
     * @param input quality to set for the item
     */
    fun setCurrentQuality(input: QualityModifier?){
        _currentQuality.update{input}
        updatePurchaseTotals()
    }

    /**
     * Updates the cost displays for the current purchase being made.
     */
    fun updatePurchaseTotals(){
        //retrieve the current cost for the item
        val initialCost =
            if(currentQuality.value != null)
                purchasedItem.value!!.baseCost * purchasedNumber.value.toInt() * currentQuality.value!!.modifier
            else
                purchasedItem.value!!.baseCost * purchasedNumber.value.toInt()

        //fix the coin amounts to display valid values
        when(purchasedItem.value!!.coinType){
            CoinType.Copper -> {setTotalCopperPurchase(initialCost, true)}
            CoinType.Silver -> {setTotalSilverPurchase(initialCost, true, true)}
            CoinType.Gold -> {setTotalGoldPurchase(initialCost, true)}
        }
    }

    /**
     * Sets the purchase figures for an item with a base cost in gold coins.
     *
     * @param input number of gold coins to adjust
     * @param setSilver true if function might need to set the silver amount afterwards
     */
    fun setTotalGoldPurchase(input: Double, setSilver: Boolean){
        //update silver display if any decimal places in input
        if(setSilver)
            setTotalSilverPurchase(
                (input % 1.0) * 100, true, false)

        //update gold display
        _totalGoldPurchase.update{input.toInt().toString()}
    }

    /**
     * Sets the purchase figures for an item with a base cost in silver coins.
     *
     * @param input number of silver coins to adjust
     * @param setCopper true if function might need to set the copper amount afterwards
     * @param setGold true if function might need to set the gold amount afterwards
     */
    fun setTotalSilverPurchase(input: Double, setCopper: Boolean, setGold: Boolean){
        //initialize silver converted to gold and gold received from conversion
        var reduction = 0.0
        var goldTotal = 0.0

        //determine reduction amount required
        while(input - reduction >= 100){
            goldTotal += 1
            reduction += 100
        }

        //determine current silver amount
        val silvFinal = input - reduction

        //update silver display
        _totalSilverPurchase.update{silvFinal.toInt().toString()}

        //update copper display if any decimal places in current result
        if(setCopper) setTotalCopperPurchase((silvFinal % 1.0) * 10, false)

        //update gold display for added gold amount
        if(setGold) setTotalGoldPurchase(goldTotal, false)
    }

    /**
     * Sets the purchase figures for an item with a base cost in copper coins.
     *
     * @param input number of copper coins to adjust
     * @param setSilver true if function might need to set the silver amount afterwards
     */
    fun setTotalCopperPurchase(input: Double, setSilver: Boolean){
        //initialize copper converted to silver and silver received from conversion
        var reduction = 0.0
        var silvTotal = 0.0

        //determine reduction amount required
        while(input - reduction >= 10){
            silvTotal += 1
            reduction += 10
        }

        //update copper purchase display
        _totalCopperPurchase.update{(input - reduction).toInt().toString()}

        //if necessary, update the silver amount displayed
        if(setSilver)
            setTotalSilverPurchase(silvTotal, false, true)
    }

    /**
     * Takes data in this viewModel and purchases the items indicated by these.
     */
    fun purchaseItems(){
        //terminate process if purchased number input is empty
        if(purchasedNumber.value == "")
            return

        //determine the quality multiplier for the item
        val qualityMult =
            if(currentQuality.value != null) currentQuality.value!!.modifier
            else 1.0

        //determines the index of the inputted quality to be stored in the item
        val qualityIndex =
            if(currentQuality.value == null || purchasingCategory.value!!.qualityInput == null) null
            else
                purchasingCategory.value!!.qualityInput!!.indexOf(currentQuality.value!!)

        //construct item from data and add it to inventory
        inventory.buyItem(
            GeneralEquipment(
                purchasedItem.value!!.saveName,
                purchasedItem.value!!.name,
                purchasedItem.value!!.baseCost * qualityMult,
                purchasedItem.value!!.coinType,
                purchasedItem.value!!.weight,
                purchasedItem.value!!.availability,
                qualityIndex
            ),
            purchasedNumber.value.toInt()
        )

        //update list
        updateBoughtGoods()

        //close dialog
        toggleItemPurchaseOpen()
    }

    /**
     * Removes the indicated item from the character's inventory.
     *
     * @param input item to remove from inventory
     */
    fun removeItem(input: GeneralEquipment){
        inventory.removeItem(input)
        updateBoughtGoods()
    }

    /**
     * Retrieves the amount of the indicated coin spent by the character.
     *
     * @param input type of coin to look up
     * @return amount of that coin spent
     */
    fun getCoinSpent(input: CoinType): Int{
        return when(input){
            CoinType.Copper -> inventory.copperSpent.value.toInt()
            CoinType.Silver -> inventory.silverSpent.value.toInt()
            CoinType.Gold -> inventory.goldSpent.value.toInt()
        }
    }

    /**
     * Determines the category a piece of equipment belongs to.
     *
     * @param input equipment to find the category of
     * @return the category it belongs to
     */
    fun getCategory(input: GeneralEquipment): GeneralCategory?{
        inventory.allCategories.forEach{
            if(it.findEquipment(input.saveName, input.currentQuality) != null)
                return it
        }

        return null
    }

    /**
     * Refreshes the state list to match the character's held inventory.
     */
    fun updateBoughtGoods(){
        boughtGoods.clear()
        boughtGoods += inventory.boughtGoods.keys
    }

    /**
     * Gets the number of the inputted item in the character's inventory.
     *
     * @param input item to look for
     * @return number of queried items in inventory
     */
    fun getQuantity(input: GeneralEquipment): Int?{return inventory.boughtGoods[input]}

    /**
     * Gets the bonus wealth value the character has.
     *
     * @return amount of bonus wealth the character has
     */
    fun getBonusWealth(): Int{return inventory.wealthBonus.value}

    //initialize data for all coin maximums
    val maxGold = MaximumData(
        R.string.maxGoldLabel,
        {inventory.maxGold.value}
    ){inventory.setMaxGold(it)}

    val maxSilver = MaximumData(
        R.string.maxSilverLabel,
        {inventory.maxSilver.value}
    ){inventory.setMaxSilver(it)}

    val maxCopper = MaximumData(
        R.string.maxCopperLabel,
        {inventory.maxCopper.value}
    ){inventory.setMaxCopper(it)}

    //collect all maximum data
    val allQuantityMaximums = listOf(maxGold, maxSilver, maxCopper)

    //instantiate all category data
    private val clothes = CategoryData(
        R.string.clothingLabel,
        inventory.clothing
    )

    private val travel = CategoryData(
        R.string.travelLabel,
        inventory.travel
    )

    private val transport = CategoryData(
        R.string.transport,
        inventory.transport
    )

    private val foodAndDrink = CategoryData(
        R.string.foodAndDrinkLabel,
        inventory.foodAndDrink
    )

    private val lodging = CategoryData(
        R.string.lodgingLabel,
        inventory.lodging
    )

    private val dwellings = CategoryData(
        R.string.dwellingLabel,
        inventory.dwellings
    )

    private val services = CategoryData(
        R.string.serviceLabel,
        inventory.services
    )

    private val art = CategoryData(
        R.string.artAndDecorLabel,
        inventory.art
    )

    private val gems = CategoryData(
        R.string.gemLabel,
        inventory.gems
    )

    private val paintings = CategoryData(
        R.string.paintingLabel,
        inventory.painting
    )

    private val poisons = CategoryData(
        R.string.poisonLabel,
        inventory.poisons
    )

    private val miscellaneous = CategoryData(
        R.string.miscLabel,
        inventory.miscellaneous
    )

    private val weapons = CategoryData(
        R.string.weaponsLabel,
        inventory.weapons
    )

    private val armors = CategoryData(
        R.string.armorsLabel,
        inventory.armors
    )

    //gather all category data
    val allCategoryData = listOf(
        clothes,
        travel,
        transport,
        foodAndDrink,
        lodging,
        dwellings,
        services,
        art,
        gems,
        paintings,
        poisons,
        miscellaneous,
        weapons,
        armors
    )

    /**
     * Data object for maximum coin amounts.
     *
     * @param nameRef resource reference to the object's title
     * @param maxInput initial maximum value
     * @param changeAmount function to run on the maximum amount's change
     */
    class MaximumData(
        val nameRef: Int,
        val maxInput: () -> Int,
        val changeAmount: (Int) -> Unit
    ){
        //initialize maximum string display
        private val _maxValue = MutableStateFlow(maxInput().toString())
        val maxValue = _maxValue.asStateFlow()

        /**
         * Changes the maximum coin value in the character's record.
         *
         * @param input new value to set as maximum
         */
        fun setMaxValue(input: Int){
            changeAmount(input)
            setMaxValue(input.toString())
        }

        /**
         * Changes the maximum coin display string.
         *
         * @param input new value to display
         */
        fun setMaxValue(input: String){_maxValue.update{input}}
    }

    /**
     * Data object for an equipment category type.
     *
     * @param nameRef resource reference to the displayed name
     * @param reference category for this display
     */
    class CategoryData(
        val nameRef: Int,
        val reference: GeneralCategory
    ){
        //initialize open state of the display
        private val _catOpen = MutableStateFlow(false)
        val catOpen = _catOpen.asStateFlow()

        /**
         * Toggles the open state of the equipment display.
         */
        fun toggleCatOpen(){_catOpen.update{!catOpen.value}}
    }

    fun refreshPage(){
        allQuantityMaximums.forEach{it.setMaxValue(it.maxInput().toString())}
        allCategoryData.forEach{
            if(it.catOpen.value)
                it.toggleCatOpen()
        }
    }
}