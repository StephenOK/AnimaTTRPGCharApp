package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the essence element.
 */
class EssenceBook{
    private val naturalAffinity = Spell(
        R.string.natAffinity,
        Element.Essence,
        true,
        2,
        30,
        R.string.natAffinityDesc,
        R.string.natAffinityEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val detectEssence = Spell(
        R.string.detectEssence,
        Element.Essence,
        true,
        6,
        30,
        R.string.detectEssenceEff,
        R.string.detectEssenceEff,
        10,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val essenceCommunicate = Spell(
        R.string.essenceCom,
        Element.Essence,
        true,
        10,
        30,
        R.string.essenceComDesc,
        R.string.essenceComEff,
        10,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val naturalKnowledge = Spell(
        R.string.natKnowledge,
        Element.Essence,
        true,
        12,
        40,
        R.string.natKnowledgeDesc,
        R.string.natKnowledgeEff,
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val healing = Spell(
        R.string.healing,
        Element.Essence,
        true,
        16,
        80,
        R.string.healingDesc,
        R.string.healingEff,
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val soulBarrier = Spell(
        R.string.soulBarrier,
        Element.Essence,
        true,
        20,
        60,
        R.string.soulBarrierDesc,
        R.string.soulBarrierEff,
        10,
        10,
        true,
        listOf(SpellType.Defense)
    )

    private val shareSenses = Spell(
        R.string.shareSense,
        Element.Essence,
        true,
        22,
        60,
        R.string.shareSenseDesc,
        R.string.shareSenseEff,
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val modifyEssence = Spell(
        R.string.essenceMod,
        Element.Essence,
        true,
        26,
        50,
        R.string.essenceModDesc,
        R.string.essenceModEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val soulPoison = Spell(
        R.string.soulPoison,
        Element.Essence,
        true,
        30,
        60,
        R.string.soulPoisonDesc,
        R.string.soulPoisonEff,
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val analyzeSoul = Spell(
        R.string.analyzeSoul,
        Element.Essence,
        true,
        32,
        60,
        R.string.analyzeSoulDesc,
        R.string.analyzeSoulEff,
        10,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    private val acquireNaturalCapacities = Spell(
        R.string.acquireCapacities,
        Element.Essence,
        true,
        36,
        120,
        R.string.acquireCapacitiesDesc,
        R.string.acquireCapacitiesEff,
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val revitalize = Spell(
        R.string.revitalize,
        Element.Essence,
        true,
        40,
        100,
        R.string.revitalizeDesc,
        R.string.revitalizeEff,
        20,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val lifeMind = Spell(
        R.string.lifeMind,
        Element.Essence,
        true,
        42,
        120,
        R.string.lifeMindDesc,
        R.string.lifeMindEff,
        20,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val alterGrowth = Spell(
        R.string.alterGrowth,
        Element.Essence,
        true,
        46,
        100,
        R.string.alterGrowthDesc,
        R.string.alterGrowthEff,
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val naturalImitation = Spell(
        R.string.natImitation,
        Element.Essence,
        true,
        50,
        60,
        R.string.natImitationDesc,
        R.string.natImitationEff,
        20,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val spiritualForm = Spell(
        R.string.spiritForm,
        Element.Essence,
        true,
        52,
        100,
        R.string.spiritFormDesc,
        R.string.spiritFormEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val naturalControl = Spell(
        R.string.natureControl,
        Element.Essence,
        true,
        56,
        100,
        R.string.natureControlDesc,
        R.string.natureControlEff,
        20,
        5,
        true,
        listOf(SpellType.Spiritual)
    )

    private val stateInduction = Spell(
        R.string.stateInduce,
        Element.Essence,
        true,
        60,
        100,
        R.string.stateInduceDesc,
        R.string.stateInduceEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val flowReturn = Spell(
        R.string.flowReturn,
        Element.Essence,
        true,
        62,
        100,
        R.string.flowReturnDesc,
        R.string.flowReturnEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val shieldArea = Spell(
        R.string.shieldArea,
        Element.Essence,
        true,
        66,
        120,
        R.string.shieldAreaDesc,
        R.string.shieldAreaEff,
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val supernaturalControl = Spell(
        R.string.supernaturalControl,
        Element.Essence,
        true,
        70,
        120,
        R.string.supernaturalControlDesc,
        R.string.supernaturalControlEff,
        20,
        5,
        true,
        listOf(SpellType.Spiritual)
    )

    private val shareEssence = Spell(
        R.string.shareEssence,
        Element.Essence,
        true,
        72,
        140,
        R.string.shareEssenceDesc,
        R.string.shareEssenceEff,
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val transmigrateSoul = Spell(
        R.string.transmigrateSoul,
        Element.Essence,
        true,
        76,
        180,
        R.string.transmigrateSoulDesc,
        R.string.transmigrateSoulEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val spiritualExistence = Spell(
        R.string.spiritExist,
        Element.Essence,
        true,
        80,
        200,
        R.string.spiritExistDesc,
        R.string.spiritExistEff,
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val spiritCreation = Spell(
        R.string.spiritCreate,
        Element.Essence,
        true,
        82,
        250,
        R.string.spiritCreateDesc,
        R.string.spiritCreateEff,
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val tieVitalEssence = Spell(
        R.string.tieVitalEssence,
        Element.Essence,
        true,
        86,
        200,
        R.string.tieVitalEssenceDesc,
        R.string.tieVitalEssenceEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val greenness = Spell(
        R.string.greenness,
        Element.Essence,
        true,
        90,
        250,
        R.string.greennessDesc,
        R.string.greennessEff,
        50,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val lifeDominion = Spell(
        R.string.lifeDominion,
        Element.Essence,
        true,
        92,
        300,
        R.string.lifeDominionDesc,
        R.string.lifeDominionEff,
        30,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val resurrection = Spell(
        R.string.resurrect,
        Element.Essence,
        true,
        96,
        400,
        R.string.resurrectDesc,
        R.string.resurrectEff,
        40,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val soulLord = Spell(
        R.string.soulLord,
        Element.Essence,
        true,
        100,
        600,
        R.string.soulLordDesc,
        R.string.soulLordEff,
        50,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    val fullBook = listOf(
        naturalAffinity,
        null,
        detectEssence,
        null,
        essenceCommunicate,
        naturalKnowledge,
        null,
        healing,
        null,
        soulBarrier,
        shareSenses,
        null,
        modifyEssence,
        null,
        soulPoison,
        analyzeSoul,
        null,
        acquireNaturalCapacities,
        null,
        revitalize,
        lifeMind,
        null,
        alterGrowth,
        null,
        naturalImitation,
        spiritualForm,
        null,
        naturalControl,
        null,
        stateInduction,
        flowReturn,
        null,
        shieldArea,
        null,
        supernaturalControl,
        shareEssence,
        null,
        transmigrateSoul,
        null,
        spiritualExistence,
        spiritCreation,
        null,
        tieVitalEssence,
        null,
        greenness,
        lifeDominion,
        null,
        resurrection,
        null,
        soulLord
    )
}