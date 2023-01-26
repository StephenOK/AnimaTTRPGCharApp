package com.example.animabuilder.character_creation.attributes.advantages.advantage_items

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.advantages.Advantage
import com.example.animabuilder.character_creation.attributes.class_objects.Archetype
import com.example.animabuilder.character_creation.attributes.race_objects.RaceName
import java.io.Serializable

class AdvantageRecord(private val charInstance: BaseCharacter): Serializable {
    var creationPointSpent = 0
    val takenAdvantages = mutableListOf<Advantage>()

    val commonAdvantages = CommonAdvantages(charInstance)
    val magicAdvantages = MagicAdvantages(charInstance)
    val psychicAdvantages = PsychicAdvantages()

    fun acquireAdvantage(toAdd: Advantage, taken: Int?, takenCost: Int): Boolean{
        when(charInstance.ownRace.heldRace){
            RaceName.sylvain -> {
                when(toAdd){
                    commonAdvantages.sickly,
                    commonAdvantages.seriousIllness,
                    commonAdvantages.magicSusceptibility -> return false

                    magicAdvantages.elementalCompatibility -> if(taken == 1) return false
                }
            }
            RaceName.jayan -> {
                when(toAdd){
                    commonAdvantages.uncommonSize -> if(taken!! < 5) return false
                    commonAdvantages.deductCharacteristic -> if(taken == 0) return false
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
                    commonAdvantages.poisonSusceptibility -> return false

                    magicAdvantages.elementalCompatibility -> if(taken == 0) return false

                    commonAdvantages.psyDisciplineAccess ->{
                        if(taken != 2 && !charInstance.psychic.legalDisciplines.contains(charInstance.psychic.pyrokinesis))
                            return false
                    }
                }
            }
            else -> {}
        }

        when(toAdd){
            commonAdvantages.characteristicPoint -> {
                when(taken) {
                    0 -> if(charInstance.totalSTR + 1 > 11) return false
                    1 -> if(charInstance.totalDEX + 1 > 11) return false
                    2 -> if(charInstance.totalAGI + 1 > 11) return false
                    3 -> if(charInstance.totalCON + 1 > 11) return false
                    4 -> if(charInstance.totalINT + 1 > 13) return false
                    5 -> if(charInstance.totalPOW + 1 > 13) return false
                    6 -> if(charInstance.totalWP + 1 > 13) return false
                    7 -> if(charInstance.totalPER + 1 > 13) return false
                    else -> return false
                }
            }

            commonAdvantages.subjectAptitude -> {
                if(charInstance.secondaryList.intToCharacteristic(taken!!)!!.devPerPoint - toAdd.cost[takenCost] <= 0)
                    return false
            }

            commonAdvantages.psyDisciplineAccess -> {
                if(containsAny("Free Access to Any Psychic Discipline") != null)
                    return false
            }

            commonAdvantages.exclusiveWeapon -> {
                if(!charInstance.ownClass.archetype.contains(Archetype.Fighter) ||
                        !charInstance.ownClass.archetype.contains(Archetype.Domine) ||
                        !charInstance.ownClass.archetype.contains(Archetype.Prowler) ||
                        !charInstance.ownClass.archetype.contains(Archetype.Novel))
                    return false
            }

            commonAdvantages.unattractive ->
                if(charInstance.appearance < 7) return false

            magicAdvantages.magicBlockage ->
                if(takenAdvantages.contains(magicAdvantages.slowMagicRecovery)) return false
        }

        val copyAdvantage = Advantage(
            toAdd.name,
            toAdd.description,
            toAdd.effect,
            toAdd.restriction,
            toAdd.special,
            taken, toAdd.cost,
            takenCost, toAdd.onTake,
            toAdd.onRemove
        )

        if(creationPointSpent + copyAdvantage.cost[copyAdvantage.pickedCost] <= 3){
            takenAdvantages += copyAdvantage
            if(copyAdvantage.onTake != null)
                copyAdvantage.onTake!!(taken, copyAdvantage.cost[takenCost])
            refreshSpent()
            return true
        }

        return false
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