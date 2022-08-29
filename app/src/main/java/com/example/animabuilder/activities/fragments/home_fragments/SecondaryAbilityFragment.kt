package com.example.animabuilder.activities.fragments.home_fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.activities.fragments.sub_fragments.SecondaryTable
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.animabuilder.listener_implementations.SecondaryToggleClick

/**
 * Fragment to be displayed when working with secondary characteristics
 */

class SecondaryAbilityFragment : Fragment() {

    private var ft: FragmentTransaction? = null

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
        val charInstance = fromActivity!!.getSerializable("Character") as BaseCharacter

        //retrieve class from character
        val valueRef = charInstance.ownClass
        valueRef!!

        //find table fragment locations
        val athFrag = SecondaryTable(charInstance, R.layout.athletics_fragment)
        val socFrag = SecondaryTable(charInstance, R.layout.social_fragment)
        val percFrag = SecondaryTable(charInstance, R.layout.perception_fragment)
        val intelFrag = SecondaryTable(charInstance, R.layout.intellecutal_fragment)
        val vigFrag = SecondaryTable(charInstance, R.layout.vigor_fragment)
        val subFrag = SecondaryTable(charInstance, R.layout.subterfuge_fragment)
        val creFrag = SecondaryTable(charInstance, R.layout.creative_fragment)

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

        return view
    }

    /**
     * Function to replace and immediately hide fragments
     */
    private fun fragmentReplace(id: Int, item: Fragment) {
        ft!!.replace(id, item)
        ft!!.hide(item)
    }
}