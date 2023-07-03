package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Art: GeneralCategory(listOf(
    QualityModifier("Mediocre Quality", R.string.mediocreQual, 0.5, Availability.Common),
    QualityModifier("Decent Quality", R.string.decentQual, 1.0, Availability.Common),
    QualityModifier("Good Quality", R.string.goodQual, 2.0, Availability.Common),
    QualityModifier("Excellent Quality", R.string.excelQual, 10.0, Availability.Common),
    QualityModifier("Luxury or Designer", R.string.luxDesign, 100.0, Availability.Common)
)) {
    val candelabra = GeneralEquipment(
        "Candelabra",
        R.string.candelabra,
        2.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val chinaCabinet = GeneralEquipment(
        "Glass China Cabinet",
        R.string.glassCabinet,
        65.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val coatOfArms = GeneralEquipment(
        "Coat of Arms",
        R.string.coatOfArms,
        20.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val carpet = GeneralEquipment(
        "Carpet",
        R.string.carpet,
        5.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val tapestry = GeneralEquipment(
        "Tapestry",
        R.string.tapestry,
        4.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val ring = GeneralEquipment(
        "Ring",
        R.string.ring,
        2.0,
        CoinType.Gold,
        0.5,
        Availability.Common,
        null
    )

    val fan = GeneralEquipment(
        "Fan",
        R.string.fan,
        1.0,
        CoinType.Gold,
        0.25,
        Availability.Common,
        null
    )

    val decoratedCane = GeneralEquipment(
        "Decorated Cane",
        R.string.decorateCane,
        3.0,
        CoinType.Gold,
        2.0,
        Availability.Common,
        null
    )

    val broach = GeneralEquipment(
        "Broach",
        R.string.broach,
        10.0,
        CoinType.Gold,
        0.25,
        Availability.Common,
        null
    )

    val scepter = GeneralEquipment(
        "Scepter",
        R.string.scepter,
        15.0,
        CoinType.Gold,
        3.0,
        Availability.Uncommon,
        null
    )

    val necklace = GeneralEquipment(
        "Necklace",
        R.string.necklace,
        4.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val crown = GeneralEquipment(
        "Crown",
        R.string.crown,
        10.0,
        CoinType.Gold,
        5.0,
        Availability.Uncommon,
        null
    )

    val diadem = GeneralEquipment(
        "Diadem",
        R.string.diadem,
        5.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val buckle = GeneralEquipment(
        "Buckle",
        R.string.buckle,
        50.0,
        CoinType.Silver,
        0.25,
        Availability.Common,
        null
    )

    val pin = GeneralEquipment(
        "Pin",
        R.string.pin,
        2.0,
        CoinType.Gold,
        0.5,
        Availability.Common,
        null
    )

    val comb = GeneralEquipment(
        "Comb",
        R.string.comb,
        3.0,
        CoinType.Gold,
        0.25,
        Availability.Common,
        null
    )

    val earrings = GeneralEquipment(
        "Earrings",
        R.string.earring,
        2.0,
        CoinType.Gold,
        0.25,
        Availability.Common,
        null
    )

    val bracelet = GeneralEquipment(
        "Bracelet",
        R.string.bracelet,
        2.0,
        CoinType.Gold,
        0.5,
        Availability.Common,
        null
    )

    val rosary = GeneralEquipment(
        "Rosary",
        R.string.rosary,
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