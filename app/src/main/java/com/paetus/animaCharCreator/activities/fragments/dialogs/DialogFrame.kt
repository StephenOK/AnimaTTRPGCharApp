package com.paetus.animaCharCreator.activities.fragments.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.paetus.animaCharCreator.theme.detailLightColors

/**
 * Composes a dialog object with the inputted title, content, and buttons.
 *
 * @param dialogTitle header to display for the individual item
 * @param mainContent what to display in the main body of the dialog
 * @param buttonContent bottom row buttons to display for the dialog option
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogFrame(
    dialogTitle: String,
    mainContent: @Composable () -> Unit,
    buttonContent: @Composable () -> Unit
){
    MaterialTheme(colorScheme = detailLightColors) {
        BasicAlertDialog(
            //prevent user from dismissing dialog
            onDismissRequest = {},
            content = {
                Surface {
                    Box(
                        Modifier
                            .size(600.dp, 600.dp)
                    ) {
                        //dialog title
                        Row(
                            Modifier
                                .align(Alignment.TopCenter)
                                .height(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = dialogTitle,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        //main body
                        Row(
                            Modifier
                                .align(Alignment.Center)
                                .height(500.dp)
                                .fillMaxWidth(0.85f)
                        ) {
                            mainContent()
                        }

                        //bottom buttons
                        Row(
                            Modifier
                                .align(Alignment.BottomCenter)
                                .height(50.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            buttonContent()
                        }
                    }
                }
            }
        )
    }
}