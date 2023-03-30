package com.example.animabuilder.view_models

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * View model that manages the main page alerts.
 */
class HomePageAlertViewModel: ViewModel() {
    //initialize detail alert open state
    private val _detailAlertOn = MutableStateFlow(false)
    val detailAlertOn = _detailAlertOn.asStateFlow()

    //initialize item shown in details
    private val _detailItem = MutableStateFlow("")
    val detailItem = _detailItem.asStateFlow()

    //initialize form of detail alert
    private val _detailContents = MutableStateFlow<@Composable () -> Unit>{}
    val detailContents = _detailContents.asStateFlow()

    //initialize open state of exit dialog
    private val _exitOpen = MutableStateFlow(false)
    val exitOpen = _exitOpen.asStateFlow()

    /**
     * Open the details of the inputted item in the inputted format.
     *
     * @param itemName string name of the inputted item
     * @param itemContents composable format of the item's details
     */
    val openDetailAlert = { itemName: String, itemContents: @Composable () -> Unit ->
        _detailAlertOn.update{true}
        _detailItem.update{itemName}
        _detailContents.update{itemContents}
    }

    /**
     * Closes the detail alert.
     */
    fun closeDetailAlert(){
        _detailAlertOn.update{false}
    }

    /**
     * Toggles the open state of the exit alert.
     */
    fun toggleExitAlert() {
        _exitOpen.update{!exitOpen.value}
    }
}