package com.example.animabuilder.character_creation.equipment.weapons.weapon_instances

import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

class Poles: Serializable {
    val cavLance = Weapon(
        "Cavalry Lance",
        80,
        -30,
        8, null,
        AttackType.Thrust, null, WeaponType.Pole,
        12, 7, 25,
        listOf(WeaponAbility.Special), null,
        "A longer and heavier version of the traditional lance, it measures from three " +
                "to four meters long and can only be wielded from horseback. If it is used to " +
                "block an attack, the defender applies a -30 penalty to his ability."
    )

    val harpoon = ProjectileWeapon(
        "Harpoon",
        35,
        -5,
        5, null,
        AttackType.Thrust, null, WeaponType.Pole,
        11, 0, 15,
        100, 20,
        listOf(WeaponAbility.Throwable, WeaponAbility.OneOrTwoHanded), null,
        "This is a short-hafted weapon similar to a Javelin, but with a barbed tip."
    )

    val javelin = ProjectileWeapon(
        "Javelin",
        35,
        5,
        4, null,
        AttackType.Thrust, null, WeaponType.Pole,
        10, -2, 20,
        80,  30,
        listOf(WeaponAbility.Throwable), null,
        "A short spear used almost exclusively for throwing."
    )

    val lance = ProjectileWeapon(
        "Lance",
        40,
        5,
        6, 4,
        AttackType.Thrust, null, WeaponType.Pole,
        13, 2, 25,
        80, 30,
        listOf(WeaponAbility.Throwable, WeaponAbility.OneOrTwoHanded), null,
        "The lance is the quintessential pole weapon. It consists of a long shaft of " +
                "wood or metal ending in a fine two-edged point. It is very suitable for use " +
                "from horseback, or for keeping enemies at a distance. It can measure from 150 " +
                "to 220 centimeters long. Although it can be used with just one hand, fighting " +
                "in that fashion brings on a -10 penalty to the attack, unless being used to " +
                "resist a charge."
    )

    val quarterstaff = Weapon(
        "Quarterstaff",
        30,
        10,
        4, null,
        AttackType.Impact, null, WeaponType.Pole,
        11, 0, 30,
        listOf(WeaponAbility.TwoHanded), null,
        "This weapon is a pole of wood or metal that may be as long as two meters. " +
                "Although it can be used with just one hand, fighting that way causes a -10 " +
                "penalty to a character's Attack ability."
    )

    val trident = ProjectileWeapon(
        "Trident",
        40,
        -10,
        7, 6,
        AttackType.Thrust, null, WeaponType.Pole,
        12, 3, 15,
        100,  15,
        listOf(WeaponAbility.Throwable, WeaponAbility.OneOrTwoHanded), null,
        "A spear or lance with a three part tip resembling a fork. Its design does " +
                "allow it to be thrown. It is slightly larger than the trident used for fishing."
    )

    val haruNoOkina = Weapon(
        "Haru No Okina",
        35,
        15,
        4, null,
        AttackType.Cut, AttackType.Thrust, WeaponType.Pole,
        12, 2, 25,
        listOf(WeaponAbility.Complex, WeaponAbility.TwoHanded, WeaponAbility.Special), null,
        "A weapon of oriental origin, it consists of two long poles connected by chains " +
                "to a third, shorter section. Each of the longer poles ends in a blade like that " +
                "of the halberd, but smaller. It is used placing the middle, shortest section " +
                "against the back, while the longer poles are maneuvered with each hand. To lend " +
                "greater power to the strikes, the weapon is twirled to use centrifugal force. " +
                "Although both hands are used, the Strength bonus is not doubled. The " +
                "Three-section Glaive allows a second attack per turn, as though a second weapon " +
                "were being used, but applying a penalty of only -10 to a character's Attack ability."
    )

    val poles = listOf(cavLance, harpoon, haruNoOkina, javelin, lance, quarterstaff, trident)
}