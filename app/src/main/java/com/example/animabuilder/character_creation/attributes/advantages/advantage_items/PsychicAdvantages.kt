package com.example.animabuilder.character_creation.attributes.advantages.advantage_items

import com.example.animabuilder.R
import com.example.animabuilder.character_creation.attributes.advantages.advantage_types.Advantage
import java.io.Serializable

class PsychicAdvantages: Serializable {
    val amplifySustainedPower = Advantage(
        R.string.amplifySustainedPower,
        "A character with this Advantage can maintain his psychic powers with more force.",
        "Any powers maintained in this way are one difficulty level higher than what the " +
                "psychic could normally attain.",
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
        R.string.psyPointRecovery,
        "A character with this Advantage can easily recover from using his abilities.",
        "The character's recovery rate for Psychic Points is 1 point every 10 minutes. " +
                "Spending additional Creation Points increases the rate to 1 point every five " +
                "minutes or every minute, respectively.",
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
        R.string.psyFatigueRes,
        "A character with this Advantage never experiences exhaustion when using his " +
                "psychic powers.",
        "If a character fails in the use of one of his powers, he does not lose Fatigue " +
                "when he has used up his available PP. Third level powers are not affected by this " +
                "Advantage.",
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
        R.string.passiveConcentration,
        "A character with this Advantage can concentrate in any situation, no matter the " +
                "difficulty or complications.",
        "The psychic can concentrate to harness a power even while executing active actions.",
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
        R.string.psychicInclination,
        "A character with this Advantage has developed one of his Psychic Disciplines " +
                "more than the rest of his abilities.",
        "The character automatically gains one level of difficulty greater than normal when " +
                "using the powers of a specific discipline.",
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
        R.string.focus,
        "Characters with the Advantage can harness their ability more than normal and " +
                "focus their powers on a particular objective.",
        "Psychic Points spent to improve Psychic Projection increase the ability by +20 " +
                "instead of +10.",
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
        R.string.extremeConcentration,
        "A character with this Advantage can concentrate much more than most psychics, " +
                "thereby gaining greater benefits from his abilities.",
        "The psychic doubles the bonus he normally gains from concentration. For example, " +
                "if he concentrates for a full round, he gains +20 instead of +10.",
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
        R.string.psychicExhaustion,
        "The psychic powers of a character with this Disadvantage cause great physical " +
                "stress upon his body, leaving him terribly exhausted even when using his " +
                "low-level psychic abilities.",
        "The character loses double the points of Fatigue indicated whenever using psychic powers.",
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
        R.string.psychicConsumption,
        "The psychic powers of a character with this Disadvantage create serious " +
                "feedback in his body, causing internal damage.",
        "If the character suffers a psychic failure, he automatically loses the same " +
                "number of Life Points as the number by which he failed.",
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
        R.string.onePowerAtATime,
        "The psychic powers of the character do not allow him to use several abilities " +
                "at once. If the character uses one power, his focus remains so intense that he " +
                "cannot project others - except those maintained unconsciously.",
        "The character can use only one psychic power per assault. This does not prevent " +
                "him from continuing to use others which are maintained, as long as they began " +
                "in previous assaults.",
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
        R.string.noConcentration,
        "The psychic powers of a character with this Disadvantage are too unreliable " +
                "for him to plan ahead in their use.",
        "A psychic with this Disadvantage does not apply bonuses to his psychic potential " +
                "by concentrating.",
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