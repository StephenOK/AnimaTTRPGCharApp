package com.example.animabuilder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SecondaryToggleClick implements View.OnClickListener {

    ToggleButton parent;
    Fragment toShow;
    FragmentManager fm;

    FragmentTransaction ft;

    public SecondaryToggleClick(ToggleButton parent, Fragment toShow, FragmentManager fm){
        this.parent = parent;
        this.toShow = toShow;
        this.fm = fm;
    }

    @Override
    public void onClick(View v) {
        ft = fm.beginTransaction();

        if(parent.isChecked())
            ft.show(toShow);
        else
            ft.hide(toShow);

        ft.commit();
    }
}
