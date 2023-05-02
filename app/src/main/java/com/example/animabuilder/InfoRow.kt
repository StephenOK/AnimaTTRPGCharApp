package com.example.animabuilder

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

/**
 * Display item that holds a label and the associated amount.
 *
 * @param label name of the item displayed
 * @param info associated value for the listed item
 */
@Composable
fun InfoRow(
    label: String,
    info: String,
    percent: Float = 1.0f
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(percent)
    ){
        Text(
            text = label,
            modifier = Modifier
                .weight(0.5f),
            textAlign = TextAlign.Right
        )

        Spacer(modifier = Modifier.weight(0.1f))

        Text(
            text = info,
            modifier = Modifier
                .weight(0.5f),
            textAlign = TextAlign.Left
        )
    }
}