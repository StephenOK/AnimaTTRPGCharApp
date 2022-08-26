package com.example.animabuilder.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animabuilder.R
import android.view.LayoutInflater
import android.content.Intent
import com.example.animabuilder.Activities.HomeActivity
import android.content.DialogInterface.OnShowListener
import android.content.DialogInterface
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.animabuilder.CharacterCreation.BaseCharacter
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize layout inflater
        val inflater = this@MainActivity.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //initialize intent to character page
        val toNextPage = Intent(this@MainActivity, HomeActivity::class.java)

        //find buttons in layout
        val newCharButton = findViewById<Button>(R.id.newCharButton)
        val loadCharButton = findViewById<Button>(R.id.loadCharButton)

        //set click action for new character button
        newCharButton.setOnClickListener { view: View? ->
            //find alert's layout
            val nameInputLayout = inflater.inflate(R.layout.new_char_alert, null, false)

            //find name input from alert
            val nameInput = nameInputLayout.findViewById<TextView>(R.id.newCharInput)

            //create alert dialog
            val nameInputAlert = AlertDialog.Builder(this@MainActivity)
                .setTitle("Save Character As:")
                .setView(nameInputLayout)
                .setPositiveButton("Create", null)
                .setNegativeButton("Cancel", null)
                .create()

            //set show alert action
            nameInputAlert.setOnShowListener { dialog: DialogInterface? ->
                val charInstance = BaseCharacter()

                //set confirmation action
                val saveButton = nameInputAlert.getButton(AlertDialog.BUTTON_POSITIVE)
                saveButton.setOnClickListener { v: View? ->
                    //check if name input is given
                    if (nameInput.text.toString().compareTo("") == 0) {
                        //notify user of invalid input
                        Toast.makeText(
                            this@MainActivity,
                            "File must have a name!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        //send new notification and file's name to next page
                        toNextPage.putExtra("filename", nameInput.text.toString())
                        toNextPage.putExtra("Character", charInstance)

                        //start next activity
                        startActivity(toNextPage)
                    }
                }

                //set cancel button action
                val cancelButton = nameInputAlert.getButton(AlertDialog.BUTTON_NEGATIVE)
                cancelButton.setOnClickListener { v: View? -> nameInputAlert.dismiss() }
            }

            //show alert
            nameInputAlert.show()
        }

        //set click action for load character button
        loadCharButton.setOnClickListener { view: View? ->
            //prepare string for intent
            val returnName = arrayOfNulls<String>(1)

            //find alert's layout
            val fileSelectionGroup = inflater.inflate(R.layout.load_char_alert, null, false)

            //find radio group from alert
            val selectionOptions = fileSelectionGroup.findViewById<RadioGroup>(R.id.loadOptions)

            //get list of files
            val allFiles = fileList()

            //add a radio button for each file available
            for (file in allFiles) {
                val fileSelection = RadioButton(this@MainActivity)
                fileSelection.text = file
                selectionOptions.addView(fileSelection)
            }

            //set string name to checked option
            selectionOptions.setOnCheckedChangeListener { group: RadioGroup?, checkedId: Int ->
                returnName[0] = allFiles[(checkedId - 1) % allFiles.size]
            }

            //create alert dialog
            val findFileAlert = AlertDialog.Builder(this@MainActivity)
                .setTitle("Load Character:")
                .setView(fileSelectionGroup)
                .setPositiveButton("Load", null)
                .setNegativeButton("Cancel", null)
                .create()

            //set show alert action
            findFileAlert.setOnShowListener { dialog: DialogInterface? ->

                //set confirmation action
                val loadButton = findFileAlert.getButton(AlertDialog.BUTTON_POSITIVE)
                loadButton.setOnClickListener { v: View? ->
                    val charInstance: BaseCharacter

                    //check if item is selected
                    if (selectionOptions.checkedRadioButtonId == -1) {
                        //notify user of invalid input
                        Toast.makeText(
                            this@MainActivity,
                            "Please select a file",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        //retreive file info
                        val inputFile = File(this.filesDir, returnName[0])

                        //instantiate character off of file data
                        charInstance = BaseCharacter(inputFile)
                        toNextPage.putExtra("filename", returnName[0])
                        toNextPage.putExtra("Character", charInstance)

                        //start next activity
                        startActivity(toNextPage)
                    }
                }
            }

            //show alert
            findFileAlert.show()
        }
    }
}