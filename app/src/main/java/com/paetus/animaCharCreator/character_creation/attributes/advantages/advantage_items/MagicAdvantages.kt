package com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage

/**
 * List of magical advantages and disadvantages a character may take.
 */
class MagicAdvantages{
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
        saveTag = "pathKnowledge",
        name = R.string.pathKnowledge,
        description = R.string.natPathDesc,
        effect = R.string.natPathEff,
        restriction = null,
        special = R.string.natPathSpec,
        options = R.array.elementList,
        picked = 0,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = {character, input, _ ->
            //add the desired element as a natural path bonus
            character.magic.retrieveBooks()[input!!].setNatural(isNat = true)
        },
        onRemove = {character, input, _ ->
            //remove the indicated natural path bonus
            character.magic.retrieveBooks()[input!!].setNatural(isNat = false)
        }
    )

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
        onTake = {character, _, cost ->
            //apply the desired zeon recovery bonus
            when(cost){
                1 -> character.magic.changeRecoveryMult(2.0)
                2 -> character.magic.changeRecoveryMult(3.0)
                3 -> character.magic.changeRecoveryMult(4.0)
            }
        },
        onRemove = {character, _, _ ->
            //reset to basic zeon recovery bonus
            character.magic.changeRecoveryMult(1.0)
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
        onTake = {character, _, _ ->
            character.magic.setMagicTies(true)
        },
        onRemove = {character, _, _ ->
            character.magic.setMagicTies(false)
        }
    )

    val slowMagicRecovery = Advantage(
        saveTag = "slowMagRecover",
        name = R.string.slowMagRecover,
        description = R.string.slowMagRecDesc,
        effect = R.string.slowMagRecEff,
        restriction = R.string.slowMagRestriction,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = {character, _, _ ->
            //apply zeon recovery penalty
            character.magic.changeRecoveryMult(0.5)
        },
        onRemove = {character, _, _ ->
            //remove zeon recovery penalty
            character.magic.changeRecoveryMult(1.0)
        }
    )

    val magicBlockage = Advantage(
        saveTag = "magicBlockage",
        name = R.string.magicBlockage,
        description = R.string.magBlockDesc,
        effect = R.string.magBlockEff,
        restriction = R.string.magBlockRestriction,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-2),
        pickedCost = 0,
        onTake = {character, _, _ ->
            //remove any ability to recover zeon
            character.magic.changeRecoveryMult(0.0)
        },
        onRemove = {character, _, _ ->
            //reacquire zeon recovery ability
            character.magic.changeRecoveryMult(1.0)
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