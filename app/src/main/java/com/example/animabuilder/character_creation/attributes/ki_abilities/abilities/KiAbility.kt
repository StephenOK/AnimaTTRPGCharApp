package com.example.animabuilder.character_creation.attributes.ki_abilities.abilities

/**
 * Data class for a ki ability a character can take.
 *
 * @param name name of the ability
 * @param prerequisites ki ability needed before this one can be acquired
 * @param mkCost martial knowledge needed to acquire this ki ability
 * @param description details of the ki ability's effect
 */
data class KiAbility(
    val name: String,
    val prerequisites: KiAbility?,
    val mkCost: Int,
    val description: String
)