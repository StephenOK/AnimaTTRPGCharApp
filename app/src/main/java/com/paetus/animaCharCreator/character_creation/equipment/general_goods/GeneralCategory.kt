package com.paetus.animaCharCreator.character_creation.equipment.general_goods

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
     * @param equipName distinguishing item for the equipment
     * @param quality index of the item's quality, if one is available
     * @return either a copy of the found equipment with the indicated quality or a null flag
     */
    fun findEquipment(
        equipName: String,
        quality: Int?
    ): GeneralEquipment?{
        //look at each available item
        itemsAvailable.forEach{equipment ->
            //item name found
            if(equipment.saveName == equipName){
                //determine cost multiplier
                val multiplier =
                    if(qualityInput != null) qualityInput[quality!!].modifier
                    else 1.0

                //create and return appropriate item
                return GeneralEquipment(
                    saveName = equipment.saveName,
                    name = equipment.name,
                    baseCost = equipment.baseCost * multiplier,
                    coinType = equipment.coinType,
                    weight = equipment.weight,
                    availability = equipment.availability,
                    currentQuality = quality
                )
            }
        }

        //no matching name found
        return null
    }
}