package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the illusion element.
 */
class IllusionBook{
    private val illusorySound = Spell(
        R.string.illusorySound,
        Element.Illusion,
        true,
        2,
        30,
        R.string.illusorySoundDesc,
        R.string.illusorySoundEff,
        10,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val illusorySmell = Spell(
        R.string.illusorySmell,
        Element.Illusion,
        true,
        6,
        30,
        R.string.illusorySmellDesc,
        R.string.illusorySmellEff,
        10,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val illusoryTouch = Spell(
        R.string.illusoryTouch,
        Element.Illusion,
        true,
        10,
        30,
        R.string.illusoryTouchDesc,
        R.string.illusoryTouchEff,
        10,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val visualIllusion = Spell(
        R.string.visualIllusion,
        Element.Illusion,
        true,
        12,
        40,
        R.string.visualIllusionDesc,
        R.string.visualIllusionEff,
        10,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val detectIllusion = Spell(
        R.string.detectIllusion,
        Element.Illusion,
        true,
        16,
        60,
        R.string.detectIllusionDesc,
        R.string.detectIllusionEff,
        20,
        10,
        false,
        listOf(SpellType.Detection)
    )

    private val sweetTalk = Spell(
        R.string.sweetTalk,
        Element.Illusion,
        true,
        20,
        50,
        R.string.sweetTalkDesc,
        R.string.sweetTalkEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val alterAppearance = Spell(
        R.string.alterAppearance,
        Element.Illusion,
        true,
        22,
        60,
        R.string.alterAppearanceDesc,
        R.string.alterAppearanceEff,
        10,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val illusoryInvisibility = Spell(
        R.string.illusoryInvisibility,
        Element.Illusion,
        true,
        26,
        60,
        R.string.illusoryInvisibilityDesc,
        R.string.illusoryInvisibilityEff,
        10,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val mirrorImage = Spell(
        R.string.mirrorImage,
        Element.Illusion,
        true,
        30,
        80,
        R.string.mirrorImageDesc,
        R.string.mirrorImageEff,
        10,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val totalIllusion = Spell(
        R.string.totalIllusion,
        Element.Illusion,
        true,
        32,
        80,
        R.string.totalIllusionDesc,
        R.string.totalIllusionEff,
        20,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val confusion = Spell(
        R.string.confusion,
        Element.Illusion,
        true,
        36,
        50,
        R.string.confusionDesc,
        R.string.confusionEff,
        10,
        20,
        false,
        listOf(SpellType.Spiritual)
    )

    private val createIllusoryBeing = Spell(
        R.string.createIllusoryBeing,
        Element.Illusion,
        true,
        40,
        60,
        R.string.createIllusoryBeingDesc,
        R.string.createIllusoryBeingEff,
        10,
        20,
        true,
        listOf(SpellType.Automatic)
    )

    private val illusionResistance = Spell(
        R.string.illusionRes,
        Element.Illusion,
        true,
        42,
        80,
        R.string.illusionResDesc,
        R.string.illusionResEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val detectLie = Spell(
        R.string.detectLie,
        Element.Illusion,
        true,
        46,
        80,
        R.string.detectLifeDesc,
        R.string.detectLieEff,
        10,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val ghostlyIllusion = Spell(
        R.string.ghostIllusion,
        Element.Illusion,
        true,
        50,
        120,
        R.string.ghostIllusionDesc,
        R.string.ghostIllusionEff,
        20,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val distortDetection = Spell(
        R.string.distortDetection,
        Element.Illusion,
        true,
        52,
        120,
        R.string.distortDetectionDesc,
        R.string.distortDetectionEff,
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val lie = Spell(
        R.string.lie,
        Element.Illusion,
        true,
        56,
        100,
        R.string.lieDesc,
        R.string.lieEff,
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val destroyIllusion = Spell(
        R.string.destroyIllusion,
        Element.Illusion,
        true,
        60,
        80,
        R.string.destroyIllusionDesc,
        R.string.destroyIllusionEff,
        40,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val ghostlyBeing = Spell(
        R.string.ghostBeing,
        Element.Illusion,
        true,
        62,
        80,
        R.string.ghostBeingDesc,
        R.string.ghostBeingEff,
        10,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val gullibility = Spell(
        R.string.gullible,
        Element.Illusion,
        true,
        66,
        80,
        R.string.gullibleDesc,
        R.string.gullibleEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val ghostlyAttack = Spell(
        R.string.ghostAttack,
        Element.Illusion,
        true,
        70,
        100,
        R.string.ghostAttackDesc,
        R.string.ghostAttackEff,
        20,
        null,
        false,
        listOf(SpellType.Attack, SpellType.Spiritual)
    )

    private val lyingGift = Spell(
        R.string.lyingGift,
        Element.Illusion,
        true,
        72,
        140,
        R.string.lyingGiftDesc,
        R.string.lyingGiftEff,
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val illusoryLie = Spell(
        R.string.illusoryLie,
        Element.Illusion,
        true,
        76,
        140,
        R.string.illusoryLieDesc,
        R.string.illusoryLieEff,
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val majorIllusion = Spell(
        R.string.majorIllusion,
        Element.Illusion,
        true,
        80,
        250,
        R.string.majorIllusionDesc,
        R.string.majorIllusionEff,
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val fixIllusion = Spell(
        R.string.fixIllusion,
        Element.Illusion,
        true,
        82,
        250,
        R.string.fixIllusionDesc,
        R.string.fixIllusionEff,
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val illusionSense = Spell(
        R.string.sensoryIllusion,
        Element.Illusion,
        true,
        86,
        200,
        R.string.sensoryIllusionDesc,
        R.string.sensoryIllusionEff,
        20,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val nonExistence = Spell(
        R.string.nonexistance,
        Element.Illusion,
        true,
        90,
        220,
        R.string.nonexistanceDesc,
        R.string.nonexistanceEff,
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val deceiveDeath = Spell(
        R.string.deceiveDeath,
        Element.Illusion,
        true,
        92,
        500,
        R.string.deceiveDeathDesc,
        R.string.deceiveDeathEff,
        40,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val worldOfLies = Spell(
        R.string.lieWorld,
        Element.Illusion,
        true,
        96,
        500,
        R.string.lieWorldDesc,
        R.string.lieWorldEff,
        40,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val falseReality = Spell(
        R.string.falseReality,
        Element.Illusion,
        true,
        100,
        600,
        R.string.falseRealityDesc,
        R.string.falseRealityEff,
        50,
        null,
        false,
        listOf(SpellType.Automatic)
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