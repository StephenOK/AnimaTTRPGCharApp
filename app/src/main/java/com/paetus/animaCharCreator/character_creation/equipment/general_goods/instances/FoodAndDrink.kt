package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

class FoodAndDrink: GeneralCategory(null) {
    val beer = GeneralEquipment(
        "Beer",
        R.string.beer,
        1.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val goodBeer = GeneralEquipment(
        "Good Beer",
        R.string.goodBeer,
        3.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val wine = GeneralEquipment(
        "Wine",
        R.string.wine,
        2.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val goodWine = GeneralEquipment(
        "Good Wine",
        R.string.goodWine,
        5.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val excellentWine = GeneralEquipment(
        "Excellent Wine",
        R.string.excellentWine,
        3.0,
        CoinType.Silver,
        null,
        Availability.Uncommon,
        null
    )

    val milk = GeneralEquipment(
        "Milk",
        R.string.milk,
        1.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val juice = GeneralEquipment(
        "Juice",
        R.string.juice,
        5.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val exoticDrinks = GeneralEquipment(
        "Exotic Drinks",
        R.string.exoticDrink,
        1.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val mediocreFood = GeneralEquipment(
        "Mediocre Food",
        R.string.mediocreFood,
        4.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val normalFood = GeneralEquipment(
        "Normal Food",
        R.string.normalFood,
        6.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val goodFood = GeneralEquipment(
        "Good Food",
        R.string.goodFood,
        5.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val fineFood = GeneralEquipment(
        "Fine Food",
        R.string.fineFood,
        5.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val mediocreRations = GeneralEquipment(
        "Field Rations (Mediocre)",
        R.string.medRation,
        2.0,
        CoinType.Copper,
        5.0,
        Availability.Common,
        null
    )

    val decentRations = GeneralEquipment(
        "Field Rations (Decent)",
        R.string.decentRation,
        5.0,
        CoinType.Copper,
        7.0,
        Availability.Common,
        null
    )

    val goodRations = GeneralEquipment(
        "Field Rations (Good)",
        R.string.goodRation,
        5.0,
        CoinType.Silver,
        10.0,
        Availability.Common,
        null
    )

    val excellentRations = GeneralEquipment(
        "Field Rations (Excellent)",
        R.string.excellentRation,
        30.0,
        CoinType.Silver,
        10.0,
        Availability.Uncommon,
        null
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