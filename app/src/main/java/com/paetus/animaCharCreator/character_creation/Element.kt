package com.paetus.animaCharCreator.character_creation

import com.paetus.animaCharCreator.R

/**
 * Enumeration of elements for objects.
 * Covers all individual elements as well as non-elemental items (Free).
 */
enum class Element {
    Light,
    Dark,
    Creation,
    Destruction,
    Essence,
    Illusion,
    Fire,
    Water,
    Earth,
    Air,
    Necromancy,
    Free;

    companion object{
        /**
         * Converts a string to the associated element.
         *
         * @param input string to check against the listed elements.
         */
        fun fromString(input: String): Element{
            Element.values().forEach{
                if(it.name == input) return it
            }

            return Free
        }

        fun toAddress(input: Element): Int{
            return when(input){
                Light -> R.string.elementLight
                Dark -> R.string.elementDark
                Creation -> R.string.elementCreation
                Destruction -> R.string.elementDestruction
                Essence -> R.string.elementEssence
                Illusion -> R.string.elementIllusion
                Fire -> R.string.elementFire
                Water -> R.string.elementWater
                Earth -> R.string.elementEarth
                Air -> R.string.elementAir
                Necromancy -> R.string.elementNecro
                Free -> R.string.elementFree
            }
        }
    }
}