package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available telepathic powers.
 */
class Telepathy: Discipline(saveName = "telepathy"){
    private val areaScanning = PsychicPower(
        saveName = "areaScanning",
        name = 0,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.areaScanningDesc,
        stringBaseList = listOf(R.string.psyResMeter, R.string.psyResKilometer),
        stringBaseCount = listOf(2, 7, 10),
        stringInput = listOf(
            2, 1,
            Pair(100, 10),
            Pair(120, 50),
            Pair(140, 100),
            Pair(160, 250),
            Pair(180, 500),
            Pair(200, 1),
            Pair(220, 10),
            Pair(260, 100)
        )
    )

    private val mentalRestraint = PsychicPower(
        saveName = "mentalRestraint",
        name = 1,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.mentalRestraintDesc,
        stringBaseList = listOf(R.string.psyResInput),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(
            4, 2,
            80,
            100,
            120,
            140,
            160,
            180,
            200,
            220
        )
    )

    private val mindReading = PsychicPower(
        saveName = "mindReading",
        name = 2,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.mindReadingDesc,
        stringBaseList = listOf(R.string.psyResInput),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(
            2, 1,
            100,
            120,
            140,
            160,
            180,
            200,
            220,
            240
        )
    )

    private val mentalCommunication = PsychicPower(
        saveName = "mentalComs",
        name = 3,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.mentalComsDesc,
        stringBaseList = listOf(R.string.meterInput, R.string.kilometerInput, R.string.anyDistance),
        stringBaseCount = listOf(2, 4, 9, 10),
        stringInput = listOf(
            2, 1,
            100,
            500,
            1,
            10,
            100,
            1000,
            5000,
            null
        )
    )

    private val psychicShield = PsychicPower(
        saveName = "psyShield",
        name = 4,
        level = 1,
        isActive = false,
        maintained = true,
        description = R.string.psychicShieldDesc,
        stringBaseList = listOf(R.string.addPsyRes),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(
            2, 1,
            10,
            30,
            50,
            80,
            120,
            160,
            200,
            240
        )
    )

    private val psychicIllusion = PsychicPower(
        saveName = "psyIllusion",
        name = 5,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.psyIllusionDesc,
        stringBaseList = listOf(R.string.psyResInput),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(
            2, 1,
            80,
            100,
            120,
            140,
            160,
            180,
            200,
            220
        )
    )

    private val mentalResearch = PsychicPower(
        saveName = "mentalResearch",
        name = 6,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.mentalResearchDesc,
        stringBaseList = listOf(R.string.psyResInput),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
            6, 4, 2,
            100,
            120,
            140,
            160,
            180,
            200,
            240
        )
    )

    private val psychicAssault = PsychicPower(
        saveName = "psyAssault",
        name = 7,
        level = 2,
        isActive = true,
        maintained = false,
        description = R.string.psyAssaultDesc,
        stringBaseList = listOf(R.string.psyResInput),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
            8, 4, 2,
            120,
            140,
            160,
            180,
            200,
            220,
            260
        )
    )

    private val psychicConnection = PsychicPower(
        saveName = "psyConnection",
        name = 8,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.psyConnectionDesc,
        stringBaseList = listOf(R.string.meterRadius, R.string.kilometerRadius, R.string.anyDistance),
        stringBaseCount = listOf(3, 5, 9, 10),
        stringInput = listOf(
            6, 4, 2,
            100,
            500,
            1,
            10,
            100,
            1000,
            null
        )
    )

    private val alterMemory = PsychicPower(
        saveName = "alterMemory",
        name = 9,
        level = 2,
        isActive = true,
        maintained = false,
        description = R.string.alterMemoryDesc,
        stringBaseList = listOf(R.string.psyResInput),
        stringBaseCount = listOf(4, 10),
        stringInput = listOf(
            8, 6, 4, 2,
            100,
            120,
            140,
            160,
            180,
            200
        )
    )

    private val astralShape = PsychicPower(
        saveName = "astralShape",
        name = 10,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.astralShapeDesc,
        stringBaseList = listOf(R.string.kilometerRadius, R.string.anyDistance),
        stringBaseCount = listOf(4, 9, 10),
        stringInput = listOf(
            8, 6, 4, 2,
            10,
            100,
            500,
            1000,
            5000,
            null
        )
    )

    private val psychicTracking = PsychicPower(
        saveName = "psyTracking",
        name = 11,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.psyTrackingDesc,
        stringBaseList = listOf(R.string.psyResKilometer, R.string.psyResAnyDistance),
        stringBaseCount = listOf(4, 9, 10),
        stringInput = listOf(
            8, 6, 4, 2,
            Pair(140, 10),
            Pair(160, 100),
            Pair(180, 500),
            Pair(200, 1000),
            Pair(220, 5000),
            260
        )
    )

    private val mindControl = PsychicPower(
        saveName = "Mind Control",
        name = 12,
        level = 3,
        isActive = true,
        maintained = true,
        description = R.string.mindControlDesc,
        stringBaseList = listOf(R.string.psyResInput),
        stringBaseCount = listOf(4, 10),
        stringInput = listOf(
            12, 8, 6, 4,
            100,
            120,
            140,
            160,
            180,
            220
        )
    )

    private val psychicDeath = PsychicPower(
        saveName = "psyDeath",
        name = 13,
        level = 3,
        isActive = true,
        maintained = false,
        description = R.string.psyDeathDesc,
        stringBaseList = listOf(R.string.psyResInput),
        stringBaseCount = listOf(5, 10),
        stringInput = listOf(
            16, 12, 8, 6, 4,
            140,
            160,
            180,
            220,
            240
        )
    )

    val area = PsychicPower(
        saveName = "area",
        name = 14,
        level = 3,
        isActive = true,
        maintained = true,
        description = R.string.areaDesc,
        stringBaseList = listOf(R.string.meterInput, R.string.kilometerInput),
        stringBaseCount = listOf(4, 6, 10),
        stringInput = listOf(
            16, 12, 8, 4,
            10,
            100,
            1,
            10,
            100,
            500
        )
    )

    init{
        allPowers.addAll(
            elements = listOf(
                areaScanning,
                mentalRestraint,
                mindReading,
                mentalCommunication,
                psychicShield,
                psychicIllusion,
                mentalResearch,
                psychicAssault,
                psychicConnection,
                alterMemory,
                astralShape,
                psychicTracking,
                mindControl,
                psychicDeath,
                area
            )
        )
    }
}