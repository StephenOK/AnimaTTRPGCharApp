package com.example.animabuilder.listener_implementations

import android.content.Context
import android.widget.EditText
import android.widget.TextView
import android.text.TextWatcher
import android.text.Editable
import com.example.animabuilder.R
import java.util.function.Consumer
import java.util.function.Supplier

/**
 * Specialized TextWatcher for inputting a character's primary characteristic
 */

class CharacteristicInput(
    private var userInput: EditText, private var modOutput: TextView, private var valSetter: Consumer<Int>,
    private var valGetter: Supplier<Int>, private var pageOf: Context
) : TextWatcher {

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    override fun afterTextChanged(editable: Editable) {
        applyModVal()
    }

    /**
     * Sets the character's indicated value to the input value and alters the indicated mod display
     * to the corresponding value
     */
    private fun applyModVal() {

        //if integer value given
        if (userInput.text.toString() != "") {
            //change the indicated value in the character page
            valSetter.accept(userInput.text.toString().toInt())

            //get the indicated value from the character page
            modOutput.text = pageOf.getString(R.string.intItem, valGetter.get())
        } else modOutput.text = ""
    }
}