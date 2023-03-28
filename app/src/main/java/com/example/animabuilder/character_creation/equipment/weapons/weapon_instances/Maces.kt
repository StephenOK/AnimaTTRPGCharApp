package com.example.animabuilder.character_creation.equipment.weapons.weapon_instances

import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of mace weapons the character may take.
 */
class Maces: Serializable {
    val club = Weapon(
        "Club",
        30,
        0,
        5, null,
        AttackType.Impact, null, WeaponType.Mace,
        11, -2, 15,
        null, null,
        "Made of wood or just stone, the Club is the quintessential Impact weapon."
    )

    val greatHammer = Weapon(
        "Great Warhammer",
        70,
        -35,
        10, 7,
        AttackType.Impact, null, WeaponType.Mace,
        16, 6, 20,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "A very large two-handed Impact weapon. It consists of a straight shaft " +
                "crowned with an enormous metal hammer. Some have sharp pointed tips on the " +
                "side so that they can be used for a second type of attack, a penetrating, or " +
                "Thrust, attack."
    )

    val mace = Weapon(
        "Mace",
        40,
        0,
        6, null,
        AttackType.Impact, null, WeaponType.Mace,
        14, 4, 15,
        null, null,
        "This weapon consists of a wood or metal shaft about half a meter long topped " +
                "with a heavy round or spherical head."
    )

    val warhammer = Weapon(
        "Warhammer",
        50,
        -5,
        6, null,
        AttackType.Impact, null, WeaponType.Mace,
        15, 4, 15,
        null, null,
        "A crushing weapon consisting of a shaft topped by a great steel hammerhead."
    )

    val hammer = Weapon(
        "Hammer",
        30,
        -20,
        4, null,
        AttackType.Impact, null, WeaponType.Mace,
        12, 2, 10,
        null, null,
        ""
    )

    val metalBar = Weapon(
        "Metal Bar",
        25,
        -5,
        5, null,
        AttackType.Impact, null, WeaponType.Mace,
        12, 2, 15,
        null, null,
        ""
    )

    val torch = Weapon(
        "Torch",
        20,
        -10,
        4, null,
        AttackType.Impact, AttackType.Heat, WeaponType.Mace,
        10, -2, 20,
        null, null,
        ""
    )

    val vase = ProjectileWeapon(
        "Vase",
        15,
        -10,
        4, null,
        AttackType.Impact, null, WeaponType.Mace,
        6, -2, 20,
        null, null,
        listOf(WeaponAbility.Throwable), null,
        ""
    )

    val woodenPole = Weapon(
        "Wooden Pole",
        20,
        0,
        4, null,
        AttackType.Impact, null, WeaponType.Mace,
        8, -1, 10,
        null, null,
        ""
    )

    val maces = listOf(club, greatHammer, hammer, mace, metalBar, torch, vase, warhammer, woodenPole)
}