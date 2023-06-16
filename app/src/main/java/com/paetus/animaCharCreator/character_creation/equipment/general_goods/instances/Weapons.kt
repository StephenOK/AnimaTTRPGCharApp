package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.QualityModifier

class Weapons: GeneralCategory(listOf(
    QualityModifier("Weapon -5", 0.5, Availability.Common),
    QualityModifier("Weapon +0", 1.0, Availability.Common),
    QualityModifier("Weapon +5", 20.0, Availability.Rare)
)) {
    val halberd = GeneralEquipment(
        "Halberd",
        12.0,
        CoinType.Gold,
        3.0,
        Availability.Common,
        null
    )

    val harpoon = GeneralEquipment(
        "Harpoon",
        50.0,
        CoinType.Silver,
        2.0,
        Availability.Common,
        null
    )

    val chain = GeneralEquipment(
        "Chain",
        50.0,
        CoinType.Silver,
        2.0,
        Availability.Common,
        null
    )

    val cestus = GeneralEquipment(
        "Cestus",
        3.0,
        CoinType.Gold,
        0.5,
        Availability.Common,
        null
    )

    val scimitar = GeneralEquipment(
        "Scimitar",
        10.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val dagger = GeneralEquipment(
        "Dagger",
        50.0,
        CoinType.Silver,
        0.5,
        Availability.Common,
        null
    )

    val parryingDagger = GeneralEquipment(
        "Parrying Dagger",
        10.0,
        CoinType.Gold,
        0.6,
        Availability.Uncommon,
        null
    )

    val broadsword = GeneralEquipment(
        "Broadsword",
        4.0,
        CoinType.Gold,
        1.5,
        Availability.Common,
        null
    )

    val bastardSword = GeneralEquipment(
        "Bastard Sword",
        20.0,
        CoinType.Gold,
        2.0,
        Availability.Uncommon,
        null
    )

    val shortSword = GeneralEquipment(
        "Short Sword",
        2.0,
        CoinType.Gold,
        0.8,
        Availability.Common,
        null
    )

    val longSword = GeneralEquipment(
        "Long Sword",
        5.0,
        CoinType.Gold,
        1.4,
        Availability.Common,
        null
    )

    val stiletto = GeneralEquipment(
        "Stiletto",
        60.0,
        CoinType.Silver,
        0.4,
        Availability.Common,
        null
    )

    val rapier = GeneralEquipment(
        "Rapier",
        25.0,
        CoinType.Gold,
        1.2,
        Availability.Uncommon,
        null
    )

    val scourge = GeneralEquipment(
        "Scourge",
        3.0,
        CoinType.Gold,
        2.0,
        Availability.Common,
        null
    )

    val foil = GeneralEquipment(
        "Foil",
        15.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val hook = GeneralEquipment(
        "Hook",
        1.0,
        CoinType.Gold,
        0.5,
        Availability.Common,
        null
    )

    val club = GeneralEquipment(
        "Club",
        50.0,
        CoinType.Silver,
        1.5,
        Availability.Common,
        null
    )

    val greatHammer = GeneralEquipment(
        "Great Warhammer",
        15.0,
        CoinType.Gold,
        5.0,
        Availability.Common,
        null
    )

    val scythe = GeneralEquipment(
        "Scythe",
        20.0,
        CoinType.Silver,
        2.5,
        Availability.Common,
        null
    )

    val twoHandedAxe = GeneralEquipment(
        "Two-handed Axe",
        40.0,
        CoinType.Gold,
        5.0,
        Availability.Common,
        null
    )

    val battleAxe = GeneralEquipment(
        "Battle Axe",
        15.0,
        CoinType.Gold,
        1.5,
        Availability.Uncommon,
        null
    )

    val handAxe = GeneralEquipment(
        "Hand Axe",
        2.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val javelin = GeneralEquipment(
        "Javelin",
        2.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val lance = GeneralEquipment(
        "Lance",
        4.0,
        CoinType.Gold,
        3.0,
        Availability.Common,
        null
    )

    val cavLance = GeneralEquipment(
        "Cavalry Lance",
        20.0,
        CoinType.Gold,
        2.0,
        Availability.Uncommon,
        null
    )

    val whip = GeneralEquipment(
        "Whip",
        5.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val lasso = GeneralEquipment(
        "Lasso",
        20.0,
        CoinType.Silver,
        0.3,
        Availability.Common,
        null
    )

    val twoHandedSword = GeneralEquipment(
        "Two-handed Sword",
        50.0,
        CoinType.Gold,
        2.5,
        Availability.Uncommon,
        null
    )

    val multiFlail = GeneralEquipment(
        "Large Multi-headed Flail",
        15.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val warhammer = GeneralEquipment(
        "Warhammer",
        4.0,
        CoinType.Gold,
        1.2,
        Availability.Common,
        null
    )

    val flail = GeneralEquipment(
        "Flail",
        12.0,
        CoinType.Gold,
        1.2,
        Availability.Common,
        null
    )

    val mace = GeneralEquipment(
        "Mace",
        2.0,
        CoinType.Gold,
        1.8,
        Availability.Common,
        null
    )

    val heavyMace = GeneralEquipment(
        "Heavy Mace",
        15.0,
        CoinType.Gold,
        2.5,
        Availability.Common,
        null
    )

    val gladNet = GeneralEquipment(
        "Gladiator\'s Net",
        1.0,
        CoinType.Gold,
        0.5,
        Availability.Common,
        null
    )

    val saber = GeneralEquipment(
        "Saber",
        20.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val trident = GeneralEquipment(
        "Trident",
        3.0,
        CoinType.Gold,
        2.0,
        Availability.Uncommon,
        null
    )

    val staff = GeneralEquipment(
        "Staff",
        40.0,
        CoinType.Silver,
        1.0,
        Availability.Common,
        null
    )

    val shield = GeneralEquipment(
        "Shield",
        20.0,
        CoinType.Gold,
        2.0,
        Availability.Common,
        null
    )

    val fullShield = GeneralEquipment(
        "Full Shield",
        50.0,
        CoinType.Gold,
        6.0,
        Availability.Common,
        null
    )

    val buckler = GeneralEquipment(
        "Buckler",
        5.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val warFan = GeneralEquipment(
        "War Fan",
        5.0,
        CoinType.Gold,
        0.2,
        Availability.Uncommon,
        null
    )

    val haruNoOkina = GeneralEquipment(
        "Haru no Okina",
        15.0,
        CoinType.Gold,
        3.0,
        Availability.Uncommon,
        null
    )

    val raven = GeneralEquipment(
        "Raven",
        5.0,
        CoinType.Gold,
        0.5,
        Availability.Uncommon,
        null
    )

    val katana = GeneralEquipment(
        "Katana",
        50.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val doubleKatana = GeneralEquipment(
        "Double-bladed Katana",
        75.0,
        CoinType.Gold,
        2.0,
        Availability.Uncommon,
        null
    )

    val kusarigama = GeneralEquipment(
        "Kusari-Gama",
        10.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val nodachi = GeneralEquipment(
        "No-Dachi",
        70.0,
        CoinType.Gold,
        1.5,
        Availability.Uncommon,
        null
    )

    val nunchakus = GeneralEquipment(
        "Nunchakus",
        2.0,
        CoinType.Gold,
        0.6,
        Availability.Uncommon,
        null
    )

    val sai = GeneralEquipment(
        "Sai",
        1.0,
        CoinType.Gold,
        0.4,
        Availability.Uncommon,
        null
    )

    val shuriken = GeneralEquipment(
        "Shuriken",
        50.0,
        CoinType.Silver,
        0.1,
        Availability.Uncommon,
        null
    )

    val shuko = GeneralEquipment(
        "Shuko",
        3.0,
        CoinType.Gold,
        0.3,
        Availability.Uncommon,
        null
    )

    val tanto = GeneralEquipment(
        "Tanto",
        20.0,
        CoinType.Gold,
        0.5,
        Availability.Uncommon,
        null
    )

    val tonfa = GeneralEquipment(
        "Tonfa",
        1.0,
        CoinType.Gold,
        0.3,
        Availability.Uncommon,
        null
    )

    val boomerang = GeneralEquipment(
        "Boomerang",
        3.0,
        CoinType.Gold,
        0.6,
        Availability.Uncommon,
        null
    )

    val claws = GeneralEquipment(
        "Claws",
        3.0,
        CoinType.Gold,
        0.5,
        Availability.Uncommon,
        null
    )

    val swordBreaker = GeneralEquipment(
        "Sword Breaker",
        30.0,
        CoinType.Gold,
        1.0,
        Availability.Uncommon,
        null
    )

    val katar = GeneralEquipment(
        "Katar",
        40.0,
        CoinType.Gold,
        0.6,
        Availability.Uncommon,
        null
    )

    val arquebus = GeneralEquipment(
        "Arquebus",
        500.0,
        CoinType.Gold,
        4.5,
        Availability.Rare,
        null
    )

    val shortBow = GeneralEquipment(
        "Short Bow",
        5.0,
        CoinType.Gold,
        0.8,
        Availability.Common,
        null
    )

    val longbow = GeneralEquipment(
        "Longbow",
        20.0,
        CoinType.Gold,
        1.4,
        Availability.Common,
        null
    )

    val compositeBow = GeneralEquipment(
        "Composite Longbow",
        80.0,
        CoinType.Gold,
        1.8,
        Availability.Uncommon,
        null
    )

    val pistolBall = GeneralEquipment(
        "Pistol Ball",
        20.0,
        CoinType.Silver,
        null,
        Availability.Uncommon,
        null
    )

    val cannonball = GeneralEquipment(
        "Cannonball",
        10.0,
        CoinType.Gold,
        15.0,
        Availability.Rare,
        null
    )

    val lightBallista = GeneralEquipment(
        "Light Ballista",
        100.0,
        CoinType.Gold,
        180.0,
        Availability.Common,
        null
    )

    val heavyBallista = GeneralEquipment(
        "Heavy Ballista",
        250.0,
        CoinType.Gold,
        350.0,
        Availability.Uncommon,
        null
    )

    val crossbow = GeneralEquipment(
        "Crossbow",
        50.0,
        CoinType.Gold,
        2.5,
        Availability.Uncommon,
        null
    )

    val miniCrossbow = GeneralEquipment(
        "Miniature Crossbow",
        250.0,
        CoinType.Gold,
        1.5,
        Availability.Uncommon,
        null
    )

    val repeatingCrossbow = GeneralEquipment(
        "Repeating Crossbow",
        200.0,
        CoinType.Gold,
        4.0,
        Availability.Rare,
        null
    )

    val heavyCrossbow = GeneralEquipment(
        "Heavy Crossbow",
        70.0,
        CoinType.Gold,
        3.0,
        Availability.Uncommon,
        null
    )

    val spikedBall = GeneralEquipment(
        "Spiked Ball",
        1.0,
        CoinType.Gold,
        0.2,
        Availability.Common,
        null
    )

    val bolas = GeneralEquipment(
        "Bolas",
        2.0,
        CoinType.Gold,
        1.0,
        Availability.Common,
        null
    )

    val cannon = GeneralEquipment(
        "Cannon",
        5000.0,
        CoinType.Gold,
        400.0,
        Availability.Rare,
        null
    )

    val blowgun = GeneralEquipment(
        "Blowgun",
        1.0,
        CoinType.Gold,
        0.4,
        Availability.Common,
        null
    )

    val blowgunDart = GeneralEquipment(
        "Blowgun Dart",
        20.0,
        CoinType.Silver,
        0.1,
        Availability.Common,
        null
    )

    val blowDart = GeneralEquipment(
        "Blowgun Dart",
        20.0,
        CoinType.Silver,
        0.1,
        Availability.Common,
        null
    )

    val dart = GeneralEquipment(
        "Darts (hand)",
        50.0,
        CoinType.Silver,
        0.1,
        Availability.Common,
        null
    )

    val arrow = GeneralEquipment(
        "Standard Arrow",
        2.0,
        CoinType.Silver,
        0.1,
        Availability.Common,
        null
    )

    val flightArrow = GeneralEquipment(
        "Flight Arrow",
        1.0,
        CoinType.Silver,
        0.1,
        Availability.Common,
        null
    )

    val armorArrow = GeneralEquipment(
        "Armor-piercing Arrow",
        50.0,
        CoinType.Silver,
        0.1,
        Availability.Common,
        null
    )

    val matchlock = GeneralEquipment(
        "Matchlock Pistol",
        600.0,
        CoinType.Gold,
        2.0,
        Availability.Rare,
        null
    )

    val twoGunpowder = GeneralEquipment(
        "Gunpowder (2 shots)",
        10.0,
        CoinType.Gold,
        0.1,
        Availability.Rare,
        null
    )

    val tenGunpowder = GeneralEquipment(
        "Gunpowder (10 shots)",
        100.0,
        CoinType.Gold,
        0.5,
        Availability.Rare,
        null
    )

    val crossbowBolt = GeneralEquipment(
        "Crossbow Bolt",
        1.0,
        CoinType.Silver,
        0.1,
        Availability.Uncommon,
        null
    )

    val miniCrossBolt = GeneralEquipment(
        "Miniature-crossbow Bolt",
        2.0,
        CoinType.Silver,
        0.1,
        Availability.Rare,
        null
    )

    val heavyCrossBolt = GeneralEquipment(
        "Heavy-crossbow Bolt",
        1.0,
        CoinType.Silver,
        0.2,
        Availability.Uncommon,
        null
    )

    val chakram = GeneralEquipment(
        "Chakram",
        5.0,
        CoinType.Gold,
        0.8,
        Availability.Uncommon,
        null
    )

    val lightQuarrel = GeneralEquipment(
        "Light Quarrel",
        10.0,
        CoinType.Silver,
        2.0,
        Availability.Common,
        null
    )

    val heavyQuarrel = GeneralEquipment(
        "Heavy Quarrel",
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
            blowDart,
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