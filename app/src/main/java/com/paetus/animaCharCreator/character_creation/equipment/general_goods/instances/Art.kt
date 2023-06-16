package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Art: GeneralCategory(listOf(
    QualityModifier("Mediocre Quality", 0.5, Availability.Common),
    QualityModifier("Decent Quality", 1.0, Availability.Common),
    QualityModifier("Good Quality", 2.0, Availability.Common),
    QualityModifier("Excellent Quality", 10.0, Availability.Common),
    QualityModifier("Luxury or Designer", 100.0, Availability.Common)
)) {
    val candelabra = GeneralEquipment(
        "Candelabra",
        2.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val chinaCabinet = GeneralEquipment(
        "Glass China Cabinet",
        65.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val coatOfArms = GeneralEquipment(
        "Coat of Arms",
        20.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val carpet = GeneralEquipment(
        "Carpet",
        5.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val tapestry = GeneralEquipment(
        "Tapestry",
        4.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val ring = GeneralEquipment(
        "Ring",
        2.0,
        CoinType.Gold,
        0.5,
        Availability.Common,
        null
    )

    val fan = GeneralEquipment(
        "Fan",
        1.0,
        CoinType.Gold,
        0.25,
        Availability.Common,
        null
    )

    val decoratedCane = GeneralEquipment(
        "Decorated Cane",
        3.0,
        CoinType.Gold,
        2.0,
        Availability.Common,
        null
    )

    val broach = GeneralEquipment(
        "Broach",
        10.0,
        CoinType.Gold,
        0.25,
        Availability.Common,
        null
    )

    val scepter = GeneralEquipment(
        "Scepter",
        15.0,
        CoinType.Gold,
        3.0,
        Availability.Uncommon,
        null
    )

    val necklace = GeneralEquipment(
        "Necklace",
        4.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val crown = GeneralEquipment(
        "Crown",
        10.0,
        CoinType.Gold,
        5.0,
        Availability.Uncommon,
        null
    )

    val diadem = GeneralEquipment(
        "Diadem",
        5.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val buckle = GeneralEquipment(
        "Buckle",
        50.0,
        CoinType.Silver,
        0.25,
        Availability.Common,
        null
    )

    val pin = GeneralEquipment(
        "Pin",
        2.0,
        CoinType.Gold,
        0.5,
        Availability.Common,
        null
    )

    val comb = GeneralEquipment(
        "Comb",
        3.0,
        CoinType.Gold,
        0.25,
        Availability.Common,
        null
    )

    val earrings = GeneralEquipment(
        "Earrings",
        2.0,
        CoinType.Gold,
        0.25,
        Availability.Common,
        null
    )

    val bracelet = GeneralEquipment(
        "Bracelet",
        2.0,
        CoinType.Gold,
        0.5,
        Availability.Common,
        null
    )

    val rosary = GeneralEquipment(
        "Rosary",
        3.0,
        CoinType.Gold,
        0.25,
        Availability.Common,
        null
    )

    init{
        itemsAvailable.addAll(listOf(
            candelabra,
            chinaCabinet,
            coatOfArms,
            carpet,
            tapestry,
            ring,
            fan,
            decoratedCane,
            broach,
            scepter,
            necklace,
            crown,
            diadem,
            buckle,
            pin,
            comb,
            earrings,
            bracelet,
            rosary
        ))
    }
}