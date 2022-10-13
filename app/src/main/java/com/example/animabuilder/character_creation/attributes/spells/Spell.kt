package com.example.animabuilder.character_creation.attributes.spells

import com.example.animabuilder.character_creation.Element
import java.io.Serializable

class Spell(
    var name: String,
    var inBook: Element,
    var isActive: Boolean,
    var level: Int,
    var zCost: Int,
    var effect: String,
    var addedEffect: String,
    var zMax: Int,
    var maintenance: String?,
    var type: List<SpellType>
): Serializable