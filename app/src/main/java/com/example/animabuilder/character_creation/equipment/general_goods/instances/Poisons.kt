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
        Availability.Uncommon
    )

    val albaladin = GeneralEquipment(
        "Al-Baladin",
        80.0,
        CoinType.Gold,
        null,
        Availability.Rare
    )

    val royalArsenic = GeneralEquipment(
        "Royal Arsenic",
        280.0,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val atropine = GeneralEquipment(
        "Atropine",
        2.0,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val belladonna = GeneralEquipment(
        "Belladonna",
        15.0,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val cantharidin = GeneralEquipment(
        "Cantheridin",
        2.0,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val cyanide = GeneralEquipment(
        "Cyanide",
        80.0,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val curare = GeneralEquipment(
        "Curare",
        100.0,
        CoinType.Gold,
        null,
        Availability.Rare
    )

    val muscarine = GeneralEquipment(
        "Muscarine",
        1.0,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val ramalenSap = GeneralEquipment(
        "Ramalen Sap",
        800.0,
        CoinType.Gold,
        null,
        Availability.Rare
    )

    val thallium = GeneralEquipment(
        "Thallium",
        20.0,
        CoinType.Silver,
        null,
        Availability.Uncommon
    )

    val whiteCobra = GeneralEquipment(
        "White Cobra Venom",
        500.0,
        CoinType.Gold,
        null,
        Availability.Rare
    )

    val serpentVenom = GeneralEquipment(
        "Serpent\'s Venom",
        5.0,
        CoinType.Gold,
        null,
        Availability.Uncommon
    )

    val saturnSalt = GeneralEquipment(
        "Salt of Saturn",
        3.0,
        CoinType.Gold,
        null,
        Availability.Uncommon
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