package com.paetus.animaCharCreator.character_creation.equipment.weapons

import com.paetus.animaCharCreator.R

/**
 * Enumeration for the kinds of weapons there are.
 */
enum class WeaponType{
    Short,
    Axe,
    Mace,
    Sword,
    TwoHanded,
    Pole,
    Cord,
    Mixed,
    Shield,
    Projectile,
    Throwing,
    Unarmed;

    companion object{
        fun toAddress(input: WeaponType): Int{
            return when(input){
                Short -> R.string.weaponShort
                Axe -> R.string.weaponAxe
                Mace -> R.string.weaponMace
                Sword -> R.string.weaponSword
                TwoHanded -> R.string.weaponTwohand
                Pole -> R.string.weaponPole
                Cord -> R.string.weaponCord
                Mixed -> R.string.weaponMixed
                Shield -> R.string.weaponShield
                Projectile -> R.string.weaponProj
                Throwing -> R.string.weaponThrowing
                Unarmed -> R.string.weaponUnarmed
            }
        }
    }
}