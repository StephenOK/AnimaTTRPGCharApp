package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.FreeSpell
import com.paetus.animaCharCreator.enumerations.SpellType
/**
 * List of spells associated with no element.
 */
class FreeSpells{
    private val tieSpell = FreeSpell(
        saveName = "tie",
        name = R.string.tie,
        isActive = true,
        level = 5,
        zCost = 40,
        effect = R.string.tieDesc,
        addedEffect = R.string.tieEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction, Element.Illusion)
    )

    private val createFire = FreeSpell(
        saveName = "createFireFree",
        name = R.string.createFireFree,
        isActive = true,
        level = 5,
        zCost = 40,
        effect = R.string.createFireFreeDesc,
        addedEffect = R.string.createFireFreeEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Water)
    )

    private val opening = FreeSpell(
        saveName = "opening",
        name = R.string.opening,
        isActive = true,
        level = 5,
        zCost = 30,
        effect = R.string.openingDesc,
        addedEffect = R.string.openingEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction, Element.Fire)
    )

    private val moveObjects = FreeSpell(
        saveName = "moveObjects",
        name = R.string.moveObjects,
        isActive = true,
        level = 5,
        zCost = 30,
        effect = R.string.moveObjectsDesc,
        addedEffect = R.string.moveObjectsEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction, Element.Earth)
    )

    private val stopFall = FreeSpell(
        saveName = "stopFall",
        name = R.string.stopFall,
        isActive = false,
        level = 5,
        zCost = 40,
        effect = R.string.stopFallDesc,
        addedEffect = R.string.stopFallEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Earth)
    )

    private val cleanliness = FreeSpell(
        saveName = "cleanliness",
        name = R.string.cleanliness,
        isActive = true,
        level = 5,
        zCost = 30,
        effect = R.string.cleanlinessDesc,
        addedEffect = R.string.cleanlinessEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val magicDetection = FreeSpell(
        saveName = "detectMag",
        name = R.string.detectMag,
        isActive = true,
        level = 5,
        zCost = 40,
        effect = R.string.detectMagDesc,
        addedEffect = R.string.detectMagEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Dark)
    )

    private val createMusic = FreeSpell(
        saveName = "createMusic",
        name = R.string.createMusic,
        isActive = true,
        level = 5,
        zCost = 40,
        effect = R.string.createMusicDesc,
        addedEffect = R.string.createMusicEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val undoWriting = FreeSpell(
        saveName = "undoWriting",
        name = R.string.undoWriting,
        isActive = true,
        level = 5,
        zCost = 40,
        effect = R.string.undoWritingDesc,
        addedEffect = R.string.undoWritingEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Creation)
    )

    private val staticMessage = FreeSpell(
        saveName = "staticMessage",
        name = R.string.staticMessage,
        isActive = true,
        level = 5,
        zCost = 30,
        effect = R.string.staticMessageDesc,
        addedEffect = R.string.staticMessageEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val jump = FreeSpell(
        saveName = "jump",
        name = R.string.jump,
        isActive = true,
        level = 5,
        zCost = 50,
        effect = R.string.jumpDesc,
        addedEffect = R.string.jumpEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Earth)
    )

    private val changeColor = FreeSpell(
        saveName = "changeColor",
        name = R.string.changeColor,
        isActive = true,
        level = 5,
        zCost = 30,
        effect = R.string.changeColorDesc,
        addedEffect = R.string.changeColorEff,
        zMax = 10,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Destruction)
    )

    val firstBook = listOf(
        tieSpell,
        createFire,
        opening,
        moveObjects,
        stopFall,
        cleanliness,
        magicDetection,
        createMusic,
        undoWriting,
        staticMessage,
        jump,
        changeColor
    )

    private val createSounds = FreeSpell(
        saveName = "createSounds",
        name = R.string.createSounds,
        isActive = true,
        level = 15,
        zCost = 40,
        effect = R.string.createSoundsDesc,
        addedEffect = R.string.createSoundsEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val fog = FreeSpell(
        saveName = "fog",
        name = R.string.fog,
        isActive = true,
        level = 15,
        zCost = 60,
        effect = R.string.fogDesc,
        addedEffect = R.string.fogEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Fire)
    )

    private val breatheLiquids = FreeSpell(
        saveName = "breatheLiquids",
        name = R.string.breatheLiquids,
        isActive = true,
        level = 15,
        zCost = 40,
        effect = R.string.breatheLiquidsDesc,
        addedEffect = R.string.breatheLiquidsEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Earth, Element.Fire)
    )

    private val enchantSpell = FreeSpell(
        saveName = "enchant",
        name = R.string.enchant,
        isActive = true,
        level = 15,
        zCost = 50,
        effect = R.string.enchantDesc,
        addedEffect = R.string.enchantEff,
        zMax = 10,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val recreateImage = FreeSpell(
        saveName = "recreateImage",
        name = R.string.recreateImage,
        isActive = true,
        level = 15,
        zCost = 40,
        effect = R.string.recreateImageDesc,
        addedEffect = R.string.recreateImageEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val repair = FreeSpell(
        saveName = "repair",
        name = R.string.repair,
        isActive = true,
        level = 15,
        zCost = 60,
        effect = R.string.repairDesc,
        addedEffect = R.string.repairEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction, Element.Illusion)
    )

    private val climb = FreeSpell(
        saveName = "climb",
        name = R.string.climb,
        isActive = true,
        level = 15,
        zCost = 50,
        effect = R.string.climbDesc,
        addedEffect = R.string.climbEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Air)
    )

    private val passWithoutTrace = FreeSpell(
        saveName = "passWithoutTrace",
        name = R.string.passWithoutTrace,
        isActive = true,
        level = 15,
        zCost = 60,
        effect = R.string.passWithoutTraceDesc,
        addedEffect = R.string.passWithoutTraceEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Light)
    )

    private val slipperyArea = FreeSpell(
        saveName = "slipperyArea",
        name = R.string.slipperyArea,
        isActive = true,
        level = 15,
        zCost = 50,
        effect = R.string.slipperyAreaDesc,
        addedEffect = R.string.slipperyAreaEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Fire)
    )

    private val attractMinorVermin = FreeSpell(
        saveName = "attractVermin",
        name = R.string.attractVermin,
        isActive = true,
        level = 15,
        zCost = 30,
        effect = R.string.attractVerminDesc,
        addedEffect = R.string.attractVerminEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val infiniteBag = FreeSpell(
        saveName = "infiniteBag",
        name = R.string.infiniteBag,
        isActive = true,
        level = 15,
        zCost = 40,
        effect = R.string.infiniteBagDesc,
        addedEffect = R.string.infiniteBagEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val inhumanity = FreeSpell(
        saveName = "inhumanity",
        name = R.string.inhumanity,
        isActive = true,
        level = 15,
        zCost = 50,
        effect = R.string.inhumanitySpellDesc,
        addedEffect = R.string.inhumanitySpellEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    val secondBook = listOf(
        createSounds,
        fog,
        breatheLiquids,
        enchantSpell,
        recreateImage,
        repair,
        climb,
        passWithoutTrace,
        slipperyArea,
        attractMinorVermin,
        infiniteBag,
        inhumanity
    )

    private val closeWithMagic = FreeSpell(
        saveName = "magicClose",
        name = R.string.magicClose,
        isActive = true,
        level = 25,
        zCost = 100,
        effect = R.string.magicCloseDesc,
        addedEffect = R.string.magicCloseEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val causeFear = FreeSpell(
        saveName = "causeFear",
        name = R.string.causeFear,
        isActive = true,
        level = 25,
        zCost = 100,
        effect = R.string.causeFearDesc,
        addedEffect = R.string.causeFearEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Light)
    )

    private val understandLanguage = FreeSpell(
        saveName = "understandLanguage",
        name = R.string.understandLanguage,
        isActive = true,
        level = 25,
        zCost = 100,
        effect = R.string.understandLanguageDesc,
        addedEffect = R.string.understandLanguageEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val net = FreeSpell(
        saveName = "net",
        name = R.string.net,
        isActive = true,
        level = 25,
        zCost = 60,
        effect = R.string.netDesc,
        addedEffect = R.string.netEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val serenity = FreeSpell(
        saveName = "serenity",
        name = R.string.serenity,
        isActive = true,
        level = 25,
        zCost = 50,
        effect = R.string.serenityDesc,
        addedEffect = R.string.serenityEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Fire, Element.Dark)
    )

    private val magicShield = FreeSpell(
        saveName = "magicShield",
        name = R.string.magicShield,
        isActive = false,
        level = 25,
        zCost = 60,
        effect = R.string.magicShieldDesc,
        addedEffect = R.string.magicShieldEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Defense),
        forbiddenElements = listOf()
    )

    private val magicalProtection = FreeSpell(
        saveName = "magProtection",
        name = R.string.magProtection,
        isActive = true,
        level = 25,
        zCost = 60,
        effect = R.string.magProtectionDesc,
        addedEffect = R.string.magProtectionEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val clouds = FreeSpell(
        saveName = "clouds",
        name = R.string.clouds,
        isActive = true,
        level = 25,
        zCost = 80,
        effect = R.string.cloudsDesc,
        addedEffect = R.string.cloudsEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Fire, Element.Earth)
    )

    private val sendMessage = FreeSpell(
        saveName = "sendMessage",
        name = R.string.sendMessage,
        isActive = true,
        level = 25,
        zCost = 80,
        effect = R.string.sendMessageDesc,
        addedEffect = R.string.sendMessageEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val speed = FreeSpell(
        saveName = "speedFree",
        name = R.string.speedFree,
        isActive = true,
        level = 25,
        zCost = 80,
        effect = R.string.speedFreeDesc,
        addedEffect = R.string.speedFreeEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Earth)
    )

    private val levitation = FreeSpell(
        saveName = "levitation",
        name = R.string.levitation,
        isActive = true,
        level = 25,
        zCost = 50,
        effect = R.string.levitationSpellDesc,
        addedEffect = R.string.levitationSpellEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Earth)
    )

    private val contraceptiveProtection = FreeSpell(
        saveName = "contraceptiveProtection",
        name = R.string.contraceptiveProtection,
        isActive = true,
        level = 25,
        zCost = 60,
        effect = R.string.contraceptiveProtectionDesc,
        addedEffect = R.string.contraceptiveProtectionEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    val thirdBook = listOf(
        closeWithMagic,
        causeFear,
        understandLanguage,
        net,
        serenity,
        magicShield,
        magicalProtection,
        clouds,
        sendMessage,
        speed,
        levitation,
        contraceptiveProtection
    )

    private val painResistance = FreeSpell(
        saveName = "painResistance",
        name = R.string.painResistance,
        isActive = true,
        level = 35,
        zCost = 60,
        effect = R.string.painResistanceDesc,
        addedEffect = R.string.painResistanceEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Essence)
    )

    private val trueClose = FreeSpell(
        saveName = "trueClose",
        name = R.string.trueClose,
        isActive = true,
        level = 35,
        zCost = 80,
        effect = R.string.trueCloseDesc,
        addedEffect = R.string.trueCloseEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val purification = FreeSpell(
        saveName = "purification",
        name = R.string.purification,
        isActive = true,
        level = 35,
        zCost = 80,
        effect = R.string.purificationDesc,
        addedEffect = R.string.purificationEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Creation)
    )

    private val extendPresence = FreeSpell(
        saveName = "extendPresence",
        name = R.string.extendPresence,
        isActive = true,
        level = 35,
        zCost = 100,
        effect = R.string.extendPresenceDesc,
        addedEffect = R.string.extendPresenceEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val alterSize = FreeSpell(
        saveName = "alterSize",
        name = R.string.alterSize,
        isActive = true,
        level = 35,
        zCost = 80,
        effect = R.string.alterSizeDesc,
        addedEffect = R.string.alterSizeEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val invokeAggressiveness = FreeSpell(
        saveName = "invokeAggressive",
        name = R.string.invokeAggressive,
        isActive = true,
        level = 35,
        zCost = 80,
        effect = R.string.invokeAggressiveDesc,
        addedEffect = R.string.invokeAggressiveEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic),
        forbiddenElements = listOf(Element.Light)
    )

    private val outlookChange = FreeSpell(
        saveName = "outlookChange",
        name = R.string.outlookChange,
        isActive = true,
        level = 35,
        zCost = 80,
        effect = R.string.outlookChangeDesc,
        addedEffect = R.string.outlookChangeEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect, SpellType.Spiritual),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val healDiseases = FreeSpell(
        saveName = "healDisease",
        name = R.string.healDisease,
        isActive = true,
        level = 35,
        zCost = 80,
        effect = R.string.healDiseaseDesc,
        addedEffect = R.string.healDiseaseEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val magicBeam = FreeSpell(
        saveName = "magicBeam",
        name = R.string.magBeam,
        isActive = true,
        level = 35,
        zCost = 60,
        effect = R.string.magBeamDesc,
        addedEffect = R.string.magBeamEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Attack),
        forbiddenElements = listOf(Element.Creation)
    )

    private val eliminateDreams = FreeSpell(
        saveName = "eliminateDream",
        name = R.string.eliminateDream,
        isActive = true,
        level = 35,
        zCost = 80,
        effect = R.string.eliminateDreamDesc,
        addedEffect = R.string.eliminateDreamEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Light, Element.Dark)
    )

    private val senseFeelings = FreeSpell(
        saveName = "senseFeelings",
        name = R.string.senseFeelings,
        isActive = true,
        level = 35,
        zCost = 60,
        effect = R.string.senseFeelingsDesc,
        addedEffect = R.string.senseFeelingsEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Detection),
        forbiddenElements = listOf()
    )

    private val eliminateSpells = FreeSpell(
        saveName = "eliminateSpells",
        name = R.string.spellEliminate,
        isActive = true,
        level = 35,
        zCost = 150,
        effect = R.string.spellEliminateDesc,
        addedEffect = R.string.spellEliminateEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic),
        forbiddenElements = listOf(Element.Creation)
    )

    val fourthBook = listOf(
        painResistance,
        trueClose,
        purification,
        extendPresence,
        alterSize,
        invokeAggressiveness,
        outlookChange,
        healDiseases,
        magicBeam,
        eliminateDreams,
        senseFeelings,
        eliminateSpells
    )

    private val friendship = FreeSpell(
        saveName = "friendship",
        name = R.string.friendship,
        isActive = true,
        level = 45,
        zCost = 80,
        effect = R.string.friendshipDesc,
        addedEffect = R.string.friendshipEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Dark)
    )

    private val quickTransport = FreeSpell(
        saveName = "quickTransport",
        name = R.string.quickTransport,
        isActive = true,
        level = 45,
        zCost = 60,
        effect = R.string.quickTransportDesc,
        addedEffect = R.string.quickTransportEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Earth)
    )

    private val sendDreams = FreeSpell(
        saveName = "sendDreams",
        name = R.string.sendDreams,
        isActive = true,
        level = 45,
        zCost = 120,
        effect = R.string.sendDreamsDesc,
        addedEffect = R.string.sendDreamsEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic),
        forbiddenElements = listOf()
    )

    private val readMinds = FreeSpell(
        saveName = "readMinds",
        name = R.string.mindRead,
        isActive = true,
        level = 45,
        zCost = 100,
        effect = R.string.mindReadDesc,
        addedEffect = R.string.mindReadEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Dark)
    )

    private val cancelMagic = FreeSpell(
        saveName = "cancelMagic",
        name = R.string.cancelMagic,
        isActive = true,
        level = 45,
        zCost = 150,
        effect = R.string.cancelMagicDesc,
        addedEffect = R.string.cancelMagicEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Automatic),
        forbiddenElements = listOf(Element.Creation)
    )

    private val curse = FreeSpell(
        saveName = "curse",
        name = R.string.curse,
        isActive = true,
        level = 45,
        zCost = 200,
        effect = R.string.curseDesc,
        addedEffect = R.string.curseEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf()
    )

    private val absorbInfo = FreeSpell(
        saveName = "absorbInfo",
        name = R.string.absorbInfo,
        isActive = true,
        level = 45,
        zCost = 50,
        effect = R.string.absorbInfoDesc,
        addedEffect = R.string.absorbInfoEff,
        zMax = 20,
        maintenance = 5,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val showInvisible = FreeSpell(
        saveName = "showInvisible",
        name = R.string.showInvisible,
        isActive = true,
        level = 45,
        zCost = 60,
        effect = R.string.showInvisibleDesc,
        addedEffect = R.string.showInvisibleEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Automatic),
        forbiddenElements = listOf(Element.Dark)
    )

    private val undo = FreeSpell(
        saveName = "undo",
        name = R.string.undo,
        isActive = true,
        level = 45,
        zCost = 100,
        effect = R.string.undoDesc,
        addedEffect = R.string.undoEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Creation)
    )

    private val causeSickness = FreeSpell(
        saveName = "causeSickness",
        name = R.string.causeSickness,
        isActive = true,
        level = 45,
        zCost = 60,
        effect = R.string.causeSicknessDesc,
        addedEffect = R.string.causeSicknessEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Illusion, Element.Water)
    )

    private val slow = FreeSpell(
        saveName = "slow",
        name = R.string.slow,
        isActive = true,
        level = 45,
        zCost = 60,
        effect = R.string.slowDesc,
        addedEffect = R.string.slowEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Air)
    )

    private val alterEnergy = FreeSpell(
        saveName = "alterEnergy",
        name = R.string.alterEnergy,
        isActive = true,
        level = 45,
        zCost = 100,
        effect = R.string.alterEnergyDesc,
        addedEffect = R.string.alterEnergyEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Destruction)
    )

    val fifthBook = listOf(
        friendship,
        quickTransport,
        sendDreams,
        readMinds,
        cancelMagic,
        curse,
        absorbInfo,
        showInvisible,
        undo,
        causeSickness,
        slow,
        alterEnergy
    )

    private val blindness = FreeSpell(
        saveName = "blindness",
        name = R.string.blindness,
        isActive = true,
        level = 55,
        zCost = 80,
        effect = R.string.blindnessDesc,
        addedEffect = R.string.blindnessEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Creation, Element.Light)
    )

    private val deafness = FreeSpell(
        saveName = "deafness",
        name = R.string.deafnessSpell,
        isActive = true,
        level = 55,
        zCost = 80,
        effect = R.string.deafnessSpellDesc,
        addedEffect = R.string.deafnessSpellEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Creation)
    )

    private val muteness = FreeSpell(
        saveName = "muteness",
        name = R.string.muteness,
        isActive = true,
        level = 55,
        zCost = 80,
        effect = R.string.mutenessDesc,
        addedEffect = R.string.mutenessEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Creation)
    )

    private val healWounds = FreeSpell(
        saveName = "healWounds",
        name = R.string.healWounds,
        isActive = true,
        level = 55,
        zCost = 100,
        effect = R.string.healWoundsDesc,
        addedEffect = R.string.healWoundsEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val visualizeCartography = FreeSpell(
        saveName = "visualCartography",
        name = R.string.visualCartography,
        isActive = true,
        level = 55,
        zCost = 60,
        effect = R.string.visualCartographyDesc,
        addedEffect = R.string.visualCartographyEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Dark)
    )

    private val sleep = FreeSpell(
        saveName = "sleep",
        name = R.string.sleep,
        isActive = true,
        level = 55,
        zCost = 80,
        effect = R.string.sleepDesc,
        addedEffect = R.string.sleepEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf()
    )

    private val walkOnWalls = FreeSpell(
        saveName = "walkOnWalls",
        name = R.string.walkOnWalls,
        isActive = true,
        level = 55,
        zCost = 60,
        effect = R.string.walkOnWallsDesc,
        addedEffect = R.string.walkOnWallsEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Water)
    )

    private val magicSaddle = FreeSpell(
        saveName = "magSaddle",
        name = R.string.magSaddle,
        isActive = true,
        level = 55,
        zCost = 100,
        effect = R.string.magSaddleDesc,
        addedEffect = R.string.magSaddleEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Illusion)
    )

    private val bodyMerge = FreeSpell(
        saveName = "bodyMerge",
        name = R.string.bodyMerge,
        isActive = true,
        level = 55,
        zCost = 60,
        effect = R.string.bodyMergeDesc,
        addedEffect = R.string.bodyMergeEff,
        zMax = 10,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val eliminateFatigue = FreeSpell(
        saveName = "eliminateFatigue",
        name = R.string.eliminateFatigue,
        isActive = true,
        level = 55,
        zCost = 80,
        effect = R.string.eliminateFatigueDesc,
        addedEffect = R.string.eliminateFatigueEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Dark)
    )

    private val acidCloud = FreeSpell(
        saveName = "acidCloud",
        name = R.string.acidCloud,
        isActive = true,
        level = 55,
        zCost = 100,
        effect = R.string.acidCloudDesc,
        addedEffect = R.string.acidCloudEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Automatic),
        forbiddenElements = listOf(Element.Earth)
    )

    private val leaveUnprotected = FreeSpell(
        saveName = "leaveUnprotected",
        name = R.string.leaveUnprotected,
        isActive = true,
        level = 55,
        zCost = 80,
        effect = R.string.leaveUnprotectedDesc,
        addedEffect = R.string.leaveUnprotectedEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Creation)
    )

    val sixthBook = listOf(
        blindness,
        deafness,
        muteness,
        healWounds,
        visualizeCartography,
        sleep,
        walkOnWalls,
        magicSaddle,
        bodyMerge,
        eliminateFatigue,
        acidCloud,
        leaveUnprotected
    )

    private val increasePsyChar = FreeSpell(
        saveName = "increasePsyChar",
        name = R.string.psyCharIncrease,
        isActive = true,
        level = 65,
        zCost = 100,
        effect = R.string.psyCharIncreaseDesc,
        addedEffect = R.string.psyCharIncreaseEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val magicalWeapon = FreeSpell(
        saveName = "magWeapon",
        name = R.string.magWeapon,
        isActive = true,
        level = 65,
        zCost = 140,
        effect = R.string.magWeaponDesc,
        addedEffect = R.string.magWeaponEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val increasePhysChar = FreeSpell(
        saveName = "physCharIncrease",
        name = R.string.physCharIncrease,
        isActive = true,
        level = 65,
        zCost = 80,
        effect = R.string.physCharIncreaseDesc,
        addedEffect = R.string.physCharIncreaseEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val minorAlteration = FreeSpell(
        saveName = "minorAlteration",
        name = R.string.minorAlteration,
        isActive = true,
        level = 65,
        zCost = 80,
        effect = R.string.minorAlterationDesc,
        addedEffect = R.string.minorAlterationEff,
        zMax = 20,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val bodyToMagic = FreeSpell(
        saveName = "bodyToMagic",
        name = R.string.bodyToMagic,
        isActive = true,
        level = 65,
        zCost = 100,
        effect = R.string.bodyToMagicDesc,
        addedEffect = R.string.bodyToMagicEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Earth)
    )

    private val paralyze = FreeSpell(
        saveName = "paralyze",
        name = R.string.paralyze,
        isActive = true,
        level = 65,
        zCost = 140,
        effect = R.string.paralyzeDesc,
        addedEffect = R.string.paralyzeEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Air)
    )

    private val createEmotion = FreeSpell(
        saveName = "createEmotion",
        name = R.string.createEmotion,
        isActive = true,
        level = 65,
        zCost = 160,
        effect = R.string.createEmotionDesc,
        addedEffect = R.string.createEmotionEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Illusion)
    )

    private val forgetfulness = FreeSpell(
        saveName = "forgetfulness",
        name = R.string.forgetfulnessFree,
        isActive = true,
        level = 65,
        zCost = 160,
        effect = R.string.forgetfulnessFreeEff,
        addedEffect = R.string.forgetfulnessFreeEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Essence)
    )

    private val weakness = FreeSpell(
        saveName = "weakness",
        name = R.string.weakness,
        isActive = true,
        level = 65,
        zCost = 80,
        effect = R.string.weaknessDesc,
        addedEffect = R.string.weaknessEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Creation)
    )

    private val resistSpell = FreeSpell(
        saveName = "resist",
        name = R.string.resist,
        isActive = false,
        level = 65,
        zCost = 80,
        effect = R.string.resistanceDesc,
        addedEffect = R.string.resistEff,
        zMax = 10,
        maintenance = 5,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val plague = FreeSpell(
        saveName = "plague",
        name = R.string.plague,
        isActive = true,
        level = 65,
        zCost = 140,
        effect = R.string.plagueDesc,
        addedEffect = R.string.plagueEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic),
        forbiddenElements = listOf(Element.Illusion)
    )

    private val rejection = FreeSpell(
        saveName = "rejection",
        name = R.string.rejection,
        isActive = true,
        level = 65,
        zCost = 80,
        effect = R.string.rejectionDesc,
        addedEffect = R.string.rejectionEff,
        zMax = 10,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Essence, Element.Water)
    )

    val seventhBook = listOf(
        increasePsyChar,
        magicalWeapon,
        increasePhysChar,
        minorAlteration,
        bodyToMagic,
        paralyze,
        createEmotion,
        forgetfulness,
        weakness,
        resistSpell,
        plague,
        rejection
    )

    private val invisibility = FreeSpell(
        saveName = "invisibility",
        name = R.string.invisibility,
        isActive = true,
        level = 75,
        zCost = 140,
        effect = R.string.invisibilityDesc,
        addedEffect = R.string.invisibilityEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Essence)
    )

    private val levitationSphere = FreeSpell(
        saveName = "levitationSphere",
        name = R.string.levitationSphere,
        isActive = true,
        level = 75,
        zCost = 150,
        effect = R.string.levitationSphereDesc,
        addedEffect = R.string.levitationSphereEff,
        zMax = 20,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Earth, Element.Water)
    )

    private val uselessness = FreeSpell(
        saveName = "uselessness",
        name = R.string.uselessness,
        isActive = true,
        level = 75,
        zCost = 120,
        effect = R.string.uselessnessDesc,
        addedEffect = R.string.uselessnessEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf(Element.Water)
    )

    private val dominion = FreeSpell(
        saveName = "dominion",
        name = R.string.dominion,
        isActive = true,
        level = 75,
        zCost = 160,
        effect = R.string.dominionDesc,
        addedEffect = R.string.dominionEff,
        zMax = 20,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf()
    )

    private val deflectTrajectory = FreeSpell(
        saveName = "deflectTrajectory",
        name = R.string.deflect,
        isActive = false,
        level = 75,
        zCost = 100,
        effect = R.string.deflectDesc,
        addedEffect = R.string.deflectEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect, SpellType.Defense),
        forbiddenElements = listOf(Element.Fire)
    )

    private val stallSpell = FreeSpell(
        saveName = "stallSpell",
        name = R.string.stallSpell,
        isActive = true,
        level = 75,
        zCost = 150,
        effect = R.string.stallSpellDesc,
        addedEffect = R.string.stallSpellEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Air)
    )

    private val detectionMark = FreeSpell(
        saveName = "detectionMark",
        name = R.string.detectionMark,
        isActive = true,
        level = 75,
        zCost = 100,
        effect = R.string.detectionMarkDesc,
        addedEffect = R.string.detectionMarkEff,
        zMax = 10,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect, SpellType.Spiritual),
        forbiddenElements = listOf(Element.Dark)
    )

    private val flight = FreeSpell(
        saveName = "flightFree",
        name = R.string.freeFlight,
        isActive = true,
        level = 75,
        zCost = 100,
        effect = R.string.freeFlightDesc,
        addedEffect = R.string.freeFlightEff,
        zMax = 10,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Earth)
    )

    private val offenseErudition = FreeSpell(
        saveName = "offenseErudition",
        name = R.string.offenseErudition,
        isActive = true,
        level = 75,
        zCost = 80,
        effect = R.string.offenseEruditionDesc,
        addedEffect = R.string.offenseEruditionEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Creation)
    )

    private val defenseErudition = FreeSpell(
        saveName = "defenseErudition",
        name = R.string.defenseErudition,
        isActive = true,
        level = 75,
        zCost = 80,
        effect = R.string.defenseEruditionDesc,
        addedEffect = R.string.defenseEruditionEff,
        zMax = 10,
        maintenance = 10,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Creation)
    )

    private val containment = FreeSpell(
        saveName = "containment",
        name = R.string.containment,
        isActive = true,
        level = 75,
        zCost = 200,
        effect = R.string.containmentDesc,
        addedEffect = R.string.containmentEff,
        zMax = 20,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Automatic),
        forbiddenElements = listOf()
    )

    private val perfectTarget = FreeSpell(
        saveName = "perfectTarget",
        name = R.string.perfectTarget,
        isActive = true,
        level = 75,
        zCost = 80,
        effect = R.string.perfectTargetDesc,
        addedEffect = R.string.perfectTargetEff,
        zMax = 10,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    val eighthBook = listOf(
        invisibility,
        levitationSphere,
        uselessness,
        dominion,
        deflectTrajectory,
        stallSpell,
        detectionMark,
        flight,
        offenseErudition,
        defenseErudition,
        containment,
        perfectTarget
    )

    private val spellReturn = FreeSpell(
        saveName = "spellReturn",
        name = R.string.spellReturn,
        isActive = false,
        level = 85,
        zCost = 150,
        effect = R.string.spellReturnDesc,
        addedEffect = R.string.spellReturnEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic),
        forbiddenElements = listOf()
    )

    private val disenchantment = FreeSpell(
        saveName = "disenchantment",
        name = R.string.disenchantment,
        isActive = true,
        level = 85,
        zCost = 200,
        effect = R.string.disenchantmentDesc,
        addedEffect = R.string.disenchantmentEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Creation)
    )

    private val naturalSpell = FreeSpell(
        saveName = "naturalSpell",
        name = R.string.naturalSpell,
        isActive = true,
        level = 85,
        zCost = 350,
        effect = R.string.naturalSpellDesc,
        addedEffect = R.string.naturalSpellEff,
        zMax = 30,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val stealSpell = FreeSpell(
        saveName = "stealSpell",
        name = R.string.stealSpell,
        isActive = true,
        level = 85,
        zCost = 200,
        effect = R.string.stealSpellDesc,
        addedEffect = R.string.stealSpellEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Automatic),
        forbiddenElements = listOf()
    )

    private val immortality = FreeSpell(
        saveName = "immortality",
        name = R.string.immortality,
        isActive = true,
        level = 85,
        zCost = 300,
        effect = R.string.immortalityDesc,
        addedEffect = R.string.immortalityEff,
        zMax = 30,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val magicPrism = FreeSpell(
        saveName = "magPrism",
        name = R.string.magPrism,
        isActive = true,
        level = 85,
        zCost = 200,
        effect = R.string.magPrismDesc,
        addedEffect = R.string.magPrismEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val eliminateNeeds = FreeSpell(
        saveName = "eliminateNeeds",
        name = R.string.eliminateNeeds,
        isActive = true,
        level = 85,
        zCost = 300,
        effect = R.string.eliminateNeedsDesc,
        addedEffect = R.string.eliminateNeedsEff,
        zMax = 30,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Essence)
    )

    private val prepareSpell = FreeSpell(
        saveName = "prepareSpell",
        name = R.string.prepareSpell,
        isActive = true,
        level = 85,
        zCost = 200,
        effect = R.string.prepareSpellDesc,
        addedEffect = R.string.prepareSpellEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val physicalImmunity = FreeSpell(
        saveName = "physImmunity",
        name = R.string.physImmunity,
        isActive = true,
        level = 85,
        zCost = 200,
        effect = R.string.physImmunityDesc,
        addedEffect = R.string.physImmunityEff,
        zMax = 20,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Essence)
    )

    private val gate = FreeSpell(
        saveName = "gate",
        name = R.string.gate,
        isActive = true,
        level = 85,
        zCost = 500,
        effect = R.string.gateDesc,
        addedEffect = R.string.gateEff,
        zMax = 50,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val teletransportation = FreeSpell(
        saveName = "teletransportation",
        name = R.string.teletransportation,
        isActive = true,
        level = 85,
        zCost = 300,
        effect = R.string.teletransportationDesc,
        addedEffect = R.string.teletransportationEff,
        zMax = 40,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Earth)
    )

    private val location = FreeSpell(
        saveName = "location",
        name = R.string.location,
        isActive = true,
        level = 85,
        zCost = 300,
        effect = R.string.locationDesc,
        addedEffect = R.string.locationEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Detection),
        forbiddenElements = listOf(Element.Dark)
    )

    val ninthBook = listOf(
        spellReturn,
        disenchantment,
        naturalSpell,
        stealSpell,
        immortality,
        magicPrism,
        eliminateNeeds,
        prepareSpell,
        physicalImmunity,
        gate,
        teletransportation,
        location
    )

    private val eyeOfTime = FreeSpell(
        saveName = "eyeOfTime",
        name = R.string.timeEye,
        isActive = true,
        level = 95,
        zCost = 300,
        effect = R.string.timeEyeDesc,
        addedEffect = R.string.timeEyeEff,
        zMax = 50,
        maintenance = 20,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Dark)
    )

    private val possession = FreeSpell(
        saveName = "possession",
        name = R.string.possession,
        isActive = true,
        level = 95,
        zCost = 300,
        effect = R.string.possessionDesc,
        addedEffect = R.string.possessionEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf()
    )

    private val powerShield = FreeSpell(
        saveName = "powerShield",
        name = R.string.powerShield,
        isActive = true,
        level = 95,
        zCost = 300,
        effect = R.string.powerShieldDesc,
        addedEffect = R.string.powerShieldEff,
        zMax = 30,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic),
        forbiddenElements = listOf(Element.Creation)
    )

    private val seal = FreeSpell(
        saveName = "seal",
        name = R.string.seal,
        isActive = true,
        level = 95,
        zCost = 200,
        effect = R.string.sealDesc,
        addedEffect = R.string.sealEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val imitateSpell = FreeSpell(
        saveName = "imitateSpell",
        name = R.string.imitateSpell,
        isActive = false,
        level = 95,
        zCost = 200,
        effect = R.string.imitateSpellDesc,
        addedEffect = R.string.imitateSpellEff,
        zMax = 30,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val conditioning = FreeSpell(
        saveName = "conditioning",
        name = R.string.conditioning,
        isActive = true,
        level = 95,
        zCost = 300,
        effect = R.string.conditioningDesc,
        addedEffect = R.string.conditioningEff,
        zMax = 40,
        maintenance = 5,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val linkMaintenance = FreeSpell(
        saveName = "linkMaintenance",
        name = R.string.linkMaintenance,
        isActive = true,
        level = 95,
        zCost = 200,
        effect = R.string.linkMaintenanceDesc,
        addedEffect = R.string.linkMaintenanceEff,
        zMax = 20,
        maintenance = null,
        isDaily = false,
        type = listOf(SpellType.Spiritual),
        forbiddenElements = listOf()
    )

    private val magistrate = FreeSpell(
        saveName = "magistrate",
        name = R.string.magistrate,
        isActive = true,
        level = 95,
        zCost = 450,
        effect = R.string.magistrateDesc,
        addedEffect = R.string.magistrateEff,
        zMax = 40,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Automatic),
        forbiddenElements = listOf()
    )

    private val giftOfKnowledge = FreeSpell(
        saveName = "knowledgeGift",
        name = R.string.knowledgeGift,
        isActive = true,
        level = 95,
        zCost = 300,
        effect = R.string.knowledgeGiftDesc,
        addedEffect = R.string.knowledgeGiftEff,
        zMax = 50,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val strengthenMagic = FreeSpell(
        saveName = "magicStrengthen",
        name = R.string.magicStrengthen,
        isActive = true,
        level = 95,
        zCost = 200,
        effect = R.string.magicStrengthenDesc,
        addedEffect = R.string.magicStrengthenEff,
        zMax = 40,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf(Element.Destruction)
    )

    private val innateMagic = FreeSpell(
        saveName = "innateMagic",
        name = R.string.innateMagicSpell,
        isActive = true,
        level = 95,
        zCost = 500,
        effect = R.string.innateMagicSpellDesc,
        addedEffect = R.string.innateMagicSpellEff,
        zMax = 50,
        maintenance = 10,
        isDaily = true,
        type = listOf(SpellType.Effect),
        forbiddenElements = listOf()
    )

    private val predestination = FreeSpell(
        saveName = "predestination",
        name = R.string.predestination,
        isActive = true,
        level = 95,
        zCost = 600,
        effect = R.string.predestinationDesc,
        addedEffect = R.string.predestinationEff,
        zMax = 40,
        maintenance = 20,
        isDaily = true,
        type = listOf(SpellType.Automatic),
        forbiddenElements = listOf()
    )

    val tenthBook = listOf(
        eyeOfTime,
        possession,
        powerShield,
        seal,
        imitateSpell,
        conditioning,
        linkMaintenance,
        magistrate,
        giftOfKnowledge,
        strengthenMagic,
        innateMagic,
        predestination
    )

    //compile all free spell books
    val allFreeSpells = listOf(
        firstBook,
        secondBook,
        thirdBook,
        fourthBook,
        fifthBook,
        sixthBook,
        seventhBook,
        eighthBook,
        ninthBook,
        tenthBook
    )

    //create generic placeholder spell
    val generic = FreeSpell(
        saveName = "PlaceHolder",
        name = R.string.emptyItem,
        isActive = false,
        level = 0,
        zCost = 0,
        effect = R.string.emptyItem,
        addedEffect = R.string.emptyItem,
        zMax = 0,
        maintenance = null,
        isDaily = false,
        type = listOf(),
        forbiddenElements = listOf()
    )

    /**
     * Finds the free spell with the given save name.
     *
     * @param saveName queried name to find the spell
     */
    fun findFreeSpell(saveName: String): FreeSpell{
        //search each free spell book
        allFreeSpells.forEach{book ->
            //return a match if found
            book.forEach{spell ->
                if(spell.saveName == saveName) return spell
            }
        }

        //return generic item if no match found
        return generic
    }
}