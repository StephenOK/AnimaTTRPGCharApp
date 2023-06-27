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
        R.string.createChillPow,
        1,
        true,
        true,
        R.string.createChillPowDesc,
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

    val freeze = PsychicPower(
        "freeze",
        R.string.freezePow,
        1,
        true,
        true,
        R.string.freezePowDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "80 PhR",
            "100 PhR",
            "120 PhR",
            "140 PhR",
            "160 PhR",
            "180 PhR",
            "220 PhR"
        )
    )

    val senseTemperature = PsychicPower(
        "senseTemp",
        R.string.senseTemp,
        1,
        true,
        true,
        R.string.senseTempDesc,
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "10-meter radius",
            "50-meter radius",
            "100-meter radius",
            "500-meter radius",
            "1-kilometer radius",
            "10-kilometer radius",
            "100-kilometer radius"
        )
    )

    val eliminateCold = PsychicPower(
        "elimCold",
        R.string.elimCold,
        1,
        true,
        false,
        R.string.elimColdDesc,
        listOf(
            "Fatigue 1",
            "-1 Intensity/80 PhR",
            "-3 Intensities/100 PhR",
            "-5 Intensities/120 PhR",
            "-7 Intensities/140 PhR",
            "-10 Intensities/160 PhR",
            "-15 Intensities/180 PhR",
            "-20 Intensities/200 PhR",
            "-30 Intensities/220 PhR",
            "-40 Intensities/260 PhR"
        )
    )

    val coldDominion = PsychicPower(
        "coldDominion",
        R.string.coldDominion,
        1,
        true,
        true,
        R.string.coldDominionDesc,
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

    val crystallize = PsychicPower(
        "crystallize",
        R.string.crystallize,
        2,
        true,
        true,
        R.string.crystallizeDesc,
        listOf(
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "120 PhR",
            "140 PhR",
            "160 PhR",
            "180 PhR",
            "220 PhR"
        )
    )

    val iceSplinters = PsychicPower(
        "iceSplinters",
        R.string.iceSplinters,
        2,
        true,
        false,
        R.string.iceSplintersDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "Base Damage 80",
            "Base Damage 100",
            "Base Damage 120",
            "Base Damage 160/5-meter area",
            "Base Damage 200/25-meter area"
        )
    )

    val decreaseTemperature = PsychicPower(
        "decreaseTemp",
        R.string.decreaseTemp,
        2,
        true,
        true,
        R.string.decreaseTempDesc,
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "-5℃/1-kilometer radius",
            "-10℃/5-kilometer radius",
            "-15℃/10-kilometer radius",
            "-20℃/25-kilometer radius",
            "-30℃/50-kilometer radius",
            "-40℃/100-kilometer radius"
        )
    )

    val iceShield = PsychicPower(
        "iceShield",
        R.string.iceShield,
        2,
        false,
        true,
        R.string.iceShieldDesc,
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "600 LP",
            "800 LP",
            "1200 LP",
            "1800 LP",
            "2500 LP",
            "4000 LP",
            "6000 LP"
        )
    )

    val absoluteZero = PsychicPower(
        "absoluteZero",
        R.string.absoluteZero,
        3,
        true,
        true,
        R.string.absoluteZeroDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "5-meter radius",
            "10-meter radius",
            "20-meter radius",
            "50-meter radius",
            "100-meter radius"
        )
    )

    val everlastingMoment = PsychicPower(
        "everlastMoment",
        R.string.everlastMoment,
        3,
        true,
        true,
        R.string.everlastMomentDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "120 PhR/5-meter radius",
            "140 PhR/10-meter radius",
            "160 PhR/20-meter radius",
            "180 PhR/50-meter radius",
            "200 PhR/100-meter radius"
        )
    )

    val majorCold = PsychicPower(
        "majorCold",
        R.string.majorCold,
        3,
        true,
        true,
        R.string.majorColdDesc,
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