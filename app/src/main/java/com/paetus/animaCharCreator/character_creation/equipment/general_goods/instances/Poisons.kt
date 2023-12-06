package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

class Poisons: GeneralCategory(qualityInput = null) {
    private val acquaToffana = GeneralEquipment(
        saveName = "Acqua Toffana",
        name = R.string.acquaToffana,
        baseCost = 25.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val albaladin = GeneralEquipment(
        saveName = "Al-Baladin",
        name = R.string.albaladin,
        baseCost = 80.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val royalArsenic = GeneralEquipment(
        saveName = "Royal Arsenic",
        name = R.string.royalArsenic,
        baseCost = 280.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val atropine = GeneralEquipment(
        saveName = "Atropine",
        name = R.string.atropine,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val belladonna = GeneralEquipment(
        saveName = "Belladonna",
        name = R.string.belladonna,
        baseCost = 15.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val cantharidin = GeneralEquipment(
        saveName = "Cantheridin",
        name = R.string.cantharidin,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val cyanide = GeneralEquipment(
        saveName = "Cyanide",
        name = R.string.cyanide,
        baseCost = 80.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val curare = GeneralEquipment(
        saveName = "Curare",
        name = R.string.curare,
        baseCost = 100.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val muscarine = GeneralEquipment(
        saveName = "Muscarine",
        name = R.string.muscarine,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val ramalenSap = GeneralEquipment(
        saveName = "Ramalen Sap",
        name = R.string.ramalenSap,
        baseCost = 800.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val thallium = GeneralEquipment(
        saveName = "Thallium",
        name = R.string.thallium,
        baseCost = 20.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val whiteCobra = GeneralEquipment(
        saveName = "White Cobra Venom",
        name = R.string.whiteCobra,
        baseCost = 500.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val serpentVenom = GeneralEquipment(
        saveName = "Serpent\'s Venom",
        name = R.string.serpentVenom,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val saturnSalt = GeneralEquipment(
        saveName = "Salt of Saturn",
        name = R.string.saturnSalt,
        baseCost = 3.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
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