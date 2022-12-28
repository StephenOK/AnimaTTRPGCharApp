package com.example.animabuilder.character_creation.equipment.weapons.weapon_instances

import com.example.animabuilder.character_creation.equipment.weapons.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import java.io.Serializable

class Projectiles: Serializable {
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

    val projectiles = listOf(arquebus, blowgun, compositeBow, crossbow, repeatingCrossbow,
        heavyCrossbow, miniCrossbow, shortBow, longBow, matchlock, sling, lightBallista, heavyBallista, cannon)
}