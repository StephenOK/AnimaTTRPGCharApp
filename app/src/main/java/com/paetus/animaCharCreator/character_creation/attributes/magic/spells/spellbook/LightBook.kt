package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.enumerations.SpellType

/**
 * List of spells associated with the light element.
 */
class LightBook{
    private val createLight = Spell(
        name = R.string.createLight,
        inBook = Element.Light,
        isActive = true,
        level = 2,
        zCost = 20,
        effect = R.string.createLightDesc,
        addedEffect = R.string.createLightEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val induceCalm = Spell(
        name = R.string.calmInduce,
        inBook = Element.Light,
        isActive = true,
        level = 6,
        zCost = 40,
        effect = R.string.calmInduceDesc,
        addedEffect = R.string.calmInduceEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val blindingFlash = Spell(
        name = R.string.blindFlash,
        inBook = Element.Light,
        isActive = true,
        level = 8,
        zCost = 50,
        effect = R.string.blindFlashDesc,
        addedEffect = R.string.blindFlashEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val lightShield = Spell(
        name = R.string.lightShield,
        inBook = Element.Light,
        isActive = false,
        level = 10,
        zCost = 50,
        effect = R.string.lightShieldDesc,
        addedEffect = R.string.lightShieldEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Defense)
    )

    private val perceiveSpell = Spell(
        name = R.string.perceive,
        inBook = Element.Light,
        isActive = true,
        level = 12,
        zCost = 50,
        effect = R.string.perceiveDesc,
        addedEffect = R.string.perceiveEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val lightArmor = Spell(
        name = R.string.lightArmor,
        inBook = Element.Light,
        isActive = true,
        level = 16,
        zCost = 60,
        effect = R.string.lightArmorDesc,
        addedEffect = R.string.lightArmorEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val banishShadows = Spell(
        name = R.string.banShadows,
        inBook = Element.Light,
        isActive = true,
        level = 18,
        zCost = 60,
        effect = R.string.banShadowsDesc,
        addedEffect = R.string.banShadowsEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val detectNegativeEmotions = Spell(
        name = R.string.findNegEmote,
        inBook = Element.Light,
        isActive = true,
        level = 20,
        zCost = 50,
        effect = R.string.findNegEmoteDesc,
        addedEffect = R.string.findNegEmoteEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Detection)
    )

    private val lightBeam = Spell(
        name = R.string.lightBeam,
        inBook = Element.Light,
        isActive = true,
        level = 22,
        zCost = 50,
        effect = R.string.lightBeamDesc,
        addedEffect = R.string.lightBeamEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val hologram = Spell(
        name = R.string.hologram,
        inBook = Element.Light,
        isActive = true,
        level = 26,
        zCost = 40,
        effect = R.string.hologramDesc,
        addedEffect = R.string.hologramEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val lightBond = Spell(
        name = R.string.lightBond,
        inBook = Element.Light,
        isActive = true,
        level = 28,
        zCost = 60,
        effect = R.string.lightBondDesc,
        addedEffect = R.string.lightBondEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val controlLight = Spell(
        name = R.string.lightControl,
        inBook = Element.Light,
        isActive = true,
        level = 30,
        zCost = 50,
        effect = R.string.lightControlDesc,
        addedEffect = R.string.lightControlEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val detectLife = Spell(
        name = R.string.detectLife,
        inBook = Element.Light,
        isActive = true,
        level = 32,
        zCost = 60,
        effect = R.string.detectLifeDesc,
        addedEffect = R.string.detectLifeEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Detection)
    )

    private val lightSpy = Spell(
        name = R.string.lightSpy,
        inBook = Element.Light,
        isActive = true,
        level = 36,
        zCost = 100,
        effect = R.string.lightSpyDesc,
        addedEffect = R.string.lightSpyEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val ecstasy = Spell(
        name = R.string.ecstasy,
        inBook = Element.Light,
        isActive = true,
        level = 38,
        zCost = 60,
        effect = R.string.ecstasyDesc,
        addedEffect = R.string.ecstasyEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val banishNegativeEmotions = Spell(
        name = R.string.banNegEmote,
        inBook = Element.Light,
        isActive = true,
        level = 40,
        zCost = 80,
        effect = R.string.banNegEmoteDesc,
        addedEffect = R.string.banNegEmoteEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val healingLight = Spell(
        name = R.string.healingLight,
        inBook = Element.Light,
        isActive = true,
        level = 42,
        zCost = 80,
        effect = R.string.healingLightDesc,
        addedEffect = R.string.healingLightEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val seekingSphere = Spell(
        name = R.string.seekSphere,
        inBook = Element.Light,
        isActive = true,
        level = 46,
        zCost = 120,
        effect = R.string.seekSphereDesc,
        addedEffect = R.string.seekSphereEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val detectionZone = Spell(
        name = R.string.detectZone,
        inBook = Element.Light,
        isActive = true,
        level = 48,
        zCost = 140,
        effect = R.string.detectZoneDesc,
        addedEffect = R.string.detectZoneEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Detection)
    )

    private val enterDreams = Spell(
        name = R.string.enterDreams,
        inBook = Element.Light,
        isActive = true,
        level = 50,
        zCost = 120,
        effect = R.string.enterDreamsDesc,
        addedEffect = R.string.enterDreamsEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val lightForm = Spell(
        name = R.string.lightForm,
        inBook = Element.Light,
        isActive = true,
        level = 52,
        zCost = 100,
        effect = R.string.lightFormDesc,
        addedEffect = R.string.lightFormEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val blessingSpell = Spell(
        name = R.string.blessing,
        inBook = Element.Light,
        isActive = true,
        level = 56,
        zCost = 100,
        effect = R.string.blessingDesc,
        addedEffect = R.string.blessingEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val createGoodFeelings = Spell(
        name = R.string.goodFeelings,
        inBook = Element.Light,
        isActive = true,
        level = 58,
        zCost = 100,
        effect = R.string.goodFeelingsDesc,
        addedEffect = R.string.goodFeelingsEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val seeTruth = Spell(
        name = R.string.seeTruth,
        inBook = Element.Light,
        isActive = true,
        level = 60,
        zCost = 100,
        effect = R.string.seeTruthDesc,
        addedEffect = R.string.seeTruthEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val shieldFromNegative = Spell(
        name = R.string.shieldNegative,
        inBook = Element.Light,
        isActive = true,
        level = 62,
        zCost = 140,
        effect = R.string.shieldNegativeDesc,
        addedEffect = R.string.shieldNegativeEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val findSpell = Spell(
        name = R.string.find,
        inBook = Element.Light,
        isActive = true,
        level = 66,
        zCost = 160,
        effect = R.string.findDesc,
        addedEffect = R.string.findEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Detection)
    )

    private val restoreSpell = Spell(
        name = R.string.restore,
        inBook = Element.Light,
        isActive = true,
        level = 68,
        zCost = 160,
        effect = R.string.restoreDesc,
        addedEffect = R.string.restoreEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val hypnoticDisplay = Spell(
        name = R.string.hypnoDisplay,
        inBook = Element.Light,
        isActive = true,
        level = 70,
        zCost = 140,
        effect = R.string.hypnoDisplayDesc,
        addedEffect = R.string.hypnoDisplayEff,
        zMax = 20,
        maintenance = 50,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val catastrophicLight = Spell(
        name = R.string.catastrophicLight,
        inBook = Element.Light,
        isActive = true,
        level = 72,
        zCost = 120,
        effect = R.string.catastrophicLightDesc,
        addedEffect = R.string.catastrophicLightEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val luminousMaterial = Spell(
        name = R.string.lumMat,
        inBook = Element.Light,
        isActive = true,
        level = 76,
        zCost = 150,
        effect = R.string.lumMatDesc,
        addedEffect = R.string.lumMatEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val lightTravel = Spell(
        name = R.string.lightTravel,
        inBook = Element.Light,
        isActive = true,
        level = 78,
        zCost = 250,
        effect = R.string.lightTravelDesc,
        addedEffect = R.string.lightTravelEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val dreamLordship = Spell(
        name = R.string.dreamLord,
        inBook = Element.Light,
        isActive = true,
        level = 80,
        zCost = 300,
        effect = R.string.dreamLordDesc,
        addedEffect = R.string.dreamLordEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val createLightBeing = Spell(
        name = R.string.lightBeing,
        inBook = Element.Light,
        isActive = true,
        level = 82,
        zCost = 250,
        effect = R.string.lightBeingDesc,
        addedEffect = R.string.lightBeingEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val reflectingPrism = Spell(
        name = R.string.reflectPrism,
        inBook = Element.Light,
        isActive = false,
        level = 86,
        zCost = 160,
        effect = R.string.reflectPrismDesc,
        addedEffect = R.string.reflectPrismEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Defense)
    )

    private val omniscienceRadius = Spell(
        name = R.string.omniscience,
        inBook = Element.Light,
        isActive = true,
        level = 88,
        zCost = 200,
        effect = R.string.omniscienceDesc,
        addedEffect = R.string.omniscienceEff,
        zMax = 20,
        maintenance = 5,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val predict = Spell(
        name = R.string.predict,
        inBook = Element.Light,
        isActive = true,
        level = 90,
        zCost = 200,
        effect = R.string.predictDesc,
        addedEffect = R.string.predictEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val lightPrison = Spell(
        name = R.string.lightPrison,
        inBook = Element.Light,
        isActive = true,
        level = 92,
        zCost = 200,
        effect = R.string.lightPrisonDesc,
        addedEffect = R.string.lightPrisonEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val oneWithLight = Spell(
        name = R.string.oneLight,
        inBook = Element.Light,
        isActive = true,
        level = 96,
        zCost = 100,
        effect = R.string.oneLightDesc,
        addedEffect = R.string.oneLightEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val ascension = Spell(
        name = R.string.ascension,
        inBook = Element.Light,
        isActive = true,
        level = 98,
        zCost = 300,
        effect = R.string.ascensionDesc,
        addedEffect = R.string.ascensionEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val lightHolocaust = Spell(
        name = R.string.holoLight,
        inBook = Element.Light,
        isActive = true,
        level = 100,
        zCost = 600,
        effect = R.string.holoLightDesc,
        addedEffect = R.string.holoLightEff,
        zMax = 50,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack, SpellType.Spiritual)
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