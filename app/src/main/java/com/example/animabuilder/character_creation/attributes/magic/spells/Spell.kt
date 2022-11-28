package com.example.animabuilder.character_creation.attributes.magic.spells

import com.example.animabuilder.character_creation.Element
import java.io.Serializable

data class Spell(
    val name: String,
    val inBook: Element,
    val isActive: Boolean,
    val level: Int,
    val zCost: Int,
    val effect: String,
    val addedEffect: String,
    val zMax: Int,
    val maintenance: Int?,
    val isDaily: Boolean,
    val type: List<SpellType>
): Serializable