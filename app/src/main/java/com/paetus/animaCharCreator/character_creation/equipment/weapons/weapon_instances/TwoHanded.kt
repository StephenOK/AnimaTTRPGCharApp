package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.weapons.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponAbility
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponType
import java.io.Serializable

/**
 * List of two-handed weapons the character may take.
 */
class TwoHanded: Serializable {
    val twoHandSword = Weapon(
        "twoHandSword",
        R.string.twoHandSword,
        90,
        -60,
        10, 8,
        AttackType.Cut, AttackType.Impact, WeaponType.TwoHanded,
        18, 6, 30,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        R.string.twoHandSwordDesc
    )

    val nodachi = Weapon(
        "nodachi",
        R.string.nodachi,
        80,
        -35,
        10, 8,
        AttackType.Cut, null, WeaponType.TwoHanded,
        14, 4, 40,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        R.string.nodachiDesc
    )

    val swordBreaker = Weapon(
        "swordBreaker",
        R.string.swordBreaker,
        50,
        -20,
        10, 8,
        AttackType.Impact, AttackType.Cut, WeaponType.TwoHanded,
        16, 8, 25,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        R.string.swordBreakerDesc
    )

    val chair = Weapon(
        "chair",
        R.string.chair,
        25,
        -20,
        5, null,
        AttackType.Impact, null, WeaponType.TwoHanded,
        9, 0, 20,
        listOf(WeaponAbility.TwoHanded), null,
        R.string.emptyItem
    )

    val twoHanded = listOf(chair, nodachi, swordBreaker, twoHandSword)
}