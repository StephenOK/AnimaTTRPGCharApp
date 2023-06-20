package com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.Element
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage

/**
 * List of magical advantages and disadvantages a character may take.
 *
 * @param charInstance object holding the character's stats
 */
class MagicAdvantages(private val charInstance: BaseCharacter){
    val elementNames = listOf(
        "Light",
        "Dark",
        "Creation",
        "Destruction",
        "Air",
        "Earth",
        "Water",
        "Fire",
        "Essence",
        "Illusion"
    )

    val elementalCompatibility = Advantage(
        "elementalCompatibility",
        R.string.elementCompatibility,
        R.string.elementCompDesc,
        R.string.elementCompEff,
        null,
        null,
        (elementNames.toSet() + "Necromancy").toList(),
        0,
        listOf(1),
        0,
        null,
        null
    )

    val naturalPath = Advantage(
        "pathKnowledge",
        R.string.pathKnowledge,
        R.string.natPathDesc,
        R.string.natPathEff,
        null,
        R.string.natPathSpec,
        elementNames,
        0,
        listOf(1),
        0,
        {input, _ ->
            when(input){
                0 -> charInstance.magic.naturalPaths.add(Element.Light)
                1 -> charInstance.magic.naturalPaths.add(Element.Dark)
                2 -> charInstance.magic.naturalPaths.add(Element.Creation)
                3 -> charInstance.magic.naturalPaths.add(Element.Destruction)
                4 -> charInstance.magic.naturalPaths.add(Element.Air)
                5-> charInstance.magic.naturalPaths.add(Element.Earth)
                6-> charInstance.magic.naturalPaths.add(Element.Water)
                7-> charInstance.magic.naturalPaths.add(Element.Fire)
                8-> charInstance.magic.naturalPaths.add(Element.Essence)
                9-> charInstance.magic.naturalPaths.add(Element.Illusion)
                10-> charInstance.magic.naturalPaths.add(Element.Necromancy)
            }

            charInstance.magic.updateSpellList()
        },
        {input, _ ->
            when(input){
                0 -> charInstance.magic.naturalPaths.remove(Element.Light)
                1 -> charInstance.magic.naturalPaths.remove(Element.Dark)
                2 -> charInstance.magic.naturalPaths.remove(Element.Creation)
                3 -> charInstance.magic.naturalPaths.remove(Element.Destruction)
                4 -> charInstance.magic.naturalPaths.remove(Element.Air)
                5-> charInstance.magic.naturalPaths.remove(Element.Earth)
                6-> charInstance.magic.naturalPaths.remove(Element.Water)
                7-> charInstance.magic.naturalPaths.remove(Element.Fire)
                8-> charInstance.magic.naturalPaths.remove(Element.Essence)
                9-> charInstance.magic.naturalPaths.remove(Element.Illusion)
                10-> charInstance.magic.naturalPaths.remove(Element.Necromancy)
            }

            charInstance.magic.updateSpellList()
        }
    )

    val contestedSpellMastery = Advantage(
        "contestedMastery",
        R.string.contestedMastery,
        R.string.contestMasterDesc,
        R.string.contestMasterEff,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val magicDevelopmentAptitude = Advantage(
        "magDevAptitude",
        R.string.magDevAptitude,
        R.string.magDevAptDesc,
        R.string.magDevAptEff,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val halfTreeAttuned = Advantage(
        "halfTreeAttuned",
        R.string.halfTreeAttuned,
        R.string.halfTreeDesc,
        R.string.halfTreeEff,
        R.string.halfTreeRestriction,
        null,
        elementNames,
        0,
        listOf(2),
        0,
        null,
        null
    )

    val improvedInnateMagic = Advantage(
        "improvedInnate",
        R.string.improvedInnate,
        R.string.innateDesc,
        R.string.innateEff,
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val unspokenCasting = Advantage(
        "silentCast",
        R.string.silentCast,
        R.string.unspokenDesc,
        R.string.unspokenEff,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val gesturelessCasting = Advantage(
        "gesturelessCast",
        R.string.gesturelessCast,
        R.string.gesturelessDesc,
        R.string.gesturelessEff,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val superiorMagicRecovery = Advantage(
        "superiorMagRecovery",
        R.string.superiorMagRecovery,
        R.string.superRecoverDesc,
        R.string.superRecoverEff,
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.magic.changeRecoveryMult(2.0)
                2 -> charInstance.magic.changeRecoveryMult(3.0)
                3 -> charInstance.magic.changeRecoveryMult(4.0)
            }
        },
        {_, _ ->
            charInstance.magic.changeRecoveryMult(1.0)
        }
    )

    val advantages = listOf(
        naturalPath, elementalCompatibility, halfTreeAttuned, unspokenCasting, gesturelessCasting,
        contestedSpellMastery, magicDevelopmentAptitude, improvedInnateMagic, superiorMagicRecovery
    )





    val oralRequirement = Advantage(
        "oralRequire",
        R.string.oralRequire,
        R.string.requireOralDesc,
        R.string.requireOralEff,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val requireGestures = Advantage(
        "requireGestures",
        R.string.gestureRequire,
        R.string.requireGestureDesc,
        R.string.requireGestureEff,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val magicalExhaustion = Advantage(
        "magicExhaustion",
        R.string.magicExhaustion,
        R.string.magExhaustDesc,
        R.string.magExhaustEff,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val shamanism = Advantage(
        "shamanism",
        R.string.shamanism,
        R.string.shamanDesc,
        R.string.shamanEff,
        null,
        null,
        null,
        null,
        listOf(-2),
        0,
        null,
        null
    )

    val magicalTies = Advantage(
        "magicalTies",
        R.string.magicTies,
        R.string.magTiesDesc,
        R.string.magTiesEff,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.magic.setMagicTies(true)
        },
        {_, _ ->
            charInstance.magic.setMagicTies(false)
        }
    )

    val slowMagicRecovery = Advantage(
        "slowMagRecover",
        R.string.slowMagRecover,
        R.string.slowMagRecDesc,
        R.string.slowMagRecEff,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.magic.changeRecoveryMult(0.5)
        },
        {_, _ ->
            charInstance.magic.changeRecoveryMult(1.0)
        }
    )

    val magicBlockage = Advantage(
        "magicBlockage",
        R.string.magicBlockage,
        R.string.magBlockDesc,
        R.string.magBlockEff,
        R.string.magicBlockageRestriction,
        null,
        null,
        null,
        listOf(-2),
        0,
        {_, _ ->
            charInstance.magic.changeRecoveryMult(0.0)
        },
        {_, _ ->
            charInstance.magic.changeRecoveryMult(1.0)
        }
    )

    val actionRequirement = Advantage(
        "actionRequire",
        R.string.actionRequire,
        R.string.requireActionDesc,
        R.string.requireActionEff,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val disadvantages = listOf(
        oralRequirement, requireGestures, shamanism, actionRequirement, magicalExhaustion, magicalTies,
        slowMagicRecovery, magicBlockage
    )
}