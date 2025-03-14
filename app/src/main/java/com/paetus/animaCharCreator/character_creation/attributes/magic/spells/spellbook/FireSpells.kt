package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellList
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.enumerations.SpellType

/**
 * List of spells associated with the fire element.
 */
class FireSpells: SpellList(Element.Fire){
    private val createFire = Spell(
        name = R.string.createFire,
        isActive = true,
        level = 2,
        zCost = 30,
        effect = R.string.createFireDesc,
        addedEffect = R.string.createFireEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val putOutFire = Spell(
        name = R.string.snuffFire,
        isActive = true,
        level = 6,
        zCost = 30,
        effect = R.string.snuffFireDesc,
        addedEffect = R.string.snuffFireEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val fireImmunity = Spell(
        name = R.string.fireImmune,
        isActive = true,
        level = 10,
        zCost = 50,
        effect = R.string.fireImmuneDesc,
        addedEffect = R.string.fireImmuneEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val detectHeat = Spell(
        name = R.string.heatDetect,
        isActive = true,
        level = 12,
        zCost = 60,
        effect = R.string.heatDetectDesc,
        addedEffect = R.string.heatDetectEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Detection)
    )

    private val fireBall = Spell(
        name = R.string.fireball,
        isActive = true,
        level = 16,
        zCost = 50,
        effect = R.string.fireballDesc,
        addedEffect = R.string.fireballEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val controlFire = Spell(
        name = R.string.controlFire,
        isActive = true,
        level = 20,
        zCost = 50,
        effect = R.string.controlFireDesc,
        addedEffect = R.string.controlFireEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val fireBarrier = Spell(
        name = R.string.fireBarrier,
        isActive = true,
        level = 22,
        zCost = 50,
        effect = R.string.fireBarrierDesc,
        addedEffect = R.string.fireBarrierEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic, SpellType.Defense)
    )

    private val igneousWeapon = Spell(
        name = R.string.igneousWeapon,
        isActive = true,
        level = 26,
        zCost = 50,
        effect = R.string.igneousWeaponDesc,
        addedEffect = R.string.igneousWeaponEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val heatWave = Spell(
        name = R.string.heatWave,
        isActive = true,
        level = 30,
        zCost = 60,
        effect = R.string.heatWaveDesc,
        addedEffect = R.string.heatWaveEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val readAshes = Spell(
        name = R.string.readAsh,
        isActive = true,
        level = 32,
        zCost = 60,
        effect = R.string.readAshDesc,
        addedEffect = R.string.readAshEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val raiseTemperature = Spell(
        name = R.string.raiseTemp,
        isActive = true,
        level = 36,
        zCost = 60,
        effect = R.string.raiseTempDesc,
        addedEffect = R.string.raiseTempEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val fireMine = Spell(
        name = R.string.fireMine,
        isActive = true,
        level = 40,
        zCost = 80,
        effect = R.string.fireMineDesc,
        addedEffect = R.string.fireMineEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val increaseCritical = Spell(
        name = R.string.increaseCrit,
        isActive = false,
        level = 42,
        zCost = 60,
        effect = R.string.increaseCritDesc,
        addedEffect = R.string.increaseCritEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val dry = Spell(
        name = R.string.dry,
        isActive = true,
        level = 46,
        zCost = 80,
        effect = R.string.dryDesc,
        addedEffect = R.string.dryEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val melt = Spell(
        name = R.string.melt,
        isActive = true,
        level = 50,
        zCost = 80,
        effect = R.string.meltDesc,
        addedEffect = R.string.meltEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val fireBody = Spell(
        name = R.string.fireBody,
        isActive = true,
        level = 52,
        zCost = 100,
        effect = R.string.fireBodyDesc,
        addedEffect = R.string.fireBodyEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val vitalSacrifice = Spell(
        name = R.string.vitalSac,
        isActive = true,
        level = 56,
        zCost = 120,
        effect = R.string.vitalSacDesc,
        addedEffect = R.string.vitalSacEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val incinerate = Spell(
        name = R.string.incinerate,
        isActive = true,
        level = 60,
        zCost = 100,
        effect = R.string.incinerateDesc,
        addedEffect = R.string.incinerateEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val consumeEssence = Spell(
        name = R.string.consumeEssence,
        isActive = true,
        level = 62,
        zCost = 120,
        effect = R.string.consumeEssenceDesc,
        addedEffect = R.string.consumeEssenceEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val powerSacrifice = Spell(
        name = R.string.powerSac,
        isActive = true,
        level = 66,
        zCost = 120,
        effect = R.string.powerSacDesc,
        addedEffect = R.string.powerSacEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val directCritical = Spell(
        name = R.string.directCrit,
        isActive = true,
        level = 70,
        zCost = 100,
        effect = R.string.directCritDesc,
        addedEffect = R.string.directCritEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val magicCapacities = Spell(
        name = R.string.magicCapacities,
        isActive = true,
        level = 72,
        zCost = 120,
        effect = R.string.magicCapacitiesDesc,
        addedEffect = R.string.magicCapacitiesEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val fireStorm = Spell(
        name = R.string.fireStorm,
        isActive = true,
        level = 76,
        zCost = 150,
        effect = R.string.fireStormDesc,
        addedEffect = R.string.fireStormEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val lifeForMagic = Spell(
        name = R.string.lifeMagic,
        isActive = true,
        level = 80,
        zCost = 120,
        effect = R.string.lifeMagicDesc,
        addedEffect = R.string.lifeMagicEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val createIfreet = Spell(
        name = R.string.createIfreet,
        isActive = true,
        level = 82,
        zCost = 250,
        effect = R.string.createIfreetDesc,
        addedEffect = R.string.createIfreetEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val pyre = Spell(
        name = R.string.pyre,
        isActive = true,
        level = 86,
        zCost = 250,
        effect = R.string.pyreDesc,
        addedEffect = R.string.pyreEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val devastation = Spell(
        name = R.string.devastation,
        isActive = true,
        level = 90,
        zCost = 200,
        effect = R.string.devastationDesc,
        addedEffect = R.string.devastationEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val sacrificingOthers = Spell(
        name = R.string.sacOthers,
        isActive = true,
        level = 92,
        zCost = 250,
        effect = R.string.sacOthersDesc,
        addedEffect = R.string.sacOthersEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val lordOfFire = Spell(
        name = R.string.fireLord,
        isActive = true,
        level = 96,
        zCost = 300,
        effect = R.string.fireLordDesc,
        addedEffect = R.string.fireLordEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val armageddon = Spell(
        name = R.string.armageddon,
        isActive = true,
        level = 100,
        zCost = 450,
        effect = R.string.armageddonDesc,
        addedEffect = R.string.armageddonEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    init{
        fullBook.addAll(elements = listOf(
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
        ))
    }
}