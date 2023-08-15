package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the earth element.
 */
class EarthBook{
    private val detectMinerals = Spell(
        R.string.detectMinerals,
        Element.Earth,
        true,
        2,
        20,
        R.string.detectMineralsDesc,
        R.string.detectMineralsEff,
        10,
        null,
        false,
        listOf(SpellType.Detection)
    )

    private val mineralControl = Spell(
        R.string.controlMineral,
        Element.Earth,
        true,
        6,
        30,
        R.string.controlMineralDesc,
        R.string.controlMineralEff,
        10,
        10,
        true,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val weightIncrement = Spell(
        R.string.weightIncrement,
        Element.Earth,
        true,
        10,
        40,
        R.string.weightIncrementDesc,
        R.string.weightIncrementEff,
        30,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val transformMineral = Spell(
        R.string.transMineral,
        Element.Earth,
        false,
        12,
        40,
        R.string.transMineralDesc,
        R.string.transMineralEff,
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val firmness = Spell(
        R.string.firmness,
        Element.Earth,
        true,
        16,
        50,
        R.string.firmnessDesc,
        R.string.firmnessEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val stoneBarrier = Spell(
        R.string.stoneBarrier,
        Element.Earth,
        false,
        20,
        60,
        R.string.stoneBarrierDesc,
        R.string.stoneBarrierEff,
        20,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val slowness = Spell(
        R.string.slowness,
        Element.Earth,
        true,
        22,
        60,
        R.string.slownessDesc,
        R.string.slownessEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val shell = Spell(
        R.string.shell,
        Element.Earth,
        true,
        26,
        80,
        R.string.shellDesc,
        R.string.shellEff,
        10,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val magneticShield = Spell(
        R.string.magnetShield,
        Element.Earth,
        false,
        30,
        50,
        R.string.magnetShieldDesc,
        R.string.magnetShieldEff,
        10,
        10,
        false,
        listOf(SpellType.Defense)
    )

    private val passThroughMatter = Spell(
        R.string.passMatter,
        Element.Earth,
        true,
        32,
        80,
        R.string.passMatterDesc,
        R.string.passMatterEff,
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val earthSpike = Spell(
        R.string.earthSpike,
        Element.Earth,
        true,
        36,
        80,
        R.string.earthSpikeDesc,
        R.string.earthSpikeEff,
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val breakage = Spell(
        R.string.breakage,
        Element.Earth,
        true,
        40,
        60,
        R.string.breakageDesc,
        R.string.breakageEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val telemetry = Spell(
        R.string.telemetry,
        Element.Earth,
        true,
        42,
        120,
        R.string.telemetryDesc,
        R.string.telemetryEff,
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val magneticControl = Spell(
        R.string.magnetControl,
        Element.Earth,
        true,
        46,
        100,
        R.string.magnetControlDesc,
        R.string.magnetControlEff,
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val forge = Spell(
        R.string.forge,
        Element.Earth,
        true,
        50,
        160,
        R.string.forgeDesc,
        R.string.forgeEff,
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val solidBody = Spell(
        R.string.solidBody,
        Element.Earth,
        true,
        52,
        100,
        R.string.solidBodyDesc,
        R.string.solidBodyEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val resistanceSpell = Spell(
        R.string.resistance,
        Element.Earth,
        true,
        56,
        100,
        R.string.resistanceDesc,
        R.string.resistanceEff,
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val petrify = Spell(
        R.string.petrify,
        Element.Earth,
        true,
        60,
        140,
        R.string.petrifyDesc,
        R.string.petrifyEff,
        20,
        20,
        true,
        listOf(SpellType.Spiritual)
    )

    private val fissure = Spell(
        R.string.fissure,
        Element.Earth,
        true,
        62,
        150,
        R.string.fissureDesc,
        R.string.fissureEff,
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val reverseGravity = Spell(
        R.string.gravReverse,
        Element.Earth,
        true,
        66,
        200,
        R.string.gravReverseDesc,
        R.string.gravReverseEff,
        20,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val mineralCreation = Spell(
        R.string.createMineral,
        Element.Earth,
        true,
        70,
        120,
        R.string.createMineralDesc,
        R.string.createMineralEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val terrainErudition = Spell(
        R.string.terraErudition,
        Element.Earth,
        true,
        72,
        120,
        R.string.terraEruditionDesc,
        R.string.terraEruditionEff,
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val earthquake = Spell(
        R.string.earthquake,
        Element.Earth,
        true,
        76,
        150,
        R.string.earthquakeDesc,
        R.string.earthquakeEff,
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val gravityDestruction = Spell(
        R.string.gravDestruct,
        Element.Earth,
        true,
        80,
        180,
        R.string.gravDestructDesc,
        R.string.gravDestructEff,
        20,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val createGolem = Spell(
        R.string.createGolem,
        Element.Earth,
        true,
        82,
        250,
        R.string.createGolemDesc,
        R.string.createGolemEff,
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val gravityIncrement = Spell(
        R.string.incrementGrav,
        Element.Earth,
        true,
        86,
        200,
        R.string.incrementGravDesc,
        R.string.incrementGravEff,
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val meteor = Spell(
        R.string.meteor,
        Element.Earth,
        true,
        90,
        200,
        R.string.meteorDesc,
        R.string.meteorEff,
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val gravityControl = Spell(
        R.string.controlGrav,
        Element.Earth,
        true,
        92,
        350,
        R.string.controlGravDesc,
        R.string.controlGravEff,
        40,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val oneWithEarth = Spell(
        R.string.oneWithEarth,
        Element.Earth,
        true,
        96,
        300,
        R.string.oneWithEarthDesc,
        R.string.oneWithEarthEff,
        30,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val atomicControl = Spell(
        R.string.atomicControl,
        Element.Earth,
        true,
        100,
        450,
        R.string.atomicControlDesc,
        R.string.atomicControlEff,
        40,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    val fullBook = listOf(
        detectMinerals,
        null,
        mineralControl,
        null,
        weightIncrement,
        transformMineral,
        null,
        firmness,
        null,
        stoneBarrier,
        slowness,
        null,
        shell,
        null,
        magneticShield,
        passThroughMatter,
        null,
        earthSpike,
        null,
        breakage,
        telemetry,
        null,
        magneticControl,
        null,
        forge,
        solidBody,
        null,
        resistanceSpell,
        null,
        petrify,
        fissure,
        null,
        reverseGravity,
        null,
        mineralCreation,
        terrainErudition,
        null,
        earthquake,
        null,
        gravityDestruction,
        createGolem,
        null,
        gravityIncrement,
        null,
        meteor,
        gravityControl,
        null,
        oneWithEarth,
        null,
        atomicControl
    )
}