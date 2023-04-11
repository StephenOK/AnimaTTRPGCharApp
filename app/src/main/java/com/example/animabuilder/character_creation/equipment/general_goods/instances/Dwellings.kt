package com.example.animabuilder.character_creation.equipment.general_goods.instances

import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.character_creation.equipment.general_goods.Availability
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralCategory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment
import com.example.animabuilder.character_creation.equipment.general_goods.QualityModifier

class Dwellings: GeneralCategory(listOf(
    QualityModifier("Mediocre Quality", 0.5, Availability.Common),
    QualityModifier("Decent Quality", 1.0, Availability.Common),
    QualityModifier("Good Quality", 2.0, Availability.Common),
    QualityModifier("Luxurious", 10.0, Availability.Common),
    QualityModifier("Urban Area", 2.0, Availability.Common)
)) {
    val shack = GeneralEquipment(
        "Shack",
        15.0,
        CoinType.Gold,
        null,
        Availability.Common
    )

    val house = GeneralEquipment(
        "House",
        60.0,
        CoinType.Gold,
        null,
        Availability.Common
    )

    val largeHouse = GeneralEquipment(
        "Large House",
        150.0,
        CoinType.Gold,
        null,
        Availability.Common
    )

    val mansion = GeneralEquipment(
        "Mansion",
        800.0,
        CoinType.Gold,
        null,
        Availability.Common
    )

    val palace = GeneralEquipment(
        "Palace",
        2000.0,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val castle = GeneralEquipment(
        "Castle",
        30000.0,
        CoinType.Gold,
        null,
        Availability.Rare
    )

    init{
        itemsAvailable.addAll(listOf(
            shack,
            house,
            largeHouse,
            mansion,
            palace,
            castle
        ))
    }
}