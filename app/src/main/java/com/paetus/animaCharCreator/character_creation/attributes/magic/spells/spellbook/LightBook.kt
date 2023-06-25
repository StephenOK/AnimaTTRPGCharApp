package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the light element.
 */
class LightBook{
    private val createLight = Spell(
        R.string.createLight,
        Element.Light,
        true,
        2,
        20,
        R.string.createLightDesc,
        R.string.createLightEff,
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val induceCalm = Spell(
        R.string.calmInduce,
        Element.Light,
        true,
        6,
        40,
        R.string.calmInduceDesc,
        R.string.calmInduceEff,
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val blindingFlash = Spell(
        R.string.blindFlash,
        Element.Light,
        true,
        8,
        50,
        R.string.blindFlashDesc,
        R.string.blindFlashEff,
        10,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    private val lightShield = Spell(
        R.string.lightShield,
        Element.Light,
        false,
        10,
        50,
        R.string.lightShieldDesc,
        R.string.lightShieldEff,
        20,
        10,
        false,
        listOf(SpellType.Defense)
    )

    private val perceiveSpell = Spell(
        R.string.perceive,
        Element.Light,
        true,
        12,
        50,
        R.string.perceiveDesc,
        R.string.perceiveEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val lightArmor = Spell(
        R.string.lightArmor,
        Element.Light,
        true,
        16,
        60,
        R.string.lightArmorDesc,
        R.string.lightArmorEff,
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val banishShadows = Spell(
        R.string.banShadows,
        Element.Light,
        true,
        18,
        60,
        R.string.banShadowsDesc,
        R.string.banShadowsEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val detectNegativeEmotions = Spell(
        R.string.findNegEmote,
        Element.Light,
        true,
        20,
        50,
        R.string.findNegEmoteDesc,
        R.string.findNegEmoteEff,
        10,
        10,
        false,
        listOf(SpellType.Detection)
    )

    private val lightBeam = Spell(
        R.string.lightBeam,
        Element.Light,
        true,
        22,
        50,
        R.string.lightBeamDesc,
        R.string.lightBeamEff,
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val hologram = Spell(
        R.string.hologram,
        Element.Light,
        true,
        26,
        40,
        R.string.hologramDesc,
        R.string.hologramEff,
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val lightBond = Spell(
        R.string.lightBond,
        Element.Light,
        true,
        28,
        60,
        R.string.lightBondDesc,
        R.string.lightBondEff,
        10,
        10,
        false,
        listOf(SpellType.Attack)
    )

    private val controlLight = Spell(
        R.string.lightControl,
        Element.Light,
        true,
        30,
        50,
        R.string.lightControlDesc,
        R.string.lightControlEff,
        20,
        10,
        false,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val detectLife = Spell(
        R.string.detectLife,
        Element.Light,
        true,
        32,
        60,
        R.string.detectLifeDesc,
        R.string.detectLifeEff,
        10,
        20,
        false,
        listOf(SpellType.Detection)
    )

    private val lightSpy = Spell(
        R.string.lightSpy,
        Element.Light,
        true,
        36,
        100,
        R.string.lightSpyDesc,
        R.string.lightSpyEff,
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val ecstasy = Spell(
        R.string.ecstasy,
        Element.Light,
        true,
        38,
        60,
        R.string.ecstasyDesc,
        R.string.ecstasyEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val banishNegativeEmotions = Spell(
        R.string.banNegEmote,
        Element.Light,
        true,
        40,
        80,
        R.string.banNegEmoteDesc,
        R.string.banNegEmoteEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val healingLight = Spell(
        R.string.healingLight,
        Element.Light,
        true,
        42,
        80,
        R.string.healingLightDesc,
        R.string.healingLightEff,
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val seekingSphere = Spell(
        R.string.seekSphere,
        Element.Light,
        true,
        46,
        120,
        R.string.seekSphereDesc,
        R.string.seekSphereEff,
        20,
        10,
        false,
        listOf(SpellType.Attack)
    )

    private val detectionZone = Spell(
        R.string.detectZone,
        Element.Light,
        true,
        48,
        140,
        R.string.detectZoneDesc,
        R.string.detectZoneEff,
        20,
        20,
        true,
        listOf(SpellType.Detection)
    )

    private val enterDreams = Spell(
        R.string.enterDreams,
        Element.Light,
        true,
        50,
        120,
        R.string.enterDreamsDesc,
        R.string.enterDreamsEff,
        20,
        20,
        true,
        listOf(SpellType.Spiritual)
    )

    private val lightForm = Spell(
        R.string.lightForm,
        Element.Light,
        true,
        52,
        100,
        R.string.lightFormDesc,
        R.string.lightFormEff,
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val blessingSpell = Spell(
        R.string.blessing,
        Element.Light,
        true,
        56,
        100,
        R.string.blessingDesc,
        R.string.blessingEff,
        20,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val createGoodFeelings = Spell(
        R.string.goodFeelings,
        Element.Light,
        true,
        58,
        100,
        R.string.goodFeelingsDesc,
        R.string.goodFeelingsEff,
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val seeTruth = Spell(
        R.string.seeTruth,
        Element.Light,
        true,
        60,
        100,
        R.string.seeTruthDesc,
        R.string.seeTruthEff,
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val shieldFromNegative = Spell(
        R.string.shieldNegative,
        Element.Light,
        true,
        62,
        140,
        R.string.shieldNegativeDesc,
        R.string.shieldNegativeEff,
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val findSpell = Spell(
        R.string.find,
        Element.Light,
        true,
        66,
        160,
        R.string.findDesc,
        R.string.findEff,
        20,
        null,
        false,
        listOf(SpellType.Detection)
    )

    private val restoreSpell = Spell(
        R.string.restore,
        Element.Light,
        true,
        68,
        160,
        R.string.restoreDesc,
        R.string.restoreEff,
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val hypnoticDisplay = Spell(
        R.string.hypnoDisplay,
        Element.Light,
        true,
        70,
        140,
        R.string.hypnoDisplayDesc,
        R.string.hypnoDisplayEff,
        20,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val catastrophicLight = Spell(
        R.string.catastrophicLight,
        Element.Light,
        true,
        72,
        120,
        R.string.catastrophicLightDesc,
        R.string.catastrophicLightEff,
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val luminousMaterial = Spell(
        R.string.lumMat,
        Element.Light,
        true,
        76,
        150,
        R.string.lumMatDesc,
        R.string.lumMatEff,
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val lightTravel = Spell(
        R.string.lightTravel,
        Element.Light,
        true,
        78,
        250,
        R.string.lightTravelDesc,
        R.string.lightTravelEff,
        30,
        null,
        false,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val dreamLordship = Spell(
        R.string.dreamLord,
        Element.Light,
        true,
        80,
        300,
        R.string.dreamLordDesc,
        R.string.dreamLordEff,
        20,
        5,
        true,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val createLightBeing = Spell(
        R.string.lightBeing,
        Element.Light,
        true,
        82,
        250,
        R.string.lightBeingDesc,
        R.string.lightBeingEff,
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val reflectingPrism = Spell(
        R.string.reflectPrism,
        Element.Light,
        false,
        86,
        160,
        R.string.reflectPrismDesc,
        R.string.reflectPrismEff,
        20,
        10,
        true,
        listOf(SpellType.Defense)
    )

    private val omniscienceRadius = Spell(
        R.string.omniscience,
        Element.Light,
        true,
        88,
        200,
        R.string.omniscienceDesc,
        R.string.omniscienceEff,
        20,
        5,
        false,
        listOf(SpellType.Effect)
    )

    private val predict = Spell(
        R.string.predict,
        Element.Light,
        true,
        90,
        200,
        R.string.predictDesc,
        R.string.predictEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val lightPrison = Spell(
        R.string.lightPrison,
        Element.Light,
        true,
        92,
        200,
        R.string.lightPrisonDesc,
        R.string.lightPrisonEff,
        20,
        5,
        true,
        listOf(SpellType.Spiritual)
    )

    private val oneWithLight = Spell(
        R.string.oneLight,
        Element.Light,
        true,
        96,
        100,
        R.string.oneLightDesc,
        R.string.oneLightEff,
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val ascension = Spell(
        R.string.ascension,
        Element.Light,
        true,
        98,
        300,
        R.string.ascensionDesc,
        R.string.ascensionEff,
        30,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val lightHolocaust = Spell(
        R.string.holoLight,
        Element.Light,
        true,
        100,
        600,
        R.string.holoLightDesc,
        R.string.holoLightEff,
        50,
        null,
        false,
        listOf(SpellType.Attack, SpellType.Spiritual)
    )

    val fullBook = listOf(
        createLight,
        null,
        induceCalm,
        blindingFlash,
        lightShield,
        perceiveSpell,
        null,
        lightArmor,
        banishShadows,
        detectNegativeEmotions,
        lightBeam,
        null,
        hologram,
        lightBond,
        controlLight,
        detectLife,
        null,
        lightSpy,
        ecstasy,
        banishNegativeEmotions,
        healingLight,
        null,
        seekingSphere,
        detectionZone,
        enterDreams,
        lightForm,
        null,
        blessingSpell,
        createGoodFeelings,
        seeTruth,
        shieldFromNegative,
        null,
        findSpell,
        restoreSpell,
        hypnoticDisplay,
        catastrophicLight,
        null,
        luminousMaterial,
        lightTravel,
        dreamLordship,
        createLightBeing,
        null,
        reflectingPrism,
        omniscienceRadius,
        predict,
        lightPrison,
        null,
        oneWithLight,
        ascension,
        lightHolocaust
    )
}