package com.example.animabuilder.character_creation.equipment.weapons

import java.io.Serializable

class Weapon(
    var name: String,

    var damage: Int?,
    var speed: Int,

    var oneHandStr: Int?,
    var twoHandStr: Int?,

    var primaryType: AttackType?,
    var secondaryType: AttackType?,

    var type: WeaponType,
    var mixedType: List<WeaponType>?,

    var fortitude: Int?,
    var breakage: Int?,
    var presence: Int?,

    var rateOfFire: Int?,
    var reload: Int?,
    var range: Int?,

    var ability: List<WeaponAbility>?,
    var ownStrength: Int?,
    var description: String
) : Serializable