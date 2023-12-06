package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect

import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.writeDataTo
import java.io.ByteArrayOutputStream

/**
 * Effect to apply to a technique.
 *
 * @param data constant values applied to this effect
 * @param kiBuild ki build distribution for this effect
 * @param buildAdditions additional ki needed for this index of cost
 * @param elements associated elements with this effect
 */
class TechniqueEffect(
    val data: TechniqueTableData,
    val kiBuild: MutableList<Int>,
    val buildAdditions: List<Int?>,
    var elements: MutableList<Element>
) {
    /**
     * Determines if the ki build of this technique is legal.
     *
     * @param isPrimary determines if the effect is the primary one
     * @return true if build is legal
     */
    fun checkBuild(isPrimary: Boolean): Boolean{
        //initialize build total
        var total = 0

        //determine build needed based on if it's primary
        var accTotal =
            if(isPrimary) data.primaryCost
            else data.secondaryCost

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
        return data.isEquivalent(compareTo.data) &&
                compareTo.kiBuild == kiBuild &&
                compareTo.elements == elements
    }

    /**
     * Write the data in this object to file.
     *
     * @param byteArray output stream to write the data to
     */
    fun write(byteArray: ByteArrayOutputStream){
        data.write(byteArray = byteArray)

        kiBuild.forEach{buildData -> writeDataTo(writer = byteArray, input = buildData)}
        buildAdditions.forEach{buildAdditions -> writeDataTo(writer = byteArray, input = buildAdditions)}

        writeDataTo(byteArray, elements.size)
        elements.forEach{element -> writeDataTo(writer = byteArray, input = element.name)}
    }
}