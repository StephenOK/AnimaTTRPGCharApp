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
            R.string.senseMatrixMeter,
            R.string.meterInput,
            R.string.senseMatrixMeter,
            R.string.senseMatrixKilometer,
            R.string.kilometerInput
        ),
        listOf(1, 4, 5, 7, 9, 10),
        listOf(
            1,
            Pair(10, {R.string.seeMatrices}),
            Pair(25, {R.string.detectPowers}),
            Pair(50, {R.string.recognizePowers}),
            100,
            Pair(250, {R.string.discernDiscipline}),
            Pair(500, {R.string.discernPsyPotential}),
            Pair(1, {R.string.discernFreePP}),
            Pair(10, {R.string.noticeAnothersPower}),
            100
        )
    )

    val destroyMatrices = PsychicPower(
        "Destroy Matrices",
        91,
        0,
        false,
        true,
        R.string.destroyMatricesDesc,
        listOf(R.string.powerLevel),
        listOf(3, 10),
        listOf(
            6, 4, 2,
            {R.string.moderate},
            {R.string.difficult},
            {R.string.veryDifficult},
            {R.string.absurd},
            {R.string.almostImpossible},
            {R.string.impossible},
            {R.string.inhuman}
        )
    )

    val hideMatrices = PsychicPower(
        "hideMatrices",
        92,
        0,
        false,
        true,
        R.string.hideMatricesDesc,
        listOf(R.string.difficultyDegree),
        listOf(2, 10),
        listOf(2, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    )

    val linkMatrices = PsychicPower(
        "linkMatrices",
        93,
        0,
        true,
        true,
        R.string.linkMatricesDesc,
        listOf(R.string.individualCount),
        listOf(3, 10),
        listOf(6, 4, 2, 2, 3, 4, 6, 8, 10, 20)
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