package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.MagicBook
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.enumerations.SpellType

/**
 * List of spells associated with the water element.
 */
class WaterBook: MagicBook(Element.Water){
    private val spring = Spell(
        name = R.string.spring,
        isActive = true,
        level = 2,
        zCost = 30,
        effect = R.string.springDesc,
        addedEffect = R.string.springEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val createChill = Spell(
        name = R.string.createChill,
        isActive = true,
        level = 6,
        zCost = 30,
        effect = R.string.createChillDesc,
        addedEffect = R.string.createChillEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val aquaticCapability = Spell(
        name = R.string.aquaCapable,
        isActive = true,
        level = 10,
        zCost = 50,
        effect = R.string.aquaCapableDesc,
        addedEffect = R.string.aquaCapableEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val coldImmunity = Spell(
        name = R.string.coldImmune,
        isActive = true,
        level = 12,
        zCost = 50,
        effect = R.string.coldImmuneDesc,
        addedEffect = R.string.coldImmuneEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val protectionBubble = Spell(
        name = R.string.protectionBubble,
        isActive = false,
        level = 16,
        zCost = 40,
        effect = R.string.protectionBubbleDesc,
        addedEffect = R.string.protectionBubbleEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Defense)
    )

    private val waterImpact = Spell(
        name = R.string.waterImpact,
        isActive = true,
        level = 20,
        zCost = 50,
        effect = R.string.waterImpactDesc,
        addedEffect = R.string.waterImpactEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val liquidControl = Spell(
        name = R.string.controlLiquid,
        isActive = true,
        level = 22,
        zCost = 60,
        effect = R.string.controlLiquidDesc,
        addedEffect = R.string.controlLiquidEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val freezeEmotions = Spell(
        name = R.string.freezeEmote,
        isActive = true,
        level = 26,
        zCost = 60,
        effect = R.string.freezeEmoteDesc,
        addedEffect = R.string.freezeEmoteEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val controlCold = Spell(
        name = R.string.controlCold,
        isActive = true,
        level = 30,
        zCost = 50,
        effect = R.string.controlColdDesc,
        addedEffect = R.string.controlColdEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val freeze = Spell(
        name = R.string.freeze,
        isActive = true,
        level = 32,
        zCost = 60,
        effect = R.string.freezeDesc,
        addedEffect = R.string.freezeEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val iceScreen = Spell(
        name = R.string.iceScreen,
        isActive = false,
        level = 36,
        zCost = 60,
        effect = R.string.iceScreenDesc,
        addedEffect = R.string.iceScreenEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Defense)
    )

    private val createLiquids = Spell(
        name = R.string.createLiquid,
        isActive = true,
        level = 40,
        zCost = 80,
        effect = R.string.createLiquidDesc,
        addedEffect = R.string.createLiquidEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val iceAttack = Spell(
        name = R.string.iceAttack,
        isActive = true,
        level = 42,
        zCost = 80,
        effect = R.string.iceAttackDesc,
        addedEffect = R.string.iceAttackEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val crystallization = Spell(
        name = R.string.crystallization,
        isActive = true,
        level = 46,
        zCost = 80,
        effect = R.string.crystallizationDesc,
        addedEffect = R.string.crystallizationEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val reflectedControl = Spell(
        name = R.string.reflectedControl,
        isActive = true,
        level = 50,
        zCost = 80,
        effect = R.string.reflectedControlDesc,
        addedEffect = R.string.reflectedControlEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val liquidBody = Spell(
        name = R.string.liquidBody,
        isActive = true,
        level = 52,
        zCost = 100,
        effect = R.string.liquidBodyDesc,
        addedEffect = R.string.liquidBodyEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val reflectStates = Spell(
        name = R.string.stateReflect,
        isActive = false,
        level = 56,
        zCost = 120,
        effect = R.string.stateReflectDesc,
        addedEffect = R.string.stateReflectEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val iceStorm = Spell(
        name = R.string.iceStorm,
        isActive = true,
        level = 60,
        zCost = 120,
        effect = R.string.iceStormDesc,
        addedEffect = R.string.iceStormEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val tideControl = Spell(
        name = R.string.tideControl,
        isActive = true,
        level = 62,
        zCost = 150,
        effect = R.string.tideControlDesc,
        addedEffect = R.string.tideControlEff,
        zMax = 40,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val waterConfinement = Spell(
        name = R.string.waterConfine,
        isActive = true,
        level = 66,
        zCost = 140,
        effect = R.string.waterConfineDesc,
        addedEffect = R.string.waterConfineEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val glacier = Spell(
        name = R.string.glacier,
        isActive = true,
        level = 70,
        zCost = 200,
        effect = R.string.glacierDesc,
        addedEffect = R.string.glacierEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val tsunami = Spell(
        name = R.string.tsunami,
        isActive = true,
        level = 72,
        zCost = 250,
        effect = R.string.tsunamiDesc,
        addedEffect = R.string.tsunamiEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val soulReflection = Spell(
        name = R.string.reflectSoul,
        isActive = true,
        level = 76,
        zCost = 200,
        effect = R.string.reflectSoulDesc,
        addedEffect = R.string.reflectSoulEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val slowTime = Spell(
        name = R.string.slowTime,
        isActive = true,
        level = 80,
        zCost = 200,
        effect = R.string.slowTimeDesc,
        addedEffect = R.string.slowTimeEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val createUndine = Spell(
        name = R.string.createUndine,
        isActive = true,
        level = 82,
        zCost = 250,
        effect = R.string.createUndineDesc,
        addedEffect = R.string.createUndineEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val freezeMagic = Spell(
        name = R.string.freezeMag,
        isActive = false,
        level = 86,
        zCost = 250,
        effect = R.string.freezeMagDesc,
        addedEffect = R.string.freezeMagEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val inTheMirror = Spell(
        name = R.string.inMirror,
        isActive = true,
        level = 90,
        zCost = 300,
        effect = R.string.inMirrorDesc,
        addedEffect = R.string.inMirrorEff,
        zMax = 40,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val lordOfIce = Spell(
        name = R.string.iceLord,
        isActive = true,
        level = 92,
        zCost = 300,
        effect = R.string.iceLordDesc,
        addedEffect = R.string.iceLordEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val lordOfWater = Spell(
        name = R.string.waterLord,
        isActive = true,
        level = 96,
        zCost = 300,
        effect = R.string.waterLordDesc,
        addedEffect = R.string.waterLordEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val perfectWorld = Spell(
        name = R.string.perfectWorld,
        isActive = true,
        level = 100,
        zCost = 450,
        effect = R.string.perfectWorldDesc,
        addedEffect = R.string.perfectWorldEff,
        zMax = 40,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    init{
        fullBook.addAll(elements = listOf(
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
        ))
    }
}