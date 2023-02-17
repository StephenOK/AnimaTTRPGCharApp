package com.example.animabuilder.character_creation.attributes.advantages

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.advantages.advantage_items.CommonAdvantages
import com.example.animabuilder.character_creation.attributes.advantages.advantage_items.MagicAdvantages
import com.example.animabuilder.character_creation.attributes.advantages.advantage_items.PsychicAdvantages
import com.example.animabuilder.character_creation.attributes.advantages.advantage_types.Advantage
import com.example.animabuilder.character_creation.attributes.class_objects.Archetype
import com.example.animabuilder.character_creation.attributes.race_objects.RaceName
import java.io.BufferedReader
import java.io.Serializable

/**
 * Section that holds data on the advantages and disadvantages chosen for the character
 * Holds information on how many creation points have been spent
 * Holds information on all base versions of a character's advantages and disadvantages
 * Adds and removes advantages from the character
 * Returns error messages to the caller for display
 */

class AdvantageRecord(private val charInstance: BaseCharacter): Serializable {
    //initialize spent creation points
    var creationPointSpent = 0

    //initialize list of advantages
    val takenAdvantages = mutableListOf<Advantage>()
    var raceAdvantages = listOf<Advantage>()

    //initialize instances of advantages and disadvantages
    val commonAdvantages = CommonAdvantages(charInstance)
    val magicAdvantages = MagicAdvantages(charInstance)
    val psychicAdvantages = PsychicAdvantages()

