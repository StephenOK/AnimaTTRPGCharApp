package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.weapons.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponAbility
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of short weapons the character may take.
 */
class ShortArms: Serializable {
    val cestus = Weapon(
        "cestus",
        R.string.cestus,
        25,
        10,
        3, null,
        AttackType.Thrust, AttackType.Cut, WeaponType.Short,
        11, -2, 15,
        null, null,
        R.string.cestusDesc
    )

    val dagger = ProjectileWeapon(
        "dagger",
        R.string.dagger,
        30,
        20,
        3, null,
        AttackType.Thrust, AttackType.Cut, WeaponType.Short,
        10, -2, 15,
        50,  20,
        listOf(WeaponAbility.Throwable, WeaponAbility.Precision), null,
        R.string.daggerDesc
    )

    val hook = Weapon(
        "hook",
        R.string.hook,
        30,
        10,
        3, null,
        AttackType.Thrust, null, WeaponType.Short,
        11, -2, 15,
        null, null,
        R.string.hookDesc
    )

    val parryDagger = ProjectileWeapon(
        "parryDagger",
        R.string.parryDagger,
        30,
        15,
        3, null,
        AttackType.Thrust, AttackType.Cut, WeaponType.Short,
        12, 0, 20,
        50, 15,
        listOf(WeaponAbility.WeaponTrap, WeaponAbility.Throwable, WeaponAbility.Precision), null,
        R.string.parryDaggerDesc
    )

    val shortSword = Weapon(
        "shortSword",
        R.string.shortSword,
        40,
        15,
        4, null,
        AttackType.Thrust, AttackType.Cut, WeaponType.Short,
        12, 1, 20,
        listOf(WeaponAbility.Precision), null,
        R.string.shortSwordDesc
    )

    val stiletto = ProjectileWeapon(
        "stiletto",
        R.string.stiletto,
        25,
        20,
        3, null,
        AttackType.Thrust, null, WeaponType.Short,
        8, -3, 15,
        30, 30,
        listOf(WeaponAbility.Throwable, WeaponAbility.Precision), null,
        R.string.stilettoDesc
    )

    val boomerang = ProjectileWeapon(
        "boomerang",
        R.string.boomerang,
        30,
        10,
        4, null,
        AttackType.Impact, AttackType.Cut, WeaponType.Short,
        10, 0, 15,
        60, 20,
        listOf(WeaponAbility.Throwable, WeaponAbility.Special), null,
        R.string.boomerangDesc
    )

    val claws = Weapon(
        "claws",
        R.string.claws,
        30,
        15,
        4, null,
        AttackType.Cut, AttackType.Thrust, WeaponType.Short,
        12, 2, 15,
        null, null,
        R.string.clawsDesc
    )

    val katar = Weapon(
        "katar",
        R.string.katar,
        40,
        10,
        8, null,
        AttackType.Thrust, AttackType.Cut, WeaponType.Short,
        13, 3, 25,
        listOf(WeaponAbility.Complex, WeaponAbility.Special), null,
        R.string.katarDesc
    )

    val raven = Weapon(
        "raven",
        R.string.raven,
        35,
        10,
        4, null,
        AttackType.Impact, AttackType.Cut, WeaponType.Short,
        11, 2, 25,
        listOf(WeaponAbility.Complex, WeaponAbility.Precision, WeaponAbility.Special), null,
        R.string.ravenDesc
    )

    val sai = Weapon(
        "sai",
        R.string.sai,
        35,
        15,
        4, null,
        AttackType.Thrust, AttackType.Cut, WeaponType.Short,
        12, 0, 25,
        listOf(WeaponAbility.WeaponTrap, WeaponAbility.Precision), null,
        R.string.saiDesc
    )

    val shuko = Weapon(
        "shuko",
        R.string.shuko,
        20,
        10,
        4, null,
        AttackType.Thrust, null, WeaponType.Short,
        9, -2, 25,
        listOf(WeaponAbility.Special), null,
        R.string.shukoDesc
    )

    val shuriken = ProjectileWeapon(
        "shuriken",
        R.string.shuriken,
        25,
        20,
        4, null,
        AttackType.Cut, AttackType.Thrust, WeaponType.Short,
        10, 1, 20,
        30, 20,
        listOf(WeaponAbility.Throwable), null,
        R.string.shurikenDesc
    )

    val tanto = Weapon(
        "tanto",
        R.string.tanto,
        40,
        20,
        3, null,
        AttackType.Cut, null, WeaponType.Short,
        9, 1, 40,
        listOf(WeaponAbility.Precision), null,
        R.string.tantoDesc
    )

    val tessen = ProjectileWeapon(
        "tessen",
        R.string.tessen,
        30,
        20,
        4, null,
        AttackType.Cut, AttackType.Impact, WeaponType.Short,
        8, 0, 25,
        40, 20,
        listOf(WeaponAbility.Precision, WeaponAbility.Throwable), null,
        R.string.tessenDesc
    )

    val tonfa = Weapon(
        "tonfa",
        R.string.tonfa,
        30,
        20,
        4, null,
        AttackType.Impact, null, WeaponType.Short,
        13, 0, 25,
        listOf(WeaponAbility.Precision), null,
        R.string.tonfaDesc
    )

    val brokenBottle = Weapon(
        "brokenBottle",
        R.string.brokenBottle,
        15,
        10,
        3, null,
        AttackType.Cut, AttackType.Impact, WeaponType.Short,
        5, -3, 15,
        null, null,
        R.string.emptyItem
    )

    val kitchenKnife = Weapon(
        "kitchenKnife",
        R.string.kitchenKnife,
        25,
        10,
        4, null,
        AttackType.Cut, null, WeaponType.Short,
        9, -1, 10,
        null, null,
        R.string.emptyItem
    )

    val pick = Weapon(
        "pick",
        R.string.pick,
        40,
        -20,
        5, null,
        AttackType.Thrust, null, WeaponType.Short,
        10, 3, 15,
        null, null,
        R.string.emptyItem
    )

    val sickle = Weapon(
        "sickle",
        R.string.sickle,
        35,
        -10,
        4, null,
        AttackType.Cut, AttackType.Thrust, WeaponType.Short,
        8, 0, 15,
        null, null,
        R.string.emptyItem
    )

    val shortArms = listOf(boomerang, brokenBottle, cestus, claws, dagger, hook, katar, kitchenKnife,
        parryDagger, pick, raven, sai, shortSword, shuko, shuriken, sickle, stiletto, tanto, tessen, tonfa)
}