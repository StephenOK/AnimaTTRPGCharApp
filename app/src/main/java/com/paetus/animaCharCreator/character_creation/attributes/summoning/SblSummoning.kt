package com.paetus.animaCharCreator.character_creation.attributes.summoning

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Section containing stats involving a SBL Character's summoning abilities.
 *
 * @param charInstance object that holds all of the character's data
 */
class SblSummoning(val charInstance: SblChar): Summoning(charInstance){
    override val summon = SblSummonAbility(
        charInstance = charInstance,
        summoningIndex = 0,
        stringRef = R.string.summonTitle
    )
    override val control = SblSummonAbility(
        charInstance = charInstance,
        summoningIndex = 1,
        stringRef = R.string.controlTitle
    )
    override val bind = SblSummonAbility(
        charInstance = charInstance,
        summoningIndex = 2,
        stringRef = R.string.bindTitle
    )
    override val banish = SblSummonAbility(
        charInstance = charInstance,
        summoningIndex = 3,
        stringRef = R.string.banishTitle
    )

    /**
     * Gets the class's summoning DP cost.
     */
    override fun summonCost(): Int {
        return charInstance.getCharAtLevel().summoning.summonCost()
    }

    /**
     * Gets the class's control DP cost.
     */
    override fun controlCost(): Int {
        return charInstance.getCharAtLevel().summoning.controlCost()
    }

    /**
     * Gets the class's bind DP cost.
     */
    override fun bindCost(): Int {
        return charInstance.getCharAtLevel().summoning.bindCost()
    }

    /**
     * Gets the class's banish DP cost.
     */
    override fun banishCost(): Int {
        return charInstance.getCharAtLevel().summoning.banishCost()
    }
}