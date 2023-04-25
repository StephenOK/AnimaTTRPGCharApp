package com.example.animabuilder.character_creation.attributes.psychic

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.psychic.disciplines.*
import java.io.BufferedReader

/**
 * Fragment that holds the data on the character's psychic abilities.
 * Holds and alters the data for psychic potential, points, and projection.
 * Holds data for the psychic disciplines and their powers.
 *
 * @param charInstance object that holds all of a character's data
 */
class Psychic(private val charInstance: BaseCharacter){
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
     * Determines the value for the character's Psychic Potential.
     */
    fun setBasePotential(){
        //determine value exclusively from the character's willpower
        psyPotentialBase = when(charInstance.primaryList.wp.total){
            in 0..4 -> 0
            in 5 .. 14 -> 10 * (charInstance.primaryList.wp.total -4)
            else -> 100 + ((charInstance.primaryList.wp.total - 14) * 20)
        }
    }

    /**
     * Set the amount of bought Psychic Points for the character.
     *
     * @param amount number of points the user is buying for their character
     */
    fun buyPsyPoints(amount: Int){
        //set bought amount and update psychic point total
        boughtPsyPoints = amount
        charInstance.updateTotalSpent()
        updatePsyPointTotal()
    }

    /**
     * Set the amount of base Psychic Points for the character.
     */
    fun setInnatePsy(){
        innatePsyPoints =
            //set points to 0 if at level 0
            if (charInstance.lvl == 0) 0
            //start character at 1 point and add more depending on additional levels
            else 1 + (charInstance.lvl - 1)/charInstance.ownClass.psyPerTurn

        if(innatePsyPoints > 0 && charInstance.ownRace == charInstance.races.dukzaristAdvantages && !disciplineInvestment.contains(pyrokinesis))
            updateInvestment(pyrokinesis, true)

        //update total
        updatePsyPointTotal()
    }

    /**
     * Recalculates the total amount of Psychic Points the character can utilize.
     */
    fun updatePsyPointTotal(){
        totalPsychicPoints = boughtPsyPoints + innatePsyPoints
    }

    /**
     * Set the amount of bought Psychic Projection for the character.
     *
     * @param amount number of Psychic Projection points to buy for the character
     */
    fun buyPsyProjection(amount: Int){
        //set bought amount and update Psychic Projection
        psyProjectionBought = amount
        charInstance.updateTotalSpent()
        updatePsyProjection()
    }

    /**
     * Recalculates the total amount of Psychic Projection points the character can utilize.
     */
    fun updatePsyProjection(){
        psyProjectionTotal = psyProjectionBought + charInstance.primaryList.dex.outputMod
    }

    /**
     * Check that the bought psychic projection points are a valid input.
     *
     * @return true if valid
     */
    fun getValidProjection(): Boolean{
        return psyProjectionBought * charInstance.ownClass.psyProjGrowth <= charInstance.maxPsyDP/2
    }

    /**
     * Determine the character's currently available Psychic Points.
     *
     * @return number of psychic points available to the character
     */
    fun getFreePsyPoints(): Int{
        return totalPsychicPoints - spentPsychicPoints
    }

    /**
     * Attempt to add or remove a Psychic Discipline from the character's taken list.
     *
     * @param item discipline that is a target of the action
     * @param into true if adding the item; false if removing
     * @return true if discipline has been successfully added
     */
    fun updateInvestment(item: Discipline, into: Boolean): Boolean{
        //if attempting to acquire and points are available to
        if(into && dukzaristAvailable(item) && getFreePsyPoints() > 0 && legalDisciplines.contains(item)) {

            //add item and spend points
            disciplineInvestment.add(item)
            recalcPsyPointsSpent()

            //notify of successful acquisition
            return true
        }

        //if attempting to remove the discipline
        else if (!into) {
            if(charInstance.ownRace != charInstance.races.dukzaristAdvantages || item != pyrokinesis) {
                //remove the discipline
                disciplineInvestment.remove(item)

                //remove any associated powers
                removeIllegal(item)
            }
            else
                while(disciplineInvestment.size > 0){
                    val current = disciplineInvestment[0]
                    disciplineInvestment.remove(current)
                    removeIllegal(current)
                }
        }

        //notify of either successful removal or failed addition
        return false
    }

    /**
     * Checks if the added discipline is allowed to be taken based on duk'zarist psychic restrictions.
     *
     * @param attemptedAddition discipline the character is intending to add
     * @return true if addition of this item is legal
     */
    fun dukzaristAvailable(attemptedAddition: Discipline): Boolean{
        //return true if the character is not a duk'zarist
        if(charInstance.ownRace != charInstance.races.dukzaristAdvantages) return true

        //return true if the duk'zarist already has pyrokinesis
        else if (disciplineInvestment.contains(pyrokinesis)) return true

        //return true if no disciplines taken, but is currently taking pyrokinesis
        else if (attemptedAddition == pyrokinesis) return true

        //notify of failed addition
        return false
    }

