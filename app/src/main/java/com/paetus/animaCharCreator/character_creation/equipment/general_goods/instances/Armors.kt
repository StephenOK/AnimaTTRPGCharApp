package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Armors: GeneralCategory(listOf(
    QualityModifier("Armor -5", R.string.armorRed5, 0.5, Availability.Common),
    QualityModifier("Armor +0", R.string.armorUnchanged, 1.0, Availability.Common),
    QualityModifier("Armor +5", R.string.armorInc5, 20.0, Availability.Rare)
)) {
    val padded = GeneralEquipment(
        "Padded",
        R.string.paddedArmor,
        1.0,
        CoinType.Gold,
        3.0,
        Availability.Common,
        null
    )

    val byrnie = GeneralEquipment(
        "Byrnie",
        R.string.byrnie,
        50.0,
        CoinType.Gold,
        9.0,
        Availability.Common,
        null
    )

    val fullPlate = GeneralEquipment(
        "Full Plate",
        R.string.fullPlate,
        400.0,
        CoinType.Gold,
        20.0,
        Availability.Uncommon,
        null
    )

    val completeLeather = GeneralEquipment(
        "Complete Leather",
        R.string.completeLeather,
        5.0,
        CoinType.Gold,
        7.0,
        Availability.Common,
        null
    )

    val fullFieldPlate = GeneralEquipment(
        "Full Field Plate",
        R.string.fullField,
        800.0,
        CoinType.Gold,
        25.0,
        Availability.Rare,
        null
    )

    val fullHeavyPlate = GeneralEquipment(
        "Full Heavy Plate",
        R.string.fullHeavy,
        700.0,
        CoinType.Gold,
        30.0,
        Availability.Rare,
        null
    )

    val leatherCoat = GeneralEquipment(
        "Leather Coat",
        R.string.leatherCoat,
        1.0,
        CoinType.Gold,
        3.0,
        Availability.Common,
        null
    )

    val hardenedLeather = GeneralEquipment(
        "Hardened Leather",
        R.string.hardLeather,
        15.0,
        CoinType.Gold,
        4.0,
        Availability.Common,
        null
    )

    val studdedLeather = GeneralEquipment(
        "Studded Leather",
        R.string.studLeather,
        25.0,
        CoinType.Gold,
        4.5,
        Availability.Common,
        null
    )

    val scaleMail = GeneralEquipment(
        "Scale Mail",
        R.string.scaleMail,
        120.0,
        CoinType.Gold,
        9.0,
        Availability.Uncommon,
        null
    )

    val armoredLongcoat = GeneralEquipment(
        "Armored Longcoat",
        R.string.armoredLongcoat,
        5.0,
        CoinType.Silver,
        1.5,
        Availability.Uncommon,
        null
    )

    val chainmail = GeneralEquipment(
        "Chainmail",
        R.string.chainmail,
        70.0,
        CoinType.Gold,
        13.0,
        Availability.Common,
        null
    )

    val breastplate = GeneralEquipment(
        "Breastplate",
        R.string.breastplate,
        40.0,
        CoinType.Gold,
        4.0,
        Availability.Uncommon,
        null
    )

    val fur = GeneralEquipment(
        "Fur",
        R.string.fur,
        5.0,
        CoinType.Gold,
        2.0,
        Availability.Common,
        null
    )

    val partialPlate = GeneralEquipment(
        "Partial Plate",
        R.string.partPlate,
        40.0,
        CoinType.Gold,
        6.0,
        Availability.Uncommon,
        null
    )

    val lightPlate = GeneralEquipment(
        "Light Plate",
        R.string.lightPlate,
        300.0,
        CoinType.Gold,
        18.0,
        Availability.Common,
        null
    )

    val halfPlate = GeneralEquipment(
        "Half Plate",
        R.string.halfPlate,
        100.0,
        CoinType.Gold,
        13.0,
        Availability.Common,
        null
    )

    val lightBarding = GeneralEquipment(
        "Light Barding",
        R.string.lightBarding,
        20.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val heavyBarding = GeneralEquipment(
        "Heavy Barding",
        R.string.heavyBarding,
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