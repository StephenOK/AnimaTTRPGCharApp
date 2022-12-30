package com.example.animabuilder.character_creation.equipment.weapons.weapon_instances

import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.MixedWeapon
import java.io.Serializable

class Mixed: Serializable {
    val bastardSword = MixedWeapon(
        "Bastard Sword",
        70,
        -30,
        9, 7,
        AttackType.Cut, AttackType.Impact, listOf(WeaponType.Sword, WeaponType.TwoHanded),
        15, 5, 25,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "This is a sword halfway between a Long Sword and the Two-handed Sword, " +
                "measuring about 130 centimeters long. The weapon's long grip and counterweight " +
                "allow it to be used with either one or two hands."
    )

    val flail = MixedWeapon(
        "Flail",
        40,
        0,
        6, null,
        AttackType.Impact, null, listOf(WeaponType.Mace, WeaponType.Cord),
        13, 4, 15,
        listOf(WeaponAbility.Complex), null,
        "This is a shaft of wood or metal with a chain that ends in a spiked metal " +
                "ball. A version exists with several smaller chains."
    )

    val foil = MixedWeapon(
        "Foil",
        35,
        15,
        3, null,
        AttackType.Thrust, null, listOf(WeaponType.Sword, WeaponType.Short),
        9, -2, 20,
        listOf(WeaponAbility.Precision), null,
        "A sword that is more slender and flexible than the Rapier. It is used as a " +
                "Thrust weapon."
    )

    val halberd = MixedWeapon(
        "Halberd",
        60,
        -15,
        11, 6,
        AttackType.Cut, AttackType.Impact, listOf(WeaponType.Pole, WeaponType.TwoHanded),
        15, 4, 20,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "The halberd is a weapon made of a pole of wood or metal that ends in an " +
                "axe-like edged blade. It measures a total of between 150 and 200 centimeters in length."
    )

    val heavyBattleMace = MixedWeapon(
        "Heavy Battle-Mace",
        60,
        -15,
        10, 6,
        AttackType.Impact, null, listOf(WeaponType.Mace, WeaponType.TwoHanded),
        16, 5, 15,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "A heavier version of the Mace with a shaft almost one meter long. It is topped " +
                "with a weight of enormous proportions. Due to its size, it is usually used with " +
                "both hands."
    )

    val largeMultiFlail = MixedWeapon(
        "Large Multi-Headed Flail",
        80,
        -50,
        10, 8,
        AttackType.Impact, null, listOf(WeaponType.Mace, WeaponType.TwoHanded),
        14, 6, 20,
        listOf(WeaponAbility.Complex), null,
        "A Flail of enormous dimensions. It has various chains coming from the end of " +
                "its shaft, each ending in a spiked metal ball."
    )

    val scythe = MixedWeapon(
        "Scythe",
        35,
        0,
        9, 5,
        AttackType.Cut, AttackType.Impact, listOf(WeaponType.Pole, WeaponType.TwoHanded),
        12, 2, 25,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "This weapon consists of a long shaft ending in a long curved blade. It also " +
                "has a handle allowing it to be used with two hands."
    )

    val twoHandAxe = MixedWeapon(
        "Two-Handed Axe",
        100,
        -70,
        11, 9,
        AttackType.Cut, AttackType.Impact, listOf(WeaponType.Axe, WeaponType.TwoHanded),
        17, 7, 30,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "An enormous axe with a counterweight at the base of the handle. Depending " +
                "on the design, it can be single- or double-bladed. Its size makes it almost " +
                "imperative to wield it with both hands. It can measure from 100 to 170 centimeters " +
                "long."
    )

    val kusariGama = MixedWeapon(
        "Kusari-Gama",
        40,
        5,
        5, null,
        AttackType.Cut, AttackType.Impact, listOf(WeaponType.Short, WeaponType.Cord),
        12, 4, 25,
        listOf(WeaponAbility.TwoHanded, WeaponAbility.Trapping, WeaponAbility.Special), 8,
        "This is a sickle of Asian design that has a chain attached to the bottom " +
                "used to trap opponents. Although both hands are used, the Strength bonus is " +
                "not doubled. It can be used for conventional attacks, or by whipping the chain " +
                "to try to trap an opponent, in which case it causes a Base Damage of only 10."
    )

    val mixed = listOf(bastardSword, flail, foil, halberd, heavyBattleMace, kusariGama,
        largeMultiFlail, scythe, twoHandAxe)
}