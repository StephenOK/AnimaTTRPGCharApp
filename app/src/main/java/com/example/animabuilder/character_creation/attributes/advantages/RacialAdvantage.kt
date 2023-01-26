package com.example.animabuilder.character_creation.attributes.advantages

class RacialAdvantage(
    name: String,
    description: String,
    onTake: (() -> Unit)?,
    onRemove: (() -> Unit)?
): Advantage(name, description, null, null, null, null, listOf(0),
    0, { _, _ -> onTake!!()}, { _, _ -> onRemove!!()})