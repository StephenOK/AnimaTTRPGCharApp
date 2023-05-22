package com.example.animabuilder.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.view_models.models.AdvantageFragmentViewModel
import com.example.animabuilder.view_models.models.CharacterFragmentViewModel
import com.example.animabuilder.view_models.models.CombatFragViewModel
import com.example.animabuilder.view_models.models.EquipmentFragmentViewModel
import com.example.animabuilder.view_models.models.HomePageAlertViewModel
import com.example.animabuilder.view_models.models.HomePageViewModel
import com.example.animabuilder.view_models.models.KiFragmentViewModel
import com.example.animabuilder.view_models.models.MagicFragmentViewModel
import com.example.animabuilder.view_models.models.MainPageViewModel
import com.example.animabuilder.view_models.models.ModuleFragmentViewModel
import com.example.animabuilder.view_models.models.PsychicFragmentViewModel
import com.example.animabuilder.view_models.models.SecondaryFragmentViewModel
import com.example.animabuilder.view_models.models.SummoningFragmentViewModel

class CustomFactory(
    private val viewModel: Class<*>,
    private val charInstance: BaseCharacter
): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(viewModel)){
            when(viewModel){
                MainPageViewModel::class.java ->{
                    return MainPageViewModel() as T
                }

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
                        charInstance
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
                        charInstance.weaponProficiencies
                    ) as T
                }

                KiFragmentViewModel::class.java ->{
                    return KiFragmentViewModel(
                        charInstance.ki
                    ) as T
                }

                MagicFragmentViewModel::class.java ->{
                    return MagicFragmentViewModel(
                        charInstance.magic,
                        charInstance
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