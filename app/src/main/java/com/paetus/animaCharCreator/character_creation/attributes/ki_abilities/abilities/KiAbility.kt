package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities

/**
 * Data class for a ki ability a character can take.
 *
 * @param saveTag value to write to the save file
 * @param name name of the ability
 * @param prerequisite ki ability needed before this one can be acquired
 * @param mkCost martial knowledge needed to acquire this ki ability
 * @param description details of the ki ability's effect
 */
data class KiAbility(
    val saveTag: String,
    val name: Int,
    val prerequisite: KiAbility?,
    val mkCost: Int,
    val description: Int
)