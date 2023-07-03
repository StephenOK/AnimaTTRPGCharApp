package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Dwellings: GeneralCategory(listOf(
    QualityModifier("Mediocre Quality", R.string.mediocreQual, 0.5, Availability.Common),
    QualityModifier("Decent Quality", R.string.decentQual, 1.0, Availability.Common),
    QualityModifier("Good Quality", R.string.goodQual, 2.0, Availability.Common),
    QualityModifier("Luxurious", R.string.luxQual, 10.0, Availability.Common),
    QualityModifier("Urban Area", R.string.urban, 2.0, Availability.Common)
)) {
    val shack = GeneralEquipment(
        "Shack",
        R.string.shack,
        15.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val house = GeneralEquipment(
        "House",
        R.string.house,
        60.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val largeHouse = GeneralEquipment(
        "Large House",
        R.string.largeHouse,
        150.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val mansion = GeneralEquipment(
        "Mansion",
        R.string.mansion,
        800.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val palace = GeneralEquipment(
        "Palace",
        R.string.palace,
        2000.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val castle = GeneralEquipment(
        "Castle",
        R.string.castle,
        30000.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
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