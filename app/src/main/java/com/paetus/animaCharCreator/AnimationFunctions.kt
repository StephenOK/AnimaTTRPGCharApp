package com.paetus.animaCharCreator

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith

/**
 * Scrolls a number up or down depending on the change in value.
 */
val numberScroll: AnimatedContentTransitionScope<Int>.() -> ContentTransform = {
    if(targetState > initialState){
        (slideInVertically{ height -> height} + fadeIn()).togetherWith(
            slideOutVertically { height -> -height } + fadeOut()
        )
    }
    else{
        (slideInVertically{ height -> -height} + fadeIn()).togetherWith(
            slideOutVertically { height -> height } + fadeOut()
        )
    }
}

/**
 * Scrolls the text upwards when it changes.
 */
val textScrollUp: AnimatedContentTransitionScope<String>.() -> ContentTransform = {
    (slideInVertically{ height -> height} + fadeIn()).togetherWith(
        slideOutVertically { height -> -height } + fadeOut()
    )
}
