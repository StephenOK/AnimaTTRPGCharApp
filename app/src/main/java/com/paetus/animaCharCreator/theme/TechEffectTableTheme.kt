package com.paetus.animaCharCreator.theme

import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Color scheme properties for a technique effect's selection table.
 */
val techEffectLightColors = lightColorScheme(
    primary = Color(0xFFCFE6A1),
    secondary = Color(0xFFFFEAE6),
    onPrimary = Color(0xFF000000)
)

/**
 * Retrieves the appropriate color for the table based on the row input.
 *
 * @param index row number of the table
 * @return color of the row
 */
@Composable
fun getRowColor(index: Int): Color{
    return if(index % 2 == 0)
        MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.secondary
}

/**
 * Retrieves the appropriate color scheme for the checkbox based on the row input.
 *
 * @param index row number of the table
 * @return color scheme for the checkbox
 */
@Composable
fun getCheckboxColors(index: Int): CheckboxColors{
    return if(index % 2 == 0)
        CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.secondary
        )
    else
        CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.primary
        )
}