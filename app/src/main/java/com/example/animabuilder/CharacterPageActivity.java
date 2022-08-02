package com.example.animabuilder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animabuilder.CharacterCreation.BaseCharacter;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class CharacterPageActivity extends AppCompatActivity {

    //instantiate drawer layout and toggle
    public DrawerLayout pageDrawer;
    public ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_page);

        //retreive filename and if the file is new
        String filename = getIntent().getStringExtra("filename");
        boolean isNew = getIntent().getBooleanExtra("isNew", true);
        BaseCharacter testGuy = (BaseCharacter) getIntent().getSerializableExtra("object");

        //instantiate character
        BaseCharacter charInstance;

        //if a character is to be loaded
        if(!isNew){
            try{
                //retreive file info
                File inputFile = new File(this.getFilesDir(), filename);

                //instantiate character off of file data
                charInstance = new BaseCharacter(inputFile);
            }
            //catch no file found exception
            catch (FileNotFoundException e) {
                Toast.makeText(CharacterPageActivity.this, "File not found!", Toast.LENGTH_SHORT).show();
                charInstance = new BaseCharacter();
            }
            //catch input/output exception
            catch (IOException e) {
                Toast.makeText(CharacterPageActivity.this, "Error in retreiving data!", Toast.LENGTH_SHORT).show();
                charInstance = new BaseCharacter();
            }
        }

        //create new character if indicated
        else
            charInstance = new BaseCharacter();

        //set drawer view and toggle
        pageDrawer = findViewById(R.id.charPageLayout);
        drawerToggle = new ActionBarDrawerToggle(this, pageDrawer, R.string.nav_open, R.string.nav_close);

        pageDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        NavigationView sideNav = findViewById(R.id.navViewSideBar);
        sideNav.setNavigationItemSelectedListener(new SideNavSelection(0, filename, charInstance, this));



        //find name input and get character's name if one found
        EditText nameInput = findViewById(R.id.charNameEntry);
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
        Spinner classSpinner = findViewById(R.id.classDropdown);
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this,
                R.array.classArray, android.R.layout.simple_spinner_item);

        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        classSpinner.setAdapter(classAdapter);

        //default to character's class
        classSpinner.setSelection(charInstance.getOwnClass().getClassIndex());

        //add race strings to dropdown menu
        Spinner raceSpinner = findViewById(R.id.raceDropdown);
        ArrayAdapter<CharSequence> raceAdapter = ArrayAdapter.createFromResource(this,
                R.array.raceArray, android.R.layout.simple_spinner_item);

        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        raceSpinner.setAdapter(raceAdapter);

        //default to character's race
        raceSpinner.setSelection(charInstance.getOwnRace().getRaceIndex());

        //add level strings to dropdown menu
        Spinner levelSpinner = findViewById(R.id.levelDropdown);
        ArrayAdapter<CharSequence> levelAdapter = ArrayAdapter.createFromResource(this,
                R.array.levelCountArray, android.R.layout.simple_spinner_item);

        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        levelSpinner.setAdapter(levelAdapter);

        //find displays for maximum point input
        TextView maxDPDisplay = findViewById(R.id.maxDPCell);
        TextView maxCombatDisplay = findViewById(R.id.maxCombatCell);
        TextView maxMagDisplay = findViewById(R.id.maxMagCell);
        TextView maxPsyDisplay = findViewById(R.id.maxPsyCell);

        //set values to display
        maxDPDisplay.setText(getString(R.string.intItem, charInstance.getDevPT()));
        maxCombatDisplay.setText(getString(R.string.intItem, charInstance.getMaxCombatDP()));
        maxMagDisplay.setText(getString(R.string.intItem, charInstance.getMaxMagDP()));
        maxPsyDisplay.setText(getString(R.string.intItem, charInstance.getMaxPsyDP()));

        //find spend amount displays
        TextView spentDPDisplay = findViewById(R.id.usedDPCell);
        TextView spentCombatDisplay = findViewById(R.id.usedCombatCell);
        TextView spentMagDisplay = findViewById(R.id.usedMagCell);
        TextView spentPsyDisplay = findViewById(R.id.usedPsyCell);

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
        EditText strScore = findViewById(R.id.strScore);
        strScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText dexScore = findViewById(R.id.dexScore);
        dexScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText agiScore = findViewById(R.id.agiScore);
        agiScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText conScore = findViewById(R.id.conScore);
        conScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText intScore = findViewById(R.id.intScore);
        intScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText powScore = findViewById(R.id.powScore);
        powScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText wpScore = findViewById(R.id.wpScore);
        wpScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});

        EditText perScore = findViewById(R.id.perScore);
        perScore.setFilters(new InputFilter[]{new InputFilterMinMax(1, 20)});



        //get mod displays from the page
        TextView strMod = findViewById(R.id.strMod);
        TextView dexMod = findViewById(R.id.dexMod);
        TextView agiMod = findViewById(R.id.agiMod);
        TextView conMod = findViewById(R.id.conMod);
        TextView intMod = findViewById(R.id.intMod);
        TextView powMod = findViewById(R.id.powMod);
        TextView wpMod = findViewById(R.id.wpMod);
        TextView perMod = findViewById(R.id.perMod);



        //set all listeners for characteristic change to make mod text change to corresponding value
        strScore.addTextChangedListener(new CharacteristicInput(strScore, strMod, charInstance.setSTR, charInstance.getModSTR, this));
        dexScore.addTextChangedListener(new CharacteristicInput(dexScore, dexMod, charInstance.setDEX, charInstance.getModDEX, this));
        agiScore.addTextChangedListener(new CharacteristicInput(agiScore, agiMod, charInstance.setAGI, charInstance.getModAGI, this));
        conScore.addTextChangedListener(new CharacteristicInput(conScore, conMod, charInstance.setCON, charInstance.getModCON, this));
        intScore.addTextChangedListener(new CharacteristicInput(intScore, intMod, charInstance.setINT, charInstance.getModINT, this));
        powScore.addTextChangedListener(new CharacteristicInput(powScore, powMod, charInstance.setPOW, charInstance.getModPOW, this));
        wpScore.addTextChangedListener(new CharacteristicInput(wpScore, wpMod, charInstance.setWP, charInstance.getModWP,this));
        perScore.addTextChangedListener(new CharacteristicInput(perScore, perMod, charInstance.setPER, charInstance.getModPER, this));

        //set initial values for characteristics
        strScore.setText(getString(R.string.intItem, finalCharInstance.getSTR()));
        dexScore.setText(getString(R.string.intItem, finalCharInstance.getDEX()));
        agiScore.setText(getString(R.string.intItem, finalCharInstance.getAGI()));
        conScore.setText(getString(R.string.intItem, finalCharInstance.getCON()));
        intScore.setText(getString(R.string.intItem, finalCharInstance.getINT()));
        powScore.setText(getString(R.string.intItem, finalCharInstance.getPOW()));
        wpScore.setText(getString(R.string.intItem, finalCharInstance.getWP()));
        perScore.setText(getString(R.string.intItem, finalCharInstance.getPER()));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}