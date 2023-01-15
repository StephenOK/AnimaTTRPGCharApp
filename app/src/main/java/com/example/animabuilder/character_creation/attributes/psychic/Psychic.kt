package com.example.animabuilder.character_creation.attributes.psychic

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.psychic.disciplines.*
import java.io.Serializable

class Psychic(private val charInstance: BaseCharacter): Serializable {
    var psyPotentialBase = 0

    var boughtPsyPoints = 0
    var innatePsyPoints = 0
    var totalPsychicPoints = 0
    var spentPsychicPoints = 0

    var psyProjectionBought = 0
    var psyProjectionTotal = 0

    val telepathy = Telepathy()
    val psychokinesis = Psychokinesis()
    val pyrokinesis = Pyrokinesis()
    val cryokinesis = Cryokinesis()
    val physicalIncrease = PhysicalIncrease()
    val energyPowers = Energy()
    val sentiencePowers = Sentience()
    val telemetry = Telemetry()
    val matrixPowers = MatrixPowers()

    val disciplineInvestment = mutableListOf<Discipline>()
    val masteredPowers = mutableListOf<PsychicPower>()



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

    fun getFreePsyPoints(): Int{
        return totalPsychicPoints - spentPsychicPoints
    }

    fun updateInvestment(item: Discipline, into: Boolean): Boolean{
        if(into && getFreePsyPoints() > 0) {
            disciplineInvestment.add(item)
            spentPsychicPoints += 1
            return true
        }
        else if (!into) {
            disciplineInvestment.remove(item)
            item.allPowers.forEach{
                masterPower(it, item, false)
            }
            spentPsychicPoints -= 1
        }

        return false
    }

    fun masterPower(item: PsychicPower, discipline: Discipline, into: Boolean): Boolean{
        if(into && getFreePsyPoints() > 0 && legalBuy(item, discipline)){
            masteredPowers.add(item)
            spentPsychicPoints += 1
            return true
        }
        else if(!into && masteredPowers.contains(item)){
            masteredPowers.remove(item)
            spentPsychicPoints -= 1
            removeIllegal(discipline)
        }

        return false
    }

    fun legalBuy(item: PsychicPower, discipline: Discipline): Boolean{
        if(item.level <= 1)
            return true
        else if(item.level == 2){
            masteredPowers.forEach{
                if(it.level == 1 && discipline.allPowers.contains(it))
                    return true
            }
        }
        else if(item.level == 3){
            masteredPowers.forEach{
                if(it.level == 2 && discipline.allPowers.contains(it))
                    return true
            }
        }

        return false
    }

    fun removeIllegal(discipline: Discipline){
        masteredPowers.forEach{
            if(!legalBuy(it, discipline)){
                masteredPowers.remove(it)
                spentPsychicPoints -= 1
            }
        }
    }

    fun calculateSpent(): Int{
        return (boughtPsyPoints * charInstance.ownClass.psyPointGrowth) +
                (psyProjectionBought * charInstance.ownClass.psyProjGrowth)
    }
}