package com.example.animabuilder.character_creation.equipment.weapons.weapon_instances

import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import java.io.Serializable

class Shields: Serializable {
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

    val shields = listOf(buckler, shield, fullShield)
}