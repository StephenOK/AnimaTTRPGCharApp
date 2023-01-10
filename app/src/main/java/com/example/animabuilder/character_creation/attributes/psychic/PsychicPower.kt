package com.example.animabuilder.character_creation.attributes.psychic

import java.io.Serializable

data class PsychicPower(
    val name: String,
    val level: Int,
    val active: Boolean,
    val maintained: Boolean,
    val description: String,
    val effects: List<String>
): Serializable