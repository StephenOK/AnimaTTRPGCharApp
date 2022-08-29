package com.example.animabuilder.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import android.os.Bundle
import android.view.MenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import com.google.android.material.navigation.NavigationView
import com.example.animabuilder.SideNavSelection
import com.example.animabuilder.activities.fragments.home_fragments.CharacterPageFragment

/**
 * Activity that runs all character creation fragments
 * Initially loads the CharacterPageFragment
 */

class HomeActivity : AppCompatActivity() {

    //instantiate drawer layout and toggle
    private var pageDrawer: DrawerLayout? = null
    private var drawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        val fm = supportFragmentManager
        val filename = intent.getStringExtra("filename")
        val charInstance = intent.getSerializableExtra("Character") as BaseCharacter?

        //set drawer view and toggle
        pageDrawer = findViewById(R.id.homePageLayout)
        drawerToggle =
            ActionBarDrawerToggle(this, pageDrawer, R.string.nav_open, R.string.nav_close)
        pageDrawer?.addDrawerListener(drawerToggle!!)
        drawerToggle!!.syncState()
        val sideNav = findViewById<NavigationView>(R.id.navViewSideBar)
        sideNav.setNavigationItemSelectedListener(
            SideNavSelection(
                pageDrawer,
                filename,
                charInstance,
                this,
                fm
            )
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val cpFrag = CharacterPageFragment()
        val ft = fm.beginTransaction()
        val b = Bundle()
        b.putSerializable("Character", charInstance)
        cpFrag.arguments = b
        ft.replace(R.id.currentFragment, cpFrag)
        ft.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (drawerToggle!!.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(
            item
        )
    }

    @Preview
    @Composable
    private fun navDrawer(){

    }
}