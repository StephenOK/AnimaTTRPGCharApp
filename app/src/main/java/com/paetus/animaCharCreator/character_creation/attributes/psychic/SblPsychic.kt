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
            in 11..14 -> 40
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

        //add psychic points from each levevl
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
     * Updates the level based values for the character's psychic attributes.
     */
    fun levelUpdate(){
        updatePsyPotential()
        updatePsyPointsBought()
        updatePsyProjectionBought()
        updateInnateSlots()
    }
}