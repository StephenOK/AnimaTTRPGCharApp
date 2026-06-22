package com.paetus.animaCharCreator.view_models

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.view_models.models.AdvantageFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.CharacterFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.CombatFragViewModel
import com.paetus.animaCharCreator.view_models.models.CustomTechniqueViewModel
import com.paetus.animaCharCreator.view_models.models.EditSecondaryViewModel
import com.paetus.animaCharCreator.view_models.models.EquipmentFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.HomePageViewModel
import com.paetus.animaCharCreator.view_models.models.KiFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.MagicFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.MainPageViewModel
import com.paetus.animaCharCreator.view_models.models.ModuleFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.PsychicFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.SecondaryFragmentViewModel
import com.paetus.animaCharCreator.view_models.models.SummoningFragmentViewModel

/**
 * Factory that makes the viewModels for the app.
 * Creates viewModels for each page the user will go to.
 *
 * @param viewModel type of viewModel that is to be created
 * @param charInstance character object to use in creation
 * @param context local context to pass to a view model object
 */
class CustomFactory(
    private val viewModel: Class<*>,
    private val charInstance: BaseCharacter,
    private val context: Context
): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(viewModel)){
            //process the requested viewModel
            when(viewModel){
                //creating main page viewModel
                MainPageViewModel::class.java ->{
                    return MainPageViewModel() as T
                }

                //creating secondary characteristic editing viewModel
                EditSecondaryViewModel::class.java ->{
                    return EditSecondaryViewModel(
                        context = context
                    ) as T
                }

                //creating home page viewModel
                HomePageViewModel::class.java ->{
                    return HomePageViewModel(
                        charInstance = charInstance
                    ) as T
                }

                //creating character page viewModel
                CharacterFragmentViewModel::class.java ->{
                    return CharacterFragmentViewModel(
                        charInstance = charInstance
                    ) as T
                }

                //creating combat viewModel
                CombatFragViewModel::class.java ->{
                    return CombatFragViewModel(
                        combat = charInstance.combat,
                        primaryList = charInstance.primaryList
                    ) as T
                }

                //creating secondary characteristic viewModel
                SecondaryFragmentViewModel::class.java ->{
                    return SecondaryFragmentViewModel(
                        charInstance = charInstance,
                        secondaryList = charInstance.secondaryList
                    ) as T
                }

                //creating advantage viewModel
                AdvantageFragmentViewModel::class.java -> {
                    return AdvantageFragmentViewModel(
                        charInstance = charInstance,
                        advantageRecord = charInstance.advantageRecord
                    ) as T
                }

                //creating module viewModel
                ModuleFragmentViewModel::class.java -> {
                    return ModuleFragmentViewModel(
                        weaponProficiencies = charInstance.weaponProficiencies,
                        context = context
                    ) as T
                }

                //creating ki viewModel
                KiFragmentViewModel::class.java ->{
                    return KiFragmentViewModel(
                        ki = charInstance.ki,
                        context = context
                    ) as T
                }

                CustomTechniqueViewModel::class.java ->{
                    return CustomTechniqueViewModel(
                        ki = charInstance.ki,
                        context = context
                    ) as T
                }

                //creating magic viewModel
                MagicFragmentViewModel::class.java ->{
                    return MagicFragmentViewModel(
                        magic = charInstance.magic,
                        charInstance = charInstance,
                        context = context
                    ) as T
                }

                //creating summoning viewModel
                SummoningFragmentViewModel::class.java ->{
                    return SummoningFragmentViewModel(
                        summoning = charInstance.summoning
                    ) as T
                }

                //creating psychic viewModel
                PsychicFragmentViewModel::class.java ->{
                    return PsychicFragmentViewModel(
                        psychic = charInstance.psychic,
                        context = context
                    ) as T
                }

                //creating equipment viewModel
                EquipmentFragmentViewModel::class.java ->{
                    return EquipmentFragmentViewModel(
                        inventory = charInstance.inventory
                    ) as T
                }
            }
        }

        //viewModel requested not accounted for
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}