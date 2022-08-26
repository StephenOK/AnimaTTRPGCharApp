package com.example.animabuilder.ListenerImplementations

import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.animabuilder.CharacterCreation.Attributes.SecondaryAbilities.SecondaryCharacteristic
import com.example.animabuilder.CharacterCreation.Attributes.SecondaryAbilities.SecondaryList
import com.example.animabuilder.R

class natBonusCheck: CompoundButton.OnCheckedChangeListener {
    var charList: SecondaryList
    var targetStat: SecondaryCharacteristic
    var location: Fragment

    var parent: CheckBox
    var total: TextView

    constructor(
        homeRow: TableRow,
        charList: SecondaryList,
        targetStat: SecondaryCharacteristic,
        location: Fragment
    ){
        this.charList = charList
        this.targetStat = targetStat
        this.location = location

        parent = homeRow.getChildAt(4) as CheckBox
        total = homeRow.getChildAt(5) as TextView
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if(parent.isChecked) {
            if (targetStat.pointsApplied == 0 || !charList.incrementNat(true))
                parent.isChecked = false
            else {
                targetStat.bonusApplied = true
                parent.text = location.getString(R.string.natTaken)
            }
        }
        else {
            charList.incrementNat(false)
            targetStat.bonusApplied = false
            parent.text = location.getString(R.string.natNotTaken)
        }

        total.text = location.getString(R.string.intItem, targetStat.total)
    }
}