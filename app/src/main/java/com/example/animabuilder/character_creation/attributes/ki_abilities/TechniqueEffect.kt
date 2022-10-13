package com.example.animabuilder.character_creation.attributes.ki_abilities

import com.example.animabuilder.character_creation.Element
import java.io.Serializable

class TechniqueEffect(
    var name: String,
    var effect: String,
    var mkCost: Int,
    var kiCost: Int,
    var maint: Int?,
    var kiBuild: List<Int>,
    var elements: List<Element>,
    var lvl: Int
): Serializable