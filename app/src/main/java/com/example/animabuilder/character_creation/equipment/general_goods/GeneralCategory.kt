package com.example.animabuilder.character_creation.equipment.general_goods

import java.io.Serializable

/**
 * Category of items that a character may purchase to their inventory.
 *
 * @param qualityInput list of item quality options if such options are available
 */
open class GeneralCategory(val qualityInput: List<QualityModifier>?): Serializable {
    //initialize list of available items in this category
    val itemsAvailable = mutableListOf<GeneralEquipment>()

    /**
     * Finds a piece of equipment based on the inputted qualities.
     *
     * @param name distinguishing item for the equipment
     * @param cost amount spent on the item
     * @return the exact item held in inventory, a copy of that item with a different cost, or
     * an empty flag indicating no item matching the inputted name
     */
    fun findEquipment(name: String, cost: Int): GeneralEquipment?{
        //look at each available item
        itemsAvailable.forEach{
            //item name found
            if(it.name == name){
                //return self if costs match
                return if(it.baseCost == cost)
                    it

                //return copy if costs don't match
                else{
                    GeneralEquipment(
                        it.name,
                        cost,
                        it.coinType,
                        it.weight,
                        it.availability
                    )
                }
            }
        }

        //no matching name found
        return null
    }
}