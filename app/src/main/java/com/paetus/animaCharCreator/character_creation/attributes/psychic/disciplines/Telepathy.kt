package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available telepathic powers.
 */
class Telepathy: Discipline("telepathy"){
    val areaScanning = PsychicPower(
        "areaScanning",
        0,
        1,
        true,
        true,
        R.string.areaScanningDesc,
        listOf(R.string.psyResMeter, R.string.psyResKilometer),
        listOf(2, 7, 10),
        listOf(
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

    val mentalRestraint = PsychicPower(
        "mentalRestraint",
        1,
        1,
        true,
        true,
        R.string.mentalRestraintDesc,
        listOf(R.string.psyResInput),
        listOf(2, 10),
        listOf(
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

    val mindReading = PsychicPower(
        "mindReading",
        2,
        1,
        true,
        true,
        R.string.mindReadingDesc,
        listOf(R.string.psyResInput),
        listOf(2, 10),
        listOf(
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

    val mentalCommunication = PsychicPower(
        "mentalComs",
        3,
        1,
        true,
        true,
        R.string.mentalComsDesc,
        listOf(R.string.meterInput, R.string.kilometerInput, R.string.anyDistance),
        listOf(2, 4, 9, 10),
        listOf(
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

    val psychicShield = PsychicPower(
        "psyShield",
        4,
        1,
        false,
        true,
        R.string.psychicShieldDesc,
        listOf(R.string.addPsyRes),
        listOf(2, 10),
        listOf(
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

    val psychicIllusion = PsychicPower(
        "psyIllusion",
        5,
        1,
        true,
        true,
        R.string.psyIllusionDesc,
        listOf(R.string.psyResInput),
        listOf(2, 10),
        listOf(
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

    val mentalResearch = PsychicPower(
        "mentalResearch",
        6,
        2,
        true,
        true,
        R.string.mentalResearchDesc,
        listOf(R.string.psyResInput),
        listOf(3, 10),
        listOf(
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

    val psychicAssault = PsychicPower(
        "psyAssault",
        7,
        2,
        true,
        false,
        R.string.psyAssaultDesc,
        listOf(R.string.psyResInput),
        listOf(3, 10),
        listOf(
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

    val psychicConnection = PsychicPower(
        "psyConnection",
        8,
        2,
        true,
        true,
        R.string.psyConnectionDesc,
        listOf(R.string.meterRadius, R.string.kilometerRadius, R.string.anyDistance),
        listOf(3, 5, 9, 10),
        listOf(
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

    val alterMemory = PsychicPower(
        "alterMemory",
        9,
        2,
        true,
        false,
        R.string.alterMemoryDesc,
        listOf(R.string.psyResInput),
        listOf(4, 10),
        listOf(
            8, 6, 4, 2,
            100,
            120,
            140,
            160,
            180,
            200
        )
    )

    val astralShape = PsychicPower(
        "astralShape",
        10,
        2,
        true,
        true,
        R.string.astralShapeDesc,
        listOf(R.string.kilometerRadius, R.string.anyDistance),
        listOf(4, 9, 10),
        listOf(
            8, 6, 4, 2,
            10,
            100,
            500,
            1000,
            5000,
            null
        )
    )

    val psychicTracking = PsychicPower(
        "psyTracking",
        11,
        2,
        true,
        true,
        R.string.psyTrackingDesc,
        listOf(R.string.psyResKilometer, R.string.psyResAnyDistance),
        listOf(4, 9, 10),
        listOf(
            8, 6, 4, 2,
            Pair(140, 10),
            Pair(160, 100),
            Pair(180, 500),
            Pair(200, 1000),
            Pair(220, 5000),
            260
        )
    )

    val mindControl = PsychicPower(
        "Mind Control",
        12,
        3,
        true,
        true,
        R.string.mindControlDesc,
        listOf(R.string.psyResInput),
        listOf(4, 10),
        listOf(
            12, 8, 6, 4,
            100,
            120,
            140,
            160,
            180,
            220
        )
    )

    val psychicDeath = PsychicPower(
        "psyDeath",
        13,
        3,
        true,
        false,
        R.string.psyDeathDesc,
        listOf(R.string.psyResInput),
        listOf(5, 10),
        listOf(
            16, 12, 8, 6, 4,
            140,
            160,
            180,
            220,
            240
        )
    )

    val area = PsychicPower(
        "area",
        14,
        3,
        true,
        true,
        R.string.areaDesc,
        listOf(R.string.meterInput, R.string.kilometerInput),
        listOf(4, 6, 10),
        listOf(
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
        allPowers.addAll(listOf(
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
        ))
    }
}