package com.example.animabuilder.character_creation.equipment.weapons

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.class_objects.ClassName
import java.io.BufferedReader
import java.io.Serializable

class WeaponProficiencies(): Serializable {
    val bastardSword = Weapon(
        "Bastard Sword",
        70,
        -30,
        9, 7,
        AttackType.Cut, AttackType.Impact,
        WeaponType.Mixed, listOf(WeaponType.Sword, WeaponType.TwoHanded),
        15, 5, 25,
        null, null, null,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "This is a sword halfway between a Long Sword and the Two-handed Sword, " +
                "measuring about five feet long. The weapon's long grip and counterweight " +
                "allow it to be used with either one or two hands."
    )

    val battleAxe = Weapon(
        "Battle Axe",
        70,
        -30,
        7, null,
        AttackType.Cut, AttackType.Impact,
        WeaponType.Axe, null,
        15, 5, 25,
        100, null, 5,
        listOf(WeaponAbility.Throwable), null,
        "A more manageable version of the Two-handed Axe, a battle axe can be wielded " +
                "with just one hand."
    )

    val broadsword = Weapon(
        "Broadsword",
        55,
        -5,
        5, null,
        AttackType.Cut, null,
        WeaponType.Sword, null,
        15, 3, 25,
        null, null, null,
        null, null,
        "A straight-bladed weapon slightly shorter than the Long sword. It is " +
                "characterized by its broad blade and great fortitude."
    )

    val cavLance = Weapon(
        "Cavalry Lance",
        80,
        -30,
        8, null,
        AttackType.Thrust, null,
        WeaponType.Pole, null,
        12, 7, 25,
        null, null, null,
        listOf(WeaponAbility.Special), null,
        "A longer and heavier version of the traditional lance, it measures from nine " +
                "to twelve feet long and can only be wielded from horseback. If it is used to " +
                "block an attack, the defender applies a -30 penalty to his ability."
    )

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

    val chain = Weapon(
        "Chain",
        25,
        0,
        6, null,
        AttackType.Impact, null,
        WeaponType.Cord, null,
        13, 2, 15,
        null, null, null,
        listOf(WeaponAbility.Complex, WeaponAbility.Trapping), 8,
        "A length of metal links."
    )

