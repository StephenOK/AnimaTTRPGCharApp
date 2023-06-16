package com.paetus.animaCharCreator.character_creation.attributes.combat

import androidx.compose.runtime.mutableStateOf

/**
 * Item that holds the data on one of a character's resistances.
 */
class ResistanceItem{
    //initialize resistance's base
    val base = mutableStateOf(0)

    //initialize resistance's modifier value
    val mod = mutableStateOf(0)

    //initialize special bonuses to this resistance
    val special = mutableStateOf(0)

    //initialize multiplier to the bonus
    val multiplier = mutableStateOf(1.0)

    //initialize resistance total
    val total = mutableStateOf(0)

    /**
     * Sets the base resistance to the user's stat.
     *
     * @param input value to set the base to
     */
    @JvmName("setBase1")
    fun setBase(input: Int){
        base.value = input
        updateTotal()
    }

    /**
     * Sets the modifier to this stat.
     *
     * @param input value to set the modifier to
     */
    @JvmName("setMod1")
    fun setMod(input: Int){
        mod.value = input
        updateTotal()
    }

    /**
     * Applies any bonus to this stat.
     *
     * @param input bonus value to apply to this stat.
     */
    @JvmName("setSpecial1")
    fun setSpecial(input: Int){
        special.value += input
        updateTotal()
    }

    /**
     * Sets the multiplier for this stat's value.
     *
     * @param input multiplier to apply to this stat.
     */
    @JvmName("setMultiplier1")
    fun setMultiplier(input: Double){
        multiplier.value = input
        updateTotal()
    }

    /**
     * Updates the total resistance for this particular item.
     */
    fun updateTotal(){
        total.value = ((base.value + mod.value + special.value) * multiplier.value).toInt()
    }
}