package com.example.animabuilder.character_creation.attributes.race_objects

import com.example.animabuilder.character_creation.attributes.advantages.AdvantageRecord
import java.io.Serializable

/**
 * Object class that holds values related to a character's race
 */

class CharRace(var heldRace: RaceName, advantageRecord: AdvantageRecord) : Serializable {
    var raceIndex = 0

    init {
        when (heldRace) {
            RaceName.sylvain -> {
                raceIndex = 1
            }
            RaceName.jayan -> {
                raceIndex = 2
            }
            RaceName.danjayni -> {
                raceIndex = 3
            }
            RaceName.ebudan -> {
                raceIndex = 4
            }
            RaceName.daimah -> {
                raceIndex = 5
            }
            RaceName.dukzarist -> {
                raceIndex = 6
            }
            else -> 0
        }
    }
}