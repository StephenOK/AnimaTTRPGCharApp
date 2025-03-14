package com.paetus.animaCharCreator.character_creation.attributes.summoning

import com.paetus.animaCharCreator.character_creation.BaseCharacter
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Section containing stats involving a character's summoning ability.
 * Holds values for summon, control, bind, and banish abilities.
 *
 * @param charInstance object that holds all of the character's data
 */
open class Summoning(private val charInstance: BaseCharacter){
    //initialize each summoning ability
    open val summon = SummonAbility(charInstance = charInstance)
    open val control = SummonAbility(charInstance = charInstance)
    open val bind = SummonAbility(charInstance = charInstance)
    open val banish = SummonAbility(charInstance = charInstance)

    /**
     * Gathers all summoning ability items.
     */
    fun allSummoning(): List<SummonAbility>{return listOf(summon, control, bind, banish)}

    /**
     * Gets the class's summoning DP cost.
     */
    open fun summonCost(): Int{return charInstance.classes.getClass().summonGrowth}

    /**
     * Gets the class's control DP cost.
     */
    open fun controlCost(): Int{return charInstance.classes.getClass().controlGrowth}

    /**
     * Gets the class's bind DP cost.
     */
    open fun bindCost(): Int{return charInstance.classes.getClass().bindGrowth}

    /**
     * Gets the class's banish DP cost.
     */
    open fun banishCost(): Int{return charInstance.classes.getClass().banishGrowth}

    /**
     * Determines the development points spent in this section.
     *
     * @return total points spent in this section
     */
    fun calculateSpent(): Int{
        return (summon.buyVal.intValue * charInstance.classes.getClass().summonGrowth) +
                (control.buyVal.intValue * charInstance.classes.getClass().controlGrowth) +
                (bind.buyVal.intValue * charInstance.classes.getClass().bindGrowth) +
                (banish.buyVal.intValue * charInstance.classes.getClass().banishGrowth)
    }

    /**
     * Loads data from file to this page's section.
     *
     * @param fileReader file to get the data from
     */
    fun loadSummoning(fileReader: BufferedReader){
        allSummoning().forEach{summonAbility -> summonAbility.loadAbility(fileReader = fileReader)}
    }

    /**
     * Writes pertinent data from this section to file.
     *
     * @param byteArray output stream for this section's data
     */
    fun writeSummoning(byteArray: ByteArrayOutputStream) {
        allSummoning().forEach{summonAbility -> summonAbility.writeAbility(byteArray = byteArray) }
    }
}