package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base

import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueEffect
import com.paetus.animaCharCreator.writeDataTo
import java.io.ByteArrayOutputStream
import java.util.Collections

/**
 * Holds the data for a technique the character can take
 *
 * @param name name of the technique
 * @param description details on what the technique does
 * @param level level of the technique
 * @param maintArray array of maintenance points for the technique
 * @param givenAbilities effects the technique utilizes
 */
class CustomTechnique(): TechniqueBase(){
    val name = mutableStateOf("")
    val isPublic = mutableStateOf(true)
    val fileOrigin = mutableStateOf("")
    val description = mutableStateOf("")

    fun togglePublic(){isPublic.value = !isPublic.value}
    fun setFileOrigin(input: String){fileOrigin.value = input}

    /**
     * Checks that a given technique effect can be added to the technique
     *
     * @param input effect to add to the technique
     * @param charMax amount of points the character can spend on the technique
     * @return error message that is the reason of failure, if addition failed
     */
    fun validEffectAddition(input: TechniqueEffect?, charMax: Int): Int?{
        //assert a non-null effect input
        if(input == null)
            return R.string.emptyEffectNotice

        //reject if technique has equivalent effect
        if(!legalAddition(input))
            return R.string.existingEffect

        //check if able to take Elemental Binding effect
        if(input.data.name == 35 && input.elements != listOf<Element>()){
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
                    return R.string.elementConflict
            }
        }

        //check that effect meets Elemental Binding requirements
        if(!checkElementalBinding(input))
            return R.string.bindingRestriction

        //reject if incompatible level
        if(input.data.level > level.intValue)
            return R.string.techLevelRestriction

        //determine maximum MK for the technique
        val max = when(level.intValue){
            1 -> 50
            2 -> 100
            3 -> 200
            else -> 0
        }

        //reject if maximum cost exceeded by effect
        if(mkCost() + input.data.mkCost > max)
            return R.string.techCostRestriction

        //reject if total technique cost exceeds character's ability
        if(mkCost() + input.data.mkCost > charMax)
            return R.string.techMKRestriction

        //return valid input indication
        return null
    }

    /**
     * Checks that the inputted effect can be added to the technique.
     *
     * @param input effect to be added
     * @return true if valid addition
     */
    fun legalAddition(input: TechniqueEffect): Boolean{
        givenAbilities.forEach{
            if(input.data.name == it.data.name){
                if(!((input.data.primaryCost == input.data.secondaryCost) xor
                            (it.data.primaryCost == it.data.secondaryCost)))
                    return false
            }
        }

        return true
    }

    /**
     * Determines if the effect complies with elemental binding rules.
     *
     * @param input effect to check
     * @return true if it complies with elemental binding
     */
    fun checkElementalBinding(input: TechniqueEffect): Boolean{
        //find binding if one present
        val binding =
            if(getAbility(35, 0, 0, -15) != null) getAbility(35, 0, 0, -10)
            else getAbility(35, 0, 0, -10)

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
     * a valid primary ability.
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
        else{
            //while pointer is within effect list
            for(index in 0 until givenAbilities.size){
                //once pointer finds positive effect
                if(!givenAbilities[index].elements.contains(Element.Free)) {
                    //initialize replacement ki build list
                    val replaceList = mutableListOf(0, 0, 0, 0, 0, 0)

                    //change effect's ki build to reflect new primary cost
                    replaceList[givenAbilities[index].buildAdditions.indexOf(0)] = givenAbilities[index].data.primaryCost
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
    override fun write(byteArray: ByteArrayOutputStream){
        //write technique's name
        writeDataTo(byteArray, name.value)

        writeDataTo(byteArray, isPublic.value)

        writeDataTo(byteArray, fileOrigin.value)

        //write technique's description
        writeDataTo(byteArray, description.value)

        //write technique's level
        writeDataTo(byteArray, level.intValue)

        //write maintenance array
        maintArray.forEach{writeDataTo(byteArray, it)}

        //write number of effects
        writeDataTo(byteArray, givenAbilities.size)

        //write data for each effect
        givenAbilities.forEach{
            it.write(byteArray)
        }
    }

    constructor(
        name: String,
        isPublic: Boolean,
        fileOrigin: String,
        description: String,
        level: Int,
        maintArray: MutableList<Int>,
        givenAbilities: MutableList<TechniqueEffect>
    ) : this() {
        this.name.value = name
        this.isPublic.value = isPublic
        this.fileOrigin.value = fileOrigin
        this.description.value = description
        this.level.intValue = level
        for(index in 0..5)
            this.maintArray[index] = maintArray[index]
        this.givenAbilities.addAll(givenAbilities)
    }
}