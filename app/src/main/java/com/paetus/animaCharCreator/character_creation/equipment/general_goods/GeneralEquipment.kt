package com.paetus.animaCharCreator.character_creation.equipment.general_goods

import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.enumerations.CoinType

/**
 * Item representing a piece of equipment a character may purchase.
 *
 * @param saveName string to write to the character's file
 * @param name displayed string of the item
 * @param baseCost default cost of the item
 * @param coinType default coin for the weapon's cost
 * @param weight mass of the item, if available
 * @param availability rarity of the item
 * @param currentQuality quality index of this individual item
 */
class GeneralEquipment(
    val saveName: String,
    val name: Int,
    val baseCost: Double,
    val coinType: CoinType,
    val weight: Double?,
    val availability: Availability,
    val currentQuality: Int?
)