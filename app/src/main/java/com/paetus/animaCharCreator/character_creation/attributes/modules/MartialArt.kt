package com.paetus.animaCharCreator.character_creation.attributes.modules

/**
 * Art that the character can buy for (primarily) unarmed combat.
 *
 * @param name name of the art
 * @param description details of the arts combat effects
 * @param prereqList prerequisites for having this art
 * @param mkBonus additional martial knowledge the character may gain from having this art
 * @param qualification function to check if the character qualifies for this art
 */
class MartialArt (
    val name: String,
    val description: String,
    val prereqList: String,
    val mkBonus: Int,
    val qualification: () -> Boolean
)