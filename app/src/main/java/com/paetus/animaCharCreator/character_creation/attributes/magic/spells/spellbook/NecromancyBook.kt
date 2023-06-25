package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with necromancy.
 */
class NecromancyBook{
    private val feelDeath = Spell(
        R.string.feelDeath,
        Element.Necromancy,
        true,
        2,
        30,
        R.string.feelDeathDesc,
        R.string.feelDeathEff,
        20,
        10,
        false,
        listOf(SpellType.Detection)
    )

    private val seeBeyond = Spell(
        R.string.seeBeyond,
        Element.Necromancy,
        true,
        6,
        30,
        R.string.seeBeyondDesc,
        R.string.seeBeyondEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val controlScavengers = Spell(
        R.string.controlScavenger,
        Element.Necromancy,
        true,
        8,
        40,
        R.string.controlScavengerDesc,
        R.string.controlScavengerEff,
        30,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val spectralShield = Spell(
        R.string.spectralShield,
        Element.Necromancy,
        false,
        10,
        40,
        R.string.spectralShieldDesc,
        R.string.spectralShieldEff,
        20,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val drainLife = Spell(
        R.string.drainLife,
        Element.Necromancy,
        true,
        12,
        50,
        R.string.drainLifeDesc,
        R.string.drainLifeEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val necromanticDetection = Spell(
        R.string.necroDetection,
        Element.Necromancy,
        true,
        16,
        50,
        R.string.necroDetectionDesc,
        R.string.necroDetectionEff,
        10,
        10,
        false,
        listOf(SpellType.Detection)
    )

    private val talkToDead = Spell(
        R.string.talkToDead,
        Element.Necromancy,
        true,
        18,
        60,
        R.string.talkToDeadDesc,
        R.string.talkToDeadEff,
        10,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val necromanticParalysis = Spell(
        R.string.necroParalyze,
        Element.Necromancy,
        true,
        20,
        60,
        R.string.necroParalyzeDesc,
        R.string.necroParalyzeEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val necromitude = Spell(
        R.string.necromitude,
        Element.Necromancy,
        true,
        22,
        80,
        R.string.necromitudeDesc,
        R.string.necromitudeEff,
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val deathBeam = Spell(
        R.string.deathBeam,
        Element.Necromancy,
        true,
        26,
        60,
        R.string.deathBeamDesc,
        R.string.deathBeamEff,
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val raiseCorpses = Spell(
        R.string.raiseCorpse,
        Element.Necromancy,
        true,
        28,
        80,
        R.string.raiseCorpseDesc,
        R.string.raiseCorpseEff,
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val deadBody = Spell(
        R.string.deadBody,
        Element.Necromancy,
        true,
        30,
        80,
        R.string.deadBodyDesc,
        R.string.deadBodyEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val drainMagic = Spell(
        R.string.drainMag,
        Element.Necromancy,
        true,
        32,
        60,
        R.string.drainMagDesc,
        R.string.drainMagEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val destroyUndead = Spell(
        R.string.destroyUndead,
        Element.Necromancy,
        true,
        36,
        80,
        R.string.destroyUndeadDesc,
        R.string.destroyUndeadEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val drainCharacteristic = Spell(
        R.string.drainChar,
        Element.Necromancy,
        true,
        38,
        80,
        R.string.drainCharDesc,
        R.string.drainCharEff,
        20,
        20,
        false,
        listOf(SpellType.Spiritual)
    )

    private val controlDead = Spell(
        R.string.controlDead,
        Element.Necromancy,
        true,
        40,
        100,
        R.string.controlDeadDesc,
        R.string.controlDeadEff,
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val witherLife = Spell(
        R.string.witherLife,
        Element.Necromancy,
        true,
        42,
        80,
        R.string.witherLifeDesc,
        R.string.witherLifeEff,
        10,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val necromanticShield = Spell(
        R.string.necroShield,
        Element.Necromancy,
        false,
        46,
        80,
        R.string.necroShieldDesc,
        R.string.necroShieldEff,
        20,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val dominateLife = Spell(
        R.string.dominateLife,
        Element.Necromancy,
        false,
        48,
        140,
        R.string.dominateLifeDesc,
        R.string.dominateLifeEff,
        20,
        5,
        true,
        listOf(SpellType.Spiritual)
    )

    private val vampireStigma = Spell(
        R.string.vampireStigma,
        Element.Necromancy,
        true,
        50,
        140,
        R.string.vampireStigmaDesc,
        R.string.vampireStigmaEff,
        20,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val spectralForm = Spell(
        R.string.spectralForm,
        Element.Necromancy,
        true,
        52,
        100,
        R.string.spectralFormDesc,
        R.string.spectralFormEff,
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val necromanticModification = Spell(
        R.string.necroMod,
        Element.Necromancy,
        true,
        56,
        100,
        R.string.necroModDesc,
        R.string.necroModEff,
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val summonDead = Spell(
        R.string.deadSummon,
        Element.Necromancy,
        true,
        58,
        100,
        R.string.deadSummonDesc,
        R.string.deadSummonEff,
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val raiseSpecters = Spell(
        R.string.raiseSpecters,
        Element.Necromancy,
        true,
        60,
        200,
        R.string.raiseSpectersDesc,
        R.string.raiseSpectersEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val drainLifeForce = Spell(
        R.string.drainLifeForce,
        Element.Necromancy,
        true,
        62,
        180,
        R.string.drainLifeForceDesc,
        R.string.drainLifeForceEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val kill = Spell(
        R.string.kill,
        Element.Necromancy,
        true,
        66,
        100,
        R.string.killDesc,
        R.string.killEff,
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val soulBeam = Spell(
        R.string.soulBeam,
        Element.Necromancy,
        true,
        68,
        140,
        R.string.soulBeamDesc,
        R.string.soulBeamEff,
        30,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val necromanticChimera = Spell(
        R.string.necroChimera,
        Element.Necromancy,
        true,
        70,
        250,
        R.string.necroChimeraDesc,
        R.string.necroChimeraEff,
        30,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val lifePerversion = Spell(
        R.string.lifePerversion,
        Element.Necromancy,
        true,
        72,
        180,
        R.string.lifePerversionDesc,
        R.string.lifePerversionEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val vassalage = Spell(
        R.string.vassalage,
        Element.Necromancy,
        true,
        76,
        250,
        R.string.vassalageDesc,
        R.string.vassalageEff,
        30,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val drainSouls = Spell(
        R.string.drainSouls,
        Element.Necromancy,
        true,
        78,
        200,
        R.string.drainSoulsDesc,
        R.string.drainSoulsEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val surpassDeath = Spell(
        R.string.surpassDeath,
        Element.Necromancy,
        true,
        80,
        300,
        R.string.surpassDeathDesc,
        R.string.surpassDeathEff,
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val trueRise = Spell(
        R.string.trueRise,
        Element.Necromancy,
        true,
        82,
        320,
        R.string.trueRiseDesc,
        R.string.trueRiseEff,
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val wellOfLife = Spell(
        R.string.lifeWell,
        Element.Necromancy,
        true,
        86,
        300,
        R.string.lifeWellDesc,
        R.string.lifeWellEff,
        30,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val cursedLand = Spell(
        R.string.cursedLand,
        Element.Necromancy,
        true,
        88,
        350,
        R.string.cursedLandDesc,
        R.string.cursedLandEff,
        40,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val sustenance = Spell(
        R.string.sustenance,
        Element.Necromancy,
        true,
        90,
        200,
        R.string.sustenanceDesc,
        R.string.sustenanceEff,
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val rawMaterial = Spell(
        R.string.rawMaterial,
        Element.Necromancy,
        true,
        92,
        350,
        R.string.rawMaterialDesc,
        R.string.rawMaterialEff,
        50,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val lordOfTheDead = Spell(
        R.string.deadLord,
        Element.Necromancy,
        true,
        96,
        300,
        R.string.deadLordDesc,
        R.string.deadLordEff,
        30,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val comeBackFromDead = Spell(
        R.string.comeBackFromDead,
        Element.Necromancy,
        true,
        98,
        400,
        R.string.comeBackFromDeadDesc,
        R.string.comeBackFromDeadEff,
        40,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    private val awakening = Spell(
        R.string.awakening,
        Element.Necromancy,
        true,
        100,
        900,
        R.string.awakeningDesc,
        R.string.awakeningEff,
        50,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    val fullBook = listOf(
        feelDeath,
        null,
        seeBeyond,
        controlScavengers,
        spectralShield,
        drainLife,
        null,
        necromanticDetection,
        talkToDead,
        necromanticParalysis,
        necromitude,
        null,
        deathBeam,
        raiseCorpses,
        deadBody,
        drainMagic,
        null,
        destroyUndead,
        drainCharacteristic,
        controlDead,
        witherLife,
        null,
        necromanticShield,
        dominateLife,
        vampireStigma,
        spectralForm,
        null,
        necromanticModification,
        summonDead,
        raiseSpecters,
        drainLifeForce,
        null,
        kill,
        soulBeam,
        necromanticChimera,
        lifePerversion,
        null,
        vassalage,
        drainSouls,
        surpassDeath,
        trueRise,
        null,
        wellOfLife,
        cursedLand,
        sustenance,
        rawMaterial,
        null,
        lordOfTheDead,
        comeBackFromDead,
        awakening
    )
}