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
    var fortitude: Int?,
    var breakage: Int?,
    var presence: Int?,
    var ability: List<WeaponAbility>?,
    var description: String
) : Serializable{
    var mixedType: List<WeaponType>? = null
    var ownStrength: Int? = null

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

        if(ability!!.contains(WeaponAbility.Trapping)){
            ownStrength = when(name){
                "Miniature Crossbow" -> 5
                "Chain", "Whip", "Kusari-Gama", "Crossbow", "Repeating Crossbow" -> 8
                "Lasso", "Matchlock Pistol" -> 9
                "Gladiator's Net", "Bolas", "Heavy Crossbow" -> 10
                "Arquebus" -> 11
                "Light Ballista" -> 12
                "Heavy Ballista", "Cannon" -> 13
                else -> null
            }
        }
    }
}