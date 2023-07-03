package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Painting: GeneralCategory(listOf(
    QualityModifier("Known Artist", R.string.knownArt, 2.0, Availability.Common),
    QualityModifier("Prestigious Artist", R.string.prestigiousArt, 4.0, Availability.Uncommon),
    QualityModifier("Legendary Artist", R.string.legendaryArt, 10.0, Availability.Rare)
)) {
    val commonPainting = GeneralEquipment(
        "Common Painting",
        R.string.commonPainting,
        25.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val goodPainting = GeneralEquipment(
        "Good Painting",
        R.string.goodPainting,
        80.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val excellentPainting = GeneralEquipment(
        "Excellent Painting",
        R.string.excellentPainting,
        125.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    init{
        itemsAvailable.addAll(listOf(
            commonPainting,
            goodPainting,
            excellentPainting
        ))
    }
}