package com.paetus.animaCharCreator.activities.fragments.dialogs

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SecondaryList
import com.paetus.animaCharCreator.composables.OutlinedDropdown
import com.paetus.animaCharCreator.composables.TextInput
import com.paetus.animaCharCreator.view_models.models.CustomSecondaryViewModel
import com.paetus.animaCharCreator.writeDataTo
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

/**
 * Dialog for the user to create their own custom secondary characteristic.
 * Users select the characteristic's field and primary characteristic bonus applied to it.
 *
 * @param secondaryList character's secondary characteristic section
 * @param filename file to associate with the characteristic
 * @param customSecondVM viewModel for this dialog
 */
@Composable
fun CustomSecondaryDialog(
    secondaryList: SecondaryList,
    filename: String,
    customSecondVM: CustomSecondaryViewModel
){
    //get local context
    val context = LocalContext.current

    //set the current file name for this custom secondary characteristic
    customSecondVM.customSecondary.setFilename(filename = filename)

    DialogFrame(
        dialogTitle = stringResource(id = R.string.customCharacteristicTitle),
        mainContent = {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                //characteristic name input
                item{
                    TextInput(
                        display = customSecondVM.charName.collectAsState().value,
                        onValueChange = {customSecondVM.setCustomName(nameInput = it)}
                    )
                }

                item{Spacer(modifier = Modifier.height(10.dp))}

                //field and characteristic selection
                items(customSecondVM.allDropdowns){dropdown ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedDropdown(
                            data = dropdown
                        )
                    }
                }

                item{Spacer(modifier = Modifier.height(10.dp))}

                //make characteristic public
                item{
                    Row{
                        Checkbox(
                            checked = customSecondVM.secondaryIsPublic.collectAsState().value,
                            onCheckedChange = {
                                customSecondVM.setSecondaryPublic(isPublic = it)
                            }
                        )

                        Text(
                            text = stringResource(id = R.string.publicPrompt)
                        )
                    }
                }
            }
        },
        buttonContent = {
            Row{
                TextButton(
                    onClick = {
                        //if a name is given
                        if(customSecondVM.customSecondary.name.value != "") {
                            //create filename
                            val secondaryFilename = "${context.filesDir}/CustomSecondaryDIR/${customSecondVM.customSecondary.name.value}"

                            //determine if file exists
                            val prevFile = File(secondaryFilename)

                            //if file does not exist
                            if(!prevFile.exists()) {
                                //initialize file writer
                                val saveSecChar = FileOutputStream(prevFile)
                                val secCharData = ByteArrayOutputStream()

                                //write characteristic's public state
                                writeDataTo(
                                    writer = secCharData,
                                    input = customSecondVM.customSecondary.isPublic.value
                                )

                                //write characteristic's associated character
                                writeDataTo(
                                    writer = secCharData,
                                    input = filename
                                )

                                //write characteristic's name
                                writeDataTo(
                                    writer = secCharData,
                                    input = customSecondVM.charName.value
                                )

                                //write characteristic's associated field
                                writeDataTo(
                                    writer = secCharData,
                                    input = customSecondVM.customSecondary.fieldIndex.intValue
                                )

                                //write characteristic's primary stat
                                writeDataTo(
                                    writer = secCharData,
                                    input = customSecondVM.customSecondary.primaryCharIndex.intValue
                                )

                                secCharData.close()

                                //write characteristic data
                                saveSecChar.write(secCharData.toByteArray())
                                saveSecChar.close()

                                //apply new characteristic to file
                                secondaryList.addCustomSecondary(newSecondary = customSecondVM.customSecondary)

                                //apply new characteristic to fragment
                                customSecondVM.secondarySource.addCharToField(
                                    characteristic = customSecondVM.customSecondary,
                                    field = customSecondVM.customSecondary.fieldIndex.intValue
                                )

                                customSecondVM.secondarySource.toggleCustomOpen()
                            }

                            //notify user of existing custom characteristic
                            else{
                                Toast.makeText(
                                    context,
                                    R.string.duplicateCustomSecondary,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        //notify user of name requirement
                        else{
                            Toast.makeText(
                                context,
                                context.getString(R.string.nameCustomSecondary),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.createLabel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                TextButton(
                    onClick = {
                        customSecondVM.secondarySource.toggleCustomOpen()
                    }
                ){
                    Text(
                        text = stringResource(id = R.string.cancelLabel),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    )
}