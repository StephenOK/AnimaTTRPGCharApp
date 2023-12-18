package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.MagicBook
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.enumerations.SpellType

/**
 * List of spells associated with the air element.
 */
class AirBook: MagicBook(Element.Air) {
    private val raiseWind = Spell(
        name = R.string.raiseWind,
        isActive = true,
        level = 2,
        zCost = 30,
        effect = R.string.raiseWindDesc,
        addedEffect = R.string.raiseWindEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val moveSpell = Spell(
        name = R.string.move,
        isActive = true,
        level = 6,
        zCost = 30,
        effect = R.string.moveDesc,
        addedEffect = R.string.moveEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val reduceWeight = Spell(
        name = R.string.reduceWeight,
        isActive = true,
        level = 10,
        zCost = 40,
        effect = R.string.reduceWeightDesc,
        addedEffect = R.string.reduceWeightEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val stopBreathing = Spell(
        name = R.string.stopBreathe,
        isActive = false,
        level = 12,
        zCost = 40,
        effect = R.string.stopBreatheDesc,
        addedEffect = R.string.stopBreatheEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val freeMotion = Spell(
        name = R.string.freeMotion,
        isActive = true,
        level = 16,
        zCost = 50,
        effect = R.string.freeMotionDesc,
        addedEffect = R.string.freeMotionEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val airBlow = Spell(
        name = R.string.airBlow,
        isActive = true,
        level = 20,
        zCost = 40,
        effect = R.string.airBlowDesc,
        addedEffect = R.string.airBlowEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val airScreen = Spell(
        name = R.string.airScreen,
        isActive = false,
        level = 22,
        zCost = 50,
        effect = R.string.airScreenDesc,
        addedEffect = R.string.airScreenEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Defense)
    )

    private val autoTransport = Spell(
        name = R.string.autoTransport,
        isActive = true,
        level = 26,
        zCost = 50,
        effect = R.string.autoTransportDesc,
        addedEffect = R.string.autoTransportEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val flight = Spell(
        name = R.string.flightSpell,
        isActive = true,
        level = 30,
        zCost = 60,
        effect = R.string.flightSpellDesc,
        addedEffect = R.string.flightSpellEff,
        zMax = 10,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val reactionIncrease = Spell(
        name = R.string.increaseReaction,
        isActive = true,
        level = 32,
        zCost = 60,
        effect = R.string.increaseReactionDesc,
        addedEffect = R.string.increaseReactionEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val electrify = Spell(
        name = R.string.electrify,
        isActive = true,
        level = 36,
        zCost = 80,
        effect = R.string.electrifyDesc,
        addedEffect = R.string.electrifyEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val airCut = Spell(
        name = R.string.airCut,
        isActive = true,
        level = 40,
        zCost = 60,
        effect = R.string.airCutDesc,
        addedEffect = R.string.airCutEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val speedSpell = Spell(
        name = R.string.speed,
        isActive = true,
        level = 42,
        zCost = 80,
        effect = R.string.speedDesc,
        addedEffect = R.string.speedEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val lightning = Spell(
        name = R.string.lightning,
        isActive = true,
        level = 46,
        zCost = 80,
        effect = R.string.lightningDesc,
        addedEffect = R.string.lightningEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val whirlwind = Spell(
        name = R.string.whirlwind,
        isActive = true,
        level = 50,
        zCost = 140,
        effect = R.string.whirlwindDesc,
        addedEffect = R.string.whirlwindEff,
        zMax = 20,
        maintenance = 5,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val etherealForm = Spell(
        name = R.string.etherForm,
        isActive = true,
        level = 52,
        zCost = 100,
        effect = R.string.etherFormDesc,
        addedEffect = R.string.etherFormEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val airControl = Spell(
        name = R.string.airControl,
        isActive = true,
        level = 56,
        zCost = 80,
        effect = R.string.airControlDesc,
        addedEffect = R.string.airControlEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val electricityControl = Spell(
        name = R.string.controlElec,
        isActive = true,
        level = 60,
        zCost = 80,
        effect = R.string.controlElecDesc,
        addedEffect = R.string.controlElecEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val defensiveMovement = Spell(
        name = R.string.defenseMove,
        isActive = false,
        level = 62,
        zCost = 120,
        effect = R.string.defenseMoveDesc,
        addedEffect = R.string.defenseMoveEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Defense)
    )

    private val teletransportation = Spell(
        name = R.string.teleTransport,
        isActive = true,
        level = 66,
        zCost = 150,
        effect = R.string.teleTransportDesc,
        addedEffect = R.string.teleTransportEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val immateriality = Spell(
        name = R.string.immaterial,
        isActive = true,
        level = 70,
        zCost = 120,
        effect = R.string.immaterialDesc,
        addedEffect = R.string.immaterialEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val hurricane = Spell(
        name = R.string.hurricane,
        isActive = true,
        level = 72,
        zCost = 200,
        effect = R.string.hurricaneDesc,
        addedEffect = R.string.hurricaneEff,
        zMax = 30,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val solidAir = Spell(
        name = R.string.solidAir,
        isActive = true,
        level = 76,
        zCost = 140,
        effect = R.string.solidAirDesc,
        addedEffect = R.string.solidAirEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect, SpellType.Attack)
    )

    private val weatherControl = Spell(
        name = R.string.weatherControl,
        isActive = true,
        level = 80,
        zCost = 220,
        effect = R.string.weatherControlDesc,
        addedEffect = R.string.weatherControlEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val createSylph = Spell(
        name = R.string.createSylph,
        isActive = true,
        level = 82,
        zCost = 250,
        effect = R.string.createSylphDesc,
        addedEffect = R.string.createSylphEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val superiorPsychokinesis = Spell(
        name = R.string.superPsychokinesis,
        isActive = true,
        level = 86,
        zCost = 160,
        effect = R.string.superPsychokinesisDesc,
        addedEffect = R.string.superPsychokinesisEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val relocateMagic = Spell(
        name = R.string.magRelocate,
        isActive = true,
        level = 90,
        zCost = 180,
        effect = R.string.magRelocateDesc,
        addedEffect = R.string.magRelocateEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val passiveMagic = Spell(
        name = R.string.passiveMag,
        isActive = true,
        level = 92,
        zCost = 300,
        effect = R.string.passiveMagDesc,
        addedEffect = R.string.passiveMagEff,
        zMax = 30,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val airLord = Spell(
        name = R.string.airLord,
        isActive = true,
        level = 96,
        zCost = 300,
        effect = R.string.airLordDesc,
        addedEffect = R.string.airLordEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val placeInTheWorld = Spell(
        name = R.string.placeInWorld,
        isActive = true,
        level = 100,
        zCost = 450,
        effect = R.string.placeInWorldDesc,
        addedEffect = R.string.placeInWorldEff,
        zMax = 40,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    init{
        fullBook.addAll(elements = listOf(
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
        ))
    }
}