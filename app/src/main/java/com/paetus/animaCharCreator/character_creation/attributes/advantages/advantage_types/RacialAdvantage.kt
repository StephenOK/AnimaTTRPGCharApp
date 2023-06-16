package com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types

/**
 * Subtype of advantage which comes with a character's race selection.
 *
 * @param name name of the advantage
 * @param description details of the advantage's effects
 * @param onTake function to run on advantage's acquisition
 * @param onRemove function to run on advantage's removal
 */
class RacialAdvantage(
    name: String,
    description: String,
    onTake: ((Int?, Int) -> Unit)?,
    onRemove: ((Int?, Int) -> Unit)?
): Advantage(name, description, null, null, null, null, null, listOf(0),
    0, onTake, onRemove)