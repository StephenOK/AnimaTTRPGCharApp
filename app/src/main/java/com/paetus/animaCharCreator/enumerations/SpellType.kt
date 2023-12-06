package com.paetus.animaCharCreator.enumerations

import com.paetus.animaCharCreator.R

/**
 * Categories a spell can be.
 */
enum class SpellType {
    Attack,
    Defense,
    Spiritual,
    Effect,
    Automatic,
    Detection;

    companion object{
        /**
         * Converts a SpellType enumeration to its corresponding string address.
         *
         * @param spellType spell type to convert to a string
         */
        fun toAddress(spellType: SpellType): Int{
            return when(spellType){
                Attack -> R.string.spellAttack
                Defense -> R.string.spellDefense
                Spiritual -> R.string.spellSpiritual
                Effect -> R.string.spellEffect
                Automatic -> R.string.spellAuto
                Detection -> R.string.spellDetection
            }
        }
    }
}