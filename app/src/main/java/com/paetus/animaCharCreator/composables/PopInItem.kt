package com.paetus.animaCharCreator.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Item that pops in and out depending on the current visibility.
 *
 * @param visible visible state of this item
 * @param modifier modifier code that affects this item
 * @param content composable contents of this item
 */
@Composable
fun PopInItem(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Box(
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = scaleIn(),
            exit = scaleOut()
        ) {content()}
    }
}