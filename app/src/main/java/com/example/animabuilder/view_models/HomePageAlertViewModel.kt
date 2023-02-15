package com.example.animabuilder.view_models

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomePageAlertViewModel: ViewModel() {
    private val _detailAlertOn = MutableStateFlow(false)
    private val _detailItem = MutableStateFlow("")
    private val _detailContents = MutableStateFlow<@Composable () -> Unit>{}
    private val _exitOpen = MutableStateFlow(false)

    val detailAlertOn = _detailAlertOn.asStateFlow()
    val detailItem = _detailItem.asStateFlow()
    val detailContents = _detailContents.asStateFlow()
    val exitOpen = _exitOpen.asStateFlow()

    val openDetailAlert = { itemName: String, itemContents: @Composable () -> Unit ->
        _detailAlertOn.update{true}
        _detailItem.update{itemName}
        _detailContents.update{itemContents}
    }

    fun closeDetailAlert(){
        _detailAlertOn.update{false}
    }

    fun setExitAlert(newState: Boolean){
        _exitOpen.update{newState}
    }
}