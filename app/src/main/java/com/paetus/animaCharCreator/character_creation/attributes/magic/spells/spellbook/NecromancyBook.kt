package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.enumerations.SpellType

/**
 * List of spells associated with necromancy.
 */
class NecromancyBook{
    private val feelDeath = Spell(
        name = R.string.feelDeath,
        inBook = Element.Necromancy,
        isActive = true,
        level = 2,
        zCost = 30,
        effect = R.string.feelDeathDesc,
        addedEffect = R.string.feelDeathEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Detection)
    )

    private val seeBeyond = Spell(
        name = R.string.seeBeyond,
        inBook = Element.Necromancy,
        isActive = true,
        level = 6,
        zCost = 30,
        effect = R.string.seeBeyondDesc,
        addedEffect = R.string.seeBeyondEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val controlScavengers = Spell(
        name = R.string.controlScavenger,
        inBook = Element.Necromancy,
        isActive = true,
        level = 8,
        zCost = 40,
        effect = R.string.controlScavengerDesc,
        addedEffect = R.string.controlScavengerEff,
        zMax = 30,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val spectralShield = Spell(
        name = R.string.spectralShield,
        inBook = Element.Necromancy,
        isActive = false,
        level = 10,
        zCost = 40,
        effect = R.string.spectralShieldDesc,
        addedEffect = R.string.spectralShieldEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Defense)
    )

    private val drainLife = Spell(
        name = R.string.drainLife,
        inBook = Element.Necromancy,
        isActive = true,
        level = 12,
        zCost = 50,
        effect = R.string.drainLifeDesc,
        addedEffect = R.string.drainLifeEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val necromanticDetection = Spell(
        name = R.string.necroDetection,
        inBook = Element.Necromancy,
        isActive = true,
        level = 16,
        zCost = 50,
        effect = R.string.necroDetectionDesc,
        addedEffect = R.string.necroDetectionEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Detection)
    )

    private val talkToDead = Spell(
        name = R.string.talkToDead,
        inBook = Element.Necromancy,
        isActive = true,
        level = 18,
        zCost = 60,
        effect = R.string.talkToDeadDesc,
        addedEffect = R.string.talkToDeadEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val necromanticParalysis = Spell(
        name = R.string.necroParalyze,
        inBook = Element.Necromancy,
        isActive = true,
        level = 20,
        zCost = 60,
        effect = R.string.necroParalyzeDesc,
        addedEffect = R.string.necroParalyzeEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val necromitude = Spell(
        name = R.string.necromitude,
        inBook = Element.Necromancy,
        isActive = true,
        level = 22,
        zCost = 80,
        effect = R.string.necromitudeDesc,
        addedEffect = R.string.necromitudeEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val deathBeam = Spell(
        name = R.string.deathBeam,
        inBook = Element.Necromancy,
        isActive = true,
        level = 26,
        zCost = 60,
        effect = R.string.deathBeamDesc,
        addedEffect = R.string.deathBeamEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val raiseCorpses = Spell(
        name = R.string.raiseCorpse,
        inBook = Element.Necromancy,
        isActive = true,
        level = 28,
        zCost = 80,
        effect = R.string.raiseCorpseDesc,
        addedEffect = R.string.raiseCorpseEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val deadBody = Spell(
        name = R.string.deadBody,
        inBook = Element.Necromancy,
        isActive = true,
        level = 30,
        zCost = 80,
        effect = R.string.deadBodyDesc,
        addedEffect = R.string.deadBodyEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val drainMagic = Spell(
        name = R.string.drainMag,
        inBook = Element.Necromancy,
        isActive = true,
        level = 32,
        zCost = 60,
        effect = R.string.drainMagDesc,
        addedEffect = R.string.drainMagEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val destroyUndead = Spell(
        name = R.string.destroyUndead,
        inBook = Element.Necromancy,
        isActive = true,
        level = 36,
        zCost = 80,
        effect = R.string.destroyUndeadDesc,
        addedEffect = R.string.destroyUndeadEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val drainCharacteristic = Spell(
        name = R.string.drainChar,
        inBook = Element.Necromancy,
        isActive = true,
        level = 38,
        zCost = 80,
        effect = R.string.drainCharDesc,
        addedEffect = R.string.drainCharEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val controlDead = Spell(
        name = R.string.controlDead,
        inBook = Element.Necromancy,
        isActive = true,
        level = 40,
        zCost = 100,
        effect = R.string.controlDeadDesc,
        addedEffect = R.string.controlDeadEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val witherLife = Spell(
        name = R.string.witherLife,
        inBook = Element.Necromancy,
        isActive = true,
        level = 42,
        zCost = 80,
        effect = R.string.witherLifeDesc,
        addedEffect = R.string.witherLifeEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val necromanticShield = Spell(
        name = R.string.necroShield,
        inBook = Element.Necromancy,
        isActive = false,
        level = 46,
        zCost = 80,
        effect = R.string.necroShieldDesc,
        addedEffect = R.string.necroShieldEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Defense)
    )

    private val dominateLife = Spell(
        name = R.string.dominateLife,
        inBook = Element.Necromancy,
        isActive = false,
        level = 48,
        zCost = 140,
        effect = R.string.dominateLifeDesc,
        addedEffect = R.string.dominateLifeEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val vampireStigma = Spell(
        name = R.string.vampireStigma,
        inBook = Element.Necromancy,
        isActive = true,
        level = 50,
        zCost = 140,
        effect = R.string.vampireStigmaDesc,
        addedEffect = R.string.vampireStigmaEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val spectralForm = Spell(
        name = R.string.spectralForm,
        inBook = Element.Necromancy,
        isActive = true,
        level = 52,
        zCost = 100,
        effect = R.string.spectralFormDesc,
        addedEffect = R.string.spectralFormEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val necromanticModification = Spell(
        name = R.string.necroMod,
        inBook = Element.Necromancy,
        isActive = true,
        level = 56,
        zCost = 100,
        effect = R.string.necroModDesc,
        addedEffect = R.string.necroModEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val summonDead = Spell(
        name = R.string.deadSummon,
        inBook = Element.Necromancy,
        isActive = true,
        level = 58,
        zCost = 100,
        effect = R.string.deadSummonDesc,
        addedEffect = R.string.deadSummonEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val raiseSpecters = Spell(
        name = R.string.raiseSpecters,
        inBook = Element.Necromancy,
        isActive = true,
        level = 60,
        zCost = 200,
        effect = R.string.raiseSpectersDesc,
        addedEffect = R.string.raiseSpectersEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val drainLifeForce = Spell(
        name = R.string.drainLifeForce,
        inBook = Element.Necromancy,
        isActive = true,
        level = 62,
        zCost = 180,
        effect = R.string.drainLifeForceDesc,
        addedEffect = R.string.drainLifeForceEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val kill = Spell(
        name = R.string.kill,
        inBook = Element.Necromancy,
        isActive = true,
        level = 66,
        zCost = 100,
        effect = R.string.killDesc,
        addedEffect = R.string.killEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val soulBeam = Spell(
        name = R.string.soulBeam,
        inBook = Element.Necromancy,
        isActive = true,
        level = 68,
        zCost = 140,
        effect = R.string.soulBeamDesc,
        addedEffect = R.string.soulBeamEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val necromanticChimera = Spell(
        name = R.string.necroChimera,
        inBook = Element.Necromancy,
        isActive = true,
        level = 70,
        zCost = 250,
        effect = R.string.necroChimeraDesc,
        addedEffect = R.string.necroChimeraEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val lifePerversion = Spell(
        name = R.string.lifePerversion,
        inBook = Element.Necromancy,
        isActive = true,
        level = 72,
        zCost = 180,
        effect = R.string.lifePerversionDesc,
        addedEffect = R.string.lifePerversionEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val vassalage = Spell(
        name = R.string.vassalage,
        inBook = Element.Necromancy,
        isActive = true,
        level = 76,
        zCost = 250,
        effect = R.string.vassalageDesc,
        addedEffect = R.string.vassalageEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val drainSouls = Spell(
        name = R.string.drainSouls,
        inBook = Element.Necromancy,
        isActive = true,
        level = 78,
        zCost = 200,
        effect = R.string.drainSoulsDesc,
        addedEffect = R.string.drainSoulsEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val surpassDeath = Spell(
        name = R.string.surpassDeath,
        inBook = Element.Necromancy,
        isActive = true,
        level = 80,
        zCost = 300,
        effect = R.string.surpassDeathDesc,
        addedEffect = R.string.surpassDeathEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val trueRise = Spell(
        name = R.string.trueRise,
        inBook = Element.Necromancy,
        isActive = true,
        level = 82,
        zCost = 320,
        effect = R.string.trueRiseDesc,
        addedEffect = R.string.trueRiseEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val wellOfLife = Spell(
        name = R.string.lifeWell,
        inBook = Element.Necromancy,
        isActive = true,
        level = 86,
        zCost = 300,
        effect = R.string.lifeWellDesc,
        addedEffect = R.string.lifeWellEff,
        zMax = 30,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val cursedLand = Spell(
        name = R.string.cursedLand,
        inBook = Element.Necromancy,
        isActive = true,
        level = 88,
        zCost = 350,
        effect = R.string.cursedLandDesc,
        addedEffect = R.string.cursedLandEff,
        zMax = 40,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val sustenance = Spell(
        name = R.string.sustenance,
        inBook = Element.Necromancy,
        isActive = true,
        level = 90,
        zCost = 200,
        effect = R.string.sustenanceDesc,
        addedEffect = R.string.sustenanceEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val rawMaterial = Spell(
        name = R.string.rawMaterial,
        inBook = Element.Necromancy,
        isActive = true,
        level = 92,
        zCost = 350,
        effect = R.string.rawMaterialDesc,
        addedEffect = R.string.rawMaterialEff,
        zMax = 50,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val lordOfTheDead = Spell(
        name = R.string.deadLord,
        inBook = Element.Necromancy,
        isActive = true,
        level = 96,
        zCost = 300,
        effect = R.string.deadLordDesc,
        addedEffect = R.string.deadLordEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val comeBackFromDead = Spell(
        name = R.string.comeBackFromDead,
        inBook = Element.Necromancy,
        isActive = true,
        level = 98,
        zCost = 400,
        effect = R.string.comeBackFromDeadDesc,
        addedEffect = R.string.comeBackFromDeadEff,
        zMax = 40,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val awakening = Spell(
        name = R.string.awakening,
        inBook = Element.Necromancy,
        isActive = true,
        level = 100,
        zCost = 900,
        effect = R.string.awakeningDesc,
        addedEffect = R.string.awakeningEff,
        zMax = 50,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic)
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