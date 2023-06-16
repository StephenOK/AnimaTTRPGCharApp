package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques

import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.Element

/**
 * Holds data in regards to an individual aspect of a technique.
 *
 * @param name name of the effect
 * @param effect details of the effect
 * @param mkCost martial knowledge needed to acquire this effect
 * @param maintTotal maintenance needed for this effect
 * @param costPair primary and secondary cost for this effect
 * @param kiBuild ki needed to utilize this effect
 * @param buildAdditions additional ki needed if channelling with the associated characteristic
 * @param elements elements associated with this effect
 * @param lvl level of the effect's power
 */
data class TechniqueEffect(
    val name: String,
    val effect: String,
    val mkCost: Int,
    val maintTotal: Int,
    val costPair: Pair<Int, Int>,
    val kiBuild: MutableList<Int>,
    val buildAdditions: List<Int?>,
    var elements: MutableList<Element>,
    val lvl: Int
){
    /**
     * Determines if the ki build of this technique is legal.
     *
     * @param isPrimary determines if the effect is the primary one
     * @return true if build is legal
     */
    fun checkBuild(isPrimary: Boolean): Boolean{
        //initialize build total
        var total = 0

        //determine build needed based on whether it's primary
        var accTotal =
            if(isPrimary) costPair.first
            else costPair.second

        //for each item in the array
        for(index in 0..5){
            //add the input to the build value
            total += kiBuild[index]

            //add additional build value if needed
            if(kiBuild[index] > 0)
                accTotal += buildAdditions[index]!!
        }

        //return if build is legal
        return total == accTotal
    }

    /**
     * Checks if the inputted effect is identical to this one.
     *
     * @param compareTo effect to check equivalency with
     * @return true if input matches this effect
     */
    fun equivalentTo(compareTo: TechniqueEffect): Boolean{
        return compareTo.name == name &&
                compareTo.effect == effect &&
                compareTo.mkCost == mkCost &&
                compareTo.maintTotal == maintTotal &&
                compareTo.costPair.first == costPair.first &&
                compareTo.costPair.second == costPair.second &&
                compareTo.kiBuild == kiBuild &&
                compareTo.elements == elements &&
                compareTo.lvl == lvl
    }

    /**
     * Writes effect data to the save file for the character.
     */
    fun write(charInstance: BaseCharacter){
        //write the effect name
        charInstance.addNewData(name)

        //write the effect description
        charInstance.addNewData(effect)

        //write the effect's cost
        charInstance.addNewData(mkCost)

        //write the effect's maintenance total
        charInstance.addNewData(maintTotal)

        //write the effect's costs
        charInstance.addNewData(costPair.first)
        charInstance.addNewData(costPair.second)

        //write the full ki build
        kiBuild.forEach{
            charInstance.addNewData(it)
        }

        //write the build additions
        buildAdditions.forEach{
            charInstance.addNewData(it)
        }

        //write the number of elements and each element
        charInstance.addNewData(elements.size)
        elements.forEach{
            charInstance.addNewData(it.name)
        }

        //write the effect's level
        charInstance.addNewData(lvl)
    }
}