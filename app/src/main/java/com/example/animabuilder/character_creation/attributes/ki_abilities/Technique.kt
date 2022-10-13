package com.example.animabuilder.character_creation.attributes.ki_abilities

import java.io.Serializable

class Technique(
    var name: String,
    var description: String,
    var level: Int,
    var givenAbilities: List<TechniqueEffect>
): Serializable{
    val cost = {
        var total = 0
        givenAbilities.forEach{total += it.mkCost}
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

    val accTotal = {
        val addUp = statSpent()
        var total = 0

        addUp.forEach{
            total += it
        }

        total
    }
}