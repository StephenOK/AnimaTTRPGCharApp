package com.example.animabuilder.character_creation.equipment.general_goods

import java.io.Serializable

open class GeneralCategory(val qualityInput: List<QualityModifier>?): Serializable {
    val itemsAvailable = mutableListOf<GeneralEquipment>()
}