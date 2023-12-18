package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Clothing: GeneralCategory(
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
            modifier = 10.0,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Luxury or Designer",
            qualityType = R.string.luxDesign,
            modifier = 100.0,
            availability = Availability.Uncommon
        )
    )
) {
    private val pants = GeneralEquipment(
        saveName = "Pants",
        name = R.string.pants,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val shirt = GeneralEquipment(
        saveName = "Shirt",
        name = R.string.shirt,
        baseCost = 2.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val vest = GeneralEquipment(
        saveName = "Vest",
        name = R.string.vest,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val tunic = GeneralEquipment(
        saveName = "Tunic",
        name = R.string.tunic,
        baseCost = 3.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val cap = GeneralEquipment(
        saveName = "Cap",
        name = R.string.cap,
        baseCost = 2.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val jacket = GeneralEquipment(
        saveName = "Jacket",
        name = R.string.jacket,
        baseCost = 2.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val coat = GeneralEquipment(
        saveName = "Coat",
        name = R.string.coat,
        baseCost = 5.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val dress = GeneralEquipment(
        saveName = "Dress",
        name = R.string.dress,
        baseCost = 5.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val scarf = GeneralEquipment(
        saveName = "Scarf",
        name = R.string.scarf,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val gloves = GeneralEquipment(
        saveName = "Gloves",
        name = R.string.gloves,
        baseCost = 2.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val broadBrimmedHat = GeneralEquipment(
        saveName = "Broad-brimmed hat",
        name = R.string.broadHat,
        baseCost = 2.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val mittens = GeneralEquipment(
        saveName = "Mittens",
        name = R.string.mittens,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val mensUnderwear = GeneralEquipment(
        saveName = "Men\'s Underwear",
        name = R.string.manUnderwear,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val womensUnderwear = GeneralEquipment(
        saveName = "Women\'s Underwear",
        name = R.string.womanUnderwear,
        baseCost = 2.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val lingerie = GeneralEquipment(
        saveName = "Lingerie",
        name = R.string.lingerie,
        baseCost = 5.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val belt = GeneralEquipment(
        saveName = "Belt",
        name = R.string.belt,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val handkerchief = GeneralEquipment(
        saveName = "Handkerchief",
        name = R.string.handkerchief,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val ballGown = GeneralEquipment(
        saveName = "Ball Gown",
        name = R.string.ballGown,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val mansFormalOutfit = GeneralEquipment(
        saveName = "Man\'s Formal Outfit",
        name = R.string.manFormal,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val mansKimono = GeneralEquipment(
        saveName = "Man\'s Kimono",
        name = R.string.manKimono,
        baseCost = 15.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val womansKimono = GeneralEquipment(
        saveName = "Woman\'s Kimono",
        name = R.string.womanKimono,
        baseCost = 20.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val clogs = GeneralEquipment(
        saveName = "Clogs",
        name = R.string.clogs,
        baseCost = 5.0,
        coinType = CoinType.Copper,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val walkingBoots = GeneralEquipment(
        saveName = "Walking Boots",
        name = R.string.walkBoots,
        baseCost = 5.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val shoes = GeneralEquipment(
        saveName = "Shoes",
        name = R.string.shoes,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    init{
        itemsAvailable.addAll(
            listOf(
                pants,
                shirt,
                vest,
                tunic,
                cap,
                jacket,
                coat,
                dress,
                scarf,
                gloves,
                broadBrimmedHat,
                mittens,
                mensUnderwear,
                womensUnderwear,
                lingerie,
                belt,
                handkerchief,
                ballGown,
                mansFormalOutfit,
                mansKimono,
                womansKimono,
                clogs,
                walkingBoots,
                shoes
            )
        )
    }
}