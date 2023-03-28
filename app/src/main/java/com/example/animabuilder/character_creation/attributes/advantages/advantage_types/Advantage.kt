package com.example.animabuilder.character_creation.attributes.advantages.advantage_types

import java.io.Serializable

/**
 * Object that holds information on an advantage a character can take.
 *
 * @param name string name of the advantage
 * @param description details of the advantage
 * @param effect further details of the advantage's effects, if available
 * @param restriction details on the requirements needed for this advantage
 * @param special additional details on taking this advantage
 * @param options items this advantage can be applied to
 * @param picked index of options taken in this instance
 * @param cost list of optional prices for acquisition
 * @param pickedCost cost taken for this particular advantage
 * @param onTake function to run on advantage's acquisition
 * @param onRemove function to run on advantage's removal
 */
open class Advantage(
    val name: String,
    val description: String,
    val effect: String?,
    val restriction: String?,
    val special: String?,
    val options: List<String>?,
    val picked: Int?,
    val cost: List<Int>,
    val pickedCost: Int,
    val onTake: ((Int?, Int) -> Unit)?,
    val onRemove: ((Int?, Int) -> Unit)?
): Serializable{
    /**
     * Checks that another advantage is exactly the same as this one.
     *
     * @param comparison item to compare this to
     * @return Equivalency between these two item
     */
    fun isEquivalent(comparison: Advantage): Boolean{
        return name == comparison.name &&
                description == comparison.description &&
                effect == comparison.effect &&
                restriction == comparison.restriction &&
                special == comparison.special &&
                picked == comparison.picked &&
                cost == comparison.cost &&
                pickedCost == comparison.pickedCost &&
                onTake == comparison.onTake &&
                onRemove == comparison.onRemove
    }
}