package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

class Lodging: GeneralCategory(qualityInput = null) {
    private val mediocreLodging = GeneralEquipment(
        saveName = "Mediocre Lodging",
        name = R.string.medLodging,
        baseCost = 5.0,
        coinType = CoinType.Copper,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val decentLodging = GeneralEquipment(
        saveName = "Decent Lodging",
        name = R.string.decentLodging,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val goodLodging = GeneralEquipment(
        saveName = "Good Lodging",
        name = R.string.goodLodging,
        baseCost = 25.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val luxuryLodging = GeneralEquipment(
        saveName = "Luxurious Lodging",
        name = R.string.luxLodging,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Rare,
        currentQuality = null
    )

    init{
        itemsAvailable.addAll(listOf(
            mediocreLodging,
            decentLodging,
            goodLodging,
            luxuryLodging
        ))
    }
}