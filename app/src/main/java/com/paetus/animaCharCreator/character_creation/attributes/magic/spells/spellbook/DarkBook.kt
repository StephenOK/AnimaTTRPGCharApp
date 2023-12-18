package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.MagicBook
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.enumerations.SpellType

/**
 * List of spells associated with the dark element.
 */
class DarkBook: MagicBook(Element.Dark){
    private val createDark = Spell(
        name = R.string.createDark,
        isActive = true,
        level = 2,
        zCost = 20,
        effect = R.string.createDarkDesc,
        addedEffect = R.string.createDarkEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val induceFear = Spell(
        name = R.string.induceFear,
        isActive = true,
        level = 6,
        zCost = 40,
        effect = R.string.induceFearDesc,
        addedEffect = R.string.induceFearEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val seeInDarkness = Spell(
        name = R.string.seeInDark,
        isActive = true,
        level = 8,
        zCost = 40,
        effect = R.string.seeInDarkDesc,
        addedEffect = R.string.seeInDarkEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val darkShield = Spell(
        name = R.string.darkShield,
        isActive = false,
        level = 10,
        zCost = 50,
        effect = R.string.darkShieldDesc,
        addedEffect = R.string.darkShieldEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Defense)
    )

    private val shadow = Spell(
        name = R.string.shadow,
        isActive = false,
        level = 12,
        zCost = 50,
        effect = R.string.shadowDesc,
        addedEffect = R.string.shadowEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val darkArmor = Spell(
        name = R.string.darkArmor,
        isActive = true,
        level = 16,
        zCost = 60,
        effect = R.string.darkArmorDesc,
        addedEffect = R.string.darkArmorEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val banishLight = Spell(
        name = R.string.banLight,
        isActive = true,
        level = 18,
        zCost = 60,
        effect = R.string.banLightDesc,
        addedEffect = R.string.banLightEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val hideMagic = Spell(
        name = R.string.hideMag,
        isActive = false,
        level = 20,
        zCost = 50,
        effect = R.string.hideMagDesc,
        addedEffect = R.string.hideMagEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val darkBeam = Spell(
        name = R.string.darkBeam,
        isActive = true,
        level = 22,
        zCost = 50,
        effect = R.string.darkBeamDesc,
        addedEffect = R.string.darkBeamEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val darkzone = Spell(
        name = R.string.darkZone,
        isActive = true,
        level = 26,
        zCost = 60,
        effect = R.string.darkZoneDesc,
        addedEffect = R.string.darkZoneEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val darkBond = Spell(
        name = R.string.darkBond,
        isActive = true,
        level = 28,
        zCost = 60,
        effect = R.string.darkBondDesc,
        addedEffect = R.string.darkBondEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val controlDark = Spell(
        name = R.string.controlDark,
        isActive = true,
        level = 30,
        zCost = 50,
        effect = R.string.controlDarkDesc,
        addedEffect = R.string.controlDarkEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val concealment = Spell(
        name = R.string.concealment,
        isActive = false,
        level = 32,
        zCost = 60,
        effect = R.string.concealmentDesc,
        addedEffect = R.string.concealmentEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val obfuscate = Spell(
        name = R.string.obfuscate,
        isActive = true,
        level = 36,
        zCost = 100,
        effect = R.string.obfuscateDesc,
        addedEffect = R.string.obfuscateEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val enrage = Spell(
        name = R.string.enrage,
        isActive = true,
        level = 38,
        zCost = 60,
        effect = R.string.enrageDesc,
        addedEffect = R.string.enrageEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val banishPositiveEmotions = Spell(
        name = R.string.banPosEmote,
        isActive = true,
        level = 40,
        zCost = 80,
        effect = R.string.banPosEmoteDesc,
        addedEffect = R.string.banPosEmoteEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val night = Spell(
        name = R.string.night,
        isActive = true,
        level = 42,
        zCost = 80,
        effect = R.string.nightDesc,
        addedEffect = R.string.nightEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val darkSphere = Spell(
        name = R.string.darkSphere,
        isActive = true,
        level = 46,
        zCost = 120,
        effect = R.string.darkSphereDesc,
        addedEffect = R.string.darkSphereEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val concealZone = Spell(
        name = R.string.concealZone,
        isActive = true,
        level = 48,
        zCost = 140,
        effect = R.string.concealZoneDesc,
        addedEffect = R.string.concealZoneEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Detection)
    )

    private val enterNightmare = Spell(
        name = R.string.enterNightmare,
        isActive = true,
        level = 50,
        zCost = 120,
        effect = R.string.enterNightmareDesc,
        addedEffect = R.string.enterNightmareEff,
        zMax = 20,
        maintenance = 50,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val darkForm = Spell(
        name = R.string.darkForm,
        isActive = true,
        level = 52,
        zCost = 100,
        effect = R.string.darkFormDesc,
        addedEffect = R.string.darkFormEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val perdition = Spell(
        name = R.string.perdition,
        isActive = true,
        level = 56,
        zCost = 100,
        effect = R.string.perditionDesc,
        addedEffect = R.string.perditionEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val createNegativeFeelings = Spell(
        name = R.string.createNegEmote,
        isActive = true,
        level = 58,
        zCost = 100,
        effect = R.string.createNegEmoteDesc,
        addedEffect = R.string.createNegEmoteEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val eliminateTraces = Spell(
        name = R.string.eliminateTrace,
        isActive = true,
        level = 60,
        zCost = 100,
        effect = R.string.eliminateTraceDesc,
        addedEffect = R.string.eliminateTraceEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val shieldFromPositive = Spell(
        name = R.string.shieldPositive,
        isActive = true,
        level = 62,
        zCost = 140,
        effect = R.string.shieldPositiveDesc,
        addedEffect = R.string.shieldPositiveEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic)
    )

    private val devouringDark = Spell(
        name = R.string.darkDevour,
        isActive = false,
        level = 66,
        zCost = 120,
        effect = R.string.darkDevourDesc,
        addedEffect = R.string.darkDevourEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Defense)
    )

    private val devastate = Spell(
        name = R.string.devastate,
        isActive = true,
        level = 68,
        zCost = 100,
        effect = R.string.devastateDesc,
        addedEffect = R.string.devastateEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual)
    )

    private val markOfFear = Spell(
        name = R.string.fearMark,
        isActive = true,
        level = 70,
        zCost = 140,
        effect = R.string.fearMarkDesc,
        addedEffect = R.string.fearMarkEff,
        zMax = 20,
        maintenance = 50,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val catastrophicDarkness = Spell(
        name = R.string.darkCatastrophe,
        isActive = true,
        level = 72,
        zCost = 120,
        effect = R.string.darkCatastropheDesc,
        addedEffect = R.string.darkCatastropheEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack)
    )

    private val darkMaterialObjects = Spell(
        name = R.string.darkMatObj,
        isActive = true,
        level = 76,
        zCost = 150,
        effect = R.string.darkMatObjDesc,
        addedEffect = R.string.darkMatObjEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val shadowTravel = Spell(
        name = R.string.shadowTravel,
        isActive = true,
        level = 78,
        zCost = 250,
        effect = R.string.shadowTravelDesc,
        addedEffect = R.string.shadowTravelEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val nightmareLord = Spell(
        name = R.string.nightmareLord,
        isActive = true,
        level = 80,
        zCost = 300,
        effect = R.string.nightmareLordDesc,
        addedEffect = R.string.nightmareLordEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val createDarkBeing = Spell(
        name = R.string.darkBeing,
        isActive = true,
        level = 82,
        zCost = 250,
        effect = R.string.darkBeingDesc,
        addedEffect = R.string.darkBeingEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val concealmentFromMagic = Spell(
        name = R.string.concealFromMag,
        isActive = false,
        level = 86,
        zCost = 200,
        effect = R.string.concealFromMagDesc,
        addedEffect = R.string.concealFromMagEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val darknessKingdom = Spell(
        name = R.string.darkKingdom,
        isActive = true,
        level = 88,
        zCost = 200,
        effect = R.string.darkKingdomDesc,
        addedEffect = R.string.darkKingdomEff,
        zMax = 30,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Automatic)
    )

    private val undetectable = Spell(
        name = R.string.undetectable,
        isActive = true,
        level = 90,
        zCost = 380,
        effect = R.string.undetectableDesc,
        addedEffect = R.string.undetectableEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val prisonOfDarkness = Spell(
        name = R.string.darkPrison,
        isActive = true,
        level = 92,
        zCost = 200,
        effect = R.string.darkPrisonDesc,
        addedEffect = R.string.darkPrisonEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Spiritual)
    )

    private val oneWithDarkness = Spell(
        name = R.string.darkOne,
        isActive = true,
        level = 96,
        zCost = 100,
        effect = R.string.darkOneDesc,
        addedEffect = R.string.darkOneEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect)
    )

    private val darkAscension = Spell(
        name = R.string.darkAscension,
        isActive = true,
        level = 98,
        zCost = 300,
        effect = R.string.darkAscensionDesc,
        addedEffect = R.string.darkAscensionEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect)
    )

    private val darkHolocaust = Spell(
        name = R.string.holoDark,
        isActive = true,
        level = 100,
        zCost = 600,
        effect = R.string.holoDarkDesc,
        addedEffect = R.string.holoDarkEff,
        zMax = 50,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack, SpellType.Spiritual)
    )

    init{
        fullBook.addAll(elements = listOf(
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
        ))
    }
}