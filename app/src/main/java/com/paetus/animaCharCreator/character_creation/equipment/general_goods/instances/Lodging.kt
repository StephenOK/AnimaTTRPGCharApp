package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

class Lodging: GeneralCategory(null) {
    val mediocreLodging = GeneralEquipment(
        "Mediocre Lodging",
        5.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val decentLodging = GeneralEquipment(
        "Decent Lodging",
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val goodLodging = GeneralEquipment(
        "Good Lodging",
        25.0,
        CoinType.Silver,
        null,
        Availability.Uncommon,
        null
    )

    val luxuryLodging = GeneralEquipment(
        "Luxurious Lodging",
        5.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
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