package com.paetus.animaCharCreator.character_creation.attributes.modules

/**
 * Styles that a character may take to improve their combat abilities.
 *
 * @param saveName name to write to the character's file
 * @param name name of the style module
 * @param description details of the effects of the style
 * @param cost development points needed for this style
 */
class StyleModule(
    val saveName: String,
    val name: Int,
    val description: Int,
    val cost: Int
)