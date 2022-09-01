package com.example.animabuilder.activities.fragments.home_fragments

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.activities.fragments.sub_fragments.SecondaryTable
import android.widget.ToggleButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryCharacteristic
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryList
import com.example.animabuilder.listener_implementations.SecondaryToggleClick
import com.google.accompanist.appcompattheme.AppCompatTheme

/**
 * Fragment to be displayed when working with secondary characteristics
 */

class SecondaryAbilityFragment : Fragment() {

    private var ft: FragmentTransaction? = null
    private var charInstance: BaseCharacter = BaseCharacter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)

        //get xml file to display
        val view = inflater.inflate(R.layout.fragment_secondary_ability, container, false)

        //get fragment manager from HomeActivity
        val fm = this.parentFragmentManager

        //receive BaseCharacter from bundle
        val fromActivity = arguments
        charInstance = fromActivity!!.getSerializable("Character") as BaseCharacter

        //retrieve class from character
        val valueRef = charInstance!!.ownClass
        valueRef!!

        //find table fragment locations
        val athFrag = SecondaryTable(charInstance!!, R.layout.athletics_fragment)
        val socFrag = SecondaryTable(charInstance!!, R.layout.social_fragment)
        val percFrag = SecondaryTable(charInstance!!, R.layout.perception_fragment)
        val intelFrag = SecondaryTable(charInstance!!, R.layout.intellecutal_fragment)
        val vigFrag = SecondaryTable(charInstance!!, R.layout.vigor_fragment)
        val subFrag = SecondaryTable(charInstance!!, R.layout.subterfuge_fragment)
        val creFrag = SecondaryTable(charInstance!!, R.layout.creative_fragment)

        //place individual tables at the fragment locations
        ft = fm.beginTransaction()
        fragmentReplace(R.id.athleticsFragment, athFrag)
        fragmentReplace(R.id.socialFragment, socFrag)
        fragmentReplace(R.id.perceptionFragment, percFrag)
        fragmentReplace(R.id.intellectualFragment, intelFrag)
        fragmentReplace(R.id.vigorFragment, vigFrag)
        fragmentReplace(R.id.subterfugeFragment, subFrag)
        fragmentReplace(R.id.creativeFragment, creFrag)
        ft!!.commit()

        //retrieve page's toggle buttons
        val athleteToggle = view.findViewById<ToggleButton>(R.id.athleticsToggle)
        val socialToggle = view.findViewById<ToggleButton>(R.id.socialToggle)
        val perceptionToggle = view.findViewById<ToggleButton>(R.id.perceptionToggle)
        val intellectToggle = view.findViewById<ToggleButton>(R.id.intellectualToggle)
        val vigorToggle = view.findViewById<ToggleButton>(R.id.vigorToggle)
        val subterToggle = view.findViewById<ToggleButton>(R.id.subterfugeToggle)
        val createToggle = view.findViewById<ToggleButton>(R.id.creativeToggle)

        //set toggle on texts to values corresponding to class growth values
        athleteToggle.textOn = getString(R.string.athleticsLabel) + getString(
                R.string.togglePointInt,
                valueRef.athGrowth
            )
        socialToggle.textOn = getString(R.string.socialLabel) + getString(
                R.string.togglePointInt,
                valueRef.socGrowth
            )
        perceptionToggle.textOn = getString(R.string.perceptionLabel) + getString(
            R.string.togglePointInt,
            valueRef.percGrowth
        )
        intellectToggle.textOn = getString(R.string.intellectualLabel) + getString(
            R.string.togglePointInt,
            valueRef.intellGrowth
        )
        vigorToggle.textOn = getString(R.string.vigorLabel) + getString(R.string.togglePointInt, valueRef.vigGrowth)
        subterToggle.textOn = getString(R.string.subterfugeLabel) + getString(
            R.string.togglePointInt,
            valueRef.subterGrowth
        )
        createToggle.textOn = getString(R.string.creativeLabel) + getString(
                R.string.togglePointInt,
                valueRef.creatGrowth
            )

        //set toggle onclick listeners
        athleteToggle.setOnClickListener(SecondaryToggleClick(athleteToggle, athFrag, fm))
        socialToggle.setOnClickListener(SecondaryToggleClick(socialToggle, socFrag, fm))
        perceptionToggle.setOnClickListener(SecondaryToggleClick(perceptionToggle, percFrag, fm))
        intellectToggle.setOnClickListener(SecondaryToggleClick(intellectToggle, intelFrag, fm))
        vigorToggle.setOnClickListener(SecondaryToggleClick(vigorToggle, vigFrag, fm))
        subterToggle.setOnClickListener(SecondaryToggleClick(subterToggle, subFrag, fm))
        createToggle.setOnClickListener(SecondaryToggleClick(createToggle, creFrag, fm))

        val nameTag = view.findViewById<ComposeView>(R.id.testRow)
        nameTag.setContent {
            AppCompatTheme() {
                intelTable(charInstance.secondaryList)
            }
        }

        return view
    }

    /**
     * Function to replace and immediately hide fragments
     */
    private fun fragmentReplace(id: Int, item: Fragment) {
        ft!!.replace(id, item)
        ft!!.hide(item)
    }

    @Composable
    private fun makeToggle(){
        Button(onClick = { /*TODO*/ }) {
            
        }
    }

    @Composable
    private fun makeRow(
        stringReference: Int,
        itemList: SecondaryList,
        item: SecondaryCharacteristic
    ){
        val userInput = remember{mutableStateOf(item.pointsApplied.toString())}
        var preValue = item.pointsApplied

        val textColor = remember{mutableStateOf(Color.BLACK)}

        val checkedState = remember{mutableStateOf(item.bonusApplied)}
        val checkedText =
            if(checkedState.value)
                remember{mutableStateOf(R.string.natTaken)}
            else
                remember{mutableStateOf(R.string.natNotTaken)}

        val total = remember{mutableStateOf(item.total.toString())}

        Row(
            //modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = stringResource(stringReference),
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(0.25f))

            TextField(
                value = userInput.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    userInput.value = it

                    var calcNum =
                        if(userInput.value == "")
                            0
                        else
                            userInput.value.trim().toInt()

                    //apply input to SecondaryCharacteristic
                    item.setPointsApplied(calcNum)

                    //get new amount of points spent
                    charInstance!!.spentTotal += item.devPerPoint * (calcNum - preValue)

                    //update text
                    total.value = getString(R.string.intItem, item.total)

                    //check if spent is  valid
                    if(charInstance!!.spentTotal < charInstance!!.devPT)
                        //make text black for valid
                        textColor.value = Color.BLACK

                    else
                        //make text red for invalid
                        textColor.value = Color.RED

                    preValue = calcNum
                },

                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                modifier = Modifier.weight(0.25f)
            )

            Text(text = item.modVal.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.125f))

            Text(text = item.pointsFromClass.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.125f))

            Checkbox(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it

                    //if user is applying a natural bonus
                    if (checkedState.value) {
                        //check if either no points are applied or if no more bonuses are available
                        if (item.pointsApplied == 0 || !itemList.incrementNat(true))
                        //prevent bonus from applying
                            checkedState.value = false
                        else {
                            //apply bonus and display stat change
                            item.setBonusApplied(true)
                            checkedText.value = R.string.natTaken
                        }
                    }

                    //if user is removing a natural bonus
                    else {
                        //remove bonus and change text accordingly
                        itemList.incrementNat(false)
                        item.setBonusApplied(false)
                        checkedText.value = R.string.natNotTaken
                    }

                    //update total text
                    total.value = item.total.toString()
                },
                modifier = Modifier.weight(0.125f)
            )

            Text(text = total.value,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.125f))
        }
    }

    @Composable
    private fun athleticsTable(list: SecondaryList){
        Column() {
            makeRow(R.string.acrobaticsLabel, list, list.acrobatics)
            makeRow(R.string.athleticsLabel, list, list.athletics)
            makeRow(R.string.climbLabel, list, list.climb)
            makeRow(R.string.jumpLabel, list, list.jump)
            makeRow(R.string.rideLabel, list, list.ride)
            makeRow(R.string.swimLabel, list, list.swim)
        }
    }

    @Composable
    private fun socialTable(list: SecondaryList){
        Column(){
            makeRow(R.string.intimidateLabel, list, list.intimidate)
            makeRow(R.string.leadershipLabel, list, list.leadership)
            makeRow(R.string.persuasionLabel, list, list.persuasion)
            makeRow(R.string.styleLabel, list, list.style)
        }
    }

    @Composable
    private fun percTable(list: SecondaryList){
        Column(){
            makeRow(R.string.noticeLabel, list, list.notice)
            makeRow(R.string.searchLabel, list, list.search)
            makeRow(R.string.trackLabel, list, list.track)
        }
    }

    @Composable
    private fun intelTable(list: SecondaryList){
        Column(){
            makeRow(R.string.animalLabel, list, list.animals)
            makeRow(R.string.appraiseLabel, list, list.appraise)
            makeRow(R.string.herbalLabel, list, list.herbalLore)
            makeRow(R.string.histLabel, list, list.history)
            makeRow(R.string.memLabel, list, list.memorize)
            makeRow(R.string.mAppraiseLabel, list, list.magicAppraise)
            makeRow(R.string.medLabel, list, list.medic)
            makeRow(R.string.navLabel, list, list.navigate)
            makeRow(R.string.occultLabel, list, list.occult)
            makeRow(R.string.scienceLabel, list, list.sciences)
        }
    }

    @Composable
    private fun vigorTable(list: SecondaryList){
        Column(){
            makeRow(R.string.composureLabel, list, list.composure)
            makeRow(R.string.strFeatLabel, list, list.strengthFeat)
            makeRow(R.string.resistPainLabel, list, list.resistPain)
        }
    }

    @Composable
    private fun subterTable(list: SecondaryList){
        Column(){
            makeRow(R.string.disguiseLabel, list, list.disguise)
            makeRow(R.string.hideLabel, list, list.hide)
            makeRow(R.string.lockpickLabel, list, list.lockPick)
            makeRow(R.string.poisonLabel, list, list.poisons)
            makeRow(R.string.theftLabel, list, list.theft)
            makeRow(R.string.stealthLabel, list, list.stealth)
            makeRow(R.string.trapLabel, list, list.trapLore)
        }
    }

    @Composable
    private fun creatTable(list: SecondaryList){
        Column(){
            makeRow(R.string.artLabel, list, list.art)
            makeRow(R.string.danceLabel, list, list.dance)
            makeRow(R.string.forgeLabel, list, list.forging)
            makeRow(R.string.musicLabel, list, list.music)
            makeRow(R.string.sleightLabel, list, list.sleightHand)
        }
    }
}