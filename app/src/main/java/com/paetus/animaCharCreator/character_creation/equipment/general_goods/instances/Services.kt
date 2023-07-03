package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Services: GeneralCategory(listOf(
    QualityModifier("Mediocre (Level 0)", R.string.mediocreLevel, 1.0, Availability.Common),
    QualityModifier("Decent (Level 1)", R.string.decentLevel, 2.0, Availability.Common),
    QualityModifier("Professional (Level 3)", R.string.professional, 10.0, Availability.Uncommon),
    QualityModifier("Celebrated (Level 5)", R.string.celebrated, 100.0, Availability.Rare)
)) {
    val artisan = GeneralEquipment(
        "Artisan",
        R.string.artisan,
        10.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val assassin = GeneralEquipment(
        "Assassin",
        R.string.assassin,
        5.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val guide = GeneralEquipment(
        "Guide",
        R.string.guide,
        20.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val blacksmith = GeneralEquipment(
        "Blacksmith",
        R.string.blacksmith,
        25.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val instructor = GeneralEquipment(
        "Teacher/Instructor",
        R.string.teacher,
        1.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val healer = GeneralEquipment(
        "Physician/Healer",
        R.string.healer,
        1.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val messenger = GeneralEquipment(
        "Messenger",
        R.string.messenger,
        5.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val mercenary = GeneralEquipment(
        "Mercenary",
        R.string.mercenary,
        50.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val lackey = GeneralEquipment(
        "Valet/Lackey",
        R.string.valet,
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val thief = GeneralEquipment(
        "Thief",
        R.string.thief,
        1.0,
        CoinType.Gold,
        null,
        Availability.Common,
        null
    )

    val troubadour = GeneralEquipment(
        "Troubadour",
        R.string.troubadour,
        5.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    init{
        itemsAvailable.addAll(listOf(
            artisan,
            assassin,
            guide,
            blacksmith,
            instructor,
            healer,
            messenger,
            mercenary,
            lackey,
            thief,
            troubadour
        ))
    }
}