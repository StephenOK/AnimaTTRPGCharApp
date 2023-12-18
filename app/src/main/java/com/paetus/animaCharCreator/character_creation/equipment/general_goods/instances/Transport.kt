package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

class Transport: GeneralCategory(qualityInput = null) {
    private val packMule = GeneralEquipment(
        saveName = "Pack Mule",
        name = R.string.packMule,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val horse = GeneralEquipment(
        saveName = "Horse",
        name = R.string.horse,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val purebredHorse = GeneralEquipment(
        saveName = "Purebred Horse",
        name = R.string.purebredHorse,
        baseCost = 50.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val warhorse = GeneralEquipment(
        saveName = "Warhorse",
        name = R.string.warhorse,
        baseCost = 250.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val workingOx = GeneralEquipment(
        saveName = "Working Ox",
        name = R.string.workingOx,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val packBull = GeneralEquipment(
        saveName = "Pack Bull",
        name = R.string.packBull,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val ridingBull = GeneralEquipment(
        saveName = "Riding Bull",
        name = R.string.ridingBull,
        baseCost = 3.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val saintBernard = GeneralEquipment(
        saveName = "Saint Bernard",
        name = R.string.saintBernard,
        baseCost = 15.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val cart = GeneralEquipment(
        saveName = "Cart",
        name = R.string.cart,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val clothWagon = GeneralEquipment(
        saveName = "Cloth-enclosed Wagon",
        name = R.string.clothWagon,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val woodWagon = GeneralEquipment(
        saveName = "Wood-enclosed Wagon",
        name = R.string.woodWagon,
        baseCost = 30.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val standardCoach = GeneralEquipment(
        saveName = "Standard Coach",
        name = R.string.standardCoach,
        baseCost = 60.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val luxuryCoach = GeneralEquipment(
        saveName = "Luxurious Coach",
        name = R.string.luxuryCoach,
        baseCost = 150.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val sleigh = GeneralEquipment(
        saveName = "Sleigh",
        name = R.string.sleigh,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val boat = GeneralEquipment(
        saveName = "Boat",
        name = R.string.boat,
        baseCost = 20.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val singleSail = GeneralEquipment(
        saveName = "Single-masted Sailing Vessel",
        name = R.string.singleSail,
        baseCost = 150.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val twoSail = GeneralEquipment(
        saveName = "Two-masted Sailing Vessel",
        name = R.string.twoSail,
        baseCost = 450.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val threeSail = GeneralEquipment(
        saveName = "Three-masted Sailing Vessel",
        name = R.string.threeSail,
        baseCost = 1200.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val smallCruiser = GeneralEquipment(
        saveName = "Small Cruiser",
        name = R.string.smallCruiser,
        baseCost = 3500.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val cruiser = GeneralEquipment(
        saveName = "Cruiser",
        name = R.string.cruiser,
        baseCost = 8000.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val warship = GeneralEquipment(
        saveName = "Warship",
        name = R.string.warship,
        baseCost = 15000.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Rare,
        currentQuality = null
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