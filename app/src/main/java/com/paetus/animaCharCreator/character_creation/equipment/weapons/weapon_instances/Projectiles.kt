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
    private val arquebus = ProjectileWeapon(
        saveName = "arquebus",
        name = R.string.arquebus,
        damage = null,
        speed = -20,
        oneHandStr = 6,
        twoHandStr = null,
        primaryType = null,
        secondaryType = null,
        type = WeaponType.Projectile,
        fortitude = 9,
        breakage = -3,
        presence = 20,
        reloadOrRate = 4,
        range = 80,
        ability = listOf(WeaponAbility.OwnStrength, WeaponAbility.Complex, WeaponAbility.Special),
        ownStrength = 11,
        description = R.string.arquebusDesc
    )

    val blowgun = ProjectileWeapon(
        saveName = "blowgun",
        name = R.string.blowgun,
        damage = null,
        speed = -10,
        oneHandStr = 4, twoHandStr = null,
        primaryType = null, secondaryType = null, type = WeaponType.Projectile,
        fortitude = 3, breakage = -3, presence = 15,
        reloadOrRate = 1, range = 50,
        ability = null, ownStrength = null,
        description = R.string.blowgunDesc
    )

    private val compositeBow = ProjectileWeapon(
        saveName = "compositeBow",
        name = R.string.compositeBow,
        damage = null,
        speed = -30,
        oneHandStr = 7, twoHandStr = null,
        primaryType = null, secondaryType = null, type = WeaponType.Projectile,
        fortitude = 8, breakage = -2, presence = 25,
        reloadOrRate = 1, range = 90,
        ability = listOf(WeaponAbility.TwoHanded), ownStrength = null,
        description = R.string.compositeBowDesc
    )

    val crossbow = ProjectileWeapon(
        saveName = "crossbow",
        name = R.string.crossbow,
        damage = null,
        speed = 0,
        oneHandStr = 8,
        twoHandStr = 4,
        primaryType = null,
        secondaryType = null,
        type = WeaponType.Projectile,
        fortitude = 8,
        breakage = -2,
        presence = 20,
        reloadOrRate = 2,
        range = 60,
        ability = listOf(WeaponAbility.OwnStrength, WeaponAbility.OneOrTwoHanded, WeaponAbility.Special),
        ownStrength = 8,
        description = R.string.crossbowDesc
    )

    private val repeatingCrossbow = ProjectileWeapon(
        saveName = "repeatCrossbow",
        name = R.string.repeatCrossbow,
        damage = null,
        speed = 0,
        oneHandStr = 8,
        twoHandStr = 5,
        primaryType = null,
        secondaryType = null,
        type = WeaponType.Projectile,
        fortitude = 6,
        breakage = -2,
        presence = 20,
        reloadOrRate = 3,
        range = 60,
        ability = listOf(WeaponAbility.OwnStrength, WeaponAbility.OneOrTwoHanded, WeaponAbility.Special),
        ownStrength = 8,
        description = R.string.repeatCrossbowDesc
    )

    private val heavyCrossbow = ProjectileWeapon(
        saveName = "heavyCrossbow",
        name = R.string.heavyCrossbow,
        damage = null,
        speed = -20,
        oneHandStr = 10,
        twoHandStr = 7,
        primaryType = null,
        secondaryType = null,
        type = WeaponType.Projectile,
        fortitude = 8,
        breakage = -1,
        presence = 20,
        reloadOrRate = 2,
        range = 80,
        ability = listOf(WeaponAbility.OwnStrength, WeaponAbility.OneOrTwoHanded, WeaponAbility.Special),
        ownStrength = 10,
        description = R.string.heavyCrossbowDesc
    )

    val miniCrossbow = ProjectileWeapon(
        saveName = "miniCrossbow",
        name = R.string.miniCrossbow,
        damage = null,
        speed = 10,
        oneHandStr = 3, twoHandStr = null,
        primaryType = null, secondaryType = null, type = WeaponType.Projectile,
        fortitude = 5, breakage = -4, presence = 15,
        reloadOrRate = 2, range = 30,
        ability = listOf(WeaponAbility.OwnStrength, WeaponAbility.Special), ownStrength = 5,
        description = R.string.miniCrossbowDesc
    )

    val shortBow = ProjectileWeapon(
        saveName = "shortBow",
        name = R.string.shortBow,
        damage = null,
        speed = -10,
        oneHandStr = 4, twoHandStr = null,
        primaryType = null, secondaryType = null, type = WeaponType.Projectile,
        fortitude = 7, breakage = -3, presence = 15,
        reloadOrRate = 1, range = 40,
        ability = listOf(WeaponAbility.TwoHanded), ownStrength = null,
        description = R.string.shortBowDesc
    )

    val longBow = ProjectileWeapon(
        saveName = "longbow",
        name = R.string.longbow,
        damage = null,
        speed = -30,
        oneHandStr = 7, twoHandStr = null,
        primaryType = null, secondaryType = null, type = WeaponType.Projectile,
        fortitude = 8, breakage = -2, presence = 20,
        reloadOrRate = 1, range = 60,
        ability = listOf(WeaponAbility.TwoHanded), ownStrength = null,
        description = R.string.longbowDesc
    )

    private val matchlock = ProjectileWeapon(
        saveName = "matchlock",
        name = R.string.matchlock,
        damage = null,
        speed = 0,
        oneHandStr = 4,
        twoHandStr = null,
        primaryType = null,
        secondaryType = null,
        type = WeaponType.Projectile,
        fortitude = 8,
        breakage = -3,
        presence = 20,
        reloadOrRate = 4,
        range = 50,
        ability = listOf(WeaponAbility.OwnStrength, WeaponAbility.Complex, WeaponAbility.Special),
        ownStrength = 9,
        description = R.string.matchlockDesc
    )

    private val sling = ProjectileWeapon(
        saveName = "sling",
        name = R.string.sling,
        damage = null,
        speed = -40,
        oneHandStr = 4, twoHandStr = null,
        primaryType = null, secondaryType = null, type = WeaponType.Projectile,
        fortitude = 3, breakage = -6, presence = 10,
        reloadOrRate = 1, range = 50,
        ability = null, ownStrength = null,
        description = R.string.slingDesc
    )

    private val lightBallista = ProjectileWeapon(
        saveName = "lightBallista",
        name = R.string.lightBallista,
        damage = null,
        speed = -80,
        oneHandStr = null, twoHandStr = null,
        primaryType = null, secondaryType = null, type = WeaponType.Projectile,
        fortitude = 18, breakage = null, presence = 25,
        reloadOrRate = 10, range = 150,
        ability = listOf(WeaponAbility.OwnStrength, WeaponAbility.Special), ownStrength = 12,
        description = R.string.lightBallistaDesc
    )

    private val heavyBallista = ProjectileWeapon(
        saveName = "heavyBallista",
        name = R.string.heavyBallista,
        damage = null,
        speed = -100,
        oneHandStr = null, twoHandStr = null,
        primaryType = null, secondaryType = null, type = WeaponType.Projectile,
        fortitude = 20, breakage = null, presence = 30,
        reloadOrRate = 12, range = 200,
        ability = listOf(WeaponAbility.OwnStrength, WeaponAbility.Special), ownStrength = 13,
        description = R.string.heavyBallistaDesc
    )

    private val cannon = ProjectileWeapon(
        saveName = "cannon",
        name = R.string.cannon,
        damage = null,
        speed = -100,
        oneHandStr = null, twoHandStr = null,
        primaryType = null, secondaryType = null, type = WeaponType.Projectile,
        fortitude = 24, breakage = null, presence = 30,
        reloadOrRate = 12, range = 250,
        ability = listOf(WeaponAbility.OwnStrength, WeaponAbility.Special), ownStrength = 14,
        description = R.string.cannonDesc
    )

    val projectiles = listOf(arquebus, blowgun, compositeBow, crossbow, repeatingCrossbow,
        heavyCrossbow, miniCrossbow, shortBow, longBow, matchlock, sling, lightBallista, heavyBallista, cannon)
}