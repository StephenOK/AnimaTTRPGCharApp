package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base

import androidx.compose.runtime.mutableIntStateOf
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueEffect
import java.io.ByteArrayOutputStream

/**
 * Base item for both prebuilt and custom techniques.
 */
open class TechniqueBase(){
    //initialize the technique's level
    val level = mutableIntStateOf(1)

    //initialize the technique's maintenance values
    val maintArray = mutableListOf(0, 0, 0, 0, 0, 0)

    //initialize the technique's held effects
    val givenAbilities = mutableListOf<TechniqueEffect>()

    /**
     * Sets the technique's level to the indicated value.
     *
     * @param lvlInput new value to set as the level
     */
    fun setLevel(lvlInput: Int){level.intValue = lvlInput}

    /**
     * Determines the total cost of the technique.
     *
     * @return total cost of the technique
     */
    fun mkCost(): Int {
        //initialize cost value
        var total = 0

        //calculate cost from each effect
        givenAbilities.forEach{effect ->
            total += effect.data.mkCost
        }

        //add points based on level if technique is maintained
        if(isMaintained()){
            total += when(level.intValue) {
                2 -> 20
                3 -> 30
                else -> 10
            }
        }

        //return final total
        return total
    }

    /**
     * Determines total maintenance for the technique.
     *
     * @return final maintenance cost of the technique
     */
    fun maintTotal(): Int {
        //initialize output
        var total = 0

        //calculate value from each effect
        givenAbilities.forEach{effect ->
            total += effect.data.maintCost
        }

        //return final total
        return total
    }

    /**
     * Determines the ki needed for each characteristic.
     *
     * @return array of ki points needed respective to each primary characteristic
     */
    fun statSpent(): MutableList<Int> {
        //initialize list of ki build
        val output = mutableListOf(0, 0, 0, 0, 0, 0)

        //get each effect's individual ki build
        givenAbilities.forEach{ability ->
            for(index in 0..5){
                output[index] += ability.kiBuild[index]
            }
        }

        //return final output
        return output
    }

    /**
     * Determines the total accumulation for the technique.
     *
     * @return total accumulation needed for the technique
     */
    fun accTotal(): Int {
        //initialize final total
        var total = 0

        //add amount from the ki built
        statSpent().forEach{kiBuild ->
            total += kiBuild
        }

        //add maintenance amount if maintainable
        if(isMaintained()){
            givenAbilities.forEach { effect ->
                total += effect.data.maintCost
            }
        }

        //return total
        return total
    }

    /**
     * Determines if the stat is maintained based on its maintenance array.
     *
     * @return true if technique is maintainable
     */
    fun isMaintained(): Boolean{
        for(index in 0..5)
        //return true if any one maintenance array item is greater than 0
            if(maintArray[index] != 0)
                return true

        return false
    }

    /**
     * Determines if the inputted maintenance array is legal for the technique.
     *
     * @return true if maintenance array is currently legal
     */
    fun checkMaintenance(): Boolean{
        //return true if array is currently empty
        if(!isMaintained())
            return true

        //initialize maintenance total
        var total = 0

        //sum up maintenance items
        for(index in 0..5){
            total += maintArray[index]
        }

        //check that input matches required total
        return total == maintTotal()
    }

    /**
     * Checks if there is any build ability in the indicated stat for the whole technique
     *
     * @param buildIndex characteristic to check for build
     * @return true if any build needed for the indicated characteristic
     */
    fun hasAccumulation(buildIndex: Int): Boolean{
        //check each ki build for any inputted ki build
        givenAbilities.forEach{
            if(it.kiBuild[buildIndex] != 0)
                return true
        }

        return false
    }

    /**
     * Checks that the given ability lists are equal or not.
     *
     * @param compareTo list of effects to compare with
     * @return true if lists are equivalent
     */
    private fun listCheck(compareTo: List<TechniqueEffect>): Boolean{
        //immediately dismiss if sizes are different
        if(compareTo.size != givenAbilities.size)
            return false

        //remove effects if they are present
        var listCopy = compareTo
        listCopy.forEach{effect ->
            givenAbilities.forEach{heldEffect ->
                if(heldEffect.equivalentTo(compareTo = effect))
                    listCopy = listCopy - effect
            }
        }

        //if list is empty, lists are equivalent
        return listCopy.isEmpty()
    }

    /**
     * Determines if the technique's ki builds are all legal.
     *
     * @return true if all builds qualify
     */
    fun checkBuilds(): Boolean{
        givenAbilities.forEach{heldEffect ->
            val isPrimary = givenAbilities[0] == heldEffect
            if(!heldEffect.checkBuild(isPrimary = isPrimary)) return false
        }

        return true
    }

    /**
     * Finds the desired effect in the technique's list.
     *
     * @param name name of the effect to search for
     * @param primeCost primary cost of the desired effect
     * @param secondCost secondary cost of the desired effect
     * @param mkCost martial knowledge cost of the effect
     * @return the sought for technique, if found
     */
    fun getAbility(
        name: Int,
        primeCost: Int,
        secondCost: Int,
        mkCost: Int
    ): TechniqueEffect?{
        //check each held ability for an exact match
        (givenAbilities).forEach{effect ->
            if(effect.data.name == name &&
                    effect.data.primaryCost == primeCost &&
                    effect.data.secondaryCost == secondCost &&
                    effect.data.mkCost == mkCost)
                return effect
        }

        //notify of no match found
        return null
    }

    /**
     * Checks if the values in a given technique are equal to this one.
     *
     * @param compareTo technique to check equivalency with
     * @return true if technique matches
     */
    fun equivalentTo(compareTo: TechniqueBase): Boolean{
        return compareTo.level.intValue == level.intValue &&
                compareTo.maintArray == maintArray &&
                listCheck(compareTo = compareTo.givenAbilities)
    }

    /**
     * Open function for writing this technique to file.
     */
    open fun write(byteArray: ByteArrayOutputStream){}

    /**
     * Constructor with the values for this item given.
     *
     * @param level strength of this technique
     * @param maintArray maintenance values for this technique
     * @param givenAbilities effects this technique possesses
     */
    constructor(
        level: Int,
        maintArray: MutableList<Int>,
        givenAbilities: MutableList<TechniqueEffect>
    ) : this() {
        setLevel(lvlInput = level)

        for(index in 0..5)
            this.maintArray[index] = maintArray[index]

        this.givenAbilities.addAll(givenAbilities)
    }
}