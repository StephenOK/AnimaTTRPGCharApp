package com.example.animabuilder.character_creation.attributes.advantages.advantage_types

class RacialAdvantage(
    name: String,
    description: String,
    onTake: ((Int?, Int) -> Unit)?,
    onRemove: ((Int?, Int) -> Unit)?
): Advantage(name, description, null, null, null, null, null, listOf(0),
    0, onTake, onRemove)