package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.enumerations.SpellType

/**
 * List of spells associated with the earth element.
 */
class EarthBook{
    private val detectMinerals = Spell(
        name = R.string.detectMinerals,
        inBook = Element.Earth,
        isActive = true,
        level = 2,
        zCost = 20,
        effect = R.string.detectMineralsDesc,
        addedEffect = R.string.detectMineralsEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Detection)
    )

    private val mineralControl = Spell(
        name = R.string.controlMineral,
        inBook = Element.Earth,
        isActive = true,
        level = 6,
        zCost = 30,
        effect = R.string.controlMineralDesc,
        addedEffect = R.string.controlMineralEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val weightIncrement = Spell(
        name = R.string.weightIncrement,
        inBook = Element.Earth,
        isActive = true,
        level = 10,
        zCost = 40,
        effect = R.string.weightIncrementDesc,
        addedEffect = R.string.weightIncrementEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val transformMineral = Spell(
        name = R.string.transMineral,
        inBook = Element.Earth,
        isActive = false,
        level = 12,
        zCost = 40,
        effect = R.string.transMineralDesc,
        addedEffect = R.string.transMineralEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val firmness = Spell(
        name = R.string.firmness,
        inBook = Element.Earth,
        isActive = true,
        level = 16,
        zCost = 50,
        effect = R.string.firmnessDesc,
        addedEffect = R.string.firmnessEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val stoneBarrier = Spell(
        name = R.string.stoneBarrier,
        inBook = Element.Earth,
        isActive = false,
        level = 20,
        zCost = 60,
        effect = R.string.stoneBarrierDesc,
        addedEffect = R.string.stoneBarrierEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Defense)
    )

    private val slowness = Spell(
        name = R.string.slowness,
        inBook = Element.Earth,
        isActive = true,
        level = 22,
        zCost = 60,
        effect = R.string.slownessDesc,
        addedEffect = R.string.slownessEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val shell = Spell(
        name = R.string.shell,
        inBook = Element.Earth,
        isActive = true,
        level = 26,
        zCost = 80,
        effect = R.string.shellDesc,
        addedEffect = R.string.shellEff,
        zMax = 10,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val magneticShield = Spell(
        name = R.string.magnetShield,
        inBook = Element.Earth,
        isActive = false,
        level = 30,
        zCost = 50,
        effect = R.string.magnetShieldDesc,
        addedEffect = R.string.magnetShieldEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Defense)
    )

    private val passThroughMatter = Spell(
        name = R.string.passMatter,
        inBook = Element.Earth,
        isActive = true,
        level = 32,
        zCost = 80,
        effect = R.string.passMatterDesc,
        addedEffect = R.string.passMatterEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val earthSpike = Spell(
        name = R.string.earthSpike,
        inBook = Element.Earth,
        isActive = true,
        level = 36,
        zCost = 80,
        effect = R.string.earthSpikeDesc,
        addedEffect = R.string.earthSpikeEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val breakage = Spell(
        name = R.string.breakage,
        inBook = Element.Earth,
        isActive = true,
        level = 40,
        zCost = 60,
        effect = R.string.breakageDesc,
        addedEffect = R.string.breakageEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val telemetry = Spell(
        name = R.string.telemetry,
        inBook = Element.Earth,
        isActive = true,
        level = 42,
        zCost = 120,
        effect = R.string.telemetryDesc,
        addedEffect = R.string.telemetryEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val magneticControl = Spell(
        name = R.string.magnetControl,
        inBook = Element.Earth,
        isActive = true,
        level = 46,
        zCost = 100,
        effect = R.string.magnetControlDesc,
        addedEffect = R.string.magnetControlEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val forge = Spell(
        name = R.string.forge,
        inBook = Element.Earth,
        isActive = true,
        level = 50,
        zCost = 160,
        effect = R.string.forgeDesc,
        addedEffect = R.string.forgeEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val solidBody = Spell(
        name = R.string.solidBody,
        inBook = Element.Earth,
        isActive = true,
        level = 52,
        zCost = 100,
        effect = R.string.solidBodyDesc,
        addedEffect = R.string.solidBodyEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val resistanceSpell = Spell(
        name = R.string.resistance,
        inBook = Element.Earth,
        isActive = true,
        level = 56,
        zCost = 100,
        effect = R.string.resistanceDesc,
        addedEffect = R.string.resistanceEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val petrify = Spell(
        name = R.string.petrify,
        inBook = Element.Earth,
        isActive = true,
        level = 60,
        zCost = 140,
        effect = R.string.petrifyDesc,
        addedEffect = R.string.petrifyEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val fissure = Spell(
        name = R.string.fissure,
        inBook = Element.Earth,
        isActive = true,
        level = 62,
        zCost = 150,
        effect = R.string.fissureDesc,
        addedEffect = R.string.fissureEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val reverseGravity = Spell(
        name = R.string.gravReverse,
        inBook = Element.Earth,
        isActive = true,
        level = 66,
        zCost = 200,
        effect = R.string.gravReverseDesc,
        addedEffect = R.string.gravReverseEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val mineralCreation = Spell(
        name = R.string.createMineral,
        inBook = Element.Earth,
        isActive = true,
        level = 70,
        zCost = 120,
        effect = R.string.createMineralDesc,
        addedEffect = R.string.createMineralEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val terrainErudition = Spell(
        name = R.string.terraErudition,
        inBook = Element.Earth,
        isActive = true,
        level = 72,
        zCost = 120,
        effect = R.string.terraEruditionDesc,
        addedEffect = R.string.terraEruditionEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val earthquake = Spell(
        name = R.string.earthquake,
        inBook = Element.Earth,
        isActive = true,
        level = 76,
        zCost = 150,
        effect = R.string.earthquakeDesc,
        addedEffect = R.string.earthquakeEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val gravityDestruction = Spell(
        name = R.string.gravDestruct,
        inBook = Element.Earth,
        isActive = true,
        level = 80,
        zCost = 180,
        effect = R.string.gravDestructDesc,
        addedEffect = R.string.gravDestructEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val createGolem = Spell(
        name = R.string.createGolem,
        inBook = Element.Earth,
        isActive = true,
        level = 82,
        zCost = 250,
        effect = R.string.createGolemDesc,
        addedEffect = R.string.createGolemEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val gravityIncrement = Spell(
        name = R.string.incrementGrav,
        inBook = Element.Earth,
        isActive = true,
        level = 86,
        zCost = 200,
        effect = R.string.incrementGravDesc,
        addedEffect = R.string.incrementGravEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val meteor = Spell(
        name = R.string.meteor,
        inBook = Element.Earth,
        isActive = true,
        level = 90,
        zCost = 200,
        effect = R.string.meteorDesc,
        addedEffect = R.string.meteorEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val gravityControl = Spell(
        name = R.string.controlGrav,
        inBook = Element.Earth,
        isActive = true,
        level = 92,
        zCost = 350,
        effect = R.string.controlGravDesc,
        addedEffect = R.string.controlGravEff,
        zMax = 40,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val oneWithEarth = Spell(
        name = R.string.oneWithEarth,
        inBook = Element.Earth,
        isActive = true,
        level = 96,
        zCost = 300,
        effect = R.string.oneWithEarthDesc,
        addedEffect = R.string.oneWithEarthEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val atomicControl = Spell(
        name = R.string.atomicControl,
        inBook = Element.Earth,
        isActive = true,
        level = 100,
        zCost = 450,
        effect = R.string.atomicControlDesc,
        addedEffect = R.string.atomicControlEff,
        zMax = 40,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic)
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