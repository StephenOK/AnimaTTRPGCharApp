package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the water element.
 */
class WaterBook{
    private val spring = Spell(
        R.string.spring,
        Element.Water,
        true,
        2,
        30,
        R.string.springDesc,
        R.string.springEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val createChill = Spell(
        R.string.createChill,
        Element.Water,
        true,
        6,
        30,
        R.string.createChillDesc,
        R.string.createChillEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val aquaticCapability = Spell(
        R.string.aquaCapable,
        Element.Water,
        true,
        10,
        50,
        R.string.aquaCapableDesc,
        R.string.aquaCapableEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val coldImmunity = Spell(
        R.string.coldImmune,
        Element.Water,
        true,
        12,
        50,
        R.string.coldImmuneDesc,
        R.string.coldImmuneEff,
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val protectionBubble = Spell(
        R.string.protectionBubble,
        Element.Water,
        false,
        16,
        40,
        R.string.protectionBubbleDesc,
        R.string.protectionBubbleEff,
        10,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val waterImpact = Spell(
        R.string.waterImpact,
        Element.Water,
        true,
        20,
        50,
        R.string.waterImpactDesc,
        R.string.waterImpactEff,
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val liquidControl = Spell(
        R.string.controlLiquid,
        Element.Water,
        true,
        22,
        60,
        R.string.controlLiquidDesc,
        R.string.controlLiquidEff,
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val freezeEmotions = Spell(
        R.string.freezeEmote,
        Element.Water,
        true,
        26,
        60,
        R.string.freezeEmoteDesc,
        R.string.freezeEmoteEff,
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val controlCold = Spell(
        R.string.controlCold,
        Element.Water,
        true,
        30,
        50,
        R.string.controlColdDesc,
        R.string.controlColdEff,
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val freeze = Spell(
        R.string.freeze,
        Element.Water,
        true,
        32,
        60,
        R.string.freezeDesc,
        R.string.freezeEff,
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val iceScreen = Spell(
        R.string.iceScreen,
        Element.Water,
        false,
        36,
        60,
        R.string.iceScreenDesc,
        R.string.iceScreenEff,
        20,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val createLiquids = Spell(
        R.string.createLiquid,
        Element.Water,
        true,
        40,
        80,
        R.string.createLiquidDesc,
        R.string.createLiquidEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val iceAttack = Spell(
        R.string.iceAttack,
        Element.Water,
        true,
        42,
        80,
        R.string.iceAttackDesc,
        R.string.iceAttackEff,
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val crystallization = Spell(
        R.string.crystallization,
        Element.Water,
        true,
        46,
        80,
        R.string.crystallizationDesc,
        R.string.crystallizationEff,
        10,
        20,
        false,
        listOf(SpellType.Spiritual)
    )

    private val reflectedControl = Spell(
        R.string.reflectedControl,
        Element.Water,
        true,
        50,
        80,
        R.string.reflectedControlDesc,
        R.string.reflectedControlEff,
        20,
        20,
        false,
        listOf(SpellType.Spiritual)
    )

    private val liquidBody = Spell(
        R.string.liquidBody,
        Element.Water,
        true,
        52,
        100,
        R.string.liquidBodyDesc,
        R.string.liquidBodyEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val reflectStates = Spell(
        R.string.stateReflect,
        Element.Water,
        false,
        56,
        120,
        R.string.stateReflectDesc,
        R.string.stateReflectEff,
        20,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    private val iceStorm = Spell(
        R.string.iceStorm,
        Element.Water,
        true,
        60,
        120,
        R.string.iceStormDesc,
        R.string.iceStormEff,
        20,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val tideControl = Spell(
        R.string.tideControl,
        Element.Water,
        true,
        62,
        150,
        R.string.tideControlDesc,
        R.string.tideControlEff,
        40,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val waterConfinement = Spell(
        R.string.waterConfine,
        Element.Water,
        true,
        66,
        140,
        R.string.waterConfineDesc,
        R.string.waterConfineEff,
        20,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val glacier = Spell(
        R.string.glacier,
        Element.Water,
        true,
        70,
        200,
        R.string.glacierDesc,
        R.string.glacierEff,
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val tsunami = Spell(
        R.string.tsunami,
        Element.Water,
        true,
        72,
        250,
        R.string.tsunamiDesc,
        R.string.tsunamiEff,
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val soulReflection = Spell(
        R.string.reflectSoul,
        Element.Water,
        true,
        76,
        200,
        R.string.reflectSoulDesc,
        R.string.reflectSoulEff,
        20,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val slowTime = Spell(
        R.string.slowTime,
        Element.Water,
        true,
        80,
        200,
        R.string.slowTimeDesc,
        R.string.slowTimeEff,
        20,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val createUndine = Spell(
        R.string.createUndine,
        Element.Water,
        true,
        82,
        250,
        R.string.createUndineDesc,
        R.string.createUndineEff,
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val freezeMagic = Spell(
        R.string.freezeMag,
        Element.Water,
        false,
        86,
        250,
        R.string.freezeMagDesc,
        R.string.freezeMagEff,
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val inTheMirror = Spell(
        R.string.inMirror,
        Element.Water,
        true,
        90,
        300,
        R.string.inMirrorDesc,
        R.string.inMirrorEff,
        40,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val lordOfIce = Spell(
        R.string.iceLord,
        Element.Water,
        true,
        92,
        300,
        R.string.iceLordDesc,
        R.string.iceLordEff,
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val lordOfWater = Spell(
        R.string.waterLord,
        Element.Water,
        true,
        96,
        300,
        R.string.waterLordDesc,
        R.string.waterLordEff,
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val perfectWorld = Spell(
        R.string.perfectWorld,
        Element.Water,
        true,
        100,
        450,
        R.string.perfectWorldDesc,
        R.string.perfectWorldEff,
        40,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    val fullBook = listOf(
        spring,
        null,
        createChill,
        null,
        aquaticCapability,
        coldImmunity,
        null,
        protectionBubble,
        null,
        waterImpact,
        liquidControl,
        null,
        freezeEmotions,
        null,
        controlCold,
        freeze,
        null,
        iceScreen,
        null,
        createLiquids,
        iceAttack,
        null,
        crystallization,
        null,
        reflectedControl,
        liquidBody,
        null,
        reflectStates,
        null,
        iceStorm,
        tideControl,
        null,
        waterConfinement,
        null,
        glacier,
        tsunami,
        null,
        soulReflection,
        null,
        slowTime,
        createUndine,
        null,
        freezeMagic,
        null,
        inTheMirror,
        lordOfIce,
        null,
        lordOfWater,
        null,
        perfectWorld
    )
}