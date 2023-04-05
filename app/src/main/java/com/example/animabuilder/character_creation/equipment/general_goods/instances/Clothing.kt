package com.example.animabuilder.character_creation.equipment.general_goods.instances

import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.character_creation.equipment.general_goods.Availability
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralCategory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment
import com.example.animabuilder.character_creation.equipment.general_goods.QualityModifier

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
        1,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val shirt = GeneralEquipment(
        "Shirt",
        2,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val vest = GeneralEquipment(
        "Vest",
        1,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val tunic = GeneralEquipment(
        "Tunic",
        3,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val cap = GeneralEquipment(
        "Cap",
        2,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val jacket = GeneralEquipment(
        "Jacket",
        2,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val coat = GeneralEquipment(
        "Coat",
        5,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val dress = GeneralEquipment(
        "Dress",
        5,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val scarf = GeneralEquipment(
        "Scarf",
        1,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val gloves = GeneralEquipment(
        "Gloves",
        2,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val broadBrimmedHat = GeneralEquipment(
        "Broad-brimmed hat",
        2,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val mittens = GeneralEquipment(
        "Mittens",
        1,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val mensUnderwear = GeneralEquipment(
        "Men\'s Underwear",
        1,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val womensUnderwear = GeneralEquipment(
        "Women\'s Underwear",
        2,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val lingerie = GeneralEquipment(
        "Lingerie",
        5,
        CoinType.Silver,
        null,
        Availability.Uncommon
    )

    val belt = GeneralEquipment(
        "Belt",
        1,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val handkerchief = GeneralEquipment(
        "Handkerchief",
        1,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val ballGown = GeneralEquipment(
        "Ball Gown",
        5,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val mansFormalOutfit = GeneralEquipment(
        "Man\'s Formal Outfit",
        2,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val mansKimono = GeneralEquipment(
        "Man\'s Kimono",
        15,
        CoinType.Silver,
        null,
        Availability.Uncommon
    )

    val womansKimono = GeneralEquipment(
        "Woman\'s Kimono",
        20,
        CoinType.Silver,
        null,
        Availability.Uncommon
    )

    val clogs = GeneralEquipment(
        "Clogs",
        5,
        CoinType.Copper,
        null,
        Availability.Common
    )

    val walkingBoots = GeneralEquipment(
        "Walking Boots",
        5,
        CoinType.Silver,
        null,
        Availability.Common
    )

    val shoes = GeneralEquipment(
        "Shoes",
        1,
        CoinType.Silver,
        null,
        Availability.Common
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