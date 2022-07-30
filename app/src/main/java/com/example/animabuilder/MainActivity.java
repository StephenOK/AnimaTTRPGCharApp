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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Intent toNextPage = new Intent(MainActivity.this, CharacterPageActivity.class);

        Button newCharButton = findViewById(R.id.newCharButton);
        Button loadCharButton = findViewById(R.id.loadCharButton);

        newCharButton.setOnClickListener(view -> {
            View nameInputLayout = inflater.inflate(R.layout.new_char_alert, null, false);

            TextView nameInput = nameInputLayout.findViewById(R.id.newCharInput);

            AlertDialog nameInputAlert = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Save Character As:")
                    .setView(nameInputLayout)
                    .setPositiveButton("Create", null)
                    .setNegativeButton("Cancel", null)
                    .create();




            nameInputAlert.setOnShowListener(dialog -> {
                Button saveButton = nameInputAlert.getButton(AlertDialog.BUTTON_POSITIVE);
                saveButton.setOnClickListener(v -> {
                    if(nameInput.getText().toString().compareTo("") == 0){
                        Toast.makeText(MainActivity.this, "File must have a name!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        toNextPage.putExtra("isNew", true);
                        toNextPage.putExtra("filename", nameInput.getText().toString());

                        startActivity(toNextPage);
                    }
                });

                Button cancelButton = nameInputAlert.getButton(AlertDialog.BUTTON_NEGATIVE);
                cancelButton.setOnClickListener(v -> nameInputAlert.dismiss());
            });

            nameInputAlert.show();
        });

        loadCharButton.setOnClickListener(view -> {
            final String[] returnName = new String[1];

            View fileSelectionGroup = inflater.inflate(R.layout.load_char_alert, null, false);
            RadioGroup selectionOptions = fileSelectionGroup.findViewById(R.id.loadOptions);

            String[] allFiles = MainActivity.this.fileList();

            for(String file: allFiles){
                RadioButton fileSelection = new RadioButton(MainActivity.this);
                fileSelection.setText(file);
                selectionOptions.addView(fileSelection);
            }

            selectionOptions.setOnCheckedChangeListener((group, checkedId) -> returnName[0] = allFiles[(checkedId-1)%allFiles.length]);

            AlertDialog findFileAlert = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Load Character:")
                    .setView(fileSelectionGroup)
                    .setPositiveButton("Load", null)
                    .setNegativeButton("Cancel", null)
                    .create();

            findFileAlert.setOnShowListener(dialog -> {
                Button loadButton = findFileAlert.getButton(AlertDialog.BUTTON_POSITIVE);
                loadButton.setOnClickListener(v -> {
                    if(selectionOptions.getCheckedRadioButtonId() == -1){
                        Toast.makeText(MainActivity.this, "Please select a file", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        toNextPage.putExtra("isNew", false);
                        toNextPage.putExtra("filename", returnName[0]);

                        startActivity(toNextPage);
                    }
                });
            });

            findFileAlert.show();
        });
    }
}