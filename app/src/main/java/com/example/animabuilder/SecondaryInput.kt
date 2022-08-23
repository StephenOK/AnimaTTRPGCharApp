package com.example.animabuilder

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.animabuilder.CharacterCreation.Attributes.SecondaryCharacteristic
import com.example.animabuilder.CharacterCreation.BaseCharacter

class SecondaryInput: TextWatcher {
    var workingRow: TableRow
    var location: Context
    var workingStat: SecondaryCharacteristic
    var charInstance: BaseCharacter

    var textFrom: EditText
    var textTotal: TextView

    var preValue: Int

    constructor(
        workingRow: TableRow,
        location: Context,
        workingStat: SecondaryCharacteristic,
        charInstance: BaseCharacter
    ){

        this.workingRow = workingRow
        this.location = location
        this.workingStat = workingStat
        this.charInstance = charInstance

        textFrom = workingRow.getChildAt(1) as EditText
        textTotal = workingRow.getChildAt(5) as TextView

        preValue = workingStat.pointsApplied
        textFrom.setText(preValue.toString())
        textTotal.text = workingStat.total.toString()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        preValue =
            if(textFrom.text.toString() != "") {
                textFrom.text.toString().toInt()
            } else
                0
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        if(textFrom.text.toString() != ""){
            var userInput = textFrom.text.toString().toInt()
            var change = userInput - preValue
            var newSpent = charInstance.spentTotal + (change * workingStat.devPerPoint)

            if(newSpent < charInstance.devPT) {
                textFrom.setTextColor(Color.rgb(0, 0, 0))
                charInstance.spentTotal = newSpent
                workingStat.pointsApplied = userInput
                textTotal.text = location.getString(R.string.intItem, workingStat.total)
            }

            else{
                textFrom.setTextColor(Color.rgb(255, 0, 0))
            }
        }
    }
}