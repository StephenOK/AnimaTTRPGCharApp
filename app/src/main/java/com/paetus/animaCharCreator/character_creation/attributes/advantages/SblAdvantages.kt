package com.paetus.animaCharCreator.character_creation.attributes.advantages

import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage

class SblAdvantages(
    val sblChar: SblChar
): AdvantageRecord(sblChar){
    /**
     * Method used to add an advantage or disadvantage to the character
     *
     * @param advantageBase base version of the advantage to add
     * @param taken index of the chosen item if applicable
     * @param takenCost index of the chosen cost of the advantage
     * @param multTaken list of taken options if applicable to the advantage
     * @return either an error message for a failed addition or a null item for a successful addition
     */
    override fun acquireAdvantage(
        advantageBase: Advantage,
        taken: Int?,
        takenCost: Int,
        multTaken: List<Int>?
    ): Int? {
        //apply advantage and get result
        val output = super.acquireAdvantage(advantageBase, taken, takenCost, multTaken)

        //if advantage successfully applied
        if(output == null) {
            //create a copy of the applied advantage
            val advantageCopy = Advantage(
                saveTag = advantageBase.saveTag,
                name = advantageBase.name,
                description = advantageBase.description,
                effect = advantageBase.effect,
                restriction = advantageBase.restriction,
                special = advantageBase.special,
                options = advantageBase.options,
                picked = taken,
                multPicked = multTaken,
                cost = advantageBase.cost,
                pickedCost = takenCost,
                onTake = advantageBase.onTake,
                onRemove = advantageBase.onRemove
            )

            //apply it to the level 0 record
            sblChar.charRefs[0]!!.advantageRecord.takenAdvantages.add(advantageCopy)
        }

        //give output
        return output
    }

    /**
     * Removes an advantage from the character.
     *
     * @param advantage item to be removed
     */
    override fun removeAdvantage(advantage: Advantage) {
        //attempt to remove advantage
        super.removeAdvantage(advantage)

        //remove item from the level 0 record
        sblChar.charRefs[0]!!.advantageRecord.takenAdvantages.remove(element = advantage)
    }
}