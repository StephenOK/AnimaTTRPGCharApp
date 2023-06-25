package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.weapons.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponAbility
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.MixedWeapon
import java.io.Serializable

/**
 * List of mixed class weapons the character may take.
 */
class Mixed: Serializable {
    val bastardSword = MixedWeapon(
        "bastardSword",
        R.string.bastardSword,
        70,
        -30,
        9, 7,
        AttackType.Cut, AttackType.Impact, listOf(WeaponType.Sword, WeaponType.TwoHanded),
        15, 5, 25,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        R.string.bastardSwordDesc
    )

    val flail = MixedWeapon(
        "flail",
        R.string.flail,
        40,
        0,
        6, null,
        AttackType.Impact, null, listOf(WeaponType.Mace, WeaponType.Cord),
        13, 4, 15,
        listOf(WeaponAbility.Complex), null,
        R.string.flailDesc
    )

    val foil = MixedWeapon(
        "foil",
        R.string.foil,
        35,
        15,
        3, null,
        AttackType.Thrust, null, listOf(WeaponType.Sword, WeaponType.Short),
        9, -2, 20,
        listOf(WeaponAbility.Precision), null,
        R.string.foilDesc
    )

    val halberd = MixedWeapon(
        "halberd",
        R.string.halberd,
        60,
        -15,
        11, 6,
        AttackType.Cut, AttackType.Impact, listOf(WeaponType.Pole, WeaponType.TwoHanded),
        15, 4, 20,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        R.string.halberdDesc
    )

    val heavyBattleMace = MixedWeapon(
        "heavyBattleMace",
        R.string.heavyBattleMace,
        60,
        -15,
        10, 6,
        AttackType.Impact, null, listOf(WeaponType.Mace, WeaponType.TwoHanded),
        16, 5, 15,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        R.string.heavyBattleMaceDesc
    )

    val largeMultiFlail = MixedWeapon(
        "largeMultiFlail",
        R.string.largeMultiFlail,
        80,
        -50,
        10, 8,
        AttackType.Impact, null, listOf(WeaponType.Mace, WeaponType.TwoHanded),
        14, 6, 20,
        listOf(WeaponAbility.Complex), null,
        R.string.largeMultiFlailDesc
    )

    val scythe = MixedWeapon(
        "scythe",
        R.string.scythe,
        35,
        0,
        9, 5,
        AttackType.Cut, AttackType.Impact, listOf(WeaponType.Pole, WeaponType.TwoHanded),
        12, 2, 25,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        R.string.scytheDesc
    )

    val twoHandAxe = MixedWeapon(
        "twoHandAxe",
        R.string.twoHandAxe,
        100,
        -70,
        11, 9,
        AttackType.Cut, AttackType.Impact, listOf(WeaponType.Axe, WeaponType.TwoHanded),
        17, 7, 30,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        R.string.twoHandAxeDesc
    )

    val kusariGama = MixedWeapon(
        "kusariGama",
        R.string.kusari,
        40,
        5,
        5, null,
        AttackType.Cut, AttackType.Impact, listOf(WeaponType.Short, WeaponType.Cord),
        12, 4, 25,
        listOf(WeaponAbility.TwoHanded, WeaponAbility.Trapping, WeaponAbility.Special), 8,
        R.string.kusariDesc
    )

    val mixed = listOf(bastardSword, flail, foil, halberd, heavyBattleMace, kusariGama,
        largeMultiFlail, scythe, twoHandAxe)

    val shortAdditions = listOf(foil, kusariGama)
    val axeAdditions = listOf(twoHandAxe)
    val maceAdditions = listOf(flail, heavyBattleMace, largeMultiFlail)
    val swordAdditions = listOf(bastardSword, foil)
    val twoHandedAdditions = listOf(bastardSword, halberd, heavyBattleMace, largeMultiFlail,
        scythe, twoHandAxe)
    val poleAdditions = listOf(halberd, scythe)
    val cordAdditions = listOf(flail, kusariGama)
}