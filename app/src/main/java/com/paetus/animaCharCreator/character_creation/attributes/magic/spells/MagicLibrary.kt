package com.paetus.animaCharCreator.character_creation.attributes.magic.spells

import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.AirSpells
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.CreationSpells
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.DarkSpells
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.DestructionSpells
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.EarthSpells
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.EssenceSpells
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.FireSpells
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.FreeSpells
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.IllusionSpells
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.LightSpells
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.NecromancySpells
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.WaterSpells

/**
 * Collection of all spells available to the character.
 */
class MagicLibrary {
    val lightSpells = LightSpells()
    val darkSpells = DarkSpells()
    val creationSpells = CreationSpells()
    val destructionSpells = DestructionSpells()
    val airSpells = AirSpells()
    val earthSpells = EarthSpells()
    val waterSpells = WaterSpells()
    val fireSpells = FireSpells()
    val essenceSpells = EssenceSpells()
    val illusionSpells = IllusionSpells()
    val necromancySpells = NecromancySpells()
    val freeSpells = FreeSpells()
}