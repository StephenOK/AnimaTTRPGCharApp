package com.example.animabuilder.character_creation.attributes.combat

import com.example.animabuilder.character_creation.BaseCharacter

class CombatItem(
    private val charInstance: BaseCharacter
) {
    var inputVal = 0
    var modPoints = 0
    var pointFromClass = 0
    var total = 0



    fun updateTotal(){
        total = inputVal + modPoints + pointFromClass
        charInstance.weaponProficiencies.updateMartialMax()
    }
}