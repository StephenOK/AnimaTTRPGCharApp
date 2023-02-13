package com.example.animabuilder.activities.fragments.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogFrame(
    dialogTitle: String,
    mainContent: @Composable () -> Unit,
    buttonContent: @Composable () -> Unit
){
    Dialog(
        onDismissRequest = {},
        content = {
            Box(
                Modifier
                    .background(Color.White)
                    .size(600.dp, 600.dp)
            ){
                Row(
                    Modifier
                        .align(Alignment.TopCenter)
                        .height(50.dp)
                ){
                    Text(
                        text = dialogTitle
                    )
                }
                Row(
                    Modifier
                        .align(Alignment.Center)
                        .height(500.dp)
                ){
                    mainContent()
                }
                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .height(50.dp)
                        .fillMaxWidth()
                ){
                    buttonContent()
                }
            }
        }
    )
}