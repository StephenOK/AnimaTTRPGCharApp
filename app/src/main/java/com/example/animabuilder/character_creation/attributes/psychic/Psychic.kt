package com.example.animabuilder.character_creation.attributes.psychic

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.Serializable

class Psychic(private val charInstance: BaseCharacter): Serializable {
    var psyPotentialBase = 0

    var boughtPsyPoints = 0
    var innatePsyPoints = 0
    var totalPsychicPoints = 0

    var psyProjectionBought = 0
    var psyProjectionTotal = 0

    fun setBasePotential(){
        psyPotentialBase = when(charInstance.wp){
            in 0..4 -> 0
            in 5 .. 14 -> 10 * (charInstance.wp -4)
            else -> 100 + ((charInstance.wp - 14) * 20)
        }
    }

    fun buyPsyPoints(amount: Int){
        boughtPsyPoints = amount
        updatePsyPointTotal()
    }

    fun setInnatePsy(){
        innatePsyPoints =
            if (charInstance.lvl == 0) 0
            else 1 + (charInstance.lvl - 1)/charInstance.ownClass.psyPerTurn
        updatePsyPointTotal()
    }

    fun updatePsyPointTotal(){
        totalPsychicPoints = boughtPsyPoints + innatePsyPoints
    }

    fun buyPsyProjection(amount: Int){
        psyProjectionBought = amount
        updatePsyProjection()
    }

    fun updatePsyProjection(){
        psyProjectionTotal = psyProjectionBought + charInstance.modDEX
    }

    fun calculateSpent(): Int{
        return (boughtPsyPoints * charInstance.ownClass.psyPointGrowth) +
                (psyProjectionBought * charInstance.ownClass.psyProjGrowth)
    }
}