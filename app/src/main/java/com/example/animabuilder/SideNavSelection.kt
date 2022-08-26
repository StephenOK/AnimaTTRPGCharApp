package com.example.animabuilder

import android.content.Context
import androidx.drawerlayout.widget.DrawerLayout
import com.example.animabuilder.CharacterCreation.BaseCharacter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import com.example.animabuilder.R
import com.example.animabuilder.Activities.Fragments.HomeFragments.CharacterPageFragment
import com.example.animabuilder.Activities.Fragments.HomeFragments.CombatFragment
import com.example.animabuilder.Activities.Fragments.HomeFragments.MagicFragment
import com.example.animabuilder.Activities.Fragments.HomeFragments.PsychicFragment
import com.example.animabuilder.Activities.Fragments.HomeFragments.SecondaryAbilityFragment
import com.example.animabuilder.Activities.Fragments.HomeFragments.EquipmentFragment
import android.content.DialogInterface.OnShowListener
import android.content.DialogInterface
import android.content.Intent
import com.example.animabuilder.Activities.MainActivity
import android.widget.Toast
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.io.FileNotFoundException
import java.io.IOException

class SideNavSelection(
    var parent: DrawerLayout?,
    var filename: String?,
    var saveObject: BaseCharacter?,
    var startPage: AppCompatActivity,
    var fm: FragmentManager
) : NavigationView.OnNavigationItemSelectedListener {
    var pageIndex = 0

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mainCharPageButton -> if (pageIndex != 0) {
                transferTo(CharacterPageFragment(), 0)
                return true
            }
            R.id.combatPageButton -> if (pageIndex != 1) {
                transferTo(CombatFragment(), 1)
                return true
            }
            R.id.magicPageButton -> if (pageIndex != 2) {
                transferTo(MagicFragment(), 2)
                return true
            }
            R.id.psychicPageButton -> if (pageIndex != 3) {
                transferTo(PsychicFragment(), 3)
                return true
            }
            R.id.secondariesPageButton -> if (pageIndex != 4) {
                transferTo(SecondaryAbilityFragment(), 4)
                return true
            }
            R.id.equipmentPageButton -> if (pageIndex != 5) {
                transferTo(EquipmentFragment(), 5)
                return true
            }
            R.id.saveButton -> return attemptSave()
            R.id.exitButton -> {
                val exitAlert = AlertDialog.Builder(
                    startPage
                )
                    .setTitle("Save before exiting?")
                    .setPositiveButton("Save", null)
                    .setNegativeButton("Exit", null)
                    .setNeutralButton("Go Back", null)
                    .create()
                exitAlert.setOnShowListener {
                    val saveButton = exitAlert.getButton(AlertDialog.BUTTON_POSITIVE)
                    saveButton.setOnClickListener { v: View? ->
                        if (attemptSave()) {
                            startPage.startActivity(Intent(startPage, MainActivity::class.java))
                        }
                    }
                    val continueButton = exitAlert.getButton(AlertDialog.BUTTON_NEGATIVE)
                    continueButton.setOnClickListener { v: View? ->
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

    private fun attemptSave(): Boolean {
        try {
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
        Toast.makeText(startPage, "Save successful!", Toast.LENGTH_SHORT).show()
        parent?.closeDrawers()
        return true
    }

    private fun transferTo(nextPage: Fragment, x: Int) {
        pageIndex = x
        val ft = fm.beginTransaction()
        val b = Bundle()
        b.putSerializable("Character", saveObject)
        nextPage.arguments = b
        ft.replace(R.id.currentFragment, nextPage)
        ft.commit()
        parent?.closeDrawers()
    }
}