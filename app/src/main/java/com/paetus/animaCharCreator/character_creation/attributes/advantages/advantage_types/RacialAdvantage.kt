package com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types

import com.paetus.animaCharCreator.character_creation.BaseCharacter

/**
 * Subclass of advantage which comes with a character's race selection.
 *
 * @param name address of the displayed name
 * @param description details of the advantage's effects
 * @param onTake function to run on advantage's acquisition
 * @param onRemove function to run on advantage's removal
 */
class RacialAdvantage(
    name: Int,
    description: Int,
    onTake: ((BaseCharacter, Int?, Int) -> Unit)?,
    onRemove: ((BaseCharacter, Int?, Int) -> Unit)?
): Advantage(
    "", name, description, null, null, null, null, null, null,
    listOf(0), 0, onTake, onRemove
)