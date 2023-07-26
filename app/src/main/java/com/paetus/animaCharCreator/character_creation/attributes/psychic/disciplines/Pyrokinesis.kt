package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available pyrokinetic powers.
 */
class Pyrokinesis: Discipline("pyrokinesis"){
    val createFire = PsychicPower(
        "createFire",
        29,
        1,
        true,
        true,
        R.string.createFirePowerDesc,
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

    val extinguishFire = PsychicPower(
        "extinguishFire",
        30,
        1,
        true,
        false,
        R.string.extinguishFireDesc,
        listOf(R.string.physResReduceIntense),
        listOf(1, 10),
        listOf(
            2,
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

    val controlFire = PsychicPower(
        "controlFire",
        31,
        1,
        true,
        true,
        R.string.controlFireDesc,
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

    val immolate = PsychicPower(
        "immolate",
        32,
        1,
        true,
        false,
        R.string.immolateDesc,
        listOf(R.string.baseDamageArea),
        listOf(3, 10),
        listOf(
            4, 2, 1,
            Pair(60, 5),
            Pair(80, 10),
            Pair(100, 20),
            Pair(120, 30),
            Pair(150, 50),
            Pair(200, 100),
            Pair(250, 200)
        )
    )

    val igneousMaintenance = PsychicPower(
        "igneousMaint",
        33,
        2,
        true,
        true,
        R.string.igneousMaintDesc,
        listOf(R.string.intensities),
        listOf(3, 10),
        listOf(
            4, 2, 1,
            5,
            10,
            15,
            20,
            30,
            40,
            50
        )
    )

    val fireImmunity = PsychicPower(
        "fireImmunity",
        34,
        2,
        false,
        true,
        R.string.fireImmuneDesc,
        listOf(R.string.intensities),
        listOf(3, 10),
        listOf(
            4, 2, 1,
            5,
            10,
            15,
            20,
            30,
            40,
            50
        )
    )

    val igneousBarrier = PsychicPower(
        "igneousBarrier",
        35,
        2,
        true,
        true,
        R.string.igneousBarrierDesc,
        listOf(R.string.baseDamageLine),
        listOf(4, 10),
        listOf(
            6, 4, 2, 1,
            Pair(60, 5),
            Pair(80, 10),
            Pair(120, 20),
            Pair(160, 30),
            Pair(200, 40),
            Pair(240, 50)
        )
    )

    val raiseTemperature = PsychicPower(
        "raiseTemp",
        36,
        2,
        true,
        true,
        R.string.raiseTempPowerDesc,
        listOf(R.string.tempIncreaseKilometer),
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

    val consume = PsychicPower(
        "consume",
        37,
        3,
        true,
        false,
        R.string.consumeDesc,
        listOf(R.string.physResAutoDamage),
        listOf(5, 10),
        listOf(
            16, 12, 8, 6, 4,
            Pair(120, 80),
            Pair(140, 120),
            Pair(160, 160),
            Pair(180, 200),
            Pair(220, 250)
        )
    )

    val nova = PsychicPower(
        "nova",
        38,
        3,
        true,
        true,
        R.string.novaDesc,
        listOf(R.string.lifePointInput),
        listOf(3, 10),
        listOf(
            16, 12, 8,
            10,
            20,
            30,
            40,
            60,
            80,
            120
        )
    )

    val majorFire = PsychicPower(
        "majorFire",
        39,
        3,
        true,
        true,
        R.string.majorFireDesc,
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
            createFire,
            extinguishFire,
            controlFire,
            immolate,
            igneousMaintenance,
            fireImmunity,
            igneousBarrier,
            raiseTemperature,
            consume,
            nova,
            majorFire
        ))
    }
}