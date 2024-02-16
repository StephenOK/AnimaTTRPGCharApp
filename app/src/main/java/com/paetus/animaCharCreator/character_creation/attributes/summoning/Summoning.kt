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
class Summoning(private val charInstance: BaseCharacter){
    //initialize each summoning ability
    val summon = SummonAbility(charInstance = charInstance)
    val control = SummonAbility(charInstance = charInstance)
    val bind = SummonAbility(charInstance = charInstance)
    val banish = SummonAbility(charInstance = charInstance)

    //gather all abilities
    val allSummoning = listOf(summon, control, bind, banish)

    /**
     * Determines the development points spent in this section.
     *
     * @return total points spent in this section
     */
    fun calculateSpent(): Int{
        return (summon.buyVal.intValue * charInstance.classes.ownClass.value.summonGrowth) +
                (control.buyVal.intValue * charInstance.classes.ownClass.value.controlGrowth) +
                (bind.buyVal.intValue * charInstance.classes.ownClass.value.bindGrowth) +
                (banish.buyVal.intValue * charInstance.classes.ownClass.value.banishGrowth)
    }

    /**
     * Loads data from file to this page's section.
     *
     * @param fileReader file to get the data from
     */
    fun loadSummoning(fileReader: BufferedReader){
        allSummoning.forEach{summonAbility -> summonAbility.loadAbility(fileReader = fileReader)}
    }

    /**
     * Writes pertinent data from this section to file.
     *
     * @param byteArray output stream for this section's data
     */
    fun writeSummoning(byteArray: ByteArrayOutputStream) {
        allSummoning.forEach{summonAbility -> summonAbility.writeAbility(byteArray = byteArray) }
    }
}