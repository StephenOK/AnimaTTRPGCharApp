package com.example.animabuilder.Activities.Fragments.HomeFragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.example.animabuilder.Activities.Fragments.SecondaryTables.AthleticsFragment;
import com.example.animabuilder.CharacterCreation.BaseCharacter;
import com.example.animabuilder.R;
import com.example.animabuilder.SecondaryToggleClick;

public class SecondaryAbilityFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_secondary_ability, container, false);

        FragmentManager fm = this.getParentFragmentManager();

        Bundle fromActivity = getArguments();
        BaseCharacter charInstance = (BaseCharacter)fromActivity.getSerializable("Character");

        AthleticsFragment athFrag = new AthleticsFragment();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.athleticsFragment, athFrag);
        ft.hide(athFrag);
        ft.commit();

        ToggleButton athleteToggle = view.findViewById(R.id.athleticsToggle);
        athleteToggle.setOnClickListener(new SecondaryToggleClick(athleteToggle, athFrag, fm, R.id.athleticsFragment));

        return view;
    }
}