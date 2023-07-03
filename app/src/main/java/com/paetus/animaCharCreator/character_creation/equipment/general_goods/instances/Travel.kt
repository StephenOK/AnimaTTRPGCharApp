package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Travel: GeneralCategory(
    listOf(
        QualityModifier("Mediocre Quality", R.string.mediocreQual,  0.5, Availability.Common),
        QualityModifier("Decent Quality", R.string.decentQual, 1.0, Availability.Common),
        QualityModifier("Good Quality", R.string.goodQual, 5.0, Availability.Common),
        QualityModifier("Luxury Quality", R.string.luxQual, 100.0, Availability.Common),
        QualityModifier("Dangerous Journey", R.string.dangerous, 10.0, Availability.Common)
    )
) {
    val shortPassage = GeneralEquipment(
        "Short Passage (2 - 5 hours)",
        R.string.shortPassage,
        5.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val shortVoyage = GeneralEquipment(
        "Short Voyage (1 day)",
        R.string.shortVoyage,
        10.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val modestVoyage = GeneralEquipment(
        "Modest Voyage (2 - 5 days)",
        R.string.modestVoyage,
        10.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val longVoyage = GeneralEquipment(
        "Long Voyage (more than a week)",
        R.string.longVoyage,
        5.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val shortTrip = GeneralEquipment(
        "Short Trip (1 day)",
        R.string.shortTrip,
        2.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val significantJourney = GeneralEquipment(
        "Significant Journey (2 - 5 days)",
        R.string.significantJourney,
        50.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val modestJourney = GeneralEquipment(
        "Modest Journey (1 - 2 weeks)",
        R.string.modestJourney,
        1.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val longJourney = GeneralEquipment(
        "Long Journey (more than a month)",
        R.string.longJourney,
        5.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
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