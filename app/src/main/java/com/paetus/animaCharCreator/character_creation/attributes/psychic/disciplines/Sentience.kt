package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available sentience powers.
 */
class Sentience: Discipline(saveName = "sentience"){
    private val senseFeelings = PsychicPower(
        saveName = "senseFeeling",
        name = 76,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.senseFeelingDesc,
        stringBaseList = listOf(R.string.psyResInput),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(
            2, 1,
            100,
            120,
            140,
            160,
            180,
            200,
            220,
            240
        )
    )

    private val intensifyFeelings = PsychicPower(
        saveName = "intensifyFeeling",
        name = 77,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.intensifyFeelingDesc,
        stringBaseList = listOf(R.string.psyResInput),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(
            2, 1,
            100,
            120,
            140,
            160,
            180,
            200,
            220,
            240
        )
    )

    private val detectFeelings = PsychicPower(
        saveName = "detectFeelings",
        name = 78,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.detectFeelingsDesc,
        stringBaseList = listOf(R.string.psyResMeter, R.string.psyResKilometer),
        stringBaseCount = listOf(2, 7, 10),
        stringInput = listOf(
            2, 1,
            Pair(80, 10),
            Pair(100, 50),
            Pair(120, 100),
            Pair(140, 250),
            Pair(160, 500),
            Pair(180, 1),
            Pair(200, 10),
            Pair(220, 100)
        )
    )

    private val connectSenses = PsychicPower(
        saveName = "connectSenses",
        name = 79,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.connectSensesDesc,
        stringBaseList = listOf(R.string.psyResMeter, R.string.psyResKilometer, R.string.psyResAnyDistance),
        stringBaseCount = listOf(2, 5, 9, 10),
        stringInput = listOf(
            2, 1,
            Pair(60, 10),
            Pair(80, 100),
            Pair(100, 500),
            Pair(120, 1),
            Pair(140, 10),
            Pair(160, 100),
            Pair(180, 1000),
            200
        )
    )

    private val projectSenses = PsychicPower(
        saveName = "projectSenses",
        name = 80,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.projectSensesDesc,
        stringBaseList = listOf(R.string.kilometerRadius, R.string.anyDistance),
        stringBaseCount = listOf(4, 9, 10),
        stringInput = listOf(
            8, 6, 4, 2,
            1,
            10,
            50,
            100,
            1000,
            null
        )
    )

    private val eliminateSenses = PsychicPower(
        saveName = "eliminateSenses",
        name = 81,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.elimSensesDesc,
        stringBaseList = listOf(R.string.psyResInput),
        stringBaseCount = listOf(4, 10),
        stringInput = listOf(
            8, 6, 4, 2,
            100,
            120,
            140,
            160,
            180,
            220
        )
    )

    private val createFeelings = PsychicPower(
        saveName = "Create Feelings",
        name = 82,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.createFeelingsDesc,
        stringBaseList = listOf(R.string.psyResInput),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
            8, 4, 2,
            80,
            100,
            120,
            140,
            160,
            180,
            200
        )
    )

    private val infuseFeelings = PsychicPower(
        saveName = "infuseFeelings",
        name = 83,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.infuseFeelingsDesc,
        stringBaseList = listOf(R.string.psyResMeter),
        stringBaseCount = listOf(4, 10),
        stringInput = listOf(
            8, 6, 4, 2,
            Pair(100, 5),
            Pair(120, 10),
            Pair(140, 25),
            Pair(160, 50),
            Pair(180, 100),
            Pair(220, 500)
        )
    )

    private val destroyFeelings = PsychicPower(
        saveName = "destroyFeelings",
        name = 84,
        level = 3,
        isActive = true,
        maintained = false,
        description = R.string.destroyFeelingsDesc,
        stringBaseList = listOf(R.string.psyResInput),
        stringBaseCount = listOf(5, 10),
        stringInput = listOf(
            16, 12, 8, 6, 4,
            120,
            140,
            160,
            180,
            200
        )
    )

    val area = PsychicPower(
        saveName = "areaSentience",
        name = 85,
        level = 3,
        isActive = true,
        maintained = true,
        description = R.string.areaSentienceDesc,
        stringBaseList = listOf(R.string.meterRadius, R.string.kilometerRadius),
        stringBaseCount = listOf(4, 6, 10),
        stringInput = listOf(
            16, 12, 8, 4,
            10,
            100,
            1,
            10,
            100,
            500
        )
    )

    init{
        allPowers.addAll(
            elements = listOf(
                senseFeelings,
                intensifyFeelings,
                detectFeelings,
                connectSenses,
                projectSenses,
                eliminateSenses,
                createFeelings,
                infuseFeelings,
                destroyFeelings,
                area
            )
        )
    }
}