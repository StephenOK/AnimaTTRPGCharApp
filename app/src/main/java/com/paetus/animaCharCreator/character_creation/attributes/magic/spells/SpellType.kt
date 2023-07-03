package com.paetus.animaCharCreator.character_creation.attributes.magic.spells

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
        fun toAddress(input: SpellType): Int{
            return when(input){
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