package com.example.animabuilder.character_creation.equipment.weapons

import java.io.Serializable

class WeaponOptions(): Serializable {
    val bastardSword = Weapon(
        "Bastard Sword",
        70,
        -30,
        9,
        7,
        AttackType.Cut,
        AttackType.Impact,
        WeaponType.Mixed,
        15,
        5,
        25
    )

    val battleAxe = Weapon(
        "Battle Axe",
        70,
        -30,
        7,
        null,
        AttackType.Cut,
        AttackType.Impact,
        WeaponType.Axe,
        15,
        5,
        25
    )

    val broadsword = Weapon(
        "Broadsword",
        55,
        -5,
        5,
        null,
        AttackType.Cut,
        null,
        WeaponType.Sword,
        15,
        3,
        25
    )

    val cavLance = Weapon(
        "Cavalry Lance",
        80,
        -30,
        8,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Pole,
        12,
        7,
        25
    )

    val cestus = Weapon(
        "Cestus",
        25,
        10,
        3,
        null,
        AttackType.Thrust,
        AttackType.Cut,
        WeaponType.Short,
        11,
        -2,
        15
    )

    val chain = Weapon(
        "Chain",
        25,
        0,
        6,
        null,
        AttackType.Impact,
        null,
        WeaponType.Cord,
        13,
        2,
        15
    )

    val club = Weapon(
        "Club",
        30,
        0,
        5,
        null,
        AttackType.Impact,
        null,
        WeaponType.Mace,
        11,
        -2,
        15
    )

    val dagger = Weapon(
        "Dagger",
        30,
        20,
        3,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Pole,
        12,
        7,
        25
    )

    val flail = Weapon(
        "Flail",
        40,
        0,
        6,
        null,
        AttackType.Impact,
        null,
        WeaponType.Mixed,
        13,
        4,
        15
    )

    val foil = Weapon(
        "Foil",
        35,
        15,
        3,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Mixed,
        9,
        -2,
        20
    )

    val gladNet = Weapon(
        "Gladiator's Net",
        5,
        0,
        4,
        null,
        AttackType.Impact,
        AttackType.Cut,
        WeaponType.Cord,
        13,
        -4,
        15
    )

    val greatHammer = Weapon(
        "Great Warhammer",
        70,
        -35,
        10,
        7,
        AttackType.Impact,
        null,
        WeaponType.Mace,
        16,
        6,
        20
    )

    val halberd = Weapon(
        "Halberd",
        60,
        -15,
        11,
        6,
        AttackType.Cut,
        AttackType.Impact,
        WeaponType.Mixed,
        15,
        4,
        20
    )

    val handAxe = Weapon(
        "Hand Axe",
        45,
        0,
        5,
        null,
        AttackType.Cut,
        null,
        WeaponType.Axe,
        13,
        4,
        15
    )

    val harpoon = Weapon(
        "Harpoon",
        35,
        -5,
        5,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Pole,
        11,
        0,
        15
    )

    val heavyBattleMace = Weapon(
        "Heavy Battle-Mace",
        60,
        -15,
        10,
        6,
        AttackType.Impact,
        null,
        WeaponType.Mixed,
        16,
        5,
        15
    )

    val hook = Weapon(
        "Hook",
        30,
        10,
        3,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Short,
        11,
        -2,
        15
    )

    val javelin = Weapon(
        "Javelin",
        35,
        5,
        4,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Pole,
        10,
        -2,
        20
    )

    val lance = Weapon(
        "Lance",
        40,
        5,
        6,
        4,
        AttackType.Thrust,
        null,
        WeaponType.Pole,
        13,
        2,
        25
    )

    val largeMultiFlail = Weapon(
        "Large Multi-Headed Flail",
        80,
        -50,
        10,
        8,
        AttackType.Impact,
        null,
        WeaponType.Mixed,
        14,
        6,
        20
    )

    val lasso = Weapon(
        "Lasso",
        5,
        10,
        4,
        null,
        AttackType.Impact,
        null,
        WeaponType.Cord,
        9,
        -4,
        20
    )

    val longSword = Weapon(
        "Long Sword",
        50,
        0,
        6,
        null,
        AttackType.Cut,
        null,
        WeaponType.Sword,
        13,
        3,
        25
    )

    val mace = Weapon(
        "Mace",
        40,
        0,
        6,
        null,
        AttackType.Impact,
        null,
        WeaponType.Mace,
        14,
        4,
        15
    )

    val parryDagger = Weapon(
        "Parrying Dagger",
        30,
        15,
        3,
        null,
        AttackType.Thrust,
        AttackType.Cut,
        WeaponType.Short,
        12,
        0,
        20
    )

