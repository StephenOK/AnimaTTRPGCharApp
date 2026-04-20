package com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage

/**
 * List of psychic advantages and disadvantages a character may take.
 */
class PsychicAdvantages{
    private val amplifySustainedPower = Advantage(
        saveTag = "amplifyPower",
        name = R.string.amplifySustainedPower,
        description = R.string.amplifyPowerDesc,
        effect = R.string.amplifyPowerEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(2),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val psychicPointRecovery = Advantage(
        saveTag = "psyPointRecovery",
        name = R.string.psyPointRecovery,
        description = R.string.psyPointRecoverDesc,
        effect = R.string.psyPointRecoverEff,
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

    private val psychicFatigueResistance = Advantage(
        saveTag = "psyFatigueRes",
        name = R.string.psyFatigueRes,
        description = R.string.psyFatResDesc,
        effect = R.string.psyFatResEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(2),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val passiveConcentration = Advantage(
        saveTag = "passiveConcentration",
        name = R.string.passiveConcentration,
        description = R.string.passiveConcDesc,
        effect = R.string.passiveConcEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(2),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val psychicInclination = Advantage(
        saveTag = "psyInclination",
        name = R.string.psyInclination,
        description = R.string.psyInclineDesc,
        effect = R.string.psyInclineEff,
        restriction = null,
        special = null,
        options = R.array.disciplineNames,
        picked = 0,
        multPicked = null,
        cost = listOf(2),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val focus = Advantage(
        saveTag = "focus",
        name = R.string.focus,
        description = R.string.focusDesc,
        effect = R.string.focusEff,
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

    private val extremeConcentration = Advantage(
        saveTag = "extremeConcentration",
        name = R.string.extremeConcentration,
        description = R.string.exConcDesc,
        effect = R.string.exConcEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(2),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val advantages = listOf(amplifySustainedPower, psychicPointRecovery, psychicFatigueResistance,
        passiveConcentration, psychicInclination, focus, extremeConcentration)




    private val psychicExhaustion = Advantage(
        saveTag = "psyExhaustion",
        name = R.string.psyExhaustion,
        description = R.string.psyExhaustDesc,
        effect = R.string.psyExhaustEff,
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

    private val psychicConsumption = Advantage(
        saveTag = "psyConsumption",
        name = R.string.psyConsumption,
        description = R.string.psyConsumptDesc,
        effect = R.string.psyConsumptEff,
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

    private val onePowerAtATime = Advantage(
        saveTag = "singlePower",
        name = R.string.singlePower,
        description = R.string.singlePowerDesc,
        effect = R.string.singlePowerEff,
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

    private val noConcentration = Advantage(
        saveTag = "noConcentration",
        name = R.string.noConcentration,
        description = R.string.noConcDesc,
        effect = R.string.noConcEff,
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

    val disadvantages = listOf(psychicExhaustion, psychicConsumption, onePowerAtATime, noConcentration)
}