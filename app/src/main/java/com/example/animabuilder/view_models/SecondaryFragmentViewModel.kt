package com.example.animabuilder.view_models

import androidx.lifecycle.ViewModel
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryCharacteristic
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SecondaryFragmentViewModel(
    secondaryList: SecondaryList
): ViewModel() {
    val athletics = SecondaryFieldData(
        secondaryList,
        R.string.athleticsLabel,
        secondaryList.intToField(0)
    )

    val creative = SecondaryFieldData(
        secondaryList,
        R.string.creativeLabel,
        secondaryList.intToField(1)
    )

    val perceptive = SecondaryFieldData(
        secondaryList,
        R.string.perceptionLabel,
        secondaryList.intToField(2)
    )

    val social = SecondaryFieldData(
        secondaryList,
        R.string.socialLabel,
        secondaryList.intToField(3)
    )

    val subterfuge = SecondaryFieldData(
        secondaryList,
        R.string.subterfugeLabel,
        secondaryList.intToField(4)
    )

    val intellectual = SecondaryFieldData(
        secondaryList,
        R.string.intellectualLabel,
        secondaryList.intToField(5)
    )

    val vigor = SecondaryFieldData(
        secondaryList,
        R.string.vigorLabel,
        secondaryList.intToField(6)
    )

    val allFields = listOf(athletics, creative, perceptive, social, subterfuge, intellectual, vigor)

    class SecondaryFieldData(
        private val secondaryList: SecondaryList,
        val fieldName: Int,
        fieldItems: List<SecondaryCharacteristic>
    ){
        private val _tableOpen = MutableStateFlow(false)
        val tableOpen = _tableOpen.asStateFlow()
        fun toggleOpen(){_tableOpen.update{!tableOpen.value}}

        val fieldCharacteristics = mutableListOf<SecondaryItem>()

        class SecondaryItem(
            private val secondaryItem: SecondaryCharacteristic,
            private val secondaryList: SecondaryList
        ){
            private val _natBonusCheck = MutableStateFlow(secondaryItem.bonusApplied)
            private val _checkedText = MutableStateFlow(updateCheckedText())
            private val _pointInput = MutableStateFlow(secondaryItem.pointsApplied.toString())
            private val _totalOutput = MutableStateFlow(secondaryItem.total.toString())

            val natBonusCheck = _natBonusCheck.asStateFlow()
            val pointInput = _pointInput.asStateFlow()
            val totalOutput = _totalOutput.asStateFlow()

            fun getName(): Int{return secondaryItem.name}
            fun getModVal(): String{return secondaryItem.modVal.toString()}
            fun getClassPointTotal(): String{return secondaryItem.classPointTotal.toString()}
            fun getMultiplier(): Int{
                return if(secondaryItem.devPerPoint > secondaryItem.developmentDeduction)
                    secondaryItem.devPerPoint - secondaryItem.developmentDeduction
                else 1
            }

            fun setBonusNatCheck(){
                secondaryList.toggleNatBonus(secondaryItem)
                _natBonusCheck.update{secondaryItem.bonusApplied}
                _totalOutput.update{secondaryItem.total.toString()}
                _checkedText.update{updateCheckedText()}
            }

            fun updateCheckedText(): Int{
                return if(secondaryItem.bonusApplied) R.string.natTaken
                else R.string.natNotTaken
            }

            fun setPointInput(input: Int){
                setPointInput(input.toString())
                secondaryItem.setPointsApplied(input)
                _totalOutput.update{secondaryItem.total.toString()}
                if(input == 0 && natBonusCheck.value)
                    setBonusNatCheck()
            }

            fun setPointInput(input: String){_pointInput.update{input}}
        }

        init{
            fieldItems.forEach{
                fieldCharacteristics.add(SecondaryItem(it, secondaryList))
            }
        }
    }
}