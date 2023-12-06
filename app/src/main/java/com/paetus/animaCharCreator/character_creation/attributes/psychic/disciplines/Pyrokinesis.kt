package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available pyrokinetic powers.
 */
class Pyrokinesis: Discipline(saveName = "pyrokinesis"){
    private val createFire = PsychicPower(
        saveName = "createFire",
        name = 29,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.createFirePowerDesc,
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

    private val extinguishFire = PsychicPower(
        saveName = "extinguishFire",
        name = 30,
        level = 1,
        isActive = true,
        maintained = false,
        description = R.string.extinguishFireDesc,
        stringBaseList = listOf(R.string.physResReduceIntense),
        stringBaseCount = listOf(1, 10),
        stringInput = listOf(
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

    private val controlFire = PsychicPower(
        saveName = "controlFire",
        name = 31,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.controlFireDesc,
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

    private val immolate = PsychicPower(
        saveName = "immolate",
        name = 32,
        level = 1,
        isActive = true,
        maintained = false,
        description = R.string.immolateDesc,
        stringBaseList = listOf(R.string.baseDamageArea),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
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

    private val igneousMaintenance = PsychicPower(
        saveName = "igneousMaint",
        name = 33,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.igneousMaintDesc,
        stringBaseList = listOf(R.string.intensities),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
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

    private val fireImmunity = PsychicPower(
        saveName = "fireImmunity",
        name = 34,
        level = 2,
        isActive = false,
        maintained = true,
        description = R.string.fireImmuneDesc,
        stringBaseList = listOf(R.string.intensities),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
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

    private val igneousBarrier = PsychicPower(
        saveName = "igneousBarrier",
        name = 35,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.igneousBarrierDesc,
        stringBaseList = listOf(R.string.baseDamageLine),
        stringBaseCount = listOf(4, 10),
        stringInput = listOf(
            6, 4, 2, 1,
            Pair(60, 5),
            Pair(80, 10),
            Pair(120, 20),
            Pair(160, 30),
            Pair(200, 40),
            Pair(240, 50)
        )
    )

    private val raiseTemperature = PsychicPower(
        saveName = "raiseTemp",
        name = 36,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.raiseTempPowerDesc,
        stringBaseList = listOf(R.string.tempIncreaseKilometer),
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

    private val consume = PsychicPower(
        saveName = "consume",
        name = 37,
        level = 3,
        isActive = true,
        maintained = false,
        description = R.string.consumeDesc,
        stringBaseList = listOf(R.string.physResAutoDamage),
        stringBaseCount = listOf(5, 10),
        stringInput = listOf(
            16, 12, 8, 6, 4,
            Pair(120, 80),
            Pair(140, 120),
            Pair(160, 160),
            Pair(180, 200),
            Pair(220, 250)
        )
    )

    private val nova = PsychicPower(
        saveName = "nova",
        name = 38,
        level = 3,
        isActive = true,
        maintained = true,
        description = R.string.novaDesc,
        stringBaseList = listOf(R.string.lifePointInput),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
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

    private val majorFire = PsychicPower(
        saveName = "majorFire",
        name = 39,
        level = 3,
        isActive = true,
        maintained = true,
        description = R.string.majorFireDesc,
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
            )
        )
    }
}