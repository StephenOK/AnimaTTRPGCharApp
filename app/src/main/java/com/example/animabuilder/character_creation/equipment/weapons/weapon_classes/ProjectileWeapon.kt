package com.example.animabuilder.character_creation.equipment.weapons.weapon_classes

import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType

class ProjectileWeapon(
    name: String,
    damage: Int?,
    speed: Int,
    oneHandStr: Int?,
    twoHandStr: Int?,
    primaryType: AttackType?,
    secondaryType: AttackType?,
    type: WeaponType,
    fortitude: Int,
    breakage: Int?,
    presence: Int,
    val reloadOrRate: Int?,
    val range: Int?,
    ability: List<WeaponAbility>?,
    ownStrength: Int?,
    description: String
): Weapon(name, damage, speed, oneHandStr, twoHandStr, primaryType, secondaryType, type,
    fortitude, breakage, presence, ability, ownStrength, description)