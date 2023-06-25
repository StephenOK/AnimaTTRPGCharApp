package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.weapons.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponAbility
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponType
import java.io.Serializable

/**
 * List of shields the character may take.
 */
class Shields: Serializable {
    val buckler = Weapon(
        "buckler",
        R.string.buckler,
        15,
        -15,
        5, null,
        AttackType.Impact, null, WeaponType.Shield,
        14, 0, 20,
        listOf(WeaponAbility.Special), null,
        R.string.bucklerDesc
    )

    val shield = Weapon(
        "shield",
        R.string.shield,
        20,
        -25,
        7, null,
        AttackType.Impact, null, WeaponType.Shield,
        16, 0, 25,
        listOf(WeaponAbility.Special), null,
        R.string.shieldDesc,
    )

    val fullShield = Weapon(
        "fullShield",
        R.string.fullShield,
        25,
        -40,
        10, null,
        AttackType.Impact, null, WeaponType.Shield,
        18, 1, 25,
        listOf(WeaponAbility.Special), null,
        R.string.fullShieldDesc
    )

    val shields = listOf(buckler, shield, fullShield)
}