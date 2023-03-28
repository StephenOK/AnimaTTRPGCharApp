package com.example.animabuilder.character_creation.attributes.psychic.disciplines

import com.example.animabuilder.character_creation.attributes.psychic.Discipline
import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower
import java.io.Serializable

/**
 * Record of all of the available cryokinetic powers.
 */
class Cryokinesis: Discipline(), Serializable {
    val createChill = PsychicPower(
        "Create Chill",
        1,
        true,
        true,
        "This Power creates several levels of Cold Intensity. If applied to liquid " +
                "bodies, Create Chill may produce ice.",
        listOf(
            "Fatigue 1",
            "1 Intensity",
            "3 Intensities",
            "5 Intensities",
            "7 Intensities",
            "10 Intensities",
            "13 Intensities",
            "16 Intensities",
            "20 Intensities",
            "25 Intensities"
        )
    )

    val freeze = PsychicPower(
        "Freeze",
        1,
        true,
        true,
        "This Power freezes anybody who fails the required check. The victim receives " +
                "an All Action Penalty equivalent to the amount by which he failed the check. If " +
                "the difference is higher than 40, the victim is frozen and subject to Partial " +
                "Paralysis. The Cold AT may be used defensively against this Power. Affected " +
                "individuals can make a new check every 5 turns.",
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "80 PhR",
            "100 PhR",
            "120 PhR",
            "140 PhR",
            "160 PhR",
            "180 PhR",
            "220 PhR"
        )
    )

    val senseTemperature = PsychicPower(
        "Sense Temperature",
        1,
        true,
        true,
        "The character can sense any variation in weather temperature - including live " +
                "body heat - within the area of effect. This ability can overcome walls or " +
                "obstacles that are not based on Energy. However, it is useless against Ki " +
                "Concealment or opponents who do not emit heat. All individuals within the area " +
                "will be equally affected without the need of Psychic Projection.",
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "10-meter radius",
            "50-meter radius",
            "100-meter radius",
            "500-meter radius",
            "1-kilometer radius",
            "10-kilometer radius",
            "100-kilometer radius"
        )
    )

    val eliminateCold = PsychicPower(
        "Eliminate Cold",
        1,
        true,
        false,
        "This Power decreases the temperature of an object, being, or zone by several " +
                "Intensities. When cast upon a cold=based being, Eliminate Cold causes 5 Life " +
                "Points of damage points for every diminished Intensity - provided the creature " +
                "fails the appropriate PhR Check. Creatures with Damage Resistance receive 25 Life " +
                "Points of damage for each level of Intensity diminished.",
        listOf(
            "Fatigue 1",
            "-1 Intensity/80 PhR",
            "-3 Intensities/100 PhR",
            "-5 Intensities/120 PhR",
            "-7 Intensities/140 PhR",
            "-10 Intensities/160 PhR",
            "-15 Intensities/180 PhR",
            "-20 Intensities/200 PhR",
            "-30 Intensities/220 PhR",
            "-40 Intensities/260 PhR"
        )
    )

    val coldDominion = PsychicPower(
        "Cold Dominion",
        1,
        true,
        true,
        "Psychics with this Power can control ice and cold within a given area. They may " +
                "modify it in any way they please, breaking it or causing it to shift its shape. " +
                "An elemental creature can avoid the effects of Cold Dominion by passing a PhR " +
                "Check against the appropriate target Difficulty (as detailed in the Effects " +
                "Table below).",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "4 Intensities/80 PhR",
            "6 Intensities/100 PhR",
            "8 Intensities/120 PhR",
            "12 Intensities/140 PhR",
            "16 Intensities/160 PhR",
            "20 Intensities/180 PhR",
            "25 Intensities/200 PhR",
            "30 Intensities/240 PhR"
        )
    )

    val crystallize = PsychicPower(
        "Crystallize",
        2,
        true,
        true,
        "This Power crystallizes any type of body that fails the required PhR Check. All " +
                "things frozen in this way become exceptionally brittle and likely to break at the " +
                "slightest bump. A crystallized character is subject to Minor Paralysis and " +
                "automatically suffers a Critical with a -40 penalty to his PhR Checks upon " +
                "suffering any sort of damage. Creatures with Damage Resistance will not receive " +
                "a direct Critical, but their whole body will be regarded as a vulnerable spot " +
                "from then on.",
        listOf(
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "120 PhR",
            "140 PhR",
            "160 PhR",
            "180 PhR",
            "220 PhR"
        )
    )

    val iceSplinters = PsychicPower(
        "Ice Splinters",
        2,
        true,
        false,
        "The psychic creates ice splinters that he can use as projectiles during an " +
                "attack. They strike on the Cold or Thrust AT with a Base Damage that varies " +
                "according to the Effects Table. These projectiles are perfectly visible to " +
                "everyone, including characters without the ability to see Psychic Matrices.",
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "Base Damage 80",
            "Base Damage 100",
            "Base Damage 120",
            "Base Damage 160/5-meter area",
            "Base Damage 200/25-meter area"
        )
    )

    val decreaseTemperature = PsychicPower(
        "Decrease Ambient Temperature",
        2,
        true,
        true,
        "The psychic is in control of the temperature and can decrease it considerably " +
                "in a wide area of effect.",
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "-5℃/1-kilometer radius",
            "-10℃/5-kilometer radius",
            "-15℃/10-kilometer radius",
            "-20℃/25-kilometer radius",
            "-30℃/50-kilometer radius",
            "-40℃/100-kilometer radius"
        )
    )

    val iceShield = PsychicPower(
        "Ice Shield",
        2,
        false,
        true,
        "This Power creates a shield of ice that protects the psychic from any " +
                "non-Energy-based source of attack and beams from Light or Darkness. Unlike " +
                "other Powers, Ice Shield works on the same Life Points with which it has been " +
                "created. Once created, the shield loses 5 Life Points per turn until it reaches " +
                "an amount that the psychic can maintain naturally.",
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "600 LP",
            "800 LP",
            "1200 LP",
            "1800 LP",
            "2500 LP",
            "4000 LP",
            "6000 LP"
        )
    )

    val absoluteZero = PsychicPower(
        "Absolute Zero",
        3,
        true,
        true,
        "The psychic is able to cause the temperature to drop to absolute zero, " +
                "destroying all organic or inorganic bodies within the radius. In gaming terms, " +
                "every being or physical object failing a PhR Check with a difficulty of 100 " +
                "each turn they remain within the area of effect are automatically destroyed by " +
                "the complete cold. All individuals within the area will be affected equally " +
                "without the need of Psychic Projection.",
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "5-meter radius",
            "10-meter radius",
            "20-meter radius",
            "50-meter radius",
            "100-meter radius"
        )
    )

    val everlastingMoment = PsychicPower(
        "Everlasting Moment",
        3,
        true,
        true,
        "Through cold manipulation, psychics can create a low temperature area within " +
                "which any body in motion, except for his own, is immobilized - unless it passes " +
                "the required PhR Check. If a character fails the check by more than 40 points, he " +
                "is completely frozen and subject to Full Paralysis. A failure of less than 40 " +
                "points inflicts an All Action Penalty equal to the Failure Level.\nPenalties " +
                "last for as long as the Power is maintained. Characters are not entitled to new " +
                "Resistance rolls while they remain in the area of effect. Even individuals who " +
                "pass their PhR Check must repeat the check every 5 turns while in the area of " +
                "effect. All individuals within the area will be affected equally without the " +
                "need of Psychic Projection.",
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "120 PhR/5-meter radius",
            "140 PhR/10-meter radius",
            "160 PhR/20-meter radius",
            "180 PhR/50-meter radius",
            "200 PhR/100-meter radius"
        )
    )

    val majorCold = PsychicPower(
        "Major Cold",
        3,
        true,
        true,
        "An amplified version of Create Chill. It permits psychics to generate " +
                "temperatures of an extreme nature.",
        listOf(
            "Fatigue 20",
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "30 Intensities",
            "40 Intensities",
            "50 Intensities",
            "60 Intensities"
        )
    )

    override var allPowers = listOf(createChill, freeze, senseTemperature, eliminateCold, coldDominion,
        crystallize, iceSplinters, decreaseTemperature, iceShield, absoluteZero, everlastingMoment,
        majorCold)
}