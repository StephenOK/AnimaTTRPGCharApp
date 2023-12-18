package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Painting: GeneralCategory(
    qualityInput = listOf(
        QualityModifier(
            saveName = "Known Artist",
            qualityType = R.string.knownArt,
            modifier = 2.0,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Prestigious Artist",
            qualityType = R.string.prestigiousArt,
            modifier = 4.0,
            availability = Availability.Uncommon
        ),
        QualityModifier(
            saveName = "Legendary Artist",
            qualityType = R.string.legendaryArt,
            modifier = 10.0,
            availability = Availability.Rare
        )
    )
) {
    private val commonPainting = GeneralEquipment(
        saveName = "Common Painting",
        name = R.string.commonPainting,
        baseCost = 25.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val goodPainting = GeneralEquipment(
        saveName = "Good Painting",
        name = R.string.goodPainting,
        baseCost = 80.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val excellentPainting = GeneralEquipment(
        saveName = "Excellent Painting",
        name = R.string.excellentPainting,
        baseCost = 125.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    init{
        itemsAvailable.addAll(listOf(
            commonPainting,
            goodPainting,
            excellentPainting
        ))
    }
}