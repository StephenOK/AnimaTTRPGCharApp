package com.example.animabuilder.Activities.Fragments.HomeFragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import com.example.animabuilder.R
import com.example.animabuilder.CharacterCreation.BaseCharacter
import com.example.animabuilder.CharacterCreation.Attributes.Class.CharClass
import com.example.animabuilder.Activities.Fragments.SubFragments.SecondaryTable
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.animabuilder.ListenerImplementations.SecondaryToggleClick

class SecondaryAbilityFragment : Fragment() {
    var ft: FragmentTransaction? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_secondary_ability, container, false)
        val fm = this.parentFragmentManager
        val fromActivity = arguments
        val charInstance = fromActivity!!.getSerializable("Character") as BaseCharacter?
        val valueRef = charInstance!!.ownClass
        val athFrag = SecondaryTable(charInstance, R.layout.athletics_fragment)
        val socFrag = SecondaryTable(charInstance, R.layout.social_fragment)
        val percFrag = SecondaryTable(charInstance, R.layout.perception_fragment)
        val intelFrag = SecondaryTable(charInstance, R.layout.intellecutal_fragment)
        val vigFrag = SecondaryTable(charInstance, R.layout.vigor_fragment)
        val subFrag = SecondaryTable(charInstance, R.layout.subterfuge_fragment)
        val creFrag = SecondaryTable(charInstance, R.layout.creative_fragment)
        ft = fm.beginTransaction()
        fragmentReplace(R.id.athleticsFragment, athFrag)
        fragmentReplace(R.id.socialFragment, socFrag)
        fragmentReplace(R.id.perceptionFragment, percFrag)
        fragmentReplace(R.id.intellectualFragment, intelFrag)
        fragmentReplace(R.id.vigorFragment, vigFrag)
        fragmentReplace(R.id.subterfugeFragment, subFrag)
        fragmentReplace(R.id.creativeFragment, creFrag)
        ft!!.commit()
        val athleteToggle = view.findViewById<ToggleButton>(R.id.athleticsToggle)
        val socialToggle = view.findViewById<ToggleButton>(R.id.socialToggle)
        val perceptionToggle = view.findViewById<ToggleButton>(R.id.perceptionToggle)
        val intellectToggle = view.findViewById<ToggleButton>(R.id.intellectualToggle)
        val vigorToggle = view.findViewById<ToggleButton>(R.id.vigorToggle)
        val subterToggle = view.findViewById<ToggleButton>(R.id.subterfugeToggle)
        val createToggle = view.findViewById<ToggleButton>(R.id.creativeToggle)
        if (valueRef != null) {
            athleteToggle.textOn =
                getString(R.string.athleticsLabel) + getString(
                    R.string.togglePointInt,
                    valueRef.athGrowth
                )
        }
        if (valueRef != null) {
            socialToggle.textOn =
                getString(R.string.socialLabel) + getString(R.string.togglePointInt, valueRef.socGrowth)
        }
        if (valueRef != null) {
            perceptionToggle.textOn = getString(R.string.perceptionLabel) + getString(
                R.string.togglePointInt,
                valueRef.percGrowth
            )
        }
        if (valueRef != null) {
            intellectToggle.textOn = getString(R.string.intellectualLabel) + getString(
                R.string.togglePointInt,
                valueRef.intellGrowth
            )
        }
        if (valueRef != null) {
            vigorToggle.textOn =
                getString(R.string.vigorLabel) + getString(R.string.togglePointInt, valueRef.vigGrowth)
        }
        if (valueRef != null) {
            subterToggle.textOn = getString(R.string.subterfugeLabel) + getString(
                R.string.togglePointInt,
                valueRef.subterGrowth
            )
        }
        if (valueRef != null) {
            createToggle.textOn =
                getString(R.string.creativeLabel) + getString(
                    R.string.togglePointInt,
                    valueRef.creatGrowth
                )
        }
        athleteToggle.setOnClickListener(SecondaryToggleClick(athleteToggle, athFrag, fm))
        socialToggle.setOnClickListener(SecondaryToggleClick(socialToggle, socFrag, fm))
        perceptionToggle.setOnClickListener(SecondaryToggleClick(perceptionToggle, percFrag, fm))
        intellectToggle.setOnClickListener(SecondaryToggleClick(intellectToggle, intelFrag, fm))
        vigorToggle.setOnClickListener(SecondaryToggleClick(vigorToggle, vigFrag, fm))
        subterToggle.setOnClickListener(SecondaryToggleClick(subterToggle, subFrag, fm))
        createToggle.setOnClickListener(SecondaryToggleClick(createToggle, creFrag, fm))
        return view
    }

    private fun fragmentReplace(id: Int, item: Fragment) {
        ft!!.replace(id, item)
        ft!!.hide(item)
    }
}