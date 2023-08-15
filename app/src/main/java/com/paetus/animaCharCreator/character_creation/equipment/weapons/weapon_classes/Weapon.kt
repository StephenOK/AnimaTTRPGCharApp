package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes

import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType

/**
 * Combat item that a character may wield.
 *
 * @param saveName name to write to the character's file
 * @param name name of the weapon
 *
 * @param damage base damage of the weapon
 * @param speed change to initiative wielding the weapon incurs
 *
 * @param oneHandStr strength needed to wield the weapon with one hand
 * @param twoHandStr strength needed to wield the weapon with two hands
 *
 * @param primaryType main damage type the weapon inflicts
 * @param secondaryType alternative damage type the weapon may possess
 *
 * @param type kind of weapon this item is
 *
 * @param fortitude durability of the weapon
 * @param breakage ability of the weapon to break things
 * @param presence size of the weapon
 *
 * @param ability special abilities the weapon may possess
 * @param ownStrength strength score of the weapon itself
 * @param description details of the weapon
 */
open class Weapon(
    val saveName: String,
    val name: Int,

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
    val description: Int
)