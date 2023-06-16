package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.character_creation.equipment.weapons.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponAbility
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of axe weapons the character may take.
 */
class Axes: Serializable {
    val battleAxe = ProjectileWeapon(
        "Battle Axe",
        70,
        -30,
        7, null,
        AttackType.Cut, AttackType.Impact, WeaponType.Axe,
        15, 5, 25,
        100, 5,
        listOf(WeaponAbility.Throwable), null,
        "A more manageable version of the Two-handed Axe, a Battle Axe can be wielded " +
                "with just one hand."
    )

    val handAxe = ProjectileWeapon(
        "Hand Axe",
        45,
        0,
        5, null,
        AttackType.Cut, null, WeaponType.Axe,
        13, 4, 15,
        80, 10,
        listOf(WeaponAbility.Throwable), null,
        "These are light axes used with a single hand. They usually have a " +
                "counterweight that facilitates their use as thrown weapons. They measure from " +
                "30 to 60 centimeters in length."
    )

    val hoe = Weapon(
        "Hoe",
        30,
        -20,
        4, null,
        AttackType.Cut, AttackType.Impact, WeaponType.Axe,
        10, 1, 15,
        null, null,
        ""
    )

    val woodAxe = Weapon(
        "Woodsman's Axe",
        40,
        -10,
        7, 5,
        AttackType.Cut, null, WeaponType.Axe,
        12, 3, 15,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        ""
    )

    val axes = listOf(battleAxe, handAxe, hoe, woodAxe)
}