    val club = Weapon(
        "Club",
        30,
        0,
        5, null,
        AttackType.Impact, null,
        WeaponType.Mace, null,
        11, -2, 15,
        null, null, null,
        null, null,
        "Made of wood or just stone, the Club is the quintessential Impact weapon."
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

    val flail = Weapon(
        "Flail",
        40,
        0,
        6, null,
        AttackType.Impact, null,
        WeaponType.Mixed, listOf(WeaponType.Mace, WeaponType.Cord),
        13, 4, 15,
        null, null, null,
        listOf(WeaponAbility.Complex), null,
        "This is a shaft of wood or metal with a chain that ends in a spiked metal " +
                "ball. A version exists with several smaller chains."
    )

    val foil = Weapon(
        "Foil",
        35,
        15,
        3, null,
        AttackType.Thrust, null,
        WeaponType.Mixed, listOf(WeaponType.Sword, WeaponType.Short),
        9, -2, 20,
        null, null, null,
        listOf(WeaponAbility.Precision), null,
        "A sword that is more slender and flexible than the Rapier. It is used as a " +
                "Thrust weapon."
    )

    val gladNet = Weapon(
        "Gladiator's Net",
        5,
        0,
        4, null,
        AttackType.Impact, AttackType.Cut,
        WeaponType.Cord, null,
        13, -4, 15,
        100, null, 5,
        listOf(WeaponAbility.Throwable, WeaponAbility.Trapping, WeaponAbility.Special), 10,
        "This weapon is a narrow net with weighted hooks designed to entangle the " +
                "person at whom it is swung or thrown. Although it is a hand-to-hand weapon, " +
                "its attack is against an area nine feet wide, and it can entangle various " +
                "targets. Do not apply either the Strength Bonus of the character or the -40 " +
                "penalty usually applied to maneuvers aimed at capturing the enemy when " +
                "utilizing this weapon."
    )

    val greatHammer = Weapon(
        "Great Warhammer",
        70,
        -35,
        10, 7,
        AttackType.Impact, null,
        WeaponType.Mace, null,
        16, 6, 20,
        null, null, null,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "A very large two-handed Impact weapon. It consists of a straight shaft " +
                "crowned with an enormous metal hammer. Some have sharp pointed tips on the " +
                "side so that they can be used for a second type of attack, a penetrating, or " +
                "Thrust, attack."
    )

    val halberd = Weapon(
        "Halberd",
        60,
        -15,
        11, 6,
        AttackType.Cut, AttackType.Impact,
        WeaponType.Mixed, listOf(WeaponType.Pole, WeaponType.TwoHanded),
        15, 4, 20,
        null, null, null,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "The halberd is a weapon made of a pole of wood or metal that ends in an " +
                "axe-like edged blade. It measures a total of between five feet and six and " +
                "a half feet in length."
    )

    val handAxe = Weapon(
        "Hand Axe",
        45,
        0,
        5, null,
        AttackType.Cut, null,
        WeaponType.Axe, null,
        13, 4, 15,
        80, null, 10,
        listOf(WeaponAbility.Throwable), null,
        "These are light axes used with a single hand. They usually have a " +
                "counterweight that facilitates their use as thrown weapons. They measure from " +
                "one to two feet in length."
    )

    val harpoon = Weapon(
        "Harpoon",
        35,
        -5,
        5, null,
        AttackType.Thrust, null,
        WeaponType.Pole, null,
        11, 0, 15,
        100, null, 20,
        listOf(WeaponAbility.Throwable, WeaponAbility.OneOrTwoHanded), null,
        "This is a short-hafted weapon similar to a javelin, but with a barbed tip."
    )

    val heavyBattleMace = Weapon(
        "Heavy Battle-Mace",
        60,
        -15,
        10, 6,
        AttackType.Impact, null,
        WeaponType.Mixed, listOf(WeaponType.Mace, WeaponType.TwoHanded),
        16, 5, 15,
        null, null, null,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "A heavier version of the Mace with a shaft almost 3 feet long. It is topped " +
                "with a weight of enormous proportions. Due to its size, it is usually used with " +
                "both hands."
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

    val javelin = Weapon(
        "Javelin",
        35,
        5,
        4, null,
        AttackType.Thrust, null,
        WeaponType.Pole, null,
        10, -2, 20,
        80, null, 30,
        listOf(WeaponAbility.Throwable), null,
        "A short spear used almost exclusively for throwing."
    )

    val lance = Weapon(
        "Lance",
        40,
        5,
        6, 4,
        AttackType.Thrust, null,
        WeaponType.Pole, null,
        13, 2, 25,
        80, null, 30,
        listOf(WeaponAbility.Throwable, WeaponAbility.OneOrTwoHanded), null,
        "The lance is the quintessential pole weapon. It consists of a long shaft of " +
                "wood or metal ending in a fine two-edged point. It is very suitable for use " +
                "from horseback, or for keeping enemies at a distance. It can measure from five " +
                "to over seven feet long. Although it can be used with just one hand, fighting " +
                "in that fashion brings on a -10 penalty to the attack, unless being used to " +
                "resist a charge."
    )

    val largeMultiFlail = Weapon(
        "Large Multi-Headed Flail",
        80,
        -50,
        10, 8,
        AttackType.Impact, null,
        WeaponType.Mixed, listOf(WeaponType.Mace, WeaponType.TwoHanded),
        14, 6, 20,
        null, null, null,
        listOf(WeaponAbility.Complex), null,
        "A Flail of enormous dimensions. It has various chains coming from the end of " +
                "its shaft, each ending in a spiked metal ball."
    )

    val lasso = Weapon(
        "Lasso",
        5,
        10,
        4, null,
        AttackType.Impact, null,
        WeaponType.Cord, null,
        9, -4, 20,
        null, null, null,
        listOf(WeaponAbility.Complex, WeaponAbility.Trapping, WeaponAbility.Special), 9,
        "A lasso is a rope prepared with a running knot for trapping animals or " +
                "people. As a special rule, it does not apply the Strength of the person to " +
                "calculate the damage caused. It requires both hands for use."
    )

    val longSword = Weapon(
        "Long Sword",
        50,
        0,
        6, null,
        AttackType.Cut, null,
        WeaponType.Sword, null,
        13, 3, 25,
        null, null, null,
        null, null,
        "A cutting blade with a sharp point. It is generally three to three and a half " +
                "feet long."
    )

    val mace = Weapon(
        "Mace",
        40,
        0,
        6, null,
        AttackType.Impact, null,
        WeaponType.Mace, null,
        14, 4, 15,
        null, null, null,
        null, null,
        "This weapon consists of a wood or metal shaft about 18 inches long topped " +
                "with a heavy round or spherical head."
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

    val quarterstaff = Weapon(
        "Quarterstaff",
        30,
        10,
        4, null,
        AttackType.Impact, null,
        WeaponType.Pole, null,
        11, 0, 30,
        null, null, null,
        listOf(WeaponAbility.TwoHanded), null,
        "This weapon is a pole of wood or metal that may be as long as three feet. " +
                "Although it can be used with just one hand, fighting that way causes a -10 " +
                "penalty to a character's Attack ability."
    )

    val rapier = Weapon(
        "Rapier",
        40,
        15,
        4, null,
        AttackType.Thrust, AttackType.Cut,
        WeaponType.Sword, null,
        11, 2, 20,
        null, null, null,
        listOf(WeaponAbility.Precision), null,
        "A fine and stylized two-edged sword."
    )

    val saber = Weapon(
        "Saber",
        45,
        10,
        6, null,
        AttackType.Cut, AttackType.Thrust,
        WeaponType.Sword, null,
        12, 3, 20,
        null, null, null,
        null, null,
        "A light curved blade that is similar and less durable than the Long Sword, " +
                "but much more maneuverable."
    )

    val scimitar = Weapon(
        "Scimitar",
        50,
        -5,
        5, null,
        AttackType.Cut, null,
        WeaponType.Sword, null,
        13, 4, 20,
        null, null, null,
        null, null,
        "A large curved sword generally shorter than the Long Sword, but with a broader blade."
    )

    val scythe = Weapon(
        "Scythe",
        35,
        0,
        9, 5,
        AttackType.Cut, AttackType.Impact,
        WeaponType.Mixed, listOf(WeaponType.Pole, WeaponType.TwoHanded),
        12, 2, 25,
        null, null, null,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "This weapon consists of a long shaft ending in a long curved blade. It also " +
                "has a handle allowing it to be used with two hands."
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

    val trident = Weapon(
        "Weapon",
        40,
        -10,
        7, 6,
        AttackType.Thrust, null,
        WeaponType.Pole, null,
        12, 3, 15,
        100, null, 15,
        listOf(WeaponAbility.Throwable, WeaponAbility.OneOrTwoHanded), null,
        "A spear or lance with a three part tip resembling a fork. Its design does " +
                "allow it to be thrown. It is slightly larger than the trident used for fishing."
    )

    val twoHandAxe = Weapon(
        "Two-Handed Axe",
        100,
        -70,
        11, 9,
        AttackType.Cut, AttackType.Impact,
        WeaponType.Mixed, listOf(WeaponType.Axe, WeaponType.TwoHanded),
        17, 7, 30,
        null, null, null,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "An enormous axe with a counterweight at the base of the handle. Depending " +
                "on the design, it can be single- or double-bladed. Its size makes it almost " +
                "imperative to wield it with both hands. It can measure from 5 feet to over 7 " +
                "feet long."
    )

    val twoHandSword = Weapon(
        "Two-Handed Sword",
        90,
        -60,
        10, 8,
        AttackType.Cut, AttackType.Impact,
        WeaponType.TwoHanded, null,
        18, 6, 30,
        null, null, null,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "The Two-handed Sword is the greatest of swords and can be measured more than " +
                "5 feet long. Used almost exclusively with two hands, it is an awkward, but very " +
                "deadly weapon."
    )

    val unarmed = Weapon(
        "Unarmed Combat",
        10,
        20,
        0, null,
        AttackType.Impact, null,
        WeaponType.Unarmed, null,
        null, null, null,
        null, null, null,
        listOf(WeaponAbility.Precision), null,
        "This is not a weapon, of course. Rather, these are the numbers used for a " +
                "character fighting without weapons. The attacks made are made by punching, " +
                "kicking, head-butting, and biting. Fighting unarmed requires the use of the " +
                "whole body, so a character fighting this way cannot apply the rules for attacks " +
                "with other weapons"
    )

    val warhammer = Weapon(
        "Warhammer",
        50,
        -5,
        6, null,
        AttackType.Impact, null,
        WeaponType.Mace, null,
        15, 4, 15,
        null, null, null,
        null, null,
        "A crushing weapon consisting of a shaft topped by a great steel hammerhead."
    )

    val whip = Weapon(
        "Whip",
        35,
        -20,
        4, null,
        AttackType.Cut, AttackType.Impact,
        WeaponType.Cord, null,
        9, -3, 20,
        null, null, null,
        listOf(WeaponAbility.Complex, WeaponAbility.Trapping), 8,
        "A whip is a cord or chain appropriately made to be used as a weapon. It is " +
                "used with rapid flicks of the wrist and is capable of cutting or trapping and " +
                "opponent."
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

    val haruNoOkina = Weapon(
        "Haru No Okina",
        35,
        15,
        4, null,
        AttackType.Cut, AttackType.Thrust,
        WeaponType.Pole, null,
        12, 2, 25,
        null, null, null,
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

    val katana = Weapon(
        "Katana",
        50,
        0,
        6, 5,
        AttackType.Cut, null,
        WeaponType.Sword, null,
        11, 3, 40,
        null, null, null,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "A sword of Asian design, it has a curved blade sharpened on only one edge. " +
                "Similar to a saber, it is considerably heavier and more effective, but not as " +
                "resistant to breaking."
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

    val kusariGama = Weapon(
        "Kusari-Gama",
        40,
        5,
        5, null,
        AttackType.Cut, AttackType.Impact,
        WeaponType.Mixed, listOf(WeaponType.Short, WeaponType.Cord),
        12, 4, 25,
        null, null, null,
        listOf(WeaponAbility.TwoHanded, WeaponAbility.Trapping, WeaponAbility.Special), 8,
        "This is a sickle of Asian design that has a chain attached to the bottom " +
                "used to trap opponents. Although both hands are used, the Strength bonus is " +
                "not doubled. It can be used for conventional attacks, or by whipping the chain " +
                "to try to trap an opponent, in which case it causes a Base Damage of only 10."
    )

    val nodachi = Weapon(
        "Nodachi",
        80,
        -35,
        10, 8,
        AttackType.Cut, null,
        WeaponType.TwoHanded, null,
        14, 4, 40,
        null, null, null,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "A larger version of the Katana. It is used in a similar way, but it is much " +
                "longer and thicker."
    )

    val nunchakus = Weapon(
        "Nunchakus",
        30,
        15,
        5, null,
        AttackType.Impact, null,
        WeaponType.Cord, null,
        11, 0, 15,
        null, null, null,
        null, null,
        "These are two short sticks of wood or metal connected by a short chain."
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
        "An unsharpened, pointed, knife-like weapon whose cross guard curves forward to resemble a trident. It is used primarily to block an opponent's weapon."
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

    val swordBreaker = Weapon(
        "Sword Breaker",
        50,
        -20,
        10, 8,
        AttackType.Impact, AttackType.Cut,
        WeaponType.TwoHanded, null,
        16, 8, 25,
        null, null, null,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        "This is a short sword of great weight, containing a blade of almost 10 " +
                "inches wide. Due to its enormous Impact potential, it is often used to break " +
                "weapons or break through an enemy's armor."
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

    val twoBladeKatana = Weapon(
        "Two-Bladed Katana",
        55,
        -5,
        8, null,
        AttackType.Cut, null,
        WeaponType.Sword, null,
        11, 3, 40,
        null, null, null,
        listOf(WeaponAbility.Special), null,
        "This is a staff with a Katana on each end. It is held and maneuvered holding " +
                "the long central shaft. Although both hands must be used, the Strength bonus " +
                "is not doubled. Because of the way it is wielded, it allows a second attack " +
                "per turn, as though a second weapon were being used. However, it applies a " +
                "penalty of only -10 to a character's Attack ability when being used in that way."
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

    val chair = Weapon(
        "Chair",
        25,
        -20,
        5, null,
        AttackType.Impact, null,
        WeaponType.TwoHanded, null,
        9, 0, 20,
        null, null, null,
        listOf(WeaponAbility.TwoHanded), null,
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

    val hammer = Weapon(
        "Hammer",
        30,
        -20,
        4, null,
        AttackType.Impact, null,
        WeaponType.Mace, null,
        12, 2, 10,
        null, null, null,
        null, null,
        ""
    )

    val hoe = Weapon(
        "Hoe",
        30,
        -20,
        4, null,
        AttackType.Cut, AttackType.Impact,
        WeaponType.Axe, null,
        10, 1, 15,
        null, null, null,
        null, null,
        ""
    )

    val metalBar = Weapon(
        "Metal Bar",
        25,
        -5,
        5, null,
        AttackType.Impact, null,
        WeaponType.Mace, null,
        12, 2, 15,
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

    val torch = Weapon(
        "Torch",
        20,
        -10,
        4, null,
        AttackType.Impact, AttackType.Heat,
        WeaponType.Mace, null,
        10, -2, 20,
        null, null, null,
        null, null,
        ""
    )

    val vase = Weapon(
        "Vase",
        15,
        -10,
        4, null,
        AttackType.Impact, null,
        WeaponType.Mace, null,
        6, -2, 20,
        null, null, null,
        listOf(WeaponAbility.Throwable), null,
        ""
    )

    val woodenPole = Weapon(
        "Wooden Pole",
        20,
        0,
        4, null,
        AttackType.Impact, null,
        WeaponType.Mace, null,
        8, -1, 10,
        null, null, null,
        null, null,
        ""
    )

    val woodAxe = Weapon(
        "Woodsman's Axe",
        40,
        -10,
        7, 5,
        AttackType.Cut, null,
        WeaponType.Axe, null,
        12, 3, 15,
        null, null, null,
        listOf(WeaponAbility.OneOrTwoHanded), null,
        ""
    )

    val buckler = Weapon(
        "Buckler",
        15,
        -15,
        5, null,
        AttackType.Impact, null,
        WeaponType.Shield, null,
        14, 0, 20,
        null, null, null,
        listOf(WeaponAbility.Special), null,
        "This is a very small shield no more than a foot across. The greatest " +
                "advantage of the buckler is that it can be fastened directly onto the forearm, " +
                "allowing both hands to remain free. The buckler grants its user +10 to their " +
                "Blocking ability and +5 to their Dodging ability."
    )

    val shield = Weapon(
        "Shield",
        20,
        -25,
        7, null,
        AttackType.Impact, null,
        WeaponType.Shield, null,
        16, 0, 25,
        null, null, null,
        listOf(WeaponAbility.Special), null,
        "A metal or reinforced wood surface with handles on the back so it can be held. " +
                "It is used mostly as a means of defense. The shield grants its user +20 to their " +
                "Blocking ability and +10 to their Dodging ability."
    )

    val fullShield = Weapon(
        "Full Shield",
        25,
        -40,
        10, null,
        AttackType.Impact, null,
        WeaponType.Shield, null,
        18, 1, 25,
        null, null, null,
        listOf(WeaponAbility.Special), null,
        "A large heavy shield often as tall as a man. Generally used by infantry " +
                "soldiers, it has either a square or pointed base allowing it to be stuck into " +
                "the ground by its own weight. The full shield grants its user +30 to their " +
                "Blocking ability and +15 to their Dodging ability."
    )

    val arquebus = Weapon(
        "Arquebus",
        null,
        -20,
        6, null,
        null, null,
        WeaponType.Projectile, null,
        9, -3, 20,
        null, 4, 80,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.Complex, WeaponAbility.Special), 11,
        "The arquebus is a firearm made from a long metal tube through which a ball of " +
                "lead shot is propelled by an explosion of gunpowder. The arquebus is basically a " +
                "cannon small enough to be carried and fired by a lone man. Like crossbows, they " +
                "do not depend on the Strength of the user and, therefore, do not use any Strength " +
                "bonus the character may have. Instead, the Arquebus has its own Strength, an 11, " +
                "for which a +20 is added to the base damage of the shot fired. If a fumble is " +
                "rolled with a level of less than 80 with the arquebus, the weapon fails to fire. " +
                "If the fumble is higher than 80, the arquebus bursts, which ruins the weapon. For " +
                "each +5 to the weapon's quality, a point is added to the Strength with which it " +
                "fires, and also adds 15 to the level of Fumble required to make it burst."
    )

    val bolas = Weapon(
        "Bolas",
        30,
        -10,
        5, null,
        AttackType.Impact, null,
        WeaponType.Throwing, null,
        6, 2, 15,
        80, null, 20,
        listOf(WeaponAbility.Trapping, WeaponAbility.Complex, WeaponAbility.Special), 10,
        "A throwing weapon made up of three balls of metal or reinforced leather tied " +
                "together by cords. It is used to capture an opponent. Unlike other weapons used " +
                "for capture, it does not suffer a -40 penalty when trying to trap an opponent."
    )

    val blowgun = Weapon(
        "Blowgun",
        null,
        -10,
        4, null,
        null, null,
        WeaponType.Projectile, null,
        3, -3, 15,
        null, 1, 50,
        null, null,
        "This is a hollow tube of wood or metal from one to three feet long. It is " +
                "used to shoot small darts, which are usually poisoned. As a special rule, the " +
                "blowgun does not apply the Strength of the person to calculate the damage " +
                "caused. It requires both hands for use."
    )

    val chakram = Weapon(
        "Chakram",
        40,
        0,
        6, null,
        AttackType.Cut, null,
        WeaponType.Throwing, null,
        9, 2, 20,
        80, null, 30,
        listOf(WeaponAbility.Special), null,
        "Indigenous weapon that consists of a circular blade, used as a thrown weapon. " +
                "It's known as Turcus in other cultures. It can return after being thrown if it " +
                "doesn't hit anything. To catch it requires beating a Very Difficult Sleight of " +
                "Hand check."
    )

    val compositeBow = Weapon(
        "Composite Bow",
        null,
        -30,
        7, null,
        null, null,
        WeaponType.Projectile, null,
        8, -2, 25,
        null, 1, 90,
        listOf(WeaponAbility.TwoHanded), null,
        "This is the largest and most powerful type of bow. It is made of three pieces " +
                "and measures more than 8 feet. It requires two hands to use, but its Strength " +
                "bonus is not doubled."
    )

    val crossbow = Weapon(
        "Crossbow",
        null,
        0,
        8, 4,
        null, null,
        WeaponType.Projectile, null,
        8, -2, 20,
        null, 2, 60,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.OneOrTwoHanded, WeaponAbility.Special), 8,
        "A bow mounted on a stock with a groove from which crossbow bolts are fired. It " +
                "is cocked using a small winch. Crossbows do not depend on the Strength of the " +
                "user and, therefore, do not use any Strength bonus a character may have. " +
                "Instead, they have their own Strength score (8) for which a +10 is added to " +
                "the Base Damage of the quarrels fired. Each +5 to the weapon's quality, aside " +
                "from improving its accuracy, adds a point to the Strength with which it fires. " +
                "It requires both hands for use."
    )

    val darts = Weapon(
        "Darts",
        20,
        20,
        3, null,
        AttackType.Thrust, null,
        WeaponType.Throwing, null,
        3, -4, 15,
        40, null, 20,
        null, null,
        "Small metal-tipped darts designed to be thrown by hand."
    )

    val repeatingCrossbow = Weapon(
        "Repeating Crossbow",
        null,
        0,
        8, 5,
        null, null,
        WeaponType.Projectile, null,
        6, -2, 20,
        null, 3, 60,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.OneOrTwoHanded, WeaponAbility.Special), 8,
        "In reality, this is not a different class of weapon from the normal crossbow, " +
                "so it can be used without any need to develop a separate expertise. It is simply " +
                "equipped with a system of gears that enable it to fire a larger number of " +
                "quarrels without needing to reload. Usually it has a magazine containing from " +
                "between 4 to 8 quarrels. It requires both hands to use."
    )

    val heavyCrossbow = Weapon(
        "Heavy Crossbow",
        null,
        -20,
        10, 7,
        null, null,
        WeaponType.Projectile, null,
        8, -1, 20,
        null, 2, 80,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.OneOrTwoHanded, WeaponAbility.Special), 10,
        "A large and heavy crossbow. It has a Strength of 10 and so it possesses a " +
                "bonus of +15 to the Base Damage of its quarrels. It requires both hands for use."
    )

    val miniCrossbow = Weapon(
        "Miniature Crossbow",
        null,
        10,
        3, null,
        null, null,
        WeaponType.Projectile, null,
        5, -4, 15,
        null, 2, 30,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.Special), 5,
        "A crossbow of very small size which may be fired with just one hand. It has " +
                "a Strength of 5, and so no bonus is added to the Base Damage. In game terms, " +
                "it works the same way as a normal crossbow, and so it is not necessary to learn " +
                "its use separately."
    )

    val shortBow = Weapon(
        "Short Bow",
        null,
        -10,
        4, null,
        null, null,
        WeaponType.Projectile, null,
        7, -3, 15,
        null, 1, 40,
        listOf(WeaponAbility.TwoHanded), null,
        "This weapon consists of a taut cord attached to either end of a singular " +
                "curved piece of flexible wood and is less than about four feet tall. It " +
                "requires two hands to use, but its Strength bonus is not doubled."
    )

    val longBow = Weapon(
        "Longbow",
        null,
        -30,
        7, null,
        null, null,
        WeaponType.Projectile, null,
        8, -2, 20,
        null, 1, 60,
        listOf(WeaponAbility.TwoHanded), null,
        "Like the Short Bow, but with a size of between four-and-a-half and six feet " +
                "tall. It requires two hands to use, but its Strength Bonus is not doubled."
    )

    val matchlock = Weapon(
        "Matchlock Pistol",
        null,
        0,
        4, null,
        null, null,
        WeaponType.Projectile, null,
        8, -3, 20,
        null, 4, 50,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.Complex, WeaponAbility.Special), 9,
        "A smaller and more complex version of the arquebus that can be fired with one" +
                " hand. Like its larger counterpart, it has its own Strength score (9), for which" +
                " a bonus of +10 is added to the Base Damage of the shot it fires. If a Fumble" +
                " is rolled with a level of less than 80, the weapon misfires. If the Fumble is" +
                " 80 or higher, it bursts, which ruins the weapon. For each +5 to the weapon's" +
                " quality, a point is added to the Strength with which it fires, and also adds" +
                " 15 to the level of Fumble required to make it burst."
    )

    val sling = Weapon(
        "Sling",
        null,
        -40,
        4, null,
        null, null,
        WeaponType.Projectile, null,
        3, -6, 10,
        null, 1, 50,
        null, null,
        "This is a small leather pouch tied to a cord. It is used with a spinning motion " +
                "to throw stones."
    )

    val spikedBall = Weapon(
        "Spiked Ball",
        20,
        0,
        5, null,
        AttackType.Impact, null,
        WeaponType.Throwing, null,
        10, 2, 15,
        50, null, 20,
        null, null,
        "Metallic balls equipped with spikes to facilitate their being thrown."
    )

    val lightBallista = Weapon(
        "Light Ballista",
        null,
        -80,
        null, null,
        null, null,
        WeaponType.Projectile, null,
        18, null, 25,
        null, 10, 150,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.Special), 12,
        "The ballista is a crossbow of enormous proportions that is used as a siege " +
                "weapon. It is moved on wheels because its large size makes it impossible to " +
                "carry. Light ballistae are manned by three persons, two who move it laterally, " +
                "and a third who aims and fires. Ballistae do not depend on the Strength of the " +
                "user and, therefore, do not use the Strength Bonuses of their crews. Instead the " +
                "weapon has its own Strength score (12), for which a +20 is added to the Base " +
                "Damage of the quarrels fired. Each +5 to the weapon's quality, aside from " +
                "improving its accuracy, adds a point to the Strength with which it fires. As a " +
                "special rule, the tremendous penetrating power of the quarrel fired by the " +
                "ballista allows it to make a type of area attack in a straight line 10 feet " +
                "long from its initial point of impact."
    )

    val heavyBallista = Weapon(
        "Heavy Ballista",
        null,
        -100,
        null, null,
        null, null,
        WeaponType.Projectile, null,
        20, null, 30,
        null, 12, 200,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.Special), 13,
        "A larger version of the Light Ballista that requires five persons to manage. " +
                "It has a Strength of 13, and so adds a +25 to the Base Damage from its quarrels. " +
                "Each +5 to the weapon's quality, aside from improving its accuracy, adds a point " +
                "to the Strength with which it fires. Its area attack extends for 15 feet in a " +
                "straight line from its initial point of impact."
    )

    val cannon = Weapon(
        "Cannon",
        null,
        -100,
        null, null,
        null, null,
        WeaponType.Projectile, null,
        24, null, 30,
        null, 12, 250,
        listOf(WeaponAbility.OwnStrength, WeaponAbility.Special), 13,
        "A firearm made of a large metal tube from which a large metal shell is " +
                "propelled by gunpowder. The shell is made to explode, creating a cloud of " +
                "shrapnel with a radius of 15-30 feet. Although the shell itself attacks in " +
                "a conventional way, the explosion does not. The accuracy is checked to see " +
                "if the shell hits where intended (using Table 45). After that, if a victim " +
                "attempts to Dodge or Block, he must pass a Dodge or Athleticism check of " +
                "Absurd difficulty if he is at greater than half the radius of the explosion, " +
                "or against an Almost Impossible level difficulty otherwise. Cannons do not " +
                "depend on the Strength of the user, instead they have their own Strength " +
                "score (13), for which reason a +25 is added to the Base Damage of the shells " +
                "fired. If a Fumble less than 70 is rolled with a cannon, the weapon fails to " +
                "fire. If the Fumble is higher than 70, the cannon bursts, which ruins the " +
                "weapon. For each +5 to the weapon's quality, a point is added to the Strength " +
                "with which its shells fire. It also adds 15 to the level of Fumble required to " +
                "make it burst."
    )

    val kempo = MartialArt(
        "Kempo",
        "This is a freewheeling style of combat that uses combinations of " +
                "strikes. The style uses rapid multiple attacks to try and find gaps in " +
                "an opponent's defenses. The rapid flurry of blows allows a Kempo master " +
                "to carry out additional attacks with a penalty of -10 to his ability " +
                "instead of the usual -25. It has a Base Damage of 20, plus the " +
                "character's Strength bonus. Kempo uses the Blunt Table.",
        "None",
        10
    )

    val capoeira = MartialArt(
        "Capoeira",
        "Capoeira is a system of combat that appears chaotic and employs " +
                "broad acrobatic movements of the legs that resemble a dance. The " +
                "movements of Capoeira are so sweeping that when the user makes ana Area " +
                "Attack, he is considered to be using a large weapon, and he can affect " +
                "up to five opponents. Capoeira has a Base Damage of 20, plus the " +
                "character's Strength bonus. It uses the Blunt Table. Practitioners of Capoeira " +
                "also gain a class bonus of +10 to their Dodge ability.",
        "Dance 40",
        10
    )

    val taiChi = MartialArt(
        "Tai Chi",
        "More than just a martial art, Tai Chi is a philosophy. It employs " +
                "every part of the body using movements that are so fluid and elegant " +
                "that it does not even seem to be a real way of fighting. Tai Chi " +
                "possesses a Base Damage of 20, plus double the Power bonus of the " +
                "character, representing the use of the character's internal energy. " +
                "Given that this energy is used only as a force multiplier, Tai Chi " +
                "attacks occur on the Blunt Table, and not the Energy Table.",
        "Use of Ki",
        30
    )

    val shotokan = MartialArt(
        "Shotokan",
        "Shotokan is a martial art that employs forceful strikes with either the fists " +
                "or the legs. The style consists of taking advantage of the weak points of an " +
                "opponent's defenses to defeat him with a single attack. It is an offensive " +
                "martial art capable of inflicting enormousdamage. Shotokan has a Base Damage " +
                "of 30, plus the character's Strength bonus. It uses the Blunt Table.",
        "None",
        10
    )

    val sambo = MartialArt(
        "Sambo",
        "The name Sambo comes from Samooborona Biez Orousia, which means \"unarmed " +
                "personal defense.\" It is a very precise and defensive combat style, developed " +
                "for the training of certain military organizations. Practitioners of Sambo " +
                "reduce the penalties for the following combat maneuvers in half: Trapping, " +
                "Area Attack, Take-Down, and Disarm. Sambo has a base damage of 20, plus the " +
                "character's Strength bonus. It uses the Blunt Table. Practitioners of Sambo also " +
                "gain a bonus of +10 to their Block ability when unarmed.",
        "None",
        10
    )

    val kungFu = MartialArt(
        "Kung Fu",
        "Kung Fu is a broad style developed by many great oriental masters of martial " +
                "arts. It takes its inspiration from various animals and imitates their " +
                "movements while adapting them to human combat. Because its techniques are highly " +
                "varied, it is an art capable of adapting itself to situations as they arise. " +
                "Thus, its practitioners can modify their style with great ease. Every combat " +
                "turn, a master of Kung Fu cana choose a bonus of +10 to his Attack, Block, " +
                "Dodge, Damage, or Initiative, as he finds convenient. He should declare the " +
                "ability to which his bonus is dedicated before Initiative is calculated for " +
                "the Combat Turn. If he uses it to improve his Attack, Block, or Dodge ability, " +
                "it is not considered an innate class bonus, and therefore can increase it by " +
                "more than +50. Kung Fu has a Base Damage of 20 plus the character's Strength " +
                "bonus, and attacks occur on the Blunt Table.",
        "Acrobatics 40, Sleight of Hand 40, Style 20",
        10
    )

    val taekwondo = MartialArt(
        "Taekwondo",
        "Taekwondo is a system of combat that is ideal for combining with the use of " +
                "weapons. It is a martial art that is based primarily on effective attacks with " +
                "the legs that are directed with power and expertise. Taekwondo possesses a Base " +
                "Damage of 20, plus the character's Strength bonus. It allows the character to " +
                "make an additional attack with his legs after all his other attacks have been " +
                "made. This extra attack suffers a penalty of only -20 and acts just as an " +
                "additional weapon would. It can be used even after making an attack with a " +
                "weapon. Taekwondo uses the Blunt Table.",
        "None",
        10
    )

    val aikido = MartialArt(
        "Aikido",
        "Aikido is a martial art that enables a practitioner to defend himself against " +
                "attacks using the enemy's own strength against him. Its devotees easily trip or " +
                "break their attacker's arms or legs using minimal movement. According to the " +
                "philosophy behind this art, the adversary's own violence is the only thing that " +
                "will defeat them. The damage caused by Aikido is 10, plus the Strength Bonus of " +
                "the Aikido artist. However, when making a counter attack, twice the opponent's " +
                "Strength Bonus is also added (a minimum bonus of +5). The attack occurs on the " +
                "Blunt Table, and it allows one's opponent to easily be controlled, so that there " +
                "are no penalties to the Trapping maneuver during a counterattack. Practitioners " +
                "of Aikido also receive a +10 bonus to either their Block or Dodge ability while unarmed.",
        "Sleight of Hand 40",
        10
    )

    val muayThai = MartialArt(
        "Muay Thai",
        "Muay Thai maximizes utilization of the strength of those who practice it. The " +
                "fighter utilizes the harder parts of his body, like his elbows and knees. Its " +
                "masters seek out the opponent's weakest points, such as the joints or ribs. " +
                "Muay Thai has a Base Damage of 20, plus triple the Strength bonus of the user. " +
                "It uses the Blunt Table.",
        "Feats of Strength 40",
        10
    )

    val grappling = MartialArt(
        "Grappling",
        "Grappling is an art that consists of holding and trapping one's opponent. " +
                "Training in grappling includes falls, punches, kicks, strangleholds, and throws. " +
                "The essence of the style is to close the distance between the fighter and his " +
                "opponent, take him down, and finish him off on the ground. Grappling permits a " +
                "character to use the Trapping and Take-down maneuvers without any penalty. It " +
                "has a Base Damage of 20, plus the character's Strength bonus. It utilizes the " +
                "Blunt Table.",
        "Feats of Strength 40",
        10
    )

    val melkaiah = MartialArt(
        "Melkaiah",
        "This is a strong system of fighting that maximizes the strength and ability of " +
                "its practitioners to inhuman levels. The style is based on holds, throws, and " +
                "take-downs of incredible effectiveness. It is said that as long as a " +
                "practitioner of Melkaiah keeps his feet on the ground, he cannot be defeated " +
                "by conventional means. This martial art awards a bonus of +3 to Strength or " +
                "Dexterity Checks when performing Take-Down or Trapping maneuvers. Practitioners " +
                "of Melkaiah also gain a +10 bonus to their Attack ability while unarmed.",
        "Grappling or Sambo, Inhumanity, more than 160 in both Attack and Defense (Unarmed)",
        10
    )

    val seraphite = MartialArt(
        "Seraphite",
        "According to tradition, Seraphite is a style that was developed for the purpose " +
                "of hunting demons. It is an art that uses unusually perilous movements that put " +
                "the practitioner at risk, but that also multiply the effectiveness of the " +
                "attack. In current times, this art is known by certain sectors of the Church, " +
                "especially members of the Inquisition. A character who employs Seraphite adds a " +
                "+10 bonus to the Final Damage of the Basic Martial Art style being used. If " +
                "desired, he can also temporarily add +20 to his Attack ability in exchange for " +
                "a -30 to his Defense ability. This must be declared before calculation of Initiative.",
        "Shotokan or Kempo, Precision Extrusion, more than 180 Attack (Unarmed)",
        10
    )

    val dumah = MartialArt(
        "Dumah",
        "Dumah is known as \"the art of the wind,\" a name it received because it " +
                "teaches its practitioners to use their hands and legs as though they were " +
                "cutting or thrusting weapons. This is a tribal practice usually transmitted " +
                "from parents to children within a family. A character who employs Dumah adds " +
                "a +10 bonus to the Final Damage of the Basic Martial Art style being used, " +
                "and he can also choose to use the Thrust or Cut Tables when attacking. The " +
                "forcefulness of these cuts or thrusts is such that it reduces the opponent's " +
                "Armor Type by two points. It also adds +10 to the Breakage of its attacks. " +
                "Practitioners of Dumah also gain a +20 bonus to their Attack ability while unarmed",
        "Kempo or Capoeira, Presence Extrusion",
        10
    )

    val emp = MartialArt(
        "Emp",
        "Emp is a refined technique of fighting that prepares its users to fight " +
                "against armed enemies. Using rapid and dizzying spiral movements, a master of " +
                "Emp is capable of advancing on and rendering an opponent helpless in a couple " +
                "of short moves. This style permits its user to perform the Disarm maneuver with " +
                "no penalty to his ability, and it adds a bonus of +3 to his Characteristic in " +
                "Contested Checks. Practitioners of Emp also gain a +20 bonus to their Attack " +
                "ability while unarmed and a +10 to their Initiative when using martial arts.",
        "Kempo or Taekwondo, Mastery of Attack (Unarmed)",
        10
    )

    val enuth = MartialArt(
        "Enuth",
        "Known as \"the art of dreams,\" Enuth was created during the period of war " +
                "between the Sylvain and the Duk\'zarist. One faction of the elves, followers of " +
                "the philosophy of C\'iel, which forbids killing, created Enuth in order to " +
                "battle the Duk\'zarist without having to take their lives. Due to the incredible " +
                "resistance of the Duk\'zarist, the style reached a level of perfection of " +
                "incredible extremes in order to equal the fighting power of their antagonists. " +
                "Enuth permits the application of a +20 bonus to the die roll to calculate the " +
                "Critical Level when the character strikes intending to knock his opponent " +
                "unconscious. It can also permit the character to voluntarily reduce the amount " +
                "of damage inflicted by his blow - even after the dice have been thrown for both " +
                "antagonists. Practitioners of Enuth also gain a +20 bonus to their Block and " +
                "Dodge abilities while unarmed.",
        "Sambo or Shotokan, more than 160 in both Attack and Defense (Unarmed)",
        10
    )

    val shephon = MartialArt(
        "Shephon",
        "Shephon is probably the most perfect system of defense that exists. It is " +
                "inspired by the flow of water, and with its free-flowing movements, a master " +
                "of this style is capable of avoiding almost any attack by changing its " +
                "trajectory. When a character declares that he has entered into Total Defense " +
                "mode, it increases the bonus for that maneuver to +60. A practitioner of Shephon " +
                "also gains a +20 to their Block and Dodge abilities while unarmed.",
        "Aikido and Kung Fu, Ki Control, Mastery of Defense (Unarmed)",
        10
    )

    val asakusen = MartialArt(
        "Asakusen",
        "Although the word Asakusen is used today to designate any fighting style used " +
                "to kill, it is actually one of the most complex martial arts in the world. It " +
                "has been prohibited for centuries due to its lethal nature. With the passage " +
                "of time, it was diluted into various styles created by schools of Kung Fu that " +
                "are no more than pale reflections of true Asakusen. Asakusen makes the variable " +
                "+10 bonus of Kung Fu apply to Dodge, Attack, Block, Initiative, and Damage all " +
                "at the same time - as long as martial arts are being used. However, the " +
                "character still adds another +10 to any one of those abilities he chooses in " +
                "the same way as is done for Kung Fu.",
        "Kung Fu, more than 16- in both Attack and Defense (Unarmed)",
        10
    )

    val velez = MartialArt(
        "Velez",
        "To learn Velez it is necessary for the practitioner to control his internal " +
                "energy and know how to channel it. This style concentrates all the spiritual " +
                "power of the martial artist when he strikes, permitting him to create a flow " +
                "of power capable of penetrating even physical matter. It is without doubt one " +
                "of the rarest and most spectacular martial arts in the world - although the " +
                "few who know its secrets are reluctant to share them. Velez permits the " +
                "character to strike using the Energy Table. Nonetheless, attacks made with this " +
                "style can be blocked normally, since they are not intangible. Practitioners of " +
                "Velez also gain a +20 bonus to either their Block or Dodge ability while unarmed.",
        "Tai Chi or Kung Fu, Presence Extrusion",
        20
    )

    val selene = MartialArt(
        "Selene",
        "According to mythology, Selene was the first martial art to be recognized as " +
                "such. It was practiced exclusively by women, and, traditionally, no man was " +
                "permitted to discover its secrets. Selene turns the attacker's own force " +
                "against him, tossing him around like a rag doll. In spite of its great " +
                "complexity, the movements of Selene are so subtle that it often seems the " +
                "artist hasn't even moved. Due to his ability to use his Defense ability, a " +
                "master of Selene doubles his bonus for counterattacking if he uses his Response " +
                "Action to attack his opponent with this martial art. Practitioners of Selene " +
                "also gain a +20 bonus to Block and Dodge when unarmed.",
        "Aikido, Mastery of Block or Dodge (Unarmed)",
        10
    )

    val hakyoukuken = MartialArt(
        "Hakyoukuken",
        "Many consider Hakyoukuken to be the most perfect martial art that has ever " +
                "existed. In fact, its origin isn't even human, though its true source is a " +
                "mystery. The practitioner of Hakyoukuken controls the tension of every muscle " +
                "in his body and makes his attacks with devastating power, literally destroying " +
                "his adversaries from the inside. This style also teaches one to get in the " +
                "first strike: If there is no attacker, there is no need for defense. Hakyoukuken " +
                "adds a bonus of +20 to the Final Damage of whatever martial art is being used. " +
                "Most armor offers no protection its attacks and so subtracts a -2 from the AT " +
                "if they are soft. As it destroys an enemy's internal organs, add a +20 to the " +
                "die roll calculating the Critical Level due to attacks made using this martial " +
                "art. This last advantage only applies to organic beings. Practitioners of " +
                "Hakyoukukun also gain a +20 bonus to Initiative when using martial arts and " +
                "+10 to Attack when unarmed.",
        "Shotokan or Muay Thai, Use of Necessary Energy, Mastery in Attack (Unarmed)",
        10
    )

    var primaryWeapon = unarmed
    var individualModules: List<Weapon> = listOf()
    var fullModWeapons: List<Weapon> = listOf()

    var shortArms = listOf(boomerang, brokenBottle, cestus, claws, dagger, hook, katar, kitchenKnife,
        parryDagger, pick, raven, sai, shortSword, shuko, shuriken, sickle, stiletto, tanto, tessen, tonfa)

    var axes = listOf(battleAxe, handAxe, hoe, woodAxe)

    var maces = listOf(club, greatHammer, hammer, mace, metalBar, torch, vase, warhammer, woodenPole)

    var swords = listOf(broadsword, katana, longSword, rapier, saber, scimitar, twoBladeKatana)

    var twoHanded = listOf(chair, nodachi, swordBreaker, twoHandSword)

    var poles = listOf(cavLance, harpoon, haruNoOkina, javelin, lance, quarterstaff, trident)

    var cords = listOf(chain, gladNet, lasso, nunchakus, whip)

    val mixed = listOf(bastardSword, flail, foil, halberd, heavyBattleMace, kusariGama,
        largeMultiFlail, scythe, twoHandAxe)

    var shields = listOf(buckler, shield, fullShield)

    var projectiles = listOf(arquebus, blowgun, compositeBow, crossbow, darts, repeatingCrossbow,
        heavyCrossbow, miniCrossbow, shortBow, longBow, matchlock, sling, lightBallista, heavyBallista, cannon)

    var thrown = listOf(bolas, chakram, spikedBall)

    val allWeapons = shortArms + axes + maces + swords + twoHanded + poles + cords + mixed +
            shields + projectiles + thrown

    fun findWeapon(weaponName: String): Weapon{
        allWeapons.forEach{
            if(it.name == weaponName)
                return it
        }

        return unarmed
    }

    var improvised = listOf(brokenBottle, chair, kitchenKnife, hammer, hoe, metalBar, pick,
        sickle, torch, vase, woodenPole, woodAxe)
    var barbarianWeapons = listOf(twoHandAxe, battleAxe, twoHandSword, bastardSword, heavyBattleMace)
    var ninjaWeapons = listOf(katana, tanto, claws, shuriken, kusariGama)
    var duelWeapons = listOf(rapier, foil, parryDagger, dagger, saber, longSword)
    var pirateWeapons = listOf(harpoon, gladNet, hook, saber, handAxe)
    var nomadWeapons = listOf(dagger, chakram, longBow, scimitar, lance)
    var huntWeapons = listOf(javelin, shortBow, shortSword, lance, bolas)
    var knightWeapons = listOf(longSword, cavLance, mace, bastardSword, shield)
    var gladiatorWeapons = listOf(shortSword, gladNet, buckler, trident, whip)
    var assassinWeapons = listOf(shortSword, miniCrossbow, club, blowgun, stiletto)
    var soldierWeapons = listOf(crossbow, longSword, halberd, lance, shield)
    var indigenousWeapons = listOf(javelin, lance, fullShield, shortBow, blowgun)
    var banditWeapons = listOf(dagger, crossbow, shortSword, mace, club)

    var takenModules: List<List<Weapon>> = mutableListOf()

    fun updateModulesTaken(weaponCheck: List<Weapon>, isAdded: Boolean): List<Weapon>{
        fullModWeapons = listOf()

        takenModules =
            if(isAdded)
                takenModules + listOf(weaponCheck)
            else
                takenModules - listOf(weaponCheck).toSet()

        takenModules.forEach{list ->
            list.forEach{
                fullModWeapons = fullModWeapons + it
            }
        }
        individualModules = individualModules - fullModWeapons.toSet()

        return fullModWeapons
    }

    var styleMods: List<String> = listOf()

    val allMartialArts = listOf(kempo, capoeira, taiChi, shotokan, sambo, kungFu, taekwondo,
        aikido, muayThai, grappling, melkaiah, seraphite, dumah, emp, enuth, shephon, asakusen,
        velez, selene, hakyoukuken)

    var takenMartialList: List<MartialArt> = mutableListOf()
    var martialMax = 0

    fun changeMartial(charInstance:BaseCharacter, changeItem: MartialArt, isInput: Boolean): Boolean{
        if(isInput){
            if(takenMartialList.size >= martialMax || !qualifies(changeItem, charInstance))
                return false
            else
                takenMartialList = takenMartialList + changeItem
        }
        else {
            takenMartialList = takenMartialList - changeItem
        }

        charInstance.updateMK()

        return true
    }

    fun qualifies(input: MartialArt, charInstance: BaseCharacter): Boolean{
        when(input){
            kempo, shotokan, sambo, taekwondo -> return true
            capoeira -> return charInstance.secondaryList.dance.total >= 40
            taiChi -> return charInstance.kiList.takenAbilities.contains(charInstance.kiList.useOfKi)
            kungFu -> return charInstance.secondaryList.acrobatics.total >= 40 &&
                    charInstance.secondaryList.sleightHand.total >= 40 &&
                    charInstance.secondaryList.style.total >= 20
            aikido -> return charInstance.secondaryList.sleightHand.total >= 40
            muayThai, grappling -> return charInstance.secondaryList.strengthFeat.total >= 40

            melkaiah -> return (takenMartialList.contains(grappling) || takenMartialList.contains(sambo)) &&
                    charInstance.kiList.takenAbilities.contains(charInstance.kiList.inhumanity) &&
                    charInstance.attack >= 160 && (charInstance.block >= 160 || charInstance.dodge >= 160) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            seraphite -> return (takenMartialList.contains(shotokan) || takenMartialList.contains(kempo)) &&
                    charInstance.kiList.takenAbilities.contains(charInstance.kiList.presenceExtrusion) &&
                    charInstance.attack >= 180 &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            dumah -> return (takenMartialList.contains(kempo) || takenMartialList.contains(capoeira)) &&
                    charInstance.kiList.takenAbilities.contains(charInstance.kiList.presenceExtrusion)

            emp -> return (takenMartialList.contains(kempo) || takenMartialList.contains(taekwondo)) &&
                    charInstance.attack >= 200 &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            enuth -> return (takenMartialList.contains(sambo) || takenMartialList.contains(shotokan)) &&
                    charInstance.attack >= 160 && (charInstance.block >= 160 || charInstance.dodge >= 160) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            shephon -> return takenMartialList.contains(aikido) && takenMartialList.contains(kungFu) &&
                    charInstance.kiList.takenAbilities.contains(charInstance.kiList.kiControl) &&
                    (charInstance.block >= 200 || charInstance.dodge >= 200) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            asakusen -> return takenMartialList.contains(kungFu) &&
                    charInstance.attack >= 160 && (charInstance.block >= 160 || charInstance.dodge >= 160) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            velez -> return (takenMartialList.contains(taiChi) || takenMartialList.contains(kungFu)) &&
                    charInstance.kiList.takenAbilities.contains(charInstance.kiList.presenceExtrusion)

            selene -> return takenMartialList.contains(aikido) &&
                    (charInstance.block >= 200 || charInstance.dodge >= 200) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            hakyoukuken -> return (takenMartialList.contains(shotokan) || takenMartialList.contains(muayThai)) &&
                    charInstance.kiList.takenAbilities.contains(charInstance.kiList.useOfNecessaryEnergy) &&
                    charInstance.attack >= 200 &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            else -> return false
        }
    }

    fun updateMartialMax(charInstance: BaseCharacter){
        martialMax = charInstance.attack +
                if (charInstance.block > charInstance.dodge)
                    charInstance.block
                else
                    charInstance.dodge

        martialMax /= 40

        if(takenMartialList.size > martialMax)
            takenMartialList = takenMartialList.slice(0 until martialMax)
    }

    fun mkFromArts(): Int{
        var total = 0

        takenMartialList.forEach{
            total += it.mkBonus
        }

        return total
    }

    fun calculateSpent(charInstance: BaseCharacter): Int{
        var total = 0

        val toCheck = individualModules.toMutableList() - fullModWeapons.toSet()

        takenModules.forEach{
            total += 50
        }

        toCheck.forEach{
            //if primary weapon is mixed
            if(primaryWeapon.type == WeaponType.Mixed){
                //apply same type for exactly matching weapons
                if(it.type == WeaponType.Mixed) {
                    if ((primaryWeapon.mixedType!! - it.mixedType).isEmpty())
                        total += 10

                    //apply mixed type for one matching type
                    else if (primaryWeapon.mixedType!!.contains(it.mixedType!![0]) ||
                        primaryWeapon.mixedType!!.contains(it.mixedType!![1])
                    )
                        total += 15
                }

                //apply mixed type for it belonging to one mixed type
                else if(primaryWeapon.mixedType!!.contains(it.type))
                    total += 15

                else
                    total += 20
            }
            else if(it.type == WeaponType.Mixed && it.mixedType!!.contains(primaryWeapon.type))
                total += 15
            else if(it.type == primaryWeapon.type)
                total += 10
            else
                total += 20
        }

        styleMods.forEach{
            total += when(it){
                "Batto Jutsu/Iai Jutsu"-> 30
                "Area Attack",
                "Disarming Attack" -> 40
                "Precision Attack" -> 50
                else -> 0
            }
        }

        if(takenMartialList.isNotEmpty()){
            total +=
                if(charInstance.ownClass.heldClass == ClassName.tao)
                    10
                else if(primaryWeapon == unarmed)
                    25
                else
                    50

            total += (takenMartialList.size - 1) * 50
        }

        return total
    }







    fun loadProficiencies(fileReader: BufferedReader){
        primaryWeapon = findWeapon(fileReader.readLine())

        var loops = fileReader.readLine().toInt()

        while(loops > 0){
            individualModules = individualModules + findWeapon(fileReader.readLine())
            loops--
        }

        loops = fileReader.readLine().toInt()

        while(loops > 0){
            loadModule(fileReader)
            loops--
        }

        loops = fileReader.readLine().toInt()

        while(loops > 0){
            styleMods = styleMods + fileReader.readLine()
            loops--
        }

        loops = fileReader.readLine().toInt()

        while(loops > 0){
            takenMartialList = takenMartialList + listOf(loadMartial(fileReader.readLine())!!)
            loops--
        }
    }

    fun writeProficiencies(charInstance: BaseCharacter){
        charInstance.addNewData(primaryWeapon.name)

        charInstance.addNewData(individualModules.size.toString())
        individualModules.forEach{
            charInstance.addNewData(it.name)
        }

        charInstance.addNewData(takenModules.size)
        writeModules(charInstance)

        charInstance.addNewData(styleMods.size)
        styleMods.forEach{
            charInstance.addNewData(it)
        }

        charInstance.addNewData(takenMartialList.size)
        takenMartialList.forEach{
            charInstance.addNewData(it.name)
        }
    }

    private fun loadModule(fileReader: BufferedReader){
        val addList = when(fileReader.readLine().toInt()){
            0 -> shortArms
            1 -> axes
            2 -> maces
            3 -> swords
            4 -> twoHanded
            5 -> poles
            6 -> cords
            7 -> shields
            8 -> projectiles
            9 -> thrown
            10 -> barbarianWeapons
            11 -> ninjaWeapons
            12 -> duelWeapons
            13 -> pirateWeapons
            14 -> nomadWeapons
            15 -> huntWeapons
            16 -> knightWeapons
            17 -> gladiatorWeapons
            18 -> assassinWeapons
            19 -> soldierWeapons
            20 -> indigenousWeapons
            21 -> banditWeapons
            22 -> improvised
            else -> listOf()
        }

        updateModulesTaken(addList, true)
    }

    private fun writeModules(charInstance: BaseCharacter){
        takenModules.forEach{
            val listNum = when(it){
                shortArms -> "0"
                axes -> "1"
                maces -> "2"
                swords -> "3"
                twoHanded -> "4"
                poles -> "5"
                cords -> "6"
                shields -> "7"
                projectiles -> "8"
                thrown -> "9"
                barbarianWeapons -> "10"
                ninjaWeapons -> "11"
                duelWeapons -> "12"
                pirateWeapons -> "13"
                nomadWeapons -> "14"
                huntWeapons -> "15"
                knightWeapons -> "16"
                gladiatorWeapons -> "17"
                assassinWeapons -> "18"
                soldierWeapons -> "19"
                indigenousWeapons -> "20"
                banditWeapons -> "21"
                improvised -> "22"
                else -> "23"
            }

            charInstance.addNewData(listNum)
        }
    }

    fun loadMartial(name: String): MartialArt?{
        allMartialArts.forEach{
            if(name == it.name)
                return it
        }

        return null
    }
}