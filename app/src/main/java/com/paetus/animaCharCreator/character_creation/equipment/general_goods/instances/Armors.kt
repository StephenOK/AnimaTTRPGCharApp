package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Armors: GeneralCategory(
    qualityInput = listOf(
        QualityModifier(
            saveName = "Armor -5",
            qualityType = R.string.armorRed5,
            modifier = 0.5,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Armor +0",
            qualityType = R.string.armorUnchanged,
            modifier = 1.0,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Armor +5",
            qualityType = R.string.armorInc5,
            modifier = 20.0,
            availability = Availability.Rare
        )
    )
) {
    private val padded = GeneralEquipment(
        saveName = "Padded",
        name = R.string.paddedArmor,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 3.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val byrnie = GeneralEquipment(
        saveName = "Byrnie",
        name = R.string.byrnie,
        baseCost = 50.0,
        coinType = CoinType.Gold,
        weight = 9.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val fullPlate = GeneralEquipment(
        saveName = "Full Plate",
        name = R.string.fullPlate,
        baseCost = 400.0,
        coinType = CoinType.Gold,
        weight = 20.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val completeLeather = GeneralEquipment(
        saveName = "Complete Leather",
        name = R.string.completeLeather,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = 7.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val fullFieldPlate = GeneralEquipment(
        saveName = "Full Field Plate",
        name = R.string.fullField,
        baseCost = 800.0,
        coinType = CoinType.Gold,
        weight = 25.0,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val fullHeavyPlate = GeneralEquipment(
        saveName = "Full Heavy Plate",
        name = R.string.fullHeavy,
        baseCost = 700.0,
        coinType = CoinType.Gold,
        weight = 30.0,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val leatherCoat = GeneralEquipment(
        saveName = "Leather Coat",
        name = R.string.leatherCoat,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 3.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val hardenedLeather = GeneralEquipment(
        saveName = "Hardened Leather",
        name = R.string.hardLeather,
        baseCost = 15.0,
        coinType = CoinType.Gold,
        weight = 4.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val studdedLeather = GeneralEquipment(
        saveName = "Studded Leather",
        name = R.string.studLeather,
        baseCost = 25.0,
        coinType = CoinType.Gold,
        weight = 4.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val scaleMail = GeneralEquipment(
        saveName = "Scale Mail",
        name = R.string.scaleMail,
        baseCost = 120.0,
        coinType = CoinType.Gold,
        weight = 9.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val armoredLongcoat = GeneralEquipment(
        saveName = "Armored Longcoat",
        name = R.string.armoredLongcoat,
        baseCost = 5.0,
        coinType = CoinType.Silver,
        weight = 1.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val chainmail = GeneralEquipment(
        saveName = "Chainmail",
        name = R.string.chainmail,
        baseCost = 70.0,
        coinType = CoinType.Gold,
        weight = 13.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val breastplate = GeneralEquipment(
        saveName = "Breastplate",
        name = R.string.breastplate,
        baseCost = 40.0,
        coinType = CoinType.Gold,
        weight = 4.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val fur = GeneralEquipment(
        saveName = "Fur",
        name = R.string.fur,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = 2.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val partialPlate = GeneralEquipment(
        saveName = "Partial Plate",
        name = R.string.partPlate,
        baseCost = 40.0,
        coinType = CoinType.Gold,
        weight = 6.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val lightPlate = GeneralEquipment(
        saveName = "Light Plate",
        name = R.string.lightPlate,
        baseCost = 300.0,
        coinType = CoinType.Gold,
        weight = 18.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val halfPlate = GeneralEquipment(
        saveName = "Half Plate",
        name = R.string.halfPlate,
        baseCost = 100.0,
        coinType = CoinType.Gold,
        weight = 13.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val lightBarding = GeneralEquipment(
        saveName = "Light Barding",
        name = R.string.lightBarding,
        baseCost = 20.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val heavyBarding = GeneralEquipment(
        "Heavy Barding",
        R.string.heavyBarding,
        150.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    init{
        itemsAvailable.addAll(listOf(
            padded,
            byrnie,
            fullPlate,
            completeLeather,
            fullFieldPlate,
            fullHeavyPlate,
            leatherCoat,
            hardenedLeather,
            studdedLeather,
            scaleMail,
            armoredLongcoat,
            chainmail,
            breastplate,
            fur,
            partialPlate,
            lightPlate,
            halfPlate,
            lightBarding,
            heavyBarding
        ))
    }
}