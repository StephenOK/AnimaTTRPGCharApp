package com.example.animabuilder.character_creation.attributes.psychic.disciplines

import com.example.animabuilder.character_creation.attributes.psychic.Discipline
import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available energy powers.
 */
class Energy: Discipline(){
    val objectCreation = PsychicPower(
        "Energy Object Creation",
        1,
        true,
        true,
        "This Power creates one simple material object, such as a sword, shaping it out " +
                "of sheer energy. The material is Energy-based and has Resistance 25. If a " +
                "character creates a weapon, it has a Base Damage between 80 and 120 (depending " +
                "on size), and a natural speed of 10. Since it is pure energy, it does not use " +
                "the character's Strength bonus, but it attacks on the Electricity AT.",
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "1 cubic meter",
            "2 cubic meters",
            "3 cubic meters",
            "4 cubic meters",
            "5 cubic meters",
            "10 cubic meters",
            "20 cubic meters"
        )
    )

    val energyDischarge = PsychicPower(
        "Energy Discharge",
        1,
        true,
        false,
        "Energy Discharge allows characters to use their Psychic Projection for " +
                "performing attacks. This Power uses the Electricity Attack Table with a Base " +
                "Damage that varies according to the character's success at activating it (as " +
                "detailed on the Effects Table below). If a character succeeds at an Impossible " +
                "or higher level activation, Energy Discharge can even damage immaterial beings. " +
                "This attack is perfectly visible - even to those who cannot see Psychic Matrices.",
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "Damage 50",
            "Damage 70",
            "Damage 100",
            "Damage 120",
            "Damage 140/It affects immaterial beings",
            "Damage 180/It affects immaterial beings",
            "Damage 220/It affects immaterial beings"
        )
    )

    val createEnergy = PsychicPower(
        "Create Energy",
        1,
        true,
        true,
        "This Power creates Energy Intensities or arouses an existing source to equal " +
                "proportions. Any type of energy may be created, from bonfires to lightning, " +
                "provided that they do not have a Supernatural origin.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "1 Intensity",
            "3 Intensities",
            "5 Intensities",
            "7 Intensities",
            "10 Intensities",
            "13 Intensities",
            "16 Intensities",
            "20 Intensities"
        )
    )

    val energyShield = PsychicPower(
        "Energy Shield",
        1,
        false,
        true,
        "This Power creates a shield of energy that protects the psychic from all sorts " +
                "of attacks - including those of a Supernatural origin. Unlike other Powers, " +
                "Energy Shield works on the same Life Points with which it was created instead of " +
                "using the psychic's natural maintenance. However, once created, it loses 5 " +
                "Life Points per turn until it reaches an amount that the psychic can maintain " +
                "naturally.",
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "300 Life Points",
            "500 Life Points",
            "800 Life Points",
            "1000 Life Points",
            "1400 Life Points",
            "2000 Life Points",
            "3000 Life Points"
        )
    )

    val senseEnergy = PsychicPower(
        "Sense Energy",
        1,
        true,
        true,
        "The character can detect the energy around him including the Intensity and " +
                "nature of the source - unless it is hidden in some way. This ability does not " +
                "call for Psychic Projection, it will automatically affect all parties within its " +
                "area of action.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "10-meter radius",
            "50-meter radius",
            "100-meter radius",
            "250-meter radius",
            "500-meter radius",
            "1-kilometer radius",
            "10-kilometer radius",
            "100-kilometer radius"
        )
    )

    val modifyNature = PsychicPower(
        "Modify Nature",
        2,
        true,
        false,
        "This Power allows a psychic to transfer several Intensities of a particular " +
                "Energy Type to another type of Energy. For example, a character using Modify " +
                "Nature might turn Fire into Electricity or Ice. A living being, or anything with " +
                "a Presence of its own, can avoid the effects of this Power by making a successful " +
                "PhR Check (as detailed in the Effects Table).",
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "6 Intensities/100 PhR",
            "8 Intensities/120 PhR",
            "12 Intensities/140 PhR",
            "16 Intensities/160 PhR",
            "20 Intensities/180 PhR",
            "25 Intensities/220 PhR"
        )
    )

    val undoEnergy = PsychicPower(
        "Undo Energy",
        2,
        true,
        false,
        "This Power decreases the Intensity level of Energy - except for those of a " +
                "Supernatural origin. When used against an Energy-based being of any kind, it " +
                "will cause 5 points of damage for every Intensity level decreased - unless the " +
                "creature succeeds at a PhR Check with a target difficulty based upon the " +
                "psychic's success at activating Undo Energy (as detailed on the Effects Table " +
                "below). Note that creatures with Damage Resistance will suffer 25 points of " +
                "damage for every Intensity level decrease.",
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "-1 Intensity/100 PhR",
            "-3 Intensities/120 PhR",
            "-5 Intensities/140 PhR",
            "-8 Intensities/160 PhR",
            "-12 Intensities/180 PhR",
            "-18 Intensities/200 PhR",
            "-24 Intensities/240 PhR"
        )
    )

    val immunity = PsychicPower(
        "Immunity",
        2,
        false,
        true,
        "The psychic, or whoever he designates, becomes immune to several Intensities of " +
                "a specific Energy Type. This is a single-type Immunity, which means that a " +
                "character choosing immunity to Electricity will still be affected by Cold and " +
                "Heat. Characters can reduce the Base Damage of an attack by 5 points for every " +
                "Intensity level of their immunity. They also receive +5 to their Resistance " +
                "Checks against effects from these attacks for every Intensity level of their immunity.",
        listOf(
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "10 Intensities",
            "15 Intensities",
            "20 Intensities",
            "30 Intensities",
            "40 Intensities"
        )
    )

    val controlEnergy = PsychicPower(
        "Control Energy",
        2,
        true,
        true,
        "This Power allows the psychic full control of several Cold, Heat, or Electricity " +
                "Intensities. A character is free to manipulate and manage these Intensities as he " +
                "wants - although his Psychic Projection will be reduced by half its value if he " +
                "employs them offensively. A live being, or anything with a Presence of its own, " +
                "can avoid the effects of this Power by making a successful PhR Check (as detailed " +
                "in the Effects Table).",
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "4 Intensities/80 PhR",
            "6 Intensities/100 PhR",
            "8 Intensities/120 PhR",
            "12 Intensities/140 PhR",
            "16 Intensities/160 PhR",
            "20 Intensities/180 PhR",
            "25 Intensities/220 PhR"
        )
    )

    val energyDome = PsychicPower(
        "Energy Dome",
        3,
        true,
        false,
        "The psychic is able to generate an energy dome that will destroy anything that " +
                "comes its way. The attack will affect a wide area within which no targets can " +
                "be selected. The attack uses the Electricity AT; its damage varies depending on " +
                "the psychic's success in activating the Power (as detailed on the Effects Table). " +
                "Note that the Energy Dome can become so pure that it damages immaterial beings. " +
                "This attack is perfectly visible - even to those who cannot see Psychic Matrices.",
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Base Damage 100/25-meter radius",
            "Base Damage 120/50-meter radius",
            "Base Damage 140/100-meter radius",
            "Base Damage 160/200-meter radius/It affects immaterial beings",
            "Base Damage 200/500-meter radius/It affects immaterial beings"
        )
    )

    val majorEnergy = PsychicPower(
        "Major Energy",
        3,
        true,
        true,
        "An amplified version of Create Energy, Major Energy is capable of provoking " +
                "much more devastating effects.",
        listOf(
            "Fatigue 20",
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "25 Intensities",
            "35 Intensities",
            "45 Intensities",
            "55 Intensities"
        )
    )

    override var allPowers = listOf(objectCreation, energyDischarge, createEnergy, energyShield,
        senseEnergy, modifyNature, undoEnergy, immunity, controlEnergy, energyDome, majorEnergy)
}