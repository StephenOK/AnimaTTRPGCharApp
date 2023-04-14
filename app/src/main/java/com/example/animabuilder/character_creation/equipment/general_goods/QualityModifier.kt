package com.example.animabuilder.character_creation.equipment.general_goods

/**
 * Item that alter's the value of a piece of general equipment.
 *
 * @param qualityType description of item's quality
 * @param modifier change in item's value
 * @param availability rarity of the quality
 */
class QualityModifier(
    val qualityType: String,
    val modifier: Double,
    val availability: Availability
)