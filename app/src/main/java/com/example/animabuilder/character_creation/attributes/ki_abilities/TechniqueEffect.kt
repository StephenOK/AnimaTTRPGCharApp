package com.example.animabuilder.character_creation.attributes.ki_abilities

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import java.io.Serializable

class TechniqueEffect(
    var name: String,
    var effect: String,
    var mkCost: Int,
    var maint: Int?,
    var maintIndex: Int?,
    var kiBuild: List<Int>,
    var elements: List<Element>,
    var lvl: Int
): Serializable{
    fun write(charInstance: BaseCharacter){
        charInstance.addNewData(name)
        charInstance.addNewData(effect)
        charInstance.addNewData(mkCost)
        charInstance.addNewData(maint)
        charInstance.addNewData(maintIndex)

        kiBuild.forEach{
            charInstance.addNewData(it)
        }

        charInstance.addNewData(elements.size)
        elements.forEach{
            charInstance.addNewData(it.name)
        }

        charInstance.addNewData(lvl)
    }

    fun equivalentTo(compareTo: TechniqueEffect): Boolean{
        return compareTo.name == name &&
                compareTo.effect == effect &&
                compareTo.mkCost == mkCost &&
                compareTo.maint == maint &&
                compareTo.maintIndex == maintIndex &&
                compareTo.kiBuild == kiBuild &&
                compareTo.elements == elements &&
                compareTo.lvl == lvl
    }
}