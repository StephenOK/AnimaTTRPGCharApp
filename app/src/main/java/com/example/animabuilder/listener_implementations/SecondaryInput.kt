package com.example.animabuilder.listener_implementations

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TableRow
import android.widget.TextView
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryCharacteristic
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.R

/**
 * Specialized listener for user input on secondary characteristics
 */

class SecondaryInput(
    workingRow: TableRow,
    private var location: Context,
    private var workingStat: SecondaryCharacteristic,
    private var charInstance: BaseCharacter
) : TextWatcher {

    private var textFrom = workingRow.getChildAt(1) as EditText
    private var textTotal = workingRow.getChildAt(5) as TextView

    private var preValue = 0

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //get previous input or zero
        preValue =
            if(textFrom.text.toString() != "") {
                textFrom.text.toString().toInt()
            } else
                0
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        //if an input is given
        if(textFrom.text.toString() != ""){
            //update total for the user's input
            updateTotal(textFrom.text.toString().toInt())

            //check if spent is  valid
            if(charInstance.spentTotal < charInstance.devPT)
                //make text black for valid
                textFrom.setTextColor(Color.rgb(0, 0, 0))

            else
                //make text red for invalid
                textFrom.setTextColor(Color.rgb(255, 0, 0))
        }

        //blank space equates to 0
        else
            updateTotal(0)
    }

    /**
     * Function that updates the SecondaryCharacteristic object as well as the total text
     */
    private fun updateTotal(
        userInput: Int
    ){
        //apply input to SecondaryCharacteristic
        workingStat.setPointsApplied(userInput)

        //get new amount of points spent
        charInstance.spentTotal += workingStat.devPerPoint * (userInput - preValue)

        //update text
        textTotal.text = location.getString(R.string.intItem, workingStat.total)
    }
}