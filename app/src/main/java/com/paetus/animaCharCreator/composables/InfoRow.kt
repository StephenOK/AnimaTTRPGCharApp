package com.paetus.animaCharCreator.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

/**
 * Display item that holds a label and the associated amount.
 *
 * @param label name of the item displayed
 * @param info associated value for the listed item
 * @param percent amount of the row the label will take
 * @param color text color to use
 */
@Composable
fun InfoRow(
    label: String,
    info: String,
    percent: Float = 1.0f,
    color: Color = MaterialTheme.colorScheme.onBackground
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(percent)
    ){
        //display item label
        Text(
            text = label,
            modifier = Modifier
                .weight(0.5f),
            textAlign = TextAlign.Right,
            color = color,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.weight(0.05f))

        //display item value
        Text(
            text = info,
            modifier = Modifier
                .weight(0.5f),
            textAlign = TextAlign.Left,
            color = color
        )
    }
}