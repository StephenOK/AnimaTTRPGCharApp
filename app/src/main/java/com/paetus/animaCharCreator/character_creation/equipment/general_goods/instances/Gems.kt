package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

class Gems: GeneralCategory(null) {
    val zircon = GeneralEquipment(
        "Zircon",
        50.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val various = GeneralEquipment(
        "Various Gems",
        100.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val pearls = GeneralEquipment(
        "Pearls",
        150.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val sapphire = GeneralEquipment(
        "Sapphire",
        200.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val ruby = GeneralEquipment(
        "Ruby",
        300.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val diamond = GeneralEquipment(
        "Diamond",
        320.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val emerald = GeneralEquipment(
        "Emerald",
        440.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val blackOpal = GeneralEquipment(
        "Black Opal",
        500.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
    )

    val blackPearl = GeneralEquipment(
        "Black Pearl",
        650.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
    )

    init{
        itemsAvailable.addAll(listOf(
            zircon,
            various,
            pearls,
            sapphire,
            ruby,
            diamond,
            emerald,
            blackOpal,
            blackPearl
        ))
    }
}