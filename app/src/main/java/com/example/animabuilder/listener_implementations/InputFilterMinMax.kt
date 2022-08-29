package com.example.animabuilder.listener_implementations

import android.text.InputFilter
import android.text.Spanned
import java.lang.NumberFormatException

/**
 * Sets a numerical minimum and maximum for the attached item
 */

class InputFilterMinMax (minIn: Int, maxIn: Int) : InputFilter {
    private var min: Int = minIn
    private var max: Int = maxIn

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dStart: Int,
        dEnd: Int
    ): CharSequence? {

        try {
            //retrieve integer value of input
            var newVal = (dest.toString().substring(0, dStart)
                    + dest.toString().substring(dEnd, dest.toString().length))
            newVal = newVal.substring(0, dStart) + source.toString() + newVal.substring(
                dStart,
                newVal.length
            )
            val input = newVal.toInt()

            //check if it meets restrictions
            if (isInRange(min, max, input)) return null
        } catch (nfe: NumberFormatException) {
        }
        return ""
    }

    //returns if a given integer is between the implemented minimum and maximums
    private fun isInRange(min: Int, max: Int, input: Int): Boolean {
        return input in min..max
    }
}