package com.example.animabuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.animabuilder.CharacterCreation.BaseCharacter;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class CharacterPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_page);

        //instantiate created character
        BaseCharacter charInstance = new BaseCharacter();



        //find name input and get character's name if one found
        EditText nameInput = (EditText)findViewById(R.id.charNameEntry);
        nameInput.setText(charInstance.getCharName());



        //add class strings to dropdown menu
        Spinner classSpinner = (Spinner)findViewById(R.id.classDropdown);
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this,
                R.array.classArray, android.R.layout.simple_spinner_item);

        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        classSpinner.setAdapter(classAdapter);

        //default to character's class
        classSpinner.setSelection(charInstance.getOwnClass().getClassIndex());


        //add race strings to dropdown menu
        Spinner raceSpinner = (Spinner)findViewById(R.id.raceDropdown);
        ArrayAdapter<CharSequence> raceAdapter = ArrayAdapter.createFromResource(this,
                R.array.raceArray, android.R.layout.simple_spinner_item);

        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        raceSpinner.setAdapter(raceAdapter);




        //add level strings to dropdown menu
        Spinner levelSpinner = (Spinner)findViewById(R.id.levelDropdown);
        ArrayAdapter<CharSequence> levelAdapter = ArrayAdapter.createFromResource(this,
                R.array.levelCountArray, android.R.layout.simple_spinner_item);

        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        levelSpinner.setAdapter(levelAdapter);



        TextView maxDPDisplay = findViewById(R.id.maxDPCell);
        TextView maxCombatDisplay = findViewById(R.id.maxCombatCell);
        TextView maxMagDisplay = findViewById(R.id.maxMagCell);
        TextView maxPsyDisplay = findViewById(R.id.maxPsyCell);

        maxDPDisplay.setText(getString(R.string.intItem, charInstance.getDevPT()));
        maxCombatDisplay.setText(getString(R.string.intItem, charInstance.getMaxCombatDP()));
        maxMagDisplay.setText(getString(R.string.intItem, charInstance.getMaxMagDP()));
        maxPsyDisplay.setText(getString(R.string.intItem, charInstance.getMaxPsyDP()));

        TextView spentDPDisplay = findViewById(R.id.usedDPCell);
        TextView spentCombatDisplay = findViewById(R.id.usedCombatCell);
        TextView spentMagDisplay = findViewById(R.id.usedMagCell);
        TextView spentPsyDisplay = findViewById(R.id.usedPsyCell);

        spentDPDisplay.setText(getString(R.string.intItem, charInstance.getSpentTotal()));
        spentCombatDisplay.setText(getString(R.string.intItem, charInstance.getPtInCombat()));
        spentMagDisplay.setText(getString(R.string.intItem, charInstance.getPtInMag()));
        spentPsyDisplay.setText(getString(R.string.intItem, charInstance.getPtInPsy()));



        //set integer range for characteristic inputs
        EditText strScore = (EditText)findViewById(R.id.strScore);
        strScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText dexScore = (EditText)findViewById(R.id.dexScore);
        dexScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText agiScore = (EditText)findViewById(R.id.agiScore);
        agiScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText conScore = (EditText)findViewById(R.id.conScore);
        conScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText intScore = (EditText)findViewById(R.id.intScore);
        intScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText powScore = (EditText)findViewById(R.id.powScore);
        powScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText wpScore = (EditText)findViewById(R.id.wpScore);
        wpScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText perScore = (EditText)findViewById(R.id.perScore);
        perScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});



        //get mod displays from the page
        TextView strMod = (TextView)findViewById(R.id.strMod);
        TextView dexMod = (TextView)findViewById(R.id.dexMod);
        TextView agiMod = (TextView)findViewById(R.id.agiMod);
        TextView conMod = (TextView)findViewById(R.id.conMod);
        TextView intMod = (TextView)findViewById(R.id.intMod);
        TextView powMod = (TextView)findViewById(R.id.powMod);
        TextView wpMod = (TextView)findViewById(R.id.wpMod);
        TextView perMod = (TextView)findViewById(R.id.perMod);



        //set all listeners for characteristic change to make mod text change to corresponding value
        strScore.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyModVal(strScore, strMod, charInstance.setSTR, charInstance.getModSTR);
            }
        });

        dexScore.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyModVal(dexScore, dexMod, charInstance.setDEX, charInstance.getModDEX);
            }
        });

        agiScore.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyModVal(agiScore, agiMod, charInstance.setAGI, charInstance.getModAGI);
            }
        });

        conScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyModVal(conScore, conMod, charInstance.setCON, charInstance.getModCON);
            }
        });

        intScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyModVal(intScore, intMod, charInstance.setINT, charInstance.getModINT);
            }
        });

        powScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyModVal(powScore, powMod, charInstance.setPOW, charInstance.getModPOW);
            }
        });

        wpScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyModVal(wpScore, wpMod, charInstance.setWP, charInstance.getModWP);
            }
        });

        perScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyModVal(perScore, perMod, charInstance.setPER, charInstance.getModPER);
            }
        });
    }

    /**
     * Sets the character's indicated value to the input value and alters the indicated mod display
     * to the corresponding value
     *
     * @param userInput     EditText input for the characteristic score
     * @param modOutput     TextView output for the characteristic mod
     * @param valSetter     Corresponding setter function
     * @param valGetter     Corresponding getter function
     */
    private void applyModVal(EditText userInput, TextView modOutput,
                                Consumer<Integer> valSetter, Supplier<Integer> valGetter){

        //if integer value given
        if(!userInput.getText().toString().equals("")){
            //chanage the indicated value in the character page
            valSetter.accept(Integer.parseInt(userInput.getText().toString()));

            //get the indicated value from the character page
            modOutput.setText(getString(R.string.intItem, valGetter.get()));
        }
        //clear mod if text is empty
        else
            modOutput.setText("");
    }
}