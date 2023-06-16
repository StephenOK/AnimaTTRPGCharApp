package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

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
        "Broadsword",
        55,
        -5,
        5, null,
        AttackType.Cut, null, WeaponType.Sword,
        15, 3, 25,
        null, null,
        "A straight-bladed weapon slightly shorter than the Long sword. It is " +
                "characterized by its broad blade and great fortitude."
    )

    val longSword = Weapon(
        "Long Sword",
        50,
        0,
        6, null,
        AttackType.Cut, null, WeaponType.Sword,
        13, 3, 25,
        null, null,
        "A cutting blade with a sharp point. It is generally 90 to 110 centimeters long."
    )

    val rapier = Weapon(
        "Rapier",
        40,
        15,
        4, null,
        AttackType.Thrust, AttackType.Cut, WeaponType.Sword,
        11, 2, 20,
        listOf(WeaponAbility.Precision), null,
        "A fine and stylized two-edged sword."
    )

    val saber = Weapon(
        "Saber",
        45,
        10,
        6, null,
        AttackType.Cut, AttackType.Thrust, WeaponType.Sword,
        12, 3, 20,
        null, null,
        "A light curved blade that is similar and less durable than the Long Sword, " +
                "but much more maneuverable."
    )

    val scimitar = Weapon(
        "Scimitar",
        50,
        -5,
        5, null,
        AttackType.Cut, null, WeaponType.Sword,
        13, 4, 20,
        null, null,
        "A large curved sword generally shorter than the Long Sword, but with a broader blade."
    )

    val katana = Weapon(
        "Katana",
        50,
        0,
        6, 5,
        AttackType.Cut, null, WeaponType.Sword,
        11, 3, 40,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "A sword of Asian design, it has a curved blade sharpened on only one edge. " +
                "Similar to a saber, it is considerably heavier and more effective, but not as " +
                "resistant to breaking."
    )

    val twoBladeKatana = Weapon(
        "Two-Bladed Katana",
        55,
        -5,
        8, null,
        AttackType.Cut, null, WeaponType.Sword,
        11, 3, 40,
        listOf(WeaponAbility.Special), null,
        "This is a staff with a Katana on each end. It is held and maneuvered holding " +
                "the long central shaft. Although both hands must be used, the Strength bonus " +
                "is not doubled. Because of the way it is wielded, it allows a second attack " +
                "per turn, as though a second weapon were being used. However, it applies a " +
                "penalty of only -10 to a character's Attack ability when being used in that way."
    )

    val swords = listOf(broadsword, katana, longSword, rapier, saber, scimitar, twoBladeKatana)
}