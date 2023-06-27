package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available telemetric powers.
 */
class Telemetry: Discipline("telemetry"){
    val senseResidues = PsychicPower(
        "senseResidues",
        R.string.senseResidues,
        1,
        true,
        true,
        R.string.senseResiduesDesc,
        listOf(
            "Fatigue 1",
            "One Hour",
            "Six Hours",
            "One Day",
            "Three Days",
            "One Week",
            "One Month",
            "One Year",
            "One Decade",
            "One Century"
        )
    )

    val readPast = PsychicPower(
        "readPast",
        R.string.readPast,
        2,
        true,
        false,
        R.string.readPastDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "One Hour",
            "Six Hours",
            "One Day",
            "One Week",
            "One Month",
            "One Year"
        )
    )

    val humanErudition = PsychicPower(
        "humanErudition",
        R.string.humanErudition,
        2,
        true,
        false,
        R.string.humanEruditionDesc,
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "One Day/80 PsR",
            "One Week/100 PsR",
            "One Month/120 PsR",
            "One Year/140 PsR",
            "Ten Years/160 PsR",
            "Fifty Years/180 PsR",
            "All of his life/200 PsR"
        )
    )

    val seeHistory = PsychicPower(
        "seeHistory",
        R.string.seeHistory,
        3,
        true,
        true,
        R.string.seeHistoryDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "One Year",
            "Ten Years",
            "One Century",
            "One Millennium",
            "Any Amount of Time"
        )
    )

    init{
        allPowers.addAll(listOf(
            senseResidues,
            readPast,
            humanErudition,
            seeHistory
        ))
    }
}