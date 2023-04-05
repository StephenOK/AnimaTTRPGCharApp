package com.example.animabuilder.character_creation.equipment.general_goods.instances

import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.character_creation.equipment.general_goods.Availability
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralCategory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment

class FoodAndDrink: GeneralCategory(null) {
    val beer = GeneralEquipment(
        "Beer",
        1,
        CoinType.Copper,
        null,
        Availability.Common
    )

    val goodBeer = GeneralEquipment(
        "Good Beer",
        3,
        CoinType.Copper,
        null,
        Availability.Common
    )

    val wine = GeneralEquipment(
        "Wine",
        2,
        CoinType.Copper,
        null,
        Availability.Common
    )

    val goodWine = GeneralEquipment(
        "Good Wine",
        5,
        CoinType.Copper,
        null,
        Availability.Common
    )

    val excellentWine = GeneralEquipment(
        "Excellent Wine",
        3,
        CoinType.Silver,
        null,
        Availability.Uncommon
    )

    val milk = GeneralEquipment(
        "Milk",
        1,
        CoinType.Copper,
        null,
        Availability.Common
    )

    val juice = GeneralEquipment(
        "Juice",
        5,
        CoinType.Copper,
        null,
        Availability.Common
    )

    val exoticDrinks = GeneralEquipment(
        "Exotic Drinks",
        1,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val mediocreFood = GeneralEquipment(
        "Mediocre Food",
        4,
        CoinType.Copper,
        null,
        Availability.Common
    )

    val normalFood = GeneralEquipment(
        "Normal Food",
        6,
        CoinType.Copper,
        null,
        Availability.Common
    )

    val goodFood = GeneralEquipment(
        "Good Food",
        5,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val fineFood = GeneralEquipment(
        "Fine Food",
        5,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val mediocreRations = GeneralEquipment(
        "Field Rations (Mediocre)",
        2,
        CoinType.Copper,
        5.0,
        Availability.Common
    )

    val decentRations = GeneralEquipment(
        "Field Rations (Decent)",
        5,
        CoinType.Copper,
        7.0,
        Availability.Common
    )

    val goodRations = GeneralEquipment(
        "Field Rations (Good)",
        5,
        CoinType.Silver,
        10.0,
        Availability.Common
    )

    val excellentRations = GeneralEquipment(
        "Field Rations (Excellent)",
        30,
        CoinType.Silver,
        10.0,
        Availability.Uncommon
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