package com.example.animabuilder.character_creation.attributes.ki_abilities.techniques

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.TechniqueEffect
import java.io.Serializable
import java.util.Collections

/**
 * Holds the data for a technique the character can take
 *
 * name: name of the technique
 * description: details on what the technique does
 * level: level of the technique
 * maintArray: array of maintenance points for the technique
 * givenAbilities: effects the technique utilizes
 */
class Technique(
    var name: String,
    var description: String,
    var level: Int,
    val maintArray: MutableList<Int>,
    val givenAbilities: MutableList<TechniqueEffect>
): Serializable{

    //returns the martial knowledge cost of the technique
    val mkCost = {
        //initialize cost value
        var total = 0

        //calculate cost from each effect
        givenAbilities.forEach{
            total += it.mkCost
        }

        //add points based on level if technique is maintained
        if(isMaintained()){
            total += when(level) {
                2 -> 20
                3 -> 30
                else -> 10
            }
        }

        //return final total
        total
    }

    //determines total maintenance for the technique
    val maintTotal = {
        //initialize output
        var total = 0

        //calculate value from each effect
        givenAbilities.forEach{
            total += it.maintTotal
        }

        //return final total
        total
    }

    //determines the ki needed for each characteristic
    val statSpent = {
        //initialize list of ki build
        val output = mutableListOf(0, 0, 0, 0, 0, 0)

        //get each effect's individual ki build
        givenAbilities.forEach{ability ->
            for(index in 0..5){
                output[index] += ability.kiBuild[index]
            }
        }

        //return final output
        output
    }

    //determines the total accumulation for the technique
    val accTotal = {
        //get the array of ki needed
        val addUp = statSpent()

        //initialize final total
        var total = 0

        //add amount from the ki built
        addUp.forEach{
            total += it
        }

        //add maintenance amount if maintainable
        givenAbilities.forEach{
            if(isMaintained())
                total += it.maintTotal
        }

        //return total
        total
    }

    /**
     * Determines if the stat is maintained based on its maintenance array
     */
    fun isMaintained(): Boolean{
        for(index in 0..5)
            if(maintArray[index] != 0)
                return true

        return false
    }

    /**
     * Checks if there is any build ability in the indicated stat for the whole technique
     *
     * index: characteristic to check for build
     */
    fun hasAccumulation(index: Int): Boolean{
        givenAbilities.forEach{
            if(it.kiBuild[index] != 0)
                return true
        }

        return false
    }

    /**
     * Checks if the values in a given technique are equal to this one
     *
     * compareTo: technique to check equivalency with
     */
    fun equivalentTo(compareTo: Technique): Boolean{
        return compareTo.name == name &&
                compareTo.description == description &&
                compareTo.level == level &&
                compareTo.maintArray == maintArray &&
                listCheck(compareTo.givenAbilities)
    }

    /**
     * Checks that the given ability lists are equal or not
     *
     * compareTo: list of effects to compare with
     */
    fun listCheck(compareTo: List<TechniqueEffect>): Boolean{
        //immediately dismiss if sizes are different
        if(compareTo.size != givenAbilities.size)
            return false

        //remove effects if they are present
        var listCopy = compareTo
        listCopy.forEach{comparison ->
            givenAbilities.forEach{
                if(it.equivalentTo(comparison))
                    listCopy = listCopy - comparison
            }
        }

        //if list is empty, lists are equivalent
        return listCopy.isEmpty()
    }

    /**
     * Checks that a given technique effect can be added to the technique
     *
     * input: effect to add to the technique
     * charMax: amount of points the character can spend on the technique
     */
    fun validEffectAddition(input: TechniqueEffect?, charMax: Int): String?{
        //assert a non-null effect input
        if(input == null)
            return "No Effect Selected"

        //reject if technique has equivalent effect
        if(getAbility(input.name) != null)
            return "Technique is already present"

        //check if able to take Elemental Binding effect
        if(input.name == "Elemental Binding" && input.elements != listOf<Element>()){
            //for each currently taken effect
            givenAbilities.forEach{effect ->
                //initialize effect's valid binding
                var isBound = false

                //for each element to bind to
                input.elements.forEach{
                    //check that the effect has at least one of them
                    if(effect.elements.contains(it))
                        isBound = true
                }

                //fail if effect is invalid to bind to
                if(!isBound)
                    return "Cannot currently bind to selected element(s)"
            }
        }

        //check that effect meets Elemental Binding requirements
        if(!checkElementalBinding(input))
            return "Cannot take because of Elemental Binding"

        //reject if incompatible level
        if(input.lvl > level)
            return "Cannot take an effect of that level"

        //determine maximum MK for the technique
        val max = when(level){
            1 -> 50
            2 -> 100
            3 -> 200
            else -> 0
        }

        //reject if maximum cost exceeded by effect
        if(mkCost() + input.mkCost > max)
            return "Maximum technique cost exceeded"

        //reject if total technique cost exceeds character's ability
        if(mkCost() + input.mkCost > charMax)
            return "Technique would exceed character's limit"

        //return valid input indication
        return null
    }

    /**
     * Finds the desired effect in the technique's list
     *
     * retrieve: name of the effect to search for
     */
    fun getAbility(retrieve: String): TechniqueEffect?{
        givenAbilities.forEach{
            if(it.name == retrieve)
                return it
        }

        return null
    }

    /**
     * Determines if the effect complies with elemental binding rules
     *
     * input: effect to check
     */
    fun checkElementalBinding(input: TechniqueEffect): Boolean{
        //find binding if one present
        val binding = getAbility("Elemental Binding")

        //if binding is present
        if(binding != null){
            //get binding's elements
            val boundElements = binding.elements

            //initialize boolean
            var trueCheck = false

            //determine that the effect has at least one of the necessary elements
            boundElements.forEach{
                if(input.elements.contains(it))
                    trueCheck = true
            }

            return trueCheck
        }

        return true
    }

    /**
     * Reorganizes the effect list in case the primary effect is removed and the next effect is not
     * a valid primary ability
     */
    fun fixPrimaryAbility(){
        //initialize boolean for only disadvantages left
        var onlyDis = true

        //determine there is at least one positive effect left for the technique
        givenAbilities.forEach{
            if(!it.elements.contains(Element.Free))
                onlyDis = false
        }

        //clear abilities if only disadvantages are left
        if(onlyDis)
            givenAbilities.clear()

        //perform action as long as list is not empty
        else if(givenAbilities.isNotEmpty()){
            //while pointer is within effect list
            for(index in 0 .. givenAbilities.size){
                //once pointer finds positive effect
                if(!givenAbilities[index].elements.contains(Element.Free)) {
                    //initialize replacement ki build list
                    val replaceList = mutableListOf(0, 0, 0, 0, 0, 0)

                    //change effect's ki build to reflect new primary cost
                    replaceList[givenAbilities[index].buildAdditions.indexOf(0)] = givenAbilities[index].costPair.first
                    for(kiIndex in 0..5)
                        givenAbilities[index].kiBuild[kiIndex] = replaceList[kiIndex]

                    //swap effect position if not already at front
                    if(index != 0)
                        Collections.swap(givenAbilities, 0, index)

                    //manually end loop
                    break
                }
            }
        }
    }

    /**
     * Writes technique data to the save file
     */
    fun write(charInstance: BaseCharacter){
        charInstance.addNewData(name)
        charInstance.addNewData(description)
        charInstance.addNewData(level)
        maintArray.forEach{charInstance.addNewData(it)}

        charInstance.addNewData(givenAbilities.size)
        givenAbilities.forEach{
            it.write(charInstance)
        }
    }
}