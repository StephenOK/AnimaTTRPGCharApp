package com.paetus.animaCharCreator.enumerations

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
         * @param elementName string to check against the listed elements
         * @return element matching with this name
         */
        fun fromString(elementName: String): Element {
            entries.forEach{element ->
                if(element.name == elementName) return element
            }

            return Free
        }

        /**
         * Converts a element to its associated string address.
         *
         * @param element enumeration object to convert
         * @return address that references this item
         */
        fun toAddress(element: Element): Int{
            return when(element){
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