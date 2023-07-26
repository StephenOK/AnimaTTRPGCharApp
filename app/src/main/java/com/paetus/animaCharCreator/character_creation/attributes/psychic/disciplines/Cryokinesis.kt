package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available cryokinetic powers.
 */
class Cryokinesis: Discipline("cryokinesis"){
    val createChill = PsychicPower(
        "createChill",
        40,
        1,
        true,
        true,
        R.string.createChillPowDesc,
        listOf(R.string.intensities),
        listOf(1, 10),
        listOf(
            1,
            1,
            3,
            5,
            7,
            10,
            13,
            16,
            20,
            25
        )
    )

    val freeze = PsychicPower(
        "freeze",
        41,
        1,
        true,
        true,
        R.string.freezePowDesc,
        listOf(R.string.physResInput),
        listOf(3, 10),
        listOf(
            8, 6, 4,
            80,
            100,
            120,
            140,
            160,
            180,
            220
        )
    )

    val senseTemperature = PsychicPower(
        "senseTemp",
        42,
        1,
        true,
        true,
        R.string.senseTempDesc,
        listOf(R.string.meterRadius, R.string.kilometerRadius),
        listOf(3, 7, 10),
        listOf(
            4, 2, 1,
            10,
            50,
            100,
            500,
            1,
            10,
            100
        )
    )

    val eliminateCold = PsychicPower(
        "elimCold",
        43,
        1,
        true,
        false,
        R.string.elimColdDesc,
        listOf(R.string.physResReduceIntense),
        listOf(1, 10),
        listOf(
            1,
            Pair(80, 1),
            Pair(100, 3),
            Pair(120, 5),
            Pair(140, 7),
            Pair(160, 10),
            Pair(180, 15),
            Pair(200, 20),
            Pair(220, 30),
            Pair(260, 40)
        )
    )

    val coldDominion = PsychicPower(
        "coldDominion",
        44,
        1,
        true,
        true,
        R.string.coldDominionDesc,
        listOf(R.string.physResIntensities),
        listOf(2, 10),
        listOf(
            2, 1,
            Pair(80, 4),
            Pair(100, 6),
            Pair(120, 8),
            Pair(140, 12),
            Pair(160, 16),
            Pair(180, 20),
            Pair(200, 25),
            Pair(240, 30)
        )
    )

    val crystallize = PsychicPower(
        "crystallize",
        45,
        2,
        true,
        true,
        R.string.crystallizeDesc,
        listOf(R.string.physResInput),
        listOf(5, 10),
        listOf(
            12, 8, 6, 4, 2,
            120,
            140,
            160,
            180,
            220
        )
    )

    val iceSplinters = PsychicPower(
        "iceSplinters",
        46,
        2,
        true,
        false,
        R.string.iceSplintersDesc,
        listOf(R.string.baseDamageInput, R.string.baseDamageArea),
        listOf(5, 8, 10),
        listOf(
            8, 6, 4, 2, 1,
            80,
            100,
            120,
            Pair(160, 5),
            Pair(200, 25)
        )
    )

    val decreaseTemperature = PsychicPower(
        "decreaseTemp",
        47,
        2,
        true,
        true,
        R.string.decreaseTempDesc,
        listOf(R.string.tempDecreaseKilometer),
        listOf(4, 10),
        listOf(
            6, 4, 2, 1,
            Pair(5, 1),
            Pair(10, 5),
            Pair(15, 10),
            Pair(20, 25),
            Pair(30, 50),
            Pair(40, 100)
        )
    )

    val iceShield = PsychicPower(
        "iceShield",
        48,
        2,
        false,
        true,
        R.string.iceShieldDesc,
        listOf(R.string.lifePointInput),
        listOf(3, 10),
        listOf(
            6, 4, 2,
            600,
            800,
            1200,
            1800,
            2500,
            4000,
            6000
        )
    )

    val absoluteZero = PsychicPower(
        "absoluteZero",
        49,
        3,
        true,
        true,
        R.string.absoluteZeroDesc,
        listOf(R.string.meterRadius),
        listOf(5, 10),
        listOf(
            16, 12, 8, 6, 4,
            5,
            10,
            20,
            50,
            100
        )
    )

    val everlastingMoment = PsychicPower(
        "everlastMoment",
        50,
        3,
        true,
        true,
        R.string.everlastMomentDesc,
        listOf(R.string.physResMeter),
        listOf(5, 10),
        listOf(
            16, 12, 8, 6, 4,
            Pair(120, 5),
            Pair(140, 10),
            Pair(160, 20),
            Pair(180, 50),
            Pair(200, 100)
        )
    )

    val majorCold = PsychicPower(
        "majorCold",
        51,
        3,
        true,
        true,
        R.string.majorColdDesc,
        listOf(R.string.intensities),
        listOf(6, 10),
        listOf(
            20, 16, 12, 8, 6, 4,
            30,
            40,
            50,
            60
        )
    )

    init{
        allPowers.addAll(listOf(
            createChill,
            freeze,
            senseTemperature,
            eliminateCold,
            coldDominion,
            crystallize,
            iceSplinters,
            decreaseTemperature,
            iceShield,
            absoluteZero,
            everlastingMoment,
            majorCold
        ))
    }
}