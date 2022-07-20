package com.example.animabuilder;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;

public class SideNavSelection implements NavigationView.OnNavigationItemSelectedListener{

    int pageIndex;
    Context startPage;

    public SideNavSelection(int pageIndex, String filenamae, Context startPage){
        this.pageIndex = pageIndex;
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

                return false;

            default: return false;
        }

        return false;
    }
}