    val quarterstaff = Weapon(
        "Quarterstaff",
        30,
        10,
        4,
        null,
        AttackType.Impact,
        null,
        WeaponType.Pole,
        11,
        0,
        30
    )

    val rapier = Weapon(
        "Rapier",
        40,
        15,
        4,
        null,
        AttackType.Thrust,
        AttackType.Cut,
        WeaponType.Sword,
        11,
        2,
        20
    )

    val saber = Weapon(
        "Saber",
        45,
        10,
        6,
        null,
        AttackType.Cut,
        AttackType.Thrust,
        WeaponType.Sword,
        12,
        3,
        20
    )

    val scimitar = Weapon(
        "Scimitar",
        50,
        -5,
        5,
        null,
        AttackType.Cut,
        null,
        WeaponType.Sword,
        13,
        4,
        20
    )

    val scythe = Weapon(
        "Scythe",
        35,
        0,
        9,
        5,
        AttackType.Cut,
        AttackType.Impact,
        WeaponType.Mixed,
        12,
        2,
        25
    )

    val shortSword = Weapon(
        "Short Sword",
        40,
        15,
        4,
        null,
        AttackType.Thrust,
        AttackType.Cut,
        WeaponType.Short,
        12,
        1,
        20
    )

    val stiletto = Weapon(
        "Stiletto",
        25,
        20,
        3,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Short,
        8,
        -3,
        15
    )

    val trident = Weapon(
        "Weapon",
        40,
        -10,
        7,
        6,
        AttackType.Thrust,
        null,
        WeaponType.Pole,
        12,
        3,
        15
    )

    val twoHandAxe = Weapon(
        "Two-Handed Axe",
        100,
        -70,
        11,
        9,
        AttackType.Cut,
        AttackType.Impact,
        WeaponType.Mixed,
        17,
        7,
        30
    )

    val twoHandSword = Weapon(
        "Two-Handed Sword",
        90,
        -60,
        10,
        8,
        AttackType.Cut,
        AttackType.Impact,
        WeaponType.TwoHanded,
        18,
        6,
        30
    )

    val unarmed = Weapon(
        "Unarmed Combat",
        10,
        20,
        0,
        null,
        AttackType.Impact,
        null,
        WeaponType.Unarmed,
        null,
        null,
        null
    )

    val warhammer = Weapon(
        "Warhammer",
        50,
        -5,
        6,
        null,
        AttackType.Impact,
        null,
        WeaponType.Mace,
        15,
        4,
        15
    )

    val whip = Weapon(
        "Whip",
        35,
        -20,
        4,
        null,
        AttackType.Cut,
        AttackType.Impact,
        WeaponType.Cord,
        9,
        -3,
        20
    )

    val boomerang = Weapon(
        "Boomerang",
        30,
        10,
        4,
        null,
        AttackType.Impact,
        AttackType.Cut,
        WeaponType.Short,
        10,
        0,
        15
    )

    val claws = Weapon(
        "Claws",
        30,
        15,
        4,
        null,
        AttackType.Cut,
        AttackType.Thrust,
        WeaponType.Short,
        12,
        2,
        15
    )

    val haruNoOkina = Weapon(
        "Haru No Okina",
        35,
        15,
        4,
        null,
        AttackType.Cut,
        AttackType.Thrust,
        WeaponType.Pole,
        12,
        2,
        25
    )

    val katana = Weapon(
        "Katana",
        50,
        0,
        6,
        5,
        AttackType.Cut,
        null,
        WeaponType.Sword,
        11,
        3,
        40
    )

    val katar = Weapon(
        "Katar",
        40,
        10,
        8,
        null,
        AttackType.Thrust,
        AttackType.Cut,
        WeaponType.Short,
        13,
        3,
        25
    )

    val kusariGama = Weapon(
        "Kusari-Gama",
        40,
        5,
        5,
        null,
        AttackType.Cut,
        AttackType.Impact,
        WeaponType.Mixed,
        12,
        4,
        25
    )

    val nodachi = Weapon(
        "Nodachi",
        80,
        -35,
        10,
        8,
        AttackType.Cut,
        null,
        WeaponType.TwoHanded,
        14,
        4,
        40
    )

    val nunchakus = Weapon(
        "Nunchakus",
        30,
        15,
        5,
        null,
        AttackType.Impact,
        null,
        WeaponType.Cord,
        11,
        0,
        15
    )

    val raven = Weapon(
        "Raven",
        35,
        10,
        4,
        null,
        AttackType.Impact,
        AttackType.Cut,
        WeaponType.Short,
        11,
        2,
        25
    )

