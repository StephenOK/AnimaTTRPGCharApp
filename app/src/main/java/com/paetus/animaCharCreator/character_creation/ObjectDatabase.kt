package com.paetus.animaCharCreator.character_creation

import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items.CommonAdvantages
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items.MagicAdvantages
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items.PsychicAdvantages
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items.RaceAdvantages
import com.paetus.animaCharCreator.character_creation.attributes.class_objects.ClassRecord
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities.KiRecord
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.TechniquePrebuilts
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueTableDataRecord
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.MagicLibrary
import com.paetus.animaCharCreator.character_creation.attributes.modules.MartialArts
import com.paetus.animaCharCreator.character_creation.attributes.modules.StyleInstances
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsyLibrary
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Goods
import com.paetus.animaCharCreator.character_creation.equipment.Armory

/**
 * Collection of all read-only items available to the character in its various fields.
 */
class ObjectDatabase {
    val classRecord = ClassRecord()
    val races = RaceAdvantages()

    val armory = Armory()
    val martials = MartialArts()
    val styles = StyleInstances()

    val kiRecord = KiRecord()
    val techniqueDatabase = TechniqueTableDataRecord()
    val prebuiltTechs = TechniquePrebuilts(techniqueDataRecord = techniqueDatabase)

    val magicLibrary = MagicLibrary()
    val psyLibrary = PsyLibrary()

    val commonAdvantages = CommonAdvantages()
    val magicAdvantages = MagicAdvantages()
    val psychicAdvantages = PsychicAdvantages()

    val goods = Goods()
}