package com.example.animabuilder.character_creation.attributes.psychic

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.psychic.disciplines.*
import com.example.animabuilder.character_creation.attributes.race_objects.RaceName
import java.io.BufferedReader
import java.io.Serializable

/**
 * Fragment that holds the data on the character's psychic abilities
 * Holds and alters the data for psychic potential, points, and projection
 * Holds data for the psychic disciplines and their powers
 */

class Psychic(private val charInstance: BaseCharacter): Serializable {
    //initialize value for Psychic Potential
    var psyPotentialBase = 0

    //initialize values for Psychic Points
    var boughtPsyPoints = 0
    var innatePsyPoints = 0
    var totalPsychicPoints = 0
    var spentPsychicPoints = 0

    //initialize values for Psychic Projection
    var psyProjectionBought = 0
    var psyProjectionTotal = 0

    //initialize psychic power items
    val telepathy = Telepathy()
    val psychokinesis = Psychokinesis()
    val pyrokinesis = Pyrokinesis()
    val cryokinesis = Cryokinesis()
    val physicalIncrease = PhysicalIncrease()
    val energyPowers = Energy()
    val sentiencePowers = Sentience()
    val telemetry = Telemetry()
    val matrixPowers = MatrixPowers()

    //initialize discipline and power lists
    val legalDisciplines = mutableListOf<Discipline>()
    val disciplineInvestment = mutableListOf<Discipline>()
    val masteredPowers = mutableListOf<PsychicPower>()

    //collect all disciplines into a list
    val allDisciplines = listOf<Discipline>(telepathy, psychokinesis, pyrokinesis, cryokinesis, physicalIncrease,
        energyPowers, sentiencePowers, telemetry, matrixPowers)

    /**
     * Determines the value for the character's Psychic Potential
     */
    fun setBasePotential(){
        //determine value exclusively from the character's willpower
        psyPotentialBase = when(charInstance.totalWP){
            in 0..4 -> 0
            in 5 .. 14 -> 10 * (charInstance.totalWP -4)
            else -> 100 + ((charInstance.totalWP - 14) * 20)
        }
    }

    /**
     * Set the amount of bought Psychic Points for the character
     *
     * amount: Number of points the user is buying for their character
     */
    fun buyPsyPoints(amount: Int){
        //set bought amount and update psychic point total
        boughtPsyPoints = amount

        if(amount > 0 && charInstance.ownRace.heldRace == RaceName.dukzarist && !disciplineInvestment.contains(pyrokinesis))
            updateInvestment(pyrokinesis, true)

        updatePsyPointTotal()
    }

    /**
     * Set the amount of base Psychic Points for the character
     */
    fun setInnatePsy(){
        innatePsyPoints =
            //set points to 0 if at level 0
            if (charInstance.lvl == 0) 0
            //start character at 1 point and add more depending on additional levels
            else 1 + (charInstance.lvl - 1)/charInstance.ownClass.psyPerTurn

        if(innatePsyPoints > 0 && charInstance.ownRace.heldRace == RaceName.dukzarist && !disciplineInvestment.contains(pyrokinesis))
            updateInvestment(pyrokinesis, true)

        //update total
        updatePsyPointTotal()
    }

    /**
     * Recalculates the total amount of Psychic Points the character can utilize
     */
    fun updatePsyPointTotal(){
        totalPsychicPoints = boughtPsyPoints + innatePsyPoints
    }

    /**
     * Set the amount of bought Psychic Projection for the character
     *
     * amount: Number of Psychic Projection points to buy for the character
     */
    fun buyPsyProjection(amount: Int){
        //set bought amount and update Psychic Projection
        psyProjectionBought = amount
        updatePsyProjection()
    }

    /**
     * Recalculates the total amount of Psychic Projection points the character can utilize
     */
    fun updatePsyProjection(){
        psyProjectionTotal = psyProjectionBought + charInstance.modDEX
    }

    /**
     * Determine the character's currently available Psychic Points
     */
    fun getFreePsyPoints(): Int{
        return totalPsychicPoints - spentPsychicPoints
    }

    /**
     * Attempt to add or remove a Psychic Discipline from the character's taken list
     *
     * item: Discipline that is a target of the action
     * into: True if adding the item; false if removing
     */
    fun updateInvestment(item: Discipline, into: Boolean): Boolean{
        //if attempting to acquire and points are available to
        if(into && getFreePsyPoints() > 0 && legalDisciplines.contains(item)) {
            //add item and spend points
            disciplineInvestment.add(item)
            spentPsychicPoints += 1

            //notify of successful acquisition
            return true
        }

        //if attempting to remove the discipline
        else if (!into) {
            if(charInstance.ownRace.heldRace != RaceName.dukzarist || item != pyrokinesis) {
                //remove the discipline
                disciplineInvestment.remove(item)
                spentPsychicPoints -= 1

                //remove any associated powers
                removeIllegal(item)
            }
        }

        //notify of either successful removal or failed addition
        return false
    }

