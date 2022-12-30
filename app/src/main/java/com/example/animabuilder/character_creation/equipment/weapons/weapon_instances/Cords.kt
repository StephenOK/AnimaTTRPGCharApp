package com.example.animabuilder.character_creation.equipment.weapons.weapon_instances

import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

class Cords: Serializable {
    val chain = Weapon(
        "Chain",
        25,
        0,
        6, null,
        AttackType.Impact, null, WeaponType.Cord,
        13, 2, 15,
        listOf(WeaponAbility.Complex, WeaponAbility.Trapping), 8,
        "A length of metal links."
    )

    val gladNet = ProjectileWeapon(
        "Gladiator's Net",
        5,
        0,
        4, null,
        AttackType.Impact, AttackType.Cut, WeaponType.Cord,
        13, -4, 15,
        100, 5,
        listOf(WeaponAbility.Throwable, WeaponAbility.Trapping, WeaponAbility.Special), 10,
        "This weapon is a narrow net with weighted hooks designed to entangle the " +
                "person at whom it is swung or thrown. Although it is a hand-to-hand weapon, " +
                "its attack is against an area three meters wide, and it can entangle various " +
                "targets. Do not apply either the Strength Bonus of the character or the -40 " +
                "penalty usually applied to maneuvers aimed at capturing the enemy when " +
                "utilizing this weapon."
    )

    val lasso = Weapon(
        "Lasso",
        5,
        10,
        4, null,
        AttackType.Impact, null, WeaponType.Cord,
        9, -4, 20,
        listOf(WeaponAbility.Complex, WeaponAbility.Trapping, WeaponAbility.Special), 9,
        "A lasso is a rope prepared with a running knot for trapping animals or " +
                "people. As a special rule, it does not apply the Strength of the person to " +
                "calculate the damage caused. It requires both hands for use."
    )

    val whip = Weapon(
        "Whip",
        35,
        -20,
        4, null,
        AttackType.Cut, AttackType.Impact, WeaponType.Cord,
        9, -3, 20,
        listOf(WeaponAbility.Complex, WeaponAbility.Trapping), 8,
        "A whip is a cord or chain appropriately made to be used as a weapon. It is " +
                "used with rapid flicks of the wrist and is capable of cutting or trapping an " +
                "opponent."
    )

    val nunchakus = Weapon(
        "Nunchakus",
        30,
        15,
        5, null,
        AttackType.Impact, null, WeaponType.Cord,
        11, 0, 15,
        null, null,
        "These are two short sticks of wood or metal connected by a short chain."
    )

    val cords = listOf(chain, gladNet, lasso, nunchakus, whip)
}