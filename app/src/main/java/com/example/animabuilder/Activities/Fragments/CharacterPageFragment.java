package com.example.animabuilder.Activities.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.animabuilder.CharacterCreation.BaseCharacter;
import com.example.animabuilder.CharacteristicInput;
import com.example.animabuilder.InputFilterMinMax;
import com.example.animabuilder.R;

public class CharacterPageFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_character_page, container, false);

        Bundle fromActivity = getArguments();

        BaseCharacter charInstance = (BaseCharacter)fromActivity.getSerializable("Character");

        //find name input and get character's name if one found
        EditText nameInput = view.findViewById(R.id.charNameEntry);
        nameInput.setText(charInstance.getCharName());

        //make base character copy for manipulation
        BaseCharacter finalCharInstance = charInstance;

        //set watcher for name input
        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //set character's name to the new input
                finalCharInstance.setCharName(nameInput.getText().toString());
            }
        });



        //add class strings to dropdown menu
        Spinner classSpinner = view.findViewById(R.id.classDropdown);
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.classArray, android.R.layout.simple_spinner_item);

        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        classSpinner.setAdapter(classAdapter);

        //default to character's class
        classSpinner.setSelection(charInstance.getOwnClass().getClassIndex());

        //add race strings to dropdown menu
        Spinner raceSpinner = view.findViewById(R.id.raceDropdown);
        ArrayAdapter<CharSequence> raceAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.raceArray, android.R.layout.simple_spinner_item);

        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        raceSpinner.setAdapter(raceAdapter);

        //default to character's race
        raceSpinner.setSelection(charInstance.getOwnRace().getRaceIndex());

        //add level strings to dropdown menu
        Spinner levelSpinner = view.findViewById(R.id.levelDropdown);
        ArrayAdapter<CharSequence> levelAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.levelCountArray, android.R.layout.simple_spinner_item);

        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        levelSpinner.setAdapter(levelAdapter);

        //find displays for maximum point input
        TextView maxDPDisplay = view.findViewById(R.id.maxDPCell);
        TextView maxCombatDisplay = view.findViewById(R.id.maxCombatCell);
        TextView maxMagDisplay = view.findViewById(R.id.maxMagCell);
        TextView maxPsyDisplay = view.findViewById(R.id.maxPsyCell);

        //set values to display
        maxDPDisplay.setText(getString(R.string.intItem, charInstance.getDevPT()));
        maxCombatDisplay.setText(getString(R.string.intItem, charInstance.getMaxCombatDP()));
        maxMagDisplay.setText(getString(R.string.intItem, charInstance.getMaxMagDP()));
        maxPsyDisplay.setText(getString(R.string.intItem, charInstance.getMaxPsyDP()));

        //find spend amount displays
        TextView spentDPDisplay = view.findViewById(R.id.usedDPCell);
        TextView spentCombatDisplay = view.findViewById(R.id.usedCombatCell);
        TextView spentMagDisplay = view.findViewById(R.id.usedMagCell);
        TextView spentPsyDisplay = view.findViewById(R.id.usedPsyCell);

        //set spent values to display
        spentDPDisplay.setText(getString(R.string.intItem, charInstance.getSpentTotal()));
        spentCombatDisplay.setText(getString(R.string.intItem, charInstance.getPtInCombat()));
        spentMagDisplay.setText(getString(R.string.intItem, charInstance.getPtInMag()));
        spentPsyDisplay.setText(getString(R.string.intItem, charInstance.getPtInPsy()));

        //set on selection listener for class options
        classSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //get string from selection and change character's class accordingly
                finalCharInstance.setOwnClass(classSpinner.getSelectedItem().toString());

                //display new maximum values
                maxCombatDisplay.setText(getString(R.string.intItem, finalCharInstance.getMaxCombatDP()));
                maxMagDisplay.setText(getString(R.string.intItem, finalCharInstance.getMaxMagDP()));
                maxPsyDisplay.setText(getString(R.string.intItem, finalCharInstance.getMaxPsyDP()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //set on selection listener for race options
        raceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //get string from selection and change character's race accordingly
                finalCharInstance.setOwnRace(raceSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //set on selection listener for level
        levelSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //get string from selection and change character's level accordingly
                finalCharInstance.setLvl(Integer.parseInt(levelSpinner.getSelectedItem().toString()));

                //display new maximum values
                maxDPDisplay.setText(getString(R.string.intItem, finalCharInstance.getDevPT()));
                maxCombatDisplay.setText(getString(R.string.intItem, finalCharInstance.getMaxCombatDP()));
                maxMagDisplay.setText(getString(R.string.intItem, finalCharInstance.getMaxMagDP()));
                maxPsyDisplay.setText(getString(R.string.intItem, finalCharInstance.getMaxPsyDP()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //set display to character's level
        levelSpinner.setSelection(finalCharInstance.getLvl());



        //set integer range for characteristic inputs
        EditText strScore = view.findViewById(R.id.strScore);
        strScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText dexScore = view.findViewById(R.id.dexScore);
        dexScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText agiScore = view.findViewById(R.id.agiScore);
        agiScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText conScore = view.findViewById(R.id.conScore);
        conScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText intScore = view.findViewById(R.id.intScore);
        intScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText powScore = view.findViewById(R.id.powScore);
        powScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText wpScore = view.findViewById(R.id.wpScore);
        wpScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText perScore = view.findViewById(R.id.perScore);
        perScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});



        //get mod displays from the page
        TextView strMod = view.findViewById(R.id.strMod);
        TextView dexMod = view.findViewById(R.id.dexMod);
        TextView agiMod = view.findViewById(R.id.agiMod);
        TextView conMod = view.findViewById(R.id.conMod);
        TextView intMod = view.findViewById(R.id.intMod);
        TextView powMod = view.findViewById(R.id.powMod);
        TextView wpMod = view.findViewById(R.id.wpMod);
        TextView perMod = view.findViewById(R.id.perMod);



        //set all listeners for characteristic change to make mod text change to corresponding value
        strScore.addTextChangedListener(new CharacteristicInput(strScore, strMod, charInstance.setSTR, charInstance.getModSTR, getActivity().getApplicationContext()));
        dexScore.addTextChangedListener(new CharacteristicInput(dexScore, dexMod, charInstance.setDEX, charInstance.getModDEX, getActivity().getApplicationContext()));
        agiScore.addTextChangedListener(new CharacteristicInput(agiScore, agiMod, charInstance.setAGI, charInstance.getModAGI, getActivity().getApplicationContext()));
        conScore.addTextChangedListener(new CharacteristicInput(conScore, conMod, charInstance.setCON, charInstance.getModCON, getActivity().getApplicationContext()));
        intScore.addTextChangedListener(new CharacteristicInput(intScore, intMod, charInstance.setINT, charInstance.getModINT, getActivity().getApplicationContext()));
        powScore.addTextChangedListener(new CharacteristicInput(powScore, powMod, charInstance.setPOW, charInstance.getModPOW, getActivity().getApplicationContext()));
        wpScore.addTextChangedListener(new CharacteristicInput(wpScore, wpMod, charInstance.setWP, charInstance.getModWP,getActivity().getApplicationContext()));
        perScore.addTextChangedListener(new CharacteristicInput(perScore, perMod, charInstance.setPER, charInstance.getModPER, getActivity().getApplicationContext()));

        //set initial values for characteristics
        strScore.setText(getString(R.string.intItem, finalCharInstance.getSTR()));
        dexScore.setText(getString(R.string.intItem, finalCharInstance.getDEX()));
        agiScore.setText(getString(R.string.intItem, finalCharInstance.getAGI()));
        conScore.setText(getString(R.string.intItem, finalCharInstance.getCON()));
        intScore.setText(getString(R.string.intItem, finalCharInstance.getINT()));
        powScore.setText(getString(R.string.intItem, finalCharInstance.getPOW()));
        wpScore.setText(getString(R.string.intItem, finalCharInstance.getWP()));
        perScore.setText(getString(R.string.intItem, finalCharInstance.getPER()));

        return view;
    }
}