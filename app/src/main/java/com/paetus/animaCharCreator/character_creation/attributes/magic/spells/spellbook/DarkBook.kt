package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the dark element.
 */
class DarkBook{
    private val createDark = Spell(
        R.string.createDark,
        Element.Dark,
        true,
        2,
        20,
        R.string.createDarkDesc,
        R.string.createDarkEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val induceFear = Spell(
        R.string.induceFear,
        Element.Dark,
        true,
        6,
        40,
        R.string.induceFearDesc,
        R.string.induceFearEff,
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val seeInDarkness = Spell(
        R.string.seeInDark,
        Element.Dark,
        true,
        8,
        40,
        R.string.seeInDarkDesc,
        R.string.seeInDarkEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val darkShield = Spell(
        R.string.darkShield,
        Element.Dark,
        false,
        10,
        50,
        R.string.darkShieldDesc,
        R.string.darkShieldEff,
        20,
        10,
        false,
        listOf(SpellType.Defense)
    )

    private val shadow = Spell(
        R.string.shadow,
        Element.Dark,
        false,
        12,
        50,
        R.string.shadowDesc,
        R.string.shadowEff,
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val darkArmor = Spell(
        R.string.darkArmor,
        Element.Dark,
        true,
        16,
        60,
        R.string.darkArmorDesc,
        R.string.darkArmorEff,
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val banishLight = Spell(
        R.string.banLight,
        Element.Dark,
        true,
        18,
        60,
        R.string.banLightDesc,
        R.string.banLightEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val hideMagic = Spell(
        R.string.hideMag,
        Element.Dark,
        false,
        20,
        50,
        R.string.hideMagDesc,
        R.string.hideMagEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val darkBeam = Spell(
        R.string.darkBeam,
        Element.Dark,
        true,
        22,
        50,
        R.string.darkBeamDesc,
        R.string.darkBeamEff,
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val darkzone = Spell(
        R.string.darkZone,
        Element.Dark,
        true,
        26,
        60,
        R.string.darkZoneDesc,
        R.string.darkZoneEff,
        20,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val darkBond = Spell(
        R.string.darkBond,
        Element.Dark,
        true,
        28,
        60,
        R.string.darkBondDesc,
        R.string.darkBondEff,
        10,
        10,
        false,
        listOf(SpellType.Attack)
    )

    private val controlDark = Spell(
        R.string.controlDark,
        Element.Dark,
        true,
        30,
        50,
        R.string.controlDarkDesc,
        R.string.controlDarkEff,
        20,
        10,
        false,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val concealment = Spell(
        R.string.concealment,
        Element.Dark,
        false,
        32,
        60,
        R.string.concealmentDesc,
        R.string.concealmentEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val obfuscate = Spell(
        R.string.obfuscate,
        Element.Dark,
        true,
        36,
        100,
        R.string.obfuscateDesc,
        R.string.obfuscateEff,
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val enrage = Spell(
        R.string.enrage,
        Element.Dark,
        true,
        38,
        60,
        R.string.enrageDesc,
        R.string.enrageEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val banishPositiveEmotions = Spell(
        R.string.banPosEmote,
        Element.Dark,
        true,
        40,
        80,
        R.string.banPosEmoteDesc,
        R.string.banPosEmoteEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val night = Spell(
        R.string.night,
        Element.Dark,
        true,
        42,
        80,
        R.string.nightDesc,
        R.string.nightEff,
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val darkSphere = Spell(
        R.string.darkSphere,
        Element.Dark,
        true,
        46,
        120,
        R.string.darkSphereDesc,
        R.string.darkSphereEff,
        20,
        10,
        false,
        listOf(SpellType.Attack)
    )

    private val concealZone = Spell(
        R.string.concealZone,
        Element.Dark,
        true,
        48,
        140,
        R.string.concealZoneDesc,
        R.string.concealZoneEff,
        20,
        20,
        true,
        listOf(SpellType.Detection)
    )

    private val enterNightmare = Spell(
        R.string.enterNightmare,
        Element.Dark,
        true,
        50,
        120,
        R.string.enterNightmareDesc,
        R.string.enterNightmareEff,
        20,
        50,
        true,
        listOf(SpellType.Spiritual)
    )

    private val darkForm = Spell(
        R.string.darkForm,
        Element.Dark,
        true,
        52,
        100,
        R.string.darkFormDesc,
        R.string.darkFormEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val perdition = Spell(
        R.string.perdition,
        Element.Dark,
        true,
        56,
        100,
        R.string.perditionDesc,
        R.string.perditionEff,
        20,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val createNegativeFeelings = Spell(
        R.string.createNegEmote,
        Element.Dark,
        true,
        58,
        100,
        R.string.createNegEmoteDesc,
        R.string.createNegEmoteEff,
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val eliminateTraces = Spell(
        R.string.eliminateTrace,
        Element.Dark,
        true,
        60,
        100,
        R.string.eliminateTraceDesc,
        R.string.eliminateTraceEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val shieldFromPositive = Spell(
        R.string.shieldPositive,
        Element.Dark,
        true,
        62,
        140,
        R.string.shieldPositiveDesc,
        R.string.shieldPositiveEff,
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val devouringDark = Spell(
        R.string.darkDevour,
        Element.Dark,
        false,
        66,
        120,
        R.string.darkDevourDesc,
        R.string.darkDevourEff,
        20,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val devastate = Spell(
        R.string.devastate,
        Element.Dark,
        true,
        68,
        100,
        R.string.devastateDesc,
        R.string.devastateEff,
        20,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val markOfFear = Spell(
        R.string.fearMark,
        Element.Dark,
        true,
        70,
        140,
        R.string.fearMarkDesc,
        R.string.fearMarkEff,
        20,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val catastrophicDarkness = Spell(
        R.string.darkCatastrophe,
        Element.Dark,
        true,
        72,
        120,
        R.string.darkCatastropheDesc,
        R.string.darkCatastropheEff,
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val darkMaterialObjects = Spell(
        R.string.darkMatObj,
        Element.Dark,
        true,
        76,
        150,
        R.string.darkMatObjDesc,
        R.string.darkMatObjEff,
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val shadowTravel = Spell(
        R.string.shadowTravel,
        Element.Dark,
        true,
        78,
        250,
        R.string.shadowTravelDesc,
        R.string.shadowTravelEff,
        30,
        null,
        false,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val nightmareLord = Spell(
        R.string.nightmareLord,
        Element.Dark,
        true,
        80,
        300,
        R.string.nightmareLordDesc,
        R.string.nightmareLordEff,
        20,
        5,
        true,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val createDarkBeing = Spell(
        R.string.darkBeing,
        Element.Dark,
        true,
        82,
        250,
        R.string.darkBeingDesc,
        R.string.darkBeingEff,
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val concealmentFromMagic = Spell(
        R.string.concealFromMag,
        Element.Dark,
        false,
        86,
        200,
        R.string.concealFromMagDesc,
        R.string.concealFromMagEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val darknessKingdom = Spell(
        R.string.darkKingdom,
        Element.Dark,
        true,
        88,
        200,
        R.string.darkKingdomDesc,
        R.string.darkKingdomEff,
        30,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val undetectable = Spell(
        R.string.undetectable,
        Element.Dark,
        true,
        90,
        380,
        R.string.undetectableDesc,
        R.string.undetectableEff,
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val prisonOfDarkness = Spell(
        R.string.darkPrison,
        Element.Dark,
        true,
        92,
        200,
        R.string.darkPrisonDesc,
        R.string.darkPrisonEff,
        20,
        5,
        true,
        listOf(SpellType.Spiritual)
    )

    private val oneWithDarkness = Spell(
        R.string.darkOne,
        Element.Dark,
        true,
        96,
        100,
        R.string.darkOneDesc,
        R.string.darkOneEff,
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val darkAscension = Spell(
        R.string.darkAscension,
        Element.Dark,
        true,
        98,
        300,
        R.string.darkAscensionDesc,
        R.string.darkAscensionEff,
        30,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val darkHolocaust = Spell(
        R.string.holoDark,
        Element.Dark,
        true,
        100,
        600,
        R.string.holoDarkDesc,
        R.string.holoDarkEff,
        50,
        null,
        false,
        listOf(SpellType.Attack, SpellType.Spiritual)
    )

    val fullBook = listOf(
        createDark,
        null,
        induceFear,
        seeInDarkness,
        darkShield,
        shadow,
        null,
        darkArmor,
        banishLight,
        hideMagic,
        darkBeam,
        null,
        darkzone,
        darkBond,
        controlDark,
        concealment,
        null,
        obfuscate,
        enrage,
        banishPositiveEmotions,
        night,
        null,
        darkSphere,
        concealZone,
        enterNightmare,
        darkForm,
        null,
        perdition,
        createNegativeFeelings,
        eliminateTraces,
        shieldFromPositive,
        null,
        devouringDark,
        devastate,
        markOfFear,
        catastrophicDarkness,
        null,
        darkMaterialObjects,
        shadowTravel,
        nightmareLord,
        createDarkBeing,
        null,
        concealmentFromMagic,
        darknessKingdom,
        undetectable,
        prisonOfDarkness,
        null,
        oneWithDarkness,
        darkAscension,
        darkHolocaust
    )
}