package com.example.animabuilder.character_creation.equipment.weapons.weapon_instances

import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import java.io.Serializable

class TwoHanded: Serializable {
    val twoHandSword = Weapon(
        "Two-Handed Sword",
        90,
        -60,
        10, 8,
        AttackType.Cut, AttackType.Impact, WeaponType.TwoHanded,
        18, 6, 30,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "The Two-handed Sword is the greatest of swords and can be measured more than " +
                "160 centimeters long. Used almost exclusively with two hands, it is an awkward, " +
                "but very deadly weapon."
    )

    val nodachi = Weapon(
        "Nodachi",
        80,
        -35,
        10, 8,
        AttackType.Cut, null, WeaponType.TwoHanded,
        14, 4, 40,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "A larger version of the Katana. It is used in a similar way, but it is much " +
                "longer and thicker."
    )

    val swordBreaker = Weapon(
        "Sword Breaker",
        50,
        -20,
        10, 8,
        AttackType.Impact, AttackType.Cut, WeaponType.TwoHanded,
        16, 8, 25,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "This is a short sword of great weight, containing a blade of almost 25 " +
                "centimeters wide. Due to its enormous Impact potential, it is often used to break " +
                "weapons or break through an enemy's armor."
    )

    val chair = Weapon(
        "Chair",
        25,
        -20,
        5, null,
        AttackType.Impact, null, WeaponType.TwoHanded,
        9, 0, 20,
        listOf(WeaponAbility.TwoHanded), null,
        ""
    )

    val twoHanded = listOf(chair, nodachi, swordBreaker, twoHandSword)
}