    /**
     * Method used to add an advantage or disadvantage to the character
     *
     * toAdd: base version of the advantage to add
     * taken: index of the chosen item if applicable
     * takenCost: index of the chosen cost of the advantage
     */
    fun acquireAdvantage(toAdd: Advantage, taken: Int?, takenCost: Int): String?{
        //halt addition of fourth disadvantage
        if(toAdd.cost[takenCost] < 0 && countDisadvantages() >= 3)
            return "Cannot take more Disadvantages"

        //implement racial restrictions on advantages
        when(charInstance.ownRace.heldRace){
            RaceName.sylvain -> {
                when(toAdd){
                    //forbid these advantages for sylvain
                    commonAdvantages.sickly,
                    commonAdvantages.seriousIllness,
                    commonAdvantages.magicSusceptibility -> return "Forbidden for Sylvain"

                    //forbid Dark element option for this advantage
                    magicAdvantages.elementalCompatibility -> if(taken == 1)
                        return "Cannot take Dark as a Sylvain"
                }
            }
            RaceName.jayan -> {
                when(toAdd){
                    //forbid size reduction in Jayan
                    commonAdvantages.uncommonSize -> if(taken!! < 5)
                        return "Cannot reduce size as Jayan"
                    //forbid Strength reduction for Jayan
                    commonAdvantages.deductCharacteristic -> if(taken == 0)
                        return "Cannot reduce Strength as Jayan"
                }
            }
            RaceName.dukzarist -> {
                when(toAdd){
                    //forbid these disadvantages for dukzarist
                    commonAdvantages.atrophiedLimb,
                    commonAdvantages.blind,
                    commonAdvantages.deafness,
                    commonAdvantages.mute,
                    commonAdvantages.nearsighted,
                    commonAdvantages.physicalWeakness,
                    commonAdvantages.seriousIllness,
                    commonAdvantages.sickly,
                    commonAdvantages.poisonSusceptibility -> return "Forbidden for Duk\'zarist"

                    //forbid Light element option for this advantage
                    magicAdvantages.elementalCompatibility -> if(taken == 0)
                        return "Cannot take Light as Duk\'zarist"

                    //Dukzarist must develop pyrokinesis first
                    commonAdvantages.psyDisciplineAccess ->{
                        if(taken != 2 && !charInstance.psychic.legalDisciplines.contains(charInstance.psychic.pyrokinesis))
                            return "Duk\'zarist must take Pyrokinesis first"
                    }
                }
            }
            else -> {}
        }

        //implement advantage restrictions
        when(toAdd){
            commonAdvantages.characteristicPoint -> {
                //apply amount cap to each stat
                when(taken) {
                    0 -> if(charInstance.primaryList.str.total + 1 > 11) return "Cannot increase Strength further"
                    1 -> if(charInstance.primaryList.dex.total + 1 > 11) return "Cannot increase Dexterity further"
                    2 -> if(charInstance.primaryList.agi.total + 1 > 11) return "Cannot increase Agility further"
                    3 -> if(charInstance.primaryList.con.total + 1 > 11) return "Cannot increase Constitution further"
                    4 -> if(charInstance.primaryList.int.total + 1 > 13) return "Cannot increase Intelligence further"
                    5 -> if(charInstance.primaryList.pow.total + 1 > 13) return "Cannot increase Power further"
                    6 -> if(charInstance.primaryList.wp.total + 1 > 13) return "Cannot increase Willpower further"
                    7 -> if(charInstance.primaryList.per.total + 1 > 13) return "Cannot increase Perception further"
                    else -> return "Invalid input"
                }
            }

            //forbid reduction of growth stat to below zero
            commonAdvantages.subjectAptitude -> {
                if(charInstance.secondaryList.fullList[taken!!].devPerPoint - toAdd.cost[takenCost] <= 0)
                    return "Cannot reduce characteristic growth below zero"
            }

            commonAdvantages.fieldAptitude -> {
                val prevGrowth =
                    when(taken){
                        0 -> charInstance.ownClass.athGrowth
                        1 -> charInstance.ownClass.creatGrowth
                        2 -> charInstance.ownClass.percGrowth
                        3 -> charInstance.ownClass.socGrowth
                        4 -> charInstance.ownClass.subterGrowth
                        5 -> charInstance.ownClass.intellGrowth
                        6 -> charInstance.ownClass.vigGrowth
                        else -> 0
                    }

                if(prevGrowth - toAdd.cost[takenCost] <= 0)
                    return "Cannot reduce field growth below zero"
            }

            //no need to acquire more disciplines if all are already taken
            commonAdvantages.psyDisciplineAccess -> {
                if(this.getAdvantage("Free Access to Any Psychic Discipline") != null)
                    return "Already have access to all Disciplines"
            }

            //prevent certain classes from taking this disadvantage
            commonAdvantages.exclusiveWeapon -> {
                if(!charInstance.ownClass.archetype.contains(Archetype.Fighter) ||
                        !charInstance.ownClass.archetype.contains(Archetype.Domine) ||
                        !charInstance.ownClass.archetype.contains(Archetype.Prowler) ||
                        !charInstance.ownClass.archetype.contains(Archetype.Novel))
                    return "Disadvantage forbidden to your class"
            }

            //check minimum appearance before taking this disadvantage
            commonAdvantages.unattractive ->
                if(charInstance.appearance < 7) return "Minimum appearance of 7 required"

            //prevent either of these disadvantages from being taken with the other one
            magicAdvantages.slowMagicRecovery ->
                if(this.getAdvantage("Magic Blockage") != null)
                    return "Cannot take this with Magic Blockage"
            magicAdvantages.magicBlockage ->
                if(this.getAdvantage("Slow Recovery of Magic") != null)
                    return "Cannot take this with Slow Recovery of Magic"
        }

        //check if able to take multiple times
        if(toAdd.special == null && this.getAdvantage(toAdd.name) != null)
            return "You cannot have multiple copies of this Advantage"
        else if(taken != null && getAdvantage("Natural Knowledge of a Path", taken, takenCost) != null)
            return "You cannot take the same Path multiple times"

        //create advantage to take
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

        //check that character has sufficient creation points to buy this advantage
        if(creationPointSpent + copyAdvantage.cost[copyAdvantage.pickedCost] <= 3){
            takenAdvantages += copyAdvantage

            //apply effect
            if(copyAdvantage.onTake != null)
                copyAdvantage.onTake!!(taken, copyAdvantage.cost[takenCost])

            refreshSpent()
            return null
        }

        return "Cannot buy this advantage"
    }

