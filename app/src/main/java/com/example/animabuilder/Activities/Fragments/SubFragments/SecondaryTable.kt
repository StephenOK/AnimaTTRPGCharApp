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
                getString(R.string.acrobaticsLabel) -> charInstance.secondaryList.acrobatics
                getString(R.string.athleticsLabel) -> charInstance.secondaryList.athletics
                getString(R.string.climbLabel) -> charInstance.secondaryList.climb
                getString(R.string.jumpLabel) -> charInstance.secondaryList.jump
                getString(R.string.rideLabel) -> charInstance.secondaryList.ride
                getString(R.string.swimLabel) -> charInstance.secondaryList.swim

                getString(R.string.intimidateLabel) -> charInstance.secondaryList.intimidate
                getString(R.string.leadershipLabel) -> charInstance.secondaryList.leadership
                getString(R.string.persuasionLabel) -> charInstance.secondaryList.persuasion
                getString(R.string.styleLabel) -> charInstance.secondaryList.style

                getString(R.string.noticeLabel) -> charInstance.secondaryList.notice
                getString(R.string.searchLabel) -> charInstance.secondaryList.search
                getString(R.string.trackLabel) -> charInstance.secondaryList.track

                getString(R.string.animalLabel) -> charInstance.secondaryList.animals
                getString(R.string.appraiseLabel) -> charInstance.secondaryList.appraise
                getString(R.string.herbalLabel) -> charInstance.secondaryList.herbalLore
                getString(R.string.histLabel) -> charInstance.secondaryList.history
                getString(R.string.memLabel) -> charInstance.secondaryList.memorize
                getString(R.string.mAppraiseLabel) -> charInstance.secondaryList.magicAppraise
                getString(R.string.medLabel) -> charInstance.secondaryList.medic
                getString(R.string.navLabel) -> charInstance.secondaryList.navigate
                getString(R.string.occultLabel) -> charInstance.secondaryList.occult
                getString(R.string.scienceLabel) -> charInstance.secondaryList.sciences

                getString(R.string.composureLabel) -> charInstance.secondaryList.composure
                getString(R.string.strFeatLabel) -> charInstance.secondaryList.strengthFeat
                getString(R.string.resistPainLabel) -> charInstance.secondaryList.resistPain

                getString(R.string.disguiseLabel) -> charInstance.secondaryList.disguise
                getString(R.string.hideLabel) -> charInstance.secondaryList.hide
                getString(R.string.lockpickLabel) -> charInstance.secondaryList.lockPick
                getString(R.string.poisonLabel) -> charInstance.secondaryList.poisons
                getString(R.string.theftLabel) -> charInstance.secondaryList.theft
                getString(R.string.stealthLabel) -> charInstance.secondaryList.stealth
                getString(R.string.trapLabel) -> charInstance.secondaryList.trapLore

                getString(R.string.artLabel) -> charInstance.secondaryList.art
                getString(R.string.danceLabel) -> charInstance.secondaryList.dance
                getString(R.string.forgeLabel) -> charInstance.secondaryList.forging
                getString(R.string.musicLabel) -> charInstance.secondaryList.music
                getString(R.string.sleightLabel) -> charInstance.secondaryList.sleightHand

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