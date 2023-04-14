package com.example.animabuilder.character_creation.equipment.general_goods.instances

import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.character_creation.equipment.general_goods.Availability
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralCategory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment

class FoodAndDrink: GeneralCategory(null) {
    val beer = GeneralEquipment(
        "Beer",
        1.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val goodBeer = GeneralEquipment(
        "Good Beer",
        3.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val wine = GeneralEquipment(
        "Wine",
        2.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val goodWine = GeneralEquipment(
        "Good Wine",
        5.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val excellentWine = GeneralEquipment(
        "Excellent Wine",
        3.0,
        CoinType.Silver,
        null,
        Availability.Uncommon,
        null
    )

    val milk = GeneralEquipment(
        "Milk",
        1.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val juice = GeneralEquipment(
        "Juice",
        5.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val exoticDrinks = GeneralEquipment(
        "Exotic Drinks",
        1.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val mediocreFood = GeneralEquipment(
        "Mediocre Food",
        4.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val normalFood = GeneralEquipment(
        "Normal Food",
        6.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val goodFood = GeneralEquipment(
        "Good Food",
        5.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val fineFood = GeneralEquipment(
        "Fine Food",
        5.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val mediocreRations = GeneralEquipment(
        "Field Rations (Mediocre)",
        2.0,
        CoinType.Copper,
        5.0,
        Availability.Common,
        null
    )

    val decentRations = GeneralEquipment(
        "Field Rations (Decent)",
        5.0,
        CoinType.Copper,
        7.0,
        Availability.Common,
        null
    )

    val goodRations = GeneralEquipment(
        "Field Rations (Good)",
        5.0,
        CoinType.Silver,
        10.0,
        Availability.Common,
        null
    )

    val excellentRations = GeneralEquipment(
        "Field Rations (Excellent)",
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