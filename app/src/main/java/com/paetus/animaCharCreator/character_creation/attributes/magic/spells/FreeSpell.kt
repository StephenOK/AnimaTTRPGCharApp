package com.paetus.animaCharCreator.character_creation.attributes.magic.spells

import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.enumerations.SpellType

/**
 * A spell with no associated element.
 * Can by taken as part of any spellbook.
 *
 * @param saveName name to write to file
 * @param name name of the spell
 * @param isActive whether the spell is active or passive
 * @param level associated level of the spell
 * @param zCost zeon needed to cast the base version of the spell
 * @param effect description of the spell
 * @param addedEffect additional effect when more zeon spent
 * @param zMax maximum zeon cost the character can spend on this spell
 * @param maintenance zeon needed to maintain the spell per turn
 * @param isDaily whether the spell is maintained daily or not
 * @param type list of types of uses the spell has
 * @param forbiddenElements books the spell is not permitted to be in
 */
class FreeSpell(
    val saveName: String,
    name: Int,
    isActive: Boolean,
    level: Int,
    zCost: Int,
    effect: Int,
    addedEffect: Int,
    zMax: Int,
    maintenance: Int?,
    isDaily: Boolean,
    type: List<SpellType>,
    val forbiddenElements: List<Element>
) : Spell(
    name = name,
    isActive = isActive,
    level = level,
    zCost = zCost,
    effect = effect,
    addedEffect = addedEffect,
    zMax = zMax,
    maintenance = maintenance,
    isDaily = isDaily,
    type = type
){
    /**
     * Function to determine equivalence between this spell and the inputted item.
     *
     * @param spell item to compare this one to
     * @return true if all parameters are equivalent
     */
    fun equals(spell: FreeSpell): Boolean{
        //catch forbidden element size difference
        if(spell.forbiddenElements.size != forbiddenElements.size)
            return false

        //check that all elements are the same
        spell.forbiddenElements.forEach{
            if(!forbiddenElements.contains(it)) return false
        }

        //return all other parameters match
        return saveName == spell.saveName &&
                name == spell.name &&
                isActive == spell.isActive &&
                level == spell.level &&
                zCost == spell.zCost &&
                effect == spell.effect &&
                addedEffect == spell.addedEffect &&
                zMax == spell.zMax &&
                maintenance == spell.maintenance &&
                isDaily == spell.isDaily &&
                type == spell.type

    }
}