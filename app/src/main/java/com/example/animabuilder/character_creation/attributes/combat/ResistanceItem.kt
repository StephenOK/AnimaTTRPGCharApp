package com.example.animabuilder.character_creation.attributes.combat

/**
 * Item that holds the data on one of a character's resistances.
 */
class ResistanceItem{
    //initialize resistance's base
    var base = 0

    //initialize resistance's modifier value
    var mod = 0

    //initialize special bonuses to this resistance
    var special = 0

    //initialize multiplier to the bonus
    var multiplier = 1.0

    //initialize resistance total
    var total = 0

    /**
     * Sets the base resistance to the user's stat.
     *
     * @param input value to set the base to
     */
    @JvmName("setBase1")
    fun setBase(input: Int){
        base = input
        updateTotal()
    }

    /**
     * Sets the modifier to this stat.
     *
     * @param input value to set the modifier to
     */
    @JvmName("setMod1")
    fun setMod(input: Int){
        mod = input
        updateTotal()
    }

    /**
     * Applies any bonus to this stat.
     *
     * @param input bonus value to apply to this stat.
     */
    @JvmName("setSpecial1")
    fun setSpecial(input: Int){
        special += input
        updateTotal()
    }

    /**
     * Sets the multiplier for this stat's value.
     *
     * @param input multiplier to apply to this stat.
     */
    @JvmName("setMultiplier1")
    fun setMultiplier(input: Double){
        multiplier = input
        updateTotal()
    }

    /**
     * Updates the total resistance for this particular item.
     */
    fun updateTotal(){
        total = ((base + mod + special) * multiplier).toInt()
    }
}