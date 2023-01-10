package com.example.animabuilder.character_creation.attributes.psychic.disciplines

import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower

class Telemetry {
    val senseResidues = PsychicPower(
        "Sense Residues",
        1,
        true,
        true,
        "The psychic senses environmental residues of intense feelings emitted long " +
                "ago. These feelings need to have been strong ones - such as a great passion or " +
                "intense fear - in order for him to perceive them.",
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
        "Read The Past",
        2,
        true,
        false,
        "This Power enables the psychic to read the history of a specific object or place. " +
                "Absolutely all events that have taken place within the time span of the Power " +
                "can be read.",
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
        "Human Erudition",
        2,
        true,
        false,
        "This Power endows the psychic with the ability of analyzing an individual's " +
                "past in the attempt of discovering actions he may have taken. The psychic may " +
                "look for a specific action on the part of the target, or he may seek to find out " +
                "what his actions were in a specific opportunity. He could, for instance, find " +
                "out if a certain individual has committed murder, or learn exactly what the " +
                "character was doing one week from then. Resisting this Power requires the victim " +
                "to pass a PsR Check against the required target (as detailed in the Effects Table).",
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
        "See In History",
        3,
        true,
        true,
        "This Power allows psychics to project their senses into the past in a manner " +
                "such that they can witness any event that might have happened at the place where " +
                "they are executing the Power. The number of years a psychic can go back is " +
                "determined by his success in activating the Power (as detailed in the Effects Table).",
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

    val allPowers = listOf(senseResidues, readPast, humanErudition, seeHistory)
}