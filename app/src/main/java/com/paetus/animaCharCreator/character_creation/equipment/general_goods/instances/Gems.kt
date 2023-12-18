package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

class Gems: GeneralCategory(qualityInput = null) {
    private val zircon = GeneralEquipment(
        saveName = "Zircon",
        name = R.string.zircon,
        baseCost = 50.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    val various = GeneralEquipment(
        saveName = "Various Gems",
        name = R.string.various,
        baseCost = 100.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val pearls = GeneralEquipment(
        saveName = "Pearls",
        name = R.string.pearls,
        baseCost = 150.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val sapphire = GeneralEquipment(
        saveName = "Sapphire",
        name = R.string.sapphire,
        baseCost = 200.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val ruby = GeneralEquipment(
        saveName = "Ruby",
        name = R.string.ruby,
        baseCost = 300.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val diamond = GeneralEquipment(
        saveName = "Diamond",
        name = R.string.diamond,
        baseCost = 320.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val emerald = GeneralEquipment(
        saveName = "Emerald",
        name = R.string.emerald,
        baseCost = 440.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val blackOpal = GeneralEquipment(
        saveName = "Black Opal",
        name = R.string.blackOpal,
        baseCost = 500.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val blackPearl = GeneralEquipment(
        saveName = "Black Pearl",
        name = R.string.blackPearl,
        baseCost = 650.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Rare,
        currentQuality = null
    )

    init{
        itemsAvailable.addAll(listOf(
            zircon,
            various,
            pearls,
            sapphire,
            ruby,
            diamond,
            emerald,
            blackOpal,
            blackPearl
        ))
    }
}