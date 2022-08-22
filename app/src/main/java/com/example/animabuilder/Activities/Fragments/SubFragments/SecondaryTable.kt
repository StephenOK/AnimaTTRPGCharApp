package com.example.animabuilder.Activities.Fragments.SubFragments

import com.example.animabuilder.CharacterCreation.BaseCharacter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import com.example.animabuilder.R
import com.example.animabuilder.CharacterCreation.Attributes.SecondaryCharacteristic
import android.widget.TextView
import android.widget.EditText
import android.widget.TableRow
import androidx.fragment.app.Fragment
import com.example.animabuilder.SecondaryInput

class SecondaryTable(var charInstance: BaseCharacter, var rID: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(rID, container, false)
        val workingTable = view.findViewById<TableLayout>(R.id.heldTable)
        for (i in 1 until workingTable.childCount) {
            val workingRow = workingTable.getChildAt(i) as TableRow
            var workingStat: SecondaryCharacteristic
            val rowText = (workingRow.getChildAt(0) as TextView).text.toString()
            workingStat = when (rowText) {
                "Acrobatics" -> charInstance.secondaryList.acrobatics
                "Athletics" -> charInstance.secondaryList.athletics
                "Climb" -> charInstance.secondaryList.climb
                "Jump" -> charInstance.secondaryList.jump
                "Ride" -> charInstance.secondaryList.ride
                "Swim" -> charInstance.secondaryList.swim
                "Intimidate" -> charInstance.secondaryList.intimidate
                "Leadership" -> charInstance.secondaryList.leadership
                "Persuasion" -> charInstance.secondaryList.persuasion
                "Style" -> charInstance.secondaryList.style
                "Notice" -> charInstance.secondaryList.notice
                "Search" -> charInstance.secondaryList.search
                "Track" -> charInstance.secondaryList.track
                "Animals" -> charInstance.secondaryList.animals
                "Appraise" -> charInstance.secondaryList.appraise
                "Herb Lore" -> charInstance.secondaryList.herbalLore
                "History" -> charInstance.secondaryList.history
                "Memorize" -> charInstance.secondaryList.memorize
                "Magic Appraise" -> charInstance.secondaryList.magicAppraise
                "Medicine" -> charInstance.secondaryList.medic
                "Navigation" -> charInstance.secondaryList.navigate
                "Occult" -> charInstance.secondaryList.occult
                "Science" -> charInstance.secondaryList.sciences
                "Composure" -> charInstance.secondaryList.composure
                "Feats of Strength" -> charInstance.secondaryList.strengthFeat
                "Withstand Pain" -> charInstance.secondaryList.resistPain
                "Disguise" -> charInstance.secondaryList.disguise
                "Hide" -> charInstance.secondaryList.hide
                "Lock Picking" -> charInstance.secondaryList.lockPick
                "Poisons" -> charInstance.secondaryList.poisons
                "Theft" -> charInstance.secondaryList.theft
                "Stealth" -> charInstance.secondaryList.stealth
                "Trap Lore" -> charInstance.secondaryList.trapLore
                "Art" -> charInstance.secondaryList.art
                "Dance" -> charInstance.secondaryList.dance
                "Forging" -> charInstance.secondaryList.forging
                "Music" -> charInstance.secondaryList.music
                "Sleight of Hand" -> charInstance.secondaryList.sleightHand
                else -> SecondaryCharacteristic()
            }
            (workingRow.getChildAt(1) as EditText).setText(
                getString(
                    R.string.intItem,
                    workingStat.pointsApplied
                )
            )
            (workingRow.getChildAt(1) as EditText).addTextChangedListener(
                SecondaryInput(
                    workingRow,
                    view.context,
                    workingStat,
                    charInstance
                )
            )
            (workingRow.getChildAt(2) as TextView).text =
                getString(R.string.intItem, workingStat.modVal)
            (workingRow.getChildAt(3) as TextView).text =
                getString(R.string.intItem, workingStat.pointsFromClass)
            if (workingStat.isBonusApplied) (workingRow.getChildAt(4) as TextView).text =
                "5" else (workingRow.getChildAt(4) as TextView).text = "0"
            (workingRow.getChildAt(5) as TextView).text =
                getString(R.string.intItem, workingStat.total)
        }
        return view
    }
}