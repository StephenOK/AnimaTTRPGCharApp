package com.paetus.animaCharCreator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GeneralCard(contents: @Composable () -> Unit) {
    Card{
        Column(
            modifier = Modifier
                .padding(
                    top = 2.dp,
                    bottom = 10.dp,
                    start = 10.dp,
                    end = 10.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){contents()}
    }
}