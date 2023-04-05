package com.example.animabuilder.character_creation.equipment.general_goods.instances

import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.character_creation.equipment.general_goods.Availability
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralCategory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment

class Lodging: GeneralCategory(null) {
    val mediocreLodging = GeneralEquipment(
        "Mediocre Lodging",
        5,
        CoinType.Copper,
        null,
        Availability.Common
    )

    val decentLodging = GeneralEquipment(
        "Decent Lodging",
        1,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val goodLodging = GeneralEquipment(
        "Good Lodging",
        25,
        CoinType.Silver,
        null,
        Availability.Uncommon
    )

    val luxuryLodging = GeneralEquipment(
        "Luxurious Lodging",
        5,
        CoinType.Gold,
        null,
        Availability.Rare
    )

    init{
        itemsAvailable.addAll(listOf(
            mediocreLodging,
            decentLodging,
            goodLodging,
            luxuryLodging
        ))
    }
}