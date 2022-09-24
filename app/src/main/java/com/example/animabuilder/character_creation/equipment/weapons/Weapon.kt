package com.example.animabuilder.character_creation.equipment.weapons

import java.io.Serializable

class Weapon(
    var name: String,
    var damage: Int?,
    var speed: Int,
    var oneHandStr: Int?,
    var twoHandStr: Int?,
    var primaryType: AttackType,
    var secondaryType: AttackType?,
    var type: WeaponType,
    var fortitude: Int?,
    var breakage: Int?,
    var presence: Int?
) : Serializable{
    var mixedType: List<WeaponType>? = null

    init {
        if(type == WeaponType.Mixed){
            mixedType = when(name) {
                "Bastard Sword" -> listOf(WeaponType.Sword, WeaponType.TwoHanded)
                "Flail" -> listOf(WeaponType.Mace, WeaponType.Cord)
                "Foil" -> listOf(WeaponType.Sword, WeaponType.Short)
                "Halberd", "Scythe" -> listOf(WeaponType.Pole, WeaponType.TwoHanded)
                "Heavy Battle-Mace", "Large Multi-Headed Flail" -> listOf(WeaponType.Mace, WeaponType.TwoHanded)
                "Two-Handed Axe" -> listOf(WeaponType.Axe, WeaponType.TwoHanded)

                "Kusari-Gama" -> listOf(WeaponType.Short, WeaponType.Cord)
                else -> null
            }
        }
    }
}