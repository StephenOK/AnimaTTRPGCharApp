package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available energy powers.
 */
class Energy: Discipline(saveName = "energy"){
    private val objectCreation = PsychicPower(
        saveName = "createEnergyObj",
        name = 65,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.createEnergyObjDesc,
        stringBaseList = listOf(R.string.cubicMeterInput),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(
            4, 2, 1,
            1,
            2,
            3,
            4,
            5,
            10,
            20
        )
    )

    private val energyDischarge = PsychicPower(
        saveName = "energyDischarge",
        name = 66,
        level = 1,
        isActive = true,
        maintained = false,
        description = R.string.energyDischargeDesc,
        stringBaseList = listOf(R.string.damageInput, R.string.damageImmaterial),
        stringBaseCount = listOf(3, 7, 10),
        stringInput = listOf(
            4, 2, 1,
            50,
            70,
            100,
            120,
            140,
            180,
            220
        )
    )

    private val createEnergy = PsychicPower(
        saveName = "createEnergy",
        name = 67,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.createEnergyPowerDesc,
        stringBaseList = listOf(R.string.intensities),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(
            2, 1,
            1,
            3,
            5,
            7,
            10,
            13,
            16,
            20
        )
    )

    private val energyShield = PsychicPower(
        saveName = "energyShield",
        name = 68,
        level = 1,
        isActive = false,
        maintained = true,
        description = R.string.energyShieldDesc,
        stringBaseList = listOf(R.string.lifePointInput),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
            6, 4, 2,
            300,
            500,
            800,
            1000,
            1400,
            2000,
            3000
        )
    )

    private val senseEnergy = PsychicPower(
        saveName = "senseEnergy",
        name = 69,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.senseEnergyDesc,
        stringBaseList = listOf(R.string.meterRadius, R.string.kilometerRadius),
        stringBaseCount = listOf(2, 7, 10),
        stringInput = listOf(
            2, 1,
            10,
            50,
            100,
            250,
            500,
            1,
            10,
            100
        )
    )

    private val modifyNature = PsychicPower(
        saveName = "modNature",
        name = 70,
        level = 2,
        isActive = true,
        maintained = false,
        description = R.string.modNatureDesc,
        stringBaseList = listOf(R.string.physResIntensities),
        stringBaseCount = listOf(4, 10),
        stringInput = listOf(
            8, 6, 4, 2,
            Pair(100, 6),
            Pair(120, 8),
            Pair(140, 12),
            Pair(160, 16),
            Pair(180, 20),
            Pair(220, 25)
        )
    )

    private val undoEnergy = PsychicPower(
        saveName = "undoEnergy",
        name = 71,
        level = 2,
        isActive = true,
        maintained = false,
        description = R.string.undoEnergyDesc,
        stringBaseList = listOf(R.string.physResReduceIntense),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
            6, 4, 2,
            Pair(100, 1),
            Pair(120, 3),
            Pair(140, 5),
            Pair(160, 8),
            Pair(180, 12),
            Pair(200, 18),
            Pair(240, 24)
        )
    )

    private val immunity = PsychicPower(
        saveName = "immunity",
        name = 72,
        level = 2,
        isActive = false,
        maintained = true,
        description = R.string.immunityPowerDesc,
        stringBaseList = listOf(R.string.intensities),
        stringBaseCount = listOf(5, 10),
        stringInput = listOf(
            12, 8, 6, 4, 2,
            10,
            15,
            20,
            30,
            40
        )
    )

    private val controlEnergy = PsychicPower(
        saveName = "controlEnergy",
        name = 73,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.controlEnergyDesc,
        stringBaseList = listOf(R.string.physResIntensities),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
            6, 4, 2,
            Pair(80, 4),
            Pair(100, 6),
            Pair(120, 8),
            Pair(140, 12),
            Pair(160, 16),
            Pair(180, 20),
            Pair(220, 25)
        )
    )

    private val energyDome = PsychicPower(
        saveName = "energyDome",
        name = 74,
        level = 3,
        isActive = true,
        maintained = false,
        description = R.string.energyDomeDesc,
        stringBaseList = listOf(R.string.baseDamageArea, R.string.baseDamageAreaImmaterial),
        stringBaseCount = listOf(5, 8, 10),
        stringInput = listOf(
            16, 12, 8, 6, 4,
            Pair(100, 25),
            Pair(120, 50),
            Pair(140, 100),
            Pair(160, 200),
            Pair(200, 500)
        )
    )

    private val majorEnergy = PsychicPower(
        saveName = "majorEnergy",
        name = 75,
        level = 3,
        isActive = true,
        maintained = true,
        description = R.string.majorEnergyDesc,
        stringBaseList = listOf(R.string.intensities),
        stringBaseCount = listOf(6, 10),
        stringInput = listOf(
            20, 16, 12, 8, 6, 4,
            25,
            35,
            45,
            55
        )
    )

    init{
        allPowers.addAll(
            elements = listOf(
                objectCreation,
                energyDischarge,
                createEnergy,
                energyShield,
                senseEnergy,
                modifyNature,
                undoEnergy,
                immunity,
                controlEnergy,
                energyDome,
                majorEnergy
            )
        )
    }
}