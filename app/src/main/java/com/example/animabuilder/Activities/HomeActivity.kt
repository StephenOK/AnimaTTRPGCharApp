package com.example.animabuilder.Activities

import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import android.os.Bundle
import android.view.MenuItem
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animabuilder.R
import com.example.animabuilder.CharacterCreation.BaseCharacter
import com.google.android.material.navigation.NavigationView
import com.example.animabuilder.SideNavSelection
import com.example.animabuilder.Activities.Fragments.HomeFragments.CharacterPageFragment
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    //instantiate drawer layout and toggle
    var pageDrawer: DrawerLayout? = null
    var drawerToggle: ActionBarDrawerToggle? = null

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