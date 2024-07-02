package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.MagicBook
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.enumerations.SpellType

/**
 * List of spells associated with the essence element.
 */
class EssenceBook: MagicBook(Element.Essence){
    private val naturalAffinity = Spell(
        name = R.string.natAffinity,
        isActive = true,
        level = 2,
        zCost = 30,
        effect = R.string.natAffinityDesc,
        addedEffect = R.string.natAffinityEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val detectEssence = Spell(
        name = R.string.detectEssence,
        isActive = true,
        level = 6,
        zCost = 30,
        effect = R.string.detectEssenceDesc,
        addedEffect = R.string.detectEssenceEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val essenceCommunicate = Spell(
        name = R.string.essenceCom,
        isActive = true,
        level = 10,
        zCost = 30,
        effect = R.string.essenceComDesc,
        addedEffect = R.string.essenceComEff,
        zMax = 10,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val naturalKnowledge = Spell(
        name = R.string.natKnowledge,
        isActive = true,
        level = 12,
        zCost = 40,
        effect = R.string.natKnowledgeDesc,
        addedEffect = R.string.natKnowledgeEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val healing = Spell(
        name = R.string.healing,
        isActive = true,
        level = 16,
        zCost = 80,
        effect = R.string.healingDesc,
        addedEffect = R.string.healingEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val soulBarrier = Spell(
        name = R.string.soulBarrier,
        isActive = true,
        level = 20,
        zCost = 60,
        effect = R.string.soulBarrierDesc,
        addedEffect = R.string.soulBarrierEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Defense)
    )

    private val shareSenses = Spell(
        name = R.string.shareSense,
        isActive = true,
        level = 22,
        zCost = 60,
        effect = R.string.shareSenseDesc,
        addedEffect = R.string.shareSenseEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val modifyEssence = Spell(
        name = R.string.essenceMod,
        isActive = true,
        level = 26,
        zCost = 50,
        effect = R.string.essenceModDesc,
        addedEffect = R.string.essenceModEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val soulPoison = Spell(
        name = R.string.soulPoison,
        isActive = true,
        level = 30,
        zCost = 60,
        effect = R.string.soulPoisonDesc,
        addedEffect = R.string.soulPoisonEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val analyzeSoul = Spell(
        name = R.string.analyzeSoul,
        isActive = true,
        level = 32,
        zCost = 60,
        effect = R.string.analyzeSoulDesc,
        addedEffect = R.string.analyzeSoulEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val acquireNaturalCapacities = Spell(
        name = R.string.acquireCapacities,
        isActive = true,
        level = 36,
        zCost = 120,
        effect = R.string.acquireCapacitiesDesc,
        addedEffect = R.string.acquireCapacitiesEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val revitalize = Spell(
        name = R.string.revitalize,
        isActive = true,
        level = 40,
        zCost = 100,
        effect = R.string.revitalizeDesc,
        addedEffect = R.string.revitalizeEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val lifeMind = Spell(
        name = R.string.lifeMind,
        isActive = true,
        level = 42,
        zCost = 120,
        effect = R.string.lifeMindDesc,
        addedEffect = R.string.lifeMindEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val alterGrowth = Spell(
        name = R.string.alterGrowth,
        isActive = true,
        level = 46,
        zCost = 100,
        effect = R.string.alterGrowthDesc,
        addedEffect = R.string.alterGrowthEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val naturalImitation = Spell(
        name = R.string.natImitation,
        isActive = true,
        level = 50,
        zCost = 60,
        effect = R.string.natImitationDesc,
        addedEffect = R.string.natImitationEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val spiritualForm = Spell(
        name = R.string.spiritForm,
        isActive = true,
        level = 52,
        zCost = 100,
        effect = R.string.spiritFormDesc,
        addedEffect = R.string.spiritFormEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val naturalControl = Spell(
        name = R.string.natureControl,
        isActive = true,
        level = 56,
        zCost = 100,
        effect = R.string.natureControlDesc,
        addedEffect = R.string.natureControlEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val stateInduction = Spell(
        name = R.string.stateInduce,
        isActive = true,
        level = 60,
        zCost = 100,
        effect = R.string.stateInduceDesc,
        addedEffect = R.string.stateInduceEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val flowReturn = Spell(
        name = R.string.flowReturn,
        isActive = true,
        level = 62,
        zCost = 100,
        effect = R.string.flowReturnDesc,
        addedEffect = R.string.flowReturnEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val shieldArea = Spell(
        name = R.string.shieldArea,
        isActive = true,
        level = 66,
        zCost = 120,
        effect = R.string.shieldAreaDesc,
        addedEffect = R.string.shieldAreaEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val supernaturalControl = Spell(
        name = R.string.supernaturalControl,
        isActive = true,
        level = 70,
        zCost = 120,
        effect = R.string.supernaturalControlDesc,
        addedEffect = R.string.supernaturalControlEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val shareEssence = Spell(
        name = R.string.shareEssence,
        isActive = true,
        level = 72,
        zCost = 140,
        effect = R.string.shareEssenceDesc,
        addedEffect = R.string.shareEssenceEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val transmigrateSoul = Spell(
        name = R.string.transmigrateSoul,
        isActive = true,
        level = 76,
        zCost = 180,
        effect = R.string.transmigrateSoulDesc,
        addedEffect = R.string.transmigrateSoulEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val spiritualExistence = Spell(
        name = R.string.spiritExist,
        isActive = true,
        level = 80,
        zCost = 200,
        effect = R.string.spiritExistDesc,
        addedEffect = R.string.spiritExistEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val spiritCreation = Spell(
        name = R.string.spiritCreate,
        isActive = true,
        level = 82,
        zCost = 250,
        effect = R.string.spiritCreateDesc,
        addedEffect = R.string.spiritCreateEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val tieVitalEssence = Spell(
        name = R.string.tieVitalEssence,
        isActive = true,
        level = 86,
        zCost = 200,
        effect = R.string.tieVitalEssenceDesc,
        addedEffect = R.string.tieVitalEssenceEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val greenness = Spell(
        name = R.string.greenness,
        isActive = true,
        level = 90,
        zCost = 250,
        effect = R.string.greennessDesc,
        addedEffect = R.string.greennessEff,
        zMax = 50,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val lifeDominion = Spell(
        name = R.string.lifeDominion,
        isActive = true,
        level = 92,
        zCost = 300,
        effect = R.string.lifeDominionDesc,
        addedEffect = R.string.lifeDominionEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val resurrection = Spell(
        name = R.string.resurrect,
        isActive = true,
        level = 96,
        zCost = 400,
        effect = R.string.resurrectDesc,
        addedEffect = R.string.resurrectEff,
        zMax = 40,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val soulLord = Spell(
        name = R.string.soulLord,
        isActive = true,
        level = 100,
        zCost = 600,
        effect = R.string.soulLordDesc,
        addedEffect = R.string.soulLordEff,
        zMax = 50,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    init{
        fullBook.addAll(elements = listOf(
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
        ))
    }
}