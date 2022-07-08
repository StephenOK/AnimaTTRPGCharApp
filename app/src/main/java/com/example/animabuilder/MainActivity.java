package com.example.animabuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newCharButton = (Button)findViewById(R.id.newCharButton);
        Button loadCharButton = (Button)findViewById(R.id.loadCharButton);

        newCharButton.setOnClickListener(
                v -> startActivity(new Intent(MainActivity.this, CharacterPageActivity.class)));
    }
}