package com.paetus.animaCharCreator.character_creation.equipment.general_goods

import com.paetus.animaCharCreator.enumerations.Availability

/**
 * Item that alter's the value of a piece of general equipment.
 *
 * @param saveName what to write to the character's file
 * @param qualityType description of item's quality
 * @param modifier change in item's value
 * @param availability rarity of the quality
 */
class QualityModifier(
    val saveName: String,
    val qualityType: Int,
    val modifier: Double,
    val availability: Availability
)