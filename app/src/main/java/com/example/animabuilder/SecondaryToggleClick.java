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
    int fragmentID;

    public SecondaryToggleClick(ToggleButton parent, Fragment toShow, FragmentManager fm, int fragmentID){
        this.parent = parent;
        this.toShow = toShow;
        this.fm = fm;
        this.fragmentID = fragmentID;
    }

    @Override
    public void onClick(View v) {
        if(parent.isChecked()){
            FragmentTransaction ft = fm.beginTransaction();
            ft.show(toShow);
            ft.commit();
        }
        else{
            FragmentTransaction ft = fm.beginTransaction();
            ft.hide(toShow);
            ft.commit();
        }
    }
}
