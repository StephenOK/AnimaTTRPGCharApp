package com.example.animabuilder.character_creation.attributes.ki_abilities

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader
import java.io.Serializable

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
            var dummyList = ability.kiBuild
            var numRemoved = 0

            dummyList.forEach{
                if(it > 0) {
                    output[ability.kiBuild.indexOf(it)] += it
                    dummyList = dummyList - it
                    numRemoved++
                }
            }
        }

        output
    }

    val maintArray = {
        val output = mutableListOf(0, 0, 0, 0, 0, 0)

        if(isMaintained) {
            givenAbilities.forEach {
                if (it.maint != null) {
                    output[it.maintIndex!!] += it.maint!!
                }
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
            if(isMaintained && it.maint != null)
                total += it.maint!!
        }

        total
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
}