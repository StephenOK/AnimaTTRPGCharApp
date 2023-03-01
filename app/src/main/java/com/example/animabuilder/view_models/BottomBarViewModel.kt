package com.example.animabuilder.view_models

import androidx.lifecycle.ViewModel
import com.example.animabuilder.character_creation.BaseCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BottomBarViewModel: ViewModel() {
    private val _maxDP = MutableStateFlow(0)
    private val _maxCombat = MutableStateFlow(0)
    private val _maxMagic = MutableStateFlow(0)
    private val _maxPsychic = MutableStateFlow(0)

    private val _spentDP = MutableStateFlow(0)
    private val _spentCombat = MutableStateFlow(0)
    private val _spentMagic = MutableStateFlow(0)
    private val _spentPsychic = MutableStateFlow(0)

    val maxDP = _maxDP.asStateFlow()
    val maxCombat = _maxCombat.asStateFlow()
    val maxMagic = _maxMagic.asStateFlow()
    val maxPsychic = _maxPsychic.asStateFlow()

    val spentDP = _spentDP.asStateFlow()
    val spentCombat = _spentCombat.asStateFlow()
    val spentMagic = _spentMagic.asStateFlow()
    val spentPsychic = _spentPsychic.asStateFlow()

    fun setMaxDP(input: Int){_maxDP.update{input}}
    fun setMaxCombat(input: Int){_maxCombat.update{input}}
    fun setMaxMagic(input: Int){_maxMagic.update{input}}
    fun setMaxPsychic(input: Int){_maxPsychic.update{input}}

    fun updateMaxValues(charInstance: BaseCharacter){
        _maxCombat.update{charInstance.maxCombatDP}
        _maxMagic.update{charInstance.maxMagDP}
        _maxPsychic.update{charInstance.maxPsyDP}
    }

    fun updateSpentValues(charInstance: BaseCharacter){
        _spentDP.update{charInstance.spentTotal}
        _spentCombat.update{charInstance.ptInCombat}
        _spentMagic.update{charInstance.ptInMag}
        _spentPsychic.update{charInstance.ptInPsy}
    }
}