package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available matrix abilities any psychic may acquire.
 */
class MatrixPowers: Discipline(saveName = "matrixPowers"){
    private val senseMatrices = PsychicPower(
        saveName = "senseMatrices",
        name = 90,
        level = 0,
        isActive = true,
        maintained = true,
        description = R.string.senseMatricesDesc,
        stringBaseList = listOf(
            R.string.senseMatrixMeter,
            R.string.meterInput,
            R.string.senseMatrixMeter,
            R.string.senseMatrixKilometer,
            R.string.kilometerInput
        ),
        stringBaseCount = listOf(1, 4, 5, 7, 9, 10),
        stringInput = listOf(
            1,
            Pair(10) { R.string.seeMatrices },
            Pair(25) { R.string.detectPowers },
            Pair(50) { R.string.recognizePowers },
            100,
            Pair(250) { R.string.discernDiscipline },
            Pair(500) { R.string.discernPsyPotential },
            Pair(1) { R.string.discernFreePP },
            Pair(10) { R.string.noticeAnothersPower },
            100
        )
    )

    private val destroyMatrices = PsychicPower(
        saveName = "Destroy Matrices",
        name = 91,
        level = 0,
        isActive = false,
        maintained = true,
        description = R.string.destroyMatricesDesc,
        stringBaseList = listOf(R.string.powerLevel),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
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

    private val hideMatrices = PsychicPower(
        saveName = "hideMatrices",
        name = 92,
        level = 0,
        isActive = false,
        maintained = true,
        description = R.string.hideMatricesDesc,
        stringBaseList = listOf(R.string.difficultyDegree),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(2, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    )

    val linkMatrices = PsychicPower(
        saveName = "linkMatrices",
        name = 93,
        level = 0,
        isActive = true,
        maintained = true,
        description = R.string.linkMatricesDesc,
        stringBaseList = listOf(R.string.individualCount),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(6, 4, 2, 2, 3, 4, 6, 8, 10, 20)
    )

    init{
        allPowers.addAll(
            elements = listOf(
                senseMatrices,
                destroyMatrices,
                hideMatrices,
                linkMatrices
            )
        )
    }
}