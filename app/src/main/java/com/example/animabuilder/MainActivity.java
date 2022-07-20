package com.example.animabuilder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
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

        AlertDialog.Builder nameInputAlert = new AlertDialog.Builder(this);

        EditText nameInput = new EditText(MainActivity.this);

        nameInputAlert.setTitle("Save Character As:");
        nameInputAlert.setView(nameInput);

        LinearLayout nameInputLayout = new LinearLayout(this);
        nameInputLayout.setOrientation(LinearLayout.VERTICAL);
        nameInputLayout.addView(nameInput);
        nameInputAlert.setView(nameInputLayout);

        nameInputAlert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BaseCharacter newInstance = new BaseCharacter();
                toNextPage.putExtra("isNew", true);
                toNextPage.putExtra("filename", nameInput.getText().toString());

                startActivity(toNextPage);
            }
        });

        nameInputAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        Button newCharButton = (Button)findViewById(R.id.newCharButton);
        Button loadCharButton = (Button)findViewById(R.id.loadCharButton);

        newCharButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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