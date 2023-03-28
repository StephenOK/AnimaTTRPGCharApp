package com.example.animabuilder.character_creation.equipment.weapons.weapon_instances

import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of thrown weapons the character may take.
 */
class Thrown: Serializable {
    val bolas = ProjectileWeapon(
        "Bolas",
        30,
        -10,
        5, null,
        AttackType.Impact, null, WeaponType.Throwing,
        6, 2, 15,
        80, 20,
        listOf(WeaponAbility.Trapping, WeaponAbility.Complex, WeaponAbility.Special), 10,
        "A throwing weapon made up of three balls of metal or reinforced leather tied " +
                "together by cords. It is used to capture an opponent. Unlike other weapons used " +
                "for capture, it does not suffer a -40 penalty when trying to trap an opponent."
    )

    val chakram = ProjectileWeapon(
        "Chakram",
        40,
        0,
        6, null,
        AttackType.Cut, null, WeaponType.Throwing,
        9, 2, 20,
        80, 30,
        listOf(WeaponAbility.Special), null,
        "Indigenous weapon that consists of a circular blade, used as a thrown weapon. " +
                "It's known as Turcus in other cultures. It can return after being thrown if it " +
                "doesn't hit anything. To catch it requires beating a Very Difficult Sleight of " +
                "Hand Check."
    )

    val darts = ProjectileWeapon(
        "Darts",
        20,
        20,
        3, null,
        AttackType.Thrust, null, WeaponType.Throwing,
        3, -4, 15,
        40, 20,
        null, null,
        "Small metal-tipped darts designed to be thrown by hand."
    )

    val spikedBall = ProjectileWeapon(
        "Spiked Ball",
        20,
        0,
        5, null,
        AttackType.Impact, null, WeaponType.Throwing,
        10, 2, 15,
        50, 20,
        null, null,
        "Metallic balls equipped with spikes to facilitate their being thrown."
    )

    val thrown = listOf(bolas, chakram, darts, spikedBall)
}