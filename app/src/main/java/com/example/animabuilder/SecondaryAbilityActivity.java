package com.example.animabuilder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.animabuilder.CharacterCreation.BaseCharacter;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class SecondaryAbilityActivity extends AppCompatActivity {

    public DrawerLayout pageDrawer;
    public ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_ability);

        String filename = "";
        BaseCharacter charInstance = new BaseCharacter();

        //set drawer view and toggle
        pageDrawer = findViewById(R.id.charPageLayout);
        drawerToggle = new ActionBarDrawerToggle(this, pageDrawer, R.string.nav_open, R.string.nav_close);

        pageDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        NavigationView sideNav = findViewById(R.id.navViewSideBar);
        sideNav.setNavigationItemSelectedListener(new SideNavSelection(0, filename, charInstance, this));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}