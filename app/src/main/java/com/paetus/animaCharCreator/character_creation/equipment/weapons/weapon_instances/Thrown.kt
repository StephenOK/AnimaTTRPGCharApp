package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.weapons.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponAbility
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of thrown weapons the character may take.
 */
class Thrown: Serializable {
    val bolas = ProjectileWeapon(
        "bolas",
        R.string.bolas,
        30,
        -10,
        5, null,
        AttackType.Impact, null, WeaponType.Throwing,
        6, 2, 15,
        80, 20,
        listOf(WeaponAbility.Trapping, WeaponAbility.Complex, WeaponAbility.Special), 10,
        R.string.bolasDesc
    )

    val chakram = ProjectileWeapon(
        "chakram",
        R.string.chakram,
        40,
        0,
        6, null,
        AttackType.Cut, null, WeaponType.Throwing,
        9, 2, 20,
        80, 30,
        listOf(WeaponAbility.Special), null,
        R.string.chakramDesc
    )

    val darts = ProjectileWeapon(
        "darts",
        R.string.darts,
        20,
        20,
        3, null,
        AttackType.Thrust, null, WeaponType.Throwing,
        3, -4, 15,
        40, 20,
        null, null,
        R.string.dartsDesc
    )

    val spikedBall = ProjectileWeapon(
        "spikedBall",
        R.string.spikeBall,
        20,
        0,
        5, null,
        AttackType.Impact, null, WeaponType.Throwing,
        10, 2, 15,
        50, 20,
        null, null,
        R.string.spikeBallDesc
    )

    val thrown = listOf(bolas, chakram, darts, spikedBall)
}