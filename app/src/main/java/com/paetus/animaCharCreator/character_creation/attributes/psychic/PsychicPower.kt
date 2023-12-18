package com.paetus.animaCharCreator.character_creation.attributes.psychic

/**
 * Psychic ability a character may acquire.
 *
 * @param saveName name to write to the character's file
 * @param name name of the power
 * @param level strength indicator of the power
 * @param isActive whether the power is active or passive
 * @param maintained whether the power is maintainable
 * @param description details on the power's effects
 *
 * @param stringBaseList list of effect descriptions needed for the effect table
 * @param stringBaseCount number of inputs for fatigue and stringBaseList objects
 * @param stringInput additional data needed for fatigue and the stringBaseList items
 */
data class PsychicPower(
    val saveName: String,
    val name: Int,
    val level: Int,
    val isActive: Boolean,
    val maintained: Boolean,
    val description: Int,

    val stringBaseList: List<Int>,
    val stringBaseCount: List<Int>,
    val stringInput: List<Any?>
)