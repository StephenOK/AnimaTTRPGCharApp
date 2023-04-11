package com.example.animabuilder.character_creation.equipment.general_goods

import java.io.Serializable

class QualityModifier(
    val qualityType: String,
    val modifier: Double,
    val availability: Availability
): Serializable