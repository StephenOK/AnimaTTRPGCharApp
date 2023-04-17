package com.example.animabuilder.character_creation.equipment.general_goods

/**
 * Category of items that a character may purchase to their inventory.
 *
 * @param qualityInput list of item quality options if such options are available
 */
open class GeneralCategory(val qualityInput: List<QualityModifier>?){
    //initialize list of available items in this category
    val itemsAvailable = mutableListOf<GeneralEquipment>()

    /**
     * Finds a piece of equipment based on the inputted qualities.
     *
     * @param name distinguishing item for the equipment
     * @param quality index of the item's quality, if one is available
     * @return either a copy of the found equipment with the indicated quality or a null flag
     */
    fun findEquipment(name: String, quality: Int?): GeneralEquipment?{
        //look at each available item
        itemsAvailable.forEach{
            //item name found
            if(it.name == name){
                //determine cost multiplier
                val multiplier =
                    if(qualityInput != null) qualityInput[quality!!].modifier
                    else 1.0

                //create and return appropriate item
                return GeneralEquipment(
                    it.name,
                    it.baseCost * multiplier,
                    it.coinType,
                    it.weight,
                    it.availability,
                    quality
                )
            }
        }

        //no matching name found
        return null
    }
}