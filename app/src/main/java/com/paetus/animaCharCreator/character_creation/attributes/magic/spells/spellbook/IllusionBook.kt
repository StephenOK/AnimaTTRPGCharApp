package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.enumerations.SpellType

/**
 * List of spells associated with the illusion element.
 */
class IllusionBook{
    private val illusorySound = Spell(
        name = R.string.illusorySound,
        inBook = Element.Illusion,
        isActive = true,
        level = 2,
        zCost = 30,
        effect = R.string.illusorySoundDesc,
        addedEffect = R.string.illusorySoundEff,
        zMax = 10,
        maintenance = 50,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val illusorySmell = Spell(
        name = R.string.illusorySmell,
        inBook = Element.Illusion,
        isActive = true,
        level = 6,
        zCost = 30,
        effect = R.string.illusorySmellDesc,
        addedEffect = R.string.illusorySmellEff,
        zMax = 10,
        maintenance = 50,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val illusoryTouch = Spell(
        name = R.string.illusoryTouch,
        inBook = Element.Illusion,
        isActive = true,
        level = 10,
        zCost = 30,
        effect = R.string.illusoryTouchDesc,
        addedEffect = R.string.illusoryTouchEff,
        zMax = 10,
        maintenance = 50,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val visualIllusion = Spell(
        name = R.string.visualIllusion,
        inBook = Element.Illusion,
        isActive = true,
        level = 12,
        zCost = 40,
        effect = R.string.visualIllusionDesc,
        addedEffect = R.string.visualIllusionEff,
        zMax = 10,
        maintenance = 50,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val detectIllusion = Spell(
        name = R.string.detectIllusion,
        inBook = Element.Illusion,
        isActive = true,
        level = 16,
        zCost = 60,
        effect = R.string.detectIllusionDesc,
        addedEffect = R.string.detectIllusionEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Detection)
    )

    private val sweetTalk = Spell(
        name = R.string.sweetTalk,
        inBook = Element.Illusion,
        isActive = true,
        level = 20,
        zCost = 50,
        effect = R.string.sweetTalkDesc,
        addedEffect = R.string.sweetTalkEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val alterAppearance = Spell(
        name = R.string.alterAppearance,
        inBook = Element.Illusion,
        isActive = true,
        level = 22,
        zCost = 60,
        effect = R.string.alterAppearanceDesc,
        addedEffect = R.string.alterAppearanceEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val illusoryInvisibility = Spell(
        name = R.string.illusoryInvisibility,
        inBook = Element.Illusion,
        isActive = true,
        level = 26,
        zCost = 60,
        effect = R.string.illusoryInvisibilityDesc,
        addedEffect = R.string.illusoryInvisibilityEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val mirrorImage = Spell(
        name = R.string.mirrorImage,
        inBook = Element.Illusion,
        isActive = true,
        level = 30,
        zCost = 80,
        effect = R.string.mirrorImageDesc,
        addedEffect = R.string.mirrorImageEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val totalIllusion = Spell(
        name = R.string.totalIllusion,
        inBook = Element.Illusion,
        isActive = true,
        level = 32,
        zCost = 80,
        effect = R.string.totalIllusionDesc,
        addedEffect = R.string.totalIllusionEff,
        zMax = 20,
        maintenance = 50,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val confusion = Spell(
        name = R.string.confusion,
        inBook = Element.Illusion,
        isActive = true,
        level = 36,
        zCost = 50,
        effect = R.string.confusionDesc,
        addedEffect = R.string.confusionEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val createIllusoryBeing = Spell(
        name = R.string.createIllusoryBeing,
        inBook = Element.Illusion,
        isActive = true,
        level = 40,
        zCost = 60,
        effect = R.string.createIllusoryBeingDesc,
        addedEffect = R.string.createIllusoryBeingEff,
        zMax = 10,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val illusionResistance = Spell(
        name = R.string.illusionRes,
        inBook = Element.Illusion,
        isActive = true,
        level = 42,
        zCost = 80,
        effect = R.string.illusionResDesc,
        addedEffect = R.string.illusionResEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val detectLie = Spell(
        name = R.string.detectLie,
        inBook = Element.Illusion,
        isActive = true,
        level = 46,
        zCost = 80,
        effect = R.string.detectLifeDesc,
        addedEffect = R.string.detectLieEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val ghostlyIllusion = Spell(
        name = R.string.ghostIllusion,
        inBook = Element.Illusion,
        isActive = true,
        level = 50,
        zCost = 120,
        effect = R.string.ghostIllusionDesc,
        addedEffect = R.string.ghostIllusionEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val distortDetection = Spell(
        name = R.string.distortDetection,
        inBook = Element.Illusion,
        isActive = true,
        level = 52,
        zCost = 120,
        effect = R.string.distortDetectionDesc,
        addedEffect = R.string.distortDetectionEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val lie = Spell(
        name = R.string.lie,
        inBook = Element.Illusion,
        isActive = true,
        level = 56,
        zCost = 100,
        effect = R.string.lieDesc,
        addedEffect = R.string.lieEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val destroyIllusion = Spell(
        name = R.string.destroyIllusion,
        inBook = Element.Illusion,
        isActive = true,
        level = 60,
        zCost = 80,
        effect = R.string.destroyIllusionDesc,
        addedEffect = R.string.destroyIllusionEff,
        zMax = 40,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val ghostlyBeing = Spell(
        name = R.string.ghostBeing,
        inBook = Element.Illusion,
        isActive = true,
        level = 62,
        zCost = 80,
        effect = R.string.ghostBeingDesc,
        addedEffect = R.string.ghostBeingEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val gullibility = Spell(
        name = R.string.gullible,
        inBook = Element.Illusion,
        isActive = true,
        level = 66,
        zCost = 80,
        effect = R.string.gullibleDesc,
        addedEffect = R.string.gullibleEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val ghostlyAttack = Spell(
        name = R.string.ghostAttack,
        inBook = Element.Illusion,
        isActive = true,
        level = 70,
        zCost = 100,
        effect = R.string.ghostAttackDesc,
        addedEffect = R.string.ghostAttackEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack, SpellType.Spiritual)
    )

    private val lyingGift = Spell(
        name = R.string.lyingGift,
        inBook = Element.Illusion,
        isActive = true,
        level = 72,
        zCost = 140,
        effect = R.string.lyingGiftDesc,
        addedEffect = R.string.lyingGiftEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val illusoryLie = Spell(
        name = R.string.illusoryLie,
        inBook = Element.Illusion,
        isActive = true,
        level = 76,
        zCost = 140,
        effect = R.string.illusoryLieDesc,
        addedEffect = R.string.illusoryLieEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val majorIllusion = Spell(
        name = R.string.majorIllusion,
        inBook = Element.Illusion,
        isActive = true,
        level = 80,
        zCost = 250,
        effect = R.string.majorIllusionDesc,
        addedEffect = R.string.majorIllusionEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val fixIllusion = Spell(
        name = R.string.fixIllusion,
        inBook = Element.Illusion,
        isActive = true,
        level = 82,
        zCost = 250,
        effect = R.string.fixIllusionDesc,
        addedEffect = R.string.fixIllusionEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val illusionSense = Spell(
        name = R.string.sensoryIllusion,
        inBook = Element.Illusion,
        isActive = true,
        level = 86,
        zCost = 200,
        effect = R.string.sensoryIllusionDesc,
        addedEffect = R.string.sensoryIllusionEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val nonExistence = Spell(
        name = R.string.nonexistance,
        inBook = Element.Illusion,
        isActive = true,
        level = 90,
        zCost = 220,
        effect = R.string.nonexistanceDesc,
        addedEffect = R.string.nonexistanceEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val deceiveDeath = Spell(
        name = R.string.deceiveDeath,
        inBook = Element.Illusion,
        isActive = true,
        level = 92,
        zCost = 500,
        effect = R.string.deceiveDeathDesc,
        addedEffect = R.string.deceiveDeathEff,
        zMax = 40,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val worldOfLies = Spell(
        name = R.string.lieWorld,
        inBook = Element.Illusion,
        isActive = true,
        level = 96,
        zCost = 500,
        effect = R.string.lieWorldDesc,
        addedEffect = R.string.lieWorldEff,
        zMax = 40,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val falseReality = Spell(
        name = R.string.falseReality,
        inBook = Element.Illusion,
        isActive = true,
        level = 100,
        zCost = 600,
        effect = R.string.falseRealityDesc,
        addedEffect = R.string.falseRealityEff,
        zMax = 50,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    val fullBook = listOf(
        illusorySound,
        null,
        illusorySmell,
        null,
        illusoryTouch,
        visualIllusion,
        null,
        detectIllusion,
        null,
        sweetTalk,
        alterAppearance,
        null,
        illusoryInvisibility,
        null,
        mirrorImage,
        totalIllusion,
        null,
        confusion,
        null,
        createIllusoryBeing,
        illusionResistance,
        null,
        detectLie,
        null,
        ghostlyIllusion,
        distortDetection,
        null,
        lie,
        null,
        destroyIllusion,
        ghostlyBeing,
        null,
        gullibility,
        null,
        ghostlyAttack,
        lyingGift,
        null,
        illusoryLie,
        null,
        majorIllusion,
        fixIllusion,
        null,
        illusionSense,
        null,
        nonExistence,
        deceiveDeath,
        null,
        worldOfLies,
        null,
        falseReality
    )
}