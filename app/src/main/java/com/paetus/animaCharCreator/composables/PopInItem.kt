package com.paetus.animaCharCreator.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalAnimationApi::class)
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
        ) { content() }
    }
}