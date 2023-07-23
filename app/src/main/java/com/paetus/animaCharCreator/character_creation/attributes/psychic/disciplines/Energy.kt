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
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "1 cubic meter",
            "2 cubic meters",
            "3 cubic meters",
            "4 cubic meters",
            "5 cubic meters",
            "10 cubic meters",
            "20 cubic meters"
        )
    )

    val energyDischarge = PsychicPower(
        "energyDischarge",
        66,
        1,
        true,
        false,
        R.string.energyDischargeDesc,
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "Damage 50",
            "Damage 70",
            "Damage 100",
            "Damage 120",
            "Damage 140/It affects immaterial beings",
            "Damage 180/It affects immaterial beings",
            "Damage 220/It affects immaterial beings"
        )
    )

    val createEnergy = PsychicPower(
        "createEnergy",
        67,
        1,
        true,
        true,
        R.string.createEnergyPowerDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "1 Intensity",
            "3 Intensities",
            "5 Intensities",
            "7 Intensities",
            "10 Intensities",
            "13 Intensities",
            "16 Intensities",
            "20 Intensities"
        )
    )

    val energyShield = PsychicPower(
        "energyShield",
        68,
        1,
        false,
        true,
        R.string.energyShieldDesc,
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "300 LP",
            "500 LP",
            "800 LP",
            "1000 LP",
            "1400 LP",
            "2000 LP",
            "3000 LP"
        )
    )

    val senseEnergy = PsychicPower(
        "senseEnergy",
        69,
        1,
        true,
        true,
        R.string.senseEnergyDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "10-meter radius",
            "50-meter radius",
            "100-meter radius",
            "250-meter radius",
            "500-meter radius",
            "1-kilometer radius",
            "10-kilometer radius",
            "100-kilometer radius"
        )
    )

    val modifyNature = PsychicPower(
        "modNature",
        70,
        2,
        true,
        false,
        R.string.modNatureDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "6 Intensities/100 PhR",
            "8 Intensities/120 PhR",
            "12 Intensities/140 PhR",
            "16 Intensities/160 PhR",
            "20 Intensities/180 PhR",
            "25 Intensities/220 PhR"
        )
    )

    val undoEnergy = PsychicPower(
        "undoEnergy",
        71,
        2,
        true,
        false,
        R.string.undoEnergyDesc,
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "-1 Intensity/100 PhR",
            "-3 Intensities/120 PhR",
            "-5 Intensities/140 PhR",
            "-8 Intensities/160 PhR",
            "-12 Intensities/180 PhR",
            "-18 Intensities/200 PhR",
            "-24 Intensities/240 PhR"
        )
    )

    val immunity = PsychicPower(
        "immunity",
        72,
        2,
        false,
        true,
        R.string.immunityPowerDesc,
        listOf(
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "10 Intensities",
            "15 Intensities",
            "20 Intensities",
            "30 Intensities",
            "40 Intensities"
        )
    )

    val controlEnergy = PsychicPower(
        "controlEnergy",
        73,
        2,
        true,
        true,
        R.string.controlEnergyDesc,
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "4 Intensities/80 PhR",
            "6 Intensities/100 PhR",
            "8 Intensities/120 PhR",
            "12 Intensities/140 PhR",
            "16 Intensities/160 PhR",
            "20 Intensities/180 PhR",
            "25 Intensities/220 PhR"
        )
    )

    val energyDome = PsychicPower(
        "energyDome",
        74,
        3,
        true,
        false,
        R.string.energyDomeDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Base Damage 100/25-meter radius",
            "Base Damage 120/50-meter radius",
            "Base Damage 140/100-meter radius",
            "Base Damage 160/200-meter radius/It affects immaterial beings",
            "Base Damage 200/500-meter radius/It affects immaterial beings"
        )
    )

    val majorEnergy = PsychicPower(
        "majorEnergy",
        75,
        3,
        true,
        true,
        R.string.majorEnergyDesc,
        listOf(
            "Fatigue 20",
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "25 Intensities",
            "35 Intensities",
            "45 Intensities",
            "55 Intensities"
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