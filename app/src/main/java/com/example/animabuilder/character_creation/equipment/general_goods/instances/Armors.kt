package com.example.animabuilder.character_creation.equipment.general_goods.instances

import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.character_creation.equipment.general_goods.Availability
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralCategory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment
import com.example.animabuilder.character_creation.equipment.general_goods.QualityModifier

class Armors: GeneralCategory(listOf(
    QualityModifier("Armor -5", 0.5, Availability.Common),
    QualityModifier("Armor +0", 1.0, Availability.Common),
    QualityModifier("Armor +5", 20.0, Availability.Rare)
)) {
    val padded = GeneralEquipment(
        "Padded",
        1.0,
        CoinType.Gold,
        3.0,
        Availability.Common,
        null
    )

    val byrnie = GeneralEquipment(
        "Byrnie",
        50.0,
        CoinType.Gold,
        9.0,
        Availability.Common,
        null
    )

    val fullPlate = GeneralEquipment(
        "Full Plate",
        400.0,
        CoinType.Gold,
        20.0,
        Availability.Uncommon,
        null
    )

    val completeLeather = GeneralEquipment(
        "Complete Leather",
        5.0,
        CoinType.Gold,
        7.0,
        Availability.Common,
        null
    )

    val fullFieldPlate = GeneralEquipment(
        "Full Field Plate",
        800.0,
        CoinType.Gold,
        25.0,
        Availability.Rare,
        null
    )

    val fullHeavyPlate = GeneralEquipment(
        "Full Heavy Plate",
        700.0,
        CoinType.Gold,
        30.0,
        Availability.Rare,
        null
    )

    val leatherCoat = GeneralEquipment(
        "Leather Coat",
        1.0,
        CoinType.Gold,
        3.0,
        Availability.Common,
        null
    )

    val hardenedLeather = GeneralEquipment(
        "Hardened Leather",
        15.0,
        CoinType.Gold,
        4.0,
        Availability.Common,
        null
    )

    val studdedLeather = GeneralEquipment(
        "Studded Leather",
        25.0,
        CoinType.Gold,
        4.5,
        Availability.Common,
        null
    )

    val scaleMail = GeneralEquipment(
        "Scale Mail",
        120.0,
        CoinType.Gold,
        9.0,
        Availability.Uncommon,
        null
    )

    val armoredLongcoat = GeneralEquipment(
        "Armored Longcoat",
        5.0,
        CoinType.Silver,
        1.5,
        Availability.Uncommon,
        null
    )

    val chainmail = GeneralEquipment(
        "Chainmail",
        70.0,
        CoinType.Gold,
        13.0,
        Availability.Common,
        null
    )

    val breastplate = GeneralEquipment(
        "Breastplate",
        40.0,
        CoinType.Gold,
        4.0,
        Availability.Uncommon,
        null
    )

    val fur = GeneralEquipment(
        "Fur",
        5.0,
        CoinType.Gold,
        2.0,
        Availability.Common,
        null
    )

    val partialPlate = GeneralEquipment(
        "Partial Plate",
        40.0,
        CoinType.Gold,
        6.0,
        Availability.Uncommon,
        null
    )

    val lightPlate = GeneralEquipment(
        "Light Plate",
        300.0,
        CoinType.Gold,
        18.0,
        Availability.Common,
        null
    )

    val halfPlate = GeneralEquipment(
        "Half Plate",
        100.0,
        CoinType.Gold,
        13.0,
        Availability.Common,
        null
    )

    val lightBarding = GeneralEquipment(
        "Light Barding",
        20.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val heavyBarding = GeneralEquipment(
        "Heavy Barding",
        150.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    init{
        itemsAvailable.addAll(listOf(
            padded,
            byrnie,
            fullPlate,
            completeLeather,
            fullFieldPlate,
            fullHeavyPlate,
            leatherCoat,
            hardenedLeather,
            studdedLeather,
            scaleMail,
            armoredLongcoat,
            chainmail,
            breastplate,
            fur,
            partialPlate,
            lightPlate,
            halfPlate,
            lightBarding,
            heavyBarding
        ))
    }
}