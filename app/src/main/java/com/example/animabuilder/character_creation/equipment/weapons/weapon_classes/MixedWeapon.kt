package com.example.animabuilder.character_creation.equipment.weapons.weapon_classes

import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType

class MixedWeapon(
    name: String,
    damage: Int,
    speed: Int,
    oneHandStr: Int,
    twoHandStr: Int?,
    primaryType: AttackType,
    secondaryType: AttackType?,
    val mixedType: List<WeaponType>,
    fortitude: Int,
    breakage: Int?,
    presence: Int,
    ability: List<WeaponAbility>?,
    ownStrength: Int?,
    description: String
): Weapon(name, damage, speed, oneHandStr, twoHandStr, primaryType, secondaryType, WeaponType.Mixed,
    fortitude, breakage, presence, ability, ownStrength, description)