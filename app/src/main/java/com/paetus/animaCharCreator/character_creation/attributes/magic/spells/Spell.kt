package com.paetus.animaCharCreator.character_creation.attributes.magic.spells

import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.enumerations.SpellType

/**
 * Magic ability a character may be able to cast to perform special actions.
 *
 * @param name name of the spell
 * @param inBook associated element of the spell
 * @param isActive whether spell is active or passive
 * @param level book level of the spell
 * @param zCost zeon needed to cast the spell
 * @param effect description of the spell's effects
 * @param addedEffect additional effect to apply if more zeon added to the spell
 * @param zMax maximum zeon that can be spent on the spell
 * @param maintenance zeon needed to maintain the spell
 * @param isDaily whether the spell is daily maintained
 * @param type category of spell
 */
open class Spell(
    val name: Int,
    val inBook: Element,
    val isActive: Boolean,
    val level: Int,
    val zCost: Int,
    val effect: Int,
    val addedEffect: Int,
    val zMax: Int,
    val maintenance: Int?,
    val isDaily: Boolean,
    val type: List<SpellType>
)