package com.example.animabuilder.character_creation.equipment.weapons.weapon_instances

import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import java.io.Serializable

class ShortArms: Serializable {
    val cestus = Weapon(
        "Cestus",
        25,
        10,
        3, null,
        AttackType.Thrust, AttackType.Cut,
        WeaponType.Short, null,
        11, -2, 15,
        null, null, null,
        null, null,
        "Metal covering for the hands, kneecaps, elbows, or forearms that includes " +
                "knives or spikes used for striking an enemy."
    )

    val dagger = Weapon(
        "Dagger",
        30,
        20,
        3, null,
        AttackType.Thrust, AttackType.Cut,
        WeaponType.Short, null,
        10, -2, 15,
        50, null, 20,
        listOf(WeaponAbility.Throwable, WeaponAbility.Precision), null,
        "A combat knife roughly eight to twelve inches long. It is usually sharpened " +
                "on both edges and balanced for throwing."
    )

    val hook = Weapon(
        "Hook",
        30,
        10,
        3, null,
        AttackType.Thrust, null,
        WeaponType.Short, null,
        11, -2, 15,
        null, null, null,
        null, null,
        "A weapon that is small and curved with a sharp point."
    )

    val parryDagger = Weapon(
        "Parrying Dagger",
        30,
        15,
        3, null,
        AttackType.Thrust, AttackType.Cut,
        WeaponType.Short, null,
        12, 0, 20,
        50, null, 15,
        listOf(WeaponAbility.WeaponTrap, WeaponAbility.Throwable, WeaponAbility.Precision), null,
        "A variation on the traditional dagger designed to block the attacks of enemy " +
                "weapons and trap them with the hilt. At its base are two sharp edges."
    )

    val shortSword = Weapon(
        "Short Sword",
        40,
        15,
        4, null,
        AttackType.Thrust, AttackType.Cut,
        WeaponType.Short, null,
        12, 1, 20,
        null, null, null,
        listOf(WeaponAbility.Precision), null,
        "A straight sharp blade about a foot and a half long. Although it can cut, it " +
                "is used principally as a Thrust weapon. Its reduced size makes it a very " +
                "discrete weapon."
    )

    val stiletto = Weapon(
        "Stiletto",
        25,
        20,
        3, null,
        AttackType.Thrust, null,
        WeaponType.Short, null,
        8, -3, 15,
        30, null, 30,
        listOf(WeaponAbility.Throwable, WeaponAbility.Precision), null,
        "A sharp needle-like knife whose main purpose is for throwing, although it can " +
                "be used in hand-to-hand combat."
    )

    val boomerang = Weapon(
        "Boomerang",
        30,
        10,
        4, null,
        AttackType.Impact, AttackType.Cut,
        WeaponType.Short, null,
        10, 0, 15,
        60, null, 20,
        listOf(WeaponAbility.Throwable, WeaponAbility.Special), null,
        "A curved stick of wood or metal designed to be thrown and to return if it " +
                "doesn't hit anything. To catch it requires a Difficult Sleight of Hand check."
    )

    val claws = Weapon(
        "Claws",
        30,
        15,
        4, null,
        AttackType.Cut, AttackType.Thrust,
        WeaponType.Short, null,
        12, 2, 15,
        null, null, null,
        null, null,
        "Knives on a glove made to resemble animal claws."
    )

    val katar = Weapon(
        "Katar",
        40,
        10,
        8, null,
        AttackType.Thrust, AttackType.Cut,
        WeaponType.Short, null,
        13, 3, 25,
        null, null, null,
        listOf(WeaponAbility.Complex, WeaponAbility.Special), null,
        "A gauntlet equipped with knife blades 10 to 12 inches long. It possesses a " +
                "complex mechanism that allows the blades to be extended and spun. In those " +
                "cases, they can block projectiles like a buckler."
    )

