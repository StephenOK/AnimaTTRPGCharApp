package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

class Transport: GeneralCategory(null) {
    val packMule = GeneralEquipment(
        "Pack Mule",
        1.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val horse = GeneralEquipment(
        "Horse",
        5.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val purebredHorse = GeneralEquipment(
        "Purebred Horse",
        50.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val warhorse = GeneralEquipment(
        "Warhorse",
        250.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val workingOx = GeneralEquipment(
        "Working Ox",
        2.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val packBull = GeneralEquipment(
        "Pack Bull",
        2.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val ridingBull = GeneralEquipment(
        "Riding Bull",
        3.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val saintBernard = GeneralEquipment(
        "Saint Bernard",
        15.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val cart = GeneralEquipment(
        "Cart",
        1.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val clothWagon = GeneralEquipment(
        "Cloth-enclosed Wagon",
        5.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val woodWagon = GeneralEquipment(
        "Wood-enclosed Wagon",
        30.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val standardCoach = GeneralEquipment(
        "Standard Coach",
        60.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val luxuryCoach = GeneralEquipment(
        "Luxurious Coach",
        150.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val sleigh = GeneralEquipment(
        "Sleigh",
        2.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val boat = GeneralEquipment(
        "Boat",
        20.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val singleSail = GeneralEquipment(
        "Single-masted Sailing Vessel",
        150.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val twoSail = GeneralEquipment(
        "Two-masted Sailing Vessel",
        450.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val threeSail = GeneralEquipment(
        "Three-masted Sailing Vessel",
        1200.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val smallCruiser = GeneralEquipment(
        "Small Cruiser",
        3500.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val cruiser = GeneralEquipment(
        "Cruiser",
        8000.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val warship = GeneralEquipment(
        "Warship",
        15000.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
    )

    init{
        itemsAvailable.addAll(listOf(
            packMule,
            horse,
            purebredHorse,
            warhorse,
            workingOx,
            packBull,
            ridingBull,
            saintBernard,
            cart,
            clothWagon,
            woodWagon,
            standardCoach,
            luxuryCoach,
            sleigh,
            boat,
            singleSail,
            twoSail,
            threeSail,
            smallCruiser,
            cruiser,
            warship
        ))
    }
}