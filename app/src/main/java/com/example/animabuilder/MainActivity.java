package com.example.animabuilder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animabuilder.CharacterCreation.BaseCharacter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Intent toNextPage = new Intent(MainActivity.this, CharacterPageActivity.class);

        Button newCharButton = (Button)findViewById(R.id.newCharButton);
        Button loadCharButton = (Button)findViewById(R.id.loadCharButton);

        newCharButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View nameInputLayout = inflater.inflate(R.layout.new_char_alert, null, false);

                TextView nameInput = nameInputLayout.findViewById(R.id.newCharInput);

                AlertDialog nameInputAlert = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Save Character As:")
                        .setView(nameInputLayout)
                        .setPositiveButton("Create", null)
                        .setNegativeButton("Cancel", null)
                        .create();




                nameInputAlert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button saveButton = nameInputAlert.getButton(AlertDialog.BUTTON_POSITIVE);
                        saveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(nameInput.getText().toString().compareTo("") == 0){
                                    Toast.makeText(MainActivity.this, "File must have a name!", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    toNextPage.putExtra("isNew", true);
                                    toNextPage.putExtra("filename", nameInput.getText().toString());

                                    startActivity(toNextPage);
                                }
                            }
                        });

                        Button cancelButton = nameInputAlert.getButton(AlertDialog.BUTTON_NEGATIVE);
                        cancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                nameInputAlert.dismiss();
                            }
                        });
                    }
                });

                nameInputAlert.show();
            }
        });

        loadCharButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] returnName = new String[1];

                View fileSelectionGroup = inflater.inflate(R.layout.load_char_alert, null, false);
                RadioGroup selectionOptions = fileSelectionGroup.findViewById(R.id.loadOptions);

                String[] allFiles = MainActivity.this.fileList();

                for(String file: allFiles){
                    RadioButton fileSelection = new RadioButton(MainActivity.this);
                    fileSelection.setText(file);
                    selectionOptions.addView(fileSelection);
                }

                selectionOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        returnName[0] = allFiles[(checkedId-1)%allFiles.length];
                    }
                });

                AlertDialog findFileAlert = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Load Character:")
                        .setView(fileSelectionGroup)
                        .setPositiveButton("Load", null)
                        .setNegativeButton("Cancel", null)
                        .create();

                findFileAlert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button loadButton = findFileAlert.getButton(AlertDialog.BUTTON_POSITIVE);
                        loadButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(selectionOptions.getCheckedRadioButtonId() == -1){
                                    Toast.makeText(MainActivity.this, "Please select a file", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    toNextPage.putExtra("isNew", false);
                                    toNextPage.putExtra("filename", returnName[0]);
                                    Toast.makeText(MainActivity.this, returnName[0], Toast.LENGTH_SHORT).show();
                                    //startActivity(toNextPage);
                                }
                            }
                        });
                    }
                });

                findFileAlert.show();
            }
        });
    }
}