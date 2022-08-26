package com.example.animabuilder.CharacterCreation.Attributes.Race

import com.example.animabuilder.CharacterCreation.Attributes.Race.RaceName
import java.io.Serializable

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