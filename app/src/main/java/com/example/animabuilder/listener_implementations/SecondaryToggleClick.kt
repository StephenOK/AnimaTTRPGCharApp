package com.example.animabuilder.listener_implementations

import android.view.View
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Specialize OnClickListener for the SecondaryCharacteristicPage's toggle buttons
 * Show and hide their designated table fragments
 */

class SecondaryToggleClick(
    var parent: ToggleButton,
    private var toShow: Fragment,
    private var fm: FragmentManager
) : View.OnClickListener {

    private var ft: FragmentTransaction? = null
    override fun onClick(v: View) {
        //hide or show fragment depending on check status
        ft = fm.beginTransaction()
        if (parent.isChecked) ft!!.show(toShow) else ft!!.hide(toShow)
        ft!!.commit()
    }
}