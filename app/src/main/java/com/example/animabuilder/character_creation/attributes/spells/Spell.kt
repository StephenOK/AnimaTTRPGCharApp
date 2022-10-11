package com.example.animabuilder.character_creation.attributes.spells

import java.io.Serializable

class Spell(
    var name: String,
    var inBook: SpellCategory,
    var isActive: Boolean,
    var level: Int,
    var zCost: Int,
    var effect: String,
    var addedEffect: String,
    var zMax: Int,
    var maintenance: String?,
    var type: List<SpellType>
): Serializable