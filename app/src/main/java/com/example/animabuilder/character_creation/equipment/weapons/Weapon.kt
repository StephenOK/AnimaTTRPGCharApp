package com.example.animabuilder.character_creation.equipment.weapons

import java.io.Serializable

class Weapon(
    name: String,
    damage: Int?,
    speed: Int,
    oneHandStr: Int?,
    twoHandStr: Int?,
    primaryType: AttackType,
    secondaryType: AttackType?,
    type: WeaponType,
    fortitude: Int?,
    breakage: Int?,
    presence: Int?
) : Serializable{
    var mixedType: List<WeaponType>? = null

    init {
        if(type == WeaponType.Mixed){
            mixedType = when(name) {
                "Bastard Sword" -> listOf(WeaponType.Sword, WeaponType.TwoHanded)
                "Flail" -> listOf(WeaponType.Mace, WeaponType.Cord)
                "Foil" -> listOf(WeaponType.Sword, WeaponType.Short)
                "Halberd" -> listOf(WeaponType.Pole, WeaponType.TwoHanded)
                "Heavy Battle-Mace" -> listOf(WeaponType.Mace, WeaponType.TwoHanded)
                "Large Multi-Headed Flail" -> listOf(WeaponType.Mace, WeaponType.TwoHanded)
                "Scythe" -> listOf(WeaponType.Pole, WeaponType.TwoHanded)
                "Two-Handed Axe" -> listOf(WeaponType.Axe, WeaponType.TwoHanded)

                "Kusari-Gama" -> listOf(WeaponType.Short, WeaponType.Cord)
                else -> null
            }
        }
    }
}