package com.paetus.animaCharCreator.character_creation.attributes.psychic

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Holds the data for the SBL Character's psychic abilities.
 *
 * @param charInstance object that holds all of a character's data
 */
class SblPsychic(
    val charInstance: SblChar
): Psychic(charInstance = charInstance) {
    /**
     * Gets the class's psychic point DP cost.
     */
    override fun psyPointCost(): Int {
        return charInstance.getCharAtLevel().psychic.psyPointCost()
    }

    /**
     * Gets the class's psychic projection DP cost.
     */
    override fun psyProjCost(): Int {
        return charInstance.getCharAtLevel().psychic.psyProjCost()
    }

    /**
     * Set the amount of base Psychic Points for the character.
     */
    override fun setInnatePsy() {
        //get class total for the character
        innatePsyPoints.intValue =
            //no points for a level 0 character
            if(charInstance.lvl.intValue == 0) 0
            //sum up each level's bonus for the character
            else {
                var output = 1.0

                charInstance.levelLoop(startLevel = 1){
                    output += 1.0/it.classes.getClass().psyPerTurn.toDouble()
                }

                output.toInt()
            }

        updatePsyPointTotal()
    }

    /**
     * Set the psychic points contributing to psychic potential and the points gained from them.
     *
     * @param potentialBuy number of psychic points invested
     */
    override fun setPointPotential(potentialBuy: Int) {
        var prevPotential = 0

        charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){character ->
            prevPotential += character.psychic.pointsInPotential.intValue
        }

        charInstance.getCharAtLevel().psychic.setPointPotential(potentialBuy - prevPotential)

        updatePsyPotential()
    }

    /**
     * Set the amount of bought Psychic Points for the character.
     *
     * @param ppBuy number of points the user is buying for their character
     */
    override fun buyPsyPoints(ppBuy: Int) {
        var prevPoints = 0

        charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){character ->
            prevPoints += character.psychic.boughtPsyPoints.intValue
        }

        charInstance.getCharAtLevel().psychic.buyPsyPoints(ppBuy - prevPoints)

        updatePsyPointsBought()
    }

    /**
     * Set the amount of bought Psychic Projection for the character.
     *
     * @param projBuy number of Psychic Projection points to buy for the character
     */
    override fun buyPsyProjection(projBuy: Int) {
        var prevPoints = 0

        charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){character ->
            prevPoints += character.psychic.psyProjectionBought.intValue
        }

        charInstance.getCharAtLevel().psychic.buyPsyProjection(projBuy - prevPoints)

        updatePsyProjectionBought()
    }

    /**
     * Purchases the indicated number of innate slots.
     *
     * @param slotBuy number of innate slots to purchase
     */
    override fun buyInnateSlots(slotBuy: Int) {
        var prevInnates = 0

        charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){character ->
            prevInnates += character.psychic.innateSlotCount.intValue
        }

        charInstance.getCharAtLevel().psychic.buyInnateSlots(slotBuy - prevInnates)

        updateInnateSlots()
    }

    /**
     * Updates the psychic potential for this character based on levels.
     */
    fun updatePsyPotential(){
        //reset psychic potential
        pointsInPotential.intValue = 0

        //add points from each level
        charInstance.levelLoop{character ->
            pointsInPotential.intValue += character.psychic.pointsInPotential.intValue
        }

        //set psychic potential added
        potentialFromPoints.intValue = when(pointsInPotential.intValue){
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
     * Updates the character's psychic points acquired based on their level.
     */
    fun updatePsyPointsBought(){
        //reset psychic points acquired
        boughtPsyPoints.intValue = 0

        //add psychic points from each level
        charInstance.levelLoop{character ->
            boughtPsyPoints.intValue += character.psychic.boughtPsyPoints.intValue
        }

        //update points bought and DP spent
        charInstance.updateTotalSpent()
        updatePsyPointTotal()
    }

    /**
     * Updates the character's psychic projection bought based on their level.
     */
    fun updatePsyProjectionBought(){
        //reset psychic projection points
        psyProjectionBought.intValue = 0

        //add psychic projection from each level
        charInstance.levelLoop{character ->
            psyProjectionBought.intValue += character.psychic.psyProjectionBought.intValue
        }

        //update projection bought and DP spent
        charInstance.updateTotalSpent()
        updatePsyProjection()
    }

    /**
     * Updates innate slots acquired based on their level.
     */
    fun updateInnateSlots(){
        //reset innate slots acquired
        innateSlotCount.intValue = 0

        //add slots for each level
        charInstance.levelLoop{character ->
            innateSlotCount.intValue += character.psychic.innateSlotCount.intValue
        }

        //update psychic points spent
        recalcPsyPointsSpent()
    }

    /**
     * Determines that psychic potential is not reduced in this level.
     *
     * @return true if no reduction
     */
    fun validPsyPotentialGrowth(): Boolean{
        return charInstance.getCharAtLevel().psychic.pointsInPotential.intValue >= 0
    }

    /**
     * Determines that psychic points are not reduced in this level.
     *
     * @return true if no reduction
     */
    fun validPsyPointGrowth(): Boolean{
        return charInstance.getCharAtLevel().psychic.boughtPsyPoints.intValue >= 0
    }

    /**
     * Determines that psychic projection is not reduced in this level.
     *
     * @return true if no reduction
     */
    fun validPsyProjGrowth(): Boolean{
        return charInstance.getCharAtLevel().psychic.psyProjectionBought.intValue >= 0
    }

    /**
     * Determines that innate slots are not reduced in this level.
     *
     * @return true if no reduction
     */
    fun validInnateSlots(): Boolean{
        return charInstance.getCharAtLevel().psychic.innateSlotCount.intValue >= 0
    }

    /**
     * Attempt to add or remove a Psychic Discipline from the character's taken list.
     *
     * @param discipline discipline that is a target of the action
     * @param isTaken true if adding the item; false if removing
     * @return true if discipline has been successfully added
     */
    override fun updateInvestment(
        discipline: Discipline,
        isTaken: Boolean
    ): Boolean {
        //if attempting to acquire and points are available
        if(isTaken && dukzaristAvailable(addingDiscipline = discipline) &&
            getFreePsyPoints() > 0 && legalDisciplines.contains(element = discipline)){

            //add item to the level record
            charInstance.getCharAtLevel().psychic.disciplineInvestment.add(element = discipline)

            //remove potential duplicate acquisition
            charInstance.levelLoop(
                startLevel = charInstance.lvl.intValue + 1,
                endLevel = 20
            ){character ->
                character.psychic.disciplineInvestment.remove(element = discipline)
            }
        }

        //if attempting to remove the discipline and it was taken at this level
        else if(!isTaken &&
            charInstance.getCharAtLevel().psychic.disciplineInvestment.contains(element = discipline)){

            //if character isn't duk'zarist or pyrokinesis isn't removed
            if(charInstance.ownRace.value != charInstance.objectDB.races.dukzaristAdvantages || discipline != pyrokinesis){
                //remove the discipline from this and the level record
                disciplineInvestment.remove(element = discipline)
                charInstance.getCharAtLevel().psychic.disciplineInvestment.remove(element = discipline)

                //remove associated powers
                removeIllegal(discipline = discipline)
            }
            else{
                //clear all disciplines from all level records
                charInstance.levelLoop(
                    startLevel = charInstance.lvl.intValue,
                    endLevel = 20
                ){character ->
                    while(character.psychic.disciplineInvestment.isNotEmpty()){
                        val current = character.psychic.disciplineInvestment[0]
                        character.psychic.disciplineInvestment.remove(element = current)
                        removeIllegal(discipline = current)
                    }
                }
            }
        }

        //update current disciplines
        updateDisciplines()

        //return held state of the inputted discipline
        return disciplineInvestment.contains(element = discipline)
    }

    /**
     * Attempt to add or remove the inputted Psychic Power to the character.
     *
     * @param power Psychic Power to add or remove from the list
     * @param discipline associated discipline of the Psychic Power
     * @param isMastering whether the power is being added or removed
     * @return true if the power has successfully been added
     */
    override fun masterPower(
        power: PsychicPower,
        discipline: Discipline,
        isMastering: Boolean
    ): Boolean {
        //if attempting to add with psychic points available and power is legally acquirable
        if(isMastering && getFreePsyPoints() > 0 && legalBuy(power = power, discipline = discipline)) {
            //add power to the level record
            charInstance.getCharAtLevel().psychic.masteredPowers += Pair(power, 0)

            //remove any instance of this power being added in future levels
            charInstance.levelLoop(
                startLevel = charInstance.lvl.intValue + 1,
                endLevel = 20
            ){character ->
                //remove instances of power being added without enhancement
                if(character.psychic.masteredPowers.containsKey(power) &&
                    character.psychic.masteredPowers[power] == 0)
                    character.psychic.masteredPowers.remove(power)
            }
        }

        //if removing a power and it was taken at this level
        else if(!isMastering && masteredPowers.contains(key = power) && charInstance.getCharAtLevel().psychic.masteredPowers.containsKey(key = power)){
            //remove power from record
            charInstance.getCharAtLevel().psychic.masteredPowers.remove(key = power)

            //remove any powers acquired that are now illegal
            removeIllegal(discipline = discipline)
        }

        //update powers taken
        updatePowers()

        //return held state of the power
        return masteredPowers.containsKey(power)
    }

    /**
     * Enhances the indicated power with the indicated number of psychic points.
     *
     * @param power psychic power to enhance
     * @param applyPoints psychic points invested in this power
     */
    override fun enhancePower(
        power: PsychicPower,
        applyPoints: Int
    ){
        //initialize points from previous levels
        var prevPoints = 0

        //collect points from the previous level records
        charInstance.levelLoop(endLevel = charInstance.lvl.intValue - 1){character ->
            if(character.psychic.masteredPowers.containsKey(power))
                prevPoints += character.psychic.masteredPowers[power]!!
        }

        //apply added points to this level record
        charInstance.getCharAtLevel().psychic.masteredPowers[power] = applyPoints - prevPoints

        //update powers and enhancements
        updatePowers()
    }

    /**
     * Removes any taken Psychic Powers that are no longer legally bought.
     *
     * @param discipline Psychic Discipline to check for legality of
     */
    override fun removeIllegal(discipline: Discipline) {
        //initialize list of legal powers acquired
        val legalList = mutableListOf<PsychicPower>()

        //accrue list of previous powers for this list
        charInstance.levelLoop(
            endLevel = charInstance.lvl.intValue - 1
        ){character ->
            character.psychic.masteredPowers.keys.forEach{power ->
                if(disciplineInvestment.contains(discipline) && discipline.allPowers.contains(power))
                    legalList.add(power)
            }
        }

        //remove illegal powers from this and future levels
        charInstance.levelLoop(
            startLevel = charInstance.lvl.intValue,
            endLevel = 20
        ){character ->
            //initialize list of powers to remove
            val removePowers = mutableListOf<PsychicPower>()

            //for each power in this record
            character.psychic.masteredPowers.keys.forEach{power ->
                //remove powers with removed disciplines
                if(!disciplineInvestment.contains(discipline))
                    removePowers.add(power)
                //maintain any level 1 power
                else if (power.level == 1)
                    legalList.add(power)
                //remove powers with no power of the next lower level
                else {
                    //initialize legal flag
                    var legal = false

                    //check legal list for powers of the next lower level
                    legalList.forEach legalCheck@{ item ->
                        //end loop if power found
                        if(item.level == power.level - 1) {
                            legal = true
                            return@legalCheck
                        }
                    }

                    //add to removal list if no validating power found
                    if(!legal)
                        removePowers.add(power)
                    //add to maintain list if power found
                    else
                        legalList.add(power)
                }
            }

            //remove all flagged powers
            removePowers.forEach {character.psychic.masteredPowers.remove(it)}
        }

        //update current powers
        updatePowers()
    }

    /**
     * Update current disciplines available to the character.
     */
    private fun updateDisciplines(){
        //clear disciplines list
        disciplineInvestment.clear()

        //retrieve disciplines
        charInstance.levelLoop{character ->
            disciplineInvestment.addAll(character.psychic.disciplineInvestment)
        }

        updatePowers()

        //recalculate psychic points spent
        recalcPsyPointsSpent()
    }

    /**
     * Update current powers available to the character.
     */
    private fun updatePowers(){
        //clear powers list
        masteredPowers.clear()

        //get power data from each level record
        charInstance.levelLoop{character ->
            character.psychic.masteredPowers.keys.forEach{power ->
                //add enhancements if gained power from a previous level
                if(masteredPowers.contains(power))
                    masteredPowers[power] = masteredPowers[power]!! + character.psychic.masteredPowers[power]!!
                //add power to record
                else
                    masteredPowers += Pair(power, character.psychic.masteredPowers[power]!!)
            }
        }

        //recalculate psychic points spent
        recalcPsyPointsSpent()
    }

    /**
     * Finds any reductions in power enhancement and returns a list of offending items.
     *
     * @return list of psychic powers that hold point reductions
     */
    fun findIllegalEnhancement(): List<PsychicPower>{
        //initialize output list
        val output = mutableListOf<PsychicPower>()

        //check each acquired power
        masteredPowers.keys.forEach{power ->
            //if level record holds a negative value, add power to the output
            if(charInstance.getCharAtLevel().psychic.masteredPowers.containsKey(power) &&
                charInstance.getCharAtLevel().psychic.masteredPowers[power]!! < 0)
                output.add(power)
        }

        //return list of offending powers
        return output
    }

    /**
     * Updates the level based values for the character's psychic attributes.
     */
    fun levelUpdate(){
        updatePsyPotential()
        updatePsyPointsBought()
        updatePsyProjectionBought()
        updateInnateSlots()
        updateDisciplines()
        updatePowers()
    }
}