package com.paetus.animaCharCreator.character_creation

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
            return when(input){
                "Light" -> Light
                "Dark" -> Dark
                "Creation" -> Creation
                "Destruction" -> Destruction
                "Essence" -> Essence
                "Illusion" -> Illusion
                "Fire" -> Fire
                "Water" -> Water
                "Earth" -> Earth
                "Air" -> Air
                "Necromancy" -> Necromancy
                else -> Free
            }
        }
    }
}