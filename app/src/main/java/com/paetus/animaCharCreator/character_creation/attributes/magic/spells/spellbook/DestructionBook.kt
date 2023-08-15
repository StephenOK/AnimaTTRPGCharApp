package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the destruction element.
 */
class DestructionBook{
    private val fragility = Spell(
        R.string.fragility,
        Element.Destruction,
        true,
        2,
        30,
        R.string.fragilityDesc,
        R.string.fragilityEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val dismantle = Spell(
        R.string.dismantle,
        Element.Destruction,
        true,
        6,
        40,
        R.string.dismantleDesc,
        R.string.dismantleEff,
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val destroyIntensities = Spell(
        R.string.destroyIntense,
        Element.Destruction,
        true,
        8,
        40,
        R.string.destroyIntenseDesc,
        R.string.destroyIntenseEff,
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val minorDestruction = Spell(
        R.string.minorDestruct,
        Element.Destruction,
        true,
        10,
        50,
        R.string.minorDestructDesc,
        R.string.minorDestructEff,
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val destructionSphere = Spell(
        R.string.destructSphere,
        Element.Destruction,
        true,
        12,
        30,
        R.string.destructSphereDesc,
        R.string.destructSphereEff,
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val increaseWeakness = Spell(
        R.string.weakIncrease,
        Element.Destruction,
        true,
        16,
        50,
        R.string.weakIncreaseDesc,
        R.string.weakIncreaseEff,
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val magicDestruction = Spell(
        R.string.magDestruct,
        Element.Destruction,
        false,
        18,
        60,
        R.string.magDestructDesc,
        R.string.magDestructEff,
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val aggravateDamage = Spell(
        R.string.aggravateDamage,
        Element.Destruction,
        false,
        20,
        60,
        R.string.aggravateDamageDesc,
        R.string.aggravateDamageEff,
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val destructMatrices = Spell(
        R.string.matrixDestruction,
        Element.Destruction,
        false,
        22,
        80,
        R.string.matrixDestructionDesc,
        R.string.matrixDestructionEff,
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val woundSpell = Spell(
        R.string.wound,
        Element.Destruction,
        true,
        26,
        80,
        R.string.woundDesc,
        R.string.woundEff,
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val destroyKi = Spell(
        R.string.kiDestroy,
        Element.Destruction,
        true,
        28,
        80,
        R.string.kiDestroyDesc,
        R.string.kiDestroyEff,
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val produceDamage = Spell(
        R.string.produceDamage,
        Element.Destruction,
        true,
        30,
        80,
        R.string.produceDamageDesc,
        R.string.produceDamageEff,
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val senseDestroy = Spell(
        R.string.senseDestruct,
        Element.Destruction,
        true,
        32,
        100,
        R.string.senseDestructDesc,
        R.string.senseDestructEff,
        10,
        20,
        false,
        listOf(SpellType.Spiritual)
    )

    private val mysticBolt = Spell(
        R.string.mysticBolt,
        Element.Destruction,
        true,
        36,
        80,
        R.string.mysticBoltDesc,
        R.string.mysticBoltEff,
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val unravelTies = Spell(
        R.string.tieUnravel,
        Element.Destruction,
        true,
        38,
        100,
        R.string.tieUnravelDesc,
        R.string.tieUnravelEff,
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val destroyResistances = Spell(
        R.string.resistDestroy,
        Element.Destruction,
        true,
        40,
        80,
        R.string.resistDestroyDesc,
        R.string.resistDestroyEff,
        20,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val undoState = Spell(
        R.string.undoState,
        Element.Destruction,
        true,
        42,
        120,
        R.string.undoStateDesc,
        R.string.undoStateEff,
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val destructionDome = Spell(
        R.string.destructDome,
        Element.Destruction,
        true,
        46,
        100,
        R.string.destructDomeDesc,
        R.string.destructDomeEff,
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val decayZone = Spell(
        R.string.decayZone,
        Element.Destruction,
        true,
        48,
        140,
        R.string.decayZoneDesc,
        R.string.decayZoneEff,
        20,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val destructionAura = Spell(
        R.string.destructAura,
        Element.Destruction,
        true,
        50,
        150,
        R.string.destructAuraDesc,
        R.string.destructAuraEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val destroyMemories = Spell(
        R.string.destroyMem,
        Element.Destruction,
        true,
        52,
        140,
        R.string.destroyMemDesc,
        R.string.destroyMemEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val blockLearning = Spell(
        R.string.learnBlock,
        Element.Destruction,
        true,
        56,
        120,
        R.string.learnBlockDesc,
        R.string.learnBlockEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val forbid = Spell(
        R.string.forbid,
        Element.Destruction,
        true,
        58,
        100,
        R.string.forbidDesc,
        R.string.forbidEff,
        20,
        20,
        false,
        listOf(SpellType.Spiritual)
    )

    private val destroyPowers = Spell(
        R.string.powerDestroy,
        Element.Destruction,
        true,
        60,
        140,
        R.string.powerDestroyDesc,
        R.string.powerDestroyEff,
        20,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val greatMysticBolt = Spell(
        R.string.greatMysticBolt,
        Element.Destruction,
        true,
        62,
        150,
        R.string.greatMysticBoltDesc,
        R.string.greatMysticBoltEff,
        30,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val destroyWill = Spell(
        R.string.destroyWill,
        Element.Destruction,
        true,
        66,
        160,
        R.string.destroyWillDesc,
        R.string.destroyWillEff,
        20,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val weaknessZone = Spell(
        R.string.weakZone,
        Element.Destruction,
        true,
        68,
        200,
        R.string.weakZoneDesc,
        R.string.weakZoneEff,
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val destructionEssence = Spell(
        R.string.essenceDestruct,
        Element.Destruction,
        true,
        70,
        150,
        R.string.essenceDestructDesc,
        R.string.essenceDestructEff,
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val deathSpell = Spell(
        R.string.death,
        Element.Destruction,
        true,
        72,
        200,
        R.string.deathDesc,
        R.string.deathEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val devouringZone = Spell(
        R.string.devourZone,
        Element.Destruction,
        true,
        76,
        250,
        R.string.devourZoneDesc,
        R.string.devourZoneEff,
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val destroyCapabilities = Spell(
        R.string.destroyCapabilities,
        Element.Destruction,
        true,
        78,
        150,
        R.string.destroyCapabilitiesDesc,
        R.string.destroyCapabilitiesEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val severExistence = Spell(
        R.string.existSever,
        Element.Destruction,
        true,
        80,
        340,
        R.string.existSeverDesc,
        R.string.existSeverEff,
        30,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val destructionRain = Spell(
        R.string.destructRain,
        Element.Destruction,
        true,
        82,
        250,
        R.string.destructRainDesc,
        R.string.destructRainEff,
        30,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val zeonDestruction = Spell(
        R.string.destructZeon,
        Element.Destruction,
        true,
        86,
        200,
        R.string.destructZeonDesc,
        R.string.destructZeonEff,
        50,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val heavenSweep = Spell(
        R.string.heavenSweep,
        Element.Destruction,
        true,
        88,
        300,
        R.string.heavenSweepDesc,
        R.string.heavenSweepEff,
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val void = Spell(
        R.string.voidSpell,
        Element.Destruction,
        true,
        90,
        250,
        R.string.voidDesc,
        R.string.voidEff,
        30,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val greaterDestruction = Spell(
        R.string.greatDestruction,
        Element.Destruction,
        true,
        92,
        350,
        R.string.greatDestructionDesc,
        R.string.greatDestructionEff,
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val destroySouls = Spell(
        R.string.soulDestroy,
        Element.Destruction,
        true,
        96,
        500,
        R.string.soulDestroyDesc,
        R.string.soulDestroyEff,
        40,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    private val chaos = Spell(
        R.string.chaos,
        Element.Destruction,
        true,
        98,
        700,
        R.string.chaosDesc,
        R.string.chaosEff,
        50,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val uncreation = Spell(
        R.string.uncreation,
        Element.Destruction,
        true,
        100,
        1000,
        R.string.uncreationDesc,
        R.string.uncreationEff,
        50,
        null,
        false,
        listOf(SpellType.Automatic)
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