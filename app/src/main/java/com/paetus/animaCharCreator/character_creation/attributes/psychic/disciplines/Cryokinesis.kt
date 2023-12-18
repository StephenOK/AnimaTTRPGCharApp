package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available cryokinetic powers.
 */
class Cryokinesis: Discipline(saveName = "cryokinesis"){
    private val createChill = PsychicPower(
        saveName = "createChill",
        name = 40,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.createChillPowDesc,
        stringBaseList = listOf(R.string.intensities),
        stringBaseCount = listOf(1, 10),
        stringInput = listOf(
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

    private val freeze = PsychicPower(
        saveName = "freeze",
        name = 41,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.freezePowDesc,
        stringBaseList = listOf(R.string.physResInput),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
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

    private val senseTemperature = PsychicPower(
        saveName = "senseTemp",
        name = 42,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.senseTempDesc,
        stringBaseList = listOf(R.string.meterRadius, R.string.kilometerRadius),
        stringBaseCount = listOf(3, 7, 10),
        stringInput = listOf(
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

    private val eliminateCold = PsychicPower(
        saveName = "elimCold",
        name = 43,
        level = 1,
        isActive = true,
        maintained = false,
        description = R.string.elimColdDesc,
        stringBaseList = listOf(R.string.physResReduceIntense),
        stringBaseCount = listOf(1, 10),
        stringInput = listOf(
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

    private val coldDominion = PsychicPower(
        saveName = "coldDominion",
        name = 44,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.coldDominionDesc,
        stringBaseList = listOf(R.string.physResIntensities),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(
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

    private val crystallize = PsychicPower(
        saveName = "crystallize",
        name = 45,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.crystallizeDesc,
        stringBaseList = listOf(R.string.physResInput),
        stringBaseCount = listOf(5, 10),
        stringInput = listOf(
            12, 8, 6, 4, 2,
            120,
            140,
            160,
            180,
            220
        )
    )

    private val iceSplinters = PsychicPower(
        saveName = "iceSplinters",
        name = 46,
        level = 2,
        isActive = true,
        maintained = false,
        description = R.string.iceSplintersDesc,
        stringBaseList = listOf(R.string.baseDamageInput, R.string.baseDamageArea),
        stringBaseCount = listOf(5, 8, 10),
        stringInput = listOf(
            8, 6, 4, 2, 1,
            80,
            100,
            120,
            Pair(160, 5),
            Pair(200, 25)
        )
    )

    private val decreaseTemperature = PsychicPower(
        saveName = "decreaseTemp",
        name = 47,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.decreaseTempDesc,
        stringBaseList = listOf(R.string.tempDecreaseKilometer),
        stringBaseCount = listOf(4, 10),
        stringInput = listOf(
            6, 4, 2, 1,
            Pair(5, 1),
            Pair(10, 5),
            Pair(15, 10),
            Pair(20, 25),
            Pair(30, 50),
            Pair(40, 100)
        )
    )

    private val iceShield = PsychicPower(
        saveName = "iceShield",
        name = 48,
        level = 2,
        isActive = false,
        maintained = true,
        description = R.string.iceShieldDesc,
        stringBaseList = listOf(R.string.lifePointInput),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
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

    private val absoluteZero = PsychicPower(
        saveName = "absoluteZero",
        name = 49,
        level = 3,
        isActive = true,
        maintained = true,
        description = R.string.absoluteZeroDesc,
        stringBaseList = listOf(R.string.meterRadius),
        stringBaseCount = listOf(5, 10),
        stringInput = listOf(
            16, 12, 8, 6, 4,
            5,
            10,
            20,
            50,
            100
        )
    )

    private val everlastingMoment = PsychicPower(
        saveName = "everlastMoment",
        name = 50,
        level = 3,
        isActive = true,
        maintained = true,
        description = R.string.everlastMomentDesc,
        stringBaseList = listOf(R.string.physResMeter),
        stringBaseCount = listOf(5, 10),
        stringInput = listOf(
            16, 12, 8, 6, 4,
            Pair(120, 5),
            Pair(140, 10),
            Pair(160, 20),
            Pair(180, 50),
            Pair(200, 100)
        )
    )

    private val majorCold = PsychicPower(
        saveName = "majorCold",
        name = 51,
        level = 3,
        isActive = true,
        maintained = true,
        description = R.string.majorColdDesc,
        stringBaseList = listOf(R.string.intensities),
        stringBaseCount = listOf(6, 10),
        stringInput = listOf(
            20, 16, 12, 8, 6, 4,
            30,
            40,
            50,
            60
        )
    )

    init{
        allPowers.addAll(
            elements = listOf(
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
            )
        )
    }
}