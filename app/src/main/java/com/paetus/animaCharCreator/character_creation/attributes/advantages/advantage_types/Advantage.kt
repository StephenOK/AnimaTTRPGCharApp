package com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types

/**
 * Object that holds information on an advantage a character can take.
 *
 * @param saveTag identifier for the save file item
 * @param name displayed name of the advantage
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
    val saveTag: String,
    val name: Int,
    val description: Int,
    val effect: Int?,
    val restriction: Int?,
    val special: Int?,
    val options: Int?,
    val picked: Int?,
    val multPicked: List<Int>?,
    val cost: List<Int>,
    val pickedCost: Int,
    val onTake: ((Int?, Int) -> Unit)?,
    val onRemove: ((Int?, Int) -> Unit)?
){
    /**
     * Checks that another advantage is exactly the same as this one.
     *
     * @param comparison item to compare this to
     * @return Equivalency between these two item
     */
    fun isEquivalent(comparison: Advantage): Boolean{
        return saveTag == comparison.saveTag &&
                name == comparison.name &&
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