package com.paetus.animaCharCreator.enumerations

import com.paetus.animaCharCreator.R

/**
 * Enumeration of class types that can be had.
 */
enum class Archetype{
    Domine,
    Fighter,
    Mystic,
    Novel,
    Prowler,
    Psychic;

    companion object {
        /**
         * Convert an Archetype enumeration to a string reference.
         *
         * @param archetype enumeration to convert
         * @return corresponding string resource for the inputted archetype
         */
        fun toAddress(archetype: Archetype): Int {
            return when (archetype) {
                Domine -> R.string.domineName
                Fighter -> R.string.fighterName
                Mystic -> R.string.mysticName
                Novel -> R.string.novelName
                Prowler -> R.string.prowlerName
                Psychic -> R.string.psychicName
            }
        }
    }
}