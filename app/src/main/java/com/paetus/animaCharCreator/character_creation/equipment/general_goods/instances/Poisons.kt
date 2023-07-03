package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

class Poisons: GeneralCategory(null) {
    val acquaToffana = GeneralEquipment(
        "Acqua Toffana",
        R.string.acquaToffana,
        25.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val albaladin = GeneralEquipment(
        "Al-Baladin",
        R.string.albaladin,
        80.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
    )

    val royalArsenic = GeneralEquipment(
        "Royal Arsenic",
        R.string.royalArsenic,
        280.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val atropine = GeneralEquipment(
        "Atropine",
        R.string.atropine,
        2.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val belladonna = GeneralEquipment(
        "Belladonna",
        R.string.belladonna,
        15.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val cantharidin = GeneralEquipment(
        "Cantheridin",
        R.string.cantheradin,
        2.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val cyanide = GeneralEquipment(
        "Cyanide",
        R.string.cyanide,
        80.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val curare = GeneralEquipment(
        "Curare",
        R.string.curare,
        100.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
    )

    val muscarine = GeneralEquipment(
        "Muscarine",
        R.string.muscarine,
        1.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val ramalenSap = GeneralEquipment(
        "Ramalen Sap",
        R.string.ramalanSap,
        800.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
    )

    val thallium = GeneralEquipment(
        "Thallium",
        R.string.thallium,
        20.0,
        CoinType.Silver,
        null,
        Availability.Uncommon,
        null
    )

    val whiteCobra = GeneralEquipment(
        "White Cobra Venom",
        R.string.whiteCobra,
        500.0,
        CoinType.Gold,
        null,
        Availability.Rare,
        null
    )

    val serpentVenom = GeneralEquipment(
        "Serpent\'s Venom",
        R.string.serpentVenom,
        5.0,
        CoinType.Gold,
        null,
        Availability.Uncommon,
        null
    )

    val saturnSalt = GeneralEquipment(
        "Salt of Saturn",
        R.string.saturnSalt,
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