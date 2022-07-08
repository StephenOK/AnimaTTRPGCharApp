package com.example.animabuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animabuilder.CharacterCreation.BaseCharacter;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CharacterPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_page);

        BaseCharacter charInstance = new BaseCharacter();



        EditText nameInput = (EditText)findViewById(R.id.charNameEntry);
        nameInput.setText(charInstance.getCharName());



        //add class strings to dropdown menu
        Spinner classSpinner = (Spinner)findViewById(R.id.classDropdown);
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this,
                R.array.classArray, android.R.layout.simple_spinner_item);

        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        classSpinner.setAdapter(classAdapter);

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



        TextView strMod = (TextView)findViewById(R.id.strMod);
        TextView dexMod = (TextView)findViewById(R.id.dexMod);
        TextView agiMod = (TextView)findViewById(R.id.agiMod);
        TextView conMod = (TextView)findViewById(R.id.conMod);
        TextView intMod = (TextView)findViewById(R.id.intMod);
        TextView powMod = (TextView)findViewById(R.id.powMod);
        TextView wpMod = (TextView)findViewById(R.id.wpMod);
        TextView perMod = (TextView)findViewById(R.id.perMod);



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

    private void applyModVal(EditText userInput, TextView modOutput,
                                Consumer<Integer> valSetter, Supplier<Integer> valGetter){

        if(!userInput.getText().toString().equals("")){
            valSetter.accept(Integer.parseInt(userInput.getText().toString()));

            modOutput.setText(getString(R.string.modValue, valGetter.get()));
        }
        else
            modOutput.setText("");
    }
}