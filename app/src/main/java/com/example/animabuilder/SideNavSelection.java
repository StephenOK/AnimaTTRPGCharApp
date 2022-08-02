package com.example.animabuilder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.animabuilder.CharacterCreation.BaseCharacter;
import com.google.android.material.navigation.NavigationView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SideNavSelection implements NavigationView.OnNavigationItemSelectedListener{

    int pageIndex;
    String filename;
    BaseCharacter saveObject;
    Context startPage;

    public SideNavSelection(int pageIndex, String filename, BaseCharacter saveObject, Context startPage){
        this.pageIndex = pageIndex;
        this.filename = filename;
        this.saveObject = saveObject;
        this.startPage = startPage;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.mainCharPageButton:
                if (pageIndex != 0) {
                    startPage.startActivity(new Intent(startPage, CharacterPageActivity.class));
                    return true;
                }
                break;

            case R.id.combatPageButton:
                if(pageIndex != 1) {
                    startPage.startActivity(new Intent(startPage, CombatActivity.class));
                    return true;
                }
                break;

            case R.id.magicPageButton:
                if(pageIndex != 2) {
                    startPage.startActivity(new Intent(startPage, MagicActivity.class));
                    return true;
                }
                break;

            case R.id.psychicPageButton:
                if(pageIndex != 3) {
                    startPage.startActivity(new Intent(startPage, PsychicActivity.class));
                    return true;
                }
                break;

            case R.id.secondariesPageButton:
                if(pageIndex != 4) {
                    startPage.startActivity(new Intent(startPage, SecondaryAbilityActivity.class));
                    return true;
                }
                break;

            case R.id.equipmentPageButton:
                if(pageIndex != 5){
                    startPage.startActivity(new Intent(startPage, EquipmentActivity.class));
                    return true;
                }
                break;

            case R.id.saveButton:

                return attemptSave();

            case R.id.exitButton:
                AlertDialog exitAlert = new AlertDialog.Builder(startPage)
                        .setTitle("Save before exiting?")
                        .setPositiveButton("Save", null)
                        .setNegativeButton("Exit", null)
                        .setNeutralButton("Go Back", null)
                        .create();

                exitAlert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button saveButton = exitAlert.getButton(AlertDialog.BUTTON_POSITIVE);
                        saveButton.setOnClickListener(v -> {
                            if(attemptSave()){
                                startPage.startActivity(new Intent(startPage, MainActivity.class));
                            }
                        });

                        Button continueButton = exitAlert.getButton(AlertDialog.BUTTON_NEGATIVE);
                        continueButton.setOnClickListener(v -> {
                            startPage.startActivity(new Intent(startPage, MainActivity.class));
                        });
                    }
                });

                exitAlert.show();

                return true;

            default: return false;
        }

        return false;
    }

    private boolean attemptSave(){
        try {
            FileOutputStream saveStream = startPage.getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
            byte[] charData = saveObject.getBytes();
            saveStream.write(charData);
            saveStream.close();
        }
        catch (FileNotFoundException e) {
            Toast.makeText(startPage, "Unable to find file!", Toast.LENGTH_SHORT).show();
            return false;
        } catch (IOException e) {
            Toast.makeText(startPage, "Failed to write data!", Toast.LENGTH_SHORT).show();
            return false;
        }

        Toast.makeText(startPage, "Save successful!", Toast.LENGTH_SHORT).show();
        return true;
    }
}
