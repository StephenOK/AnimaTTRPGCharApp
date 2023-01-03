package com.example.animabuilder.character_creation.equipment.weapons.weapon_classes

import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import java.io.Serializable

open class Weapon(
    val name: String,

    val damage: Int?,
    val speed: Int,

    val oneHandStr: Int?,
    val twoHandStr: Int?,

    val primaryType: AttackType?,
    val secondaryType: AttackType?,

    val type: WeaponType,

    val fortitude: Int?,
    val breakage: Int?,
    val presence: Int?,

    val ability: List<WeaponAbility>?,
    val ownStrength: Int?,
    val description: String
) : Serializable