package com.example.animabuilder.ListenerImplementations

import android.text.InputFilter
import android.text.Spanned
import java.lang.NumberFormatException

class InputFilterMinMax : InputFilter {
    private var min: Int
    private var max: Int

    constructor(minIn: Int, maxIn: Int) {
        min = minIn
        max = maxIn
    }

    constructor(minIn: String, maxIn: String) {
        min = minIn.toInt()
        max = maxIn.toInt()
    }

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dStart: Int,
        dEnd: Int
    ): CharSequence? {
        try {
            var newVal = (dest.toString().substring(0, dStart)
                    + dest.toString().substring(dEnd, dest.toString().length))
            newVal = newVal.substring(0, dStart) + source.toString() + newVal.substring(
                dStart,
                newVal.length
            )
            val input = newVal.toInt()
            if (isInRange(min, max, input)) return null
        } catch (nfe: NumberFormatException) {
        }
        return ""
    }

    private fun isInRange(min: Int, max: Int, input: Int): Boolean {
        return input >= min && input <= max
    }
}