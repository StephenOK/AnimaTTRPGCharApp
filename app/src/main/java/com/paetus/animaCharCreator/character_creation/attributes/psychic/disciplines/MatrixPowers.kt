package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available matrix abilities any psychic may acquire.
 */
class MatrixPowers: Discipline("matrixPowers"){
    val senseMatrices = PsychicPower(
        "senseMatrices",
        90,
        0,
        true,
        true,
        R.string.senseMatricesDesc,
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
        91,
        0,
        false,
        true,
        R.string.destroyMatricesDesc,
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
        "hideMatrices",
        92,
        0,
        false,
        true,
        R.string.hideMatricesDesc,
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
        "linkMatrices",
        93,
        0,
        true,
        true,
        R.string.linkMatricesDesc,
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

    init{
        allPowers.addAll(listOf(
            senseMatrices,
            destroyMatrices,
            hideMatrices,
            linkMatrices
        ))
    }
}