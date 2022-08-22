package com.example.animabuilder.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.animabuilder.Activities.Fragments.HomeFragments.CharacterPageFragment;
import com.example.animabuilder.CharacterCreation.BaseCharacter;
import com.example.animabuilder.R;
import com.example.animabuilder.SideNavSelection;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    //instantiate drawer layout and toggle
    public DrawerLayout pageDrawer;
    public ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentManager fm = getSupportFragmentManager();

        String filename = getIntent().getStringExtra("filename");
        BaseCharacter charInstance = (BaseCharacter) getIntent().getSerializableExtra("Character");

        //set drawer view and toggle
        pageDrawer = findViewById(R.id.homePageLayout);
        drawerToggle = new ActionBarDrawerToggle(this, pageDrawer, R.string.nav_open, R.string.nav_close);

        pageDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationView sideNav = findViewById(R.id.navViewSideBar);
        sideNav.setNavigationItemSelectedListener(new SideNavSelection(pageDrawer, filename, charInstance, this, fm));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CharacterPageFragment cpFrag = new CharacterPageFragment();

        FragmentTransaction ft = fm.beginTransaction();

        Bundle b = new Bundle();
        b.putSerializable("Character", charInstance);

        cpFrag.setArguments(b);

        ft.replace(R.id.currentFragment, cpFrag);
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}