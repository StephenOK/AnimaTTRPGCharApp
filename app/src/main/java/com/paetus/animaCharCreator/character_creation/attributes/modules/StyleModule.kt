package com.paetus.animaCharCreator.character_creation.attributes.modules

/**
 * Styles that a character may take to improve their combat abilities.
 *
 * @param name name of the style module
 * @param description details of the effects of the style
 * @param cost development points needed for this style
 */
class StyleModule(
    val name: String,
    val description: String,
    val cost: Int
)