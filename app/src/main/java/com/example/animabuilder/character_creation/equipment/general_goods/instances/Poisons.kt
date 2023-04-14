package com.example.animabuilder.character_creation.equipment.general_goods.instances

import com.example.animabuilder.character_creation.equipment.CoinType
import com.example.animabuilder.character_creation.equipment.general_goods.Availability
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralCategory
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment

class Poisons: GeneralCategory(null) {
    val acquaToffana = GeneralEquipment(
        "Acqua Toffana",
        25.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val albaladin = GeneralEquipment(
        "Al-Baladin",
        80.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
    )

    val royalArsenic = GeneralEquipment(
        "Royal Arsenic",
        280.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val atropine = GeneralEquipment(
        "Atropine",
        2.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val belladonna = GeneralEquipment(
        "Belladonna",
        15.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val cantharidin = GeneralEquipment(
        "Cantheridin",
        2.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val cyanide = GeneralEquipment(
        "Cyanide",
        80.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val curare = GeneralEquipment(
        "Curare",
        100.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
    )

    val muscarine = GeneralEquipment(
        "Muscarine",
        1.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val ramalenSap = GeneralEquipment(
        "Ramalen Sap",
        800.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
    )

    val thallium = GeneralEquipment(
        "Thallium",
        20.0,
        CoinType.Silver,
        null,
        Availability.Uncommon,
        null
    )

    val whiteCobra = GeneralEquipment(
        "White Cobra Venom",
        500.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
    )

    val serpentVenom = GeneralEquipment(
        "Serpent\'s Venom",
        5.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val saturnSalt = GeneralEquipment(
        "Salt of Saturn",
        3.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    init{
        itemsAvailable.addAll(listOf(
            acquaToffana,
            albaladin,
            royalArsenic,
            atropine,
            belladonna,
            cantharidin,
            cyanide,
            curare,
            muscarine,
            ramalenSap,
            thallium,
            whiteCobra,
            serpentVenom,
            saturnSalt
        ))
    }
}