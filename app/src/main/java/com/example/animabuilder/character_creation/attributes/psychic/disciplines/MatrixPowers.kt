package com.example.animabuilder.character_creation.attributes.psychic.disciplines

import com.example.animabuilder.character_creation.attributes.psychic.Discipline
import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower
import java.io.Serializable

class MatrixPowers: Discipline(), Serializable {
    val senseMatrices = PsychicPower(
        "Sense Matrices",
        0,
        true,
        true,
        "The psychic is able to sense the use of Powers and notice the Presence of individuals who share this ability. In this way, characters \"see\" the energy of the matrices. Therefore, they suffer no Blindness penalty against invisible Psychic Abilities. For instance, anyone reaching a Medium-level Difficulty will be able to sense active Psychic Matrices and detect latent Powers in people, all within a 25-meter area.",
        listOf(
            "Fatigue 1",
            "10-meter radius/See active Psychic Matrices",
            "25-meter radius/Detect latent Powers in people",
            "50-meter radius/Recognize the Power in use",
            "100-meter radius",
            "250-meter radius/Discern Disciplines of a psychic",
            "500-meter radius/Discern psychic's Potential",
            "1-kilometer radius/Discern psychic's free PP",
            "10-kilometer radius/Notice another psychic's Powers",
            "100-kilometer radius"
        )
    )

    val destroyMatrices = PsychicPower(
        "Destroy Matrices",
        0,
        false,
        true,
        "This ability will destroy active Psychic Powers - provided they are not of a " +
                "higher Difficulty than the Effects Table indicates.",
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "Medium-level Powers",
            "Difficult-level Powers",
            "Very Difficult-level Powers",
            "Absurd-level Powers",
            "Almost Impossible-level Powers",
            "Impossible-level Powers",
            "Inhuman-level Powers"
        )
    )

    val hideMatrices = PsychicPower(
        "Hide Matrices",
        0,
        false,
        true,
        "This Power hides the psychic's mental abilities from the Sense Matrices Power. " +
                "Hide Matrices decreases the results for Sense Matrices by as many degrees of " +
                "Difficulty as indicated on the Effects Table. If an adversary's Potential for " +
                "Sense Matrices falls below the base requirement (that is, Easy), the psychic's " +
                "Powers of the character remain unnoticed. The Sense Matrices Power is only " +
                "annulled against the Psychic using Hide Matrices. It still works normally against " +
                "other targets.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "-2 Difficulty Degrees",
            "-3 Difficulty Degrees",
            "-4 Difficulty Degrees",
            "-5 Difficulty Degrees",
            "-6 Difficulty Degrees",
            "-7 Difficulty Degrees",
            "-8 Difficulty Degrees",
            "-9 Difficulty Degrees"
        )
    )

    val linkMatrices = PsychicPower(
        "Link Matrices",
        0,
        true,
        true,
        "This Power enables the psychic to connect the minds of several other psychic " +
                "individuals to his own. In this way, one of the connected members is able to add " +
                "the Willpower bonuses of others to his own Psychic Potential. Only one character " +
                "can use his Psychic Abilities while linked, since the others are merely enhancing " +
                "the Potential of one person. Connected characters must use their Powers to this " +
                "effect voluntarily. The number of individuals admitted to the link is determined " +
                "by a character's success in activating this Power (as detailed in the Effects Table below).",
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "2 Individuals",
            "3 Individuals",
            "4 Individuals",
            "6 Individuals",
            "8 Individuals",
            "10 Individuals",
            "20 Individuals"
        )
    )

    override var allPowers = listOf(senseMatrices, destroyMatrices, hideMatrices, linkMatrices)
}