package com.example.animabuilder.ListenerImplementations

import android.view.View
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class SecondaryToggleClick(
    var parent: ToggleButton,
    var toShow: Fragment,
    var fm: FragmentManager
) : View.OnClickListener {
    var ft: FragmentTransaction? = null
    override fun onClick(v: View) {
        ft = fm.beginTransaction()
        if (parent.isChecked) ft!!.show(toShow) else ft!!.hide(toShow)
        ft!!.commit()
    }
}