package com.example.animabuilder

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailButton(
    onClick: () -> Unit,
    modifier: Modifier
){
    TextButton(
        onClick = {onClick()},
        modifier = modifier
    ) {
        Text(text = "Details")
    }
}