package com.example.animabuilder.character_creation.equipment.weapons.weapon_classes

import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType

/**
 * Special type of weapon that utilizes ranged combat.
 *
 * @param name name of the weapon
 * @param damage base damage of the weapon
 * @param speed change in initiative the character has when wielding this weapon
 * @param oneHandStr strength needed to wield this weapon with one hand
 * @param twoHandStr strength needed to wield this weapon with two hands
 * @param primaryType main type of damage this weapon deals
 * @param secondaryType alternative damage type this weapon may deal
 * @param type kind of weapon this is
 * @param fortitude durability of the weapon
 * @param breakage ability for this weapon to break things
 * @param presence size of the weapon
 * @param reloadOrRate either the reload rate of the ranged weapon or the rate of fire of the thrown weapon
 * @param range effective range of the weapon
 * @param ability special abilities the weapon may have
 * @param ownStrength strength score of the weapon itself
 * @param description details of the weapon
 */
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