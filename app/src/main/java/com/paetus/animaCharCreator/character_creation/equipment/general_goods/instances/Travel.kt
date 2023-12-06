package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Travel: GeneralCategory(
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
            modifier = 5.0,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Luxury Quality",
            qualityType = R.string.luxQual,
            modifier = 100.0,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Dangerous Journey",
            qualityType = R.string.dangerous,
            modifier = 10.0,
            availability = Availability.Common
        )
    )
) {
    private val shortPassage = GeneralEquipment(
        saveName = "Short Passage (2 - 5 hours)",
        name = R.string.shortPassage,
        baseCost = 5.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val shortVoyage = GeneralEquipment(
        saveName = "Short Voyage (1 day)",
        name = R.string.shortVoyage,
        baseCost = 10.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val modestVoyage = GeneralEquipment(
        saveName = "Modest Voyage (2 - 5 days)",
        name = R.string.modestVoyage,
        baseCost = 10.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val longVoyage = GeneralEquipment(
        saveName = "Long Voyage (more than a week)",
        name = R.string.longVoyage,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val shortTrip = GeneralEquipment(
        saveName = "Short Trip (1 day)",
        name = R.string.shortTrip,
        baseCost = 2.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val significantJourney = GeneralEquipment(
        saveName = "Significant Journey (2 - 5 days)",
        name = R.string.significantJourney,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val modestJourney = GeneralEquipment(
        saveName = "Modest Journey (1 - 2 weeks)",
        name = R.string.modestJourney,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val longJourney = GeneralEquipment(
        saveName = "Long Journey (more than a month)",
        name = R.string.longJourney,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    init{
        itemsAvailable.addAll(listOf(
            shortPassage,
            shortVoyage,
            modestVoyage,
            longVoyage,
            shortTrip,
            significantJourney,
            modestJourney,
            longJourney
        ))
    }
}