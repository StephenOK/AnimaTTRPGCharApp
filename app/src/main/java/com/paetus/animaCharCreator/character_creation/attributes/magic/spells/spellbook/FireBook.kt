package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the fire element.
 */
class FireBook{
    private val createFire = Spell(
        R.string.createFire,
        Element.Fire,
        true,
        2,
        30,
        R.string.createFireDesc,
        R.string.createFireEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val putOutFire = Spell(
        R.string.snuffFire,
        Element.Fire,
        true,
        6,
        30,
        R.string.snuffFireDesc,
        R.string.snuffFireEff,
        10,
        null,
        false,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val fireImmunity = Spell(
        R.string.fireImmune,
        Element.Fire,
        true,
        10,
        50,
        R.string.fireImmuneDesc,
        R.string.fireImmuneEff,
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val detectHeat = Spell(
        R.string.heatDetect,
        Element.Fire,
        true,
        12,
        60,
        R.string.heatDetectDesc,
        R.string.heatDetectEff,
        10,
        10,
        false,
        listOf(SpellType.Detection)
    )

    private val fireBall = Spell(
        R.string.fireball,
        Element.Fire,
        true,
        16,
        50,
        R.string.fireballDesc,
        R.string.fireballEff,
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val controlFire = Spell(
        R.string.controlFire,
        Element.Fire,
        true,
        20,
        50,
        R.string.controlFireDesc,
        R.string.controlFireEff,
        10,
        10,
        true,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val fireBarrier = Spell(
        R.string.fireBarrier,
        Element.Fire,
        true,
        22,
        50,
        R.string.fireBarrierDesc,
        R.string.fireBarrierEff,
        10,
        10,
        false,
        listOf(SpellType.Automatic, SpellType.Defense)
    )

    private val igneousWeapon = Spell(
        R.string.igneousWeapon,
        Element.Fire,
        true,
        26,
        50,
        R.string.igneousWeaponDesc,
        R.string.igneousWeaponEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val heatWave = Spell(
        R.string.heatWave,
        Element.Fire,
        true,
        30,
        60,
        R.string.heatWaveDesc,
        R.string.heatWaveEff,
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val readAshes = Spell(
        R.string.readAsh,
        Element.Fire,
        true,
        32,
        60,
        R.string.readAshDesc,
        R.string.readAshEff,
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val raiseTemperature = Spell(
        R.string.raiseTemp,
        Element.Fire,
        true,
        36,
        60,
        R.string.raiseTempDesc,
        R.string.raiseTempEff,
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val fireMine = Spell(
        R.string.fireMine,
        Element.Fire,
        true,
        40,
        80,
        R.string.fireMineDesc,
        R.string.fireMineEff,
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val increaseCritical = Spell(
        R.string.increaseCrit,
        Element.Fire,
        false,
        42,
        60,
        R.string.increaseCritDesc,
        R.string.increaseCritEff,
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val dry = Spell(
        R.string.dry,
        Element.Fire,
        true,
        46,
        80,
        R.string.dryDesc,
        R.string.dryEff,
        10,
        null,
        false,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val melt = Spell(
        R.string.melt,
        Element.Fire,
        true,
        50,
        80,
        R.string.meltDesc,
        R.string.meltEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val fireBody = Spell(
        R.string.fireBody,
        Element.Fire,
        true,
        52,
        100,
        R.string.fireBodyDesc,
        R.string.fireBodyEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val vitalSacrifice = Spell(
        R.string.vitalSac,
        Element.Fire,
        true,
        56,
        120,
        R.string.vitalSacDesc,
        R.string.vitalSacEff,
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val incinerate = Spell(
        R.string.incinerate,
        Element.Fire,
        true,
        60,
        100,
        R.string.incinerateDesc,
        R.string.incinerateEff,
        10,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val consumeEssence = Spell(
        R.string.consumeEssence,
        Element.Fire,
        true,
        62,
        120,
        R.string.consumeEssenceDesc,
        R.string.consumeEssenceEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val powerSacrifice = Spell(
        R.string.powerSac,
        Element.Fire,
        true,
        66,
        120,
        R.string.powerSacDesc,
        R.string.powerSacEff,
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val directCritical = Spell(
        R.string.directCrit,
        Element.Fire,
        true,
        70,
        100,
        R.string.directCritDesc,
        R.string.directCritEff,
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val magicCapacities = Spell(
        R.string.magicCapacities,
        Element.Fire,
        true,
        72,
        120,
        R.string.magicCapacitiesDesc,
        R.string.magicCapacitiesEff,
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val fireStorm = Spell(
        R.string.fireStorm,
        Element.Fire,
        true,
        76,
        150,
        R.string.fireStormDesc,
        R.string.fireStormEff,
        20,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val lifeForMagic = Spell(
        R.string.lifeMagic,
        Element.Fire,
        true,
        80,
        120,
        R.string.lifeMagicDesc,
        R.string.lifeMagicEff,
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val createIfreet = Spell(
        R.string.createIfreet,
        Element.Fire,
        true,
        82,
        250,
        R.string.createIfreetDesc,
        R.string.createIfreetEff,
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val pyre = Spell(
        R.string.pyre,
        Element.Fire,
        true,
        86,
        250,
        R.string.pyreDesc,
        R.string.pyreEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val devastation = Spell(
        R.string.devastation,
        Element.Fire,
        true,
        90,
        200,
        R.string.devastationDesc,
        R.string.devastationEff,
        30,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val sacrificingOthers = Spell(
        R.string.sacOthers,
        Element.Fire,
        true,
        92,
        250,
        R.string.sacOthersDesc,
        R.string.sacOthersEff,
        20,
        5,
        true,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val lordOfFire = Spell(
        R.string.fireLord,
        Element.Fire,
        true,
        96,
        300,
        R.string.fireLordDesc,
        R.string.fireLordEff,
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val armageddon = Spell(
        R.string.armageddon,
        Element.Fire,
        true,
        100,
        450,
        R.string.armageddonDesc,
        R.string.armageddonEff,
        30,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    val fullBook = listOf(
        createFire,
        null,
        putOutFire,
        null,
        fireImmunity,
        detectHeat,
        null,
        fireBall,
        null,
        controlFire,
        fireBarrier,
        null,
        igneousWeapon,
        null,
        heatWave,
        readAshes,
        null,
        raiseTemperature,
        null,
        fireMine,
        increaseCritical,
        null,
        dry,
        null,
        melt,
        fireBody,
        null,
        vitalSacrifice,
        null,
        incinerate,
        consumeEssence,
        null,
        powerSacrifice,
        null,
        directCritical,
        magicCapacities,
        null,
        fireStorm,
        null,
        lifeForMagic,
        createIfreet,
        null,
        pyre,
        null,
        devastation,
        sacrificingOthers,
        null,
        lordOfFire,
        null,
        armageddon
    )
}