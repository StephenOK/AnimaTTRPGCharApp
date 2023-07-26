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
        86,
        1,
        true,
        true,
        R.string.senseResiduesDesc,
        listOf(
            R.string.oneHour,
            R.string.sixHours,
            R.string.oneDay,
            R.string.threeDays,
            R.string.oneWeek,
            R.string.oneMonth,
            R.string.oneYear,
            R.string.oneDecade,
            R.string.oneCentury
        ),
        listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
        listOf(1, null, null, null, null, null, null, null, null, null)
    )

    val readPast = PsychicPower(
        "readPast",
        87,
        2,
        true,
        false,
        R.string.readPastDesc,
        listOf(
            R.string.oneHour,
            R.string.sixHours,
            R.string.oneDay,
            R.string.oneWeek,
            R.string.oneMonth,
            R.string.oneYear
        ),
        listOf(4, 5, 6, 7, 8, 9, 10),
        listOf(8, 6, 4, 2, null, null, null, null, null, null)
    )

    val humanErudition = PsychicPower(
        "humanErudition",
        88,
        2,
        true,
        false,
        R.string.humanEruditionDesc,
        listOf(R.string.psyResTime),
        listOf(3, 10),
        listOf(
            6, 4, 2,
            Pair(80, {R.string.oneDay}),
            Pair(100, {R.string.oneWeek}),
            Pair(120, {R.string.oneMonth}),
            Pair(140, {R.string.oneYear}),
            Pair(160, {R.string.oneDecade}),
            Pair(180, {R.string.fiftyYears}),
            Pair(200, {R.string.lifelong})
        )
    )

    val seeHistory = PsychicPower(
        "seeHistory",
        89,
        3,
        true,
        true,
        R.string.seeHistoryDesc,
        listOf(
            R.string.oneYear,
            R.string.oneDecade,
            R.string.oneCentury,
            R.string.oneMillennium,
            R.string.anyTimeAmount
        ),
        listOf(5, 6, 7, 8, 9, 10),
        listOf(16, 12, 8, 6, 4, null, null, null, null, null)
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