    val sai = Weapon(
        "Sai",
        35,
        15,
        4,
        null,
        AttackType.Thrust,
        AttackType.Cut,
        WeaponType.Short,
        12,
        0,
        25
    )

    val shuko = Weapon(
        "Shuko",
        20,
        10,
        4,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Short,
        9,
        -2,
        25
    )

    val shuriken = Weapon(
        "Shuriken",
        25,
        20,
        4,
        null,
        AttackType.Cut,
        AttackType.Thrust,
        WeaponType.Short,
        10,
        1,
        20
    )

    val swordBreaker = Weapon(
        "Sword Breaker",
        50,
        -20,
        10,
        8,
        AttackType.Impact,
        AttackType.Cut,
        WeaponType.TwoHanded,
        16,
        8,
        25
    )

    val tanto = Weapon(
        "Tanto",
        40,
        20,
        3,
        null,
        AttackType.Cut,
        null,
        WeaponType.Short,
        9,
        1,
        40
    )

    val tessen = Weapon(
        "Tessen (War Fan)",
        30,
        20,
        4,
        null,
        AttackType.Cut,
        AttackType.Impact,
        WeaponType.Short,
        8,
        0,
        25
    )

    val tonfa = Weapon(
        "Tonfa",
        30,
        20,
        4,
        null,
        AttackType.Impact,
        null,
        WeaponType.Short,
        13,
        0,
        25
    )

    val twoBladeKatana = Weapon(
        "Two-Bladed Katana",
        55,
        -5,
        8,
        null,
        AttackType.Cut,
        null,
        WeaponType.Sword,
        11,
        3,
        40
    )

    val brokenBottle = Weapon(
        "Broken Bottle",
        15,
        10,
        3,
        null,
        AttackType.Cut,
        AttackType.Impact,
        WeaponType.Short,
        5,
        -3,
        15
    )

    val chair = Weapon(
        "Chair",
        25,
        -20,
        5,
        null,
        AttackType.Impact,
        null,
        WeaponType.TwoHanded,
        9,
        0,
        20
    )

    val kitchenKnife = Weapon(
        "Kitchen Knife",
        25,
        10,
        4,
        null,
        AttackType.Cut,
        null,
        WeaponType.Short,
        9,
        -1,
        10
    )

    val hammer = Weapon(
        "Hammer",
        30,
        -20,
        4,
        null,
        AttackType.Impact,
        null,
        WeaponType.Mace,
        12,
        2,
        10
    )

    val hoe = Weapon(
        "Hoe",
        30,
        -20,
        4,
        null,
        AttackType.Cut,
        AttackType.Impact,
        WeaponType.Axe,
        10,
        1,
        15
    )

    val metalBar = Weapon(
        "Metal Bar",
        25,
        -5,
        5,
        null,
        AttackType.Impact,
        null,
        WeaponType.Mace,
        12,
        2,
        15
    )

    val pick = Weapon(
        "Pick",
        40,
        -20,
        5,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Short,
        10,
        3,
        15
    )

    val sickle = Weapon(
        "Sickle",
        35,
        -10,
        4,
        null,
        AttackType.Cut,
        AttackType.Thrust,
        WeaponType.Short,
        8,
        0,
        15
    )

    val torch = Weapon(
        "Torch",
        20,
        -10,
        4,
        null,
        AttackType.Impact,
        AttackType.Heat,
        WeaponType.Mace,
        10,
        -2,
        20
    )

    val vase = Weapon(
        "Vase",
        15,
        -10,
        4,
        null,
        AttackType.Impact,
        null,
        WeaponType.Mace,
        6,
        -2,
        20
    )

    val woodenPole = Weapon(
        "Wooden Pole",
        20,
        0,
        4,
        null,
        AttackType.Impact,
        null,
        WeaponType.Mace,
        8,
        -1,
        10
    )

    val woodAxe = Weapon(
        "Woodsman's Axe",
        40,
        -10,
        7,
        5,
        AttackType.Cut,
        null,
        WeaponType.Axe,
        12,
        3,
        15
    )

    val buckler = Weapon(
        "Buckler",
        15,
        -15,
        5,
        null,
        AttackType.Impact,
        null,
        WeaponType.Shield,
        14,
        0,
        20
    )

    val shield = Weapon(
        "Shield",
        20,
        -25,
        7,
        null,
        AttackType.Impact,
        null,
        WeaponType.Shield,
        16,
        0,
        25
    )

