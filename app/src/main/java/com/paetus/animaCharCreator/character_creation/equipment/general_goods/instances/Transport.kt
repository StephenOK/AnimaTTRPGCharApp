package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

class Transport: GeneralCategory(null) {
    val packMule = GeneralEquipment(
        "Pack Mule",
        R.string.packMule,
        1.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val horse = GeneralEquipment(
        "Horse",
        R.string.horse,
        5.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val purebredHorse = GeneralEquipment(
        "Purebred Horse",
        R.string.purebredHorse,
        50.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val warhorse = GeneralEquipment(
        "Warhorse",
        R.string.warhorse,
        250.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val workingOx = GeneralEquipment(
        "Working Ox",
        R.string.workingOx,
        2.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val packBull = GeneralEquipment(
        "Pack Bull",
        R.string.packBull,
        2.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val ridingBull = GeneralEquipment(
        "Riding Bull",
        R.string.ridingBull,
        3.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val saintBernard = GeneralEquipment(
        "Saint Bernard",
        R.string.saintBernard,
        15.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val cart = GeneralEquipment(
        "Cart",
        R.string.cart,
        1.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val clothWagon = GeneralEquipment(
        "Cloth-enclosed Wagon",
        R.string.clothWagon,
        5.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val woodWagon = GeneralEquipment(
        "Wood-enclosed Wagon",
        R.string.woodWagon,
        30.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val standardCoach = GeneralEquipment(
        "Standard Coach",
        R.string.standardCoach,
        60.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val luxuryCoach = GeneralEquipment(
        "Luxurious Coach",
        R.string.luxuryCoach,
        150.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val sleigh = GeneralEquipment(
        "Sleigh",
        R.string.sleigh,
        2.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val boat = GeneralEquipment(
        "Boat",
        R.string.boat,
        20.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val singleSail = GeneralEquipment(
        "Single-masted Sailing Vessel",
        R.string.singleSail,
        150.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val twoSail = GeneralEquipment(
        "Two-masted Sailing Vessel",
        R.string.twoSail,
        450.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val threeSail = GeneralEquipment(
        "Three-masted Sailing Vessel",
        R.string.threeSail,
        1200.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val smallCruiser = GeneralEquipment(
        "Small Cruiser",
        R.string.smallCruiser,
        3500.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val cruiser = GeneralEquipment(
        "Cruiser",
        R.string.cruiser,
        8000.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val warship = GeneralEquipment(
        "Warship",
        R.string.warship,
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