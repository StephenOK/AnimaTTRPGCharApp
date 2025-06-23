package com.paetus.animaCharCreator.character_creation.attributes.psychic

import androidx.compose.runtime.mutableIntStateOf
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Fragment that holds the data on the character's psychic abilities.
 * Holds and alters the data for psychic potential, points, and projection.
 * Holds data for the psychic disciplines and their powers.
 *
 * @param charInstance object that holds all of a character's data
 */
open class Psychic(private val charInstance: BaseCharacter){
    fun getPsyLibrary(): PsyLibrary {return charInstance.objectDB.psyLibrary}

    //initialize value for Psychic Potential
    val psyPotentialBase = mutableIntStateOf(value = 10)

    //initialize potential from Psychic Points
    val pointsInPotential = mutableIntStateOf(value = 0)
    val potentialFromPoints = mutableIntStateOf(value = 0)

    //initialize total psychic potential
    val psyPotentialTotal = mutableIntStateOf(value = 10)

    //initialize values for Psychic Points
    val boughtPsyPoints = mutableIntStateOf(value = 0)
    val innatePsyPoints = mutableIntStateOf(value = 0)
    val totalPsychicPoints = mutableIntStateOf(value = 0)
    val spentPsychicPoints = mutableIntStateOf(value = 0)

    //initialize number of innate slots available
    val innateSlotCount = mutableIntStateOf(value = 0)

    //initialize values for Psychic Projection
    val psyProjectionBought = mutableIntStateOf(value = 0)
    val psyProjectionTotal = mutableIntStateOf(value = 0)

    //initialize discipline and power lists
    val legalDisciplines = mutableListOf<Discipline>()
    val disciplineInvestment = mutableListOf<Discipline>()
    val masteredPowers = mutableMapOf<PsychicPower, Int>()

    //initialize psychic power items
    val telepathy = getPsyLibrary().allDisciplines[0]
    val psychokinesis = getPsyLibrary().allDisciplines[1]
    val pyrokinesis = getPsyLibrary().allDisciplines[2]
    val cryokinesis = getPsyLibrary().allDisciplines[3]
    val physicalIncrease = getPsyLibrary().allDisciplines[4]
    val energyPowers = getPsyLibrary().allDisciplines[5]
    val sentiencePowers = getPsyLibrary().allDisciplines[6]
    val telemetry = getPsyLibrary().allDisciplines[7]
    val matrixPowers = getPsyLibrary().allDisciplines[8]

    /**
     * Gets the class's psychic point DP cost.
     */
    open fun psyPointCost(): Int{return charInstance.classes.getClass().psyPointGrowth}

    /**
     * Gets the class's psychic projection DP cost.
     */
    open fun psyProjCost(): Int{return charInstance.classes.getClass().psyProjGrowth}

    /**
     * Determines the value for the character's Psychic Potential.
     */
    fun setBasePotential(){
        //determine value exclusively from the character's willpower
        psyPotentialBase.intValue = when(charInstance.primaryList.wp.total.intValue){
            in 0..4 -> 0
            in 5 .. 14 -> 10 * (charInstance.primaryList.wp.total.intValue -4)
            else -> 100 + ((charInstance.primaryList.wp.total.intValue - 14) * 20)
        }

        updateTotalPotential()
    }

    /**
     * Set the psychic points contributing to psychic potential and the points gained from them.
     *
     * @param potentialBuy number of psychic points invested
     */
    open fun setPointPotential(potentialBuy: Int){
        //set number of points invested
        pointsInPotential.intValue = potentialBuy

        //set psychic potential added
        potentialFromPoints.intValue = when(potentialBuy){
            0 -> 0
            1, 2 -> 10
            in 3..5 -> 20
            in 6..9 -> 30
            in 10..14 -> 40
            in 15..20 -> 50
            in 21..27 -> 60
            in 28..35 -> 70
            in 36..44 -> 80
            in 45..54 -> 90
            else -> 100
        }

        //update free points and potential
        recalcPsyPointsSpent()
        updateTotalPotential()
    }

    /**
     * Gets the total psychic potential for the character.
     */
    fun updateTotalPotential(){
        psyPotentialTotal.intValue = psyPotentialBase.intValue + potentialFromPoints.intValue
    }

