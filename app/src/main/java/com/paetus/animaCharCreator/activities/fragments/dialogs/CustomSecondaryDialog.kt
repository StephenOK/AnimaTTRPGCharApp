package com.paetus.animaCharCreator.activities.fragments.dialogs

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.toSize
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SecondaryList
import com.paetus.animaCharCreator.composables.OutlinedDropdown
import com.paetus.animaCharCreator.composables.TextInput
import com.paetus.animaCharCreator.view_models.models.CustomSecondaryViewModel
import com.paetus.animaCharCreator.writeDataTo
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

@Composable
fun CustomSecondaryDialog(
    customSecondVM: CustomSecondaryViewModel,
    secondaryList: SecondaryList,
    filename: String
){
    val context = LocalContext.current

    customSecondVM.customSecondary.setFilename(filename)

    DialogFrame(
        dialogTitle = stringResource(R.string.customCharacteristicTitle),
        mainContent = {
            LazyColumn{
                //characteristic name input
                item{
                    TextInput(
                        display = customSecondVM.charName.collectAsState().value,
                        onValueChange = {customSecondVM.setCustomName(it)}
                    )
                }

                //field and characteristic selection
                items(customSecondVM.allDropdowns){
                    OutlinedDropdown(
                        optionsRef = it.optionsRef,
                        index = it.index.collectAsState().value,
                        openState = it.isOpen.collectAsState().value,
                        labelRef = it.labelRef,
                        icon = it.icon.collectAsState().value,
                        size = it.size.collectAsState().value,
                        sizeSetter = {coordinates ->
                            it.setSize(coordinates.size.toSize())
                        },
                        itemSelection = {index ->
                            it.setIndex(index)
                        },
                        openFunc = {it.openToggle()}
                    )
                }

                //make characteristic public
                item{
                    Row{
                        Checkbox(
                            checked = customSecondVM.secondaryIsPublic.collectAsState().value,
                            onCheckedChange = {
                                customSecondVM.setSecondaryPublic(it)
                            }
                        )

                        Text(
                            text = stringResource(R.string.publicPrompt)
                        )
                    }
                }
            }
        },
        buttonContent = {
            Row{
                TextButton(
                    onClick = {
                        if(customSecondVM.customSecondary.name.value != "") {
                            val secondaryFilename = "${context.filesDir}/CustomSecondaryDIR/${customSecondVM.customSecondary.name.value}"
                            val prevFile = File(secondaryFilename)

                            if(!prevFile.exists()) {
                                val saveSecChar = FileOutputStream(prevFile)

                                val secCharData = ByteArrayOutputStream()
                                writeDataTo(
                                    secCharData,
                                    customSecondVM.customSecondary.isPublic.value
                                )
                                writeDataTo(secCharData, filename)
                                writeDataTo(secCharData, customSecondVM.charName.value)
                                writeDataTo(
                                    secCharData,
                                    customSecondVM.customSecondary.fieldIndex.value
                                )
                                writeDataTo(
                                    secCharData,
                                    customSecondVM.customSecondary.primaryCharIndex.value
                                )
                                secCharData.close()

                                saveSecChar.write(secCharData.toByteArray())
                                saveSecChar.close()

                                secondaryList.addCustomSecondary(customSecondVM.customSecondary)
                                customSecondVM.secondarySource.addCharToField(
                                    customSecondVM.customSecondary,
                                    customSecondVM.customSecondary.fieldIndex.value
                                )

                                customSecondVM.secondarySource.toggleCustomOpen()
                            }

                            else{
                                Toast.makeText(
                                    context,
                                    R.string.duplicateCustomSecondary,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        else{
                            Toast.makeText(
                                context,
                                context.getString(R.string.nameCustomSecondary),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                ) {
                    Text(text = stringResource(R.string.createLabel))
                }

                TextButton(
                    onClick = {
                        customSecondVM.secondarySource.toggleCustomOpen()
                    }
                ){
                    Text(text = stringResource(R.string.cancelLabel))
                }
            }
        }
    )
}