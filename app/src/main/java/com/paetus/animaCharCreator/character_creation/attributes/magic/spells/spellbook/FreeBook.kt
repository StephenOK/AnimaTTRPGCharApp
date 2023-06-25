package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.FreeSpell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType
/**
 * List of spells associated with no element.
 */
class FreeBook{
    private val tieSpell = FreeSpell(
        "tie",
        R.string.tie,
        true,
        5,
        40,
        R.string.tieDesc,
        R.string.tieEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction, Element.Illusion)
    )

    private val createFire = FreeSpell(
        "createFireFree",
        R.string.createFireFree,
        true,
        5,
        40,
        R.string.createFireFreeDesc,
        R.string.createFireFreeEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Water)
    )

    private val opening = FreeSpell(
        "opening",
        R.string.opening,
        true,
        5,
        30,
        R.string.openingDesc,
        R.string.openingEff,
        20,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction, Element.Fire)
    )

    private val moveObjects = FreeSpell(
        "moveObjects",
        R.string.moveObjects,
        true,
        5,
        30,
        R.string.moveObjectsDesc,
        R.string.moveObjectsEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction, Element.Earth)
    )

    private val stopFall = FreeSpell(
        "stopFall",
        R.string.stopFall,
        false,
        5,
        40,
        R.string.stopFallDesc,
        R.string.stopFallEff,
        20,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val cleanliness = FreeSpell(
        "cleanliness",
        R.string.cleanliness,
        true,
        5,
        30,
        R.string.cleanlinessDesc,
        R.string.cleanlinessEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val magicDetection = FreeSpell(
        "detectMag",
        R.string.detectMag,
        true,
        5,
        40,
        R.string.detectMagDesc,
        R.string.detectMagEff,
        10,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Dark)
    )

    private val createMusic = FreeSpell(
        "createMusic",
        R.string.createMusic,
        true,
        5,
        40,
        R.string.createMusicDesc,
        R.string.createMusicEff,
        10,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val undoWriting = FreeSpell(
        "undoWriting",
        R.string.undoWriting,
        true,
        5,
        40,
        R.string.undoWritingDesc,
        R.string.undoWritingEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Creation)
    )

    private val staticMessage = FreeSpell(
        "staticMessage",
        R.string.staticMessage,
        true,
        5,
        30,
        R.string.staticMessageDesc,
        R.string.staticMessageEff,
        20,
        20,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val jump = FreeSpell(
        "jump",
        R.string.jump,
        true,
        5,
        50,
        R.string.jumpDesc,
        R.string.jumpEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val changeColor = FreeSpell(
        "changeColor",
        R.string.changeColor,
        true,
        5,
        30,
        R.string.changeColorDesc,
        R.string.changeColorEff,
        10,
        20,
        true,
        listOf(SpellType.Spiritual),
        listOf(Element.Destruction)
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
        "createSounds",
        R.string.createSounds,
        true,
        15,
        40,
        R.string.createSoundsDesc,
        R.string.createSoundsEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val fog = FreeSpell(
        "fog",
        R.string.fog,
        true,
        15,
        60,
        R.string.fogDesc,
        R.string.fogEff,
        20,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Fire)
    )

    private val breatheLiquids = FreeSpell(
        "breatheLiquids",
        R.string.breatheLiquids,
        true,
        15,
        40,
        R.string.breatheLiquidsDesc,
        R.string.breatheLiquidsEff,
        10,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Earth, Element.Fire)
    )

    private val enchantSpell = FreeSpell(
        "enchant",
        R.string.enchant,
        true,
        15,
        50,
        R.string.enchantDesc,
        R.string.enchantEff,
        10,
        20,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val recreateImage = FreeSpell(
        "recreateImage",
        R.string.recreateImage,
        true,
        15,
        40,
        R.string.recreateImageDesc,
        R.string.recreateImageEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val repair = FreeSpell(
        "repair",
        R.string.repair,
        true,
        15,
        60,
        R.string.repairDesc,
        R.string.repairEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction, Element.Illusion)
    )

    private val climb = FreeSpell(
        "climb",
        R.string.climb,
        true,
        15,
        50,
        R.string.climbDesc,
        R.string.climbEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Air)
    )

    private val passWithoutTrace = FreeSpell(
        "passWithoutTrace",
        R.string.passWithoutTrace,
        true,
        15,
        60,
        R.string.passWithoutTraceDesc,
        R.string.passWithoutTraceEff,
        30,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Light)
    )

    private val slipperyArea = FreeSpell(
        "slipperyArea",
        R.string.slipperyArea,
        true,
        15,
        50,
        R.string.slipperyAreaDesc,
        R.string.slipperyAreaEff,
        20,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Fire)
    )

    private val attractMinorVermin = FreeSpell(
        "attractVermin",
        R.string.attractVermin,
        true,
        15,
        30,
        R.string.attractVerminDesc,
        R.string.attractVerminEff,
        10,
        20,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val infiniteBag = FreeSpell(
        "infiniteBag",
        R.string.infiniteBag,
        true,
        15,
        40,
        R.string.infiniteBagDesc,
        R.string.infiniteBagEff,
        10,
        10,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val inhumanity = FreeSpell(
        "inhumanity",
        R.string.inhumanity,
        true,
        15,
        50,
        R.string.inhumanitySpellDesc,
        R.string.inhumanitySpellEff,
        10,
        10,
        true,
        listOf(SpellType.Effect),
        listOf()
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
        "magicClose",
        R.string.magicClose,
        true,
        25,
        100,
        R.string.magicCloseDesc,
        R.string.magicCloseEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val causeFear = FreeSpell(
        "causeFear",
        R.string.causeFear,
        true,
        25,
        100,
        R.string.causeFearDesc,
        R.string.causeFearEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Light)
    )

    private val understandLanguage = FreeSpell(
        "understandLanguage",
        R.string.understandLanguage,
        true,
        25,
        100,
        R.string.understandLanguageDesc,
        R.string.understandLanguageEff,
        20,
        5,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val net = FreeSpell(
        "net",
        R.string.net,
        true,
        25,
        60,
        R.string.netDesc,
        R.string.netEff,
        10,
        20,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val serenity = FreeSpell(
        "serenity",
        R.string.serenity,
        true,
        25,
        50,
        R.string.serenityDesc,
        R.string.serenityEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Fire, Element.Dark)
    )

    private val magicShield = FreeSpell(
        "magicShield",
        R.string.magicShield,
        false,
        25,
        60,
        R.string.magicShieldDesc,
        R.string.magicShieldEff,
        20,
        10,
        false,
        listOf(SpellType.Defense),
        listOf()
    )

    private val magicalProtection = FreeSpell(
        "magProtection",
        R.string.magProtection,
        true,
        25,
        60,
        R.string.magProtectionDesc,
        R.string.magProtectionEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val clouds = FreeSpell(
        "clouds",
        R.string.clouds,
        true,
        25,
        80,
        R.string.cloudsDesc,
        R.string.cloudsEff,
        20,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Fire, Element.Earth)
    )

    private val sendMessage = FreeSpell(
        "sendMessage",
        R.string.sendMessage,
        true,
        25,
        80,
        R.string.sendMessageDesc,
        R.string.sendMessageEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val speed = FreeSpell(
        "speedFree",
        R.string.speedFree,
        true,
        25,
        80,
        R.string.speedFreeDesc,
        R.string.speedFreeEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val levitation = FreeSpell(
        "levitation",
        R.string.levitation,
        true,
        25,
        50,
        R.string.levitationSpellDesc,
        R.string.levitationSpellEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val contraceptiveProtection = FreeSpell(
        "contraceptiveProtection",
        R.string.contraceptiveProtection,
        true,
        25,
        60,
        R.string.contraceptiveProtectionDesc,
        R.string.contraceptiveProtectionEff,
        10,
        10,
        true,
        listOf(SpellType.Effect),
        listOf()
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
        "painResistance",
        R.string.painResistance,
        true,
        35,
        60,
        R.string.painResistanceDesc,
        R.string.painResistanceEff,
        10,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Essence)
    )

    private val trueClose = FreeSpell(
        "trueClose",
        R.string.trueClose,
        true,
        35,
        80,
        R.string.trueCloseDesc,
        R.string.trueCloseEff,
        20,
        20,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val purification = FreeSpell(
        "purification",
        R.string.purification,
        true,
        35,
        80,
        R.string.purificationDesc,
        R.string.purificationEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Creation)
    )

    private val extendPresence = FreeSpell(
        "extendPresence",
        R.string.extendPresence,
        true,
        35,
        100,
        R.string.extendPresenceDesc,
        R.string.extendPresenceEff,
        20,
        5,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val alterSize = FreeSpell(
        "alterSize",
        R.string.alterSize,
        true,
        35,
        80,
        R.string.alterSizeDesc,
        R.string.alterSizeEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Destruction)
    )

    private val invokeAggressiveness = FreeSpell(
        "invokeAggressive",
        R.string.invokeAggressive,
        true,
        35,
        80,
        R.string.invokeAggressiveDesc,
        R.string.invokeAggressiveEff,
        10,
        10,
        false,
        listOf(SpellType.Automatic),
        listOf(Element.Light)
    )

    private val outlookChange = FreeSpell(
        "outlookChange",
        R.string.outlookChange,
        true,
        35,
        80,
        R.string.outlookChangeDesc,
        R.string.outlookChangeEff,
        10,
        10,
        true,
        listOf(SpellType.Effect, SpellType.Spiritual),
        listOf(Element.Destruction)
    )

    private val healDiseases = FreeSpell(
        "healDisease",
        R.string.healDisease,
        true,
        35,
        80,
        R.string.healDiseaseDesc,
        R.string.healDiseaseEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val magicBeam = FreeSpell(
        "magicBeam",
        R.string.magBeam,
        true,
        35,
        60,
        R.string.magBeamDesc,
        R.string.magBeamEff,
        10,
        null,
        false,
        listOf(SpellType.Attack),
        listOf(Element.Creation)
    )

    private val eliminateDreams = FreeSpell(
        "eliminateDream",
        R.string.eliminateDream,
        true,
        35,
        80,
        R.string.eliminateDreamDesc,
        R.string.eliminateDreamEff,
        20,
        20,
        true,
        listOf(SpellType.Spiritual),
        listOf(Element.Light, Element.Dark)
    )

    private val senseFeelings = FreeSpell(
        "senseFeelings",
        R.string.senseFeelings,
        true,
        35,
        60,
        R.string.senseFeelingsDesc,
        R.string.senseFeelingsEff,
        10,
        20,
        false,
        listOf(SpellType.Detection),
        listOf()
    )

    private val eliminateSpells = FreeSpell(
        "eliminateSpells",
        R.string.spellEliminate,
        true,
        35,
        150,
        R.string.spellEliminateDesc,
        R.string.spellEliminateEff,
        20,
        null,
        false,
        listOf(SpellType.Automatic),
        listOf(Element.Creation)
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
        "friendship",
        R.string.friendship,
        true,
        45,
        80,
        R.string.friendshipDesc,
        R.string.friendshipEff,
        10,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf(Element.Dark)
    )

    private val quickTransport = FreeSpell(
        "quickTransport",
        R.string.quickTransport,
        true,
        45,
        60,
        R.string.quickTransportDesc,
        R.string.quickTransportEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val sendDreams = FreeSpell(
        "sendDreams",
        R.string.sendDreams,
        true,
        45,
        120,
        R.string.sendDreamsDesc,
        R.string.sendDreamsEff,
        20,
        null,
        false,
        listOf(SpellType.Automatic),
        listOf()
    )

    private val readMinds = FreeSpell(
        "readMinds",
        R.string.mindRead,
        true,
        45,
        100,
        R.string.mindReadDesc,
        R.string.mindReadEff,
        20,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Dark)
    )

    private val cancelMagic = FreeSpell(
        "cancelMagic",
        R.string.cancelMagic,
        true,
        45,
        150,
        R.string.cancelMagicDesc,
        R.string.cancelMagicEff,
        20,
        20,
        false,
        listOf(SpellType.Automatic),
        listOf(Element.Creation)
    )

    private val curse = FreeSpell(
        "curse",
        R.string.curse,
        true,
        45,
        200,
        R.string.curseDesc,
        R.string.curseEff,
        20,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf()
    )

    private val absorbInfo = FreeSpell(
        "absorbInfo",
        R.string.absorbInfo,
        true,
        45,
        50,
        R.string.absorbInfoDesc,
        R.string.absorbInfoEff,
        20,
        5,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val showInvisible = FreeSpell(
        "showInvisible",
        R.string.showInvisible,
        true,
        45,
        60,
        R.string.showInvisibleDesc,
        R.string.showInvisibleEff,
        10,
        20,
        false,
        listOf(SpellType.Automatic),
        listOf(Element.Dark)
    )

    private val undo = FreeSpell(
        "undo",
        R.string.undo,
        true,
        45,
        100,
        R.string.undoDesc,
        R.string.undoEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Creation)
    )

    private val causeSickness = FreeSpell(
        "causeSickness",
        R.string.causeSickness,
        true,
        45,
        60,
        R.string.causeSicknessDesc,
        R.string.causeSicknessEff,
        10,
        null,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Illusion, Element.Water)
    )

    private val slow = FreeSpell(
        "slow",
        R.string.slow,
        true,
        45,
        60,
        R.string.slowDesc,
        R.string.slowEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Air)
    )

    private val alterEnergy = FreeSpell(
        "alterEnergy",
        R.string.alterEnergy,
        true,
        45,
        100,
        R.string.alterEnergyDesc,
        R.string.alterEnergyEff,
        10,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf(Element.Destruction)
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
        "blindness",
        R.string.blindness,
        true,
        55,
        80,
        R.string.blindnessDesc,
        R.string.blindnessEff,
        10,
        20,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Creation, Element.Light)
    )

    private val deafness = FreeSpell(
        "deafness",
        R.string.deafnessSpell,
        true,
        55,
        80,
        R.string.deafnessSpellDesc,
        R.string.deafnessSpellEff,
        10,
        20,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Creation)
    )

    private val muteness = FreeSpell(
        "muteness",
        R.string.muteness,
        true,
        55,
        80,
        R.string.mutenessDesc,
        R.string.mutenessEff,
        10,
        20,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Creation)
    )

    private val healWounds = FreeSpell(
        "healWounds",
        R.string.healWounds,
        true,
        55,
        100,
        R.string.healWoundsDesc,
        R.string.healWoundsEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val visualizeCartography = FreeSpell(
        "visualCartography",
        R.string.visualCartography,
        true,
        55,
        60,
        R.string.visualCartographyDesc,
        R.string.visualCartographyEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Dark)
    )

    private val sleep = FreeSpell(
        "sleep",
        R.string.sleep,
        true,
        55,
        80,
        R.string.sleepDesc,
        R.string.sleepEff,
        10,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf()
    )

    private val walkOnWalls = FreeSpell(
        "walkOnWalls",
        R.string.walkOnWalls,
        true,
        55,
        60,
        R.string.walkOnWallsDesc,
        R.string.walkOnWallsEff,
        10,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Water)
    )

    private val magicSaddle = FreeSpell(
        "magSaddle",
        R.string.magSaddle,
        true,
        55,
        100,
        R.string.magSaddleDesc,
        R.string.magSaddleEff,
        10,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Illusion)
    )

    private val bodyMerge = FreeSpell(
        "bodyMerge",
        R.string.bodyMerge,
        true,
        55,
        60,
        R.string.bodyMergeDesc,
        R.string.bodyMergeEff,
        10,
        5,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val eliminateFatigue = FreeSpell(
        "eliminateFatigue",
        R.string.eliminateFatigue,
        true,
        55,
        80,
        R.string.eliminateFatigueDesc,
        R.string.eliminateFatigueEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Dark)
    )

    private val acidCloud = FreeSpell(
        "acidCloud",
        R.string.acidCloud,
        true,
        55,
        100,
        R.string.acidCloudDesc,
        R.string.acidCloudEff,
        10,
        10,
        false,
        listOf(SpellType.Automatic),
        listOf(Element.Earth)
    )

    private val leaveUnprotected = FreeSpell(
        "leaveUnprotected",
        R.string.leaveUnprotected,
        true,
        55,
        80,
        R.string.leaveUnprotectedDesc,
        R.string.leaveUnprotectedEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Creation)
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
        "increasePsyChar",
        R.string.psyCharIncrease,
        true,
        65,
        100,
        R.string.psyCharIncreaseDesc,
        R.string.psyCharIncreaseEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val magicalWeapon = FreeSpell(
        "magWeapon",
        R.string.magWeapon,
        true,
        65,
        140,
        R.string.magWeaponDesc,
        R.string.magWeaponEff,
        20,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val increasePhysChar = FreeSpell(
        "physCharIncrease",
        R.string.physCharIncrease,
        true,
        65,
        80,
        R.string.physCharIncreaseDesc,
        R.string.physCharIncreaseEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val minorAlteration = FreeSpell(
        "minorAlteration",
        R.string.minorAlteration,
        true,
        65,
        80,
        R.string.minorAlterationDesc,
        R.string.minorAlterationEff,
        20,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val bodyToMagic = FreeSpell(
        "bodyToMagic",
        R.string.bodyToMagic,
        true,
        65,
        100,
        R.string.bodyToMagicDesc,
        R.string.bodyToMagicEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val paralyze = FreeSpell(
        "paralyze",
        R.string.paralyze,
        true,
        65,
        140,
        R.string.paralyzeDesc,
        R.string.paralyzeEff,
        20,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Air)
    )

    private val createEmotion = FreeSpell(
        "createEmotion",
        R.string.createEmotion,
        true,
        65,
        160,
        R.string.createEmotionDesc,
        R.string.createEmotionEff,
        20,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf(Element.Illusion)
    )

    private val forgetfulness = FreeSpell(
        "forgetfulness",
        R.string.forgetfulnessFree,
        true,
        65,
        160,
        R.string.forgetfulnessFreeEff,
        R.string.forgetfulnessFreeEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Essence)
    )

    private val weakness = FreeSpell(
        "weakness",
        R.string.weakness,
        true,
        65,
        80,
        R.string.weaknessDesc,
        R.string.weaknessEff,
        10,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Creation)
    )

    private val resistSpell = FreeSpell(
        "resist",
        R.string.resist,
        false,
        65,
        80,
        R.string.resistanceDesc,
        R.string.resistEff,
        10,
        5,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val plague = FreeSpell(
        "plague",
        R.string.plague,
        true,
        65,
        140,
        R.string.plagueDesc,
        R.string.plagueEff,
        20,
        null,
        false,
        listOf(SpellType.Automatic),
        listOf(Element.Illusion)
    )

    private val rejection = FreeSpell(
        "rejection",
        R.string.rejection,
        true,
        65,
        80,
        R.string.rejectionDesc,
        R.string.rejectionEff,
        10,
        20,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Essence, Element.Water)
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
        "invisibility",
        R.string.invisibility,
        true,
        75,
        140,
        R.string.invisibilityDesc,
        R.string.invisibilityEff,
        20,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Essence)
    )

    private val levitationSphere = FreeSpell(
        "levitationSphere",
        R.string.levitationSphere,
        true,
        75,
        150,
        R.string.levitationSphereDesc,
        R.string.levitationSphereEff,
        20,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf(Element.Earth, Element.Water)
    )

    private val uselessness = FreeSpell(
        "uselessness",
        R.string.uselessness,
        true,
        75,
        120,
        R.string.uselessnessDesc,
        R.string.uselessnessEff,
        20,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Water)
    )

    private val dominion = FreeSpell(
        "dominion",
        R.string.dominion,
        true,
        75,
        160,
        R.string.dominionDesc,
        R.string.dominionEff,
        20,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf()
    )

    private val deflectTrajectory = FreeSpell(
        "deflectTrajectory",
        R.string.deflect,
        false,
        75,
        100,
        R.string.deflectDesc,
        R.string.deflectEff,
        20,
        null,
        false,
        listOf(SpellType.Effect, SpellType.Defense),
        listOf(Element.Fire)
    )

    private val stallSpell = FreeSpell(
        "stallSpell",
        R.string.stallSpell,
        true,
        75,
        150,
        R.string.stallSpellDesc,
        R.string.stallSpellEff,
        20,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Air)
    )

    private val detectionMark = FreeSpell(
        "detectionMark",
        R.string.detectionMark,
        true,
        75,
        100,
        R.string.detectionMarkDesc,
        R.string.detectionMarkEff,
        10,
        10,
        true,
        listOf(SpellType.Effect, SpellType.Spiritual),
        listOf(Element.Dark)
    )

    private val flight = FreeSpell(
        "flightFree",
        R.string.freeFlight,
        true,
        75,
        100,
        R.string.freeFlightDesc,
        R.string.freeFlightEff,
        10,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val offenseErudition = FreeSpell(
        "offenseErudition",
        R.string.offenseErudition,
        true,
        75,
        80,
        R.string.offenseEruditionDesc,
        R.string.offenseEruditionEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Creation)
    )

    private val defenseErudition = FreeSpell(
        "defenseErudition",
        R.string.defenseErudition,
        true,
        75,
        80,
        R.string.defenseEruditionDesc,
        R.string.defenseEruditionEff,
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Creation)
    )

    private val containment = FreeSpell(
        "containment",
        R.string.containment,
        true,
        75,
        200,
        R.string.containmentDesc,
        R.string.containmentEff,
        20,
        5,
        true,
        listOf(SpellType.Automatic),
        listOf()
    )

    private val perfectTarget = FreeSpell(
        "perfectTarget",
        R.string.perfectTarget,
        true,
        75,
        80,
        R.string.perfectTargetDesc,
        R.string.perfectTargetEff,
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf()
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
        "spellReturn",
        R.string.spellReturn,
        false,
        85,
        150,
        R.string.spellReturnDesc,
        R.string.spellReturnEff,
        20,
        null,
        false,
        listOf(SpellType.Automatic),
        listOf()
    )

    private val disenchantment = FreeSpell(
        "disenchantment",
        R.string.disenchantment,
        true,
        85,
        200,
        R.string.disenchantmentDesc,
        R.string.disenchantmentEff,
        20,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Creation)
    )

    private val naturalSpell = FreeSpell(
        "naturalSpell",
        R.string.naturalSpell,
        true,
        85,
        350,
        R.string.naturalSpellDesc,
        R.string.naturalSpellEff,
        30,
        5,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val stealSpell = FreeSpell(
        "stealSpell",
        R.string.stealSpell,
        true,
        85,
        200,
        R.string.stealSpellDesc,
        R.string.stealSpellEff,
        20,
        null,
        false,
        listOf(SpellType.Automatic),
        listOf()
    )

    private val immortality = FreeSpell(
        "immortality",
        R.string.immortality,
        true,
        85,
        300,
        R.string.immortalityDesc,
        R.string.immortalityEff,
        30,
        20,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val magicPrism = FreeSpell(
        "magPrism",
        R.string.magPrism,
        true,
        85,
        200,
        R.string.magPrismDesc,
        R.string.magPrismEff,
        20,
        20,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val eliminateNeeds = FreeSpell(
        "eliminateNeeds",
        R.string.eliminateNeeds,
        true,
        85,
        300,
        R.string.eliminateNeedsDesc,
        R.string.eliminateNeedsEff,
        30,
        20,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Essence)
    )

    private val prepareSpell = FreeSpell(
        "prepareSpell",
        R.string.prepareSpell,
        true,
        85,
        200,
        R.string.prepareSpellDesc,
        R.string.prepareSpellEff,
        30,
        10,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val physicalImmunity = FreeSpell(
        "physImmunity",
        R.string.physImmunity,
        true,
        85,
        200,
        R.string.physImmunityDesc,
        R.string.physImmunityEff,
        20,
        20,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Essence)
    )

    private val gate = FreeSpell(
        "gate",
        R.string.gate,
        true,
        85,
        500,
        R.string.gateDesc,
        R.string.gateEff,
        50,
        20,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val teletransportation = FreeSpell(
        "teletransportation",
        R.string.teletransportation,
        true,
        85,
        300,
        R.string.teletransportationDesc,
        R.string.teletransportationEff,
        40,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val location = FreeSpell(
        "location",
        R.string.location,
        true,
        85,
        300,
        R.string.locationDesc,
        R.string.locationEff,
        30,
        null,
        false,
        listOf(SpellType.Detection),
        listOf(Element.Dark)
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
        "eyeOfTime",
        R.string.timeEye,
        true,
        95,
        300,
        R.string.timeEyeDesc,
        R.string.timeEyeEff,
        50,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Dark)
    )

    private val possession = FreeSpell(
        "possession",
        R.string.possession,
        true,
        95,
        300,
        R.string.possessionDesc,
        R.string.possessionEff,
        30,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf()
    )

    private val powerShield = FreeSpell(
        "powerShield",
        R.string.powerShield,
        true,
        95,
        300,
        R.string.powerShieldDesc,
        R.string.powerShieldEff,
        30,
        10,
        true,
        listOf(SpellType.Automatic),
        listOf(Element.Creation)
    )

    private val seal = FreeSpell(
        "seal",
        R.string.seal,
        true,
        95,
        200,
        R.string.sealDesc,
        R.string.sealEff,
        30,
        null,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val imitateSpell = FreeSpell(
        "imitateSpell",
        R.string.imitateSpell,
        false,
        95,
        200,
        R.string.imitateSpellDesc,
        R.string.imitateSpellEff,
        30,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val conditioning = FreeSpell(
        "conditioning",
        R.string.conditioning,
        true,
        95,
        300,
        R.string.conditioningDesc,
        R.string.conditioningEff,
        40,
        5,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val linkMaintenance = FreeSpell(
        "linkMaintenance",
        R.string.linkMaintenance,
        true,
        95,
        200,
        R.string.linkMaintenanceDesc,
        R.string.linkMaintenanceEff,
        20,
        null,
        false,
        listOf(SpellType.Spiritual),
        listOf()
    )

    private val magistrate = FreeSpell(
        "magistrate",
        R.string.magistrate,
        true,
        95,
        450,
        R.string.magistrateDesc,
        R.string.magistrateEff,
        40,
        10,
        true,
        listOf(SpellType.Automatic),
        listOf()
    )

    private val giftOfKnowledge = FreeSpell(
        "knowledgeGift",
        R.string.knowledgeGift,
        true,
        95,
        300,
        R.string.knowledgeGiftDesc,
        R.string.knowledgeGiftEff,
        50,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val strengthenMagic = FreeSpell(
        "magicStrengthen",
        R.string.magicStrengthen,
        true,
        95,
        200,
        R.string.magicStrengthenDesc,
        R.string.magicStrengthenEff,
        40,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val innateMagic = FreeSpell(
        "innateMagic",
        R.string.innateMagicSpell,
        true,
        95,
        500,
        R.string.innateMagicSpellDesc,
        R.string.innateMagicSpellEff,
        50,
        10,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    val predestination = FreeSpell(
        "predestination",
        R.string.predestination,
        true,
        95,
        600,
        R.string.predestinationDesc,
        R.string.predestinationEff,
        40,
        20,
        true,
        listOf(SpellType.Automatic),
        listOf()
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
}