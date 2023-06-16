package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes

import com.paetus.animaCharCreator.character_creation.equipment.weapons.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponAbility
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponType

/**
 * A weapon that fits multiple types.
 *
 * @param name name of the weapon
 * @param damage base damage the weapon can do
 * @param speed change to the character's initiative when wielding this weapon
 * @param oneHandStr strength needed to wield this with one hand
 * @param twoHandStr strength needed to wield this with two hands
 * @param primaryType primary damage type the weapon utilizes
 * @param secondaryType secondary damage type the weapon may utilize
 * @param mixedType pair of weapon types this item has
 * @param fortitude durability of the weapon
 * @param breakage ability of this item to break things
 * @param presence size of the object
 * @param ability special abilities the weapon may have
 * @param ownStrength strength value the weapon may have
 * @param description details of the item in question
 */
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