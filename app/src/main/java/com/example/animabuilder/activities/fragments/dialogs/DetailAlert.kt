package com.example.animabuilder.activities.fragments.dialogs

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

/**
 * Shows an alert that gives details on the item in question
 *
 * contents: item's details specific to the type of item being displayed
 */
@Composable
fun DetailAlert(
    itemName: String,
    detailDisplay: @Composable () -> Unit,
    deactivate: () -> Unit
) {
    DialogFrame(
        "Description of $itemName",
        {LazyColumn{item{ detailDisplay()}}},
        {TextButton(onClick = {deactivate()}){ Text(text = "Close")}}
    )
}