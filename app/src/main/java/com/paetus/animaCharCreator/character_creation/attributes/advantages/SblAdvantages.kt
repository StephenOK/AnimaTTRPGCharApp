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
        if(output == null)
            //implement advantage to all character records
            sblChar.levelLoop(
                endLevel = 20
            ){character ->
                character.advantageRecord.acquireAdvantage(advantageBase, taken, takenCost, multTaken)
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

        //save value to the level 0 record
        sblChar.charRefs[0]!!.advantageRecord.removeAdvantage(advantage)
    }
}