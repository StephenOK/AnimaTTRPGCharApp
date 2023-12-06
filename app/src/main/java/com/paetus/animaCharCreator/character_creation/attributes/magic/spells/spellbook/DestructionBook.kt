package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.enumerations.SpellType

/**
 * List of spells associated with the destruction element.
 */
class DestructionBook{
    private val fragility = Spell(
        name = R.string.fragility,
        inBook = Element.Destruction,
        isActive = true,
        level = 2,
        zCost = 30,
        effect = R.string.fragilityDesc,
        addedEffect = R.string.fragilityEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val dismantle = Spell(
        name = R.string.dismantle,
        inBook = Element.Destruction,
        isActive = true,
        level = 6,
        zCost = 40,
        effect = R.string.dismantleDesc,
        addedEffect = R.string.dismantleEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val destroyIntensities = Spell(
        name = R.string.destroyIntense,
        inBook = Element.Destruction,
        isActive = true,
        level = 8,
        zCost = 40,
        effect = R.string.destroyIntenseDesc,
        addedEffect = R.string.destroyIntenseEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val minorDestruction = Spell(
        name = R.string.minorDestruct,
        inBook = Element.Destruction,
        isActive = true,
        level = 10,
        zCost = 50,
        effect = R.string.minorDestructDesc,
        addedEffect = R.string.minorDestructEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val destructionSphere = Spell(
        name = R.string.destructSphere,
        inBook = Element.Destruction,
        isActive = true,
        level = 12,
        zCost = 30,
        effect = R.string.destructSphereDesc,
        addedEffect = R.string.destructSphereEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val increaseWeakness = Spell(
        name = R.string.weakIncrease,
        inBook = Element.Destruction,
        isActive = true,
        level = 16,
        zCost = 50,
        effect = R.string.weakIncreaseDesc,
        addedEffect = R.string.weakIncreaseEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val magicDestruction = Spell(
        name = R.string.magDestruct,
        inBook = Element.Destruction,
        isActive = false,
        level = 18,
        zCost = 60,
        effect = R.string.magDestructDesc,
        addedEffect = R.string.magDestructEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val aggravateDamage = Spell(
        name = R.string.aggravateDamage,
        inBook = Element.Destruction,
        isActive = false,
        level = 20,
        zCost = 60,
        effect = R.string.aggravateDamageDesc,
        addedEffect = R.string.aggravateDamageEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val destructMatrices = Spell(
        name = R.string.matrixDestruction,
        inBook = Element.Destruction,
        isActive = false,
        level = 22,
        zCost = 80,
        effect = R.string.matrixDestructionDesc,
        addedEffect = R.string.matrixDestructionEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val woundSpell = Spell(
        name = R.string.wound,
        inBook = Element.Destruction,
        isActive = true,
        level = 26,
        zCost = 80,
        effect = R.string.woundDesc,
        addedEffect = R.string.woundEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val destroyKi = Spell(
        name = R.string.kiDestroy,
        inBook = Element.Destruction,
        isActive = true,
        level = 28,
        zCost = 80,
        effect = R.string.kiDestroyDesc,
        addedEffect = R.string.kiDestroyEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val produceDamage = Spell(
        name = R.string.produceDamage,
        inBook = Element.Destruction,
        isActive = true,
        level = 30,
        zCost = 80,
        effect = R.string.produceDamageDesc,
        addedEffect = R.string.produceDamageEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val senseDestroy = Spell(
        name = R.string.senseDestruct,
        inBook = Element.Destruction,
        isActive = true,
        level = 32,
        zCost = 100,
        effect = R.string.senseDestructDesc,
        addedEffect = R.string.senseDestructEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val mysticBolt = Spell(
        name = R.string.mysticBolt,
        inBook = Element.Destruction,
        isActive = true,
        level = 36,
        zCost = 80,
        effect = R.string.mysticBoltDesc,
        addedEffect = R.string.mysticBoltEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val unravelTies = Spell(
        name = R.string.tieUnravel,
        inBook = Element.Destruction,
        isActive = true,
        level = 38,
        zCost = 100,
        effect = R.string.tieUnravelDesc,
        addedEffect = R.string.tieUnravelEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val destroyResistances = Spell(
        name = R.string.resistDestroy,
        inBook = Element.Destruction,
        isActive = true,
        level = 40,
        zCost = 80,
        effect = R.string.resistDestroyDesc,
        addedEffect = R.string.resistDestroyEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val undoState = Spell(
        name = R.string.undoState,
        inBook = Element.Destruction,
        isActive = true,
        level = 42,
        zCost = 120,
        effect = R.string.undoStateDesc,
        addedEffect = R.string.undoStateEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val destructionDome = Spell(
        name = R.string.destructDome,
        inBook = Element.Destruction,
        isActive = true,
        level = 46,
        zCost = 100,
        effect = R.string.destructDomeDesc,
        addedEffect = R.string.destructDomeEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val decayZone = Spell(
        name = R.string.decayZone,
        inBook = Element.Destruction,
        isActive = true,
        level = 48,
        zCost = 140,
        effect = R.string.decayZoneDesc,
        addedEffect = R.string.decayZoneEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val destructionAura = Spell(
        name = R.string.destructAura,
        inBook = Element.Destruction,
        isActive = true,
        level = 50,
        zCost = 150,
        effect = R.string.destructAuraDesc,
        addedEffect = R.string.destructAuraEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val destroyMemories = Spell(
        name = R.string.destroyMem,
        inBook = Element.Destruction,
        isActive = true,
        level = 52,
        zCost = 140,
        effect = R.string.destroyMemDesc,
        addedEffect = R.string.destroyMemEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val blockLearning = Spell(
        name = R.string.learnBlock,
        inBook = Element.Destruction,
        isActive = true,
        level = 56,
        zCost = 120,
        effect = R.string.learnBlockDesc,
        addedEffect = R.string.learnBlockEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val forbid = Spell(
        name = R.string.forbid,
        inBook = Element.Destruction,
        isActive = true,
        level = 58,
        zCost = 100,
        effect = R.string.forbidDesc,
        addedEffect = R.string.forbidEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val destroyPowers = Spell(
        name = R.string.powerDestroy,
        inBook = Element.Destruction,
        isActive = true,
        level = 60,
        zCost = 140,
        effect = R.string.powerDestroyDesc,
        addedEffect = R.string.powerDestroyEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val greatMysticBolt = Spell(
        name = R.string.greatMysticBolt,
        inBook = Element.Destruction,
        isActive = true,
        level = 62,
        zCost = 150,
        effect = R.string.greatMysticBoltDesc,
        addedEffect = R.string.greatMysticBoltEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val destroyWill = Spell(
        name = R.string.destroyWill,
        inBook = Element.Destruction,
        isActive = true,
        level = 66,
        zCost = 160,
        effect = R.string.destroyWillDesc,
        addedEffect = R.string.destroyWillEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val weaknessZone = Spell(
        name = R.string.weakZone,
        inBook = Element.Destruction,
        isActive = true,
        level = 68,
        zCost = 200,
        effect = R.string.weakZoneDesc,
        addedEffect = R.string.weakZoneEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val destructionEssence = Spell(
        name = R.string.essenceDestruct,
        inBook = Element.Destruction,
        isActive = true,
        level = 70,
        zCost = 150,
        effect = R.string.essenceDestructDesc,
        addedEffect = R.string.essenceDestructEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val deathSpell = Spell(
        name = R.string.death,
        inBook = Element.Destruction,
        isActive = true,
        level = 72,
        zCost = 200,
        effect = R.string.deathDesc,
        addedEffect = R.string.deathEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val devouringZone = Spell(
        name = R.string.devourZone,
        inBook = Element.Destruction,
        isActive = true,
        level = 76,
        zCost = 250,
        effect = R.string.devourZoneDesc,
        addedEffect = R.string.devourZoneEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val destroyCapabilities = Spell(
        name = R.string.destroyCapabilities,
        inBook = Element.Destruction,
        isActive = true,
        level = 78,
        zCost = 150,
        effect = R.string.destroyCapabilitiesDesc,
        addedEffect = R.string.destroyCapabilitiesEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val severExistence = Spell(
        name = R.string.existSever,
        inBook = Element.Destruction,
        isActive = true,
        level = 80,
        zCost = 340,
        effect = R.string.existSeverDesc,
        addedEffect = R.string.existSeverEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val destructionRain = Spell(
        name = R.string.destructRain,
        inBook = Element.Destruction,
        isActive = true,
        level = 82,
        zCost = 250,
        effect = R.string.destructRainDesc,
        addedEffect = R.string.destructRainEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val zeonDestruction = Spell(
        name = R.string.destructZeon,
        inBook = Element.Destruction,
        isActive = true,
        level = 86,
        zCost = 200,
        effect = R.string.destructZeonDesc,
        addedEffect = R.string.destructZeonEff,
        zMax = 50,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val heavenSweep = Spell(
        name = R.string.heavenSweep,
        inBook = Element.Destruction,
        isActive = true,
        level = 88,
        zCost = 300,
        effect = R.string.heavenSweepDesc,
        addedEffect = R.string.heavenSweepEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val void = Spell(
        name = R.string.voidSpell,
        inBook = Element.Destruction,
        isActive = true,
        level = 90,
        zCost = 250,
        effect = R.string.voidDesc,
        addedEffect = R.string.voidEff,
        zMax = 30,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val greaterDestruction = Spell(
        name = R.string.greatDestruction,
        inBook = Element.Destruction,
        isActive = true,
        level = 92,
        zCost = 350,
        effect = R.string.greatDestructionDesc,
        addedEffect = R.string.greatDestructionEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val destroySouls = Spell(
        name = R.string.soulDestroy,
        inBook = Element.Destruction,
        isActive = true,
        level = 96,
        zCost = 500,
        effect = R.string.soulDestroyDesc,
        addedEffect = R.string.soulDestroyEff,
        zMax = 40,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val chaos = Spell(
        name = R.string.chaos,
        inBook = Element.Destruction,
        isActive = true,
        level = 98,
        zCost = 700,
        effect = R.string.chaosDesc,
        addedEffect = R.string.chaosEff,
        zMax = 50,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val uncreation = Spell(
        name = R.string.uncreation,
        inBook = Element.Destruction,
        isActive = true,
        level = 100,
        zCost = 1000,
        effect = R.string.uncreationDesc,
        addedEffect = R.string.uncreationEff,
        zMax = 50,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    val fullBook = listOf(
        fragility,
        null,
        dismantle,
        destroyIntensities,
        minorDestruction,
        destructionSphere,
        null,
        increaseWeakness,
        magicDestruction,
        aggravateDamage,
        destructMatrices,
        null,
        woundSpell,
        destroyKi,
        produceDamage,
        senseDestroy,
        null,
        mysticBolt,
        unravelTies,
        destroyResistances,
        undoState,
        null,
        destructionDome,
        decayZone,
        destructionAura,
        destroyMemories,
        null,
        blockLearning,
        forbid,
        destroyPowers,
        greatMysticBolt,
        null,
        destroyWill,
        weaknessZone,
        destructionEssence,
        deathSpell,
        null,
        devouringZone,
        destroyCapabilities,
        severExistence,
        destructionRain,
        null,
        zeonDestruction,
        heavenSweep,
        void,
        greaterDestruction,
        null,
        destroySouls,
        chaos,
        uncreation
    )
}