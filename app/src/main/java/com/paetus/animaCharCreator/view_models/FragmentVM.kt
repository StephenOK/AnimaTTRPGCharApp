package com.paetus.animaCharCreator.view_models

import androidx.lifecycle.ViewModel

/**
 * Subclass of Viewmodel for the main fragments.
 * Supplies a universal refresh function to be called.
 */
open class FragmentVM: ViewModel() {
    open fun refreshPage(){}
}