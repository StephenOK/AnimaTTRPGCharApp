package com.example.animabuilder.character_creation.equipment.general_goods

import com.example.animabuilder.character_creation.equipment.CoinType

/**
 * Item representing a piece of equipment a character may purchase.
 *
 * @param name string displaying the item
 * @param baseCost default cost of the item
 * @param coinType default coin  for the weapon's cost
 * @param weight mass of the item, if available
 * @param availability rarity of the item
 * @param currentQuality quality description of this individual item
 */
class GeneralEquipment(
    val name: String,
    val baseCost: Double,
    val coinType: CoinType,
    val weight: Double?,
    val availability: Availability,
    val currentQuality: Int?
)