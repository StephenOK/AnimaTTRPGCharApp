package com.example.animabuilder.character_creation.attributes.advantages

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.advantages.advantage_items.CommonAdvantages
import com.example.animabuilder.character_creation.attributes.advantages.advantage_items.MagicAdvantages
import com.example.animabuilder.character_creation.attributes.advantages.advantage_items.PsychicAdvantages
import com.example.animabuilder.character_creation.attributes.advantages.advantage_types.Advantage
import com.example.animabuilder.character_creation.attributes.class_objects.Archetype
import com.example.animabuilder.character_creation.attributes.race_objects.RaceName
import java.io.Serializable

class AdvantageRecord(private val charInstance: BaseCharacter): Serializable {
    var creationPointSpent = 0
    val takenAdvantages = mutableListOf<Advantage>()

    val commonAdvantages = CommonAdvantages(charInstance)
    val magicAdvantages = MagicAdvantages(charInstance)
    val psychicAdvantages = PsychicAdvantages()

    fun acquireAdvantage(toAdd: Advantage, taken: Int?, takenCost: Int): String?{
        when(charInstance.ownRace.heldRace){
            RaceName.sylvain -> {
                when(toAdd){
                    commonAdvantages.sickly,
                    commonAdvantages.seriousIllness,
                    commonAdvantages.magicSusceptibility -> return "Forbidden for Sylvain"

                    magicAdvantages.elementalCompatibility -> if(taken == 1)
                        return "Cannot take Dark as a Sylvain"
                }
            }
            RaceName.jayan -> {
                when(toAdd){
                    commonAdvantages.uncommonSize -> if(taken!! < 5)
                        return "Cannot reduce size as Jayan"
                    commonAdvantages.deductCharacteristic -> if(taken == 0)
                        return "Cannot reduce Strength as Jayan"
                }
            }
            RaceName.dukzarist -> {
                when(toAdd){
                    commonAdvantages.atrophiedLimb,
                    commonAdvantages.blind,
                    commonAdvantages.deafness,
                    commonAdvantages.mute,
                    commonAdvantages.nearsighted,
                    commonAdvantages.physicalWeakness,
                    commonAdvantages.seriousIllness,
                    commonAdvantages.sickly,
                    commonAdvantages.poisonSusceptibility -> return "Forbidden for Duk\'zarist"

                    magicAdvantages.elementalCompatibility -> if(taken == 0)
                        return "Cannot take Light as Duk\'zarist"

                    commonAdvantages.psyDisciplineAccess ->{
                        if(taken != 2 && !charInstance.psychic.legalDisciplines.contains(charInstance.psychic.pyrokinesis))
                            return "Duk\'zarist must take Pyrokinesis first"
                    }
                }
            }
            else -> {}
        }

        when(toAdd){
            commonAdvantages.characteristicPoint -> {
                when(taken) {
                    0 -> if(charInstance.totalSTR + 1 > 11) return "Cannot increase Strength further"
                    1 -> if(charInstance.totalDEX + 1 > 11) return "Cannot increase Dexterity further"
                    2 -> if(charInstance.totalAGI + 1 > 11) return "Cannot increase Agility further"
                    3 -> if(charInstance.totalCON + 1 > 11) return "Cannot increase Constitution further"
                    4 -> if(charInstance.totalINT + 1 > 13) return "Cannot increase Intelligence further"
                    5 -> if(charInstance.totalPOW + 1 > 13) return "Cannot increase Power further"
                    6 -> if(charInstance.totalWP + 1 > 13) return "Cannot increase Willpower further"
                    7 -> if(charInstance.totalPER + 1 > 13) return "Cannot increase Perception further"
                    else -> return "Invalid input"
                }
            }

            commonAdvantages.subjectAptitude -> {
                if(charInstance.secondaryList.intToCharacteristic(taken!!)!!.devPerPoint - toAdd.cost[takenCost] <= 0)
                    return "Cannot reduce below zero"
            }

            commonAdvantages.psyDisciplineAccess -> {
                if(containsAny("Free Access to Any Psychic Discipline") != null)
                    return "Already have access to all Disciplines"
            }

            commonAdvantages.exclusiveWeapon -> {
                if(!charInstance.ownClass.archetype.contains(Archetype.Fighter) ||
                        !charInstance.ownClass.archetype.contains(Archetype.Domine) ||
                        !charInstance.ownClass.archetype.contains(Archetype.Prowler) ||
                        !charInstance.ownClass.archetype.contains(Archetype.Novel))
                    return "Disadvantage forbidden to your class"
            }

            commonAdvantages.unattractive ->
                if(charInstance.appearance < 7) return "Minimum appearance of 7 required"

            magicAdvantages.slowMagicRecovery ->
                if(containsAny("Magic Blockage") != null)
                    return "Cannot take this with Magic Blockage"

            magicAdvantages.magicBlockage ->
                if(containsAny("Slow Recovery of Magic") != null)
                    return "Cannot take this with Slow Recovery of Magic"
        }

        if(toAdd.special == null && containsAny(toAdd.name) != null)
            return "You cannot have multiple copies of this Advantage"
        else if(contains("Natural Knowledge of a Path", taken, takenCost))
            return "You cannot take the same Path multiple times"

        val copyAdvantage = Advantage(
            toAdd.name,
            toAdd.description,
            toAdd.effect,
            toAdd.restriction,
            toAdd.special,
            toAdd.options,
            taken,
            toAdd.cost,
            takenCost,
            toAdd.onTake,
            toAdd.onRemove
        )

        if(creationPointSpent + copyAdvantage.cost[copyAdvantage.pickedCost] <= 3){
            takenAdvantages += copyAdvantage
            if(copyAdvantage.onTake != null)
                copyAdvantage.onTake!!(taken, copyAdvantage.cost[takenCost])
            refreshSpent()
            return null
        }

        return "Cannot buy this advantage"
    }

    fun removeAdvantage(toRemove: Advantage){
        takenAdvantages.forEach{
            if(it.isEquivalent(toRemove)){
                takenAdvantages -= it

                if(it.onRemove != null)
                    it.onRemove!!(it.picked, it.cost[it.pickedCost])

                refreshSpent()
                return
            }
        }
    }

    fun refreshSpent(){
        creationPointSpent = 0

        takenAdvantages.forEach{
            creationPointSpent += it.cost[it.pickedCost]
        }
    }

    fun getAdvantage(itemName: String): Advantage?{
        takenAdvantages.forEach{
            if(it.name == itemName) return it
        }

        return null
    }

    fun getAdvantage(itemName: String, itemTaken: Int, itemCost: Int): Advantage?{
        takenAdvantages.forEach{
            if(it.name == itemName && it.picked == itemTaken && it.pickedCost == itemCost) return it
        }

        return null
    }

    fun contains(findName: String, findTaken: Int?, findCost: Int): Boolean{
        takenAdvantages.forEach{
            if(it.name == findName && it.picked == findTaken && it.pickedCost == findCost)
                return true
        }

        return false
    }

    fun containsAny(findName: String): Advantage?{
        takenAdvantages.forEach{
            if(it.name == findName)
                return it
        }

        return null
    }

    fun containsEquivalent(toFind: Advantage): Boolean{
        takenAdvantages.forEach{
            if(it.name == toFind.name && it.picked == toFind.picked && it.pickedCost == toFind.pickedCost)
                return true
        }

        return false
    }
}