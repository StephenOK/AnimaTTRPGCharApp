package com.example.animabuilder.character_creation.equipment.general_goods.instances

import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.character_creation.equipment.general_goods.Availability
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralCategory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment
import com.example.animabuilder.character_creation.equipment.general_goods.QualityModifier

class Art: GeneralCategory(listOf(
    QualityModifier("Mediocre Quality", 0.5, Availability.Common),
    QualityModifier("Decent Quality", 1.0, Availability.Common),
    QualityModifier("Good Quality", 2.0, Availability.Common),
    QualityModifier("Excellent Quality", 10.0, Availability.Common),
    QualityModifier("Luxury or Designer", 100.0, Availability.Common)
)) {
    val candelabra = GeneralEquipment(
        "Candelabra",
        2,
        CoinType.Gold,
        null,
        Availability.Common
    )

    val chinaCabinet = GeneralEquipment(
        "Glass China Cabinet",
        65,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val coatOfArms = GeneralEquipment(
        "Coat of Arms",
        20,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val carpet = GeneralEquipment(
        "Carpet",
        5,
        CoinType.Gold,
        null,
        Availability.Common
    )

    val tapestry = GeneralEquipment(
        "Tapestry",
        4,
        CoinType.Gold,
        null,
        Availability.Common
    )

    val ring = GeneralEquipment(
        "Ring",
        2,
        CoinType.Gold,
        0.5,
        Availability.Common
    )

    val fan = GeneralEquipment(
        "Fan",
        1,
        CoinType.Gold,
        0.25,
        Availability.Common
    )

    val decoratedCane = GeneralEquipment(
        "Decorated Cane",
        3,
        CoinType.Gold,
        2.0,
        Availability.Common
    )

    val broach = GeneralEquipment(
        "Broach",
        10,
        CoinType.Gold,
        0.25,
        Availability.Common
    )

    val scepter = GeneralEquipment(
        "Scepter",
        15,
        CoinType.Gold,
        3.0,
        Availability.Uncommon
    )

    val necklace = GeneralEquipment(
        "Necklace",
        4,
        CoinType.Gold,
        1.0,
        Availability.Common
    )

    val crown = GeneralEquipment(
        "Crown",
        10,
        CoinType.Gold,
        5.0,
        Availability.Uncommon
    )

    val diadem = GeneralEquipment(
        "Diadem",
        5,
        CoinType.Gold,
        1.0,
        Availability.Uncommon
    )

    val buckle = GeneralEquipment(
        "Buckle",
        50,
        CoinType.Silver,
        0.25,
        Availability.Common
    )

    val pin = GeneralEquipment(
        "Pin",
        2,
        CoinType.Gold,
        0.5,
        Availability.Common
    )

    val comb = GeneralEquipment(
        "Comb",
        3,
        CoinType.Gold,
        0.25,
        Availability.Common
    )

    val earrings = GeneralEquipment(
        "Earrings",
        2,
        CoinType.Gold,
        0.25,
        Availability.Common
    )

    val bracelet = GeneralEquipment(
        "Bracelet",
        2,
        CoinType.Gold,
        0.5,
        Availability.Common
    )

    val rosary = GeneralEquipment(
        "Rosary",
        3,
        CoinType.Gold,
        0.25,
        Availability.Common
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