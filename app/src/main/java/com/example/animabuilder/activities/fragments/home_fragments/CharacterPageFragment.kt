package com.example.animabuilder.activities.fragments.home_fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import android.widget.EditText
import android.text.TextWatcher
import android.text.Editable
import android.text.InputFilter
import android.view.View
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.AdapterView
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.animabuilder.listener_implementations.InputFilterMinMax
import com.example.animabuilder.listener_implementations.CharacteristicInput
import com.google.accompanist.appcompattheme.AppCompatTheme

/**
 * Fragment to be displayed when working with basic characteristics
 * Used to manipulate core stats, class, race, and name
 * Default fragment at character load
 */

class CharacterPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)

        //inflate xml display
        val view = inflater.inflate(R.layout.fragment_character_page, container, false)

        //get BaseCharacter passed from HomeActivity bundle
        val fromActivity = arguments
        val charInstance = fromActivity!!.getSerializable("Character") as BaseCharacter?

        //compose name label
        val nameTag = view.findViewById<ComposeView>(R.id.nameText)
        nameTag.setContent{ AppCompatTheme {
            nameTag()
        }}

        //find name input and get character's saved name
        val nameInput = view.findViewById<EditText>(R.id.charNameEntry)
        nameInput.setText(charInstance!!.charName)

        //set watcher for name input
        nameInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                //set character's name to the new input
                charInstance.charName = nameInput.text.toString()
            }
        })


        //add class strings to dropdown menu
        val classSpinner = view.findViewById<Spinner>(R.id.classDropdown)
        val classAdapter = ArrayAdapter.createFromResource(
            activity!!.applicationContext,
            R.array.classArray, android.R.layout.simple_spinner_item
        )
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        classSpinner.adapter = classAdapter

        //default to character's class
        charInstance.ownClass!!.let { classSpinner.setSelection(it.classIndex) }

        //add race strings to dropdown menu
        val raceSpinner = view.findViewById<Spinner>(R.id.raceDropdown)
        val raceAdapter = ArrayAdapter.createFromResource(
            activity!!.applicationContext,
            R.array.raceArray, android.R.layout.simple_spinner_item
        )
        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        raceSpinner.adapter = raceAdapter

        //default to character's race
        charInstance.ownRace!!.let { raceSpinner.setSelection(it.raceIndex) }

        //add level strings to dropdown menu
        val levelSpinner = view.findViewById<Spinner>(R.id.levelDropdown)
        val levelAdapter = ArrayAdapter.createFromResource(
            activity!!.applicationContext,
            R.array.levelCountArray, android.R.layout.simple_spinner_item
        )
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        levelSpinner.adapter = levelAdapter

        //find displays for maximum point input
        val maxDPDisplay = view.findViewById<TextView>(R.id.maxDPCell)
        val maxCombatDisplay = view.findViewById<TextView>(R.id.maxCombatCell)
        val maxMagDisplay = view.findViewById<TextView>(R.id.maxMagCell)
        val maxPsyDisplay = view.findViewById<TextView>(R.id.maxPsyCell)

        //set values to display
        maxDPDisplay.text = getString(R.string.intItem, charInstance.devPT)
        maxCombatDisplay.text = getString(R.string.intItem, charInstance.maxCombatDP)
        maxMagDisplay.text = getString(R.string.intItem, charInstance.maxMagDP)
        maxPsyDisplay.text = getString(R.string.intItem, charInstance.maxPsyDP)

        //find spend amount displays
        val spentDPDisplay = view.findViewById<TextView>(R.id.usedDPCell)
        val spentCombatDisplay = view.findViewById<TextView>(R.id.usedCombatCell)
        val spentMagDisplay = view.findViewById<TextView>(R.id.usedMagCell)
        val spentPsyDisplay = view.findViewById<TextView>(R.id.usedPsyCell)

        //set spent values to display
        spentDPDisplay.text = getString(R.string.intItem, charInstance.spentTotal)
        spentCombatDisplay.text = getString(R.string.intItem, charInstance.ptInCombat)
        spentMagDisplay.text = getString(R.string.intItem, charInstance.ptInMag)
        spentPsyDisplay.text = getString(R.string.intItem, charInstance.ptInPsy)

        //set on selection listener for class options
        classSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                //get string from selection and change character's class accordingly
                charInstance.setOwnClass(classSpinner.selectedItem.toString())

                //display new maximum values
                maxCombatDisplay.text = getString(R.string.intItem, charInstance.maxCombatDP)
                maxMagDisplay.text = getString(R.string.intItem, charInstance.maxMagDP)
                maxPsyDisplay.text = getString(R.string.intItem, charInstance.maxPsyDP)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        //set on selection listener for race options
        raceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                //get string from selection and change character's race accordingly
                charInstance.setOwnRace(raceSpinner.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        //set on selection listener for level
        levelSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                //get string from selection and change character's level accordingly
                charInstance.setLvl(levelSpinner.selectedItem.toString().toInt())

                //display new maximum values
                maxDPDisplay.text = getString(R.string.intItem, charInstance.devPT)
                maxCombatDisplay.text = getString(R.string.intItem, charInstance.maxCombatDP)
                maxMagDisplay.text = getString(R.string.intItem, charInstance.maxMagDP)
                maxPsyDisplay.text = getString(R.string.intItem, charInstance.maxPsyDP)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        //set display to character's level
        levelSpinner.setSelection(charInstance.lvl)


        //set integer range for characteristic inputs
        val strScore = view.findViewById<EditText>(R.id.strScore)
        strScore.filters = arrayOf<InputFilter>(InputFilterMinMax(1, 20))
        val dexScore = view.findViewById<EditText>(R.id.dexScore)
        dexScore.filters = arrayOf<InputFilter>(InputFilterMinMax(1, 20))
        val agiScore = view.findViewById<EditText>(R.id.agiScore)
        agiScore.filters = arrayOf<InputFilter>(InputFilterMinMax(1, 20))
        val conScore = view.findViewById<EditText>(R.id.conScore)
        conScore.filters = arrayOf<InputFilter>(InputFilterMinMax(1, 20))
        val intScore = view.findViewById<EditText>(R.id.intScore)
        intScore.filters = arrayOf<InputFilter>(InputFilterMinMax(1, 20))
        val powScore = view.findViewById<EditText>(R.id.powScore)
        powScore.filters = arrayOf<InputFilter>(InputFilterMinMax(1, 20))
        val wpScore = view.findViewById<EditText>(R.id.wpScore)
        wpScore.filters = arrayOf<InputFilter>(InputFilterMinMax(1, 20))
        val perScore = view.findViewById<EditText>(R.id.perScore)
        perScore.filters = arrayOf<InputFilter>(InputFilterMinMax(1, 20))


        //get mod displays from the page
        val strMod = view.findViewById<TextView>(R.id.strMod)
        val dexMod = view.findViewById<TextView>(R.id.dexMod)
        val agiMod = view.findViewById<TextView>(R.id.agiMod)
        val conMod = view.findViewById<TextView>(R.id.conMod)
        val intMod = view.findViewById<TextView>(R.id.intMod)
        val powMod = view.findViewById<TextView>(R.id.powMod)
        val wpMod = view.findViewById<TextView>(R.id.wpMod)
        val perMod = view.findViewById<TextView>(R.id.perMod)


        //set all listeners for characteristic change to make mod text change to corresponding value
        strScore.addTextChangedListener(
            CharacteristicInput(
                strScore,
                strMod,
                charInstance.setSTR,
                charInstance.getModSTR,
                activity!!.applicationContext
            )
        )
        dexScore.addTextChangedListener(
            CharacteristicInput(
                dexScore,
                dexMod,
                charInstance.setDEX,
                charInstance.getModDEX,
                activity!!.applicationContext
            )
        )
        agiScore.addTextChangedListener(
            CharacteristicInput(
                agiScore,
                agiMod,
                charInstance.setAGI,
                charInstance.getModAGI,
                activity!!.applicationContext
            )
        )
        conScore.addTextChangedListener(
            CharacteristicInput(
                conScore,
                conMod,
                charInstance.setCON,
                charInstance.getModCON,
                activity!!.applicationContext
            )
        )
        intScore.addTextChangedListener(
            CharacteristicInput(
                intScore,
                intMod,
                charInstance.setINT,
                charInstance.getModINT,
                activity!!.applicationContext
            )
        )
        powScore.addTextChangedListener(
            CharacteristicInput(
                powScore,
                powMod,
                charInstance.setPOW,
                charInstance.getModPOW,
                activity!!.applicationContext
            )
        )
        wpScore.addTextChangedListener(
            CharacteristicInput(
                wpScore,
                wpMod,
                charInstance.setWP,
                charInstance.getModWP,
                activity!!.applicationContext
            )
        )
        perScore.addTextChangedListener(
            CharacteristicInput(
                perScore,
                perMod,
                charInstance.setPER,
                charInstance.getModPER,
                activity!!.applicationContext
            )
        )

        //set initial values for characteristics
        strScore.setText(getString(R.string.intItem, charInstance.str))
        dexScore.setText(getString(R.string.intItem, charInstance.dex))
        agiScore.setText(getString(R.string.intItem, charInstance.agi))
        conScore.setText(getString(R.string.intItem, charInstance.con))
        intScore.setText(getString(R.string.intItem, charInstance.int))
        powScore.setText(getString(R.string.intItem, charInstance.pow))
        wpScore.setText(getString(R.string.intItem, charInstance.wp))
        perScore.setText(getString(R.string.intItem, charInstance.per))

        return view
    }

    @Preview
    @Composable
    private fun nameTag(){
        Text(
            text = stringResource(R.string.nameText),
            fontSize = 20.sp
        )
    }
}