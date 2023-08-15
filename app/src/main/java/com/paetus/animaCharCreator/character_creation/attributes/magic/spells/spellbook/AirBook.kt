package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the air element.
 */
class AirBook{
    private val raiseWind = Spell(
        R.string.raiseWind,
        Element.Air,
        true,
        2,
        30,
        R.string.raiseWindDesc,
        R.string.raiseWindEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val moveSpell = Spell(
        R.string.move,
        Element.Air,
        true,
        6,
        30,
        R.string.moveDesc,
        R.string.moveEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val reduceWeight = Spell(
        R.string.reduceWeight,
        Element.Air,
        true,
        10,
        40,
        R.string.reduceWeightDesc,
        R.string.reduceWeightEff,
        30,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val stopBreathing = Spell(
        R.string.stopBreathe,
        Element.Air,
        false,
        12,
        40,
        R.string.stopBreatheDesc,
        R.string.stopBreatheEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val freeMotion = Spell(
        R.string.freeMotion,
        Element.Air,
        true,
        16,
        50,
        R.string.freeMotionDesc,
        R.string.freeMotionEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val airBlow = Spell(
        R.string.airBlow,
        Element.Air,
        true,
        20,
        40,
        R.string.airBlowDesc,
        R.string.airBlowEff,
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val airScreen = Spell(
        R.string.airScreen,
        Element.Air,
        false,
        22,
        50,
        R.string.airScreenDesc,
        R.string.airScreenEff,
        20,
        10,
        false,
        listOf(SpellType.Defense)
    )

    private val autoTransport = Spell(
        R.string.autoTransport,
        Element.Air,
        true,
        26,
        50,
        R.string.autoTransportDesc,
        R.string.autoTransportEff,
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val flight = Spell(
        R.string.flightSpell,
        Element.Air,
        true,
        30,
        60,
        R.string.flightSpellDesc,
        R.string.flightSpellEff,
        10,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val reactionIncrease = Spell(
        R.string.increaseReaction,
        Element.Air,
        true,
        32,
        60,
        R.string.increaseReactionDesc,
        R.string.increaseReactionEff,
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val electrify = Spell(
        R.string.electrify,
        Element.Air,
        true,
        36,
        80,
        R.string.electrifyDesc,
        R.string.electrifyEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val airCut = Spell(
        R.string.airCut,
        Element.Air,
        true,
        40,
        60,
        R.string.airCutDesc,
        R.string.airCutEff,
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val speedSpell = Spell(
        R.string.speed,
        Element.Air,
        true,
        42,
        80,
        R.string.speedDesc,
        R.string.speedEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val lightning = Spell(
        R.string.lightning,
        Element.Air,
        true,
        46,
        80,
        R.string.lightningDesc,
        R.string.lightningEff,
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val whirlwind = Spell(
        R.string.whirlwind,
        Element.Air,
        true,
        50,
        140,
        R.string.whirlwindDesc,
        R.string.whirlwindEff,
        20,
        5,
        false,
        listOf(SpellType.Automatic)
    )

    private val etherealForm = Spell(
        R.string.etherForm,
        Element.Air,
        true,
        52,
        100,
        R.string.etherFormDesc,
        R.string.etherFormEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val airControl = Spell(
        R.string.airControl,
        Element.Air,
        true,
        56,
        80,
        R.string.airControlDesc,
        R.string.airControlEff,
        20,
        10,
        false,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val electricityControl = Spell(
        R.string.controlElec,
        Element.Air,
        true,
        60,
        80,
        R.string.controlElecDesc,
        R.string.controlElecEff,
        20,
        10,
        false,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val defensiveMovement = Spell(
        R.string.defenseMove,
        Element.Air,
        false,
        62,
        120,
        R.string.defenseMoveDesc,
        R.string.defenseMoveEff,
        20,
        10,
        false,
        listOf(SpellType.Defense)
    )

    private val teletransportation = Spell(
        R.string.teleTransport,
        Element.Air,
        true,
        66,
        150,
        R.string.teleTransportDesc,
        R.string.teleTransportEff,
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val immateriality = Spell(
        R.string.immaterial,
        Element.Air,
        true,
        70,
        120,
        R.string.immaterialDesc,
        R.string.immaterialEff,
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val hurricane = Spell(
        R.string.hurricane,
        Element.Air,
        true,
        72,
        200,
        R.string.hurricaneDesc,
        R.string.hurricaneEff,
        30,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val solidAir = Spell(
        R.string.solidAir,
        Element.Air,
        true,
        76,
        140,
        R.string.solidAirDesc,
        R.string.solidAirEff,
        20,
        20,
        false,
        listOf(SpellType.Effect, SpellType.Attack)
    )

    private val weatherControl = Spell(
        R.string.weatherControl,
        Element.Air,
        true,
        80,
        220,
        R.string.weatherControlDesc,
        R.string.weatherControlEff,
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val createSylph = Spell(
        R.string.createSylph,
        Element.Air,
        true,
        82,
        250,
        R.string.createSylphDesc,
        R.string.createSylphEff,
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val superiorPsychokinesis = Spell(
        R.string.superPsychokinesis,
        Element.Air,
        true,
        86,
        160,
        R.string.superPsychokinesisDesc,
        R.string.superPsychokinesisEff,
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val relocateMagic = Spell(
        R.string.magRelocate,
        Element.Air,
        true,
        90,
        180,
        R.string.magRelocateDesc,
        R.string.magRelocateEff,
        30,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val passiveMagic = Spell(
        R.string.passiveMag,
        Element.Air,
        true,
        92,
        300,
        R.string.passiveMagDesc,
        R.string.passiveMagEff,
        30,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val airLord = Spell(
        R.string.airLord,
        Element.Air,
        true,
        96,
        300,
        R.string.airLordDesc,
        R.string.airLordEff,
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val placeInTheWorld = Spell(
        R.string.placeInWorld,
        Element.Air,
        true,
        100,
        450,
        R.string.placeInWorldDesc,
        R.string.placeInWorldEff,
        40,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    val fullBook = listOf(
        raiseWind,
        null,
        moveSpell,
        null,
        reduceWeight,
        stopBreathing,
        null,
        freeMotion,
        null,
        airBlow,
        airScreen,
        null,
        autoTransport,
        null,
        flight,
        reactionIncrease,
        null,
        electrify,
        null,
        airCut,
        speedSpell,
        null,
        lightning,
        null,
        whirlwind,
        etherealForm,
        null,
        airControl,
        null,
        electricityControl,
        defensiveMovement,
        null,
        teletransportation,
        null,
        immateriality,
        hurricane,
        null,
        solidAir,
        null,
        weatherControl,
        createSylph,
        null,
        superiorPsychokinesis,
        null,
        relocateMagic,
        passiveMagic,
        null,
        airLord,
        null,
        placeInTheWorld
    )
}