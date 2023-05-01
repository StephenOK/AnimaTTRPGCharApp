package com.example.animabuilder.view_models.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.view_models.AdvantageFragmentViewModel
import com.example.animabuilder.view_models.CharacterFragmentViewModel
import com.example.animabuilder.view_models.CombatFragViewModel
import com.example.animabuilder.view_models.EquipmentFragmentViewModel
import com.example.animabuilder.view_models.HomePageAlertViewModel
import com.example.animabuilder.view_models.HomePageViewModel
import com.example.animabuilder.view_models.KiFragmentViewModel
import com.example.animabuilder.view_models.MagicFragmentViewModel
import com.example.animabuilder.view_models.ModuleFragmentViewModel
import com.example.animabuilder.view_models.PsychicFragmentViewModel
import com.example.animabuilder.view_models.SecondaryFragmentViewModel
import com.example.animabuilder.view_models.SummoningFragmentViewModel

class CustomFactory (
    private val viewModel: Class<*>,
    private val charInstance: BaseCharacter,
    private val context: Context
): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(viewModel)){
            when(viewModel){
                HomePageViewModel::class.java ->{
                    return HomePageViewModel(
                        charInstance
                    ) as T
                }

                HomePageAlertViewModel::class.java ->{
                    return HomePageAlertViewModel() as T
                }

                CharacterFragmentViewModel::class.java ->{
                    return CharacterFragmentViewModel(
                        charInstance,
                        context
                    ) as T
                }

                CombatFragViewModel::class.java ->{
                    return CombatFragViewModel(
                        charInstance.combat,
                        charInstance.primaryList
                    ) as T
                }

                SecondaryFragmentViewModel::class.java ->{
                    return SecondaryFragmentViewModel(
                        charInstance,
                        charInstance.secondaryList
                    ) as T
                }

                AdvantageFragmentViewModel::class.java -> {
                    return AdvantageFragmentViewModel(
                        charInstance,
                        charInstance.advantageRecord
                    ) as T
                }

                ModuleFragmentViewModel::class.java -> {
                    return ModuleFragmentViewModel(
                        charInstance.weaponProficiencies,
                        context
                    ) as T
                }

                KiFragmentViewModel::class.java ->{
                    return KiFragmentViewModel(
                        charInstance.ki,
                        context
                    ) as T
                }

                MagicFragmentViewModel::class.java ->{
                    return MagicFragmentViewModel(
                        charInstance.magic,
                        charInstance.primaryList.dex
                    ) as T
                }

                SummoningFragmentViewModel::class.java ->{
                    return SummoningFragmentViewModel(
                        charInstance.summoning
                    ) as T
                }

                PsychicFragmentViewModel::class.java ->{
                    return PsychicFragmentViewModel(
                        charInstance.psychic,
                        charInstance.primaryList.dex.outputMod.value
                    ) as T
                }

                EquipmentFragmentViewModel::class.java ->{
                    return EquipmentFragmentViewModel(
                        charInstance.inventory
                    ) as T
                }
            }
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}