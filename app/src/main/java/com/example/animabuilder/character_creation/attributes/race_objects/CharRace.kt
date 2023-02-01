package com.example.animabuilder.character_creation.attributes.race_objects

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.advantages.Advantage
import com.example.animabuilder.character_creation.attributes.advantages.advantage_items.RaceAdvantages
import java.io.Serializable

/**
 * Object class that holds values related to a character's race
 */

class CharRace(var heldRace: RaceName, private val charInstance: BaseCharacter) : Serializable {
    var raceIndex = 0
    val advantageRecord = RaceAdvantages(charInstance)
    var raceAdvantages = listOf<Advantage>()

    init {
        when (heldRace) {
            RaceName.sylvain -> {
                raceIndex = 1
                raceAdvantages = advantageRecord.sylvainAdvantages
            }
            RaceName.jayan -> {
                raceIndex = 2
                raceAdvantages = advantageRecord.jayanAdvantages
            }
            RaceName.danjayni -> {
                raceIndex = 3
                raceAdvantages = advantageRecord.danjayniAdvantages
            }
            RaceName.ebudan -> {
                raceIndex = 4
                raceAdvantages = advantageRecord.ebudanAdvantages
            }
            RaceName.daimah -> {
                raceIndex = 5
                raceAdvantages = advantageRecord.daimahAdvantages
            }
            RaceName.dukzarist -> {
                raceIndex = 6
                raceAdvantages = advantageRecord.dukzaristAdvantages
            }
            else -> {raceIndex = 0; raceAdvantages = listOf()}
        }
    }
}