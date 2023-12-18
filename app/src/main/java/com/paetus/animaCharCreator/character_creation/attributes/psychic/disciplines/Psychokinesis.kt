package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available psychokinetic powers.
 */
class Psychokinesis: Discipline(saveName = "psychokinesis"){
    private val minorKinesis = PsychicPower(
        saveName = "minorKinesis",
        name = 15,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.minorKinesisDesc,
        stringBaseList = listOf(R.string.kilogramsFlightValue),
        stringBaseCount = listOf(1, 10),
        stringInput = listOf(
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

    private val kineticImpact = PsychicPower(
        saveName = "kineticImpact",
        name = 16,
        level = 1,
        isActive = true,
        maintained = false,
        description = R.string.kineticImpactDesc,
        stringBaseList = listOf(R.string.strengthInput),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(
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

    private val kineticTrap = PsychicPower(
        saveName = "kineticTrap",
        name = 17,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.psyKinTrapDesc,
        stringBaseList = listOf(R.string.strengthInput, R.string.strengthWithRadius),
        stringBaseCount = listOf(2, 5, 10),
        stringInput = listOf(
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

    private val kineticShield = PsychicPower(
        saveName = "kineticShield",
        name = 18,
        level = 1,
        isActive = false,
        maintained = true,
        description = R.string.kineticShieldDesc,
        stringBaseList = listOf(R.string.lifePointInput, R.string.lifePointDamageBarrier, R.string.lifePointBarrierEnergy),
        stringBaseCount = listOf(2, 6, 7, 10),
        stringInput = listOf(
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

    private val kineticArmor = PsychicPower(
        saveName = "kineticArmor",
        name = 19,
        level = 1,
        isActive = false,
        maintained = true,
        description = R.string.kineticArmorDesc,
        stringBaseList = listOf(R.string.armorTypeInput),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(
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

    private val motionDetection = PsychicPower(
        saveName = "detectMotion",
        name = 20,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.detectMotionDesc,
        stringBaseList = listOf(R.string.physResMeter, R.string.physResKilometer),
        stringBaseCount = listOf(3, 7, 10),
        stringInput = listOf(
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

    private val repulsion = PsychicPower(
        saveName = "repulsion",
        name = 21,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.repulsionDesc,
        stringBaseList = listOf(R.string.strengthInLine),
        stringBaseCount = listOf(4, 10),
        stringInput = listOf(
            8, 6, 4, 2,
            Pair(6, 2),
            Pair(8, 5),
            Pair(10, 10),
            Pair(12, 20),
            Pair(14, 50),
            Pair(18, 100)
        )
    )

    private val ballistics = PsychicPower(
        saveName = "ballistics",
        name = 22,
        level = 2,
        isActive = true,
        maintained = false,
        description = R.string.ballisticsDesc,
        stringBaseList = listOf(R.string.projectionRange),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
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

    private val shatter = PsychicPower(
        saveName = "shatter",
        name = 23,
        level = 2,
        isActive = true,
        maintained = false,
        description = R.string.shatterDesc,
        stringBaseList = listOf(R.string.physResInput),
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

    private val kineticFlight = PsychicPower(
        saveName = "kineticFlight",
        name = 24,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.kineticFlightDesc,
        stringBaseList = listOf(R.string.flightInput),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
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

    private val organicKinesis = PsychicPower(
        saveName = "organicKinesis",
        name = 25,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.organicKinesisDesc,
        stringBaseList = listOf(R.string.weightFlightPhys),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
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

    private val groundControl = PsychicPower(
        saveName = "groundControl",
        name = 26,
        level = 3,
        isActive = true,
        maintained = false,
        description = R.string.groundControlDesc,
        stringBaseList = listOf(R.string.meterRadiusBarrier, R.string.kilometerRadiusBarrier),
        stringBaseCount = listOf(5, 9, 10),
        stringInput = listOf(
            16, 12, 8, 6, 4,
            Pair(10, 40),
            Pair(100, 60),
            Pair(250, 80),
            Pair(500, 100),
            Pair(1, 140)
        )
    )

    private val atomicRestructuring = PsychicPower(
        saveName = "atomRestructure",
        name = 27,
        level = 3,
        isActive = true,
        maintained = false,
        description = R.string.atomRestructureDesc,
        stringBaseList = listOf(R.string.physResKilogram, R.string.physResTons),
        stringBaseCount = listOf(7, 8, 10),
        stringInput = listOf(
            24, 20, 16, 12, 8, 6, 4,
            Pair(140, 100),
            Pair(160, 10),
            Pair(200, 100)
        )
    )

    private val majorKinesis = PsychicPower(
        saveName = "majorKinesis",
        name = 28,
        level = 3,
        isActive = true,
        maintained = true,
        description = R.string.majorKinesisDesc,
        stringBaseList = listOf(R.string.tonsFlightValue),
        stringBaseCount = listOf(6, 10),
        stringInput = listOf(
            24, 20, 16, 12, 8, 4,
            Pair(500, 4),
            Pair(10000, 6),
            Pair(100000, 8),
            Pair(1000000, 10)
        )
    )

    init {
        allPowers.addAll(
            elements = listOf(
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
            )
        )
    }
}