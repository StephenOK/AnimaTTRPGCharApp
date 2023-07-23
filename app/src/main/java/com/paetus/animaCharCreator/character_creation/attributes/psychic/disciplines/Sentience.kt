package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available sentience powers.
 */
class Sentience: Discipline("sentience"){
    val senseFeelings = PsychicPower(
        "senseFeeling",
        76,
        1,
        true,
        true,
        R.string.senseFeelingDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR",
            "220 PsR",
            "240 PsR"
        )
    )

    val intensifyFeelings = PsychicPower(
        "intensifyFeeling",
        77,
        1,
        true,
        true,
        R.string.intensifyFeelingDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR",
            "220 PsR",
            "240 PsR"
        )
    )

    val detectFeelings = PsychicPower(
        "detectFeelings",
        78,
        1,
        true,
        true,
        R.string.detectFeelingsDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "80 PsR/10-meter radius",
            "100 PsR/50-meter radius",
            "120 PsR/100-meter radius",
            "140 PsR/250-meter radius",
            "160 PsR/500-meter radius",
            "180 PsR/1-kilometer radius",
            "200 PsR/10-kilometer radius",
            "220 PsR/100-kilometer radius"
        )
    )

    val connectSenses = PsychicPower(
        "connectSenses",
        79,
        1,
        true,
        true,
        R.string.connectSensesDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "60 PsR/ 10-meter radius",
            "80 PsR/100-meter radius",
            "100 PsR/500-meter radius",
            "120 PsR/1-kilometer radius",
            "140 PsR/10-kilometer radius",
            "160 PsR/100-kilometer radius",
            "180 PsR/1000-kilometer radius",
            "200 PsR/Any distance"
        )
    )

    val projectSenses = PsychicPower(
        "projectSenses",
        80,
        2,
        true,
        true,
        R.string.projectSensesDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "1-kilometer radius",
            "10-kilometer radius",
            "50-kilometer radius",
            "100-kilometer radius",
            "1000-kilometer radius",
            "Any distance"
        )
    )

    val eliminateSenses = PsychicPower(
        "eliminateSenses",
        81,
        2,
        true,
        true,
        R.string.elimSensesDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "220 PsR"
        )
    )

    val createFeelings = PsychicPower(
        "Create Feelings",
        82,
        2,
        true,
        true,
        R.string.createFeelingsDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 4",
            "Fatigue 2",
            "80 PsR",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR"
        )
    )

    val infuseFeelings = PsychicPower(
        "infuseFeelings",
        83,
        2,
        true,
        true,
        R.string.infuseFeelingsDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "100 PsR/5-meter radius",
            "120 PsR/10-meter radius",
            "140 PsR/25-meter radius",
            "160 PsR/50-meter radius",
            "180 PsR/100-meter radius",
            "220 PsR/500-meter radius"
        )
    )

    val destroyFeelings = PsychicPower(
        "destroyFeelings",
        84,
        3,
        true,
        false,
        R.string.destroyFeelingsDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR"
        )
    )

    val area = PsychicPower(
        "areaSentience",
        85,
        3,
        true,
        true,
        R.string.areaSentienceDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 4",
            "10-meter radius",
            "100-meter radius",
            "1-kilometer radius",
            "10-kilometer radius",
            "100-kilometer radius",
            "500-kilometer radius"
        )
    )

    init{
        allPowers.addAll(listOf(
            senseFeelings,
            intensifyFeelings,
            detectFeelings,
            connectSenses,
            projectSenses,
            eliminateSenses,
            createFeelings,
            infuseFeelings,
            destroyFeelings,
            area
        ))
    }
}