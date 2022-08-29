package com.example.animabuilder

import android.content.Context
import androidx.drawerlayout.widget.DrawerLayout
import com.example.animabuilder.character_creation.BaseCharacter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import com.example.animabuilder.activities.fragments.home_fragments.CharacterPageFragment
import com.example.animabuilder.activities.fragments.home_fragments.CombatFragment
import com.example.animabuilder.activities.fragments.home_fragments.MagicFragment
import com.example.animabuilder.activities.fragments.home_fragments.PsychicFragment
import com.example.animabuilder.activities.fragments.home_fragments.SecondaryAbilityFragment
import com.example.animabuilder.activities.fragments.home_fragments.EquipmentFragment
import android.content.Intent
import com.example.animabuilder.activities.MainActivity
import android.widget.Toast
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.io.FileNotFoundException
import java.io.IOException

class SideNavSelection(
    var parent: DrawerLayout?,
    private var filename: String?,
    private var saveObject: BaseCharacter?,
    private var startPage: AppCompatActivity,
    private var fm: FragmentManager
) : NavigationView.OnNavigationItemSelectedListener {
    //default page index to 0
    private var pageIndex = 0

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //go to main character page
            R.id.mainCharPageButton -> if (pageIndex != 0) {
                transferTo(CharacterPageFragment(), 0)
                return true
            }

            //go to combat stat page
            R.id.combatPageButton -> if (pageIndex != 1) {
                transferTo(CombatFragment(), 1)
                return true
            }

            //go to magic stat page
            R.id.magicPageButton -> if (pageIndex != 2) {
                transferTo(MagicFragment(), 2)
                return true
            }

            //go to psychic stat page
            R.id.psychicPageButton -> if (pageIndex != 3) {
                transferTo(PsychicFragment(), 3)
                return true
            }

            //go to secondary characteristic page
            R.id.secondariesPageButton -> if (pageIndex != 4) {
                transferTo(SecondaryAbilityFragment(), 4)
                return true
            }

            //go to equipment page
            R.id.equipmentPageButton -> if (pageIndex != 5) {
                transferTo(EquipmentFragment(), 5)
                return true
            }

            //save the BaseCharacter
            R.id.saveButton -> return attemptSave()

            //leave character building
            R.id.exitButton -> {
                //create exit alert
                val exitAlert = AlertDialog.Builder(
                    startPage
                )
                    .setTitle("Save before exiting?")
                    .setPositiveButton("Save", null)
                    .setNegativeButton("Exit", null)
                    .setNeutralButton("Go Back", null)
                    .create()

                exitAlert.setOnShowListener {
                    //set positive button to save and exit
                    val saveButton = exitAlert.getButton(AlertDialog.BUTTON_POSITIVE)
                    saveButton.setOnClickListener {
                        if (attemptSave()) {
                            startPage.startActivity(Intent(startPage, MainActivity::class.java))
                        }
                    }

                    //set negative button to just exit to start page
                    val continueButton = exitAlert.getButton(AlertDialog.BUTTON_NEGATIVE)
                    continueButton.setOnClickListener {
                        startPage.startActivity(
                            Intent(
                                startPage, MainActivity::class.java
                            )
                        )
                    }
                }
                exitAlert.show()
                return true
            }
            else -> return false
        }
        return false
    }

    /**
     * Writes the BaseCharacter's bytes to file
     */
    private fun attemptSave(): Boolean {
        try {
            //attempt to write character's bytes to file
            val saveStream = startPage.openFileOutput(filename, Context.MODE_PRIVATE)
            val charData = saveObject?.bytes
            saveStream.write(charData)
            saveStream.close()
        } catch (e: FileNotFoundException) {
            Toast.makeText(startPage, "Unable to find file!", Toast.LENGTH_SHORT).show()
            return false
        } catch (e: IOException) {
            Toast.makeText(startPage, "Failed to write data!", Toast.LENGTH_SHORT).show()
            return false
        }

        //notify user of successful save action
        Toast.makeText(startPage, "Save successful!", Toast.LENGTH_SHORT).show()
        parent?.closeDrawers()
        return true
    }

    /**
     * Initialize the fragment indicated by the user
     */
    private fun transferTo(nextPage: Fragment, x: Int) {
        //set page index to indicated number
        pageIndex = x

        //process transaction
        val ft = fm.beginTransaction()

        //retrieve passed items
        val b = Bundle()
        b.putSerializable("Character", saveObject)
        nextPage.arguments = b

        //replace fragment with new fragment
        ft.replace(R.id.currentFragment, nextPage)
        ft.commit()
        parent?.closeDrawers()
    }
}