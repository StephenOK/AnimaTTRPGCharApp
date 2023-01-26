package com.example.animabuilder.character_creation.attributes.advantages

import java.io.Serializable

open class Advantage(
    val name: String?,
    val description: String,
    val effect: String?,
    val restriction: String?,
    val special: String?,
    val picked: Int?,
    val cost: List<Int>,
    val pickedCost: Int,
    val onTake: ((Int?, Int) -> Unit)?,
    val onRemove: ((Int?, Int) -> Unit)?
): Serializable{
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