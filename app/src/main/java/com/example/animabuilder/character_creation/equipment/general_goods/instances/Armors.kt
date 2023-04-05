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
        1,
        CoinType.Gold,
        3.0,
        Availability.Common
    )

    val byrnie = GeneralEquipment(
        "Byrnie",
        50,
        CoinType.Gold,
        9.0,
        Availability.Common
    )

    val fullPlate = GeneralEquipment(
        "Full Plate",
        400,
        CoinType.Gold,
        20.0,
        Availability.Uncommon
    )

    val completeLeather = GeneralEquipment(
        "Complete Leather",
        5,
        CoinType.Gold,
        7.0,
        Availability.Common
    )

    val fullFieldPlate = GeneralEquipment(
        "Full Field Plate",
        800,
        CoinType.Gold,
        25.0,
        Availability.Rare
    )

    val fullHeavyPlate = GeneralEquipment(
        "Full Heavy Plate",
        700,
        CoinType.Gold,
        30.0,
        Availability.Rare
    )

    val leatherCoat = GeneralEquipment(
        "Leather Coat",
        1,
        CoinType.Gold,
        3.0,
        Availability.Common
    )

    val hardenedLeather = GeneralEquipment(
        "Hardened Leather",
        15,
        CoinType.Gold,
        4.0,
        Availability.Common
    )

    val studdedLeather = GeneralEquipment(
        "Studded Leather",
        25,
        CoinType.Gold,
        4.5,
        Availability.Common
    )

    val scaleMail = GeneralEquipment(
        "Scale Mail",
        120,
        CoinType.Gold,
        9.0,
        Availability.Uncommon
    )

    val armoredLongcoat = GeneralEquipment(
        "Armored Longcoat",
        5,
        CoinType.Silver,
        1.5,
        Availability.Uncommon
    )

    val chainmail = GeneralEquipment(
        "Chainmail",
        70,
        CoinType.Gold,
        13.0,
        Availability.Common
    )

    val breastplate = GeneralEquipment(
        "Breastplate",
        40,
        CoinType.Gold,
        4.0,
        Availability.Uncommon
    )

    val fur = GeneralEquipment(
        "Fur",
        5,
        CoinType.Gold,
        2.0,
        Availability.Common
    )

    val partialPlate = GeneralEquipment(
        "Partial Plate",
        40,
        CoinType.Gold,
        6.0,
        Availability.Uncommon
    )

    val lightPlate = GeneralEquipment(
        "Light Plate",
        300,
        CoinType.Gold,
        18.0,
        Availability.Common
    )

    val halfPlate = GeneralEquipment(
        "Half Plate",
        100,
        CoinType.Gold,
        13.0,
        Availability.Common
    )

    val lightBarding = GeneralEquipment(
        "Light Barding",
        20,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val heavyBarding = GeneralEquipment(
        "Heavy Barding",
        150,
        CoinType.Gold,
        null,
        Availability.Uncommon
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