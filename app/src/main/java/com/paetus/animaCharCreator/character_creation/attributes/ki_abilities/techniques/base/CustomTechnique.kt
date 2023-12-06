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
 */
class CustomTechnique(): TechniqueBase(){
    //initialize name of the technique
    val name = mutableStateOf(value = "")

    //initialize public availability of technique
    val isPublic = mutableStateOf(value = true)

    //initialize character filename that created this technique
    val fileOrigin = mutableStateOf(value = "")

    //initialize description of this technique
    val description = mutableStateOf(value = "")

    /**
     * Toggles whether other characters can access this technique.
     */
    fun togglePublic(){isPublic.value = !isPublic.value}

    /**
     * Sets the creating character for this technique.
     *
     * @param filename name of the creating character's file
     */
    fun setFileOrigin(filename: String){fileOrigin.value = filename}

    /**
     * Checks that a given technique effect can be added to the technique
     *
     * @param effect effect to add to the technique
     * @param charMax amount of points the character can spend on the technique
     * @return error message that is the reason of failure, if addition failed
     */
    fun validEffectAddition(
        effect: TechniqueEffect?,
        charMax: Int
    ): Int?{
        //assert a non-null effect input
        if(effect == null)
            return R.string.emptyEffectNotice

        //reject if technique has equivalent effect
        if(!legalAddition(effect = effect))
            return R.string.existingEffect

        //check if able to take Elemental Binding effect
        if(effect.data.name == 35 && effect.elements != listOf<Element>()){
            //for each currently taken effect
            givenAbilities.forEach{heldEffect ->
                //initialize effect's valid binding
                var isBound = false

                //for each element to bind to
                heldEffect.elements.forEach{element ->
                    //check that the effect has at least one of them
                    if(heldEffect.elements.contains(element = element))
                        isBound = true
                }

                //fail if effect is invalid to bind to
                if(!isBound)
                    return R.string.elementConflict
            }
        }

        //check that effect meets Elemental Binding requirements
        if(!checkElementalBinding(effect = effect))
            return R.string.bindingRestriction

        //reject if incompatible level
        if(effect.data.level > level.intValue)
            return R.string.techLevelRestriction

        //determine maximum MK for the technique
        val max = when(level.intValue){
            1 -> 50
            2 -> 100
            3 -> 200
            else -> 0
        }

        //reject if maximum cost exceeded by effect
        if(mkCost() + effect.data.mkCost > max)
            return R.string.techCostRestriction

        //reject if total technique cost exceeds character's ability
        if(mkCost() + effect.data.mkCost > charMax)
            return R.string.techMKRestriction

        //return valid input indication
        return null
    }

    /**
     * Checks that the inputted effect can be added to the technique.
     *
     * @param effect effect to be added
     * @return true if valid addition
     */
    private fun legalAddition(effect: TechniqueEffect): Boolean{
        givenAbilities.forEach{heldEffect ->
            if(effect.data.name == heldEffect.data.name){
                if(!((effect.data.primaryCost == effect.data.secondaryCost) xor
                            (heldEffect.data.primaryCost == heldEffect.data.secondaryCost)))
                    return false
            }
        }

        return true
    }

    /**
     * Determines if the effect complies with elemental binding rules.
     *
     * @param effect item to check
     * @return true if it complies with elemental binding
     */
    private fun checkElementalBinding(effect: TechniqueEffect): Boolean{
        //find binding if one present
        val binding =
            if(getAbility(name = 35, primeCost = 0, secondCost = 0, mkCost = -15) != null)
                getAbility(
                    name = 35,
                    primeCost = 0,
                    secondCost = 0,
                    mkCost = -10
                )
            else
                getAbility(name = 35, primeCost = 0, secondCost = 0, mkCost = -10)

        //if binding is present
        if(binding != null){
            //get binding's elements
            val boundElements = binding.elements

            //initialize boolean
            var trueCheck = false

            //determine that the effect has at least one of the necessary elements
            boundElements.forEach{element ->
                if(effect.elements.contains(element))
                    trueCheck = true
            }

            //return found state of this effect's element
            return trueCheck
        }

        //return true if no binding found
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
        givenAbilities.forEach{heldEffect ->
            if(!heldEffect.elements.contains(Element.Free))
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
     * Writes technique data to the save file.
     *
     * @param byteArray output stream to write the data to
     */
    override fun write(byteArray: ByteArrayOutputStream){
        //write technique's name
        writeDataTo(writer = byteArray, input = name.value)

        //write techniques public availability
        writeDataTo(writer = byteArray, input = isPublic.value)

        //write creating character
        writeDataTo(writer = byteArray, input = fileOrigin.value)

        //write technique's description
        writeDataTo(writer = byteArray, input = description.value)

        //write technique's level
        writeDataTo(writer = byteArray, input = level.intValue)

        //write maintenance array
        maintArray.forEach{statMaint -> writeDataTo(writer = byteArray, input = statMaint)}

        //write number of effects
        writeDataTo(writer = byteArray, input = givenAbilities.size)

        //write data for each effect
        givenAbilities.forEach{heldEffect ->
            heldEffect.write(byteArray = byteArray)
        }
    }

    /**
     * Constructs a custom technique with the indicated values.
     * @param name name of the technique
     * @param isPublic whether this technique is open to other created characters
     * @param fileOrigin character this technique is created for
     * @param description details on what the technique does
     * @param level level of the technique
     * @param maintArray array of maintenance points for the technique
     * @param givenAbilities effects the technique utilizes
     */
    constructor(
        name: String,
        isPublic: Boolean,
        fileOrigin: String,
        description: String,
        level: Int,
        maintArray: MutableList<Int>,
        givenAbilities: MutableList<TechniqueEffect>
    ): this() {
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