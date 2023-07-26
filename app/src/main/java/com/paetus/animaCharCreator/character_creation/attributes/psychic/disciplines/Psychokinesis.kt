package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available psychokinetic powers.
 */
class Psychokinesis: Discipline("psychokinesis"){
    val minorKinesis = PsychicPower(
        "minorKinesis",
        15,
        1,
        true,
        true,
        R.string.minorKinesisDesc,
        listOf(R.string.kilogramsFlightValue),
        listOf(1, 10),
        listOf(
            1,
            Pair(1, 4),
            Pair(2, 6),
            Pair(5, 8),
            Pair(10, 10),
            Pair(20, 12),
            Pair(40, 14),
            Pair(100, 16),
            Pair(200, 18),
            Pair(500, 20)
        )
    )

    val kineticImpact = PsychicPower(
        "kineticImpact",
        16,
        1,
        true,
        false,
        R.string.kineticImpactDesc,
        listOf(R.string.strengthInput),
        listOf(2, 10),
        listOf(
            2, 1,
            8,
            10,
            12,
            14,
            15,
            16,
            18,
            20
        )
    )

    val kineticTrap = PsychicPower(
        "kineticTrap",
        17,
        1,
        true,
        true,
        R.string.psyKinTrapDesc,
        listOf(R.string.strengthInput, R.string.strengthWithRadius),
        listOf(2, 5, 10),
        listOf(
            2, 1,
            6,
            8,
            10,
            Pair(12, 5),
            Pair(14, 10),
            Pair(15, 50),
            Pair(16, 100),
            Pair(18, 500)
        )
    )

    val kineticShield = PsychicPower(
        "kineticShield",
        18,
        1,
        false,
        true,
        R.string.kineticShieldDesc,
        listOf(R.string.lifePointInput, R.string.lifePointDamageBarrier, R.string.lifePointBarrierEnergy),
        listOf(2, 6, 7, 10),
        listOf(
            2, 1,
            300,
            500,
            700,
            1000,
            Pair(1500, 60),
            Pair(2000, 80),
            Pair(3000, 120),
            Pair(5000, 160)
        )
    )

    val kineticArmor = PsychicPower(
        "kineticArmor",
        19,
        1,
        false,
        true,
        R.string.kineticArmorDesc,
        listOf(R.string.armorTypeInput),
        listOf(2, 10),
        listOf(
            2, 1,
            1,
            2,
            4,
            6,
            8,
            10,
            12,
            14
        )
    )

    val motionDetection = PsychicPower(
        "detectMotion",
        20,
        2,
        true,
        true,
        R.string.detectMotionDesc,
        listOf(R.string.physResMeter, R.string.physResKilometer),
        listOf(3, 7, 10),
        listOf(
            4, 2, 1,
            Pair(120, 10),
            Pair(160, 50),
            Pair(200, 100),
            Pair(240, 500),
            Pair(280, 1),
            Pair(320, 10),
            Pair(400, 100)
        )
    )

    val repulsion = PsychicPower(
        "repulsion",
        21,
        2,
        true,
        true,
        R.string.repulsionDesc,
        listOf(R.string.strengthInLine),
        listOf(4, 10),
        listOf(
            8, 6, 4, 2,
            Pair(6, 2),
            Pair(8, 5),
            Pair(10, 10),
            Pair(12, 20),
            Pair(14, 50),
            Pair(18, 100)
        )
    )

    val ballistics = PsychicPower(
        "ballistics",
        22,
        2,
        true,
        false,
        R.string.ballisticsDesc,
        listOf(R.string.projectionRange),
        listOf(3, 10),
        listOf(
            4, 2, 1,
            Pair(0, 5),
            Pair(10, 10),
            Pair(20, 15),
            Pair(30, 25),
            Pair(40, 40),
            Pair(50, 80),
            Pair(60, 150)
        )
    )

    val shatter = PsychicPower(
        "shatter",
        23,
        2,
        true,
        false,
        R.string.shatterDesc,
        listOf(R.string.physResInput),
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

    val kineticFlight = PsychicPower(
        "kineticFlight",
        24,
        2,
        true,
        true,
        R.string.kineticFlightDesc,
        listOf(R.string.flightInput),
        listOf(3, 10),
        listOf(
            4, 2, 1,
            6,
            8,
            10,
            12,
            14,
            16,
            18
        )
    )

    val organicKinesis = PsychicPower(
        "organicKinesis",
        25,
        2,
        true,
        true,
        R.string.organicKinesisDesc,
        listOf(R.string.weightFlightPhys),
        listOf(3, 10),
        listOf(
            6, 4, 2,
            listOf(100, 4, 100),
            listOf(250, 6, 120),
            listOf(500, 8, 140),
            listOf(1000, 10, 160),
            listOf(2500, 12, 180),
            listOf(5000, 14, 200),
            listOf(10000, 16, 220)
        )
    )

    val groundControl = PsychicPower(
        "groundControl",
        26,
        3,
        true,
        false,
        R.string.groundControlDesc,
        listOf(R.string.meterRadiusBarrier, R.string.kilometerRadiusBarrier),
        listOf(5, 9, 10),
        listOf(
            16, 12, 8, 6, 4,
            Pair(10, 40),
            Pair(100, 60),
            Pair(250, 80),
            Pair(500, 100),
            Pair(1, 140)
        )
    )

    val atomicRestructuring = PsychicPower(
        "atomRestructure",
        27,
        3,
        true,
        false,
        R.string.atomRestructureDesc,
        listOf(R.string.physResKilogram, R.string.physResTons),
        listOf(7, 8, 10),
        listOf(
            24, 20, 16, 12, 8, 6, 4,
            Pair(140, 100),
            Pair(160, 10),
            Pair(200, 100)
        )
    )

    val majorKinesis = PsychicPower(
        "majorKinesis",
        28,
        3,
        true,
        true,
        R.string.majorKinesisDesc,
        listOf(R.string.tonsFlightValue),
        listOf(6, 10),
        listOf(
            24, 20, 16, 12, 8, 4,
            Pair(500, 4),
            Pair(10000, 6),
            Pair(100000, 8),
            Pair(1000000, 10)
        )
    )

    init {
        allPowers.addAll(listOf(
            minorKinesis,
            kineticImpact,
            kineticTrap,
            kineticShield,
            kineticArmor,
            motionDetection,
            repulsion,
            ballistics,
            shatter,
            kineticFlight,
            organicKinesis,
            groundControl,
            atomicRestructuring,
            majorKinesis
        ))
    }
}