    /**
     * Attempt to add or remove the inputted Psychic Power to the character
     *
     * item: Psychic Power to add or remove from the list
     * discipline: associated discipline of the Psychic Power
     * into: whether the power is being added or removed
     */
    fun masterPower(item: PsychicPower, discipline: Discipline, into: Boolean): Boolean{
        //if attempting to add, there is a psychic point to buy it, and if the character can buy it
        if(into && getFreePsyPoints() > 0 && legalBuy(item, discipline)){
            //add power to the character and spend the point
            masteredPowers.add(item)
            spentPsychicPoints += 1

            //notify of successful purchase
            return true
        }

        //if attempting to remove and the power is present in the list
        else if(!into && masteredPowers.contains(item)){
            //remove the item from the character
            masteredPowers.remove(item)

            //refund the point
            spentPsychicPoints -= 1

            //remove any other Psychic Powers as needed
            removeIllegal(discipline)
        }

        //notify of either successful removal or failed addition
        return false
    }

    /**
     * Check if the given Psychic Power can be bought by this character
     *
     * item: Psychic Power to check the validity of
     * discipline: Power's associated Psychic Discipline
     */
    fun legalBuy(item: PsychicPower, discipline: Discipline): Boolean{
        if(disciplineInvestment.contains(discipline)) {
            //add no matter what if it is level 1 or 0
            if (item.level <= 1)
                return true

            //if power is level 2
            else if (item.level == 2) {
                //for each taken power
                masteredPowers.forEach {
                    //determine if it is a level 1 power of the same discipline
                    if (it.level == 1 && discipline.allPowers.contains(it))
                        return true
                }
            }

            //if power is level 3
            else if (item.level == 3) {
                //for each taken power
                masteredPowers.forEach {
                    //determine if it is a level 2 power of the same discipline
                    if (it.level == 2 && discipline.allPowers.contains(it))
                        return true
                }
            }
        }

        //notify of illegal buy
        return false
    }

    fun removeLegalDisciplineFromInt(input: Int){
        val discipline = intToDiscipline(input)

        removeLegalDiscipline(discipline)
    }

    fun removeLegalDiscipline(input: Discipline){
        legalDisciplines.remove(input)
        if(disciplineInvestment.contains(input))
            updateInvestment(input, false)
    }

    /**
     * Removes any taken Psychic Powers that are no longer legally bought
     *
     * discipline: Psychic Discipline to check for legality of
     */
    fun removeIllegal(discipline: Discipline){
        //for each taken power
        masteredPowers.forEach{
            //if presence is not legal
            if(!legalBuy(it, discipline)){
                //remove power and refund point
                masteredPowers.remove(it)
                spentPsychicPoints -= 1
            }
        }
    }

    /**
     * Determines the number of development points spent in this section
     */
    fun calculateSpent(): Int{
        return (boughtPsyPoints * charInstance.ownClass.psyPointGrowth) +
                (psyProjectionBought * charInstance.ownClass.psyProjGrowth)
    }

    fun loadPsychic(fileReader: BufferedReader){
        buyPsyPoints(fileReader.readLine().toInt())
        buyPsyProjection(fileReader.readLine().toInt())

        for(index in 0 until fileReader.readLine().toInt()){
            updateInvestment(intToDiscipline(fileReader.readLine().toInt()), true)
        }

        for(index in 0 until fileReader.readLine().toInt()){
            val powerDiscipline = intToDiscipline(fileReader.readLine().toInt())
            val power = powerDiscipline.allPowers[fileReader.readLine().toInt()]

            masterPower(power, powerDiscipline, true)
        }
    }

    fun writePsychic(){
        charInstance.addNewData(boughtPsyPoints)
        charInstance.addNewData(psyProjectionBought)

        charInstance.addNewData(disciplineInvestment.size)
        disciplineInvestment.forEach{
            charInstance.addNewData(disciplineToInt(it))
        }

        charInstance.addNewData(masteredPowers.size)
        masteredPowers.forEach{
            val powerDiscipline = getPowerDiscipline(it)!!
            charInstance.addNewData(disciplineToInt(powerDiscipline))
            charInstance.addNewData(powerDiscipline.allPowers.indexOf(it))
        }
    }

    fun intToDiscipline(value: Int): Discipline{
        return when(value){
            0 -> telepathy
            1 -> psychokinesis
            2 -> pyrokinesis
            3 -> cryokinesis
            4 -> physicalIncrease
            5 -> energyPowers
            6 -> sentiencePowers
            7 -> telemetry
            else -> matrixPowers
        }
    }

    fun disciplineToInt(item: Discipline): Int{
        return when(item){
            telepathy -> 0
            psychokinesis -> 1
            pyrokinesis -> 2
            cryokinesis -> 3
            physicalIncrease -> 4
            energyPowers -> 5
            sentiencePowers -> 6
            telemetry ->  7
            else -> 8
        }
    }

    fun getPowerDiscipline(search: PsychicPower): Discipline?{
        allDisciplines.forEach{
            if(it.allPowers.contains(search))
                return it
        }

        return null
    }
}