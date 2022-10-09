package com.example.animabuilder.character_creation.attributes.ki_abilities

import java.io.Serializable

class KiAbility(
    var name: String,
    var prerequisites: KiAbility?,
    var mkCost: Int,
    var description: String
): Serializable