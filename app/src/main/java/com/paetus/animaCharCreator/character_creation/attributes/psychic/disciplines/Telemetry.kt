package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available telemetric powers.
 */
class Telemetry: Discipline(saveName = "telemetry"){
    private val senseResidues = PsychicPower(
        saveName = "senseResidues",
        name = 86,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.senseResiduesDesc,
        stringBaseList = listOf(
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
        stringBaseCount = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
        stringInput = listOf(1, null, null, null, null, null, null, null, null, null)
    )

    private val readPast = PsychicPower(
        saveName = "readPast",
        name = 87,
        level = 2,
        isActive = true,
        maintained = false,
        description = R.string.readPastDesc,
        stringBaseList = listOf(
            R.string.oneHour,
            R.string.sixHours,
            R.string.oneDay,
            R.string.oneWeek,
            R.string.oneMonth,
            R.string.oneYear
        ),
        stringBaseCount = listOf(4, 5, 6, 7, 8, 9, 10),
        stringInput = listOf(8, 6, 4, 2, null, null, null, null, null, null)
    )

    private val humanErudition = PsychicPower(
        saveName = "humanErudition",
        name = 88,
        level = 2,
        isActive = true,
        maintained = false,
        description = R.string.humanEruditionDesc,
        stringBaseList = listOf(R.string.psyResTime),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
            6, 4, 2,
            Pair(80) { R.string.oneDay },
            Pair(100) { R.string.oneWeek },
            Pair(120) { R.string.oneMonth },
            Pair(140) { R.string.oneYear },
            Pair(160) { R.string.oneDecade },
            Pair(180) { R.string.fiftyYears },
            Pair(200) { R.string.lifelong }
        )
    )

    private val seeHistory = PsychicPower(
        saveName = "seeHistory",
        name = 89,
        level = 3,
        isActive = true,
        maintained = true,
        description = R.string.seeHistoryDesc,
        stringBaseList = listOf(
            R.string.oneYear,
            R.string.oneDecade,
            R.string.oneCentury,
            R.string.oneMillennium,
            R.string.anyTimeAmount
        ),
        stringBaseCount = listOf(5, 6, 7, 8, 9, 10),
        stringInput = listOf(16, 12, 8, 6, 4, null, null, null, null, null)
    )

    init{
        allPowers.addAll(
            elements = listOf(
                senseResidues,
                readPast,
                humanErudition,
                seeHistory
            )
        )
    }
}