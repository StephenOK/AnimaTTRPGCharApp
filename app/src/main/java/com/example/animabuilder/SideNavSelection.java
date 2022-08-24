package com.example.animabuilder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.animabuilder.Activities.Fragments.HomeFragments.CharacterPageFragment;
import com.example.animabuilder.Activities.Fragments.HomeFragments.CombatFragment;
import com.example.animabuilder.Activities.Fragments.HomeFragments.EquipmentFragment;
import com.example.animabuilder.Activities.Fragments.HomeFragments.MagicFragment;
import com.example.animabuilder.Activities.MainActivity;
import com.example.animabuilder.Activities.Fragments.HomeFragments.PsychicFragment;
import com.example.animabuilder.Activities.Fragments.HomeFragments.SecondaryAbilityFragment;
import com.example.animabuilder.CharacterCreation.BaseCharacter;
import com.google.android.material.navigation.NavigationView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SideNavSelection implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout parent;
    int pageIndex;
    String filename;
    BaseCharacter saveObject;
    AppCompatActivity startPage;
    FragmentManager fm;

    public SideNavSelection(DrawerLayout parent,String filename, BaseCharacter saveObject, AppCompatActivity startPage, FragmentManager fm){
        this.parent = parent;
        pageIndex = 0;
        this.filename = filename;
        this.saveObject = saveObject;
        this.startPage = startPage;
        this.fm = fm;
    }

    private void setPageIndex(int x){
        pageIndex = x;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.mainCharPageButton:
                if (pageIndex != 0) {
                    transferTo(new CharacterPageFragment(), 0);
                    return true;
                }
                break;

            case R.id.combatPageButton:
                if(pageIndex != 1) {
                    transferTo(new CombatFragment(), 1);
                    return true;
                }
                break;

            case R.id.magicPageButton:
                if(pageIndex != 2) {
                    transferTo(new MagicFragment(), 2);
                    return true;
                }
                break;

            case R.id.psychicPageButton:
                if(pageIndex != 3) {
                    transferTo(new PsychicFragment(), 3);
                    return true;
                }
                break;

            case R.id.secondariesPageButton:
                if(pageIndex != 4) {
                    transferTo(new SecondaryAbilityFragment(), 4);
                    return true;
                }
                break;

            case R.id.equipmentPageButton:
                if(pageIndex != 5){
                    transferTo(new EquipmentFragment(), 5);
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
                            if(attemptSave()) {

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
            FileOutputStream saveStream = startPage.openFileOutput(filename, Context.MODE_PRIVATE);
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

        parent.closeDrawers();
        return true;
    }

    private void transferTo(Fragment nextPage, int x){
        setPageIndex(x);

        FragmentTransaction ft = fm.beginTransaction();

        Bundle b = new Bundle();
        b.putSerializable("Character", saveObject);
        nextPage.setArguments(b);

        ft.replace(R.id.currentFragment, nextPage);
        ft.commit();

        parent.closeDrawers();
    }
}
