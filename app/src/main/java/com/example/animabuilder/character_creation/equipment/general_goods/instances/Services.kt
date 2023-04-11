package com.example.animabuilder.character_creation.equipment.general_goods.instances

import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.character_creation.equipment.general_goods.Availability
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralCategory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment
import com.example.animabuilder.character_creation.equipment.general_goods.QualityModifier

class Services: GeneralCategory(listOf(
    QualityModifier("Mediocre (Level 0)", 1.0, Availability.Common),
    QualityModifier("Decent (Level 1)", 2.0, Availability.Common),
    QualityModifier("Professional (Level 3)", 10.0, Availability.Uncommon),
    QualityModifier("Celebrated (Level 5)", 100.0, Availability.Rare)
)) {
    val artisan = GeneralEquipment(
        "Artisan",
        10.0,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val assassin = GeneralEquipment(
        "Assassin",
        5.0,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val guide = GeneralEquipment(
        "Guide",
        20.0,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val blacksmith = GeneralEquipment(
        "Blacksmith",
        25.0,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val instructor = GeneralEquipment(
        "Teacher/Instructor",
        1.0,
        CoinType.Gold,
        null,
        Availability.Common
    )

    val healer = GeneralEquipment(
        "Physician/Healer",
        1.0,
        CoinType.Gold,
        null,
        Availability.Common
    )

    val messenger = GeneralEquipment(
        "Messenger",
        5.0,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val mercenary = GeneralEquipment(
        "Mercenary",
        50.0,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val lackey = GeneralEquipment(
        "Valet/Lackey",
        1.0,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val thief = GeneralEquipment(
        "Thief",
        1.0,
        CoinType.Gold,
        null,
        Availability.Common
    )

    val troubadour = GeneralEquipment(
        "Troubadour",
        5.0,
        CoinType.Silver,
        null,
        Availability.Common
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