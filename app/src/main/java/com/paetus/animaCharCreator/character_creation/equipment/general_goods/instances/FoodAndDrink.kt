package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

class FoodAndDrink: GeneralCategory(qualityInput = null) {
    private val beer = GeneralEquipment(
        saveName = "Beer",
        name = R.string.beer,
        baseCost = 1.0,
        coinType = CoinType.Copper,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val goodBeer = GeneralEquipment(
        saveName = "Good Beer",
        name = R.string.goodBeer,
        baseCost = 3.0,
        coinType = CoinType.Copper,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val wine = GeneralEquipment(
        saveName = "Wine",
        name = R.string.wine,
        baseCost = 2.0,
        coinType = CoinType.Copper,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val goodWine = GeneralEquipment(
        saveName = "Good Wine",
        name = R.string.goodWine,
        baseCost = 5.0,
        coinType = CoinType.Copper,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val excellentWine = GeneralEquipment(
        saveName = "Excellent Wine",
        name = R.string.excellentWine,
        baseCost = 3.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val milk = GeneralEquipment(
        saveName = "Milk",
        name = R.string.milk,
        baseCost = 1.0,
        coinType = CoinType.Copper,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val juice = GeneralEquipment(
        saveName = "Juice",
        name = R.string.juice,
        baseCost = 5.0,
        coinType = CoinType.Copper,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val exoticDrinks = GeneralEquipment(
        saveName = "Exotic Drinks",
        name = R.string.exoticDrink,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val mediocreFood = GeneralEquipment(
        saveName = "Mediocre Food",
        name = R.string.mediocreFood,
        baseCost = 4.0,
        coinType = CoinType.Copper,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val normalFood = GeneralEquipment(
        saveName = "Normal Food",
        name = R.string.normalFood,
        baseCost = 6.0,
        coinType = CoinType.Copper,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val goodFood = GeneralEquipment(
        saveName = "Good Food",
        name = R.string.goodFood,
        baseCost = 5.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val fineFood = GeneralEquipment(
        saveName = "Fine Food",
        name = R.string.fineFood,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val mediocreRations = GeneralEquipment(
        saveName = "Field Rations (Mediocre)",
        name = R.string.medRation,
        baseCost = 2.0,
        coinType = CoinType.Copper,
        weight = 5.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val decentRations = GeneralEquipment(
        saveName = "Field Rations (Decent)",
        name = R.string.decentRation,
        baseCost = 5.0,
        coinType = CoinType.Copper,
        weight = 7.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val goodRations = GeneralEquipment(
        saveName = "Field Rations (Good)",
        name = R.string.goodRation,
        baseCost = 5.0,
        coinType = CoinType.Silver,
        weight = 10.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val excellentRations = GeneralEquipment(
        saveName = "Field Rations (Excellent)",
        name = R.string.excellentRation,
        baseCost = 30.0,
        coinType = CoinType.Silver,
        weight = 10.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    init{
        itemsAvailable.addAll(listOf(
            beer,
            goodBeer,
            wine,
            goodWine,
            excellentWine,
            milk,
            juice,
            exoticDrinks,
            mediocreFood,
            normalFood,
            goodFood,
            fineFood,
            mediocreRations,
            decentRations,
            goodRations,
            excellentRations
        ))
    }
}