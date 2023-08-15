package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of mace weapons the character may take.
 */
class Maces: Serializable {
    val club = Weapon(
        "club",
        R.string.club,
        30,
        0,
        5, null,
        AttackType.Impact, null, WeaponType.Mace,
        11, -2, 15,
        null, null,
        R.string.clubDesc
    )

    val greatHammer = Weapon(
        "greatWarhammer",
        R.string.greatWarhammer,
        70,
        -35,
        10, 7,
        AttackType.Impact, null, WeaponType.Mace,
        16, 6, 20,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        R.string.greatWarhammerDesc
    )

    val mace = Weapon(
        "mace",
        R.string.mace,
        40,
        0,
        6, null,
        AttackType.Impact, null, WeaponType.Mace,
        14, 4, 15,
        null, null,
        R.string.maceDesc
    )

    val warhammer = Weapon(
        "warhammer",
        R.string.warhammer,
        50,
        -5,
        6, null,
        AttackType.Impact, null, WeaponType.Mace,
        15, 4, 15,
        null, null,
        R.string.warhammerDesc
    )

    val hammer = Weapon(
        "hammer",
        R.string.hammer,
        30,
        -20,
        4, null,
        AttackType.Impact, null, WeaponType.Mace,
        12, 2, 10,
        null, null,
        R.string.emptyItem
    )

    val metalBar = Weapon(
        "metalBar",
        R.string.metalBar,
        25,
        -5,
        5, null,
        AttackType.Impact, null, WeaponType.Mace,
        12, 2, 15,
        null, null,
        R.string.emptyItem
    )

    val torch = Weapon(
        "torch",
        R.string.torch,
        20,
        -10,
        4, null,
        AttackType.Impact, AttackType.Heat, WeaponType.Mace,
        10, -2, 20,
        null, null,
        R.string.emptyItem
    )

    val vase = ProjectileWeapon(
        "vase",
        R.string.vase,
        15,
        -10,
        4, null,
        AttackType.Impact, null, WeaponType.Mace,
        6, -2, 20,
        null, null,
        listOf(WeaponAbility.Throwable), null,
        R.string.emptyItem
    )

    val woodenPole = Weapon(
        "woodPole",
        R.string.woodenPole,
        20,
        0,
        4, null,
        AttackType.Impact, null, WeaponType.Mace,
        8, -1, 10,
        null, null,
        R.string.emptyItem
    )

    val maces = listOf(club, greatHammer, hammer, mace, metalBar, torch, vase, warhammer, woodenPole)
}