    val raven = Weapon(
        "Raven",
        35,
        10,
        4, null,
        AttackType.Impact, AttackType.Cut,
        WeaponType.Short, null,
        11, 2, 25,
        null, null, null,
        listOf(WeaponAbility.Complex, WeaponAbility.Precision, WeaponAbility.Special), null,
        "A multi-bladed knife in the shape of a star with a hole in the center. The " +
                "thumb is placed in the hole and the knife is spun hard. As it spins, it can " +
                "block missile attacks as though it were a buckler."
    )

    val sai = Weapon(
        "Sai",
        35,
        15,
        4, null,
        AttackType.Thrust, AttackType.Cut,
        WeaponType.Short, null,
        12, 0, 25,
        null, null, null,
        listOf(WeaponAbility.WeaponTrap, WeaponAbility.Precision), null,
        "An unsharpened, pointed, knife-like weapon whose cross guard curves forward to " +
                "resemble a trident. It is used primarily to block an opponent's weapon."
    )

    val shuko = Weapon(
        "Shuko",
        20,
        10,
        4, null,
        AttackType.Thrust, null,
        WeaponType.Short, null,
        9, -2, 25,
        null, null, null,
        listOf(WeaponAbility.Special), null,
        "This is a claw-like device held in the palms of the hands. It is used both as " +
                "a weapon and as a tool for climbing. Shuko adds a +10 to a character's climbing " +
                "ability."
    )

    val shuriken = Weapon(
        "Shuriken",
        25,
        20,
        4, null,
        AttackType.Cut, AttackType.Thrust,
        WeaponType.Short, null,
        10, 1, 20,
        30, null, 20,
        listOf(WeaponAbility.Throwable), null,
        "Small Asian metal weapons used exclusively for throwing. They can be " +
                "various shapes, from simple sharp-edged disks to star-shaped knives."
    )

    val tanto = Weapon(
        "Tanto",
        40,
        20,
        3, null,
        AttackType.Cut, null,
        WeaponType.Short, null,
        9, 1, 40,
        null, null, null,
        listOf(WeaponAbility.Precision), null,
        "Another oriental weapon, it resembles the Katana but is much smaller."
    )

    val tessen = Weapon(
        "Tessen (War Fan)",
        30,
        20,
        4, null,
        AttackType.Cut, AttackType.Impact,
        WeaponType.Short, null,
        8, 0, 25,
        40, null, 20,
        listOf(WeaponAbility.Precision, WeaponAbility.Throwable), null,
        "An especially exotic oriental weapon, its appearance is that of a fan, but " +
                "sharp knives have replaced the wooden slates of the fan. The base of the fan is " +
                "a heavy counterweight that can deliver an Impact attack."
    )

    val tonfa = Weapon(
        "Tonfa",
        30,
        20,
        4, null,
        AttackType.Impact, null,
        WeaponType.Short, null,
        13, 0, 25,
        null, null, null,
        listOf(WeaponAbility.Precision), null,
        "This is a club with a short handle sticking out in the middle used while " +
                "being held along the line of the forearm."
    )

    val brokenBottle = Weapon(
        "Broken Bottle",
        15,
        10,
        3, null,
        AttackType.Cut, AttackType.Impact,
        WeaponType.Short, null,
        5, -3, 15,
        null, null, null,
        null, null,
        ""
    )

    val kitchenKnife = Weapon(
        "Kitchen Knife",
        25,
        10,
        4, null,
        AttackType.Cut, null,
        WeaponType.Short, null,
        9, -1, 10,
        null, null, null,
        null, null,
        ""
    )

    val pick = Weapon(
        "Pick",
        40,
        -20,
        5, null,
        AttackType.Thrust, null,
        WeaponType.Short, null,
        10, 3, 15,
        null, null, null,
        null, null,
        ""
    )

    val sickle = Weapon(
        "Sickle",
        35,
        -10,
        4, null,
        AttackType.Cut, AttackType.Thrust,
        WeaponType.Short, null,
        8, 0, 15,
        null, null, null,
        null, null,
        ""
    )

    val shortArms = listOf(boomerang, brokenBottle, cestus, claws, dagger, hook, katar, kitchenKnife,
        parryDagger, pick, raven, sai, shortSword, shuko, shuriken, sickle, stiletto, tanto, tessen, tonfa)
}