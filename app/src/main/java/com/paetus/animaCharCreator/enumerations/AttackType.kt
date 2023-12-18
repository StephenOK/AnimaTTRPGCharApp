package com.paetus.animaCharCreator.enumerations

import com.paetus.animaCharCreator.R

/**
 * Enumeration for the damage types a weapon or armor can have.
 */
enum class AttackType {
    Cut,
    Impact,
    Thrust,
    Heat,
    Cold,
    Electric,
    Energy;

    companion object{
        /**
         * Retrieves the address of the attack type's associated string.
         *
         * @param attack attack type to convert
         * @return address of the displayed string
         */
        fun toAddress(
            attack: AttackType
        ): Int{
            return when(attack){
                Cut -> R.string.attackCut
                Impact -> R.string.attackImpact
                Thrust -> R.string.attackThrust
                Heat -> R.string.attackHeat
                Cold -> R.string.attackCold
                Electric -> R.string.attackElec
                Energy -> R.string.attackEnergy
            }
        }
    }
}