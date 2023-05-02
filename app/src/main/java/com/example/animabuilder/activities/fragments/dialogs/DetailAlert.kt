package com.example.animabuilder.activities.fragments.dialogs

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.animabuilder.view_models.models.HomePageAlertViewModel

/**
 * Shows an alert that gives details on the item in question.
 *
 * @param masterModel source of item information
 */
@Composable
fun DetailAlert(masterModel: HomePageAlertViewModel) {
    DialogFrame(
        "Description of " + masterModel.detailItem.collectAsState().value,
        {LazyColumn{item{masterModel.detailContents.collectAsState().value()}}},
        {TextButton(onClick = {masterModel.closeDetailAlert()}){ Text(text = "Close")}}
    )
}