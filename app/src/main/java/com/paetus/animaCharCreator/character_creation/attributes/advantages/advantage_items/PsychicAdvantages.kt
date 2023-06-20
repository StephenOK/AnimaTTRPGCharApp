package com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage

/**
 * List of psychic advantages and disadvantages a character may take.
 */
class PsychicAdvantages{
    val amplifySustainedPower = Advantage(
        "amplifyPower",
        R.string.amplifySustainedPower,
        R.string.amplifyPowerDesc,
        R.string.amplifyPowerEff,
        null,
        null,
        null,
        null,
        listOf(2),
        0,
        null,
        null
    )

    val psychicPointRecovery = Advantage(
        "psyPointRecovery",
        R.string.psyPointRecovery,
        R.string.psyPointRecoverDesc,
        R.string.psyPointRecoverEff,
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val psychicFatigueResistance = Advantage(
        "psyFatigueRes",
        R.string.psyFatigueRes,
        R.string.psyFatResDesc,
        R.string.psyFatResEff,
        null,
        null,
        null,
        null,
        listOf(2),
        0,
        null,
        null
    )

    val passiveConcentration = Advantage(
        "passiveConcentration",
        R.string.passiveConcentration,
        R.string.passiveConcDesc,
        R.string.passiveConcEff,
        null,
        null,
        null,
        null,
        listOf(2),
        0,
        null,
        null
    )

    val psychicInclination = Advantage(
        "psyInclination",
        R.string.psyInclination,
        R.string.psyInclineDesc,
        R.string.psyInclineEff,
        null,
        null,
        listOf(
            "Telepathy",
            "Psychokinesis",
            "Pyrokinesis",
            "Cryokinesis",
            "Physical Increase",
            "Energy",
            "Sentience",
            "Telemetry"
        ),
        0,
        listOf(2),
        0,
        null,
        null
    )

    val focus = Advantage(
        "focus",
        R.string.focus,
        R.string.focusDesc,
        R.string.focusEff,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val extremeConcentration = Advantage(
        "extremeConcentration",
        R.string.extremeConcentration,
        R.string.exConcDesc,
        R.string.exConcEff,
        null,
        null,
        null,
        null,
        listOf(2),
        0,
        null,
        null
    )

    val advantages = listOf(amplifySustainedPower, psychicPointRecovery, psychicFatigueResistance,
        passiveConcentration, psychicInclination, focus, extremeConcentration)




    val psychicExhaustion = Advantage(
        "psyExhaustion",
        R.string.psyExhaustion,
        R.string.psyExhaustDesc,
        R.string.psyExhaustEff,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val psychicConsumption = Advantage(
        "psyConsumption",
        R.string.psyConsumption,
        R.string.psyConsumptDesc,
        R.string.psyConsumptEff,
        null,
        null,
        null,
        null,
        listOf(-2),
        0,
        null,
        null
    )

    val onePowerAtATime = Advantage(
        "singlePower",
        R.string.singlePower,
        R.string.singlePowerDesc,
        R.string.singlePowerEff,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val noConcentration = Advantage(
        "noConcentration",
        R.string.noConcentration,
        R.string.noConcDesc,
        R.string.noConcEff,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val disadvantages = listOf(psychicExhaustion, psychicConsumption, onePowerAtATime, noConcentration)
}