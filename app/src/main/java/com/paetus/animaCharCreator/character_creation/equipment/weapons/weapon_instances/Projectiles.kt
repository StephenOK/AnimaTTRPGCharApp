package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of projectile weapons the character may take.
 */
class Projectiles: Serializable {
    val arquebus = ProjectileWeapon(
        "arquebus",
        R.string.arquebus,
        null,
        -20,
        6, null,
        null, null, WeaponType.Projectile,
        9, -3, 20,
        4, 80,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.Complex, WeaponAbility.Special), 11,
        R.string.arquebusDesc
    )

    val blowgun = ProjectileWeapon(
        "blowgun",
        R.string.blowgun,
        null,
        -10,
        4, null,
        null, null, WeaponType.Projectile,
        3, -3, 15,
        1, 50,
        null, null,
        R.string.blowgunDesc
    )

    val compositeBow = ProjectileWeapon(
        "compositeBow",
        R.string.compositeBow,
        null,
        -30,
        7, null,
        null, null, WeaponType.Projectile,
        8, -2, 25,
        1, 90,
        listOf(WeaponAbility.TwoHanded), null,
        R.string.compositeBowDesc
    )

    val crossbow = ProjectileWeapon(
        "crossbow",
        R.string.crossbow,
        null,
        0,
        8, 4,
        null, null, WeaponType.Projectile,
        8, -2, 20,
        2, 60,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.OneOrTwoHanded, WeaponAbility.Special), 8,
        R.string.crossbowDesc
    )

    val repeatingCrossbow = ProjectileWeapon(
        "repeatCrossbow",
        R.string.repeatCrossbow,
        null,
        0,
        8, 5,
        null, null, WeaponType.Projectile,
        6, -2, 20,
        3, 60,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.OneOrTwoHanded, WeaponAbility.Special), 8,
        R.string.repeatCrossbowDesc
    )

    val heavyCrossbow = ProjectileWeapon(
        "heavyCrossbow",
        R.string.heavyCrossbow,
        null,
        -20,
        10, 7,
        null, null, WeaponType.Projectile,
        8, -1, 20,
        2, 80,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.OneOrTwoHanded, WeaponAbility.Special), 10,
        R.string.heavyCrossbowDesc
    )

    val miniCrossbow = ProjectileWeapon(
        "miniCrossbow",
        R.string.miniCrossbow,
        null,
        10,
        3, null,
        null, null, WeaponType.Projectile,
        5, -4, 15,
        2, 30,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.Special), 5,
        R.string.miniCrossbowDesc
    )

    val shortBow = ProjectileWeapon(
        "shortBow",
        R.string.shortBow,
        null,
        -10,
        4, null,
        null, null, WeaponType.Projectile,
        7, -3, 15,
        1, 40,
        listOf(WeaponAbility.TwoHanded), null,
        R.string.shortBowDesc
    )

    val longBow = ProjectileWeapon(
        "longbow",
        R.string.longbow,
        null,
        -30,
        7, null,
        null, null, WeaponType.Projectile,
        8, -2, 20,
        1, 60,
        listOf(WeaponAbility.TwoHanded), null,
        R.string.longbowDesc
    )

    val matchlock = ProjectileWeapon(
        "matchlock",
        R.string.matchlock,
        null,
        0,
        4, null,
        null, null, WeaponType.Projectile,
        8, -3, 20,
        4, 50,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.Complex, WeaponAbility.Special), 9,
        R.string.matchlockDesc
    )

    val sling = ProjectileWeapon(
        "sling",
        R.string.sling,
        null,
        -40,
        4, null,
        null, null, WeaponType.Projectile,
        3, -6, 10,
        1, 50,
        null, null,
        R.string.slingDesc
    )

    val lightBallista = ProjectileWeapon(
        "lightBallista",
        R.string.lightBallista,
        null,
        -80,
        null, null,
        null, null, WeaponType.Projectile,
        18, null, 25,
        10, 150,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.Special), 12,
        R.string.lightBallistaDesc
    )

    val heavyBallista = ProjectileWeapon(
        "heavyBallista",
        R.string.heavyBallista,
        null,
        -100,
        null, null,
        null, null, WeaponType.Projectile,
        20, null, 30,
        12, 200,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.Special), 13,
        R.string.heavyBallistaDesc
    )

    val cannon = ProjectileWeapon(
        "cannon",
        R.string.cannon,
        null,
        -100,
        null, null,
        null, null, WeaponType.Projectile,
        24, null, 30,
        12, 250,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.Special), 14,
        R.string.cannonDesc
    )

    val projectiles = listOf(arquebus, blowgun, compositeBow, crossbow, repeatingCrossbow,
        heavyCrossbow, miniCrossbow, shortBow, longBow, matchlock, sling, lightBallista, heavyBallista, cannon)
}