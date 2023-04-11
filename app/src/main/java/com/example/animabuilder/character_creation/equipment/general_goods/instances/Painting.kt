package com.example.animabuilder.character_creation.equipment.general_goods.instances

import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.character_creation.equipment.general_goods.Availability
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralCategory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment
import com.example.animabuilder.character_creation.equipment.general_goods.QualityModifier

class Painting: GeneralCategory(listOf(
    QualityModifier("Known Artist", 2.0, Availability.Common),
    QualityModifier("Prestigious Artist", 4.0, Availability.Uncommon),
    QualityModifier("Legendary Artist", 10.0, Availability.Rare)
)) {
    val commonPainting = GeneralEquipment(
        "Common Painting",
        25.0,
        CoinType.Gold,
        null,
        Availability.Common
    )

    val goodPainting = GeneralEquipment(
        "Good Painting",
        80.0,
        CoinType.Gold,
        null,
        Availability.Common
    )

    val excellentPainting = GeneralEquipment(
        "Excellent Painting",
        125.0,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    init{
        itemsAvailable.addAll(listOf(
            commonPainting,
            goodPainting,
            excellentPainting
        ))
    }
}