package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List the pole weapons the character may take.
 */
class Poles: Serializable {
    val cavLance = Weapon(
        "cavLance",
        R.string.cavLance,
        80,
        -30,
        8, null,
        AttackType.Thrust, null, WeaponType.Pole,
        12, 7, 25,
        listOf(WeaponAbility.Special), null,
        R.string.cavLanceDesc
    )

    val harpoon = ProjectileWeapon(
        "harpoon",
        R.string.harpoon,
        35,
        -5,
        5, null,
        AttackType.Thrust, null, WeaponType.Pole,
        11, 0, 15,
        100, 20,
        listOf(WeaponAbility.Throwable, WeaponAbility.OneOrTwoHanded), null,
        R.string.harpoonDesc
    )

    val javelin = ProjectileWeapon(
        "javelin",
        R.string.javelin,
        35,
        5,
        4, null,
        AttackType.Thrust, null, WeaponType.Pole,
        10, -2, 20,
        80,  30,
        listOf(WeaponAbility.Throwable), null,
        R.string.javelinDesc
    )

    val lance = ProjectileWeapon(
        "lance",
        R.string.lance,
        40,
        5,
        6, 4,
        AttackType.Thrust, null, WeaponType.Pole,
        13, 2, 25,
        80, 30,
        listOf(WeaponAbility.Throwable, WeaponAbility.OneOrTwoHanded), null,
        R.string.lanceDesc
    )

    val quarterstaff = Weapon(
        "quarterstaff",
        R.string.quarterstaff,
        30,
        10,
        4, null,
        AttackType.Impact, null, WeaponType.Pole,
        11, 0, 30,
        listOf(WeaponAbility.TwoHanded), null,
        R.string.quarterstaffDesc
    )

    val trident = ProjectileWeapon(
        "trident",
        R.string.trident,
        40,
        -10,
        7, 6,
        AttackType.Thrust, null, WeaponType.Pole,
        12, 3, 15,
        100,  15,
        listOf(WeaponAbility.Throwable, WeaponAbility.OneOrTwoHanded), null,
        R.string.tridentDesc
    )

    val haruNoOkina = Weapon(
        "haruNoOkina",
        R.string.haruNoOkina,
        35,
        15,
        4, null,
        AttackType.Cut, AttackType.Thrust, WeaponType.Pole,
        12, 2, 25,
        listOf(WeaponAbility.Complex, WeaponAbility.TwoHanded, WeaponAbility.Special), null,
        R.string.haruNoOkinaDesc
    )

    val poles = listOf(cavLance, harpoon, haruNoOkina, javelin, lance, quarterstaff, trident)
}