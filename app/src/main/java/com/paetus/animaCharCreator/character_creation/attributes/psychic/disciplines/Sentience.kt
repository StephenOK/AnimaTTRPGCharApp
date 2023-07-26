package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available sentience powers.
 */
class Sentience: Discipline("sentience"){
    val senseFeelings = PsychicPower(
        "senseFeeling",
        76,
        1,
        true,
        true,
        R.string.senseFeelingDesc,
        listOf(R.string.psyResInput),
        listOf(2, 10),
        listOf(
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

    val intensifyFeelings = PsychicPower(
        "intensifyFeeling",
        77,
        1,
        true,
        true,
        R.string.intensifyFeelingDesc,
        listOf(R.string.psyResInput),
        listOf(2, 10),
        listOf(
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

    val detectFeelings = PsychicPower(
        "detectFeelings",
        78,
        1,
        true,
        true,
        R.string.detectFeelingsDesc,
        listOf(R.string.psyResMeter, R.string.psyResKilometer),
        listOf(2, 7, 10),
        listOf(
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

    val connectSenses = PsychicPower(
        "connectSenses",
        79,
        1,
        true,
        true,
        R.string.connectSensesDesc,
        listOf(R.string.psyResMeter, R.string.psyResKilometer, R.string.psyResAnyDistance),
        listOf(2, 5, 9, 10),
        listOf(
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

    val projectSenses = PsychicPower(
        "projectSenses",
        80,
        2,
        true,
        true,
        R.string.projectSensesDesc,
        listOf(R.string.kilometerRadius, R.string.anyDistance),
        listOf(4, 9, 10),
        listOf(
            8, 6, 4, 2,
            1,
            10,
            50,
            100,
            1000,
            null
        )
    )

    val eliminateSenses = PsychicPower(
        "eliminateSenses",
        81,
        2,
        true,
        true,
        R.string.elimSensesDesc,
        listOf(R.string.psyResInput),
        listOf(4, 10),
        listOf(
            8, 6, 4, 2,
            100,
            120,
            140,
            160,
            180,
            220
        )
    )

    val createFeelings = PsychicPower(
        "Create Feelings",
        82,
        2,
        true,
        true,
        R.string.createFeelingsDesc,
        listOf(R.string.psyResInput),
        listOf(3, 10),
        listOf(
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

    val infuseFeelings = PsychicPower(
        "infuseFeelings",
        83,
        2,
        true,
        true,
        R.string.infuseFeelingsDesc,
        listOf(R.string.psyResMeter),
        listOf(4, 10),
        listOf(
            8, 6, 4, 2,
            Pair(100, 5),
            Pair(120, 10),
            Pair(140, 25),
            Pair(160, 50),
            Pair(180, 100),
            Pair(220, 500)
        )
    )

    val destroyFeelings = PsychicPower(
        "destroyFeelings",
        84,
        3,
        true,
        false,
        R.string.destroyFeelingsDesc,
        listOf(R.string.psyResInput),
        listOf(5, 10),
        listOf(
            16, 12, 8, 6, 4,
            120,
            140,
            160,
            180,
            200
        )
    )

    val area = PsychicPower(
        "areaSentience",
        85,
        3,
        true,
        true,
        R.string.areaSentienceDesc,
        listOf(R.string.meterRadius, R.string.kilometerRadius),
        listOf(4, 6, 10),
        listOf(
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
        allPowers.addAll(listOf(
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
        ))
    }
}