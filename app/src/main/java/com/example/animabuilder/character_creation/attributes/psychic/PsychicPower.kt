package com.example.animabuilder.character_creation.attributes.psychic

/**
 * Psychic ability a character may acquire.
 *
 * @param name name of the power
 * @param level strength indicator of the power
 * @param active whether the power is active or passive
 * @param maintained whether the power is maintainable
 * @param description details on the power's effects
 * @param effects table of effects depending on the player's dice roll
 */
data class PsychicPower(
    val name: String,
    val level: Int,
    val active: Boolean,
    val maintained: Boolean,
    val description: String,
    val effects: List<String>
)