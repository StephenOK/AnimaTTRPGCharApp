package com.paetus.animaCharCreator.character_creation.attributes.psychic

/**
 * Psychic ability a character may acquire.
 *
 * @param saveName name to write to the character's file
 * @param name name of the power
 * @param level strength indicator of the power
 * @param active whether the power is active or passive
 * @param maintained whether the power is maintainable
 * @param description details on the power's effects
 * @param effects table of effects depending on the player's dice roll
 */
data class PsychicPower(
    val saveName: String,
    val name: Int,
    val level: Int,
    val active: Boolean,
    val maintained: Boolean,
    val description: Int,
    val effects: List<String>
)