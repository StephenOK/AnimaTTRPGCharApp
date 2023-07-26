package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available energy powers.
 */
class Energy: Discipline("energy"){
    val objectCreation = PsychicPower(
        "createEnergyObj",
        65,
        1,
        true,
        true,
        R.string.createEnergyObjDesc,
        listOf(R.string.cubicMeterInput),
        listOf(2, 10),
        listOf(
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

    val energyDischarge = PsychicPower(
        "energyDischarge",
        66,
        1,
        true,
        false,
        R.string.energyDischargeDesc,
        listOf(R.string.damageInput, R.string.damageImmaterial),
        listOf(3, 7, 10),
        listOf(
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

    val createEnergy = PsychicPower(
        "createEnergy",
        67,
        1,
        true,
        true,
        R.string.createEnergyPowerDesc,
        listOf(R.string.intensities),
        listOf(2, 10),
        listOf(
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

    val energyShield = PsychicPower(
        "energyShield",
        68,
        1,
        false,
        true,
        R.string.energyShieldDesc,
        listOf(R.string.lifePointInput),
        listOf(3, 10),
        listOf(
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

    val senseEnergy = PsychicPower(
        "senseEnergy",
        69,
        1,
        true,
        true,
        R.string.senseEnergyDesc,
        listOf(R.string.meterRadius, R.string.kilometerRadius),
        listOf(2, 7, 10),
        listOf(
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

    val modifyNature = PsychicPower(
        "modNature",
        70,
        2,
        true,
        false,
        R.string.modNatureDesc,
        listOf(R.string.physResIntensities),
        listOf(4, 10),
        listOf(
            8, 6, 4, 2,
            Pair(100, 6),
            Pair(120, 8),
            Pair(140, 12),
            Pair(160, 16),
            Pair(180, 20),
            Pair(220, 25)
        )
    )

    val undoEnergy = PsychicPower(
        "undoEnergy",
        71,
        2,
        true,
        false,
        R.string.undoEnergyDesc,
        listOf(R.string.physResReduceIntense),
        listOf(3, 10),
        listOf(
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

    val immunity = PsychicPower(
        "immunity",
        72,
        2,
        false,
        true,
        R.string.immunityPowerDesc,
        listOf(R.string.intensities),
        listOf(5, 10),
        listOf(
            12, 8, 6, 4, 2,
            10,
            15,
            20,
            30,
            40
        )
    )

    val controlEnergy = PsychicPower(
        "controlEnergy",
        73,
        2,
        true,
        true,
        R.string.controlEnergyDesc,
        listOf(R.string.physResIntensities),
        listOf(3, 10),
        listOf(
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

    val energyDome = PsychicPower(
        "energyDome",
        74,
        3,
        true,
        false,
        R.string.energyDomeDesc,
        listOf(R.string.baseDamageArea, R.string.baseDamageAreaImmaterial),
        listOf(5, 8, 10),
        listOf(
            16, 12, 8, 6, 4,
            Pair(100, 25),
            Pair(120, 50),
            Pair(140, 100),
            Pair(160, 200),
            Pair(200, 500)
        )
    )

    val majorEnergy = PsychicPower(
        "majorEnergy",
        75,
        3,
        true,
        true,
        R.string.majorEnergyDesc,
        listOf(R.string.intensities),
        listOf(6, 10),
        listOf(
            20, 16, 12, 8, 6, 4,
            25,
            35,
            45,
            55
        )
    )

    init{
        allPowers.addAll(listOf(
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
        ))
    }
}