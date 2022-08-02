package com.example.animabuilder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animabuilder.CharacterCreation.BaseCharacter;

import java.io.Serializable;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseCharacter testo = new BaseCharacter();
        //initialize layout inflater
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //initialize intent to character page
        Intent toNextPage = new Intent(MainActivity.this, CharacterPageActivity.class);

        //find buttons in layout
        Button newCharButton = findViewById(R.id.newCharButton);
        Button loadCharButton = findViewById(R.id.loadCharButton);

        //set click action for new character button
        newCharButton.setOnClickListener(view -> {
            //find alert's layout
            View nameInputLayout = inflater.inflate(R.layout.new_char_alert, null, false);

            //find name input from alert
            TextView nameInput = nameInputLayout.findViewById(R.id.newCharInput);

            //create alert dialog
            AlertDialog nameInputAlert = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Save Character As:")
                    .setView(nameInputLayout)
                    .setPositiveButton("Create", null)
                    .setNegativeButton("Cancel", null)
                    .create();

            //set show alert action
            nameInputAlert.setOnShowListener(dialog -> {
                //set confirmation action
                Button saveButton = nameInputAlert.getButton(AlertDialog.BUTTON_POSITIVE);
                saveButton.setOnClickListener(v -> {
                    //check if name input is given
                    if(nameInput.getText().toString().compareTo("") == 0){
                        //notify user of invalid input
                        Toast.makeText(MainActivity.this, "File must have a name!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //send new notification and file's name to next page
                        toNextPage.putExtra("isNew", true);
                        toNextPage.putExtra("filename", nameInput.getText().toString());
                        toNextPage.putExtra("object", (Serializable) testo);

                        //start next activity
                        startActivity(toNextPage);
                    }
                });

                //set cancel button action
                Button cancelButton = nameInputAlert.getButton(AlertDialog.BUTTON_NEGATIVE);
                cancelButton.setOnClickListener(v -> nameInputAlert.dismiss());
            });

            //show alert
            nameInputAlert.show();
        });

        //set click action for load character button
        loadCharButton.setOnClickListener(view -> {
            //prepare string for intent
            final String[] returnName = new String[1];

            //find alert's layout
            View fileSelectionGroup = inflater.inflate(R.layout.load_char_alert, null, false);

            //find radio group from alert
            RadioGroup selectionOptions = fileSelectionGroup.findViewById(R.id.loadOptions);

            //get list of files
            String[] allFiles = MainActivity.this.fileList();

            //add a radio button for each file available
            for(String file: allFiles){
                RadioButton fileSelection = new RadioButton(MainActivity.this);
                fileSelection.setText(file);
                selectionOptions.addView(fileSelection);
            }

            //set string name to checked option
            selectionOptions.setOnCheckedChangeListener((group, checkedId) -> returnName[0] = allFiles[(checkedId-1)%allFiles.length]);

            //create alert dialog
            AlertDialog findFileAlert = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Load Character:")
                    .setView(fileSelectionGroup)
                    .setPositiveButton("Load", null)
                    .setNegativeButton("Cancel", null)
                    .create();

            //set show alert action
            findFileAlert.setOnShowListener(dialog -> {
                //set confirmation action
                Button loadButton = findFileAlert.getButton(AlertDialog.BUTTON_POSITIVE);
                loadButton.setOnClickListener(v -> {
                    //check if item is selected
                    if(selectionOptions.getCheckedRadioButtonId() == -1){
                        //notify user of invalid input
                        Toast.makeText(MainActivity.this, "Please select a file", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //send load notification and file's name to next page
                        toNextPage.putExtra("isNew", false);
                        toNextPage.putExtra("filename", returnName[0]);

                        //start next activity
                        startActivity(toNextPage);
                    }
                });
            });

            //show alert
            findFileAlert.show();
        });
    }
}