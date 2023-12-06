package com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage
import com.paetus.animaCharCreator.enumerations.Element

/**
 * List of magical advantages and disadvantages a character may take.
 *
 * @param charInstance object holding the character's stats
 */
class MagicAdvantages(private val charInstance: BaseCharacter){
    val elementalCompatibility = Advantage(
        saveTag = "elementalCompatibility",
        name = R.string.elementCompatibility,
        description = R.string.elementCompDesc,
        effect = R.string.elementCompEff,
        restriction = null,
        special = null,
        options = R.array.elementList,
        picked = 0,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val naturalPath = Advantage(
        "pathKnowledge",
        R.string.pathKnowledge,
        R.string.natPathDesc,
        R.string.natPathEff,
        null,
        R.string.natPathSpec,
        R.array.elementList,
        0,
        null,
        listOf(1),
        0,
        {input, _ ->
            //add the desired element as a natural path bonus
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
        }
    ) {input, _ ->
        //remove the indicated natural path bonus
        when (input) {
            0 -> charInstance.magic.naturalPaths.remove(Element.Light)
            1 -> charInstance.magic.naturalPaths.remove(Element.Dark)
            2 -> charInstance.magic.naturalPaths.remove(Element.Creation)
            3 -> charInstance.magic.naturalPaths.remove(Element.Destruction)
            4 -> charInstance.magic.naturalPaths.remove(Element.Air)
            5 -> charInstance.magic.naturalPaths.remove(Element.Earth)
            6 -> charInstance.magic.naturalPaths.remove(Element.Water)
            7 -> charInstance.magic.naturalPaths.remove(Element.Fire)
            8 -> charInstance.magic.naturalPaths.remove(Element.Essence)
            9 -> charInstance.magic.naturalPaths.remove(Element.Illusion)
            10 -> charInstance.magic.naturalPaths.remove(Element.Necromancy)
        }

        charInstance.magic.updateSpellList()
    }

    private val contestedSpellMastery = Advantage(
        saveTag = "contestedMastery",
        name = R.string.contestedMastery,
        description = R.string.contestMasterDesc,
        effect = R.string.contestMasterEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val magicDevelopmentAptitude = Advantage(
        saveTag = "magDevAptitude",
        name = R.string.magDevAptitude,
        description = R.string.magDevAptDesc,
        effect = R.string.magDevAptEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val halfTreeAttuned = Advantage(
        saveTag = "halfTreeAttuned",
        name = R.string.halfTreeAttuned,
        description = R.string.halfTreeDesc,
        effect = R.string.halfTreeEff,
        restriction = R.string.halfTreeRestriction,
        special = null,
        options = R.array.elementList,
        picked = 0,
        multPicked = null,
        cost = listOf(2),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val improvedInnateMagic = Advantage(
        saveTag = "improvedInnate",
        name = R.string.improvedInnate,
        description = R.string.innateDesc,
        effect = R.string.innateEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val unspokenCasting = Advantage(
        saveTag = "silentCast",
        name = R.string.silentCast,
        description = R.string.unspokenDesc,
        effect = R.string.unspokenEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val gesturelessCasting = Advantage(
        saveTag = "gesturelessCast",
        name = R.string.gesturelessCast,
        description = R.string.gesturelessDesc,
        effect = R.string.gesturelessEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val superiorMagicRecovery = Advantage(
        saveTag = "superiorMagRecovery",
        name = R.string.superiorMagRecovery,
        description = R.string.superRecoverDesc,
        effect = R.string.superRecoverEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = {_, cost ->
            //apply the desired zeon recovery bonus
            when(cost){
                1 -> charInstance.magic.changeRecoveryMult(2.0)
                2 -> charInstance.magic.changeRecoveryMult(3.0)
                3 -> charInstance.magic.changeRecoveryMult(4.0)
            }
        },
        onRemove = {_, _ ->
            //reset to basic zeon recovery bonus
            charInstance.magic.changeRecoveryMult(1.0)
        }
    )

    val advantages = listOf(
        naturalPath, elementalCompatibility, halfTreeAttuned, unspokenCasting, gesturelessCasting,
        contestedSpellMastery, magicDevelopmentAptitude, improvedInnateMagic, superiorMagicRecovery
    )





    private val oralRequirement = Advantage(
        saveTag = "oralRequire",
        name = R.string.oralRequire,
        description = R.string.requireOralDesc,
        effect = R.string.requireOralEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val requireGestures = Advantage(
        saveTag = "requireGestures",
        name = R.string.gestureRequire,
        description = R.string.requireGestureDesc,
        effect = R.string.requireGestureEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val magicalExhaustion = Advantage(
        saveTag = "magicExhaustion",
        name = R.string.magicExhaustion,
        description = R.string.magExhaustDesc,
        effect = R.string.magExhaustEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val shamanism = Advantage(
        saveTag = "shamanism",
        name = R.string.shamanism,
        description = R.string.shamanDesc,
        effect = R.string.shamanEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-2),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val magicalTies = Advantage(
        saveTag = "magicalTies",
        name = R.string.magicTies,
        description = R.string.magTiesDesc,
        effect = R.string.magTiesEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = {_, _ ->
            charInstance.magic.setMagicTies(true)
        },
        onRemove = {_, _ ->
            charInstance.magic.setMagicTies(false)
        }
    )

    val slowMagicRecovery = Advantage(
        saveTag = "slowMagRecover",
        name = R.string.slowMagRecover,
        description = R.string.slowMagRecDesc,
        effect = R.string.slowMagRecEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = {_, _ ->
            //apply zeon recovery penalty
            charInstance.magic.changeRecoveryMult(0.5)
        },
        onRemove = {_, _ ->
            //remove zeon recovery penalty
            charInstance.magic.changeRecoveryMult(1.0)
        }
    )

    val magicBlockage = Advantage(
        saveTag = "magicBlockage",
        name = R.string.magicBlockage,
        description = R.string.magBlockDesc,
        effect = R.string.magBlockEff,
        restriction = R.string.magicBlockageRestriction,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-2),
        pickedCost = 0,
        onTake = {_, _ ->
            //remove any ability to recover zeon
            charInstance.magic.changeRecoveryMult(0.0)
        },
        onRemove = {_, _ ->
            //reacquire zeon recovery ability
            charInstance.magic.changeRecoveryMult(1.0)
        }
    )

    private val actionRequirement = Advantage(
        saveTag = "actionRequire",
        name = R.string.actionRequire,
        description = R.string.requireActionDesc,
        effect = R.string.requireActionEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val disadvantages = listOf(
        oralRequirement, requireGestures, shamanism, actionRequirement, magicalExhaustion, magicalTies,
        slowMagicRecovery, magicBlockage
    )
}