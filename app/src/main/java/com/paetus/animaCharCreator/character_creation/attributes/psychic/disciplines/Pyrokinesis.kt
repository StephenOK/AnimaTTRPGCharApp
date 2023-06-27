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
        R.string.createFirePower,
        1,
        true,
        true,
        R.string.createFirePowerDesc,
        listOf(
            "Fatigue 1",
            "1 Intensity",
            "3 Intensities",
            "5 Intensities",
            "7 Intensities",
            "10 Intensities",
            "13 Intensities",
            "16 Intensities",
            "20 Intensities",
            "25 Intensities"
        )
    )

    val extinguishFire = PsychicPower(
        "extinguishFire",
        R.string.extinguishFire,
        1,
        true,
        false,
        R.string.extinguishFireDesc,
        listOf(
            "Fatigue 1",
            "-1 Intensity/80 PhR",
            "-3 intensities/100 PhR",
            "-5 Intensities/120 PhR",
            "-7 Intensities/140 PhR",
            "-10 Intensities/160 PhR",
            "-15 Intensities/180 PhR",
            "-20 Intensities/200 PhR",
            "-30 Intensities/220 PhR",
            "-40 Intensities/260 PhR"
        )
    )

    val controlFire = PsychicPower(
        "controlFire",
        R.string.controlFirePow,
        1,
        true,
        true,
        R.string.controlFireDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "4 Intensities/80 PhR",
            "6 Intensities/100 PhR",
            "8 Intensities/120 PhR",
            "12 Intensities/140 PhR",
            "16 Intensities/160 PhR",
            "20 Intensities/180 PhR",
            "25 Intensities/200 PhR",
            "30 Intensities/240 PhR"
        )
    )

    val immolate = PsychicPower(
        "immolate",
        R.string.immolate,
        1,
        true,
        false,
        R.string.immolateDesc,
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "Base Damage 60/5-meter radius",
            "Base Damage 80/10-meter radius",
            "Base Damage 100/20-meter radius",
            "Base Damage 120/30-meter radius",
            "Base Damage 150/50-meter radius",
            "Base Damage 200/100-meter radius",
            "Base Damage 250/200-meter radius"
        )
    )

    val igneousMaintenance = PsychicPower(
        "igneousMaint",
        R.string.igneousMaint,
        2,
        true,
        true,
        R.string.igneousMaintDesc,
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "5 Intensities",
            "10 Intensities",
            "15 Intensities",
            "20 Intensities",
            "30 Intensities",
            "40 Intensities",
            "50 Intensities"
        )
    )

    val fireImmunity = PsychicPower(
        "fireImmunity",
        R.string.fireImmunity,
        2,
        false,
        true,
        R.string.fireImmuneDesc,
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "5 Intensities",
            "10 Intensities",
            "15 Intensities",
            "20 Intensities",
            "30 Intensities",
            "40 Intensities",
            "50 Intensities"
        )
    )

    val igneousBarrier = PsychicPower(
        "igneousBarrier",
        R.string.igneousBarrier,
        2,
        true,
        true,
        R.string.igneousBarrierDesc,
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "Base Damage 60/5 meters long",
            "Base Damage 80/10 meters long",
            "Base Damage 120/20 meters long",
            "Base Damage 160/30 meters long",
            "Base Damage 200/40 meters long",
            "Base Damage 240/50 meters long"
        )
    )

    val raiseTemperature = PsychicPower(
        "raiseTemp",
        R.string.raiseTempPower,
        2,
        true,
        true,
        R.string.raiseTempPowerDesc,
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "+5℃/1-kilometer radius",
            "+10℃/5-kilometer radius",
            "+15℃/10-kilometer radius",
            "+20℃/25-kilometer radius",
            "+30℃/50-kilometer radius",
            "+40℃/100-kilometer radius"
        )
    )

    val consume = PsychicPower(
        "consume",
        R.string.consume,
        3,
        true,
        false,
        R.string.consumeDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "120 PhR/Automatic Damage 80",
            "140 PhR/Automatic Damage 120",
            "160 PhR/Automatic Damage 160",
            "180 PhR/Automatic Damage 200",
            "220 PhR/Automatic Damage 250"
        )
    )

    val nova = PsychicPower(
        "nova",
        R.string.nova,
        3,
        true,
        true,
        R.string.novaDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "10 Life Points",
            "20 Life Points",
            "30 Life Points",
            "40 Life Points",
            "60 Life Points",
            "80 Life Points",
            "120 Life Points"
        )
    )

    val majorFire = PsychicPower(
        "majorFire",
        R.string.majorFire,
        3,
        true,
        true,
        R.string.majorFireDesc,
        listOf(
            "Fatigue 20",
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "30 Intensities",
            "40 Intensities",
            "50 Intensities",
            "60 Intensities"
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