package com.paetus.animaCharCreator

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

/**
 * Generic button that is attached to rows in order to display an object's details.
 *
 * @param onClick specific function to run when clicked for this item's details
 * @param modifier potential unique properties of the specific button
 */
@Composable
fun DetailButton(
    onClick: () -> Unit,
    modifier: Modifier
){
    TextButton(
        onClick = {onClick()},
        modifier = modifier
    ) {
        Text(text = stringResource(R.string.detailLabel))
    }
}