package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.weapons.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponAbility
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of cord weapons the character may take.
 */
class Cords: Serializable {
    val chain = Weapon(
        "chain",
        R.string.chain,
        25,
        0,
        6, null,
        AttackType.Impact, null, WeaponType.Cord,
        13, 2, 15,
        listOf(WeaponAbility.Complex, WeaponAbility.Trapping), 8,
        R.string.chainDesc
    )

    val gladNet = ProjectileWeapon(
        "gladNet",
        R.string.gladNet,
        5,
        0,
        4, null,
        AttackType.Impact, AttackType.Cut, WeaponType.Cord,
        13, -4, 15,
        100, 5,
        listOf(WeaponAbility.Throwable, WeaponAbility.Trapping, WeaponAbility.Special), 10,
        R.string.gladNetDesc
    )

    val lasso = Weapon(
        "lasso",
        R.string.lasso,
        5,
        10,
        4, null,
        AttackType.Impact, null, WeaponType.Cord,
        9, -4, 20,
        listOf(WeaponAbility.Complex, WeaponAbility.Trapping, WeaponAbility.Special), 9,
        R.string.lassoDesc
    )

    val whip = Weapon(
        "whip",
        R.string.whip,
        35,
        -20,
        4, null,
        AttackType.Cut, AttackType.Impact, WeaponType.Cord,
        9, -3, 20,
        listOf(WeaponAbility.Complex, WeaponAbility.Trapping), 8,
        R.string.whipDesc
    )

    val nunchakus = Weapon(
        "nunchakus",
        R.string.nunchakus,
        30,
        15,
        5, null,
        AttackType.Impact, null, WeaponType.Cord,
        11, 0, 15,
        null, null,
        R.string.nunchakusDesc
    )

    val cords = listOf(chain, gladNet, lasso, nunchakus, whip)
}