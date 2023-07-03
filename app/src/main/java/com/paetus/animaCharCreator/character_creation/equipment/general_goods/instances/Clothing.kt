package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Clothing: GeneralCategory(
    listOf(
        QualityModifier("Mediocre Quality", R.string.mediocreQual, 0.5, Availability.Common),
        QualityModifier("Decent Quality", R.string.decentQual, 1.0, Availability.Common),
        QualityModifier("Good Quality", R.string.goodQual, 10.0, Availability.Common),
        QualityModifier("Luxury or Designer", R.string.luxDesign, 100.0, Availability.Uncommon)
    )
) {
    val pants = GeneralEquipment(
        "Pants",
        R.string.pants,
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val shirt = GeneralEquipment(
        "Shirt",
        R.string.shirt,
        2.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val vest = GeneralEquipment(
        "Vest",
        R.string.vest,
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val tunic = GeneralEquipment(
        "Tunic",
        R.string.tunic,
        3.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val cap = GeneralEquipment(
        "Cap",
        R.string.cap,
        2.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val jacket = GeneralEquipment(
        "Jacket",
        R.string.jacket,
        2.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val coat = GeneralEquipment(
        "Coat",
        R.string.coat,
        5.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val dress = GeneralEquipment(
        "Dress",
        R.string.dress,
        5.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val scarf = GeneralEquipment(
        "Scarf",
        R.string.scarf,
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val gloves = GeneralEquipment(
        "Gloves",
        R.string.gloves,
        2.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val broadBrimmedHat = GeneralEquipment(
        "Broad-brimmed hat",
        R.string.broadHat,
        2.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val mittens = GeneralEquipment(
        "Mittens",
        R.string.mittens,
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val mensUnderwear = GeneralEquipment(
        "Men\'s Underwear",
        R.string.manUnderwear,
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val womensUnderwear = GeneralEquipment(
        "Women\'s Underwear",
        R.string.womanUnderwear,
        2.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val lingerie = GeneralEquipment(
        "Lingerie",
        R.string.lingerie,
        5.0,
        CoinType.Silver,
        null,
        Availability.Uncommon,
        null
    )

    val belt = GeneralEquipment(
        "Belt",
        R.string.belt,
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val handkerchief = GeneralEquipment(
        "Handkerchief",
        R.string.handkerchief,
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val ballGown = GeneralEquipment(
        "Ball Gown",
        R.string.ballGown,
        5.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val mansFormalOutfit = GeneralEquipment(
        "Man\'s Formal Outfit",
        R.string.manFormal,
        2.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val mansKimono = GeneralEquipment(
        "Man\'s Kimono",
        R.string.manKimono,
        15.0,
        CoinType.Silver,
        null,
        Availability.Uncommon,
        null
    )

    val womansKimono = GeneralEquipment(
        "Woman\'s Kimono",
        R.string.womanKimono,
        20.0,
        CoinType.Silver,
        null,
        Availability.Uncommon,
        null
    )

    val clogs = GeneralEquipment(
        "Clogs",
        R.string.clogs,
        5.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val walkingBoots = GeneralEquipment(
        "Walking Boots",
        R.string.walkBoots,
        5.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val shoes = GeneralEquipment(
        "Shoes",
        R.string.shoes,
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
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