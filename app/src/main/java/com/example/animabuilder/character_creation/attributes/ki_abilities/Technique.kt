package com.example.animabuilder.character_creation.attributes.ki_abilities

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import java.io.Serializable
import java.util.Collections

class Technique(
    var name: String,
    var description: String,
    var level: Int,
    var isMaintained: Boolean,
    var givenAbilities: List<TechniqueEffect>
): Serializable{
    val mkCost = {
        var total = 0
        givenAbilities.forEach{
            total += it.mkCost
        }

        if(isMaintained){
            total += when(level) {
                2 -> 20
                3 -> 30
                else -> 10
            }
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

    val maintArray = {
        val output = mutableListOf(0, 0, 0, 0, 0, 0)

        if(isMaintained) {
            givenAbilities.forEach {
                output[it.maintIndex] += it.maint
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
            if(isMaintained)
                total += it.maint
        }

        total
    }

    fun equivalentTo(compareTo: Technique): Boolean{
        return compareTo.name == name &&
                compareTo.description == description &&
                compareTo.level == level &&
                compareTo.isMaintained == isMaintained &&
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

    fun validEffectAddition(input: TechniqueEffect): Boolean{
        if(hasAbility(input.name))
            return false

        if(!checkElementalBinding(input))
            return false

        if(input.lvl > level)
            return false

        val max = when(level){
            1 -> 50
            2 -> 100
            3 -> 200
            else -> 0
        }

        if(mkCost() + input.mkCost > max)
            return false

        return true
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
                    replaceList[givenAbilities[pointer].maintIndex] = givenAbilities[pointer].costPair.first

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

        if(isMaintained)
            charInstance.addNewData("true")
        else
            charInstance.addNewData("false")

        charInstance.addNewData(givenAbilities.size)
        givenAbilities.forEach{
            it.write(charInstance)
        }
    }
}