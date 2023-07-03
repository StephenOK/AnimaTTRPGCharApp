package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Weapons: GeneralCategory(listOf(
    QualityModifier("Weapon -5", R.string.weaponRed5, 0.5, Availability.Common),
    QualityModifier("Weapon +0", R.string.weaponUnchanged, 1.0, Availability.Common),
    QualityModifier("Weapon +5", R.string.weaponInc5, 20.0, Availability.Rare)
)) {
    val halberd = GeneralEquipment(
        "Halberd",
        R.string.halberd,
        12.0,
        CoinType.Gold,
        3.0,
        Availability.Common,
        null
    )

    val harpoon = GeneralEquipment(
        "Harpoon",
        R.string.harpoon,
        50.0,
        CoinType.Silver,
        2.0,
        Availability.Common,
        null
    )

    val chain = GeneralEquipment(
        "Chain",
        R.string.chain,
        50.0,
        CoinType.Silver,
        2.0,
        Availability.Common,
        null
    )

    val cestus = GeneralEquipment(
        "Cestus",
        R.string.cestus,
        3.0,
        CoinType.Gold,
        0.5,
        Availability.Common,
        null
    )

    val scimitar = GeneralEquipment(
        "Scimitar",
        R.string.scimitar,
        10.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val dagger = GeneralEquipment(
        "Dagger",
        R.string.dagger,
        50.0,
        CoinType.Silver,
        0.5,
        Availability.Common,
        null
    )

    val parryingDagger = GeneralEquipment(
        "Parrying Dagger",
        R.string.parryDagger,
        10.0,
        CoinType.Gold,
        0.6,
        Availability.Uncommon,
        null
    )

    val broadsword = GeneralEquipment(
        "Broadsword",
        R.string.broadsword,
        4.0,
        CoinType.Gold,
        1.5,
        Availability.Common,
        null
    )

    val bastardSword = GeneralEquipment(
        "Bastard Sword",
        R.string.bastardSword,
        20.0,
        CoinType.Gold,
        2.0,
        Availability.Uncommon,
        null
    )

    val shortSword = GeneralEquipment(
        "Short Sword",
        R.string.shortSword,
        2.0,
        CoinType.Gold,
        0.8,
        Availability.Common,
        null
    )

    val longSword = GeneralEquipment(
        "Long Sword",
        R.string.longsword,
        5.0,
        CoinType.Gold,
        1.4,
        Availability.Common,
        null
    )

    val stiletto = GeneralEquipment(
        "Stiletto",
        R.string.stiletto,
        60.0,
        CoinType.Silver,
        0.4,
        Availability.Common,
        null
    )

    val rapier = GeneralEquipment(
        "Rapier",
        R.string.rapier,
        25.0,
        CoinType.Gold,
        1.2,
        Availability.Uncommon,
        null
    )

    val scourge = GeneralEquipment(
        "Scourge",
        R.string.scourge,
        3.0,
        CoinType.Gold,
        2.0,
        Availability.Common,
        null
    )

    val foil = GeneralEquipment(
        "Foil",
        R.string.foil,
        15.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val hook = GeneralEquipment(
        "Hook",
        R.string.hook,
        1.0,
        CoinType.Gold,
        0.5,
        Availability.Common,
        null
    )

    val club = GeneralEquipment(
        "Club",
        R.string.club,
        50.0,
        CoinType.Silver,
        1.5,
        Availability.Common,
        null
    )

    val greatHammer = GeneralEquipment(
        "Great Warhammer",
        R.string.greatWarhammer,
        15.0,
        CoinType.Gold,
        5.0,
        Availability.Common,
        null
    )

    val scythe = GeneralEquipment(
        "Scythe",
        R.string.scythe,
        20.0,
        CoinType.Silver,
        2.5,
        Availability.Common,
        null
    )

    val twoHandedAxe = GeneralEquipment(
        "Two-handed Axe",
        R.string.twoHandAxe,
        40.0,
        CoinType.Gold,
        5.0,
        Availability.Common,
        null
    )

    val battleAxe = GeneralEquipment(
        "Battle Axe",
        R.string.battleAxe,
        15.0,
        CoinType.Gold,
        1.5,
        Availability.Uncommon,
        null
    )

    val handAxe = GeneralEquipment(
        "Hand Axe",
        R.string.handAxe,
        2.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val javelin = GeneralEquipment(
        "Javelin",
        R.string.javelin,
        2.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val lance = GeneralEquipment(
        "Lance",
        R.string.lance,
        4.0,
        CoinType.Gold,
        3.0,
        Availability.Common,
        null
    )

    val cavLance = GeneralEquipment(
        "Cavalry Lance",
        R.string.cavLance,
        20.0,
        CoinType.Gold,
        2.0,
        Availability.Uncommon,
        null
    )

    val whip = GeneralEquipment(
        "Whip",
        R.string.whip,
        5.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val lasso = GeneralEquipment(
        "Lasso",
        R.string.lasso,
        20.0,
        CoinType.Silver,
        0.3,
        Availability.Common,
        null
    )

    val twoHandedSword = GeneralEquipment(
        "Two-handed Sword",
        R.string.twoHandSword,
        50.0,
        CoinType.Gold,
        2.5,
        Availability.Uncommon,
        null
    )

    val multiFlail = GeneralEquipment(
        "Large Multi-headed Flail",
        R.string.largeMultiFlail,
        15.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val warhammer = GeneralEquipment(
        "Warhammer",
        R.string.warhammer,
        4.0,
        CoinType.Gold,
        1.2,
        Availability.Common,
        null
    )

    val flail = GeneralEquipment(
        "Flail",
        R.string.flail,
        12.0,
        CoinType.Gold,
        1.2,
        Availability.Common,
        null
    )

    val mace = GeneralEquipment(
        "Mace",
        R.string.mace,
        2.0,
        CoinType.Gold,
        1.8,
        Availability.Common,
        null
    )

    val heavyMace = GeneralEquipment(
        "Heavy Mace",
        R.string.heavyMace,
        15.0,
        CoinType.Gold,
        2.5,
        Availability.Common,
        null
    )

    val gladNet = GeneralEquipment(
        "Gladiator\'s Net",
        R.string.gladNet,
        1.0,
        CoinType.Gold,
        0.5,
        Availability.Common,
        null
    )

    val saber = GeneralEquipment(
        "Saber",
        R.string.saber,
        20.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val trident = GeneralEquipment(
        "Trident",
        R.string.trident,
        3.0,
        CoinType.Gold,
        2.0,
        Availability.Uncommon,
        null
    )

    val staff = GeneralEquipment(
        "Staff",
        R.string.staff,
        40.0,
        CoinType.Silver,
        1.0,
        Availability.Common,
        null
    )

    val shield = GeneralEquipment(
        "Shield",
        R.string.shield,
        20.0,
        CoinType.Gold,
        2.0,
        Availability.Common,
        null
    )

    val fullShield = GeneralEquipment(
        "Full Shield",
        R.string.fullShield,
        50.0,
        CoinType.Gold,
        6.0,
        Availability.Common,
        null
    )

    val buckler = GeneralEquipment(
        "Buckler",
        R.string.buckler,
        5.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val warFan = GeneralEquipment(
        "War Fan",
        R.string.tessen,
        5.0,
        CoinType.Gold,
        0.2,
        Availability.Uncommon,
        null
    )

    val haruNoOkina = GeneralEquipment(
        "Haru no Okina",
        R.string.haruNoOkina,
        15.0,
        CoinType.Gold,
        3.0,
        Availability.Uncommon,
        null
    )

    val raven = GeneralEquipment(
        "Raven",
        R.string.raven,
        5.0,
        CoinType.Gold,
        0.5,
        Availability.Uncommon,
        null
    )

    val katana = GeneralEquipment(
        "Katana",
        R.string.katana,
        50.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val doubleKatana = GeneralEquipment(
        "Double-bladed Katana",
        R.string.twoBladeKatana,
        75.0,
        CoinType.Gold,
        2.0,
        Availability.Uncommon,
        null
    )

    val kusarigama = GeneralEquipment(
        "Kusari-Gama",
        R.string.kusari,
        10.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val nodachi = GeneralEquipment(
        "No-Dachi",
        R.string.nodachi,
        70.0,
        CoinType.Gold,
        1.5,
        Availability.Uncommon,
        null
    )

    val nunchakus = GeneralEquipment(
        "Nunchakus",
        R.string.nunchakus,
        2.0,
        CoinType.Gold,
        0.6,
        Availability.Uncommon,
        null
    )

    val sai = GeneralEquipment(
        "Sai",
        R.string.sai,
        1.0,
        CoinType.Gold,
        0.4,
        Availability.Uncommon,
        null
    )

    val shuriken = GeneralEquipment(
        "Shuriken",
        R.string.shuriken,
        50.0,
        CoinType.Silver,
        0.1,
        Availability.Uncommon,
        null
    )

    val shuko = GeneralEquipment(
        "Shuko",
        R.string.shuko,
        3.0,
        CoinType.Gold,
        0.3,
        Availability.Uncommon,
        null
    )

    val tanto = GeneralEquipment(
        "Tanto",
        R.string.tanto,
        20.0,
        CoinType.Gold,
        0.5,
        Availability.Uncommon,
        null
    )

    val tonfa = GeneralEquipment(
        "Tonfa",
        R.string.tonfa,
        1.0,
        CoinType.Gold,
        0.3,
        Availability.Uncommon,
        null
    )

    val boomerang = GeneralEquipment(
        "Boomerang",
        R.string.boomerang,
        3.0,
        CoinType.Gold,
        0.6,
        Availability.Uncommon,
        null
    )

    val claws = GeneralEquipment(
        "Claws",
        R.string.claws,
        3.0,
        CoinType.Gold,
        0.5,
        Availability.Uncommon,
        null
    )

    val swordBreaker = GeneralEquipment(
        "Sword Breaker",
        R.string.swordBreaker,
        30.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val katar = GeneralEquipment(
        "Katar",
        R.string.katar,
        40.0,
        CoinType.Gold,
        0.6,
        Availability.Uncommon,
        null
    )

    val arquebus = GeneralEquipment(
        "Arquebus",
        R.string.arquebus,
        500.0,
        CoinType.Gold,
        4.5,
        Availability.Rare,
        null
    )

    val shortBow = GeneralEquipment(
        "Short Bow",
        R.string.shortBow,
        5.0,
        CoinType.Gold,
        0.8,
        Availability.Common,
        null
    )

    val longbow = GeneralEquipment(
        "Longbow",
        R.string.longbow,
        20.0,
        CoinType.Gold,
        1.4,
        Availability.Common,
        null
    )

    val compositeBow = GeneralEquipment(
        "Composite Longbow",
        R.string.compositeBow,
        80.0,
        CoinType.Gold,
        1.8,
        Availability.Uncommon,
        null
    )

    val pistolBall = GeneralEquipment(
        "Pistol Ball",
        R.string.pistolBall,
        20.0,
        CoinType.Silver,
        null,
        Availability.Uncommon,
        null
    )

    val cannonball = GeneralEquipment(
        "Cannonball",
        R.string.cannonball,
        10.0,
        CoinType.Gold,
        15.0,
        Availability.Rare,
        null
    )

    val lightBallista = GeneralEquipment(
        "Light Ballista",
        R.string.lightBallista,
        100.0,
        CoinType.Gold,
        180.0,
        Availability.Common,
        null
    )

    val heavyBallista = GeneralEquipment(
        "Heavy Ballista",
        R.string.heavyBallista,
        250.0,
        CoinType.Gold,
        350.0,
        Availability.Uncommon,
        null
    )

    val crossbow = GeneralEquipment(
        "Crossbow",
        R.string.crossbow,
        50.0,
        CoinType.Gold,
        2.5,
        Availability.Uncommon,
        null
    )

    val miniCrossbow = GeneralEquipment(
        "Miniature Crossbow",
        R.string.miniCrossbow,
        250.0,
        CoinType.Gold,
        1.5,
        Availability.Uncommon,
        null
    )

    val repeatingCrossbow = GeneralEquipment(
        "Repeating Crossbow",
        R.string.repeatCrossbow,
        200.0,
        CoinType.Gold,
        4.0,
        Availability.Rare,
        null
    )

    val heavyCrossbow = GeneralEquipment(
        "Heavy Crossbow",
        R.string.heavyCrossbow,
        70.0,
        CoinType.Gold,
        3.0,
        Availability.Uncommon,
        null
    )

    val spikedBall = GeneralEquipment(
        "Spiked Ball",
        R.string.spikeBall,
        1.0,
        CoinType.Gold,
        0.2,
        Availability.Common,
        null
    )

    val bolas = GeneralEquipment(
        "Bolas",
        R.string.bolas,
        2.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val cannon = GeneralEquipment(
        "Cannon",
        R.string.cannon,
        5000.0,
        CoinType.Gold,
        400.0,
        Availability.Rare,
        null
    )

    val blowgun = GeneralEquipment(
        "Blowgun",
        R.string.blowgun,
        1.0,
        CoinType.Gold,
        0.4,
        Availability.Common,
        null
    )

    val blowgunDart = GeneralEquipment(
        "Blowgun Dart",
        R.string.blowgunDart,
        20.0,
        CoinType.Silver,
        0.1,
        Availability.Common,
        null
    )

    val dart = GeneralEquipment(
        "Darts (hand)",
        R.string.darts,
        50.0,
        CoinType.Silver,
        0.1,
        Availability.Common,
        null
    )

    val arrow = GeneralEquipment(
        "Standard Arrow",
        R.string.standardArrow,
        2.0,
        CoinType.Silver,
        0.1,
        Availability.Common,
        null
    )

    val flightArrow = GeneralEquipment(
        "Flight Arrow",
        R.string.flightArrow,
        1.0,
        CoinType.Silver,
        0.1,
        Availability.Common,
        null
    )

    val armorArrow = GeneralEquipment(
        "Armor-piercing Arrow",
        R.string.armorArrow,
        50.0,
        CoinType.Silver,
        0.1,
        Availability.Common,
        null
    )

    val matchlock = GeneralEquipment(
        "Matchlock Pistol",
        R.string.matchlock,
        600.0,
        CoinType.Gold,
        2.0,
        Availability.Rare,
        null
    )

    val twoGunpowder = GeneralEquipment(
        "Gunpowder (2 shots)",
        R.string.gunpowder2,
        10.0,
        CoinType.Gold,
        0.1,
        Availability.Rare,
        null
    )

    val tenGunpowder = GeneralEquipment(
        "Gunpowder (10 shots)",
        R.string.gunpowder10,
        100.0,
        CoinType.Gold,
        0.5,
        Availability.Rare,
        null
    )

    val crossbowBolt = GeneralEquipment(
        "Crossbow Bolt",
        R.string.crossbowBolt,
        1.0,
        CoinType.Silver,
        0.1,
        Availability.Uncommon,
        null
    )

    val miniCrossBolt = GeneralEquipment(
        "Miniature-crossbow Bolt",
        R.string.miniBolt,
        2.0,
        CoinType.Silver,
        0.1,
        Availability.Rare,
        null
    )

    val heavyCrossBolt = GeneralEquipment(
        "Heavy-crossbow Bolt",
        R.string.heavyBolt,
        1.0,
        CoinType.Silver,
        0.2,
        Availability.Uncommon,
        null
    )

    val chakram = GeneralEquipment(
        "Chakram",
        R.string.chakram,
        5.0,
        CoinType.Gold,
        0.8,
        Availability.Uncommon,
        null
    )

    val lightQuarrel = GeneralEquipment(
        "Light Quarrel",
        R.string.lightQuarrel,
        10.0,
        CoinType.Silver,
        2.0,
        Availability.Common,
        null
    )

    val heavyQuarrel = GeneralEquipment(
        "Heavy Quarrel",
        R.string.heavyQuarrel,
        50.0,
        CoinType.Silver,
        5.0,
        Availability.Uncommon,
        null
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