    /**
     * Set the amount of bought Psychic Points for the character.
     *
     * @param ppBuy number of points the user is buying for their character
     */
    open fun buyPsyPoints(ppBuy: Int){
        //set bought amount and update psychic point total
        boughtPsyPoints.intValue = ppBuy
        charInstance.updateTotalSpent()
        updatePsyPointTotal()
    }

    /**
     * Set the amount of base Psychic Points for the character.
     */
    open fun setInnatePsy(){
        innatePsyPoints.intValue =
            //set points to 0 if at level 0
            if (charInstance.lvl.intValue == 0) 0
            //start character at 1 point and add more depending on additional levels
            else 1 + charInstance.lvl.intValue/charInstance.classes.getClass().psyPerTurn

        //update total
        updatePsyPointTotal()
    }

    /**
     * Recalculates the total amount of Psychic Points the character can utilize.
     */
    fun updatePsyPointTotal(){
        totalPsychicPoints.intValue = boughtPsyPoints.intValue + innatePsyPoints.intValue

        //sets pyrokinesis as set if character is duk'zarist and does not have pyrokinesis
        if(totalPsychicPoints.intValue > 0 && charInstance.ownRace.value == charInstance.objectDB.races.dukzaristAdvantages &&
            !disciplineInvestment.contains(element = pyrokinesis))
            updateInvestment(discipline = pyrokinesis, isTaken = true)
    }

    /**
     * Determine the number of psychic points spent by the user.
     */
    fun recalcPsyPointsSpent(){
        var reinforcement = 0
        masteredPowers.forEach{power -> reinforcement += power.value}

        spentPsychicPoints.intValue = disciplineInvestment.size + masteredPowers.size + reinforcement + pointsInPotential.intValue + (innateSlotCount.intValue * 2)
    }

    /**
     * Set the amount of bought Psychic Projection for the character.
     *
     * @param projBuy number of Psychic Projection points to buy for the character
     */
    open fun buyPsyProjection(projBuy: Int){
        //set bought amount and update Psychic Projection
        psyProjectionBought.intValue = projBuy
        charInstance.updateTotalSpent()
        updatePsyProjection()
    }

    /**
     * Recalculates the total amount of Psychic Projection points the character can utilize.
     */
    fun updatePsyProjection(){
        psyProjectionTotal.intValue = psyProjectionBought.intValue + charInstance.primaryList.dex.outputMod.intValue
    }

    /**
     * Check that the bought psychic projection points are a valid input.
     *
     * @return true if valid
     */
    fun getValidProjection(): Boolean{
        return psyProjectionBought.intValue * charInstance.classes.getClass().psyProjGrowth <= charInstance.maxPsyDP.intValue/2
    }

    /**
     * Determine the character's currently available Psychic Points.
     *
     * @return number of psychic points available to the character
     */
    fun getFreePsyPoints(): Int{
        return totalPsychicPoints.intValue - spentPsychicPoints.intValue
    }

    /**
     * Purchases the indicated number of innate slots.
     *
     * @param slotBuy number of innate slots to purchase
     */
    open fun buyInnateSlots(slotBuy: Int){
        innateSlotCount.intValue = slotBuy
        recalcPsyPointsSpent()
    }

    /**
     * Removes a discipline from the legally acquirable list.
     *
     * @param discIndex integer indicator of a particular discipline
     */
    fun removeLegalDisciplineFromInt(discIndex: Int){
        //get the indicated discipline
        val discipline = getPsyLibrary().allDisciplines[discIndex]

        //remove the indicated discipline
        removeLegalDiscipline(discipline = discipline)
    }

    /**
     * Removes a discipline from the legally acquirable list.
     *
     * @param discipline discipline to be removed from the legal list
     */
    fun removeLegalDiscipline(discipline: Discipline){
        //remove the item from the legal list
        legalDisciplines.remove(element = discipline)

        //remove from the taken list
        if(disciplineInvestment.contains(discipline))
            updateInvestment(discipline = discipline, isTaken = false)
    }

