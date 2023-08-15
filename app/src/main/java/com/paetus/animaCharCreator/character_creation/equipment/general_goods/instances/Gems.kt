package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

class Gems: GeneralCategory(null) {
    val zircon = GeneralEquipment(
        "Zircon",
        R.string.zircon,
        50.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val various = GeneralEquipment(
        "Various Gems",
        R.string.various,
        100.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val pearls = GeneralEquipment(
        "Pearls",
        R.string.pearls,
        150.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val sapphire = GeneralEquipment(
        "Sapphire",
        R.string.sapphire,
        200.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val ruby = GeneralEquipment(
        "Ruby",
        R.string.ruby,
        300.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val diamond = GeneralEquipment(
        "Diamond",
        R.string.diamond,
        320.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val emerald = GeneralEquipment(
        "Emerald",
        R.string.emerald,
        440.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val blackOpal = GeneralEquipment(
        "Black Opal",
        R.string.blackOpal,
        500.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
    )

    val blackPearl = GeneralEquipment(
        "Black Pearl",
        R.string.blackPearl,
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