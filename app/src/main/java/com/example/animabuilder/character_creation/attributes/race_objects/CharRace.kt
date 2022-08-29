package com.example.animabuilder.character_creation.attributes.race_objects

import java.io.Serializable

/**
 * Object class that holds values related to a character's race
 */

class CharRace(var heldRace: RaceName) : Serializable {
    var raceIndex = 0

    init {
        raceIndex = when (heldRace) {
            RaceName.sylvain -> 1
            RaceName.jayan -> 2
            RaceName.danjayni -> 3
            RaceName.ebudan -> 4
            RaceName.daimah -> 5
            RaceName.dukzarist -> 6
            else -> 0
        }
    }
}