    /**
     * Removes an advantage from the character
     *
     * toRemove: item to be removed from the character
     */
    fun removeAdvantage(toRemove: Advantage){
        //search for copy of inputted advantage
        takenAdvantages.forEach{
            if(it.isEquivalent(toRemove)){
                //remove the found advantage
                takenAdvantages -= it

                //undo its effect
                if(it.onRemove != null)
                    it.onRemove!!(it.picked, it.cost[it.pickedCost])

                //halt process
                refreshSpent()
                return
            }
        }
    }

    /**
     * Recalculate the number of creation points spent by the character
     */
    fun refreshSpent(){
        //initialize spent value
        creationPointSpent = 0

        //add cost of each taken advantage
        takenAdvantages.forEach{
            creationPointSpent += it.cost[it.pickedCost]
        }
    }

    /**
     * Finds a taken advantage of the inputted name
     *
     * itemName: name of the advantage to find
     */
    fun getAdvantage(itemName: String): Advantage?{
        //search through all acquired advantages
        takenAdvantages.forEach{
            //return if match is found
            if(it.name == itemName) return it
        }

        //return null if no match
        return null
    }

    /**
     * Finds a taken advantage of the inputted name, type, and cost
     *
     * itemName: name of the advantage to find
     * itemTaken: type of the advantage to find
     * itemCost: cost of the item to find
     */
    fun getAdvantage(itemName: String, itemTaken: Int, itemCost: Int): Advantage?{
        //search through all acquired advantages
        takenAdvantages.forEach{
            //return if identical advantage found
            if(it.name == itemName && it.picked == itemTaken && it.pickedCost == itemCost) return it
        }

        //return null if no match
        return null
    }

    /**
     * Finds the base advantage of the inputted name
     *
     * toFind: name of the advantage to find
     */
    fun findAdvantage(toFind: String): Advantage?{
        //search through each base list to find a match
        commonAdvantages.advantages.forEach{
            //return if a common advantage matches
            if(it.name == toFind) return it
        }
        magicAdvantages.advantages.forEach{
            //return if a magic advantage matches
            if(it.name == toFind) return it
        }
        psychicAdvantages.advantages.forEach{
            //return if a psychic advantage matches
            if(it.name == toFind) return it
        }
        commonAdvantages.disadvantages.forEach{
            //return if a common disadvantage matches
            if(it.name == toFind) return it
        }
        magicAdvantages.disadvantages.forEach{
            //return if a magic disadvantage matches
            if(it.name == toFind) return it
        }
        psychicAdvantages.disadvantages.forEach{
            //return if a psychic disadvantage matches
            if(it.name == toFind) return it
        }

        //return null for no match found
        return null
    }

    /**
     * Determines the number of disadvantages the character has taken
     */
    fun countDisadvantages(): Int{
        //initialize counter
        var total = 0

        //search through all acquired advantages
        takenAdvantages.forEach{
            //increment if disadvantage found
            if(it.cost[it.pickedCost] < 0)
                total++
        }

        //return counter
        return total
    }


    /**
     * Retrieves advantage data from file
     *
     * fileReader: file reader to gather data from
     */
    fun loadAdvantages(fileReader: BufferedReader){
        //retrieve number of taken advantages
        for(index in 0 until fileReader.readLine().toInt()){
            //apply recorded advantage data
            acquireAdvantage(
                findAdvantage(fileReader.readLine())!!,
                fileReader.readLine().toIntOrNull(),
                fileReader.readLine().toInt()
            )
        }
    }

    /**
     * Write advantage data to file
     */
    fun writeAdvantages(){
        //record number of taken advantages
        charInstance.addNewData(takenAdvantages.size)

        //write advantage data
        takenAdvantages.forEach{
            charInstance.addNewData(it.name)
            charInstance.addNewData(it.picked)
            charInstance.addNewData(it.pickedCost)
        }
    }
}