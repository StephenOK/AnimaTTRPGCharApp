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

import com.example.animabuilder.Activities.Fragments.SubFragments.SecondaryTable;
import com.example.animabuilder.CharacterCreation.BaseCharacter;
import com.example.animabuilder.R;
import com.example.animabuilder.SecondaryToggleClick;

public class SecondaryAbilityFragment extends Fragment {

    FragmentTransaction ft;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_secondary_ability, container, false);

        FragmentManager fm = this.getParentFragmentManager();

        Bundle fromActivity = getArguments();
        BaseCharacter charInstance = (BaseCharacter)fromActivity.getSerializable("Character");

        SecondaryTable athFrag = new SecondaryTable(charInstance, R.layout.athletics_fragment);
        SecondaryTable socFrag = new SecondaryTable(charInstance, R.layout.social_fragment);
        SecondaryTable percFrag = new SecondaryTable(charInstance, R.layout.perception_fragment);
        SecondaryTable intelFrag = new SecondaryTable(charInstance, R.layout.intellecutal_fragment);
        SecondaryTable vigFrag = new SecondaryTable(charInstance, R.layout.vigor_fragment);
        SecondaryTable subFrag = new SecondaryTable(charInstance, R.layout.subterfuge_fragment);
        SecondaryTable creFrag = new SecondaryTable(charInstance, R.layout.creative_fragment);

        ft = fm.beginTransaction();
        fragmentReplace(R.id.athleticsFragment, athFrag);
        fragmentReplace(R.id.socialFragment, socFrag);
        fragmentReplace(R.id.perceptionFragment, percFrag);
        fragmentReplace(R.id.intellectualFragment, intelFrag);
        fragmentReplace(R.id.vigorFragment, vigFrag);
        fragmentReplace(R.id.subterfugeFragment, subFrag);
        fragmentReplace(R.id.creativeFragment, creFrag);
        ft.commit();

        ToggleButton athleteToggle = view.findViewById(R.id.athleticsToggle);
        ToggleButton socialToggle = view.findViewById(R.id.socialToggle);
        ToggleButton perceptionToggle = view.findViewById(R.id.perceptionToggle);
        ToggleButton intellectToggle = view.findViewById(R.id.intellectualToggle);
        ToggleButton vigorToggle = view.findViewById(R.id.vigorToggle);
        ToggleButton subterToggle = view.findViewById(R.id.subterfugeToggle);
        ToggleButton createToggle = view.findViewById(R.id.creativeToggle);

        athleteToggle.setOnClickListener(new SecondaryToggleClick(athleteToggle, athFrag, fm));
        socialToggle.setOnClickListener(new SecondaryToggleClick(socialToggle, socFrag, fm));
        perceptionToggle.setOnClickListener(new SecondaryToggleClick(perceptionToggle, percFrag, fm));
        intellectToggle.setOnClickListener(new SecondaryToggleClick(intellectToggle, intelFrag, fm));
        vigorToggle.setOnClickListener(new SecondaryToggleClick(vigorToggle, vigFrag, fm));
        subterToggle.setOnClickListener(new SecondaryToggleClick(subterToggle, subFrag, fm));
        createToggle.setOnClickListener(new SecondaryToggleClick(createToggle, creFrag, fm));

        return view;
    }

    private void fragmentReplace(int id, Fragment item){
        ft.replace(id, item);
        ft.hide(item);
    }
}