    /**
     * Attempt to add or remove a Psychic Discipline from the character's taken list.
     *
     * @param discipline discipline that is a target of the action
     * @param isTaken true if adding the item; false if removing
     * @return true if discipline has been successfully added
     */
    open fun updateInvestment(
        discipline: Discipline,
        isTaken: Boolean
    ): Boolean{
        //if attempting to acquire and points are available
        if(isTaken && dukzaristAvailable(addingDiscipline = discipline) &&
            getFreePsyPoints() > 0 && legalDisciplines.contains(element = discipline)) {

            //add item and spend points
            disciplineInvestment.add(element = discipline)
            recalcPsyPointsSpent()

            //notify of successful acquisition
            return true
        }

        //if attempting to remove the discipline
        else if (!isTaken) {
            //if character isn't duk'zarist or pyrokinesis isn't removed
            if(charInstance.ownRace.value != charInstance.objectDB.races.dukzaristAdvantages || discipline != pyrokinesis) {
                //remove the discipline
                disciplineInvestment.remove(element = discipline)

                //remove any associated powers
                removeIllegal(discipline = discipline)
            }
            else
                //clear all disciplines from the record
                while(disciplineInvestment.isNotEmpty()){
                    val current = disciplineInvestment[0]
                    disciplineInvestment.remove(current)
                    removeIllegal(discipline = current)
                }
        }

        //notify of either successful removal or failed addition
        return false
    }

    /**
     * Checks if the added discipline is allowed to be taken based on duk'zarist psychic restrictions.
     *
     * @param addingDiscipline discipline the character is intending to add
     * @return true if addition of this item is legal
     */
    fun dukzaristAvailable(
        addingDiscipline: Discipline
    ): Boolean{
        //return true if the character is not a duk'zarist
        if(charInstance.ownRace.value != charInstance.objectDB.races.dukzaristAdvantages) return true

        //return true if the duk'zarist already has pyrokinesis
        else if (disciplineInvestment.contains(pyrokinesis)) return true

        //return true if no disciplines taken, but is currently taking pyrokinesis
        else if (addingDiscipline == pyrokinesis) return true

        //notify of failed addition
        return false
    }

    /**
     * Attempt to add or remove the inputted Psychic Power to the character.
     *
     * @param power Psychic Power to add or remove from the list
     * @param discipline associated discipline of the Psychic Power
     * @param isMastering whether the power is being added or removed
     * @return true if the power has successfully been added
     */
    open fun masterPower(
        power: PsychicPower,
        discipline: Discipline,
        isMastering: Boolean
    ): Boolean{
        //if attempting to add, there is a psychic point to buy it, and if the character can buy it
        if(isMastering && getFreePsyPoints() > 0 && legalBuy(power = power, discipline = discipline)){
            //add power to the character and spend the point
            masteredPowers += Pair(power, 0)
            recalcPsyPointsSpent()

            //notify of successful purchase
            return true
        }

        //if attempting to remove and the power is present in the list
        else if(!isMastering && masteredPowers.contains(key = power)){
            //remove the item from the character
            masteredPowers.remove(key = power)

            //remove any other Psychic Powers as needed
            removeIllegal(discipline = discipline)
        }

        //notify of either successful removal or failed addition
        return false
    }

    /**
     * Enhances the indicated power with the indicated number of psychic points.
     *
     * @param power psychic power to enhance
     * @param applyPoints psychic points invested in this power
     */
    open fun enhancePower(
        power: PsychicPower,
        applyPoints: Int
    ){
        masteredPowers[power] = applyPoints
        recalcPsyPointsSpent()
    }

    /**
     * Check if the given Psychic Power can be bought by this character.
     *
     * @param power Psychic Power to check the validity of
     * @param discipline Power's associated Psychic Discipline
     * @return true if power is legally acquired
     */
    fun legalBuy(
        power: PsychicPower,
        discipline: Discipline
    ): Boolean{
        if(disciplineInvestment.contains(element = discipline) ||
            (discipline == matrixPowers && legalDisciplines.isNotEmpty())
        ){
            //add no matter what if it is level 1 or 0
            if (power.level <= 1)
                return true

            //if power is level 2
            else if (power.level == 2) {
                //for each taken power
                masteredPowers.keys.forEach{heldPower ->
                    //determine if it is a level 1 power of the same discipline
                    if (heldPower.level == 1 && discipline.allPowers.contains(element = heldPower))
                        return true
                }
            }

            //if power is level 3
            else if (power.level == 3) {
                //for each taken power
                masteredPowers.keys.forEach{heldPower ->
                    //determine if it is a level 2 power of the same discipline
                    if (heldPower.level == 2 && discipline.allPowers.contains(element = heldPower) &&
                        legalBuy(power = heldPower, discipline = discipline))
                        return true
                }
            }
        }

        //notify of illegal buy
        return false
    }

