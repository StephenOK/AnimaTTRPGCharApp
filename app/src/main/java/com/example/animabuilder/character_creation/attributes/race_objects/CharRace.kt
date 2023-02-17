package com.example.animabuilder.character_creation.attributes.race_objects

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.advantages.advantage_items.RaceAdvantages
import java.io.Serializable

/**
 * Object class that holds values related to a character's race
 */

class CharRace(var heldRace: RaceName, charInstance: BaseCharacter) : Serializable {
    var raceIndex = 0
    val advantageRecord = RaceAdvantages(charInstance)

    init {
        when (heldRace) {
            RaceName.sylvain -> {
                raceIndex = 1
                charInstance.advantageRecord.raceAdvantages = advantageRecord.sylvainAdvantages
            }
            RaceName.jayan -> {
                raceIndex = 2
                charInstance.advantageRecord.raceAdvantages = advantageRecord.jayanAdvantages
            }
            RaceName.danjayni -> {
                raceIndex = 3
                charInstance.advantageRecord.raceAdvantages = advantageRecord.danjayniAdvantages
            }
            RaceName.ebudan -> {
                raceIndex = 4
                charInstance.advantageRecord.raceAdvantages = advantageRecord.ebudanAdvantages
            }
            RaceName.daimah -> {
                raceIndex = 5
                charInstance.advantageRecord.raceAdvantages = advantageRecord.daimahAdvantages
            }
            RaceName.dukzarist -> {
                raceIndex = 6
                charInstance.advantageRecord.raceAdvantages = advantageRecord.dukzaristAdvantages
            }
            else -> {raceIndex = 0; charInstance.advantageRecord.raceAdvantages = listOf()}
        }
    }
}