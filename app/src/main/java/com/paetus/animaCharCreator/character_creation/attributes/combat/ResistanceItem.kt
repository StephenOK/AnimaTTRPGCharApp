package com.paetus.animaCharCreator.character_creation.attributes.combat

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf

/**
 * Item that holds the data on one of a character's resistances.
 */
class ResistanceItem{
    //initialize resistance's base
    val base = mutableIntStateOf(20)

    //initialize resistance's modifier value
    val mod = mutableIntStateOf(0)

    //initialize special bonuses to this resistance
    val special = mutableIntStateOf(0)

    //initialize multiplier to the bonus
    val multiplier = mutableDoubleStateOf(1.0)

    //initialize resistance total
    val total = mutableIntStateOf(20)

    /**
     * Sets the base resistance to the user's stat.
     *
     * @param newBase value to set the base to
     */
    fun setBase(newBase: Int){
        base.intValue = newBase
        updateTotal()
    }

    /**
     * Sets the modifier to this stat.
     *
     * @param newMod value to set the modifier to
     */
    fun setMod(newMod: Int){
        mod.intValue = newMod
        updateTotal()
    }

    /**
     * Applies any bonus to this stat.
     *
     * @param specChange bonus value to apply to this stat.
     */
    fun setSpecial(specChange: Int){
        special.intValue += specChange
        updateTotal()
    }

    /**
     * Sets the multiplier for this stat's value.
     *
     * @param multVal multiplier to apply to this stat.
     */
    fun setMultiplier(multVal: Double){
        multiplier.doubleValue = multVal
        updateTotal()
    }

    /**
     * Updates the total resistance for this particular item.
     */
    private fun updateTotal(){
        total.intValue = ((base.intValue + mod.intValue + special.intValue) * multiplier.doubleValue).toInt()
    }
}