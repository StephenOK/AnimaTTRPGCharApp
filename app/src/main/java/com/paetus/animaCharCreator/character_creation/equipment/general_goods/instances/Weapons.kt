package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Weapons: GeneralCategory(
    qualityInput = listOf(
        QualityModifier(
            saveName = "Weapon -5",
            qualityType = R.string.weaponRed5,
            modifier = 0.5,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Weapon +0",
            qualityType = R.string.weaponUnchanged,
            modifier = 1.0,
            availability = Availability.Common
        ),
        QualityModifier(
            saveName = "Weapon +5",
            qualityType = R.string.weaponInc5,
            modifier = 20.0,
            availability = Availability.Rare
        )
    )
) {
    private val halberd = GeneralEquipment(
        saveName = "Halberd",
        name = R.string.halberd,
        baseCost = 12.0,
        coinType = CoinType.Gold,
        weight = 3.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val harpoon = GeneralEquipment(
        saveName = "Harpoon",
        name = R.string.harpoon,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = 2.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val chain = GeneralEquipment(
        saveName = "Chain",
        name = R.string.chain,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = 2.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val cestus = GeneralEquipment(
        saveName = "Cestus",
        name = R.string.cestus,
        baseCost = 3.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val scimitar = GeneralEquipment(
        saveName = "Scimitar",
        name = R.string.scimitar,
        baseCost = 10.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val dagger = GeneralEquipment(
        saveName = "Dagger",
        name = R.string.dagger,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = 0.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val parryingDagger = GeneralEquipment(
        saveName = "Parrying Dagger",
        name = R.string.parryDagger,
        baseCost = 10.0,
        coinType = CoinType.Gold,
        weight = 0.6,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val broadsword = GeneralEquipment(
        saveName = "Broadsword",
        name = R.string.broadsword,
        baseCost = 4.0,
        coinType = CoinType.Gold,
        weight = 1.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val bastardSword = GeneralEquipment(
        saveName = "Bastard Sword",
        name = R.string.bastardSword,
        baseCost = 20.0,
        coinType = CoinType.Gold,
        weight = 2.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val shortSword = GeneralEquipment(
        saveName = "Short Sword",
        name = R.string.shortSword,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = 0.8,
        availability = Availability.Common,
        currentQuality = null
    )

    private val longSword = GeneralEquipment(
        saveName = "Long Sword",
        name = R.string.longsword,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = 1.4,
        availability = Availability.Common,
        currentQuality = null
    )

    private val stiletto = GeneralEquipment(
        saveName = "Stiletto",
        name = R.string.stiletto,
        baseCost = 60.0,
        coinType = CoinType.Silver,
        weight = 0.4,
        availability = Availability.Common,
        currentQuality = null
    )

    private val rapier = GeneralEquipment(
        saveName = "Rapier",
        name = R.string.rapier,
        baseCost = 25.0,
        coinType = CoinType.Gold,
        weight = 1.2,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val scourge = GeneralEquipment(
        saveName = "Scourge",
        name = R.string.scourge,
        baseCost = 3.0,
        coinType = CoinType.Gold,
        weight = 2.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val foil = GeneralEquipment(
        saveName = "Foil",
        name = R.string.foil,
        baseCost = 15.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val hook = GeneralEquipment(
        saveName = "Hook",
        name = R.string.hook,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val club = GeneralEquipment(
        saveName = "Club",
        name = R.string.club,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = 1.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val greatHammer = GeneralEquipment(
        saveName = "Great Warhammer",
        name = R.string.greatWarhammer,
        baseCost = 15.0,
        coinType = CoinType.Gold,
        weight = 5.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val scythe = GeneralEquipment(
        saveName = "Scythe",
        name = R.string.scythe,
        baseCost = 20.0,
        coinType = CoinType.Silver,
        weight = 2.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val twoHandedAxe = GeneralEquipment(
        saveName = "Two-handed Axe",
        name = R.string.twoHandAxe,
        baseCost = 40.0,
        coinType = CoinType.Gold,
        weight = 5.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val battleAxe = GeneralEquipment(
        saveName = "Battle Axe",
        name = R.string.battleAxe,
        baseCost = 15.0,
        coinType = CoinType.Gold,
        weight = 1.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val handAxe = GeneralEquipment(
        saveName = "Hand Axe",
        name = R.string.handAxe,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val javelin = GeneralEquipment(
        saveName = "Javelin",
        name = R.string.javelin,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val lance = GeneralEquipment(
        saveName = "Lance",
        name = R.string.lance,
        baseCost = 4.0,
        coinType = CoinType.Gold,
        weight = 3.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val cavLance = GeneralEquipment(
        saveName = "Cavalry Lance",
        name = R.string.cavLance,
        baseCost = 20.0,
        coinType = CoinType.Gold,
        weight = 2.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val whip = GeneralEquipment(
        saveName = "Whip",
        name = R.string.whip,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val lasso = GeneralEquipment(
        saveName = "Lasso",
        name = R.string.lasso,
        baseCost = 20.0,
        coinType = CoinType.Silver,
        weight = 0.3,
        availability = Availability.Common,
        currentQuality = null
    )

    private val twoHandedSword = GeneralEquipment(
        saveName = "Two-handed Sword",
        name = R.string.twoHandSword,
        baseCost = 50.0,
        coinType = CoinType.Gold,
        weight = 2.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val multiFlail = GeneralEquipment(
        saveName = "Large Multi-headed Flail",
        name = R.string.largeMultiFlail,
        baseCost = 15.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val warhammer = GeneralEquipment(
        saveName = "Warhammer",
        name = R.string.warhammer,
        baseCost = 4.0,
        coinType = CoinType.Gold,
        weight = 1.2,
        availability = Availability.Common,
        currentQuality = null
    )

    private val flail = GeneralEquipment(
        saveName = "Flail",
        name = R.string.flail,
        baseCost = 12.0,
        coinType = CoinType.Gold,
        weight = 1.2,
        availability = Availability.Common,
        currentQuality = null
    )

    private val mace = GeneralEquipment(
        saveName = "Mace",
        name = R.string.mace,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = 1.8,
        availability = Availability.Common,
        currentQuality = null
    )

    private val heavyMace = GeneralEquipment(
        saveName = "Heavy Mace",
        name = R.string.heavyMace,
        baseCost = 15.0,
        coinType = CoinType.Gold,
        weight = 2.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val gladNet = GeneralEquipment(
        saveName = "Gladiator\'s Net",
        name = R.string.gladNet,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val saber = GeneralEquipment(
        saveName = "Saber",
        name = R.string.saber,
        baseCost = 20.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val trident = GeneralEquipment(
        saveName = "Trident",
        name = R.string.trident,
        baseCost = 3.0,
        coinType = CoinType.Gold,
        weight = 2.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val staff = GeneralEquipment(
        saveName = "Staff",
        name = R.string.staff,
        baseCost = 40.0,
        coinType = CoinType.Silver,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    val shield = GeneralEquipment(
        saveName = "Shield",
        name = R.string.shield,
        baseCost = 20.0,
        coinType = CoinType.Gold,
        weight = 2.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val fullShield = GeneralEquipment(
        saveName = "Full Shield",
        name = R.string.fullShield,
        baseCost = 50.0,
        coinType = CoinType.Gold,
        weight = 6.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val buckler = GeneralEquipment(
        saveName = "Buckler",
        name = R.string.buckler,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val warFan = GeneralEquipment(
        saveName = "War Fan",
        name = R.string.tessen,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = 0.2,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val haruNoOkina = GeneralEquipment(
        saveName = "Haru no Okina",
        name = R.string.haruNoOkina,
        baseCost = 15.0,
        coinType = CoinType.Gold,
        weight = 3.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val raven = GeneralEquipment(
        saveName = "Raven",
        name = R.string.raven,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val katana = GeneralEquipment(
        saveName = "Katana",
        name = R.string.katana,
        baseCost = 50.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val doubleKatana = GeneralEquipment(
        saveName = "Double-bladed Katana",
        name = R.string.twoBladeKatana,
        baseCost = 75.0,
        coinType = CoinType.Gold,
        weight = 2.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val kusarigama = GeneralEquipment(
        saveName = "Kusari-Gama",
        name = R.string.kusari,
        baseCost = 10.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val nodachi = GeneralEquipment(
        saveName = "No-Dachi",
        name = R.string.nodachi,
        baseCost = 70.0,
        coinType = CoinType.Gold,
        weight = 1.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val nunchakus = GeneralEquipment(
        saveName = "Nunchakus",
        name = R.string.nunchakus,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = 0.6,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val sai = GeneralEquipment(
        saveName = "Sai",
        name = R.string.sai,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 0.4,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val shuriken = GeneralEquipment(
        saveName = "Shuriken",
        name = R.string.shuriken,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = 0.1,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val shuko = GeneralEquipment(
        saveName = "Shuko",
        name = R.string.shuko,
        baseCost = 3.0,
        coinType = CoinType.Gold,
        weight = 0.3,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val tanto = GeneralEquipment(
        saveName = "Tanto",
        name = R.string.tanto,
        baseCost = 20.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val tonfa = GeneralEquipment(
        saveName = "Tonfa",
        name = R.string.tonfa,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 0.3,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val boomerang = GeneralEquipment(
        saveName = "Boomerang",
        name = R.string.boomerang,
        baseCost = 3.0,
        coinType = CoinType.Gold,
        weight = 0.6,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val claws = GeneralEquipment(
        saveName = "Claws",
        name = R.string.claws,
        baseCost = 3.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val swordBreaker = GeneralEquipment(
        saveName = "Sword Breaker",
        name = R.string.swordBreaker,
        baseCost = 30.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val katar = GeneralEquipment(
        saveName = "Katar",
        name = R.string.katar,
        baseCost = 40.0,
        coinType = CoinType.Gold,
        weight = 0.6,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    val arquebus = GeneralEquipment(
        saveName = "Arquebus",
        name = R.string.arquebus,
        baseCost = 500.0,
        coinType = CoinType.Gold,
        weight = 4.5,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val shortBow = GeneralEquipment(
        saveName = "Short Bow",
        name = R.string.shortBow,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = 0.8,
        availability = Availability.Common,
        currentQuality = null
    )

    private val longbow = GeneralEquipment(
        saveName = "Longbow",
        name = R.string.longbow,
        baseCost = 20.0,
        coinType = CoinType.Gold,
        weight = 1.4,
        availability = Availability.Common,
        currentQuality = null
    )

    private val compositeBow = GeneralEquipment(
        saveName = "Composite Longbow",
        name = R.string.compositeBow,
        baseCost = 80.0,
        coinType = CoinType.Gold,
        weight = 1.8,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val pistolBall = GeneralEquipment(
        saveName = "Pistol Ball",
        name = R.string.pistolBall,
        baseCost = 20.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val cannonball = GeneralEquipment(
        saveName = "Cannonball",
        name = R.string.cannonball,
        baseCost = 10.0,
        coinType = CoinType.Gold,
        weight = 15.0,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val lightBallista = GeneralEquipment(
        saveName = "Light Ballista",
        name = R.string.lightBallista,
        baseCost = 100.0,
        coinType = CoinType.Gold,
        weight = 180.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val heavyBallista = GeneralEquipment(
        saveName = "Heavy Ballista",
        name = R.string.heavyBallista,
        baseCost = 250.0,
        coinType = CoinType.Gold,
        weight = 350.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val crossbow = GeneralEquipment(
        saveName = "Crossbow",
        name = R.string.crossbow,
        baseCost = 50.0,
        coinType = CoinType.Gold,
        weight = 2.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val miniCrossbow = GeneralEquipment(
        saveName = "Miniature Crossbow",
        name = R.string.miniCrossbow,
        baseCost = 250.0,
        coinType = CoinType.Gold,
        weight = 1.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val repeatingCrossbow = GeneralEquipment(
        saveName = "Repeating Crossbow",
        name = R.string.repeatCrossbow,
        baseCost = 200.0,
        coinType = CoinType.Gold,
        weight = 4.0,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val heavyCrossbow = GeneralEquipment(
        saveName = "Heavy Crossbow",
        name = R.string.heavyCrossbow,
        baseCost = 70.0,
        coinType = CoinType.Gold,
        weight = 3.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val spikedBall = GeneralEquipment(
        saveName = "Spiked Ball",
        name = R.string.spikeBall,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 0.2,
        availability = Availability.Common,
        currentQuality = null
    )

    private val bolas = GeneralEquipment(
        saveName = "Bolas",
        name = R.string.bolas,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val cannon = GeneralEquipment(
        saveName = "Cannon",
        name = R.string.cannon,
        baseCost = 5000.0,
        coinType = CoinType.Gold,
        weight = 400.0,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val blowgun = GeneralEquipment(
        saveName = "Blowgun",
        name = R.string.blowgun,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 0.4,
        availability = Availability.Common,
        currentQuality = null
    )

    private val blowgunDart = GeneralEquipment(
        saveName = "Blowgun Dart",
        name = R.string.blowgunDart,
        baseCost = 20.0,
        coinType = CoinType.Silver,
        weight = 0.1,
        availability = Availability.Common,
        currentQuality = null
    )

    private val dart = GeneralEquipment(
        saveName = "Darts (hand)",
        name = R.string.darts,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = 0.1,
        availability = Availability.Common,
        currentQuality = null
    )

    private val arrow = GeneralEquipment(
        saveName = "Standard Arrow",
        name = R.string.standardArrow,
        baseCost = 2.0,
        coinType = CoinType.Silver,
        weight = 0.1,
        availability = Availability.Common,
        currentQuality = null
    )

    private val flightArrow = GeneralEquipment(
        saveName = "Flight Arrow",
        name = R.string.flightArrow,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = 0.1,
        availability = Availability.Common,
        currentQuality = null
    )

    private val armorArrow = GeneralEquipment(
        saveName = "Armor-piercing Arrow",
        name = R.string.armorArrow,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = 0.1,
        availability = Availability.Common,
        currentQuality = null
    )

    private val matchlock = GeneralEquipment(
        saveName = "Matchlock Pistol",
        name = R.string.matchlock,
        baseCost = 600.0,
        coinType = CoinType.Gold,
        weight = 2.0,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val twoGunpowder = GeneralEquipment(
        saveName = "Gunpowder (2 shots)",
        name = R.string.gunpowder2,
        baseCost = 10.0,
        coinType = CoinType.Gold,
        weight = 0.1,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val tenGunpowder = GeneralEquipment(
        saveName = "Gunpowder (10 shots)",
        name = R.string.gunpowder10,
        baseCost = 100.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val crossbowBolt = GeneralEquipment(
        saveName = "Crossbow Bolt",
        name = R.string.crossbowBolt,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = 0.1,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val miniCrossBolt = GeneralEquipment(
        saveName = "Miniature-crossbow Bolt",
        name = R.string.miniBolt,
        baseCost = 2.0,
        coinType = CoinType.Silver,
        weight = 0.1,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val heavyCrossBolt = GeneralEquipment(
        saveName = "Heavy-crossbow Bolt",
        name = R.string.heavyBolt,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = 0.2,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val chakram = GeneralEquipment(
        saveName = "Chakram",
        name = R.string.chakram,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = 0.8,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val lightQuarrel = GeneralEquipment(
        saveName = "Light Quarrel",
        name = R.string.lightQuarrel,
        baseCost = 10.0,
        coinType = CoinType.Silver,
        weight = 2.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val heavyQuarrel = GeneralEquipment(
        saveName = "Heavy Quarrel",
        name = R.string.heavyQuarrel,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = 5.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    init{
        itemsAvailable.addAll(listOf(
            halberd,
            harpoon,
            chain,
            cestus,
            scimitar,
            dagger,
            parryingDagger,
            broadsword,
            bastardSword,
            shortSword,
            longSword,
            stiletto,
            rapier,
            scourge,
            foil,
            hook,
            club,
            greatHammer,
            scythe,
            twoHandedAxe,
            battleAxe,
            handAxe,
            javelin,
            lance,
            cavLance,
            whip,
            lasso,
            twoHandedSword,
            multiFlail,
            warhammer,
            flail,
            mace,
            heavyMace,
            gladNet,
            saber,
            trident,
            staff,
            shield,
            fullShield,
            buckler,
            warFan,
            haruNoOkina,
            raven,
            katana,
            doubleKatana,
            kusarigama,
            nodachi,
            nunchakus,
            sai,
            shuriken,
            shuko,
            tanto,
            tonfa,
            boomerang,
            claws,
            swordBreaker,
            katar,
            arquebus,
            shortBow,
            longbow,
            compositeBow,
            pistolBall,
            cannonball,
            lightBallista,
            heavyBallista,
            crossbow,
            miniCrossbow,
            repeatingCrossbow,
            heavyCrossbow,
            spikedBall,
            bolas,
            cannon,
            blowgun,
            blowgunDart,
            dart,
            arrow,
            flightArrow,
            armorArrow,
            matchlock,
            twoGunpowder,
            tenGunpowder,
            crossbowBolt,
            miniCrossBolt,
            heavyCrossBolt,
            chakram,
            lightQuarrel,
            heavyQuarrel
        ))
    }
}