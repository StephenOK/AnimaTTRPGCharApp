package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of axe weapons the character may take.
 */
class Axes: Serializable {
    val battleAxe = ProjectileWeapon(
        "battleAxe",
        R.string.battleAxe,
        70,
        -30,
        7, null,
        AttackType.Cut, AttackType.Impact, WeaponType.Axe,
        15, 5, 25,
        100, 5,
        listOf(WeaponAbility.Throwable), null,
        R.string.battleAxeDesc
    )

    val handAxe = ProjectileWeapon(
        "handAxe",
        R.string.handAxe,
        45,
        0,
        5, null,
        AttackType.Cut, null, WeaponType.Axe,
        13, 4, 15,
        80, 10,
        listOf(WeaponAbility.Throwable), null,
        R.string.handAxeDesc
    )

    val hoe = Weapon(
        "hoe",
        R.string.hoe,
        30,
        -20,
        4, null,
        AttackType.Cut, AttackType.Impact, WeaponType.Axe,
        10, 1, 15,
        null, null,
        R.string.emptyItem
    )

    val woodAxe = Weapon(
        "woodAxe",
        R.string.woodsmanAxe,
        40,
        -10,
        7, 5,
        AttackType.Cut, null, WeaponType.Axe,
        12, 3, 15,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        R.string.emptyItem
    )

    val axes = listOf(battleAxe, handAxe, hoe, woodAxe)
}