package com.paetus.animaCharCreator.character_creation.attributes.modules

/**
 * Art that the character can buy for (primarily) unarmed combat.
 *
 * @param saveName name to write to file for this item
 * @param name displayed name of the art
 * @param description details of the arts combat effects
 * @param prereqList description of prerequisites needed for this art
 * @param mkBonus additional martial knowledge the character may gain from having this art
 * @param qualification function to check if the character qualifies for this art
 */
class MartialArt (
    val saveName: String,
    val name: Int,
    val description: Int,
    val prereqList: Int,
    val mkBonus: Int,
    val qualification: () -> Boolean
)