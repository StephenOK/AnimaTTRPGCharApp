package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Clothing: GeneralCategory(
    listOf(
        QualityModifier("Mediocre Quality", 0.5, Availability.Common),
        QualityModifier("Decent Quality", 1.0, Availability.Common),
        QualityModifier("Good Quality", 10.0, Availability.Common),
        QualityModifier("Luxury or Designer", 100.0, Availability.Uncommon)
    )
) {
    val pants = GeneralEquipment(
        "Pants",
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val shirt = GeneralEquipment(
        "Shirt",
        2.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val vest = GeneralEquipment(
        "Vest",
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val tunic = GeneralEquipment(
        "Tunic",
        3.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val cap = GeneralEquipment(
        "Cap",
        2.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val jacket = GeneralEquipment(
        "Jacket",
        2.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val coat = GeneralEquipment(
        "Coat",
        5.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val dress = GeneralEquipment(
        "Dress",
        5.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val scarf = GeneralEquipment(
        "Scarf",
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val gloves = GeneralEquipment(
        "Gloves",
        2.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val broadBrimmedHat = GeneralEquipment(
        "Broad-brimmed hat",
        2.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val mittens = GeneralEquipment(
        "Mittens",
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val mensUnderwear = GeneralEquipment(
        "Men\'s Underwear",
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val womensUnderwear = GeneralEquipment(
        "Women\'s Underwear",
        2.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val lingerie = GeneralEquipment(
        "Lingerie",
        5.0,
        CoinType.Silver,
        null,
        Availability.Uncommon,
        null
    )

    val belt = GeneralEquipment(
        "Belt",
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val handkerchief = GeneralEquipment(
        "Handkerchief",
        1.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val ballGown = GeneralEquipment(
        "Ball Gown",
        5.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val mansFormalOutfit = GeneralEquipment(
        "Man\'s Formal Outfit",
        2.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val mansKimono = GeneralEquipment(
        "Man\'s Kimono",
        15.0,
        CoinType.Silver,
        null,
        Availability.Uncommon,
        null
    )

    val womansKimono = GeneralEquipment(
        "Woman\'s Kimono",
        20.0,
        CoinType.Silver,
        null,
        Availability.Uncommon,
        null
    )

    val clogs = GeneralEquipment(
        "Clogs",
        5.0,
        CoinType.Copper,
        null,
        Availability.Common,
        null
    )

    val walkingBoots = GeneralEquipment(
        "Walking Boots",
        5.0,
        CoinType.Silver,
        null,
        Availability.Common,
        null
    )

    val shoes = GeneralEquipment(
        "Shoes",
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