package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes

import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType

/**
 * A weapon that fits multiple types.
 *
 * @param saveName name to write to file for this item
 * @param name name of the weapon
 *
 * @param damage base damage the weapon can do
 * @param speed change to the character's initiative when wielding this weapon
 *
 * @param oneHandStr strength needed to wield this with one hand
 * @param twoHandStr strength needed to wield this with two hands
 *
 * @param primaryType primary damage type the weapon utilizes
 * @param secondaryType secondary damage type the weapon may utilize
 *
 * @param mixedType pair of weapon types this item has
 *
 * @param fortitude durability of the weapon
 * @param breakage ability of this item to break things
 * @param presence size of the object
 *
 * @param ability special abilities the weapon may have
 * @param ownStrength strength value the weapon may have
 * @param description details of the item in question
 */
class MixedWeapon(
    saveName: String,
    name: Int,

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
    description: Int
): Weapon(
    saveName = saveName,
    name = name,
    damage = damage,
    speed = speed,
    oneHandStr = oneHandStr,
    twoHandStr = twoHandStr,
    primaryType = primaryType,
    secondaryType = secondaryType,
    type = WeaponType.Mixed,
    fortitude = fortitude,
    breakage = breakage,
    presence = presence,
    ability = ability,
    ownStrength = ownStrength,
    description = description
)