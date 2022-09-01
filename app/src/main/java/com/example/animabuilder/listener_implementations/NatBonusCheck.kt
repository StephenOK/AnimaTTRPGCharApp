package com.example.animabuilder.listener_implementations

import android.content.Context
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryCharacteristic
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryList
import com.example.animabuilder.R

/**
 * Specialized listener for the natural bonus checkbox
 */

class NatBonusCheck(
    homeRow: TableRow,
    private var charList: SecondaryList,
    private var targetStat: SecondaryCharacteristic,
    private var location: Context
) : CompoundButton.OnCheckedChangeListener {

    var parent: CheckBox
    private var total: TextView

    init {
        parent = homeRow.getChildAt(4) as CheckBox
        total = homeRow.getChildAt(5) as TextView
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        //if user is applying a natural bonus
        if(parent.isChecked) {
            //check if either no points are applied or if no more bonuses are available
            if (targetStat.pointsApplied == 0 || !charList.incrementNat(true))
                //prevent bonus from applying
                parent.isChecked = false
            else {
                //apply bonus and display stat change
                targetStat.setBonusApplied(true)
                parent.text = location.getString(R.string.natTaken)
            }
        }

        //if user is removing a natural bonus
        else {
            //remove bonus and change text accordingly
            charList.incrementNat(false)
            targetStat.setBonusApplied(false)
            parent.text = location.getString(R.string.natNotTaken)
        }

        //update total text
        total.text = location.getString(R.string.intItem, targetStat.total)
    }
}