    val fullShield = Weapon(
        "Full Shield",
        25,
        -40,
        10,
        null,
        AttackType.Impact,
        null,
        WeaponType.Shield,
        18,
        1,
        25
    )

    val arquebus = Weapon(
        "Arquebus",
        null,
        -20,
        6,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Projectile,
        9,
        -3,
        20
    )

    val bolas = Weapon(
        "Bolas",
        30,
        -10,
        5,
        null,
        AttackType.Impact,
        null,
        WeaponType.Throwing,
        6,
        2,
        15
    )

    val blowgun = Weapon(
        "Blowgun",
        null,
        -10,
        4,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Projectile,
        3,
        -3,
        15
    )

    val chakram = Weapon(
        "Chakram",
        40,
        0,
        6,
        null,
        AttackType.Cut,
        null,
        WeaponType.Throwing,
        9,
        2,
        20
    )

    val compositeBow = Weapon(
        "Composite Bow",
        null,
        -30,
        7,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Projectile,
        8,
        -2,
        25
    )

    val crossbow = Weapon(
        "Crossbow",
        null,
        0,
        8,
        4,
        AttackType.Thrust,
        null,
        WeaponType.Projectile,
        8,
        -2,
        20
    )

    val darts = Weapon(
        "Darts",
        20,
        20,
        3,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Throwing,
        3,
        -4,
        15
    )

    val repeatingCrossbow = Weapon(
        "Repeating Crossbow",
        null,
        0,
        8,
        5,
        AttackType.Thrust,
        null,
        WeaponType.Projectile,
        6,
        -2,
        20
    )

    val heavyCrossbow = Weapon(
        "Heavy Crossbow",
        null,
        -20,
        10,
        7,
        AttackType.Thrust,
        null,
        WeaponType.Projectile,
        8,
        -1,
        20
    )

    val miniCrossbow = Weapon(
        "Miniature Crossbow",
        null,
        10,
        3,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Projectile,
        5,
        -4,
        15
    )

    val shortBow = Weapon(
        "Short Bow",
        null,
        -10,
        4,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Projectile,
        7,
        -3,
        15
    )

    val longBow = Weapon(
        "Longbow",
        null,
        -30,
        7,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Projectile,
        8,
        -2,
        20
    )

    val matchlock = Weapon(
        "Matchlock Pistol",
        null,
        0,
        4,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Projectile,
        8,
        -3,
        20
    )

    val sling = Weapon(
        "Sling",
        null,
        -40,
        4,
        null,
        AttackType.Impact,
        null,
        WeaponType.Projectile,
        3,
        -6,
        10
    )

    val spikedBall = Weapon(
        "Spiked Ball",
        20,
        0,
        5,
        null,
        AttackType.Impact,
        null,
        WeaponType.Throwing,
        10,
        2,
        15
    )

    val lightBallista = Weapon(
        "Light Ballista",
        null,
        -80,
        null,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Projectile,
        18,
        null,
        25
    )

    val heavyBallista = Weapon(
        "Heavy Ballista",
        null,
        -100,
        null,
        null,
        AttackType.Thrust,
        null,
        WeaponType.Projectile,
        20,
        null,
        30
    )

    val cannon = Weapon(
        "Cannon",
        null,
        -100,
        null,
        null,
        AttackType.Impact,
        null,
        WeaponType.Projectile,
        24,
        null,
        30
    )

    val commonWeapons = listOf(bastardSword, battleAxe, broadsword, cavLance, cestus, chain, club,
        dagger, flail, foil, gladNet, greatHammer, halberd, handAxe, harpoon, heavyBattleMace,
        hook, javelin, lance, largeMultiFlail, lasso, longSword, mace, parryDagger, quarterstaff,
        rapier, saber, scimitar, scythe, shortSword, stiletto, trident, twoHandAxe, twoHandSword,
        unarmed, warhammer, whip)

    val exoticWeapons = listOf(boomerang, claws, haruNoOkina, katana, katar, kusariGama, nodachi,
        nunchakus, raven, sai, shuko, shuriken, swordBreaker, tanto, tessen, tonfa, twoBladeKatana)

    val improvisedWeapons = listOf(brokenBottle, chair, kitchenKnife, hammer, hoe, metalBar, pick,
        sickle, torch, vase, woodenPole, woodAxe)

    val shields = listOf(buckler, shield, fullShield)

    val projectileWeapons = listOf(arquebus, bolas, blowgun, chakram, compositeBow, crossbow, darts,
        repeatingCrossbow, heavyCrossbow, miniCrossbow, shortBow, longBow, matchlock, sling, spikedBall)

    val siegeWeapons = listOf(lightBallista, heavyBallista, cannon)
}