package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.weapons.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponAbility
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponType
import java.io.Serializable

/**
 * List of sword weapons the character may take.
 */
class Swords: Serializable {
    val broadsword = Weapon(
        "broadsword",
        R.string.broadsword,
        55,
        -5,
        5, null,
        AttackType.Cut, null, WeaponType.Sword,
        15, 3, 25,
        null, null,
        R.string.broadswordDesc
    )

    val longSword = Weapon(
        "longSword",
        R.string.longsword,
        50,
        0,
        6, null,
        AttackType.Cut, null, WeaponType.Sword,
        13, 3, 25,
        null, null,
        R.string.longswordDesc
    )

    val rapier = Weapon(
        "rapier",
        R.string.rapier,
        40,
        15,
        4, null,
        AttackType.Thrust, AttackType.Cut, WeaponType.Sword,
        11, 2, 20,
        listOf(WeaponAbility.Precision), null,
        R.string.rapierDesc
    )

    val saber = Weapon(
        "saber",
        R.string.saber,
        45,
        10,
        6, null,
        AttackType.Cut, AttackType.Thrust, WeaponType.Sword,
        12, 3, 20,
        null, null,
        R.string.saberDesc
    )

    val scimitar = Weapon(
        "scimitar",
        R.string.scimitar,
        50,
        -5,
        5, null,
        AttackType.Cut, null, WeaponType.Sword,
        13, 4, 20,
        null, null,
        R.string.scimitarDesc
    )

    val katana = Weapon(
        "katana",
        R.string.katana,
        50,
        0,
        6, 5,
        AttackType.Cut, null, WeaponType.Sword,
        11, 3, 40,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        R.string.katanaDesc
    )

    val twoBladeKatana = Weapon(
        "twoBladeKatana",
        R.string.twoBladeKatana,
        55,
        -5,
        8, null,
        AttackType.Cut, null, WeaponType.Sword,
        11, 3, 40,
        listOf(WeaponAbility.Special), null,
        R.string.twoBladeKatanaDesc
    )

    val swords = listOf(broadsword, katana, longSword, rapier, saber, scimitar, twoBladeKatana)
}