package com.example.animabuilder.character_creation.attributes.magic.spells

import com.example.animabuilder.character_creation.Element

class FreeSpell(
    name: String,
    isActive: Boolean,
    level: Int,
    zCost: Int,
    effect: String,
    addedEffect: String,
    zMax: Int,
    maintenance: Int?,
    isDaily: Boolean,
    type: List<SpellType>,
    val forbiddenElements: List<Element>
) : Spell(name, Element.Free, isActive, level, zCost, effect, addedEffect, zMax, maintenance, isDaily, type)