    /**
     * Attempt to add or remove the inputted Psychic Power to the character.
     *
     * @param item Psychic Power to add or remove from the list
     * @param discipline associated discipline of the Psychic Power
     * @param into whether the power is being added or removed
     * @return true if the power has successfully been added
     */
    fun masterPower(item: PsychicPower, discipline: Discipline, into: Boolean): Boolean{
        //if attempting to add, there is a psychic point to buy it, and if the character can buy it
        if(into && getFreePsyPoints() > 0 && legalBuy(item, discipline)){
            //add power to the character and spend the point
            masteredPowers.add(item)
            recalcPsyPointsSpent()

            //notify of successful purchase
            return true
        }

        //if attempting to remove and the power is present in the list
        else if(!into && masteredPowers.contains(item)){
            //remove the item from the character
            masteredPowers.remove(item)

            //remove any other Psychic Powers as needed
            removeIllegal(discipline)
        }

        //notify of either successful removal or failed addition
        return false
    }

    /**
     * Check if the given Psychic Power can be bought by this character.
     *
     * @param item Psychic Power to check the validity of
     * @param discipline Power's associated Psychic Discipline
     * @return true if power is legally acquired
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
                    if (it.level == 2 && discipline.allPowers.contains(it) && legalBuy(it, discipline))
                        return true
                }
            }
        }

        //notify of illegal buy
        return false
    }

    /**
     * Removes a discipline from the legally acquirable list.
     *
     * @paraam input integer indicator of a particular discipline
     */
    fun removeLegalDisciplineFromInt(input: Int){
        //get the indicated discipline
        val discipline = allDisciplines[input]

        //remove the indicated discipline
        removeLegalDiscipline(discipline)
    }

    /**
     * Removes a discipline from the legally acquirable list.
     *
     * @param input discipline to be removed from the legal list
     */
    fun removeLegalDiscipline(input: Discipline){
        //remove the item from the legaal list
        legalDisciplines.remove(input)

        //remove from the taken list
        if(disciplineInvestment.contains(input))
            updateInvestment(input, false)
    }

    /**
     * Removes any taken Psychic Powers that are no longer legally bought.
     *
     * @param discipline Psychic Discipline to check for legality of
     */
    fun removeIllegal(discipline: Discipline){
        //remove any illegal powers of this discipline
        masteredPowers.removeIf{!legalBuy(it, discipline)}
        recalcPsyPointsSpent()
    }

    /**
     * Determine the number of psychic points spent by the user.
     */
    fun recalcPsyPointsSpent(){
        spentPsychicPoints = disciplineInvestment.size + masteredPowers.size
    }

    /**
     * Retrieves the names of all Psychic Powers.
     *
     * @return string list of all power names
     */
    fun getAllPowerNames(): List<String>{
        //initialize power list output
        val output = mutableListOf<String>()

        //gather all psychic powers
        val psychicPowerList =
            telepathy.allPowers +
                    psychokinesis.allPowers +
                    pyrokinesis.allPowers +
                    cryokinesis.allPowers +
                    physicalIncrease.allPowers +
                    energyPowers.allPowers +
                    sentiencePowers.allPowers +
                    telemetry.allPowers

        //add each name to the list
        psychicPowerList.forEach {
            output.add(it.name)
        }

        //return the full list
        return output.toList()
    }

    /**
     * Determines the number of development points spent in this section.
     */
    fun calculateSpent(): Int{
        return (boughtPsyPoints * charInstance.ownClass.psyPointGrowth) +
                (psyProjectionBought * charInstance.ownClass.psyProjGrowth)
    }

    /**
     * Get the Psychic data from the saved file.
     *
     * @param fileReader file to get the data from
     */
    fun loadPsychic(fileReader: BufferedReader){
        //retrieve psychic points and projection data
        buyPsyPoints(fileReader.readLine().toInt())
        buyPsyProjection(fileReader.readLine().toInt())

        //load discipline investment data
        for(index in 0 until fileReader.readLine().toInt()){
            updateInvestment(allDisciplines[fileReader.readLine().toInt()], true)
        }

        //load mastered powers
        for(index in 0 until fileReader.readLine().toInt()){
            val powerDiscipline = allDisciplines[fileReader.readLine().toInt()]
            val power = powerDiscipline.allPowers[fileReader.readLine().toInt()]

            masterPower(power, powerDiscipline, true)
        }
    }

    /**
     * Write data in this section to the character's file.
     */
    fun writePsychic(){
        //write psychic point and projection data
        charInstance.addNewData(boughtPsyPoints)
        charInstance.addNewData(psyProjectionBought)

        //add discipline data
        charInstance.addNewData(disciplineInvestment.size)
        disciplineInvestment.forEach{
            charInstance.addNewData(allDisciplines.indexOf(it))
        }

        //add mastered power data
        charInstance.addNewData(masteredPowers.size)
        masteredPowers.forEach{
            val powerDiscipline = getPowerDiscipline(it)!!
            charInstance.addNewData(allDisciplines.indexOf(powerDiscipline))
            charInstance.addNewData(powerDiscipline.allPowers.indexOf(it))
        }
    }

    /**
     * Retrieves the associated discipline of the inputted psychic power.
     *
     * @param search power to determine the discipline of
     * @return discipline associated with the power
     */
    fun getPowerDiscipline(search: PsychicPower): Discipline?{
        allDisciplines.forEach{
            if(it.allPowers.contains(search))
                return it
        }

        return null
    }
}