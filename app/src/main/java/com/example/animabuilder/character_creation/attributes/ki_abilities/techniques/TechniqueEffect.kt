package com.example.animabuilder.character_creation.attributes.ki_abilities.techniques

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import java.io.Serializable

/**
 * Holds data in regards to an individual aspect of a technique
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
): Serializable{
    fun checkBuild(isPrimary: Boolean): Boolean{
        var total = 0
        var accTotal =
            if(isPrimary) costPair.first
            else costPair.second

        for(index in 0..5){
            total += kiBuild[index]

            if(kiBuild[index] > 0)
                accTotal += buildAdditions[index]!!
        }

        return total == accTotal
    }

    /**
     * Writes held data to the save file for the character
     */
    fun write(charInstance: BaseCharacter){
        charInstance.addNewData(name)
        charInstance.addNewData(effect)
        charInstance.addNewData(mkCost)
        charInstance.addNewData(maintTotal)

        charInstance.addNewData(costPair.first)
        charInstance.addNewData(costPair.second)

        kiBuild.forEach{
            charInstance.addNewData(it)
        }

        buildAdditions.forEach{
            charInstance.addNewData(it)
        }

        charInstance.addNewData(elements.size)
        elements.forEach{
            charInstance.addNewData(it.name)
        }

        charInstance.addNewData(lvl)
    }

    /**
     * Checks if the inputted effect is identical to this one
     *
     * compareTo: effect to check equivalency with
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
}