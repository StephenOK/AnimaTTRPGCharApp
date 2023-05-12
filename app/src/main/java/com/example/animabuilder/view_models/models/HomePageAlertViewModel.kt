package com.example.animabuilder.view_models.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the main page alerts.
 */
class HomePageAlertViewModel: ViewModel() {
    //initialize item shown in details
    private val _detailItem = MutableStateFlow("")
    val detailItem = _detailItem.asStateFlow()

    //initialize open state of exit dialog
    private val _exitOpen = MutableStateFlow(false)
    val exitOpen = _exitOpen.asStateFlow()

    /**
     * Toggles the open state of the exit alert.
     */
    fun toggleExitAlert() {
        _exitOpen.update{!exitOpen.value}
    }
}