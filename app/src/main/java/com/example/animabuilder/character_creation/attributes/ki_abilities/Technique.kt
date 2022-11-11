package com.example.animabuilder.character_creation.attributes.ki_abilities

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import java.io.Serializable
import java.util.Collections

class Technique(
    var name: String,
    var description: String,
    var level: Int,
    var maintArray: MutableList<Int>,
    var givenAbilities: List<TechniqueEffect>
): Serializable{
    fun clearTechnique() {
        name = ""
        description = ""
        level = 1
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0)
        givenAbilities = listOf()
    }

    val mkCost = {
        var total = 0
        givenAbilities.forEach{
            total += it.mkCost
        }

        if(isMaintained()){
            total += when(level) {
                2 -> 20
                3 -> 30
                else -> 10
            }
        }

        total
    }

    val maintTotal = {
        var total = 0
        givenAbilities.forEach{
            total += it.maintTotal
        }

        total
    }

    val statSpent = {
        val output = mutableListOf(0, 0, 0, 0, 0, 0)

        givenAbilities.forEach{ability ->
            for(index in 0..5){
                output[index] += ability.kiBuild[index]
            }
        }

        output
    }

    val accTotal = {
        val addUp = statSpent()
        var total = 0

        addUp.forEach{
            total += it
        }

        givenAbilities.forEach{
            if(isMaintained())
                total += it.maintTotal
        }

        total
    }

    fun isMaintained(): Boolean{
        for(index in 0..5)
            if(maintArray[index] != 0)
                return true

        return false
    }

    fun hasAccumulation(index: Int): Boolean{
        givenAbilities.forEach{
            if(it.kiBuild[index] != 0)
                return true
        }

        return false
    }

    fun equivalentTo(compareTo: Technique): Boolean{
        return compareTo.name == name &&
                compareTo.description == description &&
                compareTo.level == level &&
                compareTo.maintArray == maintArray &&
                listCheck(compareTo.givenAbilities)
    }

    fun listCheck(compareTo: List<TechniqueEffect>): Boolean{
        if(compareTo.size != givenAbilities.size)
            return false

        var listCopy = compareTo
        listCopy.forEach{comparison ->
            givenAbilities.forEach{
                if(it.equivalentTo(comparison))
                    listCopy = listCopy - comparison
            }
        }

        return listCopy.isEmpty()
    }

    fun validEffectAddition(input: TechniqueEffect?, charMax: Int): String?{
        if(input == null)
            return "No Effect Selected"

        if(hasAbility(input.name))
            return "Technique is already present"

        if(input.name == "Elemental Binding" && input.elements != listOf<Element>()){
            givenAbilities.forEach{effect ->
                var isBound = false

                input.elements.forEach{
                    if(effect.elements.contains(it))
                        isBound = true
                }

                if(!isBound)
                    return "Cannot currently bind to selected element(s)"
            }
        }

        if(!checkElementalBinding(input))
            return "Cannot take because of Elemental Binding"

        if(input.lvl > level)
            return "Cannot take an effect of that level"

        val max = when(level){
            1 -> 50
            2 -> 100
            3 -> 200
            else -> 0
        }

        if(mkCost() + input.mkCost > max)
            return "Maximum technique cost exceeded"

        if(mkCost() + input.mkCost > charMax)
            return "Technique would exceed character's limit"

        return null
    }

    fun hasAbility(input: String): Boolean{
        givenAbilities.forEach{
            if(it.name == input)
                return true
        }

        return false
    }

    fun getAbility(retrieve: String): TechniqueEffect?{
        givenAbilities.forEach{
            if(it.name == retrieve)
                return it
        }

        return null
    }

    fun checkElementalBinding(input: TechniqueEffect): Boolean{
        val binding = getAbility("Elemental Binding")
        if(binding != null){
            val boundElements = binding.elements
            var trueCheck = false

            boundElements.forEach{
                if(input.elements.contains(it))
                    trueCheck = true
            }

            return trueCheck
        }

        return true
    }

    fun fixPrimaryAbility(){
        var onlyDis = true
        givenAbilities.forEach{
            if(!it.elements.contains(Element.Free))
                onlyDis = false
        }

        if(onlyDis || givenAbilities.isEmpty())
            givenAbilities = listOf()
        else{
            var pointer = 0
            while(pointer < givenAbilities.size){
                if(!givenAbilities[pointer].elements.contains(Element.Free)) {
                    val replaceList = mutableListOf(0, 0, 0, 0, 0, 0)
                    replaceList[givenAbilities[pointer].buildAdditions.indexOf(0)] = givenAbilities[pointer].costPair.first

                    givenAbilities[pointer].kiBuild = replaceList

                    if(pointer != 0)
                        Collections.swap(givenAbilities, 0, pointer)

                    pointer = givenAbilities.size
                }

                pointer++
            }
        }
    }

    fun write(charInstance: BaseCharacter){
        charInstance.addNewData(name)
        charInstance.addNewData(description)
        charInstance.addNewData(level)
        maintArray.forEach{charInstance.addNewData(it)}

        charInstance.addNewData(givenAbilities.size)
        givenAbilities.forEach{
            it.write(charInstance)
        }
    }
}