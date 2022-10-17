package com.example.animabuilder.character_creation

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