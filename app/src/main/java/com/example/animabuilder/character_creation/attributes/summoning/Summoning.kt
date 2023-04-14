package com.example.animabuilder.character_creation.attributes.summoning

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader

/**
 * Section containing stats involving a character's summoning ability.
 * Holds values for summon, control, bind, and banish abilities.
 *
 * @param charInstance object that holds all of the character's data
 */
class Summoning(private val charInstance: BaseCharacter){
    //initialize each summoning ability
    val summon = SummonAbility(charInstance)
    val control = SummonAbility(charInstance)
    val bind = SummonAbility(charInstance)
    val banish = SummonAbility(charInstance)

    //gather all abilities
    val allSummoning = listOf(summon, control, bind, banish)

    /**
     * Determines the development points spent in this section.
     */
    fun calculateSpent(): Int{
        return (summon.buyVal * charInstance.ownClass.summonGrowth) +
                (control.buyVal * charInstance.ownClass.controlGrowth) +
                (bind.buyVal * charInstance.ownClass.bindGrowth) +
                (banish.buyVal * charInstance.ownClass.banishGrowth)
    }

    /**
     * Loads data from file to this page's section.
     *
     * @param fileReader file to get the data from
     */
    fun loadSummoning(fileReader: BufferedReader){
        allSummoning.forEach{it.loadAbility(fileReader)}
    }

    /**
     * Writes pertinent data from this section to file.
     */
    fun writeSummoning(){
        allSummoning.forEach{it.writeAbility()}
    }
}