package com.example.animabuilder.character_creation.attributes.ki_abilities

import java.io.Serializable
//Data class for a ki ability a character can take
data class KiAbility(
    val name: String,
    val prerequisites: KiAbility?,
    val mkCost: Int,
    val description: String
): Serializable