package com.paetus.animaCharCreator.character_creation.attributes.psychic

import com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines.Cryokinesis
import com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines.Energy
import com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines.MatrixPowers
import com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines.PhysicalIncrease
import com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines.Psychokinesis
import com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines.Pyrokinesis
import com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines.Sentience
import com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines.Telemetry
import com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines.Telepathy

/**
 * Collection of all psychic abilities available to the character.
 */
class PsyLibrary {
    private val telepathy = Telepathy()
    private val psychokinesis = Psychokinesis()
    private val pyrokinesis = Pyrokinesis()
    private val cryokinesis = Cryokinesis()
    private val physicalIncrease = PhysicalIncrease()
    private val energyPowers = Energy()
    private val sentiencePowers = Sentience()
    private val telemetry = Telemetry()
    private val matrixPowers = MatrixPowers()

    val allDisciplines = listOf(
        telepathy,
        psychokinesis,
        pyrokinesis,
        cryokinesis,
        physicalIncrease,
        energyPowers,
        sentiencePowers,
        telemetry,
        matrixPowers
    )
}