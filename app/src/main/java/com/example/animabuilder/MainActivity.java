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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

        Intent toNextPage = new Intent(MainActivity.this, CharacterPageActivity.class);

        Button newCharButton = (Button)findViewById(R.id.newCharButton);
        Button loadCharButton = (Button)findViewById(R.id.loadCharButton);

        newCharButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameInput = new EditText(MainActivity.this);
                LinearLayout nameInputLayout = new LinearLayout(MainActivity.this);
                nameInputLayout.setOrientation(LinearLayout.VERTICAL);
                nameInputLayout.addView(nameInput);

                AlertDialog nameInputAlert = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Save Character As:")
                        .setView(nameInputLayout)
                        .setPositiveButton("Save", null)
                        .setNegativeButton("Cancel", null)
                        .create();




                nameInputAlert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button saveButton = ((AlertDialog)nameInputAlert).getButton(AlertDialog.BUTTON_POSITIVE);
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

                        Button cancelButton = ((AlertDialog)nameInputAlert).getButton(AlertDialog.BUTTON_NEGATIVE);
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
                //startActivity(toNextPage);
            }
        });
    }
}