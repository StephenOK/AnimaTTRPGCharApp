package com.paetus.animaCharCreator.character_creation.attributes.magic.spells

import com.paetus.animaCharCreator.enumerations.Element

/**
 * Object to hold a list of spells for an associated element.
 */
open class SpellList(val element: Element) {
    val fullBook = mutableListOf<Spell?>()
}