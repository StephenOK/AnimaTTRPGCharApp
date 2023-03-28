package com.example.animabuilder.character_creation.attributes.modules

import java.io.Serializable

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
): Serializable