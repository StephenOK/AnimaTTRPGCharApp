package com.example.animabuilder.character_creation.attributes.combat

import java.io.Serializable

class ResistanceItem: Serializable {
    var base = 0
    var mod = 0
    var special = 0
    var multiplier = 1.0
    var total = 0

    @JvmName("setBase1")
    fun setBase(input: Int){
        base = input
        updateTotal()
    }

    @JvmName("setMod1")
    fun setMod(input: Int){
        mod = input
        updateTotal()
    }

    @JvmName("setSpecial1")
    fun setSpecial(input: Int){
        special += input
        updateTotal()
    }

    @JvmName("setMultiplier1")
    fun setMultiplier(input: Double){
        multiplier = input
        updateTotal()
    }

    fun updateTotal(){
        total = ((base + mod + special) * multiplier).toInt()
    }
}