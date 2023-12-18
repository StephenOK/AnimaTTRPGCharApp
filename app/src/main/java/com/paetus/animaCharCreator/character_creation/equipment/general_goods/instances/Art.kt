package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Art: GeneralCategory(
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
            saveName = "Excellent Quality",
            qualityType = R.string.excelQual,
            modifier = 10.0,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Luxury or Designer",
            qualityType = R.string.luxDesign,
            modifier = 100.0,
            availability = Availability.Common
        )
    )
) {
    private val candelabra = GeneralEquipment(
        saveName = "Candelabra",
        name = R.string.candelabra,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val chinaCabinet = GeneralEquipment(
        saveName = "Glass China Cabinet",
        name = R.string.glassCabinet,
        baseCost = 65.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val coatOfArms = GeneralEquipment(
        saveName = "Coat of Arms",
        name = R.string.coatOfArms,
        baseCost = 20.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val carpet = GeneralEquipment(
        saveName = "Carpet",
        name = R.string.carpet,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val tapestry = GeneralEquipment(
        saveName = "Tapestry",
        name = R.string.tapestry,
        baseCost = 4.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val ring = GeneralEquipment(
        saveName = "Ring",
        name = R.string.ring,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val fan = GeneralEquipment(
        saveName = "Fan",
        name = R.string.fan,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 0.25,
        availability = Availability.Common,
        currentQuality = null
    )

    private val decoratedCane = GeneralEquipment(
        saveName = "Decorated Cane",
        name = R.string.decorateCane,
        baseCost = 3.0,
        coinType = CoinType.Gold,
        weight = 2.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val broach = GeneralEquipment(
        saveName = "Broach",
        name = R.string.broach,
        baseCost = 10.0,
        coinType = CoinType.Gold,
        weight = 0.25,
        availability = Availability.Common,
        currentQuality = null
    )

    private val scepter = GeneralEquipment(
        saveName = "Scepter",
        name = R.string.scepter,
        baseCost = 15.0,
        coinType = CoinType.Gold,
        weight = 3.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val necklace = GeneralEquipment(
        saveName = "Necklace",
        name = R.string.necklace,
        baseCost = 4.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val crown = GeneralEquipment(
        saveName = "Crown",
        name = R.string.crown,
        baseCost = 10.0,
        coinType = CoinType.Gold,
        weight = 5.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val diadem = GeneralEquipment(
        saveName = "Diadem",
        name = R.string.diadem,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val buckle = GeneralEquipment(
        saveName = "Buckle",
        name = R.string.buckle,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = 0.25,
        availability = Availability.Common,
        currentQuality = null
    )

    private val pin = GeneralEquipment(
        saveName = "Pin",
        name = R.string.pin,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val comb = GeneralEquipment(
        saveName = "Comb",
        name = R.string.comb,
        baseCost = 3.0,
        coinType = CoinType.Gold,
        weight = 0.25,
        availability = Availability.Common,
        currentQuality = null
    )

    private val earrings = GeneralEquipment(
        saveName = "Earrings",
        name = R.string.earring,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = 0.25,
        availability = Availability.Common,
        currentQuality = null
    )

    private val bracelet = GeneralEquipment(
        saveName = "Bracelet",
        name = R.string.bracelet,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val rosary = GeneralEquipment(
        saveName = "Rosary",
        name = R.string.rosary,
        baseCost = 3.0,
        coinType = CoinType.Gold,
        weight = 0.25,
        availability = Availability.Common,
        currentQuality = null
    )

    init{
        itemsAvailable.addAll(listOf(
            candelabra,
            chinaCabinet,
            coatOfArms,
            carpet,
            tapestry,
            ring,
            fan,
            decoratedCane,
            broach,
            scepter,
            necklace,
            crown,
            diadem,
            buckle,
            pin,
            comb,
            earrings,
            bracelet,
            rosary
        ))
    }
}