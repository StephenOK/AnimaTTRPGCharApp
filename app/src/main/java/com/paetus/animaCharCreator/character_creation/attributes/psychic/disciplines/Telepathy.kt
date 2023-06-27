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
        R.string.areaScanning,
        1,
        true,
        true,
        R.string.areaScanningDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "100 PsR/10-meter radius",
            "120 PsR/50-meter radius",
            "140 PsR/100-meter radius",
            "160 PsR/250-meter radius",
            "180 PsR/500-meter radius",
            "200 PsR/1 kilometer radius",
            "220 PsR/10 kilometer radius",
            "260 PsR/100 kilometer radius"
        )
    )

    val mentalRestraint = PsychicPower(
        "mentalRestraint",
        R.string.mentalRestraint,
        1,
        true,
        true,
        R.string.mentalRestraintDesc,
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "80 PsR",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR",
            "220 PsR"
        )
    )

    val mindReading = PsychicPower(
        "mindReading",
        R.string.mindReading,
        1,
        true,
        true,
        R.string.mindReadingDesc,
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

    val mentalCommunication = PsychicPower(
        "mentalComs",
        R.string.mentalComs,
        1,
        true,
        true,
        R.string.mentalComsDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "100 meters",
            "500 meters",
            "1 kilometer",
            "10 kilometers",
            "100 kilometers",
            "1000 kilometers",
            "5000 kilometers",
            "Any distance"
        )
    )

    val psychicShield = PsychicPower(
        "psyShield",
        R.string.psychicShield,
        1,
        false,
        true,
        R.string.psychicShieldDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "+10 PsR",
            "+30 PsR",
            "+50 PsR",
            "+80 PsR",
            "+120 PsR",
            "+160 PsR",
            "+200 PsR",
            "+240 PsR"
        )
    )

    val psychicIllusion = PsychicPower(
        "psyIllusion",
        R.string.psyIllusion,
        1,
        true,
        true,
        R.string.psyIllusionDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "80 PsR",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR",
            "220 PsR"
        )
    )

    val mentalResearch = PsychicPower(
        "mentalResearch",
        R.string.mentalResearchDesc,
        2,
        true,
        true,
        R.string.mentalResearchDesc,
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR",
            "240 PsR"
        )
    )

    val psychicAssault = PsychicPower(
        "psyAssault",
        R.string.psyAssault,
        2,
        true,
        false,
        R.string.psyAssaultDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 4",
            "Fatigue 2",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR",
            "220 PsR",
            "260 PsR"
        )
    )

    val psychicConnection = PsychicPower(
        "psyConnection",
        R.string.psyConnection,
        2,
        true,
        true,
        R.string.psyConnectionDesc,
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "100 meter radius",
            "500 meter radius",
            "1 kilometer radius",
            "10 kilometer radius",
            "100 kilometer radius",
            "1000 kilometer radius",
            "Any distance"
        )
    )

    val alterMemory = PsychicPower(
        "alterMemory",
        R.string.alterMemory,
        2,
        true,
        false,
        R.string.alterMemoryDesc,
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
            "200 PsR"
        )
    )

    val astralShape = PsychicPower(
        "astralShape",
        R.string.astralShape,
        2,
        true,
        true,
        R.string.astralShapeDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "10 kilometer radius",
            "100 kilometer radius",
            "500 kilometer radius",
            "1000 kilometer radius",
            "5000 kilometer radius",
            "Any distance"
        )
    )

    val psychicTracking = PsychicPower(
        "psyTracking",
        R.string.psyTracking,
        2,
        true,
        true,
        R.string.psyTrackingDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "10 kilometer radius/140 PsR",
            "100 kilometer radius/160 PsR",
            "500 kilometer radius/180 PsR",
            "1000 kilometer radius/200 PsR",
            "5000 kilometer radius/220 PsR",
            "Any distance/ 260 PsR"
        )
    )

    val mindControl = PsychicPower(
        "Mind Control",
        R.string.mindControl,
        3,
        true,
        true,
        R.string.mindControlDesc,
        listOf(
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "220 PsR"
        )
    )

    val psychicDeath = PsychicPower(
        "psyDeath",
        R.string.psyDeath,
        3,
        true,
        false,
        R.string.psyDeathDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "220 PsR",
            "240 PsR"
        )
    )

    val area = PsychicPower(
        "area",
        R.string.area,
        3,
        true,
        true,
        R.string.areaDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 4",
            "10 meters",
            "100 meters",
            "1 kilometer",
            "10 kilometers",
            "100 kilometers",
            "500 kilometers"
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