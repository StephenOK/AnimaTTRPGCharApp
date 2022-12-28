package com.example.animabuilder.character_creation.equipment.weapons.weapon_instances

import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import java.io.Serializable

class Axes: Serializable {
    val battleAxe = Weapon(
        "Battle Axe",
        70,
        -30,
        7, null,
        AttackType.Cut, AttackType.Impact,
        WeaponType.Axe, null,
        15, 5, 25,
        100, null, 5,
        listOf(WeaponAbility.Throwable), null,
        "A more manageable version of the Two-handed Axe, a battle axe can be wielded " +
                "with just one hand."
    )

    val handAxe = Weapon(
        "Hand Axe",
        45,
        0,
        5, null,
        AttackType.Cut, null,
        WeaponType.Axe, null,
        13, 4, 15,
        80, null, 10,
        listOf(WeaponAbility.Throwable), null,
        "These are light axes used with a single hand. They usually have a " +
                "counterweight that facilitates their use as thrown weapons. They measure from " +
                "one to two feet in length."
    )

    val hoe = Weapon(
        "Hoe",
        30,
        -20,
        4, null,
        AttackType.Cut, AttackType.Impact,
        WeaponType.Axe, null,
        10, 1, 15,
        null, null, null,
        null, null,
        ""
    )

    val woodAxe = Weapon(
        "Woodsman's Axe",
        40,
        -10,
        7, 5,
        AttackType.Cut, null,
        WeaponType.Axe, null,
        12, 3, 15,
        null, null, null,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        ""
    )

    val axes = listOf(battleAxe, handAxe, hoe, woodAxe)
}