    /**
     * Retrieves the associated discipline of the inputted psychic power.
     *
     * @param power power to determine the discipline of
     * @return discipline associated with the power
     */
    fun getPowerDiscipline(
        power: PsychicPower
    ): Discipline?{
        //search each discipline for the given power
        getPsyLibrary().allDisciplines.forEach{ discipline ->
            //return the discipline if it is found
            if(discipline.allPowers.contains(element = power))
                return discipline
        }

        return null
    }

    /**
     * Removes any taken Psychic Powers that are no longer legally bought.
     *
     * @param discipline Psychic Discipline to check for legality of
     */
    open fun removeIllegal(discipline: Discipline){
        //initialize removing list
        val toRemove = mutableListOf<PsychicPower>()

        //remove any illegal powers of this discipline
        masteredPowers.keys.forEach{power ->
            if(!legalBuy(power = power, discipline = discipline))
                toRemove += power
        }

        toRemove.forEach{power -> masteredPowers.remove(key = power)}
        recalcPsyPointsSpent()
    }

    /**
     * Determines the number of development points spent in this section.
     *
     * @return number of points spent in the psychic section
     */
    fun calculateSpent(): Int{
        return (boughtPsyPoints.intValue * charInstance.classes.getClass().psyPointGrowth) +
                (psyProjectionBought.intValue * charInstance.classes.getClass().psyProjGrowth)
    }

    /**
     * Get the Psychic data from the saved file.
     *
     * @param fileReader file to get the data from
     */
    fun loadPsychic(fileReader: BufferedReader){
        //retrieve psychic points and projection data
        buyPsyPoints(ppBuy = fileReader.readLine().toInt())
        setPointPotential(potentialBuy = fileReader.readLine().toInt())
        buyPsyProjection(projBuy = fileReader.readLine().toInt())

        //load discipline investment data
        (0 until fileReader.readLine().toInt()).forEach{
            val searchName = fileReader.readLine()
            getPsyLibrary().allDisciplines.forEach search@{discipline ->
                if(discipline.saveName == searchName) {
                    updateInvestment(discipline = discipline, isTaken = true)
                    return@search
                }
            }
        }

        //load mastered powers
        (0 until fileReader.readLine().toInt()).forEach{
            //retrieve power name
            val spellName = fileReader.readLine()

            getPsyLibrary().allDisciplines.forEach headSearch@{discipline ->
                discipline.allPowers.forEach{power ->
                    //find matching power
                    if(power.saveName == spellName){
                        masterPower(power = power, discipline = discipline, isMastering = true)

                        //apply enhancement value
                        enhancePower(power = power, applyPoints = fileReader.readLine().toInt())
                        return@headSearch
                    }
                }
            }
        }

        //get number of innate slots purchased
        buyInnateSlots(slotBuy = fileReader.readLine().toInt())
    }

    /**
     * Write data in this section to the character's file.
     *
     * @param byteArray output stream for this section's data
     */
    fun writePsychic(byteArray: ByteArrayOutputStream) {
        //write psychic point and projection data
        writeDataTo(writer = byteArray, input = boughtPsyPoints.intValue)
        writeDataTo(writer = byteArray, input = pointsInPotential.intValue)
        writeDataTo(writer = byteArray, input = psyProjectionBought.intValue)

        //add discipline data
        writeDataTo(writer = byteArray, input = disciplineInvestment.size)
        disciplineInvestment.forEach{discipline ->
            writeDataTo(writer = byteArray, input = discipline.saveName)
        }

        //add mastered power data
        writeDataTo(writer = byteArray, input = masteredPowers.size)
        masteredPowers.forEach{(power, investment) ->
            writeDataTo(writer = byteArray, input = power.saveName)
            writeDataTo(writer = byteArray, input = investment)
        }

        //add innate slot count
        writeDataTo(writer = byteArray, input = innateSlotCount.intValue)
    }
}