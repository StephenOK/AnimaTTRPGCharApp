package com.example.animabuilder.character_creation.attributes.ki_abilities

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import java.io.Serializable

class TechniqueEffect(
    var name: String,
    var effect: String,
    var mkCost: Int,
    var maintTotal: Int,
    var costPair: Pair<Int, Int>,
    var kiBuild: MutableList<Int>,
    var buildAdditions: List<Int?>,
    var elements: List<Element>,
    var lvl: Int
): Serializable{
    fun write(charInstance: BaseCharacter){
        charInstance.addNewData(name)
        charInstance.addNewData(effect)
        charInstance.addNewData(mkCost)
        charInstance.addNewData(maintTotal)

        charInstance.addNewData(costPair.first)
        charInstance.addNewData(costPair.second)

        kiBuild.forEach{
            charInstance.addNewData(it)
        }

        buildAdditions.forEach{
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
                compareTo.maintTotal == maintTotal &&
                compareTo.costPair.first == costPair.first &&
                compareTo.costPair.second == costPair.second &&
                compareTo.kiBuild == kiBuild &&
                compareTo.elements == elements &&
                compareTo.lvl == lvl
    }
}