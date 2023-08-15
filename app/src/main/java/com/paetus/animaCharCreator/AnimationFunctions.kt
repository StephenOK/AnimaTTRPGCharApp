package com.paetus.animaCharCreator

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with

@OptIn(ExperimentalAnimationApi::class)
val numberScroll: AnimatedContentScope<Int>.() -> ContentTransform = {
    if(targetState > initialState){
        slideInVertically{height -> height} + fadeIn() with
                slideOutVertically{height -> -height} + fadeOut()
    }
    else{
        slideInVertically{height -> -height} + fadeIn() with
                slideOutVertically{height -> height} + fadeOut()
    }
}

@OptIn(ExperimentalAnimationApi::class)
val textScrollUp: AnimatedContentScope<String>.() -> ContentTransform = {
    slideInVertically{height -> height} + fadeIn() with
            slideOutVertically{height -> -height} + fadeOut()
}

//@OptIn(ExperimentalAnimationApi::class)
