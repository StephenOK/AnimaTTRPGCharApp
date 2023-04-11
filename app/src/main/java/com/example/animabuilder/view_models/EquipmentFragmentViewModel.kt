package com.example.animabuilder.view_models

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.character_creation.equipment.Inventory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralCategory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment
import com.example.animabuilder.character_creation.equipment.general_goods.QualityModifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EquipmentFragmentViewModel(
    private val inventory: Inventory
) : ViewModel() {
    private val _maxExpenditure = MutableStateFlow(inventory.maxGold.toString())
    val maxExpenditure = _maxExpenditure.asStateFlow()

    val boughtGoods = mutableStateMapOf<GeneralEquipment, Int>()

    fun setMaxExpenditure(input: Int){
        inventory.setMaxGold(input)
        setMaxExpenditure(input.toString())
    }

    fun updateBoughtGoods(){
        boughtGoods.clear()
        boughtGoods += inventory.boughtGoods
    }

    fun setMaxExpenditure(input: String){_maxExpenditure.update{input}}

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

    class CategoryData(
        val nameRef: Int,
        val reference: GeneralCategory
    ){
        private val _catOpen = MutableStateFlow(false)
        val catOpen = _catOpen.asStateFlow()
        fun toggleCatOpen(){_catOpen.update{!catOpen.value}}
    }

    private val _itemPurchaseOpen = MutableStateFlow(false)
    val itemPurchaseOpen = _itemPurchaseOpen.asStateFlow()

    private val _purchasedItem = MutableStateFlow<GeneralEquipment?>(null)
    val purchasedItem = _purchasedItem.asStateFlow()

    private val _purchasingCategory = MutableStateFlow<GeneralCategory?>(null)
    val purchasingCategory = _purchasingCategory.asStateFlow()

    private val _purchasedNumber = MutableStateFlow("1")
    val purchasedNumber = _purchasedNumber.asStateFlow()

    private val _currentQuality = MutableStateFlow<QualityModifier?>(null)
    val currentQuality = _currentQuality.asStateFlow()

    private val _totalGoldPurchase = MutableStateFlow("0")
    val totalGoldPurchase = _totalGoldPurchase.asStateFlow()

    private val _totalSilverPurchase = MutableStateFlow("0")
    val totalSilverPurchase = _totalSilverPurchase.asStateFlow()

    private val _totalCopperPurchase = MutableStateFlow("0")
    val totalCopperPurchase = _totalCopperPurchase.asStateFlow()

    fun toggleItemPurchaseOpen(){_itemPurchaseOpen.update{!itemPurchaseOpen.value}}
    fun setPurchasedItem(input: GeneralEquipment){
        _purchasedItem.update{input}
        setPurchasedNumber(1)
    }
    fun setPurchasingCategory(input: GeneralCategory){
        _purchasingCategory.update{input}
        if(input.qualityInput != null)
            setCurrentQuality(input.qualityInput[0])
        else
            setCurrentQuality(null)
    }
    fun setPurchasedNumber(input: Int){
        setPurchasedNumber(input.toString())
        updatePurchaseTotals()
    }
    fun setPurchasedNumber(input: String){_purchasedNumber.update{input}}
    fun setCurrentQuality(input: QualityModifier?){
        _currentQuality.update{input}
        updatePurchaseTotals()
    }

    fun updatePurchaseTotals(){
        val initialCost =
            if(currentQuality.value != null)
                purchasedItem.value!!.baseCost * currentQuality.value!!.modifier
            else
                purchasedItem.value!!.baseCost.toDouble()

        when(purchasedItem.value!!.coinType){
            CoinType.Copper -> {setTotalCopperPurchase(initialCost, true)}
            CoinType.Silver -> {setTotalSilverPurchase(initialCost, true, true)}
            CoinType.Gold -> {setTotalGoldPurchase(initialCost, true)}
        }
    }

    fun setTotalCopperPurchase(input: Double, setSilver: Boolean){
        var reduction = 0.0
        var silvTotal = 0.0

        while(input - reduction >= 10){
            silvTotal += 1
            reduction += 10
        }

        _totalCopperPurchase.update{(input - reduction).toInt().toString()}
        if(setSilver)
            setTotalSilverPurchase(silvTotal, false, true)
    }

    fun setTotalSilverPurchase(input: Double, setCopper: Boolean, setGold: Boolean){
        var reduction = 0.0
        var goldTotal = 0.0

        while(input - reduction >= 100){
            goldTotal += 1
            reduction += 100
        }

        val silvFinal = input - reduction

        _totalSilverPurchase.update{silvFinal.toInt().toString()}
        if(setCopper) setTotalCopperPurchase((silvFinal % 1.0) * 10, false)
        if(setGold) setTotalGoldPurchase(goldTotal, false)
    }

    fun setTotalGoldPurchase(input: Double, setSilver: Boolean){
        if(setSilver)
            setTotalSilverPurchase(
                (input % 1.0) * 100, true, false)

        _totalGoldPurchase.update{input.toInt().toString()}
    }

    fun purchaseItems(){
        val qualityMult =
            if(currentQuality.value != null) currentQuality.value!!.modifier
            else 1.0

        if(purchasedNumber.value == "")
            return

        inventory.buyItem(
            GeneralEquipment(
                purchasedItem.value!!.name,
                purchasedItem.value!!.baseCost * qualityMult,
                purchasedItem.value!!.coinType,
                purchasedItem.value!!.weight,
                purchasedItem.value!!.availability
            ),
            purchasedNumber.value.toInt()
        )

        updateBoughtGoods()
        toggleItemPurchaseOpen()
    }

    fun getCoinSpent(input: CoinType): Int{
        return when(input){
            CoinType.Copper -> inventory.copperSpent.toInt()
            CoinType.Silver -> inventory.silverSpent.toInt()
            CoinType.Gold -> inventory.goldSpent.toInt()
        }
    }
}