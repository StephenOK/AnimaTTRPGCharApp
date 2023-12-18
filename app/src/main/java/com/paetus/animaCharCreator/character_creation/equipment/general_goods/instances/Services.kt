package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Services: GeneralCategory(
    qualityInput = listOf(
        QualityModifier(
            saveName = "Mediocre (Level 0)",
            qualityType = R.string.mediocreLevel,
            modifier = 1.0,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Decent (Level 1)",
            qualityType = R.string.decentLevel,
            modifier = 2.0,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Professional (Level 3)",
            qualityType = R.string.professional,
            modifier = 10.0,
            availability = Availability.Uncommon
        ),
        QualityModifier(
            saveName = "Celebrated (Level 5)",
            qualityType = R.string.celebrated,
            modifier = 100.0,
            availability = Availability.Rare
        )
    )
) {
    private val artisan = GeneralEquipment(
        saveName = "Artisan",
        name = R.string.artisan,
        baseCost = 10.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val assassin = GeneralEquipment(
        saveName = "Assassin",
        name = R.string.assassin,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val guide = GeneralEquipment(
        saveName = "Guide",
        name = R.string.guide,
        baseCost = 20.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val blacksmith = GeneralEquipment(
        saveName = "Blacksmith",
        name = R.string.blacksmith,
        baseCost = 25.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val instructor = GeneralEquipment(
        saveName = "Teacher/Instructor",
        name = R.string.teacher,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val healer = GeneralEquipment(
        saveName = "Physician/Healer",
        name = R.string.healer,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val messenger = GeneralEquipment(
        saveName = "Messenger",
        name = R.string.messenger,
        baseCost = 5.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val mercenary = GeneralEquipment(
        saveName = "Mercenary",
        name = R.string.mercenary,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val lackey = GeneralEquipment(
        saveName = "Valet/Lackey",
        name = R.string.valet,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val thief = GeneralEquipment(
        saveName = "Thief",
        name = R.string.thief,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val troubadour = GeneralEquipment(
        saveName = "Troubadour",
        name = R.string.troubadour,
        baseCost = 5.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
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