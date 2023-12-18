package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Dwellings: GeneralCategory(
    qualityInput = listOf(
        QualityModifier(
            saveName = "Mediocre Quality",
            qualityType = R.string.mediocreQual,
            modifier = 0.5,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Decent Quality",
            qualityType = R.string.decentQual,
            modifier = 1.0,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Good Quality",
            qualityType = R.string.goodQual,
            modifier = 2.0,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Luxurious",
            qualityType = R.string.luxQual,
            modifier = 10.0,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Urban Area",
            qualityType = R.string.urban,
            modifier = 2.0,
            availability = Availability.Common
        )
    )
) {
    private val shack = GeneralEquipment(
        saveName = "Shack",
        name = R.string.shack,
        baseCost = 15.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val house = GeneralEquipment(
        saveName = "House",
        name = R.string.house,
        baseCost = 60.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val largeHouse = GeneralEquipment(
        saveName = "Large House",
        name = R.string.largeHouse,
        baseCost = 150.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val mansion = GeneralEquipment(
        saveName = "Mansion",
        name = R.string.mansion,
        baseCost = 800.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val palace = GeneralEquipment(
        saveName = "Palace",
        name = R.string.palace,
        baseCost = 2000.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val castle = GeneralEquipment(
        saveName = "Castle",
        name = R.string.castle,
        baseCost = 30000.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Rare,
        currentQuality = null
    )

    init{
        itemsAvailable.addAll(listOf(
            shack,
            house,
            largeHouse,
            mansion,
            palace,
            castle
        ))
    }
}