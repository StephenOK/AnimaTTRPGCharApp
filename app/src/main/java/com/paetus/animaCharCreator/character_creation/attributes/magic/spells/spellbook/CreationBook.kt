package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the creation element.
 */
class CreationBook{
    private val minorCreation = Spell(
        R.string.minorCreation,
        Element.Creation,
        true,
        2,
        30,
        R.string.minorCreationDesc,
        R.string.minorCreationEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val reconstruct = Spell(
        R.string.reconstruct,
        Element.Creation,
        true,
        6,
        40,
        R.string.reconstructDesc,
        R.string.reconstructEff,
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val createEnergy = Spell(
        R.string.createEnergy,
        Element.Creation,
        true,
        8,
        40,
        R.string.createEnergyDesc,
        R.string.createEnergyEff,
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val regeneration = Spell(
        R.string.regeneration,
        Element.Creation,
        true,
        10,
        60,
        R.string.regenerationDesc,
        R.string.regenerationEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val inorganicModification = Spell(
        R.string.inorgMod,
        Element.Creation,
        true,
        12,
        60,
        R.string.inorgModDesc,
        R.string.inorgModEff,
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val increaseResistances = Spell(
        R.string.resIncrease,
        Element.Creation,
        true,
        16,
        80,
        R.string.resIncreaseDesc,
        R.string.resIncreaseEff,
        10,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val royalShield = Spell(
        R.string.royalShield,
        Element.Creation,
        false,
        18,
        40,
        R.string.royalShieldDesc,
        R.string.royalShieldEff,
        30,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val heal = Spell(
        R.string.heal,
        Element.Creation,
        true,
        20,
        80,
        R.string.healDesc,
        R.string.healEff,
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val damageBarrier = Spell(
        R.string.damageBarrier,
        Element.Creation,
        true,
        22,
        60,
        R.string.damageBarrierDesc,
        R.string.damageBarrierEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val homunculus = Spell(
        R.string.homunculus,
        Element.Creation,
        true,
        26,
        60,
        R.string.homunculusDesc,
        R.string.homunculusEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val minorChange = Spell(
        R.string.minorChange,
        Element.Creation,
        true,
        28,
        60,
        R.string.minorChangeDesc,
        R.string.minorChangeEff,
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val imitate = Spell(
        R.string.imitate,
        Element.Creation,
        true,
        30,
        100,
        R.string.imitateDesc,
        R.string.imitateEff,
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val immunity = Spell(
        R.string.immunity,
        Element.Creation,
        true,
        32,
        80,
        R.string.immunityDesc,
        R.string.immunityEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val reduceDamage = Spell(
        R.string.reduceDamage,
        Element.Creation,
        false,
        36,
        80,
        R.string.reduceDamageDesc,
        R.string.reduceDamageEff,
        10,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    private val physicalControl = Spell(
        R.string.controlPhys,
        Element.Creation,
        true,
        38,
        120,
        R.string.controlPhysDesc,
        R.string.controlPhysEff,
        20,
        5,
        true,
        listOf(SpellType.Spiritual)
    )

    private val raiseAbilities = Spell(
        R.string.raiseAbilities,
        Element.Creation,
        true,
        40,
        80,
        R.string.raiseAbilitiesDesc,
        R.string.raiseAbilitiesEff,
        20,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val fuse = Spell(
        R.string.fuse,
        Element.Creation,
        true,
        42,
        140,
        R.string.fuseDesc,
        R.string.fuseEff,
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val createMemories = Spell(
        R.string.createMem,
        Element.Creation,
        true,
        46,
        140,
        R.string.createMemDesc,
        R.string.createMemEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val recover = Spell(
        R.string.recover,
        Element.Creation,
        true,
        48,
        150,
        R.string.recoverDesc,
        R.string.recoverEff,
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val acquirePowers = Spell(
        R.string.acquirePowers,
        Element.Creation,
        true,
        50,
        100,
        R.string.acquirePowersDesc,
        R.string.acquirePowersEff,
        20,
        5,
        false,
        listOf(SpellType.Effect)
    )

    private val createMonstrosity = Spell(
        R.string.createMonster,
        Element.Creation,
        true,
        52,
        80,
        R.string.createMonsterDesc,
        R.string.createMonsterEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val protectiveAura = Spell(
        R.string.protectAura,
        Element.Creation,
        false,
        56,
        120,
        R.string.protectAuraDesc,
        R.string.protectAuraEff,
        30,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val spiritualStandstill = Spell(
        R.string.spiritStandstill,
        Element.Creation,
        true,
        58,
        150,
        R.string.spiritStandstillDesc,
        R.string.spiritStandstillEff,
        20,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val perfectShield = Spell(
        R.string.perfShield,
        Element.Creation,
        true,
        60,
        150,
        R.string.perfShieldDesc,
        R.string.perfShieldEff,
        20,
        10,
        true,
        listOf(SpellType.Defense)
    )

    private val vitality = Spell(
        R.string.vitality,
        Element.Creation,
        true,
        62,
        150,
        R.string.vitalityDesc, R.string.vitalityEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val completeCreation = Spell(
        R.string.completeCreation,
        Element.Creation,
        true,
        66,
        150,
        R.string.completeCreationDesc,
        R.string.completeCreationEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val reinforceMagic = Spell(
        R.string.magReinforce,
        Element.Creation,
        false,
        68,
        100,
        R.string.magReinforceDesc,
        R.string.magReinforceEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val transmute = Spell(
        R.string.transmute,
        Element.Creation,
        true,
        70,
        250,
        R.string.transmuteDesc,
        R.string.transmuteEff,
        30,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val metamorphism = Spell(
        R.string.metamorph,
        Element.Creation,
        true,
        72,
        150,
        R.string.metamorphDesc,
        R.string.metamorphEff,
        20,
        20,
        true,
        listOf(SpellType.Spiritual)
    )

    private val recreate = Spell(
        R.string.recreate,
        Element.Creation,
        true,
        76,
        300,
        R.string.recreateDesc,
        R.string.recreateEff,
        40,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val createBeing = Spell(
        R.string.createBeing,
        Element.Creation,
        true,
        78,
        250,
        R.string.createBeingDesc,
        R.string.createBeingEff,
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val chimera = Spell(
        R.string.chimera,
        Element.Creation,
        true,
        80,
        250,
        R.string.chimeraDesc,
        R.string.chimeraEff,
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val safetyZone = Spell(
        R.string.safeZone,
        Element.Creation,
        true,
        82,
        350,
        R.string.safeZoneDesc,
        R.string.safeZoneEff,
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val maintainMagic = Spell(
        R.string.magMaintain,
        Element.Creation,
        true,
        86,
        250,
        R.string.magMaintainDesc,
        R.string.magMaintainEff,
        40,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val provideSoul = Spell(
        R.string.provideSoul,
        Element.Creation,
        true,
        88,
        500,
        R.string.provideSoulDesc,
        R.string.provideSoulEff,
        40,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val greaterCreation = Spell(
        R.string.greatCreation,
        Element.Creation,
        true,
        90,
        400,
        R.string.greatCreationDesc,
        R.string.greatCreationEff,
        40,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val eternalMagic = Spell(
        R.string.magEternal,
        Element.Creation,
        true,
        92,
        600,
        R.string.magEternalDesc,
        R.string.magEternalEff,
        50,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val barrierSpell = Spell(
        R.string.barrier,
        Element.Creation,
        true,
        96,
        800,
        R.string.barrierDesc,
        R.string.barrierEff,
        50,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val giftOfLife = Spell(
        R.string.lifeGift,
        Element.Creation,
        true,
        98,
        800,
        R.string.lifeGiftDesc,
        R.string.lifeGiftEff,
        50,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val createSpell = Spell(
        R.string.createSpell,
        Element.Creation,
        true,
        100,
        1000,
        R.string.createSpellDesc,
        R.string.createSpellEff,
        50,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val fullBook = listOf(
        minorCreation,
        null,
        reconstruct,
        createEnergy,
        regeneration,
        inorganicModification,
        null,
        increaseResistances,
        royalShield,
        heal,
        damageBarrier,
        null,
        homunculus,
        minorChange,
        imitate,
        immunity,
        null,
        reduceDamage,
        physicalControl,
        raiseAbilities,
        fuse,
        null,
        createMemories,
        recover,
        acquirePowers,
        createMonstrosity,
        null,
        protectiveAura,
        spiritualStandstill,
        perfectShield,
        vitality,
        null,
        completeCreation,
        reinforceMagic,
        transmute,
        metamorphism,
        null,
        recreate,
        createBeing,
        chimera,
        safetyZone,
        null,
        maintainMagic,
        provideSoul,
        greaterCreation,
        eternalMagic,
        null,
        barrierSpell,
        giftOfLife,
        createSpell
    )
}