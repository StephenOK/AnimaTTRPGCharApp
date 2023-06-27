package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available psychokinetic powers.
 */
class Psychokinesis: Discipline("psychokinesis"){
    val minorKinesis = PsychicPower(
        "minorKinesis",
        R.string.minorKinesis,
        1,
        true,
        true,
        R.string.minorKinesisDesc,
        listOf(
            "Fatigue 1",
            "1 kg/Flight Value 4",
            "2 kg/Flight Value 6",
            "5 kg/Flight Value 8",
            "10 kg/Flight Value 10",
            "20 kg/Flight Value 12",
            "40 kg/Flight Value 14",
            "100 kg/Flight Value 16",
            "200 kg/Flight Value 18",
            "500 kg/Flight Value 20"
        )
    )

    val kineticImpact = PsychicPower(
        "kineticImpact",
        R.string.kineticImpact,
        1,
        true,
        false,
        R.string.kineticImpactDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "Strength 8",
            "Strength 10",
            "Strength 12",
            "Strength 14",
            "Strength 15",
            "Strength 16",
            "Strength 18",
            "Strength 20"
        )
    )

    val kineticTrap = PsychicPower(
        "kineticTrap",
        R.string.psyKinTrap,
        1,
        true,
        true,
        R.string.psyKinTrapDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "Strength 6",
            "Strength 8",
            "Strength 10",
            "Strength 12/5-meter radius",
            "Strength 14/10-meter radius",
            "Strength 15/50-meter radius",
            "Strength 16/100-meter radius",
            "Strength 18/500-meter radius"
        )
    )

    val kineticShield = PsychicPower(
        "kineticShield",
        R.string.kineticShield,
        1,
        false,
        true,
        R.string.kineticShieldDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "300 LP",
            "500 LP",
            "700 LP",
            "1000 LP",
            "1500 LP/Damage Barrier 60",
            "2000 LP/Damage Barrier 80/Stops Energy",
            "3000 LP/Damage Barrier 120/Stops Energy",
            "5000 LP/Damage Barrier 160/Stops Energy"
        )
    )

    val kineticArmor = PsychicPower(
        "kineticArmor",
        R.string.kineticArmor,
        1,
        false,
        true,
        R.string.kineticArmorDesc,
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "AT 1",
            "AT 2",
            "AT 4",
            "AT 6",
            "AT 8",
            "AT 10",
            "AT 12",
            "AT 14"
        )
    )

    val motionDetection = PsychicPower(
        "detectMotion",
        R.string.detectMotion,
        2,
        true,
        true,
        R.string.detectMotionDesc,
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "120 PhR/10-meter radius",
            "160 PhR/50-meter radius",
            "200 PhR/100-meter radius",
            "240 PhR/500-meter radius",
            "280 PhR/1-kilometer radius",
            "320 PhR/10-kilometer radius",
            "400 PhR/100-kilometer radius"
        )
    )

    val repulsion = PsychicPower(
        "repulsion",
        R.string.repulsion,
        2,
        true,
        true,
        R.string.repulsionDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "Strength 6/2-meter line",
            "Strength 8/5-meter line",
            "Strength 10/10-meter line",
            "Strength 12/20-meter line",
            "Strength 14/50-meter line",
            "Strength 18/100-meter line"
        )
    )

    val ballistics = PsychicPower(
        "ballistics",
        R.string.ballistics,
        2,
        true,
        false,
        R.string.ballisticsDesc,
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "+0 Projection/5 meters",
            "+10 Projection/10 meters",
            "+20 Projection/15 meters",
            "+30 Projection/25 meters",
            "+40 Projection/40 meters",
            "+50 Projection/80 meters",
            "+60 Projection/150 meters"
        )
    )

    val shatter = PsychicPower(
        "shatter",
        R.string.shatter,
        2,
        true,
        false,
        R.string.shatterDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "100 PhR",
            "120 PhR",
            "140 PhR",
            "160 PhR",
            "180 PhR",
            "220 PhR"
        )
    )

    val kineticFlight = PsychicPower(
        "kineticFlight",
        R.string.kineticFlight,
        2,
        true,
        true,
        R.string.kineticFlightDesc,
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "Flight Value 6",
            "Flight Value 8",
            "Flight Value 10",
            "Flight Value 12",
            "Flight Value 14",
            "Flight Value 16",
            "Flight Value 18"
        )
    )

    val organicKinesis = PsychicPower(
        "organicKinesis",
        R.string.organicKinesis,
        2,
        true,
        true,
        R.string.organicKinesisDesc,
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "100 kg/Flight Value 4/100 PhR",
            "250 kg/Flight Value 6/120 PhR",
            "500 kg/Flight Value 8/140 PhR",
            "1000 kg/Flight Value 10/160 PhR",
            "2500 kg/Flight Value 12/180 PhR",
            "5000 kg/Flight Value 14/200 PhR",
            "10000 kg/Flight Value 16/220 PhR"
        )
    )

    val groundControl = PsychicPower(
        "groundControl",
        R.string.groundControl,
        3,
        true,
        false,
        R.string.groundControlDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "10-meter radius/Damage Barrier 40",
            "100-meter radius/Damage Barrier 60",
            "250-meter radius/Damage Barrier 80",
            "500-meter radius/Damage Barrier 100",
            "1-kilometer radius/Damage Barrier 140"
        )
    )

    val atomicRestructuring = PsychicPower(
        "atomRestructure",
        R.string.atomRestructure,
        3,
        true,
        false,
        R.string.atomRestructureDesc,
        listOf(
            "Fatigue 24",
            "Fatigue 20",
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "140 PhR/100 kg",
            "160 PhR/10 tons",
            "200 PhR/100 tons"
        )
    )

    val majorKinesis = PsychicPower(
        "majorKinesis",
        R.string.majorKinesis,
        3,
        true,
        true,
        R.string.majorKinesisDesc,
        listOf(
            "Fatigue 24",
            "Fatigue 20",
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 4",
            "500 tons/Flight Value 4",
            "10000 tons/Flight Value 6",
            "100000 tons/Flight Value 8",
            "1000000 tons/Flight Value 10"
        )
    )

    init {
        allPowers.addAll(listOf(
            minorKinesis,
            kineticImpact,
            kineticTrap,
            kineticShield,
            kineticArmor,
            motionDetection,
            repulsion,
            ballistics,
            shatter,
            kineticFlight,
            organicKinesis,
            groundControl,
            atomicRestructuring,
            majorKinesis
        ))
    }
}