package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.MagicBook
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.enumerations.SpellType

/**
 * List of spells associated with the creation element.
 */
class CreationBook: MagicBook(Element.Creation){
    private val minorCreation = Spell(
        name = R.string.minorCreation,
        isActive = true,
        level = 2,
        zCost = 30,
        effect = R.string.minorCreationDesc,
        addedEffect = R.string.minorCreationEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val reconstruct = Spell(
        name = R.string.reconstruct,
        isActive = true,
        level = 6,
        zCost = 40,
        effect = R.string.reconstructDesc,
        addedEffect = R.string.reconstructEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val createEnergy = Spell(
        name = R.string.createEnergy,
        isActive = true,
        level = 8,
        zCost = 40,
        effect = R.string.createEnergyDesc,
        addedEffect = R.string.createEnergyEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val regeneration = Spell(
        name = R.string.regeneration,
        isActive = true,
        level = 10,
        zCost = 60,
        effect = R.string.regenerationDesc,
        addedEffect = R.string.regenerationEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val inorganicModification = Spell(
        name = R.string.inorgMod,
        isActive = true,
        level = 12,
        zCost = 60,
        effect = R.string.inorgModDesc,
        addedEffect = R.string.inorgModEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val increaseResistances = Spell(
        name = R.string.resIncrease,
        isActive = true,
        level = 16,
        zCost = 80,
        effect = R.string.resIncreaseDesc,
        addedEffect = R.string.resIncreaseEff,
        zMax = 10,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val royalShield = Spell(
        name = R.string.royalShield,
        isActive = false,
        level = 18,
        zCost = 40,
        effect = R.string.royalShieldDesc,
        addedEffect = R.string.royalShieldEff,
        zMax = 30,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Defense)
    )

    private val heal = Spell(
        name = R.string.heal,
        isActive = true,
        level = 20,
        zCost = 80,
        effect = R.string.healDesc,
        addedEffect = R.string.healEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val damageBarrier = Spell(
        name = R.string.damageBarrier,
        isActive = true,
        level = 22,
        zCost = 60,
        effect = R.string.damageBarrierDesc,
        addedEffect = R.string.damageBarrierEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val homunculus = Spell(
        name = R.string.homunculus,
        isActive = true,
        level = 26,
        zCost = 60,
        effect = R.string.homunculusDesc,
        addedEffect = R.string.homunculusEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val minorChange = Spell(
        name = R.string.minorChange,
        isActive = true,
        level = 28,
        zCost = 60,
        effect = R.string.minorChangeDesc,
        addedEffect = R.string.minorChangeEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val imitate = Spell(
        name = R.string.imitate,
        isActive = true,
        level = 30,
        zCost = 100,
        effect = R.string.imitateDesc,
        addedEffect = R.string.imitateEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val immunity = Spell(
        name = R.string.immunity,
        isActive = true,
        level = 32,
        zCost = 80,
        effect = R.string.immunityDesc,
        addedEffect = R.string.immunityEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val reduceDamage = Spell(
        name = R.string.reduceDamage,
        isActive = false,
        level = 36,
        zCost = 80,
        effect = R.string.reduceDamageDesc,
        addedEffect = R.string.reduceDamageEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val physicalControl = Spell(
        name = R.string.controlPhys,
        isActive = true,
        level = 38,
        zCost = 120,
        effect = R.string.controlPhysDesc,
        addedEffect = R.string.controlPhysEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val raiseAbilities = Spell(
        name = R.string.raiseAbilities,
        isActive = true,
        level = 40,
        zCost = 80,
        effect = R.string.raiseAbilitiesDesc,
        addedEffect = R.string.raiseAbilitiesEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val fuse = Spell(
        name = R.string.fuse,
        isActive = true,
        level = 42,
        zCost = 140,
        effect = R.string.fuseDesc,
        addedEffect = R.string.fuseEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val createMemories = Spell(
        name = R.string.createMem,
        isActive = true,
        level = 46,
        zCost = 140,
        effect = R.string.createMemDesc,
        addedEffect = R.string.createMemEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val recover = Spell(
        name = R.string.recover,
        isActive = true,
        level = 48,
        zCost = 150,
        effect = R.string.recoverDesc,
        addedEffect = R.string.recoverEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val acquirePowers = Spell(
        name = R.string.acquirePowers,
        isActive = true,
        level = 50,
        zCost = 100,
        effect = R.string.acquirePowersDesc,
        addedEffect = R.string.acquirePowersEff,
        zMax = 20,
        maintenance = 5,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val createMonstrosity = Spell(
        name = R.string.createMonster,
        isActive = true,
        level = 52,
        zCost = 80,
        effect = R.string.createMonsterDesc,
        addedEffect = R.string.createMonsterEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val protectiveAura = Spell(
        name = R.string.protectAura,
        isActive = false,
        level = 56,
        zCost = 120,
        effect = R.string.protectAuraDesc,
        addedEffect = R.string.protectAuraEff,
        zMax = 30,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val spiritualStandstill = Spell(
        name = R.string.spiritStandstill,
        isActive = true,
        level = 58,
        zCost = 150,
        effect = R.string.spiritStandstillDesc,
        addedEffect = R.string.spiritStandstillEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val perfectShield = Spell(
        name = R.string.perfShield,
        isActive = true,
        level = 60,
        zCost = 150,
        effect = R.string.perfShieldDesc,
        addedEffect = R.string.perfShieldEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Defense)
    )

    private val vitality = Spell(
        name = R.string.vitality,
        isActive = true,
        level = 62,
        zCost = 150,
        effect = R.string.vitalityDesc,
        addedEffect = R.string.vitalityEff,
        zMax = 20, maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val completeCreation = Spell(
        name = R.string.completeCreation,
        isActive = true,
        level = 66,
        zCost = 150,
        effect = R.string.completeCreationDesc,
        addedEffect = R.string.completeCreationEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val reinforceMagic = Spell(
        name = R.string.magReinforce,
        isActive = false,
        level = 68,
        zCost = 100,
        effect = R.string.magReinforceDesc,
        addedEffect = R.string.magReinforceEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val transmute = Spell(
        name = R.string.transmute,
        isActive = true,
        level = 70,
        zCost = 250,
        effect = R.string.transmuteDesc,
        addedEffect = R.string.transmuteEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val metamorphism = Spell(
        name = R.string.metamorph,
        isActive = true,
        level = 72,
        zCost = 150,
        effect = R.string.metamorphDesc,
        addedEffect = R.string.metamorphEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val recreate = Spell(
        name = R.string.recreate,
        isActive = true,
        level = 76,
        zCost = 300,
        effect = R.string.recreateDesc,
        addedEffect = R.string.recreateEff,
        zMax = 40,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val createBeing = Spell(
        name = R.string.createBeing,
        isActive = true,
        level = 78,
        zCost = 250,
        effect = R.string.createBeingDesc,
        addedEffect = R.string.createBeingEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val chimera = Spell(
        name = R.string.chimera,
        isActive = true,
        level = 80,
        zCost = 250,
        effect = R.string.chimeraDesc,
        addedEffect = R.string.chimeraEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val safetyZone = Spell(
        name = R.string.safeZone,
        isActive = true,
        level = 82,
        zCost = 350,
        effect = R.string.safeZoneDesc,
        addedEffect = R.string.safeZoneEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val maintainMagic = Spell(
        name = R.string.magMaintain,
        isActive = true,
        level = 86,
        zCost = 250,
        effect = R.string.magMaintainDesc,
        addedEffect = R.string.magMaintainEff,
        zMax = 40,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val provideSoul = Spell(
        name = R.string.provideSoul,
        isActive = true,
        level = 88,
        zCost = 500,
        effect = R.string.provideSoulDesc,
        addedEffect = R.string.provideSoulEff,
        zMax = 40,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val greaterCreation = Spell(
        name = R.string.greatCreation,
        isActive = true,
        level = 90,
        zCost = 400,
        effect = R.string.greatCreationDesc,
        addedEffect = R.string.greatCreationEff,
        zMax = 40,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val eternalMagic = Spell(
        name = R.string.magEternal,
        isActive = true,
        level = 92,
        zCost = 600,
        effect = R.string.magEternalDesc,
        addedEffect = R.string.magEternalEff,
        zMax = 50,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val barrierSpell = Spell(
        name = R.string.barrier,
        isActive = true,
        level = 96,
        zCost = 800,
        effect = R.string.barrierDesc,
        addedEffect = R.string.barrierEff,
        zMax = 50,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val giftOfLife = Spell(
        name = R.string.lifeGift,
        isActive = true,
        level = 98,
        zCost = 800,
        effect = R.string.lifeGiftDesc,
        addedEffect = R.string.lifeGiftEff,
        zMax = 50,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val createSpell = Spell(
        name = R.string.createSpell,
        isActive = true,
        level = 100,
        zCost = 1000,
        effect = R.string.createSpellDesc,
        addedEffect = R.string.createSpellEff,
        zMax = 50,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    init{
        fullBook.addAll(elements = listOf(
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
        ))
    }
}