package com.paetus.animaCharCreator.composables

import androidx.compose.foundation.background
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
 * @param percent amount of the row the label will take
 * @param backColor background color for this row
 * @param textColor text color to use
 * @param content displayed values in this item
 */
@Composable
fun InfoRow(
    label: String,
    percent: Float = 1.0f,
    backColor: Color = Color(0x00000000),
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    content: @Composable (Modifier, Color) -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(percent)
            .background(backColor)
    ){
        //display item label
        Text(
            text = label,
            modifier = Modifier
                .weight(0.5f),
            textAlign = TextAlign.Right,
            color = textColor,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.weight(0.05f))

        //display item value
        content(Modifier.weight(0.5f), textColor)
    }
}