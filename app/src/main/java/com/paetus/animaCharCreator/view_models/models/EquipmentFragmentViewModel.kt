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
    private val _detailAlertOpen = MutableStateFlow(value = false)
    val detailAlertOpen = _detailAlertOpen.asStateFlow()

    //initialize title of detail dialog
    private val _detailTitle = MutableStateFlow(value = R.string.emptyItem)
    val detailTitle = _detailTitle.asStateFlow()

    //initialize item to detail
    private val _detailItem = MutableStateFlow<GeneralEquipment?>(value = null)
    val detailItem = _detailItem.asStateFlow()

    //initialize dialog's open state
    private val _itemPurchaseOpen = MutableStateFlow(value = false)
    val itemPurchaseOpen = _itemPurchaseOpen.asStateFlow()

    //initialize item obtained at the moment
    private val _purchasedItem = MutableStateFlow<GeneralEquipment?>(value = null)
    private val purchasedItem = _purchasedItem.asStateFlow()

    //initialize purchased item's category
    private val _purchasingCategory = MutableStateFlow<GeneralCategory?>(value = null)
    val purchasingCategory = _purchasingCategory.asStateFlow()

    //initialize number of items purchased display
    private val _purchasedNumber = MutableStateFlow(value = "1")
    val purchasedNumber = _purchasedNumber.asStateFlow()

    //initialize item's current quality
    private val _currentQuality = MutableStateFlow<QualityModifier?>(value = null)
    val currentQuality = _currentQuality.asStateFlow()

    //initialize gold cost display
    private val _totalGoldPurchase = MutableStateFlow(value = 0)
    val totalGoldPurchase = _totalGoldPurchase.asStateFlow()

    //initialize silver cost display
    private val _totalSilverPurchase = MutableStateFlow(value = 0)
    val totalSilverPurchase = _totalSilverPurchase.asStateFlow()

    //initialize copper cost display
    private val _totalCopperPurchase = MutableStateFlow(value = 0)
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
     * @param equipment item to detail
     */
    fun setDetailItem(equipment: GeneralEquipment){
        _detailTitle.update{equipment.name}
        _detailItem.update{equipment}
    }

    /**
     * Function that opens and closes the purchase selection dialog.
     */
    fun toggleItemPurchaseOpen(){_itemPurchaseOpen.update{!itemPurchaseOpen.value}}

    /**
     * Declares what item the user is currently purchasing for their character.
     *
     * @param equipment item to set as purchased
     */
    fun setPurchasedItem(equipment: GeneralEquipment){
        _purchasedItem.update{equipment}

        //set default amount to 1
        setPurchasedNumber(quantity = 1)
    }

    /**
     * Defines the category of the item being purchased.
     *
     * @param category category item belongs to
     */
    fun setPurchasingCategory(category: GeneralCategory){
        _purchasingCategory.update{category}

        //set corresponding quality options
        if(category.qualityInput != null)
            setCurrentQuality(quality = category.qualityInput[0])
        else
            setCurrentQuality(quality = null)
    }

    /**
     * Sets the value of the number of items purchased.
     *
     * @param quantity number to set for purchase
     */
    fun setPurchasedNumber(quantity: Int){
        setPurchasedNumber(display = quantity.toString())
        updatePurchaseTotals()
    }

    /**
     * Sets the displayed purchased item number.
     *
     * @param display string to display
     */
    fun setPurchasedNumber(display: String){_purchasedNumber.update{display}}

    /**
     * Sets the currently selected quality of the purchased item.
     *
     * @param quality quality to set for the item
     */
    fun setCurrentQuality(quality: QualityModifier?){
        _currentQuality.update{quality}
        updatePurchaseTotals()
    }

    /**
     * Updates the cost displays for the current purchase being made.
     */
    private fun updatePurchaseTotals(){
        //catch empty quantity inputs
        if(purchasedNumber.value == "") return

        //retrieve the current cost for the item
        val initialCost =
            if(currentQuality.value != null)
                purchasedItem.value!!.baseCost * purchasedNumber.value.toInt() * currentQuality.value!!.modifier
            else
                purchasedItem.value!!.baseCost * purchasedNumber.value.toInt()

        //fix the coin amounts to display valid values
        when(purchasedItem.value!!.coinType){
            CoinType.Copper -> {
                setTotalCopperPurchase(
                    copperCoin = initialCost,
                    setSilver = true
                )
            }
            CoinType.Silver -> {
                setTotalSilverPurchase(
                    silverCoin = initialCost,
                    setCopper = true,
                    setGold = true
                )
            }
            CoinType.Gold -> {
                setTotalGoldPurchase(
                    goldCoins = initialCost,
                    setSilver = true
                )
            }
        }
    }

    /**
     * Sets the purchase figures for an item with a base cost in gold coins.
     *
     * @param goldCoins number of gold coins to adjust
     * @param setSilver true if function might need to set the silver amount afterwards
     */
    private fun setTotalGoldPurchase(
        goldCoins: Double,
        setSilver: Boolean
    ){
        //update silver display if any decimal places in input
        if(setSilver)
            setTotalSilverPurchase(
                silverCoin = (goldCoins % 1.0) * 100,
                setCopper = true,
                setGold = false
            )

        //update gold display
        _totalGoldPurchase.update{goldCoins.toInt()}
    }

    /**
     * Sets the purchase figures for an item with a base cost in silver coins.
     *
     * @param silverCoin number of silver coins to adjust
     * @param setCopper true if function might need to set the copper amount afterwards
     * @param setGold true if function might need to set the gold amount afterwards
     */
    private fun setTotalSilverPurchase(
        silverCoin: Double,
        setCopper: Boolean,
        setGold: Boolean
    ){
        //initialize silver converted to gold and gold received from conversion
        var reduction = 0.0
        var goldTotal = 0.0

        //determine reduction amount required
        while(silverCoin - reduction >= 100){
            goldTotal += 1
            reduction += 100
        }

        //determine current silver amount
        val silvFinal = silverCoin - reduction

        //update silver display
        _totalSilverPurchase.update{silvFinal.toInt()}

        //update copper display if any decimal places in current result
        if(setCopper) setTotalCopperPurchase(copperCoin = (silvFinal % 1.0) * 10, setSilver = false)

        //update gold display for added gold amount
        if(setGold) setTotalGoldPurchase(goldCoins = goldTotal, setSilver = false)
    }

    /**
     * Sets the purchase figures for an item with a base cost in copper coins.
     *
     * @param copperCoin number of copper coins to adjust
     * @param setSilver true if function might need to set the silver amount afterwards
     */
    private fun setTotalCopperPurchase(
        copperCoin: Double,
        setSilver: Boolean
    ){
        //initialize copper converted to silver and silver received from conversion
        var reduction = 0.0
        var silvTotal = 0.0

        //determine reduction amount required
        while(copperCoin - reduction >= 10){
            silvTotal += 1
            reduction += 10
        }

        //update copper purchase display
        _totalCopperPurchase.update{(copperCoin - reduction).toInt()}

        //if necessary, update the silver amount displayed
        if(setSilver)
            setTotalSilverPurchase(silverCoin = silvTotal, setCopper = false, setGold = true)
    }

    /**
     * Takes data in this viewModel and purchases the items indicated by these.
     */
    fun purchaseItems(){
        //terminate process if purchased number input is empty
        if(purchasedNumber.value == "" || purchasedNumber.value.toInt() <= 0)
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
            equipment = GeneralEquipment(
                saveName = purchasedItem.value!!.saveName,
                name = purchasedItem.value!!.name,
                baseCost = purchasedItem.value!!.baseCost * qualityMult,
                coinType = purchasedItem.value!!.coinType,
                weight = purchasedItem.value!!.weight,
                availability = purchasedItem.value!!.availability,
                currentQuality = qualityIndex
            ),
            quantity = purchasedNumber.value.toInt()
        )

        //update list
        updateBoughtGoods()

        //close dialog
        toggleItemPurchaseOpen()
    }

    /**
     * Removes the indicated item from the character's inventory.
     *
     * @param equipment item to remove from inventory
     */
    fun removeItem(equipment: GeneralEquipment){
        inventory.removeItem(equipment = equipment)
        updateBoughtGoods()
    }

    /**
     * Retrieves the amount of the indicated coin spent by the character.
     *
     * @param coin type of coin to look up
     * @return amount of that coin spent
     */
    fun getCoinSpent(coin: CoinType): Int{
        return when(coin){
            CoinType.Copper -> inventory.copperSpent.doubleValue.toInt()
            CoinType.Silver -> inventory.silverSpent.doubleValue.toInt()
            CoinType.Gold -> inventory.goldSpent.doubleValue.toInt()
        }
    }

    /**
     * Determines the category a piece of equipment belongs to.
     *
     * @param equipment equipment to find the category of
     * @return the category it belongs to
     */
    fun getCategory(
        equipment: GeneralEquipment
    ): GeneralCategory?{
        inventory.getAllCategories().forEach{category ->
            if(category.findEquipment(
                    equipName = equipment.saveName,
                    quality = equipment.currentQuality
                ) != null)
                return category
        }

        return null
    }

    /**
     * Refreshes the state list to match the character's held inventory.
     */
    private fun updateBoughtGoods(){
        boughtGoods.clear()
        boughtGoods += inventory.boughtGoods.keys
    }

    /**
     * Gets the number of the inputted item in the character's inventory.
     *
     * @param equipment item to look for
     * @return number of queried items in inventory
     */
    fun getQuantity(equipment: GeneralEquipment): Int?{return inventory.boughtGoods[equipment]}

    /**
     * Gets the bonus wealth value the character has.
     *
     * @return amount of bonus wealth the character has
     */
    fun getBonusWealth(): Int{return inventory.wealthBonus.intValue}

    //initialize data for all coin maximums
    private val maxGold = MaximumData(
        nameRef = R.string.maxGoldLabel,
        maxInput = {inventory.maxGold.intValue},
        changeAmount = {inventory.setMaxGold(maxVal = it)}
    )

    private val maxSilver = MaximumData(
        nameRef = R.string.maxSilverLabel,
        maxInput = {inventory.maxSilver.intValue},
        changeAmount = {inventory.setMaxSilver(maxVal = it)}
    )

    private val maxCopper = MaximumData(
        nameRef = R.string.maxCopperLabel,
        maxInput = {inventory.maxCopper.intValue},
        changeAmount = {inventory.setMaxCopper(maxVal = it)}
    )

    //collect all maximum data
    val allQuantityMaximums = listOf(maxGold, maxSilver, maxCopper)

    //instantiate all category data
    private val clothes = CategoryData(
        nameRef = R.string.clothingLabel,
        equipmentList = inventory.clothing
    )

    private val travel = CategoryData(
        nameRef = R.string.travelLabel,
        equipmentList = inventory.travel
    )

    private val transport = CategoryData(
        nameRef = R.string.transport,
        equipmentList = inventory.transport
    )

    private val foodAndDrink = CategoryData(
        nameRef = R.string.foodAndDrinkLabel,
        equipmentList = inventory.foodAndDrink
    )

    private val lodging = CategoryData(
        nameRef = R.string.lodgingLabel,
        equipmentList = inventory.lodging
    )

    private val dwellings = CategoryData(
        nameRef = R.string.dwellingLabel,
        equipmentList = inventory.dwellings
    )

    private val services = CategoryData(
        nameRef = R.string.serviceLabel,
        equipmentList = inventory.services
    )

    private val art = CategoryData(
        nameRef = R.string.artAndDecorLabel,
        equipmentList = inventory.art
    )

    private val gems = CategoryData(
        nameRef = R.string.gemLabel,
        equipmentList = inventory.gems
    )

    private val paintings = CategoryData(
        nameRef = R.string.paintingLabel,
        equipmentList = inventory.painting
    )

    private val poisons = CategoryData(
        nameRef = R.string.poisonLabel,
        equipmentList = inventory.poisons
    )

    private val miscellaneous = CategoryData(
        nameRef = R.string.miscLabel,
        equipmentList = inventory.miscellaneous
    )

    private val weapons = CategoryData(
        nameRef = R.string.weaponsLabel,
        equipmentList = inventory.weapons
    )

    private val armors = CategoryData(
        nameRef = R.string.armorsLabel,
        equipmentList = inventory.armors
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
        private val _maxValue = MutableStateFlow(value = maxInput().toString())
        val maxValue = _maxValue.asStateFlow()

        /**
         * Changes the maximum coin value in the character's record.
         *
         * @param maxCoin new value to set as maximum
         */
        fun setMaxValue(maxCoin: Int){
            changeAmount(maxCoin)
            setMaxValue(maxCoin.toString())
        }

        /**
         * Changes the maximum coin display string.
         *
         * @param display new value to display
         */
        fun setMaxValue(display: String){_maxValue.update{display}}
    }

    /**
     * Data object for an equipment category type.
     *
     * @param nameRef resource reference to the displayed name
     * @param equipmentList category for this display
     */
    class CategoryData(
        val nameRef: Int,
        val equipmentList: GeneralCategory
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
        allQuantityMaximums.forEach{maxCoin -> maxCoin.setMaxValue(maxCoin.maxInput().toString())}
        allCategoryData.forEach{category ->
            if(category.catOpen.value)
                category.toggleCatOpen()
        }
    }
}