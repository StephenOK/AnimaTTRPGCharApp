package com.paetus.animaCharCreator.enumerations.weaponEnums

import com.paetus.animaCharCreator.R

/**
 * Enumeration for special abilities a weapon can have.
 */
enum class WeaponAbility {
    Precision,
    TwoHanded,
    OneOrTwoHanded,
    Complex,
    Throwable,
    Trapping,
    WeaponTrap,
    Special,
    OwnStrength;

    companion object{
        fun getAddress(input: WeaponAbility): Int{
            return when(input){
                Precision -> R.string.abilityPrecision
                TwoHanded -> R.string.abilityTwohanded
                OneOrTwoHanded -> R.string.abilityOneOrTwo
                Complex -> R.string.abilityComplex
                Throwable -> R.string.abilityThrowable
                Trapping -> R.string.abilityTrapping
                WeaponTrap -> R.string.abilityWeaponTrap
                Special -> R.string.abilitySpecial
                OwnStrength -> R.string.abilityOwnStrength
            }
        }
    }
}